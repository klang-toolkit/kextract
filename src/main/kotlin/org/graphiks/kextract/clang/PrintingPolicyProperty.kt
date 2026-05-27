package org.graphiks.kextract.clang

import org.graphiks.kextract.clang.libclang.*
import java.util.NoSuchElementException

enum class PrintingPolicyProperty(val value: Int) {
    Indentation(CXPrintingPolicy_Indentation()),
    SuppressSpecifiers(CXPrintingPolicy_SuppressSpecifiers()),
    SuppressTagKeyword(CXPrintingPolicy_SuppressTagKeyword()),
    IncludeTagDefinition(CXPrintingPolicy_IncludeTagDefinition()),
    SuppressScope(CXPrintingPolicy_SuppressScope()),
    SuppressUnwrittenScope(CXPrintingPolicy_SuppressUnwrittenScope()),
    SuppressInitializers(CXPrintingPolicy_SuppressInitializers()),
    ConstantArraySizeAsWritten(CXPrintingPolicy_ConstantArraySizeAsWritten()),
    AnonymousTagLocations(CXPrintingPolicy_AnonymousTagLocations()),
    SuppressStrongLifetime(CXPrintingPolicy_SuppressStrongLifetime()),
    SuppressLifetimeQualifiers(CXPrintingPolicy_SuppressLifetimeQualifiers()),
    SuppressTemplateArgsInCXXConstructors(CXPrintingPolicy_SuppressTemplateArgsInCXXConstructors()),
    Bool(CXPrintingPolicy_Bool()),
    Restrict(CXPrintingPolicy_Restrict()),
    Alignof(CXPrintingPolicy_Alignof()),
    UnderscoreAlignof(CXPrintingPolicy_UnderscoreAlignof()),
    UseVoidForZeroParams(CXPrintingPolicy_UseVoidForZeroParams()),
    TerseOutput(CXPrintingPolicy_TerseOutput()),
    PolishForDeclaration(CXPrintingPolicy_PolishForDeclaration()),
    Half(CXPrintingPolicy_Half()),
    MSWChar(CXPrintingPolicy_MSWChar()),
    IncludeNewlines(CXPrintingPolicy_IncludeNewlines()),
    MSVCFormatting(CXPrintingPolicy_MSVCFormatting()),
    ConstantsAsWritten(CXPrintingPolicy_ConstantsAsWritten()),
    SuppressImplicitBase(CXPrintingPolicy_SuppressImplicitBase()),
    FullyQualifiedName(CXPrintingPolicy_FullyQualifiedName()),
    LastProperty(CXPrintingPolicy_LastProperty());

    companion object {
        private val lookup = entries.associateBy { it.value }

        @JvmStatic
        fun valueOf(value: Int): PrintingPolicyProperty =
            lookup[value] ?: throw NoSuchElementException("Invalid PrintingPolicyProperty value: $value")
    }
}
