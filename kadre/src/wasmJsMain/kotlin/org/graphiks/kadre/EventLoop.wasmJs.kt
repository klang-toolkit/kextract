/**
 * wasmJs implementation of the kadre event loop.
 *
 * Delegates to [org.graphiks.kadre.web.WasmJsWebEventLoop] (kadre-web-common), which
 * orchestrates the `requestAnimationFrame` loop and the dispatch of DOM events
 * via Wasm JS interop.
 *
 * kadre facade — jsMain + wasmJsMain targets.
 * #24: wiring the facade to the actual WebEventLoop.
 */
package org.graphiks.kadre

/**
 * wasmJs implementation of [EventLoop] — delegates to [org.graphiks.kadre.web.WasmJsWebEventLoop].
 */
actual class EventLoop actual constructor() {

    /**
     * Starts the browser-side event loop (wasmJs).
     *
     * @param handler Handler for the application's lifecycle and events.
     */
    actual fun runApp(handler: ApplicationHandler) {
        org.graphiks.kadre.web.WasmJsWebEventLoop().runApp(handler)
    }
}
