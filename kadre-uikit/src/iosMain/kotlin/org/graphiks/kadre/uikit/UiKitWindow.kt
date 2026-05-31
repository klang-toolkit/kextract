package org.graphiks.kadre.uikit

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
import kotlinx.cinterop.ObjCClass
import kotlinx.cinterop.objcPtr
import kotlinx.cinterop.useContents
import platform.CoreGraphics.CGRect
import platform.QuartzCore.CAMetalLayer
import platform.UIKit.UIEvent
import platform.UIKit.UIScreen
import platform.UIKit.UITouch
import platform.UIKit.UIView
import platform.UIKit.UIViewController
import platform.UIKit.UIViewMeta
import platform.UIKit.UIWindow

/**
 * KadreMetalView : UIView backed by CAMetalLayer.
 *
 * +layerClass override ensures UIKit uses CAMetalLayer as the backing store
 * from the very first layout pass — no sublayer attachment needed.
 *
 * UIResponder touch callbacks forward all contacts to [onTouchEvent].
 */
@OptIn(ExperimentalForeignApi::class, kotlinx.cinterop.BetaInteropApi::class)
class KadreMetalView(
    frame: CValue<CGRect>,
    private val onTouchEvent: (WindowEvent) -> Unit = {},
) : UIView(frame = frame) {
    companion object : UIViewMeta() {
        override fun layerClass(): ObjCClass = CAMetalLayer.`class`()!!
    }

    val metalLayer: CAMetalLayer get() = layer as CAMetalLayer

    override fun touchesBegan(touches: Set<*>, withEvent: UIEvent?) =
        dispatchTouches(touches, TouchPhase.Started)

    override fun touchesMoved(touches: Set<*>, withEvent: UIEvent?) =
        dispatchTouches(touches, TouchPhase.Moved)

    override fun touchesEnded(touches: Set<*>, withEvent: UIEvent?) =
        dispatchTouches(touches, TouchPhase.Ended)

    override fun touchesCancelled(touches: Set<*>, withEvent: UIEvent?) =
        dispatchTouches(touches, TouchPhase.Cancelled)

    private fun dispatchTouches(touches: Set<*>, phase: TouchPhase) {
        val scale = UIScreen.mainScreen.scale
        touches.forEach { touch ->
            val uiTouch = touch as? UITouch ?: return@forEach
            val loc = uiTouch.locationInView(this)
            val x = loc.useContents { x * scale }
            val y = loc.useContents { y * scale }
            val id = uiTouch.objcPtr().toLong()
            onTouchEvent(WindowEvent.Touch(phase, PhysicalPosition(x, y), id))
        }
    }
}

/**
 * UiKitWindow — implémente Window pour iOS.
 *
 * Crée UIWindow → UIViewController → KadreMetalView (plein écran).
 * CAMetalLayer est le backing layer de la vue (via +layerClass).
 * Les événements tactiles sont dispatchés vers [eventLoop].handler.
 */
@OptIn(ExperimentalForeignApi::class)
internal class UiKitWindow(attrs: WindowAttributes, private val eventLoop: UIKitActiveEventLoop) : Window {

    private val uiWindow: UIWindow
    private val viewController: UIViewController
    private val metalView: KadreMetalView

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

        // 1. KadreMetalView plein écran (dispatch lambda uses windowId)
        metalView = KadreMetalView(frame = screenBounds) { event ->
            eventLoop.handler.windowEvent(eventLoop, windowId, event)
        }

        // 2. contentsScale pour HiDPI / Retina
        metalView.metalLayer.setContentsScale(screen.scale)

        // 3. Root view controller hébergeant la metal view
        viewController = UIViewController(nibName = null, bundle = null)
        viewController.setView(metalView)

        // 5. Wire root VC and show
        uiWindow.rootViewController = viewController
        if (attrs.visible) {
            uiWindow.makeKeyAndVisible()
        }
    }

    override val rawWindowHandle: Any
        get() = RawWindowHandle.UiKit(
            uiView = metalView.objcPtr().toLong(),
            uiViewController = viewController.objcPtr().toLong(),
        )

    override val rawDisplayHandle: Any
        get() = RawDisplayHandle.UiKit

    override fun requestRedraw() {
        // Redraw signaling — no-op pour M3 ; le loop CADisplayLink (GRA-144+) cadence les frames.
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
        uiWindow.setHidden(true)
        uiWindow.resignKeyWindow()
    }
}
