/**
 * Objective-C subclass of NSApplication for kadre.
 *
 * Overrides `sendEvent:` to intercept keyboard events (NSEventTypeKeyDown /
 * NSEventTypeKeyUp) and dispatch them as [WindowEvent.KeyInput] to the
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
 * Direct touch pointer events are **not** emitted on AppKit. macOS exposes no
 * touchscreen API; the only touch source is the trackpad (`NSTouch` via
 * `touchesBeganWithEvent:`), which reports *indirect* contacts in a normalized
 * 0..1 space with no relation to window/client pixels.
 */
package org.graphiks.kadre.appkit

import org.graphiks.kadre.appkit.bindings.NSApplication
import org.graphiks.kadre.appkit.bindings.NSApplicationActivationPolicy
import org.graphiks.kadre.appkit.bindings.NSEvent
import org.graphiks.kadre.appkit.bindings.ObjCRuntime
import org.graphiks.kadre.core.ButtonSource
import org.graphiks.kadre.core.DeviceEvent
import org.graphiks.kadre.core.DeviceId
import org.graphiks.kadre.core.KeyCode
import org.graphiks.kadre.core.KeyEvent
import org.graphiks.kadre.core.KeyPlatform
import org.graphiks.kadre.core.KeyState
import org.graphiks.kadre.core.KeyboardModifierState
import org.graphiks.kadre.core.LogicalKey
import org.graphiks.kadre.core.location
import org.graphiks.kadre.core.MouseButton
import org.graphiks.kadre.core.NativeKeyInfo
import org.graphiks.kadre.core.NativeKeyCode
import org.graphiks.kadre.core.NativeLogicalKey
import org.graphiks.kadre.core.PhysicalPosition
import org.graphiks.kadre.core.PointerKind
import org.graphiks.kadre.core.PointerSource
import org.graphiks.kadre.core.TouchPhase
import org.graphiks.kadre.core.WindowEvent
import org.graphiks.kadre.core.defaultLogicalKey
import org.graphiks.kadre.core.defaultText
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
     * to [AppKitEventLoop] as [WindowEvent.KeyInput].
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

                val mappedCode = AppKitKeyMapper.keyCode(keyCode)
                val modifiers = AppKitKeyMapper.modifierFlags(modFlags)
                val state = if (isKeyDown) KeyState.Pressed else KeyState.Released
                // R4: extract text via [NSEvent characters] (nil-safe)
                val text: String? = if (isKeyDown) {
                    try {
                        val charsPtr = ObjCRuntime.msgSend(
                            ValueLayout.ADDRESS,
                            event,
                            ObjCRuntime.sel("characters"),
                        ) as MemorySegment
                        if (charsPtr == MemorySegment.NULL) null
                        else {
                            // NSString → UTF-8 via UTF8String selector
                            val utf8Ptr = ObjCRuntime.msgSend(
                                ValueLayout.ADDRESS,
                                charsPtr,
                                ObjCRuntime.sel("UTF8String"),
                            ) as MemorySegment
                            if (utf8Ptr == MemorySegment.NULL) null
                            else {
                                val s = utf8Ptr.reinterpret(256L).getString(0L, java.nio.charset.StandardCharsets.UTF_8)
                                // Only return text if it's printable (non-control)
                                if (s.isNotEmpty() && s.all { it >= ' ' }) s else null
                            }
                        }
                    } catch (_: Throwable) { null }
                } else null
                val native = NativeKeyInfo(
                    platform = KeyPlatform.AppKit,
                    scanCode = keyCode.toLong(),
                    nativeCode = NativeKeyCode.AppKit(keyCode.toLong()),
                    nativeKey = NativeLogicalKey.AppKit(characters = text),
                )
                val logicalKey = mappedCode?.defaultLogicalKey()
                    ?: LogicalKey.Unidentified(native)

                // GRA-156: dispatch raw DeviceEvent.Key BEFORE window-scoped WindowEvent
                loop.handler.deviceEvent(
                    loop,
                    DeviceId(0L),
                    DeviceEvent.Key(keyCode.toInt(), state),
                )

                loop.handler.windowEvent(
                    loop,
                    appKitWindow.id,
                    WindowEvent.KeyInput(
                        event = KeyEvent(
                            physicalKey = AppKitKeyMapper.physicalKey(keyCode),
                            logicalKey = logicalKey,
                            state = state,
                            modifiers = modifiers,
                            location = AppKitKeyMapper.physicalKey(keyCode).location(),
                            repeat = isRepeat,
                            synthetic = false,
                            text = text ?: mappedCode?.defaultText(),
                            keyWithoutModifiers = logicalKey,
                            native = native,
                        ),
                        deviceId = DeviceId(0L),
                    ),
                )

                // R4: emit ModifiersChanged when a modifier key is involved
                val isModifierKey = mappedCode in setOf(
                    KeyCode.ShiftLeft, KeyCode.ShiftRight,
                    KeyCode.ControlLeft, KeyCode.ControlRight,
                    KeyCode.AltLeft, KeyCode.AltRight,
                    KeyCode.MetaLeft, KeyCode.MetaRight,
                )
                if (isModifierKey) {
                    loop.handler.windowEvent(
                        loop,
                        appKitWindow.id,
                        WindowEvent.ModifiersChanged(KeyboardModifierState(logical = modifiers)),
                    )
                }
                return
            }

            // ── Trackpad gestures ─────────────────────────────────────────────────────
            if (eventType == AppKitGestureMapper.EVENT_TYPE_MAGNIFY ||
                eventType == AppKitGestureMapper.EVENT_TYPE_ROTATE ||
                eventType == AppKitGestureMapper.EVENT_TYPE_SMART_MAGNIFY ||
                eventType == AppKitGestureMapper.EVENT_TYPE_PRESSURE
            ) {
                val eventWindow = ObjCRuntime.msgSend(ValueLayout.ADDRESS, event, ObjCRuntime.sel("window")) as MemorySegment
                if (eventWindow == MemorySegment.NULL) return
                val appKitWindow = loop.windows[eventWindow.address()] ?: return
                val nsEvent = NSEvent(event)
                val deviceId = DeviceId(
                    ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, event, ObjCRuntime.sel("deviceID")) as Long
                )
                if (eventType == AppKitGestureMapper.EVENT_TYPE_MAGNIFY ||
                    eventType == AppKitGestureMapper.EVENT_TYPE_ROTATE ||
                    eventType == AppKitGestureMapper.EVENT_TYPE_SMART_MAGNIFY
                ) {
                    val locPt = nsEvent.locationInWindow()
                    val locX = locPt.getAtIndex(ValueLayout.JAVA_DOUBLE, 0)
                    val locY = locPt.getAtIndex(ValueLayout.JAVA_DOUBLE, 1)
                    val scale = appKitWindow.scaleFactor
                    val contentWidthPoints = appKitWindow.innerSize.width / scale
                    val contentHeightPoints = appKitWindow.innerSize.height / scale
                    val position = AppKitGestureMapper.pointerMovedPosition(
                        locationXPoints = locX,
                        locationYPoints = locY,
                        contentWidthPoints = contentWidthPoints,
                        contentHeightPoints = contentHeightPoints,
                        scaleFactor = scale,
                        pressedMouseButtons = NSEvent.pressedMouseButtons(),
                    )
                    if (position != null) {
                        loop.handler.windowEvent(
                            loop,
                            appKitWindow.id,
                            WindowEvent.PointerMoved(
                                deviceId = deviceId,
                                position = position,
                                primary = true,
                                source = PointerSource.Mouse,
                            ),
                        )
                    }
                }
                val phase = if (eventType == AppKitGestureMapper.EVENT_TYPE_MAGNIFY ||
                    eventType == AppKitGestureMapper.EVENT_TYPE_ROTATE
                ) {
                    AppKitGestureMapper.phase(
                        ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, event, ObjCRuntime.sel("phase")) as Long
                    ) ?: return
                } else {
                    TouchPhase.Moved
                }
                val gestureEvent = AppKitGestureMapper.event(
                    eventType = eventType,
                    deviceId = deviceId,
                    phase = phase,
                    magnification = if (eventType == AppKitGestureMapper.EVENT_TYPE_MAGNIFY) nsEvent.magnification() else 0.0,
                    rotationDegrees = if (eventType == AppKitGestureMapper.EVENT_TYPE_ROTATE) nsEvent.rotation() else 0f,
                    pressure = if (eventType == AppKitGestureMapper.EVENT_TYPE_PRESSURE) nsEvent.pressure() else 0f,
                    stage = if (eventType == AppKitGestureMapper.EVENT_TYPE_PRESSURE) nsEvent.stage() else 0L,
                ) ?: return
                loop.handler.windowEvent(loop, appKitWindow.id, gestureEvent)
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

            // ── Pointer enter/exit ────────────────────────────────────────────────────
            if (isMouseEntered) {
                loop.handler.windowEvent(
                    loop,
                    appKitWindow.id,
                    WindowEvent.PointerEntered(
                        deviceId = DeviceId(0L),
                        position = position,
                        primary = true,
                        kind = PointerKind.Mouse,
                    ),
                )
                return
            }
            if (isMouseExited) {
                loop.handler.windowEvent(
                    loop,
                    appKitWindow.id,
                    WindowEvent.PointerLeft(
                        deviceId = DeviceId(0L),
                        position = position,
                        primary = true,
                        kind = PointerKind.Mouse,
                    ),
                )
                return
            }

            // ── Scroll wheel ──────────────────────────────────────────────────────────
            if (isScrollWheel) {
                val nsEvent = NSEvent(event)
                val deltaX = nsEvent.scrollingDeltaX()
                val deltaY = nsEvent.scrollingDeltaY()
                loop.handler.windowEvent(loop, appKitWindow.id, WindowEvent.MouseWheel(DeviceId(0L), deltaX, deltaY, TouchPhase.Moved))
                return
            }

            // ── Mouse move / drag ─────────────────────────────────────────────────────
            if (isMouseMoved || isLeftDragged || isRightDragged || isOtherDragged) {
                loop.handler.windowEvent(
                    loop,
                    appKitWindow.id,
                    WindowEvent.PointerMoved(
                        deviceId = DeviceId(0L),
                        position = position,
                        primary = true,
                        source = PointerSource.Mouse,
                    ),
                )
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

            // GRA-156: dispatch raw DeviceEvent.Button BEFORE window-scoped pointer button.
            val rawButton = when {
                isLeftDown || isLeftUp   -> 0
                isRightDown || isRightUp -> 1
                else -> (ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, event, ObjCRuntime.sel("buttonNumber")) as Long).toInt()
            }
            loop.handler.deviceEvent(loop, DeviceId(0L), DeviceEvent.Button(rawButton, state))

            loop.handler.windowEvent(
                loop,
                appKitWindow.id,
                WindowEvent.PointerButton(
                    deviceId = DeviceId(0L),
                    state = state,
                    position = position,
                    primary = true,
                    button = ButtonSource.Mouse(button),
                ),
            )
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
