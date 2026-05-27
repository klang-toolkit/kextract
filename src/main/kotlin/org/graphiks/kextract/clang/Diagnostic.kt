package org.graphiks.kextract.clang

import java.lang.foreign.MemorySegment
import java.util.function.Consumer
import org.graphiks.kextract.clang.libclang.*

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
