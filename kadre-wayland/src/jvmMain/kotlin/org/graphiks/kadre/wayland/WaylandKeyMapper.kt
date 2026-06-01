/**
 * Mapper from Linux evdev key codes to kadre-core logical keys [Key].
 *
 * Wayland sends raw Linux keycodes via wl_keyboard.key (the "key" event).
 * These keycodes correspond to the evdev codes defined in:
 *   https://github.com/torvalds/linux/blob/master/include/uapi/linux/input-event-codes.h
 *
 * This implementation uses a static table for the conversion, without
 * a dependency on libxkbcommon (full keyboard layout handling is
 * planned for later).
 *
 * ## Wayland key states
 *  - WL_KEYBOARD_KEY_STATE_RELEASED = 0
 *  - WL_KEYBOARD_KEY_STATE_PRESSED  = 1
 * Key repeat is sent with state = 2 (a value not standardized
 * in the wl_keyboard protocol, handled by the compositor or the client).
 *
 * WaylandKeyMapper — Linux evdev keycodes → Key.
 */
package org.graphiks.kadre.wayland

import org.graphiks.kadre.core.Key
import org.graphiks.kadre.core.KeyState
import org.graphiks.kadre.core.Modifiers
import org.graphiks.kadre.core.WindowEvent

// ---------------------------------------------------------------------------
// wl_keyboard key state constants
// ---------------------------------------------------------------------------

/** wl_keyboard_key_state: key released. */
internal const val WL_KEY_RELEASED: Int = 0

/** wl_keyboard_key_state: key pressed. */
internal const val WL_KEY_PRESSED: Int = 1

/** Repeat state — sent by some compositors instead of WL_KEY_PRESSED. */
internal const val WL_KEY_REPEATED: Int = 2

// ---------------------------------------------------------------------------
// Table Linux evdev keycode → Key
// ---------------------------------------------------------------------------

/**
 * Mapping table from Linux evdev keycode → [Key].
 *
 * The keycodes are those defined in the Linux kernel's `input-event-codes.h`.
 * Missing entries return [Key.Unknown].
 */
private val KEYCODE_TABLE: Map<Int, Key> = mapOf(
    // ── Letters ──────────────────────────────────────────────────────────────
    16 to Key.Q,
    17 to Key.W,
    18 to Key.E,
    19 to Key.R,
    20 to Key.T,
    21 to Key.Y,
    22 to Key.U,
    23 to Key.I,
    24 to Key.O,
    25 to Key.P,
    30 to Key.A,
    31 to Key.S,
    32 to Key.D,
    33 to Key.F,
    34 to Key.G,
    35 to Key.H,
    36 to Key.J,
    37 to Key.K,
    38 to Key.L,
    44 to Key.Z,
    45 to Key.X,
    46 to Key.C,
    47 to Key.V,
    48 to Key.B,
    49 to Key.N,
    50 to Key.M,

    // ── Digits ───────────────────────────────────────────────────────────────
    2  to Key.Digit1,
    3  to Key.Digit2,
    4  to Key.Digit3,
    5  to Key.Digit4,
    6  to Key.Digit5,
    7  to Key.Digit6,
    8  to Key.Digit7,
    9  to Key.Digit8,
    10 to Key.Digit9,
    11 to Key.Digit0,

    // ── Special keys ─────────────────────────────────────────────────────────
    1  to Key.Escape,
    14 to Key.Backspace,
    15 to Key.Tab,
    28 to Key.Enter,
    57 to Key.Space,

    // ── Function keys ────────────────────────────────────────────────────────
    59 to Key.F1,
    60 to Key.F2,
    61 to Key.F3,
    62 to Key.F4,
    63 to Key.F5,
    64 to Key.F6,
    65 to Key.F7,
    66 to Key.F8,
    67 to Key.F9,
    68 to Key.F10,
    87 to Key.F11,
    88 to Key.F12,

    // ── Navigation ───────────────────────────────────────────────────────────
    103 to Key.ArrowUp,
    105 to Key.ArrowLeft,
    106 to Key.ArrowRight,
    108 to Key.ArrowDown,
    102 to Key.ArrowUp,    // KEY_HOME — no Home in the enum, fallback ArrowUp
    107 to Key.ArrowDown,  // KEY_END  — no End in the enum, fallback ArrowDown
    104 to Key.ArrowUp,    // KEY_PAGEUP
    109 to Key.ArrowDown,  // KEY_PAGEDOWN

    // ── Modifiers ────────────────────────────────────────────────────────────
    42  to Key.ShiftLeft,
    54  to Key.ShiftRight,
    29  to Key.ControlLeft,
    97  to Key.ControlRight,
    56  to Key.AltLeft,
    100 to Key.AltRight,
    125 to Key.MetaLeft,
    126 to Key.MetaRight,
)

// ---------------------------------------------------------------------------
// Conversion functions
// ---------------------------------------------------------------------------

/**
 * Converts a Linux evdev keycode into a logical key [Key].
 *
 * @param keycode Linux evdev code received in wl_keyboard.key.
 * @return The corresponding logical key, or [Key.Unknown] if unrecognized.
 */
fun linuxKeycodeToKey(keycode: Int): Key = KEYCODE_TABLE[keycode] ?: Key.Unknown

/**
 * Converts a wl_keyboard_key_state value into a kadre [KeyState].
 *
 * @param state Wayland state (0 = released, 1 = pressed, 2 = repeated).
 * @return [KeyState.Pressed] for pressed or repeated, [KeyState.Released] for released.
 */
fun waylandKeyStateToKeyState(state: Int): KeyState = when (state) {
    WL_KEY_RELEASED -> KeyState.Released
    else            -> KeyState.Pressed // WL_KEY_PRESSED and WL_KEY_REPEATED → Pressed
}

/**
 * Builds a [WindowEvent.KeyboardInput] from a wl_keyboard.key event.
 *
 * @param keycode   Linux evdev code received in wl_keyboard.key.
 * @param state     wl_keyboard_key_state value (0/1/2).
 * @param modifiers Modifiers active at the time of the event.
 * @return The corresponding keyboard event.
 */
fun mapWaylandKeyEvent(
    keycode: Int,
    state: Int,
    modifiers: Modifiers = Modifiers.NONE,
): WindowEvent.KeyboardInput = WindowEvent.KeyboardInput(
    deviceId  = null,
    key       = linuxKeycodeToKey(keycode),
    state     = waylandKeyStateToKeyState(state),
    modifiers = modifiers,
    isRepeat  = state == WL_KEY_REPEATED,
    text      = null,    // TODO(R4-wayland-text): wire xkb_state_key_get_utf8
    scanCode  = keycode, // Linux evdev keycode is the scan code equivalent
)

/**
 * Builds a [WindowEvent.Focused] from a wl_keyboard.enter or wl_keyboard.leave event.
 *
 * @param gained `true` for wl_keyboard.enter (focus gained), `false` for wl_keyboard.leave.
 * @return The corresponding focus event.
 */
fun mapWaylandKeyboardFocused(gained: Boolean): WindowEvent.Focused =
    WindowEvent.Focused(gained)
