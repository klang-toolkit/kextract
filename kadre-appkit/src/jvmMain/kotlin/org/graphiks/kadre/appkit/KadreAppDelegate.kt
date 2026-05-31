/**
 * Implémentation Kotlin de `NSApplicationDelegate` via subclassing ObjC runtime.
 *
 * Utilise les upcall stubs de Panama FFM pour exposer des fonctions statiques
 * Kotlin (`@JvmStatic`) comme implémentations de méthodes Objective-C.
 *
 * Stratégie de dispatch : la classe ObjC `KadreAppDelegateNative` n'embarque pas
 * de pointeur vers le delegate Kotlin. À la place, on indexe les instances dans
 * une [java.util.concurrent.ConcurrentHashMap] globale dont la clé est l'adresse
 * mémoire (`MemorySegment.address()`) du `self` ObjC. Le premier argument de
 * tout upcall ObjC étant `self`, cela suffit à retrouver l'instance Kotlin
 * cible lors d'un callback natif.
 */
package org.graphiks.kadre.appkit

import org.graphiks.kadre.appkit.bindings.NSApplicationTerminateReply
import org.graphiks.kadre.appkit.bindings.ObjCRuntime
import org.graphiks.kadre.core.ActiveEventLoop
import org.graphiks.kadre.core.ApplicationHandler
import java.lang.foreign.Arena
import java.lang.foreign.FunctionDescriptor
import java.lang.foreign.Linker
import java.lang.foreign.MemorySegment
import java.lang.foreign.ValueLayout
import java.lang.invoke.MethodHandles
import java.lang.invoke.MethodType
import java.util.concurrent.ConcurrentHashMap

class KadreAppDelegate(
    private val handler: ApplicationHandler,
    private val eventLoop: ActiveEventLoop,
) {
    /** Pointeur vers l'objet Objective-C wrappé par ce délégué. */
    val ptr: MemorySegment

    init {
        ensureClassRegistered()

        val cls = ObjCRuntime.getClass("KadreAppDelegateNative")
        val allocated = ObjCRuntime.msgSend(
            ValueLayout.ADDRESS,
            cls,
            ObjCRuntime.sel("alloc"),
        ) as MemorySegment
        ptr = ObjCRuntime.msgSend(
            ValueLayout.ADDRESS,
            allocated,
            ObjCRuntime.sel("init"),
        ) as MemorySegment

        delegateTable[ptr.address()] = this
    }

    /** Callback Kotlin pour `applicationDidFinishLaunching:`. */
    fun onDidFinishLaunching() {
        handler.canCreateSurfaces(eventLoop)
    }

    /**
     * Callback Kotlin pour `applicationShouldTerminate:`.
     *
     * Retourne `NSTerminateNow` lorsque la boucle est déjà en cours d'arrêt,
     * `NSTerminateCancel` sinon — l'arrêt est piloté côté Kadre via
     * [ActiveEventLoop.exit].
     */
    fun onShouldTerminate(): Long {
        return if (eventLoop.isExiting) {
            NSApplicationTerminateReply.NSTerminateNow.value
        } else {
            NSApplicationTerminateReply.NSTerminateCancel.value
        }
    }

    companion object {
        /** Table globale : adresse mémoire ObjC → delegate Kotlin associé. */
        private val delegateTable = ConcurrentHashMap<Long, KadreAppDelegate>()

        @Volatile
        private var classRegistered: Boolean = false

        @Synchronized
        internal fun ensureClassRegistered() {
            if (classRegistered) return

            val arena = Arena.global()
            val linker: Linker = Linker.nativeLinker()
            val lookup = MethodHandles.lookup()

            val cls = ObjCSubclassing.allocateClass("NSObject", "KadreAppDelegateNative")
            ObjCSubclassing.addProtocol(cls, "NSApplicationDelegate")

            // void applicationDidFinishLaunching(id self, SEL _cmd, id notification)
            val didFinishLaunchingHandle = lookup.findStatic(
                Callbacks::class.java,
                "applicationDidFinishLaunching",
                MethodType.methodType(
                    Void.TYPE,
                    MemorySegment::class.java,
                    MemorySegment::class.java,
                    MemorySegment::class.java,
                ),
            )
            val didFinishLaunchingStub = linker.upcallStub(
                didFinishLaunchingHandle,
                FunctionDescriptor.ofVoid(
                    ValueLayout.ADDRESS,
                    ValueLayout.ADDRESS,
                    ValueLayout.ADDRESS,
                ),
                arena,
            )

            // NSUInteger applicationShouldTerminate(id self, SEL _cmd, id sender)
            val shouldTerminateHandle = lookup.findStatic(
                Callbacks::class.java,
                "applicationShouldTerminate",
                MethodType.methodType(
                    java.lang.Long.TYPE,
                    MemorySegment::class.java,
                    MemorySegment::class.java,
                    MemorySegment::class.java,
                ),
            )
            val shouldTerminateStub = linker.upcallStub(
                shouldTerminateHandle,
                FunctionDescriptor.of(
                    ValueLayout.JAVA_LONG,
                    ValueLayout.ADDRESS,
                    ValueLayout.ADDRESS,
                    ValueLayout.ADDRESS,
                ),
                arena,
            )

            ObjCSubclassing.addMethod(
                cls,
                "applicationDidFinishLaunching:",
                didFinishLaunchingStub,
                "v@:@",
            )
            ObjCSubclassing.addMethod(
                cls,
                "applicationShouldTerminate:",
                shouldTerminateStub,
                "Q@:@",
            )

            ObjCSubclassing.registerClass(cls)
            classRegistered = true
        }
    }

    /**
     * Trampolines `@JvmStatic` invoqués par les upcall stubs Panama.
     *
     * Les méthodes statiques sont indispensables : `Linker.upcallStub` ne sait
     * pas lier de méthodes d'instance car le `self` ObjC est passé en premier
     * argument, pas via le receiver Java.
     */
    object Callbacks {
        @JvmStatic
        fun applicationDidFinishLaunching(
            self: MemorySegment,
            @Suppress("UNUSED_PARAMETER") cmd: MemorySegment,
            @Suppress("UNUSED_PARAMETER") notification: MemorySegment,
        ) {
            delegateTable[self.address()]?.onDidFinishLaunching()
        }

        @JvmStatic
        fun applicationShouldTerminate(
            self: MemorySegment,
            @Suppress("UNUSED_PARAMETER") cmd: MemorySegment,
            @Suppress("UNUSED_PARAMETER") sender: MemorySegment,
        ): Long {
            return delegateTable[self.address()]?.onShouldTerminate()
                ?: NSApplicationTerminateReply.NSTerminateNow.value
        }
    }
}
