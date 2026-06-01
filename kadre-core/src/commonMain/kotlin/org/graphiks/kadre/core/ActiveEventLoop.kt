/**
 * Interface representing the event loop that is active during a callback.
 *
 * Scope: pure Kotlin interface, no native reference.
 */
package org.graphiks.kadre.core

/**
 * Access to the event loop from [ApplicationHandler] callbacks.
 *
 * This interface is passed as a parameter on each incoming call into the
 * application handler, allowing the latter to create windows, control the
 * execution flow, and initiate shutdown of the loop.
 */
interface ActiveEventLoop {

    /**
     * Creates a new window with the specified attributes.
     *
     * @param attributes Configuration parameters of the window to create.
     * @return The created window.
     */
    fun createWindow(attributes: WindowAttributes): Window

    /**
     * Sets the waiting behavior of the event loop
     * after the end of the current iteration.
     *
     * @param controlFlow New waiting behavior.
     */
    fun setControlFlow(controlFlow: ControlFlow)

    /**
     * Returns the currently configured waiting behavior.
     */
    val controlFlow: ControlFlow

    /**
     * Requests shutdown of the event loop.
     *
     * The loop does not stop immediately; it finishes the current
     * iteration before stopping.
     */
    fun exit()

    /**
     * Indicates whether a shutdown request has been issued.
     *
     * @return true if [exit] has been called and the loop is going to stop.
     */
    val isExiting: Boolean

    /**
     * Creates a thread-safe proxy to this event loop.
     *
     * @return An [EventLoopProxy] usable from any thread.
     */
    fun createProxy(): EventLoopProxy

    // ── R2: monitor enumeration ───────────────────────────────────────────────

    /**
     * Returns all monitors currently connected to the system.
     *
     * The list contains at least one entry on all backends when a display is
     * available. On mobile / web backends, a single synthetic monitor
     * representing the screen is returned.
     *
     * @return Immutable list of [MonitorHandle] objects.
     */
    fun availableMonitors(): List<MonitorHandle>

    /**
     * Returns the primary monitor, or null if no primary monitor can be determined.
     *
     * On desktop backends this is the monitor designated as "primary" by the OS.
     * On mobile / web backends this is the main screen.
     *
     * @return The primary [MonitorHandle], or null if unavailable.
     */
    fun primaryMonitor(): MonitorHandle?
}
