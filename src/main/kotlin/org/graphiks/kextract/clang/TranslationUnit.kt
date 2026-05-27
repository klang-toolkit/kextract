package org.graphiks.kextract.clang

import java.io.IOException
import java.lang.foreign.Arena
import java.lang.foreign.MemorySegment
import java.lang.foreign.ValueLayout
import java.nio.file.Path
import java.util.function.Consumer
import org.graphiks.kextract.clang.libclang.*
import org.graphiks.kextract.clang.LibClang.STRING_ALLOCATOR

class TranslationUnit internal constructor(addr: MemorySegment) :
    ClangDisposable(addr, Consumer { clang_disposeTranslationUnit(it) }) {

    companion object {
        private const val MAX_RETRIES = 10
    }

    fun getCursor(): Cursor {
        val cursor = clang_getTranslationUnitCursor(arena, ptr)
        return Cursor(cursor, this)
    }

    fun save(path: Path) {
        Arena.ofConfined().use { arena ->
            val pathStr = arena.allocateFrom(path.toAbsolutePath().toString())
            val res = SaveError.valueOf(clang_saveTranslationUnit(ptr, pathStr, 0))
            if (res != SaveError.None) throw TranslationUnitSaveException(path, res)
        }
    }

    internal fun processDiagnostics(dh: Consumer<Diagnostic>) {
        val cntDiags = clang_getNumDiagnostics(ptr)
        for (i in 0 until cntDiags) {
            val diag = clang_getDiagnostic(ptr, i)
            dh.accept(Diagnostic(diag))
        }
    }

    fun reparse(vararg inMemoryFiles: Index.UnsavedFile) {
        Arena.ofConfined().use { arena ->
            val files = if (inMemoryFiles.isEmpty()) null
                        else arena.allocate(CXUnsavedFile.layout, inMemoryFiles.size.toLong()).also { seg ->
                            inMemoryFiles.forEachIndexed { i, f ->
                                val start = seg.asSlice(i * CXUnsavedFile.byteSize)
                                CXUnsavedFile.Filename(start, arena.allocateFrom(f.file))
                                CXUnsavedFile.Contents(start, arena.allocateFrom(f.contents))
                                CXUnsavedFile.Length(start, f.contents.length.toLong())
                            }
                        }
            var code: ErrorCode
            var tries = 0
            do {
                code = ErrorCode.valueOf(clang_reparseTranslationUnit(
                    ptr, inMemoryFiles.size,
                    files ?: MemorySegment.NULL,
                    clang_defaultReparseOptions(ptr)))
            } while (code == ErrorCode.Crashed && ++tries < MAX_RETRIES)

            if (code != ErrorCode.Success) throw IllegalStateException("Re-parsing failed: $code")
        }
    }

    fun reparse(dh: Consumer<Diagnostic>, vararg inMemoryFiles: Index.UnsavedFile) {
        reparse(*inMemoryFiles)
        processDiagnostics(dh)
    }

    fun tokens(range: SourceRange): Array<String> {
        return tokenize(range).use { tokens ->
            Array(tokens.size) { i -> tokens.getToken(i).spelling() }
        }
    }

    fun tokenize(range: SourceRange): Tokens {
        Arena.ofConfined().use { arena ->
            val p    = arena.allocate(ValueLayout.ADDRESS)
            val pCnt = arena.allocate(ValueLayout.JAVA_INT)
            clang_tokenize(ptr, range.segment, p, pCnt)
            return Tokens(p.get(ValueLayout.ADDRESS, 0), pCnt.get(ValueLayout.JAVA_INT, 0))
        }
    }

    inner class Tokens(addr: MemorySegment, val size: Int) : ClangDisposable(
        addr, size.toLong() * CXToken.byteSize,
        Consumer { addrCleanup -> clang_disposeTokens(this@TranslationUnit.ptr, addrCleanup, size) }
    ) {
        fun getTokenSegment(idx: Int): MemorySegment = ptr.asSlice(idx * CXToken.byteSize)

        fun getToken(index: Int): Token = Token(getTokenSegment(index), this)

        override fun toString(): String = buildString {
            for (i in 0 until size) append("Token[$i]=${getToken(i).spelling()}\n")
        }

        inner class Token(token: MemorySegment, owner: ClangDisposable) :
            ClangDisposable.Owned(token, owner) {

            fun kind(): Int = clang_getTokenKind(segment)

            fun spelling(): String {
                val s = clang_getTokenSpelling(STRING_ALLOCATOR.get(), this@TranslationUnit.ptr, segment)
                return LibClang.CXStrToString(s)
            }

            fun getLocation(): SourceLocation {
                val loc = clang_getTokenLocation(owner, this@TranslationUnit.ptr, segment)
                return SourceLocation(loc, owner)
            }

            fun getExtent(): SourceRange {
                val ext = clang_getTokenExtent(owner, this@TranslationUnit.ptr, segment)
                return SourceRange(ext, owner)
            }
        }
    }

    class TranslationUnitSaveException(path: Path, val error: SaveError) :
        IOException("Cannot save translation unit to: ${path.toAbsolutePath()}. Error: $error")
}
