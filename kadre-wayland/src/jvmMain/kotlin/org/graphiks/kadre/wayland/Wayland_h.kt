/**
 * FFM bindings for the Wayland functions required for window management.
 *
 * Loads libwayland-client.so.0 and libxkbcommon.so.0 via SymbolLookup.libraryLookup
 * with a tryCreate pattern (try/catch Throwable) so the build passes on
 * macOS/Windows without the Wayland libraries installed.
 *
 * Exposed functions (libwayland-client):
 *  - wl_display_connect
 *  - wl_display_disconnect
 *  - wl_display_get_fd
 *  - wl_display_dispatch
 *  - wl_display_dispatch_pending
 *  - wl_display_prepare_read
 *  - wl_display_read_events
 *  - wl_display_cancel_read
 *  - wl_display_flush
 *  - wl_display_get_registry
 *  - wl_registry_add_listener
 *  - wl_registry_bind
 *  - wl_proxy_destroy
 *  - wl_proxy_add_listener
 *  - wl_proxy_marshal_flags (xdg_wm_base_get_xdg_surface variant)
 *  - wl_proxy_marshal_flags (wl_compositor_create_surface variant)
 *
 * Reference: https://wayland.freedesktop.org/docs/html/
 */
package org.graphiks.kadre.wayland

import java.lang.foreign.Arena
import java.lang.foreign.FunctionDescriptor
import java.lang.foreign.Linker
import java.lang.foreign.MemorySegment
import java.lang.foreign.SymbolLookup
import java.lang.foreign.ValueLayout
import java.lang.invoke.MethodHandle

// ── Lazy loading of the libraries ─────────────────────────────────────────────

private fun waylandNativeDisabled(): Boolean =
    System.getenv("KADRE_WAYLAND_DISABLE_NATIVE") == "1" ||
        System.getProperty("kadre.wayland.disableNative") == "true"

/**
 * Lookup of libwayland-client.so.0 — null on non-Wayland platforms.
 *
 * The try/catch on Throwable is intentional: SymbolLookup.libraryLookup
 * may throw IllegalArgumentException or UnsatisfiedLinkError on macOS/Windows,
 * and we want the build to stay green in all cases.
 */
internal val libWaylandClient: SymbolLookup? by lazy {
    if (waylandNativeDisabled()) return@lazy null
    try {
        SymbolLookup.libraryLookup("libwayland-client.so.0", Arena.global())
    } catch (e: Throwable) {
        null
    }
}

/**
 * Lookup of libc.so.6 — null on non-Linux platforms or if absent.
 *
 * Used for poll(), eventfd(), read(), write(), close() in WaylandEventLoop.
 */
internal val libC: SymbolLookup? by lazy {
    try { SymbolLookup.libraryLookup("libc.so.6", Arena.global()) } catch (_: Throwable) { null }
}

/**
 * Lookup of libxkbcommon.so.0 — null on non-Wayland platforms.
 */
internal val libXkbCommon: SymbolLookup? by lazy {
    if (waylandNativeDisabled()) return@lazy null
    try {
        SymbolLookup.libraryLookup("libxkbcommon.so.0", Arena.global())
    } catch (e: Throwable) {
        null
    }
}

private val linker: Linker = Linker.nativeLinker()

// ── Helpers ───────────────────────────────────────────────────────────────────

/**
 * Looks up a symbol in a SymbolLookup and creates a downcall MethodHandle.
 * Returns null if the lookup is null or if the symbol is not found.
 */
private fun SymbolLookup?.downcall(name: String, desc: FunctionDescriptor): MethodHandle? {
    this ?: return null
    return this.find(name).map { linker.downcallHandle(it, desc) }.orElse(null)
}

/** Address of a *data* symbol (e.g. struct wl_interface) exported by the lib, or null. */
private fun SymbolLookup?.symbol(name: String): MemorySegment? =
    this?.find(name)?.orElse(null)

/**
 * Creates a native upcall stub (C function pointer → Kotlin MethodHandle).
 * Used for Wayland listeners (wl_registry.global, etc.).
 */
internal fun upcallStub(
    handle: MethodHandle,
    descriptor: FunctionDescriptor,
    arena: java.lang.foreign.Arena,
): MemorySegment = linker.upcallStub(handle, descriptor, arena)

// ── Globals discovery: registry / bind via wl_proxy_marshal_flags ──
//
// wl_display_get_registry and wl_registry_bind are `static inline` functions in the
// header (NOT exported symbols): we must therefore go through wl_proxy_marshal_flags. The
// interface structures, however, ARE exported by libwayland-client.so.0.

/** &wl_registry_interface — required by get_registry (new_id request). */
internal val wlRegistryInterface: MemorySegment? by lazy { libWaylandClient.symbol("wl_registry_interface") }

/** &wl_compositor_interface — required by bind(wl_compositor). */
internal val wlCompositorInterface: MemorySegment? by lazy { libWaylandClient.symbol("wl_compositor_interface") }

/** uint32_t wl_proxy_get_version(struct wl_proxy *proxy). */
internal val wlProxyGetVersion: MethodHandle? by lazy {
    libWaylandClient.downcall("wl_proxy_get_version",
        FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.ADDRESS))
}

/** int wl_display_roundtrip(struct wl_display *display) — blocks until requests are processed. */
internal val wlDisplayRoundtrip: MethodHandle? by lazy {
    libWaylandClient.downcall("wl_display_roundtrip",
        FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.ADDRESS))
}

/**
 * wl_proxy_marshal_flags for a simple new_id request (proxy, opcode, interface, version,
 * flags, new_id=NULL) — used by wl_display.get_registry (opcode 1).
 */
internal val wlProxyMarshalNewId: MethodHandle? by lazy {
    libWaylandClient.downcall("wl_proxy_marshal_flags",
        FunctionDescriptor.of(ValueLayout.ADDRESS,
            ValueLayout.ADDRESS,   // proxy
            ValueLayout.JAVA_INT,  // opcode
            ValueLayout.ADDRESS,   // interface
            ValueLayout.JAVA_INT,  // version
            ValueLayout.JAVA_INT,  // flags
            ValueLayout.ADDRESS,   // new_id = NULL
        ))
}

/**
 * wl_proxy_marshal_flags for wl_registry.bind (opcode 0), extended variadic signature:
 * (registry, 0, interface, version, flags, name(u), interface->name(s), version(u), new_id=NULL).
 */
internal val wlProxyMarshalBind: MethodHandle? by lazy {
    libWaylandClient.downcall("wl_proxy_marshal_flags",
        FunctionDescriptor.of(ValueLayout.ADDRESS,
            ValueLayout.ADDRESS,   // proxy (registry)
            ValueLayout.JAVA_INT,  // opcode (0 = bind)
            ValueLayout.ADDRESS,   // interface (&wl_compositor_interface)
            ValueLayout.JAVA_INT,  // version
            ValueLayout.JAVA_INT,  // flags
            ValueLayout.JAVA_INT,  // arg: name (uint)
            ValueLayout.ADDRESS,   // arg: interface->name (const char*)
            ValueLayout.JAVA_INT,  // arg: version (uint)
            ValueLayout.ADDRESS,   // arg: new_id = NULL
        ))
}

// ── wl_display_connect ────────────────────────────────────────────────────────

/**
 * struct wl_display *wl_display_connect(const char *name);
 *
 * Connects to the Wayland server. Pass NULL to use WAYLAND_DISPLAY
 * (usually "wayland-0"). Returns a wl_display* pointer or NULL on
 * failure.
 */
internal val wlDisplayConnect: MethodHandle? by lazy {
    libWaylandClient.downcall(
        "wl_display_connect",
        FunctionDescriptor.of(
            ValueLayout.ADDRESS, // wl_display*
            ValueLayout.ADDRESS, // const char* name (or NULL)
        )
    )
}

// ── wl_display_disconnect ─────────────────────────────────────────────────────

/**
 * void wl_display_disconnect(struct wl_display *display);
 *
 * Closes the connection to the Wayland server and frees the associated resources.
 */
internal val wlDisplayDisconnect: MethodHandle? by lazy {
    libWaylandClient.downcall(
        "wl_display_disconnect",
        FunctionDescriptor.ofVoid(
            ValueLayout.ADDRESS, // wl_display*
        )
    )
}

// ── wl_display_get_fd ─────────────────────────────────────────────────────────

/**
 * int wl_display_get_fd(struct wl_display *display);
 *
 * Returns the file descriptor of the Wayland connection socket.
 * Useful for integrating the Wayland event loop into a selector (epoll).
 */
internal val wlDisplayGetFd: MethodHandle? by lazy {
    libWaylandClient.downcall(
        "wl_display_get_fd",
        FunctionDescriptor.of(
            ValueLayout.JAVA_INT, // int fd
            ValueLayout.ADDRESS,  // wl_display*
        )
    )
}

// ── wl_display_dispatch ───────────────────────────────────────────────────────

/**
 * int wl_display_dispatch(struct wl_display *display);
 *
 * Processes pending events and blocks until one is received.
 * Returns the number of events processed, or -1 on error.
 */
internal val wlDisplayDispatch: MethodHandle? by lazy {
    libWaylandClient.downcall(
        "wl_display_dispatch",
        FunctionDescriptor.of(
            ValueLayout.JAVA_INT, // int (number of events or -1)
            ValueLayout.ADDRESS,  // wl_display*
        )
    )
}

// ── wl_display_dispatch_pending ───────────────────────────────────────────────

/**
 * int wl_display_dispatch_pending(struct wl_display *display);
 *
 * Processes only the events already queued, without blocking.
 * Returns the number of events processed, or -1 on error.
 */
internal val wlDisplayDispatchPending: MethodHandle? by lazy {
    libWaylandClient.downcall(
        "wl_display_dispatch_pending",
        FunctionDescriptor.of(
            ValueLayout.JAVA_INT, // int (number of events or -1)
            ValueLayout.ADDRESS,  // wl_display*
        )
    )
}

// ── wl_display_prepare_read ───────────────────────────────────────────────────

/**
 * int wl_display_prepare_read(struct wl_display *display);
 *
 * Announces the intent to read events from the Wayland socket.
 * Must be followed by wl_display_read_events() or wl_display_cancel_read().
 * Returns 0 on success, -1 if events are already pending.
 */
internal val wlDisplayPrepareRead: MethodHandle? by lazy {
    libWaylandClient.downcall(
        "wl_display_prepare_read",
        FunctionDescriptor.of(
            ValueLayout.JAVA_INT, // int (0 or -1)
            ValueLayout.ADDRESS,  // wl_display*
        )
    )
}

// ── wl_display_read_events ────────────────────────────────────────────────────

/**
 * int wl_display_read_events(struct wl_display *display);
 *
 * Reads events from the socket and places them in the queue.
 * To be called after wl_display_prepare_read(). Returns 0 or -1.
 */
internal val wlDisplayReadEvents: MethodHandle? by lazy {
    libWaylandClient.downcall(
        "wl_display_read_events",
        FunctionDescriptor.of(
            ValueLayout.JAVA_INT, // int (0 or -1)
            ValueLayout.ADDRESS,  // wl_display*
        )
    )
}

// ── wl_display_cancel_read ────────────────────────────────────────────────────

/**
 * void wl_display_cancel_read(struct wl_display *display);
 *
 * Cancels the read intent declared by wl_display_prepare_read().
 * To be called if we decide not to read (e.g. selector timeout).
 */
internal val wlDisplayCancelRead: MethodHandle? by lazy {
    libWaylandClient.downcall(
        "wl_display_cancel_read",
        FunctionDescriptor.ofVoid(
            ValueLayout.ADDRESS, // wl_display*
        )
    )
}

// ── wl_display_flush ──────────────────────────────────────────────────────────

/**
 * int wl_display_flush(struct wl_display *display);
 *
 * Sends the data pending in the send buffer to the server.
 * Returns the number of bytes sent, or -1 on error (errno = EAGAIN
 * if the socket is non-blocking and the buffer is full).
 */
internal val wlDisplayFlush: MethodHandle? by lazy {
    libWaylandClient.downcall(
        "wl_display_flush",
        FunctionDescriptor.of(
            ValueLayout.JAVA_INT, // int (bytes sent or -1)
            ValueLayout.ADDRESS,  // wl_display*
        )
    )
}

// ── wl_display_get_registry ───────────────────────────────────────────────────

/**
 * struct wl_registry *wl_display_get_registry(struct wl_display *display);
 *
 * Returns the global Wayland registry, the entry point for binding global
 * interfaces (wl_compositor, xdg_wm_base, etc.) via wl_registry_bind.
 */
internal val wlDisplayGetRegistry: MethodHandle? by lazy {
    libWaylandClient.downcall("wl_display_get_registry",
        FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS))
}

// ── wl_registry_add_listener ──────────────────────────────────────────────────

/**
 * int wl_registry_add_listener(struct wl_registry *registry,
 *     const struct wl_registry_listener *listener, void *data);
 *
 * Registers a listener for registry events (global/global_remove).
 * Returns 0 on success, -1 on error.
 */
internal val wlRegistryAddListener: MethodHandle? by lazy {
    libWaylandClient.downcall("wl_registry_add_listener",
        FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS))
}

// ── wl_registry_bind ──────────────────────────────────────────────────────────

/**
 * void *wl_registry_bind(struct wl_registry *registry, uint32_t name,
 *     const struct wl_interface *interface, uint32_t version);
 *
 * Binds a global interface by its numeric name (announced via wl_registry.global).
 * Returns an opaque Wayland proxy (wl_compositor*, xdg_wm_base*, etc.).
 */
internal val wlRegistryBind: MethodHandle? by lazy {
    libWaylandClient.downcall("wl_registry_bind",
        FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_INT, ValueLayout.ADDRESS, ValueLayout.JAVA_INT))
}

// ── wl_proxy_destroy ──────────────────────────────────────────────────────────

/**
 * void wl_proxy_destroy(struct wl_proxy *proxy);
 *
 * Destroys a Wayland proxy and frees its resources. To be called before
 * disconnecting the display for any proxy not destroyed by a destroy operation.
 */
internal val wlProxyDestroy: MethodHandle? by lazy {
    libWaylandClient.downcall("wl_proxy_destroy",
        FunctionDescriptor.ofVoid(ValueLayout.ADDRESS))
}

// ── wl_proxy_add_listener ─────────────────────────────────────────────────────

/**
 * int wl_proxy_add_listener(struct wl_proxy *proxy,
 *     void (**implementation)(void), void *data);
 *
 * Associates a function table (vtable) and user data with a proxy.
 * Used to receive client-side events (configure, ping, etc.).
 * Returns 0 on success, -1 on error.
 */
internal val wlProxyAddListener: MethodHandle? by lazy {
    libWaylandClient.downcall("wl_proxy_add_listener",
        FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS))
}

// ── wl_proxy_marshal_flags (xdg_wm_base_get_xdg_surface variant) ─────────────

/**
 * struct wl_proxy *wl_proxy_marshal_flags(struct wl_proxy *proxy,
 *     uint32_t opcode, const struct wl_interface *interface,
 *     uint32_t version, uint32_t flags,
 *     struct wl_proxy *new_id, struct wl_proxy *surface);
 *
 * 7-argument variant of wl_proxy_marshal_flags used by
 * xdg_wm_base_get_xdg_surface (opcode XDG_WM_BASE_GET_XDG_SURFACE).
 *
 * The C function is variadic; here we bind the concrete form with 2 additional
 * arguments (new_id + surface) that exactly matches this opcode.
 */
internal val wlProxyMarshalFlagsGetXdgSurface: MethodHandle? by lazy {
    libWaylandClient.downcall("wl_proxy_marshal_flags",
        FunctionDescriptor.of(ValueLayout.ADDRESS,
            ValueLayout.ADDRESS,   // proxy
            ValueLayout.JAVA_INT,  // opcode
            ValueLayout.ADDRESS,   // interface
            ValueLayout.JAVA_INT,  // version
            ValueLayout.JAVA_INT,  // flags
            ValueLayout.ADDRESS,   // arg: new_id
            ValueLayout.ADDRESS,   // arg: surface
        ))
}

// ── wl_compositor_create_surface ──────────────────────────────────────────────

/**
 * Address of the `wl_surface_interface` structure exported by libwayland-client.so.0.
 *
 * `wl_proxy_marshal_flags` requires a NON-NULL `wl_interface*` for new_id requests
 * (such as create_surface) to type the returned proxy: it dereferences this
 * structure. Passing NULL causes a SIGSEGV. We therefore fetch the real exported symbol.
 */
internal val wlSurfaceInterface: MemorySegment? by lazy {
    libWaylandClient?.find("wl_surface_interface")?.orElse(null)
}

internal val wlRegionInterface: MemorySegment? by lazy {
    libWaylandClient?.find("wl_region_interface")?.orElse(null)
}

/**
 * wl_compositor_create_surface: creates a wl_surface from a wl_compositor.
 *
 * Calls wl_proxy_marshal_flags(compositor, 0, &wl_surface_interface, version, 0)
 * where opcode 0 corresponds to wl_compositor.create_surface in the Wayland protocol.
 *
 * The fixed 5-argument variant (without an additional new_id) matches
 * the variadic form of wl_proxy_marshal_flags for a simple new_id opcode:
 * the returned proxy is the new wl_surface*.
 *
 * @see XdgShellConstants — associated xdg_shell protocol opcodes.
 */
internal val wlCompositorCreateSurface: MethodHandle? by lazy {
    libWaylandClient.downcall("wl_proxy_marshal_flags",
        FunctionDescriptor.of(ValueLayout.ADDRESS,
            ValueLayout.ADDRESS,   // wl_proxy* (compositor)
            ValueLayout.JAVA_INT,  // opcode (0 = create_surface)
            ValueLayout.ADDRESS,   // wl_interface* (&wl_surface_interface — MUST be non-NULL)
            ValueLayout.JAVA_INT,  // version
            ValueLayout.JAVA_INT,  // flags (0)
            ValueLayout.ADDRESS,   // arg new_id: NULL (placeholder, generated by libwayland)
        ))
}

/**
 * wl_compositor.create_region: creates a wl_region from a wl_compositor.
 */
internal val wlCompositorCreateRegion: MethodHandle? by lazy {
    libWaylandClient.downcall("wl_proxy_marshal_flags",
        FunctionDescriptor.of(ValueLayout.ADDRESS,
            ValueLayout.ADDRESS,   // wl_proxy* (compositor)
            ValueLayout.JAVA_INT,  // opcode (1 = create_region)
            ValueLayout.ADDRESS,   // wl_interface* (&wl_region_interface)
            ValueLayout.JAVA_INT,  // version
            ValueLayout.JAVA_INT,  // flags
            ValueLayout.ADDRESS,   // arg new_id: NULL
        ))
}

// ── wl_proxy_marshal_flags (wl_surface_commit variant) ────────────────────────

/**
 * Variant of wl_proxy_marshal_flags without an additional argument, used for
 * wl_surface.commit (opcode 6) and other opcodes without a return parameter.
 *
 * Signature: void wl_proxy_marshal_flags(proxy, opcode, NULL, version, 0)
 * with NULL as the wl_interface* for calls without new_id.
 */
internal val wlProxyMarshalFlagsVoid: MethodHandle? by lazy {
    libWaylandClient.downcall("wl_proxy_marshal_flags",
        FunctionDescriptor.ofVoid(
            ValueLayout.ADDRESS,   // wl_proxy* (target surface / proxy)
            ValueLayout.JAVA_INT,  // opcode
            ValueLayout.ADDRESS,   // wl_interface* (NULL)
            ValueLayout.JAVA_INT,  // version
            ValueLayout.JAVA_INT,  // flags (0)
        ))
}

/**
 * wl_proxy_marshal_flags variant with a single trailing uint32 argument and no new_id,
 * e.g. xdg_surface.ack_configure(serial) (opcode 4) and xdg_wm_base.pong(serial) (opcode 3).
 *
 * Signature: void wl_proxy_marshal_flags(proxy, opcode, NULL, version, flags, uint).
 */
internal val wlProxyMarshalFlagsUint: MethodHandle? by lazy {
    libWaylandClient.downcall("wl_proxy_marshal_flags",
        FunctionDescriptor.ofVoid(
            ValueLayout.ADDRESS,   // wl_proxy*
            ValueLayout.JAVA_INT,  // opcode
            ValueLayout.ADDRESS,   // wl_interface* (NULL)
            ValueLayout.JAVA_INT,  // version
            ValueLayout.JAVA_INT,  // flags
            ValueLayout.JAVA_INT,  // arg: uint32
        ))
}

/**
 * wl_proxy_marshal_flags variant with a single trailing string (const char*) argument and no
 * new_id, e.g. xdg_toplevel.set_title(title) (opcode 2) / set_app_id (opcode 3).
 *
 * Signature: void wl_proxy_marshal_flags(proxy, opcode, NULL, version, flags, const char*).
 */
internal val wlProxyMarshalFlagsString: MethodHandle? by lazy {
    libWaylandClient.downcall("wl_proxy_marshal_flags",
        FunctionDescriptor.ofVoid(
            ValueLayout.ADDRESS,   // wl_proxy*
            ValueLayout.JAVA_INT,  // opcode
            ValueLayout.ADDRESS,   // wl_interface* (NULL)
            ValueLayout.JAVA_INT,  // version
            ValueLayout.JAVA_INT,  // flags
            ValueLayout.ADDRESS,   // arg: const char*
        ))
}

/**
 * wl_proxy_marshal_flags variant with one object argument and no new_id.
 *
 * Used for wl_surface.set_opaque_region(region).
 */
internal val wlProxyMarshalFlagsObject: MethodHandle? by lazy {
    libWaylandClient.downcall("wl_proxy_marshal_flags",
        FunctionDescriptor.ofVoid(
            ValueLayout.ADDRESS,   // wl_proxy*
            ValueLayout.JAVA_INT,  // opcode
            ValueLayout.ADDRESS,   // wl_interface* (NULL)
            ValueLayout.JAVA_INT,  // version
            ValueLayout.JAVA_INT,  // flags
            ValueLayout.ADDRESS,   // arg: object*
        ))
}

/**
 * wl_proxy_marshal_flags variant with four int32 arguments and no new_id.
 *
 * Used for wl_region.add(x, y, width, height).
 */
internal val wlProxyMarshalFlagsFourInt: MethodHandle? by lazy {
    libWaylandClient.downcall("wl_proxy_marshal_flags",
        FunctionDescriptor.ofVoid(
            ValueLayout.ADDRESS,   // wl_proxy*
            ValueLayout.JAVA_INT,  // opcode
            ValueLayout.ADDRESS,   // wl_interface* (NULL)
            ValueLayout.JAVA_INT,  // version
            ValueLayout.JAVA_INT,  // flags
            ValueLayout.JAVA_INT,  // x
            ValueLayout.JAVA_INT,  // y
            ValueLayout.JAVA_INT,  // width
            ValueLayout.JAVA_INT,  // height
        ))
}

/**
 * wl_proxy_marshal_flags variant with two trailing uint32 arguments and no new_id.
 * Used for e.g. xdg_toplevel.set_min_size(width, height) and set_max_size(width, height).
 *
 * Signature: void wl_proxy_marshal_flags(proxy, opcode, NULL, version, flags, uint1, uint2).
 */
internal val wlProxyMarshalFlagsTwoUint: MethodHandle? by lazy {
    libWaylandClient.downcall("wl_proxy_marshal_flags",
        FunctionDescriptor.ofVoid(
            ValueLayout.ADDRESS,   // wl_proxy*
            ValueLayout.JAVA_INT,  // opcode
            ValueLayout.ADDRESS,   // wl_interface* (NULL)
            ValueLayout.JAVA_INT,  // version
            ValueLayout.JAVA_INT,  // flags
            ValueLayout.JAVA_INT,  // arg1: uint32
            ValueLayout.JAVA_INT,  // arg2: uint32
        ))
}

/**
 * wl_proxy_marshal_flags variant with one object and one uint32 argument.
 * Used for xdg_toplevel.move(seat, serial).
 *
 * Signature: void wl_proxy_marshal_flags(proxy, opcode, NULL, version, flags, object, uint).
 */
internal val wlProxyMarshalFlagsObjectUint: MethodHandle? by lazy {
    libWaylandClient.downcall("wl_proxy_marshal_flags",
        FunctionDescriptor.ofVoid(
            ValueLayout.ADDRESS,   // wl_proxy*
            ValueLayout.JAVA_INT,  // opcode
            ValueLayout.ADDRESS,   // wl_interface* (NULL)
            ValueLayout.JAVA_INT,  // version
            ValueLayout.JAVA_INT,  // flags
            ValueLayout.ADDRESS,   // arg1: object*
            ValueLayout.JAVA_INT,  // arg2: uint32
        ))
}

/**
 * wl_proxy_marshal_flags variant with one object, one uint32 argument and two int32 arguments.
 * Used for xdg_toplevel.show_window_menu(seat, serial, x, y).
 *
 * Signature: void wl_proxy_marshal_flags(proxy, opcode, NULL, version, flags, object, uint, int, int).
 */
internal val wlProxyMarshalFlagsObjectUintTwoInt: MethodHandle? by lazy {
    libWaylandClient.downcall("wl_proxy_marshal_flags",
        FunctionDescriptor.ofVoid(
            ValueLayout.ADDRESS,   // wl_proxy*
            ValueLayout.JAVA_INT,  // opcode
            ValueLayout.ADDRESS,   // wl_interface* (NULL)
            ValueLayout.JAVA_INT,  // version
            ValueLayout.JAVA_INT,  // flags
            ValueLayout.ADDRESS,   // arg1: object*
            ValueLayout.JAVA_INT,  // arg2: uint32
            ValueLayout.JAVA_INT,  // arg3: int32
            ValueLayout.JAVA_INT,  // arg4: int32
        ))
}

/**
 * wl_proxy_marshal_flags variant with uint32, object and two int32 arguments.
 * Used for wl_pointer.set_cursor(serial, surface, hotspot_x, hotspot_y).
 *
 * Signature: void wl_proxy_marshal_flags(proxy, opcode, NULL, version, flags, uint, object, int, int).
 */
internal val wlProxyMarshalFlagsUintObjectTwoInt: MethodHandle? by lazy {
    libWaylandClient.downcall("wl_proxy_marshal_flags",
        FunctionDescriptor.ofVoid(
            ValueLayout.ADDRESS,   // wl_proxy*
            ValueLayout.JAVA_INT,  // opcode
            ValueLayout.ADDRESS,   // wl_interface* (NULL)
            ValueLayout.JAVA_INT,  // version
            ValueLayout.JAVA_INT,  // flags
            ValueLayout.JAVA_INT,  // arg1: uint32
            ValueLayout.ADDRESS,   // arg2: object*
            ValueLayout.JAVA_INT,  // arg3: int32
            ValueLayout.JAVA_INT,  // arg4: int32
        ))
}

/**
 * wl_proxy_marshal_flags variant with one object and two uint32 arguments.
 * Used for xdg_toplevel.resize(seat, serial, edges).
 *
 * Signature: void wl_proxy_marshal_flags(proxy, opcode, NULL, version, flags, object, uint, uint).
 */
internal val wlProxyMarshalFlagsObjectTwoUint: MethodHandle? by lazy {
    libWaylandClient.downcall("wl_proxy_marshal_flags",
        FunctionDescriptor.ofVoid(
            ValueLayout.ADDRESS,   // wl_proxy*
            ValueLayout.JAVA_INT,  // opcode
            ValueLayout.ADDRESS,   // wl_interface* (NULL)
            ValueLayout.JAVA_INT,  // version
            ValueLayout.JAVA_INT,  // flags
            ValueLayout.ADDRESS,   // arg1: object*
            ValueLayout.JAVA_INT,  // arg2: uint32
            ValueLayout.JAVA_INT,  // arg3: uint32
        ))
}

/**
 * wl_proxy_marshal_flags variant with one object and two int32 arguments.
 * Used for wl_surface.attach(buffer, dx, dy) — opcode 1.
 *
 * Signature: void wl_proxy_marshal_flags(proxy, opcode, NULL, version, flags, object, int, int).
 */
internal val wlProxyMarshalFlagsObjectTwoInt: MethodHandle? by lazy {
    libWaylandClient.downcall("wl_proxy_marshal_flags",
        FunctionDescriptor.ofVoid(
            ValueLayout.ADDRESS,   // wl_proxy*
            ValueLayout.JAVA_INT,  // opcode
            ValueLayout.ADDRESS,   // wl_interface* (NULL)
            ValueLayout.JAVA_INT,  // version
            ValueLayout.JAVA_INT,  // flags
            ValueLayout.ADDRESS,   // arg1: object* (wl_buffer)
            ValueLayout.JAVA_INT,  // arg2: int32 (dx)
            ValueLayout.JAVA_INT,  // arg3: int32 (dy)
        ))
}

/**
 * wl_proxy_marshal_flags variant with a string then an object argument and no new_id.
 * Used for xdg_activation_v1.activate(token, surface) — opcode 2.
 *
 * Signature: void wl_proxy_marshal_flags(proxy, opcode, NULL, version, flags, const char*, object*).
 */
internal val wlProxyMarshalFlagsStringObject: MethodHandle? by lazy {
    libWaylandClient.downcall("wl_proxy_marshal_flags",
        FunctionDescriptor.ofVoid(
            ValueLayout.ADDRESS,   // wl_proxy*
            ValueLayout.JAVA_INT,  // opcode
            ValueLayout.ADDRESS,   // wl_interface* (NULL)
            ValueLayout.JAVA_INT,  // version
            ValueLayout.JAVA_INT,  // flags
            ValueLayout.ADDRESS,   // arg1: const char*
            ValueLayout.ADDRESS,   // arg2: object*
        ))
}

/**
 * wl_proxy_marshal_flags variant with a uint then an object argument and no new_id.
 * Used for xdg_activation_token_v1.set_serial(serial, seat) — opcode 1.
 *
 * Signature: void wl_proxy_marshal_flags(proxy, opcode, NULL, version, flags, uint, object*).
 */
internal val wlProxyMarshalFlagsUintObject: MethodHandle? by lazy {
    libWaylandClient.downcall("wl_proxy_marshal_flags",
        FunctionDescriptor.ofVoid(
            ValueLayout.ADDRESS,   // wl_proxy*
            ValueLayout.JAVA_INT,  // opcode
            ValueLayout.ADDRESS,   // wl_interface* (NULL)
            ValueLayout.JAVA_INT,  // version
            ValueLayout.JAVA_INT,  // flags
            ValueLayout.JAVA_INT,  // arg1: uint32
            ValueLayout.ADDRESS,   // arg2: object*
        ))
}

/**
 * wl_proxy_marshal_flags variant with a uint then two object arguments and no new_id.
 * Used for xdg_activation_v1.activate_full(serial, seat, surface) — opcode 3.
 *
 * Signature: void wl_proxy_marshal_flags(proxy, opcode, NULL, version, flags, uint, object*, object*).
 */
internal val wlProxyMarshalFlagsUintTwoObjects: MethodHandle? by lazy {
    libWaylandClient.downcall("wl_proxy_marshal_flags",
        FunctionDescriptor.ofVoid(
            ValueLayout.ADDRESS,   // wl_proxy*
            ValueLayout.JAVA_INT,  // opcode
            ValueLayout.ADDRESS,   // wl_interface* (NULL)
            ValueLayout.JAVA_INT,  // version
            ValueLayout.JAVA_INT,  // flags
            ValueLayout.JAVA_INT,  // arg1: uint32
            ValueLayout.ADDRESS,   // arg2: object*
            ValueLayout.ADDRESS,   // arg3: object*
        ))
}

// ── wl_shm helpers ────────────────────────────────────────────────────────────

/**
 * wl_shm.create_pool (opcode 0) via wl_proxy_marshal_flags.
 *
 * Signature: wl_shm_pool* wl_proxy_marshal_flags(shm, 0, &wl_shm_pool_interface, version, flags, fd, size, NULL).
 */
internal val wlShmCreatePool: MethodHandle? by lazy {
    libWaylandClient.downcall("wl_proxy_marshal_flags",
        FunctionDescriptor.of(ValueLayout.ADDRESS,
            ValueLayout.ADDRESS,   // wl_shm*
            ValueLayout.JAVA_INT,  // opcode = 0
            ValueLayout.ADDRESS,   // &wl_shm_pool_interface
            ValueLayout.JAVA_INT,  // version
            ValueLayout.JAVA_INT,  // flags
            ValueLayout.JAVA_INT,  // fd
            ValueLayout.JAVA_INT,  // size
            ValueLayout.ADDRESS,   // new_id = NULL
        ))
}

/**
 * wl_shm_pool.create_buffer (opcode 0) via wl_proxy_marshal_flags.
 *
 * Signature: wl_buffer* wl_proxy_marshal_flags(pool, 0, &wl_buffer_interface, version, flags, offset, width, height, stride, format, NULL).
 */
internal val wlShmPoolCreateBuffer: MethodHandle? by lazy {
    libWaylandClient.downcall("wl_proxy_marshal_flags",
        FunctionDescriptor.of(ValueLayout.ADDRESS,
            ValueLayout.ADDRESS,   // wl_shm_pool*
            ValueLayout.JAVA_INT,  // opcode = 0
            ValueLayout.ADDRESS,   // &wl_buffer_interface
            ValueLayout.JAVA_INT,  // version
            ValueLayout.JAVA_INT,  // flags
            ValueLayout.JAVA_INT,  // offset
            ValueLayout.JAVA_INT,  // width
            ValueLayout.JAVA_INT,  // height
            ValueLayout.JAVA_INT,  // stride
            ValueLayout.JAVA_INT,  // format (uint32)
            ValueLayout.ADDRESS,   // new_id = NULL
        ))
}

// ── libwayland-cursor (cursor theme) ───────────────────────────────────────────

/**
 * Lookup of libwayland-cursor.so.0 — null on non-Linux or if absent.
 * Used for cursor theme loading (setCursor with CursorIcon).
 */
internal val libWaylandCursor: SymbolLookup? by lazy {
    if (waylandNativeDisabled()) return@lazy null
    try { SymbolLookup.libraryLookup("libwayland-cursor.so.0", Arena.global()) }
    catch (_: Throwable) { null }
}

/**
 * struct wl_cursor_theme *wl_cursor_theme_load(const char *name, int size, struct wl_shm *shm);
 *
 * Loads a cursor theme with the given name and size (pixels). Returns NULL on failure.
 */
internal val wlCursorThemeLoad: MethodHandle? by lazy {
    libWaylandCursor.downcall("wl_cursor_theme_load",
        FunctionDescriptor.of(ValueLayout.ADDRESS,
            ValueLayout.ADDRESS,   // const char *name
            ValueLayout.JAVA_INT,  // int size
            ValueLayout.ADDRESS,   // struct wl_shm *shm
        ))
}

/**
 * struct wl_cursor *wl_cursor_theme_get_cursor(struct wl_cursor_theme *theme, const char *name);
 *
 * Returns the cursor with the given name from the theme, or NULL.
 */
internal val wlCursorThemeGetCursor: MethodHandle? by lazy {
    libWaylandCursor.downcall("wl_cursor_theme_get_cursor",
        FunctionDescriptor.of(ValueLayout.ADDRESS,
            ValueLayout.ADDRESS,   // struct wl_cursor_theme *
            ValueLayout.ADDRESS,   // const char *name
        ))
}

/**
 * struct wl_buffer *wl_cursor_image_get_buffer(struct wl_cursor_image *image);
 *
 * Returns the wl_buffer associated with a wl_cursor_image.
 */
internal val wlCursorImageGetBuffer: MethodHandle? by lazy {
    libWaylandCursor.downcall("wl_cursor_image_get_buffer",
        FunctionDescriptor.of(ValueLayout.ADDRESS,
            ValueLayout.ADDRESS,   // struct wl_cursor_image *
        ))
}

/**
 * void wl_cursor_theme_destroy(struct wl_cursor_theme *theme);
 *
 * Destroys a cursor theme and all associated cursors and images.
 */
internal val wlCursorThemeDestroy: MethodHandle? by lazy {
    libWaylandCursor.downcall("wl_cursor_theme_destroy",
        FunctionDescriptor.ofVoid(
            ValueLayout.ADDRESS,   // struct wl_cursor_theme *
        ))
}

// ── zwp_text_input_v3 protocol (protocol extension interface symbols) ────────
//
// The `zwp_text_input_manager_v3_interface` and `zwp_text_input_v3_interface`
// symbols are NOT exported by libwayland-client.so.0 (they are generated by
// wayland-scanner per-application). We construct minimal wl_interface structs
// so that wl_registry_bind / wl_proxy_marshal_flags can extract the interface
// name string and create the proxy.

/** Arena that lives for the process lifetime — holds our custom wl_interface structs. */
private val textInputArena: Arena = Arena.ofShared()

/** Constructs a minimal `wl_interface` struct for a Wayland protocol extension. */
internal fun buildWaylandInterface(
    name: String,
    version: Int = 1,
    methodCount: Int = 0,
    eventCount: Int = 0,
): MemorySegment {
    val nameSeg = textInputArena.allocateFrom(name)
    val ptr: Long = ValueLayout.ADDRESS.byteSize()
    val iface = textInputArena.allocate(ptr + 4 + 4 + ptr + 4 + 4 + ptr)
    iface.set(ValueLayout.ADDRESS, 0L, nameSeg)                // const char *name
    iface.set(ValueLayout.JAVA_INT, ptr, version)               // int version
    iface.set(ValueLayout.JAVA_INT, ptr + 4, methodCount)       // method_count
    iface.set(ValueLayout.ADDRESS, ptr + 8, MemorySegment.NULL) // methods = NULL
    iface.set(ValueLayout.JAVA_INT, ptr + 8 + ptr, eventCount)  // event_count
    iface.set(ValueLayout.ADDRESS, ptr + 8 + ptr + 8, MemorySegment.NULL) // events = NULL
    return iface
}

/** &xdg_activation_v1_interface (minimal, for bind + get_activation_token). */
internal val xdgActivationV1Interface: MemorySegment by lazy {
    buildWaylandInterface("xdg_activation_v1", methodCount = 4, eventCount = 1)
}

/** &xdg_activation_token_v1_interface (minimal, for get_activation_token new_id). */
internal val xdgActivationTokenV1Interface: MemorySegment by lazy {
    buildWaylandInterface("xdg_activation_token_v1", methodCount = 4, eventCount = 1)
}

/** &zwp_text_input_manager_v3_interface (minimal, for bind + create_text_input). */
internal val zwpTextInputManagerV3Interface: MemorySegment by lazy {
    buildWaylandInterface("zwp_text_input_manager_v3", methodCount = 2, eventCount = 0)
}

/** &zwp_text_input_v3_interface (minimal, for proxy creation). */
internal val zwpTextInputV3Interface: MemorySegment by lazy {
    buildWaylandInterface("zwp_text_input_v3", methodCount = 6, eventCount = 6)
}

// ── zwp_pointer_constraints_v1 protocol (protocol extension interface symbols) ──

/** &zwp_pointer_constraints_v1_interface (minimal, for bind + lock/confine). */
internal val zwpPointerConstraintsV1Interface: MemorySegment by lazy {
    buildWaylandInterface("zwp_pointer_constraints_v1", methodCount = 3, eventCount = 0)
}

/** &zwp_locked_pointer_v1_interface (minimal, for lock_pointer new_id). */
internal val zwpLockedPointerV1Interface: MemorySegment by lazy {
    buildWaylandInterface("zwp_locked_pointer_v1", methodCount = 3, eventCount = 2)
}

/** &zwp_confined_pointer_v1_interface (minimal, for confine_pointer new_id). */
internal val zwpConfinedPointerV1Interface: MemorySegment by lazy {
    buildWaylandInterface("zwp_confined_pointer_v1", methodCount = 2, eventCount = 2)
}

/** Lifetime value: the constraint is released once the next pointer button event is received. */
internal const val ZWP_POINTER_CONSTRAINTS_V1_LIFETIME_ONESHOT: Int = 0

/** Lifetime value: the constraint persists until the client destroys it or the compositor revokes it. */
internal const val ZWP_POINTER_CONSTRAINTS_V1_LIFETIME_PERSISTENT: Int = 1

/**
 * wl_proxy_marshal_flags for zwp_pointer_constraints_v1.lock_pointer (opcode 1).
 *
 * Signature: zwp_locked_pointer_v1* wl_proxy_marshal_flags(
 *     constraints, 1, &zwp_locked_pointer_v1_interface, version, flags,
 *     surface, pointer, region, lifetime, NULL).
 */
internal val wlPointerConstraintsLockPointer: MethodHandle? by lazy {
    libWaylandClient.downcall("wl_proxy_marshal_flags",
        FunctionDescriptor.of(ValueLayout.ADDRESS,
            ValueLayout.ADDRESS,  // wl_proxy* (constraints)
            ValueLayout.JAVA_INT, // opcode = 1
            ValueLayout.ADDRESS,  // wl_interface* (&zwp_locked_pointer_v1_interface)
            ValueLayout.JAVA_INT, // version
            ValueLayout.JAVA_INT, // flags
            ValueLayout.ADDRESS,  // wl_surface*
            ValueLayout.ADDRESS,  // wl_pointer*
            ValueLayout.ADDRESS,  // wl_region* (nullable)
            ValueLayout.JAVA_INT, // lifetime (uint32)
            ValueLayout.ADDRESS,  // new_id = NULL
        ))
}

/**
 * wl_proxy_marshal_flags for zwp_pointer_constraints_v1.confine_pointer (opcode 2).
 *
 * Signature: zwp_confined_pointer_v1* wl_proxy_marshal_flags(
 *     constraints, 2, &zwp_confined_pointer_v1_interface, version, flags,
 *     surface, pointer, region, lifetime, NULL).
 */
internal val wlPointerConstraintsConfinePointer: MethodHandle? by lazy {
    libWaylandClient.downcall("wl_proxy_marshal_flags",
        FunctionDescriptor.of(ValueLayout.ADDRESS,
            ValueLayout.ADDRESS,  // wl_proxy* (constraints)
            ValueLayout.JAVA_INT, // opcode = 2
            ValueLayout.ADDRESS,  // wl_interface* (&zwp_confined_pointer_v1_interface)
            ValueLayout.JAVA_INT, // version
            ValueLayout.JAVA_INT, // flags
            ValueLayout.ADDRESS,  // wl_surface*
            ValueLayout.ADDRESS,  // wl_pointer*
            ValueLayout.ADDRESS,  // wl_region* (nullable)
            ValueLayout.JAVA_INT, // lifetime (uint32)
            ValueLayout.ADDRESS,  // new_id = NULL
        ))
}

// ── xdg_toplevel_icon_manager_v1 protocol (protocol extension interface symbols) ──

/** &xdg_toplevel_icon_manager_v1_interface (minimal, for bind + icon operations). */
internal val xdgToplevelIconManagerV1Interface: MemorySegment by lazy {
    buildWaylandInterface("xdg_toplevel_icon_manager_v1", methodCount = 3, eventCount = 0)
}

/** &xdg_toplevel_icon_v1_interface (minimal, for proxy creation). */
internal val xdgToplevelIconV1Interface: MemorySegment by lazy {
    buildWaylandInterface("xdg_toplevel_icon_v1", methodCount = 4, eventCount = 0)
}

/**
 * wl_proxy_marshal_flags variant with two trailing object arguments and no new_id.
 * Used for xdg_toplevel_icon_manager_v1.set_icon(toplevel, icon).
 *
 * Signature: void wl_proxy_marshal_flags(proxy, opcode, NULL, version, flags, obj1, obj2).
 */
internal val wlProxyMarshalFlagsTwoObjects: MethodHandle? by lazy {
    libWaylandClient.downcall("wl_proxy_marshal_flags",
        FunctionDescriptor.ofVoid(
            ValueLayout.ADDRESS,   // wl_proxy*
            ValueLayout.JAVA_INT,  // opcode
            ValueLayout.ADDRESS,   // wl_interface* (NULL)
            ValueLayout.JAVA_INT,  // version
            ValueLayout.JAVA_INT,  // flags
            ValueLayout.ADDRESS,   // arg1: object*
            ValueLayout.ADDRESS,   // arg2: object*
        ))
}

/**
 * wl_proxy_marshal_flags for zwp_text_input_manager_v3.create_text_input (opcode 1).
 *
 * Signature: zwp_text_input_v3* wl_proxy_marshal_flags(manager, 1, &interface, version, flags, NULL).
 */
internal val zwpInputManagerV3CreateTextInput: MethodHandle? by lazy {
    libWaylandClient.downcall("wl_proxy_marshal_flags",
        FunctionDescriptor.of(ValueLayout.ADDRESS,
            ValueLayout.ADDRESS,  // wl_proxy* (manager)
            ValueLayout.JAVA_INT, // opcode = 1
            ValueLayout.ADDRESS,  // wl_interface* (&zwp_text_input_v3_interface)
            ValueLayout.JAVA_INT, // version
            ValueLayout.JAVA_INT, // flags
            ValueLayout.ADDRESS,  // new_id = NULL
        ))
}

// ── ext_background_effect_v1 (wlroots, KWin 6+) blur protocol interface symbols ──

/** &ext_background_effect_v1_interface (minimal, for bind + create). */
internal val extBackgroundEffectV1Interface: MemorySegment by lazy {
    buildWaylandInterface("ext_background_effect_v1", methodCount = 2, eventCount = 0)
}

/** &ext_background_effect_surface_v1_interface (minimal, for create new_id). */
internal val extBackgroundEffectSurfaceV1Interface: MemorySegment by lazy {
    buildWaylandInterface("ext_background_effect_surface_v1", methodCount = 1, eventCount = 0)
}

/**
 * wl_proxy_marshal_flags for ext_background_effect_v1.create (opcode 1).
 *
 * Signature: ext_background_effect_surface_v1* wl_proxy_marshal_flags(
 *     manager, 1, &ext_background_effect_surface_v1_interface, version, flags, surface, NULL).
 */
internal val extBackgroundEffectV1Create: MethodHandle? by lazy {
    libWaylandClient.downcall("wl_proxy_marshal_flags",
        FunctionDescriptor.of(ValueLayout.ADDRESS,
            ValueLayout.ADDRESS,  // wl_proxy* (manager)
            ValueLayout.JAVA_INT, // opcode = 1
            ValueLayout.ADDRESS,  // wl_interface* (&ext_background_effect_surface_v1_interface)
            ValueLayout.JAVA_INT, // version
            ValueLayout.JAVA_INT, // flags
            ValueLayout.ADDRESS,  // wl_surface*
            ValueLayout.ADDRESS,  // new_id = NULL
        ))
}

// ── org_kde_kwin_blur_manager (KWin 5.x) blur protocol interface symbols ──

/** &org_kde_kwin_blur_manager_interface (minimal, for bind + create). */
internal val orgKdeKwinBlurManagerInterface: MemorySegment by lazy {
    buildWaylandInterface("org_kde_kwin_blur_manager", methodCount = 2, eventCount = 0)
}

/** &org_kde_kwin_blur_interface (minimal, for create new_id). */
internal val orgKdeKwinBlurInterface: MemorySegment by lazy {
    buildWaylandInterface("org_kde_kwin_blur", methodCount = 2, eventCount = 0)
}

/**
 * wl_proxy_marshal_flags for org_kde_kwin_blur_manager.create (opcode 1).
 *
 * Signature: org_kde_kwin_blur* wl_proxy_marshal_flags(
 *     manager, 1, &org_kde_kwin_blur_interface, version, flags, surface, NULL).
 */
internal val kwinBlurManagerCreate: MethodHandle? by lazy {
    libWaylandClient.downcall("wl_proxy_marshal_flags",
        FunctionDescriptor.of(ValueLayout.ADDRESS,
            ValueLayout.ADDRESS,  // wl_proxy* (manager)
            ValueLayout.JAVA_INT, // opcode = 1
            ValueLayout.ADDRESS,  // wl_interface* (&org_kde_kwin_blur_interface)
            ValueLayout.JAVA_INT, // version
            ValueLayout.JAVA_INT, // flags
            ValueLayout.ADDRESS,  // wl_surface*
            ValueLayout.ADDRESS,  // new_id = NULL
        ))
}

// ── wl_surface convenience helpers ────────────────────────────────────────────

/**
 * Calls wl_surface.attach(buffer, dx, dy) — opcode 1.
 *
 * Attaches a wl_buffer to the surface. Pass NULL to detach the current buffer.
 * The dx/dy specify the surface-local coordinates of the buffer's new origin.
 *
 * @see wlProxyMarshalFlagsObjectTwoInt
 */
internal fun wlSurfaceAttach(
    surfacePtr: Long,
    bufferPtr: Long,
    dx: Int,
    dy: Int,
) {
    val marshal = wlProxyMarshalFlagsObjectTwoInt ?: return
    try {
        marshal.invokeExact(
            MemorySegment.ofAddress(surfacePtr),
            1,                           // opcode: wl_surface.attach
            MemorySegment.NULL,          // wl_interface* (NULL for no new_id)
            1,                           // version
            0,                           // flags
            if (bufferPtr != 0L) MemorySegment.ofAddress(bufferPtr) else MemorySegment.NULL,
            dx,
            dy,
        )
    } catch (_: Throwable) { /* no-op if native unavailable */ }
}

/**
 * Calls wl_surface.damage(x, y, width, height) — opcode 2.
 *
 * Marks a region of the surface as damaged and needing a redraw.
 *
 * @see wlProxyMarshalFlagsFourInt
 */
internal fun wlSurfaceDamage(
    surfacePtr: Long,
    x: Int,
    y: Int,
    width: Int,
    height: Int,
) {
    val marshal = wlProxyMarshalFlagsFourInt ?: return
    try {
        marshal.invokeExact(
            MemorySegment.ofAddress(surfacePtr),
            2,                           // opcode: wl_surface.damage
            MemorySegment.NULL,          // wl_interface* (NULL)
            1,                           // version
            0,                           // flags
            x,
            y,
            width,
            height,
        )
    } catch (_: Throwable) { /* no-op if native unavailable */ }
}

// ── libc : poll ───────────────────────────────────────────────────────────────

/**
 * int poll(struct pollfd *fds, nfds_t nfds, int timeout);
 *
 * Waits for events on several file descriptors.
 *  - fds     : array of pollfd structures (fd, events, revents)
 *  - nfds    : number of entries in fds
 *  - timeout : timeout in milliseconds (-1 = infinite wait, 0 = immediate return)
 * Returns the number of ready descriptors, 0 on timeout, -1 on error.
 */
internal val nativePoll: MethodHandle? by lazy {
    libC.downcall("poll", FunctionDescriptor.of(
        ValueLayout.JAVA_INT,
        ValueLayout.ADDRESS,   // struct pollfd *fds
        ValueLayout.JAVA_INT,  // nfds_t nfds
        ValueLayout.JAVA_INT,  // int timeout
    ))
}

// ── libc : eventfd ────────────────────────────────────────────────────────────

/**
 * int eventfd(unsigned int initval, int flags);
 *
 * Creates an event notification file descriptor (Linux).
 * In counter mode (flags=0), write() adds to the value, read() reads and resets to 0.
 * Used to wake up the event loop from another thread.
 * Returns the file descriptor, or -1 on error.
 */
internal val nativeEventfd: MethodHandle? by lazy {
    libC.downcall("eventfd", FunctionDescriptor.of(
        ValueLayout.JAVA_INT,
        ValueLayout.JAVA_INT,  // unsigned int initval
        ValueLayout.JAVA_INT,  // int flags
    ))
}

// ── libc : read ───────────────────────────────────────────────────────────────

/**
 * ssize_t read(int fd, void *buf, size_t count);
 *
 * Reads up to count bytes from fd into buf.
 * For an eventfd in counter mode, reads 8 bytes (uint64_t) and resets the counter to 0.
 * Returns the number of bytes read, or -1 on error.
 */
internal val nativeRead: MethodHandle? by lazy {
    libC.downcall("read", FunctionDescriptor.of(
        ValueLayout.JAVA_LONG,
        ValueLayout.JAVA_INT,  // int fd
        ValueLayout.ADDRESS,   // void *buf
        ValueLayout.JAVA_LONG, // size_t count
    ))
}

// ── libc : write ──────────────────────────────────────────────────────────────

/**
 * ssize_t write(int fd, const void *buf, size_t count);
 *
 * Writes count bytes from buf into fd.
 * For an eventfd in counter mode, writes 8 bytes (uint64_t) to increment the counter.
 * Returns the number of bytes written, or -1 on error.
 */
internal val nativeWrite: MethodHandle? by lazy {
    libC.downcall("write", FunctionDescriptor.of(
        ValueLayout.JAVA_LONG,
        ValueLayout.JAVA_INT,  // int fd
        ValueLayout.ADDRESS,   // const void *buf
        ValueLayout.JAVA_LONG, // size_t count
    ))
}

// ── libc : close ──────────────────────────────────────────────────────────────

/**
 * int close(int fd);
 *
 * Closes a file descriptor and frees the associated resources.
 * Returns 0 on success, -1 on error.
 */
internal val nativeClose: MethodHandle? by lazy {
    libC.downcall("close", FunctionDescriptor.of(
        ValueLayout.JAVA_INT,
        ValueLayout.JAVA_INT,  // int fd
    ))
}

// ── libc : memfd_create ───────────────────────────────────────────────────────

/**
 * int memfd_create(const char *name, unsigned int flags);
 *
 * Creates an anonymous file descriptor for shared memory buffers (Wayland cursors).
 * Returns fd on success, -1 on error. Falls back to shm_open if unavailable.
 */
internal val nativeMemfdCreate: MethodHandle? by lazy {
    libC.downcall("memfd_create", FunctionDescriptor.of(
        ValueLayout.JAVA_INT,
        ValueLayout.ADDRESS,   // const char *name
        ValueLayout.JAVA_INT,  // unsigned int flags
    ))
}

// ── libc : ftruncate ──────────────────────────────────────────────────────────

/**
 * int ftruncate(int fd, off_t length);
 *
 * Sets the size of the file referenced by fd to length bytes.
 * Returns 0 on success, -1 on error.
 */
internal val nativeFtruncate: MethodHandle? by lazy {
    libC.downcall("ftruncate", FunctionDescriptor.of(
        ValueLayout.JAVA_INT,
        ValueLayout.JAVA_INT,  // int fd
        ValueLayout.JAVA_LONG, // off_t length
    ))
}

// ── libc : mmap ───────────────────────────────────────────────────────────────

/**
 * void *mmap(void *addr, size_t length, int prot, int flags, int fd, off_t offset);
 *
 * Maps the file descriptor fd into the process address space.
 * Returns a pointer to the mapped area on success, MAP_FAILED ((void*)-1) on error.
 */
internal val nativeMmap: MethodHandle? by lazy {
    libC.downcall("mmap", FunctionDescriptor.of(
        ValueLayout.ADDRESS,
        ValueLayout.ADDRESS,   // void *addr
        ValueLayout.JAVA_LONG, // size_t length
        ValueLayout.JAVA_INT,  // int prot
        ValueLayout.JAVA_INT,  // int flags
        ValueLayout.JAVA_INT,  // int fd
        ValueLayout.JAVA_LONG, // off_t offset
    ))
}

// ── libc : munmap ─────────────────────────────────────────────────────────────

/**
 * int munmap(void *addr, size_t length);
 *
 * Unmaps the memory region previously mapped by mmap.
 * Returns 0 on success, -1 on error.
 */
internal val nativeMunmap: MethodHandle? by lazy {
    libC.downcall("munmap", FunctionDescriptor.of(
        ValueLayout.JAVA_INT,
        ValueLayout.ADDRESS,   // void *addr
        ValueLayout.JAVA_LONG, // size_t length
    ))
}

// ── libxkbcommon : context, keymap, state, compose ─────────────────────────────

/**
 * struct xkb_context *xkb_context_new(enum xkb_context_flags flags);
 *
 * Creates a new xkb_context. Pass 0 for default flags.
 */
internal val xkbContextNew: MethodHandle? by lazy {
    libXkbCommon.downcall("xkb_context_new",
        FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.JAVA_INT))
}

/**
 * struct xkb_keymap *xkb_keymap_new_from_string(struct xkb_context *ctx,
 *     const char *keymap, enum xkb_keymap_format format, enum xkb_keymap_compile_flags flags);
 *
 * Creates a keymap from a keymap string (as received from wl_keyboard.keymap).
 * format = 0 (XKB_KEYMAP_FORMAT_TEXT_V1), flags = 0.
 */
internal val xkbKeymapNewFromString: MethodHandle? by lazy {
    libXkbCommon.downcall("xkb_keymap_new_from_string",
        FunctionDescriptor.of(ValueLayout.ADDRESS,
            ValueLayout.ADDRESS,   // context
            ValueLayout.ADDRESS,   // string
            ValueLayout.JAVA_INT,  // format
            ValueLayout.JAVA_INT,  // flags
        ))
}

/**
 * struct xkb_state *xkb_state_new(struct xkb_keymap *keymap);
 *
 * Creates a mutable keyboard state from a keymap.
 */
internal val xkbStateNew: MethodHandle? by lazy {
    libXkbCommon.downcall("xkb_state_new",
        FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS))
}

/**
 * void xkb_keymap_unref(struct xkb_keymap *keymap);
 *
 * Decrements the keymap refcount and frees it when it reaches zero.
 */
internal val xkbKeymapUnref: MethodHandle? by lazy {
    libXkbCommon.downcall("xkb_keymap_unref",
        FunctionDescriptor.ofVoid(ValueLayout.ADDRESS))
}

/**
 * void xkb_state_unref(struct xkb_state *state);
 *
 * Decrements the state refcount and frees it when it reaches zero.
 */
internal val xkbStateUnref: MethodHandle? by lazy {
    libXkbCommon.downcall("xkb_state_unref",
        FunctionDescriptor.ofVoid(ValueLayout.ADDRESS))
}

/**
 * struct xkb_compose_table *xkb_compose_table_new_from_locale(
 *     struct xkb_context *ctx, const char *locale, enum xkb_compose_compile_flags flags);
 *
 * Loads the Compose table for the given locale.
 */
internal val xkbComposeTableNewFromLocale: MethodHandle? by lazy {
    libXkbCommon.downcall("xkb_compose_table_new_from_locale",
        FunctionDescriptor.of(ValueLayout.ADDRESS,
            ValueLayout.ADDRESS,   // context
            ValueLayout.ADDRESS,   // locale
            ValueLayout.JAVA_INT,  // flags
        ))
}

/**
 * struct xkb_compose_state *xkb_compose_state_new(struct xkb_compose_table *table,
 *     enum xkb_compose_state_flags flags);
 *
 * Creates a compose state object for processing compose sequences.
 */
internal val xkbComposeStateNew: MethodHandle? by lazy {
    libXkbCommon.downcall("xkb_compose_state_new",
        FunctionDescriptor.of(ValueLayout.ADDRESS,
            ValueLayout.ADDRESS,   // table
            ValueLayout.JAVA_INT,  // flags
        ))
}

/**
 * void xkb_compose_state_reset(struct xkb_compose_state *state);
 *
 * Resets the compose state (clears any in-progress compose sequence).
 */
internal val xkbComposeStateReset: MethodHandle? by lazy {
    libXkbCommon.downcall("xkb_compose_state_reset",
        FunctionDescriptor.ofVoid(ValueLayout.ADDRESS))
}

/**
 * void xkb_compose_state_unref(struct xkb_compose_state *state);
 *
 * Decrements the compose state refcount and frees it when it reaches zero.
 */
internal val xkbComposeStateUnref: MethodHandle? by lazy {
    libXkbCommon.downcall("xkb_compose_state_unref",
        FunctionDescriptor.ofVoid(ValueLayout.ADDRESS))
}

/**
 * void xkb_compose_table_unref(struct xkb_compose_table *table);
 *
 * Decrements the compose table refcount and frees it when it reaches zero.
 */
internal val xkbComposeTableUnref: MethodHandle? by lazy {
    libXkbCommon.downcall("xkb_compose_table_unref",
        FunctionDescriptor.ofVoid(ValueLayout.ADDRESS))
}

/**
 * void xkb_context_unref(struct xkb_context *ctx);
 *
 * Decrements the context refcount and frees it when it reaches zero.
 */
internal val xkbContextUnref: MethodHandle? by lazy {
    libXkbCommon.downcall("xkb_context_unref",
        FunctionDescriptor.ofVoid(ValueLayout.ADDRESS))
}

// ── libc : shm_open ───────────────────────────────────────────────────────────

/**
 * int shm_open(const char *name, int oflag, mode_t mode);
 *
 * Creates/opens a POSIX shared memory object.
 * Returns fd on success, -1 on error. Used as fallback when memfd_create is unavailable.
 */
internal val nativeShmOpen: MethodHandle? by lazy {
    libC.downcall("shm_open", FunctionDescriptor.of(
        ValueLayout.JAVA_INT,
        ValueLayout.ADDRESS,   // const char *name
        ValueLayout.JAVA_INT,  // int oflag
        ValueLayout.JAVA_INT,  // mode_t mode
    ))
}

// ── libc : shm_unlink ─────────────────────────────────────────────────────────

/**
 * int shm_unlink(const char *name);
 *
 * Removes a POSIX shared memory object. The memory is freed when all fds are closed.
 * Returns 0 on success, -1 on error.
 */
internal val nativeShmUnlink: MethodHandle? by lazy {
    libC.downcall("shm_unlink", FunctionDescriptor.of(
        ValueLayout.JAVA_INT,
        ValueLayout.ADDRESS,   // const char *name
    ))
}

// ── mmap constants ────────────────────────────────────────────────────────────

internal const val PROT_READ: Int = 1
internal const val PROT_WRITE: Int = 2
internal const val MAP_SHARED: Int = 1
internal const val MAP_FAILED_PTR: Long = -1L  // (void*)-1 cast to Long

// ── shm_open constants ────────────────────────────────────────────────────────

internal const val O_RDWR: Int = 2
internal const val O_CREAT: Int = 64
internal const val O_EXCL: Int = 128

// ── Input device interfaces (exported by libwayland-client.so.0) ─────────────

/** &wl_seat_interface — required by bind(wl_seat). */
internal val wlSeatInterface: MemorySegment? by lazy { libWaylandClient.symbol("wl_seat_interface") }

/** &wl_keyboard_interface — required by wl_seat_get_keyboard (new_id). */
internal val wlKeyboardInterface: MemorySegment? by lazy { libWaylandClient.symbol("wl_keyboard_interface") }

/** &wl_pointer_interface — required by wl_seat_get_pointer (new_id). */
internal val wlPointerInterface: MemorySegment? by lazy { libWaylandClient.symbol("wl_pointer_interface") }

/** &wl_touch_interface — required by wl_seat_get_touch (new_id). */
internal val wlTouchInterface: MemorySegment? by lazy { libWaylandClient.symbol("wl_touch_interface") }

/** &wl_output_interface — required by bind(wl_output). */
internal val wlOutputInterface: MemorySegment? by lazy { libWaylandClient.symbol("wl_output_interface") }

/** &wl_shm_interface — required by bind(wl_shm) for cursor buffer pool creation. */
internal val wlShmInterface: MemorySegment? by lazy { libWaylandClient.symbol("wl_shm_interface") }

/** &wl_shm_pool_interface — required by wl_shm.create_pool (new_id) and wl_shm_pool.destroy. */
internal val wlShmPoolInterface: MemorySegment? by lazy { libWaylandClient.symbol("wl_shm_pool_interface") }

/** &wl_buffer_interface — required by wl_shm_pool.create_buffer (new_id) and wl_buffer.destroy. */
internal val wlBufferInterface: MemorySegment? by lazy { libWaylandClient.symbol("wl_buffer_interface") }

// ── wl_seat_get_pointer / wl_seat_get_keyboard / wl_seat_get_touch ────────────

/**
 * wl_proxy_marshal_flags for wl_seat.get_keyboard (opcode 1).
 *
 * Signature: wl_keyboard* wl_proxy_marshal_flags(seat, 1, &wl_keyboard_interface, version, flags, NULL).
 */
internal val wlSeatGetKeyboard: MethodHandle? by lazy {
    libWaylandClient.downcall("wl_proxy_marshal_flags",
        FunctionDescriptor.of(ValueLayout.ADDRESS,
            ValueLayout.ADDRESS,   // wl_proxy* (seat)
            ValueLayout.JAVA_INT,  // opcode = 1
            ValueLayout.ADDRESS,   // wl_interface* (&wl_keyboard_interface)
            ValueLayout.JAVA_INT,  // version
            ValueLayout.JAVA_INT,  // flags
            ValueLayout.ADDRESS,   // new_id placeholder (NULL)
        ))
}

/**
 * wl_proxy_marshal_flags for wl_seat.get_pointer (opcode 0).
 *
 * Signature: wl_pointer* wl_proxy_marshal_flags(seat, 0, &wl_pointer_interface, version, flags, NULL).
 */
internal val wlSeatGetPointer: MethodHandle? by lazy {
    libWaylandClient.downcall("wl_proxy_marshal_flags",
        FunctionDescriptor.of(ValueLayout.ADDRESS,
            ValueLayout.ADDRESS,   // wl_proxy* (seat)
            ValueLayout.JAVA_INT,  // opcode = 0
            ValueLayout.ADDRESS,   // wl_interface* (&wl_pointer_interface)
            ValueLayout.JAVA_INT,  // version
            ValueLayout.JAVA_INT,  // flags
            ValueLayout.ADDRESS,   // new_id placeholder (NULL)
        ))
}

/**
 * wl_proxy_marshal_flags for wl_seat.get_touch (opcode 2).
 *
 * Signature: wl_touch* wl_proxy_marshal_flags(seat, 2, &wl_touch_interface, version, flags, NULL).
 */
internal val wlSeatGetTouch: MethodHandle? by lazy {
    libWaylandClient.downcall("wl_proxy_marshal_flags",
        FunctionDescriptor.of(ValueLayout.ADDRESS,
            ValueLayout.ADDRESS,   // wl_proxy* (seat)
            ValueLayout.JAVA_INT,  // opcode = 2
            ValueLayout.ADDRESS,   // wl_interface* (&wl_touch_interface)
            ValueLayout.JAVA_INT,  // version
            ValueLayout.JAVA_INT,  // flags
            ValueLayout.ADDRESS,   // new_id placeholder (NULL)
        ))
}

// ── pollfd layout helpers ─────────────────────────────────────────────────────

/**
 * POLLIN value: the descriptor is ready for reading.
 * Set in pollfd.events to indicate read interest.
 */
internal const val POLLIN: Short = 1

/**
 * Allocates an array of 2 pollfd structures in the provided arena.
 *
 * Linux 64-bit layout of a struct pollfd:
 *  - offset 0 : fd      (int, 4 bytes)
 *  - offset 4 : events  (short, 2 bytes)
 *  - offset 6 : revents (short, 2 bytes)
 *  - size     : 8 bytes
 *
 * @return A 16-byte MemorySegment aligned on 4 bytes.
 */
internal fun allocPollFd(arena: java.lang.foreign.Arena): java.lang.foreign.MemorySegment =
    arena.allocate(8L * 2, 4L)

/**
 * Initializes a pollfd entry in the array.
 *
 * @param seg    pollfd array allocated by [allocPollFd].
 * @param idx    Index of the entry (0 or 1).
 * @param fd     File descriptor to watch.
 * @param events Event mask (e.g. [POLLIN]).
 */
internal fun setPollFd(seg: java.lang.foreign.MemorySegment, idx: Int, fd: Int, events: Short) {
    seg.set(ValueLayout.JAVA_INT, idx * 8L, fd)
    seg.set(ValueLayout.JAVA_SHORT, idx * 8L + 4, events)
}

/**
 * Reads the revents field of a pollfd entry.
 *
 * @param seg pollfd array.
 * @param idx Index of the entry (0 or 1).
 * @return revents mask set by the kernel after poll().
 */
internal fun getPollRevents(seg: java.lang.foreign.MemorySegment, idx: Int): Short =
    seg.get(ValueLayout.JAVA_SHORT, idx * 8L + 6)
