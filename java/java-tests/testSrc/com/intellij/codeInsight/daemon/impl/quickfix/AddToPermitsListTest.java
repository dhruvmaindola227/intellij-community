// Copyright 2000-2022 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.intellij.codeInsight.daemon.impl.quickfix;

import com.intellij.psi.PsiClass;
import com.intellij.testFramework.LightProjectDescriptor;
import com.intellij.testFramework.fixtures.LightJavaCodeInsightFixtureTestCase;
import org.jetbrains.annotations.NotNull;

public class AddToPermitsListTest extends LightJavaCodeInsightFixtureTestCase {
  @Override
  protected @NotNull LightProjectDescriptor getProjectDescriptor() {
    return LightJavaCodeInsightFixtureTestCase.JAVA_LATEST_WITH_LATEST_JDK;
  }

  public void testNoPermitsList() {
    PsiClass aClass = myFixture.addClass("sealed class A {}");
    myFixture.configureByText("B.java", "final class B extends <caret>A {}");
    invokeFix("Add 'B' to permits list of a sealed class 'A'");
    assertEquals("sealed class A permits B {}", aClass.getText());
  }

  public void testUnorderedPermitsList() {
    PsiClass aClass = myFixture.addClass("sealed class A permits C {}");
    myFixture.addClass("non-sealed class C extends A {}");
    myFixture.configureByText("B.java", "final class B extends A<caret> {}");
    invokeFix("Add 'B' to permits list of a sealed class 'A'");
    assertEquals("sealed class A permits B, C {}", aClass.getText());
  }

  public void testMultipleInheritors() {
    PsiClass aClass = myFixture.addClass("sealed class A {}");
    myFixture.configureByText("C.java", "non-sealed class C extends <caret>A {}");
    invokeFix("Add 'C' to permits list of a sealed class 'A'");
    assertEquals("sealed class A permits C {}", aClass.getText());
    myFixture.configureByText("B.java", "final class B extends <caret>A {}");
    invokeFix("Add 'B' to permits list of a sealed class 'A'");
    assertEquals("sealed class A permits B, C {}", aClass.getText());
  }

  public void testMultipleParents() {
    PsiClass aClass = myFixture.addClass("sealed interface A {}");
    myFixture.addClass("interface B {}");
    myFixture.configureByText("C.java", "final class C implements A<caret>, B {}");
    invokeFix("Add 'C' to permits list of a sealed class 'A'");
    assertEquals("sealed interface A permits C {}", aClass.getText());
  }

  private void invokeFix(String hint) {
    myFixture.launchAction(myFixture.findSingleIntention(hint));
  }
}
