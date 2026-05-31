/**
 * Mapper X11 pour les événements souris et focus.
 *
 * Convertit les événements XButtonEvent, XMotionEvent, XCrossingEvent et XFocusEvent
 * en événements [org.graphiks.kadre.core.WindowEvent] kadre.
 *
 * ## Struct XButtonEvent (Linux 64-bit)
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
 * 64 : state         (uint, 4)
 * 68 : button        (uint, 4)  — numéro de bouton X11 (1-based)
 * 72 : same_screen   (int,  4)
 * ```
 *
 * ## Struct XMotionEvent (Linux 64-bit)
 * ```
 * 48 : x     (int, 4)
 * 52 : y     (int, 4)
 * 64 : state (uint, 4)
 * ```
 *
 * ## Boutons X11
 * - 1 : gauche
 * - 2 : milieu
 * - 3 : droite
 * - 4 : molette vers le bas (scroll down)
 * - 5 : molette vers le haut (scroll up)
 * - 6 : molette vers la gauche (scroll left)
 * - 7 : molette vers la droite (scroll right)
 *
 * X11MouseMapper.
 */
package org.graphiks.kadre.x11

import org.graphiks.kadre.core.KeyState
import org.graphiks.kadre.core.MouseButton
import org.graphiks.kadre.core.PhysicalPosition
import org.graphiks.kadre.core.WindowEvent
import java.lang.foreign.MemorySegment
import java.lang.foreign.ValueLayout

// ── Offsets XButtonEvent / XMotionEvent ───────────────────────────────────────

private const val OFFSET_X: Long      = 48L
private const val OFFSET_Y: Long      = 52L
private const val OFFSET_BUTTON: Long = 68L

// ── Constantes de boutons X11 ─────────────────────────────────────────────────

private const val BUTTON_LEFT:         Int = 1
private const val BUTTON_MIDDLE:       Int = 2
private const val BUTTON_RIGHT:        Int = 3
private const val BUTTON_SCROLL_DOWN:  Int = 4
private const val BUTTON_SCROLL_UP:    Int = 5
private const val BUTTON_SCROLL_LEFT:  Int = 6
private const val BUTTON_SCROLL_RIGHT: Int = 7

/**
 * Mapper stateless pour les événements souris X11.
 *
 * Gère également les événements d'entrée/sortie du pointeur (EnterNotify,
 * LeaveNotify) et les événements de focus (FocusIn, FocusOut).
 */
object X11MouseMapper {

    /**
     * Convertit un XEvent souris/focus en [WindowEvent] kadre.
     *
     * @param eventSegment Segment de 96 octets contenant le XEvent.
     * @param eventType    Type d'événement X11 (extrait en amont à l'offset 0).
     * @return [WindowEvent] correspondant, ou null si le type n'est pas géré.
     */
    fun fromXEvent(eventSegment: MemorySegment, eventType: Int): WindowEvent? {
        return when (eventType) {
            ButtonPress  -> handleButton(eventSegment, KeyState.Pressed)
            ButtonRelease -> handleButton(eventSegment, KeyState.Released)
            MotionNotify  -> handleMotion(eventSegment)
            EnterNotify   -> WindowEvent.PointerEntered
            LeaveNotify   -> WindowEvent.PointerLeft
            FocusIn       -> WindowEvent.Focused(gained = true)
            FocusOut      -> WindowEvent.Focused(gained = false)
            else          -> null
        }
    }

    // ── Helpers privés ────────────────────────────────────────────────────────

    /**
     * Convertit un XButtonEvent en [WindowEvent.MouseInput] ou [WindowEvent.MouseWheel].
     *
     * Les boutons 4–7 correspondent à la molette (scroll). Pour ceux-ci :
     * - on émet un [WindowEvent.MouseWheel] uniquement sur ButtonPress ;
     * - on ignore le ButtonRelease correspondant (pas d'événement de relâchement
     *   pour la molette côté kadre).
     *
     * @param eventSegment Segment XEvent.
     * @param state        État du bouton (Pressed / Released).
     */
    private fun handleButton(
        eventSegment: MemorySegment,
        state: KeyState,
    ): WindowEvent? {
        val button = eventSegment.get(ValueLayout.JAVA_INT, OFFSET_BUTTON) and 0xFFFF

        return when (button) {
            BUTTON_LEFT   -> WindowEvent.MouseInput(MouseButton.Left,   state)
            BUTTON_MIDDLE -> WindowEvent.MouseInput(MouseButton.Middle, state)
            BUTTON_RIGHT  -> WindowEvent.MouseInput(MouseButton.Right,  state)

            // Boutons de molette : émettre uniquement sur Pressed
            BUTTON_SCROLL_DOWN  -> if (state == KeyState.Pressed) WindowEvent.MouseWheel(0.0,  1.0) else null
            BUTTON_SCROLL_UP    -> if (state == KeyState.Pressed) WindowEvent.MouseWheel(0.0, -1.0) else null
            BUTTON_SCROLL_LEFT  -> if (state == KeyState.Pressed) WindowEvent.MouseWheel(-1.0, 0.0) else null
            BUTTON_SCROLL_RIGHT -> if (state == KeyState.Pressed) WindowEvent.MouseWheel( 1.0, 0.0) else null

            // Boutons supplémentaires (8+) : MouseInput.Other
            else -> WindowEvent.MouseInput(MouseButton.Other(button), state)
        }
    }

    /**
     * Convertit un XMotionEvent en [WindowEvent.PointerMoved].
     *
     * @param eventSegment Segment XEvent.
     */
    private fun handleMotion(eventSegment: MemorySegment): WindowEvent.PointerMoved {
        val x = eventSegment.get(ValueLayout.JAVA_INT, OFFSET_X).toDouble()
        val y = eventSegment.get(ValueLayout.JAVA_INT, OFFSET_Y).toDouble()
        return WindowEvent.PointerMoved(PhysicalPosition(x, y))
    }
}
