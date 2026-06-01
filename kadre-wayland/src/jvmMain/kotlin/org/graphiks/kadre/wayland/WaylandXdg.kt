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
    private val xdgToplevelPtr: Long,
    private val version: Int,
    private val onResized: (Int, Int) -> Unit,
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
        if (width > 0 && height > 0) onResized(width, height)
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

    /** Tears down the toplevel then the surface (reverse creation order), freeing the upcalls. */
    fun destroy() {
        val destroy = wlProxyMarshalFlagsVoid
        if (destroy != null) {
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
            onResized: (Int, Int) -> Unit,
            onClose: () -> Unit,
            decorationManagerPtr: Long = 0L,
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
                if (decorationManagerPtr != 0L) {
                    runCatching {
                        val manager = MemorySegment.ofAddress(decorationManagerPtr)
                        val decoration = getXdgSurface.invokeExact(
                            manager, XDG_DECORATION_MANAGER_GET_TOPLEVEL_DECORATION,
                            zxdg_toplevel_decoration_v1_interface, version, 0, MemorySegment.NULL, xdgToplevel,
                        ) as MemorySegment
                        if (decoration.address() != 0L) {
                            val setMode = wlProxyMarshalFlagsUint
                            if (setMode != null) {
                                setMode.invokeExact(
                                    decoration, XDG_TOPLEVEL_DECORATION_SET_MODE, MemorySegment.NULL, version, 0,
                                    XDG_TOPLEVEL_DECORATION_MODE_SERVER_SIDE,
                                )
                            }
                        }
                    }
                }

                val arena = Arena.ofShared()
                val bridge = XdgToplevel(
                    displayPtr, xdgSurface.address(), xdgToplevel.address(), version, onResized, onClose, arena,
                )
                val lookup = MethodHandles.lookup()
                val ptr = ValueLayout.ADDRESS.byteSize()

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
                null
            }
        }
    }
}
