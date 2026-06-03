/**
 * X11 platform extension types.
 *
 * Mirrors winit's `WindowExtX11`, `EventLoopBuilderExtX11`,
 * and `WindowAttributesX11` extension traits.
 */
package org.graphiks.kadre.x11

import org.graphiks.kadre.core.ActiveEventLoop
import org.graphiks.kadre.core.RawWindowHandle
import org.graphiks.kadre.core.Window
import org.graphiks.kadre.core.WindowAttributes
import org.graphiks.kadre.core.WindowRequestResult

// ── Extension enums ────────────────────────────────────────────────────────────

/**
 * Window type for X11 EWMH _NET_WM_WINDOW_TYPE.
 *
 * Corresponds to the standard EWMH window type atoms.
 */
enum class WindowType {
    Desktop,
    Dock,
    Toolbar,
    Menu,
    Utility,
    Splash,
    Dialog,
    DropdownMenu,
    PopupMenu,
    Tooltip,
    Notification,
    Combo,
    Dnd,
    Normal,
}

// ── X11WindowAttributes ──────────────────────────────────────────────────────

/**
 * X11-specific window creation attributes.
 *
 * Wraps the core [WindowAttributes] and adds X11-only options.
 * Pass to [X11EventLoop.createWindow] overload.
 *
 * @property core Core cross-platform window attributes.
 * @property windowType EWMH _NET_WM_WINDOW_TYPE hint.
 * @property overrideRedirect Whether to set override-redirect flag.
 * @property visualId XVisualID for the window's visual.
 * @property screenId X11 screen number.
 * @property baseWidth Base width hint for the window manager.
 * @property baseHeight Base height hint for the window manager.
 */
data class X11WindowAttributes(
    val core: WindowAttributes = WindowAttributes(),
    val windowType: WindowType? = null,
    val overrideRedirect: Boolean = false,
    val visualId: Long? = null,
    val screenId: Int? = null,
    val baseWidth: Int? = null,
    val baseHeight: Int? = null,
)

// ── Extension functions on Window ──────────────────────────────────────────────

/**
 * Casts this [Window] to [X11Window] or throws if the window is not an X11 window.
 */
private fun Window.asX11(): X11Window =
    this as? X11Window ?: throw IllegalStateException(
        "This window is not an X11 window (${this::class.simpleName})"
    )

/**
 * Returns the native X11 Window XID.
 */
fun Window.x11Window(): Long = when (val handle = rawWindowHandle) {
    is RawWindowHandle.Xlib -> handle.window
    else -> throw IllegalStateException("Not an X11 window")
}

/**
 * Sets the EWMH _NET_WM_WINDOW_TYPE hint.
 */
fun Window.setWindowType(type: WindowType): WindowRequestResult {
    val x11 = asX11()
    return x11.setWindowType(type)
}

/**
 * Sets the override-redirect attribute of the window.
 */
fun Window.setOverrideRedirect(redirect: Boolean): WindowRequestResult {
    val x11 = asX11()
    return x11.setOverrideRedirect(redirect)
}

// ── Extension function on ActiveEventLoop ──────────────────────────────────────

/**
 * Returns true if this event loop is an X11 event loop.
 */
fun ActiveEventLoop.isX11(): Boolean = this is X11EventLoop

// ── Internal helpers ───────────────────────────────────────────────────────────

/**
 * Maps [WindowType] to the corresponding EWMH atom name.
 */
internal fun WindowType.toNetWmWindowTypeAtom(): String = when (this) {
    WindowType.Desktop -> "_NET_WM_WINDOW_TYPE_DESKTOP"
    WindowType.Dock -> "_NET_WM_WINDOW_TYPE_DOCK"
    WindowType.Toolbar -> "_NET_WM_WINDOW_TYPE_TOOLBAR"
    WindowType.Menu -> "_NET_WM_WINDOW_TYPE_MENU"
    WindowType.Utility -> "_NET_WM_WINDOW_TYPE_UTILITY"
    WindowType.Splash -> "_NET_WM_WINDOW_TYPE_SPLASH"
    WindowType.Dialog -> "_NET_WM_WINDOW_TYPE_DIALOG"
    WindowType.DropdownMenu -> "_NET_WM_WINDOW_TYPE_DROPDOWN_MENU"
    WindowType.PopupMenu -> "_NET_WM_WINDOW_TYPE_POPUP_MENU"
    WindowType.Tooltip -> "_NET_WM_WINDOW_TYPE_TOOLTIP"
    WindowType.Notification -> "_NET_WM_WINDOW_TYPE_NOTIFICATION"
    WindowType.Combo -> "_NET_WM_WINDOW_TYPE_COMBO"
    WindowType.Dnd -> "_NET_WM_WINDOW_TYPE_DND"
    WindowType.Normal -> "_NET_WM_WINDOW_TYPE_NORMAL"
}
