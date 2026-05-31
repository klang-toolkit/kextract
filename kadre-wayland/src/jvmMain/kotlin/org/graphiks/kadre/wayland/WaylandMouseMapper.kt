/**
 * Mappeur d'événements pointeur Wayland vers les événements [WindowEvent] de kadre-core.
 *
 * Wayland transmet les événements souris via l'interface wl_pointer avec les callbacks :
 *  - wl_pointer.enter  → [WindowEvent.PointerEntered]
 *  - wl_pointer.leave  → [WindowEvent.PointerLeft]
 *  - wl_pointer.motion → [WindowEvent.PointerMoved]
 *  - wl_pointer.button → [WindowEvent.MouseInput]
 *  - wl_pointer.axis   → [WindowEvent.MouseWheel]
 *
 * ## Coordonnées wl_fixed
 * Wayland utilise le type wl_fixed_t (entier 24.8 virgule fixe) pour les positions.
 * Conversion : valeur_réelle = wl_fixed_t / 256.0
 *
 * ## Boutons souris (Linux evdev, BTN_*)
 *  - BTN_LEFT   = 272
 *  - BTN_RIGHT  = 273
 *  - BTN_MIDDLE = 274
 *  - BTN_SIDE   = 275 (bouton latéral avant)
 *  - BTN_EXTRA  = 276 (bouton latéral arrière)
 *
 * ## Axes wl_pointer
 *  - WL_POINTER_AXIS_VERTICAL_SCROLL   = 0
 *  - WL_POINTER_AXIS_HORIZONTAL_SCROLL = 1
 *
 * Redmine #67 : WaylandMouseMapper — wl_pointer events → WindowEvent.
 */
package org.graphiks.kadre.wayland

import org.graphiks.kadre.core.KeyState
import org.graphiks.kadre.core.MouseButton
import org.graphiks.kadre.core.PhysicalPosition
import org.graphiks.kadre.core.WindowEvent

// ---------------------------------------------------------------------------
// Constantes Linux evdev BTN_*
// ---------------------------------------------------------------------------

/** BTN_LEFT (bouton gauche) : code Linux evdev 272. */
internal const val BTN_LEFT: Int = 272

/** BTN_RIGHT (bouton droit) : code Linux evdev 273. */
internal const val BTN_RIGHT: Int = 273

/** BTN_MIDDLE (bouton du milieu) : code Linux evdev 274. */
internal const val BTN_MIDDLE: Int = 274

/** BTN_SIDE (bouton latéral avant) : code Linux evdev 275. */
internal const val BTN_SIDE: Int = 275

/** BTN_EXTRA (bouton latéral arrière) : code Linux evdev 276. */
internal const val BTN_EXTRA: Int = 276

// ---------------------------------------------------------------------------
// Constantes wl_pointer axes
// ---------------------------------------------------------------------------

/** wl_pointer_axis : défilement vertical. */
internal const val WL_POINTER_AXIS_VERTICAL_SCROLL: Int = 0

/** wl_pointer_axis : défilement horizontal. */
internal const val WL_POINTER_AXIS_HORIZONTAL_SCROLL: Int = 1

// ---------------------------------------------------------------------------
// Constantes wl_pointer button state
// ---------------------------------------------------------------------------

/** wl_pointer_button_state : bouton relâché. */
internal const val WL_POINTER_BUTTON_STATE_RELEASED: Int = 0

/** wl_pointer_button_state : bouton enfoncé. */
internal const val WL_POINTER_BUTTON_STATE_PRESSED: Int = 1

// ---------------------------------------------------------------------------
// Fonctions de conversion
// ---------------------------------------------------------------------------

/**
 * Convertit une valeur wl_fixed_t en Double.
 *
 * wl_fixed_t est un entier 24.8 virgule fixe : la valeur réelle vaut `wlFixed / 256.0`.
 *
 * @param wlFixed Valeur entière wl_fixed_t reçue dans les callbacks wl_pointer.
 * @return La coordonnée réelle en pixels physiques.
 */
fun wlFixedToDouble(wlFixed: Int): Double = wlFixed / 256.0

/**
 * Convertit un code de bouton Linux evdev en [MouseButton] kadre.
 *
 * @param button Code Linux evdev (BTN_LEFT=272, BTN_RIGHT=273, etc.).
 * @return Le bouton souris logique correspondant.
 */
fun linuxButtonToMouseButton(button: Int): MouseButton = when (button) {
    BTN_LEFT   -> MouseButton.Left
    BTN_RIGHT  -> MouseButton.Right
    BTN_MIDDLE -> MouseButton.Middle
    else       -> MouseButton.Other(button)
}

/**
 * Convertit un état wl_pointer_button_state en [KeyState] kadre.
 *
 * @param state État Wayland (0 = released, 1 = pressed).
 * @return [KeyState.Pressed] ou [KeyState.Released].
 */
fun waylandButtonStateToKeyState(state: Int): KeyState = when (state) {
    WL_POINTER_BUTTON_STATE_RELEASED -> KeyState.Released
    else                              -> KeyState.Pressed
}

// ---------------------------------------------------------------------------
// Constructeurs d'événements WindowEvent
// ---------------------------------------------------------------------------

/**
 * Construit un [WindowEvent.PointerMoved] à partir d'un événement wl_pointer.motion.
 *
 * @param xFixed Coordonnée X en wl_fixed_t.
 * @param yFixed Coordonnée Y en wl_fixed_t.
 * @return L'événement de déplacement du pointeur.
 */
fun mapWaylandPointerMotion(xFixed: Int, yFixed: Int): WindowEvent.PointerMoved =
    WindowEvent.PointerMoved(
        position = PhysicalPosition(
            x = wlFixedToDouble(xFixed),
            y = wlFixedToDouble(yFixed),
        )
    )

/**
 * Construit un [WindowEvent.MouseInput] à partir d'un événement wl_pointer.button.
 *
 * @param button Code Linux evdev du bouton (BTN_LEFT, BTN_RIGHT, etc.).
 * @param state  État wl_pointer_button_state (0 = released, 1 = pressed).
 * @return L'événement d'entrée souris correspondant.
 */
fun mapWaylandPointerButton(button: Int, state: Int): WindowEvent.MouseInput =
    WindowEvent.MouseInput(
        button = linuxButtonToMouseButton(button),
        state  = waylandButtonStateToKeyState(state),
    )

/**
 * Construit un [WindowEvent.MouseWheel] à partir d'un événement wl_pointer.axis.
 *
 * @param axis      Axe Wayland (0 = vertical, 1 = horizontal).
 * @param valueFixed Valeur de défilement en wl_fixed_t.
 * @return L'événement de molette correspondant.
 */
fun mapWaylandPointerAxis(axis: Int, valueFixed: Int): WindowEvent.MouseWheel {
    val value = wlFixedToDouble(valueFixed)
    return when (axis) {
        WL_POINTER_AXIS_VERTICAL_SCROLL   -> WindowEvent.MouseWheel(deltaX = 0.0, deltaY = value)
        WL_POINTER_AXIS_HORIZONTAL_SCROLL -> WindowEvent.MouseWheel(deltaX = value, deltaY = 0.0)
        else                               -> WindowEvent.MouseWheel(deltaX = 0.0, deltaY = 0.0)
    }
}
