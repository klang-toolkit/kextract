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
    fun <R> accept(visitor: Visitor<R>): R

    override fun equals(other: Any?): Boolean
    override fun hashCode(): Int

    /** Marker for declaration attributes attached via [addAttribute] / [getAttribute]. */
    interface Attribute

    /** The attributes associated with this declaration. */
    fun attributes(): Collection<Attribute>

    /** Obtains an attribute from this declaration. */
    fun <R : Attribute> getAttribute(attributeClass: Class<R>): R?

    /** Adds a new attribute to this declaration. */
    fun <R : Attribute> addAttribute(attribute: R)

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

    // ── Objective-C declarations ──────────────────────────────────────────────

    /** An Objective-C class declaration (@interface without category name). */
    interface ObjCClass : Declaration {
        fun superClass(): String?                    // null for root classes
        fun protocols(): List<String>                // adopted protocol names
        fun methods(): List<ObjCMethod>
        fun properties(): List<ObjCProperty>
    }

    /** An Objective-C protocol declaration (@protocol). */
    interface ObjCProtocol : Declaration {
        fun protocols(): List<String>                // parent protocol names
        fun methods(): List<ObjCMethod>
        fun properties(): List<ObjCProperty>
    }

    /** An Objective-C category declaration (@interface ClassName (CategoryName)). */
    interface ObjCCategory : Declaration {
        fun extendedClass(): String                  // name of the class being extended
        fun categoryName(): String                   // the category name (in parens), may be empty
        fun methods(): List<ObjCMethod>
        fun properties(): List<ObjCProperty>
    }

    /** An Objective-C method (instance or class method). */
    interface ObjCMethod : Declaration {
        fun isClassMethod(): Boolean                 // true = class (+) method, false = instance (-)
        fun selector(): String                       // full selector, e.g. "stringWithUTF8String:"
        fun returnType(): Type
        fun parameters(): List<Variable>
        fun isOptional(): Boolean                    // true for @optional protocol methods
    }

    /** An Objective-C property declaration (@property). */
    interface ObjCProperty : Declaration {
        fun type(): Type
        fun isReadOnly(): Boolean
        fun getterSelector(): String
        fun setterSelector(): String                 // empty if isReadOnly
    }

    /**
     * Declaration visitor interface.
     * @param R the visitor's return type.
     */
    interface Visitor<R> {
        fun visitScoped(d: Scoped): R = visitDeclaration(d)
        fun visitFunction(d: Function): R = visitDeclaration(d)
        fun visitVariable(d: Variable): R = visitDeclaration(d)
        fun visitConstant(d: Constant): R = visitDeclaration(d)
        fun visitTypedef(d: Typedef): R = visitDeclaration(d)
        // Objective-C visitor methods (default: delegate to visitDeclaration)
        fun visitObjCClass(d: ObjCClass): R = visitDeclaration(d)
        fun visitObjCProtocol(d: ObjCProtocol): R = visitDeclaration(d)
        fun visitObjCCategory(d: ObjCCategory): R = visitDeclaration(d)
        fun visitDeclaration(d: Declaration): R = throw UnsupportedOperationException()
    }

    companion object {
        fun constant(pos: Position, name: String, value: Any, type: Type): Constant =
            DeclarationImpl.ConstantImpl(type, value, name, pos)

        fun globalVariable(pos: Position, name: String, type: Type): Variable =
            DeclarationImpl.VariableImpl(type, Variable.Kind.GLOBAL, name, pos)

        fun field(pos: Position, name: String, type: Type): Variable =
            DeclarationImpl.VariableImpl(type, Variable.Kind.FIELD, name, pos)

        fun bitfield(pos: Position, name: String, width: Long, type: Type): Variable =
            DeclarationImpl.BitfieldImpl(type, width, name, pos)

        fun parameter(pos: Position, name: String, type: Type): Variable =
            DeclarationImpl.VariableImpl(type, Variable.Kind.PARAMETER, name, pos)

        fun `var`(kind: Variable.Kind, pos: Position, name: String, type: Type): Variable =
            DeclarationImpl.VariableImpl(type, kind, name, pos)

        fun toplevel(pos: Position, vararg decls: Declaration): Scoped =
            DeclarationImpl.ScopedImpl(Scoped.Kind.TOPLEVEL, listOf(*decls), "<toplevel>", pos)

        fun bitfields(pos: Position, vararg bitfields: Variable): Scoped =
            DeclarationImpl.ScopedImpl(Scoped.Kind.BITFIELDS, listOf(*bitfields), "", pos)

        fun struct(pos: Position, name: String, vararg decls: Declaration): Scoped =
            DeclarationImpl.ScopedImpl(Scoped.Kind.STRUCT, listOf(*decls), name, pos)

        fun union(pos: Position, name: String, vararg decls: Declaration): Scoped =
            DeclarationImpl.ScopedImpl(Scoped.Kind.UNION, listOf(*decls), name, pos)

        fun enum_(pos: Position, name: String, vararg decls: Declaration): Scoped =
            DeclarationImpl.ScopedImpl(Scoped.Kind.ENUM, listOf(*decls), name, pos)

        fun scoped(kind: Scoped.Kind, pos: Position, name: String, vararg decls: Declaration): Scoped =
            DeclarationImpl.ScopedImpl(kind, listOf(*decls), name, pos)

        fun function(pos: Position, name: String, type: Type.Function, vararg params: Variable): Function =
            DeclarationImpl.FunctionImpl(type, listOf(*params), name, pos)

        fun typedef(pos: Position, name: String, type: Type): Typedef =
            DeclarationImpl.TypedefImpl(type, name, pos)

        // Objective-C factory methods
        fun objcClass(
            pos: Position, name: String, superClass: String?,
            protocols: List<String>, methods: List<ObjCMethod>, properties: List<ObjCProperty>
        ): ObjCClass = DeclarationImpl.ObjCClassImpl(superClass, protocols, methods, properties, name, pos)

        fun objcProtocol(
            pos: Position, name: String,
            protocols: List<String>, methods: List<ObjCMethod>, properties: List<ObjCProperty>
        ): ObjCProtocol = DeclarationImpl.ObjCProtocolImpl(protocols, methods, properties, name, pos)

        fun objcCategory(
            pos: Position, name: String, extendedClass: String, categoryName: String,
            methods: List<ObjCMethod>, properties: List<ObjCProperty>
        ): ObjCCategory = DeclarationImpl.ObjCCategoryImpl(extendedClass, categoryName, methods, properties, name, pos)

        fun objcMethod(
            pos: Position, name: String, selector: String, isClassMethod: Boolean,
            returnType: Type, params: List<Variable>, isOptional: Boolean
        ): ObjCMethod = DeclarationImpl.ObjCMethodImpl(isClassMethod, selector, returnType, params, isOptional, name, pos)

        fun objcProperty(
            pos: Position, name: String, type: Type,
            isReadOnly: Boolean, getterSelector: String, setterSelector: String
        ): ObjCProperty = DeclarationImpl.ObjCPropertyImpl(type, isReadOnly, getterSelector, setterSelector, name, pos)

        /** Retrieves an attribute of type [R], or null if absent. */
        inline fun <reified R : Attribute> Declaration.getAttribute(): R? =
            getAttribute(R::class.java)

        /** Returns true if an attribute of type [R] is present. */
        inline fun <reified R : Attribute> Declaration.hasAttribute(): Boolean =
            getAttribute<R>() != null
    }

    /**
     * A record used to capture clang attributes attached to a declaration.
     * @param attributes a map from attribute name to attribute values.
     */
    data class ClangAttributes(val attributes: Map<String, List<String>>) : Attribute
}
