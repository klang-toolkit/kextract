/**
 * AppKit platform extension types.
 *
 * Mirrors winit's `WindowExtMacOS`, `EventLoopBuilderExtMacOS`,
 * and `WindowAttributesExtMacOS` extension traits.
 */
package org.graphiks.kadre.appkit

import org.graphiks.kadre.ffi.objc.ObjCRuntime
import org.graphiks.kadre.core.ActiveEventLoop
import org.graphiks.kadre.core.Window
import org.graphiks.kadre.core.WindowAttributes
import java.lang.foreign.MemorySegment
import java.lang.foreign.ValueLayout

/**
 * Application activation policy for macOS.
 *
 * Corresponds to `NSApplicationActivationPolicy`:
 * - [Regular]: Normal app with icon in the Dock.
 * - [Accessory]: No Dock icon, menu bar appears when active.
 * - [Prohibited]: No Dock icon, no menu bar. Only background services.
 */
enum class ActivationPolicy {
    Regular,
    Accessory,
    Prohibited,
}

/**
 * AppKit-specific window creation attributes.
 *
 * Wraps the core [WindowAttributes] and adds macOS-only options.
 * Pass to [AppKitEventLoop.createWindow] overload.
 *
 * @property core Core cross-platform window attributes.
 * @property activationPolicy Application-level activation policy (set once at window creation).
 * @property tabbingIdentifier Identifier for NSWindow tabbing groups.
 * @property hasShadow Whether the window should have a shadow (null = keep default).
 * @property titlebarTransparent Whether the titlebar should be transparent (vibrancy effect).
 * @property titleHidden Whether the window title text is hidden.
 * @property titlebarHidden Whether the entire titlebar area is hidden.
 * @property fullSizeContentView Whether content extends into the titlebar area.
 * @property acceptsFirstMouse Whether the window accepts mouse events on first click when inactive.
 * @property movableByWindowBackground Whether the window can be moved by dragging its background.
 */
data class AppKitWindowAttributes(
    val core: WindowAttributes = WindowAttributes(),
    val activationPolicy: ActivationPolicy? = null,
    val tabbingIdentifier: String? = null,
    val hasShadow: Boolean? = null,
    val titlebarTransparent: Boolean = false,
    val titleHidden: Boolean = false,
    val titlebarHidden: Boolean = false,
    val fullSizeContentView: Boolean = false,
    val acceptsFirstMouse: Boolean = false,
    val movableByWindowBackground: Boolean = false,
)

// ── Extension functions on Window ──────────────────────────────────────────────

/**
 * Casts this [Window] to [AppKitWindow] or throws if the window is not an AppKit window.
 */
private fun Window.asAppKit(): AppKitWindow =
    this as? AppKitWindow ?: throw IllegalStateException(
        "This window is not an AppKit window (${this::class.simpleName})"
    )

/**
 * Toggles the window between normal and fullscreen state via `[NSWindow toggleFullScreen:]`.
 */
fun Window.setSimpleFullscreen(enabled: Boolean) {
    val appKit = asAppKit()
    appKit.setSimpleFullscreen(enabled)
}

/**
 * Shows or hides the window's shadow via `[NSWindow setHasShadow:]`.
 */
fun Window.setHasShadow(hasShadow: Boolean) {
    val appKit = asAppKit()
    appKit.setHasShadow(hasShadow)
}

/**
 * Sets the tabbing identifier for NSWindow tab groups via `[NSWindow setTabbingIdentifier:]`.
 * Pass null to clear the identifier.
 */
fun Window.setTabbingIdentifier(identifier: String?) {
    val appKit = asAppKit()
    appKit.setTabbingIdentifier(identifier)
}

/**
 * Makes the titlebar transparent via `[NSWindow setTitlebarAppearsTransparent:]`.
 */
fun Window.setTitlebarTransparent(transparent: Boolean) {
    val appKit = asAppKit()
    appKit.setTitlebarTransparent(transparent)
}

/**
 * Enables or disables window dragging by its background via `[NSWindow setMovableByWindowBackground:]`.
 */
fun Window.setMovableByWindowBackground(movable: Boolean) {
    val appKit = asAppKit()
    appKit.setMovableByWindowBackground(movable)
}

// ── Extension functions on ActiveEventLoop ─────────────────────────────────────

/**
 * Hides the application via `[NSApp hide:]`.
 */
fun ActiveEventLoop.hideApplication() {
    val nsApp = objcSharedApplication()
    ObjCRuntime.msgSend(null, nsApp, ObjCRuntime.sel("hide:"), MemorySegment.NULL)
}

/**
 * Hides all other applications via `[NSApp hideOtherApplications:]`.
 */
fun ActiveEventLoop.hideOtherApplications() {
    val nsApp = objcSharedApplication()
    ObjCRuntime.msgSend(null, nsApp, ObjCRuntime.sel("hideOtherApplications:"), MemorySegment.NULL)
}

// ── Internal helpers ──────────────────────────────────────────────────────────

internal fun objcSharedApplication(): MemorySegment {
    val nsAppClass = ObjCRuntime.getClass("NSApplication")
    return ObjCRuntime.msgSend(
        ValueLayout.ADDRESS,
        nsAppClass,
        ObjCRuntime.sel("sharedApplication"),
    ) as MemorySegment
}

internal fun ActivationPolicy.toAppKitValue(): Long = when (this) {
    ActivationPolicy.Regular -> 0L  // NSApplicationActivationPolicyRegular
    ActivationPolicy.Accessory -> 1L  // NSApplicationActivationPolicyAccessory
    ActivationPolicy.Prohibited -> 2L  // NSApplicationActivationPolicyProhibited
}
