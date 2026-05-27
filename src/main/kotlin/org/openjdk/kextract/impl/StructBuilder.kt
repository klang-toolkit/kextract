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
import org.openjdk.kextract.Declaration.Scoped
import org.openjdk.kextract.Type
import org.openjdk.kextract.Type.Declared
import org.openjdk.kextract.impl.DeclarationImpl.AnonymousStruct
import org.openjdk.kextract.impl.DeclarationImpl.ClangAlignOf
import org.openjdk.kextract.impl.DeclarationImpl.ClangOffsetOf
import org.openjdk.kextract.impl.DeclarationImpl.ClangSizeOf
import org.openjdk.kextract.impl.DeclarationImpl.JavaFunctionalInterfaceName
import org.openjdk.kextract.impl.DeclarationImpl.JavaName
import org.openjdk.kextract.impl.DeclarationImpl.Skip

import java.util.ArrayDeque
import java.util.stream.Collectors
import java.util.stream.IntStream

/**
 * This class generates static utilities class for C structs, unions.
 */
internal class StructBuilder(
    builder: SourceFileBuilder,
    modifiers: String,
    className: String,
    enclosing: ClassSourceBuilder?,
    runtimeHelperName: String,
    private val structTree: Declaration.Scoped
) : ClassSourceBuilder(builder, modifiers, Kind.CLASS, className, null, enclosing, runtimeHelperName),
    Builder {

    private val structType: Type = Type.declared(structTree)
    private val nestedAnonDeclarations: ArrayDeque<Declaration> = ArrayDeque()

    private fun safeParameterName(paramName: String): String =
        if (isEnclosedBySameName(paramName)) paramName + "$" else paramName

    private fun pushNestedAnonDecl(anonDecl: Declaration) {
        nestedAnonDeclarations.push(anonDecl)
    }

    private fun popNestedAnonDecl() {
        nestedAnonDeclarations.pop()
    }

    fun begin() {
        if (!inAnonymousNested()) {
            if (isNested()) {
                sourceFileBuilder().incrAlign()
            }
            appendBlankLine()
            emitDocComment(structTree)
            classBegin()
            emitDefaultConstructor()
            emitLayoutDecl()
        }
    }

    fun end() {
        if (!inAnonymousNested()) {
            emitAsSlice()
            emitSizeof()
            emitAllocatorAllocate()
            emitAllocatorAllocateArray()
            emitReinterpret()
            classEnd()
            if (isNested()) {
                sourceFileBuilder().decrAlign()
            }
        } else {
            // we're in an anonymous struct which got merged into this one, return this very builder and keep it open
            popNestedAnonDecl()
        }
    }

    private fun inAnonymousNested(): Boolean = !nestedAnonDeclarations.isEmpty()

    override fun addStruct(structTree: Declaration.Scoped): StructBuilder {
        return if (AnonymousStruct.isPresent(structTree)) {
            // nested anon struct - merge into this builder!
            pushNestedAnonDecl(structTree)
            this
        } else {
            val builder = StructBuilder(
                sourceFileBuilder(), "public static",
                JavaName.getOrThrow(structTree), this, runtimeHelperName(), structTree
            )
            builder.begin()
            builder
        }
    }

    override fun addFunctionalInterface(parentDecl: Declaration, funcType: Type.Function) {
        incrAlign()
        FunctionalInterfaceBuilder.generate(
            sourceFileBuilder(), JavaFunctionalInterfaceName.getOrThrow(parentDecl),
            this, runtimeHelperName(), parentDecl, funcType, true
        )
        decrAlign()
    }

    override fun addVar(varTree: Declaration.Variable) {
        val javaName = JavaName.getOrThrow(varTree)
        appendBlankLine()
        val layoutField = emitLayoutFieldDecl(varTree, javaName)
        appendBlankLine()
        val offsetField = emitOffsetFieldDecl(varTree, javaName)
        if (Utils.isArray(varTree.type()) || Utils.isStructOrUnion(varTree.type())) {
            emitSegmentGetter(javaName, varTree, offsetField, layoutField)
            emitSegmentSetter(javaName, varTree, offsetField, layoutField)
            val dims = Utils.dimensions(varTree.type()).size
            if (dims > 0) {
                emitDimensionsFieldDecl(varTree, javaName)
                val arrayHandle = emitArrayElementHandle(javaName, varTree, layoutField, dims)
                val indexList = IndexList.of(dims)
                emitFieldArrayGetter(javaName, varTree, arrayHandle, offsetField, indexList)
                emitFieldArraySetter(javaName, varTree, arrayHandle, offsetField, indexList)
            }
        } else if (Utils.isPointer(varTree.type()) || Utils.isPrimitive(varTree.type())) {
            emitFieldGetter(javaName, varTree, layoutField, offsetField)
            emitFieldSetter(javaName, varTree, layoutField, offsetField)
        } else {
            throw IllegalArgumentException(String.format("Type not supported: %1\$s", varTree.type()))
        }
    }

    private fun prefixNamesList(): List<String> =
        nestedAnonDeclarations.stream()
            .map { d -> AnonymousStruct.anonName(d as Declaration.Scoped) }
            .toList().reversed()

    private fun fieldElementPaths(nativeName: String): String {
        val builder = StringBuilder()
        var prefix = ""
        for (prefixElementName in prefixNamesList()) {
            builder.append(prefix + "groupElement(\"$prefixElementName\")")
            prefix = ", "
        }
        builder.append(prefix + "groupElement(\"$nativeName\")")
        return builder.toString()
    }

    private fun emitFieldDocComment(varTree: Declaration.Variable, header: String) {
        incrAlign()
        emitDocComment(varTree, header)
        decrAlign()
    }

    private fun kindName(): String =
        if (structTree.kind() == Scoped.Kind.STRUCT) "struct" else "union"

    private fun emitFieldGetter(javaName: String, varTree: Declaration.Variable, layoutField: String, offsetField: String) {
        val segmentParam = safeParameterName(kindName())
        val type = Utils.carrierFor(varTree.type())
        appendBlankLine()
        emitFieldDocComment(varTree, "Getter for field:")
        appendIndentedLines("""
            public static %1${'$'}s %2${'$'}s(MemorySegment %3${'$'}s) {
                return %3${'$'}s.get(%4${'$'}s, %5${'$'}s);
            }
            """.trimIndent() + "\n",
            type.simpleName, javaName, segmentParam, layoutField, offsetField)
    }

    private fun emitFieldSetter(javaName: String, varTree: Declaration.Variable, layoutField: String, offsetField: String) {
        val segmentParam = safeParameterName(kindName())
        val valueParam = safeParameterName("fieldValue")
        val type = Utils.carrierFor(varTree.type())
        appendBlankLine()
        emitFieldDocComment(varTree, "Setter for field:")
        appendIndentedLines("""
            public static void %1${'$'}s(MemorySegment %2${'$'}s, %3${'$'}s %4${'$'}s) {
                %2${'$'}s.set(%5${'$'}s, %6${'$'}s, %4${'$'}s);
            }
            """.trimIndent() + "\n",
            javaName, segmentParam, type.simpleName, valueParam, layoutField, offsetField)
    }

    private fun emitSegmentGetter(javaName: String, varTree: Declaration.Variable, offsetField: String, layoutField: String) {
        appendBlankLine()
        emitFieldDocComment(varTree, "Getter for field:")
        val segmentParam = safeParameterName(kindName())
        appendIndentedLines("""
            public static MemorySegment %1${'$'}s(MemorySegment %2${'$'}s) {
                return %2${'$'}s.asSlice(%3${'$'}s, %4${'$'}s.byteSize());
            }
            """.trimIndent() + "\n",
            javaName, segmentParam, offsetField, layoutField)
    }

    private fun emitSegmentSetter(javaName: String, varTree: Declaration.Variable, offsetField: String, layoutField: String) {
        appendBlankLine()
        emitFieldDocComment(varTree, "Setter for field:")
        val segmentParam = safeParameterName(kindName())
        val valueParam = safeParameterName("fieldValue")
        appendIndentedLines("""
            public static void %1${'$'}s(MemorySegment %2${'$'}s, MemorySegment %3${'$'}s) {
                MemorySegment.copy(%3${'$'}s, 0L, %2${'$'}s, %4${'$'}s, %5${'$'}s.byteSize());
            }
            """.trimIndent() + "\n",
            javaName, segmentParam, valueParam, offsetField, layoutField)
    }

    private fun emitArrayElementHandle(javaName: String, varTree: Declaration.Variable, fieldLayoutName: String, dims: Int): String {
        val arrayHandleName = String.format("%1\$s\$ELEM_HANDLE", javaName)
        val path = IntStream.range(0, dims)
            .mapToObj { _ -> "sequenceElement()" }
            .collect(Collectors.joining(", "))
        val elemType = Utils.typeOrElemType(varTree.type())
        if (Utils.isStructOrUnion(elemType)) {
            appendIndentedLines("""
                private static final MethodHandle %1${'$'}s = %2${'$'}s.sliceHandle(%3${'$'}s);
                """.trimIndent() + "\n",
                arrayHandleName, fieldLayoutName, path)
        } else {
            appendIndentedLines("""
                private static final VarHandle %1${'$'}s = %2${'$'}s.varHandle(%3${'$'}s);
                """.trimIndent() + "\n",
                arrayHandleName, fieldLayoutName, path)
        }
        return arrayHandleName
    }

    private fun emitFieldArrayGetter(javaName: String, varTree: Declaration.Variable, arrayElementHandle: String, offsetField: String, indexList: IndexList) {
        val segmentParam = safeParameterName(kindName())
        val elemType = Utils.typeOrElemType(varTree.type())
        val elemTypeCls = Utils.carrierFor(elemType)
        appendBlankLine()
        emitFieldDocComment(varTree, "Indexed getter for field:")
        if (Utils.isStructOrUnion(elemType)) {
            appendIndentedLines("""
                public static MemorySegment %1${'$'}s(MemorySegment %2${'$'}s, %3${'$'}s) {
                    try {
                        return (MemorySegment)%4${'$'}s.invokeExact(%2${'$'}s, %5${'$'}s, %6${'$'}s);
                    } catch (Error | RuntimeException ex) {
                        throw ex;
                    } catch (Throwable ex${'$'}) {
                        throw new AssertionError("should not reach here", ex${'$'});
                    }
                }
                """.trimIndent() + "\n",
                javaName, segmentParam, indexList.decl, arrayElementHandle, offsetField, indexList.use)
        } else {
            appendIndentedLines("""
                public static %1${'$'}s %2${'$'}s(MemorySegment %3${'$'}s, %4${'$'}s) {
                    return (%1${'$'}s)%5${'$'}s.get(%3${'$'}s, %6${'$'}s, %7${'$'}s);
                }
                """.trimIndent() + "\n",
                elemTypeCls.simpleName, javaName, segmentParam,
                indexList.decl, arrayElementHandle, offsetField, indexList.use)
        }
    }

    private fun emitFieldArraySetter(javaName: String, varTree: Declaration.Variable, arrayElementHandle: String, offsetField: String, indexList: IndexList) {
        val segmentParam = safeParameterName(kindName())
        val valueParam = safeParameterName("fieldValue")
        val elemType = Utils.typeOrElemType(varTree.type())
        val elemTypeCls = Utils.carrierFor(elemType)
        appendBlankLine()
        emitFieldDocComment(varTree, "Indexed setter for field:")
        if (Utils.isStructOrUnion(elemType)) {
            appendIndentedLines("""
                public static void %1${'$'}s(MemorySegment %2${'$'}s, %3${'$'}s, MemorySegment %4${'$'}s) {
                    MemorySegment.copy(%4${'$'}s, 0L, %1${'$'}s(%2${'$'}s, %5${'$'}s), 0L, %6${'$'}s.byteSize());
                }
                """.trimIndent() + "\n",
                javaName, segmentParam, indexList.decl,
                valueParam, indexList.use, layoutString(elemType))
        } else {
            appendIndentedLines("""
                public static void %1${'$'}s(MemorySegment %2${'$'}s, %3${'$'}s, %4${'$'}s %5${'$'}s) {
                    %6${'$'}s.set(%2${'$'}s, %7${'$'}s, %8${'$'}s, %5${'$'}s);
                }
                """.trimIndent() + "\n",
                javaName, segmentParam, indexList.decl, elemTypeCls.simpleName,
                valueParam, arrayElementHandle, offsetField, indexList.use)
        }
    }

    private fun emitAsSlice() {
        val arrayParam = safeParameterName("array")
        appendIndentedLines("""

            /**
             * Obtains a slice of {@code arrayParam} which selects the array element at {@code index}.
             * The returned segment has address {@code arrayParam.address() + index * layout().byteSize()}
             */
            public static MemorySegment asSlice(MemorySegment %1${'$'}s, long index) {
                return %1${'$'}s.asSlice(layout().byteSize() * index);
            }
            """.trimIndent() + "\n", arrayParam)
    }

    private fun emitSizeof() {
        appendIndentedLines("""

            /**
             * The size (in bytes) of this %1${'$'}s
             */
            public static long sizeof() { return layout().byteSize(); }
            """.trimIndent() + "\n", kindName())
    }

    private fun emitAllocatorAllocate() {
        val allocatorParam = safeParameterName("allocator")
        appendIndentedLines("""

            /**
             * Allocate a segment of size {@code layout().byteSize()} using {@code %1${'$'}s}
             */
            public static MemorySegment allocate(SegmentAllocator %1${'$'}s) {
                return %1${'$'}s.allocate(layout());
            }
            """.trimIndent() + "\n", allocatorParam)
    }

    private fun emitAllocatorAllocateArray() {
        val allocatorParam = safeParameterName("allocator")
        val elementCountParam = safeParameterName("elementCount")
        appendIndentedLines("""

            /**
             * Allocate an array of size {@code %1${'$'}s} using {@code %2${'$'}s}.
             * The returned segment has size {@code %1${'$'}s * layout().byteSize()}.
             */
            public static MemorySegment allocateArray(long %1${'$'}s, SegmentAllocator %2${'$'}s) {
                return %2${'$'}s.allocate(MemoryLayout.sequenceLayout(%1${'$'}s, layout()));
            }
            """.trimIndent() + "\n", elementCountParam, allocatorParam)
    }

    private fun emitReinterpret() {
        appendIndentedLines("""

            /**
             * Reinterprets {@code addr} using target {@code arena} and {@code cleanupAction} (if any).
             * The returned segment has size {@code layout().byteSize()}
             */
            public static MemorySegment reinterpret(MemorySegment addr, Arena arena, Consumer<MemorySegment> cleanup) {
                return reinterpret(addr, 1, arena, cleanup);
            }

            /**
             * Reinterprets {@code addr} using target {@code arena} and {@code cleanupAction} (if any).
             * The returned segment has size {@code elementCount * layout().byteSize()}
             */
            public static MemorySegment reinterpret(MemorySegment addr, long elementCount, Arena arena, Consumer<MemorySegment> cleanup) {
                return addr.reinterpret(layout().byteSize() * elementCount, arena, cleanup);
            }
            """.trimIndent() + "\n")
    }

    private fun emitLayoutDecl() {
        appendIndentedLines("""

            private static final GroupLayout ${'$'}LAYOUT = %1${'$'}s;

            /**
             * The layout of this %2${'$'}s
             */
            public static final GroupLayout layout() {
                return ${'$'}LAYOUT;
            }
            """.trimIndent() + "\n", structOrUnionLayoutString(structType), kindName())
    }

    private fun emitOffsetFieldDecl(field: Declaration.Variable, javaName: String): String {
        val offsetFieldName = javaName + "\$OFFSET"
        appendIndentedLines(String.format("""
            private static final long %1${'$'}s = ${'$'}LAYOUT.byteOffset(%2${'$'}s);
            """.trimIndent() + "\n",
            offsetFieldName, fieldElementPaths(field.name())))
        appendBlankLine()
        emitFieldDocComment(field, "Offset for field:")
        appendIndentedLines("""
            public static final long %1${'$'}s${'$'}offset() {
                return %2${'$'}s;
            }
            """.trimIndent() + "\n", javaName, offsetFieldName)
        return offsetFieldName
    }

    private fun emitLayoutFieldDecl(field: Declaration.Variable, javaName: String): String {
        val layoutFieldName = javaName + "\$LAYOUT"
        val layoutType = Utils.layoutCarrierFor(field.type()).simpleName
        appendIndentedLines("""
            private static final %1${'$'}s %2${'$'}s = (%1${'$'}s)${'$'}LAYOUT.select(%3${'$'}s);
            """.trimIndent() + "\n",
            layoutType, layoutFieldName, fieldElementPaths(field.name()))
        appendBlankLine()
        emitFieldDocComment(field, "Layout for field:")
        appendIndentedLines("""
            public static final %1${'$'}s %2${'$'}s${'$'}layout() {
                return %3${'$'}s;
            }
            """.trimIndent() + "\n", layoutType, javaName, layoutFieldName)
        return layoutFieldName
    }

    private fun emitDimensionsFieldDecl(field: Declaration.Variable, javaName: String) {
        val dimsFieldName = javaName + "\$DIMS"
        val dimensions = Utils.dimensions(field.type())
        val dimsString = dimensions.stream().map { obj: Long -> obj.toString() }
            .collect(Collectors.joining(", "))
        appendIndentedLines("""

            private static long[] %1${'$'}s = { %2${'$'}s };
            """.trimIndent() + "\n", dimsFieldName, dimsString)
        appendBlankLine()
        emitFieldDocComment(field, "Dimensions for array field:")
        appendIndentedLines("""
            public static long[] %1${'$'}s${'$'}dimensions() {
                return %2${'$'}s;
            }
            """.trimIndent() + "\n", javaName, dimsFieldName)
    }

    private fun structOrUnionLayoutString(type: Type): String {
        return when {
            type is Declared && Utils.isStructOrUnion(type) -> structOrUnionLayoutString(0, type.tree(), 0)
            else -> throw UnsupportedOperationException(type.toString())
        }
    }

    companion object {
        private fun recordMemberOffset(member: Declaration): Long {
            return if (member is Declaration.Variable) {
                ClangOffsetOf.get(member)!!
            } else {
                // anonymous struct
                AnonymousStruct.getOrThrow(member as Scoped).offset!!
            }
        }
    }

    private fun structOrUnionLayoutString(base: Long, scoped: Declaration.Scoped, indent: Int): String {
        val memberLayouts = mutableListOf<String>()

        val isStruct = scoped.kind() == Scoped.Kind.STRUCT

        val scopedTypeAlign = ClangAlignOf.getOrThrow(scoped) / 8
        var offset = base

        var size = 0L // bits
        for (member in scoped.members()) {
            if (!Skip.isPresent(member)) {
                val nextOffset = recordMemberOffset(member)
                val delta = nextOffset - offset
                if (delta > 0) {
                    memberLayouts.add(paddingLayoutString(delta / 8, indent + 1))
                    offset += delta
                    if (isStruct) {
                        size += delta
                    }
                }
                val memberLayout: String
                if (member is Declaration.Variable) {
                    val fieldTypeAlign = ClangAlignOf.getOrThrow(member) / 8
                    val expectedAlign = if (nextOffset != 0L)
                        minOf(java.lang.Long.lowestOneBit(nextOffset / 8), fieldTypeAlign)
                    else
                        fieldTypeAlign
                    val finalExpectedAlign = minOf(expectedAlign, scopedTypeAlign)
                    val fieldLayout = fieldLayoutString(member.type(), fieldTypeAlign, finalExpectedAlign)
                    memberLayout = String.format("%1\$s%2\$s.withName(\"%3\$s\")", indentString(indent + 1), fieldLayout, member.name())
                } else {
                    // anon struct
                    memberLayout = structOrUnionLayoutString(offset, member as Scoped, indent + 1)
                }
                memberLayouts.add(memberLayout)
                // update offset and size
                val fieldSize = ClangSizeOf.getOrThrow(member)
                if (isStruct) {
                    offset += fieldSize
                    size += fieldSize
                } else {
                    size = maxOf(size, ClangSizeOf.getOrThrow(member))
                }
            }
        }
        val expectedSize = ClangSizeOf.getOrThrow(scoped)
        if (size != expectedSize) {
            val trailPadding = if (isStruct)
                (expectedSize - size) / 8
            else
                expectedSize / 8
            memberLayouts.add(paddingLayoutString(trailPadding, indent + 1))
        }

        val prefix = if (isStruct)
            String.format("%1\$sMemoryLayout.structLayout(\n", indentString(indent))
        else
            String.format("%1\$sMemoryLayout.unionLayout(\n", indentString(indent))
        val suffix = String.format("\n%1\$s)", indentString(indent))
        val layoutString = memberLayouts.stream()
            .collect(Collectors.joining(",\n", prefix, suffix))

        val name = if (scoped.name().isEmpty())
            AnonymousStruct.anonName(scoped)
        else
            scoped.name()
        return String.format("%1\$s.withName(\"%2\$s\")", layoutString, name)
    }
}
