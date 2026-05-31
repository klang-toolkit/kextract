/**
 * Objective-C subclass of NSApplication for kadre.
 *
 * Overrides `sendEvent:` to intercept keyboard events (NSEventTypeKeyDown /
 * NSEventTypeKeyUp) and dispatch them as [WindowEvent.KeyboardInput] to the
 * active [AppKitEventLoop], and mouse events as the corresponding [WindowEvent].
 *
 * The reference to [AppKitEventLoop] is stored in the [KadreApplication] instance
 * (the [eventLoop] property) and retrieved in the `sendEvent:` bridge via
 * `NSApp as? KadreApplication` — concretely: [Companion.sharedApp] which stores
 * the unique Kotlin instance returned by [initialize].
 *
 * This design avoids the global mutable static variable that made the code
 * non-reentrant and corruptible by parallel tests.
 *
 * **Non-reentrant constraint**: a single [AppKitEventLoop] instance must be
 * attached at a time. Creating two loops in the same process or calling [runApp]
 * from several threads simultaneously is not supported — AppKit requires that
 * `NSApp.run()` runs on the main thread and only returns on close.
 *
 * GRA-154: added keyboard support via sendEvent: NSEvent interception.
 * Refactored eventLoop from static → scoped instance.
 *
 * ## Touch events — intentionally not mapped
 * [WindowEvent.Touch] is **not** emitted on AppKit. macOS exposes no
 * touchscreen API; the only touch source is the trackpad (`NSTouch` via
 * `touchesBeganWithEvent:`), which reports *indirect* contacts in a normalized
 * 0..1 space with no relation to window/client pixels. Mapping those to
 * [WindowEvent.Touch] (which carries physical-pixel locations from a direct
 * touchscreen) would be misleading, so it is left out. Touch is supported on
 * the touchscreen-capable backends (Web and Win32 via WM_TOUCH).
 */
package org.graphiks.kadre.appkit

import org.graphiks.kadre.appkit.bindings.NSApplication
import org.graphiks.kadre.appkit.bindings.NSApplicationActivationPolicy
import org.graphiks.kadre.appkit.bindings.NSEvent
import org.graphiks.kadre.appkit.bindings.ObjCRuntime
import org.graphiks.kadre.core.DeviceEvent
import org.graphiks.kadre.core.DeviceId
import org.graphiks.kadre.core.KeyState
import org.graphiks.kadre.core.MouseButton
import org.graphiks.kadre.core.PhysicalPosition
import org.graphiks.kadre.core.WindowEvent
import java.lang.foreign.Arena
import java.lang.foreign.FunctionDescriptor
import java.lang.foreign.Linker
import java.lang.foreign.MemorySegment
import java.lang.foreign.SymbolLookup
import java.lang.foreign.ValueLayout
import java.lang.invoke.MethodHandles
import java.lang.invoke.MethodType

class KadreApplication private constructor(ptr: MemorySegment) : NSApplication(ptr) {

    /**
     * Reference to the active event loop, scoped to this instance.
     *
     * Assigned by [runApp] immediately after [initialize] and before launching
     * `NSApp.run()`. Retrieved in [Callbacks.sendEvent] via [Companion.sharedApp]
     * (equivalent to `NSApp as? KadreApplication`).
     *
     * **Non-reentrant constraint**: must only be assigned once per
     * application lifecycle. Two simultaneous calls to [runApp] in the same
     * process are not supported.
     */
    @Volatile
    internal var eventLoop: AppKitEventLoop? = null

    /**
     * Sets the application's activation policy
     * (default: `NSApplicationActivationPolicyRegular`).
     */
    fun setActivationPolicyRegular() {
        ObjCRuntime.msgSend(
            ValueLayout.JAVA_BOOLEAN,
            ptr,
            ObjCRuntime.sel("setActivationPolicy:"),
            NSApplicationActivationPolicy.NSApplicationActivationPolicyRegular.value,
        )
    }

    companion object {
        /**
         * Unique [KadreApplication] instance created by [initialize].
         *
         * Plays the role of `NSApp as? KadreApplication`: access point to the Kotlin
         * instance that carries the [eventLoop] property. Initialized by [initialize] and
         * used by [Callbacks.sendEvent] to retrieve the active loop.
         */
        @Volatile
        internal var sharedApp: KadreApplication? = null

        /** Initializes the ObjC subclass only once. */
        private val klass: MemorySegment by lazy {
            val cls = ObjCSubclassing.allocateClass("NSApplication", "KadreApplication")

            // Register sendEvent: upcall
            val linker = Linker.nativeLinker()
            val sendEventMethod = MethodHandles.lookup().findStatic(
                Callbacks::class.java,
                "sendEvent",
                MethodType.methodType(
                    Void.TYPE,
                    MemorySegment::class.java,
                    MemorySegment::class.java,
                    MemorySegment::class.java,
                ),
            )
            val sendEventStub = linker.upcallStub(
                sendEventMethod,
                FunctionDescriptor.ofVoid(
                    ValueLayout.ADDRESS,
                    ValueLayout.ADDRESS,
                    ValueLayout.ADDRESS,
                ),
                Arena.global(),
            )
            ObjCSubclassing.addMethod(cls, "sendEvent:", sendEventStub, "v@:@")

            ObjCSubclassing.registerClass(cls)
            cls
        }

        /**
         * Creates (or retrieves) the unique shared `KadreApplication` instance.
         *
         * Stores the instance in [sharedApp] — equivalent to the
         * `NSApp as? KadreApplication` pattern: any other part of the code can
         * retrieve the instance and its [eventLoop] property without a dedicated
         * mutable static variable.
         *
         * Must be called from the main thread — the invariant is validated
         * via [MainThreadCheck].
         */
        fun initialize(): KadreApplication {
            MainThreadCheck.require()
            // Force registration of the subclass before sharedApplication.
            klass
            val appClass = ObjCRuntime.getClass("KadreApplication")
            val sharedAppPtr = ObjCRuntime.msgSend(
                ValueLayout.ADDRESS,
                appClass,
                ObjCRuntime.sel("sharedApplication"),
            ) as MemorySegment
            return KadreApplication(sharedAppPtr).also { sharedApp = it }
        }
    }

    /**
     * `@JvmStatic` trampolines invoked by the Panama upcall stubs.
     *
     * `sendEvent:` is overridden to intercept keyDown/keyUp and dispatch them
     * to [AppKitEventLoop] as [WindowEvent.KeyboardInput].
     *
     * The event loop is retrieved via [Companion.sharedApp] (equivalent
     * to `NSApp as? KadreApplication`) — no mutable static variable dedicated
     * to the loop.
     *
     * @throws IllegalStateException if [Companion.sharedApp] is null (initialize()
     * not called) or if [eventLoop] is null (runApp() did not wire the loop).
     */
    private object Callbacks {
        @JvmStatic
        fun sendEvent(self: MemorySegment, sel: MemorySegment, event: MemorySegment) {
            // 1. FIRST call super (objc_msgSendSuper) so AppKit processes normally
            callSuperSendEvent(self, sel, event)

            // 2. Retrieve the event loop via sharedApp (NSApp as? KadreApplication).
            val kadreApp = sharedApp
                ?: throw IllegalStateException(
                    "KadreApplication.sharedApp est null dans sendEvent: — " +
                        "initialize() doit être appelé avant NSApp.run()"
                )
            val loop = kadreApp.eventLoop
                ?: throw IllegalStateException(
                    "KadreApplication.eventLoop est null dans sendEvent: — " +
                        "runApp() doit assigner eventLoop avant NSApp.run()"
                )

            // Get event type: [event type] → Long
            val eventType = ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, event, ObjCRuntime.sel("type")) as Long

            val isKeyDown = eventType == 10L   // NSEventTypeKeyDown
            val isKeyUp   = eventType == 11L   // NSEventTypeKeyUp

            // ── Keyboard ──────────────────────────────────────────────────────────────
            if (isKeyDown || isKeyUp) {
                // Get the NSWindow for this event: [event window]
                val eventWindow = ObjCRuntime.msgSend(ValueLayout.ADDRESS, event, ObjCRuntime.sel("window")) as MemorySegment
                if (eventWindow == MemorySegment.NULL) return

                // Find the AppKitWindow by NSWindow address (windowId.value == nsWindowPtr.address())
                val appKitWindow = loop.windows[eventWindow.address()] ?: return

                // Get keyCode: [event keyCode] → Short
                val keyCode = ObjCRuntime.msgSend(ValueLayout.JAVA_SHORT, event, ObjCRuntime.sel("keyCode")) as Short

                // Get modifierFlags: [event modifierFlags] → Long
                val modFlags = ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, event, ObjCRuntime.sel("modifierFlags")) as Long

                // Get isARepeat: [event isARepeat] → Boolean
                val isRepeat = ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, event, ObjCRuntime.sel("isARepeat")) as Boolean

                val key = AppKitKeyMapper.keyCode(keyCode)
                val modifiers = AppKitKeyMapper.modifierFlags(modFlags)
                val state = if (isKeyDown) KeyState.Pressed else KeyState.Released

                // GRA-156: dispatch raw DeviceEvent.Key BEFORE window-scoped WindowEvent
                loop.handler.deviceEvent(
                    loop,
                    DeviceId(0L),
                    DeviceEvent.Key(keyCode.toInt(), state),
                )

                loop.handler.windowEvent(
                    loop,
                    appKitWindow.id,
                    WindowEvent.KeyboardInput(key, state, modifiers, isRepeat),
                )
                return
            }

            // ── Mouse ─────────────────────────────────────────────────────────────────
            val isLeftDown     = eventType == 1L
            val isLeftUp       = eventType == 2L
            val isRightDown    = eventType == 3L
            val isRightUp      = eventType == 4L
            val isMouseMoved   = eventType == 5L
            val isLeftDragged  = eventType == 6L
            val isRightDragged = eventType == 7L
            val isMouseEntered = eventType == 8L
            val isMouseExited  = eventType == 9L
            val isScrollWheel  = eventType == 22L
            val isOtherDown    = eventType == 25L
            val isOtherUp      = eventType == 26L
            val isOtherDragged = eventType == 27L

            val isAnyMouse = isLeftDown || isLeftUp || isRightDown || isRightUp ||
                isMouseMoved || isLeftDragged || isRightDragged ||
                isMouseEntered || isMouseExited || isScrollWheel ||
                isOtherDown || isOtherUp || isOtherDragged

            if (!isAnyMouse) return

            // GRA-156: dispatch raw DeviceEvent.PointerMotion BEFORE any window-scoped dispatch.
            // Raw device events don't require a focused window — only `loop` is needed.
            if (isMouseMoved || isLeftDragged || isRightDragged || isOtherDragged) {
                val nsEvent = NSEvent(event)
                val rawDx = nsEvent.deltaX()
                val rawDy = nsEvent.deltaY()
                loop.handler.deviceEvent(
                    loop,
                    DeviceId(0L),
                    DeviceEvent.PointerMotion(rawDx, rawDy),
                )
            }

            // Get the window for this event
            val eventWindow = ObjCRuntime.msgSend(ValueLayout.ADDRESS, event, ObjCRuntime.sel("window")) as MemorySegment
            if (eventWindow == MemorySegment.NULL) return

            val appKitWindow = loop.windows[eventWindow.address()] ?: return

            // ── Pointer enter/exit ────────────────────────────────────────────────────
            if (isMouseEntered) {
                loop.handler.windowEvent(loop, appKitWindow.id, WindowEvent.PointerEntered)
                return
            }
            if (isMouseExited) {
                loop.handler.windowEvent(loop, appKitWindow.id, WindowEvent.PointerLeft)
                return
            }

            // ── Scroll wheel ──────────────────────────────────────────────────────────
            if (isScrollWheel) {
                val nsEvent = NSEvent(event)
                val deltaX = nsEvent.scrollingDeltaX()
                val deltaY = nsEvent.scrollingDeltaY()
                loop.handler.windowEvent(loop, appKitWindow.id, WindowEvent.MouseWheel(deltaX, deltaY))
                return
            }

            // ── Pointer position (shared for move and click) ───────────────────────────
            // locationInWindow returns NSPoint (struct { CGFloat x, y })
            val locPt = NSEvent(event).locationInWindow()
            val locX = locPt.getAtIndex(ValueLayout.JAVA_DOUBLE, 0)
            val locY = locPt.getAtIndex(ValueLayout.JAVA_DOUBLE, 1)

            // Flip Y: NSView origin is bottom-left, Kadre origin is top-left
            val scale = appKitWindow.scaleFactor
            // Get content view height in points from innerSize (already in physical pixels / scaleFactor)
            val contentHeightPoints = appKitWindow.innerSize.height / scale
            val physX = locX * scale
            val physY = (contentHeightPoints - locY) * scale
            val position = PhysicalPosition(physX, physY)

            // ── Mouse move / drag ─────────────────────────────────────────────────────
            if (isMouseMoved || isLeftDragged || isRightDragged || isOtherDragged) {
                loop.handler.windowEvent(loop, appKitWindow.id, WindowEvent.PointerMoved(position))
                return
            }

            // ── Mouse buttons ─────────────────────────────────────────────────────────
            val button: MouseButton = when {
                isLeftDown || isLeftUp   -> MouseButton.Left
                isRightDown || isRightUp -> MouseButton.Right
                isOtherDown || isOtherUp -> {
                    val btnNum = ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, event, ObjCRuntime.sel("buttonNumber")) as Long
                    if (btnNum == 2L) MouseButton.Middle else MouseButton.Other(btnNum.toInt())
                }
                else -> return
            }
            val state = if (isLeftDown || isRightDown || isOtherDown) KeyState.Pressed else KeyState.Released

            // GRA-156: dispatch raw DeviceEvent.Button BEFORE window-scoped WindowEvent.MouseInput
            val rawButton = when {
                isLeftDown || isLeftUp   -> 0
                isRightDown || isRightUp -> 1
                else -> (ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, event, ObjCRuntime.sel("buttonNumber")) as Long).toInt()
            }
            loop.handler.deviceEvent(loop, DeviceId(0L), DeviceEvent.Button(rawButton, state))

            loop.handler.windowEvent(loop, appKitWindow.id, WindowEvent.MouseInput(button, state))
        }

        private fun callSuperSendEvent(self: MemorySegment, sel: MemorySegment, event: MemorySegment) {
            Arena.ofConfined().use { arena ->
                // struct objc_super { id receiver; Class super_class; }
                val superStruct = arena.allocate(16L, 8L)
                superStruct.setAtIndex(ValueLayout.ADDRESS, 0, self)
                superStruct.setAtIndex(ValueLayout.ADDRESS, 1, ObjCRuntime.getClass("NSApplication"))
                val msgSendSuperAddr = SymbolLookup.loaderLookup().find("objc_msgSendSuper")
                    .orElseGet {
                        SymbolLookup.libraryLookup("/usr/lib/libobjc.dylib", Arena.global())
                            .find("objc_msgSendSuper").get()
                    }
                val desc = FunctionDescriptor.ofVoid(
                    ValueLayout.ADDRESS, // struct objc_super *
                    ValueLayout.ADDRESS, // SEL
                    ValueLayout.ADDRESS, // id event
                )
                val handle = Linker.nativeLinker().downcallHandle(msgSendSuperAddr, desc)
                // Kotlin's polymorphic-signature handling: invokeExact defaults to Object
                // return; we must cast to Unit so the void descriptor matches.
                handle.invokeExact(superStruct, sel, event) as Unit
            }
        }
    }
}
