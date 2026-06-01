/**
 * X11 implementation of the [Window] interface for Linux Desktop.
 *
 * Uses the Foreign Function & Memory API (JEP 454, JDK 25) to interact
 * with libX11.so.6 without JNA or any other intermediate layer.
 *
 * Creation flow:
 *  1. XCreateSimpleWindow     — creates the child window of the root window
 *  2. XSelectInput            — selects the full event mask
 *  3. XInternAtom             — obtains the WM_DELETE_WINDOW atom
 *  4. XSetWMProtocols         — installs the clean-close protocol
 *  5. XStoreName              — sets the title
 *  6. XMapWindow              — makes the window visible (if attrs.visible = true)
 *
 * X11Window — complete implementation of the Window interface.
 */
package org.graphiks.kadre.x11

import org.graphiks.kadre.core.PhysicalPosition
import org.graphiks.kadre.core.PhysicalSize
import org.graphiks.kadre.core.RawDisplayHandle
import org.graphiks.kadre.core.RawWindowHandle
import org.graphiks.kadre.core.Window
import org.graphiks.kadre.core.WindowAttributes
import org.graphiks.kadre.core.WindowId
import java.lang.foreign.Arena
import java.lang.foreign.MemorySegment
import java.lang.foreign.ValueLayout


/**
 * Combined event mask selected for each X11 window.
 *
 * Includes: Expose, KeyPress, KeyRelease, ButtonPress, ButtonRelease,
 * PointerMotion, StructureNotify (ConfigureNotify, DestroyNotify, …).
 */
private val FULL_EVENT_MASK: Long =
    ExposureMask or
    KeyPressMask or
    KeyReleaseMask or
    ButtonPressMask or
    ButtonReleaseMask or
    PointerMotionMask or
    StructureNotifyMask

/**
 * Native X11 window implementing [Window].
 *
 * The constructor is internal: use [X11Window.create] to instantiate.
 *
 * @param displayPtr Pointer to the X11 Display structure (Long value of MemorySegment.address()).
 * @param xWindowId  XID identifier of the created window (unsigned long → Long).
 * @param attrs      Window creation attributes.
 */
class X11Window private constructor(
    private val displayPtr: Long,
    private val xWindowId: Long,
    private val attrs: WindowAttributes,
) : Window {

    override val id: WindowId = WindowId(xWindowId)

    override val rawWindowHandle: RawWindowHandle
        get() = RawWindowHandle.Xlib(window = xWindowId, display = displayPtr)

    override val rawDisplayHandle: RawDisplayHandle
        get() = RawDisplayHandle.Xlib(display = displayPtr)

    /**
     * Current inner size in physical pixels.
     *
     * Initialized from attrs.size; updated by ConfigureNotify events
     * via [onConfigureNotify].
     */
    @Volatile
    private var _innerSize: PhysicalSize<Int> = attrs.size ?: PhysicalSize(800, 600)

    override val innerSize: PhysicalSize<Int>
        get() = _innerSize

    /**
     * Outer size (surface + WM decorations) in physical pixels.
     *
     * On X11, decorations are managed by the window manager and
     * unknown without a call to XGetGeometry + XQueryTree. We return the same
     * value as [innerSize] for now.
     *
     * TODO: use XGetGeometry to distinguish inner/outer if needed.
     */
    override val outerSize: PhysicalSize<Int>
        get() = _innerSize

    /**
     * DPI scale factor of this window.
     *
     * Read once at construction time from the X11 RESOURCE_MANAGER property
     * (Xft.dpi entry). Formula: scaleFactor = Xft.dpi / 96.0.
     * Falls back to 1.0 if the resource is absent or unreadable.
     *
     * ScaleFactorChanged is not emitted dynamically (no RRNotify subscription yet).
     */
    override val scaleFactor: Double = readXftDpi(displayPtr)

    override fun requestRedraw() {
        // No direct action needed: the event loop picks up the Expose events.
        // Optionally, we could send an XSendEvent Expose — deferred to later.
    }

    override fun setVisible(visible: Boolean) {
        _isVisible = visible
        val display = MemorySegment.ofAddress(displayPtr)
        try {
            if (visible) {
                xMapWindow?.invokeExact(display, xWindowId) as? Int
            } else {
                xUnmapWindow?.invokeExact(display, xWindowId) as? Int
            }
            xFlush?.invokeExact(display) as? Int
        } catch (_: Throwable) {}
    }

    override fun close() {
        val handle = xDestroyWindow ?: return
        val display = MemorySegment.ofAddress(displayPtr)
        handle.invokeExact(display, xWindowId) as Int
        xFlush?.invokeExact(display) as? Int
    }

    // ── R1: window state & geometry ───────────────────────────────────────────

    /** Track the title in memory since XFetchName requires additional bindings. */
    @Volatile private var _title: String = attrs.title

    override val title: String get() = _title

    override fun setTitle(title: String) {
        _title = title
        val handle = xStoreName ?: return
        val display = MemorySegment.ofAddress(displayPtr)
        Arena.ofConfined().use { arena ->
            val nameBytes = title.toByteArray(Charsets.ISO_8859_1)
            val namePtr = arena.allocate(nameBytes.size.toLong() + 1)
            for (i in nameBytes.indices) namePtr.set(ValueLayout.JAVA_BYTE, i.toLong(), nameBytes[i])
            namePtr.set(ValueLayout.JAVA_BYTE, nameBytes.size.toLong(), 0)
            handle.invokeExact(display, xWindowId, namePtr) as Int
        }
    }

    @Volatile private var _isVisible: Boolean = attrs.visible

    override val isVisible: Boolean get() = _isVisible

    @Volatile private var _isResizable: Boolean = attrs.resizable

    override val isResizable: Boolean get() = _isResizable

    @Volatile private var _isMinimized: Boolean = false

    override val isMinimized: Boolean get() = _isMinimized

    @Volatile private var _isMaximized: Boolean = attrs.maximized

    override val isMaximized: Boolean get() = _isMaximized

    @Volatile private var _isDecorated: Boolean = attrs.decorations

    override val isDecorated: Boolean get() = _isDecorated

    override fun setResizable(resizable: Boolean) {
        _isResizable = resizable
        // Update _NET_WM_NORMAL_HINTS: set min/max size equal to current size when not resizable.
        // The simplest approach is to use WM_NORMAL_HINTS (XSetWMNormalHints equivalent) via
        // XChangeProperty. We just update our tracked state here; the event loop may re-apply.
        // (Full XSizeHints implementation deferred — flagged for future ticket.)
    }

    override fun setMinimized(minimized: Boolean) {
        _isMinimized = minimized
        val display = MemorySegment.ofAddress(displayPtr)
        try {
            if (minimized) {
                // XIconifyWindow sends a WM_CHANGE_STATE ClientMessage with IconicState
                xIconifyWindow?.invokeExact(display, xWindowId, 0) as? Int
            } else {
                // XMapWindow restores the window
                xMapWindow?.invokeExact(display, xWindowId) as? Int
            }
            xFlush?.invokeExact(display) as? Int
        } catch (_: Throwable) {}
    }

    override fun setMaximized(maximized: Boolean) {
        _isMaximized = maximized
        // Send _NET_WM_STATE ClientMessage to the root window
        sendNetWmState(maximized,
            internAtom(displayPtr, "_NET_WM_STATE_MAXIMIZED_VERT"),
            internAtom(displayPtr, "_NET_WM_STATE_MAXIMIZED_HORZ"),
        )
    }

    override fun setDecorations(decorated: Boolean) {
        _isDecorated = decorated
        // Set/clear the _MOTIF_WM_HINTS property to request decorations from the WM.
        setMotifDecorations(decorated)
    }

    override fun setMinSurfaceSize(size: PhysicalSize<Int>?) {
        // Store for potential use in _NET_WM_NORMAL_HINTS / WM_NORMAL_HINTS.
        // Full XSetWMNormalHints would require the XSizeHints struct — deferred.
        _minSurfaceSize = size
    }

    override fun setMaxSurfaceSize(size: PhysicalSize<Int>?) {
        _maxSurfaceSize = size
    }

    @Volatile private var _minSurfaceSize: PhysicalSize<Int>? = attrs.minSize
    @Volatile private var _maxSurfaceSize: PhysicalSize<Int>? = attrs.maxSize

    override val outerPosition: PhysicalPosition<Int>
        get() {
            val display = MemorySegment.ofAddress(displayPtr)
            return try {
                Arena.ofConfined().use { arena ->
                    val rootOut = arena.allocate(ValueLayout.JAVA_LONG)
                    val xOut    = arena.allocate(ValueLayout.JAVA_INT)
                    val yOut    = arena.allocate(ValueLayout.JAVA_INT)
                    val wOut    = arena.allocate(ValueLayout.JAVA_INT)
                    val hOut    = arena.allocate(ValueLayout.JAVA_INT)
                    val bwOut   = arena.allocate(ValueLayout.JAVA_INT)
                    val dOut    = arena.allocate(ValueLayout.JAVA_INT)
                    val ok = xGetGeometry?.invokeExact(
                        display, xWindowId, rootOut, xOut, yOut, wOut, hOut, bwOut, dOut,
                    ) as? Int ?: 0
                    if (ok == 0) return@use PhysicalPosition(0, 0)
                    val x = xOut.get(ValueLayout.JAVA_INT, 0L)
                    val y = yOut.get(ValueLayout.JAVA_INT, 0L)
                    PhysicalPosition(x, y)
                }
            } catch (_: Throwable) { PhysicalPosition(0, 0) }
        }

    override fun setOuterPosition(position: PhysicalPosition<Int>) {
        try {
            val display = MemorySegment.ofAddress(displayPtr)
            xMoveWindow?.invokeExact(display, xWindowId, position.x, position.y) as? Int
            xFlush?.invokeExact(display) as? Int
        } catch (_: Throwable) {}
    }

    /**
     * No-op on X11: there is no equivalent to Wayland's `wl_surface.pre_commit`.
     */
    override fun prePresentNotify() { /* no-op on X11 */ }

    // ── X11 helper: intern atom ───────────────────────────────────────────────

    private fun internAtom(displayPtr: Long, name: String): Long {
        val display = MemorySegment.ofAddress(displayPtr)
        return try {
            Arena.ofConfined().use { arena ->
                val bytes = name.toByteArray(Charsets.US_ASCII)
                val ptr = arena.allocate(bytes.size.toLong() + 1)
                bytes.forEachIndexed { i, b -> ptr.set(ValueLayout.JAVA_BYTE, i.toLong(), b) }
                ptr.set(ValueLayout.JAVA_BYTE, bytes.size.toLong(), 0)
                xInternAtom?.invokeExact(display, ptr, 0) as? Long ?: 0L
            }
        } catch (_: Throwable) { 0L }
    }

    // ── X11 helper: send _NET_WM_STATE ────────────────────────────────────────

    /**
     * Sends a _NET_WM_STATE ClientMessage to the root window to request a
     * state change from the window manager.
     *
     * @param add   true = add (_NET_WM_STATE_ADD=1), false = remove (_NET_WM_STATE_REMOVE=0).
     * @param atom1 First state atom.
     * @param atom2 Second state atom (0 if unused).
     */
    private fun sendNetWmState(add: Boolean, atom1: Long, atom2: Long) {
        if (atom1 == 0L) return
        val display = MemorySegment.ofAddress(displayPtr)
        val wmStateAtom = internAtom(displayPtr, "_NET_WM_STATE")
        if (wmStateAtom == 0L) return
        try {
            Arena.ofConfined().use { arena ->
                // XClientMessageEvent canonical LP64 layout (96 bytes):
                //   0 type, 8 serial, 16 send_event, 24 display, 32 window,
                //   40 message_type, 48 format, 56 data.l[0], 64 l[1], 72 l[2].
                // These are the offsets a real X server / WM reads, so they must be
                // canonical regardless of how X11DrawMapper reads incoming events
                // (its documented 56/64 offsets are inconsistent with this and are
                // flagged for separate investigation).
                val eventBuf = arena.allocate(96L, 8L)
                eventBuf.set(ValueLayout.JAVA_INT, 0L, ClientMessage)  // type = ClientMessage (33)
                eventBuf.set(ValueLayout.JAVA_LONG, 32L, xWindowId)    // window
                eventBuf.set(ValueLayout.JAVA_LONG, 40L, wmStateAtom)  // message_type
                eventBuf.set(ValueLayout.JAVA_INT, 48L, 32)            // format = 32
                // data.l[0] = action (_NET_WM_STATE_ADD=1 / _REMOVE=0)
                eventBuf.set(ValueLayout.JAVA_LONG, 56L, if (add) 1L else 0L)
                // data.l[1] = atom1, data.l[2] = atom2
                eventBuf.set(ValueLayout.JAVA_LONG, 64L, atom1)
                eventBuf.set(ValueLayout.JAVA_LONG, 72L, atom2)

                // Obtain the root window XID
                val rootHandle = xRootWindow ?: return@use
                val root: Long = rootHandle.invokeExact(display, 0) as Long

                // SubstructureRedirectMask | SubstructureNotifyMask = 0x180000
                val mask: Long = 0x180000L
                xSendEvent?.invokeExact(display, root, 0, mask, eventBuf) as? Int
                xFlush?.invokeExact(display) as? Int
            }
        } catch (_: Throwable) {}
    }

    // ── X11 helper: set/unset _MOTIF_WM_HINTS for decorations ────────────────

    /**
     * Sets or clears the _MOTIF_WM_HINTS property to request the window
     * manager to show or hide the window decorations.
     *
     * _MOTIF_WM_HINTS has 5 elements with property format 32. On LP64, an X11
     * property of format 32 is an array of C `long` (8 bytes each), so the buffer
     * is 5 × 8 = 40 bytes written as JAVA_LONG (nelements stays 5):
     *   [0] flags: bit 1 = functions, bit 2 = decorations
     *   [1] functions
     *   [2] decorations: 0 = no decorations, 1 = all decorations
     *   [3] inputMode
     *   [4] status
     */
    private fun setMotifDecorations(decorated: Boolean) {
        val display = MemorySegment.ofAddress(displayPtr)
        val motifAtom = internAtom(displayPtr, "_MOTIF_WM_HINTS")
        if (motifAtom == 0L) return
        try {
            Arena.ofConfined().use { arena ->
                // format 32 → array of C long on LP64: 5 × 8 = 40 bytes.
                val hints = arena.allocate(40L, 8L)
                hints.setAtIndex(ValueLayout.JAVA_LONG, 0L, 2L)              // flags = MWM_HINTS_DECORATIONS (2)
                hints.setAtIndex(ValueLayout.JAVA_LONG, 1L, 0L)             // functions (unused)
                hints.setAtIndex(ValueLayout.JAVA_LONG, 2L, if (decorated) 1L else 0L) // decorations
                hints.setAtIndex(ValueLayout.JAVA_LONG, 3L, 0L)             // inputMode
                hints.setAtIndex(ValueLayout.JAVA_LONG, 4L, 0L)             // status
                xChangeProperty?.invokeExact(
                    display, xWindowId,
                    motifAtom, motifAtom,
                    32, 0 /* PropModeReplace */,
                    hints, 5,
                ) as? Int
                xFlush?.invokeExact(display) as? Int
            }
        } catch (_: Throwable) {}
    }

    /**
     * Updates the inner size upon receiving a ConfigureNotify event.
     *
     * @param width  New width in pixels.
     * @param height New height in pixels.
     */
    fun onConfigureNotify(width: Int, height: Int) {
        if (width > 0 && height > 0) {
            _innerSize = PhysicalSize(width, height)
        }
    }

    // ── Companion ─────────────────────────────────────────────────────────────

    companion object {

        /**
         * Creates a native X11 window.
         *
         * Performs all the necessary initialization:
         * XCreateSimpleWindow → XSelectInput → WM_DELETE_WINDOW → XStoreName → XMapWindow.
         *
         * @param display Long representing the Display* pointer (address of the MemorySegment).
         * @param screen  X11 screen number (DefaultScreen).
         * @param attrs   Window attributes (title, size, visibility, etc.).
         * @return The created window, or null if the libX11 bindings are not available
         *         (macOS/Windows) or if creation fails.
         */
        fun create(display: Long, screen: Int, attrs: WindowAttributes): X11Window? {
            // The bindings are null on non-Linux — return null gracefully.
            val createHandle = xCreateSimpleWindow ?: return null

            val displaySeg = MemorySegment.ofAddress(display)

            // ── 1. Root window via XRootWindow(display, screen) ───────────────
            // Equivalent to DefaultRootWindow(display). The real root XID is required
            // as the parent of XCreateSimpleWindow: a hardcoded conventional value
            // causes BadWindow (X_CreateWindow) on real X servers.
            val rootHandle = xRootWindow ?: return null
            val rootWindow: Long = rootHandle.invokeExact(displaySeg, screen) as Long
            if (rootWindow == 0L) return null

            val width = attrs.size?.width ?: 800
            val height = attrs.size?.height ?: 600

            // ── 2. XCreateSimpleWindow ────────────────────────────────────────
            val xWindowId: Long = createHandle.invokeExact(
                displaySeg,     // Display*
                rootWindow,     // Window parent
                0,              // int x
                0,              // int y
                width,          // unsigned int width
                height,         // unsigned int height
                1,              // unsigned int border_width
                0L,             // unsigned long border (BlackPixel = 0)
                0L,             // unsigned long background (BlackPixel = 0)
            ) as Long

            if (xWindowId == 0L) return null

            // ── 3. XSelectInput ───────────────────────────────────────────────
            xSelectInput?.invokeExact(displaySeg, xWindowId, FULL_EVENT_MASK) as? Int

            // ── 4. WM_DELETE_WINDOW (clean-close protocol) ────────────────────
            Arena.ofConfined().use { arena ->
                val atomName = "WM_DELETE_WINDOW".toByteArray(Charsets.US_ASCII)
                val atomNamePtr = arena.allocate(atomName.size.toLong() + 1)
                for (i in atomName.indices) atomNamePtr.set(ValueLayout.JAVA_BYTE, i.toLong(), atomName[i])
                atomNamePtr.set(ValueLayout.JAVA_BYTE, atomName.size.toLong(), 0)

                val wmDeleteWindow: Long = xInternAtom?.invokeExact(
                    displaySeg,
                    atomNamePtr,
                    0,  // Bool only_if_exists = False → creates if absent
                ) as? Long ?: 0L

                if (wmDeleteWindow != 0L) {
                    // Allocate an array of 1 Atom (unsigned long = 8 bytes) for XSetWMProtocols
                    val atomArray = arena.allocate(ValueLayout.JAVA_LONG, 1L)
                    atomArray.set(ValueLayout.JAVA_LONG, 0L, wmDeleteWindow)
                    xSetWMProtocols?.invokeExact(displaySeg, xWindowId, atomArray, 1) as? Int
                }
            }

            // ── 5. XStoreName ─────────────────────────────────────────────────
            Arena.ofConfined().use { arena ->
                val nameBytes = attrs.title.toByteArray(Charsets.ISO_8859_1)
                val namePtr = arena.allocate(nameBytes.size.toLong() + 1)
                for (i in nameBytes.indices) namePtr.set(ValueLayout.JAVA_BYTE, i.toLong(), nameBytes[i])
                namePtr.set(ValueLayout.JAVA_BYTE, nameBytes.size.toLong(), 0)
                xStoreName?.invokeExact(displaySeg, xWindowId, namePtr) as? Int
            }

            val window = X11Window(display, xWindowId, attrs)

            // ── 6. XMapWindow (if visible) ────────────────────────────────────
            if (attrs.visible) {
                xMapWindow?.invokeExact(displaySeg, xWindowId) as? Int
                xFlush?.invokeExact(displaySeg) as? Int
            }

            return window
        }
    }
}
