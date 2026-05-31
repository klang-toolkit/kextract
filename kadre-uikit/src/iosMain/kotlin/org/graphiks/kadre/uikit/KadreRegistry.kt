package org.graphiks.kadre.uikit

import org.graphiks.kadre.core.ApplicationHandler

/**
 * Registre global Kadre pour iOS.
 *
 * Stocke l'ApplicationHandler avant le démarrage de UIApplicationMain.
 * Nécessaire car UIApplicationMain instancie KadreAppDelegate lui-même
 * sans permettre l'injection de dépendances au constructeur.
 *
 * Usage :
 * ```kotlin
 * startKadreApplication(myHandler)  // stocke dans KadreRegistry puis lance UIApplicationMain
 * ```
 */
object KadreRegistry {
    var handler: ApplicationHandler? = null
}
