package org.graphiks.kextract.pipeline

import org.graphiks.kextract.Declaration
import org.graphiks.kextract.Type
import org.graphiks.kextract.TypeImpl
import org.graphiks.kextract.clang.Cursor
import org.graphiks.kextract.clang.CursorKind
import org.graphiks.kextract.DeclarationImpl.ClangEnumType
import org.graphiks.kextract.DeclarationImpl.NestedDeclarations
import java.lang.foreign.MemorySegment
import java.lang.invoke.MethodType

// ── Declaration extensions ────────────────────────────────────────────────

internal fun Declaration.forEachNested(action: (Declaration) -> Unit) {
    NestedDeclarations.get(this)?.forEach(action)
}

internal fun Declaration.isStructOrUnion(): Boolean =
    this is Declaration.Scoped &&
        (kind() == Declaration.Scoped.Kind.STRUCT || kind() == Declaration.Scoped.Kind.UNION)

internal fun Declaration.isEnum(): Boolean =
    this is Declaration.Scoped && kind() == Declaration.Scoped.Kind.ENUM

// ── Type extensions ───────────────────────────────────────────────────────

internal fun Type.isPointer(): Boolean = when {
    this is Type.Delegated && kind() == Type.Delegated.Kind.TYPEDEF -> type().isPointer()
    this is Type.Delegated && kind() == Type.Delegated.Kind.POINTER -> true
    else -> false
}

internal fun Type.isEnum(): Boolean = when {
    this is Type.Declared -> {
        val t = tree()
        if (t is Declaration.Typedef) {
            t.type().isEnum()
        } else {
            t.isEnum()
        }
    }
    this is Type.Delegated && kind() == Type.Delegated.Kind.TYPEDEF -> type().isEnum()
    else -> false
}

private fun Type.structOrUnionDecl(): Declaration.Scoped? = when {
    this is Type.Declared -> {
        val t = tree()
        if (t.isStructOrUnion()) {
            if (t is Declaration.Typedef) {
                t.type().structOrUnionDecl()
            } else {
                t
            }
        } else {
            null
        }
    }
    this is Type.Delegated && kind() == Type.Delegated.Kind.TYPEDEF -> type().structOrUnionDecl()
    else -> null
}

internal fun Type.isStructOrUnion(): Boolean = structOrUnionDecl() != null

internal fun Type.asFunctionPointer(): Type.Function? = when {
    this is Type.Delegated && kind() == Type.Delegated.Kind.POINTER -> type().asFunctionPointer()
    this is Type.Function -> this
    else -> null
}

// ── Cursor extensions ─────────────────────────────────────────────────────

internal fun Cursor.isFlattenable(): Boolean =
    isAnonymousStruct() || kind() == CursorKind.FieldDecl

// ── Panama carrier utilities ──────────────────────────────────────────────

internal fun Type.Function.methodType(win32Abi: Boolean = false): MethodType =
    MethodType.methodType(
        returnType().carrier(win32Abi),
        argumentTypes().map { it.carrier(win32Abi) },
    )

private fun Type.carrier(win32Abi: Boolean): Class<*> {
    if (isErroneous()) return MemorySegment::class.java
    return when {
        this is Type.Array                                                         -> MemorySegment::class.java
        this is Type.Primitive                                                     -> primitiveCarrier(win32Abi)
        this is Type.Declared && tree().kind() == Declaration.Scoped.Kind.ENUM    ->
            ClangEnumType.get(tree())!!.carrier(win32Abi)
        this is Type.Declared                                                      -> MemorySegment::class.java
        this is Type.Delegated && kind() == Type.Delegated.Kind.POINTER            -> MemorySegment::class.java
        this is Type.Delegated                                                     -> type().carrier(win32Abi)
        this is Type.Function                                                      -> MemorySegment::class.java
        else -> throw UnsupportedOperationException(toString())
    }
}

private fun Type.Primitive.primitiveCarrier(win32Abi: Boolean): Class<*> = when (kind()) {
    Type.Primitive.Kind.Void       -> Void.TYPE
    Type.Primitive.Kind.Bool       -> java.lang.Boolean.TYPE
    Type.Primitive.Kind.Char       -> java.lang.Byte.TYPE
    Type.Primitive.Kind.Short      -> java.lang.Short.TYPE
    Type.Primitive.Kind.Int        -> Integer.TYPE
    Type.Primitive.Kind.Long       -> if (win32Abi) Integer.TYPE else java.lang.Long.TYPE
    Type.Primitive.Kind.LongLong   -> java.lang.Long.TYPE
    Type.Primitive.Kind.Float      -> java.lang.Float.TYPE
    Type.Primitive.Kind.Double     -> java.lang.Double.TYPE
    Type.Primitive.Kind.WChar      -> if (win32Abi) java.lang.Character.TYPE
                                      else throw UnsupportedOperationException(toString())
    Type.Primitive.Kind.LongDouble ->
        if (TypeImpl.IS_WINDOWS) java.lang.Double.TYPE
        else throw UnsupportedOperationException(toString())
    else -> throw UnsupportedOperationException(toString())
}
