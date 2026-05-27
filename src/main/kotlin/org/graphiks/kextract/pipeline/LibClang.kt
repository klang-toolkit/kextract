package org.graphiks.kextract.pipeline

import java.lang.foreign.Arena
import java.lang.foreign.Linker
import java.lang.foreign.MemorySegment
import java.lang.foreign.SegmentAllocator
import java.lang.foreign.FunctionDescriptor
import java.lang.foreign.ValueLayout
import java.lang.invoke.MethodHandle

/**
 * FFI wrapper for libclang functionality.
 * Thread-safe implementation using ThreadLocal allocators.
 */
object LibClang {
    // Constants - using lazy initialization for properties that depend on System.getProperty
    private val DEBUG: Boolean by lazy { System.getProperty("libclang.debug") == "true" }
    private val IS_WINDOWS: Boolean by lazy { System.getProperty("os.name").orEmpty().startsWith("Windows") }
    // crash recovery is not an issue on Windows, so enable it there by default
    private val CRASH_RECOVERY: Boolean by lazy { IS_WINDOWS || System.getProperty("libclang.crash_recovery") == "true" }

    // Thread-safe string allocator
    // Using ThreadLocal to ensure thread-safety, as kextract may be used in multi-threaded contexts
    @JvmStatic
    val STRING_ALLOCATOR: ThreadLocal<SegmentAllocator> = ThreadLocal.withInitial {
        SegmentAllocator.prefixAllocator(
            Arena.ofAuto().allocate(CXString.sizeof(), 8L)
        )
    }

    // Layout constants matching Index_h
    private object Layout {
        val C_POINTER = ValueLayout.ADDRESS
        val C_INT = ValueLayout.JAVA_INT
    }

    // Crash recovery configuration
    private val disableCrashRecovery: MemorySegment

    init {
        // Allocate crash recovery environment variable string
        val implicitAllocator = SegmentAllocator { size, align ->
            Arena.ofAuto().allocate(size, align)
        }
        disableCrashRecovery = implicitAllocator.allocateFrom("LIBCLANG_DISABLE_CRASH_RECOVERY=$CRASH_RECOVERY")

        if (!CRASH_RECOVERY) {
            // This is a hack - needed because clang_toggleCrashRecovery only takes effect _after_
            // the first call to createIndex.
            try {
                val linker = Linker.nativeLinker()
                val putenv = if (IS_WINDOWS) "_putenv" else "putenv"
                val putenvHandle: MethodHandle = linker.downcallHandle(
                    linker.defaultLookup().find(putenv).orElseThrow {
                        UnsatisfiedLinkError("Symbol not found: $putenv")
                    },
                    FunctionDescriptor.of(Layout.C_INT, Layout.C_POINTER)
                )
                val res = putenvHandle.invokeExact(disableCrashRecovery) as Int
                if (DEBUG) {
                    System.err.println("putenv result: $res")
                }
            } catch (ex: Throwable) {
                throw ExceptionInInitializerError(ex)
            }
        }
    }

    /**
     * Creates a new clang Index.
     * Note: This returns an IndexWrapper to avoid dependency on Java Index class
     *
     * @param local whether to create a local index (excludes declarations from PCH)
     * @return an IndexWrapper containing the index pointer
     */
    @JvmStatic
    fun createIndex(local: Boolean): IndexWrapper {
        val indexPtr = callClangCreateIndex(if (local) 1 else 0, 0)
        if (DEBUG) {
            System.err.println("LibClang crash recovery ${if (CRASH_RECOVERY) "enabled" else "disabled"}")
        }
        return IndexWrapper(indexPtr)
    }

    /**
     * Converts a CXString to a Kotlin String.
     *
     * @param cxstr the CXString memory segment
     * @return the converted Kotlin string
     */
    @JvmStatic
    fun cxStrToString(cxstr: MemorySegment): String {
        return try {
            val buf = callClangGetCString(cxstr)
            // Reinterpret with unbounded size so getString can scan for null terminator
            val str = buf.reinterpret(Long.MAX_VALUE).getString(0)
            callClangDisposeString(cxstr)
            str
        } catch (e: Exception) {
            throw RuntimeException("Failed to convert CXString", e)
        }
    }

    /**
     * Returns the clang version string.
     *
     * @return the clang version string
     */
    @JvmStatic
    fun version(): String {
        val clangVersion = callClangGetClangVersion(STRING_ALLOCATOR.get())
        return cxStrToString(clangVersion)
    }

    /**
     * Wrapper for Index pointer to avoid direct dependency on Java Index class.
     *
     * @property ptr the MemorySegment containing the index pointer
     */
    data class IndexWrapper(val ptr: MemorySegment)

    // CXString layout helper - matches the layout from Index_h.CXString
    private object CXString {
        // CXString struct: { const void *data; unsigned int private_flags; } + 4 bytes padding = 16 bytes
        fun sizeof(): Long = 16
    }

    // Private helper methods to call Index_h methods via reflection
    // This avoids direct compilation dependency on the generated Index_h class

    private fun callClangCreateIndex(excludeDeclsFromPCH: Int, displayDiagnostics: Int): MemorySegment {
        return try {
            val clazz = Class.forName("org.graphiks.kextract.clang.libclang.Index_h")
            val method = clazz.getMethod("clang_createIndex", Int::class.javaPrimitiveType, Int::class.javaPrimitiveType)
            method.invoke(null, excludeDeclsFromPCH, displayDiagnostics) as MemorySegment
        } catch (e: Exception) {
            throw RuntimeException("Failed to call clang_createIndex", e)
        }
    }

    private fun callClangGetCString(cxstr: MemorySegment): MemorySegment {
        return try {
            val clazz = Class.forName("org.graphiks.kextract.clang.libclang.Index_h")
            val method = clazz.getMethod("clang_getCString", MemorySegment::class.java)
            method.invoke(null, cxstr) as MemorySegment
        } catch (e: Exception) {
            throw RuntimeException("Failed to call clang_getCString", e)
        }
    }

    private fun callClangDisposeString(cxstr: MemorySegment) {
        try {
            val clazz = Class.forName("org.graphiks.kextract.clang.libclang.Index_h")
            val method = clazz.getMethod("clang_disposeString", MemorySegment::class.java)
            method.invoke(null, cxstr)
        } catch (e: Exception) {
            throw RuntimeException("Failed to call clang_disposeString", e)
        }
    }

    private fun callClangGetClangVersion(allocator: SegmentAllocator): MemorySegment {
        return try {
            val clazz = Class.forName("org.graphiks.kextract.clang.libclang.Index_h")
            val method = clazz.getMethod("clang_getClangVersion", SegmentAllocator::class.java)
            method.invoke(null, allocator) as MemorySegment
        } catch (e: Exception) {
            throw RuntimeException("Failed to call clang_getClangVersion", e)
        }
    }
}
