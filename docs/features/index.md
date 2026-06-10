# Feature Comparison: winit (Rust) vs Kadre (Kotlin)

This directory documents the feature-by-feature comparison between [winit](https://github.com/rust-windowing/winit) (the upstream Rust windowing library, reference commit `c4afadbfabf7b1e7989b40b493db1a4c7bd8ff4e` / v0.30.13) and **Kadre** (its pure-Kotlin Multiplatform reimplementation, v1.0.0).

## Scope

Kadre aims for **1:1 feature parity** with winit's API. These documents track what is implemented, deferred, or unsupported, for both the common API surface and each platform backend.

## Feature Groups

| Group | File | Description |
|-------|------|-------------|
| **Architecture & Platform Support** | [architecture.md](architecture.md) | Module structure, backend mapping, language/binding differences, platform capability matrix |
| **Window API** | [window-api.md](window-api.md) | `Window` interface, `WindowAttributes`, `ActiveEventLoop`, `MonitorHandle`, `VideoMode`, `Fullscreen` |
| **Events** | [events.md](events.md) | `WindowEvent` and `DeviceEvent` variants, emission status per backend |
| **Keyboard & IME** | [keyboard.md](keyboard.md) | Key event model, type mappings (`KeyCode`, `NamedKey`, `PhysicalKey`, `LogicalKey`), modifiers, IME lifecycle |
| **Cursor** | [cursor.md](cursor.md) | `CursorIcon`, grab modes, visibility, position, hittest, custom cursors, platform matrix |
| **Theme & Appearance** | [theme-appearance.md](theme-appearance.md) | Theme, window level, transparency, blur, window icon, user attention, content protection |
| **Fullscreen & Monitors** | [fullscreen-monitor.md](fullscreen-monitor.md) | Fullscreen modes, monitor enumeration, video modes |
| **Kadre Extras** | [kadre-extras.md](kadre-extras.md) | Features unique to Kadre (no winit equivalent): `KeyChord`, screen capture, gamepad, coroutines, framing timing |
| **Gaps Summary** | [gaps.md](gaps.md) | Consolidated list of missing, deferred, or partially implemented features across all categories |

## Status Legend

| Badge | Meaning |
|-------|---------|
| ✅ Implemented | Feature is implemented in common API and at least one backend |
| ⚠️ Partial | Feature exists but has limitations or incomplete backends |
| 🔶 Deferred | API defined but backend wiring pending |
| ❌ Unsupported / No-op | Feature documented as not applicable or intentionally no-op |
