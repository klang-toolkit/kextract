/**
 * Wayland globals discovery.
 *
 * `WaylandEventLoop` left `compositorPtr = 0` (stub #66): no `wl_surface` could
 * therefore be created. This file implements the missing negotiation via FFM:
 *
 *   wl_display.get_registry → wl_registry.add_listener(global) → wl_display.roundtrip
 *   → wl_registry.bind(wl_compositor)
 *
 * `wl_display_get_registry` and `wl_registry_bind` are `static inline` functions in the
 * header (not exported): we perform them via `wl_proxy_marshal_flags`. The
 * `wl_registry_interface` / `wl_compositor_interface` structures, however, are exported.
 */
package org.graphiks.kadre.wayland

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

/**
 * `wl_registry.global` event collector: retains the `name` and `version`
 * of the "wl_compositor" global as soon as it is announced.
 */
private class CompositorCollector {
    var name: Int = -1
    var version: Int = 0

    /** C callback: void global(data, wl_registry*, uint32 name, const char* interface, uint32 version). */
    @Suppress("UNUSED_PARAMETER")
    fun onGlobal(data: MemorySegment, registry: MemorySegment, name: Int, iface: MemorySegment, version: Int) {
        if (this.name >= 0) return
        // Read the C string of the announced interface.
        val ifaceName = try {
            iface.reinterpret(128).getString(0)
        } catch (_: Throwable) {
            return
        }
        if (ifaceName == "wl_compositor") {
            this.name = name
            this.version = version
        }
    }

    /** C callback: void global_remove(data, wl_registry*, uint32 name). */
    @Suppress("UNUSED_PARAMETER")
    fun onGlobalRemove(data: MemorySegment, registry: MemorySegment, name: Int) { /* no-op */ }
}

/**
 * Discovers and binds the `wl_compositor` global.
 *
 * @param displayPtr Address of the connected `wl_display*`.
 * @return Address of the bound `wl_compositor*`, or 0 if unavailable.
 */
internal fun discoverCompositor(displayPtr: Long): Long {
    val marshalNewId = wlProxyMarshalNewId ?: return 0L
    val addListener = wlProxyAddListener ?: return 0L
    val roundtrip = wlDisplayRoundtrip ?: return 0L
    val bind = wlProxyMarshalBind ?: return 0L
    val registryIface = wlRegistryInterface ?: return 0L
    val compositorIface = wlCompositorInterface ?: return 0L
    val getVersion = wlProxyGetVersion ?: return 0L

    val display = MemorySegment.ofAddress(displayPtr)

    return try {
        // 1. wl_display.get_registry → wl_registry*
        val displayVersion = getVersion.invokeExact(display) as Int
        val registry = marshalNewId.invokeExact(
            display, WL_DISPLAY_GET_REGISTRY, registryIface, displayVersion, 0, MemorySegment.NULL,
        ) as MemorySegment
        if (registry.address() == 0L) return 0L

        // 2. Registry listener (global/global_remove upcall) in a durable arena.
        val arena = Arena.ofShared()
        val collector = CompositorCollector()
        val lookup = MethodHandles.lookup()

        val onGlobalHandle = lookup.findVirtual(
            CompositorCollector::class.java, "onGlobal",
            MethodType.methodType(
                Void.TYPE,
                MemorySegment::class.java, MemorySegment::class.java,
                Int::class.javaPrimitiveType, MemorySegment::class.java, Int::class.javaPrimitiveType,
            ),
        ).bindTo(collector)
        val onGlobalRemoveHandle = lookup.findVirtual(
            CompositorCollector::class.java, "onGlobalRemove",
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
        if (rc != 0) return 0L

        // 3. roundtrip → triggers the global events (fills the collector).
        roundtrip.invokeExact(display) as Int
        if (collector.name < 0) return 0L

        // 4. wl_registry.bind(name, &wl_compositor_interface, version)
        //    interface->name = 1st field (const char*) of the wl_interface struct.
        val ifaceNamePtr = compositorIface.reinterpret(ValueLayout.ADDRESS.byteSize())
            .get(ValueLayout.ADDRESS, 0L)
        val compositor = bind.invokeExact(
            registry, WL_REGISTRY_BIND, compositorIface, collector.version, 0,
            collector.name, ifaceNamePtr, collector.version, MemorySegment.NULL,
        ) as MemorySegment
        compositor.address()
    } catch (_: Throwable) {
        0L
    }
}
