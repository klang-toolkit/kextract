/**
 * Smoke tests pour WaylandEventLoop et WaylandEventLoopProxy.
 *
 * Ces tests ne requièrent pas de serveur Wayland en fonctionnement :
 * ils vérifient uniquement les invariants statiques et le comportement
 * null-safe des bindings.
 *
 * WaylandEventLoop.
 */
package org.graphiks.kadre.wayland

import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertNotNull

class WaylandEventLoopSmokeTest {

    /**
     * Vérifie que waylandRunning est false au démarrage de la JVM.
     *
     * Si ce test échoue, c'est qu'une boucle Wayland est déjà en cours — ce qui
     * serait inattendu dans un contexte de test unitaire isolé.
     */
    @Test
    fun `waylandRunning starts false`() {
        assertFalse(waylandRunning.get(), "waylandRunning doit être false au démarrage")
    }

    /**
     * Vérifie que WaylandEventLoopProxy.wakeUp() ne plante pas quand libC est absent.
     *
     * Sur macOS/Windows, nativeWrite est null — wakeUp() doit simplement retourner
     * sans lever d'exception.
     */
    @Test
    fun `wakeUp proxy no-crash when libC absent`() {
        // On passe fd=-1 pour simuler l'absence d'eventfd (retour immédiat dans wakeUp)
        val proxy = WaylandEventLoopProxy(eventFd = -1)
        proxy.wakeUp()  // ne doit pas lever d'exception
        proxy.wakeUp()  // deuxième appel — idempotent
    }

    /**
     * Vérifie que WaylandEventLoopProxy.wakeUp() est sans effet si nativeWrite est null
     * et que l'eventFd est valide (simulation).
     *
     * Sur les plateformes sans libc.so.6, nativeWrite est null et wakeUp() doit retourner
     * proprement, même avec un fd > 0.
     */
    @Test
    fun `wakeUp proxy handles missing nativeWrite gracefully`() {
        // fd=42 — fictif, jamais ouvert
        val proxy = WaylandEventLoopProxy(eventFd = 42)
        // Sur macOS/Windows, nativeWrite est null → wakeUp retourne proprement
        // Sur Linux avec libc, un write sur fd=42 (invalide) retournera -1 ou EBADF
        // mais ne doit pas lever d'exception (try/catch dans wakeUp)
        try {
            proxy.wakeUp()
        } catch (e: Throwable) {
            // Toléré uniquement si l'exception est inattendue — logguer pour le diagnostic
            throw AssertionError("wakeUp() ne doit jamais propager d'exception : $e", e)
        }
    }

    /**
     * Vérifie que libC se charge sans exception (ou est null proprement).
     *
     * Sur Linux : libC est non-null.
     * Sur macOS/Windows : libC est null (libc.so.6 absent).
     */
    @Test
    fun `libC loads safely on any platform`() {
        // Pas d'assertion sur la valeur — on vérifie juste que l'accès ne plante pas
        val lib = libC  // peut être null
        // Sur Linux, on peut vérifier les handles dérivés
        if (lib != null) {
            assertNotNull(nativePoll, "nativePoll doit être non-null si libC est disponible")
            assertNotNull(nativeEventfd, "nativeEventfd doit être non-null si libC est disponible")
            assertNotNull(nativeRead, "nativeRead doit être non-null si libC est disponible")
            assertNotNull(nativeWrite, "nativeWrite doit être non-null si libC est disponible")
            assertNotNull(nativeClose, "nativeClose doit être non-null si libC est disponible")
        }
    }
}
