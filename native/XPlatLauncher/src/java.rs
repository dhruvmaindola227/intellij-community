use std::{mem, panic, thread};
use std::ffi::{c_void, CString};
use std::path::{Path, PathBuf};
use std::thread::JoinHandle;
use std::thread::sleep;
use std::time::Duration;
use jni::errors::Error;
use jni::objects::{JObject, JValue};
use log::{debug, error, info, Log};
use crate::{err_from_string, errors};
use crate::errors::{LauncherError, Result};

#[cfg(target_os = "macos")] use {
    core_foundation::base::{CFRelease, CFTypeRef, kCFAllocatorDefault, TCFTypeRef},
    core_foundation::date::{CFAbsoluteTime, CFTimeInterval},
    core_foundation::runloop::{CFRunLoopAddTimer, CFRunLoopGetCurrent, CFRunLoopRunInMode, CFRunLoopTimerCreate, CFRunLoopTimerRef, kCFRunLoopDefaultMode, kCFRunLoopRunFinished},
};

#[cfg(target_os = "windows")] use {
    crate::utils::canonical_non_unc,
    crate::utils::PathExt,
    std::env
};

#[derive(Clone)]
pub struct JvmLaunchParameters {
    pub jbr_home: PathBuf,
    pub vm_options: Vec<String>,
    pub class_path: Vec<String>
}

pub fn run_jvm_and_event_loop(java_home: &Path, vm_options: Vec<String>, args: Vec<String>) -> Result<()> {
    let java_home = java_home.to_path_buf();

    // JNI docs says that JVM should not be created on primordial thread
    // See Chapter 5: The Invocation API
    let join_handle = thread::spawn(move || {
        unsafe {
            debug!("Trying to spin up VM and call IntelliJ main from non-primordial thread");
            match intellij_main_thread(&java_home, vm_options, args) {
                Ok(_) => {
                    info!("JVM thread has exited.");
                    log::logger().flush();
                    std::process::exit(0);
                }
                Err(e) => {
                    error!("{e:?}");
                    log::logger().flush();
                    std::process::exit(1);
                }
            }
        }
    });

    // Using the primordial thread as at least Mac OS X needs it for GUI loop according to JBR
    // https://github.com/JetBrains/JetBrainsRuntime/blob/363650bbf48789e4c5f68840b66372442d3e481f/src/java.base/macosx/native/libjli/java_md_macosx.c#L328
    unsafe {
        run_event_loop()?
    };

    return Ok(());
}

unsafe fn intellij_main_thread(java_home: &Path, vm_options: Vec<String>, args: Vec<String>) -> Result<()> {
    debug!("Preparing JNI env");
    let jni_env = prepare_jni_env(&java_home, vm_options)?;

    debug!("Calling main");
    call_intellij_main(jni_env, args)
}

unsafe fn prepare_jni_env(
    java_home: &Path,
    vm_options: Vec<String>
) -> Result<jni::JNIEnv<'static>> {
    debug!("Resolving libjvm");
    let libjvm_path = get_libjvm(&java_home)?;
    debug!("libjvm resolved as {libjvm_path:?}");

    debug!("Loading libjvm");
    let libjvm = load_libjvm(libjvm_path)?;
    debug!("libjvm loaded");

    debug!("Getting JNI_CreateJavaVM symbol from libjvm");
    let create_jvm_call:
        libloading::Symbol<unsafe extern fn(*mut *mut jni_sys::JavaVM, *mut *mut c_void, *mut c_void) -> jni_sys::jint>
        = libjvm.get(b"JNI_CreateJavaVM")?;
    debug!("Got JNI_CreateJavaVM symbol from libjvm");

    let mut java_vm: *mut jni_sys::JavaVM = std::ptr::null_mut();
    let mut jni_env: *mut jni_sys::JNIEnv = std::ptr::null_mut();
    let args = get_jvm_init_args(vm_options)?;
    debug!("Constructed VM init args");

    debug!("Creating VM");
    let create_jvm_result = create_jvm_call(
        &mut java_vm as *mut *mut jni_sys::JavaVM,
        &mut jni_env as *mut *mut jni_sys::JNIEnv as *mut *mut c_void,
        &args as *const jni_sys::JavaVMInitArgs as *mut c_void);
    debug!("Create VM result={create_jvm_result}");

    errors::JniError::check_result(create_jvm_result)?;

    let jni_env = jni::JNIEnv::from_raw(jni_env)?;
    debug!("Got JNI env");

    Ok(jni_env)
}

pub fn call_intellij_main(jni_env: jni::JNIEnv, args: Vec<String>) -> Result<()> {
    let main_args = jni_env.new_object_array(
        args.len() as jni_sys::jsize,
        "java/lang/String",
        JObject::null()
    )?;

    for (i, arg) in args.iter().enumerate() {
        let j_string = jni_env.new_string(arg)?;
        jni_env.set_object_array_element(main_args, i as jni_sys::jsize, j_string)?;
    }

    let method_call_args = vec![JValue::from(main_args)];

    debug!("Calling IntelliJ main");
    match jni_env.call_static_method("com/intellij/idea/Main", "main", "([Ljava/lang/String;)V", &method_call_args) {
        Ok(_) => {}
        Err(e) => {
            match e {
                Error::JavaException => {
                    jni_env.exception_describe()?;
                }
                _ => { return Err(LauncherError::JniRsError(e)) }
            }
        }
    };

    Ok(())
}

#[cfg(target_os = "windows")]
unsafe fn load_libjvm(libjvm_path: PathBuf) -> Result<libloading::Library> {
    // TODO: pass the bin
    let jbr_bin = libjvm_path.parent_or_err()?.parent_or_err()?;


    // using UNC as the current directory leads to crash when starting JVM
    let non_unc_bin = canonical_non_unc(&jbr_bin)?;
    // SetCurrentDirectoryA
    env::set_current_dir(&non_unc_bin)?;
    debug!("Set working dir as '{non_unc_bin:?} so that libjvm can find the dependencies");

    // using UNC for libjvm leads to crash when trying to resolve jimage
    // classloader.cpp:   guarantee(name != NULL, "jimage file name is null");
    let load_path = canonical_non_unc(&libjvm_path)?;
    debug!("Loading libvjm by path {load_path:?}");

    match libloading::Library::new(load_path) {
        Ok(l) => { Ok(l)}
        Err(e) => { Err(LauncherError::LibloadingError(e))}
    }
}

#[cfg(any(target_os = "linux", target_os = "macos"))]
unsafe fn load_libjvm(libjvm_path: PathBuf) -> Result<libloading::Library> {
    match libloading::Library::new(libjvm_path.as_os_str()) {
        Ok(l) => { Ok(l)}
        Err(e) => { Err(LauncherError::LibloadingError(e))}
    }
}

fn get_jvm_init_args(vm_options: Vec<String>) -> Result<jni_sys::JavaVMInitArgs> {
    let joined = vm_options.join("\n");
    debug!("Using following vmoptions as VM init args: {joined}");

    let jni_opts = get_jni_vm_opts(vm_options)?;
    let args = jni_sys::JavaVMInitArgs {
        version: jni_sys::JNI_VERSION_1_8,
        nOptions: jni_opts.len() as jni_sys::jint,
        options: jni_opts.as_ptr() as *mut jni_sys::JavaVMOption,
        ignoreUnrecognized: true as jni_sys::jboolean
    };

    // TODO: PhantomData?
    // vm_init_args live longer then jni_opts vec
    mem::forget(jni_opts);

    Ok(args)
}

fn get_libjvm(java_home: &Path) -> Result<PathBuf> {
    debug!("Resolving libjvm from java home: {java_home:?}");

    let libjvm = get_libjvm_path(java_home);
    if !libjvm.exists() {
        let message = format!("libvjm not found at path {libjvm:?}");
        return err_from_string(message);
    }
    debug!("Found libjvm at {libjvm:?}");

    let path = std::fs::canonicalize(libjvm)?;
    debug!("Canonical libvjm: {path:?}");

    Ok(path)
}

#[cfg(target_os = "windows")]
fn get_libjvm_path(java_home: &Path) -> PathBuf {
    // TODO: client/jvm.dll ?
    java_home
        .join("bin")
        .join("server")
        .join("jvm.dll")
}

#[cfg(target_os = "macos")]
fn get_libjvm_path(java_home: &Path) -> PathBuf {
    java_home
        .join("lib")
        .join("server")
        .join("libjvm.dylib")
}

#[cfg(target_os = "linux")]
fn get_libjvm_path(java_home: &Path) -> PathBuf {
    java_home
        .join("lib")
        .join("server")
        .join("libjvm.so")
}

fn get_jni_vm_opts(opts: Vec<String>) -> Result<Vec<jni_sys::JavaVMOption>> {
    let mut jni_opts = Vec::with_capacity(opts.len());
    for opt in opts {
        let option_string = CString::new(opt.as_str())?;
        let jvm_opt = jni_sys::JavaVMOption {
            // TODO: possible memory leak
            optionString: option_string.into_raw(),
            extraInfo: std::ptr::null_mut(),
        };
        jni_opts.push(jvm_opt);
    }

    Ok(jni_opts)
}

#[cfg(target_os = "windows")]
fn run_event_loop() -> Result<()> {
    loop {

    }
}

#[cfg(target_os = "macos")]
unsafe fn run_event_loop() -> Result<()> {
    #[allow(non_snake_case)]
    let FOREVER = 1e20;

    extern "C" fn timer_empty(_timer: CFRunLoopTimerRef, _info: *mut c_void) {}

    let timer = CFRunLoopTimerCreate(
        kCFAllocatorDefault,
        FOREVER,
        0.0 as CFTimeInterval,
        0,
        0,
        timer_empty,
        std::ptr::null_mut()
    );

    CFRunLoopAddTimer(CFRunLoopGetCurrent(), timer, kCFRunLoopDefaultMode);
    CFRelease(timer.as_void_ptr());

    loop {
        let result = CFRunLoopRunInMode(
            kCFRunLoopDefaultMode,
            FOREVER,
            0 as core_foundation::base::Boolean
        );

        if result == kCFRunLoopRunFinished {
            info!("Core foundation loop has exited");
            break;
        }
    }

    Ok(())
}

#[cfg(target_os = "linux")]
fn run_event_loop() -> Result<()> {
    debug!("Running event loop on primordial thread");
    // TODO: proper run loop so that we can exit
    loop {
        sleep(Duration::from_secs(1))

        // let r = intellij_main_call.load(Ordering::Relaxed);
        // match &*r {
        //     Ok(_) => { sleep(Duration::from_secs(1)) }
        //     Err(e) => { return Err(LauncherError::OtherError(OtherLauncherError{message: format!("{e:?}").to_string()}));}
        // }
    }
}