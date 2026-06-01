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
import org.graphiks.kadre.core.RawDisplayHandle
import org.graphiks.kadre.core.RawWindowHandle
import org.graphiks.kadre.core.Theme
import org.graphiks.kadre.core.VideoMode
import org.graphiks.kadre.core.Window
import org.graphiks.kadre.core.WindowAttributes
import org.graphiks.kadre.core.WindowEvent
import org.graphiks.kadre.core.WindowId
import org.graphiks.kadre.core.WindowLevel
import java.lang.foreign.MemorySegment

/** wl_compositor.create_surface opcode in the core Wayland protocol. */
private const val WL_COMPOSITOR_CREATE_SURFACE_OPCODE: Int = 0

/** wl_surface.commit opcode in the core Wayland protocol. */
private const val WL_SURFACE_COMMIT_OPCODE: Int = 6


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

    /**
     * Current inner size in physical pixels.
     *
     * Initialized from attrs.size; updated by xdg_surface.configure events
     * via [onConfigure].
     */
    @Volatile
    private var _innerSize: PhysicalSize<Int> = attrs.size ?: PhysicalSize(800, 600)

    override val innerSize: PhysicalSize<Int>
        get() = _innerSize

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
     * Makes the window visible or invisible.
     *
     * On Wayland, the visibility of a toplevel surface is controlled via
     * xdg_surface / xdg_toplevel. The initial commit makes the surface visible;
     * wl_surface.attach(NULL) + commit hides it.
     * This implementation performs a commit to make it visible.
     *
     * @param visible true to show the window, false ignored (stub).
     */
    override fun setVisible(visible: Boolean) {
        if (visible) requestRedraw()
        // setInvisible requires wl_surface.attach(NULL) + commit — deferred to later
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

    override val isVisible: Boolean get() = xdg != null // true once xdg_shell handshake completes

    @Volatile private var _isResizable: Boolean = attrs.resizable

    override val isResizable: Boolean get() = _isResizable

    @Volatile private var _isMinimized: Boolean = false

    override val isMinimized: Boolean get() = _isMinimized

    @Volatile private var _isMaximized: Boolean = attrs.maximized

    override val isMaximized: Boolean get() = _isMaximized

    @Volatile private var _isDecorated: Boolean = attrs.decorations

    override val isDecorated: Boolean get() = _isDecorated

    override fun setResizable(resizable: Boolean) {
        _isResizable = resizable
        // On Wayland, resizability is communicated via set_min_size / set_max_size:
        // setting min == max prevents the compositor from suggesting a different size.
        if (!resizable) {
            val sz = _innerSize
            xdg?.setMinSize(sz.width, sz.height)
            xdg?.setMaxSize(sz.width, sz.height)
        } else {
            xdg?.setMinSize(0, 0)
            xdg?.setMaxSize(0, 0)
        }
        flushDisplay()
    }

    override fun setMinimized(minimized: Boolean) {
        _isMinimized = minimized
        if (minimized) {
            xdg?.setMinimized()
            flushDisplay()
        }
        // Restoring from minimized is compositor-driven; no un-minimize request in xdg_shell.
    }

    override fun setMaximized(maximized: Boolean) {
        _isMaximized = maximized
        xdg?.setMaximized(maximized)
        flushDisplay()
    }

    override fun setDecorations(decorated: Boolean) {
        _isDecorated = decorated
        // Decoration mode is set at creation via zxdg_decoration_manager_v1.
        // Runtime switching is not yet wired — no-op with a note.
        // TODO: call zxdg_toplevel_decoration_v1.set_mode at runtime (R3 / future).
    }

    override fun setMinSurfaceSize(size: PhysicalSize<Int>?) {
        val w = size?.width ?: 0
        val h = size?.height ?: 0
        xdg?.setMinSize(w, h)
        flushDisplay()
    }

    override fun setMaxSurfaceSize(size: PhysicalSize<Int>?) {
        val w = size?.width ?: 0
        val h = size?.height ?: 0
        xdg?.setMaxSize(w, h)
        flushDisplay()
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

    /** In-memory fullscreen state (R2). */
    @Volatile private var _fullscreen: Fullscreen? = attrs.fullscreen

    override val fullscreen: Fullscreen?
        get() = _fullscreen

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
                _fullscreen = null
            }
            is Fullscreen.Borderless -> {
                xdg?.setFullscreen(true)
                flushDisplay()
                _fullscreen = fullscreen
            }
            is Fullscreen.Exclusive -> {
                // Exclusive fullscreen is not supported on Wayland (xdg-shell limitation).
                // Fall back to borderless silently.
                xdg?.setFullscreen(true)
                flushDisplay()
                _fullscreen = fullscreen // store requested mode for API parity
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

    /**
     * Updates the inner size upon receiving an xdg_surface.configure event.
     *
     * @param width  New width suggested by the compositor in pixels (0 = leave unchanged).
     * @param height New height suggested by the compositor in pixels (0 = leave unchanged).
     */
    fun onConfigure(width: Int, height: Int) {
        if (width > 0 && height > 0) {
            _innerSize = PhysicalSize(width, height)
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

    /**
     * No-op on Wayland.
     *
     * TODO(R3-wayland-cursor-visible): hide via wl_pointer.set_cursor(null).
     */
    override fun setCursorVisible(visible: Boolean) {
        // No-op on Wayland: cursor visibility requires libwayland-cursor integration.
    }

    /**
     * No-op on Wayland.
     *
     * Pointer confinement requires zwp_pointer_constraints_v1, which is an
     * optional Wayland protocol extension not yet wired in this backend.
     *
     * TODO(R3-wayland-grab): implement via zwp_pointer_constraints_v1.
     */
    override fun setCursorGrab(mode: CursorGrabMode) {
        // No-op on Wayland: pointer constraints require zwp_pointer_constraints_v1.
    }

    /**
     * No-op on Wayland.
     *
     * Wayland does not expose global cursor warping. The protocol intentionally
     * hides pointer positions from clients for security reasons.
     */
    override fun setCursorPosition(position: PhysicalPosition<Int>) {
        // No-op on Wayland: cursor warping is not exposed by the Wayland protocol.
    }

    /**
     * No-op on Wayland.
     *
     * TODO(R3-wayland-hittest): implement via the input-region protocol.
     */
    override fun setCursorHittest(hittest: Boolean) {
        // No-op on Wayland: no standard click-through mechanism.
    }

    /**
     * Returns null on Wayland.
     *
     * Theme detection via org.freedesktop.portal.Settings is not yet wired.
     *
     * TODO(R3-wayland-theme): query org.freedesktop.portal.Settings via D-Bus.
     */
    override val theme: Theme? get() = null

    /**
     * No-op on Wayland — no standard per-window theme control.
     */
    override fun setTheme(theme: Theme?) {
        // No-op on Wayland: no standard per-window theme API.
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
     * No-op on Wayland.
     *
     * Per-pixel alpha transparency requires the compositor to support the
     * EGL_EXT_platform_wayland or similar; this is renderer-side and outside
     * the window API scope.
     *
     * TODO(R3-wayland-transparent): set _NET_WM_WINDOW_OPACITY or use
     * wl_surface with ARGB buffer format when the renderer supports it.
     */
    override fun setTransparent(transparent: Boolean) {
        // No-op on Wayland.
    }

    /**
     * No-op on Wayland.
     *
     * Blur requires compositor-specific protocols (e.g. org.kde.kwin.blur).
     */
    override fun setBlur(blur: Boolean) {
        // No-op on Wayland: no standard blur protocol.
    }

    /**
     * No-op on Wayland.
     *
     * Wayland does not support per-window application icons; the desktop
     * file or XDG portal is the correct mechanism.
     */
    override fun setWindowIcon(icon: Icon?) {
        // No-op on Wayland: window icons are not part of the Wayland protocol.
    }

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

            // ── 2. xdg_shell handshake → real mapped toplevel + configure/close events ──
            if (surface != 0L && xdgWmBase != 0L && WaylandXdgLib.loaded) {
                window.xdg = XdgToplevel.create(
                    displayPtr = display,
                    wmBasePtr = xdgWmBase,
                    surfacePtr = surface,
                    decorationManagerPtr = decorationManager,
                    onResized = { w, h ->
                        window._innerSize = PhysicalSize(w, h)
                        window.onWindowEvent?.invoke(WindowEvent.Resized(PhysicalSize(w, h)))
                        // Repaint once at the new size (on-demand rendering).
                        window.onWindowEvent?.invoke(WindowEvent.RedrawRequested)
                    },
                    onClose = { window.onWindowEvent?.invoke(WindowEvent.CloseRequested) },
                )
                window.xdg?.setTitle(attrs.title)
                // Apply R1 attrs
                if (attrs.maximized) window.xdg?.setMaximized(true)
                attrs.minSize?.let { window.xdg?.setMinSize(it.width, it.height) }
                attrs.maxSize?.let { window.xdg?.setMaxSize(it.width, it.height) }
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
        ): WaylandWindow = WaylandWindow(display, compositor, xdgWmBase, surface, attrs)
    }
}
