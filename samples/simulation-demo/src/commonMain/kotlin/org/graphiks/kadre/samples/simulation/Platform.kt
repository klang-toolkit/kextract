package org.graphiks.kadre.samples.simulation

enum class Platform {
    MACOS, WINDOWS, LINUX_X11, LINUX_WAYLAND, ANDROID, IOS, WEB;

    companion object {
        val ALL: Set<Platform> = entries.toSet()

        fun current(): Platform {
            val osName = System.getProperty("os.name", "").lowercase()
            val xdgSession = System.getenv("XDG_SESSION_TYPE")

            return when {
                osName.contains("mac") || osName.contains("darwin") -> MACOS
                osName.contains("win") -> WINDOWS
                osName.contains("linux") -> {
                    if (xdgSession?.equals("wayland", ignoreCase = true) == true) {
                        LINUX_WAYLAND
                    } else {
                        LINUX_X11
                    }
                }
                osName.contains("android") -> ANDROID
                System.getProperty("kotlin.platform.type") == "js" -> WEB
                else -> MACOS
            }
        }
    }
}
