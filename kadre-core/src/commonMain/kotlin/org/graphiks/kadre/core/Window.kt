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
     * Returns the size of the renderable surface.
     */
    val surfaceSize: PhysicalSize<Int> get() = innerSize

    /**
     * Requests a new renderable surface size.
     */
    fun requestSurfaceSize(size: PhysicalSize<Int>): SurfaceSizeRequestResult =
        SurfaceSizeRequestResult.Failure(RequestError.Unsupported("Surface resizing is unsupported by this window"))

    /**
     * Returns the surface position relative to the outer window.
     */
    val surfacePosition: PhysicalPosition<Int> get() = PhysicalPosition(0, 0)

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
     * Enables or disables individual native window buttons where supported.
     */
    fun setEnabledButtons(buttons: WindowButtons) { /* no-op by default */ }

    /**
     * Returns the enabled native window buttons, if tracked by the backend.
     */
    val enabledButtons: WindowButtons get() = WindowButtons.ALL

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
     * Insets of the unobstructed area inside [surfaceSize].
     */
    val safeArea: Insets<Int> get() = Insets(0, 0, 0, 0)

    /**
     * Resize increments requested for the renderable surface.
     */
    val surfaceResizeIncrements: PhysicalSize<Int>? get() = null

    /**
     * Sets resize increments for the renderable surface when supported.
     */
    fun setSurfaceResizeIncrements(increments: PhysicalSize<Int>?) { /* no-op by default */ }

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
     * Returns the monitors available to this window.
     *
     * The default implementation is intentionally conservative: it returns an
     * empty list when the backend cannot determine display-wide monitor
     * information at the window level. Backends with a monitor registry, or a
     * documented synthetic monitor model, should override this method.
     */
    fun availableMonitors(): List<MonitorHandle> = emptyList()

    /**
     * Returns the primary monitor visible to this window, or null if unknown.
     *
     * By default Kadre returns null because [currentMonitor] is not necessarily
     * the platform primary monitor. Backends that can distinguish the platform
     * primary monitor, or intentionally expose a single synthetic monitor,
     * should override this method.
     */
    fun primaryMonitor(): MonitorHandle? = null

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

    /**
     * Requests keyboard focus for this window where supported.
     */
    fun focusWindow() { /* no-op by default */ }

    /**
     * Returns whether this window currently has keyboard focus.
     */
    val hasFocus: Boolean get() = false

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
     * Backends that do not support a given mode return [WindowRequestResult.Failure]
     * with [RequestError.Unsupported]. Never throws.
     *
     * @param mode New grab mode.
     */
    fun setCursorGrab(mode: CursorGrabMode): WindowRequestResult

    /**
     * Warps the cursor to the given position (physical pixels, relative to the
     * top-left of the window's client area).
     *
     * On backends where cursor warping is not supported this returns
     * [WindowRequestResult.Failure] with [RequestError.Unsupported]. Never throws.
     *
     * @param position New cursor position in physical pixels.
     */
    fun setCursorPosition(position: PhysicalPosition<Int>): WindowRequestResult

    /**
     * Enables or disables cursor hit-testing for this window.
     *
     * When disabled (`false`) the window becomes click-through — pointer
     * events are forwarded to the window underneath. On backends that do not
     * support hit-testing this returns [WindowRequestResult.Failure] with
     * [RequestError.Unsupported]. Never throws.
     *
     * @param hittest true (default) to receive pointer events; false to pass them through.
     */
    fun setCursorHittest(hittest: Boolean): WindowRequestResult

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

    // ── R5-IME: input method ──────────────────────────────────────────────────

    /**
     * Enables or disables IME (Input Method Editor) input for this window.
     *
     * When [allowed] is `true`, the platform may activate the IME and the window
     * will start receiving [WindowEvent.Ime] events. When `false`, the IME is
     * suppressed and only raw [WindowEvent.KeyInput] events are delivered.
     *
     * Default implementation is a no-op — backends that support IME will override.
     * TODO R5-IME: wire in each backend.
     *
     * @param allowed `true` to allow IME input, `false` to suppress it.
     */
    fun setImeAllowed(allowed: Boolean) { /* no-op by default, TODO R5-IME */ }

    /**
     * Notifies the IME of the text cursor's current position and bounding box.
     *
     * The platform uses this information to position the IME candidate window
     * near the cursor. Should be called whenever the cursor moves or the
     * text layout changes.
     *
     * Default implementation is a no-op — backends that support IME will override.
     * TODO R5-IME: wire in each backend.
     *
     * @param position Top-left corner of the cursor area in physical pixels (window-relative).
     * @param size     Size of the cursor area in physical pixels.
     */
    fun setImeCursorArea(position: PhysicalPosition<Int>, size: PhysicalSize<Int>) { }

    /**
     * Hints the IME about the intended purpose of the focused text field.
     *
     * Allows the input method to adapt its behaviour (e.g. hide suggestions
     * for a terminal, mask characters for a password field).
     *
     * Default implementation is a no-op — backends that support IME will override.
     * TODO R5-IME: wire in each backend.
     *
     * @param purpose Intended use of the text field.
     * @see ImePurpose
     */
    fun setImePurpose(purpose: ImePurpose) { }

    // ── R5-CustomCursor ───────────────────────────────────────────────────────

    /**
     * Applies a previously created custom cursor to this window.
     *
     * The [cursor] must have been obtained via [ActiveEventLoop.createCustomCursor].
     * Default implementation is a no-op — backends will override.
     * No-op on iOS and Android (platform does not support custom cursors).
     * Never throws.
     *
     * TODO R5-CustomCursor: wire in each backend (AppKit, Win32, X11, Wayland, Web).
     *
     * @param cursor Custom cursor handle to apply.
     */
    fun setCustomCursor(cursor: CustomCursor) { /* no-op by default, TODO R5-CustomCursor */ }

    // ── R5-MiscWindow ─────────────────────────────────────────────────────────

    /**
     * Requests the platform to attract the user's attention (taskbar / dock icon).
     *
     * Passing null cancels an active attention request.
     *
     * Platform behaviour:
     * - AppKit : `NSApp.requestUserAttention` / `cancelUserAttentionRequest`.
     * - Win32  : `FlashWindowEx` (FLASHW_TRAY / FLASHW_TIMER).
     * - Others : no-op documented.
     *
     * Default implementation is a no-op. Never throws.
     * TODO R5-MiscWindow: wire in AppKit and Win32 backends.
     *
     * @param requestType Attention level, or null to cancel the current request.
     */
    fun requestUserAttention(requestType: UserAttentionType?) { /* no-op by default, TODO R5-MiscWindow */ }

    /**
     * Enables or disables screen-capture protection for this window.
     *
     * When `true`, the window content is excluded from screenshots and screen recordings.
     * Platform behaviour:
     * - Win32  : `SetWindowDisplayAffinity(WDA_EXCLUDEFROMCAPTURE)`.
     * - AppKit : `NSWindow.sharingType = NSWindowSharingNone`.
     * - Others : no-op documented.
     *
     * Default implementation is a no-op. Never throws.
     * TODO R5-MiscWindow: wire in Win32 and AppKit backends.
     *
     * @param protected `true` to enable content protection, `false` to disable.
     */
    fun setContentProtected(protected: Boolean) { /* no-op by default, TODO R5-MiscWindow */ }

    /**
     * Shows the platform window menu (system / title-bar context menu) at the given position.
     *
     * Platform behaviour:
     * - Win32  : `TrackPopupMenu(GetSystemMenu(...))` when implemented.
     * - Others : [WindowRequestResult.Failure] with [RequestError.Unsupported].
     *
     * Default implementation returns [WindowRequestResult.Failure] with
     * [RequestError.Unsupported]. Never throws.
     * TODO R5-MiscWindow: wire in Win32 backend.
     *
     * @param position Position in physical pixels (window-relative) at which to show the menu.
     */
    fun showWindowMenu(position: PhysicalPosition<Int>): WindowRequestResult =
        WindowRequestResult.Failure(RequestError.Unsupported("Window menu is unsupported by this window"))

    /**
     * Initiates a user-driven window drag from the current cursor position.
     *
     * Intended to be called from a pointer-pressed event handler to allow dragging
     * a custom title bar.
     * Platform behaviour:
     * - AppKit   : `NSWindow.performWindowDragWithEvent` when implemented.
     * - Wayland  : `xdg_toplevel.move` when implemented.
     * - Others   : [WindowRequestResult.Failure] with [RequestError.Unsupported].
     *
     * Default implementation returns [WindowRequestResult.Failure] with
     * [RequestError.Unsupported]. Never throws.
     * TODO R5-MiscWindow: wire in AppKit and Wayland backends.
     */
    fun dragWindow(): WindowRequestResult =
        WindowRequestResult.Failure(RequestError.Unsupported("Window dragging is unsupported by this window"))

    /**
     * Initiates a user-driven window resize from the current cursor position.
     *
     * Must be called from a pointer-pressed event handler.
     * Platform behaviour:
     * - Wayland  : `xdg_toplevel.resize` with the matching edge when implemented.
     * - Others   : [WindowRequestResult.Failure] with [RequestError.Unsupported].
     *
     * Default implementation returns [WindowRequestResult.Failure] with
     * [RequestError.Unsupported]. Never throws.
     * TODO R5-MiscWindow: wire in Wayland (and potentially Win32) backend.
     *
     * @param direction The window edge / corner to resize from.
     */
    fun dragResizeWindow(direction: ResizeDirection): WindowRequestResult =
        WindowRequestResult.Failure(RequestError.Unsupported("Window resizing is unsupported by this window"))


    /**
     * Enables or disables native pinch gesture recognition for this window.
     *
     * Mirrors winit's iOS opt-in model: UIKit does not recognize gestures by
     * default; applications must enable the recognizers they want. Other
     * backends may ignore this method when recognition is always platform-driven
     * or unsupported.
     */
    fun recognizePinchGesture(shouldRecognize: Boolean) { /* no-op by default */ }

    /**
     * Enables or disables native pan gesture recognition for this window.
     *
     * On UIKit this installs/removes a [WindowEvent.PanGesture] recognizer and
     * applies the requested touch-count bounds. Other backends are documented
     * no-ops unless they expose an equivalent native opt-in.
     */
    fun recognizePanGesture(
        shouldRecognize: Boolean,
        minimumNumberOfTouches: Int,
        maximumNumberOfTouches: Int,
    ) { /* no-op by default */ }

    /**
     * Enables or disables native double-tap gesture recognition for this window.
     *
     * On UIKit this installs/removes a double-tap recognizer. AppKit emits smart
     * magnify events directly when the platform sends them.
     */
    fun recognizeDoubleTapGesture(shouldRecognize: Boolean) { /* no-op by default */ }

    /**
     * Enables or disables native rotation gesture recognition for this window.
     *
     * On UIKit this installs/removes a rotation recognizer. Other backends are
     * documented no-ops unless they expose an equivalent native opt-in.
     */
    fun recognizeRotationGesture(shouldRecognize: Boolean) { /* no-op by default */ }

    /**
     * Returns the input features this window backend can report precisely.
     */
    fun inputCapabilities(): InputCapabilities = InputCapabilities()

    // ── R4: keyboard ──────────────────────────────────────────────────────────

    /**
     * Resets any pending dead-key state for this window.
     *
     * On layouts with dead keys (e.g. `^` + `e` → `ê`), pressing a dead key
     * puts the input method into a "waiting" state. If the application wants to
     * discard that state (e.g. when the window loses focus and the user switches
     * away), it can call this method.
     *
     * Backend behaviour:
     * - appkit   : calls `[[NSInputManager currentInputManager] markedTextAbandoned:]`
     *              (best-effort; no-op if not supported).
     * - win32    : calls `ToUnicode` with a dummy scan code to flush the dead-key buffer.
     * - x11      : resets the XIC / XkbCompose state (best-effort).
     * - wayland  : resets the xkb_compose_state (best-effort).
     * - web      : no-op — dead-key state is managed by the browser IME.
     * - android  : no-op — IME state is managed by InputMethodManager.
     * - uikit    : no-op — dead-key state is managed by UIKit.
     *
     * Never throws.
     */
    fun resetDeadKeys()
}


/**
 * Runtime input capabilities reported by a window backend.
 */
data class InputCapabilities(
    val deviceIds: Boolean = false,
    val touch: Boolean = false,
    val tabletTool: Boolean = false,
    val touchForce: Boolean = false,
    val pinchGesture: Boolean = false,
    val panGesture: Boolean = false,
    val rotationGesture: Boolean = false,
    val doubleTapGesture: Boolean = false,
    val touchpadPressure: Boolean = false,
)
