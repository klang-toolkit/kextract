/**
 * xdg_shell decoration of a wl_surface: turns a bare surface into a real mapped toplevel and
 * routes the compositor's configure/close events back to Kadre.
 *
 * xdg_shell is a protocol extension: its request opcodes are known ([XdgShellConstants]) but the
 * `xdg_*_interface` tables needed by `wl_proxy_marshal_flags` are not exported by
 * libwayland-client. They come from the kextract-generated bindings ([generated] package) backed
 * by libkadre-xdg.so ([WaylandXdgLib]).
 *
 * Handshake (xdg-shell protocol):
 *   xdg_wm_base.get_xdg_surface(surface)  → xdg_surface
 *   xdg_surface.get_toplevel()            → xdg_toplevel
 *   add listeners (surface.configure, toplevel.configure/close)
 *   wl_surface.commit + roundtrip         → first configure → ack_configure
 *
 * Per the protocol, the surface must be acked-configured before a buffer is attached, so the
 * roundtrip here completes that handshake before any rendering.
 */
package org.graphiks.kadre.wayland

import org.graphiks.kadre.wayland.generated.xdg_surface_interface
import org.graphiks.kadre.wayland.generated.xdg_toplevel_interface
import org.graphiks.kadre.wayland.generated.zxdg_toplevel_decoration_v1_interface
import java.lang.foreign.Arena
import java.lang.foreign.FunctionDescriptor
import java.lang.foreign.MemorySegment
import java.lang.foreign.ValueLayout
import java.lang.invoke.MethodHandles
import java.lang.invoke.MethodType

/** wl_surface.commit opcode (core protocol). */
private const val WL_SURFACE_COMMIT: Int = 6

internal class XdgToplevel private constructor(
    private val displayPtr: Long,
    private val xdgSurfacePtr: Long,
    /** Returns the xdg_toplevel proxy pointer for platform extension use. */
    val xdgToplevelPtr: Long,
    private val xdgDecorationPtr: Long,
    private val version: Int,
    private val decorationVersion: Int,
    private val onResized: (Int, Int, Boolean) -> Unit,
    private val onStateConfigured: (WaylandToplevelConfigureStates) -> Unit,
    private val onClose: () -> Unit,
    private val arena: Arena,
) {

    // ── Native upcall targets (invoked by the compositor through the listener vtables) ──────

    /** xdg_surface.configure(serial): must ack, then the surface is considered configured. */
    @Suppress("UNUSED_PARAMETER")
    fun onSurfaceConfigure(data: MemorySegment, surface: MemorySegment, serial: Int) {
        val ack = wlProxyMarshalFlagsUint ?: return
        runCatching {
            // invokeExact must be in statement position (not the lambda's return value), else the
            // void handle is matched against an Object return → WrongMethodTypeException.
            ack.invokeExact(
                MemorySegment.ofAddress(xdgSurfacePtr), XDG_SURFACE_ACK_CONFIGURE,
                MemorySegment.NULL, version, 0, serial,
            )
            wlDisplayFlush?.let { it.invokeExact(MemorySegment.ofAddress(displayPtr)) as Int }
        }
    }

    /** xdg_toplevel.configure(width, height, states): a (0,0) size means "pick your own". */
    @Suppress("UNUSED_PARAMETER")
    fun onToplevelConfigure(data: MemorySegment, tl: MemorySegment, width: Int, height: Int, states: MemorySegment) {
        val configureStates = waylandToplevelConfigureStates(states)
        onStateConfigured(configureStates)
        if (width > 0 && height > 0) {
            onResized(
                width,
                height,
                waylandShouldApplyResizeIncrements(
                    isResizing = configureStates.resizing,
                    isMaximized = configureStates.maximized,
                    isFullscreen = configureStates.fullscreen,
                    isTiled = configureStates.tiled,
                ),
            )
        }
    }

    /** xdg_toplevel.close(): the user/compositor asked to close the window. */
    @Suppress("UNUSED_PARAMETER")
    fun onToplevelClose(data: MemorySegment, tl: MemorySegment) {
        onClose()
    }

    /** xdg_toplevel.configure_bounds (since v4) — recommended max size; unused. */
    @Suppress("UNUSED_PARAMETER")
    fun onToplevelConfigureBounds(data: MemorySegment, tl: MemorySegment, width: Int, height: Int) { /* no-op */ }

    /** xdg_toplevel.wm_capabilities (since v5) — available compositor actions; unused. */
    @Suppress("UNUSED_PARAMETER")
    fun onToplevelWmCapabilities(data: MemorySegment, tl: MemorySegment, caps: MemorySegment) { /* no-op */ }

    /** zxdg_toplevel_decoration_v1.configure(mode): compositor chose a decoration mode. */
    @Suppress("UNUSED_PARAMETER")
    fun onDecorationConfigure(data: MemorySegment, decoration: MemorySegment, mode: Int) { /* no-op */ }

    /** Sets the minimum size via xdg_toplevel.set_min_size(width, height). */
    fun setMinSize(width: Int, height: Int) {
        val handle = wlProxyMarshalFlagsTwoUint ?: return
        runCatching {
            handle.invokeExact(
                MemorySegment.ofAddress(xdgToplevelPtr), XDG_TOPLEVEL_SET_MIN_SIZE,
                MemorySegment.NULL, version, 0, width, height,
            )
            Unit
        }
    }

    /** Sets the maximum size via xdg_toplevel.set_max_size(width, height). */
    fun setMaxSize(width: Int, height: Int) {
        val handle = wlProxyMarshalFlagsTwoUint ?: return
        runCatching {
            handle.invokeExact(
                MemorySegment.ofAddress(xdgToplevelPtr), XDG_TOPLEVEL_SET_MAX_SIZE,
                MemorySegment.NULL, version, 0, width, height,
            )
            Unit
        }
    }

    /** Starts compositor-managed interactive window movement. */
    fun move(seatPtr: Long, serial: Int): Boolean {
        val handle = wlProxyMarshalFlagsObjectUint ?: return false
        return runCatching {
            handle.invokeExact(
                MemorySegment.ofAddress(xdgToplevelPtr), XDG_TOPLEVEL_MOVE,
                MemorySegment.NULL, version, 0, MemorySegment.ofAddress(seatPtr), serial,
            )
            true
        }.getOrDefault(false)
    }

    /** Shows the compositor-managed window menu at a surface-local logical position. */
    fun showWindowMenu(seatPtr: Long, serial: Int, x: Int, y: Int): Boolean {
        val handle = wlProxyMarshalFlagsObjectUintTwoInt ?: return false
        return runCatching {
            handle.invokeExact(
                MemorySegment.ofAddress(xdgToplevelPtr), XDG_TOPLEVEL_SHOW_WINDOW_MENU,
                MemorySegment.NULL, version, 0, MemorySegment.ofAddress(seatPtr), serial, x, y,
            )
            true
        }.getOrDefault(false)
    }

    /** Starts compositor-managed interactive window resize. */
    fun resize(seatPtr: Long, serial: Int, edges: Int): Boolean {
        val handle = wlProxyMarshalFlagsObjectTwoUint ?: return false
        return runCatching {
            handle.invokeExact(
                MemorySegment.ofAddress(xdgToplevelPtr), XDG_TOPLEVEL_RESIZE,
                MemorySegment.NULL, version, 0, MemorySegment.ofAddress(seatPtr), serial, edges,
            )
            true
        }.getOrDefault(false)
    }

    /** Requests that the toplevel be maximized. */
    fun setMaximized(maximized: Boolean) {
        val opcode = if (maximized) XDG_TOPLEVEL_SET_MAXIMIZED else XDG_TOPLEVEL_UNSET_MAXIMIZED
        val handle = wlProxyMarshalFlagsVoid ?: return
        runCatching {
            handle.invokeExact(
                MemorySegment.ofAddress(xdgToplevelPtr), opcode,
                MemorySegment.NULL, version, 0,
            )
            Unit
        }
    }

    /**
     * Requests fullscreen / unfullscreen via xdg_toplevel.set_fullscreen / unset_fullscreen.
     *
     * @param fullscreen true = enter fullscreen (borderless), false = exit fullscreen.
     */
    fun setFullscreen(fullscreen: Boolean) {
        val opcode = if (fullscreen) XDG_TOPLEVEL_SET_FULLSCREEN else XDG_TOPLEVEL_UNSET_FULLSCREEN
        val handle = wlProxyMarshalFlagsVoid ?: return
        runCatching {
            handle.invokeExact(
                MemorySegment.ofAddress(xdgToplevelPtr), opcode,
                MemorySegment.NULL, version, 0,
            )
            Unit
        }
    }

    /** Requests that the toplevel be minimized (set_minimized — compositor may ignore). */
    fun setMinimized() {
        val handle = wlProxyMarshalFlagsVoid ?: return
        runCatching {
            handle.invokeExact(
                MemorySegment.ofAddress(xdgToplevelPtr), XDG_TOPLEVEL_SET_MINIMIZED,
                MemorySegment.NULL, version, 0,
            )
            Unit
        }
    }

    /** Sets the toplevel title via xdg_toplevel.set_title. */
    fun setTitle(title: String) {
        val setTitle = wlProxyMarshalFlagsString ?: return
        runCatching {
            val str = arena.allocateFrom(title)
            setTitle.invokeExact(
                MemorySegment.ofAddress(xdgToplevelPtr), XDG_TOPLEVEL_SET_TITLE,
                MemorySegment.NULL, version, 0, str,
            )
            Unit
        }
    }

    /** Requests server-side or client-side decoration mode when xdg-decoration is available. */
    fun setDecorations(decorated: Boolean): Boolean {
        if (xdgDecorationPtr == 0L) return false
        val setMode = wlProxyMarshalFlagsUint ?: return false
        return runCatching {
            setMode.invokeExact(
                MemorySegment.ofAddress(xdgDecorationPtr),
                XDG_TOPLEVEL_DECORATION_SET_MODE,
                MemorySegment.NULL,
                decorationVersion,
                0,
                waylandDecorationMode(decorated),
            )
            true
        }.getOrDefault(false)
    }

    /** Tears down the toplevel then the surface (reverse creation order), freeing the upcalls. */
    fun destroy() {
        val destroy = wlProxyMarshalFlagsVoid
        if (destroy != null) {
            if (xdgDecorationPtr != 0L) {
                runCatching {
                    destroy.invokeExact(
                        MemorySegment.ofAddress(xdgDecorationPtr),
                        XDG_TOPLEVEL_DECORATION_DESTROY,
                        MemorySegment.NULL,
                        decorationVersion,
                        0,
                    )
                    Unit
                }
            }
            runCatching {
                destroy.invokeExact(
                    MemorySegment.ofAddress(xdgToplevelPtr), XDG_TOPLEVEL_DESTROY, MemorySegment.NULL, version, 0,
                )
                Unit
            }
            runCatching {
                destroy.invokeExact(
                    MemorySegment.ofAddress(xdgSurfacePtr), XDG_SURFACE_DESTROY, MemorySegment.NULL, version, 0,
                )
                Unit
            }
        }
        runCatching { arena.close() }
    }

    companion object {

        /**
         * Performs the xdg_shell handshake for [surfacePtr] under [wmBasePtr], wiring the
         * configure/close listeners to [onResized]/[onClose]. Returns null if the bindings are
         * unavailable (non-Wayland, missing libkadre-xdg.so) or any step fails.
         */
        fun create(
            displayPtr: Long,
            wmBasePtr: Long,
            surfacePtr: Long,
            onResized: (Int, Int, Boolean) -> Unit,
            onStateConfigured: (WaylandToplevelConfigureStates) -> Unit = {},
            onClose: () -> Unit,
            decorationManagerPtr: Long = 0L,
            decorated: Boolean = true,
        ): XdgToplevel? {
            if (wmBasePtr == 0L || surfacePtr == 0L) return null
            if (!WaylandXdgLib.loaded) return null
            val getXdgSurface = wlProxyMarshalFlagsGetXdgSurface ?: return null
            // get_toplevel is a plain new_id request (6-arg marshal) — reuse the create_surface form.
            val getToplevel = wlCompositorCreateSurface ?: return null
            val addListener = wlProxyAddListener ?: return null
            val getVersion = wlProxyGetVersion ?: return null
            val commit = wlProxyMarshalFlagsVoid ?: return null
            val roundtrip = wlDisplayRoundtrip ?: return null

            var decorationForCleanup = 0L
            return try {
                val wmBase = MemorySegment.ofAddress(wmBasePtr)
                val surface = MemorySegment.ofAddress(surfacePtr)
                val display = MemorySegment.ofAddress(displayPtr)
                // Cap the version to 5: the xdg_toplevel listener vtable below has 4 entries
                // (configure, close, configure_bounds[v4], wm_capabilities[v5]), so the compositor
                // must not be allowed to send an event index beyond that range.
                val version = (getVersion.invokeExact(wmBase) as Int).coerceIn(1, 5)

                // xdg_wm_base.get_xdg_surface(surface) → xdg_surface*
                val xdgSurface = getXdgSurface.invokeExact(
                    wmBase, XDG_WM_BASE_GET_XDG_SURFACE, xdg_surface_interface, version, 0,
                    MemorySegment.NULL, surface,
                ) as MemorySegment
                if (xdgSurface.address() == 0L) return null

                // xdg_surface.get_toplevel() → xdg_toplevel*
                val xdgToplevel = getToplevel.invokeExact(
                    xdgSurface, XDG_SURFACE_GET_TOPLEVEL, xdg_toplevel_interface, version, 0, MemorySegment.NULL,
                ) as MemorySegment
                if (xdgToplevel.address() == 0L) return null

                // Request server-side decorations (titlebar + close/resize) when the compositor
                // supports zxdg_decoration_manager_v1. Without this, Weston leaves the toplevel
                // undecorated (clients are expected to draw their own).
                var xdgDecorationPtr = 0L
                var decorationVersion = 1
                if (decorationManagerPtr != 0L) {
                    runCatching {
                        val manager = MemorySegment.ofAddress(decorationManagerPtr)
                        decorationVersion = (getVersion.invokeExact(manager) as Int).coerceAtLeast(1)
                        val decoration = getXdgSurface.invokeExact(
                            manager,
                            XDG_DECORATION_MANAGER_GET_TOPLEVEL_DECORATION,
                            zxdg_toplevel_decoration_v1_interface,
                            decorationVersion,
                            0,
                            MemorySegment.NULL,
                            xdgToplevel,
                        ) as MemorySegment
                        if (decoration.address() != 0L) {
                            xdgDecorationPtr = decoration.address()
                            decorationForCleanup = xdgDecorationPtr
                        }
                    }
                }

                val arena = Arena.ofShared()
                val bridge = XdgToplevel(
                    displayPtr,
                    xdgSurface.address(),
                    xdgToplevel.address(),
                    xdgDecorationPtr,
                    version,
                    decorationVersion,
                    onResized,
                    onStateConfigured,
                    onClose,
                    arena,
                )
                val lookup = MethodHandles.lookup()
                val ptr = ValueLayout.ADDRESS.byteSize()

                if (xdgDecorationPtr != 0L) {
                    val decorationConfigure = upcallStub(
                        lookup.findVirtual(
                            XdgToplevel::class.java, "onDecorationConfigure",
                            MethodType.methodType(
                                Void.TYPE,
                                MemorySegment::class.java,
                                MemorySegment::class.java,
                                Int::class.javaPrimitiveType,
                            ),
                        ).bindTo(bridge),
                        FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_INT),
                        arena,
                    )
                    val decorationListener = arena.allocate(ptr)
                    decorationListener.set(ValueLayout.ADDRESS, 0L, decorationConfigure)
                    addListener.invokeExact(MemorySegment.ofAddress(xdgDecorationPtr), decorationListener, MemorySegment.NULL) as Int
                    bridge.setDecorations(decorated)
                }

                // struct xdg_surface_listener { configure } — 1 pointer.
                val surfaceConfigure = upcallStub(
                    lookup.findVirtual(
                        XdgToplevel::class.java, "onSurfaceConfigure",
                        MethodType.methodType(
                            Void.TYPE, MemorySegment::class.java, MemorySegment::class.java, Int::class.javaPrimitiveType,
                        ),
                    ).bindTo(bridge),
                    FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_INT),
                    arena,
                )
                val surfaceListener = arena.allocate(ptr * 1)
                surfaceListener.set(ValueLayout.ADDRESS, 0L, surfaceConfigure)
                addListener.invokeExact(xdgSurface, surfaceListener, MemorySegment.NULL) as Int

                // struct xdg_toplevel_listener { configure, close, configure_bounds, wm_capabilities }.
                val tlConfigure = upcallStub(
                    lookup.findVirtual(
                        XdgToplevel::class.java, "onToplevelConfigure",
                        MethodType.methodType(
                            Void.TYPE, MemorySegment::class.java, MemorySegment::class.java,
                            Int::class.javaPrimitiveType, Int::class.javaPrimitiveType, MemorySegment::class.java,
                        ),
                    ).bindTo(bridge),
                    FunctionDescriptor.ofVoid(
                        ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_INT, ValueLayout.JAVA_INT, ValueLayout.ADDRESS,
                    ),
                    arena,
                )
                val tlClose = upcallStub(
                    lookup.findVirtual(
                        XdgToplevel::class.java, "onToplevelClose",
                        MethodType.methodType(Void.TYPE, MemorySegment::class.java, MemorySegment::class.java),
                    ).bindTo(bridge),
                    FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS),
                    arena,
                )
                val tlBounds = upcallStub(
                    lookup.findVirtual(
                        XdgToplevel::class.java, "onToplevelConfigureBounds",
                        MethodType.methodType(
                            Void.TYPE, MemorySegment::class.java, MemorySegment::class.java,
                            Int::class.javaPrimitiveType, Int::class.javaPrimitiveType,
                        ),
                    ).bindTo(bridge),
                    FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_INT, ValueLayout.JAVA_INT),
                    arena,
                )
                val tlCaps = upcallStub(
                    lookup.findVirtual(
                        XdgToplevel::class.java, "onToplevelWmCapabilities",
                        MethodType.methodType(Void.TYPE, MemorySegment::class.java, MemorySegment::class.java, MemorySegment::class.java),
                    ).bindTo(bridge),
                    FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS),
                    arena,
                )
                val tlListener = arena.allocate(ptr * 4)
                tlListener.set(ValueLayout.ADDRESS, 0L, tlConfigure)
                tlListener.set(ValueLayout.ADDRESS, ptr, tlClose)
                tlListener.set(ValueLayout.ADDRESS, ptr * 2, tlBounds)
                tlListener.set(ValueLayout.ADDRESS, ptr * 3, tlCaps)
                addListener.invokeExact(xdgToplevel, tlListener, MemorySegment.NULL) as Int

                // Initial commit (no buffer yet) + roundtrip → triggers the first configure, which
                // the surface listener acks. Only then is it legal to attach a buffer.
                commit.invokeExact(surface, WL_SURFACE_COMMIT, MemorySegment.NULL, version, 0)
                roundtrip.invokeExact(display) as Int

                bridge
            } catch (_: Throwable) {
                if (decorationForCleanup != 0L) {
                    runCatching {
                        wlProxyDestroy?.invokeExact(MemorySegment.ofAddress(decorationForCleanup))
                        Unit
                    }
                }
                null
            }
        }
    }
}

internal data class WaylandToplevelConfigureStates(
    val maximized: Boolean = false,
    val fullscreen: Boolean = false,
    val resizing: Boolean = false,
    val tiled: Boolean = false,
) {
    fun isStateless(): Boolean =
        !maximized && !fullscreen && !tiled
}

internal fun waylandShouldApplyResizeIncrements(
    isResizing: Boolean,
    isMaximized: Boolean,
    isFullscreen: Boolean,
    isTiled: Boolean,
    constrain: Boolean = false,
): Boolean =
    (constrain || isResizing) && !isMaximized && !isFullscreen && !isTiled

internal fun waylandDecorationMode(decorated: Boolean): Int =
    if (decorated) {
        XDG_TOPLEVEL_DECORATION_MODE_SERVER_SIDE
    } else {
        XDG_TOPLEVEL_DECORATION_MODE_CLIENT_SIDE
    }

private const val WAYLAND_WL_ARRAY_SIZE_OFFSET: Long = 0L
private const val WAYLAND_WL_ARRAY_DATA_OFFSET: Long = 16L
private const val XDG_TOPLEVEL_STATE_MAXIMIZED_VALUE: Int = 1
private const val XDG_TOPLEVEL_STATE_FULLSCREEN_VALUE: Int = 2
private const val XDG_TOPLEVEL_STATE_RESIZING_VALUE: Int = 3
private const val XDG_TOPLEVEL_STATE_TILED_LEFT_VALUE: Int = 5
private const val XDG_TOPLEVEL_STATE_TILED_RIGHT_VALUE: Int = 6
private const val XDG_TOPLEVEL_STATE_TILED_TOP_VALUE: Int = 7
private const val XDG_TOPLEVEL_STATE_TILED_BOTTOM_VALUE: Int = 8

internal fun waylandToplevelConfigureStates(states: MemorySegment): WaylandToplevelConfigureStates {
    if (states == MemorySegment.NULL) return WaylandToplevelConfigureStates()
    return try {
        val size = states.get(ValueLayout.JAVA_LONG, WAYLAND_WL_ARRAY_SIZE_OFFSET)
        if (size <= 0L) return WaylandToplevelConfigureStates()
        val data = states.get(ValueLayout.ADDRESS, WAYLAND_WL_ARRAY_DATA_OFFSET)
        if (data == MemorySegment.NULL) return WaylandToplevelConfigureStates()
        val items = data.reinterpret(size)
        var maximized = false
        var fullscreen = false
        var resizing = false
        var tiled = false
        val count = (size / 4L).toInt()
        for (index in 0 until count) {
            when (items.getAtIndex(ValueLayout.JAVA_INT, index.toLong())) {
                XDG_TOPLEVEL_STATE_MAXIMIZED_VALUE -> maximized = true
                XDG_TOPLEVEL_STATE_FULLSCREEN_VALUE -> fullscreen = true
                XDG_TOPLEVEL_STATE_RESIZING_VALUE -> resizing = true
                XDG_TOPLEVEL_STATE_TILED_LEFT_VALUE,
                XDG_TOPLEVEL_STATE_TILED_RIGHT_VALUE,
                XDG_TOPLEVEL_STATE_TILED_TOP_VALUE,
                XDG_TOPLEVEL_STATE_TILED_BOTTOM_VALUE -> tiled = true
            }
        }
        WaylandToplevelConfigureStates(maximized, fullscreen, resizing, tiled)
    } catch (_: Throwable) {
        WaylandToplevelConfigureStates()
    }
}
