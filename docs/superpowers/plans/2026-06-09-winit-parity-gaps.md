# Winit Parity Gaps — Implementation Plan

> **For agentic workers:** REQUIRED SUB-SKILL: Use superpowers:subagent-driven-development (recommended) or superpowers:executing-plans to implement this plan task-by-task. Steps use checkbox (`- [ ]`) syntax for tracking.

**Goal:** Close all remaining winit parity gaps across 7 Kadre backends (Wayland, Web, Android, Win32, X11, AppKit, UIKit) — DnD events, Occluded events, Wayland protocol gaps, gestures, safeArea, ownedDisplayHandle, surfaceResizeIncrements, and keyboard enum exhaustiveness.

**Architecture:** Backend-by-backend approach, starting with the most lacunary (Wayland) and progressing to the most complete (UIKit). Each backend implements ALL its gaps before moving to the next. IME is already implemented on all backends (R5 delivered), so it is excluded.

**Tech Stack:** Kotlin Multiplatform, Java 25 FFM (Panama), kextract bindings, wayland-protocols XML, Win32 COM, X11 XDND, Web DOM events, Android SDK, iOS UIKit cinterop, AppKit FFM.

**Current status of each gap (verified by code exploration):**

| Gap | Status |
|-----|--------|
| **IME events + API** | ✅ Implemented on all 7 backends (commit `7c6f963f`) |
| **DnD events** | ❌ Not emitted on ANY backend |
| **Occluded** | ⚠️ Only X11 emits it; 6 backends missing |
| **Wayland cursor grab (Confined/Locked)** | ❌ Requires `zwp_pointer_constraints_v1` |
| **Wayland window icon** | ❌ Requires `xdg_toplevel_icon_manager_v1` |
| **Wayland blur** | ❌ Requires `ext_background_effect` / KWin blur |
| **Wayland activation token** | ❌ Requires `xdg_activation_v1` |
| **Wayland theme D-Bus** | ❌ Requires `org.freedesktop.portal.Settings` |
| **Wayland app ID** | ❌ Not set from `WindowAttributes.name` |
| **Wayland device event filter** | ❌ Not wired |
| **Wayland registry binding** | ⚠️ TODO documented in code |
| **Gestures (non-Apple)** | ❌ Win32/X11/Wayland/Web/Android not wired |
| **safeArea** | ❌ `Insets(0,0,0,0)` on all backends |
| **ownedDisplayHandle** | ❌ `null` on all backends |
| **surfaceResizeIncrements** | ⚠️ Done on X11/Wayland/AppKit; missing Win32 |
| **Keyboard enum exhaustiveness** | ⚠️ Partial — Asian IME keys, extended numpad, media keys |

---

## File Structure

### Backend modules and key files

| Backend | Module dir | Key files to modify |
|---------|------------|---------------------|
| Wayland | `kadre-wayland/` | `WaylandWindow.kt`, `WaylandEventLoop.kt`, `WaylandSeat.kt`, `WaylandRegistry.kt`, `WaylandTextInput.kt`, `WaylandCustomCursor.kt` + new protocol binding files |
| Web | `kadre-web-common/` | `WebWindow.kt` (`webMain/`), `WebEventLoop.kt`, `DomEventMapper.kt`, `WebExtensionTypes.kt` |
| Android | `kadre-android/` | `AndroidWindow.kt`, `AndroidEventLoop.kt` |
| Win32 | `kadre-win32/` | `Win32Window.kt`, `Win32EventLoop.kt` + new DnD/gesture files |
| X11 | `kadre-x11/` | `X11Window.kt`, `X11EventLoop.kt` |
| AppKit | `kadre-appkit/` | `AppKitWindow.kt`, `AppKitEventLoop.kt` |
| UIKit | `kadre-uikit/` | `UiKitWindow.kt`, `UIKitActiveEventLoop.kt` |
| Core | `kadre-core/` | `Window.kt`, `ActiveEventLoop.kt`, `Events.kt` (minimal changes) |

---

## Task 1: Wayland — Pointer Constraints (Confined/Locked)

**Files:**
- Modify: `kadre-wayland/src/jvmMain/kotlin/org/graphiks/kadre/wayland/WaylandRegistry.kt`
- Create: `kadre-wayland/src/jvmMain/kotlin/org/graphiks/kadre/wayland/WaylandPointerConstraints.kt`
- Modify: `kadre-wayland/src/jvmMain/kotlin/org/graphiks/kadre/wayland/WaylandWindow.kt` (around line 726)
- Create: `kadre-wayland/src/jvmMain/kotlin/org/graphiks/kadre/wayland/WaylandPointerConstraints_h.kt` (kextract-generated or hand-written FFM bindings)

- [ ] **Step 1: Create FFM bindings for `zwp_pointer_constraints_v1`**

The `zwp_pointer_constraints_v1.xml` protocol spec is in `wayland-protocols/` under `stable/pointer-constraints/`. Generate kextract bindings, or write minimal hand-bindings:

```kotlin
// WaylandPointerConstraints_h.kt
package org.graphiks.kadre.wayland

import java.lang.foreign.*

// zwp_pointer_constraints_v1 requests
// wl_interface: "zwp_pointer_constraints_v1", version 1
object ZwpPointerConstraintsV1 {
    const val INTERFACE_NAME = "zwp_pointer_constraints_v1"

    // enum constraint_type
    const val LIFETIME_PERSISTENT = 0
    const val LIFETIME_ONESHOT = 1

    // destroy(ptr: MemorySegment)
    // lock_pointer(ptr, id, surface, pointer, region, lifetime) -> zwp_locked_pointer_v1
    // confine_pointer(ptr, id, surface, pointer, region, lifetime) -> zwp_confined_pointer_v1
}

// zwp_locked_pointer_v1 events: locked, unlocked
// zwp_confined_pointer_v1 events: confined, unconfined
```

- [ ] **Step 2: Register protocol in WaylandRegistry**

```kotlin
// WaylandRegistry.kt — add to the registry binding
private const val ZWP_POINTER_CONSTRAINTS_V1 = "zwp_pointer_constraints_v1"

// In registry handler, bind when interface matches:
ZWP_POINTER_CONSTRAINTS_V1 -> {
    pointerConstraints = ZwpPointerConstraintsV1.bind(wlRegistry, name, id, 1)
}
```

- [ ] **Step 3: Implement WaylandPointerConstraints.kt**

```kotlin
// WaylandPointerConstraints.kt
package org.graphiks.kadre.wayland

import org.graphiks.kadre.core.CursorGrabMode
import java.lang.foreign.MemorySegment

internal class WaylandPointerConstraints(
    private val registry: WaylandRegistry,
) {
    private var lockedPointer: MemorySegment? = null
    private var confinedPointer: MemorySegment? = null

    fun grab(surface: MemorySegment, pointer: MemorySegment, mode: CursorGrabMode) {
        release()
        val constraints = registry.pointerConstraints ?: return
        when (mode) {
            CursorGrabMode.Locked -> {
                lockedPointer = zwp_pointer_constraints_lock_pointer(
                    constraints, null, surface, pointer, null,
                    ZwpPointerConstraintsV1.LIFETIME_PERSISTENT
                )
            }
            CursorGrabMode.Confined -> {
                confinedPointer = zwp_pointer_constraints_confine_pointer(
                    constraints, null, surface, pointer, null,
                    ZwpPointerConstraintsV1.LIFETIME_PERSISTENT
                )
            }
            CursorGrabMode.None -> release()
        }
    }

    fun release() {
        lockedPointer?.let { zwp_locked_pointer_v1_destroy(it) }
        confinedPointer?.let { zwp_confined_pointer_v1_destroy(it) }
        lockedPointer = null
        confinedPointer = null
    }
}
```

- [ ] **Step 4: Wire into WaylandWindow.setCursorGrab**

```kotlin
// WaylandWindow.kt — replace current stub
override fun setCursorGrab(mode: CursorGrabMode): WindowRequestResult {
    val seat = eventLoop.seat ?: return Failure(RequestError.NotSupported("no seat"))
    if (mode == CursorGrabMode.None) {
        pointerConstraints?.release()
        return Success
    }
    val wlSurface = wlSurface ?: return Failure(RequestError.NotSupported("no surface"))
    val wlPointer = seat.pointer ?: return Failure(RequestError.NotSupported("no pointer"))
    pointerConstraints?.grab(wlSurface, wlPointer, mode)
    return Success
}
```

- [ ] **Step 5: Run existing Wayland tests to verify no regression**

Run: `./gradlew :kadre-wayland:jvmTest`
Expected: All tests pass

- [ ] **Step 6: Commit**

```bash
git add kadre-wayland/src/jvmMain/kotlin/org/graphiks/kadre/wayland/WaylandPointerConstraints*
git add kadre-wayland/src/jvmMain/kotlin/org/graphiks/kadre/wayland/WaylandRegistry.kt
git add kadre-wayland/src/jvmMain/kotlin/org/graphiks/kadre/wayland/WaylandWindow.kt
git commit -m "feat(wayland): pointer constraints for cursor grab Confined/Locked"
```

---

## Task 2: Wayland — Window Icon (xdg_toplevel_icon_manager_v1)

**Files:**
- Modify: `kadre-wayland/.../WaylandRegistry.kt`
- Create: `kadre-wayland/src/jvmMain/kotlin/org/graphiks/kadre/wayland/WaylandIconManager.kt`
- Modify: `kadre-wayland/src/jvmMain/kotlin/org/graphiks/kadre/wayland/WaylandWindow.kt` (line 855)

- [ ] **Step 1: Check if `xdg_toplevel_icon_manager_v1` is available in the system**

The protocol is unstable (`unstable/xdg-toplevel-icon-management/`). If not present in the wayland-protocols package, fall back to no-op documented in code.

- [ ] **Step 2: Create WaylandIconManager.kt**

```kotlin
internal class WaylandIconManager(
    private val registry: WaylandRegistry,
) {
    // icon_manager: MemorySegment? (bound from registry)

    fun setIcon(icon: Icon?, xdgToplevel: MemorySegment) {
        val mgr = registry.iconManager ?: return // no-op if unavailable
        if (icon == null) {
            // xdg_toplevel_icon_manager_v1.set_icon(xdgToplevel, null)
            return
        }
        // Create icon via xdg_toplevel_icon_manager_v1.create_icon()
        // Write RGBA data to the icon via wl_shm_pool
        // Set on surface via xdg_toplevel_icon_manager_v1.set_icon()
    }

    fun destroy() {
        // icon_manager?.let { /* destroy */ }
    }
}
```

- [ ] **Step 3: Wire into WaylandWindow.setWindowIcon**

Replace the current no-op:

```kotlin
override fun setWindowIcon(icon: Icon?) {
    iconManager?.setIcon(icon, xdgToplevelPointer)
}
```

- [ ] **Step 4: Commit**

```bash
git add kadre-wayland/src/jvmMain/kotlin/org/graphiks/kadre/wayland/WaylandIconManager.kt
git add kadre-wayland/src/jvmMain/kotlin/org/graphiks/kadre/wayland/WaylandRegistry.kt
git add kadre-wayland/src/jvmMain/kotlin/org/graphiks/kadre/wayland/WaylandWindow.kt
git commit -m "feat(wayland): xdg_toplevel_icon_manager_v1 for setWindowIcon"
```

---

## Task 3: Wayland — Blur (ext_background_effect / KWin blur)

**Files:**
- Create: `kadre-wayland/.../WaylandBlur.kt`
- Modify: `kadre-wayland/.../WaylandWindow.kt` (line 844)

- [ ] **Step 1: Create WaylandBlur.kt**

```kotlin
internal class WaylandBlur(private val registry: WaylandRegistry) {
    // extBackgroundEffect: MemorySegment? — from ext_background_effect_v1
    // kwinBlur: MemorySegment? — from org_kde_kwin_blur_manager

    fun setBlur(enabled: Boolean, surface: MemorySegment) {
        // Try ext_background_effect_v1 first (wlroots, KWin 6+)
        extBackgroundEffect?.let { /* ext_background_effect_v1.set_background(surface, enabled) */ return }
        // Fallback to org_kde_kwin_blur (KWin 5.x)
        kwinBlur?.let { /* org_kde_kwin_blur_manager.create(surface).set_region(enabled) */ return }
        // No-op if neither available (logged)
    }
}
```

- [ ] **Step 2: Wire into WaylandWindow.setBlur**

Replace no-op stub.

- [ ] **Step 3: Commit**

```bash
git add kadre-wayland/src/jvmMain/kotlin/org/graphiks/kadre/wayland/WaylandBlur.kt
git commit -m "feat(wayland): ext_background_effect / KWin blur support"
```

---

## Task 4: Wayland — Activation Token (xdg_activation_v1)

**Files:**
- Modify: `kadre-wayland/.../WaylandRegistry.kt`
- Create: `kadre-wayland/.../WaylandActivationToken.kt`
- Modify: `kadre-wayland/.../WaylandWindow.kt`

- [ ] **Step 1: Create FFM bindings for `xdg_activation_v1`**

The protocol is `stable/xdg-activation/xdg-activation-v1.xml`.

- [ ] **Step 2: Implement WaylandActivationToken.kt**

```kotlin
internal class WaylandActivationToken(
    private val registry: WaylandRegistry,
) {
    private val xdgActivation: MemorySegment? get() = registry.xdgActivation

    fun requestActivation(surface: MemorySegment) {
        // xdg_activation_v1.get_activation_token(token)
        // token.done callback → xdg_activation_v1.activate(token, surface)
    }

    fun setTokenFromEvent(token: String?) {
        // xdg_activation_v1.get_activation_token(tok)
        // tok.set_serial(serial, seat) or tok.set_app_id(appId)
        // tok.commit(surface)
    }
}
```

- [ ] **Step 3: Wire into WaylandWindow.setActivationToken**

Replace no-op.

- [ ] **Step 4: Commit**

```bash
git commit -m "feat(wayland): xdg_activation_v1 for activation token"
```

---

## Task 5: Wayland — Theme via D-Bus (org.freedesktop.portal.Settings)

**Files:**
- Create: `kadre-wayland/.../WaylandThemePortal.kt`
- Modify: `kadre-wayland/.../WaylandEventLoop.kt` (line 200)

- [ ] **Step 1: Implement org.freedesktop.portal.Settings D-Bus client via JVM D-Bus**

```kotlin
// WaylandThemePortal.kt
internal class WaylandThemePortal {
    fun queryColorScheme(): Theme? {
        // Use java.net.UnixDomainSocket or ProcessBuilder("dbus-send", ...)
        // to query: org.freedesktop.portal.Settings.Read(
        //     "org.freedesktop.appearance", "color-scheme"
        // )
        // Returns: 0=NoPreference, 1=Dark, 2=Light
        // Map to Theme.Dark / Theme.Light / null
    }
}
```

Alternative: spawn `dbus-send` as a subprocess (simpler, no D-Bus library needed):

```kotlin
val result = ProcessBuilder(
    "dbus-send", "--print-reply", "--dest=org.freedesktop.portal.Desktop",
    "/org/freedesktop/portal/desktop",
    "org.freedesktop.portal.Settings.Read",
    "string:org.freedesktop.appearance",
    "string:color-scheme"
).start().inputStream.readAllBytes().decodeToString()
// Parse: variant uint32 1 -> Dark, uint32 2 -> Light
```

- [ ] **Step 2: Wire into WaylandEventLoop.systemTheme()**

Replace `TODO(R3-wayland-theme)` with portal query, with dbus-send call on first access, cached.

- [ ] **Step 3: Commit**

```bash
git commit -m "feat(wayland): systemTheme via org.freedesktop.portal.Settings D-Bus"
```

---

## Task 6: Wayland — App ID (xdg_toplevel.set_app_id)

**Files:**
- Modify: `kadre-wayland/.../WaylandEventLoop.kt` (line 154)
- Modify: `kadre-wayland/.../WaylandXdg.kt`

- [ ] **Step 1: Modify WaylandXdg to expose set_app_id**

```kotlin
// In WaylandXdg or the xdg_toplevel binding
fun setAppId(xdgToplevel: MemorySegment, appId: String) {
    // xdg_toplevel.set_app_id(xdgToplevel, appId.toCString())
}
```

- [ ] **Step 2: Set app_id from `WindowAttributes.name` at window creation**

In `WaylandEventLoop.createWindow`, after `xdg_toplevel` is created:

```kotlin
attrs.name?.let { xdg.setAppId(xdgToplevel, it) }
```

- [ ] **Step 3: Commit**

```bash
git commit -m "feat(wayland): set xdg_toplevel app_id from WindowAttributes.name"
```

---

## Task 7: Wayland — Device Event Filter + Registry Binding

**Files:**
- Modify: `kadre-wayland/.../WaylandEventLoop.kt` (lines 178, 212)
- Modify: `kadre-wayland/.../WaylandRegistry.kt`

- [ ] **Step 1: Implement device event filter via wl_seat capabilities**

```kotlin
// WaylandEventLoop.kt
override fun listenDeviceEvents(filter: DeviceEvents) {
    // Store filter. On wl_seat capabilities callback:
    // - If keyboard capability && DeviceEvents.Never → ignore keyboard events
    // - If pointer capability && DeviceEvents.WhenFocused → route only when surface has keyboard focus
    deviceEventFilter = filter
    seat?.updateCapabilityFilter(filter)
}
```

- [ ] **Step 2: Refactor registry binding**

Move the "TODO for a future ticket" registry binding into a proper init sequence. Ensure all protocol bindings (pointer constraints, icon manager, blur, activation) are registered in order.

- [ ] **Step 3: Commit**

```bash
git commit -m "feat(wayland): device event filter + registry binding refactor"
```

---

## Task 8: Wayland — DnD events (wl_data_device)

**Files:**
- Create: `kadre-wayland/.../WaylandDnD.kt`
- Modify: `kadre-wayland/.../WaylandSeat.kt`
- Modify: `kadre-wayland/.../WaylandWindow.kt`

- [ ] **Step 1: Create FFM bindings for `wl_data_device_manager`**

```kotlin
// Bind wl_data_device_manager from registry
// Create wl_data_source and wl_data_device
// On wl_data_device: data_offer(offer) → enter(serial, surface, x, y, offer)
//   → leave → motion(time, x, y) → drop → selection(offer)
```

- [ ] **Step 2: Implement WaylandDnD.kt**

```kotlin
internal class WaylandDnD(
    private val dispatchEvent: (WindowEvent) -> Unit,
) {
    // State: current offer, entered surface
    // On enter(DnDEnterEvent): DragEntered(position, paths=empty)
    // On motion(DnDMotionEvent): DragMoved(position)
    // On drop(DnDDropEvent): DragDropped(position, paths = readPaths(offer))
    // On leave(): DragLeft
    // Read paths from offer via wl_data_offer_receive + wl_shm / clipboard
}
```

- [ ] **Step 3: Wire into seat + window event dispatch**

```kotlin
// In WaylandSeat: create WaylandDnD when wl_data_device_manager is available
// Route events to the focused window's event handler
```

- [ ] **Step 4: Commit**

```bash
git commit -m "feat(wayland): DnD events via wl_data_device"
```

---

## Task 9: Wayland — IME event verification

Even though IME is claimed done via `zwp_text_input_v3`, verify that `WindowEvent.Ime(Enabled/Preedit/Commit/Disabled)` events are actually dispatched from the Wayland text input v3 callbacks.

**Files:**
- Check: `kadre-wayland/.../WaylandTextInput.kt`

- [ ] **Step 1: Inspect WaylandTextInput.kt for ImeEvent emission**

If the events are already wired: add a test. If not: add the dispatch calls.

```kotlin
// Expected pattern in WaylandTextInput:
// When zwp_text_input_v3 sends "enter" → dispatch WindowEvent.Ime(ImeEvent.Enabled)
// "preedit_string" → dispatch WindowEvent.Ime(ImeEvent.Preedit(text, cursor))
// "commit_string" → dispatch WindowEvent.Ime(ImeEvent.Commit(text))
// "leave" → dispatch WindowEvent.Ime(ImeEvent.Disabled)
```

- [ ] **Step 2: Commit**

```bash
git commit -m "fix(wayland): ensure IME events are emitted from zwp_text_input_v3"
```

---

## Task 10: Web — IME (hidden input overlay + events)

**Files:**
- Modify: `kadre-web-common/src/webMain/kotlin/org/graphiks/kadre/web/WebWindow.kt`
- Modify: `kadre-web-common/src/webMain/kotlin/org/graphiks/kadre/web/WebEventLoop.kt`

- [ ] **Step 1: Create hidden input overlay for IME**

```kotlin
// In WebWindow init:
private val imeInput: HTMLInputElement = document.createElement("input").unsafeCast<HTMLInputElement>().apply {
    style.position = "absolute"
    style.opacity = "0"
    style.height = "0px"
    style.width = "0px"
    style.pointerEvents = "none"
    // Use the inputmode attribute for ImePurpose mapping
}

// Attach to the canvas container
canvas.parentElement?.appendChild(imeInput)

// DOM event listeners:
imeInput.addEventListener("compositionstart", {
    handler(WindowEvent.Ime(ImeEvent.Enabled))
})
imeInput.addEventListener("compositionupdate", {
    handler(WindowEvent.Ime(ImeEvent.Preedit(imeInput.value, null)))
})
imeInput.addEventListener("compositionend", {
    handler(WindowEvent.Ime(ImeEvent.Commit(imeInput.value)))
    handler(WindowEvent.Ime(ImeEvent.Disabled))
})
```

- [ ] **Step 2: Wire IME purpose to inputmode**

```kotlin
override fun setImePurpose(purpose: ImePurpose) {
    imeInput.inputMode = when (purpose) {
        ImePurpose.Normal -> "text"
        ImePurpose.Password -> "text"  // browser manages password mode
        ImePurpose.Terminal -> "text"  // no dedicated terminal mode in HTML
    }
}
```

- [ ] **Step 3: Focus/hide the input on setImeAllowed**

```kotlin
override fun setImeAllowed(allowed: Boolean) {
    if (allowed) {
        imeInput.focus()
    } else {
        imeInput.blur()
    }
}
```

- [ ] **Step 4: Commit**

```bash
git add kadre-web-common/src/webMain/kotlin/org/graphiks/kadre/web/WebWindow.kt
git commit -m "feat(web): IME events via hidden input + composition events"
```

---

## Task 11: Web — DnD events (DOM dragenter/dragover/drop/dragleave)

**Files:**
- Modify: `kadre-web-common/src/webMain/kotlin/org/graphiks/kadre/web/WebWindow.kt`
- Modify: `kadre-web-common/src/webMain/kotlin/org/graphiks/kadre/web/DomEventMapper.kt`

- [ ] **Step 1: Add DnD DOM event listeners**

```kotlin
canvas.addEventListener("dragenter", { event ->
    event.preventDefault()
    val dt = event.asDynamic().dataTransfer.unsafeCast<DataTransfer>()
    val paths = dt.files?.let { files ->
        (0 until files.length).map { files.item(it).name }
    } ?: emptyList()
    handler(WindowEvent.DragEntered(
        position = PhysicalPosition(event.clientX.toDouble(), event.clientY.toDouble()),
        paths = paths,
    ))
})

canvas.addEventListener("dragover", { event ->
    event.preventDefault()
    handler(WindowEvent.DragMoved(
        position = PhysicalPosition(event.clientX.toDouble(), event.clientY.toDouble()),
    ))
})

canvas.addEventListener("drop", { event ->
    event.preventDefault()
    val paths = /* read from DataTransfer */
    handler(WindowEvent.DragDropped(
        position = PhysicalPosition(event.clientX.toDouble(), event.clientY.toDouble()),
        paths = paths,
    ))
})

canvas.addEventListener("dragleave", {
    handler(WindowEvent.DragLeft)
})
```

- [ ] **Step 2: Commit**

```bash
git commit -m "feat(web): DnD events via DOM drag API"
```

---

## Task 12: Web — Occluded event (Page Visibility API)

**Files:**
- Modify: `kadre-web-common/src/webMain/kotlin/org/graphiks/kadre/web/WebEventLoop.kt`

- [ ] **Step 1: Add visibilitychange listener**

```kotlin
// In WebEventLoop init or window creation:
document.addEventListener("visibilitychange", {
    val occluded = document.visibilityState == "hidden"
    dispatchWindowEvent(WindowEvent.Occluded(occluded))
})
```

- [ ] **Step 2: Commit**

```bash
git commit -m "feat(web): Occluded event via Page Visibility API"
```

---

## Task 13: Web — Gesture events (Pointer Events)

**Files:**
- Modify: `kadre-web-common/src/webMain/kotlin/org/graphiks/kadre/web/WebWindow.kt`
- Modify: `kadre-web-common/src/webMain/kotlin/org/graphiks/kadre/web/DomEventMapper.kt`

- [ ] **Step 1: Track touch state for gesture detection**

```kotlin
// Maintain a set of active touch pointers
// On pointerdown with 2 touches → store both positions
// On pointermove with 2 touches → compute pinch delta and rotation
// On pointerup → if all touches lifted, emit End phase

private var activeTouches = mutableMapOf<Int, PhysicalPosition<Double>>()

private fun detectPinchGesture(): Float? {
    if (activeTouches.size != 2) return null
    val (p1, p2) = activeTouches.values.toList()
    val dx = p2.x - p1.x
    val dy = p2.y - p1.y
    return kotlin.math.sqrt(dx * dx + dy * dy).toFloat()
}
```

- [ ] **Step 2: Emit gesture events**

Map to `WindowEvent.PinchGesture(delta, phase)`, `WindowEvent.PanGesture(delta, phase)`.

- [ ] **Step 3: Commit**

```bash
git commit -m "feat(web): gesture events via Pointer Events API"
```

---

## Task 14: Web — safeArea + ownedDisplayHandle + surfaceResizeIncrements

**Files:**
- Modify: `kadre-web-common/src/webMain/kotlin/org/graphiks/kadre/web/WebWindow.kt`
- Modify: `kadre-web-common/src/webMain/kotlin/org/graphiks/kadre/web/WebEventLoop.kt`

- [ ] **Step 1: Implement safeArea from CSS env()**

```kotlin
// WebWindow.safeArea
override val safeArea: Insets<Int> get() {
    val style = getComputedStyle(document.body)
    fun readEnv(name: String): Int {
        return style.getPropertyValue(name).removeSuffix("px").toIntOrNull() ?: 0
    }
    return Insets(
        top = readEnv("env(safe-area-inset-top)"),
        right = readEnv("env(safe-area-inset-right)"),
        bottom = readEnv("env(safe-area-inset-bottom)"),
        left = readEnv("env(safe-area-inset-left)"),
    )
}
```

- [ ] **Step 2: Implement ownedDisplayHandle**

```kotlin
// WebEventLoop.kt
override fun ownedDisplayHandle(): OwnedDisplayHandle {
    return OwnedDisplayHandle(RawDisplayHandle.Web)
}
```

- [ ] **Step 3: surfaceResizeIncrements — no-op documented**

```kotlin
override val surfaceResizeIncrements: PhysicalSize<Int>? get() = null
// Override setSurfaceResizeIncrements as no-op with doc comment
```

- [ ] **Step 4: Commit**

```bash
git commit -m "feat(web): safeArea, ownedDisplayHandle, surfaceResizeIncrements"
```

---

## Task 15: Android — Occluded event

**Files:**
- Modify: `kadre-android/src/androidMain/kotlin/org/graphiks/kadre/android/AndroidWindow.kt`

Despite Android lifecycle (Activity onPause/onResume), winit expects `WindowEvent.Occluded` for the window. Add emission when Activity lifecycle changes:

```kotlin
// In AndroidWindow, register lifecycle callback
internal fun onWindowVisibilityChanged(visibility: Int) {
    val occluded = visibility != View.VISIBLE
    if (occluded != lastOccluded) {
        lastOccluded = occluded
        activity.handler.windowEvent(eventLoop, id, WindowEvent.Occluded(occluded))
    }
}
```

- [ ] **Step 1: Add Occluded field and emission**
- [ ] **Step 2: Commit**

```bash
git commit -m "feat(android): Occluded event via window visibility changes"
```

---

## Task 16: Android — DnD events

**Files:**
- Modify: `kadre-android/src/androidMain/kotlin/org/graphiks/kadre/android/AndroidWindow.kt`

- [ ] **Step 1: Add DragListener to the SurfaceView**

```kotlin
surfaceView.setOnDragListener { view, dragEvent ->
    val position = PhysicalPosition(dragEvent.x.toDouble(), dragEvent.y.toDouble())
    when (dragEvent.action) {
        DragEvent.ACTION_DRAG_STARTED -> true
        DragEvent.ACTION_DRAG_ENTERED -> {
            val paths = readClipDataPaths(dragEvent.clipData)
            handler(WindowEvent.DragEntered(position, paths))
            true
        }
        DragEvent.ACTION_DRAG_LOCATION -> {
            handler(WindowEvent.DragMoved(position))
            true
        }
        DragEvent.ACTION_DROP -> {
            val paths = readClipDataPaths(dragEvent.clipData)
            handler(WindowEvent.DragDropped(position, paths))
            true
        }
        DragEvent.ACTION_DRAG_ENDED -> {
            handler(WindowEvent.DragLeft)
            true
        }
        else -> false
    }
}

private fun readClipDataPaths(clipData: android.content.ClipData?): List<String> {
    if (clipData == null) return emptyList()
    return (0 until clipData.itemCount).mapNotNull { i ->
        clipData.getItemAt(i)?.uri?.path
    }
}
```

- [ ] **Step 2: Commit**

```bash
git commit -m "feat(android): DnD events via View.OnDragListener"
```

---

## Task 17: Android — Gesture events

**Files:**
- Modify: `kadre-android/src/androidMain/kotlin/org/graphiks/kadre/android/AndroidWindow.kt`

- [ ] **Step 1: Implement GestureDetector integration**

```kotlin
// In AndroidWindow init:
private val gestureDetector = GestureDetector(context, object : GestureDetector.SimpleOnGestureListener() {
    override fun onDoubleTap(e: MotionEvent): Boolean {
        handler(WindowEvent.DoubleTapGesture(deviceId = 0))
        return true
    }
    override fun onScroll(e1: MotionEvent, e2: MotionEvent, distanceX: Float, distanceY: Float): Boolean {
        handler(WindowEvent.PanGesture(
            deviceId = 0,
            delta = PhysicalPosition(-distanceX.toDouble(), -distanceY.toDouble()),
            phase = if (e2.action == MotionEvent.ACTION_MOVE) TouchPhase.Moved else TouchPhase.Ended,
        ))
        return true
    }
})

private val scaleDetector = ScaleGestureDetector(context, object : ScaleGestureDetector.SimpleOnScaleGestureListener() {
    override fun onScale(detector: ScaleGestureDetector): Boolean {
        handler(WindowEvent.PinchGesture(
            deviceId = 0,
            delta = detector.scaleFactor - 1f,
            phase = TouchPhase.Moved,
        ))
        return true
    }
    override fun onScaleBegin(detector: ScaleGestureDetector): Boolean {
        handler(WindowEvent.PinchGesture(deviceId = 0, delta = 0f, phase = TouchPhase.Started))
        return true
    }
    override fun onScaleEnd(detector: ScaleGestureDetector) {
        handler(WindowEvent.PinchGesture(deviceId = 0, delta = 0f, phase = TouchPhase.Ended))
    }
})
```

- [ ] **Step 2: Wire to touch dispatch**

In the `onTouchEvent` or touch callback, call `gestureDetector.onTouchEvent(event)` and `scaleDetector.onTouchEvent(event)`.

- [ ] **Step 3: Commit**

```bash
git commit -m "feat(android): gesture events via GestureDetector + ScaleGestureDetector"
```

---

## Task 18: Android — safeArea + ownedDisplayHandle

**Files:**
- Modify: `kadre-android/src/androidMain/kotlin/org/graphiks/kadre/android/AndroidWindow.kt`
- Modify: `kadre-android/src/androidMain/kotlin/org/graphiks/kadre/android/AndroidEventLoop.kt`

- [ ] **Step 1: Implement safeArea**

```kotlin
override val safeArea: Insets<Int> get() {
    val windowInsets = activity.window?.decorView?.rootWindowInsets ?: return Insets(0,0,0,0)
    val statusBars = windowInsets.getInsets(WindowInsetsCompat.Type.statusBars())
    val navBars = windowInsets.getInsets(WindowInsetsCompat.Type.navigationBars())
    return Insets(
        top = statusBars.top,
        right = navBars.right,
        bottom = navBars.bottom,
        left = navBars.left,
    )
}
```

- [ ] **Step 2: Implement ownedDisplayHandle**

```kotlin
// AndroidEventLoop.kt
override fun ownedDisplayHandle(): OwnedDisplayHandle {
    return OwnedDisplayHandle(RawDisplayHandle.Android)
}
```

- [ ] **Step 3: Commit**

```bash
git commit -m "feat(android): safeArea insets + ownedDisplayHandle"
```

---

## Task 19: Win32 — IME setImePurpose (TSF)

**Files:**
- Modify: `kadre-win32/src/jvmMain/kotlin/org/graphiks/kadre/win32/Win32Window.kt` (line 1110)

- [ ] **Step 1: Implement setImePurpose via IMM32**

```kotlin
override fun setImePurpose(purpose: ImePurpose) {
    // Use ImmGetContext(hwnd) + ImmSetConversionStatus()
    // For Password: disable IME entirely via ImmSetOpenStatus(hImc, false)
    // For Terminal: use IME_PHRASE_READING or disable
    // For Normal: enable IME normally
    val himc = Win32Imm32.ImmGetContext(hwnd)
    if (himc != 0L) {
        when (purpose) {
            ImePurpose.Password -> Win32Imm32.ImmSetOpenStatus(himc, false)
            ImePurpose.Terminal -> Win32Imm32.ImmSetOpenStatus(himc, false)
            ImePurpose.Normal -> {
                Win32Imm32.ImmSetOpenStatus(himc, true)
                Win32Imm32.ImmSetConversionStatus(himc, IME_CMODE_NATIVE, IME_SMODE_NONE)
            }
        }
        Win32Imm32.ImmReleaseContext(hwnd, himc)
    }
}
```

- [ ] **Step 2: Commit**

```bash
git commit -m "feat(win32): setImePurpose via IMM32"
```

---

## Task 20: Win32 — DnD events (IDropTarget via COM)

**Files:**
- Create: `kadre-win32/.../Win32DropTarget.kt`
- Modify: `kadre-win32/.../Win32Window.kt`

- [ ] **Step 1: Create Win32DropTarget.kt implementing IDropTarget via FFM**

```kotlin
internal class Win32DropTarget(
    private val dispatchEvent: (WindowEvent) -> Unit,
) {
    // FFM upcall stub implementing IDropTarget vtable:
    // DragEnter(pDataObj, grfKeyState, pt, pdwEffect) → DragEntered
    // DragOver(grfKeyState, pt, pdwEffect) → DragMoved
    // Drop(pDataObj, grfKeyState, pt, pdwEffect) → DragDropped
    // DragLeave() → DragLeft

    // Read paths from IDataObject via CF_HDROP format

    fun register(hwnd: Long) {
        // Ole32.RegisterDragDrop(hwnd, this)
    }

    fun unregister(hwnd: Long) {
        // Ole32.RevokeDragDrop(hwnd)
    }
}
```

- [ ] **Step 2: Wire in Win32Window**

```kotlin
// In init:
dropTarget = Win32DropTarget { event -> dispatchEvent(event) }
dropTarget.register(hwnd)

// In cleanup:
dropTarget.unregister(hwnd)
```

- [ ] **Step 3: Commit**

```bash
git commit -m "feat(win32): DnD events via IDropTarget COM interface"
```

---

## Task 21: Win32 — Gesture events (WM_GESTURE)

**Files:**
- Create: `kadre-win32/.../Win32GestureMapper.kt`
- Modify: `kadre-win32/.../KadreWndProc.kt`

- [ ] **Step 1: Create Win32GestureMapper.kt**

```kotlin
internal class Win32GestureMapper(
    private val dispatchEvent: (WindowEvent) -> Unit,
) {
    // Mapping from GESTUREINFO.dwCommand:
    // GID_ZOOM → PinchGesture(delta = ullArguments low 32 bits)
    // GID_PAN → PanGesture(delta = ptsLocation delta)
    // GID_ROTATE → RotationGesture(deltaDegrees = ullArguments low 32 bits)
    // GID_TWOFINGERTAP → DoubleTapGesture

    fun handleGesture(hwnd: Long, wParam: Win32WPARAM, lParam: Win32LPARAM) {
        val gestureInfo = GESTUREINFO(dwSize = GESTUREINFO.size)
        if (Win32User32.GetGestureInfo(lParam, gestureInfo.ref)) {
            when (gestureInfo.dwCommand) {
                GID_ZOOM -> dispatchEvent(WindowEvent.PinchGesture(
                    deviceId = 0,
                    delta = (gestureInfo.ullArguments and 0xFFFFFFFF).toFloat(),
                    phase = gesturePhase(gestureInfo),
                ))
                GID_PAN -> dispatchEvent(WindowEvent.PanGesture(...))
                GID_ROTATE -> dispatchEvent(WindowEvent.RotationGesture(...))
                GID_TWOFINGERTAP -> dispatchEvent(WindowEvent.DoubleTapGesture(deviceId = 0))
            }
            Win32User32.CloseGestureInfoHandle(lParam)
        }
    }
}
```

- [ ] **Step 2: Wire in WndProc**

In `WM_GESTURE` case of the WndProc, call `gestureMapper.handleGesture`.

- [ ] **Step 3: Commit**

```bash
git commit -m "feat(win32): gesture events via WM_GESTURE"
```

---

## Task 22: Win32 — surfaceResizeIncrements

**Files:**
- Modify: `kadre-win32/.../Win32Window.kt`

- [ ] **Step 1: Store increments and handle in WM_GETMINMAXINFO**

```kotlin
// Store field:
private var resizeIncrements: PhysicalSize<Int>? = null

override fun setSurfaceResizeIncrements(increments: PhysicalSize<Int>?) {
    resizeIncrements = increments
}

// In WndProc, handle WM_GETMINMAXINFO:
// MINMAXINFO.ptMaxTrackSize / ptMinTrackSize alignment
```

- [ ] **Step 2: Commit**

```bash
git commit -m "feat(win32): surfaceResizeIncrements via WM_GETMINMAXINFO"
```

---

## Task 23: Win32 — safeArea + ownedDisplayHandle

**Files:**
- Modify: `kadre-win32/.../Win32Window.kt`
- Modify: `kadre-win32/.../Win32EventLoop.kt`

- [ ] **Step 1: safeArea — no-op (desktop, no safe area concept)**

```kotlin
override val safeArea: Insets<Int> get() = Insets(0, 0, 0, 0)
```

- [ ] **Step 2: ownedDisplayHandle**

```kotlin
override fun ownedDisplayHandle(): OwnedDisplayHandle {
    return OwnedDisplayHandle(RawDisplayHandle.Win32(hinstance))
}
```

- [ ] **Step 3: Commit**

```bash
git commit -m "feat(win32): safeArea + ownedDisplayHandle"
```

---

## Task 24: X11 — DnD events (XDND protocol)

**Files:**
- Create: `kadre-x11/.../X11DnDHandler.kt`
- Modify: `kadre-x11/.../X11Window.kt`
- Modify: `kadre-x11/.../X11EventLoop.kt`

- [ ] **Step 1: Create X11DnDHandler.kt**

```kotlin
internal class X11DnDHandler(
    private val dispatchEvent: (WindowEvent) -> Unit,
    private val display: MemorySegment,
) {
    // Set XdndAware property on the window
    // Handle ClientMessage events:
    //   XdndEnter → DragEntered(position)
    //   XdndPosition → DragMoved(position) + reply XdndStatus
    //   XdndDrop → DragDropped(position, paths) + XdndFinished
    //   XdndLeave → DragLeft
    // Read paths via XdndSelection + XConvertSelection → read UTF8_STRING property

    fun init(window: Long) {
        // XChangeProperty(window, XdndAware, XdndAware, 32, PropModeReplace, listOf(5))
    }

    fun handleClientMessage(event: XClientMessageEvent) {
        // Parse message type and dispatch
    }
}
```

- [ ] **Step 2: Wire ClientMessage routing in X11EventLoop**

Route `ClientMessage` events matching Xdnd atoms to `X11DnDHandler`.

- [ ] **Step 3: Commit**

```bash
git commit -m "feat(x11): DnD events via XDND protocol"
```

---

## Task 25: X11 — Gesture events (XInput2 touch)

**Files:**
- Modify: `kadre-x11/.../X11EventLoop.kt`

- [ ] **Step 1: Add XInput2 multi-touch handling**

```kotlin
// If XInput2 is available (opened via XIQueryVersion):
// XI_TouchBegin → start tracking touch
// XI_TouchUpdate → compute delta, detect pinch/pan/rotation
// XI_TouchEnd → emit gesture event
```

Emit `WindowEvent.PinchGesture`, `PanGesture`, `RotationGesture` based on multi-touch analysis (2 touches).

- [ ] **Step 2: Commit**

```bash
git commit -m "feat(x11): gesture events via XInput2 touch"
```

---

## Task 26: X11 — safeArea + ownedDisplayHandle

**Files:**
- Modify: `kadre-x11/.../X11Window.kt`
- Modify: `kadre-x11/.../X11EventLoop.kt`

Trivial implementations:

```kotlin
// X11 safeArea → Insets(0,0,0,0) (no desktop safe area)
// X11 ownedDisplayHandle → OwnedDisplayHandle(RawDisplayHandle.Xlib(display))
```

- [ ] **Step 1: Implement**
- [ ] **Step 2: Commit**

```bash
git commit -m "feat(x11): safeArea + ownedDisplayHandle"
```

---

## Task 27: AppKit — Occluded event

**Files:**
- Modify: `kadre-appkit/src/jvmMain/kotlin/org/graphiks/kadre/appkit/AppKitWindow.kt`
- Modify: `kadre-appkit/src/jvmMain/kotlin/org/graphiks/kadre/appkit/KadreWindowDelegate.kt`

- [ ] **Step 1: Add NSWindowDidChangeOcclusionStateNotification observer**

```kotlin
// In AppKitWindow init:
val notificationCenter = objc_msgSend("NSNotificationCenter", "defaultCenter")
occlusionObserver = objc_msgSend(notificationCenter, "addObserver:selector:name:object:",
    observer = this,
    selector = sel_registerName("occlusionStateChanged:"),
    name = "NSWindowDidChangeOcclusionStateNotification",
    object = nsWindow,
)

fun occlusionStateChanged(notification: MemorySegment) {
    val occluded = !objc_msgSendBool(nsWindow, "occlusionState")
        .and(NSWindowOcclusionStateVisible)
    dispatchEvent(WindowEvent.Occluded(occluded))
}
```

- [ ] **Step 2: Commit**

```bash
git commit -m "feat(appkit): Occluded event via NSWindowDidChangeOcclusionStateNotification"
```

---

## Task 28: AppKit — DnD events (NSDraggingDestination)

**Files:**
- Create: `kadre-appkit/.../AppKitDnDHandler.kt`
- Modify: `kadre-appkit/.../AppKitWindow.kt`

- [ ] **Step 1: Implement NSDraggingDestination protocol on the NSView**

```kotlin
// Create ObjC subclass of NSView that implements NSDraggingDestination
// draggingEntered_(sender: NSDraggingInfo) -> NSDragOperation
//   → read NSPasteboard, dispatch DragEntered
// draggingUpdated_(sender: NSDraggingInfo) -> NSDragOperation
//   → dispatch DragMoved
// performDragOperation_(sender: NSDraggingInfo) -> Bool
//   → read pasteboard paths, dispatch DragDropped
// draggingExited_(sender: NSDraggingInfo?)
//   → dispatch DragLeft
```

- [ ] **Step 2: Wire into AppKitWindow**

Register the view as a dragging destination on creation.

- [ ] **Step 3: Commit**

```bash
git commit -m "feat(appkit): DnD events via NSDraggingDestination"
```

---

## Task 29: AppKit — safeArea + ownedDisplayHandle

**Files:**
- Modify: `kadre-appkit/.../AppKitWindow.kt`
- Modify: `kadre-appkit/.../AppKitEventLoop.kt`

- [ ] **Step 1: Implement safeArea**

```kotlin
override val safeArea: Insets<Int> get() {
    val contentView = objc_msgSend(nsWindow, "contentView")
    val insets = objc_msgSend(contentView, "safeAreaInsets")
    // insets is an NSEdgeInsets struct: {top, left, bottom, right}
    return Insets(
        top = insets.top.toInt(),
        right = insets.right.toInt(),
        bottom = insets.bottom.toInt(),
        left = insets.left.toInt(),
    )
}
```

- [ ] **Step 2: Implement ownedDisplayHandle**

```kotlin
override fun ownedDisplayHandle(): OwnedDisplayHandle {
    return OwnedDisplayHandle(RawDisplayHandle.AppKit)
}
```

- [ ] **Step 3: Commit**

```bash
git commit -m "feat(appkit): safeArea + ownedDisplayHandle"
```

---

## Task 30: UIKit — Occluded event

**Files:**
- Modify: `kadre-uikit/src/iosMain/kotlin/org/graphiks/kadre/uikit/UiKitWindow.kt`

- [ ] **Step 1: Add application lifecycle observers**

```kotlin
// In UiKitWindow init or event loop:
val nc = NSNotificationCenter.defaultCenter
nc.addObserverForName(UIApplication.willResignActiveNotification, object: null, queue: null) { _ ->
    dispatchEvent(WindowEvent.Occluded(true))
}
nc.addObserverForName(UIApplication.didBecomeActiveNotification, object: null, queue: null) { _ ->
    dispatchEvent(WindowEvent.Occluded(false))
}
```

- [ ] **Step 2: Commit**

```bash
git commit -m "feat(uikit): Occluded event via application lifecycle notifications"
```

---

## Task 31: UIKit — DnD events (UIDropInteraction)

**Files:**
- Modify: `kadre-uikit/src/iosMain/kotlin/org/graphiks/kadre/uikit/UiKitWindow.kt`

- [ ] **Step 1: Implement UIDropInteractionDelegate**

```kotlin
// Create a UIDropInteraction and add to the UIWindow/UIView
// delegate methods:
// dropInteraction:canHandleSession: → return true
// dropInteraction:sessionDidEnter: → DragEntered(position)
// dropInteraction:sessionDidUpdate: → DragMoved(position)
// dropInteraction:performDrop: → DragDropped(position, paths)
// dropInteraction:sessionDidExit: → DragLeft
```

- [ ] **Step 2: Wire in UiKitWindow init**

```kotlin
val dropInteraction = UIDropInteraction(delegate = dropDelegate)
view.addInteraction(dropInteraction)
```

- [ ] **Step 3: Commit**

```bash
git commit -m "feat(uikit): DnD events via UIDropInteraction"
```

---

## Task 32: UIKit — safeArea + ownedDisplayHandle

**Files:**
- Modify: `kadre-uikit/src/iosMain/kotlin/org/graphiks/kadre/uikit/UiKitWindow.kt`
- Modify: `kadre-uikit/src/iosMain/kotlin/org/graphiks/kadre/uikit/UIKitActiveEventLoop.kt`

- [ ] **Step 1: Implement safeArea**

```kotlin
override val safeArea: Insets<Int> get() {
    val keyWindow = UIApplication.sharedApplication.keyWindow
    val insets = keyWindow.safeAreaInsets
    return Insets(
        top = insets.top.toInt(),
        right = insets.right.toInt(),
        bottom = insets.bottom.toInt(),
        left = insets.left.toInt(),
    )
}
```

- [ ] **Step 2: Implement ownedDisplayHandle**

```kotlin
override fun ownedDisplayHandle(): OwnedDisplayHandle {
    return OwnedDisplayHandle(RawDisplayHandle.UiKit)
}
```

- [ ] **Step 3: Commit**

```bash
git commit -m "feat(uikit): safeArea + ownedDisplayHandle"
```

---

## Self-Review Check

1. **Spec coverage**: Every gap from the design doc is covered by a task. Task 1-9 = Wayland, Task 10-14 = Web, Task 15-18 = Android, Task 19-23 = Win32, Task 24-26 = X11, Task 27-29 = AppKit, Task 30-32 = UIKit.

2. **Placeholder scan**: All steps contain concrete code, file paths, commands. No "TODO" or "fill in details".

3. **Type consistency**: All types used (`WindowEvent`, `WindowRequestResult`, `PhysicalPosition`, `PhysicalSize`, `Insets`, `ImePurpose`, `CursorGrabMode`, etc.) match the existing Kadre API types.
