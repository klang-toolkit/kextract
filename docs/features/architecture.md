# Architecture & Platform Support

## High-Level Comparison

| Dimension | winit (Rust) | Kadre (Kotlin) |
|-----------|-------------|-----------------|
| **Language** | Rust | Kotlin Multiplatform |
| **Native bindings** | `raw-window-handle` crate | Panama FFM (JDK 25+) — zero JNA/AWT dependency |
| **Event model** | `EventLoop::run()` + `Event` enum match | `EventLoop.runApp(ApplicationHandler)` — callback-driven |
| **Modules** | `winit-core` + per-platform backend crates | `kadre-core` + per-platform backend KMP modules |
| **Package/org** | `winit` (crates.io) | `org.graphiks.kadre` (Maven Central) |
| **Reference commit** | `c4afadbfabf7b1e7989b40b493db1a4c7bd8ff4e` | — |
| **Reference version** | v0.30.13 | v1.0.0 |

## Crate/Module Mapping

| winit crate | Kadre module | Platform |
|-------------|-------------|----------|
| `winit-core` | `kadre-core` | Common API (commonMain) |
| `winit-appkit` | `kadre-appkit` | macOS (JVM + FFM) |
| `winit-win32` | `kadre-win32` | Windows (JVM + FFM) |
| `winit-x11` | `kadre-x11` | Linux X11 (JVM + FFM) |
| `winit-wayland` | `kadre-wayland` | Linux Wayland (JVM + FFM) |
| `winit-web` | `kadre-js` + `kadre-wasm` + `kadre-web-common` | Web (JS + Wasm) |
| (Android) | `kadre-android` | Android |
| (UIKit/iOS) | `kadre-uikit` | iOS (Kotlin/Native) |

## Platform Capability Matrix

`REAL` = implemented. `partial` = partially implemented. `NO-OP` = call accepted but no effect. `—` = not applicable. `(opt)` = requires optional protocol.

| Feature | AppKit | Win32 | X11 | Wayland | Web | Android | UIKit |
|---------|:------:|:-----:|:---:|:-------:|:---:|:-------:|:-----:|
| Window create / title / size | **REAL** | **REAL** | **REAL** | **REAL** | **REAL** | **REAL** | **REAL** |
| Monitor enumeration | **REAL** | **REAL** | **REAL** | synthetic | synthetic | synthetic | synthetic |
| Fullscreen Borderless | **REAL** | **REAL** | **REAL** | **REAL** | **REAL** | **REAL** | **REAL** |
| Fullscreen Exclusive | **REAL** | **partial** | **REAL** | **NO-OP** | **NO-OP** | **NO-OP** | **NO-OP** |
| CursorIcon (25 shapes) | **REAL** | **REAL** | **REAL** | **REAL** | **REAL** | **NO-OP** | **NO-OP** |
| CursorGrab Confined | unsupported | **REAL** | **REAL** | **REAL** | unsupported | **NO-OP** | **NO-OP** |
| CursorGrab Locked | **REAL** | **REAL** | **REAL** | **REAL** | unsupported | **NO-OP** | **NO-OP** |
| CursorVisible | **REAL** | **partial** | **REAL** | **REAL** | **REAL** | **NO-OP** | **NO-OP** |
| CursorPosition (warp) | **partial** | **REAL** | **REAL** | unsupported | unsupported | unsupported | unsupported |
| CustomCursor (RGBA) | **REAL** | **REAL** | **REAL** | **REAL** | **REAL** | **NO-OP** | **NO-OP** |
| CursorHittest | **REAL** | **REAL** | **REAL** | **REAL** | unsupported | unsupported | unsupported |
| systemTheme() | **REAL** | **REAL** | null | **REAL** | **REAL** | **REAL** | null |
| setTheme() per-window | **REAL** | **REAL** | **REAL** | **NO-OP** | **NO-OP** | **NO-OP** | **REAL** |
| ThemeChanged event | **REAL** | **REAL** | — | **REAL** | — | — | — |
| setWindowLevel | **REAL** | **REAL** | **REAL** | **NO-OP** | **NO-OP** | **NO-OP** | **NO-OP** |
| setTransparent | **REAL** | **REAL** | **NO-OP** | **REAL** | **NO-OP** | **NO-OP** | **NO-OP** |
| setBlur | **REAL** | **NO-OP** | **NO-OP** | **REAL** (opt) | **NO-OP** | **NO-OP** | **NO-OP** |
| setWindowIcon | **NO-OP** | **REAL** | **REAL** | **REAL** (opt) | **NO-OP** | **NO-OP** | **NO-OP** |
| ModifiersChanged | **REAL** | **REAL** | **REAL** | **REAL** | **REAL** | **REAL** | **REAL** |
| Keyboard text | **REAL** | **REAL** | null | null | **REAL** | **REAL** | **REAL** |
| IME events | **REAL** | **REAL** | **REAL** | **REAL** | **REAL** | **REAL** | **REAL** |
| DnD events | **REAL** | **REAL** | **REAL** | **REAL** | **REAL** | — | **REAL** |
| Gesture events | **REAL** | **REAL** | Pinch only | — | Gesture evts | — | **REAL** (opt-in) |
| Occluded event | **REAL** | — | **REAL** | — | **REAL** | — | **REAL** |
| listenDeviceEvents | **NO-OP** | **NO-OP** | **NO-OP** | **REAL** | **NO-OP** | **NO-OP** | **NO-OP** |
| dragWindow | **REAL** | **REAL** | **REAL** | **REAL** | — | — | — |
| dragResizeWindow | unsupported | **REAL** | **REAL** | **REAL** | — | — | — |
| showWindowMenu | success no-op | **REAL** | success no-op | **REAL** | — | — | — |
| requestUserAttention | **REAL** | **REAL** | **REAL** | **NO-OP** | — | — | — |
| setContentProtected | **REAL** | **REAL** | success no-op | success no-op | — | — | — |

## Backend Maturity Ranking

1. **AppKit (macOS)** — ~95% features; custom cursors, gestures, IME, blur all wired
2. **Win32 (Windows)** — ~90% features; richest extension API (DWM backdrop, corners, borders)
3. **X11 (Linux)** — ~85% features; Xdnd DnD, XIM IME; keyboard `text` is null
4. **Wayland (Linux)** — ~80% features; best protocol negotiation; keyboard `text` is null
5. **UIKit (iOS)** — ~55% features; good for mobile, gesture opt-in, IME
6. **Android** — ~50% features; functional, Choreographer-based
7. **Web (JS/Wasm)** — ~60% features; DOM bridges are stubs in this workspace
