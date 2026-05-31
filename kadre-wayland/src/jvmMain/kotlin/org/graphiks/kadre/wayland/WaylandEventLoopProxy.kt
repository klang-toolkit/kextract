/**
 * WaylandEventLoopProxy — thread-safe proxy to WaylandEventLoop.
 *
 * Allows secondary threads to wake up the waiting Wayland loop
 * via an eventfd (counter mode, flags=0).
 *
 * Uses an AtomicBoolean to guarantee that only a single write() is performed
 * even if wakeUp() is called from several threads simultaneously.
 * The main loop drains the counter with read() and resets the flag to false.
 *
 * WaylandEventLoop.
 */
package org.graphiks.kadre.wayland

import org.graphiks.kadre.core.EventLoopProxy
import java.lang.foreign.Arena
import java.lang.foreign.ValueLayout
import java.util.concurrent.atomic.AtomicBoolean

/**
 * Thread-safe proxy to the Wayland event loop.
 *
 * @param eventFd eventfd descriptor created by [runApp] (int ≥ 0, or -1 if absent).
 */
class WaylandEventLoopProxy(private val eventFd: Int) : EventLoopProxy {

    /**
     * Indicates that a wakeup is pending.
     *
     * Guards against double writes to the eventfd: only a single write() is performed
     * until the main loop drains the counter.
     */
    private val wakeupPending = AtomicBoolean(false)

    /**
     * Wakes up the Wayland loop if it is blocked in poll().
     *
     * Writes 1 to the eventfd to trigger POLLIN on the descriptor watched
     * by the main loop. The call has no effect if:
     *  - the eventfd is invalid (fd < 0)
     *  - nativeWrite is not available (libc.so.6 absent)
     *  - a wakeup is already pending
     *
     * Safe to call from any thread.
     */
    override fun wakeUp() {
        if (eventFd < 0) return
        if (!wakeupPending.compareAndSet(false, true)) return
        try {
            Arena.ofConfined().use { arena ->
                val buf = arena.allocate(8L, 8L)
                buf.set(ValueLayout.JAVA_LONG, 0L, 1L)
                nativeWrite?.invokeExact(eventFd, buf, 8L)
            }
        } catch (_: Throwable) {
            // Write failed — reset the flag to allow a retry
            wakeupPending.set(false)
        }
    }

    /**
     * Resets the pending-wakeup flag.
     *
     * Called by the main loop after draining the eventfd with read().
     * Allows a subsequent [wakeUp] to write to the eventfd again.
     */
    internal fun clearPending() {
        wakeupPending.set(false)
    }
}
