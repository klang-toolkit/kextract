/**
 * Mapper from Wayland pointer events to kadre-core [WindowEvent]s.
 *
 * Wayland sends mouse events via the wl_pointer interface with the callbacks:
 *  - wl_pointer.enter  → [WindowEvent.PointerEntered]
 *  - wl_pointer.leave  → [WindowEvent.PointerLeft]
 *  - wl_pointer.motion → [WindowEvent.PointerMoved]
 *  - wl_pointer.button → [WindowEvent.PointerButton]
 *  - wl_pointer.axis   → [WindowEvent.MouseWheel]
 *
 * ## wl_fixed coordinates
 * Wayland uses the wl_fixed_t type (24.8 fixed-point integer) for positions.
 * Conversion: real_value = wl_fixed_t / 256.0
 *
 * ## Mouse buttons (Linux evdev, BTN_*)
 *  - BTN_LEFT   = 272
 *  - BTN_RIGHT  = 273
 *  - BTN_MIDDLE = 274
 *  - BTN_SIDE   = 275 (forward side button)
 *  - BTN_EXTRA  = 276 (back side button)
 *
 * ## wl_pointer axes
 *  - WL_POINTER_AXIS_VERTICAL_SCROLL   = 0
 *  - WL_POINTER_AXIS_HORIZONTAL_SCROLL = 1
 *
 * WaylandMouseMapper — wl_pointer events → WindowEvent.
 */
package org.graphiks.kadre.wayland

import org.graphiks.kadre.core.KeyState
import org.graphiks.kadre.core.ButtonSource
import org.graphiks.kadre.core.MouseButton
import org.graphiks.kadre.core.PhysicalPosition
import org.graphiks.kadre.core.PointerSource
import org.graphiks.kadre.core.TouchPhase
import org.graphiks.kadre.core.WindowEvent

// ---------------------------------------------------------------------------
// Linux evdev BTN_* constants
// ---------------------------------------------------------------------------

/** BTN_LEFT (left button): Linux evdev code 272. */
internal const val BTN_LEFT: Int = 272

/** BTN_RIGHT (right button): Linux evdev code 273. */
internal const val BTN_RIGHT: Int = 273

/** BTN_MIDDLE (middle button): Linux evdev code 274. */
internal const val BTN_MIDDLE: Int = 274

/** BTN_SIDE (forward side button): Linux evdev code 275. */
internal const val BTN_SIDE: Int = 275

/** BTN_EXTRA (back side button): Linux evdev code 276. */
internal const val BTN_EXTRA: Int = 276

// ---------------------------------------------------------------------------
// wl_pointer axes constants
// ---------------------------------------------------------------------------

/** wl_pointer_axis: vertical scroll. */
internal const val WL_POINTER_AXIS_VERTICAL_SCROLL: Int = 0

/** wl_pointer_axis: horizontal scroll. */
internal const val WL_POINTER_AXIS_HORIZONTAL_SCROLL: Int = 1

// ---------------------------------------------------------------------------
// wl_pointer button state constants
// ---------------------------------------------------------------------------

/** wl_pointer_button_state: button released. */
internal const val WL_POINTER_BUTTON_STATE_RELEASED: Int = 0

/** wl_pointer_button_state: button pressed. */
internal const val WL_POINTER_BUTTON_STATE_PRESSED: Int = 1

// ---------------------------------------------------------------------------
// Conversion functions
// ---------------------------------------------------------------------------

/**
 * Converts a wl_fixed_t value into a Double.
 *
 * wl_fixed_t is a 24.8 fixed-point integer: the real value is `wlFixed / 256.0`.
 *
 * @param wlFixed Integer wl_fixed_t value received in wl_pointer callbacks.
 * @return The real coordinate in physical pixels.
 */
fun wlFixedToDouble(wlFixed: Int): Double = wlFixed / 256.0

/**
 * Converts a Linux evdev button code into a kadre [MouseButton].
 *
 * @param button Linux evdev code (BTN_LEFT=272, BTN_RIGHT=273, etc.).
 * @return The corresponding logical mouse button.
 */
fun linuxButtonToMouseButton(button: Int): MouseButton = when (button) {
    BTN_LEFT   -> MouseButton.Left
    BTN_RIGHT  -> MouseButton.Right
    BTN_MIDDLE -> MouseButton.Middle
    else       -> MouseButton.Other(button)
}

/**
 * Converts a wl_pointer_button_state value into a kadre [KeyState].
 *
 * @param state Wayland state (0 = released, 1 = pressed).
 * @return [KeyState.Pressed] or [KeyState.Released].
 */
fun waylandButtonStateToKeyState(state: Int): KeyState = when (state) {
    WL_POINTER_BUTTON_STATE_RELEASED -> KeyState.Released
    else                              -> KeyState.Pressed
}

// ---------------------------------------------------------------------------
// WindowEvent builders
// ---------------------------------------------------------------------------

/**
 * Builds a [WindowEvent.PointerMoved] from a wl_pointer.motion event.
 *
 * @param xFixed X coordinate in wl_fixed_t.
 * @param yFixed Y coordinate in wl_fixed_t.
 * @return The pointer move event.
 */
fun mapWaylandPointerMotion(xFixed: Int, yFixed: Int): WindowEvent.PointerMoved =
    WindowEvent.PointerMoved(
        deviceId = null,
        position = PhysicalPosition(
            x = wlFixedToDouble(xFixed),
            y = wlFixedToDouble(yFixed),
        ),
        primary = true,
        source = PointerSource.Mouse,
    )

/**
 * Builds a [WindowEvent.PointerButton] from a wl_pointer.button event.
 *
 * @param button Linux evdev button code (BTN_LEFT, BTN_RIGHT, etc.).
 * @param state  wl_pointer_button_state value (0 = released, 1 = pressed).
 * @param position Last known pointer position from wl_pointer.enter/motion.
 * @return The corresponding pointer button event.
 */
fun mapWaylandPointerButton(button: Int, state: Int, position: PhysicalPosition<Double>): WindowEvent.PointerButton =
    WindowEvent.PointerButton(
        deviceId = null,
        state = waylandButtonStateToKeyState(state),
        position = position,
        primary = true,
        button = ButtonSource.Mouse(linuxButtonToMouseButton(button)),
    )

/**
 * Builds a [WindowEvent.MouseWheel] from a wl_pointer.axis event.
 *
 * @param axis      Wayland axis (0 = vertical, 1 = horizontal).
 * @param valueFixed Scroll value in wl_fixed_t.
 * @return The corresponding mouse wheel event.
 */
fun mapWaylandPointerAxis(axis: Int, valueFixed: Int): WindowEvent.MouseWheel {
    val value = wlFixedToDouble(valueFixed)
    return when (axis) {
        WL_POINTER_AXIS_VERTICAL_SCROLL   -> WindowEvent.MouseWheel(null, deltaX = 0.0, deltaY = value, phase = TouchPhase.Moved)
        WL_POINTER_AXIS_HORIZONTAL_SCROLL -> WindowEvent.MouseWheel(null, deltaX = value, deltaY = 0.0, phase = TouchPhase.Moved)
        else                               -> WindowEvent.MouseWheel(null, deltaX = 0.0, deltaY = 0.0, phase = TouchPhase.Moved)
    }
}
