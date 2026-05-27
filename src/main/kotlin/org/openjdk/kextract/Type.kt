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
package org.openjdk.kextract

import org.openjdk.kextract.impl.TypeImpl

/**
 * Instances of this class are used to model types in the foreign language.
 * Instances of this class support the *visitor* pattern (see [Type.accept] and [Type.Visitor]).
 */
interface Type {

    /** Is this type the erroneous type? */
    fun isErroneous(): Boolean

    /** Entry point for visiting type instances. */
    fun <R> accept(visitor: Visitor<R>): R

    override fun equals(other: Any?): Boolean
    override fun hashCode(): Int

    /** A primitive type. */
    interface Primitive : Type {
        enum class Kind(private val _typeName: String) {
            Void("void"),
            Bool("_Bool"),
            Char("char"),
            Char16("char16"),
            Short("short"),
            Int("int"),
            Long("long"),
            LongLong("long long"),
            Int128("__int128"),
            Float("float"),
            Double("double"),
            LongDouble("long double"),
            Float128("float128"),
            HalfFloat("__fp16"),
            WChar("wchar_t");

            fun typeName(): String = _typeName
        }
        fun kind(): Kind
    }

    /**
     * Instances of this class are used to model types which are associated to a declaration
     * in the foreign language (see [Declaration]).
     */
    interface Declared : Type {
        fun tree(): Declaration.Scoped
    }

    /** A function type. */
    interface Function : Type {
        fun varargs(): Boolean
        fun argumentTypes(): List<Type>
        fun returnType(): Type
        fun parameterNames(): List<String>?
        fun withParameterNames(paramNames: List<String>): Function
    }

    /**
     * An array type. Array types feature an element type and an optional size.
     */
    interface Array : Type {
        enum class Kind { VECTOR, ARRAY, INCOMPLETE_ARRAY }
        fun kind(): Kind
        fun elementCount(): Long?
        fun elementType(): Type
    }

    /**
     * A delegated type models a type containing an indirection to some other underlying type.
     */
    interface Delegated : Type {
        enum class Kind { TYPEDEF, POINTER, SIGNED, UNSIGNED, ATOMIC, VOLATILE, COMPLEX }
        fun kind(): Kind
        fun name(): String?
        fun type(): Type
    }

    /**
     * Type visitor interface.
     * @param R the visitor's return type.
     */
    interface Visitor<R> {
        fun visitPrimitive(t: Primitive): R = visitType(t)
        fun visitFunction(t: Function): R = visitType(t)
        fun visitDeclared(t: Declared): R = visitType(t)
        fun visitDelegated(t: Delegated): R = visitType(t)
        fun visitArray(t: Array): R = visitType(t)
        fun visitType(t: Type): R = throw UnsupportedOperationException()
    }

    companion object {
        fun void_(): Primitive =
            TypeImpl.PrimitiveImpl(Primitive.Kind.Void)

        fun primitive(kind: Primitive.Kind): Primitive =
            TypeImpl.PrimitiveImpl(kind)

        fun qualified(kind: Delegated.Kind, type: Type): Delegated =
            TypeImpl.QualifiedImpl(kind, null, type)

        fun typedef(name: String, aliased: Type): Delegated =
            TypeImpl.QualifiedImpl(Delegated.Kind.TYPEDEF, name, aliased)

        fun pointer(): Delegated =
            TypeImpl.PointerImpl { TypeImpl.PrimitiveImpl(Primitive.Kind.Void) }

        fun pointer(pointee: Type): Delegated =
            TypeImpl.PointerImpl { pointee }

        fun pointer(pointee: () -> Type): Delegated =
            TypeImpl.PointerImpl(pointee)

        fun function(varargs: Boolean, returnType: Type, vararg arguments: Type): Function =
            TypeImpl.FunctionImpl(varargs, arguments.toList(), returnType)

        fun declared(tree: Declaration.Scoped): Declared =
            TypeImpl.DeclaredImpl(tree)

        fun vector(elementCount: Long, elementType: Type): Array =
            TypeImpl.ArrayImpl(Array.Kind.VECTOR, elementCount, elementType)

        fun array(elementCount: Long, elementType: Type): Array =
            TypeImpl.ArrayImpl(Array.Kind.ARRAY, elementCount, elementType)

        fun array(elementType: Type): Array =
            TypeImpl.ArrayImpl(Array.Kind.INCOMPLETE_ARRAY, null, elementType)

        fun error(erroneousName: String): Type =
            TypeImpl.ErronrousTypeImpl(erroneousName)
    }
}
