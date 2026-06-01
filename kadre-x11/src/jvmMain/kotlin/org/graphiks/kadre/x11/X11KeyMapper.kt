/**
 * X11 mapper for keyboard events (XKeyEvent).
 *
 * Converts raw XKeyEvent events (KeyPress / KeyRelease) into
 * [org.graphiks.kadre.core.WindowEvent.KeyboardInput].
 *
 * ## Struct XKeyEvent (Linux 64-bit)
 * ```
 *  0 : type          (int,  4)
 *  8 : display       (ptr,  8)
 * 16 : window        (long, 8)
 * 24 : root          (long, 8)
 * 32 : subwindow     (long, 8)
 * 40 : time          (long, 8)
 * 48 : x             (int,  4)
 * 52 : y             (int,  4)
 * 56 : x_root        (int,  4)
 * 60 : y_root        (int,  4)
 * 64 : state         (uint, 4)  — modifier mask
 * 68 : keycode       (uint, 4)
 * 72 : same_screen   (int,  4)
 * ```
 *
 * ## X11 modifier masks
 * - ShiftMask   = 0x01
 * - ControlMask = 0x04
 * - Mod1Mask    = 0x08 (Alt)
 * - Mod4Mask    = 0x40 (Super / Meta)
 *
 * ## Keysyms
 * The X11 keysyms for lowercase letters are simply the ASCII value
 * (0x61 = 'a', …, 0x7A = 'z'). Uppercase letters: 0x41–0x5A.
 * Digits: 0x30–0x39. Special keys start at 0xFF00.
 *
 * X11KeyMapper.
 */
package org.graphiks.kadre.x11

import org.graphiks.kadre.core.Key
import org.graphiks.kadre.core.KeyState
import org.graphiks.kadre.core.Modifiers
import org.graphiks.kadre.core.WindowEvent
import java.lang.foreign.MemorySegment
import java.lang.foreign.ValueLayout

// ── XKeyEvent offsets ─────────────────────────────────────────────────────────

private const val OFFSET_STATE: Long = 64L
private const val OFFSET_KEYCODE: Long = 68L

// ── X11 modifier masks ────────────────────────────────────────────────────────

private const val SHIFT_MASK: Int   = 0x01
private const val CONTROL_MASK: Int = 0x04
private const val MOD1_MASK: Int    = 0x08   // Alt
private const val MOD4_MASK: Int    = 0x40   // Super / Meta (Win)

// ── Keysym → Key conversion table ────────────────────────────────────────────

/**
 * Keysym → [Key] mapping table.
 *
 * The X11 keysyms are defined in <X11/keysymdef.h>.
 * - Lowercase letters : 0x61–0x7A (same value as ASCII)
 * - Uppercase letters : 0x41–0x5A (same value as ASCII)
 * - Digits            : 0x30–0x39 (same value as ASCII)
 * - Special keys      : 0xFF00+ (BackSpace, Tab, Return, Escape, …)
 * - Function keys     : 0xFFBE–0xFFC9 (F1–F12)
 * - Navigation keys   : 0xFF51–0xFF54 (Left, Up, Right, Down)
 * - Modifiers         : 0xFFE1–0xFFEA (Shift_L, Shift_R, Control_L, …)
 */
internal val KEYSYM_TABLE: Map<Int, Key> = buildMap {
    // ── Letters (lowercase 0x61–0x7A = ASCII 'a'–'z') ────────────────────────
    put(0x61, Key.A); put(0x62, Key.B); put(0x63, Key.C); put(0x64, Key.D)
    put(0x65, Key.E); put(0x66, Key.F); put(0x67, Key.G); put(0x68, Key.H)
    put(0x69, Key.I); put(0x6A, Key.J); put(0x6B, Key.K); put(0x6C, Key.L)
    put(0x6D, Key.M); put(0x6E, Key.N); put(0x6F, Key.O); put(0x70, Key.P)
    put(0x71, Key.Q); put(0x72, Key.R); put(0x73, Key.S); put(0x74, Key.T)
    put(0x75, Key.U); put(0x76, Key.V); put(0x77, Key.W); put(0x78, Key.X)
    put(0x79, Key.Y); put(0x7A, Key.Z)

    // ── Letters (uppercase 0x41–0x5A = ASCII 'A'–'Z') ────────────────────────
    put(0x41, Key.A); put(0x42, Key.B); put(0x43, Key.C); put(0x44, Key.D)
    put(0x45, Key.E); put(0x46, Key.F); put(0x47, Key.G); put(0x48, Key.H)
    put(0x49, Key.I); put(0x4A, Key.J); put(0x4B, Key.K); put(0x4C, Key.L)
    put(0x4D, Key.M); put(0x4E, Key.N); put(0x4F, Key.O); put(0x50, Key.P)
    put(0x51, Key.Q); put(0x52, Key.R); put(0x53, Key.S); put(0x54, Key.T)
    put(0x55, Key.U); put(0x56, Key.V); put(0x57, Key.W); put(0x58, Key.X)
    put(0x59, Key.Y); put(0x5A, Key.Z)

    // ── Digits (0x30–0x39 = ASCII '0'–'9') ───────────────────────────────────
    put(0x30, Key.Digit0); put(0x31, Key.Digit1); put(0x32, Key.Digit2)
    put(0x33, Key.Digit3); put(0x34, Key.Digit4); put(0x35, Key.Digit5)
    put(0x36, Key.Digit6); put(0x37, Key.Digit7); put(0x38, Key.Digit8)
    put(0x39, Key.Digit9)

    // ── Special keys ──────────────────────────────────────────────────────────
    put(0xFF08, Key.Backspace)   // XK_BackSpace
    put(0xFF09, Key.Tab)         // XK_Tab
    put(0xFF0D, Key.Enter)       // XK_Return
    put(0xFF1B, Key.Escape)      // XK_Escape
    put(0x0020, Key.Space)       // XK_space

    // ── Navigation keys ───────────────────────────────────────────────────────
    put(0xFF51, Key.ArrowLeft)   // XK_Left
    put(0xFF52, Key.ArrowUp)     // XK_Up
    put(0xFF53, Key.ArrowRight)  // XK_Right
    put(0xFF54, Key.ArrowDown)   // XK_Down

    // ── Function keys F1–F12 ──────────────────────────────────────────────────
    put(0xFFBE, Key.F1);  put(0xFFBF, Key.F2);  put(0xFFC0, Key.F3)
    put(0xFFC1, Key.F4);  put(0xFFC2, Key.F5);  put(0xFFC3, Key.F6)
    put(0xFFC4, Key.F7);  put(0xFFC5, Key.F8);  put(0xFFC6, Key.F9)
    put(0xFFC7, Key.F10); put(0xFFC8, Key.F11); put(0xFFC9, Key.F12)

    // ── Modifiers ─────────────────────────────────────────────────────────────
    put(0xFFE1, Key.ShiftLeft)    // XK_Shift_L
    put(0xFFE2, Key.ShiftRight)   // XK_Shift_R
    put(0xFFE3, Key.ControlLeft)  // XK_Control_L
    put(0xFFE4, Key.ControlRight) // XK_Control_R
    put(0xFFE9, Key.AltLeft)      // XK_Alt_L
    put(0xFFEA, Key.AltRight)     // XK_Alt_R
    put(0xFFEB, Key.MetaLeft)     // XK_Super_L (left Win)
    put(0xFFEC, Key.MetaRight)    // XK_Super_R (right Win)
}

/**
 * Converts the state (X11 modifier mask) into kadre [Modifiers].
 *
 * @param state Value of XKeyEvent's `state` field (unsigned int, read as Int).
 * @return [Modifiers] with the shift, ctrl, alt, meta bits set.
 */
internal fun stateToModifiers(state: Int): Modifiers {
    var bits = 0
    if (state and SHIFT_MASK   != 0) bits = bits or Modifiers.SHIFT.bits
    if (state and CONTROL_MASK != 0) bits = bits or Modifiers.CTRL.bits
    if (state and MOD1_MASK    != 0) bits = bits or Modifiers.ALT.bits
    if (state and MOD4_MASK    != 0) bits = bits or Modifiers.META.bits
    return Modifiers(bits)
}

/**
 * Stateless mapper for X11 keyboard events.
 *
 * ### Repeat detection
 * When XkbSetDetectableAutoRepeat is enabled (which is done in
 * [X11KeyMapper.enableDetectableAutoRepeat]), automatic key repeats
 * generate only additional KeyPress events, without an intermediate
 * KeyRelease. We can thus detect that a key is repeated by testing
 * whether the keycode was already pressed at the previous KeyPress.
 *
 * This object maintains a set of currently pressed keycodes to
 * perform this detection.
 */
object X11KeyMapper {

    /** Set of currently pressed keycodes (for repeat detection). */
    private val pressedKeys: MutableSet<Int> = mutableSetOf()

    /**
     * Enables Xkb's "Detectable AutoRepeat" mode.
     *
     * In normal mode, X11 simulates automatic repeats by sending consecutive
     * KeyRelease / KeyPress pairs. With this mode enabled, only an additional
     * KeyPress is emitted, which allows the application to know
     * that it is a repeat (the keycode was already in [pressedKeys]).
     *
     * @param displayPtr Address of the Display* (Long, opaque).
     */
    fun enableDetectableAutoRepeat(displayPtr: Long) {
        val handle = xkbSetDetectableAutoRepeat ?: return
        val display = MemorySegment.ofAddress(displayPtr)
        try {
            // NULL for supported_rtrn: we don't need the boolean return value
            handle.invokeExact(display, 1, MemorySegment.NULL) as Int
        } catch (_: Throwable) {
            // No action — the Xkb extension is not available on this server.
        }
    }

    /**
     * Resets the internal state (set of pressed keys).
     *
     * Useful on focus loss to avoid false repeats.
     */
    fun resetState() {
        pressedKeys.clear()
    }

    /**
     * Converts an XKeyEvent [MemorySegment] into a [WindowEvent.KeyboardInput].
     *
     * @param eventSegment 96-byte segment containing the XEvent.
     * @param eventType    X11 event type (2 = KeyPress, 3 = KeyRelease).
     * @param keysym       Keysym computed beforehand (e.g. via XLookupString);
     *                     if 0, the keycode is used directly against the table.
     * @return [WindowEvent.KeyboardInput] or null if the keysym is unknown.
     */
    fun fromXEvent(
        eventSegment: MemorySegment,
        eventType: Int,
        keysym: Int = 0,
    ): WindowEvent.KeyboardInput? {
        val state   = eventSegment.get(ValueLayout.JAVA_INT, OFFSET_STATE)
        val keycode = eventSegment.get(ValueLayout.JAVA_INT, OFFSET_KEYCODE) and 0xFFFF

        val isPressed = eventType == KeyPress

        // Repeat detection: if the keycode was already pressed at the time
        // of a KeyPress, it is an automatic repeat.
        val isRepeat = isPressed && pressedKeys.contains(keycode)

        // Update the internal state
        if (isPressed) {
            pressedKeys.add(keycode)
        } else {
            pressedKeys.remove(keycode)
        }

        // Keysym resolution: prefer the keysym provided by XLookupString,
        // otherwise fall back to the table (not available here without XLookupString).
        val ks = if (keysym != 0) keysym else 0

        val key = KEYSYM_TABLE[ks] ?: Key.Unknown
        val keyState = if (isPressed) KeyState.Pressed else KeyState.Released
        val modifiers = stateToModifiers(state)

        // R4: scanCode = X11 hardware keycode (independent of layout)
        // R4: text = null (TODO: XLookupString / Xutf8LookupString not yet wired as FFM binding)
        return WindowEvent.KeyboardInput(
            deviceId = null,
            key = key,
            state = keyState,
            modifiers = modifiers,
            isRepeat = isRepeat,
            isSynthetic = false,
            text = null,          // TODO(R4-x11-text): wire XLookupString / Xutf8LookupString
            scanCode = keycode,   // X11 hardware keycode is the scan code equivalent
        )
    }
}
