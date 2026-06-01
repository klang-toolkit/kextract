package org.graphiks.kadre.uikit

import org.graphiks.kadre.core.*

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
        windows.forEach { handler.windowEvent(this, it.id, WindowEvent.Focused(gained)) }
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
}
