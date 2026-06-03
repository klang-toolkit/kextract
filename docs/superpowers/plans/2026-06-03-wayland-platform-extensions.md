# Wayland Platform Extensions Implementation Plan

> **For agentic workers:** REQUIRED SUB-SKILL: Use superpowers:subagent-driven-development (recommended) or superpowers:executing-plans to implement this plan task-by-task.

**Goal:** Add Wayland platform extension types following the Win32 pattern.

**Architecture:** Create `WaylandExtensionTypes.kt` with `WaylandWindowAttributes` data class + extension functions on `Window` and `ActiveEventLoop`. Modify `WaylandWindow.kt` and `WaylandEventLoop.kt` minimally to support the extensions.

**Tech Stack:** Kotlin + Java FFM (same pattern as existing Wayland backend)

---

### Task 1: Expose xdg_toplevel pointer in XdgToplevel

**Files:**
- Modify: `kadre-wayland/src/jvmMain/kotlin/org/graphiks/kadre/wayland/WaylandXdg.kt:37`

- [ ] **Step 1: Add public accessor for xdgToplevelPtr**

Add a property to expose `xdgToplevelPtr`:

```kotlin
    /** Returns the xdg_toplevel proxy pointer for platform extension use. */
    val xdgToplevelPtr: Long get() = xdgToplevelPtr
```

Note: the constructor parameter is already named `xdgToplevelPtr`, so we add a `val` property declaration.

Change the constructor parameter from `private val xdgToplevelPtr: Long` to `val xdgToplevelPtr: Long`.

### Task 2: Add internal methods to WaylandWindow

**Files:**
- Modify: `kadre-wayland/src/jvmMain/kotlin/org/graphiks/kadre/wayland/WaylandWindow.kt:71`

- [ ] **Step 1: Add internal methods for extension support**

Add after the `applyWaylandInputRegionHittest` method (around line 851):

```kotlin
    /** Returns the xdg_toplevel pointer, or 0 if not available. */
    internal fun xdgToplevelPtr(): Long =
        xdg?.xdgToplevelPtr ?: 0L

    /**
     * Sets the client-side decoration preference.
     * When preferCsd=true, requests client-side decorations (CSD);
     * when false, requests server-side decorations (SSD).
     */
    internal fun setPreferCsd(preferCsd: Boolean) {
        xdg?.setDecorations(!preferCsd)
        flushDisplay()
    }

    /**
     * Sets the xdg_activation_v1 activation token for this window.
     * Stub: xdg_activation_v1 protocol not yet wired in Kadre.
     */
    internal fun setActivationToken(token: String?) {
        // no-op: xdg_activation_v1 is not yet bound.
        // TODO: wire xdg_activation_v1 when the protocol is generated.
    }
```

### Task 3: Create WaylandExtensionTypes.kt

**Files:**
- Create: `kadre-wayland/src/jvmMain/kotlin/org/graphiks/kadre/wayland/WaylandExtensionTypes.kt`

- [ ] **Step 1: Create the extension types file**

```kotlin
/**
 * Wayland platform extension types.
 *
 * Mirrors winit's `WindowExtWayland`, `EventLoopBuilderExtWayland`,
 * and `WindowAttributesWayland` extension traits.
 */
package org.graphiks.kadre.wayland

import org.graphiks.kadre.core.ActiveEventLoop
import org.graphiks.kadre.core.Window
import org.graphiks.kadre.core.WindowAttributes
import org.graphiks.kadre.core.RawWindowHandle
import org.graphiks.kadre.core.RawDisplayHandle

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
 * @property name App ID equivalent (wl_surface name / app_id).
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
    // This is applied per-window at creation time via WaylandWindowAttributes.
    // For runtime changes, call the corresponding window extension.
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
```

### Task 4: Add storage fields to WaylandEventLoop

**Files:**
- Modify: `kadre-wayland/src/jvmMain/kotlin/org/graphiks/kadre/wayland/WaylandEventLoop.kt:64`

- [ ] **Step 1: Add mutable storage fields for per-loop extension state**

Add after `decorationManagerPtr` (around line 69):

```kotlin
    /**
     * Default CSD preference for newly created windows.
     * Set via [setPreferCsd] extension.
     */
    @Volatile
    internal var _preferCsd: Boolean = false

    /**
     * Activation token for xdg_activation_v1, set via [setActivationToken] extension.
     * Applied to the next window(s) created.
     */
    internal var _activationToken: String? = null
```

- [ ] **Step 2: Add createWindow(WaylandWindowAttributes) overload**

Add after the existing `createWindow(WindowAttributes)` method (around line 116):

```kotlin
    /**
     * Creates a window with Wayland-specific attributes.
     *
     * Merges [WaylandWindowAttributes] fields into the core [WindowAttributes]
     * and applies platform-specific settings at creation time.
     */
    fun createWindow(attrs: WaylandWindowAttributes): Window {
        val window = WaylandWindow.create(
            display = displayPtr,
            compositor = compositorPtr,
            xdgWmBase = xdgWmBasePtr,
            attrs = attrs.core,
            decorationManager = decorationManagerPtr,
        ) ?: error("WaylandWindow.create failed — libwayland-client.so.0 absent")
        window.onWindowEvent = { event -> eventQueue.add(window.id to event) }
        windows[window.id.value] = window
        // Apply platform extension settings
        attrs.preferCsd?.let { window.setPreferCsd(it) }
        attrs.activationToken?.let { window.setActivationToken(it) }
        attrs.name?.let { /* TODO: set wl_surface name via xdg_toplevel.set_app_id */ }
        eventQueue.add(window.id to org.graphiks.kadre.core.WindowEvent.RedrawRequested)
        return window
    }
```

### Task 5: Verify compilation

- [ ] **Step 1: Compile the Wayland module**

Run: `./gradlew :kadre-wayland:compileKotlinJvm`

Expected: BUILD SUCCESSFUL
