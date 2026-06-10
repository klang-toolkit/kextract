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

import org.graphiks.kadre.core.CursorGrabMode
import org.graphiks.kadre.core.CursorIcon
import org.graphiks.kadre.core.CustomCursor
import org.graphiks.kadre.core.Fullscreen
import org.graphiks.kadre.core.Icon
import org.graphiks.kadre.core.Insets
import org.graphiks.kadre.core.MonitorHandle
import org.graphiks.kadre.core.PhysicalPosition
import org.graphiks.kadre.core.PhysicalSize
import org.graphiks.kadre.core.InputCapabilities
import org.graphiks.kadre.core.RawDisplayHandle
import org.graphiks.kadre.core.RawWindowHandle
import org.graphiks.kadre.core.RequestError
import org.graphiks.kadre.core.ResizeDirection
import org.graphiks.kadre.core.SurfaceSizeRequestResult
import org.graphiks.kadre.core.Theme
import org.graphiks.kadre.core.UserAttentionType
import org.graphiks.kadre.core.ActiveEventLoop
import org.graphiks.kadre.core.ApplicationHandler
import org.graphiks.kadre.core.ImePurpose
import org.graphiks.kadre.core.Window
import org.graphiks.kadre.core.WindowAttributes
import org.graphiks.kadre.core.WindowButtons
import org.graphiks.kadre.core.WindowEvent
import org.graphiks.kadre.core.WindowId
import org.graphiks.kadre.core.WindowLevel
import org.graphiks.kadre.core.WindowRequestResult
import java.lang.foreign.Arena
import java.lang.foreign.FunctionDescriptor
import java.lang.foreign.Linker
import java.lang.foreign.MemorySegment
import java.lang.foreign.ValueLayout
import java.lang.invoke.MethodHandles
import java.lang.invoke.MethodType
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentLinkedQueue


/**
 * Combined event mask selected for each X11 window.
 *
 * Includes: Expose, KeyPress, KeyRelease, ButtonPress, ButtonRelease,
 * PointerMotion, VisibilityNotify, FocusIn/FocusOut, StructureNotify.
 */
private val FULL_EVENT_MASK: Long =
    ExposureMask or
    KeyPressMask or
    KeyReleaseMask or
    ButtonPressMask or
    ButtonReleaseMask or
    PointerMotionMask or
    VisibilityChangeMask or
    FocusChangeMask or
    StructureNotifyMask

// EWMH _NET_WM_MOVERESIZE directions.
private const val MOVERESIZE_TOPLEFT: Long = 0L
private const val MOVERESIZE_TOP: Long = 1L
private const val MOVERESIZE_TOPRIGHT: Long = 2L
private const val MOVERESIZE_RIGHT: Long = 3L
private const val MOVERESIZE_BOTTOMRIGHT: Long = 4L
private const val MOVERESIZE_BOTTOM: Long = 5L
private const val MOVERESIZE_BOTTOMLEFT: Long = 6L
private const val MOVERESIZE_LEFT: Long = 7L
private const val MOVERESIZE_MOVE: Long = 8L

private const val X11_SHAPE_SET: Int = 0
private const val X11_SHAPE_INPUT: Int = 2
private const val X11_SHAPE_UNSORTED: Int = 0
private const val X11_RECTANGLE_SIZE_BYTES: Long = 8L
private const val X11_WM_HINTS_FLAGS_OFFSET: Long = 0L
internal const val X11_WM_HINTS_URGENCY_FLAG: Long = 1L shl 8

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
    private val screen: Int,
    private val xWindowId: Long,
    private val attrs: WindowAttributes,
) : Window {

    override val id: WindowId = WindowId(xWindowId)

    override val rawWindowHandle: RawWindowHandle
        get() = RawWindowHandle.Xlib(window = xWindowId, display = displayPtr)

    override val rawDisplayHandle: RawDisplayHandle
        get() = RawDisplayHandle.Xlib(display = displayPtr)

    override fun inputCapabilities(): InputCapabilities =
        InputCapabilities()

    /**
     * Current inner size in physical pixels.
     *
     * Initialized from attrs.size; updated by ConfigureNotify events
     * via [onConfigureNotify].
     */
    @Volatile
    private var _innerSize: PhysicalSize<Int> = attrs.size ?: PhysicalSize(800, 600)

    @Volatile
    private var _outerPosition: PhysicalPosition<Int> = x11InitialPosition(attrs.position)

    @Volatile
    private var _frameExtents: X11FrameExtents? = null

    override val innerSize: PhysicalSize<Int>
        get() = _innerSize

    override val outerSize: PhysicalSize<Int>
        get() = (_frameExtents ?: readFrameExtents()?.also { _frameExtents = it })?.surfaceSizeToOuter(surfaceSize)
            ?: surfaceSize

    override val surfaceSize: PhysicalSize<Int>
        get() = readSurfaceSize() ?: _innerSize

    override fun requestSurfaceSize(size: PhysicalSize<Int>): SurfaceSizeRequestResult {
        val resizeWindow = xResizeWindow ?: return SurfaceSizeRequestResult.Failure(
            RequestError.Unsupported("XResizeWindow is unavailable"),
        )
        val requested = x11ValidSurfaceSize(size)
        return try {
            if (!_isResizable) {
                applyNormalHints(sizeOverride = requested)
            }
            val display = MemorySegment.ofAddress(displayPtr)
            resizeWindow.invokeExact(display, xWindowId, requested.width, requested.height) as Int
            val flush = xFlush
            if (flush != null) flush.invokeExact(display) as Int
            SurfaceSizeRequestResult.Pending
        } catch (t: Throwable) {
            SurfaceSizeRequestResult.Failure(
                RequestError.OsError(t.message ?: t::class.simpleName ?: "X11 surface resize failed"),
            )
        }
    }

    override val surfacePosition: PhysicalPosition<Int>
        get() = (_frameExtents ?: readFrameExtents()?.also { _frameExtents = it })?.surfacePosition
            ?: PhysicalPosition(0, 0)

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

    /**
     * X11 has no platform safe-area concept — window managers handle decorations.
     */
    override val safeArea: Insets<Int> get() = Insets(0, 0, 0, 0)

    override fun requestRedraw() {
        // No direct action needed: the event loop picks up the Expose events.
        // Optionally, we could send an XSendEvent Expose — deferred to later.
    }

    override fun setVisible(visible: Boolean) {
        val display = MemorySegment.ofAddress(displayPtr)
        try {
            if (visible) {
                if (_visibilityState != X11_VISIBILITY_NO) return
                val mapWindow = xMapWindow ?: return
                val raiseWindow = xRaiseWindow
                mapWindow.invokeExact(display, xWindowId) as Int
                if (raiseWindow != null) raiseWindow.invokeExact(display, xWindowId) as Int
                val flush = xFlush
                if (flush != null) flush.invokeExact(display) as Int
                _visibilityState = x11VisibilityAfterSet(_visibilityState, visible = true)
            } else {
                if (_visibilityState == X11_VISIBILITY_NO) return
                val unmapWindow = xUnmapWindow ?: return
                unmapWindow.invokeExact(display, xWindowId) as Int
                val flush = xFlush
                if (flush != null) flush.invokeExact(display) as Int
                _visibilityState = x11VisibilityAfterSet(_visibilityState, visible = false)
            }
        } catch (_: Throwable) {}
    }

    override fun close() {
        val display = MemorySegment.ofAddress(displayPtr)
        disableIme()
        val handle = xDestroyWindow ?: return
        handle.invokeExact(display, xWindowId) as Int
        freeCachedCursors(display)
        val flush = xFlush
        if (flush != null) flush.invokeExact(display) as Int
    }

    // ── R1: window state & geometry ───────────────────────────────────────────

    /** Track the title in memory since XFetchName requires additional bindings. */
    @Volatile private var _title: String = attrs.title

    override val title: String get() = _title

    override fun setTitle(title: String) {
        _title = title
        writeX11Title(title)
    }

    @Volatile private var _visibilityState: Int =
        if (attrs.visible) X11_VISIBILITY_YES_WAIT else X11_VISIBILITY_NO

    override val isVisible: Boolean? get() = x11VisibilityIsVisible(_visibilityState)

    @Volatile private var _isResizable: Boolean = attrs.resizable

    override val isResizable: Boolean get() = _isResizable

    /**
     * No-op on X11, matching winit.
     *
     * The local winit X11 backend accepts the request and continues reporting
     * all window buttons enabled.
     */
    override fun setEnabledButtons(buttons: WindowButtons) {
        @Suppress("UNUSED_EXPRESSION")
        x11EnabledButtonsAfterSet(buttons)
    }

    override val enabledButtons: WindowButtons
        get() = x11EnabledButtons()

    @Volatile private var _isMinimized: Boolean = false

    override val isMinimized: Boolean? get() =
        readNetWmStateContains(
            internAtom(displayPtr, "_NET_WM_STATE_HIDDEN"),
        ) ?: _isMinimized

    @Volatile private var _isMaximized: Boolean = attrs.maximized

    override val isMaximized: Boolean get() =
        readNetWmStateContainsAll(
            internAtom(displayPtr, "_NET_WM_STATE_MAXIMIZED_VERT"),
            internAtom(displayPtr, "_NET_WM_STATE_MAXIMIZED_HORZ"),
        ) ?: _isMaximized

    @Volatile private var _isDecorated: Boolean = attrs.decorations

    override val isDecorated: Boolean get() = _isDecorated

    @Volatile private var _hasFocus: Boolean = false

    override val hasFocus: Boolean get() = _hasFocus

    // ── IME (Input Method Editor) state ────────────────────────────────────────

    private var xic: MemorySegment = MemorySegment.NULL
    private var ximArena: Arena? = null
    private var clientDataSegment: MemorySegment = MemorySegment.NULL

    internal val pendingImeEvents = ConcurrentLinkedQueue<WindowEvent.Ime>()

    override fun focusWindow() {
        val iconic = readWmStateIconic() ?: (isMinimized == true)
        if (!x11FocusRequestAllowed(isVisible == true, iconic)) return
        sendNetActiveWindow()
    }

    override fun setResizable(resizable: Boolean) {
        val newResizable = x11ResizableChangeAfterRequest(
            current = _isResizable,
            requested = resizable,
            isXfwm4 = isXfwm4WindowManager(),
        ) ?: return
        _isResizable = newResizable
        applyNormalHints()
        setMotifMaximizable(newResizable)
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
        _minSurfaceSize = size
        applyNormalHints()
    }

    override fun setMaxSurfaceSize(size: PhysicalSize<Int>?) {
        _maxSurfaceSize = size
        applyNormalHints()
    }

    @Volatile private var _minSurfaceSize: PhysicalSize<Int>? = attrs.minSize
    @Volatile private var _maxSurfaceSize: PhysicalSize<Int>? = attrs.maxSize
    @Volatile private var _surfaceResizeIncrements: PhysicalSize<Int>? = attrs.resizeIncrements
    @Volatile private var _initialPosition: PhysicalPosition<Int>? = attrs.position

    override val surfaceResizeIncrements: PhysicalSize<Int>?
        get() = _surfaceResizeIncrements

    override fun setSurfaceResizeIncrements(increments: PhysicalSize<Int>?) {
        _surfaceResizeIncrements = increments
        applyNormalHints()
    }

    override val outerPosition: PhysicalPosition<Int>
        get() {
            val display = MemorySegment.ofAddress(displayPtr)
            return try {
                Arena.ofConfined().use { arena ->
                    val translate = xTranslateCoordinates ?: return@use _outerPosition
                    val rootHandle = xRootWindow ?: return@use _outerPosition
                    val extents = _frameExtents ?: readFrameExtents()?.also { _frameExtents = it }
                    val xOut    = arena.allocate(ValueLayout.JAVA_INT)
                    val yOut    = arena.allocate(ValueLayout.JAVA_INT)
                    val childOut = arena.allocate(ValueLayout.JAVA_LONG)
                    val root = rootHandle.invokeExact(display, screen) as Long
                    if (root == 0L) return@use _outerPosition
                    val ok = translate.invokeExact(
                        display,
                        xWindowId,
                        root,
                        0,
                        0,
                        xOut,
                        yOut,
                        childOut,
                    ) as Int
                    if (ok == 0) return@use _outerPosition
                    val x = xOut.get(ValueLayout.JAVA_INT, 0L)
                    val y = yOut.get(ValueLayout.JAVA_INT, 0L)
                    val position = if (extents != null) {
                        extents.innerToOuter(PhysicalPosition(x, y))
                    } else {
                        _outerPosition
                    }
                    position.also { _outerPosition = it }
                }
            } catch (_: Throwable) { _outerPosition }
        }

    override fun setOuterPosition(position: PhysicalPosition<Int>) {
        try {
            val display = MemorySegment.ofAddress(displayPtr)
            val moveWindow = xMoveWindow ?: return
            moveWindow.invokeExact(display, xWindowId, position.x, position.y) as Int
            val flush = xFlush
            if (flush != null) flush.invokeExact(display) as Int
            _outerPosition = position
        } catch (_: Throwable) {}
    }

    /**
     * No-op on X11: there is no equivalent to Wayland's `wl_surface.pre_commit`.
     */
    override fun prePresentNotify() { /* no-op on X11 */ }

    // ── R2: monitor & fullscreen ──────────────────────────────────────────────

    override fun currentMonitor(): MonitorHandle? {
        val monitors = enumerateX11Monitors(displayPtr, screen, scaleFactor)
        return selectX11MonitorForWindow(
            monitors,
            X11WindowRect(position = outerPosition, size = surfaceSize),
        )
    }

    override fun availableMonitors(): List<MonitorHandle> =
        enumerateX11Monitors(displayPtr, screen, scaleFactor)

    override fun primaryMonitor(): MonitorHandle? =
        primaryX11Monitor(displayPtr, screen, scaleFactor)

    /** In-memory fullscreen state (R2). */
    @Volatile private var _fullscreen: Fullscreen? = null
    @Volatile private var _desiredFullscreenPending: Boolean = attrs.fullscreen != null
    @Volatile private var _desiredFullscreen: Fullscreen? = attrs.fullscreen

    override val fullscreen: Fullscreen?
        get() = if (_desiredFullscreenPending) _desiredFullscreen else _fullscreen

    /**
     * Enters or exits fullscreen on X11 via _NET_WM_STATE_FULLSCREEN.
     *
     * Uses the [sendNetWmState] helper (already present in this class) to
     * send the appropriate ClientMessage to the window manager.
     *
     * Both [Fullscreen.Borderless] and [Fullscreen.Exclusive] map to the
     * _NET_WM_STATE_FULLSCREEN atom — X11 WMs do not support exclusive mode.
     */
    override fun setFullscreen(fullscreen: Fullscreen?) {
        val request = x11FullscreenRequest(_fullscreen, fullscreen, _visibilityState)
        if (request.defer) {
            _desiredFullscreenPending = true
            _desiredFullscreen = fullscreen
            return
        }

        _desiredFullscreenPending = false
        _desiredFullscreen = null
        if (!request.send) {
            _fullscreen = fullscreen
            return
        }

        applyFullscreenHint(fullscreen)
        _fullscreen = fullscreen
    }

    private fun applyFullscreenHint(fullscreen: Fullscreen?) {
        val atom = internAtom(displayPtr, "_NET_WM_STATE_FULLSCREEN")
        when (fullscreen) {
            null -> sendNetWmState(false, atom, 0L)
            is Fullscreen.Borderless -> sendNetWmState(true, atom, 0L)
            is Fullscreen.Exclusive  -> {
                // Exclusive fullscreen not supported on X11 via EWMH — fall back to borderless.
                // Note: XRandR mode-setting is possible but out of scope for R2.
                sendNetWmState(true, atom, 0L)
            }
        }
    }

    // ── R3: cursor, theme & appearance ───────────────────────────────────────

    @Volatile private var _selectedCursor: CursorIcon = attrs.cursor
    @Volatile private var _cursorVisible: Boolean = true
    @Volatile private var _hiddenCursor: Long = 0L
    @Volatile private var _cursorHittest: Boolean? = null
    private val namedCursorCache: MutableMap<CursorIcon, Long> = mutableMapOf()

    /**
     * Sets the cursor shape via XCreateFontCursor + XDefineCursor.
     */
    override fun setCursor(cursor: CursorIcon) {
        val previous = _selectedCursor
        _selectedCursor = cursor
        if (x11CursorChangeRequiresApply(previous, cursor, _cursorVisible)) {
            applySelectedCursor()
        }
    }

    /**
     * Shows or hides the cursor by defining either the selected X cursor or a
     * cached transparent 1x1 pixmap cursor on this window.
     */
    override fun setCursorVisible(visible: Boolean) {
        if (visible == _cursorVisible) return
        _cursorVisible = visible
        applySelectedCursor()
    }

    private fun applySelectedCursor() {
        try {
            val defineCursor = xDefineCursor ?: return
            val display = MemorySegment.ofAddress(displayPtr)
            val cursor = if (_cursorVisible) {
                createNamedCursor(display, _selectedCursor)
            } else {
                hiddenCursor(display)
            }
            if (cursor == 0L) return
            defineCursor.invokeExact(display, xWindowId, cursor) as Int
            val flush = xFlush
            if (flush != null) flush.invokeExact(display) as Int
        } catch (_: Throwable) {}
    }

    private fun createNamedCursor(display: MemorySegment, cursor: CursorIcon): Long {
        synchronized(namedCursorCache) {
            namedCursorCache[cursor]?.let { return it }
            val createFontCursor = xCreateFontCursor ?: return 0L
            val shape = cursorToXShape(cursor)
            val xcursor = createFontCursor.invokeExact(display, shape) as Long
            if (xcursor != 0L) namedCursorCache[cursor] = xcursor
            return xcursor
        }
    }

    private fun hiddenCursor(display: MemorySegment): Long {
        if (_hiddenCursor != 0L) return _hiddenCursor
        val rootHandle = xRootWindow ?: return 0L
        val createBitmap = xCreateBitmapFromData ?: return 0L
        val createPixmapCursor = xCreatePixmapCursor ?: return 0L
        val freePixmap = xFreePixmap
        return try {
            val root = rootHandle.invokeExact(display, screen) as Long
            if (root == 0L) return 0L
            Arena.ofConfined().use { arena ->
                val bitmapData = arena.allocate(1L, 1L)
                bitmapData.set(ValueLayout.JAVA_BYTE, 0L, 0)
                val source = createBitmap.invokeExact(display, root, bitmapData, 1, 1) as Long
                if (source == 0L) return@use 0L
                val mask = createBitmap.invokeExact(display, root, bitmapData, 1, 1) as Long
                if (mask == 0L) {
                    if (freePixmap != null) freePixmap.invokeExact(display, source) as Int
                    return@use 0L
                }
                try {
                    val foreground = arena.allocate(X11_COLOR_SIZE_BYTES, X11_COLOR_ALIGN_BYTES)
                    val background = arena.allocate(X11_COLOR_SIZE_BYTES, X11_COLOR_ALIGN_BYTES)
                    foreground.fill(0)
                    background.fill(0)
                    val cursor = createPixmapCursor.invokeExact(display, source, mask, foreground, background, 0, 0) as Long
                    _hiddenCursor = cursor
                    cursor
                } finally {
                    if (freePixmap != null) {
                        freePixmap.invokeExact(display, source) as Int
                        freePixmap.invokeExact(display, mask) as Int
                    }
                }
            }
        } catch (_: Throwable) {
            0L
        }
    }

    private fun freeCachedCursors(display: MemorySegment) {
        val freeCursor = xFreeCursor ?: return
        try {
            synchronized(namedCursorCache) {
                namedCursorCache.values.forEach { cursor ->
                    if (cursor != 0L) freeCursor.invokeExact(display, cursor) as Int
                }
                namedCursorCache.clear()
            }
            if (_hiddenCursor != 0L) {
                freeCursor.invokeExact(display, _hiddenCursor) as Int
                _hiddenCursor = 0L
            }
        } catch (_: Throwable) {}
    }

    /**
     * Sets the cursor grab mode via XGrabPointer / XUngrabPointer.
     *
     * - [CursorGrabMode.Confined]: grabs the pointer confined to this window.
     * - [CursorGrabMode.Locked]:   same (raw delta via DeviceEvent — no standard
     *   X11 API for lock; XGrabPointer is the best equivalent).
     * - [CursorGrabMode.None]:     releases the grab.
     *
     * Note: XGrabPointer may fail (returns != GrabSuccess) if the pointer is
     * already grabbed; the result is silently ignored per the no-throw contract.
     */
    override fun setCursorGrab(mode: CursorGrabMode): WindowRequestResult =
        try {
            val display = MemorySegment.ofAddress(displayPtr)
            when (mode) {
                CursorGrabMode.None -> {
                    val ungrab = xUngrabPointer ?: return WindowRequestResult.Failure(
                        RequestError.Unsupported("XUngrabPointer is unavailable"),
                    )
                    ungrab.invokeExact(display, 0L) as Int
                }
                CursorGrabMode.Confined, CursorGrabMode.Locked -> {
                    val grab = xGrabPointer ?: return WindowRequestResult.Failure(
                        RequestError.Unsupported("XGrabPointer is unavailable"),
                    )
                    // GrabModeAsync = 1; event_mask = PointerMotionMask|ButtonPressMask|ButtonReleaseMask
                    val eventMask = (PointerMotionMask or ButtonPressMask or ButtonReleaseMask).toInt()
                    val result = grab.invokeExact(
                        display,
                        xWindowId,        // grab_window
                        1,                // owner_events = True
                        eventMask,        // event_mask
                        1,                // pointer_mode = GrabModeAsync
                        1,                // keyboard_mode = GrabModeAsync
                        xWindowId,        // confine_to = this window
                        0L,               // cursor = None
                        0L,               // time = CurrentTime
                    ) as Int
                    if (result != 0) {
                        return WindowRequestResult.Failure(RequestError.OsError("XGrabPointer failed: $result"))
                    }
                }
            }
            val flush = xFlush
            if (flush != null) flush.invokeExact(display) as Int
            WindowRequestResult.Success
        } catch (t: Throwable) {
            WindowRequestResult.Failure(RequestError.OsError(t.message ?: t::class.simpleName ?: "X11 cursor grab failed"))
        }

    /**
     * Warps the cursor to [position] relative to this window via XWarpPointer.
     */
    override fun setCursorPosition(position: PhysicalPosition<Int>): WindowRequestResult =
        try {
            val warp = xWarpPointer ?: return WindowRequestResult.Failure(
                RequestError.Unsupported("XWarpPointer is unavailable"),
            )
            val display = MemorySegment.ofAddress(displayPtr)
            warp.invokeExact(
                display,
                0L,           // src_window = None
                xWindowId,    // dest_window = this window
                0, 0, 0, 0,   // src_x, src_y, src_width, src_height (ignored when src=None)
                position.x,
                position.y,
            ) as Int
            val flush = xFlush
            if (flush != null) flush.invokeExact(display) as Int
            WindowRequestResult.Success
        } catch (t: Throwable) {
            WindowRequestResult.Failure(RequestError.OsError(t.message ?: t::class.simpleName ?: "X11 cursor position failed"))
        }

    override fun setCursorHittest(hittest: Boolean): WindowRequestResult {
        val combineRectangles = xShapeCombineRectangles ?: return WindowRequestResult.Failure(
            RequestError.Unsupported("XShapeCombineRectangles is unavailable"),
        )
        return try {
            val display = MemorySegment.ofAddress(displayPtr)
            Arena.ofConfined().use { arena ->
                val rectangles = x11CursorHittestRectangles(hittest, surfaceSize)
                val rectPtr = if (rectangles.isEmpty()) {
                    MemorySegment.NULL
                } else {
                    val ptr = arena.allocate(X11_RECTANGLE_SIZE_BYTES * rectangles.size, 2L)
                    rectangles.forEachIndexed { index, rectangle ->
                        val offset = X11_RECTANGLE_SIZE_BYTES * index
                        ptr.set(ValueLayout.JAVA_SHORT, offset, rectangle.x.toShort())
                        ptr.set(ValueLayout.JAVA_SHORT, offset + 2L, rectangle.y.toShort())
                        ptr.set(ValueLayout.JAVA_SHORT, offset + 4L, rectangle.width.toShort())
                        ptr.set(ValueLayout.JAVA_SHORT, offset + 6L, rectangle.height.toShort())
                    }
                    ptr
                }
                combineRectangles.invokeExact(
                    display,
                    xWindowId,
                    X11_SHAPE_INPUT,
                    0,
                    0,
                    rectPtr,
                    rectangles.size,
                    X11_SHAPE_SET,
                    X11_SHAPE_UNSORTED,
                )
                val flush = xFlush
                if (flush != null) flush.invokeExact(display) as Int
            }
            _cursorHittest = hittest
            WindowRequestResult.Success
        } catch (t: Throwable) {
            WindowRequestResult.Failure(
                RequestError.OsError(t.message ?: t::class.simpleName ?: "X11 cursor hit-testing failed"),
            )
        }
    }

    /**
     * Applies a previously created custom cursor to this window.
     *
     * Calls XDefineCursor with the cursor XID stored in [cursor.id].
     * Never throws.
     */
    override fun setCustomCursor(cursor: CustomCursor) {
        try {
            val define = xDefineCursor ?: return
            val display = MemorySegment.ofAddress(displayPtr)
            define.invokeExact(display, xWindowId, cursor.id) as Int
            val flush = xFlush
            if (flush != null) flush.invokeExact(display) as Int
        } catch (_: Throwable) {}
    }

    /**
     * Starts an interactive WM-managed move via EWMH _NET_WM_MOVERESIZE.
     */
    override fun dragWindow(): WindowRequestResult =
        sendNetWmMoveResize(MOVERESIZE_MOVE)

    /**
     * Starts an interactive WM-managed resize via EWMH _NET_WM_MOVERESIZE.
     */
    override fun dragResizeWindow(direction: ResizeDirection): WindowRequestResult =
        sendNetWmMoveResize(
            when (direction) {
                ResizeDirection.East -> MOVERESIZE_RIGHT
                ResizeDirection.North -> MOVERESIZE_TOP
                ResizeDirection.NorthEast -> MOVERESIZE_TOPRIGHT
                ResizeDirection.NorthWest -> MOVERESIZE_TOPLEFT
                ResizeDirection.South -> MOVERESIZE_BOTTOM
                ResizeDirection.SouthEast -> MOVERESIZE_BOTTOMRIGHT
                ResizeDirection.SouthWest -> MOVERESIZE_BOTTOMLEFT
                ResizeDirection.West -> MOVERESIZE_LEFT
            }
        )

    /**
     * No-op on X11, matching winit.
     *
     * EWMH exposes move/resize initiation but no portable window-menu request.
     * winit accepts this call and ignores it on X11.
     */
    override fun showWindowMenu(position: PhysicalPosition<Int>): WindowRequestResult =
        x11ShowWindowMenuResult(position)

    /**
     * Returns null, matching winit X11: the backend can request a GTK theme
     * variant but does not expose a reliable current per-window theme query.
     */
    override val theme: Theme? get() = null

    /**
     * Requests a GTK theme variant through `_GTK_THEME_VARIANT`.
     *
     * This follows winit's X11 behavior. `null` maps to `"dark"`, which asks
     * GTK-aware window managers/toolkits to use their dark variant.
     */
    override fun setTheme(theme: Theme?) {
        try {
            val display = MemorySegment.ofAddress(displayPtr)
            val themeVariantAtom = internAtom(displayPtr, "_GTK_THEME_VARIANT")
            val utf8StringAtom = internAtom(displayPtr, "UTF8_STRING")
            if (themeVariantAtom == 0L || utf8StringAtom == 0L) return
            val variant = x11ThemeVariant(theme)
            Arena.ofConfined().use { arena ->
                val bytes = variant.toByteArray(Charsets.UTF_8)
                val data = arena.allocate(bytes.size.toLong(), 1L)
                for (index in bytes.indices) {
                    data.set(ValueLayout.JAVA_BYTE, index.toLong(), bytes[index])
                }
                xChangeProperty?.invokeExact(
                    display,
                    xWindowId,
                    themeVariantAtom,
                    utf8StringAtom,
                    8,
                    0,
                    data,
                    bytes.size,
                ) as? Int
                xFlush?.invokeExact(display) as? Int
            }
        } catch (_: Throwable) {}
    }

    // ── Platform extensions ────────────────────────────────────────────────────

    /**
     * Sets the EWMH _NET_WM_WINDOW_TYPE atom via XChangeProperty.
     *
     * Uses the standard ATOM type with format 32 (array of C longs on LP64).
     */
    internal fun setWindowType(type: WindowType): WindowRequestResult = try {
        val display = MemorySegment.ofAddress(displayPtr)
        val wmWindowTypeAtom = internAtom(displayPtr, "_NET_WM_WINDOW_TYPE")
        val atomAtom = internAtom(displayPtr, "ATOM")
        val typeAtom = internAtom(displayPtr, type.toNetWmWindowTypeAtom())
        if (wmWindowTypeAtom == 0L || atomAtom == 0L || typeAtom == 0L) {
            return WindowRequestResult.Failure(
                RequestError.Unsupported("_NET_WM_WINDOW_TYPE atoms not available")
            )
        }
        Arena.ofConfined().use { arena ->
            val data = arena.allocate(ValueLayout.JAVA_LONG, 1L)
            data.set(ValueLayout.JAVA_LONG, 0L, typeAtom)
            xChangeProperty?.invokeExact(
                display,
                xWindowId,
                wmWindowTypeAtom,
                atomAtom,
                32,
                0,
                data,
                1,
            ) as? Int
            xFlush?.invokeExact(display) as? Int
        }
        WindowRequestResult.Success
    } catch (t: Throwable) {
        WindowRequestResult.Failure(
            RequestError.OsError(t.message ?: t::class.simpleName ?: "X11 set window type failed")
        )
    }

    /**
     * Sets the override-redirect attribute via XChangeWindowAttributes.
     *
     * Uses CWOverrideRedirect valuemask and writes the Bool at the
     * correct offset in the XSetWindowAttributes structure.
     */
    internal fun setOverrideRedirect(redirect: Boolean): WindowRequestResult = try {
        val handle = xChangeWindowAttributes ?: return WindowRequestResult.Failure(
            RequestError.Unsupported("XChangeWindowAttributes is unavailable")
        )
        val display = MemorySegment.ofAddress(displayPtr)
        Arena.ofConfined().use { arena ->
            val attrs = arena.allocate(XSETWINDOWATTRIBUTES_SIZE, XSETWINDOWATTRIBUTES_ALIGN)
            attrs.fill(0)
            attrs.set(ValueLayout.JAVA_INT, XSETWINDOWATTR_OVERRIDE_REDIRECT_OFFSET, if (redirect) 1 else 0)
            handle.invokeExact(display, xWindowId, CWOverrideRedirect, attrs) as Int
        }
        xFlush?.invokeExact(display) as? Int
        WindowRequestResult.Success
    } catch (t: Throwable) {
        WindowRequestResult.Failure(
            RequestError.OsError(t.message ?: t::class.simpleName ?: "X11 set override redirect failed")
        )
    }

    /**
     * Sets the Z-order level via _NET_WM_STATE_ABOVE / _NET_WM_STATE_BELOW.
     */
    override fun setWindowLevel(level: WindowLevel) {
        val aboveAtom = internAtom(displayPtr, "_NET_WM_STATE_ABOVE")
        val belowAtom = internAtom(displayPtr, "_NET_WM_STATE_BELOW")
        val state = x11WindowLevelState(level)
        when {
            state.above -> {
                sendNetWmState(false, belowAtom, 0L)
                sendNetWmState(true,  aboveAtom, 0L)
            }
            state.below -> {
                sendNetWmState(false, aboveAtom, 0L)
                sendNetWmState(true,  belowAtom, 0L)
            }
            else -> {
                sendNetWmState(false, aboveAtom, 0L)
                sendNetWmState(false, belowAtom, 0L)
            }
        }
    }

    override fun requestUserAttention(requestType: UserAttentionType?): WindowRequestResult {
        val getHints = xGetWMHints ?: return WindowRequestResult.Failure(
            RequestError.Unsupported("XGetWMHints is unavailable"),
        )
        val allocHints = xAllocWMHints ?: return WindowRequestResult.Failure(
            RequestError.Unsupported("XAllocWMHints is unavailable"),
        )
        val setHints = xSetWMHints ?: return WindowRequestResult.Failure(
            RequestError.Unsupported("XSetWMHints is unavailable"),
        )
        val free = xFree
        return try {
            val display = MemorySegment.ofAddress(displayPtr)
            val existingHints = try {
                val existing = getHints.invokeExact(display, xWindowId) as MemorySegment
                if (existing != MemorySegment.NULL && existing.address() != 0L) existing else null
            } catch (_: Throwable) {
                null
            }
            val hints = existingHints ?: allocHints.invokeExact() as MemorySegment
            if (hints == MemorySegment.NULL || hints.address() == 0L) {
                return WindowRequestResult.Failure(RequestError.OsError("XAllocWMHints returned null"))
            }
            try {
                val view = hints.reinterpret(X11_WM_HINTS_SIZE_BYTES)
                if (existingHints == null) view.fill(0)
                val flags = view.get(ValueLayout.JAVA_LONG, X11_WM_HINTS_FLAGS_OFFSET)
                view.set(
                    ValueLayout.JAVA_LONG,
                    X11_WM_HINTS_FLAGS_OFFSET,
                    x11WmHintsUrgencyFlags(flags, requestType != null),
                )
                setHints.invokeExact(display, xWindowId, view)
                val flush = xFlush
                if (flush != null) flush.invokeExact(display) as Int
                WindowRequestResult.Success
            } finally {
                if (free != null) free.invokeExact(hints) as Int
            }
        } catch (t: Throwable) {
            WindowRequestResult.Failure(
                RequestError.OsError(t.message ?: t::class.simpleName ?: "X11 user attention request failed"),
            )
        }
    }

    /**
     * No-op on X11.
     *
     * Window transparency requires compositor support (e.g. compton/picom)
     * and the _NET_WM_WINDOW_OPACITY property or a compositor-specific API.
     * Documented no-op.
     *
     * TODO(R3-x11-transparent): set _NET_WM_WINDOW_OPACITY or request ARGB visual.
     */
    override fun setTransparent(transparent: Boolean) {
        if (!x11TransparencyRequiresNativeUpdate(transparent)) return
        // No-op on X11: standard transparency requires compositor-specific APIs.
    }

    /**
     * No-op on X11.
     *
     * Blur requires compositor-specific APIs (e.g. KDE Blur, _KDE_NET_WM_BLUR_BEHIND_REGION).
     * Documented no-op.
     */
    override fun setBlur(blur: Boolean) {
        if (!x11BlurRequiresNativeUpdate(blur)) return
        // No-op on X11: no standard blur API.
    }

    /**
     * Sets the window icon via _NET_WM_ICON.
     *
     * The property data is a flat array of C longs:
     *   [width, height, pixel0, pixel1, …]  (each in ARGB format)
     *
     * Risk FFM: writes an array of C longs (8 bytes each on LP64).
     * Passing null removes the property.
     */
    override fun setWindowIcon(icon: Icon?) {
        val display = MemorySegment.ofAddress(displayPtr)
        val wmIconAtom = internAtom(displayPtr, "_NET_WM_ICON")
        val cardinalAtom = internAtom(displayPtr, "CARDINAL")
        if (wmIconAtom == 0L) return
        try {
            if (icon == null) {
                // Delete the property (XDeleteProperty)
                xChangeProperty?.invokeExact(
                    display, xWindowId,
                    wmIconAtom, cardinalAtom,
                    32, 0, MemorySegment.NULL, 0,
                ) as? Int
                xFlush?.invokeExact(display) as? Int
                return
            }
            val w = icon.width
            val h = icon.height
            val nPixels = w * h
            val nElements = 2 + nPixels // width + height + pixels
            Arena.ofConfined().use { arena ->
                // Each element is a C long (8 bytes on LP64)
                val buf = arena.allocate(nElements.toLong() * 8, 8L)
                buf.setAtIndex(ValueLayout.JAVA_LONG, 0, w.toLong())
                buf.setAtIndex(ValueLayout.JAVA_LONG, 1, h.toLong())
                val rgba = icon.rgba
                for (i in 0 until nPixels) {
                    val base = i * 4
                    val r = rgba[base].toLong() and 0xFFL
                    val g = rgba[base + 1].toLong() and 0xFFL
                    val b = rgba[base + 2].toLong() and 0xFFL
                    val a = rgba[base + 3].toLong() and 0xFFL
                    // _NET_WM_ICON uses ARGB
                    val argb = (a shl 24) or (r shl 16) or (g shl 8) or b
                    buf.setAtIndex(ValueLayout.JAVA_LONG, (2 + i).toLong(), argb)
                }
                xChangeProperty?.invokeExact(
                    display, xWindowId,
                    wmIconAtom, cardinalAtom,
                    32, 0 /* PropModeReplace */,
                    buf, nElements,
                ) as? Int
                xFlush?.invokeExact(display) as? Int
            }
        } catch (_: Throwable) {}
    }

    /**
     * No-op on X11, matching winit.
     *
     * The X11 backend has no portable screen-capture protection mechanism.
     * winit accepts this request and ignores it, so Kadre reports success
     * instead of a platform-unsupported failure.
     */
    override fun setContentProtected(protected: Boolean): WindowRequestResult =
        x11ContentProtectionResult(protected)

    // ── R4: keyboard ──────────────────────────────────────────────────────────

    /**
     * Resets dead-key state for this X11 window.
     *
     * Best-effort: resets the [X11KeyMapper] internal pressed-key state.
     * Full XkbCompose / XIC reset would require the XIC pointer, which
     * is not yet stored in X11Window — documented TODO.
     *
     * TODO(R4-x11-dead-keys): store the XIC and call XmbResetIC / Xutf8ResetIC.
     */
    override fun resetDeadKeys() {
        X11KeyMapper.resetState()
    }

    // ── R5-IME: XIM (X Input Method) ──────────────────────────────────────────

    @Volatile private var _imeAllowed: Boolean = false

    override fun setImeAllowed(allowed: Boolean) {
        if (allowed == _imeAllowed) return
        _imeAllowed = allowed
        if (allowed) {
            enableIme()
        } else {
            disableIme()
        }
    }

    private fun enableIme() {
        val im = X11Window.acquireXIM(displayPtr)
        if (im == MemorySegment.NULL || im.address() == 0L) return

        val arena = Arena.ofShared()
        try {
            val clientData = arena.allocate(ValueLayout.JAVA_LONG)
            clientData.set(ValueLayout.JAVA_LONG, 0L, xWindowId)

            val inputStyleVal = MemorySegment.ofAddress((XIMPreeditCallbacks or XIMStatusNothing).toLong())
            val windowXidSeg = MemorySegment.ofAddress(xWindowId)

            val inputStyleName = arena.allocateFrom(XNInputStyle)
            val clientWindowName = arena.allocateFrom(XNClientWindow)
            val focusWindowName = arena.allocateFrom(XNFocusWindow)
            val psName = arena.allocateFrom(XNPreeditStartCallback)
            val pdName = arena.allocateFrom(XNPreeditDrawCallback)
            val pdDoneName = arena.allocateFrom(XNPreeditDoneCallback)
            val cmName = arena.allocateFrom(XNCommitStringCallback)

            val psCb = allocateXIMCallback(arena, clientData, X11Window.preeditStartUpcall)
            val pdCb = allocateXIMCallback(arena, clientData, X11Window.preeditDrawUpcall)
            val pdDoneCb = allocateXIMCallback(arena, clientData, X11Window.preeditDoneUpcall)
            val cmCb = allocateXIMCallback(arena, clientData, X11Window.commitUpcall)

            val handle = xCreateIC ?: return
            val ic = handle.invokeExact(
                im,
                inputStyleName, inputStyleVal,
                clientWindowName, windowXidSeg,
                focusWindowName, windowXidSeg,
                psName, psCb,
                pdName, pdCb,
                pdDoneName, pdDoneCb,
                cmName, cmCb,
                MemorySegment.NULL,
            ) as MemorySegment

            if (ic == MemorySegment.NULL || ic.address() == 0L) {
                X11Window.releaseXIM()
                arena.close()
                return
            }

            this.xic = ic
            this.ximArena = arena
            this.clientDataSegment = clientData
            X11Window.activeWindows[xWindowId] = this

            if (_hasFocus) {
                try {
                    xSetICFocus?.invokeExact(ic)
                } catch (_: Throwable) {}
            }
        } catch (t: Throwable) {
            X11Window.releaseXIM()
            arena.close()
        }
    }

    private fun disableIme() {
        val ic = xic
        if (ic.address() != 0L) {
            try {
                xDestroyIC?.invokeExact(ic)
            } catch (_: Throwable) {}
            xic = MemorySegment.NULL
        }
        ximArena?.close()
        ximArena = null
        clientDataSegment = MemorySegment.NULL
        X11Window.activeWindows.remove(xWindowId)
        X11Window.releaseXIM()
    }

    override fun setImeCursorArea(position: PhysicalPosition<Int>, size: PhysicalSize<Int>) {
        val ic = xic
        if (ic.address() == 0L) return
        val setHandle = xSetICValues ?: return
        try {
            Arena.ofConfined().use { arena ->
                val rect = arena.allocate(XRECTANGLE_SIZE, XRECTANGLE_ALIGN)
                rect.set(ValueLayout.JAVA_SHORT, 0L, position.x.toShort())
                rect.set(ValueLayout.JAVA_SHORT, 2L, position.y.toShort())
                rect.set(ValueLayout.JAVA_SHORT, 4L, size.width.toShort())
                rect.set(ValueLayout.JAVA_SHORT, 6L, size.height.toShort())

                val point = arena.allocate(XPOINT_SIZE, XPOINT_ALIGN)
                point.set(ValueLayout.JAVA_SHORT, 0L, position.x.toShort())
                point.set(ValueLayout.JAVA_SHORT, 2L, position.y.toShort())

                val areaName = arena.allocateFrom(XNArea)
                val spotName = arena.allocateFrom(XNSpotLocation)

                setHandle.invokeExact(
                    ic,
                    areaName, rect,
                    spotName, point,
                    MemorySegment.NULL,
                )
            }
        } catch (_: Throwable) {}
    }

    override fun setImePurpose(purpose: ImePurpose) {
        // No-op on X11: XIM has no concept of IME purpose hints.
    }

    internal fun drainImeEvents(handler: ApplicationHandler, loop: ActiveEventLoop, windowId: WindowId) {
        while (true) {
            val event = pendingImeEvents.poll() ?: break
            handler.windowEvent(loop, windowId, event)
        }
    }

    private fun allocateXIMCallback(arena: Arena, clientData: MemorySegment, callbackProc: MemorySegment): MemorySegment {
        val cb = arena.allocate(XIM_CALLBACK_SIZE, 8L)
        cb.set(ValueLayout.ADDRESS, XIM_CALLBACK_CLIENT_DATA_OFFSET, clientData)
        cb.set(ValueLayout.ADDRESS, XIM_CALLBACK_PROC_OFFSET, callbackProc)
        return cb
    }

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

    private fun sendNetActiveWindow() {
        val sendEvent = xSendEvent ?: return
        val rootHandle = xRootWindow ?: return
        val display = MemorySegment.ofAddress(displayPtr)
        val activeWindowAtom = internAtom(displayPtr, "_NET_ACTIVE_WINDOW")
        if (activeWindowAtom == 0L) return
        try {
            Arena.ofConfined().use { arena ->
                val root = rootHandle.invokeExact(display, screen) as Long
                if (root == 0L) return@use
                val eventBuf = arena.allocate(96L, 8L)
                eventBuf.set(ValueLayout.JAVA_INT, 0L, ClientMessage)
                eventBuf.set(ValueLayout.JAVA_LONG, 32L, xWindowId)
                eventBuf.set(ValueLayout.JAVA_LONG, 40L, activeWindowAtom)
                eventBuf.set(ValueLayout.JAVA_INT, 48L, 32)
                eventBuf.set(ValueLayout.JAVA_LONG, 56L, 1L)
                eventBuf.set(ValueLayout.JAVA_LONG, 64L, 0L)
                eventBuf.set(ValueLayout.JAVA_LONG, 72L, 0L)
                eventBuf.set(ValueLayout.JAVA_LONG, 80L, 0L)
                eventBuf.set(ValueLayout.JAVA_LONG, 88L, 0L)

                val mask = SubstructureRedirectMask or SubstructureNotifyMask
                sendEvent.invokeExact(display, root, 0, mask, eventBuf) as Int
                val flush = xFlush
                if (flush != null) flush.invokeExact(display) as Int
            }
        } catch (_: Throwable) {}
    }

    /**
     * Sends the EWMH _NET_WM_MOVERESIZE ClientMessage to the root window.
     *
     * Data layout:
     * - data.l[0]: root x
     * - data.l[1]: root y
     * - data.l[2]: direction/action
     * - data.l[3]: button (1 = left mouse button)
     * - data.l[4]: source indication (1 = normal application)
     */
    private fun sendNetWmMoveResize(action: Long): WindowRequestResult =
        try {
            val queryPointer = xQueryPointer ?: return WindowRequestResult.Failure(
                RequestError.Unsupported("XQueryPointer is unavailable"),
            )
            val ungrabPointer = xUngrabPointer ?: return WindowRequestResult.Failure(
                RequestError.Unsupported("XUngrabPointer is unavailable"),
            )
            val sendEvent = xSendEvent ?: return WindowRequestResult.Failure(
                RequestError.Unsupported("XSendEvent is unavailable"),
            )
            val rootHandle = xRootWindow ?: return WindowRequestResult.Failure(
                RequestError.Unsupported("XRootWindow is unavailable"),
            )
            val wmMoveResizeAtom = internAtom(displayPtr, "_NET_WM_MOVERESIZE")
            if (wmMoveResizeAtom == 0L) {
                return WindowRequestResult.Failure(RequestError.Unsupported("_NET_WM_MOVERESIZE is unavailable"))
            }

            val display = MemorySegment.ofAddress(displayPtr)
            Arena.ofConfined().use { arena ->
                val rootOut = arena.allocate(ValueLayout.JAVA_LONG)
                val childOut = arena.allocate(ValueLayout.JAVA_LONG)
                val rootXOut = arena.allocate(ValueLayout.JAVA_INT)
                val rootYOut = arena.allocate(ValueLayout.JAVA_INT)
                val winXOut = arena.allocate(ValueLayout.JAVA_INT)
                val winYOut = arena.allocate(ValueLayout.JAVA_INT)
                val maskOut = arena.allocate(ValueLayout.JAVA_INT)

                val pointerOk = queryPointer.invokeExact(
                    display,
                    xWindowId,
                    rootOut,
                    childOut,
                    rootXOut,
                    rootYOut,
                    winXOut,
                    winYOut,
                    maskOut,
                ) as Int
                if (pointerOk == 0) {
                    return WindowRequestResult.Failure(
                        RequestError.OsError("XQueryPointer did not return a pointer position for this window"),
                    )
                }

                // Match winit: release any pointer grab before asking the WM to move/resize.
                ungrabPointer.invokeExact(display, 0L) as Int
                xFlush?.invokeExact(display) as? Int

                val root: Long = rootHandle.invokeExact(display, screen) as Long
                if (root == 0L) {
                    return WindowRequestResult.Failure(RequestError.OsError("XRootWindow returned 0"))
                }

                val eventBuf = arena.allocate(96L, 8L)
                eventBuf.set(ValueLayout.JAVA_INT, 0L, ClientMessage)
                eventBuf.set(ValueLayout.JAVA_LONG, 32L, xWindowId)
                eventBuf.set(ValueLayout.JAVA_LONG, 40L, wmMoveResizeAtom)
                eventBuf.set(ValueLayout.JAVA_INT, 48L, 32)
                eventBuf.set(ValueLayout.JAVA_LONG, 56L, rootXOut.get(ValueLayout.JAVA_INT, 0L).toLong())
                eventBuf.set(ValueLayout.JAVA_LONG, 64L, rootYOut.get(ValueLayout.JAVA_INT, 0L).toLong())
                eventBuf.set(ValueLayout.JAVA_LONG, 72L, action)
                eventBuf.set(ValueLayout.JAVA_LONG, 80L, 1L)
                eventBuf.set(ValueLayout.JAVA_LONG, 88L, 1L)

                val mask = SubstructureRedirectMask or SubstructureNotifyMask
                val status = sendEvent.invokeExact(display, root, 0, mask, eventBuf) as Int
                if (status == 0) {
                    return WindowRequestResult.Failure(
                        RequestError.OsError("XSendEvent(_NET_WM_MOVERESIZE) failed"),
                    )
                }
                xFlush?.invokeExact(display) as? Int
                WindowRequestResult.Success
            }
        } catch (t: Throwable) {
            WindowRequestResult.Failure(RequestError.OsError(t.message ?: t::class.simpleName ?: "X11 move/resize request failed"))
        }

    private fun writeX11Title(title: String) {
        val changeProperty = xChangeProperty ?: return
        val display = MemorySegment.ofAddress(displayPtr)
        val wmNameAtom = internAtom(displayPtr, "WM_NAME")
        val stringAtom = internAtom(displayPtr, "STRING")
        val netWmNameAtom = internAtom(displayPtr, "_NET_WM_NAME")
        val utf8StringAtom = internAtom(displayPtr, "UTF8_STRING")
        if (wmNameAtom == 0L || stringAtom == 0L || netWmNameAtom == 0L || utf8StringAtom == 0L) return
        val titleBytes = x11TitlePropertyBytes(title)
        try {
            Arena.ofConfined().use { arena ->
                val data = arena.allocate(maxOf(titleBytes.size, 1).toLong(), 1L)
                for (index in titleBytes.indices) {
                    data.set(ValueLayout.JAVA_BYTE, index.toLong(), titleBytes[index])
                }
                changeProperty.invokeExact(
                    display,
                    xWindowId,
                    wmNameAtom,
                    stringAtom,
                    8,
                    0,
                    data,
                    titleBytes.size,
                ) as Int
                changeProperty.invokeExact(
                    display,
                    xWindowId,
                    netWmNameAtom,
                    utf8StringAtom,
                    8,
                    0,
                    data,
                    titleBytes.size,
                ) as Int
                val flush = xFlush
                if (flush != null) flush.invokeExact(display) as Int
            }
        } catch (_: Throwable) {}
    }

    // ── X11 helper: set/unset _MOTIF_WM_HINTS ────────────────────────────────

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
        setMotifHints { existing -> x11MotifDecorationHints(decorated, existing) }
    }

    private fun setMotifMaximizable(maximizable: Boolean) {
        setMotifHints { existing -> x11MotifMaximizableHints(maximizable, existing) }
    }

    private fun setMotifHints(update: (LongArray?) -> LongArray) {
        val display = MemorySegment.ofAddress(displayPtr)
        val motifAtom = internAtom(displayPtr, "_MOTIF_WM_HINTS")
        if (motifAtom == 0L) return
        try {
            val updatedHints = update(readMotifHints(display, motifAtom))
            Arena.ofConfined().use { arena ->
                // format 32 → array of C long on LP64: 5 × 8 = 40 bytes.
                val hints = arena.allocate(40L, 8L)
                updatedHints.forEachIndexed { index, value ->
                    hints.setAtIndex(ValueLayout.JAVA_LONG, index.toLong(), value)
                }
                xChangeProperty?.invokeExact(
                    display, xWindowId,
                    motifAtom, motifAtom,
                    32, 0 /* PropModeReplace */,
                    hints, 5,
                ) as Int
                xFlush?.invokeExact(display) as Int
            }
        } catch (_: Throwable) {}
    }

    private fun readMotifHints(display: MemorySegment, motifAtom: Long): LongArray? {
        val getProperty = xGetWindowProperty ?: return null
        return readX11Property(getProperty, display, xWindowId, motifAtom, reqType = motifAtom, length = X11_MOTIF_HINTS_ELEMENTS.toLong()) { ptr, nitems ->
            if (nitems <= 0L) return@readX11Property null
            LongArray(X11_MOTIF_HINTS_ELEMENTS) { index ->
                if (index.toLong() < nitems) ptr.getAtIndex(ValueLayout.JAVA_LONG, index.toLong()) else 0L
            }
        }
    }

    private fun readNetWmStateContains(atom: Long): Boolean? =
        if (atom == 0L) null else readNetWmStateAtoms()?.let { x11WindowStateContains(it, atom) }

    private fun readNetWmStateContainsAll(first: Long, second: Long): Boolean? =
        if (first == 0L || second == 0L) null else readNetWmStateAtoms()?.let { atoms ->
            x11WindowStateContains(atoms, first) && x11WindowStateContains(atoms, second)
        }

    private fun readNetWmStateAtoms(): LongArray? {
        val getProperty = xGetWindowProperty ?: return null
        val stateAtom = internAtom(displayPtr, "_NET_WM_STATE")
        val atomType = internAtom(displayPtr, "ATOM")
        if (stateAtom == 0L || atomType == 0L) return null
        val display = MemorySegment.ofAddress(displayPtr)
        return readX11Property(getProperty, display, xWindowId, stateAtom, reqType = atomType, length = 1024L) { ptr, nitems ->
            if (nitems <= 0L) return@readX11Property LongArray(0)
            LongArray(nitems.toInt()) { index -> ptr.getAtIndex(ValueLayout.JAVA_LONG, index.toLong()) }
        }
    }

    private fun readWmStateIconic(): Boolean? {
        val getProperty = xGetWindowProperty ?: return null
        val wmStateAtom = internAtom(displayPtr, "WM_STATE")
        val card32Atom = internAtom(displayPtr, "CARD32")
        if (wmStateAtom == 0L || card32Atom == 0L) return null
        val display = MemorySegment.ofAddress(displayPtr)
        return readX11Property(getProperty, display, xWindowId, wmStateAtom, reqType = card32Atom, length = 2L) { ptr, nitems ->
            if (nitems <= 0L) return@readX11Property null
            ptr.getAtIndex(ValueLayout.JAVA_LONG, 0L) == X11_ICONIC_STATE
        }
    }

    private fun readFrameExtents(): X11FrameExtents? {
        val getProperty = xGetWindowProperty ?: return null
        val frameExtentsAtom = internAtom(displayPtr, "_NET_FRAME_EXTENTS")
        if (frameExtentsAtom == 0L) return null
        val cardinalAtom = internAtom(displayPtr, "CARDINAL")
        val display = MemorySegment.ofAddress(displayPtr)
        return readX11Property(
            getProperty = getProperty,
            display = display,
            window = xWindowId,
            property = frameExtentsAtom,
            reqType = cardinalAtom,
            length = 4L,
        ) { ptr, nitems ->
            if (nitems < 4L) return@readX11Property null
            X11FrameExtents(
                left = ptr.getAtIndex(ValueLayout.JAVA_LONG, 0L).toInt(),
                right = ptr.getAtIndex(ValueLayout.JAVA_LONG, 1L).toInt(),
                top = ptr.getAtIndex(ValueLayout.JAVA_LONG, 2L).toInt(),
                bottom = ptr.getAtIndex(ValueLayout.JAVA_LONG, 3L).toInt(),
            )
        }
    }

    private fun readSurfaceSize(): PhysicalSize<Int>? {
        val getGeometry = xGetGeometry ?: return null
        val display = MemorySegment.ofAddress(displayPtr)
        return try {
            Arena.ofConfined().use { arena ->
                val rootOut = arena.allocate(ValueLayout.JAVA_LONG)
                val xOut = arena.allocate(ValueLayout.JAVA_INT)
                val yOut = arena.allocate(ValueLayout.JAVA_INT)
                val widthOut = arena.allocate(ValueLayout.JAVA_INT)
                val heightOut = arena.allocate(ValueLayout.JAVA_INT)
                val borderWidthOut = arena.allocate(ValueLayout.JAVA_INT)
                val depthOut = arena.allocate(ValueLayout.JAVA_INT)
                val ok = getGeometry.invokeExact(
                    display,
                    xWindowId,
                    rootOut,
                    xOut,
                    yOut,
                    widthOut,
                    heightOut,
                    borderWidthOut,
                    depthOut,
                ) as Int
                if (ok == 0) return@use null
                val size = x11ValidSurfaceSize(
                    PhysicalSize(
                        width = widthOut.get(ValueLayout.JAVA_INT, 0L),
                        height = heightOut.get(ValueLayout.JAVA_INT, 0L),
                    )
                )
                _innerSize = size
                size
            }
        } catch (_: Throwable) {
            null
        }
    }

    /**
     * Updates the inner size upon receiving a ConfigureNotify event.
     *
     * @param width  New width in pixels.
     * @param height New height in pixels.
     */
    internal fun onConfigureNotify(width: Int, height: Int) {
        onConfigureNotify(width, height, position = null, positionIsRootRelative = false)
    }

    internal fun onConfigureNotify(
        width: Int,
        height: Int,
        position: PhysicalPosition<Int>?,
        positionIsRootRelative: Boolean,
    ): X11ConfigureChanges {
        val extents = _frameExtents ?: readFrameExtents()?.also { _frameExtents = it }
        val outerPosition = if (positionIsRootRelative && position != null && extents != null) {
            extents.innerToOuter(position)
        } else {
            null
        }
        val changes = x11ConfigureChanges(_innerSize, _outerPosition, width, height, outerPosition, outerPosition != null)
        val resized = width > 0 && height > 0
        if (resized) {
            _innerSize = PhysicalSize(width, height)
        }
        if (outerPosition != null) {
            _outerPosition = outerPosition
        }
        if (x11ShouldReapplyCursorHittestAfterConfigure(_cursorHittest, resized)) {
            setCursorHittest(true)
        }
        return changes
    }

    fun onFocusChanged(focused: Boolean): Boolean {
        if (_hasFocus == focused) return false
        _hasFocus = focused
        if (xic.address() != 0L) {
            try {
                if (focused) {
                    xSetICFocus?.invokeExact(xic)
                } else {
                    xUnsetICFocus?.invokeExact(xic)
                }
            } catch (_: Throwable) {}
        }
        return true
    }

    fun onVisibilityNotify() {
        when (_visibilityState) {
            X11_VISIBILITY_NO -> {
                val unmapWindow = xUnmapWindow ?: return
                val display = MemorySegment.ofAddress(displayPtr)
                try {
                    unmapWindow.invokeExact(display, xWindowId) as Int
                    val flush = xFlush
                    if (flush != null) flush.invokeExact(display) as Int
                } catch (_: Throwable) {}
            }
            X11_VISIBILITY_YES_WAIT -> {
                _visibilityState = x11VisibilityAfterNotify(_visibilityState)
                if (_desiredFullscreenPending) {
                    val desiredFullscreen = _desiredFullscreen
                    _desiredFullscreenPending = false
                    _desiredFullscreen = null
                    setFullscreen(desiredFullscreen)
                }
            }
            X11_VISIBILITY_YES -> {}
        }
    }

    // ── R3 helpers ────────────────────────────────────────────────────────────

    /** Maps a [CursorIcon] to the X11 cursorfont shape constant. */
    private fun cursorToXShape(cursor: CursorIcon): Int = when (cursor) {
        CursorIcon.Default        -> XC_left_ptr
        CursorIcon.Pointer        -> XC_hand2
        CursorIcon.Text           -> XC_xterm
        CursorIcon.Crosshair      -> XC_crosshair
        CursorIcon.Move           -> XC_fleur
        CursorIcon.ResizeNorth    -> XC_top_side
        CursorIcon.ResizeSouth    -> XC_bottom_side
        CursorIcon.ResizeEast     -> XC_right_side
        CursorIcon.ResizeWest     -> XC_left_side
        CursorIcon.ResizeNorthEast -> XC_top_right_corner
        CursorIcon.ResizeNorthWest -> XC_top_left_corner
        CursorIcon.ResizeSouthEast -> XC_bottom_right_corner
        CursorIcon.ResizeSouthWest -> XC_bottom_left_corner
        CursorIcon.NotAllowed     -> XC_X_cursor
        CursorIcon.Grab           -> XC_hand1
        CursorIcon.Grabbing       -> XC_fleur
        CursorIcon.Wait,
        CursorIcon.Progress       -> XC_watch
        CursorIcon.EwResize,
        CursorIcon.ColResize      -> XC_sb_h_double_arrow
        CursorIcon.NsResize,
        CursorIcon.RowResize      -> XC_sb_v_double_arrow
        CursorIcon.NeswResize     -> XC_top_right_corner
        CursorIcon.NwseResize     -> XC_top_left_corner
    CursorIcon.AllScroll      -> XC_fleur
    CursorIcon.ZoomIn,
    CursorIcon.ZoomOut        -> XC_crosshair
    CursorIcon.Copy,
    CursorIcon.Alias          -> XC_left_ptr          // No standard X11 shape
    CursorIcon.ContextMenu    -> XC_left_ptr          // No standard X11 shape
    CursorIcon.Cell           -> XC_plus
    CursorIcon.NoDrop         -> XC_X_cursor
    CursorIcon.Help           -> XC_question_arrow
    CursorIcon.Hidden         -> XC_left_ptr          // Arrow placeholder; invisible shape
    CursorIcon.NoneReset      -> XC_left_ptr          // Reset to default
    CursorIcon.WaitCursor     -> XC_watch             // Same as Wait
    CursorIcon.VerticalText   -> XC_xterm             // No vertical variant on X11
    }

    // ── Companion ─────────────────────────────────────────────────────────────

    companion object {

        // ── XIM global state ───────────────────────────────────────────────────

        internal val activeWindows = ConcurrentHashMap<Long, X11Window>()

        private var displayXIM: MemorySegment = MemorySegment.NULL
        private var ximRefCount: Int = 0

        @Synchronized
        internal fun acquireXIM(displayPtr: Long): MemorySegment {
            if (displayXIM.address() == 0L) {
                val openHandle = xOpenIM ?: return MemorySegment.NULL
                val display = MemorySegment.ofAddress(displayPtr)
                try {
                    displayXIM = openHandle.invokeExact(
                        display,
                        MemorySegment.NULL,
                        MemorySegment.NULL,
                        MemorySegment.NULL,
                    ) as MemorySegment
                } catch (_: Throwable) {
                    return MemorySegment.NULL
                }
            }
            if (displayXIM.address() != 0L) ximRefCount++
            return displayXIM
        }

        @Synchronized
        internal fun releaseXIM() {
            if (ximRefCount > 0) ximRefCount--
            if (ximRefCount == 0 && displayXIM.address() != 0L) {
                try {
                    xCloseIM?.invokeExact(displayXIM) as? Int
                } catch (_: Throwable) {}
                displayXIM = MemorySegment.NULL
            }
        }

        // ── XIMProc upcall stubs ──────────────────────────────────────────────

        private val imLinker: Linker = Linker.nativeLinker()

        internal val preeditStartUpcall: MemorySegment by lazy {
            createXimUpcall("onPreeditStart")
        }
        internal val preeditDrawUpcall: MemorySegment by lazy {
            createXimUpcall("onPreeditDraw")
        }
        internal val preeditDoneUpcall: MemorySegment by lazy {
            createXimUpcall("onPreeditDone")
        }
        internal val commitUpcall: MemorySegment by lazy {
            createXimUpcall("onCommit")
        }

        private fun createXimUpcall(methodName: String): MemorySegment {
            return try {
                val handle = MethodHandles.lookup().findStatic(
                    X11Window::class.java,
                    methodName,
                    MethodType.methodType(Void.TYPE, MemorySegment::class.java, MemorySegment::class.java, MemorySegment::class.java),
                )
                imLinker.upcallStub(handle, XIM_PROC_DESCRIPTOR, Arena.global())
            } catch (_: Throwable) {
                MemorySegment.NULL
            }
        }

        @JvmStatic private fun onPreeditStart(im: MemorySegment, clientData: MemorySegment, callData: MemorySegment) {
            if (clientData == MemorySegment.NULL) return
            val xid = clientData.get(ValueLayout.JAVA_LONG, 0L)
            val window = activeWindows[xid] ?: return
            if (callData != MemorySegment.NULL) {
                callData.set(ValueLayout.JAVA_SHORT, PRESTATE_COUNT_OFFSET, 100)
            }
            window.pendingImeEvents.add(WindowEvent.Ime(WindowEvent.Ime.ImeEvent.Enabled))
        }

        @JvmStatic private fun onPreeditDraw(im: MemorySegment, clientData: MemorySegment, callData: MemorySegment) {
            if (clientData == MemorySegment.NULL) return
            val xid = clientData.get(ValueLayout.JAVA_LONG, 0L)
            val window = activeWindows[xid] ?: return
            if (callData == MemorySegment.NULL) return

            val caret = callData.get(ValueLayout.JAVA_INT, PREDRAW_CARET_OFFSET)
            val textPtr = callData.get(ValueLayout.ADDRESS, PREDRAW_TEXT_PTR_OFFSET)
            val text = readXIMTextContent(textPtr)

            window.pendingImeEvents.add(WindowEvent.Ime(
                WindowEvent.Ime.ImeEvent.Preedit(text = text, cursorRange = Pair(caret, caret)),
            ))
        }

        @JvmStatic private fun onPreeditDone(im: MemorySegment, clientData: MemorySegment, callData: MemorySegment) {
            if (clientData == MemorySegment.NULL) return
            val xid = clientData.get(ValueLayout.JAVA_LONG, 0L)
            activeWindows[xid]?.pendingImeEvents?.add(WindowEvent.Ime(WindowEvent.Ime.ImeEvent.Disabled))
        }

        @JvmStatic private fun onCommit(im: MemorySegment, clientData: MemorySegment, callData: MemorySegment) {
            if (clientData == MemorySegment.NULL) return
            val xid = clientData.get(ValueLayout.JAVA_LONG, 0L)
            val window = activeWindows[xid] ?: return
            val text = if (callData != MemorySegment.NULL) readXIMTextContent(callData) else ""
            window.pendingImeEvents.add(WindowEvent.Ime(WindowEvent.Ime.ImeEvent.Commit(text)))
        }

        private fun readXIMTextContent(text: MemorySegment): String {
            if (text == MemorySegment.NULL) return ""
            val length = text.get(ValueLayout.JAVA_SHORT, XIMTEXT_LENGTH_OFFSET).toInt() and 0xFFFF
            val isWchar = text.get(ValueLayout.JAVA_INT, XIMTEXT_ENCODING_IS_WCHAR_OFFSET) != 0
            val stringPtr = text.get(ValueLayout.ADDRESS, XIMTEXT_STRING_PTR_OFFSET)
            if (stringPtr == MemorySegment.NULL || length == 0) return ""
            return if (isWchar) {
                val codePoints = IntArray(length) { i ->
                    stringPtr.get(ValueLayout.JAVA_INT, (i * 4).toLong())
                }
                String(codePoints, 0, length)
            } else {
                val bytes = ByteArray(length) { i ->
                    stringPtr.get(ValueLayout.JAVA_BYTE, i.toLong())
                }
                bytes.toString(Charsets.UTF_8)
            }
        }

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
            val position = x11InitialPosition(attrs.position)

            // ── 2. XCreateSimpleWindow ────────────────────────────────────────
            val xWindowId: Long = createHandle.invokeExact(
                displaySeg,     // Display*
                rootWindow,     // Window parent
                position.x,      // int x
                position.y,      // int y
                width,          // unsigned int width
                height,         // unsigned int height
                1,              // unsigned int border_width
                0L,             // unsigned long border (BlackPixel = 0)
                0L,             // unsigned long background (BlackPixel = 0)
            ) as Long

            if (xWindowId == 0L) return null

            // ── 3. XSelectInput ───────────────────────────────────────────────
            val selectInput = xSelectInput ?: return null
            selectInput.invokeExact(displaySeg, xWindowId, FULL_EVENT_MASK) as Int

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

            val window = X11Window(display, screen, xWindowId, attrs)
            window.writeX11Title(attrs.title)
            attrs.preferredTheme?.let(window::setTheme)
            window.applyNormalHints()
            window.setMotifDecorations(attrs.decorations)
            window.applySelectedCursor()

            // ── 6. XMapWindow (if visible) ────────────────────────────────────
            if (attrs.visible) {
                val mapWindow = xMapWindow ?: return null
                mapWindow.invokeExact(displaySeg, xWindowId) as Int
                val raiseWindow = xRaiseWindow
                if (raiseWindow != null) raiseWindow.invokeExact(displaySeg, xWindowId) as Int
                val flush = xFlush
                if (flush != null) flush.invokeExact(displaySeg) as Int
            }

            // ── 7. XdndAware (drag-and-drop support) ──────────────────────────
            try {
                val xdndAwareAtom = x11DragAndDropAtom(displaySeg, "XdndAware")
                val atomAtom = x11DragAndDropAtom(displaySeg, "ATOM")
                if (xdndAwareAtom != 0L && atomAtom != 0L) {
                    Arena.ofConfined().use { a ->
                        val data = a.allocate(ValueLayout.JAVA_LONG, 1L)
                        data.set(ValueLayout.JAVA_LONG, 0L, 5L)
                        xChangeProperty?.invokeExact(
                            displaySeg, xWindowId,
                            xdndAwareAtom, atomAtom,
                            32, 0, data, 1,
                        ) as? Int
                    }
                }
            } catch (_: Throwable) {}

            // ── 8. Apply initial fullscreen from attrs ────────────────────────
            if (attrs.fullscreen != null) {
                window.setFullscreen(attrs.fullscreen)
            }

            // winit applies _NET_WM_STATE_ABOVE/_BELOW after initial mapping/fullscreen.
            window.setWindowLevel(attrs.windowLevel)

            return window
        }
    }

    private fun applyNormalHints(sizeOverride: PhysicalSize<Int>? = null) {
        val wmNormalHints = internAtom(displayPtr, "WM_NORMAL_HINTS")
        val wmSizeHints = internAtom(displayPtr, "WM_SIZE_HINTS")
        if (wmNormalHints == 0L || wmSizeHints == 0L) return
        val hints = x11NormalHints(
            position = _initialPosition,
            size = sizeOverride ?: _innerSize,
            minSize = _minSurfaceSize,
            maxSize = _maxSurfaceSize,
            resizeIncrements = _surfaceResizeIncrements,
            resizable = _isResizable,
            avoidNonResizablePin = isXfwm4WindowManager(),
        )
        val display = MemorySegment.ofAddress(displayPtr)
        try {
            Arena.ofConfined().use { arena ->
                val data = arena.allocate(X11_NORMAL_HINTS_ELEMENTS * 8L, 8L)
                hints.elements.forEachIndexed { index, value ->
                    data.setAtIndex(ValueLayout.JAVA_LONG, index.toLong(), value)
                }
                xChangeProperty?.invokeExact(
                    display,
                    xWindowId,
                    wmNormalHints,
                    wmSizeHints,
                    32,
                    0 /* PropModeReplace */,
                    data,
                    X11_NORMAL_HINTS_ELEMENTS,
                ) as? Int
                xFlush?.invokeExact(display) as? Int
            }
        } catch (_: Throwable) {}
    }

    private fun isXfwm4WindowManager(): Boolean =
        currentX11WindowManagerName(displayPtr, screen) == "Xfwm4"
}

internal const val X11_NORMAL_HINTS_ELEMENTS: Int = 18
internal const val X11_MOTIF_HINTS_ELEMENTS: Int = 5
internal const val X11_MWM_HINTS_FUNCTIONS: Long = 1L shl 0
internal const val X11_MWM_HINTS_DECORATIONS: Long = 1L shl 1
internal const val X11_MWM_FUNC_ALL: Long = 1L shl 0
internal const val X11_MWM_FUNC_MAXIMIZE: Long = 1L shl 4
internal const val X11_ICONIC_STATE: Long = 3L
internal const val X11_VISIBILITY_NO: Int = 0
internal const val X11_VISIBILITY_YES_WAIT: Int = 1
internal const val X11_VISIBILITY_YES: Int = 2
internal const val X11_COLOR_SIZE_BYTES: Long = 16L
internal const val X11_COLOR_ALIGN_BYTES: Long = 8L
internal const val X11_WM_HINTS_SIZE_BYTES: Long = 56L
internal const val X11_US_POSITION: Long = 1L shl 0
internal const val X11_US_SIZE: Long = 1L shl 1
internal const val X11_P_MIN_SIZE: Long = 1L shl 4
internal const val X11_P_MAX_SIZE: Long = 1L shl 5
internal const val X11_P_RESIZE_INC: Long = 1L shl 6

internal data class X11NormalHints(
    val elements: LongArray,
) {
    override fun equals(other: Any?): Boolean =
        other is X11NormalHints && elements.contentEquals(other.elements)

    override fun hashCode(): Int =
        elements.contentHashCode()
}

internal data class X11ConfigureChanges(
    val sizeChanged: Boolean,
    val movedPosition: PhysicalPosition<Int>?,
)

internal data class X11FrameExtents(
    val left: Int,
    val right: Int,
    val top: Int,
    val bottom: Int,
) {
    val surfacePosition: PhysicalPosition<Int>
        get() = PhysicalPosition(left, top)

    fun innerToOuter(position: PhysicalPosition<Int>): PhysicalPosition<Int> =
        PhysicalPosition(position.x - left, position.y - top)

    fun surfaceSizeToOuter(size: PhysicalSize<Int>): PhysicalSize<Int> =
        PhysicalSize(
            width = size.width + left + right,
            height = size.height + top + bottom,
        )
}

internal fun x11ValidSurfaceSize(size: PhysicalSize<Int>): PhysicalSize<Int> =
    PhysicalSize(
        width = size.width.coerceAtLeast(1),
        height = size.height.coerceAtLeast(1),
    )

internal data class X11ShapeRectangle(
    val x: Int,
    val y: Int,
    val width: Int,
    val height: Int,
)

internal fun x11CursorHittestRectangles(
    hittest: Boolean,
    surfaceSize: PhysicalSize<Int>,
): List<X11ShapeRectangle> =
    if (hittest) {
        listOf(
            X11ShapeRectangle(
                x = 0,
                y = 0,
                width = surfaceSize.width.coerceIn(1, UShort.MAX_VALUE.toInt()),
                height = surfaceSize.height.coerceIn(1, UShort.MAX_VALUE.toInt()),
            )
        )
    } else {
        emptyList()
    }

internal fun x11ShouldReapplyCursorHittestAfterConfigure(
    cursorHittest: Boolean?,
    resized: Boolean,
): Boolean =
    cursorHittest == true && resized

internal fun x11WmHintsUrgencyFlags(flags: Long, urgent: Boolean): Long =
    if (urgent) {
        flags or X11_WM_HINTS_URGENCY_FLAG
    } else {
        flags and X11_WM_HINTS_URGENCY_FLAG.inv()
    }

internal fun x11ConfigureChanges(
    currentSize: PhysicalSize<Int>,
    currentPosition: PhysicalPosition<Int>,
    width: Int,
    height: Int,
    position: PhysicalPosition<Int>?,
    positionIsRootRelative: Boolean,
): X11ConfigureChanges =
    X11ConfigureChanges(
        sizeChanged = width > 0 && height > 0 && currentSize != PhysicalSize(width, height),
        movedPosition = if (positionIsRootRelative && position != null && currentPosition != position) position else null,
    )

internal fun x11MotifDecorationHints(decorated: Boolean, existing: LongArray? = null): LongArray =
    x11MotifHints(existing).also { hints ->
        hints[0] = hints[0] or X11_MWM_HINTS_DECORATIONS
        hints[2] = if (decorated) 1L else 0L
    }

internal fun x11MotifMaximizableHints(maximizable: Boolean, existing: LongArray? = null): LongArray =
    x11MotifHints(existing).also { hints ->
        if (maximizable) {
            if (hints[0] and X11_MWM_HINTS_FUNCTIONS != 0L) {
                if (hints[1] and X11_MWM_FUNC_ALL != 0L) {
                    hints[1] = hints[1] and X11_MWM_FUNC_MAXIMIZE.inv()
                } else {
                    hints[1] = hints[1] or X11_MWM_FUNC_MAXIMIZE
                }
            }
        } else {
            if (hints[0] and X11_MWM_HINTS_FUNCTIONS == 0L) {
                hints[0] = hints[0] or X11_MWM_HINTS_FUNCTIONS
                hints[1] = X11_MWM_FUNC_ALL
            }
            if (hints[1] and X11_MWM_FUNC_ALL != 0L) {
                hints[1] = hints[1] or X11_MWM_FUNC_MAXIMIZE
            } else {
                hints[1] = hints[1] and X11_MWM_FUNC_MAXIMIZE.inv()
            }
        }
    }

private fun x11MotifHints(existing: LongArray?): LongArray =
    LongArray(X11_MOTIF_HINTS_ELEMENTS) { index -> existing?.getOrNull(index) ?: 0L }

internal fun x11TitlePropertyBytes(title: String): ByteArray =
    title.toByteArray(Charsets.UTF_8)

internal fun x11CursorChangeRequiresApply(
    previous: CursorIcon,
    next: CursorIcon,
    visible: Boolean,
): Boolean =
    visible && previous != next

internal fun x11WindowStateContains(atoms: LongArray, atom: Long): Boolean =
    atom != 0L && atoms.any { it == atom }

internal fun x11FocusRequestAllowed(visible: Boolean, minimized: Boolean): Boolean =
    visible && !minimized

internal fun x11VisibilityAfterSet(current: Int, visible: Boolean): Int =
    if (visible) {
        if (current == X11_VISIBILITY_NO) X11_VISIBILITY_YES_WAIT else current
    } else {
        X11_VISIBILITY_NO
    }

internal fun x11VisibilityAfterNotify(current: Int): Int =
    if (current == X11_VISIBILITY_YES_WAIT) X11_VISIBILITY_YES else current

internal fun x11VisibilityIsVisible(state: Int): Boolean =
    state == X11_VISIBILITY_YES

internal data class X11FullscreenRequest(
    val defer: Boolean,
    val send: Boolean,
)

internal fun x11FullscreenRequest(
    current: Fullscreen?,
    requested: Fullscreen?,
    visibilityState: Int,
): X11FullscreenRequest =
    if (visibilityState != X11_VISIBILITY_YES) {
        X11FullscreenRequest(defer = true, send = false)
    } else {
        X11FullscreenRequest(defer = false, send = current != requested)
    }

internal fun x11NormalHints(
    position: PhysicalPosition<Int>?,
    size: PhysicalSize<Int>,
    minSize: PhysicalSize<Int>?,
    maxSize: PhysicalSize<Int>?,
    resizeIncrements: PhysicalSize<Int>?,
    resizable: Boolean,
    avoidNonResizablePin: Boolean = false,
): X11NormalHints {
    val elements = LongArray(X11_NORMAL_HINTS_ELEMENTS)
    var flags = X11_US_SIZE
    elements[3] = size.width.toLong()
    elements[4] = size.height.toLong()

    if (position != null) {
        flags = flags or X11_US_POSITION
        elements[1] = position.x.toLong()
        elements[2] = position.y.toLong()
    }

    val pinToCurrentSize = !resizable && !avoidNonResizablePin
    val effectiveMin = if (pinToCurrentSize) size else minSize
    val effectiveMax = if (pinToCurrentSize) size else maxSize
    if (effectiveMin != null) {
        flags = flags or X11_P_MIN_SIZE
        elements[5] = effectiveMin.width.toLong()
        elements[6] = effectiveMin.height.toLong()
    }
    if (effectiveMax != null) {
        flags = flags or X11_P_MAX_SIZE
        elements[7] = effectiveMax.width.toLong()
        elements[8] = effectiveMax.height.toLong()
    }
    if (resizeIncrements != null) {
        flags = flags or X11_P_RESIZE_INC
        elements[9] = resizeIncrements.width.toLong()
        elements[10] = resizeIncrements.height.toLong()
    }

    elements[0] = flags
    return X11NormalHints(elements)
}

internal fun currentX11WindowManagerName(displayPtr: Long, screen: Int): String? {
    val getProperty = xGetWindowProperty ?: return null
    val display = MemorySegment.ofAddress(displayPtr)
    val root = try {
        xRootWindow?.invokeExact(display, screen) as? Long ?: return null
    } catch (_: Throwable) {
        return null
    }
    val supportingWmCheck = x11InternAtom(displayPtr, "_NET_SUPPORTING_WM_CHECK")
    val wmName = x11InternAtom(displayPtr, "_NET_WM_NAME")
    val utf8String = x11InternAtom(displayPtr, "UTF8_STRING")
    if (supportingWmCheck == 0L || wmName == 0L || utf8String == 0L) return null
    val wmWindow = readX11WindowPropertyLong(getProperty, display, root, supportingWmCheck) ?: return null
    return readX11StringProperty(getProperty, display, wmWindow, wmName, utf8String)
}

private fun readX11WindowPropertyLong(
    getProperty: java.lang.invoke.MethodHandle,
    display: MemorySegment,
    window: Long,
    property: Long,
): Long? =
    readX11Property(getProperty, display, window, property, reqType = 0L, length = 1L) { ptr, nitems ->
        if (nitems <= 0L) null else ptr.getAtIndex(ValueLayout.JAVA_LONG, 0L)
    }

private fun readX11StringProperty(
    getProperty: java.lang.invoke.MethodHandle,
    display: MemorySegment,
    window: Long,
    property: Long,
    reqType: Long,
): String? =
    readX11Property(getProperty, display, window, property, reqType, length = 1024L) { ptr, nitems ->
        if (nitems <= 0L) return@readX11Property null
        val bytes = ByteArray(nitems.toInt())
        for (index in bytes.indices) {
            bytes[index] = ptr.getAtIndex(ValueLayout.JAVA_BYTE, index.toLong())
        }
        bytes.toString(Charsets.UTF_8).trimEnd('\u0000')
    }

private inline fun <T> readX11Property(
    getProperty: java.lang.invoke.MethodHandle,
    display: MemorySegment,
    window: Long,
    property: Long,
    reqType: Long,
    length: Long,
    read: (MemorySegment, Long) -> T?,
): T? =
    try {
        Arena.ofConfined().use { arena ->
            val actualType = arena.allocate(ValueLayout.JAVA_LONG)
            val actualFormat = arena.allocate(ValueLayout.JAVA_INT)
            val nitems = arena.allocate(ValueLayout.JAVA_LONG)
            val bytesAfter = arena.allocate(ValueLayout.JAVA_LONG)
            val propReturn = arena.allocate(ValueLayout.ADDRESS)
            val status = getProperty.invokeExact(
                display,
                window,
                property,
                0L,
                length,
                0,
                reqType,
                actualType,
                actualFormat,
                nitems,
                bytesAfter,
                propReturn,
            ) as Int
            if (status != 0) return@use null
            val ptr = propReturn.get(ValueLayout.ADDRESS, 0L)
            if (ptr == MemorySegment.NULL) return@use null
            try {
                val itemCount = nitems.get(ValueLayout.JAVA_LONG, 0L)
                val format = actualFormat.get(ValueLayout.JAVA_INT, 0L)
                val byteSize = when (format) {
                    8 -> itemCount
                    16 -> itemCount * 2L
                    32 -> itemCount * 8L
                    else -> 0L
                }
                if (byteSize <= 0L) return@use null
                read(ptr.reinterpret(byteSize), itemCount)
            } finally {
                val free = xFree
                if (free != null) free.invokeExact(ptr) as Int
            }
        }
    } catch (_: Throwable) {
        null
    }

private fun x11InternAtom(displayPtr: Long, name: String): Long {
    val handle = xInternAtom ?: return 0L
    val display = MemorySegment.ofAddress(displayPtr)
    return try {
        Arena.ofConfined().use { arena ->
            val bytes = name.toByteArray(Charsets.US_ASCII)
            val ptr = arena.allocate(bytes.size.toLong() + 1)
            for (index in bytes.indices) {
                ptr.set(ValueLayout.JAVA_BYTE, index.toLong(), bytes[index])
            }
            ptr.set(ValueLayout.JAVA_BYTE, bytes.size.toLong(), 0)
            handle.invokeExact(display, ptr, 0) as Long
        }
    } catch (_: Throwable) {
        0L
    }
}

internal data class X11WindowLevelState(
    val above: Boolean,
    val below: Boolean,
)

internal fun x11WindowLevelState(level: WindowLevel): X11WindowLevelState =
    when (level) {
        WindowLevel.AlwaysOnTop -> X11WindowLevelState(above = true, below = false)
        WindowLevel.Normal -> X11WindowLevelState(above = false, below = false)
        WindowLevel.AlwaysOnBottom -> X11WindowLevelState(above = false, below = true)
    }

internal fun x11ThemeVariant(theme: Theme?): String =
    when (theme) {
        Theme.Dark -> "dark"
        Theme.Light -> "light"
        null -> "dark"
    }

@Suppress("UNUSED_PARAMETER")
internal fun x11ContentProtectionResult(protected: Boolean): WindowRequestResult =
    WindowRequestResult.Success

@Suppress("UNUSED_PARAMETER")
internal fun x11ShowWindowMenuResult(position: PhysicalPosition<Int>): WindowRequestResult =
    WindowRequestResult.Success

@Suppress("UNUSED_PARAMETER")
internal fun x11TransparencyRequiresNativeUpdate(transparent: Boolean): Boolean = false

@Suppress("UNUSED_PARAMETER")
internal fun x11BlurRequiresNativeUpdate(blur: Boolean): Boolean = false

@Suppress("UNUSED_PARAMETER")
internal fun x11ResizableChangeAfterRequest(
    current: Boolean,
    requested: Boolean,
    isXfwm4: Boolean,
): Boolean? =
    if (isXfwm4) {
        null
    } else {
        requested
    }

@Suppress("UNUSED_PARAMETER")
internal fun x11EnabledButtonsAfterSet(buttons: WindowButtons): WindowButtons = WindowButtons.ALL

internal fun x11EnabledButtons(): WindowButtons = WindowButtons.ALL

internal fun x11InitialPosition(position: PhysicalPosition<Int>?): PhysicalPosition<Int> =
    position ?: PhysicalPosition(0, 0)
