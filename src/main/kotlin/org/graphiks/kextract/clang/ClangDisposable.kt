package org.graphiks.kextract.clang

import java.lang.foreign.Arena
import java.lang.foreign.MemorySegment
import java.lang.foreign.SegmentAllocator
import java.util.function.Consumer

abstract class ClangDisposable(ptr: MemorySegment, size: Long, cleanup: Consumer<MemorySegment>) :
    SegmentAllocator, AutoCloseable {

    constructor(ptr: MemorySegment, cleanup: Consumer<MemorySegment>) : this(ptr, 0L, cleanup)

    protected val arena: Arena = Arena.ofConfined()
    protected val ptr: MemorySegment = ptr.reinterpret(size, arena, cleanup).asReadOnly()

    override fun close() { arena.close() }

    override fun allocate(bytesSize: Long, bytesAlignment: Long): MemorySegment =
        arena.allocate(bytesSize, bytesAlignment)

    open class Owned(val segment: MemorySegment, val owner: ClangDisposable)
}
