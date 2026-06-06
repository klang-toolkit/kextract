package org.graphiks.kadre.core.capture

actual fun resolveScreenCapturer(): ScreenCapturer? {
    val osName = System.getProperty("os.name").lowercase()
    return when {
        osName.contains("mac") -> reflect("org.graphiks.kadre.appkit.capture.AppKitScreenCapturer")
        osName.contains("win") -> reflect("org.graphiks.kadre.win32.capture.Win32ScreenCapturer")
        osName.contains("nix") || osName.contains("nux") -> {
            if (isWayland()) reflect("org.graphiks.kadre.wayland.capture.WaylandScreenCapturer")
            else reflect("org.graphiks.kadre.x11.capture.X11ScreenCapturer")
        }
        else -> null
    }
}

private fun reflect(className: String): ScreenCapturer? = try {
    Class.forName(className).getDeclaredConstructor().newInstance() as ScreenCapturer
} catch (_: Throwable) { null }

private fun isWayland(): Boolean = System.getenv("WAYLAND_DISPLAY") != null
