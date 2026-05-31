/**
 * JVM implementation of the kadre event loop — selects the backend
 * based on the host operating system.
 *
 * On macOS → delegates to [org.graphiks.kadre.appkit.AppKitEventLoopKt.runApp]
 *   (kadre-appkit, direct dependency of jvmMain).
 * On Windows → delegates to [org.graphiks.kadre.win32.Win32EventLoopKt.runApp]
 *   (kadre-win32, loaded via reflection — lazy loading, no direct import).
 * On Linux → delegates to X11 or Wayland according to [LinuxBackendDetector]
 *   (kadre-x11 / kadre-wayland, loaded via reflection).
 *
 * Lazy loading via reflection ensures that native bindings (user32.dll,
 * libX11, libwayland…) are never initialized on another OS, even if
 * several backends are present on the classpath (multi-OS build).
 *
 * GRA-129: KMP facade — actual jvmMain.
 * Windows EventLoop facade.
 * Linux X11/Wayland backend detection.
 * Linux kadre facade extension.
 */
package org.graphiks.kadre

/**
 * JVM implementation of [EventLoop].
 *
 * Routes to the appropriate backend via reflection:
 * - macOS   → `org.graphiks.kadre.appkit.AppKitEventLoopKt#runApp`
 * - Windows → `org.graphiks.kadre.win32.Win32EventLoopKt#runApp`
 * - Linux   → X11 or Wayland according to [LinuxBackendDetector]
 *
 * No direct import of the backend modules — loading is deferred to
 * runtime to avoid initializing native bindings on the wrong OS.
 */
actual class EventLoop actual constructor() {

    /**
     * Starts the event loop and delegates callbacks to the provided handler.
     *
     * Blocking — only returns when the application closes.
     *
     * @param handler Handler for the application's lifecycle and events.
     * @throws UnsupportedOperationException if the OS is not supported or if the
     *   corresponding backend cannot be found on the classpath.
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
