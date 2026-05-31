/**
 * X11 mapper for window draw/lifecycle events.
 *
 * Converts Expose, ConfigureNotify and ClientMessage events into
 * kadre [org.graphiks.kadre.core.WindowEvent]s.
 *
 * ## Struct XExposeEvent (type = 12 Expose)
 * ```
 *  0 : type   (int, 4)
 * ...
 * 40 : count  (int, 4) — 0 = last expose of the sequence
 * ```
 *
 * ## Struct XConfigureEvent (type = 22 ConfigureNotify)
 * ```
 *  0 : type         (int,  4)
 * ...
 * 24 : x            (int,  4)
 * 28 : y            (int,  4)
 * 32 : width        (int,  4)
 * 36 : height       (int,  4)
 * 40 : border_width (int,  4)
 * ```
 *
 * ## Struct XClientMessageEvent (type = 33 ClientMessage)
 * ```
 *  0 : type         (int,  4)
 * ...
 * 56 : message_type (Atom = long, 8)
 * 64 : data.l[0]   (long, 8) — first atom of the message
 * ```
 *
 * ## Reading Xft.dpi
 * The [readXftDpi] function uses XResourceManagerString to read the X11
 * resource database and extract "Xft.dpi: <value>".
 *
 * X11DrawMapper.
 */
package org.graphiks.kadre.x11

import org.graphiks.kadre.core.PhysicalPosition
import org.graphiks.kadre.core.PhysicalSize
import org.graphiks.kadre.core.WindowEvent
import java.lang.foreign.MemorySegment
import java.lang.foreign.ValueLayout

// ── XExposeEvent offsets ──────────────────────────────────────────────────────

private const val EXPOSE_OFFSET_COUNT: Long = 40L

// ── XConfigureEvent offsets ───────────────────────────────────────────────────

private const val CONFIGURE_OFFSET_X: Long      = 24L
private const val CONFIGURE_OFFSET_Y: Long      = 28L
private const val CONFIGURE_OFFSET_WIDTH: Long  = 32L
private const val CONFIGURE_OFFSET_HEIGHT: Long = 36L

// ── XClientMessageEvent offsets ───────────────────────────────────────────────

private const val CLIENT_MSG_OFFSET_DATA_L0: Long = 64L

/**
 * Stateless mapper for Expose, ConfigureNotify and ClientMessage events.
 */
object X11DrawMapper {

    /**
     * Converts a window-lifecycle XEvent into a kadre [WindowEvent].
     *
     * @param eventSegment  96-byte segment containing the XEvent.
     * @param eventType     X11 event type (extracted beforehand at offset 0).
     * @param window        Associated X11 window (for updating the inner size).
     * @param wmDeleteWindow The session's WM_DELETE_WINDOW atom.
     * @return The corresponding [WindowEvent], or null if the type/condition does not match.
     */
    fun fromXEvent(
        eventSegment: MemorySegment,
        eventType: Int,
        window: X11Window?,
        wmDeleteWindow: Long,
    ): WindowEvent? {
        return when (eventType) {
            Expose         -> handleExpose(eventSegment)
            ConfigureNotify -> handleConfigureNotify(eventSegment, window)
            ClientMessage   -> handleClientMessage(eventSegment, wmDeleteWindow)
            else            -> null
        }
    }

    // ── Private helpers ─────────────────────────────────────────────────────────

    /**
     * Handles an Expose event.
     *
     * X11 may send several consecutive Expose events for the same
     * region. The `count` field indicates how many additional Expose events will
     * follow. We emit [WindowEvent.RedrawRequested] only when `count == 0`
     * (i.e. the last of the sequence), to avoid intermediate redraws.
     *
     * @param eventSegment XEvent segment.
     */
    private fun handleExpose(eventSegment: MemorySegment): WindowEvent? {
        val count = eventSegment.get(ValueLayout.JAVA_INT, EXPOSE_OFFSET_COUNT)
        return if (count == 0) WindowEvent.RedrawRequested else null
    }

    /**
     * Handles a ConfigureNotify event.
     *
     * Emits:
     * - [WindowEvent.Resized] if the size changed compared to [X11Window.innerSize].
     * - [WindowEvent.Moved] with the new position (x, y).
     *
     * Note: we return only the first significant event. The event loop
     * may call this function twice if needed, or we could
     * return a list. To keep it simple, we prioritize Resized over Moved.
     *
     * @param eventSegment XEvent segment.
     * @param window       X11 window whose size is updated.
     */
    private fun handleConfigureNotify(
        eventSegment: MemorySegment,
        window: X11Window?,
    ): WindowEvent? {
        val x      = eventSegment.get(ValueLayout.JAVA_INT, CONFIGURE_OFFSET_X)
        val y      = eventSegment.get(ValueLayout.JAVA_INT, CONFIGURE_OFFSET_Y)
        val width  = eventSegment.get(ValueLayout.JAVA_INT, CONFIGURE_OFFSET_WIDTH)
        val height = eventSegment.get(ValueLayout.JAVA_INT, CONFIGURE_OFFSET_HEIGHT)

        // Check whether the size changed compared to the window's known size
        val sizeChanged = window == null ||
            window.innerSize.width  != width ||
            window.innerSize.height != height

        // Update the window's inner size
        window?.onConfigureNotify(width, height)

        return when {
            sizeChanged && width > 0 && height > 0 ->
                WindowEvent.Resized(PhysicalSize(width, height))
            else ->
                WindowEvent.Moved(PhysicalPosition(x, y))
        }
    }

    /**
     * Handles a ClientMessage event.
     *
     * If data.l[0] matches the WM_DELETE_WINDOW atom, emits
     * [WindowEvent.CloseRequested] to request a clean close.
     *
     * @param eventSegment   XEvent segment.
     * @param wmDeleteWindow WM_DELETE_WINDOW atom (Long, X11 unsigned long).
     */
    private fun handleClientMessage(
        eventSegment: MemorySegment,
        wmDeleteWindow: Long,
    ): WindowEvent? {
        val atom = eventSegment.get(ValueLayout.JAVA_LONG, CLIENT_MSG_OFFSET_DATA_L0)
        return if (atom == wmDeleteWindow) WindowEvent.CloseRequested else null
    }
}

// ── Reading Xft.dpi ───────────────────────────────────────────────────────────

/**
 * Reads the DPI factor from the X11 resource database.
 *
 * Uses [xResourceManagerString] to obtain the X server's RESOURCE_MANAGER
 * string, then looks for the "Xft.dpi:" property and returns its value
 * as a [Double].
 *
 * The resource string typically has the form:
 * ```
 * Xft.dpi:	96
 * Xft.antialias:	1
 * ...
 * ```
 *
 * @param displayPtr Address of the Display* (Long, opaque).
 * @return DPI value divided by 96.0 (e.g. 1.0 for 96 dpi, 2.0 for 192 dpi),
 *         or 1.0 if XResourceManagerString returns NULL or if Xft.dpi is absent.
 */
fun readXftDpi(displayPtr: Long): Double {
    val handle = xResourceManagerString ?: return 1.0
    val display = MemorySegment.ofAddress(displayPtr)
    return try {
        val result = handle.invokeExact(display) as MemorySegment
        if (result == MemorySegment.NULL) return 1.0

        // Read the null-terminated string returned by XResourceManagerString
        val resourceString = result.reinterpret(Long.MAX_VALUE)
            .getString(0, Charsets.UTF_8)

        // Look for "Xft.dpi:" in the resource string
        parseXftDpi(resourceString)
    } catch (_: Throwable) {
        1.0
    }
}

/**
 * Extracts the Xft.dpi value from an X11 resource string.
 *
 * Looks for the pattern "Xft.dpi:" followed by a number (integer or decimal).
 * The value is returned divided by 96.0 to obtain a scale factor.
 *
 * @param resourceString Content of the RESOURCE_MANAGER string.
 * @return DPI scale factor (value/96), or 1.0 if absent/invalid.
 */
internal fun parseXftDpi(resourceString: String): Double {
    for (line in resourceString.lineSequence()) {
        val trimmed = line.trim()
        if (trimmed.startsWith("Xft.dpi:")) {
            val value = trimmed.removePrefix("Xft.dpi:").trim().toDoubleOrNull()
            if (value != null && value > 0.0) {
                return value / 96.0
            }
        }
    }
    return 1.0
}
