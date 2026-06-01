/**
 * DOM bridge shared between the JS and wasmJs targets.
 *
 * This interface defines the contract for attaching a Kadre renderer to an
 * HTML element. The concrete implementations reside in jsMain and wasmJsMain
 * so that they can use the DOM APIs specific to each target.
 *
 * ## Constraint
 * This file is in webMain — NO DOM import is allowed here.
 * Only pure Kotlin types (String, Lambda, local types) are permitted.
 *
 * ## Note on kadre-core
 * The dependency on kadre-core (WindowEvent, Key, etc.) will be enabled
 * when kadre-core exposes JS/wasmJs targets (ticket #32).
 * In the meantime, [WindowEvent] is defined locally in this module.
 *
 * @since 1.0.0
 */
package org.graphiks.kadre.web

/**
 * Binding interface between the browser DOM and the Kadre engine.
 *
 * Implemented separately for JS (via [org.w3c.dom]) and wasmJs (via Wasm JS interop).
 */
interface WebDomBridge {

    /**
     * Attaches the Kadre renderer to the HTML element identified by [targetElementId].
     *
     * @param targetElementId Value of the `id` attribute of the target element in the DOM.
     */
    fun attach(targetElementId: String)

    /**
     * Detaches the Kadre renderer from the DOM element and releases the associated resources.
     */
    fun detach()

    /**
     * Callback invoked on each window event produced by the DOM bridge.
     *
     * May be null if no listener is registered.
     */
    var onWindowEvent: ((WebWindowEvent) -> Unit)?

    /**
     * Ensures a DOM canvas matching [attrs] is present and adds it
     * to the DOM if necessary (`appendToBody` mode).
     *
     * Default implementation: no-op (returns the id without touching the DOM).
     * The concrete implementations ([JsWebDomBridge], [WasmJsWebDomBridge])
     * override it to create a real `<canvas>` when requested.
     *
     * @return the final CSS identifier of the canvas (to then pass to [attach]).
     */
    fun ensureCanvas(attrs: WebWindowAttributes): String = attrs.effectiveCanvasId

    /**
     * Returns the current `window.devicePixelRatio`.
     *
     * Used by [WebWindow] to initialize [WebWindow.scaleFactor] synchronously
     * at window-creation time (before the first [WebWindowEvent.ScaleFactorChanged]).
     *
     * Default: 1.0 (test / no-op bridge).
     */
    fun readDevicePixelRatio(): Double = 1.0

    /**
     * Returns the current CSS dimensions of the canvas identified by [canvasId]
     * in physical pixels (CSS pixels × devicePixelRatio, rounded to nearest Int).
     *
     * Used by [WebWindow] to initialize [WebWindow.innerSize] synchronously at
     * window-creation time (before the first [WebWindowEvent.Resized]).
     *
     * Default: 0×0 (test / no-op bridge).
     */
    fun readCanvasPhysicalSize(canvasId: String): Pair<Int, Int> = Pair(0, 0)
}
