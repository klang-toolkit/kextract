/**
 * X11 mapper for keyboard events (XKeyEvent).
 *
 * Converts raw XKeyEvent events (KeyPress / KeyRelease) into
 * [org.graphiks.kadre.core.WindowEvent.KeyInput].
 */
package org.graphiks.kadre.x11

import org.graphiks.kadre.core.KeyCode
import org.graphiks.kadre.core.KeyEvent
import org.graphiks.kadre.core.KeyPlatform
import org.graphiks.kadre.core.KeyState
import org.graphiks.kadre.core.KeyboardModifiers
import org.graphiks.kadre.core.LogicalKey
import org.graphiks.kadre.core.NativeKeyInfo
import org.graphiks.kadre.core.PhysicalKey
import org.graphiks.kadre.core.WindowEvent
import org.graphiks.kadre.core.defaultLogicalKey
import org.graphiks.kadre.core.defaultText
import java.lang.foreign.MemorySegment
import java.lang.foreign.ValueLayout

private const val OFFSET_STATE: Long = 64L
private const val OFFSET_KEYCODE: Long = 68L

private const val SHIFT_MASK: Int = 0x01
private const val CONTROL_MASK: Int = 0x04
private const val MOD1_MASK: Int = 0x08
private const val MOD4_MASK: Int = 0x40

internal val KEYSYM_TABLE: Map<Int, KeyCode> = buildMap {
    put(0x61, KeyCode.KeyA); put(0x62, KeyCode.KeyB); put(0x63, KeyCode.KeyC); put(0x64, KeyCode.KeyD)
    put(0x65, KeyCode.KeyE); put(0x66, KeyCode.KeyF); put(0x67, KeyCode.KeyG); put(0x68, KeyCode.KeyH)
    put(0x69, KeyCode.KeyI); put(0x6A, KeyCode.KeyJ); put(0x6B, KeyCode.KeyK); put(0x6C, KeyCode.KeyL)
    put(0x6D, KeyCode.KeyM); put(0x6E, KeyCode.KeyN); put(0x6F, KeyCode.KeyO); put(0x70, KeyCode.KeyP)
    put(0x71, KeyCode.KeyQ); put(0x72, KeyCode.KeyR); put(0x73, KeyCode.KeyS); put(0x74, KeyCode.KeyT)
    put(0x75, KeyCode.KeyU); put(0x76, KeyCode.KeyV); put(0x77, KeyCode.KeyW); put(0x78, KeyCode.KeyX)
    put(0x79, KeyCode.KeyY); put(0x7A, KeyCode.KeyZ)

    put(0x41, KeyCode.KeyA); put(0x42, KeyCode.KeyB); put(0x43, KeyCode.KeyC); put(0x44, KeyCode.KeyD)
    put(0x45, KeyCode.KeyE); put(0x46, KeyCode.KeyF); put(0x47, KeyCode.KeyG); put(0x48, KeyCode.KeyH)
    put(0x49, KeyCode.KeyI); put(0x4A, KeyCode.KeyJ); put(0x4B, KeyCode.KeyK); put(0x4C, KeyCode.KeyL)
    put(0x4D, KeyCode.KeyM); put(0x4E, KeyCode.KeyN); put(0x4F, KeyCode.KeyO); put(0x50, KeyCode.KeyP)
    put(0x51, KeyCode.KeyQ); put(0x52, KeyCode.KeyR); put(0x53, KeyCode.KeyS); put(0x54, KeyCode.KeyT)
    put(0x55, KeyCode.KeyU); put(0x56, KeyCode.KeyV); put(0x57, KeyCode.KeyW); put(0x58, KeyCode.KeyX)
    put(0x59, KeyCode.KeyY); put(0x5A, KeyCode.KeyZ)

    put(0x30, KeyCode.Digit0); put(0x31, KeyCode.Digit1); put(0x32, KeyCode.Digit2)
    put(0x33, KeyCode.Digit3); put(0x34, KeyCode.Digit4); put(0x35, KeyCode.Digit5)
    put(0x36, KeyCode.Digit6); put(0x37, KeyCode.Digit7); put(0x38, KeyCode.Digit8)
    put(0x39, KeyCode.Digit9)

    put(0xFF08, KeyCode.Backspace)
    put(0xFF09, KeyCode.Tab)
    put(0xFF0D, KeyCode.Enter)
    put(0xFF1B, KeyCode.Escape)
    put(0x0020, KeyCode.Space)

    put(0xFF51, KeyCode.ArrowLeft)
    put(0xFF52, KeyCode.ArrowUp)
    put(0xFF53, KeyCode.ArrowRight)
    put(0xFF54, KeyCode.ArrowDown)

    put(0xFFBE, KeyCode.F1); put(0xFFBF, KeyCode.F2); put(0xFFC0, KeyCode.F3)
    put(0xFFC1, KeyCode.F4); put(0xFFC2, KeyCode.F5); put(0xFFC3, KeyCode.F6)
    put(0xFFC4, KeyCode.F7); put(0xFFC5, KeyCode.F8); put(0xFFC6, KeyCode.F9)
    put(0xFFC7, KeyCode.F10); put(0xFFC8, KeyCode.F11); put(0xFFC9, KeyCode.F12)

    put(0xFFE1, KeyCode.ShiftLeft)
    put(0xFFE2, KeyCode.ShiftRight)
    put(0xFFE3, KeyCode.ControlLeft)
    put(0xFFE4, KeyCode.ControlRight)
    put(0xFFE9, KeyCode.AltLeft)
    put(0xFFEA, KeyCode.AltRight)
    put(0xFFEB, KeyCode.MetaLeft)
    put(0xFFEC, KeyCode.MetaRight)
}

internal fun stateToModifiers(state: Int): KeyboardModifiers {
    var bits = 0
    if (state and SHIFT_MASK != 0) bits = bits or KeyboardModifiers.SHIFT
    if (state and CONTROL_MASK != 0) bits = bits or KeyboardModifiers.CTRL
    if (state and MOD1_MASK != 0) bits = bits or KeyboardModifiers.ALT
    if (state and MOD4_MASK != 0) bits = bits or KeyboardModifiers.META
    return KeyboardModifiers(bits)
}

object X11KeyMapper {
    private val pressedKeys: MutableSet<Int> = mutableSetOf()

    fun enableDetectableAutoRepeat(displayPtr: Long) {
        val handle = xkbSetDetectableAutoRepeat ?: return
        val display = MemorySegment.ofAddress(displayPtr)
        try {
            handle.invokeExact(display, 1, MemorySegment.NULL) as Int
        } catch (_: Throwable) {
            // The Xkb extension is optional.
        }
    }

    fun resetState() {
        pressedKeys.clear()
    }

    fun fromXEvent(
        eventSegment: MemorySegment,
        eventType: Int,
        keysym: Int = 0,
    ): WindowEvent.KeyInput? {
        val state = eventSegment.get(ValueLayout.JAVA_INT, OFFSET_STATE)
        val keycode = eventSegment.get(ValueLayout.JAVA_INT, OFFSET_KEYCODE) and 0xFFFF
        val isPressed = eventType == KeyPress
        val isRepeat = isPressed && pressedKeys.contains(keycode)

        if (isPressed) {
            pressedKeys.add(keycode)
        } else {
            pressedKeys.remove(keycode)
        }

        val mappedCode = if (keysym != 0) KEYSYM_TABLE[keysym] else null
        val keyState = if (isPressed) KeyState.Pressed else KeyState.Released
        val modifiers = stateToModifiers(state)
        val native = NativeKeyInfo(
            platform = KeyPlatform.X11,
            scanCode = keycode.toLong(),
            keyValue = keysym.takeIf { it != 0 }?.toString(),
        )
        val logicalKey = mappedCode?.defaultLogicalKey() ?: LogicalKey.Unidentified(native)

        return WindowEvent.KeyInput(
            KeyEvent(
                physicalKey = mappedCode?.let(PhysicalKey::Code) ?: PhysicalKey.Native(KeyPlatform.X11, keycode.toLong()),
                logicalKey = logicalKey,
                state = keyState,
                modifiers = modifiers,
                repeat = isRepeat,
                text = mappedCode?.defaultText(),
                keyWithoutModifiers = logicalKey,
                native = native,
            ),
        )
    }
}
