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

import java.lang.foreign.MemorySegment
import java.util.function.Consumer
import org.openjdk.kextract.clang.libclang.*

class Diagnostic internal constructor(ptr: MemorySegment) :
    ClangDisposable(ptr, Consumer { clang_disposeDiagnostic(it) }) {

    companion object {
        @JvmField val CXDiagnostic_Ignored = CXDiagnostic_Ignored()
        @JvmField val CXDiagnostic_Note    = CXDiagnostic_Note()
        @JvmField val CXDiagnostic_Warning = CXDiagnostic_Warning()
        @JvmField val CXDiagnostic_Error   = CXDiagnostic_Error()
        @JvmField val CXDiagnostic_Fatal   = CXDiagnostic_Fatal()
    }

    fun severity(): Int = clang_getDiagnosticSeverity(ptr)

    fun location(): SourceLocation {
        val loc = clang_getDiagnosticLocation(arena, ptr)
        return SourceLocation(loc, this)
    }

    fun spelling(): String {
        val spelling = clang_getDiagnosticSpelling(LibClang.STRING_ALLOCATOR.get(), ptr)
        return LibClang.CXStrToString(spelling)
    }

    override fun toString(): String {
        val diagString = clang_formatDiagnostic(arena, ptr,
            clang_defaultDiagnosticDisplayOptions())
        return LibClang.CXStrToString(diagString)
    }
}
