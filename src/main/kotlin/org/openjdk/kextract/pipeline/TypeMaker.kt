/*
 *  Copyright (c) 2020, 2023, Oracle and/or its affiliates. All rights reserved.
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

package org.openjdk.kextract.pipeline

import org.openjdk.kextract.Declaration
import org.openjdk.kextract.Type
import org.openjdk.kextract.Type.Delegated
import org.openjdk.kextract.Type.Primitive
import org.openjdk.kextract.clang.Cursor
import org.openjdk.kextract.clang.TypeKind

/**
 * This class turns a clang type into a kextract type. Unlike declarations, kextract types are not
 * de-duplicated, so relying on the identity of a kextract type is wrong. Since pointer types can
 * point back to declarations, we create special pointer types backed by a supplier which fetches
 * the correct declaration from the tree maker cache. This makes sure that situations with
 * mutually referring pointers are dealt with correctly (i.e. by breaking cycles).
 */
internal object TypeMaker {

    fun makeType(t: org.openjdk.kextract.clang.Type, treeMaker: TreeMaker): Type {
        return when (t.kind()) {
            TypeKind.Auto -> makeType(t.canonicalType(), treeMaker)
            TypeKind.Void -> Type.void_()
            TypeKind.Char_S,
            TypeKind.Char_U -> Type.primitive(Primitive.Kind.Char)
            TypeKind.Short -> Type.primitive(Primitive.Kind.Short)
            TypeKind.Int -> Type.primitive(Primitive.Kind.Int)
            TypeKind.Long -> Type.primitive(Primitive.Kind.Long)
            TypeKind.LongLong -> Type.primitive(Primitive.Kind.LongLong)
            TypeKind.SChar -> {
                val chType = Type.primitive(Primitive.Kind.Char)
                Type.qualified(Delegated.Kind.SIGNED, chType)
            }
            TypeKind.UShort -> {
                val chType = Type.primitive(Primitive.Kind.Short)
                Type.qualified(Delegated.Kind.UNSIGNED, chType)
            }
            TypeKind.UInt -> {
                val chType = Type.primitive(Primitive.Kind.Int)
                Type.qualified(Delegated.Kind.UNSIGNED, chType)
            }
            TypeKind.ULong -> {
                val chType = Type.primitive(Primitive.Kind.Long)
                Type.qualified(Delegated.Kind.UNSIGNED, chType)
            }
            TypeKind.ULongLong -> {
                val chType = Type.primitive(Primitive.Kind.LongLong)
                Type.qualified(Delegated.Kind.UNSIGNED, chType)
            }
            TypeKind.UChar -> {
                val chType = Type.primitive(Primitive.Kind.Char)
                Type.qualified(Delegated.Kind.UNSIGNED, chType)
            }
            TypeKind.Bool -> Type.primitive(Primitive.Kind.Bool)
            TypeKind.Double -> Type.primitive(Primitive.Kind.Double)
            TypeKind.Float -> Type.primitive(Primitive.Kind.Float)
            TypeKind.Unexposed,
            TypeKind.Elaborated -> {
                val canonical = t.canonicalType()
                if (canonical.equalType(t)) {
                    Type.error(t.spelling())
                } else {
                    makeType(canonical, treeMaker)
                }
            }
            TypeKind.ConstantArray -> {
                val elem = makeType(t.getElementType(), treeMaker)
                Type.array(t.getNumberOfElements(), elem)
            }
            TypeKind.IncompleteArray -> {
                val elem = makeType(t.getElementType(), treeMaker)
                Type.array(elem)
            }
            TypeKind.FunctionProto,
            TypeKind.FunctionNoProto -> {
                val args = mutableListOf<Type>()
                for (i in 0 until t.numberOfArgs()) {
                    args.add(lowerFunctionType(t.argType(i), treeMaker))
                }
                Type.function(t.isVariadic(), lowerFunctionType(t.resultType(), treeMaker), *args.toTypedArray())
            }
            TypeKind.Enum,
            TypeKind.Record -> {
                val d = treeMaker.createTree(t.getDeclarationCursor())
                if (d != null) Type.declared(d as Declaration.Scoped) else Type.error(t.spelling())
            }
            TypeKind.BlockPointer,
            TypeKind.Pointer -> {
                val pointee = t.getPointeeType()
                if (pointee.kind() == TypeKind.FunctionProto ||
                    pointee.getDeclarationCursor().isInvalid()) {
                    Type.pointer(makeType(t.getPointeeType(), treeMaker))
                } else {
                    // struct/union pointer - defer processing of pointee type
                    val declCursor: Cursor = pointee.getDeclarationCursor()
                    val key: Cursor.Key = declCursor.toKey()
                    Type.pointer {
                        val decl = treeMaker.lookup(key)
                        if (decl == null) {
                            // no declaration, maybe an opaque type, give up and downgrade to void pointer
                            Type.void_()
                        } else {
                            when (val d = decl) {
                                is Declaration.Scoped -> Type.declared(d)
                                is Declaration.Typedef -> Type.typedef(d.name(), d.type())
                                else -> throw UnsupportedOperationException()
                            }
                        }
                    }
                }
            }
            TypeKind.Typedef -> {
                val type = makeType(t.canonicalType(), treeMaker)
                Type.typedef(t.spelling(), type)
            }
            TypeKind.Complex -> {
                val type = makeType(t.getElementType(), treeMaker)
                Type.qualified(Delegated.Kind.COMPLEX, type)
            }
            TypeKind.Vector -> {
                val type = makeType(t.getElementType(), treeMaker)
                Type.vector(t.getNumberOfElements(), type)
            }
            TypeKind.WChar -> Type.primitive(Primitive.Kind.WChar)      // unsupported
            TypeKind.Char16 -> Type.primitive(Primitive.Kind.Char16)    // unsupported
            TypeKind.Half -> Type.primitive(Primitive.Kind.HalfFloat)   // unsupported
            TypeKind.Int128 -> Type.primitive(Primitive.Kind.Int128)    // unsupported
            TypeKind.LongDouble -> Type.primitive(Primitive.Kind.LongDouble) // unsupported
            TypeKind.UInt128 -> {                                        // unsupported
                val iType = Type.primitive(Primitive.Kind.Int128)
                Type.qualified(Delegated.Kind.UNSIGNED, iType)
            }
            TypeKind.Atomic -> {
                val aType = makeType(t.getValueType(), treeMaker)
                Type.qualified(Delegated.Kind.ATOMIC, aType)
            }
            // Objective-C types — all map to opaque pointer (MemorySegment in Kotlin)
            TypeKind.ObjCId,
            TypeKind.ObjCClass,
            TypeKind.ObjCSel,
            TypeKind.ObjCObjectPointer,
            TypeKind.ObjCInterface,
            TypeKind.ObjCObject,
            TypeKind.ObjCTypeParam -> Type.pointer(Type.void_())
            else -> Type.error(t.spelling())
        }
    }

    private fun lowerFunctionType(t: org.openjdk.kextract.clang.Type, treeMaker: TreeMaker): Type {
        val t2 = makeType(t, treeMaker)
        return t2.accept(lowerFunctionTypeVisitor)
    }

    private val lowerFunctionTypeVisitor = object : Type.Visitor<Type> {
        override fun visitArray(t: Type.Array): Type {
            return Type.pointer(t.elementType())
        }

        override fun visitDelegated(t: Type.Delegated): Type {
            if (t.kind() == Delegated.Kind.TYPEDEF && t.type() is Type.Array) {
                return visitArray(t.type() as Type.Array)
            }
            return visitType(t)
        }

        override fun visitType(t: Type): Type {
            return t
        }
    }
}
