// Copyright 2000-2022 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

package org.jetbrains.kotlin.projectModel

import com.intellij.openapi.roots.libraries.PersistentLibraryKind
import org.jetbrains.kotlin.idea.base.platforms.KotlinCommonLibraryKind
import org.jetbrains.kotlin.idea.base.platforms.KotlinJavaScriptLibraryKind
import org.jetbrains.kotlin.idea.base.plugin.artifacts.TestKotlinArtifacts
import org.jetbrains.kotlin.idea.test.IDEA_TEST_DATA_DIR
import org.jetbrains.kotlin.platform.CommonPlatforms
import org.jetbrains.kotlin.platform.TargetPlatform
import org.jetbrains.kotlin.platform.js.JsPlatforms
import org.jetbrains.kotlin.platform.jvm.JvmPlatforms
import org.jetbrains.kotlin.utils.Printer
import java.io.File

open class ProjectResolveModel(val modules: List<ResolveModule>) {
    open class Builder {
        val modules: MutableList<ResolveModule.Builder> = mutableListOf()

        open fun build(): ProjectResolveModel = ProjectResolveModel(modules.map { it.build() })
    }

}

open class ResolveModule(
    val name: String,
    val root: File,
    val platform: TargetPlatform,
    val dependencies: List<ResolveDependency>,
    val testRoot: File? = null,
    val additionalCompilerArgs: String? = null
) {
    final override fun toString(): String {
        return buildString { renderDescription(Printer(this)) }
    }

    open fun renderDescription(printer: Printer) {
        printer.println("Module $name")
        printer.pushIndent()
        printer.println("platform=$platform")
        printer.println("root=${root.absolutePath}")
        if (testRoot != null) printer.println("testRoot=${testRoot.absolutePath}")
        printer.println("dependencies=${dependencies.joinToString { it.to.name }}")
        if (additionalCompilerArgs != null)  printer.println("additionalCompilerArgs=$additionalCompilerArgs")
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ResolveModule

        if (name != other.name) return false

        return true
    }

    override fun hashCode(): Int {
        return name.hashCode()
    }

    open class Builder {
        private var state: State = State.NOT_BUILT
        private var cachedResult: ResolveModule? = null

        var name: String? = null
        var root: File? = null
        var platform: TargetPlatform? = null
        val dependencies: MutableList<ResolveDependency.Builder> = mutableListOf()
        var testRoot: File? = null
        var additionalCompilerArgs: String? = null

        open fun build(): ResolveModule {
            if (state == State.BUILT) return cachedResult!!

            require(state == State.NOT_BUILT) { "Re-building module $this with name $name (root at $root)" }
            state = State.BUILDING

            val builtDependencies = dependencies.map { it.build() }
            cachedResult = ResolveModule(name!!, root!!, platform!!, builtDependencies, testRoot, additionalCompilerArgs= additionalCompilerArgs)
            state = State.BUILT

            return cachedResult!!
        }

        enum class State {
            NOT_BUILT,
            BUILDING,
            BUILT
        }
    }
}

sealed class ResolveLibrary(
    name: String,
    root: File,
    platform: TargetPlatform,
    val kind: PersistentLibraryKind<*>?
) : ResolveModule(name, root, platform, emptyList()) {
    class Builder(val target: ResolveLibrary) : ResolveModule.Builder() {
        override fun build(): ResolveModule = target
    }
}

sealed class Stdlib(
    name: String,
    root: File,
    platform: TargetPlatform,
    kind: PersistentLibraryKind<*>?
) : ResolveLibrary(name, root, platform, kind) {

    object CommonStdlib : Stdlib(
        "stdlib-common",
        TestKotlinArtifacts.kotlinStdlibCommon,
        CommonPlatforms.defaultCommonPlatform,
        KotlinCommonLibraryKind
    )

    object JvmStdlib : Stdlib(
        "stdlib-jvm",
        TestKotlinArtifacts.kotlinStdlib,
        JvmPlatforms.defaultJvmPlatform,
        null
    )

    object JsStdlib : Stdlib(
        "stdlib-js",
        TestKotlinArtifacts.kotlinStdlibJs,
        JsPlatforms.defaultJsPlatform,
        KotlinJavaScriptLibraryKind
    )
}

sealed class KotlinTest(
    name: String,
    root: File,
    platform: TargetPlatform,
    kind: PersistentLibraryKind<*>?
) : ResolveLibrary(name, root, platform, kind) {

    object JsKotlinTest : KotlinTest(
        "kotlin-test-js",
        TestKotlinArtifacts.kotlinTestJs,
        JsPlatforms.defaultJsPlatform,
        KotlinJavaScriptLibraryKind
    )

    object JvmKotlinTest : KotlinTest(
        "kotlin-test-jvm",
        TestKotlinArtifacts.kotlinTestJunit,
        JvmPlatforms.defaultJvmPlatform,
        null
    )

    object JustKotlinTest : KotlinTest(
        "kotlin-test",
        TestKotlinArtifacts.kotlinTest,
        JvmPlatforms.defaultJvmPlatform,
        null
    )

    object Junit : KotlinTest(
        "junit",
        File("$IDEA_TEST_DATA_DIR/lib/junit-4.12.jar"),
        JvmPlatforms.defaultJvmPlatform,
        null
    )
}

interface ResolveSdk

object FullJdk : ResolveLibrary(
    "full-jdk",
    File("fake file for full jdk"),
    JvmPlatforms.defaultJvmPlatform,
    null
), ResolveSdk

object MockJdk : ResolveLibrary(
    "mock-jdk",
    File("fake file for mock jdk"),
    JvmPlatforms.defaultJvmPlatform,
    null
), ResolveSdk

object KotlinSdk : ResolveLibrary(
    "kotlin-sdk",
    File("fake file for kotlin sdk"),
    CommonPlatforms.defaultCommonPlatform,
    null,
), ResolveSdk

open class ResolveDependency(val to: ResolveModule, val kind: Kind) {
    open class Builder {
        var to: ResolveModule.Builder = ResolveModule.Builder()
        var kind: Kind? = null

        open fun build(): ResolveDependency = ResolveDependency(to.build(), kind!!)
    }

    enum class Kind {
        DEPENDS_ON,
        DEPENDENCY,
    }
}