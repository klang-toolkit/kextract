/**
 * Modèle d'événements de kadre-core.
 *
 * Ce fichier définit les deux hiérarchies d'événements centrales :
 * - [WindowEvent] : événements relatifs à une fenêtre (redimensionnement, clavier, pointeur, etc.)
 * - [DeviceEvent] : événements bruts de périphérique (mouvement absolu, bouton, touche)
 *
 * Il définit également les types support utilisés par ces événements :
 * [Key], [KeyState], [Modifiers], [MouseButton] et [TouchPhase].
 *
 * ## Périmètre
 * Toutes les déclarations sont 100 % commonMain (aucune dépendance native).
 * Le dispatch vers les backends reste hors scope de ce fichier.
 *
 * @since 0.1.0
 * @see WindowEvent
 * @see DeviceEvent
 */
package org.graphiks.kadre.core

import kotlin.jvm.JvmInline

// ---------------------------------------------------------------------------
// Types support
// ---------------------------------------------------------------------------

/**
 * Touches logiques du clavier.
 *
 * Couvre les lettres A–Z, les chiffres Digit0–Digit9, les touches de fonction
 * F1–F12, les touches de navigation et de modification, ainsi qu'une valeur
 * de repli [Unknown] pour les touches non reconnues.
 */
enum class Key {
    // Lettres
    /** Touche « A ». */ A,
    /** Touche « B ». */ B,
    /** Touche « C ». */ C,
    /** Touche « D ». */ D,
    /** Touche « E ». */ E,
    /** Touche « F ». */ F,
    /** Touche « G ». */ G,
    /** Touche « H ». */ H,
    /** Touche « I ». */ I,
    /** Touche « J ». */ J,
    /** Touche « K ». */ K,
    /** Touche « L ». */ L,
    /** Touche « M ». */ M,
    /** Touche « N ». */ N,
    /** Touche « O ». */ O,
    /** Touche « P ». */ P,
    /** Touche « Q ». */ Q,
    /** Touche « R ». */ R,
    /** Touche « S ». */ S,
    /** Touche « T ». */ T,
    /** Touche « U ». */ U,
    /** Touche « V ». */ V,
    /** Touche « W ». */ W,
    /** Touche « X ». */ X,
    /** Touche « Y ». */ Y,
    /** Touche « Z ». */ Z,

    // Chiffres
    /** Touche chiffre « 0 ». */ Digit0,
    /** Touche chiffre « 1 ». */ Digit1,
    /** Touche chiffre « 2 ». */ Digit2,
    /** Touche chiffre « 3 ». */ Digit3,
    /** Touche chiffre « 4 ». */ Digit4,
    /** Touche chiffre « 5 ». */ Digit5,
    /** Touche chiffre « 6 ». */ Digit6,
    /** Touche chiffre « 7 ». */ Digit7,
    /** Touche chiffre « 8 ». */ Digit8,
    /** Touche chiffre « 9 ». */ Digit9,

    // Touches de fonction
    /** Touche de fonction F1. */ F1,
    /** Touche de fonction F2. */ F2,
    /** Touche de fonction F3. */ F3,
    /** Touche de fonction F4. */ F4,
    /** Touche de fonction F5. */ F5,
    /** Touche de fonction F6. */ F6,
    /** Touche de fonction F7. */ F7,
    /** Touche de fonction F8. */ F8,
    /** Touche de fonction F9. */ F9,
    /** Touche de fonction F10. */ F10,
    /** Touche de fonction F11. */ F11,
    /** Touche de fonction F12. */ F12,

    // Touches spéciales
    /** Barre d'espace. */ Space,
    /** Touche Entrée. */ Enter,
    /** Touche Échappement. */ Escape,
    /** Touche Retour arrière. */ Backspace,
    /** Touche Tabulation. */ Tab,

    // Touches de navigation
    /** Flèche haut. */ ArrowUp,
    /** Flèche bas. */ ArrowDown,
    /** Flèche gauche. */ ArrowLeft,
    /** Flèche droite. */ ArrowRight,

    // Modificateurs
    /** Majuscule gauche. */ ShiftLeft,
    /** Majuscule droite. */ ShiftRight,
    /** Contrôle gauche. */ ControlLeft,
    /** Contrôle droite. */ ControlRight,
    /** Alt gauche. */ AltLeft,
    /** Alt droite (AltGr). */ AltRight,
    /** Meta/Commande gauche (⌘/Win). */ MetaLeft,
    /** Meta/Commande droite (⌘/Win). */ MetaRight,

    /** Touche non reconnue par la plateforme. */ Unknown,
}

/**
 * État d'une touche ou d'un bouton.
 */
enum class KeyState {
    /** La touche vient d'être enfoncée. */
    Pressed,

    /** La touche vient d'être relâchée. */
    Released,
}

/**
 * Ensemble de modificateurs clavier actifs au moment d'un événement.
 *
 * Implémenté comme un entier de bits pour minimiser les allocations.
 * Utilisez les constantes du [companion object][Modifiers.Companion] pour
 * construire des valeurs, et l'opérateur [plus] pour les combiner.
 *
 * ```kotlin
 * val mods = Modifiers.SHIFT + Modifiers.CTRL
 * assert(mods.contains(Modifiers.SHIFT))
 * assert(mods.shift)
 * assert(mods.ctrl)
 * ```
 *
 * @property bits Représentation interne sous forme de champ de bits.
 */
@JvmInline
value class Modifiers(val bits: Int) {

    /** `true` si la touche Majuscule est enfoncée. */
    val shift: Boolean get() = bits and 0x1 != 0

    /** `true` si la touche Contrôle est enfoncée. */
    val ctrl: Boolean get() = bits and 0x2 != 0

    /** `true` si la touche Alt est enfoncée. */
    val alt: Boolean get() = bits and 0x4 != 0

    /** `true` si la touche Meta (⌘ / Win) est enfoncée. */
    val meta: Boolean get() = bits and 0x8 != 0

    /**
     * Combine ces modificateurs avec [other].
     *
     * @return Nouvel ensemble contenant les modificateurs des deux opérandes.
     */
    operator fun plus(other: Modifiers): Modifiers = Modifiers(bits or other.bits)

    /**
     * Vérifie si cet ensemble contient tous les modificateurs de [other].
     *
     * @return `true` si chaque bit de [other] est présent dans cet ensemble.
     */
    fun contains(other: Modifiers): Boolean = bits and other.bits == other.bits

    companion object {
        /** Aucun modificateur actif. */
        val NONE = Modifiers(0x0)

        /** Seul le modificateur Majuscule est actif. */
        val SHIFT = Modifiers(0x1)

        /** Seul le modificateur Contrôle est actif. */
        val CTRL = Modifiers(0x2)

        /** Seul le modificateur Alt est actif. */
        val ALT = Modifiers(0x4)

        /** Seul le modificateur Meta est actif. */
        val META = Modifiers(0x8)
    }
}

/**
 * Bouton de souris.
 *
 * Les trois boutons principaux disposent d'objets nommés ; les boutons
 * supplémentaires sont représentés par [Other].
 */
sealed interface MouseButton {
    /** Bouton gauche (bouton principal). */
    data object Left : MouseButton

    /** Bouton droit (bouton secondaire / menu contextuel). */
    data object Right : MouseButton

    /** Bouton du milieu (molette ou bouton central). */
    data object Middle : MouseButton

    /**
     * Bouton supplémentaire identifié par son index numérique.
     *
     * @property button Indice du bouton (spécifique à la plateforme, commence à 4).
     */
    data class Other(val button: Int) : MouseButton
}

/**
 * Phase d'un contact tactile.
 */
enum class TouchPhase {
    /** Le contact vient d'être posé sur l'écran. */
    Started,

    /** Le contact s'est déplacé sur l'écran. */
    Moved,

    /** Le contact a été retiré de l'écran. */
    Ended,

    /** Le contact a été annulé (ex. appel entrant, geste système). */
    Cancelled,
}

// ---------------------------------------------------------------------------
// WindowEvent
// ---------------------------------------------------------------------------

/**
 * Événement émis par une fenêtre.
 *
 * Chaque variant correspond à un changement d'état ou à une action de
 * l'utilisateur sur la fenêtre ciblée.
 *
 * ### Utilisation typique
 * ```kotlin
 * fun onWindowEvent(event: WindowEvent) {
 *     when (event) {
 *         WindowEvent.CloseRequested    -> quitter()
 *         is WindowEvent.Resized        -> redimensionner(event.size)
 *         is WindowEvent.Moved          -> deplacer(event.position)
 *         is WindowEvent.ScaleFactorChanged -> mettreAJourDpi(event.factor)
 *         is WindowEvent.Focused        -> gererFocus(event.gained)
 *         is WindowEvent.KeyboardInput  -> gererClavier(event.key, event.state, event.modifiers)
 *         is WindowEvent.PointerMoved   -> gererPointeur(event.position)
 *         WindowEvent.PointerEntered    -> gererEntree()
 *         WindowEvent.PointerLeft       -> gererSortie()
 *         is WindowEvent.MouseInput     -> gererSouris(event.button, event.state)
 *         is WindowEvent.MouseWheel     -> gererMolette(event.deltaX, event.deltaY)
 *         is WindowEvent.Touch          -> gererTactile(event.phase, event.location, event.id)
 *         WindowEvent.RedrawRequested   -> redessiner()
 *         WindowEvent.Destroyed         -> libererRessources()
 *     }
 * }
 * ```
 */
sealed interface WindowEvent {

    /**
     * L'utilisateur a demandé la fermeture de la fenêtre (bouton ×, Alt+F4, ⌘W, etc.).
     *
     * L'application reste libre d'ignorer ou de différer la fermeture.
     */
    data object CloseRequested : WindowEvent

    /**
     * La fenêtre a été redimensionnée.
     *
     * @property size Nouvelle taille en pixels physiques.
     */
    data class Resized(val size: PhysicalSize<Int>) : WindowEvent

    /**
     * La fenêtre a été déplacée.
     *
     * @property position Nouvelle position du coin supérieur gauche en pixels physiques.
     */
    data class Moved(val position: PhysicalPosition<Int>) : WindowEvent

    /**
     * Le facteur d'échelle DPI de la fenêtre a changé (ex. déplacement vers un autre moniteur).
     *
     * @property factor Nouveau facteur d'échelle (ex. `2.0` sur un écran Retina).
     */
    data class ScaleFactorChanged(val factor: Double) : WindowEvent

    /**
     * La fenêtre a gagné ou perdu le focus clavier.
     *
     * @property gained `true` si la fenêtre vient de gagner le focus, `false` si elle l'a perdu.
     */
    data class Focused(val gained: Boolean) : WindowEvent

    /**
     * Un événement clavier s'est produit alors que la fenêtre avait le focus.
     *
     * @property key     Touche logique concernée.
     * @property state   État de la touche ([KeyState.Pressed] ou [KeyState.Released]).
     * @property modifiers Modificateurs actifs au moment de l'événement.
     */
    data class KeyboardInput(
        val key: Key,
        val state: KeyState,
        val modifiers: Modifiers,
        val isRepeat: Boolean = false,
    ) : WindowEvent

    /**
     * Le pointeur s'est déplacé au-dessus de la fenêtre.
     *
     * @property position Position courante du pointeur en pixels physiques (virgule flottante
     *   pour la précision sub-pixel des tablettes et trackpads).
     */
    data class PointerMoved(val position: PhysicalPosition<Double>) : WindowEvent

    /**
     * Le pointeur vient d'entrer dans la zone cliente de la fenêtre.
     */
    data object PointerEntered : WindowEvent

    /**
     * Le pointeur vient de quitter la zone cliente de la fenêtre.
     */
    data object PointerLeft : WindowEvent

    /**
     * Un bouton de souris a été enfoncé ou relâché.
     *
     * @property button Bouton concerné.
     * @property state  État du bouton ([KeyState.Pressed] ou [KeyState.Released]).
     */
    data class MouseInput(val button: MouseButton, val state: KeyState) : WindowEvent

    /**
     * La molette de souris (ou le pavé tactile) a produit un défilement.
     *
     * @property deltaX Défilement horizontal (positif vers la droite).
     * @property deltaY Défilement vertical (positif vers le bas).
     */
    data class MouseWheel(val deltaX: Double, val deltaY: Double) : WindowEvent

    /**
     * Un contact tactile a changé d'état.
     *
     * @property phase    Phase du contact.
     * @property location Position du contact en pixels physiques.
     * @property id       Identifiant unique du contact (stable entre [TouchPhase.Started] et
     *   [TouchPhase.Ended]/[TouchPhase.Cancelled]).
     */
    data class Touch(
        val phase: TouchPhase,
        val location: PhysicalPosition<Double>,
        val id: Long,
    ) : WindowEvent

    /**
     * La fenêtre doit être redessinée.
     *
     * Émis par la plateforme (vsync, invalidation de région, etc.).
     */
    data object RedrawRequested : WindowEvent

    /**
     * La fenêtre a été détruite et ses ressources natives libérées.
     *
     * Aucun autre événement ne sera émis pour cette fenêtre après [Destroyed].
     */
    data object Destroyed : WindowEvent
}

// ---------------------------------------------------------------------------
// DeviceEvent
// ---------------------------------------------------------------------------

/**
 * Événement brut de périphérique d'entrée.
 *
 * Contrairement à [WindowEvent], ces événements sont émis indépendamment de
 * la fenêtre active et reflètent l'état brut du périphérique.
 *
 * ### Utilisation typique
 * ```kotlin
 * fun onDeviceEvent(event: DeviceEvent) {
 *     when (event) {
 *         is DeviceEvent.PointerMotion -> gererMouvement(event.dx, event.dy)
 *         is DeviceEvent.Button        -> gererBouton(event.button, event.state)
 *         is DeviceEvent.Key           -> gererTouche(event.scancode, event.state)
 *     }
 * }
 * ```
 */
sealed interface DeviceEvent {

    /**
     * Mouvement brut du pointeur (delta, non limité aux bords de l'écran).
     *
     * @property dx Déplacement horizontal en pixels bruts.
     * @property dy Déplacement vertical en pixels bruts.
     */
    data class PointerMotion(val dx: Double, val dy: Double) : DeviceEvent

    /**
     * Un bouton physique de périphérique a changé d'état.
     *
     * @property button Indice du bouton (spécifique à la plateforme).
     * @property state  État du bouton ([KeyState.Pressed] ou [KeyState.Released]).
     */
    data class Button(val button: Int, val state: KeyState) : DeviceEvent

    /**
     * Une touche physique du clavier a changé d'état (identifiée par scancode).
     *
     * @property scancode Code physique de la touche (indépendant de la disposition clavier).
     * @property state    État de la touche ([KeyState.Pressed] ou [KeyState.Released]).
     */
    data class Key(val scancode: Int, val state: KeyState) : DeviceEvent
}
