/**
 * Wayland globals discovery.
 *
 * `WaylandEventLoop` left `compositorPtr = 0` (stub #66): no `wl_surface` could
 * therefore be created. This file implements the missing negotiation via FFM:
 *
 *   wl_display.get_registry → wl_registry.add_listener(global) → wl_display.roundtrip
 *   → wl_registry.bind(wl_compositor) + wl_registry.bind(xdg_wm_base)
 *
 * `wl_display_get_registry` and `wl_registry_bind` are `static inline` functions in the
 * header (not exported): we perform them via `wl_proxy_marshal_flags`. The
 * `wl_registry_interface` / `wl_compositor_interface` structures, however, are exported;
 * `xdg_wm_base_interface` comes from the kextract-generated bindings ([generated]).
 */
package org.graphiks.kadre.wayland

import org.graphiks.kadre.wayland.generated.xdg_wm_base_interface
import org.graphiks.kadre.wayland.generated.zxdg_decoration_manager_v1_interface
import java.lang.foreign.Arena
import java.lang.foreign.FunctionDescriptor
import java.lang.foreign.MemorySegment
import java.lang.foreign.ValueLayout
import java.lang.invoke.MethodHandles
import java.lang.invoke.MethodType

/** Opcode wl_display.get_registry. */
private const val WL_DISPLAY_GET_REGISTRY: Int = 1
/** Opcode wl_registry.bind. */
private const val WL_REGISTRY_BIND: Int = 0

/** Bound Wayland globals. Addresses are 0 when the global is unavailable. */
internal data class WaylandGlobals(
    val compositorPtr: Long,
    val xdgWmBasePtr: Long,
    val decorationManagerPtr: Long = 0L,
)

/**
 * `wl_registry.global` event collector: retains the `name` and `version` of the globals
 * Kadre needs (`wl_compositor`, `xdg_wm_base`) as they are announced.
 */
private class GlobalsCollector {
    var compositorName: Int = -1
    var compositorVersion: Int = 0
    var xdgWmBaseName: Int = -1
    var xdgWmBaseVersion: Int = 0
    var decorationManagerName: Int = -1
    var decorationManagerVersion: Int = 0

    /** C callback: void global(data, wl_registry*, uint32 name, const char* interface, uint32 version). */
    @Suppress("UNUSED_PARAMETER")
    fun onGlobal(data: MemorySegment, registry: MemorySegment, name: Int, iface: MemorySegment, version: Int) {
        val ifaceName = try {
            iface.reinterpret(128).getString(0)
        } catch (_: Throwable) {
            return
        }
        when (ifaceName) {
            "wl_compositor" -> if (compositorName < 0) { compositorName = name; compositorVersion = version }
            "xdg_wm_base" -> if (xdgWmBaseName < 0) { xdgWmBaseName = name; xdgWmBaseVersion = version }
            "zxdg_decoration_manager_v1" ->
                if (decorationManagerName < 0) { decorationManagerName = name; decorationManagerVersion = version }
        }
    }

    /** C callback: void global_remove(data, wl_registry*, uint32 name). */
    @Suppress("UNUSED_PARAMETER")
    fun onGlobalRemove(data: MemorySegment, registry: MemorySegment, name: Int) { /* no-op */ }
}

/**
 * Answers xdg_wm_base.ping with pong so the compositor does not consider the client unresponsive.
 * Held for the whole connection lifetime (a strong reference keeps its upcall arena alive).
 */
private class XdgWmBasePinger(private val wmBasePtr: Long, private val displayPtr: Long, private val version: Int) {
    /** C callback: void ping(data, xdg_wm_base*, uint32 serial). */
    @Suppress("UNUSED_PARAMETER")
    fun onPing(data: MemorySegment, wmBase: MemorySegment, serial: Int) {
        val pong = wlProxyMarshalFlagsUint ?: return
        runCatching {
            // invokeExact in statement position (void handle) — see onSurfaceConfigure.
            pong.invokeExact(
                MemorySegment.ofAddress(wmBasePtr), XDG_WM_BASE_PONG, MemorySegment.NULL, version, 0, serial,
            )
            wlDisplayFlush?.let { it.invokeExact(MemorySegment.ofAddress(displayPtr)) as Int }
        }
    }
}

/** Keeps pinger instances (and their upcall arenas) alive for the process lifetime. */
private val pingers = mutableListOf<XdgWmBasePinger>()

/**
 * Discovers and binds the `wl_compositor` and `xdg_wm_base` globals, and installs the
 * xdg_wm_base ping→pong listener.
 *
 * @param displayPtr Address of the connected `wl_display*`.
 * @return Bound global addresses (0 where unavailable).
 */
internal fun discoverGlobals(displayPtr: Long): WaylandGlobals {
    val marshalNewId = wlProxyMarshalNewId ?: return WaylandGlobals(0L, 0L)
    val addListener = wlProxyAddListener ?: return WaylandGlobals(0L, 0L)
    val roundtrip = wlDisplayRoundtrip ?: return WaylandGlobals(0L, 0L)
    val bind = wlProxyMarshalBind ?: return WaylandGlobals(0L, 0L)
    val registryIface = wlRegistryInterface ?: return WaylandGlobals(0L, 0L)
    val compositorIface = wlCompositorInterface ?: return WaylandGlobals(0L, 0L)
    val getVersion = wlProxyGetVersion ?: return WaylandGlobals(0L, 0L)

    val display = MemorySegment.ofAddress(displayPtr)

    return try {
        // 1. wl_display.get_registry → wl_registry*
        val displayVersion = getVersion.invokeExact(display) as Int
        val registry = marshalNewId.invokeExact(
            display, WL_DISPLAY_GET_REGISTRY, registryIface, displayVersion, 0, MemorySegment.NULL,
        ) as MemorySegment
        if (registry.address() == 0L) return WaylandGlobals(0L, 0L)

        // 2. Registry listener (global/global_remove upcall) in a durable arena.
        val arena = Arena.ofShared()
        val collector = GlobalsCollector()
        val lookup = MethodHandles.lookup()

        val onGlobalHandle = lookup.findVirtual(
            GlobalsCollector::class.java, "onGlobal",
            MethodType.methodType(
                Void.TYPE,
                MemorySegment::class.java, MemorySegment::class.java,
                Int::class.javaPrimitiveType, MemorySegment::class.java, Int::class.javaPrimitiveType,
            ),
        ).bindTo(collector)
        val onGlobalRemoveHandle = lookup.findVirtual(
            GlobalsCollector::class.java, "onGlobalRemove",
            MethodType.methodType(
                Void.TYPE,
                MemorySegment::class.java, MemorySegment::class.java, Int::class.javaPrimitiveType,
            ),
        ).bindTo(collector)

        val globalStub = upcallStub(
            onGlobalHandle,
            FunctionDescriptor.ofVoid(
                ValueLayout.ADDRESS, ValueLayout.ADDRESS,
                ValueLayout.JAVA_INT, ValueLayout.ADDRESS, ValueLayout.JAVA_INT,
            ),
            arena,
        )
        val globalRemoveStub = upcallStub(
            onGlobalRemoveHandle,
            FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_INT),
            arena,
        )

        // struct wl_registry_listener { global; global_remove; } — 2 pointers.
        val listener = arena.allocate(ValueLayout.ADDRESS.byteSize() * 2)
        listener.set(ValueLayout.ADDRESS, 0L, globalStub)
        listener.set(ValueLayout.ADDRESS, ValueLayout.ADDRESS.byteSize(), globalRemoveStub)

        val rc = addListener.invokeExact(registry, listener, MemorySegment.NULL) as Int
        if (rc != 0) return WaylandGlobals(0L, 0L)

        // 3. roundtrip → triggers the global events (fills the collector).
        roundtrip.invokeExact(display) as Int
        if (collector.compositorName < 0) return WaylandGlobals(0L, 0L)

        // 4. wl_registry.bind(wl_compositor). interface->name = 1st field (const char*).
        val compositorNamePtr = compositorIface.reinterpret(ValueLayout.ADDRESS.byteSize()).get(ValueLayout.ADDRESS, 0L)
        val compositor = bind.invokeExact(
            registry, WL_REGISTRY_BIND, compositorIface, collector.compositorVersion, 0,
            collector.compositorName, compositorNamePtr, collector.compositorVersion, MemorySegment.NULL,
        ) as MemorySegment

        // 5. wl_registry.bind(xdg_wm_base) if present, then install the ping→pong listener.
        var xdgWmBasePtr = 0L
        if (collector.xdgWmBaseName >= 0 && WaylandXdgLib.loaded) {
            xdgWmBasePtr = bindXdgWmBase(registry, bind, collector, addListener, lookup, arena, displayPtr)
        }

        // 6. wl_registry.bind(zxdg_decoration_manager_v1) for server-side window decorations.
        var decorationManagerPtr = 0L
        if (collector.decorationManagerName >= 0 && WaylandXdgLib.loaded) {
            decorationManagerPtr = runCatching {
                val iface = zxdg_decoration_manager_v1_interface
                val namePtr = iface.reinterpret(ValueLayout.ADDRESS.byteSize()).get(ValueLayout.ADDRESS, 0L)
                (bind.invokeExact(
                    registry, WL_REGISTRY_BIND, iface, collector.decorationManagerVersion, 0,
                    collector.decorationManagerName, namePtr, collector.decorationManagerVersion, MemorySegment.NULL,
                ) as MemorySegment).address()
            }.getOrDefault(0L)
        }

        WaylandGlobals(compositor.address(), xdgWmBasePtr, decorationManagerPtr)
    } catch (_: Throwable) {
        WaylandGlobals(0L, 0L)
    }
}

/** Binds xdg_wm_base and registers the ping→pong listener. Returns its address, or 0 on failure. */
private fun bindXdgWmBase(
    registry: MemorySegment,
    bind: java.lang.invoke.MethodHandle,
    collector: GlobalsCollector,
    addListener: java.lang.invoke.MethodHandle,
    lookup: MethodHandles.Lookup,
    arena: Arena,
    displayPtr: Long,
): Long = try {
    val wmBaseIface = xdg_wm_base_interface
    val wmBaseNamePtr = wmBaseIface.reinterpret(ValueLayout.ADDRESS.byteSize()).get(ValueLayout.ADDRESS, 0L)
    val wmBase = bind.invokeExact(
        registry, WL_REGISTRY_BIND, wmBaseIface, collector.xdgWmBaseVersion, 0,
        collector.xdgWmBaseName, wmBaseNamePtr, collector.xdgWmBaseVersion, MemorySegment.NULL,
    ) as MemorySegment
    if (wmBase.address() == 0L) return 0L

    val pinger = XdgWmBasePinger(wmBase.address(), displayPtr, collector.xdgWmBaseVersion)
    pingers.add(pinger)
    val pingStub = upcallStub(
        lookup.findVirtual(
            XdgWmBasePinger::class.java, "onPing",
            MethodType.methodType(Void.TYPE, MemorySegment::class.java, MemorySegment::class.java, Int::class.javaPrimitiveType),
        ).bindTo(pinger),
        FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_INT),
        arena,
    )
    // struct xdg_wm_base_listener { ping } — 1 pointer.
    val pingListener = arena.allocate(ValueLayout.ADDRESS.byteSize())
    pingListener.set(ValueLayout.ADDRESS, 0L, pingStub)
    (wlProxyAddListener ?: return 0L).invokeExact(wmBase, pingListener, MemorySegment.NULL) as Int

    wmBase.address()
} catch (t: Throwable) {
    System.err.println("[kadre-wayland] bindXdgWmBase failed: $t")
    0L
}
