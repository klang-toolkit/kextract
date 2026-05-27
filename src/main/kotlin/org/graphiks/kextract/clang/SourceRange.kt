package org.graphiks.kextract.clang

import java.lang.foreign.MemorySegment
import org.graphiks.kextract.clang.libclang.*

class SourceRange internal constructor(range: MemorySegment, owner: ClangDisposable) :
    ClangDisposable.Owned(range, owner) {

    fun getBegin(): SourceLocation {
        val rangeStart = clang_getRangeStart(owner, segment)
        return SourceLocation(rangeStart, owner)
    }

    fun getEnd(): SourceLocation {
        val rangeEnd = clang_getRangeEnd(owner, segment)
        return SourceLocation(rangeEnd, owner)
    }
}
