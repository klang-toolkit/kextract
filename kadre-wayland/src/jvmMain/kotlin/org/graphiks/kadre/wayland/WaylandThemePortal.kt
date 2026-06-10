package org.graphiks.kadre.wayland

import org.graphiks.kadre.core.Theme
import java.util.concurrent.TimeUnit

internal object WaylandThemePortal {
    private var cachedTheme: Theme? = null

    fun queryColorScheme(): Theme? {
        if (cachedTheme != null) return cachedTheme
        val theme = executeQuery()
        cachedTheme = theme
        return theme
    }

    fun resetCache() {
        cachedTheme = null
    }

    private fun executeQuery(): Theme? {
        return try {
            val process = ProcessBuilder(
                "dbus-send", "--print-reply", "--dest=org.freedesktop.portal.Desktop",
                "/org/freedesktop/portal/desktop",
                "org.freedesktop.portal.Settings.Read",
                "string:org.freedesktop.appearance",
                "string:color-scheme"
            ).start()
            val output = process.inputStream.readAllBytes().decodeToString()
            process.waitFor(5, TimeUnit.SECONDS)
            parseColorScheme(output)
        } catch (_: Exception) { null }
    }

    internal fun parseColorScheme(output: String): Theme? {
        return when {
            "uint32 1" in output -> Theme.Dark
            "uint32 2" in output -> Theme.Light
            else -> null
        }
    }
}
