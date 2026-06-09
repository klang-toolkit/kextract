/**
 * iOS arm64 (stub) implementation of the event loop.
 *
 * This implementation is a temporary stub. The complete implementation
 * will be provided in a dedicated iOS ticket.
 */
package org.graphiks.kadre.core

/**
 * iOS arm64 implementation of [EventLoop].
 *
 * Temporary stub — the real implementation will be provided in a dedicated ticket.
 */
actual class EventLoop actual constructor() {

    /**
     * Starts the event loop and delegates callbacks to the provided handler.
     *
     * @param handler Handler for the application lifecycle and events.
     */
    actual fun runApp(handler: ApplicationHandler) {
        throw UnsupportedOperationException(
            "EventLoop iOS arm64 not implemented — pending a dedicated ticket."
        )
    }
}
