/**
 * Wayland platform extension types.
 *
 * Mirrors winit's `WindowExtWayland`, `EventLoopBuilderExtWayland`,
 * and `WindowAttributesWayland` extension traits.
 */
package org.graphiks.kadre.wayland

import org.graphiks.kadre.ffi.wayland.*
import org.graphiks.kadre.core.ActiveEventLoop
import org.graphiks.kadre.core.Window
import org.graphiks.kadre.core.WindowAttributes
import org.graphiks.kadre.core.RawWindowHandle

// ── WaylandWindowAttributes ────────────────────────────────────────────────────

/**
 * Wayland-specific window creation attributes.
 *
 * Wraps the core [WindowAttributes] and adds Wayland-only options.
 * Pass to [WaylandEventLoop.createWindow] overload.
 *
 * @property core Core cross-platform window attributes.
 * @property preferCsd Whether to prefer client-side decorations (CSD).
 * @property activationToken Activation token for xdg_activation_v1.
 * @property name App ID equivalent (app_id for xdg_toplevel).
 */
data class WaylandWindowAttributes(
    val core: WindowAttributes = WindowAttributes(),
    val preferCsd: Boolean? = null,
    val activationToken: String? = null,
    val name: String? = null,
)

// ── Extension functions on Window ──────────────────────────────────────────────

/**
 * Casts this [Window] to [WaylandWindow] or throws if the window is not a Wayland window.
 */
private fun Window.asWayland(): WaylandWindow =
    this as? WaylandWindow ?: throw IllegalStateException(
        "This window is not a Wayland window (${this::class.simpleName})"
    )

/**
 * Returns true if this window is backed by the Wayland platform.
 */
fun Window.isWayland(): Boolean =
    rawWindowHandle is RawWindowHandle.Wayland

/**
 * Returns the xdg_toplevel proxy pointer for this Wayland window.
 *
 * @throws IllegalStateException if the window is not a Wayland window.
 */
fun Window.xdgToplevel(): Long {
    val wayland = asWayland()
    return wayland.xdgToplevelPtr()
}

// ── Extension functions on ActiveEventLoop ────────────────────────────────────

/**
 * Returns true if the event loop is a Wayland event loop.
 */
fun ActiveEventLoop.isWayland(): Boolean =
    this is WaylandEventLoop

/**
 * Sets the client-side decoration preference on the next window creation.
 *
 * When [preferCsd] is true, wayland surfaces will be created with
 * client-side decorations (CSD). When false, server-side decorations (SSD).
 *
 * @throws IllegalStateException if the event loop is not a Wayland event loop.
 */
fun ActiveEventLoop.setPreferCsd(preferCsd: Boolean) {
    val wayland = this as? WaylandEventLoop
        ?: throw IllegalStateException("Event loop is not a Wayland event loop")
    wayland._preferCsd = preferCsd
}

/**
 * Sets the xdg_activation_v1 activation token for the next window creation.
 *
 * @throws IllegalStateException if the event loop is not a Wayland event loop.
 */
fun ActiveEventLoop.setActivationToken(token: String?) {
    val wayland = this as? WaylandEventLoop
        ?: throw IllegalStateException("Event loop is not a Wayland event loop")
    wayland._activationToken = token
}
