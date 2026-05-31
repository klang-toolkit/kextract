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
