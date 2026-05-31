/**
 * Types web miroir des types kadre-core.
 *
 * Ces types reproduisent les modèles de kadre-core (Key, KeyState, Modifiers,
 * MouseButton, WindowEvent) pour les cibles JS et wasmJs, qui ne peuvent pas
 * encore dépendre de kadre-core (celui-ci n'expose pas encore de cibles JS).
 *
 * ## Migration prévue
 * Quand kadre-core sera étendu avec les cibles JS/wasmJs (ticket #32),
 * ces types seront remplacés par des typealias vers kadre-core et les
 * classes Web* seront supprimées.
 *
 * ## Contrainte
 * Fichier webMain — AUCUN import DOM autorisé.
 *
 * @since 1.0.0
 */
package org.graphiks.kadre.web

// ---------------------------------------------------------------------------
// Touches logiques
// ---------------------------------------------------------------------------

/**
 * Touches logiques du clavier (miroir web de kadre-core.Key).
 */
enum class WebKey {
    // Lettres
    A, B, C, D, E, F, G, H, I, J, K, L, M,
    N, O, P, Q, R, S, T, U, V, W, X, Y, Z,

    // Chiffres
    Digit0, Digit1, Digit2, Digit3, Digit4,
    Digit5, Digit6, Digit7, Digit8, Digit9,

    // Touches de fonction
    F1, F2, F3, F4, F5, F6, F7, F8, F9, F10, F11, F12,

    // Touches spéciales
    Space, Enter, Escape, Backspace, Tab,

    // Navigation
    ArrowUp, ArrowDown, ArrowLeft, ArrowRight,

    // Modificateurs
    ShiftLeft, ShiftRight,
    ControlLeft, ControlRight,
    AltLeft, AltRight,
    MetaLeft, MetaRight,

    Unknown,
}

// ---------------------------------------------------------------------------
// État de touche
// ---------------------------------------------------------------------------

/** État d'une touche ou d'un bouton. */
enum class WebKeyState {
    /** La touche vient d'être enfoncée. */
    Pressed,

    /** La touche vient d'être relâchée. */
    Released,
}

// ---------------------------------------------------------------------------
// Modificateurs
// ---------------------------------------------------------------------------

/**
 * Ensemble de modificateurs clavier actifs (miroir web de kadre-core.Modifiers).
 *
 * Représentation interne par champ de bits.
 */
data class WebModifiers(val bits: Int) {

    /** `true` si Majuscule est enfoncée. */
    val shift: Boolean get() = bits and 0x1 != 0

    /** `true` si Contrôle est enfoncé. */
    val ctrl: Boolean get() = bits and 0x2 != 0

    /** `true` si Alt est enfoncé. */
    val alt: Boolean get() = bits and 0x4 != 0

    /** `true` si Meta (⌘/Win) est enfoncé. */
    val meta: Boolean get() = bits and 0x8 != 0

    /** Combine ces modificateurs avec [other]. */
    operator fun plus(other: WebModifiers): WebModifiers = WebModifiers(bits or other.bits)

    /** Vérifie si cet ensemble contient tous les modificateurs de [other]. */
    fun contains(other: WebModifiers): Boolean = bits and other.bits == other.bits

    companion object {
        val NONE  = WebModifiers(0x0)
        val SHIFT = WebModifiers(0x1)
        val CTRL  = WebModifiers(0x2)
        val ALT   = WebModifiers(0x4)
        val META  = WebModifiers(0x8)
    }
}

// ---------------------------------------------------------------------------
// Bouton de souris
// ---------------------------------------------------------------------------

/** Bouton de souris (miroir web de kadre-core.MouseButton). */
sealed interface WebMouseButton {
    data object Left   : WebMouseButton
    data object Right  : WebMouseButton
    data object Middle : WebMouseButton
    data class Other(val button: Int) : WebMouseButton
}

// ---------------------------------------------------------------------------
// Événements de fenêtre web
// ---------------------------------------------------------------------------

/**
 * Événements de fenêtre émis par [WebDomBridge] (miroir web de kadre-core.WindowEvent).
 *
 * Sous-ensemble des événements DOM pertinents pour un rendu web Kadre.
 */
sealed interface WebWindowEvent {

    /** L'utilisateur a demandé la fermeture / navigation hors de la page. */
    data object CloseRequested : WebWindowEvent

    /**
     * La fenêtre/canvas a été redimensionnée.
     *
     * @property width  Nouvelle largeur en pixels physiques.
     * @property height Nouvelle hauteur en pixels physiques.
     */
    data class Resized(val width: Int, val height: Int) : WebWindowEvent

    /**
     * Un événement clavier s'est produit.
     *
     * @property key       Touche logique.
     * @property state     État (appuyé / relâché).
     * @property modifiers Modificateurs actifs.
     * @property isRepeat  `true` si l'événement est une répétition automatique.
     */
    data class KeyboardInput(
        val key: WebKey,
        val state: WebKeyState,
        val modifiers: WebModifiers,
        val isRepeat: Boolean = false,
    ) : WebWindowEvent

    /**
     * Le pointeur s'est déplacé.
     *
     * @property x Position X en pixels physiques.
     * @property y Position Y en pixels physiques.
     */
    data class PointerMoved(val x: Double, val y: Double) : WebWindowEvent

    /** Le pointeur est entré dans le canvas. */
    data object PointerEntered : WebWindowEvent

    /** Le pointeur a quitté le canvas. */
    data object PointerLeft : WebWindowEvent

    /**
     * Un bouton de souris a changé d'état.
     *
     * @property button Bouton concerné.
     * @property state  État du bouton.
     */
    data class MouseInput(val button: WebMouseButton, val state: WebKeyState) : WebWindowEvent

    /**
     * La molette a produit un défilement.
     *
     * @property deltaX Défilement horizontal.
     * @property deltaY Défilement vertical.
     */
    data class MouseWheel(val deltaX: Double, val deltaY: Double) : WebWindowEvent

    /** La fenêtre a gagné ou perdu le focus. */
    data class Focused(val gained: Boolean) : WebWindowEvent

    /** Un redessin est demandé (requestAnimationFrame). */
    data object RedrawRequested : WebWindowEvent
}
