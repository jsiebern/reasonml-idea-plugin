package com.reason.ide.insight;

import com.intellij.codeInsight.completion.CompletionParameters;
import com.intellij.codeInsight.completion.CompletionProvider;
import com.intellij.codeInsight.completion.CompletionResultSet;
import com.intellij.codeInsight.completion.CompletionType;
import com.intellij.psi.PsiElement;
import com.intellij.util.ProcessingContext;
import com.reason.ide.insight.provider.*;
import com.reason.lang.MlTypes;
import com.reason.lang.ModulePathFinder;
import org.jetbrains.annotations.NotNull;

abstract class CompletionContributor extends com.intellij.codeInsight.completion.CompletionContributor {

    CompletionContributor(@NotNull MlTypes types, @NotNull ModulePathFinder modulePathFinder, @NotNull CompletionPatterns patterns) {
        //extend(CompletionType.BASIC, com.intellij.patterns.PlatformPatterns.psiElement(), new DebugCompletionProvider());

        extend(CompletionType.BASIC, patterns.open(), new ModuleCompletionProvider(types));
        extend(CompletionType.BASIC, patterns.freeExpression(), new FreeExpressionCompletionProvider());
        extend(CompletionType.BASIC, patterns.dotExpression(), new DotExpressionCompletionProvider(modulePathFinder));
        extend(CompletionType.BASIC, patterns.jsObject(), new ObjectCompletionProvider());
        extend(CompletionType.BASIC, patterns.jsxName(), new JsxNameCompletionProvider());
        extend(CompletionType.BASIC, patterns.jsxAttribute(), new JsxAttributeCompletionProvider());
    }

    @SuppressWarnings("unused")
    static class DebugCompletionProvider extends CompletionProvider<CompletionParameters> {
        @Override
        protected void addCompletions(@NotNull CompletionParameters parameters, ProcessingContext context, @NotNull CompletionResultSet result) {
            PsiElement position = parameters.getPosition();
            PsiElement originalPosition = parameters.getOriginalPosition();
            System.out.println("»» Completion: position=" + position + ", original=" + originalPosition);
            System.out.println("               inside=" + (originalPosition == null ? null : originalPosition.getParent()));
        }
    }
}
