/*
 * Copyright 2003-2021 Dave Griffith, Bas Leijdekkers
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.siyeh.ig.errorhandling;

import com.intellij.codeInspection.ui.InspectionOptionsPanel;
import com.intellij.codeInspection.ui.ListTable;
import com.intellij.codeInspection.ui.ListWrappingTableModel;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.*;
import com.intellij.psi.util.PsiTreeUtil;
import com.siyeh.InspectionGadgetsBundle;
import com.siyeh.ig.BaseInspection;
import com.siyeh.ig.BaseInspectionVisitor;
import com.siyeh.ig.InspectionGadgetsFix;
import com.siyeh.ig.fixes.SuppressForTestsScopeFix;
import com.siyeh.ig.ui.ExternalizableStringSet;
import com.siyeh.ig.ui.UiUtils;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.util.List;

public class BadExceptionCaughtInspection extends BaseInspection {

  @SuppressWarnings("PublicField")
  public String exceptionsString = "";
  @SuppressWarnings("PublicField")
  public final ExternalizableStringSet exceptions =
    new ExternalizableStringSet(
      "java.lang.NullPointerException",
      "java.lang.IllegalMonitorStateException",
      "java.lang.ArrayIndexOutOfBoundsException",
      "java.lang.IndexOutOfBoundsException",
      "java.util.ConcurrentModificationException"
    );

  public BadExceptionCaughtInspection() {
    if (!exceptionsString.isEmpty()) {
      exceptions.clear();
      final List<String> strings = StringUtil.split(exceptionsString, ",");
      exceptions.addAll(strings);
      exceptionsString = "";
    }
  }

  @Override
  protected InspectionGadgetsFix buildFix(Object... infos) {
    final PsiElement context = (PsiElement)infos[0];
    return SuppressForTestsScopeFix.build(this, context);
  }

  @Override
  public JComponent createOptionsPanel() {
    final ListTable table =
      new ListTable(new ListWrappingTableModel(exceptions, InspectionGadgetsBundle.message("exception.class.column.name")));
    final var panel = new InspectionOptionsPanel();
    panel.addGrowing(UiUtils.createAddRemoveTreeClassChooserPanel(
      InspectionGadgetsBundle.message("choose.exception.class"),
      InspectionGadgetsBundle.message("choose.exception.label"),
      table,
      true,
      CommonClassNames.JAVA_LANG_THROWABLE));
    return panel;
  }

  @Override
  @NotNull
  public String getID() {
    return "ProhibitedExceptionCaught";
  }

  @Override
  @NotNull
  public String buildErrorString(Object... infos) {
    return InspectionGadgetsBundle.message("bad.exception.caught.problem.descriptor");
  }

  @Override
  public BaseInspectionVisitor buildVisitor() {
    return new BadExceptionCaughtVisitor();
  }

  private class BadExceptionCaughtVisitor extends BaseInspectionVisitor {

    @Override
    public void visitCatchSection(@NotNull PsiCatchSection section) {
      super.visitCatchSection(section);
      final PsiParameter parameter = section.getParameter();
      if (parameter == null) {
        return;
      }
      final PsiTypeElement typeElement = parameter.getTypeElement();
      if (typeElement == null) {
        return;
      }
      final PsiTypeElement[] childTypeElements = PsiTreeUtil.getChildrenOfType(typeElement, PsiTypeElement.class);
      if (childTypeElements != null) {
        for (PsiTypeElement childTypeElement : childTypeElements) {
          checkTypeElement(childTypeElement);
        }
      }
      else {
        checkTypeElement(typeElement);
      }
    }

    private void checkTypeElement(PsiTypeElement typeElement) {
      final PsiType type = typeElement.getType();
      if (exceptions.contains(type.getCanonicalText())) {
        registerError(typeElement, typeElement);
      }
    }
  }
}
