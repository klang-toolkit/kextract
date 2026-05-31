/**
 * Interface représentant la boucle d'événements active lors d'un rappel.
 *
 * Périmètre : interface pure Kotlin, aucune référence native.
 */
package org.graphiks.kadre.core

/**
 * Accès à la boucle d'événements depuis les rappels de [ApplicationHandler].
 *
 * Cette interface est passée en paramètre lors de chaque appel entrant dans
 * le gestionnaire d'application, permettant à ce dernier de créer des fenêtres,
 * de contrôler le flux d'exécution et d'initier l'arrêt de la boucle.
 */
interface ActiveEventLoop {

    /**
     * Crée une nouvelle fenêtre avec les attributs spécifiés.
     *
     * @param attributes Paramètres de configuration de la fenêtre à créer.
     * @return La fenêtre créée.
     */
    fun createWindow(attributes: WindowAttributes): Window

    /**
     * Définit le comportement d'attente de la boucle d'événements
     * après la fin de l'itération courante.
     *
     * @param controlFlow Nouveau comportement d'attente.
     */
    fun setControlFlow(controlFlow: ControlFlow)

    /**
     * Retourne le comportement d'attente actuellement configuré.
     */
    val controlFlow: ControlFlow

    /**
     * Demande l'arrêt de la boucle d'événements.
     *
     * La boucle ne s'arrête pas immédiatement ; elle termine l'itération
     * courante avant de s'arrêter.
     */
    fun exit()

    /**
     * Indique si une demande d'arrêt a été émise.
     *
     * @return true si [exit] a été appelé et que la boucle va s'arrêter.
     */
    val isExiting: Boolean

    /**
     * Crée un proxy thread-safe vers cette boucle d'événements.
     *
     * @return Un [EventLoopProxy] utilisable depuis n'importe quel fil d'exécution.
     */
    fun createProxy(): EventLoopProxy
}
