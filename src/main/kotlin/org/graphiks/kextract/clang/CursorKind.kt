package org.graphiks.kextract.clang

import org.graphiks.kextract.clang.libclang.*
import java.util.NoSuchElementException

enum class CursorKind(val value: Int) {
    UnexposedDecl(CXCursor_UnexposedDecl()),
    StructDecl(CXCursor_StructDecl()),
    UnionDecl(CXCursor_UnionDecl()),
    ClassDecl(CXCursor_ClassDecl()),
    EnumDecl(CXCursor_EnumDecl()),
    FieldDecl(CXCursor_FieldDecl()),
    EnumConstantDecl(CXCursor_EnumConstantDecl()),
    FunctionDecl(CXCursor_FunctionDecl()),
    VarDecl(CXCursor_VarDecl()),
    ParmDecl(CXCursor_ParmDecl()),
    TypedefDecl(CXCursor_TypedefDecl()),
    Namespace(CXCursor_Namespace()),
    TypeRef(CXCursor_TypeRef()),
    IntegerLiteral(CXCursor_IntegerLiteral()),
    FloatingLiteral(CXCursor_FloatingLiteral()),
    ImaginaryLiteral(CXCursor_ImaginaryLiteral()),
    StringLiteral(CXCursor_StringLiteral()),
    CharacterLiteral(CXCursor_CharacterLiteral()),
    UnexposedAttr(CXCursor_UnexposedAttr()),
    IBActionAttr(CXCursor_IBActionAttr()),
    IBOutletAttr(CXCursor_IBOutletAttr()),
    IBOutletCollectionAttr(CXCursor_IBOutletCollectionAttr()),
    CXXFinalAttr(CXCursor_CXXFinalAttr()),
    CXXOverrideAttr(CXCursor_CXXOverrideAttr()),
    AnnotateAttr(CXCursor_AnnotateAttr()),
    AsmLabelAttr(CXCursor_AsmLabelAttr()),
    PackedAttr(CXCursor_PackedAttr()),
    PureAttr(CXCursor_PureAttr()),
    ConstAttr(CXCursor_ConstAttr()),
    NoDuplicateAttr(CXCursor_NoDuplicateAttr()),
    CUDAConstantAttr(CXCursor_CUDAConstantAttr()),
    CUDADeviceAttr(CXCursor_CUDADeviceAttr()),
    CUDAGlobalAttr(CXCursor_CUDAGlobalAttr()),
    CUDAHostAttr(CXCursor_CUDAHostAttr()),
    CUDASharedAttr(CXCursor_CUDASharedAttr()),
    VisibilityAttr(CXCursor_VisibilityAttr()),
    DLLExport(CXCursor_DLLExport()),
    DLLImport(CXCursor_DLLImport()),
    NSReturnsRetained(CXCursor_NSReturnsRetained()),
    NSReturnsNotRetained(CXCursor_NSReturnsNotRetained()),
    NSReturnsAutoreleased(CXCursor_NSReturnsAutoreleased()),
    NSConsumesSelf(CXCursor_NSConsumesSelf()),
    NSConsumed(CXCursor_NSConsumed()),
    ObjCException(CXCursor_ObjCException()),
    ObjCNSObject(CXCursor_ObjCNSObject()),
    ObjCIndependentClass(CXCursor_ObjCIndependentClass()),
    ObjCPreciseLifetime(CXCursor_ObjCPreciseLifetime()),
    ObjCReturnsInnerPointer(CXCursor_ObjCReturnsInnerPointer()),
    ObjCRequiresSuper(CXCursor_ObjCRequiresSuper()),
    ObjCRootClass(CXCursor_ObjCRootClass()),
    ObjCSubclassingRestricted(CXCursor_ObjCSubclassingRestricted()),
    ObjCExplicitProtocolImpl(CXCursor_ObjCExplicitProtocolImpl()),
    ObjCDesignatedInitializer(CXCursor_ObjCDesignatedInitializer()),
    ObjCRuntimeVisible(CXCursor_ObjCRuntimeVisible()),
    ObjCBoxable(CXCursor_ObjCBoxable()),
    FlagEnum(CXCursor_FlagEnum()),
    ConvergentAttr(CXCursor_ConvergentAttr()),
    WarnUnusedAttr(CXCursor_WarnUnusedAttr()),
    WarnUnusedResultAttr(CXCursor_WarnUnusedResultAttr()),
    AlignedAttr(CXCursor_AlignedAttr()),
    // ObjC declaration kinds
    ObjCInterfaceDecl(CXCursor_ObjCInterfaceDecl()),
    ObjCCategoryDecl(CXCursor_ObjCCategoryDecl()),
    ObjCProtocolDecl(CXCursor_ObjCProtocolDecl()),
    ObjCPropertyDecl(CXCursor_ObjCPropertyDecl()),
    ObjCIvarDecl(CXCursor_ObjCIvarDecl()),
    ObjCInstanceMethodDecl(CXCursor_ObjCInstanceMethodDecl()),
    ObjCClassMethodDecl(CXCursor_ObjCClassMethodDecl()),
    // ObjC reference kinds (used as children during traversal)
    ObjCSuperClassRef(CXCursor_ObjCSuperClassRef()),
    ObjCProtocolRef(CXCursor_ObjCProtocolRef()),
    // Preprocessor
    MacroDefinition(CXCursor_MacroDefinition()),
    MacroExpansion(CXCursor_MacroExpansion()),
    MacroInstantiation(CXCursor_MacroInstantiation()),
    InclusionDirective(CXCursor_InclusionDirective()),
    StaticAssert(CXCursor_StaticAssert());

    companion object {
        private val lookup = entries.associateBy { it.value }

        @JvmStatic
        fun valueOf(value: Int): CursorKind =
            lookup[value] ?: throw NoSuchElementException("Invalid Cursor kind value: $value")

        /** Like [valueOf] but returns null for unknown cursor kind integers instead of throwing. */
        @JvmStatic
        fun valueOfOrNull(value: Int): CursorKind? = lookup[value]
    }
}
