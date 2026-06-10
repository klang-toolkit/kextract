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

import org.graphiks.kadre.core.WindowEvent
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
    val seatPtr: Long = 0L,
    val seatVersion: Int = 0,
    val outputPtr: Long = 0L,
    val outputVersion: Int = 0,
    val textInputManagerPtr: Long = 0L,
    val shmPtr: Long = 0L,
    val shmVersion: Int = 0,
    val pointerConstraintsPtr: Long = 0L,
    val iconManagerPtr: Long = 0L,
    val activationManagerPtr: Long = 0L,
    val extBackgroundEffectManagerPtr: Long = 0L,
    val kwinBlurManagerPtr: Long = 0L,
)

/**
 * `wl_registry.global` event collector: retains the `name` and `version` of the globals
 * Kadre needs (`wl_compositor`, `xdg_wm_base`, `wl_seat`, `wl_output`) as they are announced.
 */
private class GlobalsCollector {
    var compositorName: Int = -1
    var compositorVersion: Int = 0
    var xdgWmBaseName: Int = -1
    var xdgWmBaseVersion: Int = 0
    var decorationManagerName: Int = -1
    var decorationManagerVersion: Int = 0
    var seatName: Int = -1
    var seatVersion: Int = 0
    var outputName: Int = -1
    var outputVersion: Int = 0
    var textInputManagerName: Int = -1
    var textInputManagerVersion: Int = 0
    var shmName: Int = -1
    var shmVersion: Int = 0
    var pointerConstraintsName: Int = -1
    var pointerConstraintsVersion: Int = 0
    var iconManagerName: Int = -1
    var iconManagerVersion: Int = 0
    var activationManagerName: Int = -1
    var activationManagerVersion: Int = 0
    var extBackgroundEffectManagerName: Int = -1
    var extBackgroundEffectManagerVersion: Int = 0
    var kwinBlurManagerName: Int = -1
    var kwinBlurManagerVersion: Int = 0

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
            "wl_seat" -> if (seatName < 0) { seatName = name; seatVersion = version }
            "wl_output" -> if (outputName < 0) { outputName = name; outputVersion = version }
            "zwp_text_input_manager_v3" ->
                if (textInputManagerName < 0) { textInputManagerName = name; textInputManagerVersion = version }
            "wl_shm" -> if (shmName < 0) { shmName = name; shmVersion = version }
            "zwp_pointer_constraints_v1" ->
                if (pointerConstraintsName < 0) { pointerConstraintsName = name; pointerConstraintsVersion = version }
            "xdg_toplevel_icon_manager_v1" ->
                if (iconManagerName < 0) { iconManagerName = name; iconManagerVersion = version }
            "xdg_activation_v1" ->
                if (activationManagerName < 0) { activationManagerName = name; activationManagerVersion = version }
            "ext_background_effect_v1" ->
                if (extBackgroundEffectManagerName < 0) { extBackgroundEffectManagerName = name; extBackgroundEffectManagerVersion = version }
            "org_kde_kwin_blur_manager" ->
                if (kwinBlurManagerName < 0) { kwinBlurManagerName = name; kwinBlurManagerVersion = version }
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
internal fun discoverGlobals(
    displayPtr: Long,
    protocolExtensions: Set<String> = setOf(
        "zwp_pointer_constraints_v1",
        "xdg_toplevel_icon_manager_v1",
        "ext_background_effect_v1",
        "org_kde_kwin_blur_manager",
        "xdg_activation_v1",
    ),
): WaylandGlobals {
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

        // 7. wl_registry.bind(wl_seat) for keyboard/pointer/touch input.
        var seatPtr = 0L
        var seatVersion = 0
        if (collector.seatName >= 0) {
            val iface = wlSeatInterface
            if (iface != null) {
                val namePtr = iface.reinterpret(ValueLayout.ADDRESS.byteSize()).get(ValueLayout.ADDRESS, 0L)
                val boundVersion = collector.seatVersion.coerceAtMost(7) // cap at v7
                seatPtr = runCatching {
                    (bind.invokeExact(
                        registry, WL_REGISTRY_BIND, iface, boundVersion, 0,
                        collector.seatName, namePtr, boundVersion, MemorySegment.NULL,
                    ) as MemorySegment).address()
                }.getOrDefault(0L)
                seatVersion = boundVersion
            }
        }

        // 8. wl_registry.bind(wl_output) for scale factor.
        var outputPtr = 0L
        var outputVersion = 0
        if (collector.outputName >= 0) {
            val iface = wlOutputInterface
            if (iface != null) {
                val namePtr = iface.reinterpret(ValueLayout.ADDRESS.byteSize()).get(ValueLayout.ADDRESS, 0L)
                val boundVersion = collector.outputVersion.coerceAtMost(4) // cap at v4 (scale is v2)
                outputPtr = runCatching {
                    (bind.invokeExact(
                        registry, WL_REGISTRY_BIND, iface, boundVersion, 0,
                        collector.outputName, namePtr, boundVersion, MemorySegment.NULL,
                    ) as MemorySegment).address()
                }.getOrDefault(0L)
                outputVersion = boundVersion
            }
        }

        // 9. wl_registry.bind(zwp_text_input_manager_v3) for IME.
        var textInputManagerPtr = 0L
        if (collector.textInputManagerName >= 0) {
            val iface = zwpTextInputManagerV3Interface
            val namePtr = iface.reinterpret(ValueLayout.ADDRESS.byteSize()).get(ValueLayout.ADDRESS, 0L)
            val boundVersion = collector.textInputManagerVersion.coerceAtMost(1)
            textInputManagerPtr = runCatching {
                (bind.invokeExact(
                    registry, WL_REGISTRY_BIND, iface, boundVersion, 0,
                    collector.textInputManagerName, namePtr, boundVersion, MemorySegment.NULL,
                ) as MemorySegment).address()
            }.getOrDefault(0L)
        }

        // 10. wl_registry.bind(wl_shm) for cursor buffer creation.
        var shmPtr = 0L
        var shmVersion = 0
        if (collector.shmName >= 0) {
            val iface = wlShmInterface
            if (iface != null) {
                val namePtr = iface.reinterpret(ValueLayout.ADDRESS.byteSize()).get(ValueLayout.ADDRESS, 0L)
                val boundVersion = collector.shmVersion.coerceAtMost(1)
                shmPtr = runCatching {
                    (bind.invokeExact(
                        registry, WL_REGISTRY_BIND, iface, boundVersion, 0,
                        collector.shmName, namePtr, boundVersion, MemorySegment.NULL,
                    ) as MemorySegment).address()
                }.getOrDefault(0L)
                shmVersion = boundVersion
            }
        }

        // 11. wl_registry.bind(zwp_pointer_constraints_v1) for pointer confinement/locking.
        var pointerConstraintsPtr = 0L
        if (collector.pointerConstraintsName >= 0 && "zwp_pointer_constraints_v1" in protocolExtensions) {
            val iface = zwpPointerConstraintsV1Interface
            val namePtr = iface.reinterpret(ValueLayout.ADDRESS.byteSize()).get(ValueLayout.ADDRESS, 0L)
            pointerConstraintsPtr = runCatching {
                (bind.invokeExact(
                    registry, WL_REGISTRY_BIND, iface, collector.pointerConstraintsVersion, 0,
                    collector.pointerConstraintsName, namePtr, collector.pointerConstraintsVersion, MemorySegment.NULL,
                ) as MemorySegment).address()
            }.getOrDefault(0L)
        }

        // 12. wl_registry.bind(xdg_toplevel_icon_manager_v1) for window icons.
        var iconManagerPtr = 0L
        if (collector.iconManagerName >= 0 && "xdg_toplevel_icon_manager_v1" in protocolExtensions) {
            val iface = xdgToplevelIconManagerV1Interface
            val namePtr = iface.reinterpret(ValueLayout.ADDRESS.byteSize()).get(ValueLayout.ADDRESS, 0L)
            iconManagerPtr = runCatching {
                (bind.invokeExact(
                    registry, WL_REGISTRY_BIND, iface, collector.iconManagerVersion, 0,
                    collector.iconManagerName, namePtr, collector.iconManagerVersion, MemorySegment.NULL,
                ) as MemorySegment).address()
            }.getOrDefault(0L)
        }

        // 13. wl_registry.bind(xdg_activation_v1) for activation tokens.
        var activationManagerPtr = 0L
        if (collector.activationManagerName >= 0 && "xdg_activation_v1" in protocolExtensions) {
            val iface = xdgActivationV1Interface
            val namePtr = iface.reinterpret(ValueLayout.ADDRESS.byteSize()).get(ValueLayout.ADDRESS, 0L)
            activationManagerPtr = runCatching {
                (bind.invokeExact(
                    registry, WL_REGISTRY_BIND, iface, collector.activationManagerVersion, 0,
                    collector.activationManagerName, namePtr, collector.activationManagerVersion, MemorySegment.NULL,
                ) as MemorySegment).address()
            }.getOrDefault(0L)
        }

        // 15. wl_registry.bind(ext_background_effect_v1) for Wayland blur (wlroots, KWin 6+).
        var extBackgroundEffectManagerPtr = 0L
        if (collector.extBackgroundEffectManagerName >= 0 && "ext_background_effect_v1" in protocolExtensions) {
            val iface = extBackgroundEffectV1Interface
            val namePtr = iface.reinterpret(ValueLayout.ADDRESS.byteSize()).get(ValueLayout.ADDRESS, 0L)
            extBackgroundEffectManagerPtr = runCatching {
                (bind.invokeExact(
                    registry, WL_REGISTRY_BIND, iface, collector.extBackgroundEffectManagerVersion, 0,
                    collector.extBackgroundEffectManagerName, namePtr, collector.extBackgroundEffectManagerVersion, MemorySegment.NULL,
                ) as MemorySegment).address()
            }.getOrDefault(0L)
        }

        // 16. wl_registry.bind(org_kde_kwin_blur_manager) for Wayland blur (KWin 5.x).
        var kwinBlurManagerPtr = 0L
        if (collector.kwinBlurManagerName >= 0 && "org_kde_kwin_blur_manager" in protocolExtensions) {
            val iface = orgKdeKwinBlurManagerInterface
            val namePtr = iface.reinterpret(ValueLayout.ADDRESS.byteSize()).get(ValueLayout.ADDRESS, 0L)
            kwinBlurManagerPtr = runCatching {
                (bind.invokeExact(
                    registry, WL_REGISTRY_BIND, iface, collector.kwinBlurManagerVersion, 0,
                    collector.kwinBlurManagerName, namePtr, collector.kwinBlurManagerVersion, MemorySegment.NULL,
                ) as MemorySegment).address()
            }.getOrDefault(0L)
        }

        WaylandGlobals(
            compositorPtr           = compositor.address(),
            xdgWmBasePtr            = xdgWmBasePtr,
            decorationManagerPtr    = decorationManagerPtr,
            seatPtr                 = seatPtr,
            seatVersion             = seatVersion,
            outputPtr               = outputPtr,
            outputVersion           = outputVersion,
            textInputManagerPtr     = textInputManagerPtr,
            shmPtr                  = shmPtr,
            shmVersion              = shmVersion,
            pointerConstraintsPtr   = pointerConstraintsPtr,
            iconManagerPtr          = iconManagerPtr,
            activationManagerPtr    = activationManagerPtr,
            extBackgroundEffectManagerPtr = extBackgroundEffectManagerPtr,
            kwinBlurManagerPtr      = kwinBlurManagerPtr,
        )
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
