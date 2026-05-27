package org.graphiks.kextract

import org.graphiks.kextract.pipeline.PrettyPrinter
import java.util.Objects

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
    }

    /**
     * A record used to capture clang attributes attached to a declaration.
     * @param attributes a map from attribute name to attribute values.
     */
    data class ClangAttributes(val attributes: Map<String, List<String>>) : Attribute
}

/** Retrieves an attribute of type [R], or null if absent. */
inline fun <reified R : Declaration.Attribute> Declaration.getAttribute(): R? =
    getAttribute(R::class.java)

/** Returns true if an attribute of type [R] is present. */
inline fun <reified R : Declaration.Attribute> Declaration.hasAttribute(): Boolean =
    getAttribute<R>() != null

// ── Implementation ────────────────────────────────────────────────────────────

internal abstract class DeclarationImpl(
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
            fun with(scoped: Declaration.Scoped, offset: Long?) = scoped.addAttribute(AnonymousStruct(offset))
            fun getOrThrow(scoped: Declaration.Scoped): AnonymousStruct = scoped.getAttribute<AnonymousStruct>()!!
            fun isPresent(scoped: Declaration.Scoped): Boolean = scoped.hasAttribute<AnonymousStruct>()
            fun anonName(scoped: Declaration.Scoped): String = "\$anon\$${scoped.pos().line}:${scoped.pos().col}"
        }
    }

    data class EnumConstant(val enumName: String) : Declaration.Attribute {
        companion object {
            fun with(constant: Declaration.Constant, enumName: String) = constant.addAttribute(EnumConstant(enumName))
            fun get(constant: Declaration.Constant): String? = constant.getAttribute<EnumConstant>()?.enumName
        }
    }

    data class ClangEnumType(val type: Type) : Declaration.Attribute {
        companion object {
            fun with(enumDecl: Declaration.Scoped, type: Type) = enumDecl.addAttribute(ClangEnumType(type))
            fun get(enumDecl: Declaration.Scoped): Type? = enumDecl.getAttribute<ClangEnumType>()?.type
        }
    }

    /** Marker attribute: no code should be generated for this declaration. */
    object Skip : Declaration.Attribute {
        fun with(declaration: Declaration) = declaration.addAttribute(this)
        fun isPresent(declaration: Declaration): Boolean = declaration.hasAttribute<Skip>()
    }

    data class JavaName(val names: List<String>) : Declaration.Attribute {
        companion object {
            fun with(declaration: Declaration, names: List<String>) = declaration.addAttribute(JavaName(names))
            fun getOrThrow(declaration: Declaration): String = declaration.getAttribute<JavaName>()!!.names.last()
            fun getFullNameOrThrow(declaration: Declaration): String =
                declaration.getAttribute<JavaName>()!!.names.joinToString(".")
            fun isPresent(declaration: Declaration): Boolean = declaration.hasAttribute<JavaName>()
        }
    }

    data class JavaFunctionalInterfaceName(val fiName: String) : Declaration.Attribute {
        companion object {
            fun with(declaration: Declaration, fiName: String) =
                declaration.addAttribute(JavaFunctionalInterfaceName(fiName))
            fun getOrThrow(declaration: Declaration): String =
                declaration.getAttribute<JavaFunctionalInterfaceName>()!!.fiName
        }
    }

    data class ClangAlignOf(val align: Long) : Declaration.Attribute {
        companion object {
            fun with(declaration: Declaration, align: Long) = declaration.addAttribute(ClangAlignOf(align))
            fun get(declaration: Declaration): Long? = declaration.getAttribute<ClangAlignOf>()?.align
            fun getOrThrow(declaration: Declaration): Long = declaration.getAttribute<ClangAlignOf>()!!.align
        }
    }

    data class ClangSizeOf(val size: Long) : Declaration.Attribute {
        companion object {
            fun with(declaration: Declaration, size: Long) = declaration.addAttribute(ClangSizeOf(size))
            fun get(declaration: Declaration): Long? = declaration.getAttribute<ClangSizeOf>()?.size
            fun getOrThrow(declaration: Declaration): Long = declaration.getAttribute<ClangSizeOf>()!!.size
        }
    }

    data class ClangOffsetOf(val offset: Long) : Declaration.Attribute {
        companion object {
            fun with(declaration: Declaration, offset: Long) = declaration.addAttribute(ClangOffsetOf(offset))
            fun get(declaration: Declaration): Long? = declaration.getAttribute<ClangOffsetOf>()?.offset
            fun getOrThrow(declaration: Declaration): Long = declaration.getAttribute<ClangOffsetOf>()!!.offset
        }
    }

    data class NestedDeclarations(val nestedDeclarations: List<Declaration.Scoped>) : Declaration.Attribute {
        companion object {
            fun with(declaration: Declaration, nestedDeclarations: List<Declaration.Scoped>) =
                declaration.addAttribute(NestedDeclarations(nestedDeclarations))
            fun get(declaration: Declaration): List<Declaration.Scoped>? =
                declaration.getAttribute<NestedDeclarations>()?.nestedDeclarations
        }
    }

    data class DeclarationString(val declString: String) : Declaration.Attribute {
        companion object {
            fun with(declaration: Declaration, declString: String) =
                declaration.addAttribute(DeclarationString(declString))
            fun get(declaration: Declaration): String? = declaration.getAttribute<DeclarationString>()?.declString
            fun getOrThrow(declaration: Declaration): String = declaration.getAttribute<DeclarationString>()!!.declString
        }
    }
}
