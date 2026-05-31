# Koreos

A cross-platform windowing POC in **pure Kotlin**, inspired by [winit](https://github.com/rust-windowing/winit).

Goal: expose native handles (`NSView`, `UIView`, `android.view.Surface`) that a 3D renderer (Metal, Vulkan, [wgpu4k](https://github.com/wgpu4k/wgpu4k)) can consume **without AWT/Swing dependencies**.

## Tutorials

- [Integrate Koreos in a Windows application](./tutorials/windows-app.md) — Win32 window, events, PerMonitorV2 DPI
- [Integrate Koreos in a Linux application](./tutorials/linux-app.md) — X11 and Wayland, backend auto-detection, DPI, headless CI
- [Integrate Koreos in a web page](./tutorials/web-embed.md) — HTML canvas, Kotlin/JS + Kotlin/Wasm, RAF loop

## Blog

- [Koreos v0.2.0 — 6 platforms, cross-platform Pong, live demo](./blog/v0.2.0-release.md) — recap of the 5 sprints: Win32, Web (JS+Wasm), Linux (X11+Wayland), Pong

## Documents

- [Project plan](./plan.md) — vision, milestones, risks, timeline
- [Sprint Review v0.1](./sprint-review-v0.1.md) — metrics, deliverables, gaps, v0.1.0 retro
- [Technical specifications](./specs.md) — architecture, API, diagrams
- [Roadmap progress](./roadmap-progress.md) — auto-generated progress (Redmine + git, Redmine #87)

## Milestones

| Milestone | Deliverable |
|-----------|------------|
| **M1 — POC** | macOS NSWindow with layer-backed contentView ready for Metal |
| **M2 — wgpu4k demo** | Simple 3D scene rendered via wgpu4k consuming the raw handle |
| **M3 — Target lib** | Publishable KMP lib, complete AppKit + UIKit + Android backends |

## Status

**Draft for review** — see the spec validation PR.
