/**
 * AppKit implementation of the Window interface for macOS.
 *
 * Creates an NSWindow with a CAMetalLayer layer-backed contentView,
 * following the AppKit Metal pattern (wantsLayer + setLayer).
 *
 * GRA-126: native macOS window via FFM, zero JNA/Rococoa.
 */
package org.graphiks.kadre.appkit

import org.graphiks.kadre.appkit.bindings.NSBackingStoreType
import org.graphiks.kadre.appkit.bindings.NSApplication
import org.graphiks.kadre.appkit.bindings.NSRequestUserAttentionType
import org.graphiks.kadre.appkit.bindings.NSRect
import org.graphiks.kadre.appkit.bindings.NSView
import org.graphiks.kadre.appkit.bindings.NSWindow
import org.graphiks.kadre.appkit.bindings.NSWindowButton
import org.graphiks.kadre.appkit.bindings.NSWindowSharingType
import org.graphiks.kadre.appkit.bindings.NSWindowStyleMask
import org.graphiks.kadre.appkit.bindings.ObjCRuntime
import org.graphiks.kadre.core.ActiveEventLoop
import org.graphiks.kadre.core.ApplicationHandler
import org.graphiks.kadre.core.CursorGrabMode
import org.graphiks.kadre.core.CursorIcon
import org.graphiks.kadre.core.Fullscreen
import org.graphiks.kadre.core.Icon
import org.graphiks.kadre.core.InputCapabilities
import org.graphiks.kadre.core.MonitorHandle
import org.graphiks.kadre.core.PhysicalPosition
import org.graphiks.kadre.core.PhysicalSize
import org.graphiks.kadre.core.RawDisplayHandle
import org.graphiks.kadre.core.RawWindowHandle
import org.graphiks.kadre.core.RequestError
import org.graphiks.kadre.core.Theme
import org.graphiks.kadre.core.UserAttentionType
import org.graphiks.kadre.core.Window
import org.graphiks.kadre.core.WindowAttributes
import org.graphiks.kadre.core.WindowButtons
import org.graphiks.kadre.core.WindowId
import org.graphiks.kadre.core.WindowLevel
import org.graphiks.kadre.core.WindowRequestResult
import java.lang.foreign.Arena
import java.lang.foreign.MemorySegment
import java.lang.foreign.ValueLayout

/**
 * Native macOS window implementing [Window].
 *
 * Uses NSWindow + CAMetalLayer via FFM (Foreign Function & Memory API).
 * The AppKit Metal pattern is respected: `contentView.wantsLayer = true` THEN
 * `contentView.layer = CAMetalLayer()` — never `+layerClass`.
 */
class AppKitWindow(attrs: WindowAttributes) : Window {

    private val arena = Arena.global()
    private val nsWindowPtr: MemorySegment
    private val contentViewPtr: MemorySegment
    private val metalLayerPtr: MemorySegment

    override val id: WindowId

    /** In-memory fullscreen state (R2). */
    @Volatile
    private var _fullscreen: Fullscreen? = attrs.fullscreen

    @Volatile
    private var activeAttentionRequest: Long? = null

    @Volatile
    private var _enabledButtons: WindowButtons = attrs.enabledButtons

    @Volatile
    private var blurEffectViewPtr: MemorySegment = MemorySegment.NULL

    /**
     * NSWindowDelegate installed on this window.
     * Null until [setWindowDelegate] has been called.
     */
    var delegate: KadreWindowDelegate? = null
        private set

    init {
        MainThreadCheck.require()

        // 1. Compute styleMask from the attributes
        var styleMask = if (attrs.decorations) {
            NSWindowStyleMask.NSWindowStyleMaskTitled +
            NSWindowStyleMask.NSWindowStyleMaskClosable +
            NSWindowStyleMask.NSWindowStyleMaskMiniaturizable
        } else {
            NSWindowStyleMask.NSWindowStyleMaskBorderless
        }
        if (attrs.resizable && attrs.decorations) {
            styleMask = styleMask + NSWindowStyleMask.NSWindowStyleMaskResizable
        }
        styleMask = appKitStyleMaskWithEnabledButtons(styleMask, attrs.enabledButtons)

        // 2. Window size (in logical points — scaleFactor is 1.0 at init time,
        //    before the window is attached to a screen)
        val width = attrs.size?.width?.toDouble() ?: 800.0
        val height = attrs.size?.height?.toDouble() ?: 600.0
        val contentRect: NSRect = allocNSRect(arena, 100.0, 100.0, width, height)

        // 3. Allocate + initialize NSWindow via alloc/init
        val nsWindowClass = ObjCRuntime.getClass("NSWindow")
        val allocated = ObjCRuntime.msgSend(
            ValueLayout.ADDRESS,
            nsWindowClass,
            ObjCRuntime.sel("alloc"),
        ) as MemorySegment

        val backing = NSBackingStoreType.NSBackingStoreBuffered

        // kextract v0.0.2 now correctly emits initWithContentRect:styleMask:backing:defer:
        // with NSRect by-value via ObjCStructArg + the CGRect GroupLayout.
        val initializedPtr = NSWindow(allocated).initWithContentRect_styleMask_backing_defer(
            contentRect,
            styleMask,
            backing,
            false, // BOOL defer = NO (BOOL is now Boolean since v0.0.2)
        )
        nsWindowPtr = initializedPtr
        id = WindowId(nsWindowPtr.address())

        // 4. Get the existing contentView of the NSWindow
        val nsWindow = NSWindow(nsWindowPtr)
        contentViewPtr = nsWindow.contentView()

        // 5. Correct AppKit Metal pattern: layer = CAMetalLayer() THEN wantsLayer = YES
        //    Apple docs: "If you want to use a custom layer, you must call setLayer: BEFORE
        //    calling setWantsLayer:YES". The reverse order makes AppKit first create a
        //    generic CALayer, making [nsView layer] unreliable and nextDrawable impossible.
        val contentView = NSView(contentViewPtr)

        val metalLayerClass = ObjCRuntime.getClass("CAMetalLayer")
        metalLayerPtr = ObjCRuntime.msgSend(
            ValueLayout.ADDRESS,
            metalLayerClass,
            ObjCRuntime.sel("new"),
        ) as MemorySegment
        contentView.setLayer(metalLayerPtr) // ← setLayer BEFORE setWantsLayer
        contentView.setWantsLayer(true)     // ← setWantsLayer LAST (BOOL = Boolean since v0.0.2)

        // 6. contentsScale = backingScaleFactor for HiDPI / Retina support
        val scale = NSWindow(nsWindowPtr).backingScaleFactor()
        ObjCRuntime.msgSend(
            null,
            metalLayerPtr,
            ObjCRuntime.sel("setContentsScale:"),
            scale,
        )

        // 7. Initial title
        nsWindow.setTitle(attrs.title)
        applyEnabledButtons(attrs.enabledButtons)
        setWindowLevel(attrs.windowLevel)
        applyInitialAppearance(attrs)

        // 7b. Apply R1 attrs: minSize / maxSize / position / maximized
        attrs.minSize?.let { min ->
            Arena.ofConfined().use { a ->
                nsWindow.setContentMinSize(allocNSSize(a, min.width.toDouble(), min.height.toDouble()))
            }
        }
        attrs.maxSize?.let { max ->
            Arena.ofConfined().use { a ->
                nsWindow.setContentMaxSize(allocNSSize(a, max.width.toDouble(), max.height.toDouble()))
            }
        }
        attrs.resizeIncrements?.let { increments ->
            setSurfaceResizeIncrements(increments)
        }
        attrs.position?.let { pos ->
            Arena.ofConfined().use { a ->
                nsWindow.setFrameOrigin(allocNSPoint(a, pos.x.toDouble(), pos.y.toDouble()))
            }
        }
        if (attrs.maximized) {
            nsWindow.zoom(MemorySegment.NULL)
        }
        if (attrs.contentProtected) {
            setContentProtected(true)
        }

        // 8. Display if requested
        if (attrs.visible) {
            nsWindow.makeKeyAndOrderFront(MemorySegment.NULL)
        }

        // 8b. Apply initial fullscreen from attrs (toggleFullScreen is called; _fullscreen already set above)
        if (attrs.fullscreen != null) {
            nsWindow.toggleFullScreen(MemorySegment.NULL)
        }

        // 9. Install NSTrackingArea for mouseEntered/mouseExited/mouseMoved events
        val trackingAreaClass = ObjCRuntime.getClass("NSTrackingArea")
        val trackingAreaAlloc = ObjCRuntime.msgSend(
            ValueLayout.ADDRESS,
            trackingAreaClass,
            ObjCRuntime.sel("alloc"),
        ) as MemorySegment

        // NSZeroRect (all zeros) since NSTrackingInVisibleRect handles the rect automatically
        val zeroRect = allocNSRect(arena, 0.0, 0.0, 0.0, 0.0)

        // Options bitmask:
        // NSTrackingMouseEnteredAndExited = 0x01
        // NSTrackingMouseMoved            = 0x02
        // NSTrackingActiveInKeyWindow     = 0x20
        // NSTrackingInVisibleRect         = 0x100
        // Combined = 0x123
        val trackingOptions = 0x123L

        // NSTrackingArea isn't in --include-objc-class, so we hand-roll the init call.
        // CGRect layout for the by-value NSRect argument.
        val trackingArea = ObjCRuntime.msgSend(
            ValueLayout.ADDRESS,
            trackingAreaAlloc,
            ObjCRuntime.sel("initWithRect:options:owner:userInfo:"),
            ObjCRuntime.ObjCStructArg(zeroRect, NS_RECT_LAYOUT),  // NSRect by value
            trackingOptions,           // NSTrackingAreaOptions (Long)
            contentViewPtr,            // owner = contentView
            MemorySegment.NULL,        // userInfo = nil
        ) as MemorySegment

        ObjCRuntime.msgSend(
            null,
            contentViewPtr,
            ObjCRuntime.sel("addTrackingArea:"),
            trackingArea,
        )
    }

    private fun applyInitialAppearance(attrs: WindowAttributes) {
        if (appKitShouldApplyInitialTransparency(attrs.transparent)) {
            setTransparent(true)
        }
        if (appKitShouldApplyInitialBlur(attrs.blur)) {
            setBlur(true)
        }
    }

    override val rawWindowHandle: RawWindowHandle
        get() = RawWindowHandle.AppKit(
            nsView = contentViewPtr.address(),
            nsWindow = nsWindowPtr.address(),
            nsLayer = metalLayerPtr.address(),
        )

    override val rawDisplayHandle: RawDisplayHandle
        get() = RawDisplayHandle.AppKit

    override fun inputCapabilities(): InputCapabilities =
        InputCapabilities(
            deviceIds = true,
            pinchGesture = true,
            rotationGesture = true,
            doubleTapGesture = true,
        )

    /**
     * Requested-redraw flag — read and reset by [CFRunLoopRedrawObserver]
     * on kCFRunLoopBeforeWaiting (GRA-134).
     */
    @Volatile
    internal var needsRedraw: Boolean = false

    /**
     * Requests a redraw: sets the [needsRedraw] flag, which will be consumed
     * by the CFRunLoop observer before the next sleep (native coalescing).
     */
    override fun requestRedraw() {
        needsRedraw = true
    }

    override fun setTitle(title: String) {
        NSWindow(nsWindowPtr).setTitle(title)
    }

    /**
     * Inner size of the window (rendering surface) in physical pixels.
     *
     * Reads the contentView frame (in logical points) and multiplies
     * by backingScaleFactor to obtain physical pixels.
     */
    override val innerSize: PhysicalSize<Int>
        get() {
            val scale = NSWindow(nsWindowPtr).backingScaleFactor()
            val frame = NSView(contentViewPtr).frame()
            val w = frame.getAtIndex(ValueLayout.JAVA_DOUBLE, 2)
            val h = frame.getAtIndex(ValueLayout.JAVA_DOUBLE, 3)
            return PhysicalSize((w * scale).toInt(), (h * scale).toInt())
        }

    /**
     * Outer size of the window (including decorations) in physical pixels.
     *
     * Reads the NSWindow frame (in logical points) and multiplies
     * by backingScaleFactor.
     */
    override val outerSize: PhysicalSize<Int>
        get() {
            val nsWindow = NSWindow(nsWindowPtr)
            val scale = nsWindow.backingScaleFactor()
            val frame = nsWindow.frame()
            val w = frame.getAtIndex(ValueLayout.JAVA_DOUBLE, 2)
            val h = frame.getAtIndex(ValueLayout.JAVA_DOUBLE, 3)
            return PhysicalSize((w * scale).toInt(), (h * scale).toInt())
        }

    override val scaleFactor: Double
        get() = NSWindow(nsWindowPtr).backingScaleFactor()

    override fun setVisible(visible: Boolean) {
        if (visible) {
            NSWindow(nsWindowPtr).makeKeyAndOrderFront(MemorySegment.NULL)
        } else {
            NSWindow(nsWindowPtr).orderOut(MemorySegment.NULL)
        }
    }

    override fun close() {
        NSWindow(nsWindowPtr).close()
    }

    // ── R1: window state & geometry ───────────────────────────────────────────

    override val title: String
        get() = try {
            NSWindow(nsWindowPtr).titleAsString()
        } catch (_: Throwable) { "" }

    override val isVisible: Boolean?
        get() = try {
            NSWindow(nsWindowPtr).isVisible()
        } catch (_: Throwable) { null }

    override fun setResizable(resizable: Boolean) {
        try {
            val nsWindow = NSWindow(nsWindowPtr)
            val current = nsWindow.styleMask()
            val resizableMask = NSWindowStyleMask.NSWindowStyleMaskResizable
            val newMask = if (resizable) current + resizableMask
                          else NSWindowStyleMask(current.rawValue and resizableMask.rawValue.inv())
            nsWindow.setStyleMask(newMask)
        } catch (_: Throwable) {}
    }

    override val isResizable: Boolean
        get() = try {
            NSWindowStyleMask.NSWindowStyleMaskResizable in NSWindow(nsWindowPtr).styleMask()
        } catch (_: Throwable) { false }

    override fun setMinimized(minimized: Boolean) {
        try {
            val nsWindow = NSWindow(nsWindowPtr)
            if (minimized) nsWindow.miniaturize(MemorySegment.NULL)
            else nsWindow.deminiaturize(MemorySegment.NULL)
        } catch (_: Throwable) {}
    }

    override val isMinimized: Boolean?
        get() = try {
            NSWindow(nsWindowPtr).isMiniaturized()
        } catch (_: Throwable) { null }

    override fun setMaximized(maximized: Boolean) {
        try {
            val isZoomed = NSWindow(nsWindowPtr).isZoomed()
            if (maximized != isZoomed) NSWindow(nsWindowPtr).zoom(MemorySegment.NULL)
        } catch (_: Throwable) {}
    }

    override val isMaximized: Boolean
        get() = try {
            NSWindow(nsWindowPtr).isZoomed()
        } catch (_: Throwable) { false }

    override fun setDecorations(decorated: Boolean) {
        try {
            AppKitMainThread.runSync {
                val nsWindow = NSWindow(nsWindowPtr)
                val newMask = if (decorated) {
                    NSWindowStyleMask.NSWindowStyleMaskTitled +
                    NSWindowStyleMask.NSWindowStyleMaskClosable +
                    NSWindowStyleMask.NSWindowStyleMaskMiniaturizable +
                    (if (isResizable) NSWindowStyleMask.NSWindowStyleMaskResizable else NSWindowStyleMask.NSWindowStyleMaskBorderless)
                } else {
                    NSWindowStyleMask.NSWindowStyleMaskBorderless
                }
                nsWindow.setStyleMask(newMask)
                if (decorated) {
                    applyEnabledButtons(_enabledButtons)
                } else {
                    setStandardWindowButtonEnabled(nsWindow, NSWindowButton.NSWindowZoomButton, false)
                }
            }
        } catch (_: Throwable) {}
    }

    override val isDecorated: Boolean
        get() = try {
            NSWindowStyleMask.NSWindowStyleMaskTitled in NSWindow(nsWindowPtr).styleMask()
        } catch (_: Throwable) { true }

    override fun setEnabledButtons(buttons: WindowButtons) {
        _enabledButtons = buttons
        try {
            AppKitMainThread.runSync {
                try {
                    applyEnabledButtons(buttons)
                } catch (_: Throwable) {}
            }
        } catch (_: Throwable) {}
    }

    override val enabledButtons: WindowButtons
        get() = try {
            AppKitMainThread.runSync {
                try {
                    val nsWindow = NSWindow(nsWindowPtr)
                    val mask = nsWindow.styleMask()
                    var buttons = WindowButtons.NONE
                    if (NSWindowStyleMask.NSWindowStyleMaskClosable in mask) {
                        buttons += WindowButtons.CLOSE
                    }
                    if (NSWindowStyleMask.NSWindowStyleMaskMiniaturizable in mask) {
                        buttons += WindowButtons.MINIMIZE
                    }
                    if (isStandardWindowButtonEnabled(nsWindow, NSWindowButton.NSWindowZoomButton)) {
                        buttons += WindowButtons.MAXIMIZE
                    }
                    buttons
                } catch (_: Throwable) {
                    _enabledButtons
                }
            }
        } catch (_: Throwable) {
            _enabledButtons
        }

    private fun applyEnabledButtons(buttons: WindowButtons) {
        val nsWindow = NSWindow(nsWindowPtr)
        val newMask = appKitStyleMaskWithEnabledButtons(nsWindow.styleMask(), buttons)
        nsWindow.setStyleMask(newMask)
        setStandardWindowButtonEnabled(
            nsWindow,
            NSWindowButton.NSWindowZoomButton,
            buttons.contains(WindowButtons.MAXIMIZE),
        )
        _enabledButtons = buttons
    }

    private fun setStandardWindowButtonEnabled(nsWindow: NSWindow, button: NSWindowButton, enabled: Boolean) {
        val buttonPtr = nsWindow.standardWindowButton(button)
        if (buttonPtr == MemorySegment.NULL) return
        ObjCRuntime.msgSend(null, buttonPtr, ObjCRuntime.sel("setEnabled:"), enabled)
    }

    private fun isStandardWindowButtonEnabled(nsWindow: NSWindow, button: NSWindowButton): Boolean {
        val buttonPtr = nsWindow.standardWindowButton(button)
        if (buttonPtr == MemorySegment.NULL) return true
        return ObjCRuntime.msgSend(
            ValueLayout.JAVA_BOOLEAN,
            buttonPtr,
            ObjCRuntime.sel("isEnabled"),
        ) as Boolean
    }

    override fun setMinSurfaceSize(size: PhysicalSize<Int>?) {
        try {
            val nsWindow = NSWindow(nsWindowPtr)
            val scale = nsWindow.backingScaleFactor()
            Arena.ofConfined().use { arena ->
                val nsSize = allocNSSize(arena,
                    if (size != null) size.width / scale else 0.0,
                    if (size != null) size.height / scale else 0.0,
                )
                nsWindow.setContentMinSize(nsSize)
            }
        } catch (_: Throwable) {}
    }

    override fun setMaxSurfaceSize(size: PhysicalSize<Int>?) {
        try {
            val nsWindow = NSWindow(nsWindowPtr)
            val scale = nsWindow.backingScaleFactor()
            // NSWindow maximum: use a very large value to remove the constraint
            val w = if (size != null) size.width / scale else Double.MAX_VALUE.coerceAtMost(32767.0)
            val h = if (size != null) size.height / scale else Double.MAX_VALUE.coerceAtMost(32767.0)
            Arena.ofConfined().use { arena ->
                val nsSize = allocNSSize(arena, w, h)
                nsWindow.setContentMaxSize(nsSize)
            }
        } catch (_: Throwable) {}
    }

    override val surfaceResizeIncrements: PhysicalSize<Int>?
        get() = try {
            val nsWindow = NSWindow(nsWindowPtr)
            val scale = nsWindow.backingScaleFactor()
            val increments = nsWindow.contentResizeIncrements()
            val width = increments.getAtIndex(ValueLayout.JAVA_DOUBLE, 0)
            val height = increments.getAtIndex(ValueLayout.JAVA_DOUBLE, 1)
            appKitResizeIncrementsToPhysicalSize(width, height, scale)
        } catch (_: Throwable) { null }

    override fun setSurfaceResizeIncrements(increments: PhysicalSize<Int>?) {
        try {
            val nsWindow = NSWindow(nsWindowPtr)
            val scale = nsWindow.backingScaleFactor()
            val (width, height) = physicalSizeToAppKitResizeIncrements(increments, scale)
            Arena.ofConfined().use { arena ->
                val nsSize = allocNSSize(arena, width, height)
                nsWindow.setContentResizeIncrements(nsSize)
            }
        } catch (_: Throwable) {}
    }

    override val outerPosition: PhysicalPosition<Int>
        get() = try {
            val nsWindow = NSWindow(nsWindowPtr)
            val scale = nsWindow.backingScaleFactor()
            val frame = nsWindow.frame()
            // NSWindow.frame origin is in screen coordinates (bottom-left in Cocoa).
            // Note: Cocoa uses a bottom-left origin. The screen height is needed for
            // a strict top-left conversion; we return the raw Cocoa bottom-left values
            // converted to physical pixels — callers that need top-left must adjust.
            val x = frame.getAtIndex(ValueLayout.JAVA_DOUBLE, 0)
            val y = frame.getAtIndex(ValueLayout.JAVA_DOUBLE, 1)
            PhysicalPosition((x * scale).toInt(), (y * scale).toInt())
        } catch (_: Throwable) { PhysicalPosition(0, 0) }

    override fun setOuterPosition(position: PhysicalPosition<Int>) {
        try {
            val nsWindow = NSWindow(nsWindowPtr)
            val scale = nsWindow.backingScaleFactor()
            Arena.ofConfined().use { arena ->
                val nsPoint = allocNSPoint(arena,
                    position.x / scale,
                    position.y / scale,
                )
                nsWindow.setFrameOrigin(nsPoint)
            }
        } catch (_: Throwable) {}
    }

    /**
     * No-op on AppKit: there is no direct equivalent to Wayland's
     * `wl_surface.pre_commit` on macOS.
     */
    override fun prePresentNotify() { /* no-op on AppKit */ }

    // ── R2: monitor & fullscreen ──────────────────────────────────────────────

    /**
     * Returns the monitor that currently contains the majority of this window
     * by reading NSWindow.screen.
     */
    override fun currentMonitor(): MonitorHandle? {
        return try {
            val screenPtr = NSWindow(nsWindowPtr).screen()
            if (screenPtr == MemorySegment.NULL || screenPtr.address() == 0L) null
            else {
                val mainScreenPtr = ObjCRuntime.msgSend(
                    ValueLayout.ADDRESS,
                    ObjCRuntime.getClass("NSScreen"),
                    ObjCRuntime.sel("mainScreen"),
                ) as MemorySegment
                AppKitMonitorHandle(screenPtr, screenPtr.address() == mainScreenPtr.address())
            }
        } catch (_: Throwable) { null }
    }

    override fun availableMonitors(): List<MonitorHandle> =
        AppKitMonitorHandle.allScreens()

    override fun primaryMonitor(): MonitorHandle? =
        AppKitMonitorHandle.primaryScreen()

    override val fullscreen: Fullscreen?
        get() = _fullscreen

    /**
     * Enters or exits fullscreen mode on AppKit.
     *
     * - [Fullscreen.Borderless] / [Fullscreen.Exclusive]: calls NSWindow.toggleFullScreen
     *   to enter AppKit's native fullscreen (which covers the menu bar and dock).
     *   Both modes map to the same native call since AppKit manages the video mode
     *   automatically when the window is in fullscreen.
     * - null: calls toggleFullScreen again if the window is currently fullscreen.
     *
     * Note: the actual fullscreen transition is asynchronous (AppKit animates it);
     * [fullscreen] is updated eagerly.
     */
    override fun setFullscreen(fullscreen: Fullscreen?) {
        try {
            val nsWindow = NSWindow(nsWindowPtr)
            val currentlyFullscreen = NSWindowStyleMask.NSWindowStyleMaskFullScreen in nsWindow.styleMask()
            if (fullscreen != null && !currentlyFullscreen) {
                nsWindow.toggleFullScreen(MemorySegment.NULL)
                _fullscreen = fullscreen
            } else if (fullscreen == null && currentlyFullscreen) {
                nsWindow.toggleFullScreen(MemorySegment.NULL)
                _fullscreen = null
            }
            // If state already matches, no-op.
        } catch (_: Throwable) {}
    }

    override fun focusWindow() {
        try {
            AppKitMainThread.runSync {
                val nsWindow = NSWindow(nsWindowPtr)
                if (appKitShouldFocusWindow(nsWindow.isVisible(), nsWindow.isMiniaturized())) {
                    activateApplicationForWindowFocus()
                    nsWindow.makeKeyAndOrderFront(MemorySegment.NULL)
                }
            }
        } catch (_: Throwable) {}
    }

    override val hasFocus: Boolean
        get() = try {
            NSWindow(nsWindowPtr).isKeyWindow()
        } catch (_: Throwable) { false }

    // ── R3: cursor, theme & appearance ───────────────────────────────────────

    /** Currently applied cursor icon (in-memory). */
    @Volatile private var _cursorIcon: CursorIcon = attrs.cursor

    /**
     * Sets the cursor shape via NSCursor.
     *
     * Maps [CursorIcon] to NSCursor class methods. Unmapped cursors fall back
     * to the arrow cursor.
     */
    override fun setCursor(cursor: CursorIcon) {
        _cursorIcon = cursor
        try {
            val selectorName = cursorSelectorName(cursor)
            val nsCursorClass = ObjCRuntime.getClass("NSCursor")
            val cursorObj = ObjCRuntime.msgSend(
                ValueLayout.ADDRESS,
                nsCursorClass,
                ObjCRuntime.sel(selectorName),
            ) as MemorySegment
            ObjCRuntime.msgSend(null, cursorObj, ObjCRuntime.sel("set"))
        } catch (_: Throwable) {
            // No-op on non-macOS or if native call fails — never throws
        }
    }

    /**
     * Shows or hides the cursor via NSCursor.hide/unhide.
     */
    override fun setCursorVisible(visible: Boolean) {
        try {
            val nsCursorClass = ObjCRuntime.getClass("NSCursor")
            if (visible) {
                ObjCRuntime.msgSend(null, nsCursorClass, ObjCRuntime.sel("unhide"))
            } else {
                ObjCRuntime.msgSend(null, nsCursorClass, ObjCRuntime.sel("hide"))
            }
        } catch (_: Throwable) {}
    }

    /**
     * Locks the cursor via CGAssociateMouseAndMouseCursorPosition.
     *
     * - [CursorGrabMode.Locked]: calls CGAssociateMouseAndMouseCursorPosition(false)
     *   to detach cursor movement from pointer position (raw delta mode).
     * - [CursorGrabMode.None]: re-associates.
     * - [CursorGrabMode.Confined]: unsupported, matching winit AppKit.
     */
    override fun setCursorGrab(mode: CursorGrabMode): WindowRequestResult =
        AppKitCursorHelper.setGrabMode(mode)

    /**
     * Warps the cursor to [position] via CGWarpMouseCursorPosition.
     */
    override fun setCursorPosition(position: PhysicalPosition<Int>): WindowRequestResult =
        try {
            Arena.ofConfined().use { arena ->
                val nsWindow = NSWindow(nsWindowPtr)
                val contentView = NSView(contentViewPtr)
                val scale = nsWindow.backingScaleFactor()
                val contentFrame = contentView.frame()
                val contentHeight = contentFrame.getAtIndex(ValueLayout.JAVA_DOUBLE, 3)
                val xPoints = position.x / scale
                val yPointsFromBottom = contentHeight - (position.y / scale)
                val localRect = allocNSRect(arena, xPoints, yPointsFromBottom, 0.0, 0.0)
                val screenRect = nsWindow.convertRectToScreen(localRect)
                AppKitCursorHelper.warpCursor(
                    screenRect.getAtIndex(ValueLayout.JAVA_DOUBLE, 0),
                    screenRect.getAtIndex(ValueLayout.JAVA_DOUBLE, 1),
                )
            }
        } catch (t: Throwable) {
            WindowRequestResult.Failure(RequestError.OsError(t.message ?: t::class.simpleName ?: "AppKit cursor position conversion failed"))
        }

    /**
     * Enables or disables cursor hit-testing via NSWindow.ignoresMouseEvents.
     */
    override fun setCursorHittest(hittest: Boolean): WindowRequestResult =
        try {
            val nsWindow = NSWindow(nsWindowPtr)
            // ignoresMouseEvents = true means the window is click-through (hittest = false)
            ObjCRuntime.msgSend(
                null,
                nsWindowPtr,
                ObjCRuntime.sel("setIgnoresMouseEvents:"),
                !hittest,
            )
            WindowRequestResult.Success
        } catch (t: Throwable) {
            WindowRequestResult.Failure(RequestError.OsError(t.message ?: t::class.simpleName ?: "NSWindow cursor hit-testing failed"))
        }

    /**
     * Starts a native AppKit window drag using the current NSEvent.
     *
     * AppKit requires this to be called while processing a mouse event. When no
     * current event exists we report a typed failure instead of silently no-oping.
     */
    override fun dragWindow(): WindowRequestResult =
        try {
            AppKitMainThread.runSync {
                val nsAppClass = ObjCRuntime.getClass("NSApplication")
                val nsApp = ObjCRuntime.msgSend(
                    ValueLayout.ADDRESS,
                    nsAppClass,
                    ObjCRuntime.sel("sharedApplication"),
                ) as MemorySegment
                if (nsApp == MemorySegment.NULL) {
                    return@runSync WindowRequestResult.Failure(RequestError.OsError("NSApplication.sharedApplication is unavailable"))
                }
                val event = ObjCRuntime.msgSend(
                    ValueLayout.ADDRESS,
                    nsApp,
                    ObjCRuntime.sel("currentEvent"),
                ) as MemorySegment
                if (event == MemorySegment.NULL) {
                    return@runSync WindowRequestResult.Failure(RequestError.Ignored("NSApplication.currentEvent is unavailable for window drag"))
                }
                NSWindow(nsWindowPtr).performWindowDragWithEvent(event)
                WindowRequestResult.Success
            }
        } catch (t: Throwable) {
            WindowRequestResult.Failure(RequestError.OsError(t.message ?: t::class.simpleName ?: "AppKit window drag failed"))
        }

    /**
     * No-op on AppKit, matching winit.
     *
     * macOS does not expose a native per-window system menu equivalent to the
     * Win32/Wayland title-bar menu path. winit accepts this call and ignores it.
     */
    override fun showWindowMenu(position: PhysicalPosition<Int>): WindowRequestResult =
        appKitShowWindowMenuResult(position)

    /** In-memory theme override. */
    @Volatile private var _theme: Theme? = attrs.preferredTheme

    /**
     * Returns the current effective theme for this window.
     *
     * On macOS reads the NSAppearance name to determine Light/Dark.
     */
    override val theme: Theme?
        get() = try {
            AppKitThemeHelper.effectiveTheme(nsWindowPtr)
        } catch (_: Throwable) { _theme }

    /**
     * Requests a specific theme via NSAppearance.
     *
     * Passing null restores the default appearance.
     */
    override fun setTheme(theme: Theme?) {
        _theme = theme
        try {
            AppKitThemeHelper.setTheme(nsWindowPtr, theme)
        } catch (_: Throwable) {}
    }

    /**
     * Sets the Z-order level via NSWindow.level.
     *
     * - [WindowLevel.AlwaysOnTop]:    NSWindowLevel.floating (3)
     * - [WindowLevel.Normal]:         NSWindowLevel.normal (0)
     * - [WindowLevel.AlwaysOnBottom]: NSWindowLevel.normal - 1 (-1)
     */
    override fun setWindowLevel(level: WindowLevel) {
        AppKitMainThread.runSync {
            try {
                ObjCRuntime.msgSend(
                    null,
                    nsWindowPtr,
                    ObjCRuntime.sel("setLevel:"),
                    appKitWindowLevelValue(level),
                )
            } catch (_: Throwable) {
            }
        }
    }

    /**
     * Makes the window background transparent via NSWindow.
     *
     * Sets opaque/backgroundColor together, matching winit AppKit.
     */
    override fun setTransparent(transparent: Boolean) {
        try {
            ObjCRuntime.msgSend(null, nsWindowPtr, ObjCRuntime.sel("setOpaque:"), !transparent)
            val nsColorClass = ObjCRuntime.getClass("NSColor")
            val backgroundColor = ObjCRuntime.msgSend(
                ValueLayout.ADDRESS,
                nsColorClass,
                ObjCRuntime.sel(appKitBackgroundColorSelectorForTransparency(transparent)),
            ) as MemorySegment
            ObjCRuntime.msgSend(null, nsWindowPtr, ObjCRuntime.sel("setBackgroundColor:"), backgroundColor)
        } catch (_: Throwable) {}
    }

    /**
     * Enables a blur effect behind the window via NSVisualEffectView.
     *
     * Inserts a full-size NSVisualEffectView as the first subview of contentView
     * when enabled, and removes the inserted view when disabled.
     */
    override fun setBlur(blur: Boolean) {
        AppKitMainThread.runSync {
            try {
                if (blur) {
                    if (!appKitShouldInstallBlurEffectView(blurEffectViewPtr)) return@runSync
                    installBlurEffectView()
                } else if (appKitShouldRemoveBlurEffectView(blurEffectViewPtr)) {
                    ObjCRuntime.msgSend(null, blurEffectViewPtr, ObjCRuntime.sel("removeFromSuperview"))
                    blurEffectViewPtr = MemorySegment.NULL
                }
            } catch (_: Throwable) {}
        }
    }

    private fun installBlurEffectView() {
        var vev = MemorySegment.NULL
        try {
            val vevClass = ObjCRuntime.getClass("NSVisualEffectView")
            vev = ObjCRuntime.msgSend(
                ValueLayout.ADDRESS, vevClass, ObjCRuntime.sel("new"),
            ) as MemorySegment
            val frame = NSView(contentViewPtr).frame()
            ObjCRuntime.msgSend(null, vev, ObjCRuntime.sel("setFrame:"),
                ObjCRuntime.ObjCStructArg(frame, NS_RECT_LAYOUT))
            ObjCRuntime.msgSend(null, vev, ObjCRuntime.sel("setBlendingMode:"), 0L)
            ObjCRuntime.msgSend(null, contentViewPtr, ObjCRuntime.sel("addSubview:positioned:relativeTo:"),
                vev, 0L /* NSWindowBelow */, MemorySegment.NULL)
            blurEffectViewPtr = vev
        } finally {
            if (vev != MemorySegment.NULL) {
                ObjCRuntime.msgSend(null, vev, ObjCRuntime.sel("release"))
            }
        }
    }

    /**
     * No-op on AppKit, matching winit.
     *
     * macOS does not expose a per-window icon. NSApplication.applicationIconImage
     * is process-global and represented-file icons are semantically distinct, so
     * `Window.setWindowIcon` intentionally does not mutate either of them.
     */
    override fun setWindowIcon(icon: Icon?) {
        if (appKitWindowIconIsSupported()) {
            // Kept as a branch instead of removing the parameter to make the
            // platform policy testable while preserving the common API shape.
            @Suppress("UNUSED_EXPRESSION")
            icon
        }
    }

    override fun requestUserAttention(requestType: UserAttentionType?): WindowRequestResult =
        AppKitMainThread.runSync {
            try {
                val nsAppPtr = NSApplication.sharedApplication()
                if (nsAppPtr == MemorySegment.NULL) {
                    return@runSync WindowRequestResult.Failure(RequestError.OsError("NSApplication.sharedApplication is unavailable"))
                }

                val nsApp = NSApplication(nsAppPtr)
                if (requestType == null) {
                    // Match winit AppKit: None has no effect for attention requests.
                    return@runSync WindowRequestResult.Success
                }

                activeAttentionRequest?.let { request ->
                    nsApp.cancelUserAttentionRequest(request)
                    activeAttentionRequest = null
                }

                val appKitRequest = when (requestType) {
                    UserAttentionType.Critical -> NSRequestUserAttentionType.NSCriticalRequest
                    UserAttentionType.Informational -> NSRequestUserAttentionType.NSInformationalRequest
                }
                activeAttentionRequest = nsApp.requestUserAttention(appKitRequest)
                WindowRequestResult.Success
            } catch (t: Throwable) {
                WindowRequestResult.Failure(RequestError.OsError(t.message ?: t::class.simpleName ?: "AppKit user attention failed"))
            }
        }

    override fun setContentProtected(protected: Boolean): WindowRequestResult =
        AppKitMainThread.runSync {
            try {
                NSWindow(nsWindowPtr).setSharingType(
                    if (protected) {
                        NSWindowSharingType.NSWindowSharingNone
                    } else {
                        NSWindowSharingType.NSWindowSharingReadOnly
                    },
                )
                WindowRequestResult.Success
            } catch (t: Throwable) {
                WindowRequestResult.Failure(RequestError.OsError(t.message ?: t::class.simpleName ?: "AppKit content protection failed"))
            }
        }

    /**
     * Installs a [KadreWindowDelegate] on this window.
     *
     * Must be called from the main thread after the window has been created.
     * The delegate intercepts `windowShouldClose:` and dispatches
     * [org.graphiks.kadre.core.WindowEvent.CloseRequested] to [handler].
     *
     * GRA-128 will call this method from the event loop when creating
     * each window.
     *
     * @param handler   Handler receiving the window events.
     * @param eventLoop Active event loop.
     */
    fun setWindowDelegate(handler: ApplicationHandler, eventLoop: ActiveEventLoop) {
        MainThreadCheck.require()
        val appKitEventLoop = eventLoop as AppKitEventLoop
        val del = KadreWindowDelegate(handler, eventLoop, id, nsWindowPtr, metalLayerPtr, appKitEventLoop.windows)
        NSWindow(nsWindowPtr).setDelegate(del.ptr)
        delegate = del
    }

    // ── R4: keyboard ─────────────────────────────────────────────────────────

    /**
     * Resets any pending dead-key state for this window.
     *
     * Best-effort: sends `markedTextAbandoned:` to the current NSInputManager.
     * On macOS 10.15+ the NSInputManager API is deprecated but still works.
     * No-op if the call fails (the IME state may persist but nothing breaks).
     */
    override fun resetDeadKeys() {
        try {
            val inputManagerClass = ObjCRuntime.getClass("NSInputManager")
            val currentIM = ObjCRuntime.msgSend(
                ValueLayout.ADDRESS,
                inputManagerClass,
                ObjCRuntime.sel("currentInputManager"),
            ) as MemorySegment
            if (currentIM != MemorySegment.NULL) {
                ObjCRuntime.msgSend(null, currentIM, ObjCRuntime.sel("markedTextAbandoned:"), nsWindowPtr)
            }
        } catch (_: Throwable) {
            // Best-effort only — no-op on failure
        }
    }
}

/**
 * Allocates an NSSize (struct {CGFloat width, CGFloat height}) in the provided arena.
 */
private fun allocNSSize(arena: Arena, width: Double, height: Double): MemorySegment {
    val seg = arena.allocate(16L, 8L)
    seg.setAtIndex(ValueLayout.JAVA_DOUBLE, 0, width)
    seg.setAtIndex(ValueLayout.JAVA_DOUBLE, 1, height)
    return seg
}

internal fun appKitResizeIncrementsToPhysicalSize(
    widthPoints: Double,
    heightPoints: Double,
    scale: Double,
): PhysicalSize<Int>? =
    if (widthPoints <= 0.0 || heightPoints <= 0.0 || scale <= 0.0) {
        null
    } else {
        PhysicalSize((widthPoints * scale).toInt(), (heightPoints * scale).toInt())
    }

internal fun physicalSizeToAppKitResizeIncrements(
    increments: PhysicalSize<Int>?,
    scale: Double,
): Pair<Double, Double> =
    if (increments == null || scale <= 0.0) {
        0.0 to 0.0
    } else {
        increments.width / scale to increments.height / scale
    }

internal fun appKitStyleMaskWithEnabledButtons(
    styleMask: NSWindowStyleMask,
    buttons: WindowButtons,
): NSWindowStyleMask =
    if (NSWindowStyleMask.NSWindowStyleMaskTitled in styleMask) {
        styleMask
            .withStyleFlag(NSWindowStyleMask.NSWindowStyleMaskClosable, buttons.contains(WindowButtons.CLOSE))
            .withStyleFlag(NSWindowStyleMask.NSWindowStyleMaskMiniaturizable, buttons.contains(WindowButtons.MINIMIZE))
    } else {
        styleMask
    }

internal fun appKitShouldFocusWindow(isVisible: Boolean, isMiniaturized: Boolean): Boolean =
    isVisible && !isMiniaturized

internal fun appKitShouldApplyInitialTransparency(transparent: Boolean): Boolean = transparent

internal fun appKitShouldApplyInitialBlur(blur: Boolean): Boolean = blur

internal fun appKitBackgroundColorSelectorForTransparency(transparent: Boolean): String =
    if (transparent) "clearColor" else "windowBackgroundColor"

internal fun appKitShouldInstallBlurEffectView(currentBlurView: MemorySegment): Boolean =
    currentBlurView == MemorySegment.NULL

internal fun appKitShouldRemoveBlurEffectView(currentBlurView: MemorySegment): Boolean =
    currentBlurView != MemorySegment.NULL

internal fun appKitWindowLevelValue(level: WindowLevel): Long =
    when (level) {
        WindowLevel.AlwaysOnTop -> 3L
        WindowLevel.Normal -> 0L
        WindowLevel.AlwaysOnBottom -> -1L
    }

private fun NSWindowStyleMask.withStyleFlag(flag: NSWindowStyleMask, enabled: Boolean): NSWindowStyleMask =
    if (enabled) {
        NSWindowStyleMask(rawValue or flag.rawValue)
    } else {
        NSWindowStyleMask(rawValue and flag.rawValue.inv())
    }

private fun activateApplicationForWindowFocus() {
    val nsAppClass = ObjCRuntime.getClass("NSApplication")
    val nsApp = ObjCRuntime.msgSend(
        ValueLayout.ADDRESS,
        nsAppClass,
        ObjCRuntime.sel("sharedApplication"),
    ) as MemorySegment
    ObjCRuntime.msgSend(null, nsApp, ObjCRuntime.sel("activateIgnoringOtherApps:"), true)
}

/**
 * Allocates an NSPoint (struct {CGFloat x, CGFloat y}) in the provided arena.
 */
private fun allocNSPoint(arena: Arena, x: Double, y: Double): MemorySegment {
    val seg = arena.allocate(16L, 8L)
    seg.setAtIndex(ValueLayout.JAVA_DOUBLE, 0, x)
    seg.setAtIndex(ValueLayout.JAVA_DOUBLE, 1, y)
    return seg
}

/**
 * Allocates an NSRect (struct {CGFloat x, CGFloat y, CGFloat width, CGFloat height})
 * in the provided arena.
 *
 * NSRect = 4 × CGFloat (64-bit Double) = 32 bytes, 8-byte alignment.
 */
private fun allocNSRect(arena: Arena, x: Double, y: Double, width: Double, height: Double): MemorySegment {
    val seg = arena.allocate(32L, 8L)
    seg.setAtIndex(ValueLayout.JAVA_DOUBLE, 0, x)
    seg.setAtIndex(ValueLayout.JAVA_DOUBLE, 1, y)
    seg.setAtIndex(ValueLayout.JAVA_DOUBLE, 2, width)
    seg.setAtIndex(ValueLayout.JAVA_DOUBLE, 3, height)
    return seg
}

/**
 * Maps a [CursorIcon] to the NSCursor factory selector name.
 */
private fun cursorSelectorName(cursor: CursorIcon): String = when (cursor) {
    CursorIcon.Default        -> "arrowCursor"
    CursorIcon.Pointer        -> "pointingHandCursor"
    CursorIcon.Text           -> "IBeamCursor"
    CursorIcon.Crosshair      -> "crosshairCursor"
    CursorIcon.Move           -> "openHandCursor"
    CursorIcon.ResizeNorth    -> "resizeUpCursor"
    CursorIcon.ResizeSouth    -> "resizeDownCursor"
    CursorIcon.ResizeEast     -> "resizeRightCursor"
    CursorIcon.ResizeWest     -> "resizeLeftCursor"
    CursorIcon.ResizeNorthEast,
    CursorIcon.ResizeNorthWest,
    CursorIcon.ResizeSouthEast,
    CursorIcon.ResizeSouthWest -> "resizeCursor"
    CursorIcon.NotAllowed     -> "operationNotAllowedCursor"
    CursorIcon.Grab           -> "openHandCursor"
    CursorIcon.Grabbing       -> "closedHandCursor"
    CursorIcon.Wait,
    CursorIcon.Progress       -> "arrowCursor"   // No direct equivalent — fall back
    CursorIcon.EwResize,
    CursorIcon.ColResize      -> "resizeLeftRightCursor"
    CursorIcon.NsResize,
    CursorIcon.RowResize      -> "resizeUpDownCursor"
    CursorIcon.NeswResize     -> "resizeCursor"
    CursorIcon.NwseResize     -> "resizeCursor"
}

internal fun appKitWindowIconIsSupported(): Boolean = false

@Suppress("UNUSED_PARAMETER")
internal fun appKitShowWindowMenuResult(position: PhysicalPosition<Int>): WindowRequestResult =
    WindowRequestResult.Success

/**
 * NSSize GroupLayout: struct { CGFloat width, CGFloat height }.
 */
private val NS_SIZE_LAYOUT: java.lang.foreign.GroupLayout = java.lang.foreign.MemoryLayout.structLayout(
    ValueLayout.JAVA_DOUBLE.withName("width"),
    ValueLayout.JAVA_DOUBLE.withName("height"),
).withName("NSSize")

/**
 * GroupLayout for NSRect / CGRect — nested struct {origin: CGPoint, size: CGSize}.
 *
 * Used manually for ObjC classes NOT included in `--include-objc-class`
 * (e.g. NSTrackingArea). Included classes benefit from the layouts inlined by
 * kextract v0.0.2 in their wrappers.
 */
private val NS_RECT_LAYOUT: java.lang.foreign.GroupLayout = java.lang.foreign.MemoryLayout.structLayout(
    java.lang.foreign.MemoryLayout.structLayout(
        ValueLayout.JAVA_DOUBLE.withName("x"),
        ValueLayout.JAVA_DOUBLE.withName("y"),
    ).withName("origin"),
    java.lang.foreign.MemoryLayout.structLayout(
        ValueLayout.JAVA_DOUBLE.withName("width"),
        ValueLayout.JAVA_DOUBLE.withName("height"),
    ).withName("size"),
).withName("CGRect")
