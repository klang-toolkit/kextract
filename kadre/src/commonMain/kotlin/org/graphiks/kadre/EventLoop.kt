/**
 * Public entry point of the kadre event loop.
 *
 * This file declares the `expect class EventLoop` exposed to consumers
 * of the `kadre` facade. Each backend provides its `actual` implementation:
 *   - jvmMain  → delegates directly to `kadre-appkit` (AppKit / macOS)
 *   - iosMain  → M1 stub, UIKit implementation planned for M3
 *   - androidMain → M1 stub, Android implementation planned for M3
 *
 * GRA-129: KMP facade — M1 (jvm only).
 */
package org.graphiks.kadre

/**
 * Entry point of the kadre event loop.
 *
 * Typical usage:
 * ```kotlin
 * EventLoop().runApp(object : ApplicationHandler {
 *     override fun canCreateSurfaces(eventLoop: ActiveEventLoop) {
 *         val window = eventLoop.createWindow(WindowAttributes(title = "My App"))
 *     }
 *     override fun windowEvent(eventLoop: ActiveEventLoop, windowId: WindowId, event: WindowEvent) {
 *         if (event is WindowEvent.CloseRequested) eventLoop.exit()
 *     }
 * })
 * ```
 */
expect class EventLoop() {

    /**
     * Starts the event loop and delegates callbacks to the provided handler.
     *
     * This method is blocking — it only returns when the application closes.
     *
     * @param handler Handler for the application's lifecycle and events.
     */
    fun runApp(handler: ApplicationHandler)
}
