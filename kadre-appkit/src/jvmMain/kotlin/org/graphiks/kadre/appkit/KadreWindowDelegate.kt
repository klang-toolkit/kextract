/**
 * Implémentation Kotlin de `NSWindowDelegate` via subclassing ObjC runtime.
 *
 * Utilise les upcall stubs de Panama FFM pour exposer des fonctions statiques
 * Kotlin (`@JvmStatic`) comme implémentations de méthodes Objective-C.
 *
 * Stratégie de dispatch : la classe ObjC `KadreWindowDelegateNative` n'embarque pas
 * de pointeur vers le delegate Kotlin. À la place, on indexe les instances dans
 * une [java.util.concurrent.ConcurrentHashMap] globale dont la clé est l'adresse
 * mémoire (`MemorySegment.address()`) du `self` ObjC. Le premier argument de
 * tout upcall ObjC étant `self`, cela suffit à retrouver l'instance Kotlin
 * cible lors d'un callback natif.
 *
 * GRA-127 : dispatch de WindowEvent.CloseRequested vers ApplicationHandler.
 * GRA-132 : dispatch de WindowEvent.Resized + mise à jour CAMetalLayer.drawableSize.
 * GRA-133 : dispatch de WindowEvent.ScaleFactorChanged + mise à jour CAMetalLayer.contentsScale.
 */
package org.graphiks.kadre.appkit

import org.graphiks.kadre.appkit.bindings.NSWindow
import org.graphiks.kadre.appkit.bindings.ObjCRuntime
import org.graphiks.kadre.core.ActiveEventLoop
import org.graphiks.kadre.core.ApplicationHandler
import org.graphiks.kadre.core.PhysicalSize
import org.graphiks.kadre.core.WindowEvent
import org.graphiks.kadre.core.WindowId
import java.lang.foreign.Arena
import java.lang.foreign.FunctionDescriptor
import java.lang.foreign.Linker
import java.lang.foreign.MemoryLayout
import java.lang.foreign.MemorySegment
import java.lang.foreign.ValueLayout
import java.lang.invoke.MethodHandles
import java.lang.invoke.MethodType
import java.util.concurrent.ConcurrentHashMap

/**
 * Delegate de fenêtre macOS implémentant `NSWindowDelegate` via FFM.
 *
 * Intercepte `windowShouldClose:` pour dispatcher [WindowEvent.CloseRequested],
 * `windowDidResize:` pour dispatcher [WindowEvent.Resized], et
 * `windowDidChangeBackingProperties:` pour dispatcher [WindowEvent.ScaleFactorChanged]
 * vers l'[ApplicationHandler]. Met également à jour les propriétés CAMetalLayer
 * (`drawableSize`, `contentsScale`) lors de chaque changement.
 *
 * @param handler        Gestionnaire d'application recevant les événements.
 * @param eventLoop      Boucle d'événements active au moment de la création du delegate.
 * @param windowId       Identifiant de la fenêtre surveillée.
 * @param nsWindowPtr    Pointeur natif vers la NSWindow associée.
 * @param metalLayerPtr  Pointeur natif vers le CAMetalLayer de la fenêtre.
 */
class KadreWindowDelegate(
    private val handler: ApplicationHandler,
    private val eventLoop: ActiveEventLoop,
    private val windowId: WindowId,
    private val nsWindowPtr: MemorySegment,
    private val metalLayerPtr: MemorySegment,
    private val windows: ConcurrentHashMap<Long, AppKitWindow>,
) {
    /** Pointeur vers l'objet Objective-C wrappé par ce délégué. */
    val ptr: MemorySegment

    init {
        ensureClassRegistered()

        val cls = ObjCRuntime.getClass("KadreWindowDelegateNative")
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

    /**
     * Callback Kotlin pour `windowShouldClose:`.
     *
     * Dispatche [WindowEvent.CloseRequested] vers le gestionnaire. Si [eventLoop.exit]
     * a été appelé durant le callback, déclenche `[NSApp terminate:nil]` pour
     * arrêter la boucle AppKit. Retourne `0` (BOOL NO) — la fermeture reste sous
     * contrôle de l'application.
     */
    fun onWindowShouldClose(): Byte {
        handler.windowEvent(eventLoop, windowId, WindowEvent.CloseRequested)
        if (eventLoop.isExiting) {
            val nsAppClass = ObjCRuntime.getClass("NSApplication")
            val nsApp = ObjCRuntime.msgSend(
                ValueLayout.ADDRESS,
                nsAppClass,
                ObjCRuntime.sel("sharedApplication"),
            ) as MemorySegment
            ObjCRuntime.msgSend(null, nsApp, ObjCRuntime.sel("terminate:"), MemorySegment.NULL)
        }
        return 0 // NO — l'application contrôle la fermeture via exit()
    }

    /**
     * Callback Kotlin pour `windowWillClose:`.
     *
     * Supprime cette fenêtre de la map [windows] lorsque la NSWindow est sur le
     * point d'être fermée, garantissant qu'aucun événement ultérieur (redraw,
     * aboutToWait) ne cible une fenêtre déjà détruite.
     */
    fun onWindowWillClose() {
        windows.remove(windowId.value)
    }

    /**
     * Callback Kotlin pour `windowDidResize:`.
     *
     * Calcule la nouvelle taille physique en pixels :
     *   physW = contentLayoutRect.width × backingScaleFactor
     *   physH = contentLayoutRect.height × backingScaleFactor
     *
     * Dispatche [WindowEvent.Resized] vers le gestionnaire puis met à jour
     * `CAMetalLayer.drawableSize` pour que la surface Metal suive le resize.
     *
     * Note : contentLayoutRect est lu via l'introspection ADDRESS layout (MemorySegment
     * traité comme pointer-or-sret selon la plateforme) puis reinterpret(32) pour
     * accéder aux quatre CGFloat {x, y, width, height}.
     */
    fun onWindowDidResize() {
        val nsWindow = NSWindow(nsWindowPtr)
        val scale = nsWindow.backingScaleFactor()

        // contentLayoutRect → NSRect (MemorySegment) → {x, y, width, height}
        // reinterpret(32) = 4 × 8 bytes pour lire les doubles
        val rect = nsWindow.contentLayoutRect().reinterpret(32)
        val w = rect.getAtIndex(ValueLayout.JAVA_DOUBLE, 2)
        val h = rect.getAtIndex(ValueLayout.JAVA_DOUBLE, 3)

        val physW = (w * scale).toInt()
        val physH = (h * scale).toInt()

        val newSize = PhysicalSize(physW, physH)
        handler.windowEvent(eventLoop, windowId, WindowEvent.Resized(newSize))

        // Mise à jour du drawableSize du CAMetalLayer pour suivre la nouvelle taille
        // CGSize = {width: Double, height: Double} passé par valeur (HFA ARM64)
        setMetalLayerDrawableSize(physW.toDouble(), physH.toDouble())
    }

    /**
     * Met à jour `CAMetalLayer.drawableSize` via un appel ObjC typé struct.
     *
     * CGSize (= {CGFloat, CGFloat}) est un HFA de 2 doubles sur ARM64 — il doit être
     * passé par valeur via un [MemoryLayout.structLayout] pour que Panama utilise les
     * registres SIMD v0/v1 conformément à l'ABI AArch64.
     */
    private fun setMetalLayerDrawableSize(width: Double, height: Double) {
        val cgSizeLayout = MemoryLayout.structLayout(
            ValueLayout.JAVA_DOUBLE.withName("width"),
            ValueLayout.JAVA_DOUBLE.withName("height"),
        )
        val arena = Arena.ofAuto()
        val cgSize = arena.allocate(cgSizeLayout)
        cgSize.setAtIndex(ValueLayout.JAVA_DOUBLE, 0, width)
        cgSize.setAtIndex(ValueLayout.JAVA_DOUBLE, 1, height)

        val linker = Linker.nativeLinker()
        val sel = ObjCRuntime.sel("setDrawableSize:")
        val desc = FunctionDescriptor.ofVoid(
            ValueLayout.ADDRESS,  // self (CAMetalLayer *)
            ValueLayout.ADDRESS,  // SEL
            cgSizeLayout,         // CGSize by value
        )
        val handle = linker.downcallHandle(ObjCRuntime.objcMsgSendAddr, desc)
        handle.invokeWithArguments(metalLayerPtr, sel, cgSize)
    }

    /**
     * Callback Kotlin pour `windowDidChangeBackingProperties:`.
     *
     * Déclenché quand la fenêtre est déplacée entre un écran Retina et un écran standard.
     *
     * 1. Lit le nouveau `backingScaleFactor`
     * 2. Met à jour `CAMetalLayer.contentsScale`
     * 3. Dispatche [WindowEvent.ScaleFactorChanged]
     * 4. Dispatche ensuite [WindowEvent.Resized] car la drawableSize change en pixels
     */
    fun onWindowDidChangeBackingProperties() {
        val nsWindow = NSWindow(nsWindowPtr)
        val newScale = nsWindow.backingScaleFactor()

        // 1. Mettre à jour contentsScale du CAMetalLayer
        ObjCRuntime.msgSend(
            null,
            metalLayerPtr,
            ObjCRuntime.sel("setContentsScale:"),
            newScale,
        )

        // 2. Dispatcher ScaleFactorChanged
        handler.windowEvent(eventLoop, windowId, WindowEvent.ScaleFactorChanged(newScale))

        // 3. Dispatcher Resized consécutif : le drawableSize en pixels change avec le scale
        val rect = nsWindow.contentLayoutRect().reinterpret(32)
        val w = rect.getAtIndex(ValueLayout.JAVA_DOUBLE, 2)
        val h = rect.getAtIndex(ValueLayout.JAVA_DOUBLE, 3)
        val physW = (w * newScale).toInt()
        val physH = (h * newScale).toInt()
        val newSize = PhysicalSize(physW, physH)
        handler.windowEvent(eventLoop, windowId, WindowEvent.Resized(newSize))
        setMetalLayerDrawableSize(physW.toDouble(), physH.toDouble())
    }

    companion object {
        /** Table globale : adresse mémoire ObjC → delegate Kotlin associé. */
        private val delegateTable = ConcurrentHashMap<Long, KadreWindowDelegate>()

        @Volatile
        private var classRegistered: Boolean = false

        @Synchronized
        internal fun ensureClassRegistered() {
            if (classRegistered) return

            val arena = Arena.global()
            val linker: Linker = Linker.nativeLinker()
            val lookup = MethodHandles.lookup()

            val cls = ObjCSubclassing.allocateClass("NSObject", "KadreWindowDelegateNative")
            ObjCSubclassing.addProtocol(cls, "NSWindowDelegate")

            // BOOL windowShouldClose(id self, SEL _cmd, id sender)
            // Encoding : "c@:@" — BOOL est signed char (c) sur macOS 64-bit ARM
            val windowShouldCloseHandle = lookup.findStatic(
                Callbacks::class.java,
                "windowShouldClose",
                MethodType.methodType(
                    java.lang.Byte.TYPE,
                    MemorySegment::class.java,
                    MemorySegment::class.java,
                    MemorySegment::class.java,
                ),
            )
            val windowShouldCloseStub = linker.upcallStub(
                windowShouldCloseHandle,
                FunctionDescriptor.of(
                    ValueLayout.JAVA_BYTE,
                    ValueLayout.ADDRESS,
                    ValueLayout.ADDRESS,
                    ValueLayout.ADDRESS,
                ),
                arena,
            )

            ObjCSubclassing.addMethod(
                cls,
                "windowShouldClose:",
                windowShouldCloseStub,
                "c@:@",
            )

            // void windowDidResize:(NSNotification *) — encoding "v@:@"
            val windowDidResizeHandle = lookup.findStatic(
                Callbacks::class.java,
                "windowDidResize",
                MethodType.methodType(
                    Void.TYPE,
                    MemorySegment::class.java, // self
                    MemorySegment::class.java, // cmd
                    MemorySegment::class.java, // notification
                ),
            )
            val windowDidResizeStub = linker.upcallStub(
                windowDidResizeHandle,
                FunctionDescriptor.ofVoid(
                    ValueLayout.ADDRESS,
                    ValueLayout.ADDRESS,
                    ValueLayout.ADDRESS,
                ),
                arena,
            )
            ObjCSubclassing.addMethod(
                cls,
                "windowDidResize:",
                windowDidResizeStub,
                "v@:@",
            )

            // void windowDidChangeBackingProperties:(NSNotification *) — encoding "v@:@"
            val windowDidChangeBackingPropertiesHandle = lookup.findStatic(
                Callbacks::class.java,
                "windowDidChangeBackingProperties",
                MethodType.methodType(
                    Void.TYPE,
                    MemorySegment::class.java, // self
                    MemorySegment::class.java, // cmd
                    MemorySegment::class.java, // notification
                ),
            )
            val windowDidChangeBackingPropertiesStub = linker.upcallStub(
                windowDidChangeBackingPropertiesHandle,
                FunctionDescriptor.ofVoid(
                    ValueLayout.ADDRESS,
                    ValueLayout.ADDRESS,
                    ValueLayout.ADDRESS,
                ),
                arena,
            )
            ObjCSubclassing.addMethod(
                cls,
                "windowDidChangeBackingProperties:",
                windowDidChangeBackingPropertiesStub,
                "v@:@",
            )

            // void windowWillClose:(NSNotification *) — encoding "v@:@"
            val windowWillCloseHandle = lookup.findStatic(
                Callbacks::class.java,
                "windowWillClose",
                MethodType.methodType(
                    Void.TYPE,
                    MemorySegment::class.java, // self
                    MemorySegment::class.java, // cmd
                    MemorySegment::class.java, // notification
                ),
            )
            val windowWillCloseStub = linker.upcallStub(
                windowWillCloseHandle,
                FunctionDescriptor.ofVoid(
                    ValueLayout.ADDRESS,
                    ValueLayout.ADDRESS,
                    ValueLayout.ADDRESS,
                ),
                arena,
            )
            ObjCSubclassing.addMethod(
                cls,
                "windowWillClose:",
                windowWillCloseStub,
                "v@:@",
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
        fun windowShouldClose(
            self: MemorySegment,
            @Suppress("UNUSED_PARAMETER") cmd: MemorySegment,
            @Suppress("UNUSED_PARAMETER") sender: MemorySegment,
        ): Byte {
            // Si aucun delegate Kotlin n'est enregistré pour ce self, retourner YES (1)
            // pour permettre la fermeture par défaut.
            return delegateTable[self.address()]?.onWindowShouldClose() ?: 1
        }

        @JvmStatic
        fun windowDidResize(
            self: MemorySegment,
            @Suppress("UNUSED_PARAMETER") cmd: MemorySegment,
            @Suppress("UNUSED_PARAMETER") notification: MemorySegment,
        ) {
            delegateTable[self.address()]?.onWindowDidResize()
        }

        @JvmStatic
        fun windowDidChangeBackingProperties(
            self: MemorySegment,
            @Suppress("UNUSED_PARAMETER") cmd: MemorySegment,
            @Suppress("UNUSED_PARAMETER") notification: MemorySegment,
        ) {
            delegateTable[self.address()]?.onWindowDidChangeBackingProperties()
        }

        @JvmStatic
        fun windowWillClose(
            self: MemorySegment,
            @Suppress("UNUSED_PARAMETER") cmd: MemorySegment,
            @Suppress("UNUSED_PARAMETER") notification: MemorySegment,
        ) {
            delegateTable[self.address()]?.onWindowWillClose()
        }
    }
}
