package org.graphiks.kadre.appkit.capture

import java.lang.foreign.Arena
import java.lang.foreign.FunctionDescriptor
import java.lang.foreign.Linker
import java.lang.foreign.MemoryLayout
import java.lang.foreign.MemorySegment
import java.lang.foreign.SymbolLookup
import java.lang.foreign.ValueLayout
import java.lang.foreign.ValueLayout.ADDRESS
import java.lang.foreign.ValueLayout.JAVA_INT
import java.lang.foreign.ValueLayout.JAVA_LONG
import java.lang.invoke.MethodHandle
import java.lang.invoke.MethodHandles
import java.lang.invoke.MethodType
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

internal object ObjCBlocks {

    private val NSConcreteGlobalBlock: MemorySegment
    private val blockCopyFn: MethodHandle?

    init {
        val libobjc = SymbolLookup.libraryLookup("/usr/lib/libobjc.A.dylib", Arena.global())
        NSConcreteGlobalBlock = libobjc.find("_NSConcreteGlobalBlock")
            .orElseThrow { UnsatisfiedLinkError("_NSConcreteGlobalBlock not found") }
        val linker = Linker.nativeLinker()
        blockCopyFn = try {
            val libSystem = SymbolLookup.libraryLookup("/usr/lib/libSystem.B.dylib", Arena.global())
            libSystem.find("_Block_copy").map { addr ->
                linker.downcallHandle(addr, FunctionDescriptor.of(ADDRESS, ADDRESS))
            }.orElse(null)
        } catch (_: Throwable) { null }
    }

    /** Copies a block to the heap for async use. Returns same block if copy unavailable. */
    fun copy(block: MemorySegment): MemorySegment {
        val fn = blockCopyFn ?: return block
        return try { fn.invokeExact(block) as MemorySegment } catch (_: Throwable) { block }
    }

    private val blockStruct = MemoryLayout.structLayout(
        ADDRESS.withName("isa"),
        JAVA_INT.withName("flags"),
        JAVA_INT.withName("reserved"),
        ADDRESS.withName("invoke"),
        ADDRESS.withName("descriptor"),
    )

    fun create(invokeFn: MemorySegment, arena: Arena): MemorySegment {
        val descriptor = arena.allocate(16L)
        descriptor.set(JAVA_LONG, 0L, 0L)
        descriptor.set(JAVA_LONG, 8L, 32L)

        val block = arena.allocate(32L)
        block.set(ADDRESS, 0L, NSConcreteGlobalBlock)
        block.set(JAVA_INT, 8L, 0x10000000) // BLOCK_IS_GLOBAL
        block.set(JAVA_INT, 12L, 0)
        block.set(ADDRESS, 16L, invokeFn)
        block.set(ADDRESS, 24L, descriptor)
        return block
    }
}

internal class ObjCCallback2(
    private val onResult: (MemorySegment?, MemorySegment?) -> Unit = { _, _ -> },
) {
    private val latch = CountDownLatch(1)
    var result: MemorySegment? = null
    var error: MemorySegment? = null

    @Suppress("unused")
    fun onCallback(block: MemorySegment, arg1: MemorySegment, arg2: MemorySegment) {
        result = if (arg1 == MemorySegment.NULL) null else arg1
        error = if (arg2 == MemorySegment.NULL) null else arg2
        onResult(result, error)
        latch.countDown()
    }

    val methodHandle: MethodHandle by lazy {
        MethodHandles.lookup().findVirtual(
            ObjCCallback2::class.java,
            "onCallback",
            MethodType.methodType(Void.TYPE, MemorySegment::class.java, MemorySegment::class.java, MemorySegment::class.java),
        ).bindTo(this)
    }

    val fnDescriptor: FunctionDescriptor = FunctionDescriptor.ofVoid(ADDRESS, ADDRESS, ADDRESS)

    fun await(timeoutMs: Long = 5000): Boolean = latch.await(timeoutMs, TimeUnit.MILLISECONDS)
}

internal class ObjCCallback1(
    private val onResult: (MemorySegment?) -> Unit = {},
) {
    private val latch = CountDownLatch(1)
    var result: MemorySegment? = null

    @Suppress("unused")
    fun onCallback(block: MemorySegment, arg1: MemorySegment) {
        result = if (arg1 == MemorySegment.NULL) null else arg1
        onResult(result)
        latch.countDown()
    }

    val methodHandle: MethodHandle by lazy {
        MethodHandles.lookup().findVirtual(
            ObjCCallback1::class.java,
            "onCallback",
            MethodType.methodType(Void.TYPE, MemorySegment::class.java, MemorySegment::class.java),
        ).bindTo(this)
    }

    val fnDescriptor: FunctionDescriptor = FunctionDescriptor.ofVoid(ADDRESS, ADDRESS)

    fun await(timeoutMs: Long = 5000): Boolean = latch.await(timeoutMs, TimeUnit.MILLISECONDS)
}
