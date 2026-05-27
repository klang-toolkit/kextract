/*
 * Copyright (c) 2020, 2025, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */
package org.openjdk.kextract.impl

import org.openjdk.kextract.Type
import org.openjdk.kextract.impl.DeclarationImpl.ClangEnumType
import org.openjdk.kextract.impl.DeclarationImpl.JavaName

/**
 * Utility class for generating layout strings for code generation.
 * Mirrors the Java LayoutUtils class; callers need not change.
 */
object LayoutUtils {

    fun layoutString(type: Type): String = fieldLayoutString(type, -1, -1)

    fun functionDescriptorString(functionType: Type.Function): String {
        val type = functionType.methodType()
        val noArgs = type.parameterCount() == 0
        return buildString {
            if (type.returnType() != Void.TYPE) {
                append("FunctionDescriptor.of(")
                append(layoutString(functionType.returnType()))
                if (!noArgs) append(", ")
            } else {
                append("FunctionDescriptor.ofVoid(")
            }
            if (!noArgs) {
                append(functionType.argumentTypes().joinToString(", ") { layoutString(it) })
            }
            append(")")
        }
    }

    private fun fieldLayoutString(type: Type, typeAlign: Long, expectedAlign: Long): String {
        if (type.isErroneous()) return "ValueLayout.ADDRESS"
        return when {
            type is Type.Primitive -> primitiveLayoutString(type, typeAlign, expectedAlign)
            type is Type.Declared && type.isEnum() -> {
                val enumType = ClangEnumType.get(type.tree())
                if (enumType != null) fieldLayoutString(enumType, typeAlign, expectedAlign)
                else "ValueLayout.JAVA_INT"
            }
            type is Type.Declared && type.isStructOrUnion() -> {
                val name = JavaName.getFullNameOrThrow(type.tree())
                "${name}.layout"
            }
            type is Type.Delegated && type.kind() == Type.Delegated.Kind.POINTER -> "ValueLayout.ADDRESS"
            type is Type.Delegated -> fieldLayoutString(type.type(), typeAlign, expectedAlign)
            type is Type.Function -> "ValueLayout.ADDRESS"
            type is Type.Array -> "MemoryLayout.sequenceLayout(${type.elementCount() ?: 0L}, ${fieldLayoutString(type.elementType(), typeAlign, expectedAlign)})"
            else -> throw UnsupportedOperationException("Unexpected type: $type")
        }
    }

    private fun primitiveLayoutString(type: Type.Primitive, typeAlign: Long, expectedAlign: Long): String {
        val layoutPrefix = when (type.kind()) {
            Type.Primitive.Kind.Bool      -> "ValueLayout.JAVA_BOOLEAN"
            Type.Primitive.Kind.Char      -> "ValueLayout.JAVA_BYTE"
            Type.Primitive.Kind.Short     -> "ValueLayout.JAVA_SHORT"
            Type.Primitive.Kind.Int       -> "ValueLayout.JAVA_INT"
            Type.Primitive.Kind.Long,
            Type.Primitive.Kind.LongLong  -> "ValueLayout.JAVA_LONG"
            Type.Primitive.Kind.Float     -> "ValueLayout.JAVA_FLOAT"
            Type.Primitive.Kind.Double    -> "ValueLayout.JAVA_DOUBLE"
            Type.Primitive.Kind.Char16    -> "ValueLayout.JAVA_CHAR"
            Type.Primitive.Kind.Int128    -> "ValueLayout.JAVA_LONG"
            Type.Primitive.Kind.Void      -> throw UnsupportedOperationException("Void type cannot be laid out")
            else -> throw UnsupportedOperationException("Unexpected primitive kind: ${type.kind()}")
        }
        return if (expectedAlign > 0) "$layoutPrefix.align($layoutPrefix, $expectedAlign)" else layoutPrefix
    }
}
