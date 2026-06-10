/**
 * Thread-safe proxy to the X11 event loop.
 *
 * Allows secondary threads to wake up the X11 event loop
 * via XSendEvent (synthetic ClientMessage).
 *
 * Implementation:
 * - Uses an X11 ClientMessage sent to the first available window
 *   to unblock XNextEvent.
 * - An AtomicBoolean flag avoids redundant multiple sends.
 *
 * X11EventLoopProxy — thread-safe wakeUp.
 */
package org.graphiks.kadre.x11

import org.graphiks.kadre.ffi.x11.*
import org.graphiks.kadre.core.EventLoopProxy
import java.lang.foreign.Arena
import java.lang.foreign.MemorySegment
import java.lang.foreign.ValueLayout
import java.util.concurrent.atomic.AtomicBoolean

/**
 * ClientMessage message type used for the internal wakeUp.
 *
 * Arbitrary value chosen to be distinctive. This type is not
 * an X11 atom — it is compared directly in dispatchEvent to
 * ignore wakeUp ClientMessages.
 */
private const val KADRE_WAKEUP_MESSAGE: Long = 0L  // Non-atom: wakeUp ignored

/**
 * Thread-safe proxy to an X11 event loop.
 *
 * [wakeUp] sends a synthetic X11 ClientMessage to the first available
 * window, which immediately unblocks [XNextEvent] in the main thread.
 *
 * @param loop       Target X11 event loop.
 * @param displayPtr Integer address of the X11 Display*.
 */
class X11EventLoopProxy internal constructor(
    private val loop: X11EventLoop,
    private val displayPtr: Long,
) : EventLoopProxy {

    /**
     * Anti-duplicate flag: guarantees that only a single ClientMessage is sent
     * even if wakeUp() is called from several threads simultaneously.
     *
     * Reset to false after dispatch by the main loop.
     */
    private val wakeupPending = AtomicBoolean(false)

    /**
     * Wakes up the X11 event loop by sending a synthetic ClientMessage.
     *
     * Thread-safe — can be called from any thread.
     * No-op on macOS/Windows (xSendEvent is null) or if no window is open.
     * No-op if a wakeUp is already pending processing.
     */
    override fun wakeUp() {
        if (!wakeupPending.compareAndSet(false, true)) return

        val sendHandle = xSendEvent ?: return
        val flushHandle = xFlush ?: return

        // Find a target window (the first available)
        val targetWindowId = loop.windows.values.firstOrNull()?.id?.value ?: return

        try {
            Arena.ofConfined().use { arena ->
                val displaySeg = MemorySegment.ofAddress(displayPtr)

                // Build an XClientMessageEvent (96 bytes, zero-filled)
                val event = arena.allocate(96L, 8L)
                // type = ClientMessage (33)
                event.set(ValueLayout.JAVA_INT, 0L, ClientMessage)
                // XClientMessageEvent canonical LP64 layout:
                // 16 send_event, 24 display, 32 window, 40 message_type,
                // 48 format, 56 data.l[0].
                event.set(ValueLayout.JAVA_INT, XCLIENT_SEND_EVENT_OFFSET, 1)
                event.set(ValueLayout.ADDRESS, XCLIENT_DISPLAY_OFFSET, displaySeg)
                event.set(ValueLayout.JAVA_LONG, XCLIENT_WINDOW_OFFSET, targetWindowId)
                event.set(ValueLayout.JAVA_LONG, XCLIENT_MESSAGE_TYPE_OFFSET, KADRE_WAKEUP_MESSAGE)
                event.set(ValueLayout.JAVA_INT, XCLIENT_FORMAT_OFFSET, 32)
                // data.l[0] is already zero by arena default.

                // XSendEvent(display, window, propagate=False, event_mask=0L, event)
                sendHandle.invokeExact(
                    displaySeg,      // Display*
                    targetWindowId,  // Window
                    0,               // Bool propagate = False
                    0L,              // long event_mask = 0 (ClientMessage unmasked)
                    event,           // XEvent*
                ) as Int

                flushHandle.invokeExact(displaySeg) as Int
            }
        } catch (_: Throwable) {
            // Graceful degradation — the wakeUp fails silently
            wakeupPending.set(false)
        }
    }
}
