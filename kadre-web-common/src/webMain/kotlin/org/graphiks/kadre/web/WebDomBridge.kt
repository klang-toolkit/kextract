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

    /**
     * Sets the document title (`document.title`).
     *
     * Used by [WebWindow.setTitle] to update the browser tab title.
     * Default: no-op (test / non-browser bridge).
     */
    fun setDocumentTitle(title: String) { /* no-op by default */ }

    // ── R2: fullscreen API ─────────────────────────────────────────────────────

    /**
     * Requests the browser Fullscreen API for the element identified by [canvasId].
     *
     * Calls `element.requestFullscreen()` (or the prefixed variant on older browsers).
     * Default: no-op (test / non-browser bridge).
     *
     * @param canvasId CSS id of the canvas element to go fullscreen.
     */
    fun requestFullscreen(canvasId: String) { /* no-op by default */ }

    /**
     * Exits fullscreen via `document.exitFullscreen()`.
     *
     * Default: no-op (test / non-browser bridge).
     */
    fun exitFullscreen() { /* no-op by default */ }

    // ── R3: cursor, pointer lock, theme ──────────────────────────────────────

    /**
     * Sets the CSS cursor style on the canvas identified by [canvasId].
     *
     * @param cssCursorValue CSS cursor name (e.g. "pointer", "text", "none").
     * Default: no-op (test / non-browser bridge).
     */
    fun setCssCursor(canvasId: String, cssCursorValue: String) { /* no-op by default */ }

    /**
     * Requests Pointer Lock on the canvas element (for [CursorGrabMode.Locked]).
     *
     * The browser may require a user gesture. Asynchronous — the lock is granted
     * via a `pointerlockchange` event.
     * Default: no-op.
     */
    fun requestPointerLock(canvasId: String) { /* no-op by default */ }

    /**
     * Exits Pointer Lock via `document.exitPointerLock()`.
     *
     * Default: no-op.
     */
    fun exitPointerLock() { /* no-op by default */ }

    /**
     * Returns true if `window.matchMedia('(prefers-color-scheme: dark)')` matches.
     *
     * Used by [WebEventLoop.systemTheme] to detect the system theme.
     * Default: false (test / non-browser bridge).
     */
    fun prefersDarkColorScheme(): Boolean = false

    // ── R5-CustomCursor ─────────────────────────────────────────────────────────

    /**
     * Creates a data URL from RGBA pixel data for use as a CSS custom cursor.
     *
     * The returned string can be used as `url(<dataUrl>) <hotspotX> <hotspotY>, auto`
     * in a CSS cursor property.
     *
     * Default: empty string (test / non-browser bridge).
     *
     * @param rgba     Raw RGBA bytes (4 bytes per pixel, row-major, top-left origin).
     * @param width    Image width in pixels.
     * @param height   Image height in pixels.
     * @param hotspotX Horizontal hot-spot offset from the left edge.
     * @param hotspotY Vertical hot-spot offset from the top edge.
     * @return A data URL string (e.g. "data:image/png;base64,...") or empty string on failure.
     */
    fun createCursorDataUrl(rgba: ByteArray, width: Int, height: Int, hotspotX: Int, hotspotY: Int): String = ""
}
