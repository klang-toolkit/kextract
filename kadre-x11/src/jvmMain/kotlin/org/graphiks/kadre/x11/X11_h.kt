/**
 * FFM bindings for the X11 functions required for window management.
 *
 * Loads libX11.so.6 via SymbolLookup.libraryLookup with a tryCreate pattern
 * (try/catch Throwable) so the build passes on macOS/Windows.
 *
 * Exposed functions:
 *  - XOpenDisplay      — opens the connection to the X server
 *  - XCloseDisplay     — closes the connection to the X server
 *  - XCreateSimpleWindow — creates a simple window
 *  - XSelectInput      — selects the events to receive
 *  - XDestroyWindow    — destroys a window
 *  - XFlush            — flushes the command queue to the X server
 *  - XPending          — returns the number of pending events
 *  - XNextEvent        — reads the next event
 *  - XStoreName        — sets the window title
 *  - XInternAtom       — obtains an atom by name
 *  - XSetWMProtocols   — sets the WM protocols (e.g. WM_DELETE_WINDOW)
 *  - XMapWindow        — makes a window visible
 *  - XSendEvent        — sends a synthetic event (wakeUp ClientMessage)
 *
 * Reference: https://www.x.org/releases/current/doc/libX11/libX11/libX11.html
 */
package org.graphiks.kadre.x11

import java.lang.foreign.Arena
import java.lang.foreign.FunctionDescriptor
import java.lang.foreign.Linker
import java.lang.foreign.SymbolLookup
import java.lang.foreign.ValueLayout
import java.lang.invoke.MethodHandle

// ── Lazy loading of the library ───────────────────────────────────────────────

/**
 * Lookup of libX11.so.6 — null on non-Linux platforms (macOS, Windows).
 *
 * The try/catch on Throwable is intentional: SymbolLookup.libraryLookup
 * may throw IllegalArgumentException or UnsatisfiedLinkError on macOS/Windows,
 * and we want the build to stay green in all cases.
 */
internal val libX11: SymbolLookup? by lazy {
    try {
        SymbolLookup.libraryLookup("libX11.so.6", Arena.global())
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

// ── XOpenDisplay ──────────────────────────────────────────────────────────────

/**
 * Display *XOpenDisplay(char *display_name);
 *
 * Opens the connection to the X server. Pass NULL to use the DISPLAY
 * environment variable. Returns a Display* pointer (NULL on failure).
 */
internal val xOpenDisplay: MethodHandle? by lazy {
    libX11.downcall(
        "XOpenDisplay",
        FunctionDescriptor.of(
            ValueLayout.ADDRESS,    // Display* return
            ValueLayout.ADDRESS,    // char* display_name (or NULL)
        )
    )
}

// ── XCloseDisplay ─────────────────────────────────────────────────────────────

/**
 * int XCloseDisplay(Display *display);
 *
 * Closes the connection to the X server and frees the associated resources.
 */
internal val xCloseDisplay: MethodHandle? by lazy {
    libX11.downcall(
        "XCloseDisplay",
        FunctionDescriptor.of(
            ValueLayout.JAVA_INT,   // int return
            ValueLayout.ADDRESS,    // Display*
        )
    )
}

// ── XCreateSimpleWindow ───────────────────────────────────────────────────────

/**
 * Window XCreateSimpleWindow(
 *     Display *display,
 *     Window parent,
 *     int x, int y,
 *     unsigned int width, unsigned int height,
 *     unsigned int border_width,
 *     unsigned long border,
 *     unsigned long background
 * );
 *
 * Creates a simple child window. Window is an XID = unsigned long (64 bits).
 */
/**
 * Window XRootWindow(Display *display, int screen_number);
 *
 * Returns the XID of the screen's root window (equivalent to the DefaultRootWindow macro).
 * Essential as the parent of XCreateSimpleWindow: an incorrect hardcoded value
 * causes `BadWindow`.
 */
internal val xRootWindow: MethodHandle? by lazy {
    libX11.downcall(
        "XRootWindow",
        FunctionDescriptor.of(
            ValueLayout.JAVA_LONG,  // Window (XID)
            ValueLayout.ADDRESS,    // Display*
            ValueLayout.JAVA_INT,   // int screen_number
        )
    )
}

internal val xCreateSimpleWindow: MethodHandle? by lazy {
    libX11.downcall(
        "XCreateSimpleWindow",
        FunctionDescriptor.of(
            ValueLayout.JAVA_LONG,  // Window (XID = unsigned long)
            ValueLayout.ADDRESS,    // Display*
            ValueLayout.JAVA_LONG,  // Window parent (XID)
            ValueLayout.JAVA_INT,   // int x
            ValueLayout.JAVA_INT,   // int y
            ValueLayout.JAVA_INT,   // unsigned int width
            ValueLayout.JAVA_INT,   // unsigned int height
            ValueLayout.JAVA_INT,   // unsigned int border_width
            ValueLayout.JAVA_LONG,  // unsigned long border
            ValueLayout.JAVA_LONG,  // unsigned long background
        )
    )
}

// ── XSelectInput ──────────────────────────────────────────────────────────────

/**
 * int XSelectInput(Display *display, Window w, long event_mask);
 *
 * Selects the event types to receive for the given window.
 */
internal val xSelectInput: MethodHandle? by lazy {
    libX11.downcall(
        "XSelectInput",
        FunctionDescriptor.of(
            ValueLayout.JAVA_INT,   // int return
            ValueLayout.ADDRESS,    // Display*
            ValueLayout.JAVA_LONG,  // Window (XID)
            ValueLayout.JAVA_LONG,  // long event_mask
        )
    )
}

// ── XDestroyWindow ────────────────────────────────────────────────────────────

/**
 * int XDestroyWindow(Display *display, Window w);
 *
 * Destroys the window and all its subwindows.
 */
internal val xDestroyWindow: MethodHandle? by lazy {
    libX11.downcall(
        "XDestroyWindow",
        FunctionDescriptor.of(
            ValueLayout.JAVA_INT,   // int return
            ValueLayout.ADDRESS,    // Display*
            ValueLayout.JAVA_LONG,  // Window (XID)
        )
    )
}

// ── XFlush ────────────────────────────────────────────────────────────────────

/**
 * int XFlush(Display *display);
 *
 * Flushes the pending command queue to the X server.
 */
internal val xFlush: MethodHandle? by lazy {
    libX11.downcall(
        "XFlush",
        FunctionDescriptor.of(
            ValueLayout.JAVA_INT,   // int return
            ValueLayout.ADDRESS,    // Display*
        )
    )
}

// ── XPending ──────────────────────────────────────────────────────────────────

/**
 * int XPending(Display *display);
 *
 * Returns the number of pending events in the client-side queue.
 * Returns 0 if the queue is empty (without blocking).
 */
internal val xPending: MethodHandle? by lazy {
    libX11.downcall(
        "XPending",
        FunctionDescriptor.of(
            ValueLayout.JAVA_INT,   // int (number of events)
            ValueLayout.ADDRESS,    // Display*
        )
    )
}

// ── XNextEvent ────────────────────────────────────────────────────────────────

/**
 * int XNextEvent(Display *display, XEvent *event_return);
 *
 * Reads the next event from the queue, blocking if necessary.
 * The event is written into the MemorySegment pointed to by event_return.
 */
internal val xNextEvent: MethodHandle? by lazy {
    libX11.downcall(
        "XNextEvent",
        FunctionDescriptor.of(
            ValueLayout.JAVA_INT,   // int return
            ValueLayout.ADDRESS,    // Display*
            ValueLayout.ADDRESS,    // XEvent* event_return
        )
    )
}

// ── XStoreName ────────────────────────────────────────────────────────────────

/**
 * int XStoreName(Display *display, Window w, char *window_name);
 *
 * Sets the title (name) of the window in the window manager's title bar.
 * The string must be encoded in Latin-1 or UTF-8 depending on the WM.
 */
internal val xStoreName: MethodHandle? by lazy {
    libX11.downcall(
        "XStoreName",
        FunctionDescriptor.of(
            ValueLayout.JAVA_INT,   // int return
            ValueLayout.ADDRESS,    // Display*
            ValueLayout.JAVA_LONG,  // Window (XID)
            ValueLayout.ADDRESS,    // char* window_name
        )
    )
}

// ── XInternAtom ───────────────────────────────────────────────────────────────

/**
 * Atom XInternAtom(Display *display, char *atom_name, Bool only_if_exists);
 *
 * Obtains the identifier (Atom) of a property by its name.
 * Used in particular for WM_DELETE_WINDOW and WM_PROTOCOLS.
 * Atom = unsigned long (64 bits).
 */
internal val xInternAtom: MethodHandle? by lazy {
    libX11.downcall(
        "XInternAtom",
        FunctionDescriptor.of(
            ValueLayout.JAVA_LONG,  // Atom (unsigned long)
            ValueLayout.ADDRESS,    // Display*
            ValueLayout.ADDRESS,    // char* atom_name
            ValueLayout.JAVA_INT,   // Bool only_if_exists
        )
    )
}

// ── XSetWMProtocols ───────────────────────────────────────────────────────────

/**
 * Status XSetWMProtocols(Display *display, Window w, Atom *protocols, int count);
 *
 * Sets the window's WM_PROTOCOLS property. Used to ask the window manager
 * to send a ClientMessage instead of destroying the window directly
 * (WM_DELETE_WINDOW).
 */
internal val xSetWMProtocols: MethodHandle? by lazy {
    libX11.downcall(
        "XSetWMProtocols",
        FunctionDescriptor.of(
            ValueLayout.JAVA_INT,   // Status
            ValueLayout.ADDRESS,    // Display*
            ValueLayout.JAVA_LONG,  // Window (XID)
            ValueLayout.ADDRESS,    // Atom* protocols
            ValueLayout.JAVA_INT,   // int count
        )
    )
}

// ── XMapWindow ────────────────────────────────────────────────────────────────

/**
 * int XMapWindow(Display *display, Window w);
 *
 * Makes the window visible on screen. The window must first have been created
 * with XCreateSimpleWindow or XCreateWindow.
 */
internal val xMapWindow: MethodHandle? by lazy {
    libX11.downcall(
        "XMapWindow",
        FunctionDescriptor.of(
            ValueLayout.JAVA_INT,   // int return
            ValueLayout.ADDRESS,    // Display*
            ValueLayout.JAVA_LONG,  // Window (XID)
        )
    )
}

// ── XSendEvent ────────────────────────────────────────────────────────────────

/**
 * Status XSendEvent(
 *     Display *display,
 *     Window w,
 *     Bool propagate,
 *     long event_mask,
 *     XEvent *event_send
 * );
 *
 * Sends a synthetic event to a window.
 * Used by X11EventLoopProxy.wakeUp() to unblock XNextEvent via
 * a ClientMessage sent to the main window.
 *
 * Returns Status (int): 0 on failure, non-zero on success.
 */
internal val xSendEvent: MethodHandle? by lazy {
    libX11.downcall(
        "XSendEvent",
        FunctionDescriptor.of(
            ValueLayout.JAVA_INT,   // Status (int)
            ValueLayout.ADDRESS,    // Display*
            ValueLayout.JAVA_LONG,  // Window w (XID)
            ValueLayout.JAVA_INT,   // Bool propagate
            ValueLayout.JAVA_LONG,  // long event_mask
            ValueLayout.ADDRESS,    // XEvent* event_send
        )
    )
}

// ── XResizeWindow ─────────────────────────────────────────────────────────────

/**
 * int XResizeWindow(Display *display, Window w, unsigned int width, unsigned int height);
 *
 * Resizes the window. Does not move it.
 */
internal val xResizeWindow: MethodHandle? by lazy {
    libX11.downcall(
        "XResizeWindow",
        FunctionDescriptor.of(
            ValueLayout.JAVA_INT,   // int return
            ValueLayout.ADDRESS,    // Display*
            ValueLayout.JAVA_LONG,  // Window (XID)
            ValueLayout.JAVA_INT,   // unsigned int width
            ValueLayout.JAVA_INT,   // unsigned int height
        )
    )
}

// ── XMoveWindow ───────────────────────────────────────────────────────────────

/**
 * int XMoveWindow(Display *display, Window w, int x, int y);
 *
 * Moves the window to the given position (relative to the parent window).
 * For a top-level window this is the screen position.
 */
internal val xMoveWindow: MethodHandle? by lazy {
    libX11.downcall(
        "XMoveWindow",
        FunctionDescriptor.of(
            ValueLayout.JAVA_INT,   // int return
            ValueLayout.ADDRESS,    // Display*
            ValueLayout.JAVA_LONG,  // Window (XID)
            ValueLayout.JAVA_INT,   // int x
            ValueLayout.JAVA_INT,   // int y
        )
    )
}

// ── XIconifyWindow ────────────────────────────────────────────────────────────

/**
 * Status XIconifyWindow(Display *display, Window w, int screen_number);
 *
 * Asks the window manager to iconify (minimize) the window.
 */
internal val xIconifyWindow: MethodHandle? by lazy {
    libX11.downcall(
        "XIconifyWindow",
        FunctionDescriptor.of(
            ValueLayout.JAVA_INT,   // Status
            ValueLayout.ADDRESS,    // Display*
            ValueLayout.JAVA_LONG,  // Window (XID)
            ValueLayout.JAVA_INT,   // int screen_number
        )
    )
}

// ── XChangeProperty ───────────────────────────────────────────────────────────

/**
 * int XChangeProperty(Display *display, Window w, Atom property, Atom type,
 *     int format, int mode, const unsigned char *data, int nelements);
 *
 * Changes the value of a property on a window. Used to set _NET_WM_STATE,
 * _MOTIF_WM_HINTS, etc.
 */
internal val xChangeProperty: MethodHandle? by lazy {
    libX11.downcall(
        "XChangeProperty",
        FunctionDescriptor.of(
            ValueLayout.JAVA_INT,   // int return
            ValueLayout.ADDRESS,    // Display*
            ValueLayout.JAVA_LONG,  // Window (XID)
            ValueLayout.JAVA_LONG,  // Atom property
            ValueLayout.JAVA_LONG,  // Atom type
            ValueLayout.JAVA_INT,   // int format (8, 16, or 32)
            ValueLayout.JAVA_INT,   // int mode (PropModeReplace=0)
            ValueLayout.ADDRESS,    // const unsigned char* data
            ValueLayout.JAVA_INT,   // int nelements
        )
    )
}

// ── XGetGeometry ──────────────────────────────────────────────────────────────

/**
 * Status XGetGeometry(Display *display, Drawable d,
 *     Window *root_return, int *x_return, int *y_return,
 *     unsigned int *width_return, unsigned int *height_return,
 *     unsigned int *border_width_return, unsigned int *depth_return);
 *
 * Returns the geometry of the window in root-window coordinates.
 * Used to obtain the outer position.
 */
internal val xGetGeometry: MethodHandle? by lazy {
    libX11.downcall(
        "XGetGeometry",
        FunctionDescriptor.of(
            ValueLayout.JAVA_INT,   // Status
            ValueLayout.ADDRESS,    // Display*
            ValueLayout.JAVA_LONG,  // Drawable (XID)
            ValueLayout.ADDRESS,    // Window* root_return
            ValueLayout.ADDRESS,    // int* x_return
            ValueLayout.ADDRESS,    // int* y_return
            ValueLayout.ADDRESS,    // unsigned int* width_return
            ValueLayout.ADDRESS,    // unsigned int* height_return
            ValueLayout.ADDRESS,    // unsigned int* border_width_return
            ValueLayout.ADDRESS,    // unsigned int* depth_return
        )
    )
}

// ── XUnmapWindow ──────────────────────────────────────────────────────────────

/**
 * int XUnmapWindow(Display *display, Window w);
 *
 * Unmaps the window (hides it from screen).
 */
internal val xUnmapWindow: MethodHandle? by lazy {
    libX11.downcall(
        "XUnmapWindow",
        FunctionDescriptor.of(
            ValueLayout.JAVA_INT,   // int return
            ValueLayout.ADDRESS,    // Display*
            ValueLayout.JAVA_LONG,  // Window (XID)
        )
    )
}

// ── XkbSetDetectableAutoRepeat ────────────────────────────────────────────────

/**
 * Bool XkbSetDetectableAutoRepeat(Display *display, Bool detectable, Bool *supported_rtrn);
 *
 * Enables "detectable auto-repeat" mode, allowing real automatic repeats
 * to be distinguished from genuine key presses and releases.
 */
internal val xkbSetDetectableAutoRepeat: MethodHandle? by lazy {
    libX11.downcall(
        "XkbSetDetectableAutoRepeat",
        FunctionDescriptor.of(
            ValueLayout.JAVA_INT,   // Bool return
            ValueLayout.ADDRESS,    // Display*
            ValueLayout.JAVA_INT,   // Bool detectable
            ValueLayout.ADDRESS,    // Bool* supported_rtrn (may be NULL)
        )
    )
}

// ── XResourceManagerString ────────────────────────────────────────────────────

/**
 * char *XResourceManagerString(Display *display);
 *
 * Returns the X11 resource database string (RESOURCE_MANAGER).
 * Used to read DPI preferences (Xft.dpi), the font, etc.
 */
internal val xResourceManagerString: MethodHandle? by lazy {
    libX11.downcall(
        "XResourceManagerString",
        FunctionDescriptor.of(
            ValueLayout.ADDRESS,    // char* return
            ValueLayout.ADDRESS,    // Display*
        )
    )
}
