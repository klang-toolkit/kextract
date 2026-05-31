/**
 * Mappeur de codes de touches Linux evdev vers les touches logiques [Key] de kadre-core.
 *
 * Wayland transmet des keycodes Linux bruts via wl_keyboard.key (événement « key »).
 * Ces keycodes correspondent aux codes evdev définis dans :
 *   https://github.com/torvalds/linux/blob/master/include/uapi/linux/input-event-codes.h
 *
 * Cette implémentation utilise une table statique pour la conversion, sans
 * dépendance à libxkbcommon (la gestion de disposition clavier complète est
 *planifiée ultérieurement.
 *
 * ## États de touche Wayland
 *  - WL_KEYBOARD_KEY_STATE_RELEASED = 0
 *  - WL_KEYBOARD_KEY_STATE_PRESSED  = 1
 * La répétition (key repeat) est transmise avec state = 2 (valeur non normalisée
 * dans le protocole wl_keyboard, gérée par le compositor ou le client).
 *
 * WaylandKeyMapper — Linux evdev keycodes → Key.
 */
package org.graphiks.kadre.wayland

import org.graphiks.kadre.core.Key
import org.graphiks.kadre.core.KeyState
import org.graphiks.kadre.core.Modifiers
import org.graphiks.kadre.core.WindowEvent

// ---------------------------------------------------------------------------
// Constantes wl_keyboard key state
// ---------------------------------------------------------------------------

/** wl_keyboard_key_state : touche relâchée. */
internal const val WL_KEY_RELEASED: Int = 0

/** wl_keyboard_key_state : touche enfoncée. */
internal const val WL_KEY_PRESSED: Int = 1

/** État répétition — envoyé par certains compositeurs au lieu de WL_KEY_PRESSED. */
internal const val WL_KEY_REPEATED: Int = 2

// ---------------------------------------------------------------------------
// Table Linux evdev keycode → Key
// ---------------------------------------------------------------------------

/**
 * Table de correspondance keycode Linux evdev → [Key].
 *
 * Les keycodes sont ceux définis dans `input-event-codes.h` du noyau Linux.
 * Les entrées absentes retournent [Key.Unknown].
 */
private val KEYCODE_TABLE: Map<Int, Key> = mapOf(
    // ── Lettres ──────────────────────────────────────────────────────────────
    16 to Key.Q,
    17 to Key.W,
    18 to Key.E,
    19 to Key.R,
    20 to Key.T,
    21 to Key.Y,
    22 to Key.U,
    23 to Key.I,
    24 to Key.O,
    25 to Key.P,
    30 to Key.A,
    31 to Key.S,
    32 to Key.D,
    33 to Key.F,
    34 to Key.G,
    35 to Key.H,
    36 to Key.J,
    37 to Key.K,
    38 to Key.L,
    44 to Key.Z,
    45 to Key.X,
    46 to Key.C,
    47 to Key.V,
    48 to Key.B,
    49 to Key.N,
    50 to Key.M,

    // ── Chiffres ─────────────────────────────────────────────────────────────
    2  to Key.Digit1,
    3  to Key.Digit2,
    4  to Key.Digit3,
    5  to Key.Digit4,
    6  to Key.Digit5,
    7  to Key.Digit6,
    8  to Key.Digit7,
    9  to Key.Digit8,
    10 to Key.Digit9,
    11 to Key.Digit0,

    // ── Touches spéciales ────────────────────────────────────────────────────
    1  to Key.Escape,
    14 to Key.Backspace,
    15 to Key.Tab,
    28 to Key.Enter,
    57 to Key.Space,

    // ── Touches de fonction ──────────────────────────────────────────────────
    59 to Key.F1,
    60 to Key.F2,
    61 to Key.F3,
    62 to Key.F4,
    63 to Key.F5,
    64 to Key.F6,
    65 to Key.F7,
    66 to Key.F8,
    67 to Key.F9,
    68 to Key.F10,
    87 to Key.F11,
    88 to Key.F12,

    // ── Navigation ───────────────────────────────────────────────────────────
    103 to Key.ArrowUp,
    105 to Key.ArrowLeft,
    106 to Key.ArrowRight,
    108 to Key.ArrowDown,
    102 to Key.ArrowUp,    // KEY_HOME — pas de Home dans l'enum, fallback ArrowUp
    107 to Key.ArrowDown,  // KEY_END  — pas de End dans l'enum, fallback ArrowDown
    104 to Key.ArrowUp,    // KEY_PAGEUP
    109 to Key.ArrowDown,  // KEY_PAGEDOWN

    // ── Modificateurs ────────────────────────────────────────────────────────
    42  to Key.ShiftLeft,
    54  to Key.ShiftRight,
    29  to Key.ControlLeft,
    97  to Key.ControlRight,
    56  to Key.AltLeft,
    100 to Key.AltRight,
    125 to Key.MetaLeft,
    126 to Key.MetaRight,
)

// ---------------------------------------------------------------------------
// Fonctions de conversion
// ---------------------------------------------------------------------------

/**
 * Convertit un keycode Linux evdev en touche logique [Key].
 *
 * @param keycode Code Linux evdev reçu dans wl_keyboard.key.
 * @return La touche logique correspondante, ou [Key.Unknown] si non reconnue.
 */
fun linuxKeycodeToKey(keycode: Int): Key = KEYCODE_TABLE[keycode] ?: Key.Unknown

/**
 * Convertit un état wl_keyboard_key_state en [KeyState] kadre.
 *
 * @param state État Wayland (0 = released, 1 = pressed, 2 = repeated).
 * @return [KeyState.Pressed] pour pressed ou repeated, [KeyState.Released] pour released.
 */
fun waylandKeyStateToKeyState(state: Int): KeyState = when (state) {
    WL_KEY_RELEASED -> KeyState.Released
    else            -> KeyState.Pressed // WL_KEY_PRESSED et WL_KEY_REPEATED → Pressed
}

/**
 * Construit un [WindowEvent.KeyboardInput] à partir d'un événement wl_keyboard.key.
 *
 * @param keycode   Code Linux evdev reçu dans wl_keyboard.key.
 * @param state     État wl_keyboard_key_state (0/1/2).
 * @param modifiers Modificateurs actifs au moment de l'événement.
 * @return L'événement clavier correspondant.
 */
fun mapWaylandKeyEvent(
    keycode: Int,
    state: Int,
    modifiers: Modifiers = Modifiers.NONE,
): WindowEvent.KeyboardInput = WindowEvent.KeyboardInput(
    key       = linuxKeycodeToKey(keycode),
    state     = waylandKeyStateToKeyState(state),
    modifiers = modifiers,
    isRepeat  = state == WL_KEY_REPEATED,
)
