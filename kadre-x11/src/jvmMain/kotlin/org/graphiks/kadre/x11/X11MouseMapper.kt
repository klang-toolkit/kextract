/**
 * X11 mapper for mouse and focus events.
 *
 * Converts XButtonEvent, XMotionEvent, XCrossingEvent and XFocusEvent events
 * into kadre [org.graphiks.kadre.core.WindowEvent]s.
 *
 * ## Struct XButtonEvent (Linux 64-bit)
 * ```
 *  0 : type          (int,  4)
 *  8 : display       (ptr,  8)
 * 16 : window        (long, 8)
 * 24 : root          (long, 8)
 * 32 : subwindow     (long, 8)
 * 40 : time          (long, 8)
 * 48 : x             (int,  4)
 * 52 : y             (int,  4)
 * 56 : x_root        (int,  4)
 * 60 : y_root        (int,  4)
 * 64 : state         (uint, 4)
 * 68 : button        (uint, 4)  — X11 button number (1-based)
 * 72 : same_screen   (int,  4)
 * ```
 *
 * ## Struct XMotionEvent (Linux 64-bit)
 * ```
 * 48 : x     (int, 4)
 * 52 : y     (int, 4)
 * 64 : state (uint, 4)
 * ```
 *
 * ## X11 buttons
 * - 1 : left
 * - 2 : middle
 * - 3 : right
 * - 4 : scroll down
 * - 5 : scroll up
 * - 6 : scroll left
 * - 7 : scroll right
 *
 * X11MouseMapper.
 */
package org.graphiks.kadre.x11

import org.graphiks.kadre.ffi.x11.*
import org.graphiks.kadre.core.KeyState
import org.graphiks.kadre.core.ButtonSource
import org.graphiks.kadre.core.MouseButton
import org.graphiks.kadre.core.PhysicalPosition
import org.graphiks.kadre.core.PointerKind
import org.graphiks.kadre.core.PointerSource
import org.graphiks.kadre.core.TouchPhase
import org.graphiks.kadre.core.WindowEvent
import java.lang.foreign.MemorySegment
import java.lang.foreign.ValueLayout

// ── XButtonEvent / XMotionEvent offsets ───────────────────────────────────────

private const val OFFSET_X: Long      = 48L
private const val OFFSET_Y: Long      = 52L
private const val OFFSET_BUTTON: Long = 68L

// ── X11 button constants ──────────────────────────────────────────────────────

private const val BUTTON_LEFT:         Int = 1
private const val BUTTON_MIDDLE:       Int = 2
private const val BUTTON_RIGHT:        Int = 3
private const val BUTTON_SCROLL_DOWN:  Int = 4
private const val BUTTON_SCROLL_UP:    Int = 5
private const val BUTTON_SCROLL_LEFT:  Int = 6
private const val BUTTON_SCROLL_RIGHT: Int = 7

/**
 * Stateless mapper for X11 mouse events.
 *
 * Also handles pointer enter/leave events (EnterNotify,
 * LeaveNotify) and focus events (FocusIn, FocusOut).
 */
object X11MouseMapper {

    /**
     * Converts a mouse/focus XEvent into a kadre [WindowEvent].
     *
     * @param eventSegment 96-byte segment containing the XEvent.
     * @param eventType    X11 event type (extracted beforehand at offset 0).
     * @return The corresponding [WindowEvent], or null if the type is not handled.
     */
    fun fromXEvent(eventSegment: MemorySegment, eventType: Int): WindowEvent? {
        return when (eventType) {
            ButtonPress  -> handleButton(eventSegment, KeyState.Pressed)
            ButtonRelease -> handleButton(eventSegment, KeyState.Released)
            MotionNotify  -> handleMotion(eventSegment)
            EnterNotify   -> handleEnter(eventSegment)
            LeaveNotify   -> handleLeave(eventSegment)
            FocusIn       -> WindowEvent.Focused(gained = true)
            FocusOut      -> WindowEvent.Focused(gained = false)
            else          -> null
        }
    }

    // ── Private helpers ─────────────────────────────────────────────────────────

    /**
     * Converts an XButtonEvent into a [WindowEvent.PointerButton] or [WindowEvent.MouseWheel].
     *
     * Buttons 4–7 correspond to the scroll wheel. For these:
     * - we emit a [WindowEvent.MouseWheel] only on ButtonPress;
     * - we ignore the corresponding ButtonRelease (no release event
     *   for the scroll wheel on the kadre side).
     *
     * @param eventSegment XEvent segment.
     * @param state        Button state (Pressed / Released).
     */
    private fun handleButton(
        eventSegment: MemorySegment,
        state: KeyState,
    ): WindowEvent? {
        val button = eventSegment.get(ValueLayout.JAVA_INT, OFFSET_BUTTON) and 0xFFFF
        val position = eventSegment.pointerPosition()

        return when (button) {
            BUTTON_LEFT   -> pointerButton(MouseButton.Left, state, position)
            BUTTON_MIDDLE -> pointerButton(MouseButton.Middle, state, position)
            BUTTON_RIGHT  -> pointerButton(MouseButton.Right, state, position)

            // Scroll wheel buttons: emit only on Pressed
            BUTTON_SCROLL_DOWN  -> if (state == KeyState.Pressed) WindowEvent.MouseWheel(null, 0.0,  1.0, TouchPhase.Moved) else null
            BUTTON_SCROLL_UP    -> if (state == KeyState.Pressed) WindowEvent.MouseWheel(null, 0.0, -1.0, TouchPhase.Moved) else null
            BUTTON_SCROLL_LEFT  -> if (state == KeyState.Pressed) WindowEvent.MouseWheel(null, -1.0, 0.0, TouchPhase.Moved) else null
            BUTTON_SCROLL_RIGHT -> if (state == KeyState.Pressed) WindowEvent.MouseWheel(null,  1.0, 0.0, TouchPhase.Moved) else null

            // Additional buttons (8+): PointerButton.Other
            else -> pointerButton(MouseButton.Other(button), state, position)
        }
    }

    /**
     * Converts an XMotionEvent into a [WindowEvent.PointerMoved].
     *
     * @param eventSegment XEvent segment.
     */
    private fun handleMotion(eventSegment: MemorySegment): WindowEvent.PointerMoved {
        return WindowEvent.PointerMoved(
            deviceId = null,
            position = eventSegment.pointerPosition(),
            primary = true,
            source = PointerSource.Mouse,
        )
    }

    private fun handleEnter(eventSegment: MemorySegment): WindowEvent.PointerEntered =
        WindowEvent.PointerEntered(
            deviceId = null,
            position = eventSegment.pointerPosition(),
            primary = true,
            kind = PointerKind.Mouse,
        )

    private fun handleLeave(eventSegment: MemorySegment): WindowEvent.PointerLeft =
        WindowEvent.PointerLeft(
            deviceId = null,
            position = eventSegment.pointerPosition(),
            primary = true,
            kind = PointerKind.Mouse,
        )

    private fun pointerButton(
        button: MouseButton,
        state: KeyState,
        position: PhysicalPosition<Double>,
    ): WindowEvent.PointerButton =
        WindowEvent.PointerButton(
            deviceId = null,
            state = state,
            position = position,
            primary = true,
            button = ButtonSource.Mouse(button),
        )

    private fun MemorySegment.pointerPosition(): PhysicalPosition<Double> =
        PhysicalPosition(
            x = get(ValueLayout.JAVA_INT, OFFSET_X).toDouble(),
            y = get(ValueLayout.JAVA_INT, OFFSET_Y).toDouble(),
        )
}
