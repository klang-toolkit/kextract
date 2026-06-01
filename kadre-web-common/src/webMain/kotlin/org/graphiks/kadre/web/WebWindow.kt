/**
 * Web implementation of the [Window] interface.
 *
 * ## Canvas
 * The target canvas is identified by [attrs.title] used as the CSS `id`.
 * If the title is empty, the default identifier `"kadre-canvas"` is used.
 * The actual DOM attachment is delegated to [WebDomBridge.attach].
 *
 * ## webMain constraint
 * This file resides in `webMain` — NO DOM import is allowed here.
 * The DOM types (HTMLCanvasElement, etc.) are handled exclusively in
 * `jsMain` via [JsWebDomBridge] and in `wasmJsMain` via `WasmJsWebDomBridge`.
 *
 * ## Dynamic size and scale
 * [innerSize], [outerSize] and [scaleFactor] reflect the real canvas dimensions
 * and `devicePixelRatio`. The values are updated via [updatePhysicalSize] and
 * [updateScaleFactor], called by [WebEventLoop] when the bridge emits
 * [WebWindowEvent.Resized] and [WebWindowEvent.ScaleFactorChanged].
 *
 * @param attrs   Window creation attributes (title used as CSS id).
 * @param bridge  DOM bridge used to attach / detach the canvas.
 *
 * @since 1.0.0
 */
package org.graphiks.kadre.web

import org.graphiks.kadre.core.PhysicalSize
import org.graphiks.kadre.core.RawDisplayHandle
import org.graphiks.kadre.core.RawWindowHandle
import org.graphiks.kadre.core.Window
import org.graphiks.kadre.core.WindowAttributes
import org.graphiks.kadre.core.WindowId

/**
 * [Window] implementation for the web backends (JS and wasmJs).
 *
 * Resolves the canvas via [attrs.title] as a CSS identifier, or dynamically
 * creates a `"kadre-canvas"` canvas if the title is empty.
 *
 * [setTitle] is a no-op on the Web side (pages have no title bar
 * in the native-window sense).
 */
class WebWindow(
    /**
     * CSS identifier of the target canvas element.
     *
     * Must match a `<canvas>` already present in the DOM. Use
     * [WebDomBridge.ensureCanvas] (or [WebEventLoop.createWindow] with a
     * [WebWindowAttributes]) for auto-creation.
     */
    private val canvasElementId: String,
    private val bridge: WebDomBridge,
) : Window {

    /**
     * Builds a Web window from the core [WindowAttributes] contract.
     *
     * **Legacy**: uses `attrs.title` as the canvas CSS `id`, or
     * `"kadre-canvas"` by default — a non-idiomatic convention (the title is
     * semantically unrelated to a DOM `id`). Prefer
     * `WebEventLoop.createWindow(WebWindowAttributes)`.
     */
    @Deprecated(
        "Convention title-as-canvasId. Utiliser WebEventLoop.createWindow(WebWindowAttributes) " +
                "pour cibler explicitement un canvas DOM par son id.",
    )
    constructor(attrs: WindowAttributes, bridge: WebDomBridge)
            : this(attrs.title.ifEmpty { WebWindowAttributes.DEFAULT_CANVAS_ID }, bridge)


    /**
     * Unique identifier of this web window.
     *
     * Generated from the hash of [canvasElementId] to be stable
     * and reproducible on the same page.
     */
    override val id: WindowId = WindowId(canvasElementId.hashCode().toLong())

    /**
     * Raw handle of the rendering surface — identifies the canvas by its CSS id.
     *
     * Returns [RawWindowHandle.Web] with [canvasElementId].
     * Declared `Any` in [Window] to stay platform-independent.
     */
    override val rawWindowHandle: RawWindowHandle
        get() = RawWindowHandle.Web(canvasElementId = canvasElementId)

    /**
     * Raw handle of the display — web singleton with no additional pointer.
     *
     * Returns [RawDisplayHandle.Web].
     * Declared `Any` in [Window] to stay platform-independent.
     */
    override val rawDisplayHandle: RawDisplayHandle
        get() = RawDisplayHandle.Web

    // -------------------------------------------------------------------------
    // Dynamic size / scale state — updated by WebEventLoop via updatePhysicalSize
    // and updateScaleFactor when the bridge emits Resized / ScaleFactorChanged.
    // -------------------------------------------------------------------------

    /** Current physical size of the canvas in pixels. Default 0×0 until first Resized event. */
    private var _physicalSize: PhysicalSize<Int> = PhysicalSize(0, 0)

    /** Current device pixel ratio. Default 1.0 until first ScaleFactorChanged event. */
    private var _scaleFactor: Double = 1.0

    /**
     * Updates the stored physical size.
     *
     * Called by [WebEventLoop] when the bridge fires [WebWindowEvent.Resized].
     * Dimensions are already in physical pixels (CSS pixels × devicePixelRatio).
     */
    internal fun updatePhysicalSize(width: Int, height: Int) {
        _physicalSize = PhysicalSize(width, height)
    }

    /**
     * Updates the stored scale factor.
     *
     * Called by [WebEventLoop] when the bridge fires [WebWindowEvent.ScaleFactorChanged].
     */
    internal fun updateScaleFactor(factor: Double) {
        _scaleFactor = factor
    }

    /**
     * Inner size of the window (rendering surface) in physical pixels.
     *
     * Reflects the real canvas dimensions as reported by the ResizeObserver.
     * Returns 0×0 until the first [WebWindowEvent.Resized] event.
     */
    override val innerSize: PhysicalSize<Int>
        get() = _physicalSize

    /**
     * Outer size of the window in physical pixels.
     *
     * Identical to [innerSize] on the Web side (no native decorations).
     */
    override val outerSize: PhysicalSize<Int>
        get() = _physicalSize

    /**
     * Scale factor between logical and physical pixels (`window.devicePixelRatio`).
     *
     * Returns 1.0 until the first [WebWindowEvent.ScaleFactorChanged] event,
     * then reflects the actual device pixel ratio.
     */
    override val scaleFactor: Double
        get() = _scaleFactor

    /**
     * Requests a redraw of the window via [WebWindowEvent.RedrawRequested].
     *
     * Emits the event to [WebDomBridge.onWindowEvent] if it is registered.
     */
    override fun requestRedraw() {
        bridge.onWindowEvent?.invoke(WebWindowEvent.RedrawRequested)
    }

    /**
     * No-op on the Web side.
     *
     * Web pages have no title bar in the sense of a native window.
     * `document.title` could be updated here but is out of scope for this ticket.
     */
    override fun setTitle(title: String) {
        // no-op Web — document.title would be the target, out of scope for ticket #25
    }

    /**
     * Handles canvas visibility via CSS (future) — no-op in this version.
     *
     * @param visible true to show, false to hide.
     */
    override fun setVisible(visible: Boolean) {
        // no-op Web — CSS display could be driven here (out of scope for #25)
    }

    /**
     * Closes the web window by detaching the DOM bridge.
     *
     * Delegates to [WebDomBridge.detach] to remove the DOM listeners
     * and release the associated resources.
     */
    override fun close() {
        bridge.detach()
    }
}
