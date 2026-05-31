/**
 * Détecte le backend Linux approprié (X11 ou Wayland) à l'exécution.
 *
 * Stratégie de détection (sans appels FFM, safe dans tous les contextes) :
 *  1. Variable d'environnement KADRE_LINUX_BACKEND — override explicite.
 *  2. XDG_SESSION_TYPE / WAYLAND_DISPLAY — indice session courante.
 *  3. Tentative de chargement réflexif des classes de backend disponibles.
 *
 * détection X11/Wayland Linux.
 * extension de la façade kadre.
 */
package org.graphiks.kadre

/**
 * Détecteur de backend Linux — sélectionne X11 ou Wayland selon l'environnement.
 *
 * Toutes les détections reposent sur des variables d'environnement ou
 * [Class.forName] : aucun appel FFM dans les initialiseurs statiques.
 */
internal object LinuxBackendDetector {

    internal const val X11_CLASS = "org.graphiks.kadre.x11.X11EventLoopKt"
    internal const val WAYLAND_CLASS = "org.graphiks.kadre.wayland.WaylandEventLoopKt"

    /**
     * Résout le nom de classe du backend Linux à utiliser.
     *
     * @return Nom complet de la classe backend à instancier par réflexion.
     * @throws IllegalStateException si aucun backend n'est disponible.
     * @throws IllegalArgumentException si KADRE_LINUX_BACKEND contient une valeur invalide.
     */
    fun detectBackendClass(): String {
        val debug = System.getenv("KADRE_DEBUG") == "1"

        // Priorité 1 : override explicite par variable d'environnement
        val override = System.getenv("KADRE_LINUX_BACKEND")?.lowercase()
        if (override != null) {
            return when (override) {
                "wayland" -> WAYLAND_CLASS
                "x11" -> X11_CLASS
                else -> error(
                    "KADRE_LINUX_BACKEND invalide: '$override'. " +
                    "Valeurs acceptées : 'x11', 'wayland'."
                )
            }
        }

        // Priorité 2 : indices de session (variables d'environnement, pas de FFM)
        val xdgSession = System.getenv("XDG_SESSION_TYPE")?.lowercase()
        val waylandDisplay = System.getenv("WAYLAND_DISPLAY")
        val display = System.getenv("DISPLAY")
        val preferWayland = xdgSession == "wayland" || waylandDisplay != null

        // Essaie le backend préféré en premier, puis l'autre en fallback
        val candidates = if (preferWayland) listOf(WAYLAND_CLASS, X11_CLASS)
                         else listOf(X11_CLASS, WAYLAND_CLASS)

        for (cls in candidates) {
            if (canLoad(cls, debug)) return cls
        }

        error(
            "Aucun backend Linux disponible. " +
            "Installez libX11-dev OU libwayland-dev et ajoutez kadre-x11 ou " +
            "kadre-wayland au classpath. " +
            "[WAYLAND_DISPLAY=$waylandDisplay, DISPLAY=$display, XDG_SESSION_TYPE=$xdgSession]"
        )
    }

    /**
     * Vérifie si une classe est accessible sur le classpath courant.
     *
     * Utilise [Class.forName] et intercepte tout [Throwable] (couvre
     * [ClassNotFoundException], [LinkageError], [ExceptionInInitializerError],
     * [UnsatisfiedLinkError]) conformément à la règle PR #49 §1B.
     *
     * @param className Nom complet de la classe à tester.
     * @param debug Si `true`, affiche un message de diagnostic en cas d'échec.
     * @return `true` si la classe est chargeable, `false` sinon.
     */
    internal fun canLoad(className: String, debug: Boolean = false): Boolean {
        return try {
            Class.forName(className)
            true
        } catch (e: Throwable) {
            if (debug) println("[kadre-debug] Cannot load $className: ${e::class.simpleName}: ${e.message}")
            false
        }
    }
}
