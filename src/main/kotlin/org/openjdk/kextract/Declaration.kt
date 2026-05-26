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

import org.openjdk.kextract.impl.DeclarationImpl
import java.util.Optional

/**
 * Instances of this class are used to model declaration elements in the foreign language.
 * All declarations have a position (see [Position]) and a name. Instances of this class
 * support the *visitor* pattern (see [Declaration.accept] and [Visitor]).
 */
interface Declaration {

    /** The position associated with this declaration. */
    fun pos(): Position

    /** The name associated with this declaration. */
    fun name(): String

    /** Entry point for visiting declaration instances. */
    fun <R, D> accept(visitor: Visitor<R, D>, data: D): R

    override fun equals(other: Any?): Boolean
    override fun hashCode(): Int

    /** The attributes associated with this declaration. */
    fun attributes(): Collection<java.lang.Record>

    /** Obtains an attribute from this declaration. */
    fun <R : java.lang.Record> getAttribute(attributeClass: Class<R>): Optional<R>

    /** Adds a new attribute to this declaration. */
    fun <R : java.lang.Record> addAttribute(attribute: R)

    /** A function declaration. */
    interface Function : Declaration {
        fun parameters(): List<Variable>
        fun type(): Type.Function
    }

    /**
     * A scoped declaration is a container for one or more nested declarations.
     */
    interface Scoped : Declaration {
        enum class Kind {
            ENUM, STRUCT, UNION, BITFIELDS, TOPLEVEL
        }
        fun members(): List<Declaration>
        fun kind(): Kind
    }

    /** A typedef declaration. */
    interface Typedef : Declaration {
        fun type(): Type
    }

    /** A variable declaration. */
    interface Variable : Declaration {
        enum class Kind {
            GLOBAL, FIELD, BITFIELD, PARAMETER
        }
        fun type(): Type
        fun kind(): Kind
    }

    /**
     * A bitfield declaration. Same as a variable declaration, but with a width instead of a layout.
     */
    interface Bitfield : Variable {
        fun width(): Long
    }

    /** A constant value declaration. */
    interface Constant : Declaration {
        fun value(): Any
        fun type(): Type
    }

    /**
     * Declaration visitor interface.
     * @param R the visitor's return type.
     * @param P the visitor's parameter type.
     */
    interface Visitor<R, P> {
        fun visitScoped(d: Scoped, p: P): R = visitDeclaration(d, p)
        fun visitFunction(d: Function, p: P): R = visitDeclaration(d, p)
        fun visitVariable(d: Variable, p: P): R = visitDeclaration(d, p)
        fun visitConstant(d: Constant, p: P): R = visitDeclaration(d, p)
        fun visitTypedef(d: Typedef, p: P): R = visitDeclaration(d, p)
        fun visitDeclaration(d: Declaration, p: P): R = throw UnsupportedOperationException()
    }

    companion object {
        @JvmStatic fun constant(pos: Position, name: String, value: Any, type: Type): Constant =
            DeclarationImpl.ConstantImpl(type, value, name, pos)

        @JvmStatic fun globalVariable(pos: Position, name: String, type: Type): Variable =
            DeclarationImpl.VariableImpl(type, Variable.Kind.GLOBAL, name, pos)

        @JvmStatic fun field(pos: Position, name: String, type: Type): Variable =
            DeclarationImpl.VariableImpl(type, Variable.Kind.FIELD, name, pos)

        @JvmStatic fun bitfield(pos: Position, name: String, width: Long, type: Type): Variable =
            DeclarationImpl.BitfieldImpl(type, width, name, pos)

        @JvmStatic fun parameter(pos: Position, name: String, type: Type): Variable =
            DeclarationImpl.VariableImpl(type, Variable.Kind.PARAMETER, name, pos)

        @JvmStatic fun `var`(kind: Variable.Kind, pos: Position, name: String, type: Type): Variable =
            DeclarationImpl.VariableImpl(type, kind, name, pos)

        @JvmStatic fun toplevel(pos: Position, vararg decls: Declaration): Scoped =
            DeclarationImpl.ScopedImpl(Scoped.Kind.TOPLEVEL, listOf(*decls), "<toplevel>", pos)

        @JvmStatic fun bitfields(pos: Position, vararg bitfields: Variable): Scoped =
            DeclarationImpl.ScopedImpl(Scoped.Kind.BITFIELDS, listOf(*bitfields), "", pos)

        @JvmStatic fun struct(pos: Position, name: String, vararg decls: Declaration): Scoped =
            DeclarationImpl.ScopedImpl(Scoped.Kind.STRUCT, listOf(*decls), name, pos)

        @JvmStatic fun union(pos: Position, name: String, vararg decls: Declaration): Scoped =
            DeclarationImpl.ScopedImpl(Scoped.Kind.UNION, listOf(*decls), name, pos)

        @JvmStatic fun enum_(pos: Position, name: String, vararg decls: Declaration): Scoped =
            DeclarationImpl.ScopedImpl(Scoped.Kind.ENUM, listOf(*decls), name, pos)

        @JvmStatic fun scoped(kind: Scoped.Kind, pos: Position, name: String, vararg decls: Declaration): Scoped =
            DeclarationImpl.ScopedImpl(kind, listOf(*decls), name, pos)

        @JvmStatic fun function(pos: Position, name: String, type: Type.Function, vararg params: Variable): Function =
            DeclarationImpl.FunctionImpl(type, listOf(*params), name, pos)

        @JvmStatic fun typedef(pos: Position, name: String, type: Type): Typedef =
            DeclarationImpl.TypedefImpl(type, name, pos)
    }

    /**
     * A record used to capture clang attributes attached to a declaration.
     * @param attributes a map from attribute name to attribute values.
     */
    @JvmRecord data class ClangAttributes(val attributes: Map<String, List<String>>)
}
