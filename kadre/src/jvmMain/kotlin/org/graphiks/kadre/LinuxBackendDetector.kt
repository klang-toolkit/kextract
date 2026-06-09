/**
 * Detects the appropriate Linux backend (X11 or Wayland) at runtime.
 *
 * Detection strategy (no FFM calls, safe in all contexts):
 *  1. KADRE_LINUX_BACKEND environment variable — explicit override.
 *  2. XDG_SESSION_TYPE / WAYLAND_DISPLAY — current session hint.
 *  3. Reflective load attempt of the available backend classes.
 *
 * Linux X11/Wayland detection.
 * kadre facade extension.
 */
package org.graphiks.kadre

/**
 * Linux backend detector — selects X11 or Wayland depending on the environment.
 *
 * All detections rely on environment variables or
 * [Class.forName]: no FFM calls in the static initializers.
 */
internal object LinuxBackendDetector {

    internal const val X11_CLASS = "org.graphiks.kadre.x11.X11EventLoopKt"
    internal const val WAYLAND_CLASS = "org.graphiks.kadre.wayland.WaylandEventLoopKt"

    /**
     * Resolves the class name of the Linux backend to use.
     *
     * @return Fully qualified name of the backend class to instantiate via reflection.
     * @throws IllegalStateException if no backend is available.
     * @throws IllegalArgumentException if KADRE_LINUX_BACKEND contains an invalid value.
     */
    fun detectBackendClass(): String {
        val debug = System.getenv("KADRE_DEBUG") == "1"

        // Priority 1: explicit override via environment variable
        val override = System.getenv("KADRE_LINUX_BACKEND")?.lowercase()
        if (override != null) {
            return when (override) {
                "wayland" -> WAYLAND_CLASS
                "x11" -> X11_CLASS
                else -> error(
                    "Invalid KADRE_LINUX_BACKEND: '$override'. " +
                    "Accepted values: 'x11', 'wayland'."
                )
            }
        }

        // Priority 2: session hints (environment variables, no FFM)
        val xdgSession = System.getenv("XDG_SESSION_TYPE")?.lowercase()
        val waylandDisplay = System.getenv("WAYLAND_DISPLAY")
        val display = System.getenv("DISPLAY")
        val preferWayland = xdgSession == "wayland" || waylandDisplay != null

        // Try the preferred backend first, then the other as fallback
        val candidates = if (preferWayland) listOf(WAYLAND_CLASS, X11_CLASS)
                         else listOf(X11_CLASS, WAYLAND_CLASS)

        for (cls in candidates) {
            if (canLoad(cls, debug)) return cls
        }

        error(
            "No Linux backend available. " +
            "Install libX11-dev OR libwayland-dev and add kadre-x11 or " +
            "kadre-wayland to the classpath. " +
            "[WAYLAND_DISPLAY=$waylandDisplay, DISPLAY=$display, XDG_SESSION_TYPE=$xdgSession]"
        )
    }

    /**
     * Checks whether a class is reachable on the current classpath.
     *
     * Uses [Class.forName] and catches any [Throwable] (covers
     * [ClassNotFoundException], [LinkageError], [ExceptionInInitializerError],
     * [UnsatisfiedLinkError]) per rule PR #49 §1B.
     *
     * @param className Fully qualified name of the class to test.
     * @param debug If `true`, prints a diagnostic message on failure.
     * @return `true` if the class is loadable, `false` otherwise.
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
