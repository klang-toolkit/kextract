/**
 * iOS arm64 implementation of the event loop — delegates to kadre-uikit.
 */
package org.graphiks.kadre

actual class EventLoop actual constructor() {
    actual fun runApp(handler: ApplicationHandler) {
        org.graphiks.kadre.uikit.startKadreApplication(handler)
    }
}
