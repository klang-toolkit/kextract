/**
 * Point d'entrée public de la boucle d'événements kadre.
 *
 * Ce fichier déclare l'`expect class EventLoop` exposée aux consommateurs
 * de la façade `kadre`. Chaque backend fournit son implémentation `actual` :
 *   - jvmMain  → délègue directement à `kadre-appkit` (AppKit / macOS)
 *   - iosMain  → stub M1, implémentation UIKit prévue M3
 *   - androidMain → stub M1, implémentation Android prévue M3
 *
 * GRA-129 : façade KMP — M1 (jvm only).
 */
package org.graphiks.kadre

/**
 * Point d'entrée de la boucle d'événements kadre.
 *
 * Utilisation typique :
 * ```kotlin
 * EventLoop().runApp(object : ApplicationHandler {
 *     override fun canCreateSurfaces(eventLoop: ActiveEventLoop) {
 *         val window = eventLoop.createWindow(WindowAttributes(title = "Mon App"))
 *     }
 *     override fun windowEvent(eventLoop: ActiveEventLoop, windowId: WindowId, event: Any) {
 *         if (event is WindowEvent.CloseRequested) eventLoop.exit()
 *     }
 * })
 * ```
 */
expect class EventLoop() {

    /**
     * Démarre la boucle d'événements et délègue les rappels au gestionnaire fourni.
     *
     * Cette méthode est bloquante — elle ne retourne qu'à la fermeture de l'application.
     *
     * @param handler Gestionnaire du cycle de vie et des événements de l'application.
     */
    fun runApp(handler: ApplicationHandler)
}
