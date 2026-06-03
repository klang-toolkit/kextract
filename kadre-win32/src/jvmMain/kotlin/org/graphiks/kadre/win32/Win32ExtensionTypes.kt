/**
 * Win32 platform extension types.
 *
 * Mirrors winit's `WindowExtWindows`, `EventLoopBuilderExtWindows`,
 * and `WindowAttributesWindows` extension traits.
 */
package org.graphiks.kadre.win32

import org.graphiks.kadre.core.Window
import org.graphiks.kadre.core.WindowAttributes
import org.graphiks.kadre.core.WindowRequestResult
import java.lang.foreign.Arena
import java.lang.foreign.ValueLayout

// ── Extension enums ────────────────────────────────────────────────────────────

/**
 * System backdrop type for Win32 windows (Windows 11 22523+).
 *
 * Corresponds to winit's `SystemBackdrop` and DWM's DWMWA_SYSTEMBACKDROP_TYPE.
 */
enum class SystemBackdrop {
    Auto,
    None,
    MainWindow,
    TransientWindow,
    TabbedWindow,
}

/**
 * Corner preference for Win32 windows (Windows 11 22000+).
 *
 * Corresponds to winit's `CornerPreference` and DWM's DWMWA_WINDOW_CORNER_PREFERENCE.
 */
enum class CornerPreference {
    Default,
    DoNotRound,
    Round,
    RoundSmall,
}

// ── Win32WindowAttributes ──────────────────────────────────────────────────────

/**
 * Win32-specific window creation attributes.
 *
 * Works like [WebWindowAttributes]: wraps the core [WindowAttributes]
 * and adds Win32-only options. Pass to [Win32EventLoop.createWindow] overload.
 *
 * @property core Core cross-platform window attributes.
 * @property skipTaskbar Whether to hide the window from the taskbar.
 * @property undecoratedShadow Whether to show a shadow on undecorated windows.
 * @property systemBackdrop System backdrop style (Win11 22523+).
 * @property cornerPreference Window corner rounding (Win11 22000+).
 * @property borderColor ARGB border color (Win11 22000+), null = default.
 * @property titleBackgroundColor ARGB title bar background (Win11 22000+), null = default.
 * @property titleTextColor ARGB title bar text color (Win11 22000+), null = default.
 * @property enabled Whether the window accepts input (default true).
 */
data class Win32WindowAttributes(
    val core: WindowAttributes = WindowAttributes(),
    val skipTaskbar: Boolean = false,
    val undecoratedShadow: Boolean = false,
    val systemBackdrop: SystemBackdrop? = null,
    val cornerPreference: CornerPreference? = null,
    val borderColor: Long? = null,
    val titleBackgroundColor: Long? = null,
    val titleTextColor: Long? = null,
    val enabled: Boolean = true,
)

// ── Extension functions on Window ──────────────────────────────────────────────

/**
 * Casts this [Window] to [Win32Window] or throws if the window is not a Win32 window.
 */
private fun Window.asWin32(): Win32Window =
    this as? Win32Window ?: throw IllegalStateException(
        "This window is not a Win32 window (${this::class.simpleName})"
    )

/**
 * Sets the system backdrop type via DwmSetWindowAttribute(DWMWA_SYSTEMBACKDROP_TYPE).
 */
fun Window.setSystemBackdrop(backdrop: SystemBackdrop): WindowRequestResult {
    val win32 = asWin32()
    return win32.setSystemBackdrop(backdrop)
}

/**
 * Sets the window corner preference via DwmSetWindowAttribute(DWMWA_WINDOW_CORNER_PREFERENCE).
 */
fun Window.setCornerPreference(preference: CornerPreference): WindowRequestResult {
    val win32 = asWin32()
    return win32.setCornerPreference(preference)
}

/**
 * Sets the window border color via DwmSetWindowAttribute(DWMWA_BORDER_COLOR).
 */
fun Window.setBorderColor(color: Long?): WindowRequestResult {
    val win32 = asWin32()
    return win32.setBorderColor(color)
}

/**
 * Sets the title bar background color via DwmSetWindowAttribute(DWMWA_CAPTION_COLOR).
 */
fun Window.setTitleBackgroundColor(color: Long?): WindowRequestResult {
    val win32 = asWin32()
    return win32.setTitleBackgroundColor(color)
}

/**
 * Sets the title bar text color via DwmSetWindowAttribute(DWMWA_TEXT_COLOR).
 */
fun Window.setTitleTextColor(color: Long?): WindowRequestResult {
    val win32 = asWin32()
    return win32.setTitleTextColor(color)
}

/**
 * Shows or hides the window in the taskbar via ITaskbarList or ITaskbarList2.
 */
fun Window.setSkipTaskbar(skip: Boolean): WindowRequestResult {
    val win32 = asWin32()
    return win32.setSkipTaskbar(skip)
}

/**
 * Shows or hides the shadow on undecorated windows via DwmSetWindowAttribute.
 */
fun Window.setUndecoratedShadow(show: Boolean): WindowRequestResult {
    val win32 = asWin32()
    return win32.setUndecoratedShadow(show)
}

/**
 * Enables or disables input to the window via EnableWindow.
 */
fun Window.setEnabled(enabled: Boolean): WindowRequestResult {
    val win32 = asWin32()
    return win32.setEnabled(enabled)
}

// ── DWM value conversions ──────────────────────────────────────────────────────

/**
 * Maps [SystemBackdrop] to the integer value used by DWMWA_SYSTEMBACKDROP_TYPE.
 *
 * Values correspond to the DWM_SYSTEMBACKDROP_TYPE enum:
 * 0 = Auto, 1 = None, 2 = MainWindow, 3 = TransientWindow, 4 = TabbedWindow
 */
internal fun SystemBackdrop.toDwmValue(): Int = when (this) {
    SystemBackdrop.Auto -> 0
    SystemBackdrop.None -> 1
    SystemBackdrop.MainWindow -> 2
    SystemBackdrop.TransientWindow -> 3
    SystemBackdrop.TabbedWindow -> 4
}

/**
 * Maps [CornerPreference] to the integer value used by DWMWA_WINDOW_CORNER_PREFERENCE.
 *
 * Values correspond to the DWM_WINDOW_CORNER_PREFERENCE enum:
 * 0 = Default, 1 = DoNotRound, 2 = Round, 3 = RoundSmall
 */
internal fun CornerPreference.toDwmValue(): Int = when (this) {
    CornerPreference.Default -> 0
    CornerPreference.DoNotRound -> 1
    CornerPreference.Round -> 2
    CornerPreference.RoundSmall -> 3
}

// ── Internal helper: apply DWM attribute ───────────────────────────────────────

/**
 * Applies a DWMWA_* attribute to [hwnd] with a 4-byte integer value.
 * Returns [WindowRequestResult.Success] if the HRESULT >= 0, or a Failure.
 */
internal fun win32ApplyDwmAttribute(
    hwnd: java.lang.foreign.MemorySegment,
    attribute: Int,
    value: Int,
): WindowRequestResult = try {
    val handle = dwmSetWindowAttribute ?: return WindowRequestResult.Failure(
        org.graphiks.kadre.core.RequestError.Unsupported("DwmSetWindowAttribute is not available")
    )
    Arena.ofConfined().use { arena ->
        val ptr = arena.allocate(ValueLayout.JAVA_INT, 1L)
        ptr.set(ValueLayout.JAVA_INT, 0L, value)
        val hr = handle.invokeExact(hwnd, attribute, ptr, 4) as Int
        if (hr >= 0) WindowRequestResult.Success
        else WindowRequestResult.Failure(
            org.graphiks.kadre.core.RequestError.OsError("DwmSetWindowAttribute returned HRESULT=$hr")
        )
    }
} catch (t: Throwable) {
    WindowRequestResult.Failure(
        org.graphiks.kadre.core.RequestError.OsError(t.message ?: t::class.simpleName ?: "DWM attribute failed")
    )
}
