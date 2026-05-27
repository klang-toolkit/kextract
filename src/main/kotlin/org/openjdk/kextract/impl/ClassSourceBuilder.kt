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

import org.openjdk.kextract.Declaration
import org.openjdk.kextract.Type
import org.openjdk.kextract.Type.Declared
import org.openjdk.kextract.Type.Delegated
import org.openjdk.kextract.Type.Function
import org.openjdk.kextract.Type.Primitive
import org.openjdk.kextract.impl.DeclarationImpl.ClangEnumType
import org.openjdk.kextract.impl.DeclarationImpl.DeclarationString
import org.openjdk.kextract.impl.DeclarationImpl.JavaName

import java.lang.foreign.MemoryLayout
import java.lang.foreign.MemorySegment
import java.lang.invoke.MethodHandle
import java.lang.invoke.MethodType
import java.lang.invoke.VarHandle
import java.util.Objects
import java.util.stream.Collectors
import java.util.stream.IntStream

/**
 * Superclass for .java source generator classes.
 */
internal abstract class ClassSourceBuilder(
    private val sb: SourceFileBuilder,
    private val modifiers: String,
    private val kind: Kind,
    private val className: String,
    private val superName: String?,
    private val enclosing: ClassSourceBuilder?,
    private val runtimeHelperName: String
) {

    enum class Kind(val kindName: String) {
        CLASS("class"),
        INTERFACE("interface")
    }

    companion object {
        private const val NO_ALIGN_REQUIRED_MARKER = -1L

        fun declarationComment(decl: Declaration): String {
            Objects.requireNonNull(decl)
            val declString = DeclarationString.getOrThrow(decl)
            return declString.lines().stream().collect(Collectors.joining("\n * ", " * ", ""))
        }
    }

    fun className(): String = className

    fun runtimeHelperName(): String = runtimeHelperName

    // is the name enclosed by a class of the same name?
    protected fun isEnclosedBySameName(name: String): Boolean =
        className() == name || (isNested() && enclosing!!.isEnclosedBySameName(name))

    protected fun isNested(): Boolean = enclosing != null

    fun sourceFileBuilder(): SourceFileBuilder = sb

    fun classBegin() {
        val extendsExpr = if (superName != null) " extends $superName" else ""
        appendLines("%1\$s %2\$s %3\$s%4\$s {", modifiers, kind.kindName, className, extendsExpr)
    }

    fun classEnd() {
        appendLines("""
            }
            """.trimIndent() + "\n")
    }

    // Internal generation helpers (used by other builders)

    fun incrAlign() {
        sb.incrAlign()
    }

    fun decrAlign() {
        sb.decrAlign()
    }

    open fun format(s: String, vararg args: String): String {
        val anyArgs: kotlin.Array<out Any> = args
        return String.format(s, *anyArgs)
    }

    // append multiple lines (indentation is added automatically)
    open fun appendLines(s: String) {
        sb.appendLines(s)
    }

    open fun appendLines(s: String, vararg args: String) {
        sb.appendLines(format(s, *args))
    }

    fun appendBlankLine() {
        appendLines("\n")
    }

    // increase indentation before appending lines, decrease afterwards
    open fun appendIndentedLines(s: String) {
        sb.appendIndentedLines(s)
    }

    open fun appendIndentedLines(s: String, vararg args: String) {
        sb.appendIndentedLines(format(s, *args))
    }

    fun emitDefaultConstructor() {
        appendIndentedLines("""

            %1${"$"}s() {
                // Should not be called directly
            }
            """.trimIndent() + "\n", className)
    }

    fun emitPrivateConstructor() {
        appendIndentedLines("""

            private %1${"$"}s() {
                // Should not be called directly
            }
            """.trimIndent() + "\n", className)
    }

    fun emitDocComment(decl: Declaration) {
        emitDocComment(decl, "")
    }

    fun emitDocComment(decl: Declaration, header: String) {
        appendLines("""
            /**
            %1${"$"}s * {@snippet lang=c :
            %2${"$"}s
             * }
             */
            """.trimIndent() + "\n",
            if (header.isNotEmpty()) " * $header\n" else "",
            declarationComment(decl)
        )
    }

    open fun mangleName(javaName: String, type: Class<*>): String {
        return javaName + nameSuffix(type)
    }

    open fun nameSuffix(type: Class<*>): String {
        return when {
            type == MemorySegment::class.java -> "\$SEGMENT"
            type == MemoryLayout::class.java -> "\$LAYOUT"
            type == MethodHandle::class.java -> "\$MH"
            type == VarHandle::class.java -> "\$VH"
            else -> throw IllegalArgumentException("Not handled: $type")
        }
    }

    open fun layoutString(type: Type): String {
        return fieldLayoutString(type, -1L, NO_ALIGN_REQUIRED_MARKER)
    }

    open fun fieldLayoutString(type: Type, typeAlign: Long, expectedAlign: Long): String {
        return when {
            type is Primitive -> primitiveLayoutString(type, typeAlign, expectedAlign)
            type is Declared && Utils.isEnum(type) ->
                fieldLayoutString(ClangEnumType.get(type.tree())!!, typeAlign, expectedAlign)
            type is Declared && Utils.isStructOrUnion(type) ->
                alignIfNeeded(JavaName.getFullNameOrThrow(type.tree()) + ".layout()", typeAlign, expectedAlign)
            type is Delegated && type.kind() == Delegated.Kind.POINTER ->
                alignIfNeeded(runtimeHelperName() + ".C_POINTER", typeAlign, expectedAlign)
            type is Delegated -> fieldLayoutString(type.type(), typeAlign, expectedAlign)
            type is Function -> alignIfNeeded(runtimeHelperName() + ".C_POINTER", typeAlign, expectedAlign)
            type is Type.Array -> String.format(
                "MemoryLayout.sequenceLayout(%1\$d, %2\$s)",
                type.elementCount() ?: 0L,
                fieldLayoutString(type.elementType(), typeAlign, expectedAlign)
            )
            else -> throw UnsupportedOperationException()
        }
    }

    open fun functionDescriptorString(textBoxIndent: Int, functionType: Type.Function): String {
        val type: MethodType = Utils.methodTypeFor(functionType)
        val noArgs = type.parameterCount() == 0
        val builder = StringBuilder()
        if (type.returnType() != Void.TYPE) {
            builder.append("FunctionDescriptor.of(")
            builder.append("\n")
            builder.append(String.format("%1\$s%2\$s", indentString(textBoxIndent + 1), layoutString(functionType.returnType())))
            if (!noArgs) {
                builder.append(",")
            }
        } else {
            builder.append("FunctionDescriptor.ofVoid(")
        }
        if (!noArgs) {
            builder.append("\n")
            var delim = ""
            for (arg in functionType.argumentTypes()) {
                builder.append(delim)
                builder.append(String.format("%1\$s%2\$s", indentString(textBoxIndent + 1), layoutString(arg)))
                delim = ",\n"
            }
            builder.append("\n")
        }
        builder.append(indentString(textBoxIndent)).append(")")
        return builder.toString()
    }

    open fun indentString(size: Int): String = " ".repeat(size * 4)

    private fun primitiveLayoutString(primitiveType: Primitive, typeAlign: Long, expectedAlign: Long): String {
        return when (primitiveType.kind()) {
            Primitive.Kind.Bool -> alignIfNeeded(runtimeHelperName() + ".C_BOOL", typeAlign, expectedAlign)
            Primitive.Kind.Char -> alignIfNeeded(runtimeHelperName() + ".C_CHAR", typeAlign, expectedAlign)
            Primitive.Kind.Short -> alignIfNeeded(runtimeHelperName() + ".C_SHORT", typeAlign, expectedAlign)
            Primitive.Kind.Int -> alignIfNeeded(runtimeHelperName() + ".C_INT", typeAlign, expectedAlign)
            Primitive.Kind.Long -> alignIfNeeded(runtimeHelperName() + ".C_LONG", typeAlign, expectedAlign)
            Primitive.Kind.LongLong -> alignIfNeeded(runtimeHelperName() + ".C_LONG_LONG", typeAlign, expectedAlign)
            Primitive.Kind.Float -> alignIfNeeded(runtimeHelperName() + ".C_FLOAT", typeAlign, expectedAlign)
            Primitive.Kind.Double -> alignIfNeeded(runtimeHelperName() + ".C_DOUBLE", typeAlign, expectedAlign)
            Primitive.Kind.LongDouble -> if (TypeImpl.IS_WINDOWS)
                alignIfNeeded(runtimeHelperName() + ".C_LONG_DOUBLE", typeAlign, expectedAlign)
            else
                paddingLayoutString(8L, 0)
            Primitive.Kind.HalfFloat,
            Primitive.Kind.Char16,
            Primitive.Kind.WChar -> paddingLayoutString(2L, 0) // unsupported
            Primitive.Kind.Float128,
            Primitive.Kind.Int128 -> paddingLayoutString(16L, 0) // unsupported
            else -> throw UnsupportedOperationException(primitiveType.toString())
        }
    }

    private fun alignIfNeeded(layoutPrefix: String, defaultAlign: Long, expectedAlign: Long): String {
        return if (expectedAlign != NO_ALIGN_REQUIRED_MARKER && defaultAlign != expectedAlign)
            String.format("%1\$s.align(%2\$s, %3\$d)", runtimeHelperName(), layoutPrefix, expectedAlign)
        else
            layoutPrefix
    }

    open fun paddingLayoutString(size: Long, indent: Int): String {
        return String.format("%1\$sMemoryLayout.paddingLayout(%2\$d)", indentString(indent), size)
    }

    data class IndexList(val decl: String, val use: String) {
        companion object {
            fun of(dims: Int): IndexList {
                val indexNames = IntStream.range(0, dims).mapToObj { i -> "index$i" }.toList()
                val indexDecls = indexNames.stream()
                    .map { i -> "long $i" }
                    .collect(Collectors.joining(", "))
                val indexUses = indexNames.stream()
                    .collect(Collectors.joining(", "))
                return IndexList(indexDecls, indexUses)
            }
        }
    }
}
