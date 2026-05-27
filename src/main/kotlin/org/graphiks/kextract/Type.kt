package org.graphiks.kextract

import org.graphiks.kextract.pipeline.PrettyPrinter
import java.lang.foreign.AddressLayout
import java.lang.foreign.MemoryLayout
import java.lang.foreign.ValueLayout
import java.lang.foreign.ValueLayout.ADDRESS
import java.util.Objects

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

internal abstract class TypeImpl : Type {

    companion object {
        val IS_WINDOWS: Boolean = System.getProperty("os.name").startsWith("Windows")

        /** Package-private equality helper: TYPEDEF delegation. */
        internal fun equals(t1: Type, t2: Type.Delegated): Boolean =
            t2.kind() == Type.Delegated.Kind.TYPEDEF && t1 == t2.type()
    }

    override fun isErroneous(): Boolean = false
    override fun toString(): String = PrettyPrinter.type(this)

    class ErronrousTypeImpl(val erroneousName: String) : TypeImpl() {
        override fun <R> accept(visitor: Type.Visitor<R>): R =
            visitor.visitType(this)
        override fun isErroneous(): Boolean = true
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            return other is ErronrousTypeImpl && erroneousName == other.erroneousName
        }
        override fun hashCode(): Int = erroneousName.hashCode()
    }

    class PrimitiveImpl(private val _kind: Type.Primitive.Kind) : TypeImpl(), Type.Primitive {
        override fun <R> accept(visitor: Type.Visitor<R>): R =
            visitor.visitPrimitive(this)
        override fun kind(): Type.Primitive.Kind = _kind
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is Type.Primitive)
                return other is Type.Delegated && TypeImpl.equals(this, other)
            return _kind == other.kind()
        }
        override fun hashCode(): Int = _kind.hashCode()
    }

    abstract class DelegatedBase(
        private val _kind: Type.Delegated.Kind,
        private val _name: String?
    ) : TypeImpl(), Type.Delegated {
        override fun <R> accept(visitor: Type.Visitor<R>): R =
            visitor.visitDelegated(this)
        final override fun kind(): Type.Delegated.Kind = _kind
        final override fun name(): String? = _name
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is Type.Delegated)
                return other is Type && TypeImpl.equals(other, this)
            return _kind == other.kind() && _name == other.name()
        }
        override fun hashCode(): Int = Objects.hash(_kind, _name)
    }

    class QualifiedImpl(
        kind: Type.Delegated.Kind,
        name: String?,
        private val _type: Type
    ) : DelegatedBase(kind, name) {

        override fun type(): Type = _type
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is Type.Delegated) return false
            if (!super.equals(other))
                return TypeImpl.equals(this, other)
            return _type == other.type()
        }
        override fun hashCode(): Int =
            if (kind() == Type.Delegated.Kind.TYPEDEF) type().hashCode()
            else Objects.hash(super.hashCode(), _type)
    }

    class PointerImpl : DelegatedBase {
        companion object {
            val POINTER_LAYOUT: AddressLayout = ADDRESS
                .withTargetLayout(MemoryLayout.sequenceLayout(Long.MAX_VALUE, ValueLayout.JAVA_BYTE))
        }

        private val _pointeeFactory: () -> Type

        constructor(pointeeFactory: () -> Type) : super(Type.Delegated.Kind.POINTER, null) {
            _pointeeFactory = pointeeFactory
        }
        constructor(pointee: Type) : this({ pointee })

        override fun type(): Type = _pointeeFactory()
    }

    class DeclaredImpl(private val _declaration: Declaration.Scoped) : TypeImpl(), Type.Declared {
        override fun <R> accept(visitor: Type.Visitor<R>): R =
            visitor.visitDeclared(this)
        override fun tree(): Declaration.Scoped = _declaration
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is Type.Declared)
                return other is Type.Delegated && TypeImpl.equals(this, other)
            return _declaration == other.tree()
        }
        override fun hashCode(): Int = _declaration.hashCode()
    }

    class FunctionImpl(
        private val _varargs: Boolean,
        private val _argtypes: List<Type>,
        private val _restype: Type,
        private val _paramNames: List<String>? = null
    ) : TypeImpl(), Type.Function {

        override fun <R> accept(visitor: Type.Visitor<R>): R =
            visitor.visitFunction(this)
        override fun varargs(): Boolean = _varargs
        override fun argumentTypes(): List<Type> = _argtypes
        override fun returnType(): Type = _restype
        override fun withParameterNames(paramNames: List<String>): Type.Function {
            return FunctionImpl(_varargs, _argtypes, _restype, paramNames)
        }
        override fun parameterNames(): List<String>? = _paramNames
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is Type.Function)
                return other is Type.Delegated && TypeImpl.equals(this, other)
            return _varargs == other.varargs() &&
                _argtypes == other.argumentTypes() &&
                _restype == other.returnType()
        }
        override fun hashCode(): Int = Objects.hash(_varargs, _argtypes, _restype)
    }

    class ArrayImpl(
        private val _kind: Type.Array.Kind,
        private val _elemCount: Long?,
        private val _elemType: Type
    ) : TypeImpl(), Type.Array {

        override fun <R> accept(visitor: Type.Visitor<R>): R =
            visitor.visitArray(this)
        override fun elementCount(): Long? = _elemCount
        override fun elementType(): Type = _elemType
        override fun kind(): Type.Array.Kind = _kind
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is Type.Array)
                return other is Type.Delegated && TypeImpl.equals(this, other)
            return _kind == other.kind() && _elemType == other.elementType()
        }
        override fun hashCode(): Int = Objects.hash(_kind, _elemType)
    }

    fun isPointer(): Boolean =
        (this as? Type.Delegated)?.kind() == Type.Delegated.Kind.POINTER
}
