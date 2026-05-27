package org.graphiks.kextract.clang

import java.lang.foreign.MemorySegment
import org.graphiks.kextract.clang.libclang.*

class PrintingPolicy internal constructor(private var policy: MemorySegment) : AutoCloseable {

    internal fun ptr(): MemorySegment = policy

    override fun close() = dispose()

    fun dispose() {
        if (policy != MemorySegment.NULL) {
            clang_PrintingPolicy_dispose(policy)
            policy = MemorySegment.NULL
        }
    }

    fun getProperty(prop: PrintingPolicyProperty): Boolean =
        clang_PrintingPolicy_getProperty(policy, prop.value) != 0

    fun setProperty(prop: PrintingPolicyProperty, value: Boolean) {
        clang_PrintingPolicy_setProperty(policy, prop.value, if (value) 1 else 0)
    }
}
