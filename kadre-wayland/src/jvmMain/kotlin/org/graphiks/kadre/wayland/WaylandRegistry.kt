/**
 * Découverte des globaux Wayland.
 *
 * `WaylandEventLoop` laissait `compositorPtr = 0` (stub #66) : aucune `wl_surface` ne
 * pouvait donc être créée. Ce fichier implémente la négociation manquante via FFM :
 *
 *   wl_display.get_registry → wl_registry.add_listener(global) → wl_display.roundtrip
 *   → wl_registry.bind(wl_compositor)
 *
 * `wl_display_get_registry` et `wl_registry_bind` sont des `static inline` du header
 * (non exportés) : on les réalise via `wl_proxy_marshal_flags`. Les structures
 * `wl_registry_interface` / `wl_compositor_interface` sont, elles, exportées.
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
 * Collecteur d'événements `wl_registry.global` : retient le `name` et la `version`
 * du global « wl_compositor » dès qu'il est annoncé.
 */
private class CompositorCollector {
    var name: Int = -1
    var version: Int = 0

    /** Callback C : void global(data, wl_registry*, uint32 name, const char* interface, uint32 version). */
    @Suppress("UNUSED_PARAMETER")
    fun onGlobal(data: MemorySegment, registry: MemorySegment, name: Int, iface: MemorySegment, version: Int) {
        if (this.name >= 0) return
        // Lecture de la chaîne C de l'interface annoncée.
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

    /** Callback C : void global_remove(data, wl_registry*, uint32 name). */
    @Suppress("UNUSED_PARAMETER")
    fun onGlobalRemove(data: MemorySegment, registry: MemorySegment, name: Int) { /* no-op */ }
}

/**
 * Découvre et lie le `wl_compositor` global.
 *
 * @param displayPtr Adresse du `wl_display*` connecté.
 * @return Adresse du `wl_compositor*` lié, ou 0 si indisponible.
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

        // 2. Listener du registre (upcall global/global_remove) dans une arène durable.
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

        // struct wl_registry_listener { global; global_remove; } — 2 pointeurs.
        val listener = arena.allocate(ValueLayout.ADDRESS.byteSize() * 2)
        listener.set(ValueLayout.ADDRESS, 0L, globalStub)
        listener.set(ValueLayout.ADDRESS, ValueLayout.ADDRESS.byteSize(), globalRemoveStub)

        val rc = addListener.invokeExact(registry, listener, MemorySegment.NULL) as Int
        if (rc != 0) return 0L

        // 3. roundtrip → déclenche les événements global (remplit le collector).
        roundtrip.invokeExact(display) as Int
        if (collector.name < 0) return 0L

        // 4. wl_registry.bind(name, &wl_compositor_interface, version)
        //    interface->name = 1er champ (const char*) de la struct wl_interface.
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
