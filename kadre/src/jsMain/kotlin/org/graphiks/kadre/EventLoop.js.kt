/**
 * JS implementation of the kadre event loop.
 *
 * Delegates to [org.graphiks.kadre.web.JsWebEventLoop] (kadre-web-common), which
 * orchestrates the `requestAnimationFrame` loop and the dispatch of DOM events.
 *
 * kadre facade — jsMain + wasmJsMain targets.
 * #24: wiring the facade to the actual WebEventLoop.
 */
package org.graphiks.kadre

/**
 * JS implementation of [EventLoop] — delegates to [org.graphiks.kadre.web.JsWebEventLoop].
 */
actual class EventLoop actual constructor() {

    /**
     * Starts the browser-side event loop (JS/IR).
     *
     * @param handler Handler for the application's lifecycle and events.
     */
    actual fun runApp(handler: ApplicationHandler) {
        org.graphiks.kadre.web.JsWebEventLoop().runApp(handler)
    }
}
