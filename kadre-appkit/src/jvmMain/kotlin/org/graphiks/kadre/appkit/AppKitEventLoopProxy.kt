/**
 * Implémentation [EventLoopProxy] pour AppKit (GRA-136).
 *
 * [wakeUp] est sûr à appeler depuis n'importe quel thread : il invoque
 * `CFRunLoopWakeUp(CFRunLoopGetMain())` via Panama FFM. `CFRunLoopWakeUp`
 * est documenté thread-safe (manipulation d'un mach port). Plusieurs appels
 * consécutifs avant que la boucle ne se réveille sont coalescés naturellement
 * par le comportement du mach port — pas d'overhead supplémentaire.
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
     * Réveille la boucle d'événements principale.
     *
     * Thread-safe : `CFRunLoopWakeUp` peut être appelé depuis n'importe quel
     * thread. Coalescent : plusieurs appels avant le réveil effectif n'ajoutent
     * aucun overhead (mach port interne).
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
