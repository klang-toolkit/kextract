/**
 * Facade of the main kadre event loop.
 *
 * Scope: pure Kotlin `expect` declaration, no native reference.
 * The `actual` implementations are provided by the platform modules.
 */
package org.graphiks.kadre.core

/**
 * Entry point of the kadre event loop.
 *
 * This class is declared with `expect`: each compilation target
 * (JVM, iOS, etc.) must provide a corresponding `actual` implementation
 * in its respective platform module.
 *
 * Typical usage:
 * ```kotlin
 * EventLoop().runApp(object : ApplicationHandler {
 *     override fun canCreateSurfaces(eventLoop: ActiveEventLoop) { /* ... */ }
 *     override fun windowEvent(eventLoop: ActiveEventLoop, windowId: WindowId, event: WindowEvent) { /* ... */ }
 * })
 * ```
 */
expect class EventLoop() {

    /**
     * Starts the event loop and delegates callbacks to the provided handler.
     *
     * This method is blocking: it returns only once the loop has ended
     * (via [ActiveEventLoop.exit] or closing all windows depending on the platform).
     *
     * @param handler Handler for the application lifecycle and events.
     */
    fun runApp(handler: ApplicationHandler)
}
