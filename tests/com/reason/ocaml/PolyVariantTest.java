package com.reason.ocaml;

import com.intellij.psi.util.PsiTreeUtil;
import com.reason.BaseParsingTestCase;
import com.reason.lang.core.psi.PsiNamedElement;
import com.reason.lang.core.psi.PsiPatternMatch;
import com.reason.lang.core.psi.impl.PsiFileModuleImpl;
import com.reason.lang.ocaml.OclParserDefinition;

import java.util.Collection;

public class PolyVariantTest extends BaseParsingTestCase {
    public PolyVariantTest() {
        super("", "ml", new OclParserDefinition());
    }

    public void testPatternMatchConstant() {
        PsiFileModuleImpl psiFileModule = parseCode("let unwrapValue = function " +
                "  | `String s -> toJsUnsafe s " +
                "  | `Bool b -> toJsUnsafe (Js.Boolean.to_js_boolean b)");

        Collection<PsiNamedElement> expressions = psiFileModule.getExpressions();
        assertEquals(1, expressions.size());

        Collection<PsiPatternMatch> matches = PsiTreeUtil.findChildrenOfType(first(expressions), PsiPatternMatch.class);
        assertEquals(2, matches.size());
    }

}
