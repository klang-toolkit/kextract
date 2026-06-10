/**
 * DOM → Kadre web types mapping (pure functions, no DOM imports).
 *
 * This file is in webMain. It must contain NO DOM import
 * (no kotlinx.browser, org.w3c.dom.*, nor Wasm JS interop).
 * The parameters receive pure Kotlin types (String, Short, Boolean)
 * extracted by the implementations in jsMain / wasmJsMain.
 *
 * The functions are `internal` to avoid polluting the public API.
 *
 * @since 1.0.0
 */
package org.graphiks.kadre.web

import org.graphiks.kadre.core.KeyCode
import org.graphiks.kadre.core.KeyEvent
import org.graphiks.kadre.core.KeyPlatform
import org.graphiks.kadre.core.KeyState
import org.graphiks.kadre.core.ButtonSource
import org.graphiks.kadre.core.FingerId
import org.graphiks.kadre.core.KeyboardModifierState
import org.graphiks.kadre.core.KeyboardModifiers
import org.graphiks.kadre.core.LogicalKey
import org.graphiks.kadre.core.MouseButton
import org.graphiks.kadre.core.NativeKeyInfo
import org.graphiks.kadre.core.NativeKeyCode
import org.graphiks.kadre.core.NativeLogicalKey
import org.graphiks.kadre.core.PhysicalKey
import org.graphiks.kadre.core.PhysicalPosition
import org.graphiks.kadre.core.PhysicalSize
import org.graphiks.kadre.core.PointerKind
import org.graphiks.kadre.core.PointerSource
import org.graphiks.kadre.core.TouchPhase
import org.graphiks.kadre.core.WindowEvent
import org.graphiks.kadre.core.defaultLogicalKey
import org.graphiks.kadre.core.defaultText

/**
 * Converts a DOM code (`KeyboardEvent.code`) into a Kadre [WebKey].
 *
 * DOM codes are strings of the form `"KeyA"`, `"Digit1"`, `"ArrowUp"`, etc.
 * See: https://developer.mozilla.org/en-US/docs/Web/API/UI_Events/Keyboard_event_code_values
 *
 * @param code Value of `KeyboardEvent.code` (e.g. `"KeyA"`, `"Space"`, `"F1"`).
 * @return The corresponding [WebKey], or [WebKey.Unknown] if the code is not recognized.
 */
internal fun domCodeToKey(code: String): WebKey = when (code) {
    // Letters
    "KeyA" -> WebKey.A
    "KeyB" -> WebKey.B
    "KeyC" -> WebKey.C
    "KeyD" -> WebKey.D
    "KeyE" -> WebKey.E
    "KeyF" -> WebKey.F
    "KeyG" -> WebKey.G
    "KeyH" -> WebKey.H
    "KeyI" -> WebKey.I
    "KeyJ" -> WebKey.J
    "KeyK" -> WebKey.K
    "KeyL" -> WebKey.L
    "KeyM" -> WebKey.M
    "KeyN" -> WebKey.N
    "KeyO" -> WebKey.O
    "KeyP" -> WebKey.P
    "KeyQ" -> WebKey.Q
    "KeyR" -> WebKey.R
    "KeyS" -> WebKey.S
    "KeyT" -> WebKey.T
    "KeyU" -> WebKey.U
    "KeyV" -> WebKey.V
    "KeyW" -> WebKey.W
    "KeyX" -> WebKey.X
    "KeyY" -> WebKey.Y
    "KeyZ" -> WebKey.Z

    // Digits
    "Digit0" -> WebKey.Digit0
    "Digit1" -> WebKey.Digit1
    "Digit2" -> WebKey.Digit2
    "Digit3" -> WebKey.Digit3
    "Digit4" -> WebKey.Digit4
    "Digit5" -> WebKey.Digit5
    "Digit6" -> WebKey.Digit6
    "Digit7" -> WebKey.Digit7
    "Digit8" -> WebKey.Digit8
    "Digit9" -> WebKey.Digit9

    // Function keys
    "F1"  -> WebKey.F1
    "F2"  -> WebKey.F2
    "F3"  -> WebKey.F3
    "F4"  -> WebKey.F4
    "F5"  -> WebKey.F5
    "F6"  -> WebKey.F6
    "F7"  -> WebKey.F7
    "F8"  -> WebKey.F8
    "F9"  -> WebKey.F9
    "F10" -> WebKey.F10
    "F11" -> WebKey.F11
    "F12" -> WebKey.F12

    // Special keys
    "Space"     -> WebKey.Space
    "Enter"     -> WebKey.Enter
    "Escape"    -> WebKey.Escape
    "Backspace" -> WebKey.Backspace
    "Tab"       -> WebKey.Tab

    // Navigation
    "ArrowUp"    -> WebKey.ArrowUp
    "ArrowDown"  -> WebKey.ArrowDown
    "ArrowLeft"  -> WebKey.ArrowLeft
    "ArrowRight" -> WebKey.ArrowRight

    // Modifiers
    "ShiftLeft"    -> WebKey.ShiftLeft
    "ShiftRight"   -> WebKey.ShiftRight
    "ControlLeft"  -> WebKey.ControlLeft
    "ControlRight" -> WebKey.ControlRight
    "AltLeft"      -> WebKey.AltLeft
    "AltRight"     -> WebKey.AltRight
    "MetaLeft"     -> WebKey.MetaLeft
    "MetaRight"    -> WebKey.MetaRight

    else -> WebKey.Unknown
}

internal fun domCodeToKeyCode(code: String): KeyCode? = when (code) {
    "KeyA" -> KeyCode.KeyA
    "KeyB" -> KeyCode.KeyB
    "KeyC" -> KeyCode.KeyC
    "KeyD" -> KeyCode.KeyD
    "KeyE" -> KeyCode.KeyE
    "KeyF" -> KeyCode.KeyF
    "KeyG" -> KeyCode.KeyG
    "KeyH" -> KeyCode.KeyH
    "KeyI" -> KeyCode.KeyI
    "KeyJ" -> KeyCode.KeyJ
    "KeyK" -> KeyCode.KeyK
    "KeyL" -> KeyCode.KeyL
    "KeyM" -> KeyCode.KeyM
    "KeyN" -> KeyCode.KeyN
    "KeyO" -> KeyCode.KeyO
    "KeyP" -> KeyCode.KeyP
    "KeyQ" -> KeyCode.KeyQ
    "KeyR" -> KeyCode.KeyR
    "KeyS" -> KeyCode.KeyS
    "KeyT" -> KeyCode.KeyT
    "KeyU" -> KeyCode.KeyU
    "KeyV" -> KeyCode.KeyV
    "KeyW" -> KeyCode.KeyW
    "KeyX" -> KeyCode.KeyX
    "KeyY" -> KeyCode.KeyY
    "KeyZ" -> KeyCode.KeyZ
    "Digit0" -> KeyCode.Digit0
    "Digit1" -> KeyCode.Digit1
    "Digit2" -> KeyCode.Digit2
    "Digit3" -> KeyCode.Digit3
    "Digit4" -> KeyCode.Digit4
    "Digit5" -> KeyCode.Digit5
    "Digit6" -> KeyCode.Digit6
    "Digit7" -> KeyCode.Digit7
    "Digit8" -> KeyCode.Digit8
    "Digit9" -> KeyCode.Digit9
    "F1" -> KeyCode.F1
    "F2" -> KeyCode.F2
    "F3" -> KeyCode.F3
    "F4" -> KeyCode.F4
    "F5" -> KeyCode.F5
    "F6" -> KeyCode.F6
    "F7" -> KeyCode.F7
    "F8" -> KeyCode.F8
    "F9" -> KeyCode.F9
    "F10" -> KeyCode.F10
    "F11" -> KeyCode.F11
    "F12" -> KeyCode.F12
    "Space" -> KeyCode.Space
    "Enter" -> KeyCode.Enter
    "Escape" -> KeyCode.Escape
    "Backspace" -> KeyCode.Backspace
    "Tab" -> KeyCode.Tab
    "ArrowUp" -> KeyCode.ArrowUp
    "ArrowDown" -> KeyCode.ArrowDown
    "ArrowLeft" -> KeyCode.ArrowLeft
    "ArrowRight" -> KeyCode.ArrowRight
    "ShiftLeft" -> KeyCode.ShiftLeft
    "ShiftRight" -> KeyCode.ShiftRight
    "ControlLeft" -> KeyCode.ControlLeft
    "ControlRight" -> KeyCode.ControlRight
    "AltLeft" -> KeyCode.AltLeft
    "AltRight" -> KeyCode.AltRight
    "MetaLeft" -> KeyCode.MetaLeft
    "MetaRight" -> KeyCode.MetaRight
    else -> null
}

/**
 * Builds a [WebModifiers] from the boolean fields of a DOM `KeyboardEvent` or `MouseEvent`.
 *
 * @param shiftKey  Value of `event.shiftKey`.
 * @param ctrlKey   Value of `event.ctrlKey`.
 * @param altKey    Value of `event.altKey`.
 * @param metaKey   Value of `event.metaKey`.
 * @return [WebModifiers] with the corresponding bits set.
 */
internal fun domModifiers(
    shiftKey: Boolean,
    ctrlKey: Boolean,
    altKey: Boolean,
    metaKey: Boolean,
): WebModifiers {
    var mods = WebModifiers.NONE
    if (shiftKey) mods = mods + WebModifiers.SHIFT
    if (ctrlKey)  mods = mods + WebModifiers.CTRL
    if (altKey)   mods = mods + WebModifiers.ALT
    if (metaKey)  mods = mods + WebModifiers.META
    return mods
}

internal fun domKeyboardModifiers(
    shiftKey: Boolean,
    ctrlKey: Boolean,
    altKey: Boolean,
    metaKey: Boolean,
): KeyboardModifiers {
    var mods = KeyboardModifiers.NONE
    if (shiftKey) mods += KeyboardModifiers.Shift
    if (ctrlKey) mods += KeyboardModifiers.Ctrl
    if (altKey) mods += KeyboardModifiers.Alt
    if (metaKey) mods += KeyboardModifiers.Meta
    return mods
}

internal fun domKeyEvent(
    code: String,
    key: String,
    eventType: String,
    shiftKey: Boolean,
    ctrlKey: Boolean,
    altKey: Boolean,
    metaKey: Boolean,
    repeat: Boolean,
): KeyEvent {
    val mappedCode = domCodeToKeyCode(code)
    val native = NativeKeyInfo(
        platform = KeyPlatform.Web,
        keyCode = code,
        keyValue = key,
        nativeCode = NativeKeyCode.Web(code),
        nativeKey = NativeLogicalKey.Web(key),
    )
    val logicalKey = when {
        key.length == 1 -> LogicalKey.Character(key)
        mappedCode != null -> mappedCode.defaultLogicalKey()
        else -> LogicalKey.Unidentified(native)
    }
    return KeyEvent(
        physicalKey = mappedCode?.let(PhysicalKey::Code) ?: PhysicalKey.Native(NativeKeyCode.Web(code)),
        logicalKey = logicalKey,
        state = domCoreKeyStateFromEventType(eventType),
        modifiers = domKeyboardModifiers(shiftKey, ctrlKey, altKey, metaKey),
        repeat = repeat,
        text = key.takeIf { it.length == 1 } ?: mappedCode?.defaultText(),
        textWithAllModifiers = key.takeIf { it.length == 1 } ?: mappedCode?.defaultText(),
        keyWithoutModifiers = mappedCode?.defaultText(),
        native = native,
    )
}

/**
 * Converts a DOM button index (`MouseEvent.button`) into a Kadre [WebMouseButton].
 *
 * Standard DOM mapping:
 * - `0` → left button
 * - `1` → middle button (wheel)
 * - `2` → right button
 * - `≥3` → additional buttons ([WebMouseButton.Other])
 *
 * @param button Value of `MouseEvent.button` (Short on the DOM side).
 * @return The corresponding [WebMouseButton].
 */
internal fun domButtonToMouseButton(button: Short): WebMouseButton = when (button.toInt()) {
    0    -> WebMouseButton.Left
    1    -> WebMouseButton.Middle
    2    -> WebMouseButton.Right
    else -> WebMouseButton.Other(button.toInt())
}

/**
 * Derives the [WebKeyState] from the DOM event type (`"keydown"` or `"keyup"`).
 *
 * @param eventType Value of `event.type` (`"keydown"` or `"keyup"`).
 * @return [WebKeyState.Pressed] for `"keydown"`, [WebKeyState.Released] otherwise.
 */
internal fun domKeyStateFromEventType(eventType: String): WebKeyState = when (eventType) {
    "keydown"     -> WebKeyState.Pressed
    "pointerdown" -> WebKeyState.Pressed
    else          -> WebKeyState.Released
}

internal fun domCoreKeyStateFromEventType(eventType: String): KeyState = when (eventType) {
    "keydown" -> KeyState.Pressed
    else -> KeyState.Released
}

/**
 * Maps a DOM touch event type into a [WebTouchPhase].
 *
 * @param eventType Value of `event.type` (`"touchstart"`, `"touchmove"`,
 *   `"touchend"` or `"touchcancel"`).
 * @return The corresponding [WebTouchPhase]; unrecognized types fall back to
 *   [WebTouchPhase.Cancelled] (the safest "release" semantics).
 */
internal fun domTouchTypeToPhase(eventType: String): WebTouchPhase = when (eventType) {
    "touchstart"  -> WebTouchPhase.Started
    "touchmove"   -> WebTouchPhase.Moved
    "touchend"    -> WebTouchPhase.Ended
    "touchcancel" -> WebTouchPhase.Cancelled
    else          -> WebTouchPhase.Cancelled
}

/**
 * Maps a DOM `KeyboardEvent.location` value to a [WebKeyLocation].
 *
 * Standard DOM values: 0=Standard, 1=Left, 2=Right, 3=Numpad.
 *
 * @param location Value of `KeyboardEvent.location`.
 * @return Corresponding [WebKeyLocation].
 */
internal fun domLocationToKeyLocation(location: Int): WebKeyLocation = when (location) {
    1 -> WebKeyLocation.Left
    2 -> WebKeyLocation.Right
    3 -> WebKeyLocation.Numpad
    else -> WebKeyLocation.Standard
}

/**
 * Returns the printable text for a DOM key string (from `KeyboardEvent.key`).
 *
 * Returns the string if it is a single printable character; null otherwise
 * (e.g. "Shift", "ArrowUp", "Enter" are not printable text).
 *
 * @param domKey Value of `KeyboardEvent.key`.
 * @return The character if printable, null otherwise.
 */
internal fun domKeyToText(domKey: String): String? {
    // A named key (e.g. "Shift", "ArrowUp", "F1") has length > 1 in most cases,
    // but some named keys have length 1 (e.g. "a", " "). We only return text for
    // single printable characters.
    return if (domKey.length == 1 && domKey[0] >= ' ') domKey else null
}

/**
 * Normalizes a DOM wheel delta into logical pixels.
 *
 * The DOM exposes three scroll modes:
 * - `0` (DOM_DELTA_PIXEL): the delta is already in pixels — no transformation.
 * - `1` (DOM_DELTA_LINE) : the delta is in lines — multiply by 16 px.
 * - `2` (DOM_DELTA_PAGE) : the delta is in pages — multiply by 600 px.
 *
 * @param delta     Raw value of `WheelEvent.deltaX` or `deltaY`.
 * @param deltaMode Value of `WheelEvent.deltaMode` (0, 1 or 2).
 * @return Delta normalized into logical pixels.
 */
internal fun normalizeWheelDelta(delta: Double, deltaMode: Int): Double {
    val scale = when (deltaMode) {
        1 -> 16.0
        2 -> 600.0
        else -> 1.0
    }
    return delta * scale
}

// ---------------------------------------------------------------------------
// WebWindowEvent → WindowEvent bridge
// ---------------------------------------------------------------------------

/**
 * Converts a [WebWindowEvent] to the canonical kadre-core [WindowEvent].
 *
 * This bridge is needed because kadre-web-common uses its own Web* mirror types
 * (defined in [WebTypes]) while the [ApplicationHandler] interface uses kadre-core
 * sealed types. When kadre-core gains JS/wasmJs targets (ticket #32), these mirror
 * types will be replaced with typealiases and this mapper can be removed.
 */
internal fun WebWindowEvent.toWindowEvent(): WindowEvent = when (this) {
    WebWindowEvent.CloseRequested -> WindowEvent.CloseRequested
    is WebWindowEvent.Resized -> WindowEvent.Resized(PhysicalSize(width, height))
    is WebWindowEvent.KeyInput -> WindowEvent.KeyInput(event, deviceId = null)
    is WebWindowEvent.PointerMoved -> WindowEvent.PointerMoved(
        deviceId = null,
        position = PhysicalPosition(x, y),
        primary = true,
        source = PointerSource.Mouse,
    )
    WebWindowEvent.PointerEntered -> WindowEvent.PointerEntered(null, PhysicalPosition(0.0, 0.0), primary = true, kind = PointerKind.Mouse)
    WebWindowEvent.PointerLeft -> WindowEvent.PointerLeft(null, position = null, primary = true, kind = PointerKind.Mouse)
    is WebWindowEvent.MouseInput -> WindowEvent.PointerButton(
        deviceId = null,
        state = state.toKeyState(),
        position = PhysicalPosition(0.0, 0.0),
        primary = true,
        button = ButtonSource.Mouse(button.toMouseButton()),
    )
    is WebWindowEvent.MouseWheel -> WindowEvent.MouseWheel(null, deltaX, deltaY, TouchPhase.Moved)
    is WebWindowEvent.Focused -> WindowEvent.Focused(gained)
    is WebWindowEvent.WebOccluded -> WindowEvent.Occluded(occluded)
    is WebWindowEvent.Touch -> {
        val location = PhysicalPosition(x, y)
        val fingerId = FingerId(id)
        when (phase.toTouchPhase()) {
            TouchPhase.Started -> WindowEvent.PointerButton(null, KeyState.Pressed, location, primary = id == 0L, button = ButtonSource.Touch(fingerId))
            TouchPhase.Moved -> WindowEvent.PointerMoved(null, location, primary = id == 0L, source = PointerSource.Touch(fingerId))
            TouchPhase.Ended -> WindowEvent.PointerButton(null, KeyState.Released, location, primary = id == 0L, button = ButtonSource.Touch(fingerId))
            TouchPhase.Cancelled -> WindowEvent.PointerLeft(null, location, primary = id == 0L, kind = PointerKind.Touch)
        }
    }
    is WebWindowEvent.ScaleFactorChanged -> WindowEvent.ScaleFactorChanged(factor)
    WebWindowEvent.RedrawRequested -> WindowEvent.RedrawRequested
    WebWindowEvent.Destroyed -> WindowEvent.Destroyed
    is WebWindowEvent.ModifiersChanged -> WindowEvent.ModifiersChanged(
        KeyboardModifierState(logical = modifiers.toKeyboardModifiers()),
    )
    is WebWindowEvent.Ime -> WindowEvent.Ime(ime.toCoreImeEvent())
    is WebWindowEvent.DragEntered -> WindowEvent.DragEntered(
        position = PhysicalPosition(x, y),
        paths = files,
    )
    is WebWindowEvent.DragMoved -> WindowEvent.DragMoved(
        position = PhysicalPosition(x, y),
    )
    is WebWindowEvent.DragDropped -> WindowEvent.DragDropped(
        position = PhysicalPosition(x, y),
        paths = files,
    )
    WebWindowEvent.DragLeft -> WindowEvent.DragLeft
    is WebWindowEvent.WebGestureStart -> WindowEvent.PinchGesture(
        deviceId = null,
        delta = scale.toDouble(),
        phase = TouchPhase.Started,
    )
    is WebWindowEvent.WebGestureChange -> WindowEvent.PinchGesture(
        deviceId = null,
        delta = scale.toDouble(),
        phase = TouchPhase.Moved,
    )
    is WebWindowEvent.WebGestureEnd -> WindowEvent.PinchGesture(
        deviceId = null,
        delta = scale.toDouble(),
        phase = TouchPhase.Ended,
    )
    is WebWindowEvent.WebPinchZoom -> WindowEvent.PinchGesture(
        deviceId = null,
        delta = delta.toDouble(),
        phase = TouchPhase.Moved,
    )
}

private fun WebImeEvent.toCoreImeEvent(): WindowEvent.Ime.ImeEvent = when (this) {
    WebImeEvent.Enabled -> WindowEvent.Ime.ImeEvent.Enabled
    is WebImeEvent.Preedit -> WindowEvent.Ime.ImeEvent.Preedit(text, cursorRange)
    is WebImeEvent.Commit -> WindowEvent.Ime.ImeEvent.Commit(text)
    is WebImeEvent.DeleteSurrounding -> WindowEvent.Ime.ImeEvent.DeleteSurrounding(beforeBytes, afterBytes)
    WebImeEvent.Disabled -> WindowEvent.Ime.ImeEvent.Disabled
}

private fun WebKeyState.toKeyState(): KeyState = when (this) {
    WebKeyState.Pressed  -> KeyState.Pressed
    WebKeyState.Released -> KeyState.Released
}

private fun WebModifiers.toKeyboardModifiers(): KeyboardModifiers = KeyboardModifiers(bits)

private fun WebMouseButton.toMouseButton(): MouseButton = when (this) {
    WebMouseButton.Left   -> MouseButton.Left
    WebMouseButton.Right  -> MouseButton.Right
    WebMouseButton.Middle -> MouseButton.Middle
    is WebMouseButton.Other -> MouseButton.Other(button)
}

private fun WebTouchPhase.toTouchPhase(): TouchPhase = when (this) {
    WebTouchPhase.Started   -> TouchPhase.Started
    WebTouchPhase.Moved     -> TouchPhase.Moved
    WebTouchPhase.Ended     -> TouchPhase.Ended
    WebTouchPhase.Cancelled -> TouchPhase.Cancelled
}
