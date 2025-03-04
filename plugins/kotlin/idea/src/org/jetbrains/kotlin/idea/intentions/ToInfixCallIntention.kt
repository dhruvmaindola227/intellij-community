// Copyright 2000-2022 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

package org.jetbrains.kotlin.idea.intentions

import com.intellij.openapi.editor.Editor
import org.jetbrains.kotlin.descriptors.FunctionDescriptor
import org.jetbrains.kotlin.idea.base.resources.KotlinBundle
import org.jetbrains.kotlin.idea.caches.resolve.safeAnalyzeNonSourceRootCode
import org.jetbrains.kotlin.idea.codeinsight.api.classic.intentions.SelfTargetingIntention
import org.jetbrains.kotlin.psi.*
import org.jetbrains.kotlin.psi.psiUtil.getQualifiedExpressionForSelector
import org.jetbrains.kotlin.resolve.calls.util.getResolvedCall
import org.jetbrains.kotlin.resolve.lazy.BodyResolveMode

class ToInfixCallIntention : SelfTargetingIntention<KtCallExpression>(
    KtCallExpression::class.java,
    KotlinBundle.lazyMessage("replace.with.infix.function.call")
) {
    override fun isApplicableTo(element: KtCallExpression, caretOffset: Int): Boolean {
        val calleeExpr = element.calleeExpression as? KtNameReferenceExpression ?: return false
        if (!calleeExpr.textRange.containsOffset(caretOffset)) return false

        val dotQualified = element.getQualifiedExpressionForSelector() as? KtDotQualifiedExpression ?: return false

        if (element.typeArgumentList != null) return false

        val argument = element.valueArguments.singleOrNull() ?: return false
        if (argument.isNamed()) return false
        if (argument.getArgumentExpression() == null) return false

        val bindingContext = element.safeAnalyzeNonSourceRootCode(BodyResolveMode.PARTIAL)
        val resolvedCall = element.getResolvedCall(bindingContext) ?: return false
        val function = resolvedCall.resultingDescriptor as? FunctionDescriptor ?: return false
        if (!function.isInfix) return false

        // check that receiver has type to filter out calls with package/java class qualifier
        if (bindingContext.getType(dotQualified.receiverExpression) == null) return false

        return true
    }

    override fun applyTo(element: KtCallExpression, editor: Editor?) {
        val dotQualified = element.parent as KtDotQualifiedExpression
        val receiver = dotQualified.receiverExpression
        val argument = element.valueArguments.single().getArgumentExpression()!!
        val name = element.calleeExpression!!.text

        val newCall = KtPsiFactory(element).createExpressionByPattern("$0 $name $1", receiver, argument)
        dotQualified.replace(newCall)
    }
}
