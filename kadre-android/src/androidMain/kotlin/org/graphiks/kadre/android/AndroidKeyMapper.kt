/**
 * Mapping of Android key codes ([android.view.KeyEvent] KEYCODE_*) to kadre
 * physical key codes, and of the `metaState` bitmask to kadre modifiers.
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
import org.graphiks.kadre.core.KeyCode
import org.graphiks.kadre.core.KeyPlatform
import org.graphiks.kadre.core.KeyboardModifiers
import org.graphiks.kadre.core.PhysicalKey

internal object AndroidKeyMapper {

    /** KEYCODE_* -> [KeyCode] table (built once). */
    private val table: Map<Int, KeyCode> = buildMap {
        // Letters A–Z
        put(KeyEvent.KEYCODE_A, KeyCode.KeyA)
        put(KeyEvent.KEYCODE_B, KeyCode.KeyB)
        put(KeyEvent.KEYCODE_C, KeyCode.KeyC)
        put(KeyEvent.KEYCODE_D, KeyCode.KeyD)
        put(KeyEvent.KEYCODE_E, KeyCode.KeyE)
        put(KeyEvent.KEYCODE_F, KeyCode.KeyF)
        put(KeyEvent.KEYCODE_G, KeyCode.KeyG)
        put(KeyEvent.KEYCODE_H, KeyCode.KeyH)
        put(KeyEvent.KEYCODE_I, KeyCode.KeyI)
        put(KeyEvent.KEYCODE_J, KeyCode.KeyJ)
        put(KeyEvent.KEYCODE_K, KeyCode.KeyK)
        put(KeyEvent.KEYCODE_L, KeyCode.KeyL)
        put(KeyEvent.KEYCODE_M, KeyCode.KeyM)
        put(KeyEvent.KEYCODE_N, KeyCode.KeyN)
        put(KeyEvent.KEYCODE_O, KeyCode.KeyO)
        put(KeyEvent.KEYCODE_P, KeyCode.KeyP)
        put(KeyEvent.KEYCODE_Q, KeyCode.KeyQ)
        put(KeyEvent.KEYCODE_R, KeyCode.KeyR)
        put(KeyEvent.KEYCODE_S, KeyCode.KeyS)
        put(KeyEvent.KEYCODE_T, KeyCode.KeyT)
        put(KeyEvent.KEYCODE_U, KeyCode.KeyU)
        put(KeyEvent.KEYCODE_V, KeyCode.KeyV)
        put(KeyEvent.KEYCODE_W, KeyCode.KeyW)
        put(KeyEvent.KEYCODE_X, KeyCode.KeyX)
        put(KeyEvent.KEYCODE_Y, KeyCode.KeyY)
        put(KeyEvent.KEYCODE_Z, KeyCode.KeyZ)

        // Digits 0–9 (top row)
        put(KeyEvent.KEYCODE_0, KeyCode.Digit0)
        put(KeyEvent.KEYCODE_1, KeyCode.Digit1)
        put(KeyEvent.KEYCODE_2, KeyCode.Digit2)
        put(KeyEvent.KEYCODE_3, KeyCode.Digit3)
        put(KeyEvent.KEYCODE_4, KeyCode.Digit4)
        put(KeyEvent.KEYCODE_5, KeyCode.Digit5)
        put(KeyEvent.KEYCODE_6, KeyCode.Digit6)
        put(KeyEvent.KEYCODE_7, KeyCode.Digit7)
        put(KeyEvent.KEYCODE_8, KeyCode.Digit8)
        put(KeyEvent.KEYCODE_9, KeyCode.Digit9)

        // Function keys F1–F12
        put(KeyEvent.KEYCODE_F1, KeyCode.F1)
        put(KeyEvent.KEYCODE_F2, KeyCode.F2)
        put(KeyEvent.KEYCODE_F3, KeyCode.F3)
        put(KeyEvent.KEYCODE_F4, KeyCode.F4)
        put(KeyEvent.KEYCODE_F5, KeyCode.F5)
        put(KeyEvent.KEYCODE_F6, KeyCode.F6)
        put(KeyEvent.KEYCODE_F7, KeyCode.F7)
        put(KeyEvent.KEYCODE_F8, KeyCode.F8)
        put(KeyEvent.KEYCODE_F9, KeyCode.F9)
        put(KeyEvent.KEYCODE_F10, KeyCode.F10)
        put(KeyEvent.KEYCODE_F11, KeyCode.F11)
        put(KeyEvent.KEYCODE_F12, KeyCode.F12)

        // Navigation (also the D-pad of game controllers)
        put(KeyEvent.KEYCODE_DPAD_UP, KeyCode.ArrowUp)
        put(KeyEvent.KEYCODE_DPAD_DOWN, KeyCode.ArrowDown)
        put(KeyEvent.KEYCODE_DPAD_LEFT, KeyCode.ArrowLeft)
        put(KeyEvent.KEYCODE_DPAD_RIGHT, KeyCode.ArrowRight)

        // Special keys
        put(KeyEvent.KEYCODE_SPACE, KeyCode.Space)
        put(KeyEvent.KEYCODE_ENTER, KeyCode.Enter)
        put(KeyEvent.KEYCODE_ESCAPE, KeyCode.Escape)
        put(KeyEvent.KEYCODE_DEL, KeyCode.Backspace) // KEYCODE_DEL is the Backspace key
        put(KeyEvent.KEYCODE_TAB, KeyCode.Tab)

        // Modifiers (left / right)
        put(KeyEvent.KEYCODE_SHIFT_LEFT, KeyCode.ShiftLeft)
        put(KeyEvent.KEYCODE_SHIFT_RIGHT, KeyCode.ShiftRight)
        put(KeyEvent.KEYCODE_CTRL_LEFT, KeyCode.ControlLeft)
        put(KeyEvent.KEYCODE_CTRL_RIGHT, KeyCode.ControlRight)
        put(KeyEvent.KEYCODE_ALT_LEFT, KeyCode.AltLeft)
        put(KeyEvent.KEYCODE_ALT_RIGHT, KeyCode.AltRight)
        put(KeyEvent.KEYCODE_META_LEFT, KeyCode.MetaLeft)
        put(KeyEvent.KEYCODE_META_RIGHT, KeyCode.MetaRight)
    }

    /**
     * Returns the [KeyCode] for an Android key code, or null if unmapped
     * (e.g. volume, back, media keys — left for the system to handle).
     *
     * @param keyCode Value from [android.view.KeyEvent.getKeyCode].
     */
    fun keyCode(keyCode: Int): KeyCode? = table[keyCode]

    fun physicalKey(keyCode: Int): PhysicalKey = keyCode(keyCode)?.let(PhysicalKey::Code)
        ?: PhysicalKey.Native(KeyPlatform.Android, keyCode.toLong())

    /**
     * Builds the kadre [KeyboardModifiers] from an Android `metaState` bitmask.
     *
     * @param metaState Value from [android.view.KeyEvent.getMetaState].
     */
    fun modifiersFrom(metaState: Int): KeyboardModifiers {
        var mods = KeyboardModifiers.NONE
        if (metaState and KeyEvent.META_SHIFT_ON != 0) mods += KeyboardModifiers.Shift
        if (metaState and KeyEvent.META_CTRL_ON != 0) mods += KeyboardModifiers.Ctrl
        if (metaState and KeyEvent.META_ALT_ON != 0) mods += KeyboardModifiers.Alt
        if (metaState and KeyEvent.META_META_ON != 0) mods += KeyboardModifiers.Meta
        return mods
    }
}
