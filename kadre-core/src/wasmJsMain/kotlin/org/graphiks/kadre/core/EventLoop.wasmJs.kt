/**
 * wasmJs (stub) implementation of the kadre-core event loop.
 *
 * Ticket #28: adding the JS/wasmJs targets to kadre-core so the `kadre`
 * facade can expose EventLoop to browser targets.
 * The complete implementation will be done in ticket #24 (WebEventLoop).
 */
package org.graphiks.kadre.core

/**
 * wasmJs implementation of [EventLoop].
 *
 * Temporary stub — the real implementation will be provided in ticket #24.
 */
actual class EventLoop actual constructor() {

    /**
     * Starts the event loop and delegates callbacks to the provided handler.
     *
     * @param handler Handler for the application lifecycle and events.
     * @throws UnsupportedOperationException Always — complete implementation in #24.
     */
    actual fun runApp(handler: ApplicationHandler) {
        throw UnsupportedOperationException(
            "EventLoop wasmJs non implémenté — en attente du ticket #24 (WebEventLoop)."
        )
    }
}
