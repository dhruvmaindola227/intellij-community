import p1.sleep

// "Import" "true"
// WITH_STDLIB
// FULL_JDK
// ERROR: Unresolved reference: sleep
/* IGNORE_FIR */

fun usage() {
    sleep<caret>()
}
