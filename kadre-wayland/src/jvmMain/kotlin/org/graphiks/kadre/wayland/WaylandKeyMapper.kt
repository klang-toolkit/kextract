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
import org.graphiks.kadre.core.KeyboardModifierState
import org.graphiks.kadre.core.KeyboardModifiers
import org.graphiks.kadre.core.LogicalKey
import org.graphiks.kadre.core.ModifierKeyState
import org.graphiks.kadre.core.ModifierKeys
import org.graphiks.kadre.core.NativeKeyInfo
import org.graphiks.kadre.core.NativeKeyCode
import org.graphiks.kadre.core.NativeLogicalKey
import org.graphiks.kadre.core.PhysicalKey
import org.graphiks.kadre.core.WindowEvent
import org.graphiks.kadre.core.defaultLogicalKey
import org.graphiks.kadre.core.defaultText
import java.lang.foreign.MemorySegment
import java.lang.foreign.ValueLayout

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

internal fun waylandInitialModifierState(): KeyboardModifierState =
    KeyboardModifierState(
        logical = KeyboardModifiers.NONE,
        physical = ModifierKeys(
            leftShift = ModifierKeyState.Released,
            rightShift = ModifierKeyState.Released,
            leftCtrl = ModifierKeyState.Released,
            rightCtrl = ModifierKeyState.Released,
            leftAlt = ModifierKeyState.Released,
            rightAlt = ModifierKeyState.Released,
            leftMeta = ModifierKeyState.Released,
            rightMeta = ModifierKeyState.Released,
        ),
    )

internal fun isWaylandModifierKey(keycode: Int): Boolean = keycode in MODIFIER_KEYCODES

internal fun waylandModifierStateFrom(
    previous: KeyboardModifierState,
    keycode: Int,
    state: KeyState,
): KeyboardModifierState {
    val keyState = when (state) {
        KeyState.Pressed -> ModifierKeyState.Pressed
        KeyState.Released -> ModifierKeyState.Released
    }
    val previousPhysical = previous.physical
    val physical = when (keycode) {
        KEY_LEFT_SHIFT -> previousPhysical.copy(leftShift = keyState)
        KEY_RIGHT_SHIFT -> previousPhysical.copy(rightShift = keyState)
        KEY_LEFT_CTRL -> previousPhysical.copy(leftCtrl = keyState)
        KEY_RIGHT_CTRL -> previousPhysical.copy(rightCtrl = keyState)
        KEY_LEFT_ALT -> previousPhysical.copy(leftAlt = keyState)
        KEY_RIGHT_ALT -> previousPhysical.copy(rightAlt = keyState)
        KEY_LEFT_META -> previousPhysical.copy(leftMeta = keyState)
        KEY_RIGHT_META -> previousPhysical.copy(rightMeta = keyState)
        else -> previousPhysical
    }
    return KeyboardModifierState(logical = waylandLogicalModifiersFrom(physical), physical = physical)
}

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
            textWithAllModifiers = mappedCode?.defaultText(),
            keyWithoutModifiers = mappedCode?.defaultText(),
            native = native,
        ),
        deviceId = null,
    )
}

fun mapWaylandKeyboardFocused(focused: Boolean): WindowEvent.Focused = WindowEvent.Focused(focused)

/**
 * Best-effort mapping from XKB modifier masks to [KeyboardModifiers] without xkbcommon.
 *
 * Standard XKB modifier indices used by essentially all Linux desktop keymaps:
 * - Shift: index 0 → mask 0x01
 * - Control: index 2 → mask 0x04
 * - Mod1 (Alt): index 3 → mask 0x08
 * - Mod4 (Super/Meta): index 6 → mask 0x40
 */
internal fun xkbModMaskToKeyboardModifiers(modsDepressed: Int): KeyboardModifiers {
    var mods = KeyboardModifiers.NONE
    if ((modsDepressed and 0x01) != 0) mods += KeyboardModifiers.Shift
    if ((modsDepressed and 0x04) != 0) mods += KeyboardModifiers.Ctrl
    if ((modsDepressed and 0x08) != 0) mods += KeyboardModifiers.Alt
    if ((modsDepressed and 0x40) != 0) mods += KeyboardModifiers.Meta
    return mods
}

internal fun xkbModMaskToModifierKeys(modsDepressed: Int): ModifierKeys {
    val leftShift = if ((modsDepressed and 0x01) != 0) ModifierKeyState.Pressed else ModifierKeyState.Released
    val leftCtrl = if ((modsDepressed and 0x04) != 0) ModifierKeyState.Pressed else ModifierKeyState.Released
    val leftAlt = if ((modsDepressed and 0x08) != 0) ModifierKeyState.Pressed else ModifierKeyState.Released
    val leftMeta = if ((modsDepressed and 0x40) != 0) ModifierKeyState.Pressed else ModifierKeyState.Released
    return ModifierKeys(
        leftShift = leftShift, rightShift = ModifierKeyState.Released,
        leftCtrl = leftCtrl, rightCtrl = ModifierKeyState.Released,
        leftAlt = leftAlt, rightAlt = ModifierKeyState.Released,
        leftMeta = leftMeta, rightMeta = ModifierKeyState.Released,
    )
}

internal class WaylandKeyboardModifierTracker {
    private var modifierState = waylandInitialModifierState()

    fun mapFocusGained(pressedKeycodes: Iterable<Int>): List<WindowEvent> {
        val nextModifierState = modifierStateFromPressedKeys(pressedKeycodes)
        val events = mutableListOf<WindowEvent>()
        if (nextModifierState != modifierState) {
            modifierState = nextModifierState
            events += WindowEvent.ModifiersChanged(nextModifierState)
        }
        events += mapWaylandKeyboardFocused(true)
        return events
    }

    fun mapKey(keycode: Int, state: Int): List<WindowEvent> {
        val keyState = waylandKeyStateToKeyState(state)
        val nextModifierState = if (isWaylandModifierKey(keycode)) {
            waylandModifierStateFrom(modifierState, keycode, keyState)
        } else {
            modifierState
        }
        val events = mutableListOf<WindowEvent>()
        if (nextModifierState != modifierState) {
            modifierState = nextModifierState
            events += WindowEvent.ModifiersChanged(nextModifierState)
        }
        events += mapWaylandKeyEvent(
            keycode = keycode,
            state = state,
            modifiers = modifierState.logical,
        )
        return events
    }

    fun mapModifiers(modsDepressed: Int): List<WindowEvent> {
        val logical = xkbModMaskToKeyboardModifiers(modsDepressed)
        val physical = xkbModMaskToModifierKeys(modsDepressed)
        val nextState = KeyboardModifierState(logical = logical, physical = physical)
        return if (nextState != modifierState) {
            modifierState = nextState
            listOf(WindowEvent.ModifiersChanged(nextState))
        } else {
            emptyList()
        }
    }

    fun mapFocusLost(): List<WindowEvent> {
        val initial = waylandInitialModifierState()
        return if (modifierState == initial) {
            listOf(mapWaylandKeyboardFocused(false))
        } else {
            modifierState = initial
            listOf(WindowEvent.ModifiersChanged(initial), mapWaylandKeyboardFocused(false))
        }
    }
}

internal fun waylandPressedKeysFromArray(keys: MemorySegment): List<Int> {
    if (keys == MemorySegment.NULL || keys.address() == 0L) return emptyList()
    return try {
        val array = keys.reinterpret(WL_ARRAY_SIZE)
        val size = array.get(ValueLayout.JAVA_LONG, WL_ARRAY_SIZE_OFFSET)
        val data = array.get(ValueLayout.ADDRESS, WL_ARRAY_DATA_OFFSET)
        if (size <= 0L || data == MemorySegment.NULL || data.address() == 0L) return emptyList()
        val count = size / ValueLayout.JAVA_INT.byteSize()
        if (count <= 0L) return emptyList()
        val keyData = data.reinterpret(count * ValueLayout.JAVA_INT.byteSize())
        (0 until count).map { index ->
            keyData.get(ValueLayout.JAVA_INT, index * ValueLayout.JAVA_INT.byteSize())
        }
    } catch (_: Throwable) {
        emptyList()
    }
}

private fun modifierStateFromPressedKeys(pressedKeycodes: Iterable<Int>): KeyboardModifierState {
    var state = waylandInitialModifierState()
    pressedKeycodes.forEach { keycode ->
        if (isWaylandModifierKey(keycode)) {
            state = waylandModifierStateFrom(state, keycode, KeyState.Pressed)
        }
    }
    return state
}

private fun waylandLogicalModifiersFrom(physical: ModifierKeys): KeyboardModifiers {
    var mods = KeyboardModifiers.NONE
    if (physical.leftShift == ModifierKeyState.Pressed || physical.rightShift == ModifierKeyState.Pressed) {
        mods += KeyboardModifiers.Shift
    }
    if (physical.leftCtrl == ModifierKeyState.Pressed || physical.rightCtrl == ModifierKeyState.Pressed) {
        mods += KeyboardModifiers.Ctrl
    }
    if (physical.leftAlt == ModifierKeyState.Pressed || physical.rightAlt == ModifierKeyState.Pressed) {
        mods += KeyboardModifiers.Alt
    }
    if (physical.leftMeta == ModifierKeyState.Pressed || physical.rightMeta == ModifierKeyState.Pressed) {
        mods += KeyboardModifiers.Meta
    }
    return mods
}

private const val KEY_LEFT_SHIFT = 42
private const val KEY_RIGHT_SHIFT = 54
private const val KEY_LEFT_CTRL = 29
private const val KEY_RIGHT_CTRL = 97
private const val KEY_LEFT_ALT = 56
private const val KEY_RIGHT_ALT = 100
private const val KEY_LEFT_META = 125
private const val KEY_RIGHT_META = 126

private val MODIFIER_KEYCODES = setOf(
    KEY_LEFT_SHIFT,
    KEY_RIGHT_SHIFT,
    KEY_LEFT_CTRL,
    KEY_RIGHT_CTRL,
    KEY_LEFT_ALT,
    KEY_RIGHT_ALT,
    KEY_LEFT_META,
    KEY_RIGHT_META,
)

private const val WL_ARRAY_SIZE_OFFSET = 0L
private const val WL_ARRAY_DATA_OFFSET = 16L
private const val WL_ARRAY_SIZE = 24L
