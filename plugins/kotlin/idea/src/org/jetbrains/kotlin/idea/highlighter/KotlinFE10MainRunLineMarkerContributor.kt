// Copyright 2000-2022 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

package org.jetbrains.kotlin.idea.highlighter

import org.jetbrains.kotlin.idea.base.facet.platform.platform
import org.jetbrains.kotlin.idea.base.util.module
import org.jetbrains.kotlin.idea.base.lineMarkers.run.AbstractKotlinMainRunLineMarkerContributor
import org.jetbrains.kotlin.idea.base.codeInsight.tooling.tooling
import org.jetbrains.kotlin.platform.idePlatformKind
import org.jetbrains.kotlin.platform.isCommon
import org.jetbrains.kotlin.psi.KtNamedFunction

internal class KotlinFE10MainRunLineMarkerContributor : AbstractKotlinMainRunLineMarkerContributor() {
    override fun acceptEntryPoint(function: KtNamedFunction): Boolean {
        val platform = function.containingKtFile.module?.platform ?: return false
        if (platform.isCommon()) return false

        return platform.idePlatformKind.tooling.acceptsAsEntryPoint(function)
    }
}
