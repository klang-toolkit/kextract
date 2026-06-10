/**
 * Mapper from Wayland touch events to kadre-core pointer events.
 *
 * Wayland sends touch events via the wl_touch interface with the callbacks:
 *  - wl_touch.down   → pointer enter + button press
 *  - wl_touch.up     → pointer button release + leave
 *  - wl_touch.motion → pointer moved
 *  - wl_touch.frame  → no-op (batching hint; we dispatch eagerly)
 *  - wl_touch.cancel → pointer button release + leave for all active contacts
 *
 * ## wl_fixed coordinates
 * Wayland uses wl_fixed_t (24.8 fixed-point integer) for touch positions.
 * Conversion: `wlFixedToDouble(wlFixed)` — the same helper used by [WaylandMouseMapper].
 *
 * ## Touch id
 * `wl_touch.down` assigns a stable integer id to each new contact; this id is
 * reused in subsequent `motion` and `up/cancel` events for the same finger.
 * We map it directly to [FingerId].
 *
 * WaylandTouchMapper — wl_touch events → pointer WindowEvents.
 */
package org.graphiks.kadre.wayland
import org.graphiks.kadre.ffi.wayland.*

import org.graphiks.kadre.core.ButtonSource
import org.graphiks.kadre.core.FingerId
import org.graphiks.kadre.core.KeyState
import org.graphiks.kadre.core.PhysicalPosition
import org.graphiks.kadre.core.PointerKind
import org.graphiks.kadre.core.PointerSource
import org.graphiks.kadre.core.WindowEvent

// ---------------------------------------------------------------------------
// Conversion functions
// ---------------------------------------------------------------------------

/**
 * Builds pointer events for a wl_touch.down event.
 *
 * @param id     Contact identifier assigned by the compositor.
 * @param xFixed X coordinate in wl_fixed_t.
 * @param yFixed Y coordinate in wl_fixed_t.
 * @return The corresponding pointer enter and press events.
 */
fun mapWaylandTouchDown(id: Int, xFixed: Int, yFixed: Int, primary: Boolean = id == 0): List<WindowEvent> {
    val fingerId = FingerId(id.toLong())
    val location = PhysicalPosition(wlFixedToDouble(xFixed), wlFixedToDouble(yFixed))
    return listOf(
        WindowEvent.PointerEntered(null, location, primary = primary, kind = PointerKind.Touch),
        WindowEvent.PointerButton(null, KeyState.Pressed, location, primary = primary, button = ButtonSource.Touch(fingerId)),
    )
}

/**
 * Builds pointer events for a wl_touch.up event.
 *
 * wl_touch.up does not carry coordinates; the caller supplies the last known position.
 *
 * @param id Contact identifier.
 * @return The corresponding pointer release and leave events.
 */
fun mapWaylandTouchUp(id: Int, location: PhysicalPosition<Double>, primary: Boolean = id == 0): List<WindowEvent> {
    val fingerId = FingerId(id.toLong())
    return listOf(
        WindowEvent.PointerButton(null, KeyState.Released, location, primary = primary, button = ButtonSource.Touch(fingerId)),
        WindowEvent.PointerLeft(null, location, primary = primary, kind = PointerKind.Touch),
    )
}

/**
 * Builds a pointer moved event for a wl_touch.motion event.
 *
 * @param id     Contact identifier.
 * @param xFixed X coordinate in wl_fixed_t.
 * @param yFixed Y coordinate in wl_fixed_t.
 * @return The corresponding pointer moved event.
 */
fun mapWaylandTouchMotion(id: Int, xFixed: Int, yFixed: Int, primary: Boolean = id == 0): WindowEvent.PointerMoved =
    WindowEvent.PointerMoved(
        deviceId = null,
        position = PhysicalPosition(wlFixedToDouble(xFixed), wlFixedToDouble(yFixed)),
        primary = primary,
        source = PointerSource.Touch(FingerId(id.toLong())),
    )

/**
 * Builds pointer cancellation events for a given contact id.
 *
 * On wl_touch.cancel the compositor cancels ALL active contacts simultaneously.
 * The listener iterates over its tracked ids and calls this function for each.
 *
 * @param id Contact identifier.
 * @return The corresponding pointer release and leave events.
 */
fun mapWaylandTouchCancel(id: Int, location: PhysicalPosition<Double>, primary: Boolean = id == 0): List<WindowEvent> =
    mapWaylandTouchUp(id, location, primary)
