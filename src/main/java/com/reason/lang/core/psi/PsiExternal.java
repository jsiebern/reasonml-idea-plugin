package com.reason.lang.core.psi;

import com.reason.lang.core.HMSignature;
import org.jetbrains.annotations.NotNull;

public interface PsiExternal extends PsiNamedElement, PsiStructuredElement {
    @NotNull
    HMSignature getSignature();
}
