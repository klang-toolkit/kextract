/**
 * Interface representing a native window managed by kadre.
 *
 * Scope: pure Kotlin interface, no native reference.
 */
package org.graphiks.kadre.core

/**
 * Abstraction of a native window created by the event loop.
 *
 * The concrete implementations are provided by the platform modules
 * (kadre-appkit, etc.).
 */
interface Window {

    /** Unique identifier of the window. */
    val id: WindowId

    /**
     * Returns the native handle of the rendering surface.
     */
    val rawWindowHandle: RawWindowHandle

    /**
     * Returns the native handle of the display.
     */
    val rawDisplayHandle: RawDisplayHandle

    /**
     * Requests a redraw of the window at the next iteration.
     */
    fun requestRedraw()

    /**
     * Sets the title shown in the window's title bar.
     *
     * @param title New title of the window.
     */
    fun setTitle(title: String)

    /**
     * Returns the current title of the window's title bar.
     */
    val title: String

    /**
     * Returns the inner size of the window in physical pixels
     * (rendering surface, without the decorations).
     */
    val innerSize: PhysicalSize<Int>

    /**
     * Returns the outer size of the window in physical pixels
     * (rendering surface plus the platform decorations).
     */
    val outerSize: PhysicalSize<Int>

    /**
     * Returns the scale factor between logical and physical pixels
     * for this window.
     */
    val scaleFactor: Double

    /**
     * Makes the window visible or invisible.
     *
     * @param visible true to show the window, false to hide it.
     */
    fun setVisible(visible: Boolean)

    /**
     * Returns whether the window is currently visible.
     */
    val isVisible: Boolean

    /**
     * Closes the window.
     *
     * Once closed, the window no longer emits events and its identifier
     * becomes invalid.
     */
    fun close()

    // ── R1: window state & geometry ───────────────────────────────────────────

    /**
     * Sets whether the window can be resized by the user.
     *
     * @param resizable true to allow resizing, false to prevent it.
     */
    fun setResizable(resizable: Boolean)

    /**
     * Returns whether the window can be resized by the user.
     */
    val isResizable: Boolean

    /**
     * Minimizes or restores the window.
     *
     * @param minimized true to minimize, false to restore.
     */
    fun setMinimized(minimized: Boolean)

    /**
     * Returns whether the window is currently minimized.
     */
    val isMinimized: Boolean

    /**
     * Maximizes or restores the window.
     *
     * @param maximized true to maximize, false to restore.
     */
    fun setMaximized(maximized: Boolean)

    /**
     * Returns whether the window is currently maximized.
     */
    val isMaximized: Boolean

    /**
     * Shows or hides the window's platform decorations (title bar, borders).
     *
     * @param decorated true to show decorations, false to hide them.
     */
    fun setDecorations(decorated: Boolean)

    /**
     * Returns whether the window currently has platform decorations.
     */
    val isDecorated: Boolean

    /**
     * Sets the minimum surface size constraint.
     *
     * @param size minimum size in physical pixels, or null to remove the constraint.
     */
    fun setMinSurfaceSize(size: PhysicalSize<Int>?)

    /**
     * Sets the maximum surface size constraint.
     *
     * @param size maximum size in physical pixels, or null to remove the constraint.
     */
    fun setMaxSurfaceSize(size: PhysicalSize<Int>?)

    /**
     * Returns the outer position of the window on the screen in physical pixels
     * (top-left corner of the window frame, including decorations).
     */
    val outerPosition: PhysicalPosition<Int>

    /**
     * Moves the window so that its outer top-left corner is at [position]
     * (in physical screen pixels).
     *
     * @param position new position in physical pixels.
     */
    fun setOuterPosition(position: PhysicalPosition<Int>)

    /**
     * Notifies the compositor that the window is about to present a frame.
     *
     * On Wayland this triggers `wl_surface.pre_commit` / frame optimizations.
     * On other backends this is a no-op.
     */
    fun prePresentNotify()

    // ── R2: monitor & fullscreen ──────────────────────────────────────────────

    /**
     * Returns the monitor that currently contains the majority of the window,
     * or null if the information is not available.
     *
     * On mobile / web backends this always returns the single synthetic monitor.
     *
     * @return The [MonitorHandle] for the window's current monitor, or null.
     */
    fun currentMonitor(): MonitorHandle?

    /**
     * Enters or exits fullscreen mode.
     *
     * - Pass [Fullscreen.Borderless] to cover the monitor without a mode change.
     * - Pass [Fullscreen.Exclusive] to request exclusive fullscreen (desktop only).
     * - Pass null to exit fullscreen and return to the windowed state.
     *
     * Backends that do not support [Fullscreen.Exclusive] (Wayland, Web, Android, UIKit)
     * treat it as [Fullscreen.Borderless] and do NOT throw.
     *
     * @param fullscreen New fullscreen state, or null to exit fullscreen.
     */
    fun setFullscreen(fullscreen: Fullscreen?)

    /**
     * Returns the current fullscreen state, or null if the window is not fullscreen.
     *
     * The value reflects the last successful [setFullscreen] call on backends that
     * track state in-memory. It may differ from the actual compositor state immediately
     * after calling [setFullscreen] (the compositor may asynchronously confirm the change).
     *
     * @return The active [Fullscreen] mode, or null when in windowed mode.
     */
    val fullscreen: Fullscreen?
}
