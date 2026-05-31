/**
 * Implémentation iOS Simulator arm64 de la boucle d'événements — délègue à kadre-uikit.
 */
package org.graphiks.kadre

actual class EventLoop actual constructor() {
    actual fun runApp(handler: ApplicationHandler) {
        org.graphiks.kadre.uikit.startKadreApplication(handler)
    }
}
