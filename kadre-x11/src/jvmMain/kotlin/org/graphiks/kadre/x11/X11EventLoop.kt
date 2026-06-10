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
import org.graphiks.kadre.core.ButtonSource
import org.graphiks.kadre.core.ApplicationHandler
import org.graphiks.kadre.core.ControlFlow
import org.graphiks.kadre.core.CursorImage
import org.graphiks.kadre.core.CustomCursor
import org.graphiks.kadre.core.DeviceEvents
import org.graphiks.kadre.core.EventLoopProxy
import org.graphiks.kadre.core.KeyCode
import org.graphiks.kadre.core.OwnedDisplayHandle
import org.graphiks.kadre.core.RawDisplayHandle
import org.graphiks.kadre.core.KeyEvent
import org.graphiks.kadre.core.KeyPlatform
import org.graphiks.kadre.core.KeyState
import org.graphiks.kadre.core.KeyboardModifierState
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
import java.util.concurrent.ConcurrentLinkedQueue
import java.util.concurrent.atomic.AtomicBoolean

// ── XEvent constants ──────────────────────────────────────────────────────────

/** Size of XEvent in bytes on 64-bit systems (96 bytes). */
private const val XEVENT_SIZE: Long = 96L

/** Alignment of XEvent (8 bytes for 64-bit pointers). */
private const val XEVENT_ALIGN: Long = 8L

// Offsets in XEvent for Xlib LP64 structs.
private const val XEVENT_TYPE_OFFSET: Long = 0L     // int type
internal const val XANY_WINDOW_OFFSET: Long = 32L   // XAnyEvent.window

// XKeyEvent offsets (type=KeyPress or KeyRelease)
private const val XKEY_STATE_OFFSET: Long = 80L     // unsigned int state (modifiers)
private const val XKEY_KEYCODE_OFFSET: Long = 84L   // unsigned int keycode

// XButtonEvent offsets (type=ButtonPress or ButtonRelease)
private const val XBUTTON_X_OFFSET: Long = 64L      // int x
private const val XBUTTON_Y_OFFSET: Long = 68L      // int y
private const val XBUTTON_BUTTON_OFFSET: Long = 84L // unsigned int button

// XMotionEvent offsets (type=MotionNotify)
private const val XMOTION_X_OFFSET: Long = 64L      // int x
private const val XMOTION_Y_OFFSET: Long = 68L      // int y

// XConfigureEvent offsets (type=ConfigureNotify)
private const val XCONFIGURE_SEND_EVENT_OFFSET: Long = 16L // Bool send_event
private const val XCONFIGURE_WINDOW_OFFSET: Long = 40L  // Window window
private const val XCONFIGURE_X_OFFSET: Long = 48L       // int x
private const val XCONFIGURE_Y_OFFSET: Long = 52L       // int y
private const val XCONFIGURE_WIDTH_OFFSET: Long = 56L   // int width
private const val XCONFIGURE_HEIGHT_OFFSET: Long = 60L  // int height

// XDestroyWindowEvent offsets (type=DestroyNotify)
private const val XDESTROY_WINDOW_OFFSET: Long = 40L // Window window

// XVisibilityEvent offsets (type=VisibilityNotify)
private const val XVISIBILITY_STATE_OFFSET: Long = 40L // int state

// X11 modifiers
private const val X11_SHIFT_MASK: Int = 0x0001
private const val X11_CONTROL_MASK: Int = 0x0004
private const val X11_MOD1_MASK: Int = 0x0008  // Alt
private const val X11_MOD4_MASK: Int = 0x0040  // Super / Meta
private const val X11_KEYMAP_SIZE: Long = 32L
private const val X11_KEYMAP_BITS: Int = 256

private const val X11_VISIBILITY_FULLY_OBSCURED: Int = 2

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
    internal val keyboardModifierTracker = X11KeyboardModifierTracker()

    /**
     * Xdnd drag source window per target window.
     * Cleared when drag leaves or finishes.
     */
    internal val dragSourceWindows = ConcurrentHashMap<Long, Long>()

    /**
     * Whether an Xdnd drop data request (XConvertSelection) is pending.
     * Key: target window XID, Value: drop position.
     */
    internal val pendingDropRequests = ConcurrentHashMap<Long, PhysicalPosition<Double>>()

    /** Queue of pending Xdnd drops awaiting selection data. */
    internal val pendingXdndDrops = ConcurrentLinkedQueue<PendingXdndDrop>()

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
                "X11Window.create() returned null — libX11.so.6 bindings are not available on this platform."
            )
        windows[window.id.value] = window
        return window
    }

    /**
     * Creates a window with X11-specific attributes.
     *
     * Merges [X11WindowAttributes] fields into the core [WindowAttributes]
     * and applies platform-specific settings at creation time.
     */
    fun createWindow(attrs: X11WindowAttributes): Window {
        val window = X11Window.create(displayPtr, screen, attrs.core)
            ?: error(
                "X11Window.create() returned null — libX11.so.6 bindings are not available on this platform."
            )
        windows[window.id.value] = window
        // Apply platform extension settings
        if (attrs.windowType != null) window.setWindowType(attrs.windowType)
        if (attrs.overrideRedirect) window.setOverrideRedirect(true)
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

    /**
     * Returns a persistent X11 display handle usable independently from a window.
     */
    override fun ownedDisplayHandle(): OwnedDisplayHandle? {
        return OwnedDisplayHandle(RawDisplayHandle.Xlib(display = displayPtr))
    }

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
     * Returns the XRandR primary monitor, or the first/synthetic fallback.
     */
    override fun primaryMonitor(): MonitorHandle? {
        val scale = windows.values.firstOrNull()?.scaleFactor ?: 1.0
        return primaryX11Monitor(displayPtr, screen, scale)
    }

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

    // ── R5-CustomCursor ─────────────────────────────────────────────────────────

    /**
     * Creates a monochrome custom cursor from RGBA pixel data on X11.
     *
     * Computes 1-bit source/mask bitmaps (source = luminance>128, mask = alpha>0),
     * then calls XCreateBitmapFromData + XCreatePixmapCursor with white foreground
     * and black background. Both pixmaps are freed after cursor creation.
     *
     * This is a monochrome fallback — no XRender or ARGB cursor support yet.
     * Returns null on failure (missing symbols, invalid image, or OOM).
     */
    override fun createCustomCursor(image: CursorImage): CustomCursor? {
        val createBitmap = xCreateBitmapFromData ?: return null
        val createPixmapCursor = xCreatePixmapCursor ?: return null
        val freePixmap = xFreePixmap
        val rootHandle = xRootWindow ?: return null

        if (image.width <= 0 || image.height <= 0) return null
        val pixelCount = image.width * image.height
        val byteCount = pixelCount * 4
        if (byteCount > Int.MAX_VALUE || image.rgba.size != byteCount) return null

        val display = MemorySegment.ofAddress(displayPtr)

        return try {
            val root = rootHandle.invokeExact(display, screen) as Long
            if (root == 0L) return null

            Arena.ofConfined().use { arena ->
                val srcBytes = ByteArray(pixelCount) { index ->
                    val offset = index * 4
                    val r = image.rgba[offset].toInt() and 0xFF
                    val g = image.rgba[offset + 1].toInt() and 0xFF
                    val b = image.rgba[offset + 2].toInt() and 0xFF
                    val alpha = image.rgba[offset + 3].toInt() and 0xFF
                    if ((r + g + b) / 3 > 128) 1 else 0
                }
                val maskBytes = ByteArray(pixelCount) { index ->
                    val alpha = image.rgba[index * 4 + 3].toInt() and 0xFF
                    if (alpha > 0) 1 else 0
                }

                val srcData = arena.allocate(srcBytes.size.toLong(), 1L)
                val maskData = arena.allocate(maskBytes.size.toLong(), 1L)
                for (i in srcBytes.indices) srcData.setAtIndex(ValueLayout.JAVA_BYTE, i.toLong(), srcBytes[i])
                for (i in maskBytes.indices) maskData.setAtIndex(ValueLayout.JAVA_BYTE, i.toLong(), maskBytes[i])

                val source = createBitmap.invokeExact(display, root, srcData, image.width, image.height) as Long
                if (source == 0L) return@use null
                val mask = createBitmap.invokeExact(display, root, maskData, image.width, image.height) as Long
                if (mask == 0L) {
                    if (freePixmap != null) freePixmap.invokeExact(display, source) as Int
                    return@use null
                }
                try {
                    val foreground = arena.allocate(X11_COLOR_SIZE_BYTES, X11_COLOR_ALIGN_BYTES)
                    val background = arena.allocate(X11_COLOR_SIZE_BYTES, X11_COLOR_ALIGN_BYTES)
                    foreground.setAtIndex(ValueLayout.JAVA_BYTE, 0L, 1)
                    background.fill(0)
                    val cursor = createPixmapCursor.invokeExact(
                        display, source, mask, foreground, background,
                        image.hotspotX, image.hotspotY,
                    ) as Long
                    if (cursor == 0L) return@use null
                    CustomCursor(id = cursor)
                } finally {
                    if (freePixmap != null) {
                        freePixmap.invokeExact(display, source) as Int
                        freePixmap.invokeExact(display, mask) as Int
                    }
                }
            }
        } catch (_: Throwable) { null }
    }
}

/**
 * Pending Xdnd drop state used when waiting for SelectionNotify.
 */
internal data class PendingXdndDrop(
    val targetWindow: Long,
    val sourceWindow: Long,
    val position: PhysicalPosition<Double>,
)

/**
 * Xdnd atoms interned at event loop start.
 */
internal data class XdndAtoms(
    val xdndEnter: Long,
    val xdndPosition: Long,
    val xdndLeave: Long,
    val xdndDrop: Long,
    val xdndSelection: Long,
    val textUriList: Long,
)

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
internal fun keysymToKeyCode(keysym: Int): KeyCode? = when (keysym) {
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

internal fun x11ModifierStateFromPressedKeycodes(keycodes: Iterable<Int>): KeyboardModifierState {
    var state = X11KeyMapper.initialModifierState()
    keycodes.forEach { keycode ->
        val keyCode = x11KeycodeToKeyCode(keycode)
        if (X11KeyMapper.isModifierKey(keyCode)) {
            state = X11KeyMapper.modifierStateFrom(state, keyCode, KeyState.Pressed)
        }
    }
    return state
}

internal fun x11PressedKeycodesFromKeymap(keymap: MemorySegment): List<Int> {
    val snapshot = keymap.reinterpret(X11_KEYMAP_SIZE)
    return (0 until X11_KEYMAP_BITS).filter { keycode ->
        val bits = snapshot.get(ValueLayout.JAVA_BYTE, (keycode / 8).toLong()).toInt() and 0xFF
        bits and (1 shl (keycode % 8)) != 0
    }
}

private fun queryX11ModifierState(displayPtr: Long): KeyboardModifierState? {
    val handle = xQueryKeymap ?: return null
    if (displayPtr == 0L) return null
    return try {
        Arena.ofConfined().use { arena ->
            val keymap = arena.allocate(X11_KEYMAP_SIZE)
            val result = handle.invokeExact(MemorySegment.ofAddress(displayPtr), keymap) as Int
            if (result == 0) {
                null
            } else {
                x11ModifierStateFromPressedKeycodes(x11PressedKeycodesFromKeymap(keymap))
            }
        }
    } catch (_: Throwable) {
        null
    }
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
            textWithAllModifiers = mappedCode?.defaultText(),
            keyWithoutModifiers = mappedCode?.defaultText(),
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
internal fun x11KeycodeToKeyCode(keycode: Int): KeyCode? = keysymToKeyCode(keycodeToKeysym(keycode))

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
    xdnd: XdndAtoms?,
) {
    val eventType = eventBuf.get(ValueLayout.JAVA_INT, XEVENT_TYPE_OFFSET)
    val windowXid = x11EventWindowXid(eventBuf, eventType)
    val windowId = WindowId(windowXid)

    when (eventType) {

        // ── Exposure (redraw) ─────────────────────────────────────────────────
        Expose -> {
            handler.windowEvent(loop, windowId, WindowEvent.RedrawRequested)
        }

        // ── Resize ────────────────────────────────────────────────────────────
        ConfigureNotify -> {
            val isSynthetic = eventBuf.get(ValueLayout.JAVA_INT, XCONFIGURE_SEND_EVENT_OFFSET) != 0
            val x = eventBuf.get(ValueLayout.JAVA_INT, XCONFIGURE_X_OFFSET)
            val y = eventBuf.get(ValueLayout.JAVA_INT, XCONFIGURE_Y_OFFSET)
            val width  = eventBuf.get(ValueLayout.JAVA_INT, XCONFIGURE_WIDTH_OFFSET)
            val height = eventBuf.get(ValueLayout.JAVA_INT, XCONFIGURE_HEIGHT_OFFSET)
            val window = loop.windows[windowXid] ?: return
            val changes = window.onConfigureNotify(
                width = width,
                height = height,
                position = PhysicalPosition(x, y),
                positionIsRootRelative = isSynthetic,
            )
            if (changes.sizeChanged && width > 0 && height > 0) {
                handler.windowEvent(loop, windowId, WindowEvent.Resized(PhysicalSize(width, height)))
            }
            changes.movedPosition?.let { position ->
                handler.windowEvent(loop, windowId, WindowEvent.Moved(position))
            }
        }

        // ── Keyboard ──────────────────────────────────────────────────────────
        KeyPress -> {
            val window = loop.windows[windowXid]
            if (window != null) {
                val filterEvent = xFilterEvent
                if (filterEvent != null) {
                    try {
                        val consumed = filterEvent.invokeExact(eventBuf, windowXid) as Int
                        if (consumed != 0) {
                            window.drainImeEvents(handler, loop, windowId)
                            return
                        }
                    } catch (_: Throwable) {}
                }
            }
            val keycode = eventBuf.get(ValueLayout.JAVA_INT, XKEY_KEYCODE_OFFSET)
            val state   = eventBuf.get(ValueLayout.JAVA_INT, XKEY_STATE_OFFSET)
            val keysym  = keycodeToKeysym(keycode)
            val mappedCode = keysymToKeyCode(keysym)
            val modifierState = loop.keyboardModifierTracker.modifierStateFor(mappedCode, KeyState.Pressed)
            loop.keyboardModifierTracker.modifiersChangedIfNeeded(modifierState)?.let { handler.windowEvent(loop, windowId, it) }
            val mods = modifierState?.logical ?: x11StateToModifiers(state)
            val repeat = X11LiveRepeatTracker.update(keycode, KeyState.Pressed)
            handler.windowEvent(loop, windowId, x11KeyInput(keycode, keysym, mappedCode, KeyState.Pressed, mods, repeat))
        }

        KeyRelease -> {
            val keycode = eventBuf.get(ValueLayout.JAVA_INT, XKEY_KEYCODE_OFFSET)
            val state   = eventBuf.get(ValueLayout.JAVA_INT, XKEY_STATE_OFFSET)
            val keysym  = keycodeToKeysym(keycode)
            val mappedCode = keysymToKeyCode(keysym)
            val modifierState = loop.keyboardModifierTracker.modifierStateFor(mappedCode, KeyState.Released)
            loop.keyboardModifierTracker.modifiersChangedIfNeeded(modifierState)?.let { handler.windowEvent(loop, windowId, it) }
            val mods = modifierState?.logical ?: x11StateToModifiers(state)
            X11LiveRepeatTracker.update(keycode, KeyState.Released)
            handler.windowEvent(loop, windowId, x11KeyInput(keycode, keysym, mappedCode, KeyState.Released, mods))
        }

        // ── Mouse buttons ─────────────────────────────────────────────────────
        ButtonPress -> {
            val button = eventBuf.get(ValueLayout.JAVA_INT, XBUTTON_BUTTON_OFFSET)
            val position = xButtonPosition(eventBuf)
            if (button == X11_BUTTON4 || button == X11_BUTTON5) {
                val state = eventBuf.get(ValueLayout.JAVA_INT, XKEY_STATE_OFFSET)
                if (state and X11_CONTROL_MASK != 0) {
                    val delta = if (button == X11_BUTTON4) 1.0 else -1.0
                    handler.windowEvent(loop, windowId,
                        WindowEvent.PinchGesture(null, delta, TouchPhase.Moved))
                    return
                }
            }
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

        FocusIn -> {
            val window = loop.windows[windowXid] ?: return
            queryX11ModifierState(loop.displayPtr)
                ?.let(loop.keyboardModifierTracker::initializeIfNeeded)
                ?.let { handler.windowEvent(loop, windowId, it) }
            if (window.onFocusChanged(true)) {
                handler.windowEvent(loop, windowId, WindowEvent.Focused(gained = true))
            }
        }

        FocusOut -> {
            val window = loop.windows[windowXid] ?: return
            X11LiveRepeatTracker.reset()
            loop.keyboardModifierTracker.resetIfNeeded()?.let { handler.windowEvent(loop, windowId, it) }
            if (window.onFocusChanged(false)) {
                handler.windowEvent(loop, windowId, WindowEvent.Focused(gained = false))
            }
        }

        VisibilityNotify -> {
            val window = loop.windows[windowXid] ?: return
            val state = eventBuf.get(ValueLayout.JAVA_INT, XVISIBILITY_STATE_OFFSET)
            handler.windowEvent(loop, windowId, WindowEvent.Occluded(state == X11_VISIBILITY_FULLY_OBSCURED))
            window.onVisibilityNotify()
        }

        // ── Window destruction ────────────────────────────────────────────────
        DestroyNotify -> {
            handler.windowEvent(loop, windowId, WindowEvent.Destroyed)
            loop.windows.remove(windowXid)
            loop.dragSourceWindows.remove(windowXid)
            loop.pendingXdndDrops.removeAll { it.targetWindow == windowXid }
        }

        // ── ClientMessage (WM close + wakeUp + Xdnd) ─────────────────────────
        ClientMessage -> {
            val messageType = eventBuf.get(ValueLayout.JAVA_LONG, XCLIENT_MESSAGE_TYPE_OFFSET)
            val data0 = eventBuf.get(ValueLayout.JAVA_LONG, XCLIENT_DATA_L0_OFFSET)
            if (messageType == wmDeleteWindow || data0 == wmDeleteWindow) {
                handler.windowEvent(loop, windowId, WindowEvent.CloseRequested)
            } else if (xdnd != null) {
                handleXdndClientMessage(eventBuf, loop, handler, windowXid, windowId, xdnd)
            }
        }

        // ── SelectionNotify (Xdnd drop data) ─────────────────────────────────
        SelectionNotify -> {
            if (xdnd != null) {
                handleSelectionNotify(eventBuf, loop, handler, windowXid, windowId, xdnd)
            }
        }
    }
}

private fun handleXdndClientMessage(
    eventBuf: MemorySegment,
    loop: X11EventLoop,
    handler: ApplicationHandler,
    windowXid: Long,
    windowId: WindowId,
    xdnd: XdndAtoms,
) {
    val messageType = eventBuf.get(ValueLayout.JAVA_LONG, XCLIENT_MESSAGE_TYPE_OFFSET)
    val display = MemorySegment.ofAddress(loop.displayPtr)

    when (messageType) {
        xdnd.xdndEnter -> {
            val sourceWindow = eventBuf.get(ValueLayout.JAVA_LONG, XCLIENT_DATA_L0_OFFSET)
            val flags = eventBuf.get(ValueLayout.JAVA_LONG, XCLIENT_DATA_L1_OFFSET)
            loop.dragSourceWindows[windowXid] = sourceWindow
            val types = mutableListOf<Long>()
            val hasMoreTypes = (flags and 1L) != 0L
            if (hasMoreTypes) {
                readXdndTypeList(display, sourceWindow, types)
            } else {
                val t0 = eventBuf.get(ValueLayout.JAVA_LONG, XCLIENT_DATA_L2_OFFSET)
                val t1 = eventBuf.get(ValueLayout.JAVA_LONG, XCLIENT_DATA_L3_OFFSET)
                val t2 = eventBuf.get(ValueLayout.JAVA_LONG, XCLIENT_DATA_L4_OFFSET)
                if (t0 != 0L) types.add(t0)
                if (t1 != 0L) types.add(t1)
                if (t2 != 0L) types.add(t2)
            }
            loop.pendingDropRequests.remove(windowXid)
            handler.windowEvent(loop, windowId, WindowEvent.DragEntered(
                position = PhysicalPosition(0.0, 0.0),
                paths = if (types.contains(xdnd.textUriList)) listOf("text/uri-list") else emptyList(),
            ))
        }

        xdnd.xdndPosition -> {
            val sourceWindow = eventBuf.get(ValueLayout.JAVA_LONG, XCLIENT_DATA_L0_OFFSET)
            val packedPos = eventBuf.get(ValueLayout.JAVA_LONG, XCLIENT_DATA_L2_OFFSET)
            val rootX = (packedPos shr 16).toInt()
            val rootY = (packedPos and 0xFFFFL).toInt()
            val localPos = rootToWindowPosition(display, windowXid, rootX, rootY, loop.screen)
            loop.dragSourceWindows[windowXid] = sourceWindow
            val displayMs = MemorySegment.ofAddress(loop.displayPtr)
            X11DragAndDrop.sendXdndStatus(displayMs, windowXid, sourceWindow, accept = true, rootX, rootY)
            handler.windowEvent(loop, windowId, WindowEvent.DragMoved(localPos))
        }

        xdnd.xdndLeave -> {
            val sourceWindow = eventBuf.get(ValueLayout.JAVA_LONG, XCLIENT_DATA_L0_OFFSET)
            loop.dragSourceWindows.remove(windowXid)
            loop.pendingDropRequests.remove(windowXid)
            handler.windowEvent(loop, windowId, WindowEvent.DragLeft)
        }

        xdnd.xdndDrop -> {
            val sourceWindow = eventBuf.get(ValueLayout.JAVA_LONG, XCLIENT_DATA_L0_OFFSET)
            val packedPos = eventBuf.get(ValueLayout.JAVA_LONG, XCLIENT_DATA_L2_OFFSET)
            val rootX = (packedPos shr 16).toInt()
            val rootY = (packedPos and 0xFFFFL).toInt()
            val localPos = rootToWindowPosition(display, windowXid, rootX, rootY, loop.screen)
            val displayMs = MemorySegment.ofAddress(loop.displayPtr)
            val ok = X11DragAndDrop.requestDropData(
                display = displayMs,
                targetWindow = windowXid,
                xdndSelectionAtom = xdnd.xdndSelection,
                targetAtom = xdnd.textUriList,
                time = 0L,
            )
            if (ok) {
                loop.pendingXdndDrops.add(PendingXdndDrop(
                    targetWindow = windowXid,
                    sourceWindow = sourceWindow,
                    position = localPos,
                ))
            } else {
                loop.dragSourceWindows.remove(windowXid)
                handler.windowEvent(loop, windowId, WindowEvent.DragDropped(localPos, emptyList()))
            }
        }
    }
}

private fun handleSelectionNotify(
    eventBuf: MemorySegment,
    loop: X11EventLoop,
    handler: ApplicationHandler,
    windowXid: Long,
    windowId: WindowId,
    xdnd: XdndAtoms,
) {
    val selection = eventBuf.get(ValueLayout.JAVA_LONG, XSELECTION_SELECTION_OFFSET)
    if (selection != xdnd.xdndSelection) return
    val drop = loop.pendingXdndDrops.poll() ?: return
    val display = MemorySegment.ofAddress(loop.displayPtr)
    val property = eventBuf.get(ValueLayout.JAVA_LONG, XSELECTION_PROPERTY_OFFSET)
    val paths: List<String>
    if (property != 0L) {
        val data = X11DragAndDrop.readSelectionProperty(
            getProperty = xGetWindowProperty,
            free = xFree,
            display = display,
            window = windowXid,
            property = property,
        )
        paths = if (data != null) X11DragAndDrop.parseUriList(data) else emptyList()
    } else {
        paths = emptyList()
    }
    X11DragAndDrop.sendXdndFinished(display, windowXid, drop.sourceWindow, accept = true)
    loop.dragSourceWindows.remove(windowXid)
    handler.windowEvent(loop, windowId, WindowEvent.DragDropped(drop.position, paths))
}

private fun readXdndTypeList(display: MemorySegment, sourceWindow: Long, types: MutableList<Long>) {
    val getProperty = xGetWindowProperty ?: return
    val xdndTypeListAtom = x11DragAndDropAtom(display, "XdndTypeList")
    if (xdndTypeListAtom == 0L) return
    try {
        Arena.ofConfined().use { arena ->
            val actualType = arena.allocate(ValueLayout.JAVA_LONG)
            val actualFormat = arena.allocate(ValueLayout.JAVA_INT)
            val nitems = arena.allocate(ValueLayout.JAVA_LONG)
            val bytesAfter = arena.allocate(ValueLayout.JAVA_LONG)
            val propReturn = arena.allocate(ValueLayout.ADDRESS)
            val status = getProperty.invokeExact(
                display, sourceWindow,
                xdndTypeListAtom, 0L, 1024L, 0,
                0L,
                actualType, actualFormat, nitems, bytesAfter, propReturn,
            ) as Int
            if (status != 0) return
            val ptr = propReturn.get(ValueLayout.ADDRESS, 0L)
            if (ptr == MemorySegment.NULL || ptr.address() == 0L) return
            try {
                val count = nitems.get(ValueLayout.JAVA_LONG, 0L)
                for (i in 0 until count) {
                    types.add(ptr.getAtIndex(ValueLayout.JAVA_LONG, i))
                }
            } finally {
                xFree?.invokeExact(ptr) as? Int
            }
        }
    } catch (_: Throwable) {}
}

private fun rootToWindowPosition(
    display: MemorySegment,
    window: Long,
    rootX: Int,
    rootY: Int,
    screen: Int = 0,
): PhysicalPosition<Double> {
    val translate = xTranslateCoordinates ?: return PhysicalPosition(rootX.toDouble(), rootY.toDouble())
    val rootHandle = xRootWindow ?: return PhysicalPosition(rootX.toDouble(), rootY.toDouble())
    return try {
        Arena.ofConfined().use { arena ->
            val destX = arena.allocate(ValueLayout.JAVA_INT)
            val destY = arena.allocate(ValueLayout.JAVA_INT)
            val child = arena.allocate(ValueLayout.JAVA_LONG)
            val rootWindow = rootHandle.invokeExact(display, screen) as Long
            if (rootWindow == 0L) return@use PhysicalPosition(rootX.toDouble(), rootY.toDouble())
            val ok = translate.invokeExact(
                display, rootWindow, window,
                rootX, rootY,
                destX, destY, child,
            ) as Int
            if (ok != 0) {
                PhysicalPosition(
                    destX.get(ValueLayout.JAVA_INT, 0L).toDouble(),
                    destY.get(ValueLayout.JAVA_INT, 0L).toDouble(),
                )
            } else {
                PhysicalPosition(rootX.toDouble(), rootY.toDouble())
            }
        }
    } catch (_: Throwable) {
        PhysicalPosition(rootX.toDouble(), rootY.toDouble())
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

internal fun x11EventWindowXid(eventBuf: MemorySegment, eventType: Int): Long {
    val offset = when (eventType) {
        ConfigureNotify -> XCONFIGURE_WINDOW_OFFSET
        DestroyNotify -> XDESTROY_WINDOW_OFFSET
        else -> XANY_WINDOW_OFFSET
    }
    return eventBuf.get(ValueLayout.JAVA_LONG, offset)
}

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
    xdnd: XdndAtoms?,
): StartCause {
    xFlush?.invokeExact(displaySeg) as? Int

    val pendingHandle = xPending
    val nextHandle    = xNextEvent

    if (pendingHandle != null && nextHandle != null) {
        while (!loop.isExiting) {
            val pending = pendingHandle.invokeExact(displaySeg) as Int
            if (pending <= 0) break
            nextHandle.invokeExact(displaySeg, eventBuf) as Int
            dispatchEvent(eventBuf, loop, handler, wmDeleteWindow, xdnd)
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
    xdnd: XdndAtoms?,
): StartCause {
    val nextHandle = xNextEvent ?: return StartCause.WaitCancelled()

    // XNextEvent blocks until an event arrives
    nextHandle.invokeExact(displaySeg, eventBuf) as Int
    dispatchEvent(eventBuf, loop, handler, wmDeleteWindow, xdnd)

    // Drain the additional events already available
    val pendingHandle = xPending
    if (pendingHandle != null) {
        while (!loop.isExiting) {
            val pending = pendingHandle.invokeExact(displaySeg) as Int
            if (pending <= 0) break
            nextHandle.invokeExact(displaySeg, eventBuf) as Int
            dispatchEvent(eventBuf, loop, handler, wmDeleteWindow, xdnd)
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
    xdnd: XdndAtoms?,
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
                dispatchEvent(eventBuf, loop, handler, wmDeleteWindow, xdnd)
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
        "X11EventLoop.runApp() can only be called once per process. An X11 event loop is already active."
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

        // Intern Xdnd atoms needed for drag-and-drop
        val xdnd: XdndAtoms? = Arena.ofConfined().use { arena ->
            val displayMs = MemorySegment.ofAddress(displayPtr)
            val enter = x11DragAndDropAtom(displayMs, "XdndEnter")
            val position = x11DragAndDropAtom(displayMs, "XdndPosition")
            val leave = x11DragAndDropAtom(displayMs, "XdndLeave")
            val drop = x11DragAndDropAtom(displayMs, "XdndDrop")
            val selection = x11DragAndDropAtom(displayMs, "XdndSelection")
            val textUriList = x11DragAndDropAtom(displayMs, "text/uri-list")
            if (enter != 0L && position != 0L && leave != 0L && drop != 0L && selection != 0L && textUriList != 0L) {
                XdndAtoms(enter, position, leave, drop, selection, textUriList)
            } else null
        }

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
                    is ControlFlow.Poll      -> dispatchPoll(displaySeg, eventBuf, loop, handler, wmDeleteWindow, xdnd)
                    is ControlFlow.Wait      -> dispatchWait(displaySeg, eventBuf, loop, handler, wmDeleteWindow, xdnd)
                    is ControlFlow.WaitUntil -> dispatchWaitUntil(displaySeg, eventBuf, loop, handler, cf, wmDeleteWindow, xdnd)
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
