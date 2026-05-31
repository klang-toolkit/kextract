/**
 * Implémentation Android de la boucle d'événements — délègue à kadre-android.
 *
 * Sur Android, le point d'entrée est l'Activity. [runApp] enregistre le
 * [ApplicationHandler] dans [org.graphiks.kadre.android.AndroidKadreRuntime]
 * pour qu'il soit récupéré par [org.graphiks.kadre.android.KadreActivity].
 */
package org.graphiks.kadre

import org.graphiks.kadre.android.AndroidKadreRuntime

actual class EventLoop actual constructor() {

    actual fun runApp(handler: ApplicationHandler) {
        AndroidKadreRuntime.currentHandler = handler
        // On Android the Activity lifecycle is the entry point.
        // The handler is retrieved by KadreActivity.createHandler() via AndroidKadreRuntime.
    }
}
