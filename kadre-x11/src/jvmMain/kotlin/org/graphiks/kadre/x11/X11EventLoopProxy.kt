/**
 * Proxy thread-safe vers la boucle d'événements X11.
 *
 * Permet à des fils d'exécution secondaires de réveiller la boucle
 * d'événements X11 via XSendEvent (ClientMessage synthétique).
 *
 * Implémentation :
 * - Utilise un ClientMessage X11 envoyé à la première fenêtre disponible
 *   pour débloquer XNextEvent.
 * - Un flag AtomicBoolean évite les envois multiples redondants.
 *
 * X11EventLoopProxy — wakeUp thread-safe.
 */
package org.graphiks.kadre.x11

import org.graphiks.kadre.core.EventLoopProxy
import java.lang.foreign.Arena
import java.lang.foreign.MemorySegment
import java.lang.foreign.ValueLayout
import java.util.concurrent.atomic.AtomicBoolean

/**
 * Type de message ClientMessage utilisé pour le wakeUp interne.
 *
 * Valeur arbitraire choisie pour être distinctive. Ce type n'est pas
 * un atome X11 — il est comparé directement dans dispatchEvent pour
 * ignorer les ClientMessage de wakeUp.
 */
private const val KADRE_WAKEUP_MESSAGE: Long = 0L  // Non-atome : wakeUp ignoré

/**
 * Proxy thread-safe vers une boucle d'événements X11.
 *
 * [wakeUp] envoie un ClientMessage X11 synthétique à la première fenêtre
 * disponible, ce qui débloque immédiatement [XNextEvent] dans le thread
 * principal.
 *
 * @param loop       Boucle d'événements X11 cible.
 * @param displayPtr Adresse entière du Display* X11.
 */
class X11EventLoopProxy internal constructor(
    private val loop: X11EventLoop,
    private val displayPtr: Long,
) : EventLoopProxy {

    /**
     * Flag anti-doublon : garantit qu'un seul ClientMessage est envoyé
     * même si wakeUp() est appelé depuis plusieurs threads simultanément.
     *
     * Remis à false après dispatch par la boucle principale.
     */
    private val wakeupPending = AtomicBoolean(false)

    /**
     * Réveille la boucle d'événements X11 en envoyant un ClientMessage synthétique.
     *
     * Thread-safe — peut être appelé depuis n'importe quel fil d'exécution.
     * No-op sur macOS/Windows (xSendEvent est null) ou si aucune fenêtre n'est ouverte.
     * No-op si un wakeUp est déjà en attente de traitement.
     */
    override fun wakeUp() {
        if (!wakeupPending.compareAndSet(false, true)) return

        val sendHandle = xSendEvent ?: return
        val flushHandle = xFlush ?: return

        // Trouver une fenêtre cible (la première disponible)
        val targetWindowId = loop.windows.values.firstOrNull()?.id?.value ?: return

        try {
            Arena.ofConfined().use { arena ->
                val displaySeg = MemorySegment.ofAddress(displayPtr)

                // Construire un XClientMessageEvent (96 octets, remplis à zéro)
                val event = arena.allocate(96L, 8L)
                // type = ClientMessage (33)
                event.set(ValueLayout.JAVA_INT, 0L, ClientMessage)
                // send_event = True (1) — champ à offset 4
                event.set(ValueLayout.JAVA_INT, 4L, 1)
                // display — pointeur à offset 8
                event.set(ValueLayout.ADDRESS, 8L, displaySeg)
                // window (XID) — offset 16 (XAnyEvent.window)
                event.set(ValueLayout.JAVA_LONG, 16L, targetWindowId)
                // message_type — offset 20 (atome arbitraire, 0 = None)
                event.set(ValueLayout.JAVA_LONG, 20L, KADRE_WAKEUP_MESSAGE)
                // format — offset 28 (32 bits)
                event.set(ValueLayout.JAVA_INT, 28L, 32)
                // data.l[0] = 0 (offset 32) — déjà zéro par défaut de l'arène

                // XSendEvent(display, window, propagate=False, event_mask=0L, event)
                sendHandle.invokeExact(
                    displaySeg,      // Display*
                    targetWindowId,  // Window
                    0,               // Bool propagate = False
                    0L,              // long event_mask = 0 (ClientMessage non masqué)
                    event,           // XEvent*
                ) as Int

                flushHandle.invokeExact(displaySeg) as Int
            }
        } catch (_: Throwable) {
            // Dégradation gracieuse — le wakeUp échoue silencieusement
            wakeupPending.set(false)
        }
    }
}
