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
import org.graphiks.kadre.appkit.bindings.NSRect
import org.graphiks.kadre.appkit.bindings.NSView
import org.graphiks.kadre.appkit.bindings.NSWindow
import org.graphiks.kadre.appkit.bindings.NSWindowStyleMask
import org.graphiks.kadre.appkit.bindings.ObjCRuntime
import org.graphiks.kadre.core.ActiveEventLoop
import org.graphiks.kadre.core.ApplicationHandler
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

    /**
     * NSWindowDelegate installed on this window.
     * Null until [setWindowDelegate] has been called.
     */
    var delegate: KadreWindowDelegate? = null
        private set

    init {
        MainThreadCheck.require()

        // 1. Compute styleMask from the attributes
        var styleMask = NSWindowStyleMask.NSWindowStyleMaskTitled +
                NSWindowStyleMask.NSWindowStyleMaskClosable +
                NSWindowStyleMask.NSWindowStyleMaskMiniaturizable
        if (attrs.resizable) {
            styleMask = styleMask + NSWindowStyleMask.NSWindowStyleMaskResizable
        }

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

        // 8. Display if requested
        if (attrs.visible) {
            nsWindow.makeKeyAndOrderFront(MemorySegment.NULL)
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

    override val rawWindowHandle: RawWindowHandle
        get() = RawWindowHandle.AppKit(
            nsView = contentViewPtr.address(),
            nsWindow = nsWindowPtr.address(),
            nsLayer = metalLayerPtr.address(),
        )

    override val rawDisplayHandle: RawDisplayHandle
        get() = RawDisplayHandle.AppKit

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
