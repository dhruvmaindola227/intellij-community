// PSI_ELEMENT: com.intellij.psi.PsiClass
// OPTIONS: usages
public class Outer {
    public static class <caret>A {
        public String bar = "bar";
        public static String BAR = "BAR";

        public A() {

        }

        public void foo () {

        }

        public static void foos () {

        }
    }
}

// FIR_COMPARISON
