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
import org.openjdk.kextract.Type.Delegated
import java.lang.foreign.AddressLayout
import java.lang.foreign.MemoryLayout
import java.lang.foreign.ValueLayout
import java.lang.foreign.ValueLayout.ADDRESS
import java.util.Objects

abstract class TypeImpl : Type {

    companion object {
        val IS_WINDOWS: Boolean = System.getProperty("os.name").startsWith("Windows")

        /** Package-private equality helper: TYPEDEF delegation. */
        internal fun equals(t1: Type, t2: Type.Delegated): Boolean =
            t2.kind() == Delegated.Kind.TYPEDEF && t1 == t2.type()
    }

    override fun isErroneous(): Boolean = false

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
        override fun hashCode(): Int = Objects.hash(_kind)
    }

    abstract class DelegatedBase(
        private val _kind: Delegated.Kind,
        private val _name: String?
    ) : TypeImpl(), Type.Delegated {
        override fun <R> accept(visitor: Type.Visitor<R>): R =
            visitor.visitDelegated(this)
        final override fun kind(): Delegated.Kind = _kind
        final override fun name(): String? = _name
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is Type.Delegated)
                return other is Type && TypeImpl.equals(other, this)
            return _kind == other.kind() && _name == other.name()
        }
        override fun hashCode(): Int = Objects.hash(_kind, _name)
    }

    class QualifiedImpl : DelegatedBase {
        private val _type: Type

        constructor(kind: Delegated.Kind, type: Type) : super(kind, null) {
            _type = type
        }
        constructor(kind: Delegated.Kind, name: String, type: Type) : super(kind, name) {
            _type = type
        }

        override fun type(): Type = _type
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is Type.Delegated) return false
            if (!super.equals(other))
                return TypeImpl.equals(this, other)
            return _type == other.type()
        }
        override fun hashCode(): Int =
            if (kind() == Delegated.Kind.TYPEDEF) type().hashCode()
            else Objects.hash(super.hashCode(), _type)
    }

    class PointerImpl : DelegatedBase {
        companion object {
            val POINTER_LAYOUT: AddressLayout = ADDRESS
                .withTargetLayout(MemoryLayout.sequenceLayout(Long.MAX_VALUE, ValueLayout.JAVA_BYTE))
        }

        private val _pointeeFactory: () -> Type

        constructor(pointeeFactory: () -> Type) : super(Delegated.Kind.POINTER, null) {
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
        override fun hashCode(): Int = Objects.hash(_declaration)
    }

    class FunctionImpl : TypeImpl, Type.Function {
        private val _varargs: Boolean
        private val _argtypes: List<Type>
        private val _restype: Type
        private val _paramNames: List<String>?

        constructor(varargs: Boolean, argtypes: List<Type>, restype: Type, paramNames: List<String>?) : super() {
            _varargs = varargs
            _argtypes = argtypes
            _restype = restype
            _paramNames = paramNames
        }
        constructor(varargs: Boolean, argtypes: List<Type>, restype: Type) :
            this(varargs, argtypes, restype, null)

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

    class ArrayImpl : TypeImpl, Type.Array {
        private val _kind: Type.Array.Kind
        private val _elemCount: Long?
        private val _elemType: Type

        constructor(kind: Type.Array.Kind, count: Long, elemType: Type) : super() {
            _kind = kind
            _elemCount = count
            _elemType = elemType
        }
        constructor(kind: Type.Array.Kind, elemType: Type) : super() {
            _kind = kind
            _elemCount = null
            _elemType = elemType
        }

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

    override fun toString(): String = PrettyPrinter.type(this)
}
