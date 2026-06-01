/**
 * Mapping of Android key codes ([android.view.KeyEvent] KEYCODE_*) to kadre
 * logical keys [Key], and of the `metaState` bitmask to kadre [Modifiers].
 *
 * Key codes arrive in [android.view.KeyEvent.getKeyCode] from the Activity
 * `onKeyDown` / `onKeyUp` callbacks (hardware keyboards, Bluetooth keyboards,
 * game controller D-pads and face buttons that surface as keys).
 *
 * The KEYCODE_* / META_* values are compile-time constants, so this mapper is
 * pure Kotlin and unit-testable without instantiating a KeyEvent.
 *
 * Reference: https://developer.android.com/reference/android/view/KeyEvent
 */
package org.graphiks.kadre.android

import android.view.KeyEvent
import org.graphiks.kadre.core.Key
import org.graphiks.kadre.core.Modifiers

internal object AndroidKeyMapper {

    /** KEYCODE_* → [Key] table (built once). */
    private val table: Map<Int, Key> = buildMap {
        // Letters A–Z
        put(KeyEvent.KEYCODE_A, Key.A)
        put(KeyEvent.KEYCODE_B, Key.B)
        put(KeyEvent.KEYCODE_C, Key.C)
        put(KeyEvent.KEYCODE_D, Key.D)
        put(KeyEvent.KEYCODE_E, Key.E)
        put(KeyEvent.KEYCODE_F, Key.F)
        put(KeyEvent.KEYCODE_G, Key.G)
        put(KeyEvent.KEYCODE_H, Key.H)
        put(KeyEvent.KEYCODE_I, Key.I)
        put(KeyEvent.KEYCODE_J, Key.J)
        put(KeyEvent.KEYCODE_K, Key.K)
        put(KeyEvent.KEYCODE_L, Key.L)
        put(KeyEvent.KEYCODE_M, Key.M)
        put(KeyEvent.KEYCODE_N, Key.N)
        put(KeyEvent.KEYCODE_O, Key.O)
        put(KeyEvent.KEYCODE_P, Key.P)
        put(KeyEvent.KEYCODE_Q, Key.Q)
        put(KeyEvent.KEYCODE_R, Key.R)
        put(KeyEvent.KEYCODE_S, Key.S)
        put(KeyEvent.KEYCODE_T, Key.T)
        put(KeyEvent.KEYCODE_U, Key.U)
        put(KeyEvent.KEYCODE_V, Key.V)
        put(KeyEvent.KEYCODE_W, Key.W)
        put(KeyEvent.KEYCODE_X, Key.X)
        put(KeyEvent.KEYCODE_Y, Key.Y)
        put(KeyEvent.KEYCODE_Z, Key.Z)

        // Digits 0–9 (top row)
        put(KeyEvent.KEYCODE_0, Key.Digit0)
        put(KeyEvent.KEYCODE_1, Key.Digit1)
        put(KeyEvent.KEYCODE_2, Key.Digit2)
        put(KeyEvent.KEYCODE_3, Key.Digit3)
        put(KeyEvent.KEYCODE_4, Key.Digit4)
        put(KeyEvent.KEYCODE_5, Key.Digit5)
        put(KeyEvent.KEYCODE_6, Key.Digit6)
        put(KeyEvent.KEYCODE_7, Key.Digit7)
        put(KeyEvent.KEYCODE_8, Key.Digit8)
        put(KeyEvent.KEYCODE_9, Key.Digit9)

        // Function keys F1–F12
        put(KeyEvent.KEYCODE_F1, Key.F1)
        put(KeyEvent.KEYCODE_F2, Key.F2)
        put(KeyEvent.KEYCODE_F3, Key.F3)
        put(KeyEvent.KEYCODE_F4, Key.F4)
        put(KeyEvent.KEYCODE_F5, Key.F5)
        put(KeyEvent.KEYCODE_F6, Key.F6)
        put(KeyEvent.KEYCODE_F7, Key.F7)
        put(KeyEvent.KEYCODE_F8, Key.F8)
        put(KeyEvent.KEYCODE_F9, Key.F9)
        put(KeyEvent.KEYCODE_F10, Key.F10)
        put(KeyEvent.KEYCODE_F11, Key.F11)
        put(KeyEvent.KEYCODE_F12, Key.F12)

        // Navigation (also the D-pad of game controllers)
        put(KeyEvent.KEYCODE_DPAD_UP, Key.ArrowUp)
        put(KeyEvent.KEYCODE_DPAD_DOWN, Key.ArrowDown)
        put(KeyEvent.KEYCODE_DPAD_LEFT, Key.ArrowLeft)
        put(KeyEvent.KEYCODE_DPAD_RIGHT, Key.ArrowRight)

        // Special keys
        put(KeyEvent.KEYCODE_SPACE, Key.Space)
        put(KeyEvent.KEYCODE_ENTER, Key.Enter)
        put(KeyEvent.KEYCODE_ESCAPE, Key.Escape)
        put(KeyEvent.KEYCODE_DEL, Key.Backspace) // KEYCODE_DEL is the Backspace key
        put(KeyEvent.KEYCODE_TAB, Key.Tab)

        // Modifiers (left / right)
        put(KeyEvent.KEYCODE_SHIFT_LEFT, Key.ShiftLeft)
        put(KeyEvent.KEYCODE_SHIFT_RIGHT, Key.ShiftRight)
        put(KeyEvent.KEYCODE_CTRL_LEFT, Key.ControlLeft)
        put(KeyEvent.KEYCODE_CTRL_RIGHT, Key.ControlRight)
        put(KeyEvent.KEYCODE_ALT_LEFT, Key.AltLeft)
        put(KeyEvent.KEYCODE_ALT_RIGHT, Key.AltRight)
        put(KeyEvent.KEYCODE_META_LEFT, Key.MetaLeft)
        put(KeyEvent.KEYCODE_META_RIGHT, Key.MetaRight)
    }

    /**
     * Returns the [Key] for an Android key code, or [Key.Unknown] if unmapped
     * (e.g. volume, back, media keys — left for the system to handle).
     *
     * @param keyCode Value from [android.view.KeyEvent.getKeyCode].
     */
    fun fromKeyCode(keyCode: Int): Key = table[keyCode] ?: Key.Unknown

    /**
     * Builds the kadre [Modifiers] from an Android `metaState` bitmask.
     *
     * @param metaState Value from [android.view.KeyEvent.getMetaState].
     */
    fun modifiersFrom(metaState: Int): Modifiers {
        var mods = Modifiers.NONE
        if (metaState and KeyEvent.META_SHIFT_ON != 0) mods += Modifiers.SHIFT
        if (metaState and KeyEvent.META_CTRL_ON != 0) mods += Modifiers.CTRL
        if (metaState and KeyEvent.META_ALT_ON != 0) mods += Modifiers.ALT
        if (metaState and KeyEvent.META_META_ON != 0) mods += Modifiers.META
        return mods
    }
}
