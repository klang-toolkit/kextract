/**
 * Mapping of iOS keyboard input to kadre logical keys and modifiers.
 *
 * iOS surfaces hardware-keyboard and game-controller key events through
 * `UIResponder.pressesBegan/Ended` (iOS 13.4+). Each `UIPress` carries a
 * `UIKey` whose `keyCode` is a **USB HID usage** (Keyboard/Keypad page 0x07)
 * and whose `modifierFlags` is a `UIKeyModifierFlags` bitmask.
 *
 * This mapper works on the raw numeric values (HID usage as [Long], modifier
 * flags as [Long]) so it stays independent of the UIKit cinterop binding shapes
 * and is straightforward to reason about.
 *
 * Reference: USB HID Usage Tables, Keyboard/Keypad Page (0x07).
 */
package org.graphiks.kadre.uikit

import org.graphiks.kadre.core.Key
import org.graphiks.kadre.core.Modifiers

internal object UiKitKeyMapper {

    // ── HID usage constants (Keyboard/Keypad page 0x07) ───────────────────────
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

    // ── UIKeyModifierFlags bits ───────────────────────────────────────────────
    // UIKeyModifierShift = 1<<17, Control = 1<<18, Alternate = 1<<19, Command = 1<<20.
    private const val MOD_SHIFT = 1L shl 17
    private const val MOD_CONTROL = 1L shl 18
    private const val MOD_ALTERNATE = 1L shl 19
    private const val MOD_COMMAND = 1L shl 20

    /**
     * Returns the kadre [Key] for a USB HID keyboard usage, or [Key.Unknown]
     * for usages outside the supported set.
     *
     * Letters, digits and function keys are contiguous in the HID table, so they
     * are resolved by index; the remaining keys are matched explicitly.
     *
     * @param usage `UIKey.keyCode` as a HID usage value.
     */
    fun fromHidUsage(usage: Long): Key = when (usage) {
        in HID_A..HID_Z -> LETTER_KEYS[(usage - HID_A).toInt()]
        in HID_1..HID_9 -> DIGIT_KEYS[(usage - HID_1).toInt() + 1]
        HID_0 -> Key.Digit0
        in HID_F1..HID_F12 -> FUNCTION_KEYS[(usage - HID_F1).toInt()]
        HID_ENTER -> Key.Enter
        HID_ESCAPE -> Key.Escape
        HID_BACKSPACE -> Key.Backspace
        HID_TAB -> Key.Tab
        HID_SPACE -> Key.Space
        HID_RIGHT -> Key.ArrowRight
        HID_LEFT -> Key.ArrowLeft
        HID_DOWN -> Key.ArrowDown
        HID_UP -> Key.ArrowUp
        HID_LCTRL -> Key.ControlLeft
        HID_LSHIFT -> Key.ShiftLeft
        HID_LALT -> Key.AltLeft
        HID_LMETA -> Key.MetaLeft
        HID_RCTRL -> Key.ControlRight
        HID_RSHIFT -> Key.ShiftRight
        HID_RALT -> Key.AltRight
        HID_RMETA -> Key.MetaRight
        else -> Key.Unknown
    }

    private val LETTER_KEYS = arrayOf(
        Key.A, Key.B, Key.C, Key.D, Key.E, Key.F, Key.G, Key.H, Key.I, Key.J,
        Key.K, Key.L, Key.M, Key.N, Key.O, Key.P, Key.Q, Key.R, Key.S, Key.T,
        Key.U, Key.V, Key.W, Key.X, Key.Y, Key.Z,
    )

    // Index 0 unused; digits 1..9 then 0 handled separately.
    private val DIGIT_KEYS = arrayOf(
        Key.Digit0, Key.Digit1, Key.Digit2, Key.Digit3, Key.Digit4,
        Key.Digit5, Key.Digit6, Key.Digit7, Key.Digit8, Key.Digit9,
    )

    private val FUNCTION_KEYS = arrayOf(
        Key.F1, Key.F2, Key.F3, Key.F4, Key.F5, Key.F6,
        Key.F7, Key.F8, Key.F9, Key.F10, Key.F11, Key.F12,
    )

    /**
     * Builds the kadre [Modifiers] from a `UIKeyModifierFlags` bitmask.
     *
     * @param flags `UIKey.modifierFlags` as a raw bitmask.
     */
    fun modifiersFrom(flags: Long): Modifiers {
        var mods = Modifiers.NONE
        if (flags and MOD_SHIFT != 0L) mods += Modifiers.SHIFT
        if (flags and MOD_CONTROL != 0L) mods += Modifiers.CTRL
        if (flags and MOD_ALTERNATE != 0L) mods += Modifiers.ALT
        if (flags and MOD_COMMAND != 0L) mods += Modifiers.META
        return mods
    }
}
