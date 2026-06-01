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

    override fun setTitle(title: String) {
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

    override fun setVisible(visible: Boolean) {
        if (visible) {
            val handle = xMapWindow ?: return
            val display = MemorySegment.ofAddress(displayPtr)
            handle.invokeExact(display, xWindowId) as Int
            xFlush?.invokeExact(display) as? Int
        }
        // XUnmapWindow is not yet in the bindings — deferred to later.
    }

    override fun close() {
        val handle = xDestroyWindow ?: return
        val display = MemorySegment.ofAddress(displayPtr)
        handle.invokeExact(display, xWindowId) as Int
        xFlush?.invokeExact(display) as? Int
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
