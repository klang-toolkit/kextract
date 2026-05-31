/**
 * Implémentation wasmJs (stub) de la boucle d'événements kadre-core.
 *
 * Ticket #28 : ajout des cibles JS/wasmJs à kadre-core pour permettre
 * à la façade `kadre` d'exposer EventLoop aux cibles navigateur.
 * L'implémentation complète sera réalisée dans le ticket #24 (WebEventLoop).
 */
package org.graphiks.kadre.core

/**
 * Implémentation wasmJs de [EventLoop].
 *
 * Stub provisoire — l'implémentation réelle sera apportée dans le ticket #24.
 */
actual class EventLoop actual constructor() {

    /**
     * Démarre la boucle d'événements et délègue les rappels au gestionnaire fourni.
     *
     * @param handler Gestionnaire du cycle de vie et des événements de l'application.
     * @throws UnsupportedOperationException Toujours — implémentation complète dans #24.
     */
    actual fun runApp(handler: ApplicationHandler) {
        throw UnsupportedOperationException(
            "EventLoop wasmJs non implémenté — en attente du ticket #24 (WebEventLoop)."
        )
    }
}
