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
 *  - XQueryKeymap      — snapshots currently pressed keys
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

internal val libXext: SymbolLookup? by lazy {
    try {
        SymbolLookup.libraryLookup("libXext.so.6", Arena.global())
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

// ── XQueryKeymap ─────────────────────────────────────────────────────────────

/**
 * int XQueryKeymap(Display *display, char keys_return[32]);
 *
 * Writes a 256-bit snapshot of currently pressed keycodes into keys_return.
 */
internal val xQueryKeymap: MethodHandle? by lazy {
    libX11.downcall(
        "XQueryKeymap",
        FunctionDescriptor.of(
            ValueLayout.JAVA_INT,   // int return
            ValueLayout.ADDRESS,    // Display*
            ValueLayout.ADDRESS,    // char[32] keys_return
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

/**
 * int XRaiseWindow(Display *display, Window w);
 */
internal val xRaiseWindow: MethodHandle? by lazy {
    libX11.downcall(
        "XRaiseWindow",
        FunctionDescriptor.of(
            ValueLayout.JAVA_INT,
            ValueLayout.ADDRESS,
            ValueLayout.JAVA_LONG,
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

// ── XGetWindowProperty / XFree ───────────────────────────────────────────────

/**
 * int XGetWindowProperty(Display *display, Window w, Atom property,
 *     long long_offset, long long_length, Bool delete, Atom req_type,
 *     Atom *actual_type_return, int *actual_format_return,
 *     unsigned long *nitems_return, unsigned long *bytes_after_return,
 *     unsigned char **prop_return);
 */
internal val xGetWindowProperty: MethodHandle? by lazy {
    libX11.downcall(
        "XGetWindowProperty",
        FunctionDescriptor.of(
            ValueLayout.JAVA_INT,
            ValueLayout.ADDRESS,
            ValueLayout.JAVA_LONG,
            ValueLayout.JAVA_LONG,
            ValueLayout.JAVA_LONG,
            ValueLayout.JAVA_LONG,
            ValueLayout.JAVA_INT,
            ValueLayout.JAVA_LONG,
            ValueLayout.ADDRESS,
            ValueLayout.ADDRESS,
            ValueLayout.ADDRESS,
            ValueLayout.ADDRESS,
            ValueLayout.ADDRESS,
        )
    )
}

/**
 * int XFree(void *data);
 */
internal val xFree: MethodHandle? by lazy {
    libX11.downcall(
        "XFree",
        FunctionDescriptor.of(
            ValueLayout.JAVA_INT,
            ValueLayout.ADDRESS,
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

// ── XTranslateCoordinates ────────────────────────────────────────────────────

/**
 * Bool XTranslateCoordinates(Display *display, Window src_w, Window dest_w,
 *     int src_x, int src_y, int *dest_x_return, int *dest_y_return,
 *     Window *child_return);
 */
internal val xTranslateCoordinates: MethodHandle? by lazy {
    libX11.downcall(
        "XTranslateCoordinates",
        FunctionDescriptor.of(
            ValueLayout.JAVA_INT,
            ValueLayout.ADDRESS,
            ValueLayout.JAVA_LONG,
            ValueLayout.JAVA_LONG,
            ValueLayout.JAVA_INT,
            ValueLayout.JAVA_INT,
            ValueLayout.ADDRESS,
            ValueLayout.ADDRESS,
            ValueLayout.ADDRESS,
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

// ── R3: cursor, grab, warp ────────────────────────────────────────────────────

/**
 * Cursor XCreateFontCursor(Display* display, unsigned int shape);
 *
 * Creates a cursor from the standard X11 cursor font.
 * Risk FFM: shape argument is unsigned int (32-bit); passed as JAVA_INT.
 */
internal val xCreateFontCursor: MethodHandle? by lazy {
    libX11.downcall(
        "XCreateFontCursor",
        FunctionDescriptor.of(
            ValueLayout.JAVA_LONG,  // Cursor (XID = unsigned long)
            ValueLayout.ADDRESS,    // Display*
            ValueLayout.JAVA_INT,   // unsigned int shape
        )
    )
}

/**
 * int XDefineCursor(Display* display, Window w, Cursor cursor);
 */
internal val xDefineCursor: MethodHandle? by lazy {
    libX11.downcall(
        "XDefineCursor",
        FunctionDescriptor.of(
            ValueLayout.JAVA_INT,   // int
            ValueLayout.ADDRESS,    // Display*
            ValueLayout.JAVA_LONG,  // Window (XID)
            ValueLayout.JAVA_LONG,  // Cursor (XID)
        )
    )
}

/**
 * int XUndefineCursor(Display* display, Window w);
 */
internal val xUndefineCursor: MethodHandle? by lazy {
    libX11.downcall(
        "XUndefineCursor",
        FunctionDescriptor.of(
            ValueLayout.JAVA_INT,   // int
            ValueLayout.ADDRESS,    // Display*
            ValueLayout.JAVA_LONG,  // Window
        )
    )
}

/**
 * int XFreeCursor(Display* display, Cursor cursor);
 */
internal val xFreeCursor: MethodHandle? by lazy {
    libX11.downcall(
        "XFreeCursor",
        FunctionDescriptor.of(
            ValueLayout.JAVA_INT,
            ValueLayout.ADDRESS,
            ValueLayout.JAVA_LONG,
        )
    )
}

/**
 * Pixmap XCreateBitmapFromData(Display* display, Drawable d, const char* data,
 *     unsigned int width, unsigned int height);
 */
internal val xCreateBitmapFromData: MethodHandle? by lazy {
    libX11.downcall(
        "XCreateBitmapFromData",
        FunctionDescriptor.of(
            ValueLayout.JAVA_LONG,  // Pixmap
            ValueLayout.ADDRESS,    // Display*
            ValueLayout.JAVA_LONG,  // Drawable
            ValueLayout.ADDRESS,    // const char*
            ValueLayout.JAVA_INT,   // unsigned int width
            ValueLayout.JAVA_INT,   // unsigned int height
        )
    )
}

/**
 * Cursor XCreatePixmapCursor(Display* display, Pixmap source, Pixmap mask,
 *     XColor* foreground_color, XColor* background_color,
 *     unsigned int x, unsigned int y);
 */
internal val xCreatePixmapCursor: MethodHandle? by lazy {
    libX11.downcall(
        "XCreatePixmapCursor",
        FunctionDescriptor.of(
            ValueLayout.JAVA_LONG,  // Cursor
            ValueLayout.ADDRESS,    // Display*
            ValueLayout.JAVA_LONG,  // Pixmap source
            ValueLayout.JAVA_LONG,  // Pixmap mask
            ValueLayout.ADDRESS,    // XColor* foreground
            ValueLayout.ADDRESS,    // XColor* background
            ValueLayout.JAVA_INT,   // unsigned int x
            ValueLayout.JAVA_INT,   // unsigned int y
        )
    )
}

/**
 * int XFreePixmap(Display* display, Pixmap pixmap);
 */
internal val xFreePixmap: MethodHandle? by lazy {
    libX11.downcall(
        "XFreePixmap",
        FunctionDescriptor.of(
            ValueLayout.JAVA_INT,
            ValueLayout.ADDRESS,
            ValueLayout.JAVA_LONG,
        )
    )
}

/**
 * int XGrabPointer(Display*, Window, Bool, unsigned int, int, int, Window, Cursor, Time);
 *
 * Grabs the pointer (mouse). Returns GrabSuccess (0) on success.
 * Risk FFM: multiple integer types; we pass as JAVA_INT / JAVA_LONG.
 */
internal val xGrabPointer: MethodHandle? by lazy {
    libX11.downcall(
        "XGrabPointer",
        FunctionDescriptor.of(
            ValueLayout.JAVA_INT,   // int (GrabSuccess/etc)
            ValueLayout.ADDRESS,    // Display*
            ValueLayout.JAVA_LONG,  // Window grab_window
            ValueLayout.JAVA_INT,   // Bool owner_events
            ValueLayout.JAVA_INT,   // unsigned int event_mask
            ValueLayout.JAVA_INT,   // int pointer_mode (GrabModeAsync=1)
            ValueLayout.JAVA_INT,   // int keyboard_mode
            ValueLayout.JAVA_LONG,  // Window confine_to (or None=0)
            ValueLayout.JAVA_LONG,  // Cursor cursor (or None=0)
            ValueLayout.JAVA_LONG,  // Time (CurrentTime=0)
        )
    )
}

/**
 * int XUngrabPointer(Display*, Time);
 */
internal val xUngrabPointer: MethodHandle? by lazy {
    libX11.downcall(
        "XUngrabPointer",
        FunctionDescriptor.of(
            ValueLayout.JAVA_INT,   // int
            ValueLayout.ADDRESS,    // Display*
            ValueLayout.JAVA_LONG,  // Time (CurrentTime=0)
        )
    )
}

/**
 * Bool XQueryPointer(Display* display, Window w,
 *                    Window* root_return, Window* child_return,
 *                    int* root_x_return, int* root_y_return,
 *                    int* win_x_return, int* win_y_return,
 *                    unsigned int* mask_return);
 */
internal val xQueryPointer: MethodHandle? by lazy {
    libX11.downcall(
        "XQueryPointer",
        FunctionDescriptor.of(
            ValueLayout.JAVA_INT,   // Bool
            ValueLayout.ADDRESS,    // Display*
            ValueLayout.JAVA_LONG,  // Window
            ValueLayout.ADDRESS,    // Window* root_return
            ValueLayout.ADDRESS,    // Window* child_return
            ValueLayout.ADDRESS,    // int* root_x_return
            ValueLayout.ADDRESS,    // int* root_y_return
            ValueLayout.ADDRESS,    // int* win_x_return
            ValueLayout.ADDRESS,    // int* win_y_return
            ValueLayout.ADDRESS,    // unsigned int* mask_return
        )
    )
}

/**
 * int XWarpPointer(Display*, Window src_w, Window dest_w, int src_x, int src_y,
 *                  unsigned int src_width, unsigned int src_height, int dest_x, int dest_y);
 */
internal val xWarpPointer: MethodHandle? by lazy {
    libX11.downcall(
        "XWarpPointer",
        FunctionDescriptor.of(
            ValueLayout.JAVA_INT,   // int
            ValueLayout.ADDRESS,    // Display*
            ValueLayout.JAVA_LONG,  // Window src_w (None=0)
            ValueLayout.JAVA_LONG,  // Window dest_w
            ValueLayout.JAVA_INT,   // int src_x
            ValueLayout.JAVA_INT,   // int src_y
            ValueLayout.JAVA_INT,   // unsigned src_width
            ValueLayout.JAVA_INT,   // unsigned src_height
            ValueLayout.JAVA_INT,   // int dest_x
            ValueLayout.JAVA_INT,   // int dest_y
        )
    )
}

/**
 * XWMHints *XGetWMHints(Display *display, Window w);
 */
internal val xGetWMHints: MethodHandle? by lazy {
    libX11.downcall(
        "XGetWMHints",
        FunctionDescriptor.of(
            ValueLayout.ADDRESS,    // XWMHints*
            ValueLayout.ADDRESS,    // Display*
            ValueLayout.JAVA_LONG,  // Window
        )
    )
}

/**
 * XWMHints *XAllocWMHints(void);
 */
internal val xAllocWMHints: MethodHandle? by lazy {
    libX11.downcall(
        "XAllocWMHints",
        FunctionDescriptor.of(ValueLayout.ADDRESS)
    )
}

/**
 * void XSetWMHints(Display *display, Window w, XWMHints *wm_hints);
 */
internal val xSetWMHints: MethodHandle? by lazy {
    libX11.downcall(
        "XSetWMHints",
        FunctionDescriptor.ofVoid(
            ValueLayout.ADDRESS,    // Display*
            ValueLayout.JAVA_LONG,  // Window
            ValueLayout.ADDRESS,    // XWMHints*
        )
    )
}

/**
 * void XShapeCombineRectangles(Display*, Window dest, int dest_kind, int x_off, int y_off,
 *                              XRectangle* rectangles, int n_rects, int op, int ordering);
 */
internal val xShapeCombineRectangles: MethodHandle? by lazy {
    libXext.downcall(
        "XShapeCombineRectangles",
        FunctionDescriptor.ofVoid(
            ValueLayout.ADDRESS,    // Display*
            ValueLayout.JAVA_LONG,  // Window
            ValueLayout.JAVA_INT,   // dest_kind
            ValueLayout.JAVA_INT,   // x_off
            ValueLayout.JAVA_INT,   // y_off
            ValueLayout.ADDRESS,    // XRectangle*
            ValueLayout.JAVA_INT,   // n_rects
            ValueLayout.JAVA_INT,   // op
            ValueLayout.JAVA_INT,   // ordering
        )
    )
}

// ── XChangeWindowAttributes ────────────────────────────────────────────────────

/**
 * int XChangeWindowAttributes(Display *display, Window w, unsigned long valuemask, XSetWindowAttributes *attributes);
 *
 * Changes the attributes of a window. The valuemask indicates which attributes
 * in the XSetWindowAttributes structure are to be changed.
 */
internal val xChangeWindowAttributes: MethodHandle? by lazy {
    libX11.downcall(
        "XChangeWindowAttributes",
        FunctionDescriptor.of(
            ValueLayout.JAVA_INT,   // int return
            ValueLayout.ADDRESS,    // Display*
            ValueLayout.JAVA_LONG,  // Window (XID)
            ValueLayout.JAVA_LONG,  // unsigned long valuemask
            ValueLayout.ADDRESS,    // XSetWindowAttributes*
        )
    )
}

/**
 * int XChangeProperty(Display* display, Window w, Atom property, Atom type,
 *                     int format, int mode, const unsigned char* data, int nelements);
 *
 * Already declared above for _MOTIF_WM_HINTS; re-exported as an alias
 * for the _NET_WM_ICON implementation.
 * Note: xChangeProperty is already declared in the file above.
 */

// ── X11 cursor shape constants (cursorfont.h) ─────────────────────────────────

internal const val XC_left_ptr: Int          = 68   // default arrow
internal const val XC_hand2: Int             = 60   // pointer / hand
internal const val XC_xterm: Int             = 152  // text I-beam
internal const val XC_crosshair: Int         = 34   // crosshair
internal const val XC_fleur: Int             = 52   // move (all-direction)
internal const val XC_watch: Int             = 150  // wait / busy
internal const val XC_X_cursor: Int          = 0    // not-allowed (X shape)
internal const val XC_hand1: Int             = 58   // grab open
internal const val XC_top_side: Int          = 138  // resize north
internal const val XC_bottom_side: Int       = 16   // resize south
internal const val XC_right_side: Int        = 96   // resize east
internal const val XC_left_side: Int         = 70   // resize west
internal const val XC_top_right_corner: Int  = 136  // resize NE
internal const val XC_top_left_corner: Int   = 134  // resize NW
internal const val XC_bottom_right_corner: Int = 14 // resize SE
internal const val XC_bottom_left_corner: Int = 12  // resize SW
internal const val XC_question_arrow: Int     = 30   // help
internal const val XC_plus: Int              = 58   // cell / plus (same shape as hand1)
internal const val XC_sb_h_double_arrow: Int = 108  // EW resize
internal const val XC_sb_v_double_arrow: Int = 116  // NS resize

// ── XIM (X Input Method) constants (X11/Xlib.h, X11/XIM.h) ────────────────

// XIM styles
internal const val XIMPreeditArea: Int = 0x0001
internal const val XIMPreeditCallbacks: Int = 0x0004
internal const val XIMPreeditPosition: Int = 0x0008
internal const val XIMPreeditNothing: Int = 0x0010
internal const val XIMStatusNothing: Int = 0x0100

// XN* attribute names (used as C strings in XIM varargs)
internal val XNInputStyle: String = "inputStyle"
internal val XNClientWindow: String = "clientWindow"
internal val XNFocusWindow: String = "focusWindow"
internal val XNPreeditAttributes: String = "preeditAttributes"
internal val XNArea: String = "area"
internal val XNAreaNeeded: String = "areaNeeded"
internal val XNSpotLocation: String = "spotLocation"
internal val XNPreeditStartCallback: String = "preeditStartCallback"
internal val XNPreeditDrawCallback: String = "preeditDrawCallback"
internal val XNPreeditDoneCallback: String = "preeditDoneCallback"
internal val XNCommitStringCallback: String = "commitStringCallback"

// ── XIM struct offset constants (LP64: Linux x86-64 / aarch64) ────────────

// XIMCallback: XPointer client_data (8) + XIMProc callback (8) = 16 bytes
internal const val XIM_CALLBACK_CLIENT_DATA_OFFSET: Long = 0L
internal const val XIM_CALLBACK_PROC_OFFSET: Long = 8L
internal const val XIM_CALLBACK_SIZE: Long = 16L

// XRectangle: short x(2) + short y(2) + ushort width(2) + ushort height(2) = 8
internal const val XRECTANGLE_SIZE: Long = 8L
internal const val XRECTANGLE_ALIGN: Long = 2L

// XPoint: short x(2) + short y(2) = 4
internal const val XPOINT_SIZE: Long = 4L
internal const val XPOINT_ALIGN: Long = 2L

// XIMPreeditDrawCallbackStruct (LP64):
//   int caret(4) + int chg_first(4) + int chg_length(4) + pad(4) + XIMText*(8) = 24
internal const val PREDRAW_CARET_OFFSET: Long = 0L
internal const val PREDRAW_CHG_FIRST_OFFSET: Long = 4L
internal const val PREDRAW_CHG_LENGTH_OFFSET: Long = 8L
internal const val PREDRAW_TEXT_PTR_OFFSET: Long = 16L

// XIMText / XIMCommitStringCallbackStruct (LP64):
//   ushort length(2) + pad(6) + feedback*(8) + Bool encoding(4) + pad(4) + string*(8) = 32
internal const val XIMTEXT_LENGTH_OFFSET: Long = 0L
internal const val XIMTEXT_FEEDBACK_OFFSET: Long = 8L
internal const val XIMTEXT_ENCODING_IS_WCHAR_OFFSET: Long = 16L
internal const val XIMTEXT_STRING_PTR_OFFSET: Long = 24L

// XIMPreeditState: short count
internal const val PRESTATE_COUNT_OFFSET: Long = 0L

// XIMProc callback descriptor: void (*)(XIM, XPointer, XPointer)
internal val XIM_PROC_DESCRIPTOR: FunctionDescriptor = FunctionDescriptor.ofVoid(
    ValueLayout.ADDRESS,  // XIM
    ValueLayout.ADDRESS,  // XPointer client_data
    ValueLayout.ADDRESS,  // XPointer call_data
)

// ── XIM FFM bindings ──────────────────────────────────────────────────────

/**
 * XIM XOpenIM(Display* display, XrmDatabase db, char* res_name, char* res_class);
 */
internal val xOpenIM: MethodHandle? by lazy {
    libX11.downcall(
        "XOpenIM",
        FunctionDescriptor.of(
            ValueLayout.ADDRESS,   // XIM return
            ValueLayout.ADDRESS,   // Display*
            ValueLayout.ADDRESS,   // XrmDatabase (NULL)
            ValueLayout.ADDRESS,   // char* res_name (NULL)
            ValueLayout.ADDRESS,   // char* res_class (NULL)
        )
    )
}

/**
 * Status XCloseIM(XIM im);
 */
internal val xCloseIM: MethodHandle? by lazy {
    libX11.downcall(
        "XCloseIM",
        FunctionDescriptor.of(
            ValueLayout.JAVA_INT,  // Status
            ValueLayout.ADDRESS,   // XIM
        )
    )
}

/**
 * XIC XCreateIC(XIM im, ...) variadic.
 *
 * Uses firstVariadicArg(1) for correct variadic ABI.
 * All variadic args passed as ADDRESS (on LP64 pointers and longs
 * share the same register class for variadic calls).
 */
internal val xCreateIC: MethodHandle? by lazy {
    val lookup = libX11 ?: return@lazy null
    lookup.find("XCreateIC").map { symbol ->
        linker.downcallHandle(symbol,
            FunctionDescriptor.of(ValueLayout.ADDRESS,
                ValueLayout.ADDRESS,  // XIM
                ValueLayout.ADDRESS, ValueLayout.ADDRESS, // inputStyle, value
                ValueLayout.ADDRESS, ValueLayout.ADDRESS, // clientWindow, value
                ValueLayout.ADDRESS, ValueLayout.ADDRESS, // focusWindow, value
                ValueLayout.ADDRESS, ValueLayout.ADDRESS, // preeditStartCallback, value
                ValueLayout.ADDRESS, ValueLayout.ADDRESS, // preeditDrawCallback, value
                ValueLayout.ADDRESS, ValueLayout.ADDRESS, // preeditDoneCallback, value
                ValueLayout.ADDRESS, ValueLayout.ADDRESS, // commitStringCallback, value
                ValueLayout.ADDRESS, // NULL terminator
            ),
            Linker.Option.firstVariadicArg(1),
        )
    }.orElse(null)
}

/**
 * void XDestroyIC(XIC ic);
 */
internal val xDestroyIC: MethodHandle? by lazy {
    libX11.downcall(
        "XDestroyIC",
        FunctionDescriptor.ofVoid(
            ValueLayout.ADDRESS,  // XIC
        )
    )
}

/**
 * Status XConvertSelection(Display* display, Atom selection, Atom target,
 *     Atom property, Window requestor, Time time);
 *
 * Requests that the owner of selection convert it to target and write it
 * to property on requestor. The result arrives via SelectionNotify.
 */
internal val xConvertSelection: MethodHandle? by lazy {
    libX11.downcall(
        "XConvertSelection",
        FunctionDescriptor.of(
            ValueLayout.JAVA_INT,    // Status
            ValueLayout.ADDRESS,     // Display*
            ValueLayout.JAVA_LONG,   // Atom selection
            ValueLayout.JAVA_LONG,   // Atom target
            ValueLayout.JAVA_LONG,   // Atom property
            ValueLayout.JAVA_LONG,   // Window requestor
            ValueLayout.JAVA_LONG,   // Time
        )
    )
}

/**
 * void XSetICFocus(XIC ic);
 */
internal val xSetICFocus: MethodHandle? by lazy {
    libX11.downcall(
        "XSetICFocus",
        FunctionDescriptor.ofVoid(
            ValueLayout.ADDRESS,  // XIC
        )
    )
}

/**
 * void XUnsetICFocus(XIC ic);
 */
internal val xUnsetICFocus: MethodHandle? by lazy {
    libX11.downcall(
        "XUnsetICFocus",
        FunctionDescriptor.ofVoid(
            ValueLayout.ADDRESS,  // XIC
        )
    )
}

/**
 * Bool XFilterEvent(XEvent* event, Window w);
 *
 * Returns non-zero if the event was consumed by the IME.
 */
internal val xFilterEvent: MethodHandle? by lazy {
    libX11.downcall(
        "XFilterEvent",
        FunctionDescriptor.of(
            ValueLayout.JAVA_INT,   // Bool return
            ValueLayout.ADDRESS,    // XEvent*
            ValueLayout.JAVA_LONG,  // Window
        )
    )
}

/**
 * char* XSetICValues(XIC ic, ...) variadic.
 *
 * Used to set XIC attributes (XNArea, XNSpotLocation, etc.).
 * Returns NULL on success.
 */
internal val xSetICValues: MethodHandle? by lazy {
    val lookup = libX11 ?: return@lazy null
    lookup.find("XSetICValues").map { symbol ->
        linker.downcallHandle(symbol,
            FunctionDescriptor.of(ValueLayout.ADDRESS,
                ValueLayout.ADDRESS,  // XIC
                ValueLayout.ADDRESS, ValueLayout.ADDRESS, // area, &rect
                ValueLayout.ADDRESS, ValueLayout.ADDRESS, // spotLocation, &point
                ValueLayout.ADDRESS, // NULL
            ),
            Linker.Option.firstVariadicArg(1),
        )
    }.orElse(null)
}
