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
import java.nio.file.Path
import java.nio.file.Paths
import org.openjdk.kextract.clang.libclang.*

class SourceLocation internal constructor(loc: MemorySegment, owner: ClangDisposable) :
    ClangDisposable.Owned(loc, owner) {

    private val loc: MemorySegment = loc

    private fun interface LocationFactory {
        fun get(loc: MemorySegment, file: MemorySegment, line: MemorySegment,
                column: MemorySegment, offset: MemorySegment)
    }

    private fun getLocation(fn: LocationFactory): Location {
        Arena.ofConfined().use { arena ->
            val file   = arena.allocate(ValueLayout.ADDRESS)
            val line   = arena.allocate(ValueLayout.JAVA_INT)
            val col    = arena.allocate(ValueLayout.JAVA_INT)
            val offset = arena.allocate(ValueLayout.JAVA_INT)
            fn.get(this.loc, file, line, col, offset)
            val fname = file.get(ValueLayout.ADDRESS, 0)
            val str = if (fname == MemorySegment.NULL) null else getFileName(fname)
            return Location(str, line.get(ValueLayout.JAVA_INT, 0), col.get(ValueLayout.JAVA_INT, 0), offset.get(ValueLayout.JAVA_INT, 0))
        }
    }

    private fun getFileName(fname: MemorySegment): String {
        val filename = clang_getFileName(LibClang.STRING_ALLOCATOR.get(), fname)
        return LibClang.CXStrToString(filename)
    }

    fun getFileLocation(): Location      = getLocation { loc, f, l, c, o -> clang_getFileLocation(loc, f, l, c, o) }
    fun getExpansionLocation(): Location = getLocation { loc, f, l, c, o -> clang_getExpansionLocation(loc, f, l, c, o) }
    fun getSpellingLocation(): Location  = getLocation { loc, f, l, c, o -> clang_getSpellingLocation(loc, f, l, c, o) }

    fun isInSystemHeader(): Boolean = clang_Location_isInSystemHeader(this.loc) != 0
    fun isFromMainFile(): Boolean   = clang_Location_isFromMainFile(this.loc) != 0

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        return other is SourceLocation && getFileLocation() == other.getFileLocation()
    }

    override fun hashCode(): Int = getFileLocation().hashCode()

    data class Location(val path: Path?, val line: Int, val column: Int, val offset: Int) {
        internal constructor(filename: String?, line: Int, column: Int, offset: Int) : this(
            if (filename.isNullOrEmpty()) null else Paths.get(filename),
            line, column, offset
        )

        override fun toString(): String = "$path:$line:$column:$offset"
    }
}
