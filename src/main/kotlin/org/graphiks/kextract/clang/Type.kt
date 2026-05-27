package org.graphiks.kextract.clang

import java.lang.foreign.Arena
import java.lang.foreign.MemorySegment
import org.graphiks.kextract.clang.libclang.*
import org.graphiks.kextract.clang.LibClang.STRING_ALLOCATOR

class Type internal constructor(segment: MemorySegment, owner: ClangDisposable) :
    ClangDisposable.Owned(segment, owner) {

    fun isInvalid(): Boolean = kind() == TypeKind.Invalid

    fun isVariadic(): Boolean = clang_isFunctionTypeVariadic(segment) != 0
    fun resultType(): Type    = Type(clang_getResultType(owner, segment), owner)
    fun numberOfArgs(): Int   = clang_getNumArgTypes(segment)
    fun argType(idx: Int): Type = Type(clang_getArgType(owner, segment, idx), owner)

    fun getCallingConvention(): CallingConvention =
        CallingConvention.valueOf(clang_getFunctionTypeCallingConv(segment))

    fun isPointer(): Boolean {
        val k = kind()
        return k == TypeKind.Pointer || k == TypeKind.BlockPointer || k == TypeKind.MemberPointer
    }

    fun isReference(): Boolean {
        val k = kind()
        return k == TypeKind.LValueReference || k == TypeKind.RValueReference
    }

    fun isArray(): Boolean {
        val k = kind()
        return k == TypeKind.ConstantArray || k == TypeKind.IncompleteArray ||
               k == TypeKind.VariableArray || k == TypeKind.DependentSizedArray
    }

    fun getPointeeType(): Type    = Type(clang_getPointeeType(owner, segment), owner)
    fun getElementType(): Type    = Type(clang_getElementType(owner, segment), owner)
    fun getValueType(): Type      = Type(clang_Type_getValueType(owner, segment), owner)
    fun getNumberOfElements(): Long = clang_getNumElements(segment)

    fun getOffsetOf(fieldName: String): Long {
        val res = Arena.ofConfined().use { arena ->
            clang_Type_getOffsetOf(segment, arena.allocateFrom(fieldName))
        }
        if (TypeLayoutError.isError(res)) throw TypeLayoutError(res, "segment: $this, fieldName: $fieldName")
        return res
    }

    fun canonicalType(): Type = Type(clang_getCanonicalType(owner, segment), owner)

    fun isConstQualifierdType(): Boolean = clang_isConstQualifiedType(segment) != 0
    fun isVolatileQualified(): Boolean   = clang_isVolatileQualifiedType(segment) != 0

    fun spelling(): String {
        val spelling = clang_getTypeSpelling(STRING_ALLOCATOR.get(), segment)
        return LibClang.CXStrToString(spelling)
    }

    fun kind0(): Int = CXType.kind(segment)

    fun size(): Long {
        val res = clang_Type_getSizeOf(segment)
        if (TypeLayoutError.isError(res)) throw TypeLayoutError(res, "segment: $this")
        return res
    }

    fun align(): Long {
        val res = clang_Type_getAlignOf(segment)
        if (TypeLayoutError.isError(res)) throw TypeLayoutError(res, "segment: $this")
        return res
    }

    fun kind(): TypeKind = TypeKind.valueOf(kind0())

    fun getDeclarationCursor(): Cursor =
        Cursor(clang_getTypeDeclaration(owner, segment), owner)

    fun equalType(other: Type): Boolean = clang_equalTypes(segment, other.segment) != 0

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        return other is Type && equalType(other)
    }

    override fun hashCode(): Int = spelling().hashCode()
}
