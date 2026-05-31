/**
 * Minimal Panama FFM helpers for the GL context backends (WGL / GLX / EGL).
 *
 * Keeps the per-platform context files focused on the actual API calls rather than
 * downcall-handle boilerplate.
 */
package org.graphiks.kadre.samples.hellocompose

import java.lang.foreign.Arena
import java.lang.foreign.FunctionDescriptor
import java.lang.foreign.Linker
import java.lang.foreign.MemorySegment
import java.lang.foreign.SymbolLookup
import java.lang.invoke.MethodHandle

internal object NativeFfi {
    val linker: Linker = Linker.nativeLinker()
    private val arena: Arena = Arena.global()

    /** Loads the first of [names] (e.g. "GL", "libGL.so.1") that resolves. */
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

/** Convenience invoke for downcall handles (boxes args; fine outside tight inner loops). */
internal fun MethodHandle.call(vararg args: Any?): Any? = invokeWithArguments(*args)

