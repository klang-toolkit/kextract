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

    // ── R3: cursor, theme & appearance ───────────────────────────────────────

    /**
     * Changes the cursor shape displayed over this window.
     *
     * No-op on mobile (Android, iOS) where there is no visible cursor.
     * Never throws.
     *
     * @param cursor New cursor shape.
     */
    fun setCursor(cursor: CursorIcon)

    /**
     * Shows or hides the cursor while it is inside this window.
     *
     * On mobile backends this is a no-op.
     * Never throws.
     *
     * @param visible true to show the cursor, false to hide it.
     */
    fun setCursorVisible(visible: Boolean)

    /**
     * Sets the cursor grab mode for this window.
     *
     * - [CursorGrabMode.None]     — releases any existing grab.
     * - [CursorGrabMode.Confined] — limits the cursor to the window bounds.
     * - [CursorGrabMode.Locked]   — locks the cursor position (FPS mode).
     *
     * Backends that do not support a given mode implement a documented no-op.
     * Never throws.
     *
     * @param mode New grab mode.
     */
    fun setCursorGrab(mode: CursorGrabMode)

    /**
     * Warps the cursor to the given position (physical pixels, relative to the
     * top-left of the window's client area).
     *
     * On backends where cursor warping is not supported this is a no-op.
     * Never throws.
     *
     * @param position New cursor position in physical pixels.
     */
    fun setCursorPosition(position: PhysicalPosition<Int>)

    /**
     * Enables or disables cursor hit-testing for this window.
     *
     * When disabled (`false`) the window becomes click-through — pointer
     * events are forwarded to the window underneath. On backends that do not
     * support hit-testing this is a no-op.
     * Never throws.
     *
     * @param hittest true (default) to receive pointer events; false to pass them through.
     */
    fun setCursorHittest(hittest: Boolean)

    /**
     * Returns the current system theme as seen by this window, or null if unknown.
     *
     * Uses the same source as [ActiveEventLoop.systemTheme] but restricted to
     * this window's appearance (e.g. a per-window NSAppearance override on macOS).
     */
    val theme: Theme?

    /**
     * Requests a specific theme for this window.
     *
     * Passing null restores the system default. On backends where per-window
     * theme control is not available this is a no-op.
     * Never throws.
     *
     * @param theme Desired theme, or null to follow the system.
     */
    fun setTheme(theme: Theme?)

    /**
     * Sets the Z-order level of this window.
     *
     * On mobile / web backends where window ordering is not applicable this
     * is a no-op.
     * Never throws.
     *
     * @param level New window level.
     */
    fun setWindowLevel(level: WindowLevel)

    /**
     * Makes the window background transparent.
     *
     * Transparency requires the renderer to draw with alpha < 1.0 for the
     * transparent areas. On backends that do not support per-pixel alpha
     * this is a no-op.
     * Never throws.
     *
     * @param transparent true to enable transparency, false to disable it.
     */
    fun setTransparent(transparent: Boolean)

    /**
     * Enables or disables a blur effect behind the window.
     *
     * Requires [setTransparent](true) to be meaningful. On backends that do
     * not support blur (X11, Wayland, Web, Android) this is a no-op.
     * Never throws.
     *
     * @param blur true to enable blur, false to disable it.
     */
    fun setBlur(blur: Boolean)

    /**
     * Sets the application icon shown in the taskbar / dock.
     *
     * Passing null resets to the default icon. Behaviour is best-effort:
     * - AppKit: sets `NSApp.applicationIconImage`.
     * - Win32:  sends `WM_SETICON`.
     * - X11:    sets `_NET_WM_ICON`.
     * - Others: no-op.
     * Never throws.
     *
     * @param icon Icon data, or null to restore the default.
     */
    fun setWindowIcon(icon: Icon?)
}
