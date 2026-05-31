/**
 * CFRunLoopObserver pour le dispatch de WindowEvent.RedrawRequested (GRA-134)
 * et l'appel à ApplicationHandler.aboutToWait (GRA-135).
 *
 * Installé sur kCFRunLoopBeforeWaiting : pour chaque AppKitWindow ayant
 * needsRedraw=true, dispatch WindowEvent.RedrawRequested et reset le flag.
 * Puis appelle aboutToWait(eventLoop) — après tous les RedrawRequested.
 * Coalescing natif : plusieurs requestRedraw() → un seul event par itération.
 *
 * GRA-136 : suite à aboutToWait, applique le [ControlFlow] courant :
 *  - [ControlFlow.Poll] : réveille immédiatement le run loop (jamais d'attente).
 *  - [ControlFlow.WaitUntil] : programme un CFRunLoopTimer one-shot.
 *  - [ControlFlow.Wait] : annule tout timer en attente (blocage indéfini).
 */
package org.graphiks.kadre.appkit

import org.graphiks.kadre.core.ActiveEventLoop
import org.graphiks.kadre.core.ApplicationHandler
import org.graphiks.kadre.core.ControlFlow
import org.graphiks.kadre.core.WindowEvent
import java.lang.foreign.Arena
import java.lang.foreign.FunctionDescriptor
import java.lang.foreign.Linker
import java.lang.foreign.MemorySegment
import java.lang.foreign.SymbolLookup
import java.lang.foreign.ValueLayout
import java.lang.invoke.MethodHandle
import java.lang.invoke.MethodHandles
import java.lang.invoke.MethodType
import java.util.concurrent.ConcurrentHashMap

/**
 * Installe un CFRunLoopObserver sur le run loop courant pour :
 * 1. Dispatcher WindowEvent.RedrawRequested (GRA-134) pour les fenêtres en attente
 * 2. Appeler ApplicationHandler.aboutToWait(eventLoop) (GRA-135) — après les redraws
 * 3. Appliquer le [ControlFlow] courant (GRA-136)
 *
 * Doit être créé et installé depuis le thread principal, avant app.run().
 */
internal class CFRunLoopRedrawObserver(
    private val handler: ApplicationHandler,
    private val eventLoop: ActiveEventLoop,
    private val windows: ConcurrentHashMap<Long, AppKitWindow>,
) {
    init {
        // Register this instance globally so the static callback can find it
        instance = this
    }

    // GRA-136 — handles/refs résolus dans install() pour l'application de ControlFlow.
    private var runLoopPtr: MemorySegment = MemorySegment.NULL
    private var cfRunLoopWakeUpHandle: MethodHandle? = null
    private var cfRunLoopTimerCreateHandle: MethodHandle? = null
    private var cfRunLoopAddTimerHandle: MethodHandle? = null
    private var cfRunLoopTimerInvalidateHandle: MethodHandle? = null
    private var kCFRunLoopCommonModes: MemorySegment = MemorySegment.NULL
    private var noopTimerCallout: MemorySegment = MemorySegment.NULL

    @Volatile
    private var currentTimer: MemorySegment? = null

    /**
     * Appelé par le CFRunLoopObserver avant chaque mise en veille du run loop.
     *
     * Ordre garanti par la spec :
     * 1. Dispatch [WindowEvent.RedrawRequested] pour chaque fenêtre avec needsRedraw=true (GRA-134)
     * 2. Appel [ApplicationHandler.aboutToWait] après tous les redraws (GRA-135)
     * 3. Applique le [ControlFlow] courant (GRA-136) — APRÈS aboutToWait
     *    pour que le handler puisse l'avoir mis à jour.
     *
     * Ne dispatch pas si [ActiveEventLoop.isExiting] — évite des callbacks parasites
     * entre [ActiveEventLoop.exit] et la sortie effective du run loop.
     */
    fun onBeforeWaiting() {
        if (eventLoop.isExiting) return

        windows.values.forEach { window ->
            if (window.needsRedraw) {
                window.needsRedraw = false
                handler.windowEvent(eventLoop, window.id, WindowEvent.RedrawRequested)
            }
        }

        // aboutToWait dispatché après tous les RedrawRequested (GRA-135)
        handler.aboutToWait(eventLoop)

        // ControlFlow handling — APRÈS aboutToWait (GRA-136)
        when (val cf = eventLoop.controlFlow) {
            is ControlFlow.Poll -> {
                // Poll : on ne bloque jamais — réveille immédiatement.
                cancelScheduledTimer()
                cfRunLoopWakeUpHandle?.invokeExact(runLoopPtr)
            }
            is ControlFlow.WaitUntil -> {
                scheduleWakeUpAt(cf.instant)
            }
            is ControlFlow.Wait -> {
                // Blocage indéfini : pas de timer.
                cancelScheduledTimer()
            }
        }
    }

    /**
     * Programme un CFRunLoopTimer one-shot pour réveiller le run loop à [instant]
     * (millisecondes depuis l'epoch Unix). Annule tout timer précédent.
     *
     * CFAbsoluteTime est en secondes depuis le 1er janvier 2001 :
     * conversion `instant / 1000.0 - 978307200.0`.
     */
    private fun scheduleWakeUpAt(instant: Long) {
        cancelScheduledTimer()
        val createHandle = cfRunLoopTimerCreateHandle ?: return
        val addHandle = cfRunLoopAddTimerHandle ?: return

        val cfFireTime: Double = (instant / 1000.0) - 978_307_200.0

        val timer = createHandle.invoke(
            MemorySegment.NULL, // allocator
            cfFireTime,          // fireDate
            0.0,                 // interval (0 = one-shot)
            0L,                  // flags
            0L,                  // order
            noopTimerCallout,    // callout
            MemorySegment.NULL,  // context
        ) as MemorySegment

        addHandle.invokeExact(runLoopPtr, timer, kCFRunLoopCommonModes)
        currentTimer = timer
    }

    /**
     * Invalide tout CFRunLoopTimer en attente.
     */
    private fun cancelScheduledTimer() {
        val timer = currentTimer ?: return
        currentTimer = null
        cfRunLoopTimerInvalidateHandle?.invokeExact(timer)
    }

    companion object {
        /** Instance singleton — un seul observer par application. */
        @Volatile
        private var instance: CFRunLoopRedrawObserver? = null

        /** kCFRunLoopBeforeWaiting activity bit */
        private const val kCFRunLoopBeforeWaiting = 0x20L

        /**
         * Crée et installe l'observer sur le run loop courant.
         * Retourne l'observer (retenu dans Arena.global()).
         */
        fun install(
            handler: ApplicationHandler,
            eventLoop: ActiveEventLoop,
            windows: ConcurrentHashMap<Long, AppKitWindow>,
        ): CFRunLoopRedrawObserver {
            val observer = CFRunLoopRedrawObserver(handler, eventLoop, windows)

            val arena = Arena.global()
            val linker = Linker.nativeLinker()
            val lookup = MethodHandles.lookup()

            // Load CoreFoundation
            val cfLib: SymbolLookup = SymbolLookup.loaderLookup().let { loader ->
                if (loader.find("CFRunLoopGetCurrent").isPresent) loader
                else SymbolLookup.libraryLookup(
                    "/System/Library/Frameworks/CoreFoundation.framework/CoreFoundation", arena
                )
            }

            // Resolve CF functions
            val cfRunLoopGetCurrent = cfLib.find("CFRunLoopGetCurrent").orElseThrow {
                UnsatisfiedLinkError("CFRunLoopGetCurrent not found")
            }
            val cfRunLoopObserverCreate = cfLib.find("CFRunLoopObserverCreate").orElseThrow {
                UnsatisfiedLinkError("CFRunLoopObserverCreate not found")
            }
            val cfRunLoopAddObserver = cfLib.find("CFRunLoopAddObserver").orElseThrow {
                UnsatisfiedLinkError("CFRunLoopAddObserver not found")
            }

            // kCFRunLoopCommonModes global constant (CFStringRef)
            val kCFRunLoopCommonModesPtr = cfLib.find("kCFRunLoopCommonModes").orElseThrow {
                UnsatisfiedLinkError("kCFRunLoopCommonModes not found")
            }
            // Dereference: the symbol IS a pointer to the CFStringRef, not the CFStringRef itself
            val kCFRunLoopCommonModes: MemorySegment = kCFRunLoopCommonModesPtr
                .reinterpret(8L)
                .get(ValueLayout.ADDRESS, 0L)

            // Upcall stub: void callback(CFRunLoopObserverRef, CFRunLoopActivity, void*)
            val callbackHandle = lookup.findStatic(
                CFRunLoopRedrawObserver::class.java,
                "redrawCallback",
                MethodType.methodType(
                    Void.TYPE,
                    MemorySegment::class.java, // observer
                    Long::class.javaPrimitiveType, // activity (CFRunLoopActivity = CFOptionFlags = long)
                    MemorySegment::class.java, // info
                ),
            )
            val callbackStub = linker.upcallStub(
                callbackHandle,
                FunctionDescriptor.ofVoid(
                    ValueLayout.ADDRESS,  // observer
                    ValueLayout.JAVA_LONG, // activity
                    ValueLayout.ADDRESS,  // info
                ),
                arena,
            )

            // CFRunLoopGetCurrent()
            val getCurrentHandle = linker.downcallHandle(
                cfRunLoopGetCurrent,
                FunctionDescriptor.of(ValueLayout.ADDRESS),
            )
            val runLoop = getCurrentHandle.invokeExact() as MemorySegment

            // CFRunLoopObserverCreate(allocator, activities, repeats, order, callout, context)
            val createHandle = linker.downcallHandle(
                cfRunLoopObserverCreate,
                FunctionDescriptor.of(
                    ValueLayout.ADDRESS,   // return: CFRunLoopObserverRef
                    ValueLayout.ADDRESS,   // allocator (NULL = kCFAllocatorDefault)
                    ValueLayout.JAVA_LONG, // activities (CFOptionFlags)
                    ValueLayout.JAVA_BYTE, // repeats (Boolean = unsigned char)
                    ValueLayout.JAVA_LONG, // order (CFIndex)
                    ValueLayout.ADDRESS,   // callout
                    ValueLayout.ADDRESS,   // context (NULL)
                ),
            )
            val observerRef = createHandle.invoke(
                MemorySegment.NULL,          // allocator
                kCFRunLoopBeforeWaiting,     // activities
                1.toByte(),                  // repeats = true
                0L,                          // order
                callbackStub,                // callout
                MemorySegment.NULL,          // context
            ) as MemorySegment

            // CFRunLoopAddObserver(runLoop, observer, kCFRunLoopCommonModes)
            val addHandle = linker.downcallHandle(
                cfRunLoopAddObserver,
                FunctionDescriptor.ofVoid(
                    ValueLayout.ADDRESS, // rl
                    ValueLayout.ADDRESS, // rlo
                    ValueLayout.ADDRESS, // mode
                ),
            )
            addHandle.invokeExact(runLoop, observerRef, kCFRunLoopCommonModes)

            // -------- GRA-136 — CFRunLoopWakeUp / CFRunLoopTimer setup --------

            val wakeUpSym = cfLib.find("CFRunLoopWakeUp").orElseThrow {
                UnsatisfiedLinkError("CFRunLoopWakeUp not found")
            }
            val wakeUpHandle = linker.downcallHandle(
                wakeUpSym,
                FunctionDescriptor.ofVoid(ValueLayout.ADDRESS),
            )

            val timerCreateSym = cfLib.find("CFRunLoopTimerCreate").orElseThrow {
                UnsatisfiedLinkError("CFRunLoopTimerCreate not found")
            }
            val timerCreateHandle = linker.downcallHandle(
                timerCreateSym,
                FunctionDescriptor.of(
                    ValueLayout.ADDRESS,      // return: CFRunLoopTimerRef
                    ValueLayout.ADDRESS,      // allocator
                    ValueLayout.JAVA_DOUBLE,  // fireDate (CFAbsoluteTime)
                    ValueLayout.JAVA_DOUBLE,  // interval (CFTimeInterval)
                    ValueLayout.JAVA_LONG,    // flags (CFOptionFlags)
                    ValueLayout.JAVA_LONG,    // order (CFIndex)
                    ValueLayout.ADDRESS,      // callout
                    ValueLayout.ADDRESS,      // context
                ),
            )

            val addTimerSym = cfLib.find("CFRunLoopAddTimer").orElseThrow {
                UnsatisfiedLinkError("CFRunLoopAddTimer not found")
            }
            val addTimerHandle = linker.downcallHandle(
                addTimerSym,
                FunctionDescriptor.ofVoid(
                    ValueLayout.ADDRESS, // rl
                    ValueLayout.ADDRESS, // timer
                    ValueLayout.ADDRESS, // mode
                ),
            )

            val invalidateSym = cfLib.find("CFRunLoopTimerInvalidate").orElseThrow {
                UnsatisfiedLinkError("CFRunLoopTimerInvalidate not found")
            }
            val invalidateHandle = linker.downcallHandle(
                invalidateSym,
                FunctionDescriptor.ofVoid(ValueLayout.ADDRESS),
            )

            // No-op timer callback — la présence du timer suffit à réveiller le run loop ;
            // l'observer kCFRunLoopBeforeWaiting fera tout le travail.
            val noopHandle = lookup.findStatic(
                CFRunLoopRedrawObserver::class.java,
                "noopTimerCallback",
                MethodType.methodType(
                    Void.TYPE,
                    MemorySegment::class.java, // timer
                    MemorySegment::class.java, // info
                ),
            )
            val noopStub = linker.upcallStub(
                noopHandle,
                FunctionDescriptor.ofVoid(
                    ValueLayout.ADDRESS, // timer
                    ValueLayout.ADDRESS, // info
                ),
                arena,
            )

            observer.runLoopPtr = runLoop
            observer.cfRunLoopWakeUpHandle = wakeUpHandle
            observer.cfRunLoopTimerCreateHandle = timerCreateHandle
            observer.cfRunLoopAddTimerHandle = addTimerHandle
            observer.cfRunLoopTimerInvalidateHandle = invalidateHandle
            observer.kCFRunLoopCommonModes = kCFRunLoopCommonModes
            observer.noopTimerCallout = noopStub

            return observer
        }

        /** Static trampoline invoked by the upcall stub from CoreFoundation. */
        @JvmStatic
        fun redrawCallback(
            @Suppress("UNUSED_PARAMETER") observerRef: MemorySegment,
            @Suppress("UNUSED_PARAMETER") activity: Long,
            @Suppress("UNUSED_PARAMETER") info: MemorySegment,
        ) {
            instance?.onBeforeWaiting()
        }

        /**
         * Callback no-op pour CFRunLoopTimer (GRA-136). La seule fonction du timer
         * est de provoquer un réveil du run loop ; l'observer
         * `kCFRunLoopBeforeWaiting` fait ensuite tout le travail.
         */
        @JvmStatic
        fun noopTimerCallback(
            @Suppress("UNUSED_PARAMETER") timer: MemorySegment,
            @Suppress("UNUSED_PARAMETER") info: MemorySegment,
        ) {
            // intentionally empty
        }
    }
}
