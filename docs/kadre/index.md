# Kadre

A cross-platform windowing POC in **pure Kotlin**, inspired by [winit](https://github.com/rust-windowing/winit).

Goal: expose native handles (`NSView`, `UIView`, `android.view.Surface`) that a 3D renderer (Metal, Vulkan, [wgpu4k](https://github.com/wgpu4k/wgpu4k)) can consume **without AWT/Swing dependencies**.

## Tutorials

- [Integrate Kadre in a Windows application](./tutorials/windows-app.md) — Win32 window, events, PerMonitorV2 DPI
- [Integrate Kadre in a Linux application](./tutorials/linux-app.md) — X11 and Wayland, backend auto-detection, DPI, headless CI
- [Integrate Kadre in a web page](./tutorials/web-embed.md) — HTML canvas, Kotlin/JS + Kotlin/Wasm, RAF loop

## Blog

- [Kadre v1.0.0 — 6 platforms, cross-platform Pong, live demo](./blog/v1.0.0-release.md) — the 1.0.0 release: macOS, iOS, Android, Win32, Web (JS+Wasm), Linux (X11+Wayland), Pong

## Documents

- [Project plan](./plan.md) — vision, scope, risks
- [Sprint Review](./sprint-review.md) — metrics, deliverables, retrospective
- [Technical specifications](./specs.md) — architecture, API, diagrams

## Features

- **Window state & geometry** — size constraints, min/max, resizable, minimized, maximized, decorations, outer position, pre-present notify
- **Monitor enumeration** — `availableMonitors()` / `primaryMonitor()` with `VideoMode` data
- **Fullscreen** — `Fullscreen.Borderless` (all backends) and `Fullscreen.Exclusive` (desktop; falls back to borderless on Wayland/Web/mobile)
- **Cursor** — 25 `CursorIcon` shapes, visibility, grab (Confined/Locked), warp, hit-testing; custom RGBA cursors (`CursorImage` / `CustomCursor`, wiring TODO)
- **Theme & appearance** — `Theme` (Light/Dark), per-window theme override, `ThemeChanged` event, `WindowLevel`, transparency, blur, window icon
- **Keyboard richness** — `text`, `location` (`KeyLocation`), `scanCode`, `isRepeat` on `KeyboardInput`; `ModifiersChanged`; dead-key reset
- **Device events** — `DeviceEvent.MouseWheel`; filter via `listenDeviceEvents(DeviceEvents.Always/WhenFocused/Never)`
- **IME** — `setImeAllowed`, `setImeCursorArea`, `setImePurpose(ImePurpose)`; full `ImeEvent` lifecycle (Enabled/Preedit/Commit/DeleteSurrounding/Disabled) — API defined, emission TODO
- **Drag & drop** — `DragEntered/Moved/Dropped/Left` events — API defined, emission TODO
- **Gestures** — Pinch, Pan, Rotation, DoubleTap, TouchpadPressure — API defined, emission TODO
- **Occluded** — visibility state change event — API defined, emission TODO
- **Misc window** — user attention, content protection, window menu, drag/resize-window — API defined, all no-op (see [DEFERRED.md](https://github.com/ygdrasil-io/poc-koreos/blob/master/DEFERRED.md))

## Platform capability matrix

| Feature | macOS (appkit) | Windows (win32) | Linux X11 | Linux Wayland | Web (JS/Wasm) | Android | iOS (uikit) |
|---------|:-:|:-:|:-:|:-:|:-:|:-:|:-:|
| Window create / title / size | real | real | real | real | real | real | real |
| Monitor enumeration | real | real | real | real | synthetic | synthetic | synthetic |
| Fullscreen Borderless | real | real | real | real | real | real | real |
| Fullscreen Exclusive | real | partial | real | no-op | no-op | no-op | no-op |
| CursorIcon | real | real | real | no-op* | real (CSS) | no-op | no-op |
| CursorGrab Confined/Locked | real | real | real | no-op* | real | no-op | no-op |
| CursorVisible | real | partial* | no-op* | no-op | real | no-op | no-op |
| CursorPosition (warp) | partial* | real | real | no-op | no-op | no-op | no-op |
| systemTheme() | real | real | null | null | real | real | real |
| setTheme() per-window | real | real | no-op | no-op | no-op | no-op | no-op |
| ThemeChanged event | real | real | — | — | — | — | — |
| setBlur() | real | real | no-op | no-op | no-op | no-op | no-op |
| setWindowIcon() | partial* | partial* | real | no-op | no-op | no-op | no-op |
| ModifiersChanged event | real | real | TODO | TODO | real | TODO | TODO |
| IME (setImeAllowed etc.) | TODO | TODO | TODO | TODO | TODO | TODO | TODO |
| DnD events | TODO | TODO | TODO | TODO | TODO | TODO | TODO |
| Gesture events | TODO | TODO | — | — | TODO | — | TODO |
| Occluded event | TODO | — | — | — | TODO | — | — |
| Custom cursors | TODO | TODO | TODO | TODO | TODO | no-op | no-op |

`real` = implemented. `partial*` / `no-op*` = partial or documented no-op, see [DEFERRED.md](https://github.com/ygdrasil-io/poc-koreos/blob/master/DEFERRED.md). `TODO` = API defined, backend wiring pending. `—` = not applicable on this platform.

## Platforms

| Platform | Backend |
|----------|---------|
| **macOS** | NSWindow with layer-backed contentView ready for Metal |
| **iOS** | UIKit (UIWindow + UIView + CAMetalLayer) |
| **Android** | SurfaceView + Choreographer |
| **Windows** | Win32 (RegisterClassExW + CreateWindowExW) |
| **Linux** | X11 + Wayland, auto-detected |
| **Web** | HTML canvas (Kotlin/JS + Kotlin/Wasm) |

## Status

**Released** — `org.graphiks.kadre:kadre:1.0.0` on Maven Central.

Residual deferred items (API defined, backend wiring pending): IME emission, DnD emission, gesture emission, Occluded emission, custom cursors. Full list: [DEFERRED.md](https://github.com/ygdrasil-io/poc-koreos/blob/master/DEFERRED.md).
