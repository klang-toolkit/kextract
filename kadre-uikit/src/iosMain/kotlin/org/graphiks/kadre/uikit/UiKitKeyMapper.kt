/**
 * Mapping of iOS keyboard input to kadre physical key codes and modifiers.
 *
 * iOS surfaces hardware-keyboard and game-controller key events through
 * `UIResponder.pressesBegan/Ended` (iOS 13.4+). Each `UIPress` carries a
 * `UIKey` whose `keyCode` is a USB HID usage (Keyboard/Keypad page 0x07)
 * and whose `modifierFlags` is a `UIKeyModifierFlags` bitmask.
 */
package org.graphiks.kadre.uikit

import org.graphiks.kadre.core.KeyCode
import org.graphiks.kadre.core.KeyboardModifiers
import org.graphiks.kadre.core.NativeKeyCode
import org.graphiks.kadre.core.PhysicalKey

internal object UiKitKeyMapper {
    private const val HID_A = 0x04L
    private const val HID_Z = 0x1DL
    private const val HID_1 = 0x1EL
    private const val HID_9 = 0x26L
    private const val HID_0 = 0x27L
    private const val HID_ENTER = 0x28L
    private const val HID_ESCAPE = 0x29L
    private const val HID_BACKSPACE = 0x2AL
    private const val HID_TAB = 0x2BL
    private const val HID_SPACE = 0x2CL
    private const val HID_F1 = 0x3AL
    private const val HID_F12 = 0x45L
    private const val HID_RIGHT = 0x4FL
    private const val HID_LEFT = 0x50L
    private const val HID_DOWN = 0x51L
    private const val HID_UP = 0x52L
    private const val HID_LCTRL = 0xE0L
    private const val HID_LSHIFT = 0xE1L
    private const val HID_LALT = 0xE2L
    private const val HID_LMETA = 0xE3L
    private const val HID_RCTRL = 0xE4L
    private const val HID_RSHIFT = 0xE5L
    private const val HID_RALT = 0xE6L
    private const val HID_RMETA = 0xE7L

    private const val MOD_SHIFT = 1L shl 17
    private const val MOD_CONTROL = 1L shl 18
    private const val MOD_ALTERNATE = 1L shl 19
    private const val MOD_COMMAND = 1L shl 20

    fun keyCode(usage: Long): KeyCode? = when (usage) {
        in HID_A..HID_Z -> LETTER_KEYS[(usage - HID_A).toInt()]
        in HID_1..HID_9 -> DIGIT_KEYS[(usage - HID_1).toInt() + 1]
        HID_0 -> KeyCode.Digit0
        in HID_F1..HID_F12 -> FUNCTION_KEYS[(usage - HID_F1).toInt()]
        HID_ENTER -> KeyCode.Enter
        HID_ESCAPE -> KeyCode.Escape
        HID_BACKSPACE -> KeyCode.Backspace
        HID_TAB -> KeyCode.Tab
        HID_SPACE -> KeyCode.Space
        HID_RIGHT -> KeyCode.ArrowRight
        HID_LEFT -> KeyCode.ArrowLeft
        HID_DOWN -> KeyCode.ArrowDown
        HID_UP -> KeyCode.ArrowUp
        HID_LCTRL -> KeyCode.ControlLeft
        HID_LSHIFT -> KeyCode.ShiftLeft
        HID_LALT -> KeyCode.AltLeft
        HID_LMETA -> KeyCode.MetaLeft
        HID_RCTRL -> KeyCode.ControlRight
        HID_RSHIFT -> KeyCode.ShiftRight
        HID_RALT -> KeyCode.AltRight
        HID_RMETA -> KeyCode.MetaRight
        else -> null
    }

    fun physicalKey(usage: Long): PhysicalKey = keyCode(usage)?.let(PhysicalKey::Code)
        ?: PhysicalKey.Native(NativeKeyCode.UIKit(usage))

    private val LETTER_KEYS = arrayOf(
        KeyCode.KeyA, KeyCode.KeyB, KeyCode.KeyC, KeyCode.KeyD, KeyCode.KeyE, KeyCode.KeyF,
        KeyCode.KeyG, KeyCode.KeyH, KeyCode.KeyI, KeyCode.KeyJ, KeyCode.KeyK, KeyCode.KeyL,
        KeyCode.KeyM, KeyCode.KeyN, KeyCode.KeyO, KeyCode.KeyP, KeyCode.KeyQ, KeyCode.KeyR,
        KeyCode.KeyS, KeyCode.KeyT, KeyCode.KeyU, KeyCode.KeyV, KeyCode.KeyW, KeyCode.KeyX,
        KeyCode.KeyY, KeyCode.KeyZ,
    )

    private val DIGIT_KEYS = arrayOf(
        KeyCode.Digit0, KeyCode.Digit1, KeyCode.Digit2, KeyCode.Digit3, KeyCode.Digit4,
        KeyCode.Digit5, KeyCode.Digit6, KeyCode.Digit7, KeyCode.Digit8, KeyCode.Digit9,
    )

    private val FUNCTION_KEYS = arrayOf(
        KeyCode.F1, KeyCode.F2, KeyCode.F3, KeyCode.F4, KeyCode.F5, KeyCode.F6,
        KeyCode.F7, KeyCode.F8, KeyCode.F9, KeyCode.F10, KeyCode.F11, KeyCode.F12,
    )

    fun modifiersFrom(flags: Long): KeyboardModifiers {
        var mods = KeyboardModifiers.NONE
        if (flags and MOD_SHIFT != 0L) mods += KeyboardModifiers.Shift
        if (flags and MOD_CONTROL != 0L) mods += KeyboardModifiers.Ctrl
        if (flags and MOD_ALTERNATE != 0L) mods += KeyboardModifiers.Alt
        if (flags and MOD_COMMAND != 0L) mods += KeyboardModifiers.Meta
        return mods
    }
}
