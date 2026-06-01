/**
 * R3 — Win32 theme helpers.
 *
 * Reads the system theme from the registry and sets the dark-mode title bar
 * via DwmSetWindowAttribute(DWMWA_USE_IMMERSIVE_DARK_MODE).
 */
package org.graphiks.kadre.win32

import org.graphiks.kadre.core.Theme
import java.lang.foreign.Arena
import java.lang.foreign.MemorySegment
import java.lang.foreign.ValueLayout

/**
 * Win32 theme utilities.
 */
internal object Win32ThemeHelper {

    /**
     * Reads the current system theme from the registry.
     *
     * Reads:
     *   HKCU\Software\Microsoft\Windows\CurrentVersion\Themes\Personalize\AppsUseLightTheme
     *
     * Returns [Theme.Light] when the DWORD value is non-zero (light),
     * [Theme.Dark] when zero (dark), null on failure.
     *
     * Note: uses the Java `Registry` class via `Preferences` API (no FFM needed).
     */
    fun systemThemeFromRegistry(): Theme? = try {
        val prefs = java.util.prefs.Preferences.userRoot()
        // Java Preferences maps HKCU on Windows
        val node = prefs.node(
            "Software/Microsoft/Windows/CurrentVersion/Themes/Personalize"
        )
        val value = node.getInt("AppsUseLightTheme", -1)
        when (value) {
            -1   -> null             // key absent — unknown
            0    -> Theme.Dark
            else -> Theme.Light
        }
    } catch (_: Throwable) { null }

    /**
     * Applies or removes the DWMWA_USE_IMMERSIVE_DARK_MODE attribute on [hwnd].
     *
     * Available since Windows 11 Build 22000; silently fails on older builds.
     * Risk FFM: writes a 4-byte BOOL via a pointer.
     */
    fun setWindowDarkMode(hwnd: MemorySegment, theme: Theme?) {
        try {
            val handle = dwmSetWindowAttribute ?: return
            Arena.ofConfined().use { arena ->
                val boolPtr = arena.allocate(ValueLayout.JAVA_INT, 1L)
                boolPtr.set(ValueLayout.JAVA_INT, 0L, if (theme == Theme.Dark) 1 else 0)
                handle.invokeExact(hwnd, DWMWA_USE_IMMERSIVE_DARK_MODE, boolPtr, 4) as Int
            }
        } catch (_: Throwable) {}
    }
}
