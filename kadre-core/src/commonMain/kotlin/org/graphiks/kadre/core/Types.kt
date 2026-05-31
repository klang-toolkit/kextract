/**
 * Types de base utilisés dans kadre-core.
 *
 * Contient les identifiants, les types de contrôle de flux et les causes
 * de démarrage de la boucle d'événements.
 *
 * Périmètre : types purs Kotlin, aucune référence native.
 */
package org.graphiks.kadre.core

import kotlin.jvm.JvmInline

/**
 * Identifiant unique d'une fenêtre.
 *
 * Encapsule un entier long opaque assigné par la plateforme.
 */
@JvmInline
value class WindowId(val value: Long)

/**
 * Identifiant unique d'un périphérique d'entrée.
 *
 * Encapsule un entier long opaque assigné par la plateforme.
 */
@JvmInline
value class DeviceId(val value: Long)

/**
 * Contrôle du flux d'exécution de la boucle d'événements.
 *
 * Permet à l'application de dicter le comportement d'attente entre les itérations
 * de la boucle d'événements.
 */
sealed class ControlFlow {
    /** Attend indéfiniment jusqu'au prochain événement. */
    object Wait : ControlFlow()

    /** Retourne immédiatement sans attendre d'événement. */
    object Poll : ControlFlow()

    /**
     * Attend jusqu'à un instant précis (en millisecondes depuis l'époque Unix)
     * ou jusqu'au prochain événement.
     *
     * @param instant Instant cible exprimé en millisecondes depuis l'époque Unix.
     */
    data class WaitUntil(val instant: Long) : ControlFlow()
}

/**
 * Cause du démarrage ou de la reprise d'une itération de la boucle d'événements.
 */
sealed class StartCause {
    /** La boucle d'événements vient d'être initialisée. */
    object Init : StartCause()

    /** La boucle d'événements a été interrogée (mode Poll). */
    object Poll : StartCause()

    /**
     * L'attente a été annulée avant l'instant prévu.
     *
     * @param requestedResume Instant cible original, ou null s'il n'était pas défini.
     */
    data class WaitCancelled(val requestedResume: Long? = null) : StartCause()

    /**
     * L'instant cible d'attente a été atteint.
     *
     * @param requestedResume Instant cible original.
     * @param start Instant auquel la reprise a effectivement eu lieu.
     */
    data class ResumeTimeReached(val requestedResume: Long, val start: Long) : StartCause()
}

// PhysicalSize est défini dans Dpi.kt (GRA-121) — stub supprimé.
