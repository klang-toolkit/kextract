package org.graphiks.kadre.samples.simulation.compose

import java.lang.foreign.Arena
import java.lang.foreign.FunctionDescriptor
import java.lang.foreign.Linker
import java.lang.foreign.MemorySegment
import java.lang.foreign.SymbolLookup
import java.lang.invoke.MethodHandle

internal object NativeFfi {
    val linker: Linker = Linker.nativeLinker()
    private val arena: Arena = Arena.global()

    fun lookup(vararg names: String): SymbolLookup {
        for (name in names) {
            val r = runCatching { SymbolLookup.libraryLookup(name, arena) }
            if (r.isSuccess) return r.getOrThrow()
        }
        throw UnsatisfiedLinkError("Could not load any of: ${names.joinToString()}")
    }

    fun handle(lookup: SymbolLookup, name: String, desc: FunctionDescriptor): MethodHandle {
        val addr = lookup.find(name).orElseThrow { UnsatisfiedLinkError("Symbol not found: $name") }
        return linker.downcallHandle(addr, desc)
    }

    fun ptr(addr: Long): MemorySegment =
        if (addr == 0L) MemorySegment.NULL else MemorySegment.ofAddress(addr)
}

internal fun MethodHandle.call(vararg args: Any?): Any? = invokeWithArguments(*args)
