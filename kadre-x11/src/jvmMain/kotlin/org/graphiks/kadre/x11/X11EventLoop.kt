/**
 * X11 implementation of [ActiveEventLoop] and the [runApp] entry point.
 *
 * [X11EventLoop] implements [ActiveEventLoop] and is passed to each
 * [ApplicationHandler] callback. The [runApp] function orchestrates
 * the X11 initialization (XOpenDisplay) and the event loop
 * with dynamic switching according to [ControlFlow]:
 *
 * - [ControlFlow.Poll]      → XFlush + while(XPending > 0) { XNextEvent } — non-blocking
 * - [ControlFlow.Wait]      → blocking XNextEvent — CPU < 1 % when idle
 * - [ControlFlow.WaitUntil] → poll with Thread.sleep(1) until the deadline
 *
 * Lazy FFM pattern (tryCreate): all MethodHandles are null on macOS/Windows,
 * which lets the build pass on all platforms.
 *
 * X11EventLoop — X11 event loop with ControlFlow switching.
 */
package org.graphiks.kadre.x11

import org.graphiks.kadre.core.ActiveEventLoop
import org.graphiks.kadre.core.ApplicationHandler
import org.graphiks.kadre.core.ButtonSource
import org.graphiks.kadre.core.ControlFlow
import org.graphiks.kadre.core.DeviceEvents
import org.graphiks.kadre.core.EventLoopProxy
import org.graphiks.kadre.core.KeyCode
import org.graphiks.kadre.core.KeyEvent
import org.graphiks.kadre.core.KeyPlatform
import org.graphiks.kadre.core.KeyState
import org.graphiks.kadre.core.KeyboardModifiers
import org.graphiks.kadre.core.LogicalKey
import org.graphiks.kadre.core.MonitorHandle
import org.graphiks.kadre.core.MouseButton
import org.graphiks.kadre.core.NativeKeyInfo
import org.graphiks.kadre.core.NativeKeyCode
import org.graphiks.kadre.core.NativeLogicalKey
import org.graphiks.kadre.core.PhysicalKey
import org.graphiks.kadre.core.PhysicalPosition
import org.graphiks.kadre.core.PhysicalSize
import org.graphiks.kadre.core.PointerKind
import org.graphiks.kadre.core.PointerSource
import org.graphiks.kadre.core.StartCause
import org.graphiks.kadre.core.TouchPhase
import org.graphiks.kadre.core.Theme
import org.graphiks.kadre.core.Window
import org.graphiks.kadre.core.WindowAttributes
import org.graphiks.kadre.core.WindowEvent
import org.graphiks.kadre.core.WindowId
import org.graphiks.kadre.core.defaultLogicalKey
import org.graphiks.kadre.core.defaultText
import java.lang.foreign.Arena
import java.lang.foreign.MemorySegment
import java.lang.foreign.ValueLayout
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicBoolean

// ── XEvent constants ──────────────────────────────────────────────────────────

/** Size of XEvent in bytes on 64-bit systems (96 bytes). */
private const val XEVENT_SIZE: Long = 96L

/** Alignment of XEvent (8 bytes for 64-bit pointers). */
private const val XEVENT_ALIGN: Long = 8L

// Offsets in XEvent for the common fields (XAnyEvent)
private const val XEVENT_TYPE_OFFSET: Long = 0L     // int type
private const val XEVENT_WINDOW_OFFSET: Long = 16L  // Window window (unsigned long)

// XKeyEvent offsets (type=KeyPress or KeyRelease)
private const val XKEY_STATE_OFFSET: Long = 28L     // unsigned int state (modifiers)
private const val XKEY_KEYCODE_OFFSET: Long = 32L   // unsigned int keycode

// XButtonEvent offsets (type=ButtonPress or ButtonRelease)
private const val XBUTTON_X_OFFSET: Long = 20L      // int x
private const val XBUTTON_Y_OFFSET: Long = 24L      // int y
private const val XBUTTON_BUTTON_OFFSET: Long = 32L // unsigned int button

// XMotionEvent offsets (type=MotionNotify)
private const val XMOTION_X_OFFSET: Long = 20L      // int x
private const val XMOTION_Y_OFFSET: Long = 24L      // int y

// XConfigureEvent offsets (type=ConfigureNotify)
private const val XCONFIGURE_WIDTH_OFFSET: Long = 28L   // int width
private const val XCONFIGURE_HEIGHT_OFFSET: Long = 32L  // int height

// XClientMessageEvent offsets (type=ClientMessage)
private const val XCLIENT_MESSAGE_TYPE_OFFSET: Long = 20L  // Atom message_type (unsigned long)
private const val XCLIENT_DATA_L0_OFFSET: Long = 32L       // long data.l[0]

// X11 modifiers
private const val X11_SHIFT_MASK: Int = 0x0001
private const val X11_CONTROL_MASK: Int = 0x0004
private const val X11_MOD1_MASK: Int = 0x0008  // Alt
private const val X11_MOD4_MASK: Int = 0x0040  // Super / Meta

// X11 buttons
private const val X11_BUTTON1: Int = 1
private const val X11_BUTTON2: Int = 2
private const val X11_BUTTON3: Int = 3
private const val X11_BUTTON4: Int = 4  // scroll up
private const val X11_BUTTON5: Int = 5  // scroll down

// Keysym → Key mapping (common keysyms)
// Reference: /usr/include/X11/keysymdef.h
private const val XK_BackSpace: Int = 0xFF08
private const val XK_Tab: Int = 0xFF09
private const val XK_Return: Int = 0xFF0D
private const val XK_Escape: Int = 0xFF1B
private const val XK_space: Int = 0x0020
private const val XK_F1: Int = 0xFFBE
private const val XK_F12: Int = 0xFFC9
private const val XK_Left: Int = 0xFF51
private const val XK_Up: Int = 0xFF52
private const val XK_Right: Int = 0xFF53
private const val XK_Down: Int = 0xFF54
private const val XK_Shift_L: Int = 0xFFE1
private const val XK_Shift_R: Int = 0xFFE2
private const val XK_Control_L: Int = 0xFFE3
private const val XK_Control_R: Int = 0xFFE4
private const val XK_Alt_L: Int = 0xFFE9
private const val XK_Alt_R: Int = 0xFFEA
private const val XK_Meta_L: Int = 0xFFE7
private const val XK_Meta_R: Int = 0xFFE8
private const val XK_Super_L: Int = 0xFFEB
private const val XK_Super_R: Int = 0xFFEC

// ── Single-instance lock ──────────────────────────────────────────────────────

/**
 * Global lock guaranteeing that only a single X11 event loop is active
 * at a time in the process.
 */
internal val x11Running = AtomicBoolean(false)

// ── X11EventLoop ──────────────────────────────────────────────────────────────

/**
 * Internal implementation of [ActiveEventLoop] for the X11 platform (Linux).
 *
 * An instance is created by a call to [runApp] and passed as the receiver
 * to all [ApplicationHandler] callbacks.
 *
 * ### Lifecycle
 * ```
 * runApp(handler)
 *   └─ handler.resumed(this)
 *   └─ handler.canCreateSurfaces(this)
 *   └─ event loop
 *        ├─ handler.newEvents(this, cause)
 *        ├─ dispatch events according to ControlFlow
 *        └─ handler.aboutToWait(this)
 *   └─ handler.suspended(this)
 * ```
 *
 * ### Thread-safety
 * - [_controlFlow] is @Volatile: readable from any thread.
 * - [_isExiting] is @Volatile: readable from any thread.
 * - [windows] is a ConcurrentHashMap.
 * - The event loop itself runs in the calling thread.
 */
class X11EventLoop internal constructor(
    internal val displayPtr: Long,
    internal val screen: Int,
) : ActiveEventLoop {

    /** Live windows: windowId (XID) → X11Window. */
    internal val windows = ConcurrentHashMap<Long, X11Window>()

    @Volatile
    private var _isExiting = false

    override val isExiting: Boolean
        get() = _isExiting

    @Volatile
    private var _controlFlow: ControlFlow = ControlFlow.Wait

    override val controlFlow: ControlFlow
        get() = _controlFlow

    override fun setControlFlow(controlFlow: ControlFlow) {
        _controlFlow = controlFlow
    }

    /**
     * Creates a new native X11 window and registers it in the window table.
     *
     * @param attributes Window configuration attributes.
     * @return The created window.
     * @throws IllegalStateException if the libX11 bindings are not available.
     */
    override fun createWindow(attributes: WindowAttributes): Window {
        val window = X11Window.create(displayPtr, screen, attributes)
            ?: error(
                "X11Window.create() a retourné null — les bindings libX11.so.6 " +
                "ne sont pas disponibles sur cette plateforme."
            )
        windows[window.id.value] = window
        return window
    }

    /**
     * Requests the X11 event loop to stop.
     */
    override fun exit() {
        _isExiting = true
    }

    /**
     * Creates a thread-safe proxy to this event loop.
     */
    override fun createProxy(): EventLoopProxy =
        X11EventLoopProxy(this, displayPtr)

    // ── R2: monitor enumeration ───────────────────────────────────────────────

    /**
     * Returns all monitors for this X11 display.
     *
     * Tries RANDR, then Xinerama, then a synthetic single-monitor fallback.
     */
    override fun availableMonitors(): List<MonitorHandle> {
        val scale = windows.values.firstOrNull()?.scaleFactor ?: 1.0
        return enumerateX11Monitors(displayPtr, screen, scale)
    }

    /**
     * Returns the first monitor (primary/leftmost) or the synthetic monitor.
     */
    override fun primaryMonitor(): MonitorHandle? = availableMonitors().firstOrNull()

    // ── R3: system theme ──────────────────────────────────────────────────────

    /**
     * X11 has no standard theme API.
     *
     * The xsettings daemon exposes `Net/ThemeName` but there is no standardised
     * Light/Dark distinction. Documented null.
     *
     * TODO(R3-x11-theme): query xsettings or GTK_THEME env variable.
     */
    override fun systemTheme(): Theme? = null

    // ── R4: device event filter ───────────────────────────────────────────────

    /**
     * No-op on X11: device events are always dispatched.
     *
     * TODO(R4-x11-device-filter): use XSelectInput to selectively disable raw motion.
     */
    override fun listenDeviceEvents(mode: DeviceEvents) {
        // no-op on X11
    }
}

// ── Dispatch X11 events ───────────────────────────────────────────────────────

/**
 * Translates an X11 keysym into a kadre [KeyCode].
 *
 * X11 keysym = symbolic key code (defined in keysymdef.h).
 * For letters a-z and digits 0-9, the keysyms are identical to the ASCII codes.
 *
 * @param keysym X11 keysym (INT in XKeyEvent.keycode, translated via XLookupKeysym).
 * @return The corresponding kadre key code, or null if unrecognized.
 */
private fun keysymToKeyCode(keysym: Int): KeyCode? = when (keysym) {
    // Letters a-z (keysyms = lowercase ASCII codes 0x61–0x7A)
    in 0x61..0x7A -> KeyCode.entries[KeyCode.KeyA.ordinal + (keysym - 0x61)]

    // Letters A-Z (keysyms = uppercase ASCII codes 0x41–0x5A)
    in 0x41..0x5A -> KeyCode.entries[KeyCode.KeyA.ordinal + (keysym - 0x41)]

    // Digits 0-9 (keysyms = ASCII codes 0x30–0x39)
    0x30 -> KeyCode.Digit0
    0x31 -> KeyCode.Digit1
    0x32 -> KeyCode.Digit2
    0x33 -> KeyCode.Digit3
    0x34 -> KeyCode.Digit4
    0x35 -> KeyCode.Digit5
    0x36 -> KeyCode.Digit6
    0x37 -> KeyCode.Digit7
    0x38 -> KeyCode.Digit8
    0x39 -> KeyCode.Digit9

    // Function keys F1-F12
    in XK_F1..XK_F12 -> KeyCode.entries[KeyCode.F1.ordinal + (keysym - XK_F1)]

    // Navigation keys
    XK_Left  -> KeyCode.ArrowLeft
    XK_Right -> KeyCode.ArrowRight
    XK_Up    -> KeyCode.ArrowUp
    XK_Down  -> KeyCode.ArrowDown

    // Special keys
    XK_space     -> KeyCode.Space
    XK_Return    -> KeyCode.Enter
    XK_Escape    -> KeyCode.Escape
    XK_BackSpace -> KeyCode.Backspace
    XK_Tab       -> KeyCode.Tab

    // Modifiers
    XK_Shift_L   -> KeyCode.ShiftLeft
    XK_Shift_R   -> KeyCode.ShiftRight
    XK_Control_L -> KeyCode.ControlLeft
    XK_Control_R -> KeyCode.ControlRight
    XK_Alt_L     -> KeyCode.AltLeft
    XK_Alt_R     -> KeyCode.AltRight
    XK_Meta_L,
    XK_Super_L   -> KeyCode.MetaLeft
    XK_Meta_R,
    XK_Super_R   -> KeyCode.MetaRight

    else -> null
}

/**
 * Translates an X11 state field (modifiers) into kadre [KeyboardModifiers].
 *
 * @param state state field of XKeyEvent or XButtonEvent.
 * @return The corresponding [KeyboardModifiers].
 */
internal fun x11StateToModifiers(state: Int): KeyboardModifiers {
    var bits = 0
    if (state and X11_SHIFT_MASK != 0) bits = bits or KeyboardModifiers.SHIFT
    if (state and X11_CONTROL_MASK != 0) bits = bits or KeyboardModifiers.CTRL
    if (state and X11_MOD1_MASK != 0) bits = bits or KeyboardModifiers.ALT
    if (state and X11_MOD4_MASK != 0) bits = bits or KeyboardModifiers.META
    return KeyboardModifiers(bits)
}

internal object X11LiveRepeatTracker {
    private val pressedKeycodes = mutableSetOf<Int>()

    fun update(keycode: Int, state: KeyState): Boolean = when (state) {
        KeyState.Pressed -> !pressedKeycodes.add(keycode)
        KeyState.Released -> {
            pressedKeycodes.remove(keycode)
            false
        }
    }

    fun reset() {
        pressedKeycodes.clear()
    }
}

private fun x11KeyInput(
    keycode: Int,
    keysym: Int,
    mappedCode: KeyCode?,
    state: KeyState,
    modifiers: KeyboardModifiers,
    repeat: Boolean = false,
): WindowEvent.KeyInput {
    val native = NativeKeyInfo(
        platform = KeyPlatform.X11,
        scanCode = keycode.toLong(),
        keyValue = keysym.toString(),
        nativeCode = NativeKeyCode.X11(keycode.toLong()),
        nativeKey = NativeLogicalKey.X11(keysym.toLong()),
    )
    val logicalKey = mappedCode?.defaultLogicalKey() ?: LogicalKey.Unidentified(native)
    return WindowEvent.KeyInput(
        event = KeyEvent(
            physicalKey = mappedCode?.let(PhysicalKey::Code) ?: PhysicalKey.Native(NativeKeyCode.X11(keycode.toLong())),
            logicalKey = logicalKey,
            state = state,
            modifiers = modifiers,
            repeat = repeat,
            text = mappedCode?.defaultText(),
            keyWithoutModifiers = logicalKey,
            native = native,
        ),
        deviceId = null,
    )
}

/**
 * Reads an X11 keycode from the event buffer.
 *
 * Note: a correct implementation would use XLookupKeysym or XkbKeycodeToKeysym
 * to convert a keycode into a keysym. For simplicity (no XLookupKeysym binding),
 * we map keycode → keysym directly using a heuristic based on the standard
 * PC keyboard layout (X11 adds 8 to the hardware keycode).
 *
 * For example: keycode 38 → 'a' (keysym 0x61) on a QWERTY keyboard.
 * This heuristic works for standard PC keyboards.
 */
private fun keycodeToKeysym(keycode: Int): Int {
    // X11 keycode mapping (hardware + 8) → approximate keysym for QWERTY
    // X11 keycodes are layout-dependent — this is an approximation
    return when (keycode) {
        // Letters (standard QWERTY layout)
        38 -> 0x61  // a
        56 -> 0x62  // b
        54 -> 0x63  // c
        40 -> 0x64  // d
        26 -> 0x65  // e
        41 -> 0x66  // f
        42 -> 0x67  // g
        43 -> 0x68  // h
        31 -> 0x69  // i
        44 -> 0x6A  // j
        45 -> 0x6B  // k
        46 -> 0x6C  // l
        58 -> 0x6D  // m
        57 -> 0x6E  // n
        32 -> 0x6F  // o
        33 -> 0x70  // p
        24 -> 0x71  // q
        27 -> 0x72  // r
        39 -> 0x73  // s
        28 -> 0x74  // t
        30 -> 0x75  // u
        55 -> 0x76  // v
        25 -> 0x77  // w
        53 -> 0x78  // x
        29 -> 0x79  // y
        52 -> 0x7A  // z
        // Digits
        19 -> 0x30  // 0
        10 -> 0x31  // 1
        11 -> 0x32  // 2
        12 -> 0x33  // 3
        13 -> 0x34  // 4
        14 -> 0x35  // 5
        15 -> 0x36  // 6
        16 -> 0x37  // 7
        17 -> 0x38  // 8
        18 -> 0x39  // 9
        // Special keys
        65  -> XK_space
        36  -> XK_Return
        9   -> XK_Escape
        22  -> XK_BackSpace
        23  -> XK_Tab
        // Function keys
        67  -> XK_F1
        68  -> XK_F1 + 1   // F2
        69  -> XK_F1 + 2   // F3
        70  -> XK_F1 + 3   // F4
        71  -> XK_F1 + 4   // F5
        72  -> XK_F1 + 5   // F6
        73  -> XK_F1 + 6   // F7
        74  -> XK_F1 + 7   // F8
        75  -> XK_F1 + 8   // F9
        76  -> XK_F1 + 9   // F10
        95  -> XK_F1 + 10  // F11
        96  -> XK_F1 + 11  // F12
        // Navigation
        113 -> XK_Left
        114 -> XK_Right
        111 -> XK_Up
        116 -> XK_Down
        // Modifiers
        50  -> XK_Shift_L
        62  -> XK_Shift_R
        37  -> XK_Control_L
        105 -> XK_Control_R
        64  -> XK_Alt_L
        108 -> XK_Alt_R
        133 -> XK_Super_L
        134 -> XK_Super_R
        else -> 0
    }
}

/**
 * Dispatches a single XEvent to the [ApplicationHandler] callbacks.
 *
 * @param eventBuf Buffer containing the XEvent read by XNextEvent.
 * @param loop     Active event loop.
 * @param handler  Application handler to notify.
 * @param wmDeleteWindow WM_DELETE_WINDOW atom for detecting window close.
 */
private fun dispatchEvent(
    eventBuf: MemorySegment,
    loop: X11EventLoop,
    handler: ApplicationHandler,
    wmDeleteWindow: Long,
) {
    val eventType = eventBuf.get(ValueLayout.JAVA_INT, XEVENT_TYPE_OFFSET)
    val windowXid = eventBuf.get(ValueLayout.JAVA_LONG, XEVENT_WINDOW_OFFSET)
    val windowId = WindowId(windowXid)

    when (eventType) {

        // ── Exposure (redraw) ─────────────────────────────────────────────────
        Expose -> {
            handler.windowEvent(loop, windowId, WindowEvent.RedrawRequested)
        }

        // ── Resize ────────────────────────────────────────────────────────────
        ConfigureNotify -> {
            val width  = eventBuf.get(ValueLayout.JAVA_INT, XCONFIGURE_WIDTH_OFFSET)
            val height = eventBuf.get(ValueLayout.JAVA_INT, XCONFIGURE_HEIGHT_OFFSET)
            loop.windows[windowXid]?.onConfigureNotify(width, height)
            if (width > 0 && height > 0) {
                handler.windowEvent(loop, windowId, WindowEvent.Resized(PhysicalSize(width, height)))
            }
        }

        // ── Keyboard ──────────────────────────────────────────────────────────
        KeyPress -> {
            val keycode = eventBuf.get(ValueLayout.JAVA_INT, XKEY_KEYCODE_OFFSET)
            val state   = eventBuf.get(ValueLayout.JAVA_INT, XKEY_STATE_OFFSET)
            val keysym  = keycodeToKeysym(keycode)
            val mappedCode = keysymToKeyCode(keysym)
            val mods    = x11StateToModifiers(state)
            val repeat = X11LiveRepeatTracker.update(keycode, KeyState.Pressed)
            handler.windowEvent(loop, windowId, x11KeyInput(keycode, keysym, mappedCode, KeyState.Pressed, mods, repeat))
        }

        KeyRelease -> {
            val keycode = eventBuf.get(ValueLayout.JAVA_INT, XKEY_KEYCODE_OFFSET)
            val state   = eventBuf.get(ValueLayout.JAVA_INT, XKEY_STATE_OFFSET)
            val keysym  = keycodeToKeysym(keycode)
            val mappedCode = keysymToKeyCode(keysym)
            val mods    = x11StateToModifiers(state)
            X11LiveRepeatTracker.update(keycode, KeyState.Released)
            handler.windowEvent(loop, windowId, x11KeyInput(keycode, keysym, mappedCode, KeyState.Released, mods))
        }

        // ── Mouse buttons ─────────────────────────────────────────────────────
        ButtonPress -> {
            val button = eventBuf.get(ValueLayout.JAVA_INT, XBUTTON_BUTTON_OFFSET)
            val position = xButtonPosition(eventBuf)
            when (button) {
                X11_BUTTON1 -> handler.windowEvent(loop, windowId,
                    pointerButton(MouseButton.Left, KeyState.Pressed, position))
                X11_BUTTON2 -> handler.windowEvent(loop, windowId,
                    pointerButton(MouseButton.Middle, KeyState.Pressed, position))
                X11_BUTTON3 -> handler.windowEvent(loop, windowId,
                    pointerButton(MouseButton.Right, KeyState.Pressed, position))
                X11_BUTTON4 -> handler.windowEvent(loop, windowId,
                    WindowEvent.MouseWheel(null, deltaX = 0.0, deltaY = 1.0, phase = TouchPhase.Moved))
                X11_BUTTON5 -> handler.windowEvent(loop, windowId,
                    WindowEvent.MouseWheel(null, deltaX = 0.0, deltaY = -1.0, phase = TouchPhase.Moved))
                else -> handler.windowEvent(loop, windowId,
                    pointerButton(MouseButton.Other(button), KeyState.Pressed, position))
            }
        }

        ButtonRelease -> {
            val button = eventBuf.get(ValueLayout.JAVA_INT, XBUTTON_BUTTON_OFFSET)
            val position = xButtonPosition(eventBuf)
            // Do not emit MouseInput Released for scroll wheel events (4 and 5)
            when (button) {
                X11_BUTTON1 -> handler.windowEvent(loop, windowId,
                    pointerButton(MouseButton.Left, KeyState.Released, position))
                X11_BUTTON2 -> handler.windowEvent(loop, windowId,
                    pointerButton(MouseButton.Middle, KeyState.Released, position))
                X11_BUTTON3 -> handler.windowEvent(loop, windowId,
                    pointerButton(MouseButton.Right, KeyState.Released, position))
                X11_BUTTON4, X11_BUTTON5 -> { /* scroll wheel — no Released */ }
                else -> handler.windowEvent(loop, windowId,
                    pointerButton(MouseButton.Other(button), KeyState.Released, position))
            }
        }

        // ── Mouse motion ──────────────────────────────────────────────────────
        MotionNotify -> {
            val x = eventBuf.get(ValueLayout.JAVA_INT, XMOTION_X_OFFSET).toDouble()
            val y = eventBuf.get(ValueLayout.JAVA_INT, XMOTION_Y_OFFSET).toDouble()
            handler.windowEvent(loop, windowId, WindowEvent.PointerMoved(null, PhysicalPosition(x, y), primary = true, source = PointerSource.Mouse))
        }

        // ── Cursor enter/leave ────────────────────────────────────────────────
        EnterNotify -> {
            handler.windowEvent(loop, windowId, WindowEvent.PointerEntered(null, xCrossingPosition(eventBuf), primary = true, kind = PointerKind.Mouse))
        }

        LeaveNotify -> {
            handler.windowEvent(loop, windowId, WindowEvent.PointerLeft(null, xCrossingPosition(eventBuf), primary = true, kind = PointerKind.Mouse))
        }

        // ── Window destruction ────────────────────────────────────────────────
        DestroyNotify -> {
            handler.windowEvent(loop, windowId, WindowEvent.Destroyed)
            loop.windows.remove(windowXid)
        }

        // ── ClientMessage (WM close + wakeUp) ─────────────────────────────────
        ClientMessage -> {
            val messageType = eventBuf.get(ValueLayout.JAVA_LONG, XCLIENT_MESSAGE_TYPE_OFFSET)
            if (messageType == wmDeleteWindow) {
                handler.windowEvent(loop, windowId, WindowEvent.CloseRequested)
            }
            // The wakeUp ClientMessages (KADRE_WAKEUP_TYPE) are simply ignored —
            // their only role is to unblock XNextEvent.
        }
    }
}

private fun pointerButton(
    button: MouseButton,
    state: KeyState,
    position: PhysicalPosition<Double>,
): WindowEvent.PointerButton =
    WindowEvent.PointerButton(
        deviceId = null,
        state = state,
        position = position,
        primary = true,
        button = ButtonSource.Mouse(button),
    )

private fun xButtonPosition(eventBuf: MemorySegment): PhysicalPosition<Double> =
    PhysicalPosition(
        eventBuf.get(ValueLayout.JAVA_INT, XBUTTON_X_OFFSET).toDouble(),
        eventBuf.get(ValueLayout.JAVA_INT, XBUTTON_Y_OFFSET).toDouble(),
    )

private fun xCrossingPosition(eventBuf: MemorySegment): PhysicalPosition<Double> =
    PhysicalPosition(
        eventBuf.get(ValueLayout.JAVA_INT, XBUTTON_X_OFFSET).toDouble(),
        eventBuf.get(ValueLayout.JAVA_INT, XBUTTON_Y_OFFSET).toDouble(),
    )

// ── Dispatch modes ──────────────────────────────────────────────────────────

/**
 * Poll mode: XFlush + while(XPending > 0) { XNextEvent; dispatch } — non-blocking.
 *
 * Drains the event queue in a single pass and returns immediately.
 */
private fun dispatchPoll(
    displaySeg: MemorySegment,
    eventBuf: MemorySegment,
    loop: X11EventLoop,
    handler: ApplicationHandler,
    wmDeleteWindow: Long,
): StartCause {
    xFlush?.invokeExact(displaySeg) as? Int

    val pendingHandle = xPending
    val nextHandle    = xNextEvent

    if (pendingHandle != null && nextHandle != null) {
        while (!loop.isExiting) {
            val pending = pendingHandle.invokeExact(displaySeg) as Int
            if (pending <= 0) break
            nextHandle.invokeExact(displaySeg, eventBuf) as Int
            dispatchEvent(eventBuf, loop, handler, wmDeleteWindow)
        }
    }

    return StartCause.Poll
}

/**
 * Wait mode: blocking XNextEvent — CPU < 1 % when idle.
 *
 * Blocks the thread until the next event is received.
 */
private fun dispatchWait(
    displaySeg: MemorySegment,
    eventBuf: MemorySegment,
    loop: X11EventLoop,
    handler: ApplicationHandler,
    wmDeleteWindow: Long,
): StartCause {
    val nextHandle = xNextEvent ?: return StartCause.WaitCancelled()

    // XNextEvent blocks until an event arrives
    nextHandle.invokeExact(displaySeg, eventBuf) as Int
    dispatchEvent(eventBuf, loop, handler, wmDeleteWindow)

    // Drain the additional events already available
    val pendingHandle = xPending
    if (pendingHandle != null) {
        while (!loop.isExiting) {
            val pending = pendingHandle.invokeExact(displaySeg) as Int
            if (pending <= 0) break
            nextHandle.invokeExact(displaySeg, eventBuf) as Int
            dispatchEvent(eventBuf, loop, handler, wmDeleteWindow)
        }
    }

    return StartCause.WaitCancelled()
}

/**
 * WaitUntil mode: poll with Thread.sleep(1ms) until the deadline.
 *
 * Dispatches available events and sleeps 1ms between each check,
 * until the timeout expires or an event is received.
 */
private fun dispatchWaitUntil(
    displaySeg: MemorySegment,
    eventBuf: MemorySegment,
    loop: X11EventLoop,
    handler: ApplicationHandler,
    cf: ControlFlow.WaitUntil,
    wmDeleteWindow: Long,
): StartCause {
    val deadline = cf.instant
    val pendingHandle = xPending
    val nextHandle    = xNextEvent

    while (!loop.isExiting) {
        val now = System.currentTimeMillis()
        if (now >= deadline) {
            return StartCause.ResumeTimeReached(
                requestedResume = deadline,
                start = now,
            )
        }

        // Attempt to dispatch the available events
        if (pendingHandle != null && nextHandle != null) {
            val pending = pendingHandle.invokeExact(displaySeg) as Int
            if (pending > 0) {
                nextHandle.invokeExact(displaySeg, eventBuf) as Int
                dispatchEvent(eventBuf, loop, handler, wmDeleteWindow)
                return StartCause.WaitCancelled(deadline)
            }
        }

        Thread.sleep(1L)
    }

    return StartCause.WaitCancelled(deadline)
}

// ── Entry point ───────────────────────────────────────────────────────────────

/**
 * Entry point of the kadre event loop on Linux (X11).
 *
 * Opens the connection to the X server (XOpenDisplay), creates an [X11EventLoop],
 * calls [ApplicationHandler.resumed], then starts the blocking event loop.
 * Only returns when the application closes.
 *
 * Must be called from the main thread (the one that opened the display).
 *
 * @param handler Lifecycle and event handler.
 * @throws IllegalStateException if an X11 loop is already active in this process.
 */
fun runApp(handler: ApplicationHandler) {
    check(x11Running.compareAndSet(false, true)) {
        "X11EventLoop.runApp() ne peut être appelé qu'une seule fois par processus. " +
        "Une boucle d'événements X11 est déjà active."
    }

    try {
        val openHandle = xOpenDisplay
        if (openHandle == null) {
            // libX11 unavailable (macOS/Windows) — graceful no-op
            return
        }

        // XOpenDisplay(NULL) → uses the DISPLAY environment variable
        val displaySeg = openHandle.invokeExact(MemorySegment.NULL) as MemorySegment
        if (displaySeg == MemorySegment.NULL || displaySeg.address() == 0L) {
            return  // No X server available
        }
        val displayPtr = displaySeg.address()
        val screen = 0  // default screen

        // Obtain the WM_DELETE_WINDOW atom to detect a clean close
        val wmDeleteWindow: Long = Arena.ofConfined().use { arena ->
            val atomName = "WM_DELETE_WINDOW".toByteArray(Charsets.US_ASCII)
            val namePtr = arena.allocate(atomName.size.toLong() + 1)
            for (i in atomName.indices) namePtr.set(ValueLayout.JAVA_BYTE, i.toLong(), atomName[i])
            namePtr.set(ValueLayout.JAVA_BYTE, atomName.size.toLong(), 0)
            xInternAtom?.invokeExact(displaySeg, namePtr, 0) as? Long ?: 0L
        }

        val loop = X11EventLoop(displayPtr, screen)

        // Allocate the XEvent buffer (96 bytes, 8-aligned) for the duration of the loop
        val arena = Arena.ofConfined()
        try {
            val eventBuf = arena.allocate(XEVENT_SIZE, XEVENT_ALIGN)

            // Notify the handler that the application resumes
            handler.resumed(loop)

            // Notify that surfaces can be created
            handler.canCreateSurfaces(loop)

            var startCause: StartCause = StartCause.Init

            while (!loop.isExiting) {
                // Notify the handler of the iteration start
                handler.newEvents(loop, startCause)
                if (loop.isExiting) break

                // Dispatch the events according to the current ControlFlow
                startCause = when (val cf = loop.controlFlow) {
                    is ControlFlow.Poll      -> dispatchPoll(displaySeg, eventBuf, loop, handler, wmDeleteWindow)
                    is ControlFlow.Wait      -> dispatchWait(displaySeg, eventBuf, loop, handler, wmDeleteWindow)
                    is ControlFlow.WaitUntil -> dispatchWaitUntil(displaySeg, eventBuf, loop, handler, cf, wmDeleteWindow)
                }

                // Notify the handler that the loop is about to wait
                handler.aboutToWait(loop)
            }

            handler.suspended(loop)
        } finally {
            arena.close()
            xCloseDisplay?.invokeExact(displaySeg) as? Int
        }
    } finally {
        x11Running.set(false)
    }
}
