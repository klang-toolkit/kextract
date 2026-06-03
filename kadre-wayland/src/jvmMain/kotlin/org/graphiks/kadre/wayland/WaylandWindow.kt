/**
 * Wayland implementation of the [Window] interface for Linux Desktop.
 *
 * Uses the Foreign Function & Memory API (JEP 454, JDK 25) to interact
 * with libwayland-client.so.0 without JNA or any other intermediate layer.
 *
 * Creation flow (simplified — full xdg_shell delegated to WaylandEventLoop):
 *  1. wl_compositor_create_surface  — creates the wl_surface (opcode 0 on compositor)
 *  2. wl_display_flush              — sends the request to the server
 *
 * The xdg_surface / xdg_toplevel calls require pointers to the wl_interface
 * structures (not available via pure FFM); they are implemented
 * as stubs and delegated to WaylandEventLoop (ticket #66).
 *
 * WaylandWindow — implementation of the Window interface.
 */
package org.graphiks.kadre.wayland

import org.graphiks.kadre.core.CursorGrabMode
import org.graphiks.kadre.core.CursorIcon
import org.graphiks.kadre.core.Fullscreen
import org.graphiks.kadre.core.Icon
import org.graphiks.kadre.core.MonitorHandle
import org.graphiks.kadre.core.PhysicalPosition
import org.graphiks.kadre.core.PhysicalSize
import org.graphiks.kadre.core.InputCapabilities
import org.graphiks.kadre.core.RawDisplayHandle
import org.graphiks.kadre.core.RawWindowHandle
import org.graphiks.kadre.core.RequestError
import org.graphiks.kadre.core.ResizeDirection
import org.graphiks.kadre.core.SurfaceSizeRequestResult
import org.graphiks.kadre.core.Theme
import org.graphiks.kadre.core.VideoMode
import org.graphiks.kadre.core.Window
import org.graphiks.kadre.core.WindowAttributes
import org.graphiks.kadre.core.WindowButtons
import org.graphiks.kadre.core.WindowEvent
import org.graphiks.kadre.core.WindowId
import org.graphiks.kadre.core.WindowLevel
import org.graphiks.kadre.core.WindowRequestResult
import java.lang.foreign.MemorySegment
import kotlin.math.roundToInt

/** wl_compositor.create_surface opcode in the core Wayland protocol. */
private const val WL_COMPOSITOR_CREATE_SURFACE_OPCODE: Int = 0
private const val WL_COMPOSITOR_CREATE_REGION_OPCODE: Int = 1

/** wl_surface.commit opcode in the core Wayland protocol. */
private const val WL_SURFACE_COMMIT_OPCODE: Int = 6
private const val WL_SURFACE_SET_OPAQUE_REGION_OPCODE: Int = 4
private const val WL_SURFACE_SET_INPUT_REGION_OPCODE: Int = 5
private const val WL_SURFACE_VERSION: Int = 1
private const val WL_REGION_DESTROY_OPCODE: Int = 0
private const val WL_REGION_ADD_OPCODE: Int = 1
private const val WL_REGION_VERSION: Int = 1


/**
 * Native Wayland window implementing [Window].
 *
 * The constructor is internal: use [WaylandWindow.create] to instantiate.
 * Instances are created by WaylandEventLoop (ticket #66), which supplies
 * the already-initialized display, compositor and xdgWmBase pointers.
 *
 * @param displayPtr   wl_display* pointer (Long address of the MemorySegment).
 * @param compositorPtr wl_compositor* pointer (Wayland proxy returned by wl_registry_bind).
 * @param xdgWmBasePtr  xdg_wm_base* pointer (Wayland proxy, or 0 if unavailable).
 * @param attrs         Window creation attributes.
 */
class WaylandWindow private constructor(
    private val displayPtr: Long,
    private val compositorPtr: Long,
    private val xdgWmBasePtr: Long,
    private val surfacePtr: Long,
    private val attrs: WindowAttributes,
) : Window {

    /** Unique identifier based on the address of the wl_surface. */
    override val id: WindowId = WindowId(surfacePtr)

    /**
     * Sink for compositor-driven window events (Resized, CloseRequested), set by
     * [WaylandEventLoop] right after creation so the loop can enqueue and dispatch them.
     */
    @Volatile
    internal var onWindowEvent: ((WindowEvent) -> Unit)? = null

    /** The xdg_shell decoration (real toplevel), or null if xdg_shell is unavailable. */
    private var xdg: XdgToplevel? = null

    override val rawWindowHandle: RawWindowHandle
        get() = RawWindowHandle.Wayland(surface = surfacePtr, display = displayPtr)

    override val rawDisplayHandle: RawDisplayHandle
        get() = RawDisplayHandle.Wayland(display = displayPtr)

    override fun inputCapabilities(): InputCapabilities =
        InputCapabilities()

    /**
     * Current inner size in physical pixels.
     *
     * Initialized from attrs.size; updated by xdg_surface.configure events
     * via [onConfigure].
     */
    @Volatile
    private var _innerSize: PhysicalSize<Int> = attrs.size ?: PhysicalSize(800, 600)

    @Volatile
    private var lastConfigureStates: WaylandToplevelConfigureStates? = null

    override val innerSize: PhysicalSize<Int>
        get() = _innerSize

    override val surfaceSize: PhysicalSize<Int>
        get() = _innerSize

    override fun requestSurfaceSize(size: PhysicalSize<Int>): SurfaceSizeRequestResult {
        if (lastConfigureStates?.isStateless() != false) {
            _innerSize = size
            onWindowEvent?.invoke(WindowEvent.RedrawRequested)
        }
        return SurfaceSizeRequestResult.Applied(_innerSize)
    }

    /**
     * Outer size (surface + WM decorations) in physical pixels.
     *
     * On Wayland, server-side decorations (SSD) are managed by the compositor.
     * Without access to decoration configuration events, we return the same
     * value as [innerSize].
     */
    override val outerSize: PhysicalSize<Int>
        get() = _innerSize

    /**
     * DPI scale factor of this window.
     *
     * Initialized to 1.0; updated when a wl_output.scale event is received
     * (see [WaylandSeat.kt] / [installSeatListeners]).
     */
    @Volatile
    internal var _scaleFactor: Double = 1.0

    override val scaleFactor: Double get() = _scaleFactor

    @Volatile
    internal var transparentHint: Boolean = false
        private set

    @Volatile
    internal var cursorVisible: Boolean = true
        private set

    @Volatile
    private var _theme: Theme? = attrs.preferredTheme

    /**
     * Requests a redraw.
     *
     * When the window is an xdg_toplevel, redraws are driven by frame callbacks (see
     * [armFrameCallback]) and the actual surface commit is performed by the renderer's
     * present (eglSwapBuffers). Committing here as well would flood the compositor with empty
     * commits and consume frame callbacks on non-displaying frames, so this is a no-op in that
     * case. Only the bare-surface fallback (no xdg_shell) commits directly.
     */
    override fun requestRedraw() {
        if (surfacePtr == 0L || xdg != null) return
        val handle = wlProxyMarshalFlagsVoid ?: return
        try {
            val surfaceSeg = MemorySegment.ofAddress(surfacePtr)
            handle.invokeExact(
                surfaceSeg,
                WL_SURFACE_COMMIT_OPCODE,
                MemorySegment.NULL,  // wl_interface* = NULL for calls without new_id
                WL_COMPOSITOR_VERSION,
                0,                   // flags = 0
            )
            // Flush to send the request to the server
            wlDisplayFlush?.let { flush ->
                val displaySeg = MemorySegment.ofAddress(displayPtr)
                flush.invokeExact(displaySeg) as Int
            }
        } catch (_: Throwable) {
            // Ignore — invalid surface or library absent
        }
    }

    /**
     * Sets the window title via xdg_toplevel.set_title.
     *
     * @param title New window title.
     */
    override fun setTitle(title: String) {
        _title = title
        xdg?.setTitle(title)
    }

    /**
     * Ignored on Wayland, matching winit.
     *
     * xdg-shell does not expose a runtime show/hide request for toplevels. The initial surface
     * commit controls mapping; subsequent `set_visible` calls are intentionally no-ops.
     */
    override fun setVisible(visible: Boolean) {
        // no-op: Wayland runtime visibility changes are unsupported.
    }

    /**
     * Closes the window by destroying the wl_surface via wl_proxy_destroy.
     *
     * On Wayland, clean shutdown goes through xdg_toplevel.destroy →
     * xdg_surface.destroy → wl_surface.destroy. This simplified implementation
     * calls wl_proxy_destroy directly on the surface.
     */
    override fun close() {
        // Tear down xdg_toplevel/xdg_surface first (reverse creation order).
        xdg?.destroy()
        xdg = null
        if (surfacePtr == 0L) return
        val handle = wlProxyDestroy ?: return
        try {
            val surfaceSeg = MemorySegment.ofAddress(surfacePtr)
            handle.invokeExact(surfaceSeg)
            wlDisplayFlush?.let { flush ->
                val displaySeg = MemorySegment.ofAddress(displayPtr)
                flush.invokeExact(displaySeg) as Int
            }
        } catch (_: Throwable) {
            // Ignore — proxy already destroyed or library absent
        }
    }

    // ── R1: window state & geometry ───────────────────────────────────────────

    @Volatile private var _title: String = attrs.title

    override val title: String get() = _title

    override val isVisible: Boolean? get() = null

    @Volatile private var _isResizable: Boolean = attrs.resizable

    override val isResizable: Boolean get() = _isResizable

    @Volatile private var _isMinimized: Boolean? = null

    override val isMinimized: Boolean? get() = _isMinimized

    @Volatile private var _isMaximized: Boolean = attrs.maximized

    override val isMaximized: Boolean get() = _isMaximized

    @Volatile private var _isDecorated: Boolean = attrs.decorations

    override val isDecorated: Boolean get() = _isDecorated

    @Volatile private var _minSurfaceSize: PhysicalSize<Int>? = attrs.minSize
    @Volatile private var _maxSurfaceSize: PhysicalSize<Int>? = attrs.maxSize
    @Volatile private var _surfaceResizeIncrements: PhysicalSize<Int>? = attrs.resizeIncrements

    override fun setResizable(resizable: Boolean) {
        if (_isResizable == resizable) return
        _isResizable = resizable
        applyWaylandSurfaceConstraints()
        flushDisplay()
        onWindowEvent?.invoke(WindowEvent.RedrawRequested)
    }

    override fun setMinimized(minimized: Boolean) {
        _isMinimized = null
        if (minimized) {
            xdg?.setMinimized()
            flushDisplay()
        }
        // Restoring from minimized is compositor-driven; no un-minimize request in xdg_shell.
    }

    override fun setMaximized(maximized: Boolean) {
        xdg?.setMaximized(maximized)
        flushDisplay()
    }

    override fun setDecorations(decorated: Boolean) {
        _isDecorated = decorated
        xdg?.setDecorations(decorated)
        flushDisplay()
    }

    override fun setMinSurfaceSize(size: PhysicalSize<Int>?) {
        _minSurfaceSize = size
        applyWaylandSurfaceConstraints()
        flushDisplay()
        onWindowEvent?.invoke(WindowEvent.RedrawRequested)
    }

    override fun setMaxSurfaceSize(size: PhysicalSize<Int>?) {
        _maxSurfaceSize = size
        applyWaylandSurfaceConstraints()
        flushDisplay()
        onWindowEvent?.invoke(WindowEvent.RedrawRequested)
    }

    override val surfaceResizeIncrements: PhysicalSize<Int>?
        get() = _surfaceResizeIncrements

    override fun setSurfaceResizeIncrements(increments: PhysicalSize<Int>?) {
        _surfaceResizeIncrements = increments
    }

    /**
     * On Wayland, global screen positions are not exposed by the protocol.
     * Returns PhysicalPosition(0, 0) as a documented no-op.
     */
    override val outerPosition: PhysicalPosition<Int>
        get() = PhysicalPosition(0, 0)

    /**
     * No-op on Wayland: the compositor controls window placement.
     *
     * The Wayland protocol intentionally does not allow clients to set their
     * global screen position. This method is a documented no-op.
     */
    override fun setOuterPosition(position: PhysicalPosition<Int>) { /* no-op: Wayland does not expose global positions */ }

    // ── R2: monitor & fullscreen ──────────────────────────────────────────────

    /**
     * Returns a synthetic monitor representing the Wayland compositor's output.
     *
     * Uses the current window size and scale factor as the monitor's video mode,
     * since the Wayland protocol does not expose physical screen geometry directly.
     */
    override fun currentMonitor(): MonitorHandle? = object : MonitorHandle {
        override val id: Long = displayPtr
        override val name: String? = null
        override val position: PhysicalPosition<Int> = PhysicalPosition(0, 0)
        override val scaleFactor: Double = _scaleFactor
        override val currentVideoMode: VideoMode = VideoMode(_innerSize, null, null)
        override val videoModes: List<VideoMode> = listOf(currentVideoMode)
    }

    override fun availableMonitors(): List<MonitorHandle> =
        currentMonitor()?.let(::listOf) ?: emptyList()

    override fun primaryMonitor(): MonitorHandle? =
        null

    /** In-memory fullscreen state (R2). */
    @Volatile private var _fullscreen: Fullscreen? = attrs.fullscreen

    override val fullscreen: Fullscreen?
        get() = _fullscreen

    override val hasFocus: Boolean
        get() = WaylandFocusState.hasFocus(surfacePtr)

    /**
     * No-op on Wayland, matching winit.
     *
     * Current xdg-shell does not expose per-button control; local winit accepts
     * the request and continues reporting all buttons enabled.
     */
    override fun setEnabledButtons(buttons: WindowButtons) {
        @Suppress("UNUSED_EXPRESSION")
        waylandEnabledButtonsAfterSet(buttons)
    }

    override val enabledButtons: WindowButtons
        get() = waylandEnabledButtons()

    /**
     * Enters or exits borderless fullscreen via xdg_toplevel.set_fullscreen / unset_fullscreen.
     *
     * **Exclusive fullscreen is not supported on Wayland** — the xdg-shell protocol does not
     * expose mode-change requests to clients. [Fullscreen.Exclusive] is treated as
     * [Fullscreen.Borderless] and the no-op is intentional.
     *
     * @param fullscreen New fullscreen state, or null to exit fullscreen.
     */
    override fun setFullscreen(fullscreen: Fullscreen?) {
        when (fullscreen) {
            null -> {
                xdg?.setFullscreen(false)
                flushDisplay()
            }
            is Fullscreen.Borderless -> {
                xdg?.setFullscreen(true)
                flushDisplay()
            }
            is Fullscreen.Exclusive -> {
                // Exclusive fullscreen is not supported on Wayland (xdg-shell limitation).
                // Fall back to borderless silently.
                xdg?.setFullscreen(true)
                flushDisplay()
            }
        }
    }

    /**
     * Sends a `wl_surface.commit` to the compositor to signal that the next frame is ready.
     *
     * On Wayland this is equivalent to a `pre_commit` hint — the surface commit
     * is sent immediately without attaching a new buffer, letting the compositor
     * update its internal state.
     */
    override fun prePresentNotify() {
        if (surfacePtr == 0L) return
        val handle = wlProxyMarshalFlagsVoid ?: return
        try {
            val surfaceSeg = MemorySegment.ofAddress(surfacePtr)
            handle.invokeExact(
                surfaceSeg,
                WL_SURFACE_COMMIT_OPCODE,
                MemorySegment.NULL,
                WL_COMPOSITOR_VERSION,
                0,
            )
            flushDisplay()
        } catch (_: Throwable) {}
    }

    /** Convenience: flush the Wayland display connection. */
    private fun flushDisplay() {
        wlDisplayFlush?.let { flush ->
            try {
                val displaySeg = MemorySegment.ofAddress(displayPtr)
                flush.invokeExact(displaySeg) as Int
            } catch (_: Throwable) {}
        }
    }

    private fun physicalToWaylandCoordinate(value: Int): Int {
        val scale = scaleFactor.takeIf { it > 0.0 } ?: 1.0
        return (value / scale).roundToInt()
    }

    /**
     * Updates the inner size upon receiving an xdg_surface.configure event.
     *
     * @param width  New width suggested by the compositor in pixels (0 = leave unchanged).
     * @param height New height suggested by the compositor in pixels (0 = leave unchanged).
     */
    fun onConfigure(width: Int, height: Int, applyResizeIncrements: Boolean = true) {
        if (width > 0 && height > 0) {
            val size = PhysicalSize(width, height)
            _innerSize = if (applyResizeIncrements) {
                waylandApplyResizeIncrements(
                    size = size,
                    minSize = _minSurfaceSize,
                    increments = _surfaceResizeIncrements,
                )
            } else {
                size
            }
        }
    }

    internal fun onToplevelStateConfigured(states: WaylandToplevelConfigureStates) {
        lastConfigureStates = states
        _isMaximized = states.maximized
        _fullscreen = if (states.fullscreen) {
            Fullscreen.Borderless(currentMonitor())
        } else {
            null
        }
    }

    // ── R3: cursor, theme & appearance ───────────────────────────────────────

    /**
     * No-op on Wayland.
     *
     * Cursor shape requires wl_pointer.set_cursor with a wl_surface carrying
     * a cursor buffer from libwayland-cursor. This is significant extra work
     * (loading cursor theme, creating a wl_surface, attaching a shm buffer).
     *
     * TODO(R3-wayland-cursor): implement via wl_cursor_theme_load +
     * wl_pointer.set_cursor if libwayland-cursor is available.
     */
    override fun setCursor(cursor: CursorIcon) {
        // No-op on Wayland: cursor theme requires libwayland-cursor integration.
    }

    override fun setCursorVisible(visible: Boolean) {
        cursorVisible = visible
        WaylandPointerState.setCursorVisible(surfacePtr, visible)
        if (!visible) {
            WaylandPointerState.hideCursorForSurface(surfacePtr)
            flushDisplay()
        }
        // Restoring visibility requires a cursor theme surface, which is not wired yet.
    }

    /**
     * Releases pointer grabs as a success no-op, matching winit.
     *
     * Pointer confinement/locking requires zwp_pointer_constraints_v1, which is
     * an optional Wayland protocol extension not yet wired in this backend.
     */
    override fun setCursorGrab(mode: CursorGrabMode): WindowRequestResult =
        if (mode == CursorGrabMode.None) {
            WindowRequestResult.Success
        } else {
            WindowRequestResult.Failure(RequestError.Unsupported("Wayland pointer constraints are not wired"))
        }

    /**
     * No-op on Wayland.
     *
     * Wayland does not expose global cursor warping. The protocol intentionally
     * hides pointer positions from clients for security reasons.
     */
    override fun setCursorPosition(position: PhysicalPosition<Int>): WindowRequestResult =
        WindowRequestResult.Failure(RequestError.Unsupported("Wayland does not expose cursor warping"))

    override fun setCursorHittest(hittest: Boolean): WindowRequestResult =
        if (applyWaylandInputRegionHittest(hittest)) {
            WindowRequestResult.Success
        } else {
            WindowRequestResult.Failure(RequestError.Unsupported("Wayland input-region cursor hit-testing is unavailable"))
        }

    /**
     * Shows the compositor-managed window menu via xdg_toplevel.show_window_menu.
     */
    override fun showWindowMenu(position: PhysicalPosition<Int>): WindowRequestResult {
        val toplevel = xdg ?: return WindowRequestResult.Failure(
            RequestError.Unsupported("Wayland xdg_toplevel is unavailable"),
        )
        val pointer = WaylandPointerState.current(surfacePtr) ?: return WindowRequestResult.Success
        val x = physicalToWaylandCoordinate(position.x)
        val y = physicalToWaylandCoordinate(position.y)
        return if (toplevel.showWindowMenu(pointer.seatPtr, pointer.serial, x, y)) {
            flushDisplay()
            WindowRequestResult.Success
        } else {
            WindowRequestResult.Failure(RequestError.OsError("xdg_toplevel.show_window_menu failed"))
        }
    }

    /**
     * Starts compositor-managed interactive window movement via xdg_toplevel.move.
     */
    override fun dragWindow(): WindowRequestResult {
        val toplevel = xdg ?: return WindowRequestResult.Failure(
            RequestError.Unsupported("Wayland xdg_toplevel is unavailable"),
        )
        val pointer = WaylandPointerState.current(surfacePtr) ?: return WindowRequestResult.Success
        return if (toplevel.move(pointer.seatPtr, pointer.serial)) {
            flushDisplay()
            WindowRequestResult.Success
        } else {
            WindowRequestResult.Failure(RequestError.OsError("xdg_toplevel.move failed"))
        }
    }

    /**
     * Starts compositor-managed interactive window resize via xdg_toplevel.resize.
     */
    override fun dragResizeWindow(direction: ResizeDirection): WindowRequestResult {
        val toplevel = xdg ?: return WindowRequestResult.Failure(
            RequestError.Unsupported("Wayland xdg_toplevel is unavailable"),
        )
        val pointer = WaylandPointerState.current(surfacePtr) ?: return WindowRequestResult.Success
        val edge = when (direction) {
            ResizeDirection.North -> XDG_TOPLEVEL_RESIZE_EDGE_TOP
            ResizeDirection.West -> XDG_TOPLEVEL_RESIZE_EDGE_LEFT
            ResizeDirection.NorthWest -> XDG_TOPLEVEL_RESIZE_EDGE_TOP_LEFT
            ResizeDirection.NorthEast -> XDG_TOPLEVEL_RESIZE_EDGE_TOP_RIGHT
            ResizeDirection.East -> XDG_TOPLEVEL_RESIZE_EDGE_RIGHT
            ResizeDirection.SouthWest -> XDG_TOPLEVEL_RESIZE_EDGE_BOTTOM_LEFT
            ResizeDirection.SouthEast -> XDG_TOPLEVEL_RESIZE_EDGE_BOTTOM_RIGHT
            ResizeDirection.South -> XDG_TOPLEVEL_RESIZE_EDGE_BOTTOM
        }
        return if (toplevel.resize(pointer.seatPtr, pointer.serial, edge)) {
            flushDisplay()
            WindowRequestResult.Success
        } else {
            WindowRequestResult.Failure(RequestError.OsError("xdg_toplevel.resize failed"))
        }
    }

    override val theme: Theme?
        get() = _theme

    override fun setTheme(theme: Theme?) {
        _theme = theme
    }

    /**
     * No-op on Wayland.
     *
     * Window Z-ordering is managed entirely by the compositor.
     * There is no standard Wayland protocol to request AlwaysOnTop/AlwaysOnBottom.
     */
    override fun setWindowLevel(level: WindowLevel) {
        // No-op on Wayland: Z-ordering is compositor-managed.
    }

    /**
     * Stores the renderer-side transparency hint.
     *
     * Wayland transparency is expressed by attaching buffers with an alpha
     * channel; there is no xdg_toplevel request for global window opacity. The
     * hint is kept so creation/runtime calls preserve winit's state semantics.
     */
    override fun setTransparent(transparent: Boolean) {
        transparentHint = transparent
        applyWaylandTransparencyHint()
    }

    /**
     * Deferred optional-protocol support on Wayland.
     *
     * winit can use compositor-specific blur protocols such as
     * `ext_background_effect` or `org_kde_kwin_blur` when available. Kadre has
     * not generated or bound those protocols yet, so this is a documented no-op.
     */
    override fun setBlur(blur: Boolean) {
        // No-op until optional Wayland blur protocols are bound.
    }

    /**
     * Deferred optional-protocol support on Wayland.
     *
     * winit can use `xdg_toplevel_icon_manager_v1` when the compositor exposes
     * it. Kadre has not generated or bound that protocol yet, so this remains a
     * documented no-op instead of claiming Wayland cannot support it.
     */
    override fun setWindowIcon(icon: Icon?) {
        // No-op until xdg_toplevel_icon_manager_v1 is bound.
    }

    /**
     * No-op on Wayland, matching winit.
     *
     * The core Wayland/xdg-shell protocol has no portable screen-capture
     * protection request. winit accepts this call and ignores it, so Kadre
     * reports success instead of a platform-unsupported failure.
     */
    override fun setContentProtected(protected: Boolean): WindowRequestResult =
        waylandContentProtectionResult(protected)

    // ── R4: keyboard ──────────────────────────────────────────────────────────

    /**
     * Resets dead-key state for this Wayland window.
     *
     * Best-effort: would require an xkb_compose_state pointer, which is not yet stored.
     * Documented no-op.
     *
     * TODO(R4-wayland-dead-keys): store xkb_compose_state and call xkb_compose_state_reset.
     */
    override fun resetDeadKeys() {
        // no-op: xkb_compose_state not yet wired (TODO R4-wayland-dead-keys)
    }

    // ── Companion ─────────────────────────────────────────────────────────────

    companion object {

        /**
         * Creates a native Wayland window.
         *
         * Performs the creation of the wl_surface from the wl_compositor.
         * The xdg_surface / xdg_toplevel steps are delegated to WaylandEventLoop (ticket #66).
         *
         * @param display     wl_display* pointer (Long address of the MemorySegment).
         * @param compositor  wl_compositor* pointer (proxy returned by wl_registry_bind).
         * @param xdgWmBase   xdg_wm_base* pointer (proxy, or 0 if unavailable).
         * @param attrs       Window attributes (title, size, visibility, etc.).
         * @return The created window, or null if the libwayland-client bindings are not
         *         available (macOS/Windows) or if surface creation fails.
         */
        fun create(
            display: Long,
            compositor: Long,
            xdgWmBase: Long,
            attrs: WindowAttributes,
            decorationManager: Long = 0L,
        ): WaylandWindow? {
            // The bindings are null on non-Wayland platforms — return null.
            val createSurface = wlCompositorCreateSurface ?: return null
            // wl_proxy_marshal_flags dereferences the wl_interface* for a new_id request:
            // it MUST be non-NULL, otherwise a native SIGSEGV (not catchable by try/catch).
            val surfaceInterface = wlSurfaceInterface ?: return null

            // ── 1. wl_compositor_create_surface ──────────────────────────────
            val surface: Long = try {
                val compositorSeg = MemorySegment.ofAddress(compositor)
                (createSurface.invokeExact(
                    compositorSeg,
                    WL_COMPOSITOR_CREATE_SURFACE_OPCODE,
                    surfaceInterface,    // &wl_surface_interface (libwayland symbol)
                    WL_COMPOSITOR_VERSION,
                    0,                   // flags = 0
                    MemorySegment.NULL,  // new_id placeholder (libwayland creates the proxy)
                ) as MemorySegment).address()
            } catch (_: Throwable) {
                0L
            }

            if (surface == 0L && compositor != 0L) {
                // On non-Wayland or in tests with mock pointers (compositor = 0),
                // we allow surface = 0 for unit tests.
                // If compositor != 0 and surface = 0: real creation failure.
                return null
            }

            val window = WaylandWindow(display, compositor, xdgWmBase, surface, attrs)
            window.setTransparent(attrs.transparent)

            // ── 2. xdg_shell handshake → real mapped toplevel + configure/close events ──
            if (surface != 0L && xdgWmBase != 0L && WaylandXdgLib.loaded) {
                window.xdg = XdgToplevel.create(
                    displayPtr = display,
                    wmBasePtr = xdgWmBase,
                    surfacePtr = surface,
                    decorationManagerPtr = decorationManager,
                    decorated = attrs.decorations,
                    onResized = { w, h, applyResizeIncrements ->
                        window.onConfigure(w, h, applyResizeIncrements)
                        val size = window.innerSize
                        window.onWindowEvent?.invoke(WindowEvent.Resized(size))
                        // Repaint once at the new size (on-demand rendering).
                        window.onWindowEvent?.invoke(WindowEvent.RedrawRequested)
                    },
                    onStateConfigured = { states -> window.onToplevelStateConfigured(states) },
                    onClose = { window.onWindowEvent?.invoke(WindowEvent.CloseRequested) },
                )
                window.xdg?.setTitle(attrs.title)
                // Apply R1 attrs
                if (attrs.maximized) window.xdg?.setMaximized(true)
                window.applyWaylandSurfaceConstraints()
                if (attrs.fullscreen != null) window.xdg?.setFullscreen(true)
            }

            // ── 3. Fallback for a bare surface (no xdg_shell): legacy initial commit ──
            if (window.xdg == null && attrs.visible && surface != 0L) {
                window.requestRedraw()
            }

            return window
        }

        /**
         * Creates a [WaylandWindow] with mock pointers, for unit tests.
         *
         * Usable without libwayland-client.so.0 — performs no FFM calls.
         *
         * @param display    Mock wl_display* pointer (may be 0 in tests).
         * @param compositor Mock wl_compositor* pointer (may be 0 in tests).
         * @param xdgWmBase  Mock xdg_wm_base* pointer (may be 0 in tests).
         * @param surface    Mock wl_surface* pointer (may be 0 in tests).
         * @param attrs      Window attributes.
         * @return A [WaylandWindow] instance built directly, without FFM calls.
         */
        internal fun createForTest(
            display: Long = 0L,
            compositor: Long = 0L,
            xdgWmBase: Long = 0L,
            surface: Long = 0L,
            attrs: WindowAttributes = WindowAttributes(),
        ): WaylandWindow =
            WaylandWindow(display, compositor, xdgWmBase, surface, attrs).also {
                it.setTransparent(attrs.transparent)
            }
    }

    private fun applyWaylandSurfaceConstraints() {
        if (!_isResizable) {
            val size = _innerSize
            xdg?.setMinSize(size.width, size.height)
            xdg?.setMaxSize(size.width, size.height)
            return
        }
        xdg?.setMinSize(_minSurfaceSize?.width ?: 0, _minSurfaceSize?.height ?: 0)
        xdg?.setMaxSize(_maxSurfaceSize?.width ?: 0, _maxSurfaceSize?.height ?: 0)
    }

    internal fun applyWaylandTransparencyHint(): Boolean {
        if (surfacePtr == 0L) return false
        val surface = MemorySegment.ofAddress(surfacePtr)
        val setOpaqueRegion = wlProxyMarshalFlagsObject ?: return false
        val region = if (transparentHint) {
            MemorySegment.NULL
        } else {
            createFullOpaqueRegion() ?: return false
        }

        return try {
            setOpaqueRegion.invokeExact(
                surface,
                WL_SURFACE_SET_OPAQUE_REGION_OPCODE,
                MemorySegment.NULL,
                WL_SURFACE_VERSION,
                0,
                region,
            )
            flushDisplay()
            true
        } catch (_: Throwable) {
            false
        } finally {
            if (region != MemorySegment.NULL) {
                destroyWaylandRegion(region)
            }
        }
    }

    internal fun applyWaylandInputRegionHittest(hittest: Boolean): Boolean {
        if (surfacePtr == 0L) return false
        val setInputRegion = wlProxyMarshalFlagsObject ?: return false
        val surface = MemorySegment.ofAddress(surfacePtr)
        val region = if (hittest) {
            MemorySegment.NULL
        } else {
            createEmptyInputRegion() ?: return false
        }

        return try {
            setInputRegion.invokeExact(
                surface,
                WL_SURFACE_SET_INPUT_REGION_OPCODE,
                MemorySegment.NULL,
                WL_SURFACE_VERSION,
                0,
                region,
            )
            flushDisplay()
            true
        } catch (_: Throwable) {
            false
        } finally {
            if (region != MemorySegment.NULL) {
                destroyWaylandRegion(region)
            }
        }
    }

    private fun createFullOpaqueRegion(): MemorySegment? {
        if (compositorPtr == 0L) return null
        val createRegion = wlCompositorCreateRegion ?: return null
        val regionInterface = wlRegionInterface ?: return null
        val addRegion = wlProxyMarshalFlagsFourInt ?: return null
        var region = MemorySegment.NULL
        return try {
            val compositor = MemorySegment.ofAddress(compositorPtr)
            region = createRegion.invokeExact(
                compositor,
                WL_COMPOSITOR_CREATE_REGION_OPCODE,
                regionInterface,
                WL_REGION_VERSION,
                0,
                MemorySegment.NULL,
            ) as MemorySegment
            if (region == MemorySegment.NULL) return null
            addRegion.invokeExact(
                region,
                WL_REGION_ADD_OPCODE,
                MemorySegment.NULL,
                WL_REGION_VERSION,
                0,
                0,
                0,
                WAYLAND_OPAQUE_REGION_EXTENT,
                WAYLAND_OPAQUE_REGION_EXTENT,
            )
            region.also {
                region = MemorySegment.NULL
            }
        } catch (_: Throwable) {
            null
        } finally {
            if (region != MemorySegment.NULL) {
                destroyWaylandRegion(region)
            }
        }
    }

    private fun createEmptyInputRegion(): MemorySegment? {
        if (compositorPtr == 0L) return null
        val createRegion = wlCompositorCreateRegion ?: return null
        val regionInterface = wlRegionInterface ?: return null
        val addRegion = wlProxyMarshalFlagsFourInt ?: return null
        var region = MemorySegment.NULL
        return try {
            val compositor = MemorySegment.ofAddress(compositorPtr)
            region = createRegion.invokeExact(
                compositor,
                WL_COMPOSITOR_CREATE_REGION_OPCODE,
                regionInterface,
                WL_REGION_VERSION,
                0,
                MemorySegment.NULL,
            ) as MemorySegment
            if (region == MemorySegment.NULL) return null
            val rect = waylandEmptyInputRegionRect()
            addRegion.invokeExact(
                region,
                WL_REGION_ADD_OPCODE,
                MemorySegment.NULL,
                WL_REGION_VERSION,
                0,
                rect.x,
                rect.y,
                rect.width,
                rect.height,
            )
            region.also {
                region = MemorySegment.NULL
            }
        } catch (_: Throwable) {
            null
        } finally {
            if (region != MemorySegment.NULL) {
                destroyWaylandRegion(region)
            }
        }
    }

    private fun destroyWaylandRegion(region: MemorySegment) {
        try {
            wlProxyMarshalFlagsVoid?.invokeExact(
                region,
                WL_REGION_DESTROY_OPCODE,
                MemorySegment.NULL,
                WL_REGION_VERSION,
                WL_MARSHAL_FLAG_DESTROY,
            )
        } catch (_: Throwable) {}
    }
}

internal const val WAYLAND_OPAQUE_REGION_EXTENT: Int = Int.MAX_VALUE

internal data class WaylandRegionRect(
    val x: Int,
    val y: Int,
    val width: Int,
    val height: Int,
)

internal fun waylandEmptyInputRegionRect(): WaylandRegionRect =
    WaylandRegionRect(x = 0, y = 0, width = 0, height = 0)

@Suppress("UNUSED_PARAMETER")
internal fun waylandContentProtectionResult(protected: Boolean): WindowRequestResult =
    WindowRequestResult.Success

@Suppress("UNUSED_PARAMETER")
internal fun waylandEnabledButtonsAfterSet(buttons: WindowButtons): WindowButtons = WindowButtons.ALL

internal fun waylandEnabledButtons(): WindowButtons = WindowButtons.ALL

internal fun waylandApplyResizeIncrements(
    size: PhysicalSize<Int>,
    minSize: PhysicalSize<Int>?,
    increments: PhysicalSize<Int>?,
): PhysicalSize<Int> {
    increments ?: return size
    val widthIncrement = increments.width.takeIf { it > 0 } ?: return size
    val heightIncrement = increments.height.takeIf { it > 0 } ?: return size
    val baseWidth = minSize?.width ?: 0
    val baseHeight = minSize?.height ?: 0
    val deltaWidth = (size.width - baseWidth).coerceAtLeast(0)
    val deltaHeight = (size.height - baseHeight).coerceAtLeast(0)
    return PhysicalSize(
        baseWidth + (deltaWidth / widthIncrement) * widthIncrement,
        baseHeight + (deltaHeight / heightIncrement) * heightIncrement,
    )
}
