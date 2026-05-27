package org.graphiks.kextract.clang

import java.lang.foreign.Arena
import java.lang.foreign.FunctionDescriptor
import java.lang.foreign.Linker
import java.lang.foreign.MemorySegment
import java.lang.foreign.SegmentAllocator
import java.lang.foreign.ValueLayout
import org.graphiks.kextract.clang.libclang.*

object LibClang {
    private val DEBUG = java.lang.Boolean.getBoolean("libclang.debug")
    private val IS_WINDOWS = System.getProperty("os.name").startsWith("Windows")
    // crash recovery is not an issue on Windows, so enable it there by default to work around a libclang issue
    private val CRASH_RECOVERY = IS_WINDOWS || java.lang.Boolean.getBoolean("libclang.crash_recovery")

    private val IMPLICIT_ALLOCATOR = SegmentAllocator { size, align -> Arena.ofAuto().allocate(size, align) }

    private val disableCrashRecovery =
        IMPLICIT_ALLOCATOR.allocateFrom("LIBCLANG_DISABLE_CRASH_RECOVERY=$CRASH_RECOVERY")

    init {
        if (!CRASH_RECOVERY) {
            try {
                val linker = Linker.nativeLinker()
                val putenvName = if (IS_WINDOWS) "_putenv" else "putenv"
                val PUT_ENV = linker.downcallHandle(
                    linker.defaultLookup().find(putenvName).get(),
                    FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.ADDRESS)
                )
                PUT_ENV.invoke(disableCrashRecovery)
            } catch (ex: Throwable) {
                throw ExceptionInInitializerError(ex)
            }
        }
    }

    @JvmStatic
    fun createIndex(local: Boolean): Index {
        val index = Index(clang_createIndex(if (local) 1 else 0, 0))
        if (DEBUG) System.err.println("LibClang crash recovery ${if (CRASH_RECOVERY) "enabled" else "disabled"}")
        return index
    }

    @JvmStatic
    fun CXStrToString(cxstr: MemorySegment): String {
        val buf = clang_getCString(cxstr)
        // Reinterpret with unbounded size so getString can scan for null terminator
        val str = buf.reinterpret(Long.MAX_VALUE).getString(0)
        clang_disposeString(cxstr)
        return str
    }

    @JvmField
    val STRING_ALLOCATOR: ThreadLocal<SegmentAllocator> = ThreadLocal.withInitial {
        SegmentAllocator.prefixAllocator(Arena.ofAuto().allocate(CXString.byteSize, 8))
    }

    @JvmStatic
    fun version(): String {
        val clangVersion = clang_getClangVersion(STRING_ALLOCATOR.get())
        return CXStrToString(clangVersion)
    }
}
