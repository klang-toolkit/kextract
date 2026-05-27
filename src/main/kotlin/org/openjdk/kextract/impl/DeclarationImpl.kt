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
import java.util.Objects

abstract class DeclarationImpl(
    private val _name: String,
    private val _pos: Position
) : Declaration {

    private val attributes: MutableMap<Class<*>, Declaration.Attribute> = mutableMapOf()

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

    override fun attributes(): Collection<Declaration.Attribute> = attributes.values

    @Suppress("UNCHECKED_CAST")
    override fun <R : Declaration.Attribute> getAttribute(attributeClass: Class<R>): R? =
        attributes[attributeClass] as R?

    override fun <R : Declaration.Attribute> addAttribute(attribute: R) {
        val existing = attributes[attribute.javaClass]
        if (existing != null && existing != attribute) {
            throw IllegalStateException("Attribute already exists: ${attribute.javaClass.simpleName}")
        }
        attributes[attribute.javaClass] = attribute
    }

    // ── Concrete declaration types ──────────────────────────────────────────

    class TypedefImpl(
        val type: Type,
        name: String,
        pos: Position
    ) : DeclarationImpl(name, pos), Declaration.Typedef {
        override fun <R> accept(visitor: Declaration.Visitor<R>): R =
            visitor.visitTypedef(this)
        override fun type(): Type = type
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            return other is Declaration.Typedef && super.equals(other) && type == other.type()
        }
        override fun hashCode(): Int = Objects.hash(super.hashCode(), type)
    }

    open class VariableImpl(
        val type: Type,
        val kind: Declaration.Variable.Kind,
        name: String,
        pos: Position
    ) : DeclarationImpl(name, pos), Declaration.Variable {
        override fun kind(): Declaration.Variable.Kind = kind
        override fun <R> accept(visitor: Declaration.Visitor<R>): R =
            visitor.visitVariable(this)
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
        val width: Long,
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
        val type: Type.Function,
        val params: List<Declaration.Variable>,
        name: String,
        pos: Position
    ) : DeclarationImpl(name, pos), Declaration.Function {
        override fun <R> accept(visitor: Declaration.Visitor<R>): R =
            visitor.visitFunction(this)
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
        val kind: Declaration.Scoped.Kind,
        val declarations: List<Declaration>,
        name: String,
        pos: Position
    ) : DeclarationImpl(name, pos), Declaration.Scoped {
        override fun <R> accept(visitor: Declaration.Visitor<R>): R =
            visitor.visitScoped(this)
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
        val type: Type,
        val value: Any,
        name: String,
        pos: Position
    ) : DeclarationImpl(name, pos), Declaration.Constant {
        override fun <R> accept(visitor: Declaration.Visitor<R>): R =
            visitor.visitConstant(this)
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

    // ── Objective-C concrete declaration types ─────────────────────────────

    class ObjCClassImpl(
        private val superClass: String?,
        private val protocols: List<String>,
        private val methods: List<Declaration.ObjCMethod>,
        private val properties: List<Declaration.ObjCProperty>,
        name: String, pos: Position
    ) : DeclarationImpl(name, pos), Declaration.ObjCClass {
        override fun <R> accept(v: Declaration.Visitor<R>): R = v.visitObjCClass(this)
        override fun superClass(): String? = superClass
        override fun protocols(): List<String> = protocols
        override fun methods(): List<Declaration.ObjCMethod> = methods
        override fun properties(): List<Declaration.ObjCProperty> = properties
    }

    class ObjCProtocolImpl(
        private val protocols: List<String>,
        private val methods: List<Declaration.ObjCMethod>,
        private val properties: List<Declaration.ObjCProperty>,
        name: String, pos: Position
    ) : DeclarationImpl(name, pos), Declaration.ObjCProtocol {
        override fun <R> accept(v: Declaration.Visitor<R>): R = v.visitObjCProtocol(this)
        override fun protocols(): List<String> = protocols
        override fun methods(): List<Declaration.ObjCMethod> = methods
        override fun properties(): List<Declaration.ObjCProperty> = properties
    }

    class ObjCCategoryImpl(
        private val extendedClass: String,
        private val categoryName: String,
        private val methods: List<Declaration.ObjCMethod>,
        private val properties: List<Declaration.ObjCProperty>,
        name: String, pos: Position
    ) : DeclarationImpl(name, pos), Declaration.ObjCCategory {
        override fun <R> accept(v: Declaration.Visitor<R>): R = v.visitObjCCategory(this)
        override fun extendedClass(): String = extendedClass
        override fun categoryName(): String = categoryName
        override fun methods(): List<Declaration.ObjCMethod> = methods
        override fun properties(): List<Declaration.ObjCProperty> = properties
    }

    class ObjCMethodImpl(
        private val isClassMethod: Boolean,
        private val selector: String,
        private val returnType: Type,
        private val params: List<Declaration.Variable>,
        private val isOptional: Boolean,
        name: String, pos: Position
    ) : DeclarationImpl(name, pos), Declaration.ObjCMethod {
        override fun <R> accept(v: Declaration.Visitor<R>): R = v.visitDeclaration(this)
        override fun isClassMethod(): Boolean = isClassMethod
        override fun selector(): String = selector
        override fun returnType(): Type = returnType
        override fun parameters(): List<Declaration.Variable> = params
        override fun isOptional(): Boolean = isOptional
    }

    class ObjCPropertyImpl(
        private val type: Type,
        private val isReadOnly: Boolean,
        private val getterSelector: String,
        private val setterSelector: String,
        name: String, pos: Position
    ) : DeclarationImpl(name, pos), Declaration.ObjCProperty {
        override fun <R> accept(v: Declaration.Visitor<R>): R =
            v.visitDeclaration(this)
        override fun type(): Type = type
        override fun isReadOnly(): Boolean = isReadOnly
        override fun getterSelector(): String = getterSelector
        override fun setterSelector(): String = setterSelector
    }

    // ── Attribute record classes ───────────────────────────────────────────

    data class AnonymousStruct(val offset: Long?) : Declaration.Attribute {
        companion object {
            fun with(scoped: Declaration.Scoped, offset: Long?) {
                scoped.addAttribute(AnonymousStruct(offset))
            }
            fun getOrThrow(scoped: Declaration.Scoped): AnonymousStruct =
                scoped.getAttribute(AnonymousStruct::class.java)!!
            fun isPresent(scoped: Declaration.Scoped): Boolean =
                scoped.getAttribute(AnonymousStruct::class.java) != null
            fun anonName(scoped: Declaration.Scoped): String =
                "\$anon\$${scoped.pos().line}:${scoped.pos().col}"
        }
    }

    data class EnumConstant(val enumName: String) : Declaration.Attribute {
        companion object {
            fun with(constant: Declaration.Constant, enumName: String) {
                constant.addAttribute(EnumConstant(enumName))
            }
            fun get(constant: Declaration.Constant): String? =
                constant.getAttribute(EnumConstant::class.java)?.enumName
        }
    }

    data class ClangEnumType(val type: Type) : Declaration.Attribute {
        companion object {
            fun with(enumDecl: Declaration.Scoped, type: Type) {
                enumDecl.addAttribute(ClangEnumType(type))
            }
            fun get(enumDecl: Declaration.Scoped): Type? =
                enumDecl.getAttribute(ClangEnumType::class.java)?.type
        }
    }

    /** Marker attribute: no code should be generated for this declaration. */
    data class Skip(private val _compat: Int = 0) : Declaration.Attribute {
        companion object {
            private val INSTANCE = Skip()
            fun with(declaration: Declaration) {
                declaration.addAttribute(INSTANCE)
            }
            fun isPresent(declaration: Declaration): Boolean =
                declaration.getAttribute(Skip::class.java) != null
        }
    }

    data class JavaName(val names: List<String>) : Declaration.Attribute {
        companion object {
            fun with(declaration: Declaration, names: List<String>) {
                declaration.addAttribute(JavaName(names))
            }
            fun getOrThrow(declaration: Declaration): String =
                declaration.getAttribute(JavaName::class.java)!!.names.last()
            fun getFullNameOrThrow(declaration: Declaration): String =
                declaration.getAttribute(JavaName::class.java)!!.names.joinToString(".")
            fun isPresent(declaration: Declaration): Boolean =
                declaration.getAttribute(JavaName::class.java) != null
        }
    }

    data class JavaFunctionalInterfaceName(val fiName: String) : Declaration.Attribute {
        companion object {
            fun with(declaration: Declaration, fiName: String) {
                declaration.addAttribute(JavaFunctionalInterfaceName(fiName))
            }
            fun getOrThrow(declaration: Declaration): String =
                declaration.getAttribute(JavaFunctionalInterfaceName::class.java)!!.fiName
        }
    }

    data class ClangAlignOf(val align: Long) : Declaration.Attribute {
        companion object {
            fun with(declaration: Declaration, align: Long) {
                declaration.addAttribute(ClangAlignOf(align))
            }
            fun get(declaration: Declaration): Long? =
                declaration.getAttribute(ClangAlignOf::class.java)?.align
            fun getOrThrow(declaration: Declaration): Long =
                declaration.getAttribute(ClangAlignOf::class.java)!!.align
        }
    }

    data class ClangSizeOf(val size: Long) : Declaration.Attribute {
        companion object {
            fun with(declaration: Declaration, size: Long) {
                declaration.addAttribute(ClangSizeOf(size))
            }
            fun get(declaration: Declaration): Long? =
                declaration.getAttribute(ClangSizeOf::class.java)?.size
            fun getOrThrow(declaration: Declaration): Long =
                declaration.getAttribute(ClangSizeOf::class.java)!!.size
        }
    }

    data class ClangOffsetOf(val offset: Long) : Declaration.Attribute {
        companion object {
            fun with(declaration: Declaration, offset: Long) {
                declaration.addAttribute(ClangOffsetOf(offset))
            }
            fun get(declaration: Declaration): Long? =
                declaration.getAttribute(ClangOffsetOf::class.java)?.offset
            fun getOrThrow(declaration: Declaration): Long =
                declaration.getAttribute(ClangOffsetOf::class.java)!!.offset
        }
    }

    data class NestedDeclarations(val nestedDeclarations: List<Declaration.Scoped>) : Declaration.Attribute {
        companion object {
            fun with(declaration: Declaration, nestedDeclarations: List<Declaration.Scoped>) {
                declaration.addAttribute(NestedDeclarations(nestedDeclarations))
            }
            fun get(declaration: Declaration): List<Declaration.Scoped>? =
                declaration.getAttribute(NestedDeclarations::class.java)?.nestedDeclarations
        }
    }

    data class DeclarationString(val declString: String) : Declaration.Attribute {
        companion object {
            fun with(declaration: Declaration, declString: String) {
                declaration.addAttribute(DeclarationString(declString))
            }
            fun get(declaration: Declaration): String? =
                declaration.getAttribute(DeclarationString::class.java)?.declString
            fun getOrThrow(declaration: Declaration): String =
                declaration.getAttribute(DeclarationString::class.java)!!.declString
        }
    }
}
