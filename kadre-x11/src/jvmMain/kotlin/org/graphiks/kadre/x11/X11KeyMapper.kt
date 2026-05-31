/**
 * Mapper X11 pour les événements clavier (XKeyEvent).
 *
 * Convertit les événements XKeyEvent bruts (KeyPress / KeyRelease) en
 * [org.graphiks.kadre.core.WindowEvent.KeyboardInput].
 *
 * ## Struct XKeyEvent (Linux 64-bit)
 * ```
 *  0 : type          (int,  4)
 *  8 : display       (ptr,  8)
 * 16 : window        (long, 8)
 * 24 : root          (long, 8)
 * 32 : subwindow     (long, 8)
 * 40 : time          (long, 8)
 * 48 : x             (int,  4)
 * 52 : y             (int,  4)
 * 56 : x_root        (int,  4)
 * 60 : y_root        (int,  4)
 * 64 : state         (uint, 4)  — masque de modificateurs
 * 68 : keycode       (uint, 4)
 * 72 : same_screen   (int,  4)
 * ```
 *
 * ## Masques de modificateurs X11
 * - ShiftMask   = 0x01
 * - ControlMask = 0x04
 * - Mod1Mask    = 0x08 (Alt)
 * - Mod4Mask    = 0x40 (Super / Meta)
 *
 * ## Keysyms
 * Les keysyms X11 pour les lettres minuscules sont simplement la valeur ASCII
 * (0x61 = 'a', …, 0x7A = 'z'). Les lettres majuscules : 0x41–0x5A.
 * Les chiffres : 0x30–0x39. Les touches spéciales commencent à 0xFF00.
 *
 * Redmine #61 : X11KeyMapper.
 */
package org.graphiks.kadre.x11

import org.graphiks.kadre.core.Key
import org.graphiks.kadre.core.KeyState
import org.graphiks.kadre.core.Modifiers
import org.graphiks.kadre.core.WindowEvent
import java.lang.foreign.MemorySegment
import java.lang.foreign.ValueLayout

// ── Offsets XKeyEvent ─────────────────────────────────────────────────────────

private const val OFFSET_STATE: Long = 64L
private const val OFFSET_KEYCODE: Long = 68L

// ── Masques de modificateurs X11 ──────────────────────────────────────────────

private const val SHIFT_MASK: Int   = 0x01
private const val CONTROL_MASK: Int = 0x04
private const val MOD1_MASK: Int    = 0x08   // Alt
private const val MOD4_MASK: Int    = 0x40   // Super / Meta (Win)

// ── Table de conversion Keysym → Key ─────────────────────────────────────────

/**
 * Table de correspondance keysym → [Key].
 *
 * Les keysyms X11 sont définis dans <X11/keysymdef.h>.
 * - Lettres minuscules : 0x61–0x7A (même valeur que ASCII)
 * - Lettres majuscules : 0x41–0x5A (même valeur que ASCII)
 * - Chiffres           : 0x30–0x39 (même valeur que ASCII)
 * - Touches spéciales  : 0xFF00+ (BackSpace, Tab, Return, Escape, …)
 * - Touches de fonction: 0xFFBE–0xFFC9 (F1–F12)
 * - Touches de nav.    : 0xFF51–0xFF54 (Left, Up, Right, Down)
 * - Modificateurs      : 0xFFE1–0xFFEA (Shift_L, Shift_R, Control_L, …)
 */
internal val KEYSYM_TABLE: Map<Int, Key> = buildMap {
    // ── Lettres (minuscules 0x61–0x7A = ASCII 'a'–'z') ───────────────────────
    put(0x61, Key.A); put(0x62, Key.B); put(0x63, Key.C); put(0x64, Key.D)
    put(0x65, Key.E); put(0x66, Key.F); put(0x67, Key.G); put(0x68, Key.H)
    put(0x69, Key.I); put(0x6A, Key.J); put(0x6B, Key.K); put(0x6C, Key.L)
    put(0x6D, Key.M); put(0x6E, Key.N); put(0x6F, Key.O); put(0x70, Key.P)
    put(0x71, Key.Q); put(0x72, Key.R); put(0x73, Key.S); put(0x74, Key.T)
    put(0x75, Key.U); put(0x76, Key.V); put(0x77, Key.W); put(0x78, Key.X)
    put(0x79, Key.Y); put(0x7A, Key.Z)

    // ── Lettres (majuscules 0x41–0x5A = ASCII 'A'–'Z') ───────────────────────
    put(0x41, Key.A); put(0x42, Key.B); put(0x43, Key.C); put(0x44, Key.D)
    put(0x45, Key.E); put(0x46, Key.F); put(0x47, Key.G); put(0x48, Key.H)
    put(0x49, Key.I); put(0x4A, Key.J); put(0x4B, Key.K); put(0x4C, Key.L)
    put(0x4D, Key.M); put(0x4E, Key.N); put(0x4F, Key.O); put(0x50, Key.P)
    put(0x51, Key.Q); put(0x52, Key.R); put(0x53, Key.S); put(0x54, Key.T)
    put(0x55, Key.U); put(0x56, Key.V); put(0x57, Key.W); put(0x58, Key.X)
    put(0x59, Key.Y); put(0x5A, Key.Z)

    // ── Chiffres (0x30–0x39 = ASCII '0'–'9') ─────────────────────────────────
    put(0x30, Key.Digit0); put(0x31, Key.Digit1); put(0x32, Key.Digit2)
    put(0x33, Key.Digit3); put(0x34, Key.Digit4); put(0x35, Key.Digit5)
    put(0x36, Key.Digit6); put(0x37, Key.Digit7); put(0x38, Key.Digit8)
    put(0x39, Key.Digit9)

    // ── Touches spéciales ─────────────────────────────────────────────────────
    put(0xFF08, Key.Backspace)   // XK_BackSpace
    put(0xFF09, Key.Tab)         // XK_Tab
    put(0xFF0D, Key.Enter)       // XK_Return
    put(0xFF1B, Key.Escape)      // XK_Escape
    put(0x0020, Key.Space)       // XK_space

    // ── Touches de navigation ─────────────────────────────────────────────────
    put(0xFF51, Key.ArrowLeft)   // XK_Left
    put(0xFF52, Key.ArrowUp)     // XK_Up
    put(0xFF53, Key.ArrowRight)  // XK_Right
    put(0xFF54, Key.ArrowDown)   // XK_Down

    // ── Touches de fonction F1–F12 ────────────────────────────────────────────
    put(0xFFBE, Key.F1);  put(0xFFBF, Key.F2);  put(0xFFC0, Key.F3)
    put(0xFFC1, Key.F4);  put(0xFFC2, Key.F5);  put(0xFFC3, Key.F6)
    put(0xFFC4, Key.F7);  put(0xFFC5, Key.F8);  put(0xFFC6, Key.F9)
    put(0xFFC7, Key.F10); put(0xFFC8, Key.F11); put(0xFFC9, Key.F12)

    // ── Modificateurs ─────────────────────────────────────────────────────────
    put(0xFFE1, Key.ShiftLeft)    // XK_Shift_L
    put(0xFFE2, Key.ShiftRight)   // XK_Shift_R
    put(0xFFE3, Key.ControlLeft)  // XK_Control_L
    put(0xFFE4, Key.ControlRight) // XK_Control_R
    put(0xFFE9, Key.AltLeft)      // XK_Alt_L
    put(0xFFEA, Key.AltRight)     // XK_Alt_R
    put(0xFFEB, Key.MetaLeft)     // XK_Super_L (Win gauche)
    put(0xFFEC, Key.MetaRight)    // XK_Super_R (Win droite)
}

/**
 * Convertit l'état (masque de modificateurs X11) en [Modifiers] kadre.
 *
 * @param state Valeur du champ `state` de XKeyEvent (unsigned int, lue comme Int).
 * @return [Modifiers] avec les bits shift, ctrl, alt, meta positionnés.
 */
internal fun stateToModifiers(state: Int): Modifiers {
    var bits = 0
    if (state and SHIFT_MASK   != 0) bits = bits or Modifiers.SHIFT.bits
    if (state and CONTROL_MASK != 0) bits = bits or Modifiers.CTRL.bits
    if (state and MOD1_MASK    != 0) bits = bits or Modifiers.ALT.bits
    if (state and MOD4_MASK    != 0) bits = bits or Modifiers.META.bits
    return Modifiers(bits)
}

/**
 * Mapper stateless pour les événements clavier X11.
 *
 * ### Détection de répétition
 * Lorsque XkbSetDetectableAutoRepeat est activé (ce qui est effectué dans
 * [X11KeyMapper.enableDetectableAutoRepeat]), les répétitions automatiques
 * de touches génèrent uniquement des KeyPress supplémentaires, sans KeyRelease
 * intermédiaire. On peut ainsi détecter qu'une touche est répétée en testant
 * si le keycode était déjà pressé au KeyPress précédent.
 *
 * Cet objet maintient un ensemble de keycodes actuellement enfoncés pour
 * effectuer cette détection.
 */
object X11KeyMapper {

    /** Ensemble des keycodes actuellement enfoncés (pour détection de répétition). */
    private val pressedKeys: MutableSet<Int> = mutableSetOf()

    /**
     * Active le mode "Detectable AutoRepeat" de Xkb.
     *
     * En mode normal, X11 simule les répétitions automatiques en envoyant des
     * paires KeyRelease / KeyPress consécutives. Avec ce mode activé, seul un
     * KeyPress supplémentaire est émis, ce qui permet à l'application de savoir
     * qu'il s'agit d'une répétition (le keycode était déjà dans [pressedKeys]).
     *
     * @param displayPtr Adresse du Display* (Long, opaque).
     */
    fun enableDetectableAutoRepeat(displayPtr: Long) {
        val handle = xkbSetDetectableAutoRepeat ?: return
        val display = MemorySegment.ofAddress(displayPtr)
        try {
            // NULL pour supported_rtrn : on n'a pas besoin du retour booléen
            handle.invokeExact(display, 1, MemorySegment.NULL) as Int
        } catch (_: Throwable) {
            // Aucune action — l'extension Xkb n'est pas disponible sur ce serveur.
        }
    }

    /**
     * Réinitialise l'état interne (ensemble des touches pressées).
     *
     * Utile lors de la perte de focus pour éviter les fausses répétitions.
     */
    fun resetState() {
        pressedKeys.clear()
    }

    /**
     * Convertit un [MemorySegment] XKeyEvent en [WindowEvent.KeyboardInput].
     *
     * @param eventSegment Segment de 96 octets contenant le XEvent.
     * @param eventType    Type d'événement X11 (2 = KeyPress, 3 = KeyRelease).
     * @param keysym       Keysym calculé en amont (par ex. via XLookupString) ;
     *                     si 0, on utilise le keycode directement pour la table.
     * @return [WindowEvent.KeyboardInput] ou null si le keysym est inconnu.
     */
    fun fromXEvent(
        eventSegment: MemorySegment,
        eventType: Int,
        keysym: Int = 0,
    ): WindowEvent.KeyboardInput? {
        val state   = eventSegment.get(ValueLayout.JAVA_INT, OFFSET_STATE)
        val keycode = eventSegment.get(ValueLayout.JAVA_INT, OFFSET_KEYCODE) and 0xFFFF

        val isPressed = eventType == KeyPress

        // Détection de répétition : si le keycode était déjà pressé au moment
        // d'un KeyPress, c'est une répétition automatique.
        val isRepeat = isPressed && pressedKeys.contains(keycode)

        // Mise à jour de l'état interne
        if (isPressed) {
            pressedKeys.add(keycode)
        } else {
            pressedKeys.remove(keycode)
        }

        // Résolution du keysym : préférer le keysym fourni par XLookupString,
        // sinon retomber sur la table (non disponible ici sans XLookupString).
        val ks = if (keysym != 0) keysym else 0

        val key = KEYSYM_TABLE[ks] ?: Key.Unknown
        val keyState = if (isPressed) KeyState.Pressed else KeyState.Released
        val modifiers = stateToModifiers(state)

        return WindowEvent.KeyboardInput(key, keyState, modifiers, isRepeat)
    }
}
