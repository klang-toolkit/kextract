/**
 * Mapper from Wayland touch events to kadre-core [WindowEvent.Touch].
 *
 * Wayland sends touch events via the wl_touch interface with the callbacks:
 *  - wl_touch.down   → [WindowEvent.Touch] with [TouchPhase.Started]
 *  - wl_touch.up     → [WindowEvent.Touch] with [TouchPhase.Ended]
 *  - wl_touch.motion → [WindowEvent.Touch] with [TouchPhase.Moved]
 *  - wl_touch.frame  → no-op (batching hint; we dispatch eagerly)
 *  - wl_touch.cancel → [WindowEvent.Touch] with [TouchPhase.Cancelled] for all active contacts
 *
 * ## wl_fixed coordinates
 * Wayland uses wl_fixed_t (24.8 fixed-point integer) for touch positions.
 * Conversion: `wlFixedToDouble(wlFixed)` — the same helper used by [WaylandMouseMapper].
 *
 * ## Touch id
 * `wl_touch.down` assigns a stable integer id to each new contact; this id is
 * reused in subsequent `motion` and `up/cancel` events for the same finger.
 * We map it directly to [WindowEvent.Touch.id] (Long).
 *
 * WaylandTouchMapper — wl_touch events → WindowEvent.Touch.
 */
package org.graphiks.kadre.wayland

import org.graphiks.kadre.core.PhysicalPosition
import org.graphiks.kadre.core.TouchPhase
import org.graphiks.kadre.core.WindowEvent

// ---------------------------------------------------------------------------
// Conversion functions
// ---------------------------------------------------------------------------

/**
 * Builds a [WindowEvent.Touch] for a wl_touch.down event.
 *
 * @param id     Contact identifier assigned by the compositor.
 * @param xFixed X coordinate in wl_fixed_t.
 * @param yFixed Y coordinate in wl_fixed_t.
 * @return The corresponding touch started event.
 */
fun mapWaylandTouchDown(id: Int, xFixed: Int, yFixed: Int): WindowEvent.Touch =
    WindowEvent.Touch(
        phase    = TouchPhase.Started,
        location = PhysicalPosition(wlFixedToDouble(xFixed), wlFixedToDouble(yFixed)),
        id       = id.toLong(),
    )

/**
 * Builds a [WindowEvent.Touch] for a wl_touch.up event.
 *
 * wl_touch.up does not carry coordinates; [location] defaults to (0.0, 0.0).
 * The caller may substitute the last known position from [mapWaylandTouchDown]
 * or [mapWaylandTouchMotion] if needed.
 *
 * @param id Contact identifier.
 * @return The corresponding touch ended event.
 */
fun mapWaylandTouchUp(id: Int): WindowEvent.Touch =
    WindowEvent.Touch(
        phase    = TouchPhase.Ended,
        location = PhysicalPosition(0.0, 0.0),
        id       = id.toLong(),
    )

/**
 * Builds a [WindowEvent.Touch] for a wl_touch.motion event.
 *
 * @param id     Contact identifier.
 * @param xFixed X coordinate in wl_fixed_t.
 * @param yFixed Y coordinate in wl_fixed_t.
 * @return The corresponding touch moved event.
 */
fun mapWaylandTouchMotion(id: Int, xFixed: Int, yFixed: Int): WindowEvent.Touch =
    WindowEvent.Touch(
        phase    = TouchPhase.Moved,
        location = PhysicalPosition(wlFixedToDouble(xFixed), wlFixedToDouble(yFixed)),
        id       = id.toLong(),
    )

/**
 * Builds a [WindowEvent.Touch] with [TouchPhase.Cancelled] for a given contact id.
 *
 * On wl_touch.cancel the compositor cancels ALL active contacts simultaneously.
 * The listener iterates over its tracked ids and calls this function for each.
 *
 * @param id Contact identifier.
 * @return The corresponding touch cancelled event.
 */
fun mapWaylandTouchCancel(id: Int): WindowEvent.Touch =
    WindowEvent.Touch(
        phase    = TouchPhase.Cancelled,
        location = PhysicalPosition(0.0, 0.0),
        id       = id.toLong(),
    )
