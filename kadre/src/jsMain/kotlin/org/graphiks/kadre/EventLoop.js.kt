/**
 * Implémentation JS de la boucle d'événements kadre.
 *
 * Délègue à [org.graphiks.kadre.web.JsWebEventLoop] (kadre-web-common), qui
 * orchestre la boucle `requestAnimationFrame` et le dispatch des événements DOM.
 *
 * façade kadre — cibles jsMain + wasmJsMain.
 * #24 : câblage de la façade vers le WebEventLoop réel.
 */
package org.graphiks.kadre

/**
 * Implémentation JS de [EventLoop] — délègue à [org.graphiks.kadre.web.JsWebEventLoop].
 */
actual class EventLoop actual constructor() {

    /**
     * Démarre la boucle d'événements côté navigateur (JS/IR).
     *
     * @param handler Gestionnaire du cycle de vie et des événements de l'application.
     */
    actual fun runApp(handler: ApplicationHandler) {
        org.graphiks.kadre.web.JsWebEventLoop().runApp(handler)
    }
}
