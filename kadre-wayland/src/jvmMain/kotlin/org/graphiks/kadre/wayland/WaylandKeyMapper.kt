/**
 * Mapper from Linux evdev key codes to kadre-core keyboard events.
 *
 * Wayland sends raw Linux keycodes via wl_keyboard.key. This static table keeps
 * physical-key support independent from libxkbcommon; layout-aware logical text
 * can be layered on later.
 */
package org.graphiks.kadre.wayland

import org.graphiks.kadre.core.KeyCode
import org.graphiks.kadre.core.KeyEvent
import org.graphiks.kadre.core.KeyPlatform
import org.graphiks.kadre.core.KeyState
import org.graphiks.kadre.core.KeyboardModifiers
import org.graphiks.kadre.core.LogicalKey
import org.graphiks.kadre.core.NativeKeyInfo
import org.graphiks.kadre.core.NativeKeyCode
import org.graphiks.kadre.core.NativeLogicalKey
import org.graphiks.kadre.core.PhysicalKey
import org.graphiks.kadre.core.WindowEvent
import org.graphiks.kadre.core.defaultLogicalKey
import org.graphiks.kadre.core.defaultText

internal const val WL_KEY_RELEASED: Int = 0
internal const val WL_KEY_PRESSED: Int = 1
internal const val WL_KEY_REPEATED: Int = 2

private val KEYCODE_TABLE: Map<Int, KeyCode> = mapOf(
    16 to KeyCode.KeyQ,
    17 to KeyCode.KeyW,
    18 to KeyCode.KeyE,
    19 to KeyCode.KeyR,
    20 to KeyCode.KeyT,
    21 to KeyCode.KeyY,
    22 to KeyCode.KeyU,
    23 to KeyCode.KeyI,
    24 to KeyCode.KeyO,
    25 to KeyCode.KeyP,
    30 to KeyCode.KeyA,
    31 to KeyCode.KeyS,
    32 to KeyCode.KeyD,
    33 to KeyCode.KeyF,
    34 to KeyCode.KeyG,
    35 to KeyCode.KeyH,
    36 to KeyCode.KeyJ,
    37 to KeyCode.KeyK,
    38 to KeyCode.KeyL,
    44 to KeyCode.KeyZ,
    45 to KeyCode.KeyX,
    46 to KeyCode.KeyC,
    47 to KeyCode.KeyV,
    48 to KeyCode.KeyB,
    49 to KeyCode.KeyN,
    50 to KeyCode.KeyM,
    2 to KeyCode.Digit1,
    3 to KeyCode.Digit2,
    4 to KeyCode.Digit3,
    5 to KeyCode.Digit4,
    6 to KeyCode.Digit5,
    7 to KeyCode.Digit6,
    8 to KeyCode.Digit7,
    9 to KeyCode.Digit8,
    10 to KeyCode.Digit9,
    11 to KeyCode.Digit0,
    1 to KeyCode.Escape,
    14 to KeyCode.Backspace,
    15 to KeyCode.Tab,
    28 to KeyCode.Enter,
    57 to KeyCode.Space,
    59 to KeyCode.F1,
    60 to KeyCode.F2,
    61 to KeyCode.F3,
    62 to KeyCode.F4,
    63 to KeyCode.F5,
    64 to KeyCode.F6,
    65 to KeyCode.F7,
    66 to KeyCode.F8,
    67 to KeyCode.F9,
    68 to KeyCode.F10,
    87 to KeyCode.F11,
    88 to KeyCode.F12,
    103 to KeyCode.ArrowUp,
    105 to KeyCode.ArrowLeft,
    106 to KeyCode.ArrowRight,
    108 to KeyCode.ArrowDown,
    102 to KeyCode.Home,
    107 to KeyCode.End,
    104 to KeyCode.PageUp,
    109 to KeyCode.PageDown,
    42 to KeyCode.ShiftLeft,
    54 to KeyCode.ShiftRight,
    29 to KeyCode.ControlLeft,
    97 to KeyCode.ControlRight,
    56 to KeyCode.AltLeft,
    100 to KeyCode.AltRight,
    125 to KeyCode.MetaLeft,
    126 to KeyCode.MetaRight,
)

fun linuxKeycodeToKeyCode(keycode: Int): KeyCode? = KEYCODE_TABLE[keycode]

fun linuxKeycodeToPhysicalKey(keycode: Int): PhysicalKey = linuxKeycodeToKeyCode(keycode)?.let(PhysicalKey::Code)
    ?: PhysicalKey.Native(NativeKeyCode.Wayland(keycode.toLong()))

fun waylandKeyStateToKeyState(state: Int): KeyState = when (state) {
    WL_KEY_RELEASED -> KeyState.Released
    else -> KeyState.Pressed
}

fun mapWaylandKeyEvent(
    keycode: Int,
    state: Int,
    modifiers: KeyboardModifiers = KeyboardModifiers.NONE,
): WindowEvent.KeyInput {
    val mappedCode = linuxKeycodeToKeyCode(keycode)
    val native = NativeKeyInfo(
        platform = KeyPlatform.Wayland,
        scanCode = keycode.toLong(),
        nativeCode = NativeKeyCode.Wayland(keycode.toLong()),
        nativeKey = NativeLogicalKey.Wayland(keysym = null),
    )
    val logicalKey = mappedCode?.defaultLogicalKey() ?: LogicalKey.Unidentified(native)
    return WindowEvent.KeyInput(
        event = KeyEvent(
            physicalKey = linuxKeycodeToPhysicalKey(keycode),
            logicalKey = logicalKey,
            state = waylandKeyStateToKeyState(state),
            modifiers = modifiers,
            repeat = state == WL_KEY_REPEATED,
            text = mappedCode?.defaultText(),
            keyWithoutModifiers = logicalKey,
            native = native,
        ),
        deviceId = null,
    )
}

fun mapWaylandKeyboardFocused(focused: Boolean): WindowEvent.Focused = WindowEvent.Focused(focused)
