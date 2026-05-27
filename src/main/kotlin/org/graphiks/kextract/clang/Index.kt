package org.graphiks.kextract.clang

import java.lang.foreign.Arena
import java.lang.foreign.MemorySegment
import java.lang.foreign.ValueLayout
import java.util.function.Consumer
import org.graphiks.kextract.clang.libclang.*

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
