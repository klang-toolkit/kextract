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

import org.graphiks.kadre.core.PhysicalSize
import org.graphiks.kadre.core.RawDisplayHandle
import org.graphiks.kadre.core.RawWindowHandle
import org.graphiks.kadre.core.Window
import org.graphiks.kadre.core.WindowAttributes
import org.graphiks.kadre.core.WindowEvent
import org.graphiks.kadre.core.WindowId
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
     * The xdg_toplevel.set_title call requires an xdg_toplevel* pointer created
     * during xdg_shell negotiation. This implementation is a logged stub;
     * full support will be provided by WaylandEventLoop (ticket #66).
     *
     * @param title New window title.
     */
    override fun setTitle(title: String) {
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
