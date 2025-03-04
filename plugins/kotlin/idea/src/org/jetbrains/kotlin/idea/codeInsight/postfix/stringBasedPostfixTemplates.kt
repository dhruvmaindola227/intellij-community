// Copyright 2000-2022 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

package org.jetbrains.kotlin.idea.codeInsight.postfix

import com.intellij.codeInsight.template.Template
import com.intellij.codeInsight.template.impl.ConstantNode
import com.intellij.codeInsight.template.impl.MacroCallNode
import com.intellij.codeInsight.template.postfix.templates.PostfixTemplateExpressionSelector
import com.intellij.codeInsight.template.postfix.templates.PostfixTemplateProvider
import com.intellij.codeInsight.template.postfix.templates.StringBasedPostfixTemplate
import com.intellij.psi.PsiElement
import org.jetbrains.kotlin.builtins.KotlinBuiltIns
import org.jetbrains.kotlin.idea.caches.resolve.getResolutionFacade
import org.jetbrains.kotlin.idea.core.IterableTypesDetection
import org.jetbrains.kotlin.idea.liveTemplates.macro.SuggestVariableNameMacro
import org.jetbrains.kotlin.idea.resolve.ideService
import org.jetbrains.kotlin.idea.util.getResolutionScope
import org.jetbrains.kotlin.psi.KtExpression
import org.jetbrains.kotlin.psi.KtReturnExpression
import org.jetbrains.kotlin.psi.KtThrowExpression
import org.jetbrains.kotlin.resolve.BindingContext
import org.jetbrains.kotlin.resolve.calls.util.getType
import org.jetbrains.kotlin.types.KotlinType
import org.jetbrains.kotlin.types.typeUtil.isBoolean

internal abstract class ConstantStringBasedPostfixTemplate(
    name: String,
    desc: String,
    private val template: String,
    selector: PostfixTemplateExpressionSelector,
    provider: PostfixTemplateProvider
) : StringBasedPostfixTemplate(name, desc, selector, provider) {
    override fun getTemplateString(element: PsiElement) = template

    override fun getElementToRemove(expr: PsiElement?) = expr
}

internal abstract class KtWrapWithCallPostfixTemplate(val functionName: String, provider: PostfixTemplateProvider) :
    ConstantStringBasedPostfixTemplate(
        functionName,
        "$functionName(expr)",
        "$functionName(\$expr$)\$END$",
        createExpressionSelectorWithComplexFilter { expression, _ -> expression !is KtReturnExpression },
        provider
    )

internal class KtWrapWithListOfPostfixTemplate(provider: PostfixTemplateProvider) : KtWrapWithCallPostfixTemplate("listOf", provider)
internal class KtWrapWithSetOfPostfixTemplate(provider: PostfixTemplateProvider) : KtWrapWithCallPostfixTemplate("setOf", provider)
internal class KtWrapWithArrayOfPostfixTemplate(provider: PostfixTemplateProvider) : KtWrapWithCallPostfixTemplate("arrayOf", provider)
internal class KtWrapWithSequenceOfPostfixTemplate(provider: PostfixTemplateProvider) : KtWrapWithCallPostfixTemplate("sequenceOf", provider)

internal class KtForEachPostfixTemplate(
    name: String,
    provider: PostfixTemplateProvider
) : ConstantStringBasedPostfixTemplate(
    name,
    "for (item in expr)",
    "for (\$name$ in \$expr$) {\n    \$END$\n}",
    createExpressionSelectorWithComplexFilter(statementsOnly = true, predicate = KtExpression::hasIterableType),
    provider
) {
    override fun setVariables(template: Template, element: PsiElement) {
        val name = MacroCallNode(SuggestVariableNameMacro())
        template.addVariable("name", name, ConstantNode("item"), true)
    }
}

private fun KtExpression.hasIterableType(bindingContext: BindingContext): Boolean {
    val resolutionFacade = getResolutionFacade()
    val type = getType(bindingContext) ?: return false
    val scope = getResolutionScope(bindingContext, resolutionFacade)
    val detector = resolutionFacade.ideService<IterableTypesDetection>().createDetector(scope)
    return detector.isIterable(type)
}

internal class KtAssertPostfixTemplate(provider: PostfixTemplateProvider) : ConstantStringBasedPostfixTemplate(
    "assert",
    "assert(expr) { \"\" }",
    "assert(\$expr$) { \"\$END$\" }",
    createExpressionSelector(statementsOnly = true, typePredicate = KotlinType::isBoolean),
    provider
)

internal class KtParenthesizedPostfixTemplate(provider: PostfixTemplateProvider) : ConstantStringBasedPostfixTemplate(
    "par", "(expr)",
    "(\$expr$)\$END$",
    createExpressionSelector(),
    provider
)

internal class KtSoutPostfixTemplate(provider: PostfixTemplateProvider) : ConstantStringBasedPostfixTemplate(
    "sout",
    "println(expr)",
    "println(\$expr$)\$END$",
    createExpressionSelector(statementsOnly = true),
    provider
)

internal class KtReturnPostfixTemplate(provider: PostfixTemplateProvider) : ConstantStringBasedPostfixTemplate(
    "return",
    "return expr",
    "return \$expr$\$END$",
    createExpressionSelector(statementsOnly = true),
    provider
)

internal class KtWhilePostfixTemplate(provider: PostfixTemplateProvider) : ConstantStringBasedPostfixTemplate(
    "while",
    "while (expr) {}",
    "while (\$expr$) {\n\$END$\n}",
    createExpressionSelector(statementsOnly = true, typePredicate = KotlinType::isBoolean),
    provider
)

internal class KtSpreadPostfixTemplate(provider: PostfixTemplateProvider) : ConstantStringBasedPostfixTemplate(
    "spread",
    "*expr",
    "*\$expr$\$END$",
    createExpressionSelector(typePredicate = { KotlinBuiltIns.isArray(it) || KotlinBuiltIns.isPrimitiveArray(it) }),
    provider
)

internal class KtArgumentPostfixTemplate(provider: PostfixTemplateProvider) : ConstantStringBasedPostfixTemplate(
    "arg",
    "functionCall(expr)",
    "\$call$(\$expr$\$END$)",
    createExpressionSelectorWithComplexFilter { expression, _ -> expression !is KtReturnExpression && expression !is KtThrowExpression },
    provider
) {
    override fun setVariables(template: Template, element: PsiElement) {
        template.addVariable("call", "", "", true)
    }
}

internal class KtWithPostfixTemplate(provider: PostfixTemplateProvider) : ConstantStringBasedPostfixTemplate(
    "with",
    "with(expr) {}",
    "with(\$expr$) {\n\$END$\n}",
    createExpressionSelector(),
    provider
)
