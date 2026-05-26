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
import java.lang.foreign.ValueLayout
import java.util.function.Consumer
import org.openjdk.kextract.clang.libclang.*

class Index internal constructor(addr: MemorySegment) :
    ClangDisposable(addr, Consumer { clang_disposeIndex(it) }) {

    class UnsavedFile private constructor(val file: String, val contents: String) {
        companion object {
            @JvmStatic fun of(file: String, contents: String) = UnsavedFile(file, contents)
        }
    }

    class ParsingFailedException(val srcFile: String, val code: ErrorCode) :
        RuntimeException("Failed to parse $srcFile: $code")

    private fun parseTU(
        file: String, content: String,
        dh: Consumer<Diagnostic>, options: Int, vararg args: String
    ): TranslationUnit {
        Arena.ofConfined().use { arena ->
            val fileSeg    = arena.allocateFrom(file)
            val contentSeg = arena.allocateFrom(content)
            val cargs = if (args.isEmpty()) null
                        else arena.allocate(ValueLayout.ADDRESS, args.size.toLong()).also { seg ->
                            args.forEachIndexed { i, arg ->
                                seg.set(ValueLayout.ADDRESS, i * ValueLayout.ADDRESS.byteSize(), arena.allocateFrom(arg))
                            }
                        }

            val unsavedFile = CXUnsavedFile.allocate(arena)
            CXUnsavedFile.Filename(unsavedFile, fileSeg)
            CXUnsavedFile.Contents(unsavedFile, contentSeg)
            CXUnsavedFile.Length(unsavedFile, content.length.toLong())

            val outAddress = arena.allocate(ValueLayout.ADDRESS)
            val code = ErrorCode.valueOf(clang_parseTranslationUnit2(
                ptr, fileSeg,
                cargs ?: MemorySegment.NULL, args.size,
                unsavedFile, 1,
                options, outAddress
            ))

            val tu = outAddress.get(ValueLayout.ADDRESS, 0)
            val rv = TranslationUnit(tu)
            rv.processDiagnostics(dh)

            if (code != ErrorCode.Success) throw ParsingFailedException(file, code)
            return rv
        }
    }

    private fun defaultOptions(detailedPreprocessorRecord: Boolean): Int {
        var rv = CXTranslationUnit_ForSerialization()
        rv = rv or CXTranslationUnit_SkipFunctionBodies()
        if (detailedPreprocessorRecord) rv = rv or CXTranslationUnit_DetailedPreprocessingRecord()
        return rv
    }

    fun parse(
        filename: String, content: String,
        dh: Consumer<Diagnostic>, detailedPreprocessorRecord: Boolean, vararg args: String
    ): TranslationUnit = parseTU(filename, content, dh, defaultOptions(detailedPreprocessorRecord), *args)

    fun parse(
        filename: String, content: String,
        detailedPreprocessorRecord: Boolean, vararg args: String
    ): TranslationUnit = parse(filename, content, Consumer {}, detailedPreprocessorRecord, *args)
}
