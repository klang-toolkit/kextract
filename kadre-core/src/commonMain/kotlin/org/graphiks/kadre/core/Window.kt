/**
 * Interface représentant une fenêtre native gérée par kadre.
 *
 * Périmètre : interface pure Kotlin, aucune référence native.
 */
package org.graphiks.kadre.core

/**
 * Abstraction d'une fenêtre native créée par la boucle d'événements.
 *
 * Les implémentations concrètes sont fournies par les modules de plateforme
 * (kadre-appkit, etc.).
 */
interface Window {

    /** Identifiant unique de la fenêtre. */
    val id: WindowId

    /**
     * Retourne le handle natif de la surface de rendu.
     *
     * Le type concret sera `RawWindowHandle` une fois GRA-122 mergé ;
     * déclaré `Any` pour que commonMain reste indépendant de la plateforme.
     */
    val rawWindowHandle: Any

    /**
     * Retourne le handle natif de l'affichage.
     *
     * Le type concret sera `RawDisplayHandle` une fois GRA-122 mergé ;
     * déclaré `Any` pour que commonMain reste indépendant de la plateforme.
     */
    val rawDisplayHandle: Any

    /**
     * Demande un rafraîchissement (redraw) de la fenêtre à la prochaine itération.
     */
    fun requestRedraw()

    /**
     * Définit le titre affiché dans la barre de titre de la fenêtre.
     *
     * @param title Nouveau titre de la fenêtre.
     */
    fun setTitle(title: String)

    /**
     * Retourne la taille interne de la fenêtre en pixels physiques
     * (surface de rendu, sans les décorations).
     */
    val innerSize: PhysicalSize<Int>

    /**
     * Retourne la taille externe de la fenêtre en pixels physiques
     * (surface de rendu plus les décorations de la plateforme).
     */
    val outerSize: PhysicalSize<Int>

    /**
     * Retourne le facteur d'échelle entre les pixels logiques et physiques
     * pour cette fenêtre.
     */
    val scaleFactor: Double

    /**
     * Rend la fenêtre visible ou invisible.
     *
     * @param visible true pour afficher la fenêtre, false pour la masquer.
     */
    fun setVisible(visible: Boolean)

    /**
     * Ferme la fenêtre.
     *
     * Une fois fermée, la fenêtre n'émet plus d'événements et son identifiant
     * devient invalide.
     */
    fun close()
}
