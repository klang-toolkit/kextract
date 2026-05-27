/*
 *  Copyright (c) 2020, 2024, Oracle and/or its affiliates. All rights reserved.
 *  DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 *  This code is free software; you can redistribute it and/or modify it
 *  under the terms of the GNU General Public License version 2 only, as
 *  published by the Free Software Foundation.  Oracle designates this
 *  particular file as subject to the "Classpath" exception as provided
 *  by Oracle in the LICENSE file that accompanied this code.
 *
 *  This code is distributed in the hope that it will be useful, but WITHOUT
 *  ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 *  FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 *  version 2 for more details (a copy is included in the LICENSE file that
 *  accompanied this code).
 *
 *  You should have received a copy of the GNU General Public License version
 *  2 along with this work; if not, write to the Free Software Foundation,
 *  Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 *   Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 *  or visit www.oracle.com if you need additional information or have any
 *  questions.
 *
 */
package org.openjdk.kextract.impl

import org.openjdk.kextract.Declaration
import org.openjdk.kextract.Type
import org.openjdk.kextract.TypeImpl
import org.openjdk.kextract.clang.Cursor
import org.openjdk.kextract.clang.CursorKind
import org.openjdk.kextract.DeclarationImpl.ClangEnumType
import org.openjdk.kextract.DeclarationImpl.NestedDeclarations
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
    this is Type.Declared -> tree().isEnum()
    this is Type.Delegated && kind() == Type.Delegated.Kind.TYPEDEF -> type().isEnum()
    else -> false
}

private fun Type.structOrUnionDecl(): Declaration.Scoped? = when {
    this is Type.Declared && tree().isStructOrUnion() -> tree()
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

internal fun Type.Function.methodType(): MethodType =
    MethodType.methodType(returnType().carrier(), argumentTypes().map { it.carrier() })

private fun Type.carrier(): Class<*> {
    if (isErroneous()) return MemorySegment::class.java
    return when {
        this is Type.Array                                                         -> MemorySegment::class.java
        this is Type.Primitive                                                     -> primitiveCarrier()
        this is Type.Declared && tree().kind() == Declaration.Scoped.Kind.ENUM    ->
            ClangEnumType.get(tree())!!.carrier()
        this is Type.Declared                                                      -> MemorySegment::class.java
        this is Type.Delegated && kind() == Type.Delegated.Kind.POINTER            -> MemorySegment::class.java
        this is Type.Delegated                                                     -> type().carrier()
        this is Type.Function                                                      -> MemorySegment::class.java
        else -> throw UnsupportedOperationException(toString())
    }
}

private fun Type.Primitive.primitiveCarrier(): Class<*> = when (kind()) {
    Type.Primitive.Kind.Void       -> Void.TYPE
    Type.Primitive.Kind.Bool       -> java.lang.Boolean.TYPE
    Type.Primitive.Kind.Char       -> java.lang.Byte.TYPE
    Type.Primitive.Kind.Short      -> java.lang.Short.TYPE
    Type.Primitive.Kind.Int        -> Integer.TYPE
    Type.Primitive.Kind.Long       -> if (TypeImpl.IS_WINDOWS) Integer.TYPE else java.lang.Long.TYPE
    Type.Primitive.Kind.LongLong   -> java.lang.Long.TYPE
    Type.Primitive.Kind.Float      -> java.lang.Float.TYPE
    Type.Primitive.Kind.Double     -> java.lang.Double.TYPE
    Type.Primitive.Kind.LongDouble ->
        if (TypeImpl.IS_WINDOWS) java.lang.Double.TYPE
        else throw UnsupportedOperationException(toString())
    else -> throw UnsupportedOperationException(toString())
}
