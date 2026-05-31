/**
 * Implémentation JVM de la boucle d'événements kadre — sélectionne le backend
 * selon le système d'exploitation hôte.
 *
 * Sur macOS → délègue à [org.graphiks.kadre.appkit.AppKitEventLoopKt.runApp]
 *   (kadre-appkit, dépendance directe de jvmMain).
 * Sur Windows → délègue à [org.graphiks.kadre.win32.Win32EventLoopKt.runApp]
 *   (kadre-win32, chargé par réflexion — lazy loading, pas d'import direct).
 * Sur Linux → délègue à X11 ou Wayland selon [LinuxBackendDetector]
 *   (kadre-x11 / kadre-wayland, chargés par réflexion).
 *
 * Le lazy loading par réflexion garantit que les bindings natifs (user32.dll,
 * libX11, libwayland…) ne sont jamais initialisés sur un autre OS, même si
 * plusieurs backends se trouvent sur le classpath (build multi-OS).
 *
 * GRA-129 : façade KMP — actual jvmMain.
 * façade EventLoop Windows.
 * détection backend Linux X11/Wayland.
 * extension façade kadre Linux.
 */
package org.graphiks.kadre

/**
 * Implémentation JVM de [EventLoop].
 *
 * Route vers le backend approprié via réflexion :
 * - macOS   → `org.graphiks.kadre.appkit.AppKitEventLoopKt#runApp`
 * - Windows → `org.graphiks.kadre.win32.Win32EventLoopKt#runApp`
 * - Linux   → X11 ou Wayland selon [LinuxBackendDetector]
 *
 * Aucun import direct des modules backend — le chargement est différé à
 * l'exécution pour éviter d'initialiser des bindings natifs sur le mauvais OS.
 */
actual class EventLoop actual constructor() {

    /**
     * Démarre la boucle d'événements et délègue les rappels au gestionnaire fourni.
     *
     * Bloquant — ne retourne qu'à la fermeture de l'application.
     *
     * @param handler Gestionnaire du cycle de vie et des événements de l'application.
     * @throws UnsupportedOperationException si l'OS n'est pas supporté ou si le
     *   backend correspondant est introuvable sur le classpath.
     */
    actual fun runApp(handler: ApplicationHandler) {
        val os = System.getProperty("os.name", "").lowercase()
        val backendClass = when {
            os.contains("mac")   -> "org.graphiks.kadre.appkit.AppKitEventLoopKt"
            os.contains("win")   -> "org.graphiks.kadre.win32.Win32EventLoopKt"
            os.contains("linux") -> LinuxBackendDetector.detectBackendClass()
            else -> throw UnsupportedOperationException(
                "Système d'exploitation non supporté par kadre-jvm : '$os'. " +
                "Plateformes supportées : macOS, Windows, Linux."
            )
        }

        val klass = try {
            Class.forName(backendClass)
        } catch (e: ClassNotFoundException) {
            val module = when {
                os.contains("win")   -> "kadre-win32"
                os.contains("linux") -> "kadre-x11 ou kadre-wayland"
                else                 -> "kadre-appkit"
            }
            throw UnsupportedOperationException(
                "$backendClass introuvable sur le classpath. " +
                "Ajoutez la dépendance implementation(project(\":$module\")).",
                e,
            )
        }

        val method = klass.getMethod("runApp", ApplicationHandler::class.java)
        method.invoke(null, handler)
    }
}
