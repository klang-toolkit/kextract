/**
 * Implémentation iOS Simulator arm64 (stub) de la boucle d'événements.
 *
 * Cette implémentation est un stub provisoire. L'implémentation complète
 * sera fournie dans un ticket dédié iOS.
 */
package org.graphiks.kadre.core

/**
 * Implémentation iOS Simulator arm64 de [EventLoop].
 *
 * Stub provisoire — l'implémentation réelle sera apportée dans un ticket dédié.
 */
actual class EventLoop actual constructor() {

    /**
     * Démarre la boucle d'événements et délègue les rappels au gestionnaire fourni.
     *
     * @param handler Gestionnaire du cycle de vie et des événements de l'application.
     */
    actual fun runApp(handler: ApplicationHandler) {
        throw UnsupportedOperationException(
            "EventLoop iOS Simulator arm64 non implémenté — en attente d'un ticket dédié."
        )
    }
}
