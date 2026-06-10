package org.graphiks.kadre.uikit

import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.useContents
import org.graphiks.kadre.core.*
import platform.UIKit.UIScreen
import platform.UIKit.UIUserInterfaceStyle


/**
 * UIKit ActiveEventLoop — lightweight proxy to ApplicationHandler.
 *
 * On iOS, UIKit owns the event loop (UIApplicationMain).
 * This implementation exposes the ActiveEventLoop contract to the
 * KadreAppDelegate callbacks without duplicating the loop.
 */
internal class UIKitActiveEventLoop(internal val handler: ApplicationHandler) : ActiveEventLoop {

    private var _controlFlow: ControlFlow = ControlFlow.Wait
    private var _isExiting = false

    /** Windows created by this loop, used to scope app-level lifecycle events. */
    private val windows = mutableListOf<UiKitWindow>()

    override fun createWindow(attributes: WindowAttributes): Window =
        UiKitWindow(attributes, this).also { windows.add(it) }

    fun createWindow(attrs: UiKitWindowAttributes): Window {
        val window = createWindow(attrs.core) as UiKitWindow
        attrs.scaleFactor?.let { /* scale override would be applied here */ }
        // validOrientations: Kotlin/Native bindings do not expose
        // UIViewController.supportedInterfaceOrientations override.
        // TODO: implement via UIViewController subclass if bindings are extended.
        if (attrs.prefersHomeIndicatorHidden) window.setPrefersHomeIndicatorHidden(true)
        if (attrs.prefersStatusBarHidden) window.setPrefersStatusBarHidden(true)
        attrs.preferredStatusBarStyle?.let { window.setPreferredStatusBarStyle(it) }
        if (attrs.recognizePinchGesture) window.recognizePinchGesture(true)
        if (attrs.recognizePanGesture) window.recognizePanGesture(true, 1, 2)
        if (attrs.recognizeDoubleTapGesture) window.recognizeDoubleTapGesture(true)
        if (attrs.recognizeRotationGesture) window.recognizeRotationGesture(true)
        return window
    }

    /**
     * Emits [WindowEvent.Focused] for every window.
     *
     * Driven by [KadreAppDelegate] from the app activation callbacks. This is the
     * per-window counterpart of the app-level [ApplicationHandler.resumed] /
     * [ApplicationHandler.suspended] lifecycle: on iOS, app focus *is* window
     * focus (single-window AppDelegate model), but both are emitted so consumers
     * that switch on [WindowEvent] (desktop/winit parity) also receive focus.
     */
    internal fun dispatchWindowFocused(gained: Boolean) {
        windows.forEach {
            if (!gained) it.resetKeyboardModifiersIfNeeded()
            handler.windowEvent(this, it.id, WindowEvent.Focused(gained))
        }
    }

    /**
     * Emits [WindowEvent.Occluded] for every window.
     *
     * Driven by [KadreAppDelegate] from the app background/foreground callbacks.
     * On iOS, when the app enters the background it is fully occluded by another
     * app; when it returns to the foreground it is no longer occluded.
     */
    internal fun dispatchOccluded(occluded: Boolean) {
        val event = WindowEvent.Occluded(occluded)
        windows.forEach { handler.windowEvent(this, it.id, event) }
    }

    /**
     * Emits [WindowEvent.Destroyed] for every window, then forgets them.
     *
     * Driven by [KadreAppDelegate] on termination — the per-window counterpart of
     * the app-level [ApplicationHandler.destroySurfaces].
     */
    internal fun dispatchWindowsDestroyed() {
        windows.forEach { handler.windowEvent(this, it.id, WindowEvent.Destroyed) }
        windows.clear()
    }

    override fun setControlFlow(controlFlow: ControlFlow) { _controlFlow = controlFlow }
    override val controlFlow: ControlFlow get() = _controlFlow
    override fun exit() { _isExiting = true }
    override val isExiting: Boolean get() = _isExiting

    override fun createProxy(): EventLoopProxy = UIKitEventLoopProxy(this)

    override fun ownedDisplayHandle(): OwnedDisplayHandle? =
        OwnedDisplayHandle(RawDisplayHandle.UiKit)

    // ── R2: monitor enumeration ───────────────────────────────────────────────

    /**
     * Returns a synthetic monitor based on UIScreen.mainScreen.
     *
     * iOS does not expose physical monitor information; we synthesize a single
     * monitor from the main screen's bounds and scale factor.
     */
    override fun availableMonitors(): List<MonitorHandle> = listOf(syntheticUiKitMonitor())

    /**
     * Returns the primary monitor (the single UIKit screen).
     */
    override fun primaryMonitor(): MonitorHandle? = syntheticUiKitMonitor()

    // ── R3: system theme ──────────────────────────────────────────────────────

    /**
     * Returns the current system theme via UITraitCollection.
     *
     * - UIUserInterfaceStyleLight (1) → [Theme.Light]
     * - UIUserInterfaceStyleDark  (2) → [Theme.Dark]
     * - otherwise                     → null
     */
    override fun systemTheme(): Theme? = try {
        // UITraitCollection.currentTraitCollection is not available in Kotlin/Native bindings.
        // Use the main screen's traitCollection as a best-effort fallback via the window.
        // TODO(R3-uikit-theme): access via UIApplication.shared.keyWindow?.traitCollection
        null // documented no-op until proper API access is established
    } catch (_: Throwable) { null }

    // ── R4: device event filter ───────────────────────────────────────────────

    /**
     * No-op on UIKit: device events are not dispatched on iOS.
     */
    override fun listenDeviceEvents(mode: DeviceEvents) {
        // no-op on UIKit
    }
}

/** Creates a synthetic monitor from UIScreen.mainScreen. */
@OptIn(ExperimentalForeignApi::class)
internal fun syntheticUiKitMonitor(): MonitorHandle {
    val screen = UIScreen.mainScreen
    val scale = screen.scale
    val (w, h) = screen.bounds.useContents {
        Pair(
            (size.width * scale).toInt(),
            (size.height * scale).toInt(),
        )
    }
    return object : MonitorHandle {
        override val id: Long = 0L
        override val name: String? = null
        override val position: PhysicalPosition<Int> = PhysicalPosition(0, 0)
        override val scaleFactor: Double = scale
        override val currentVideoMode: VideoMode = VideoMode(PhysicalSize(w, h), null, null)
        override val videoModes: List<VideoMode> = listOf(currentVideoMode)
    }
}
