/**
 * Interface principale du gestionnaire d'application kadre.
 *
 * Périmètre : interface pure Kotlin, aucune référence native.
 */
package org.graphiks.kadre.core

/**
 * Gestionnaire du cycle de vie de l'application et des événements de la boucle.
 *
 * L'implémentation de cette interface constitue le point d'entrée métier
 * de toute application kadre. La boucle d'événements invoque les méthodes
 * de ce gestionnaire en réponse aux événements système et aux changements
 * d'état du cycle de vie.
 *
 * Les méthodes [canCreateSurfaces] et [windowEvent] sont obligatoires
 * (aucune implémentation par défaut). Toutes les autres méthodes disposent
 * d'une implémentation par défaut vide et peuvent être surchargées au besoin.
 */
interface ApplicationHandler {

    /**
     * Appelé lorsque la plateforme autorise la création de surfaces de rendu.
     *
     * Obligatoire — aucune implémentation par défaut.
     *
     * C'est le moment idéal pour créer les fenêtres et initialiser le pipeline
     * de rendu.
     *
     * @param eventLoop Boucle d'événements active, permettant de créer des fenêtres.
     */
    fun canCreateSurfaces(eventLoop: ActiveEventLoop)

    /**
     * Appelé lorsqu'un événement de fenêtre est reçu.
     *
     * Obligatoire — aucune implémentation par défaut.
     *
     * Les types d'événements de fenêtre seront définis dans GRA-123.
     *
     * @param eventLoop Boucle d'événements active.
     * @param windowId  Identifiant de la fenêtre ayant émis l'événement.
     * @param event     Événement reçu (type Any en attente de GRA-123).
     */
    fun windowEvent(eventLoop: ActiveEventLoop, windowId: WindowId, event: Any)

    /**
     * Appelé lorsqu'un événement de périphérique d'entrée est reçu.
     *
     * @param eventLoop Boucle d'événements active.
     * @param deviceId  Identifiant du périphérique ayant émis l'événement.
     * @param event     Événement reçu (type Any en attente de GRA-123).
     */
    fun deviceEvent(eventLoop: ActiveEventLoop, deviceId: DeviceId, event: Any): Unit = Unit

    /**
     * Appelé au début de chaque itération de la boucle d'événements,
     * avant la distribution des événements accumulés.
     *
     * @param eventLoop  Boucle d'événements active.
     * @param startCause Cause ayant déclenché cette nouvelle itération.
     */
    fun newEvents(eventLoop: ActiveEventLoop, startCause: StartCause): Unit = Unit

    /**
     * Appelé lorsque tous les événements de l'itération courante ont été distribués
     * et que la boucle est sur le point de se mettre en attente.
     *
     * @param eventLoop Boucle d'événements active.
     */
    fun aboutToWait(eventLoop: ActiveEventLoop): Unit = Unit

    /**
     * Appelé lorsque l'application reprend son exécution après une suspension.
     *
     * @param eventLoop Boucle d'événements active.
     */
    fun resumed(eventLoop: ActiveEventLoop): Unit = Unit

    /**
     * Appelé lorsque l'application est sur le point d'être suspendue.
     *
     * @param eventLoop Boucle d'événements active.
     */
    fun suspended(eventLoop: ActiveEventLoop): Unit = Unit

    /**
     * Appelé lorsque la plateforme demande la destruction des surfaces de rendu.
     *
     * C'est le moment idéal pour libérer les ressources graphiques liées
     * aux surfaces avant leur invalidation.
     *
     * @param eventLoop Boucle d'événements active.
     */
    fun destroySurfaces(eventLoop: ActiveEventLoop): Unit = Unit
}
