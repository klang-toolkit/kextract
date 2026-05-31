/**
 * Proxy permettant d'interagir avec la boucle d'événements depuis un autre fil d'exécution.
 *
 * Périmètre : interface pure Kotlin, aucune référence native.
 */
package org.graphiks.kadre.core

/**
 * Proxy thread-safe vers la boucle d'événements principale.
 *
 * Permet à des fils d'exécution secondaires de réveiller la boucle
 * d'événements sans y avoir accès directement.
 */
interface EventLoopProxy {

    /**
     * Réveille la boucle d'événements si elle est en attente.
     *
     * Cette méthode est sûre à appeler depuis n'importe quel fil d'exécution.
     */
    fun wakeUp()
}
