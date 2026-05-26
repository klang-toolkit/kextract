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
import org.openjdk.kextract.Position
import org.openjdk.kextract.Type
import java.util.HashMap
import java.util.Objects
import java.util.Optional
import java.util.OptionalLong

abstract class DeclarationImpl(
    private val _name: String,
    private val _pos: Position
) : Declaration {

    private val attributes: MutableMap<Class<*>, java.lang.Record> = HashMap()

    override fun toString(): String = PrettyPrinter().print(this)
    override fun name(): String = _name
    override fun pos(): Position = _pos

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        return other is Declaration &&
            _name == other.name() &&
            attributes == other.attributes()
    }

    override fun hashCode(): Int = Objects.hash(_name, attributes)

    override fun attributes(): Collection<java.lang.Record> = attributes.values

    @Suppress("UNCHECKED_CAST")
    override fun <R : java.lang.Record> getAttribute(attributeClass: Class<R>): Optional<R> =
        Optional.ofNullable(attributes[attributeClass] as R?)

    override fun <R : java.lang.Record> addAttribute(attribute: R) {
        val existing = attributes[attribute.javaClass]
        if (existing != null && existing != attribute) {
            throw IllegalStateException("Attribute already exists: ${attribute.javaClass.simpleName}")
        }
        attributes[attribute.javaClass] = attribute
    }

    // ── Concrete declaration types ──────────────────────────────────────────

    class TypedefImpl(
        @JvmField val type: Type,
        name: String,
        pos: Position
    ) : DeclarationImpl(name, pos), Declaration.Typedef {
        override fun <R, D> accept(visitor: Declaration.Visitor<R, D>, data: D): R =
            visitor.visitTypedef(this, data)
        override fun type(): Type = type
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            return other is Declaration.Typedef && super.equals(other) && type == other.type()
        }
        override fun hashCode(): Int = Objects.hash(super.hashCode(), type)
    }

    open class VariableImpl(
        @JvmField val type: Type,
        @JvmField val kind: Declaration.Variable.Kind,
        name: String,
        pos: Position
    ) : DeclarationImpl(name, pos), Declaration.Variable {
        override fun kind(): Declaration.Variable.Kind = kind
        override fun <R, D> accept(visitor: Declaration.Visitor<R, D>, data: D): R =
            visitor.visitVariable(this, data)
        override fun type(): Type = type
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            return other is Declaration.Variable &&
                super.equals(other) &&
                kind == other.kind() &&
                type == other.type()
        }
        override fun hashCode(): Int = Objects.hash(super.hashCode(), kind, type)
    }

    class BitfieldImpl(
        type: Type,
        @JvmField val width: Long,
        name: String,
        pos: Position
    ) : VariableImpl(type, Declaration.Variable.Kind.BITFIELD, name, pos), Declaration.Bitfield {
        override fun width(): Long = width
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            return other is Declaration.Bitfield && super.equals(other) && width == other.width()
        }
        override fun hashCode(): Int = Objects.hash(super.hashCode(), width)
    }

    class FunctionImpl(
        @JvmField val type: Type.Function,
        @JvmField val params: List<Declaration.Variable>,
        name: String,
        pos: Position
    ) : DeclarationImpl(name, pos), Declaration.Function {
        override fun <R, D> accept(visitor: Declaration.Visitor<R, D>, data: D): R =
            visitor.visitFunction(this, data)
        override fun parameters(): List<Declaration.Variable> = params
        override fun type(): Type.Function = type
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            return other is Declaration.Function &&
                super.equals(other) &&
                params == other.parameters() &&
                type == other.type()
        }
        override fun hashCode(): Int = Objects.hash(super.hashCode(), params, type)
    }

    open class ScopedImpl(
        @JvmField val kind: Declaration.Scoped.Kind,
        @JvmField val declarations: List<Declaration>,
        name: String,
        pos: Position
    ) : DeclarationImpl(name, pos), Declaration.Scoped {
        override fun <R, D> accept(visitor: Declaration.Visitor<R, D>, data: D): R =
            visitor.visitScoped(this, data)
        override fun members(): List<Declaration> = declarations
        override fun kind(): Declaration.Scoped.Kind = kind
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            return other is Declaration.Scoped &&
                super.equals(other) &&
                kind == other.kind() &&
                declarations == other.members()
        }
        override fun hashCode(): Int = Objects.hash(super.hashCode(), kind, declarations)
    }

    class ConstantImpl(
        @JvmField val type: Type,
        @JvmField val value: Any,
        name: String,
        pos: Position
    ) : DeclarationImpl(name, pos), Declaration.Constant {
        override fun <R, D> accept(visitor: Declaration.Visitor<R, D>, data: D): R =
            visitor.visitConstant(this, data)
        override fun value(): Any = value
        override fun type(): Type = type
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            return other is Declaration.Constant &&
                super.equals(other) &&
                value === other.value() &&
                type == other.type()
        }
        override fun hashCode(): Int = Objects.hash(super.hashCode(), value, type)
    }

    // ── Attribute record classes ────────────────────────────────────��───────

    @JvmRecord
    data class AnonymousStruct(val offset: OptionalLong) {
        companion object {
            @JvmStatic
            fun with(scoped: Declaration.Scoped, offset: OptionalLong) {
                scoped.addAttribute(AnonymousStruct(offset))
            }
            @JvmStatic
            fun getOrThrow(scoped: Declaration.Scoped): AnonymousStruct =
                scoped.getAttribute(AnonymousStruct::class.java).orElseThrow()
            @JvmStatic
            fun isPresent(scoped: Declaration.Scoped): Boolean =
                scoped.getAttribute(AnonymousStruct::class.java).isPresent
            @JvmStatic
            fun anonName(scoped: Declaration.Scoped): String =
                "\$anon\$${scoped.pos().line()}:${scoped.pos().col()}"
        }
    }

    @JvmRecord
    data class EnumConstant(val enumName: String) {
        companion object {
            @JvmStatic
            fun with(constant: Declaration.Constant, enumName: String) {
                constant.addAttribute(EnumConstant(enumName))
            }
            @JvmStatic
            fun get(constant: Declaration.Constant): Optional<String> =
                constant.getAttribute(EnumConstant::class.java).map { it.enumName }
        }
    }

    @JvmRecord
    data class ClangEnumType(val type: Type) {
        companion object {
            @JvmStatic
            fun with(enumDecl: Declaration.Scoped, type: Type) {
                enumDecl.addAttribute(ClangEnumType(type))
            }
            @JvmStatic
            fun get(enumDecl: Declaration.Scoped): Optional<Type> =
                enumDecl.getAttribute(ClangEnumType::class.java).map { it.type }
        }
    }

    /** Marker attribute: no code should be generated for this declaration. */
    @JvmRecord
    data class Skip(private val _compat: Int = 0) {
        companion object {
            private val INSTANCE = Skip()
            @JvmStatic
            fun with(declaration: Declaration) {
                declaration.addAttribute(INSTANCE)
            }
            @JvmStatic
            fun isPresent(declaration: Declaration): Boolean =
                declaration.getAttribute(Skip::class.java).isPresent
        }
    }

    @JvmRecord
    data class JavaName(val names: List<String>) {
        companion object {
            @JvmStatic
            fun with(declaration: Declaration, names: List<String>) {
                declaration.addAttribute(JavaName(names))
            }
            @JvmStatic
            fun getOrThrow(declaration: Declaration): String =
                declaration.getAttribute(JavaName::class.java)
                    .map { it.names.last() }.get()
            @JvmStatic
            fun getFullNameOrThrow(declaration: Declaration): String =
                declaration.getAttribute(JavaName::class.java)
                    .map { it.names.joinToString(".") }.get()
            @JvmStatic
            fun isPresent(declaration: Declaration): Boolean =
                declaration.getAttribute(JavaName::class.java).isPresent
        }
    }

    @JvmRecord
    data class JavaFunctionalInterfaceName(val fiName: String) {
        companion object {
            @JvmStatic
            fun with(declaration: Declaration, fiName: String) {
                declaration.addAttribute(JavaFunctionalInterfaceName(fiName))
            }
            @JvmStatic
            fun getOrThrow(declaration: Declaration): String =
                declaration.getAttribute(JavaFunctionalInterfaceName::class.java)
                    .map { it.fiName }.get()
        }
    }

    @JvmRecord
    data class ClangAlignOf(val align: Long) {
        companion object {
            @JvmStatic
            fun with(declaration: Declaration, align: Long) {
                declaration.addAttribute(ClangAlignOf(align))
            }
            @JvmStatic
            fun get(declaration: Declaration): OptionalLong =
                declaration.getAttribute(ClangAlignOf::class.java)
                    .stream().mapToLong { it.align }.findFirst()
            @JvmStatic
            fun getOrThrow(declaration: Declaration): Long =
                declaration.getAttribute(ClangAlignOf::class.java)
                    .stream().mapToLong { it.align }.findFirst().asLong
        }
    }

    @JvmRecord
    data class ClangSizeOf(val size: Long) {
        companion object {
            @JvmStatic
            fun with(declaration: Declaration, size: Long) {
                declaration.addAttribute(ClangSizeOf(size))
            }
            @JvmStatic
            fun get(declaration: Declaration): OptionalLong =
                declaration.getAttribute(ClangSizeOf::class.java)
                    .stream().mapToLong { it.size }.findFirst()
            @JvmStatic
            fun getOrThrow(declaration: Declaration): Long =
                declaration.getAttribute(ClangSizeOf::class.java)
                    .stream().mapToLong { it.size }.findFirst().asLong
        }
    }

    @JvmRecord
    data class ClangOffsetOf(val offset: Long) {
        companion object {
            @JvmStatic
            fun with(declaration: Declaration, offset: Long) {
                declaration.addAttribute(ClangOffsetOf(offset))
            }
            @JvmStatic
            fun get(declaration: Declaration): OptionalLong =
                declaration.getAttribute(ClangOffsetOf::class.java)
                    .stream().mapToLong { it.offset }.findFirst()
            @JvmStatic
            fun getOrThrow(declaration: Declaration): Long =
                declaration.getAttribute(ClangOffsetOf::class.java)
                    .stream().mapToLong { it.offset }.findFirst().asLong
        }
    }

    @JvmRecord
    data class NestedDeclarations(val nestedDeclarations: List<Declaration.Scoped>) {
        companion object {
            @JvmStatic
            fun with(declaration: Declaration, nestedDeclarations: List<Declaration.Scoped>) {
                declaration.addAttribute(NestedDeclarations(nestedDeclarations))
            }
            @JvmStatic
            fun get(declaration: Declaration): Optional<List<Declaration.Scoped>> =
                declaration.getAttribute(NestedDeclarations::class.java)
                    .stream().map { it.nestedDeclarations }.findFirst()
        }
    }

    @JvmRecord
    data class DeclarationString(val declString: String) {
        companion object {
            @JvmStatic
            fun with(declaration: Declaration, declString: String) {
                declaration.addAttribute(DeclarationString(declString))
            }
            @JvmStatic
            fun get(declaration: Declaration): Optional<String> =
                declaration.getAttribute(DeclarationString::class.java)
                    .stream().map { it.declString }.findFirst()
            @JvmStatic
            fun getOrThrow(declaration: Declaration): String =
                declaration.getAttribute(DeclarationString::class.java)
                    .stream().map { it.declString }.findFirst().get()
        }
    }
}
