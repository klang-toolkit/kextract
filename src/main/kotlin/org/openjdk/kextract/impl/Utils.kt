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
import org.openjdk.kextract.clang.Cursor
import org.openjdk.kextract.clang.CursorKind
import org.openjdk.kextract.impl.DeclarationImpl.ClangEnumType
import org.openjdk.kextract.impl.DeclarationImpl.NestedDeclarations
import java.lang.foreign.AddressLayout
import java.lang.foreign.GroupLayout
import java.lang.foreign.MemorySegment
import java.lang.foreign.SequenceLayout
import java.lang.foreign.ValueLayout
import java.lang.invoke.MethodType
import java.util.function.Consumer

/**
 * General utility functions.
 */
internal object Utils {

    fun isFlattenable(c: Cursor): Boolean =
        c.isAnonymousStruct() || c.kind() == CursorKind.FieldDecl

    fun quote(s: String): String = buildString {
        for (ch in s) append(quote(ch))
    }

    fun quote(ch: Char): String = when (ch) {
        '\b' -> "\\b"
        '' -> "\\f"
        '\n' -> "\\n"
        '\r' -> "\\r"
        '\t' -> "\\t"
        '\'' -> "\\'"
        '"'  -> "\\\""
        '\\' -> "\\\\"
        else -> if (isPrintableAscii(ch)) ch.toString() else "\\u%04x".format(ch.code)
    }

    fun forEachNested(declaration: Declaration, nestedDeclAction: Consumer<Declaration>) {
        NestedDeclarations.get(declaration)?.forEach(nestedDeclAction)
    }

    fun isStructOrUnion(declaration: Declaration): Boolean =
        declaration is Declaration.Scoped &&
            (declaration.kind() == Declaration.Scoped.Kind.STRUCT ||
             declaration.kind() == Declaration.Scoped.Kind.UNION)

    fun isEnum(declaration: Declaration): Boolean =
        declaration is Declaration.Scoped &&
            declaration.kind() == Declaration.Scoped.Kind.ENUM

    fun isArray(type: Type): Boolean = when {
        type is Type.Array -> true
        type is Type.Delegated && type.kind() == Type.Delegated.Kind.TYPEDEF -> isArray(type.type())
        else -> false
    }

    fun isEnum(type: Type): Boolean = when {
        type is Type.Declared -> isEnum(type.tree())
        type is Type.Delegated && type.kind() == Type.Delegated.Kind.TYPEDEF -> isEnum(type.type())
        else -> false
    }

    fun isStructOrUnion(type: Type): Boolean = structOrUnionDecl(type) != null

    fun structOrUnionDecl(type: Type): Declaration.Scoped? = when {
        type is Type.Declared && isStructOrUnion(type.tree()) -> type.tree()
        type is Type.Delegated && type.kind() == Type.Delegated.Kind.TYPEDEF -> structOrUnionDecl(type.type())
        else -> null
    }

    fun isPointer(type: Type): Boolean = when {
        type is Type.Delegated && type.kind() == Type.Delegated.Kind.TYPEDEF -> isPointer(type.type())
        type is Type.Delegated && type.kind() == Type.Delegated.Kind.POINTER -> true
        else -> false
    }

    fun isPrimitive(type: Type): Boolean = when {
        type is Type.Declared && type.tree().kind() == Declaration.Scoped.Kind.ENUM -> true
        type is Type.Delegated && type.kind() != Type.Delegated.Kind.POINTER -> isPrimitive(type.type())
        type is Type.Primitive -> true
        else -> false
    }

    fun getAsFunctionPointer(type: Type): Type.Function? = when {
        type is Type.Delegated && type.kind() == Type.Delegated.Kind.POINTER -> getAsFunctionPointer(type.type())
        type is Type.Function -> type
        else -> null
    }

    fun getAsSignedOrUnsigned(type: Type): Type.Primitive? {
        if (type is Type.Delegated && type.type() is Type.Primitive) {
            val kind = type.kind()
            if (kind == Type.Delegated.Kind.SIGNED || kind == Type.Delegated.Kind.UNSIGNED) {
                return type.type() as Type.Primitive
            }
        }
        return null
    }

    fun dimensions(type: Type): List<Long> {
        val dims = mutableListOf<Long>()
        var current = type
        while (current is Type.Array) {
            if (current.elementCount() == null) return emptyList()
            dims.add(current.elementCount()!!)
            current = current.elementType()
        }
        return dims
    }

    fun typeOrElemType(type: Type): Type = when (type) {
        is Type.Array -> typeOrElemType(type.elementType())
        else -> type
    }

    private fun isPrintableAscii(ch: Char): Boolean = ch >= ' ' && ch <= '~'

    fun carrierFor(type: Type): Class<*> {
        if (type.isErroneous()) return MemorySegment::class.java
        return when {
            type is Type.Array -> MemorySegment::class.java
            type is Type.Primitive -> carrierFor(type)
            type is Type.Declared && type.tree().kind() == Declaration.Scoped.Kind.ENUM ->
                carrierFor(ClangEnumType.get(type.tree())!!)
            type is Type.Declared -> MemorySegment::class.java
            type is Type.Delegated && type.kind() == Type.Delegated.Kind.POINTER -> MemorySegment::class.java
            type is Type.Delegated -> carrierFor(type.type())
            type is Type.Function -> MemorySegment::class.java
            else -> throw UnsupportedOperationException(type.toString())
        }
    }

    fun carrierFor(p: Type.Primitive): Class<*> = when (p.kind()) {
        Type.Primitive.Kind.Void -> Void.TYPE
        Type.Primitive.Kind.Bool -> java.lang.Boolean.TYPE
        Type.Primitive.Kind.Char -> java.lang.Byte.TYPE
        Type.Primitive.Kind.Short -> java.lang.Short.TYPE
        Type.Primitive.Kind.Int -> Integer.TYPE
        Type.Primitive.Kind.Long -> if (TypeImpl.IS_WINDOWS) Integer.TYPE else java.lang.Long.TYPE
        Type.Primitive.Kind.LongLong -> java.lang.Long.TYPE
        Type.Primitive.Kind.Float -> java.lang.Float.TYPE
        Type.Primitive.Kind.Double -> java.lang.Double.TYPE
        Type.Primitive.Kind.LongDouble -> {
            if (TypeImpl.IS_WINDOWS) java.lang.Double.TYPE
            else throw UnsupportedOperationException(p.toString())
        }
        else -> throw UnsupportedOperationException(p.toString())
    }

    val CARRIERS_TO_LAYOUT_CARRIERS: Map<Class<*>, Class<*>> = mapOf(
        java.lang.Byte.TYPE      to ValueLayout.OfByte::class.java,
        java.lang.Boolean.TYPE   to ValueLayout.OfBoolean::class.java,
        Character.TYPE           to ValueLayout.OfChar::class.java,
        java.lang.Short.TYPE     to ValueLayout.OfShort::class.java,
        Integer.TYPE             to ValueLayout.OfInt::class.java,
        java.lang.Float.TYPE     to ValueLayout.OfFloat::class.java,
        java.lang.Long.TYPE      to ValueLayout.OfLong::class.java,
        java.lang.Double.TYPE    to ValueLayout.OfDouble::class.java
    )

    fun layoutCarrierFor(t: Type): Class<*> = when {
        t is Type.Array -> SequenceLayout::class.java
        t is Type.Delegated && t.kind() == Type.Delegated.Kind.POINTER -> AddressLayout::class.java
        t is Type.Delegated -> layoutCarrierFor(t.type())
        t is Type.Primitive -> {
            val clazz = carrierFor(t)
            CARRIERS_TO_LAYOUT_CARRIERS[clazz]!!
        }
        t is Type.Declared && isStructOrUnion(t) -> GroupLayout::class.java
        t is Type.Declared && isEnum(t) -> layoutCarrierFor(ClangEnumType.get(t.tree())!!)
        else -> throw UnsupportedOperationException(t.toString())
    }

    fun methodTypeFor(type: Type.Function): MethodType =
        MethodType.methodType(
            carrierFor(type.returnType()),
            type.argumentTypes().stream().map { carrierFor(it) }.toList()
        )
}
