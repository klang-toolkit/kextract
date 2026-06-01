package org.graphiks.kadre.uikit

import org.graphiks.kadre.core.KeyState
import org.graphiks.kadre.core.PhysicalPosition
import org.graphiks.kadre.core.PhysicalSize
import org.graphiks.kadre.core.RawDisplayHandle
import org.graphiks.kadre.core.RawWindowHandle
import org.graphiks.kadre.core.TouchPhase
import org.graphiks.kadre.core.Window
import org.graphiks.kadre.core.WindowAttributes
import org.graphiks.kadre.core.WindowEvent
import org.graphiks.kadre.core.WindowId
import kotlinx.cinterop.CValue
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.ObjCAction
import kotlinx.cinterop.ObjCClass
import kotlinx.cinterop.objcPtr
import kotlinx.cinterop.useContents
import platform.CoreGraphics.CGRect
import platform.CoreGraphics.CGSizeMake
import platform.Foundation.NSRunLoop
import platform.Foundation.NSRunLoopCommonModes
import platform.Foundation.NSSelectorFromString
import platform.QuartzCore.CADisplayLink
import platform.QuartzCore.CAMetalLayer
import platform.UIKit.UIEvent
import platform.UIKit.UIKey
import platform.UIKit.UIPress
import platform.UIKit.UIPressesEvent
import platform.UIKit.UIScreen
import platform.UIKit.UITouch
import platform.UIKit.UITraitCollection
import platform.UIKit.UIView
import platform.UIKit.UIViewController
import platform.UIKit.UIViewMeta
import platform.UIKit.UIWindow
import platform.darwin.NSObject

/**
 * KadreMetalView : UIView backed by CAMetalLayer.
 *
 * +layerClass override ensures UIKit uses CAMetalLayer as the backing store
 * from the very first layout pass — no sublayer attachment needed.
 *
 * UIResponder touch callbacks forward all contacts to [onEvent] as
 * [WindowEvent.Touch]. Bounds changes (rotation, split-view, status-bar layout)
 * are detected in [layoutSubviews]: the CAMetalLayer `drawableSize` is updated
 * and a [WindowEvent.Resized] is emitted when the physical size actually changes.
 * Display-scale changes (e.g. moving to an external screen) update
 * `contentsScale` and emit [WindowEvent.ScaleFactorChanged].
 */
@OptIn(ExperimentalForeignApi::class, kotlinx.cinterop.BetaInteropApi::class)
class KadreMetalView(
    frame: CValue<CGRect>,
    private val onEvent: (WindowEvent) -> Unit = {},
) : UIView(frame = frame) {
    companion object : UIViewMeta() {
        override fun layerClass(): ObjCClass = CAMetalLayer.`class`()!!
    }

    val metalLayer: CAMetalLayer get() = layer as CAMetalLayer

    /** Last emitted physical size, to avoid duplicate Resized events. */
    private var lastWidth: Int = -1
    private var lastHeight: Int = -1

    /**
     * Last emitted scale factor. Initialized to the main screen scale to match
     * the `contentsScale` set at window creation, so no spurious
     * ScaleFactorChanged is emitted on the first layout pass.
     */
    private var lastScale: Double = UIScreen.mainScreen.scale

    override fun touchesBegan(touches: Set<*>, withEvent: UIEvent?) =
        dispatchTouches(touches, TouchPhase.Started)

    override fun touchesMoved(touches: Set<*>, withEvent: UIEvent?) =
        dispatchTouches(touches, TouchPhase.Moved)

    override fun touchesEnded(touches: Set<*>, withEvent: UIEvent?) =
        dispatchTouches(touches, TouchPhase.Ended)

    override fun touchesCancelled(touches: Set<*>, withEvent: UIEvent?) =
        dispatchTouches(touches, TouchPhase.Cancelled)

    // ── Hardware keyboard / game controller keys (iOS 13.4+) ──────────────────

    /** The view must be first responder to receive key presses. */
    override fun canBecomeFirstResponder(): Boolean = true

    override fun pressesBegan(presses: Set<*>, withEvent: UIPressesEvent?) {
        if (!dispatchPresses(presses, KeyState.Pressed)) super.pressesBegan(presses, withEvent)
    }

    override fun pressesEnded(presses: Set<*>, withEvent: UIPressesEvent?) {
        if (!dispatchPresses(presses, KeyState.Released)) super.pressesEnded(presses, withEvent)
    }

    override fun pressesCancelled(presses: Set<*>, withEvent: UIPressesEvent?) {
        if (!dispatchPresses(presses, KeyState.Released)) super.pressesCancelled(presses, withEvent)
    }

    /**
     * Translates UIPress key data into [WindowEvent.KeyboardInput].
     *
     * @return `true` if at least one press mapped to a known key (and was
     *   consumed); `false` so the caller forwards the event up the chain.
     */
    private fun dispatchPresses(presses: Set<*>, state: KeyState): Boolean {
        var handled = false
        presses.forEach { element ->
            val press = element as? UIPress ?: return@forEach
            val uiKey = press.key ?: return@forEach
            val key = UiKitKeyMapper.fromHidUsage(uiKey.keyCode)
            if (key == org.graphiks.kadre.core.Key.Unknown) return@forEach
            handled = true
            onEvent(
                WindowEvent.KeyboardInput(
                    key = key,
                    state = state,
                    modifiers = UiKitKeyMapper.modifiersFrom(uiKey.modifierFlags),
                    isRepeat = false,
                )
            )
        }
        return handled
    }

    /**
     * Called by UIKit on every layout pass (initial display, device rotation,
     * split-view resize, safe-area changes).
     *
     * Computes the new size in physical pixels, updates the CAMetalLayer
     * `drawableSize` so the Metal surface follows the new bounds, and emits a
     * [WindowEvent.Resized] only when the physical size changed.
     */
    override fun layoutSubviews() {
        super.layoutSubviews()
        syncScaleIfChanged()
        val scale = currentScale()
        val physW = bounds.useContents { size.width * scale }
        val physH = bounds.useContents { size.height * scale }
        val w = physW.toInt()
        val h = physH.toInt()
        if (w <= 0 || h <= 0) return

        // Keep the Metal drawable in sync with the view bounds (every layout pass).
        metalLayer.setDrawableSize(CGSizeMake(physW, physH))

        // Emit Resized only on an actual change to mirror winit semantics.
        if (w != lastWidth || h != lastHeight) {
            lastWidth = w
            lastHeight = h
            onEvent(WindowEvent.Resized(PhysicalSize(w, h)))
        }
    }

    /**
     * Detects a change of `displayScale` (moving to a screen with a different
     * pixel ratio, e.g. an external display). `displayScale` is a UITraitCollection
     * trait, so this fires when it changes.
     */
    override fun traitCollectionDidChange(previousTraitCollection: UITraitCollection?) {
        super.traitCollectionDidChange(previousTraitCollection)
        syncScaleIfChanged()
    }

    /** Effective scale of the view's screen, falling back to the main screen. */
    private fun currentScale(): Double = window?.screen?.scale ?: UIScreen.mainScreen.scale

    /**
     * Updates `CAMetalLayer.contentsScale` and emits [WindowEvent.ScaleFactorChanged]
     * when the effective scale factor changes.
     */
    private fun syncScaleIfChanged() {
        val scale = currentScale()
        if (scale > 0.0 && scale != lastScale) {
            lastScale = scale
            metalLayer.setContentsScale(scale)
            onEvent(WindowEvent.ScaleFactorChanged(scale))
        }
    }

    private fun dispatchTouches(touches: Set<*>, phase: TouchPhase) {
        val scale = UIScreen.mainScreen.scale
        touches.forEach { touch ->
            val uiTouch = touch as? UITouch ?: return@forEach
            val loc = uiTouch.locationInView(this)
            val x = loc.useContents { x * scale }
            val y = loc.useContents { y * scale }
            val id = uiTouch.objcPtr().toLong()
            onEvent(WindowEvent.Touch(phase, PhysicalPosition(x, y), id))
        }
    }
}

/**
 * Objective-C target for a [CADisplayLink].
 *
 * `CADisplayLink` requires a target/selector pair; this NSObject subclass
 * exposes [onFrame] (an `@ObjCAction`) as that selector and forwards each
 * vsync tick to the Kotlin lambda.
 */
@OptIn(ExperimentalForeignApi::class, kotlinx.cinterop.BetaInteropApi::class)
private class DisplayLinkProxy(private val onFrame: () -> Unit) : NSObject() {
    @ObjCAction
    fun handleDisplayLink() = onFrame()
}

/**
 * UiKitWindow — implements Window for iOS.
 *
 * Creates UIWindow → UIViewController → KadreMetalView (full screen).
 * CAMetalLayer is the view's backing layer (via +layerClass).
 * Touch and resize events are dispatched to [eventLoop].handler, and a
 * [CADisplayLink] paces [WindowEvent.RedrawRequested] on every screen refresh.
 */
@OptIn(ExperimentalForeignApi::class)
internal class UiKitWindow(attrs: WindowAttributes, private val eventLoop: UIKitActiveEventLoop) : Window {

    private val uiWindow: UIWindow
    private val viewController: UIViewController
    private val metalView: KadreMetalView

    /** Per-frame redraw driver (vsync-paced). Invalidated on [close]. */
    private var displayLink: CADisplayLink? = null
    private val displayLinkProxy = DisplayLinkProxy { emitRedraw() }

    override val id: WindowId

    init {
        val screen = UIScreen.mainScreen
        val screenBounds = screen.bounds

        // 4. UIWindow first — needed to derive the WindowId
        uiWindow = UIWindow(frame = screenBounds)
        id = WindowId(uiWindow.objcPtr().toLong())

        // Capture id in a local val so the lambda does not close over a val
        // that the compiler might consider uninitialized at lambda-definition time.
        val windowId = id

        // 1. Full-screen KadreMetalView (dispatch lambda uses windowId)
        metalView = KadreMetalView(frame = screenBounds) { event ->
            eventLoop.handler.windowEvent(eventLoop, windowId, event)
        }

        // 2. contentsScale for HiDPI / Retina
        metalView.metalLayer.setContentsScale(screen.scale)

        // 3. Root view controller hosting the metal view
        viewController = UIViewController(nibName = null, bundle = null)
        viewController.setView(metalView)

        // 5. Wire root VC and show
        uiWindow.rootViewController = viewController
        if (attrs.visible) {
            uiWindow.makeKeyAndVisible()
            // Become first responder so hardware-keyboard / controller key
            // presses reach pressesBegan/Ended.
            metalView.becomeFirstResponder()
        }

        // 6. Start the vsync-paced redraw loop.
        startDisplayLink()
    }

    /**
     * Creates and schedules the [CADisplayLink] on the main run loop so
     * [emitRedraw] fires once per screen refresh.
     */
    private fun startDisplayLink() {
        if (displayLink != null) return
        val link = CADisplayLink.displayLinkWithTarget(
            target = displayLinkProxy,
            selector = NSSelectorFromString("handleDisplayLink"),
        )
        link.addToRunLoop(NSRunLoop.mainRunLoop, NSRunLoopCommonModes)
        displayLink = link
    }

    /**
     * Dispatches [WindowEvent.RedrawRequested] for this window each frame.
     *
     * Stops and releases the display link once the loop is exiting so no
     * further frame is emitted after shutdown.
     */
    private fun emitRedraw() {
        if (eventLoop.isExiting) {
            displayLink?.invalidate()
            displayLink = null
            return
        }
        eventLoop.handler.windowEvent(eventLoop, id, WindowEvent.RedrawRequested)
    }

    override val rawWindowHandle: RawWindowHandle
        get() = RawWindowHandle.UiKit(
            uiView = metalView.objcPtr().toLong(),
            uiViewController = viewController.objcPtr().toLong(),
        )

    override val rawDisplayHandle: RawDisplayHandle
        get() = RawDisplayHandle.UiKit

    override fun requestRedraw() {
        // No-op: the CADisplayLink paces RedrawRequested on every screen refresh.
        // Kept for API parity with the desktop backends.
    }

    override fun setTitle(title: String) {
        viewController.title = title
    }

    override val innerSize: PhysicalSize<Int>
        get() {
            val scale = UIScreen.mainScreen.scale
            return metalView.bounds.useContents {
                PhysicalSize(
                    (size.width * scale).toInt(),
                    (size.height * scale).toInt(),
                )
            }
        }

    override val outerSize: PhysicalSize<Int>
        get() {
            val scale = UIScreen.mainScreen.scale
            return uiWindow.bounds.useContents {
                PhysicalSize(
                    (size.width * scale).toInt(),
                    (size.height * scale).toInt(),
                )
            }
        }

    override val scaleFactor: Double
        get() = UIScreen.mainScreen.scale

    override fun setVisible(visible: Boolean) {
        uiWindow.setHidden(!visible)
        if (visible) uiWindow.makeKeyAndVisible()
    }

    override fun close() {
        displayLink?.invalidate()
        displayLink = null
        uiWindow.setHidden(true)
        uiWindow.resignKeyWindow()
    }
}
