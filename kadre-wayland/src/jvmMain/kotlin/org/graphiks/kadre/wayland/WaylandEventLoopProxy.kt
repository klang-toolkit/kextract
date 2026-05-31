/**
 * WaylandEventLoopProxy — proxy thread-safe vers WaylandEventLoop.
 *
 * Permet à des threads secondaires de réveiller la boucle Wayland en attente
 * via un eventfd (mode compteur, flags=0).
 *
 * Utilise un AtomicBoolean pour garantir qu'un seul write() est effectué
 * même si wakeUp() est appelé depuis plusieurs threads simultanément.
 * La boucle principale vide le compteur avec read() et remet le flag à false.
 *
 * WaylandEventLoop.
 */
package org.graphiks.kadre.wayland

import org.graphiks.kadre.core.EventLoopProxy
import java.lang.foreign.Arena
import java.lang.foreign.ValueLayout
import java.util.concurrent.atomic.AtomicBoolean

/**
 * Proxy thread-safe vers la boucle d'événements Wayland.
 *
 * @param eventFd Descripteur eventfd créé par [runApp] (int ≥ 0, ou -1 si absent).
 */
class WaylandEventLoopProxy(private val eventFd: Int) : EventLoopProxy {

    /**
     * Indique qu'un réveil est en attente.
     *
     * Protège contre les doubles écrits sur l'eventfd : un seul write() est effectué
     * jusqu'à ce que la boucle principale vide le compteur.
     */
    private val wakeupPending = AtomicBoolean(false)

    /**
     * Réveille la boucle Wayland si elle est bloquée dans poll().
     *
     * Écrit 1 dans l'eventfd pour déclencher POLLIN sur le descripteur surveillé
     * par la boucle principale. L'appel est sans effet si :
     *  - l'eventfd est invalide (fd < 0)
     *  - nativeWrite n'est pas disponible (libc.so.6 absent)
     *  - un réveil est déjà en attente
     *
     * Sûr à appeler depuis n'importe quel thread.
     */
    override fun wakeUp() {
        if (eventFd < 0) return
        if (!wakeupPending.compareAndSet(false, true)) return
        try {
            Arena.ofConfined().use { arena ->
                val buf = arena.allocate(8L, 8L)
                buf.set(ValueLayout.JAVA_LONG, 0L, 1L)
                nativeWrite?.invokeExact(eventFd, buf, 8L)
            }
        } catch (_: Throwable) {
            // Écriture échouée — remettre le flag pour permettre un prochain essai
            wakeupPending.set(false)
        }
    }

    /**
     * Réinitialise le flag de réveil en attente.
     *
     * Appelé par la boucle principale après avoir vidé l'eventfd avec read().
     * Permet à un prochain [wakeUp] d'écrire à nouveau dans l'eventfd.
     */
    internal fun clearPending() {
        wakeupPending.set(false)
    }
}
