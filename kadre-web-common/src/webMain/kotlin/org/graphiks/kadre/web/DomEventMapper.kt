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

import org.graphiks.kadre.core.Key
import org.graphiks.kadre.core.KeyLocation
import org.graphiks.kadre.core.KeyState
import org.graphiks.kadre.core.Modifiers
import org.graphiks.kadre.core.ButtonSource
import org.graphiks.kadre.core.FingerId
import org.graphiks.kadre.core.MouseButton
import org.graphiks.kadre.core.PhysicalPosition
import org.graphiks.kadre.core.PhysicalSize
import org.graphiks.kadre.core.PointerKind
import org.graphiks.kadre.core.PointerSource
import org.graphiks.kadre.core.TouchPhase
import org.graphiks.kadre.core.WindowEvent

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
    is WebWindowEvent.KeyboardInput -> WindowEvent.KeyboardInput(
        deviceId = null,
        key = key.toKey(),
        state = state.toKeyState(),
        modifiers = modifiers.toModifiers(),
        isRepeat = isRepeat,
        text = text,
        location = location.toKeyLocation(),
        scanCode = scanCode?.hashCode(),  // DOM code string → stable Int hash for cross-platform compat
    )
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
    is WebWindowEvent.ModifiersChanged -> WindowEvent.ModifiersChanged(modifiers.toModifiers())
}

private fun WebKey.toKey(): Key = when (this) {
    WebKey.A -> Key.A; WebKey.B -> Key.B; WebKey.C -> Key.C; WebKey.D -> Key.D
    WebKey.E -> Key.E; WebKey.F -> Key.F; WebKey.G -> Key.G; WebKey.H -> Key.H
    WebKey.I -> Key.I; WebKey.J -> Key.J; WebKey.K -> Key.K; WebKey.L -> Key.L
    WebKey.M -> Key.M; WebKey.N -> Key.N; WebKey.O -> Key.O; WebKey.P -> Key.P
    WebKey.Q -> Key.Q; WebKey.R -> Key.R; WebKey.S -> Key.S; WebKey.T -> Key.T
    WebKey.U -> Key.U; WebKey.V -> Key.V; WebKey.W -> Key.W; WebKey.X -> Key.X
    WebKey.Y -> Key.Y; WebKey.Z -> Key.Z
    WebKey.Digit0 -> Key.Digit0; WebKey.Digit1 -> Key.Digit1
    WebKey.Digit2 -> Key.Digit2; WebKey.Digit3 -> Key.Digit3
    WebKey.Digit4 -> Key.Digit4; WebKey.Digit5 -> Key.Digit5
    WebKey.Digit6 -> Key.Digit6; WebKey.Digit7 -> Key.Digit7
    WebKey.Digit8 -> Key.Digit8; WebKey.Digit9 -> Key.Digit9
    WebKey.F1  -> Key.F1;  WebKey.F2  -> Key.F2;  WebKey.F3  -> Key.F3
    WebKey.F4  -> Key.F4;  WebKey.F5  -> Key.F5;  WebKey.F6  -> Key.F6
    WebKey.F7  -> Key.F7;  WebKey.F8  -> Key.F8;  WebKey.F9  -> Key.F9
    WebKey.F10 -> Key.F10; WebKey.F11 -> Key.F11; WebKey.F12 -> Key.F12
    WebKey.Space     -> Key.Space;     WebKey.Enter  -> Key.Enter
    WebKey.Escape    -> Key.Escape;    WebKey.Backspace -> Key.Backspace
    WebKey.Tab       -> Key.Tab
    WebKey.ArrowUp   -> Key.ArrowUp;   WebKey.ArrowDown  -> Key.ArrowDown
    WebKey.ArrowLeft -> Key.ArrowLeft; WebKey.ArrowRight -> Key.ArrowRight
    WebKey.ShiftLeft    -> Key.ShiftLeft;    WebKey.ShiftRight   -> Key.ShiftRight
    WebKey.ControlLeft  -> Key.ControlLeft;  WebKey.ControlRight -> Key.ControlRight
    WebKey.AltLeft      -> Key.AltLeft;      WebKey.AltRight     -> Key.AltRight
    WebKey.MetaLeft     -> Key.MetaLeft;     WebKey.MetaRight    -> Key.MetaRight
    WebKey.Unknown -> Key.Unknown
}

private fun WebKeyState.toKeyState(): KeyState = when (this) {
    WebKeyState.Pressed  -> KeyState.Pressed
    WebKeyState.Released -> KeyState.Released
}

private fun WebModifiers.toModifiers(): Modifiers = Modifiers(bits)

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

private fun WebKeyLocation.toKeyLocation(): KeyLocation = when (this) {
    WebKeyLocation.Standard -> KeyLocation.Standard
    WebKeyLocation.Left     -> KeyLocation.Left
    WebKeyLocation.Right    -> KeyLocation.Right
    WebKeyLocation.Numpad   -> KeyLocation.Numpad
}
