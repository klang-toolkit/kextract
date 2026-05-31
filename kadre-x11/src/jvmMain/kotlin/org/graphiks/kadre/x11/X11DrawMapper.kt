/**
 * Mapper X11 pour les événements de dessin/cycle de vie de fenêtre.
 *
 * Convertit les événements Expose, ConfigureNotify et ClientMessage en
 * événements [org.graphiks.kadre.core.WindowEvent] kadre.
 *
 * ## Struct XExposeEvent (type = 12 Expose)
 * ```
 *  0 : type   (int, 4)
 * ...
 * 40 : count  (int, 4) — 0 = dernier expose de la séquence
 * ```
 *
 * ## Struct XConfigureEvent (type = 22 ConfigureNotify)
 * ```
 *  0 : type         (int,  4)
 * ...
 * 24 : x            (int,  4)
 * 28 : y            (int,  4)
 * 32 : width        (int,  4)
 * 36 : height       (int,  4)
 * 40 : border_width (int,  4)
 * ```
 *
 * ## Struct XClientMessageEvent (type = 33 ClientMessage)
 * ```
 *  0 : type         (int,  4)
 * ...
 * 56 : message_type (Atom = long, 8)
 * 64 : data.l[0]   (long, 8) — premier atome du message
 * ```
 *
 * ## Lecture Xft.dpi
 * La fonction [readXftDpi] utilise XResourceManagerString pour lire la base
 * de données de ressources X11 et en extraire "Xft.dpi: <valeur>".
 *
 * Redmine #63 : X11DrawMapper.
 */
package org.graphiks.kadre.x11

import org.graphiks.kadre.core.PhysicalPosition
import org.graphiks.kadre.core.PhysicalSize
import org.graphiks.kadre.core.WindowEvent
import java.lang.foreign.MemorySegment
import java.lang.foreign.ValueLayout

// ── Offsets XExposeEvent ──────────────────────────────────────────────────────

private const val EXPOSE_OFFSET_COUNT: Long = 40L

// ── Offsets XConfigureEvent ───────────────────────────────────────────────────

private const val CONFIGURE_OFFSET_X: Long      = 24L
private const val CONFIGURE_OFFSET_Y: Long      = 28L
private const val CONFIGURE_OFFSET_WIDTH: Long  = 32L
private const val CONFIGURE_OFFSET_HEIGHT: Long = 36L

// ── Offsets XClientMessageEvent ───────────────────────────────────────────────

private const val CLIENT_MSG_OFFSET_DATA_L0: Long = 64L

/**
 * Mapper stateless pour les événements Expose, ConfigureNotify et ClientMessage.
 */
object X11DrawMapper {

    /**
     * Convertit un XEvent relatif au cycle de vie d'une fenêtre en [WindowEvent] kadre.
     *
     * @param eventSegment  Segment de 96 octets contenant le XEvent.
     * @param eventType     Type d'événement X11 (extrait en amont à l'offset 0).
     * @param window        Fenêtre X11 associée (pour mise à jour de la taille interne).
     * @param wmDeleteWindow Atome WM_DELETE_WINDOW de la session.
     * @return [WindowEvent] correspondant, ou null si le type/la condition ne correspond pas.
     */
    fun fromXEvent(
        eventSegment: MemorySegment,
        eventType: Int,
        window: X11Window?,
        wmDeleteWindow: Long,
    ): WindowEvent? {
        return when (eventType) {
            Expose         -> handleExpose(eventSegment)
            ConfigureNotify -> handleConfigureNotify(eventSegment, window)
            ClientMessage   -> handleClientMessage(eventSegment, wmDeleteWindow)
            else            -> null
        }
    }

    // ── Helpers privés ────────────────────────────────────────────────────────

    /**
     * Gère un événement Expose.
     *
     * X11 peut envoyer plusieurs événements Expose consécutifs pour la même
     * région. Le champ `count` indique combien d'Expose supplémentaires vont
     * suivre. On émet [WindowEvent.RedrawRequested] uniquement lorsque `count == 0`
     * (i.e. le dernier de la séquence), pour éviter les redessinages intermédiaires.
     *
     * @param eventSegment Segment XEvent.
     */
    private fun handleExpose(eventSegment: MemorySegment): WindowEvent? {
        val count = eventSegment.get(ValueLayout.JAVA_INT, EXPOSE_OFFSET_COUNT)
        return if (count == 0) WindowEvent.RedrawRequested else null
    }

    /**
     * Gère un événement ConfigureNotify.
     *
     * Émet :
     * - [WindowEvent.Resized] si la taille a changé par rapport à [X11Window.innerSize].
     * - [WindowEvent.Moved] avec la nouvelle position (x, y).
     *
     * Note : on retourne uniquement le premier événement significatif. La boucle
     * d'événements peut appeler cette fonction deux fois si besoin, ou on pourrait
     * retourner une liste. Pour rester simple, on priorise Resized sur Moved.
     *
     * @param eventSegment Segment XEvent.
     * @param window       Fenêtre X11 dont la taille est mise à jour.
     */
    private fun handleConfigureNotify(
        eventSegment: MemorySegment,
        window: X11Window?,
    ): WindowEvent? {
        val x      = eventSegment.get(ValueLayout.JAVA_INT, CONFIGURE_OFFSET_X)
        val y      = eventSegment.get(ValueLayout.JAVA_INT, CONFIGURE_OFFSET_Y)
        val width  = eventSegment.get(ValueLayout.JAVA_INT, CONFIGURE_OFFSET_WIDTH)
        val height = eventSegment.get(ValueLayout.JAVA_INT, CONFIGURE_OFFSET_HEIGHT)

        // Vérifier si la taille a changé par rapport à la taille connue de la fenêtre
        val sizeChanged = window == null ||
            window.innerSize.width  != width ||
            window.innerSize.height != height

        // Mettre à jour la taille interne de la fenêtre
        window?.onConfigureNotify(width, height)

        return when {
            sizeChanged && width > 0 && height > 0 ->
                WindowEvent.Resized(PhysicalSize(width, height))
            else ->
                WindowEvent.Moved(PhysicalPosition(x, y))
        }
    }

    /**
     * Gère un événement ClientMessage.
     *
     * Si data.l[0] correspond à l'atome WM_DELETE_WINDOW, émet
     * [WindowEvent.CloseRequested] pour demander la fermeture propre.
     *
     * @param eventSegment   Segment XEvent.
     * @param wmDeleteWindow Atome WM_DELETE_WINDOW (Long, unsigned long X11).
     */
    private fun handleClientMessage(
        eventSegment: MemorySegment,
        wmDeleteWindow: Long,
    ): WindowEvent? {
        val atom = eventSegment.get(ValueLayout.JAVA_LONG, CLIENT_MSG_OFFSET_DATA_L0)
        return if (atom == wmDeleteWindow) WindowEvent.CloseRequested else null
    }
}

// ── Lecture Xft.dpi ───────────────────────────────────────────────────────────

/**
 * Lit le facteur DPI depuis la base de données de ressources X11.
 *
 * Utilise [xResourceManagerString] pour obtenir la chaîne RESOURCE_MANAGER du
 * serveur X, puis cherche la propriété "Xft.dpi:" et en retourne la valeur
 * sous forme de [Double].
 *
 * La chaîne de ressources a typiquement la forme :
 * ```
 * Xft.dpi:	96
 * Xft.antialias:	1
 * ...
 * ```
 *
 * @param displayPtr Adresse du Display* (Long, opaque).
 * @return Valeur DPI divisée par 96.0 (ex. 1.0 pour 96 dpi, 2.0 pour 192 dpi),
 *         ou 1.0 si XResourceManagerString retourne NULL ou si Xft.dpi est absent.
 */
fun readXftDpi(displayPtr: Long): Double {
    val handle = xResourceManagerString ?: return 1.0
    val display = MemorySegment.ofAddress(displayPtr)
    return try {
        val result = handle.invokeExact(display) as MemorySegment
        if (result == MemorySegment.NULL) return 1.0

        // Lire la chaîne null-terminée retournée par XResourceManagerString
        val resourceString = result.reinterpret(Long.MAX_VALUE)
            .getString(0, Charsets.UTF_8)

        // Chercher "Xft.dpi:" dans la chaîne de ressources
        parseXftDpi(resourceString)
    } catch (_: Throwable) {
        1.0
    }
}

/**
 * Extrait la valeur de Xft.dpi depuis une chaîne de ressources X11.
 *
 * Cherche le pattern "Xft.dpi:" suivi d'un nombre (entier ou décimal).
 * La valeur est retournée divisée par 96.0 pour obtenir un facteur d'échelle.
 *
 * @param resourceString Contenu de la chaîne RESOURCE_MANAGER.
 * @return Facteur d'échelle DPI (valeur/96), ou 1.0 si absent/invalide.
 */
internal fun parseXftDpi(resourceString: String): Double {
    for (line in resourceString.lineSequence()) {
        val trimmed = line.trim()
        if (trimmed.startsWith("Xft.dpi:")) {
            val value = trimmed.removePrefix("Xft.dpi:").trim().toDoubleOrNull()
            if (value != null && value > 0.0) {
                return value / 96.0
            }
        }
    }
    return 1.0
}
