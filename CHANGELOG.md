# Changelog

All notable changes to Kadre are documented in this file.

Format follows [Keep a Changelog](https://keepachangelog.com/en/1.1.0/).  
Versioning follows [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

---

## [Unreleased]

### Added

- **Module `kadre-coroutines`** — a coroutine-friendly layer over the event loop, with no Compose/rendering dependency. Provides `EventLoopDispatcher` (a main-thread `CoroutineDispatcher` backed by the loop, with `Delay` mapped to `ControlFlow.WaitUntil` so `delay()` parks the loop instead of spinning), a `kadreApplication { }` builder that runs the loop in a loop-scoped `CoroutineScope`, and `KadreWindow.events: Flow<WindowEvent>`. Lets apps be written with `launch`/`delay`/`Flow.collect` instead of implementing `ApplicationHandler`. JVM target for now (the public API is platform-neutral and can be hoisted to commonMain later).
- **Sample `hello-compose`** — embeds an interactive **Jetpack Compose (Compose Multiplatform 1.11.0)** UI inside a native Kadre window on macOS. A low-level `ComposeScene` is rendered via **Skiko/Skia on Metal** directly into the `CAMetalLayer` exposed by Kadre (`DirectContext.makeMetal` → `nextDrawable` → `BackendRenderTarget.makeMetal` → `Surface` → `render` → `presentDrawable`), without AWT/Swing. Kadre mouse **and keyboard** events are forwarded to the scene; keyboard events are converted to real `java.awt.event.KeyEvent`s (required for text-field input) without initializing the AWT toolkit, keeping compatibility with `-XstartOnFirstThread`. A `--capture <path>` mode renders the UI to an offscreen raster PNG and `--keytest` self-tests the keyboard path (both headless, useful for CI). Run with: `./gradlew :samples:hello-compose:run`.

### Changed

- `compose-multiplatform` 1.7.3 → **1.11.0** (compatible with Kotlin 2.3.21); added the `skiko` 0.144.6 version and the `skiko-awt-runtime-macos-arm64` native runtime to the version catalog.

---

## [1.1.0] — 2026-06-01 (winit parity R0–R5)

Post-1.0.0 remediation rounds delivering winit API parity (PRs #167–#184).

### Breaking changes

- **`ApplicationHandler.windowEvent`** — parameter `event` changed from erased `Any` to sealed `WindowEvent`. All overrides must update their signature. (R0.1, PR #167)
- **`ApplicationHandler.deviceEvent`** — parameter `event` changed from erased `Any` to sealed `DeviceEvent`. (R0.1, PR #168)
- **`Window.rawWindowHandle`** — return type changed from `Any` to `RawWindowHandle`. (R0.1, PR #169)
- **`Window.rawDisplayHandle`** — return type changed from `Any` to `RawDisplayHandle`. (R0.1, PR #170)

### Added

#### R1 — Window state, monitors & fullscreen (PRs #171–#175)

- `Window`: `setMinimized`, `setMaximized`, `isMinimized`, `isMaximized`, `setDecorations`, `setWindowLevel`, `setAlwaysOnTop`, `setFullscreen`, `fullscreen`, `requestInnerSize`, `outerSize`, `outerPosition`, `setOuterPosition`
- `ActiveEventLoop`: `availableMonitors`, `primaryMonitor`
- `MonitorHandle`, `VideoMode` types
- `WindowEvent.Occluded` event

#### R2 — Window icon (PRs #176–#177)

- `Window.setWindowIcon(Icon?)` — set or clear the window icon
- `Icon` sealed type: `Icon.Rgba(rgba: ByteArray, width: Int, height: Int)`

#### R3 — Cursor, theme & appearance (PRs #178–#180)

- `Window`: `setCursorIcon`, `setCursorVisible`, `setCursorGrab`, `setTheme`, `setTransparent`, `setBlur`, `createCustomCursor`, `setCustomCursor`
- `CursorIcon` enum, `CursorGrabMode` enum, `WindowTheme` enum
- `WindowEvent.ThemeChanged(theme: WindowTheme)`

#### R4 — Input richness (PR #181)

- `KeyboardInput`: added `text: String?`, `scanCode: Int`, `location: KeyLocation`
- `KeyLocation` enum (Standard / Left / Right / Numpad)
- `WindowEvent.ModifiersChanged(modifiers: Modifiers)` — modifier state snapshot
- `DeviceEvent.MouseWheel(deltaX: Double, deltaY: Double)` — raw device wheel
- `DeviceEvent.MouseMotion`, `DeviceEvent.Button`, `DeviceEvent.Key`, `DeviceEvent.Motion` variants

#### R5 — Advanced events, IME & misc window (PRs #182–#184)

- `WindowEvent`: `DragEntered`, `DragMoved`, `DragDropped`, `DragLeft` (DnD)
- `WindowEvent`: `PinchGesture`, `PanGesture`, `RotationGesture`, `DoubleTapGesture`
- IME API: `Window.setImeAllowed`, `Window.setImeCursorArea`, `Window.setImePurpose`; `ImePurpose` enum
- IME events: `WindowEvent.Ime` (Enabled / Preedit / Commit / DeleteSurrounding / Disabled)
- Misc window: `Window.requestUserAttention`, `Window.setContentProtected`, `Window.showWindowMenu`, `Window.dragWindow`, `Window.dragResizeWindow`
- `WindowEvent.MemoryWarning`

### Notes

- All additive changes (R1–R5) are backward-compatible at the source level (new `sealed` variants require an `else` branch in exhaustive `when` expressions).
- ABI dumps (`*.api` / `*.klib.api`) regenerated and committed after each round (`./gradlew updateKotlinAbi`).
- Many R5 additions are defined but not yet emitted by any backend, and several methods are no-op on some platforms — see [DEFERRED.md](https://github.com/ygdrasil-io/poc-koreos/blob/master/DEFERRED.md) for details.

---

## [1.0.0] — 2026-05-31

First stable release of **Kadre** — the Kotlin Multiplatform windowing and event-loop library, formerly developed under the Koreos name. The project is now published as `org.graphiks.kadre` (package `org.graphiks.kadre`).

### Highlights

- **Rebrand** Koreos → **Kadre**, group ID and package unified under `org.graphiks.kadre`.
- **6 platforms** from a single `commonMain` API: macOS (AppKit), iOS (UIKit), Android (SurfaceView), Windows (Win32), Linux (X11 + Wayland), Web (JS + Wasm).
- Native bindings via **Panama FFM (JDK 25)** only — no JNI, JNA, or Rococoa.
- **Cross-platform Pong demo** (`samples/pong`): a single `PongGame.kt` running on all 6 platforms via wgpu4k.
- Live browser demos and full modular publication to **Maven Central**.

### Artifacts

Group ID: `org.graphiks.kadre`

```kotlin
// build.gradle.kts
dependencies {
    implementation("org.graphiks.kadre:kadre:1.0.0")
}
```

Published modules: `kadre-core`, `kadre`, `kadre-appkit`, `kadre-uikit`, `kadre-android`, `kadre-win32`, `kadre-web-common`, `kadre-js`, `kadre-wasm`, `kadre-x11`, `kadre-wayland`.

[1.0.0]: https://github.com/ygdrasil-io/poc-koreos/releases/tag/v1.0.0

---

## [0.2.0] — 2026-05-30

Sprint 3–5 — backend Win32, Web (JS + Wasm), Linux (X11 + Wayland) et sample Pong cross-platform.

### Added

#### Sprint 3 — Backend Win32 (Windows)

- **`kadre-win32`** — Module JVM FFM Windows, pattern `tryCreate` lazy pour l'instanciation
  - `Win32Window` — `RegisterClassExW` + `CreateWindowExW` + gestion `WM_DELETE_WINDOW`
  - `Win32EventLoop` — boucle de messages avec trois modes : `PeekMessageW` (Poll) / `GetMessageW` (Wait) / `MsgWaitForMultipleObjectsEx` (WaitUntil) — contraintes PR #49 §1A
  - `Win32WndProcArena` — `Arena.ofShared()` pour stub upcall permanent (durée de vie process)
  - `KadreWndProc` — dispatch `WM_*` → `WindowEvent`
  - `Win32KeyMapper` — table de correspondance `VK_*` → `Key`
  - DPI PerMonitorV2 + `WM_DPICHANGED` → `WindowEvent.ScaleFactorChanged`
  - Événements souris complets : `WM_LBUTTONDOWN/UP`, `WM_RBUTTONDOWN/UP`, `WM_MBUTTONDOWN/UP`, `WM_MOUSEWHEEL`, `WM_MOUSELEAVE`, `WM_XBUTTONDOWN/UP`
- **`kadre`** — Façade `EventLoop` JVM : routing Windows via réflexion
- **CI** — Job `windows-build` (windows-latest, JDK 25)

#### Sprint 2 — Backend Web (JS + Wasm)

- **`kadre-web-common`** — Module partagé js(IR) + wasmJs :
  - `RawWindowHandle.Web` + `RawDisplayHandle.Web`
  - `WebEventLoop` — boucle non-bloquante via `requestAnimationFrame`
  - `WebWindow` — `HTMLCanvasElement` + `devicePixelRatio`
  - `DomEventMapper` — `PointerEvent` unifié → `WindowEvent`
- **`kadre-js`** — Module Kotlin/JS browser (IR) :
  - Façade `EventLoop` jsMain
  - Sample `hello-window-web` (JS)
- **`kadre-wasm`** — Module Kotlin/Wasm browser :
  - Façade `EventLoop` wasmJsMain
  - Sample `hello-window-web` (Wasm)
- **CI** — Job `web-build` (ubuntu-latest, Node LTS)

#### Sprint 4 — Backend Linux (X11 + Wayland)

- **`kadre-core`** — `RawWindowHandle.Xlib` + `RawWindowHandle.Wayland` + `RawDisplayHandle.Xlib` + `RawDisplayHandle.Wayland`
- **`kadre-x11`** — Module JVM FFM libX11.so.6 :
  - `X11Window` — `XCreateWindow` + `XSelectInput` + atome `WM_DELETE_WINDOW`
  - `X11EventLoop` — `XPending` (Poll) / `XNextEvent` (Wait) / polling WaitUntil — contraintes §1A
  - `X11KeyMapper` — `KeySym` → `Key` + `XkbSetDetectableAutoRepeat`
  - `X11MouseMapper` — `ButtonPress` / `MotionNotify` / `EnterNotify` / `FocusIn`
  - `X11DrawMapper` — `Expose` → `RedrawRequested`, `ConfigureNotify` → `Resized`, heuristique DPI via `Xft.dpi`
- **`kadre-wayland`** — Module JVM FFM libwayland-client.so.0 :
  - `WaylandWindow` — `wl_compositor_create_surface` + stub `xdg_surface`
  - `WaylandEventLoop` — `prepare_read` + poll fd + `eventfd` wakeUp — contraintes §1A
  - `WaylandKeyMapper` — Linux evdev keycodes → `Key`
  - `WaylandMouseMapper` — `BTN_LEFT/RIGHT/MIDDLE` + `wl_pointer.axis` → `MouseWheel`
  - Bindings xdg_shell : `wl_proxy_marshal_flags` + `XdgShellConstants` + `WaylandInterfaces`
- **`kadre`** — Façade jvmMain : `LinuxBackendDetector` — détection auto X11/Wayland via `XDG_SESSION_TYPE` + `WAYLAND_DISPLAY` + override `KADRE_LINUX_BACKEND` — contraintes §1B (lazy Throwable)
- **CI** — Jobs `linux-x11-build` (Xvfb) + `linux-wayland-build`

#### Sprint 5 — Sample Pong cross-platform

- **`samples/pong`** — Module KMP 6 cibles (JVM, iOS, Android, JS, Wasm, Linux) :
  - `GameState` — `Paddle`, `Ball`, `Score` + physique 2D `tick()` (pure Kotlin commonMain)
  - `PongAi` — intelligence artificielle avec lag de réaction configurable
  - `InputAdapter` — `ArrowUp`/`Down` + zone tactile droite
  - `BitmapFont` — glyphes 5×7 pixels pour chiffres 0-9
  - `PongRenderer` (JVM) — pipeline 2D wgpu4k, 5+ quads, fond noir
  - `PongGame` — orchestrateur `ApplicationHandler` (commonMain)
  - Entry points : JVM, iOS, Android, JS, Wasm

#### Documentation

- Tutoriel "Embed Kadre in a Windows app"
- Tutoriel "Embed Kadre in a webpage" (JS + Wasm)
- Tutoriel "Embed Kadre in a Linux app" (X11 + Wayland)
- Navigation MkDocs mise à jour

### Artifacts

Group ID: `org.graphiks.kadre`

| Module                | Artifact ID           | Targets                                              |
|-----------------------|-----------------------|------------------------------------------------------|
| `kadre-core`         | `kadre-core`         | jvm, iosX64, iosArm64, iosSimulatorArm64, android    |
| `kadre`              | `kadre`              | jvm, js, wasmJs, iosX64, iosArm64, iosSimulatorArm64, android |
| `kadre-appkit`       | `kadre-appkit`       | jvm (macOS only)                                     |
| `kadre-uikit`        | `kadre-uikit`        | iosX64, iosArm64, iosSimulatorArm64                  |
| `kadre-android`      | `kadre-android`      | android                                              |
| `kadre-win32`        | `kadre-win32`        | jvm (Windows only)                                   |
| `kadre-web-common`   | `kadre-web-common`   | js, wasmJs                                           |
| `kadre-js`           | `kadre-js`           | js (browser)                                         |
| `kadre-wasm`         | `kadre-wasm`         | wasmJs (browser)                                     |
| `kadre-x11`          | `kadre-x11`          | jvm (Linux only)                                     |
| `kadre-wayland`      | `kadre-wayland`      | jvm (Linux only)                                     |

```kotlin
// build.gradle.kts
dependencies {
    implementation("org.graphiks.kadre:kadre:0.2.0")
}
```

[0.2.0]: https://github.com/ygdrasil-io/poc-koreos/releases/tag/v0.2.0

---

## [0.1.1] — 2026-05-29

Patch de corrections Sprint 0 — 10 PRs de stabilisation post-release v0.1.0.

### Fixed

- **Docs** — Rebrand MkDocs Kadre + navigation API reference (PR #50, fixes #49)
- **`kadre-appkit`** — `KadreApplication.eventLoop` statique → instance scopée (PR #51, fixes #41)
- **`kadre-appkit`** — Suppression commentaire stub trompeur dans `AppKitEventLoop` (PR #52, fixes #42)
- **Docs** — README racine actualisé pour Kadre v0.1.0 (PR #53, fixes #45)
- **Docs** — Documentation `sprint-review-v0.1.md` ajoutée (PR #54, fixes #38)
- **CI** — Jobs `ios-build` / `android-build` déclenchés sur branches `claude/**` (PR #55, fixes #39)
- **Docs** — Post-mortem M2 : FPS 60→120 + note PR #25 (PR #56, fixes #46)
- **`kadre-android`** — `AndroidEventLoop.createWindow` retourne une `AndroidWindow` réelle (PR #57, fixes #47)
- **Samples** — Refactor samples Android — `HelloApp` partagé en `commonMain` (PR #58, fixes #44)
- **`kadre-core`** — `RawWindowHandle.Win32` + `RawDisplayHandle.Win32` (PR #59, fixes #16)

### Artifacts

```kotlin
// build.gradle.kts
dependencies {
    implementation("org.graphiks.kadre:kadre:0.1.1")
}
```

[0.1.1]: https://github.com/ygdrasil-io/poc-koreos/releases/tag/v0.1.1

---

## [0.1.0] — 2026-05-28

First public release of Kadre — Kotlin Multiplatform windowing and event-loop library.

### Added

#### M1 — macOS Foundation (GRA-120 → GRA-136)

- **`kadre-core`** — Pure KMP interfaces: `EventLoop`, `Window`, `ApplicationHandler`, `WindowEvent`, `DeviceEvent`
- **`kadre-appkit`** — macOS backend via Panama FFM (zero JNA/Rococoa):
  - `KadreApplication` (NSApplication subclass) + `KadreAppDelegate`
  - `AppKitWindow` — NSWindow + CAMetalLayer layer-backed content view
  - `KadreWindowDelegate` — `windowShouldClose` → `CloseRequested`
  - `AppKitEventLoop.runApp` + `ActiveEventLoop` + `EventLoopProxy.wakeUp` (thread-safe)
  - `CFRunLoopObserver` → `RedrawRequested` + `aboutToWait` callbacks
  - `WindowEvent.Resized` (drawableSize), `ScaleFactorChanged`, `RedrawRequested`
  - `ControlFlow` effective (Poll / Wait)
- **`kadre`** — KMP façade: `expect`/`actual` `EventLoop` + re-exports of `kadre-core`
- **`samples/hello-metal`** — macOS sample: NSWindow + CAMetalLayer @ 60 fps
- **CI** — `macos-build` job (Fast-Track JVM / Deep-Testing allTests)

#### M2 — GPU Rendering (GRA-137 → GRA-140)

- **`samples/hello-triangle`** — RGB rotating triangle via wgpu4k 0.1.1 (Metal backend, WGSL shaders)
- Resize swap-chain on `Resized`/`ScaleFactorChanged`
- AppKit bindings via kextract v0.0.0-test6

#### M3 — Multi-platform (GRA-141 → GRA-161)

- **`kadre-uikit`** — iOS backend (Kotlin/Native cinterop, UIKit):
  - `KadreAppDelegate`, `KadreViewController`, `UiKitWindow` (UIWindow + UIView + CAMetalLayer full-screen)
  - `WindowEvent.Touch` multi-touch (UIResponder)
  - Lifecycle: background/foreground events
- **`kadre-android`** — Android backend (SurfaceView, API 24+):
  - `KadreActivity` abstract Activity + `AndroidWindow` (SurfaceView full-screen)
  - `WindowEvent.Touch` multi-touch (MotionEvent)
  - `Choreographer` frame timing → `RedrawRequested`
  - Lifecycle dispatch: `canCreateSurfaces`, `destroySurfaces`, `suspended`, `resumed`
  - `AndroidKadreRuntime` global singleton bridging `EventLoop.runApp()` to `KadreActivity`
- **`kadre`** — `actual EventLoop` for iOS (→ kadre-uikit) and Android (→ kadre-android)
- **`samples/hello-touch`** — iOS sample (iosSimulatorArm64 / iosArm64)
- **`samples/hello-touch-android`** — Android APK sample (minSdk 24)
- **`samples/hello-window`** — Cross-platform sample (JVM + iOS + Android) with shared `HelloApp`
- **`samples/hello-window-android`** — Android APK for hello-window
- **AppKit input** (GRA-153–156):
  - Multi-window support: `windowWillClose` cleanup + `exit()` closes all
  - Keyboard input: `KeyboardInput` + `isRepeat`
  - Mouse input: clicks, motion, scroll, enter/exit → `WindowEvent` + `DeviceEvent`
  - `DeviceEvent` dispatch before `WindowEvent` (ordering guarantee)
- **KDoc + MkDocs** — API reference (Dokka GFM) integrated into `docs/kadre/api/`
- **Maven Central** — `kmp-publish` convention plugin with GPG in-memory signing; `docs/kadre/release-process.md`
- **CI multi-platform** — 4-job matrix:
  - `build-and-test` (macos-15): Fast-Track JVM / Deep-Testing JVM + iosSimulatorArm64
  - `macos-build` (macos-latest): AppKit + samples
  - `ios-build` (macos-15, master only): all iOS targets + simulator tests
  - `android-build` (ubuntu-latest, master only): Android modules + APK samples

### Breaking Changes

None — this is the first public release.

### Known Issues

- `kadre-appkit` requires JDK 25 (`--enable-native-access=ALL-UNNAMED`); JDK 17/21 are not supported.
- `samples/hello-triangle` requires wgpu4k 0.1.1 (Metal backend); GPU tests are not run in CI.
- Maven Central publication requires manual GPG key + Sonatype credentials setup (see `docs/kadre/release-process.md`).

### Artifacts

Group ID: `org.graphiks.kadre`

| Module            | Artifact ID        | Targets                          |
|-------------------|--------------------|----------------------------------|
| `kadre-core`     | `kadre-core`      | jvm, iosX64, iosArm64, iosSimulatorArm64, android |
| `kadre`          | `kadre`           | jvm, iosX64, iosArm64, iosSimulatorArm64, android |
| `kadre-appkit`   | `kadre-appkit`    | jvm (macOS only)                 |
| `kadre-uikit`    | `kadre-uikit`     | iosX64, iosArm64, iosSimulatorArm64 |
| `kadre-android`  | `kadre-android`   | android                          |

```kotlin
// build.gradle.kts
dependencies {
    implementation("org.graphiks.kadre:kadre:0.1.0")
}
```

[0.1.0]: https://github.com/ygdrasil-io/poc-koreos/releases/tag/v0.1.0
