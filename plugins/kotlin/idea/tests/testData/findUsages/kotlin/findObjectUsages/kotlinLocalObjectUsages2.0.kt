// FIR_COMPARISON
// PSI_ELEMENT: org.jetbrains.kotlin.psi.KtProperty
// OPTIONS: usages
fun foo(): Any {
    if (true) {
        val Bar<caret> = object {}

        return Bar
    }

    return Bar
}

object Bar {}

val x = Bar
