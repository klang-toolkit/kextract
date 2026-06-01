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

/**
 * Lookup of libwayland-client.so.0 — null on non-Wayland platforms.
 *
 * The try/catch on Throwable is intentional: SymbolLookup.libraryLookup
 * may throw IllegalArgumentException or UnsatisfiedLinkError on macOS/Windows,
 * and we want the build to stay green in all cases.
 */
internal val libWaylandClient: SymbolLookup? by lazy {
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

// ── wl_seat_get_keyboard / wl_seat_get_pointer / wl_seat_get_touch ────────────

/**
 * wl_proxy_marshal_flags for wl_seat.get_keyboard (opcode 0).
 *
 * Signature: wl_keyboard* wl_proxy_marshal_flags(seat, 0, &wl_keyboard_interface, version, flags, NULL).
 */
internal val wlSeatGetKeyboard: MethodHandle? by lazy {
    libWaylandClient.downcall("wl_proxy_marshal_flags",
        FunctionDescriptor.of(ValueLayout.ADDRESS,
            ValueLayout.ADDRESS,   // wl_proxy* (seat)
            ValueLayout.JAVA_INT,  // opcode = 0
            ValueLayout.ADDRESS,   // wl_interface* (&wl_keyboard_interface)
            ValueLayout.JAVA_INT,  // version
            ValueLayout.JAVA_INT,  // flags
            ValueLayout.ADDRESS,   // new_id placeholder (NULL)
        ))
}

/**
 * wl_proxy_marshal_flags for wl_seat.get_pointer (opcode 1).
 *
 * Signature: wl_pointer* wl_proxy_marshal_flags(seat, 1, &wl_pointer_interface, version, flags, NULL).
 */
internal val wlSeatGetPointer: MethodHandle? by lazy {
    libWaylandClient.downcall("wl_proxy_marshal_flags",
        FunctionDescriptor.of(ValueLayout.ADDRESS,
            ValueLayout.ADDRESS,   // wl_proxy* (seat)
            ValueLayout.JAVA_INT,  // opcode = 1
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
