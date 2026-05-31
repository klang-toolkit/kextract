/**
 * Attributs de configuration d'une fenêtre.
 *
 * Périmètre : types purs Kotlin, aucune référence native.
 */
package org.graphiks.kadre.core

/**
 * Paramètres de création d'une fenêtre.
 *
 * @property title Titre affiché dans la barre de titre de la fenêtre.
 * @property size Taille initiale en pixels physiques, ou null pour utiliser la taille par défaut.
 * @property visible Indique si la fenêtre est visible au moment de sa création.
 * @property resizable Indique si l'utilisateur peut redimensionner la fenêtre.
 */
data class WindowAttributes(
    val title: String = "Kadre",
    val size: PhysicalSize<Int>? = null,
    val visible: Boolean = true,
    val resizable: Boolean = true,
)
