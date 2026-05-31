/**
 * Façade de la boucle d'événements principale de kadre.
 *
 * Périmètre : déclaration `expect` pure Kotlin, aucune référence native.
 * Les implémentations `actual` sont fournies par les modules de plateforme.
 */
package org.graphiks.kadre.core

/**
 * Point d'entrée de la boucle d'événements kadre.
 *
 * Cette classe est déclarée avec `expect` : chaque cible de compilation
 * (JVM, iOS, etc.) doit fournir une implémentation `actual` correspondante
 * dans son module de plateforme respectif.
 *
 * Utilisation typique :
 * ```kotlin
 * EventLoop().runApp(object : ApplicationHandler {
 *     override fun canCreateSurfaces(eventLoop: ActiveEventLoop) { /* ... */ }
 *     override fun windowEvent(eventLoop: ActiveEventLoop, windowId: WindowId, event: Any) { /* ... */ }
 * })
 * ```
 */
expect class EventLoop() {

    /**
     * Démarre la boucle d'événements et délègue les rappels au gestionnaire fourni.
     *
     * Cette méthode est bloquante : elle ne retourne qu'une fois la boucle terminée
     * (via [ActiveEventLoop.exit] ou fermeture de toutes les fenêtres selon la plateforme).
     *
     * @param handler Gestionnaire du cycle de vie et des événements de l'application.
     */
    fun runApp(handler: ApplicationHandler)
}
