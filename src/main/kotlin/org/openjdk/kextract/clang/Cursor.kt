/*
 *  Copyright (c) 2020, 2024, Oracle and/or its affiliates. All rights reserved.
 *  DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 *  This code is free software; you can redistribute it and/or modify it
 *  under the terms of the GNU General Public License version 2 only, as
 *  published by the Free Software Foundation.  Oracle designates this
 *  particular file is subject to the "Classpath" exception as provided
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
package org.openjdk.kextract.clang

import java.lang.foreign.Arena
import java.lang.foreign.MemorySegment
import java.lang.foreign.SegmentAllocator
import java.lang.foreign.ValueLayout
import java.util.Objects
import java.util.function.Consumer
import java.util.function.Predicate
import org.openjdk.kextract.clang.libclang.*

class Cursor internal constructor(segment: MemorySegment, owner: ClangDisposable) :
    ClangDisposable.Owned(segment, owner) {

    private val kind: Int = clang_getCursorKind(segment)

    fun isDeclaration(): Boolean      = clang_isDeclaration(kind) != 0
    fun isPreprocessing(): Boolean     = clang_isPreprocessing(kind) != 0
    fun isInvalid(): Boolean           = clang_isInvalid(kind) != 0
    fun isDefinition(): Boolean        = clang_isCursorDefinition(segment) != 0
    fun isAttribute(): Boolean         = clang_isAttribute(kind) != 0
    fun isAnonymousStruct(): Boolean   = clang_Cursor_isAnonymousRecordDecl(segment) != 0
    fun isAnonymous(): Boolean         = clang_Cursor_isAnonymous(segment) != 0
    fun isMacroFunctionLike(): Boolean = clang_Cursor_isMacroFunctionLike(segment) != 0

    fun spelling(): String {
        val s = clang_getCursorSpelling(LibClang.STRING_ALLOCATOR.get(), segment)
        return LibClang.CXStrToString(s)
    }

    fun USR(): String {
        val u = clang_getCursorUSR(LibClang.STRING_ALLOCATOR.get(), segment)
        return LibClang.CXStrToString(u)
    }

    fun prettyPrinted(policy: PrintingPolicy): String {
        val s = clang_getCursorPrettyPrinted(LibClang.STRING_ALLOCATOR.get(), segment, policy.ptr())
        return LibClang.CXStrToString(s)
    }

    fun prettyPrinted(): String = getPrintingPolicy().use { prettyPrinted(it) }

    fun displayName(): String {
        val s = clang_getCursorDisplayName(LibClang.STRING_ALLOCATOR.get(), segment)
        return LibClang.CXStrToString(s)
    }

    fun equalCursor(other: Cursor): Boolean = clang_equalCursors(segment, other.segment) != 0

    fun type(): Type               = Type(clang_getCursorType(owner, segment), owner)
    fun getEnumDeclIntegerType(): Type = Type(clang_getEnumDeclIntegerType(owner, segment), owner)
    fun getDefinition(): Cursor    = Cursor(clang_getCursorDefinition(owner, segment), owner)
    fun isFunctionInlined(): Boolean = clang_Cursor_isFunctionInlined(segment) != 0

    fun getSourceLocation(): SourceLocation? {
        val loc = clang_getCursorLocation(owner, segment)
        Arena.ofConfined().use { arena ->
            if (clang_equalLocations(loc, clang_getNullLocation(arena)) != 0) return null
        }
        return SourceLocation(loc, owner)
    }

    fun getExtent(): SourceRange? {
        val range = clang_getCursorExtent(owner, segment)
        if (clang_Range_isNull(range) != 0) return null
        return SourceRange(range, owner)
    }

    fun numberOfArgs(): Int          = clang_Cursor_getNumArguments(segment)
    fun getArgument(idx: Int): Cursor = Cursor(clang_Cursor_getArgument(owner, segment, idx), owner)

    fun getEnumConstantValue(): Long         = clang_getEnumConstantDeclValue(segment)
    fun getEnumConstantUnsignedValue(): Long  = clang_getEnumConstantDeclUnsignedValue(segment)
    fun isBitField(): Boolean                = clang_Cursor_isBitField(segment) != 0
    fun getBitFieldWidth(): Int              = clang_getFieldDeclBitWidth(segment)

    fun kind(): CursorKind      = CursorKind.valueOf(kind)
    fun language(): CursorLanguage = CursorLanguage.valueOf(clang_getCursorLanguage(segment))
    fun linkage(): LinkageKind  = LinkageKind.valueOf(clang_getCursorLinkage(segment))
    fun kind0(): Int            = kind

    fun getCursorReferenced(): Cursor =
        Cursor(clang_getCursorReferenced(owner, segment), owner)

    fun forEach(action: Consumer<Cursor>) {
        CursorChildren.forEach(this, action)
    }

    fun forEachShortCircuit(action: Predicate<Cursor>) {
        CursorChildren.forEachShortCircuit(this, action)
    }

    private object CursorChildren {

        class Context(val action: Predicate<Cursor>, val owner: ClangDisposable) {
            var exception: RuntimeException? = null

            fun visit(segment: MemorySegment): Boolean {
                return try {
                    action.test(Cursor(segment, owner))
                } catch (ex: RuntimeException) {
                    exception = ex
                    false
                }
            }

            fun handleExceptions() { exception?.let { throw it } }
        }

        var pendingContext: Context? = null

        private val callback: MemorySegment = CXCursorVisitor.allocate({ c, _, _ ->
            if (pendingContext!!.visit(c)) CXChildVisit_Continue()
            else CXChildVisit_Break()
        }, Arena.global())

        fun forEach(c: Cursor, op: Consumer<Cursor>) {
            forEachShortCircuit(c) { decl -> op.accept(decl); true }
        }

        @Synchronized
        fun forEachShortCircuit(c: Cursor, op: Predicate<Cursor>) {
            val prevContext = pendingContext
            try {
                pendingContext = Context(op, c.owner)
                clang_visitChildren(c.segment, callback, MemorySegment.NULL)
                pendingContext!!.handleExceptions()
            } finally {
                pendingContext = prevContext
            }
        }
    }

    fun getTranslationUnit(): TranslationUnit =
        TranslationUnit(clang_Cursor_getTranslationUnit(segment))

    fun eval(): EvalResult {
        val ptr = clang_Cursor_Evaluate(segment)
        return if (ptr == MemorySegment.NULL) EvalResult.erroneous else EvalResult(ptr)
    }

    fun getPrintingPolicy(): PrintingPolicy =
        PrintingPolicy(clang_getCursorPrintingPolicy(segment))

    fun toKey(): Key = Key(this)

    class Key internal constructor(cursor: Cursor) {
        val spelling: String = cursor.spelling()
        val kind: CursorKind = cursor.kind()
        val payload: MemorySegment = MemorySegment.ofArray(ByteArray(CXCursor.byteSize.toInt())).also {
            it.copyFrom(cursor.segment)
        }

        override fun equals(other: Any?): Boolean {
            if (other !is Key) return false
            if (kind != other.kind) return false
            if (spelling != other.spelling) return false
            val allocator = SegmentAllocator.slicingAllocator(COMPARISON_SEGMENT)
            return clang_equalCursors(toSegment(allocator), other.toSegment(allocator)) != 0
        }

        override fun hashCode(): Int = Objects.hash(kind, spelling)

        private fun toSegment(allocator: SegmentAllocator): MemorySegment =
            allocator.allocateFrom(ValueLayout.JAVA_BYTE, payload, ValueLayout.JAVA_BYTE, 0, CXCursor.byteSize)

        companion object {
            private val COMPARISON_SEGMENT: MemorySegment = Arena.ofAuto().allocate(CXCursor.layout, 2)
        }
    }
}
