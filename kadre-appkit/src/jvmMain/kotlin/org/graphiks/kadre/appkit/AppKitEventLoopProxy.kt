/**
 * [EventLoopProxy] implementation for AppKit (GRA-136).
 *
 * [wakeUp] is safe to call from any thread: it invokes
 * `CFRunLoopWakeUp(CFRunLoopGetMain())` via Panama FFM. `CFRunLoopWakeUp`
 * is documented as thread-safe (manipulating a mach port). Several
 * consecutive calls before the loop wakes up are naturally coalesced
 * by the mach port behavior — no additional overhead.
 */
package org.graphiks.kadre.appkit

import org.graphiks.kadre.core.EventLoopProxy
import java.lang.foreign.Arena
import java.lang.foreign.FunctionDescriptor
import java.lang.foreign.Linker
import java.lang.foreign.MemorySegment
import java.lang.foreign.SymbolLookup
import java.lang.foreign.ValueLayout
import java.lang.invoke.MethodHandle

internal class AppKitEventLoopProxy private constructor(
    private val mainRunLoop: MemorySegment,
    private val wakeUpHandle: MethodHandle,
) : EventLoopProxy {

    /**
     * Wakes up the main event loop.
     *
     * Thread-safe: `CFRunLoopWakeUp` can be called from any thread.
     * Coalescing: several calls before the actual wake-up add no
     * overhead (internal mach port).
     */
    override fun wakeUp() {
        wakeUpHandle.invokeExact(mainRunLoop)
    }

    companion object {
        fun create(): AppKitEventLoopProxy {
            val arena = Arena.global()
            val linker = Linker.nativeLinker()

            val cfLib: SymbolLookup = SymbolLookup.loaderLookup().let { loader ->
                if (loader.find("CFRunLoopGetMain").isPresent) loader
                else SymbolLookup.libraryLookup(
                    "/System/Library/Frameworks/CoreFoundation.framework/CoreFoundation",
                    arena,
                )
            }

            val getMainSymbol = cfLib.find("CFRunLoopGetMain").orElseThrow {
                UnsatisfiedLinkError("CFRunLoopGetMain not found")
            }
            val getMainHandle = linker.downcallHandle(
                getMainSymbol,
                FunctionDescriptor.of(ValueLayout.ADDRESS),
            )
            val mainRunLoop = getMainHandle.invokeExact() as MemorySegment

            val wakeUpSymbol = cfLib.find("CFRunLoopWakeUp").orElseThrow {
                UnsatisfiedLinkError("CFRunLoopWakeUp not found")
            }
            val wakeUpHandle = linker.downcallHandle(
                wakeUpSymbol,
                FunctionDescriptor.ofVoid(ValueLayout.ADDRESS),
            )

            return AppKitEventLoopProxy(mainRunLoop, wakeUpHandle)
        }
    }
}
