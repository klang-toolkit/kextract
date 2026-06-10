# Gaps Summary

Consolidated list of features that are missing, deferred, or only partially implemented in Kadre relative to winit.

## Coverage Overview

| Category | Total APIs | Implemented | Deferred | Unsupported/No-op | Partial |
|----------|:----------:|:-----------:|:--------:|:-----------------:|:-------:|
| Window API | 24 targeted | 15 (62%) | 6 (25%) | 1 (4%) | 2 (8%) |
| Keyboard | 20 capabilities | 16 (80%) | — | — | 4 (20%) |
| Events | 22 variants | 18 (82%) | — | — | 4 (18%) |

## Category Gaps

### 1. Window API Gaps

| Gap | Type | Details |
|-----|------|---------|
| `Window.ime_capabilities()` | 🔶 Deferred | No rich IME capability reporting model |
| `Window.request_ime_update()` | 🔶 Deferred | Uses individual setters instead |
| `ActiveEventLoop.owned_display_handle()` non-null | 🔶 Deferred | Kadre returns `null` by default |
| `dragWindow()` | 🔶 Deferred | Returns `WindowRequestResult`; fire-and-forget native start |
| `dragResizeWindow()` | 🔶 Deferred | Same pattern; AppKit unsupported like winit |
| `showWindowMenu()` | 🔶 Deferred | Win32/Wayland wired; AppKit/X11 success no-op |
| Appearance setters | 🔶 Deferred | Backends incomplete for blur, icon, theme, attention, level |
| `Fullscreen.Exclusive` | ❌ UnsupportedPlatform | No-op on Wayland, Web, Android, UIKit |

### 2. Keyboard Enum Coverage Gaps

| Gap | Impact |
|-----|--------|
| **`KeyCode`:** IME/Asian language keys | `Lang1..Lang5`, `KanaMode`, `Convert`, `NonConvert`, `Hiragana`, `Katakana`, `Eisu` |
| **`KeyCode`:** Extended numpad | `NumpadParenLeft/Right`, `NumpadMemory*`, `NumpadSignChange`, `NumpadHash` |
| **`KeyCode`:** Media/app/system | Various Android/XKB/Web-specific codes may fall through to `Native` |
| **`NamedKey`:** TV/media advanced | Long-tail UI Events keys |
| **`NamedKey`:** IME and composition | `GroupNext`, `KanjiMode`, `AllCandidates`, `NextCandidate` |
| **`NamedKey`:** Android/XKB specifics | Values that winit exposes as `NamedKey` when possible |

### 3. Keyboard Runtime Backend Gaps

| Backend | Gaps |
|---------|------|
| **X11** | `text` = null (no `XLookupString`); `textWithAllModifiers` = null; `keyWithoutModifiers` = null |
| **Wayland** | `text` = null (no `xkb_state_key_get_utf8`); same derived fields null |
| **AppKit** | Mapping is QWERTY US; other layouts may produce incorrect `PhysicalKey` |
| **Win32** | Prefers VK over scancode (less accurate for non-US layouts); left/right generic VK ambiguous |
| **All backends** | `ModifierKeys` left/right tracking incomplete; `textWithAllModifiers` and `keyWithoutModifiers` mostly fallback logic |

### 4. Event Emission Gaps

| Event | Status | Backends |
|-------|--------|----------|
| `ThemeChanged` | ⚠️ Partial | Only AppKit, Win32 |
| `Occluded` | ⚠️ Partial | AppKit, X11, Web, iOS |
| `Ime` events | ⚠️ Partial (historically deferred) | All backends now emit in latest code |
| `DragEntered/Moved/Dropped/Left` | ⚠️ Partial | Win32, X11, Wayland, Web, iOS; AppKit partial |
| Gestures (Pinch/Pan/Rotation/DoubleTap) | ⚠️ Partial | AppKit/Win32/UIKit only |
| `TouchpadPressure` | ⚠️ Partial | AppKit only (macOS Force Touch) |

### 5. Platform-Specific Gaps

#### Wayland

| Gap | Blocked by |
|-----|-----------|
| `requestUserAttention` | `xdg_activation_v1` protocol not wired |
| `setWindowIcon` | `xdg_toplevel_icon_manager_v1` protocol not wired |
| `setBlur` | `ext_background_effect` / KWin blur protocols not wired |
| `systemTheme()` portal | D-Bus integration works but detection is incomplete |
| Monitor geometry | `wl_output` geometry/mode not stored (synthetic only) |
| `Fullscreen.Exclusive` | Not applicable on Wayland |
| Keyboard `text` | `xkb_state_key_get_utf8` not called |

#### Web (JS/Wasm)

| Gap | Blocked by |
|-----|-----------|
| **Bridges are stubs** | `JsWebDomBridge` and `WasmJsWebDomBridge` implementations not in workspace |
| `setCursorGrab(Locked)` | Pointer Lock API bridge not wired |
| `setCursorHittest` | CSS `pointer-events` not wired |

#### AppKit (macOS)

| Gap | Details |
|-----|---------|
| `outerPosition` | Cocoa bottom-left coordinates not converted |
| `CGWarpMouseCursorPosition` scalar cast | Works on x64/arm64 but not FFM spec-conformant |
| `setWindowIcon` | No-op (winit parity — macOS has no per-window icon) |
| `dragResizeWindow` | Unsupported (winit parity) |

#### Win32 (Windows)

| Gap | Details |
|-----|---------|
| `Fullscreen.Exclusive` | `ChangeDisplaySettingsExW` TODO |
| `ShowCursor` not rebalanced | Counter mismatch (DEFERRED.md) |
| `readWString` | Stops at space instead of `\0` (minor) |
| `setBlur` | No-op runtime (DWM APIs deprecated) |

#### X11 (Linux)

| Gap | Details |
|-----|---------|
| Keyboard `text` = null | No `XLookupString` binding |
| `setTransparent` | `_NET_WM_WINDOW_OPACITY` TODO |
| `ScaleFactorChanged` dynamic | No RRNotify handling (static DPI only) |
| `systemTheme()` | Always null (no standard X11 mechanism) |
| `setBlur` | No-op (compositor-specific) |

#### Mobile (Android / UIKit)

| Gap | Details |
|-----|---------|
| Most window state setters | No-op (expected — mobile OS controls window chrome) |
| `setCursor*` all methods | No-op (expected — no cursor on touch) |
| `setTheme()` overwrite | Android no-op (must use `AppCompatDelegate`) |
| Various R1 features | No-op (resizable, decorations, position, etc.) |

### 6. Coverage by Backend

| Backend | Estimated coverage | Notes |
|---------|:-----------------:|-------|
| **AppKit (macOS)** | ~95% | Most mature; custom cursors, gestures, IME, blur |
| **Win32 (Windows)** | ~90% | Richest extension API (DWM, corners, borders) |
| **X11 (Linux)** | ~85% | Xdnd DnD, XIM IME; keyboard `text` is main gap |
| **Wayland (Linux)** | ~80% | Best protocol negotiation; keyboard `text` is main gap |
| **UIKit (iOS)** | ~55% | Good for mobile; gesture opt-in, IME working |
| **Android** | ~50% | Functional; Choreographer-based event loop |
| **Web (JS/Wasm)** | ~60% | DOM bridges are stubs — real implementation TBD |

## Reference

- winit commit: `c4afadbfabf7b1e7989b40b493db1a4c7bd8ff4e`
- winit version: v0.30.13
- Kadre version: v1.0.0
- Last updated: 2026-06-10
