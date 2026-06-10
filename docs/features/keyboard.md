# Keyboard & IME

## Type Mapping

### Key Event Types

| winit type (Rust) | Kadre type (Kotlin) | Status | Notes |
|-------------------|---------------------|--------|-------|
| `Key` | `LogicalKey` (sealed interface) | ✅ | |
| `Key::Character(String)` | `LogicalKey.Character(text: String)` | ✅ | |
| `Key::Named(NamedKey)` | `LogicalKey.Named(key: NamedKey)` | ✅ | |
| `Key::Dead(Option<char>)` | `LogicalKey.Dead(accent: String?)` | ✅ | |
| `Key::Unidentified(NativeKey)` | `LogicalKey.Unidentified(native: NativeKeyInfo)` | ✅ | |
| `PhysicalKey` | `PhysicalKey` (sealed interface) | ✅ | |
| `PhysicalKey::Code(KeyCode)` | `PhysicalKey.Code(code: KeyCode)` | ✅ | |
| `PhysicalKey::Unidentified(NativeKeyCode)` | `PhysicalKey.Native(platform, code)` + `PhysicalKey.Unidentified` | ⚠️ Kadre adds `Native` variant | |
| `KeyCode` (enum) | `KeyCode` (enum) | ⚠️ Partial | Missing IME/Asian keys, extended numpad, media long tail |
| `NamedKey` (enum) | `NamedKey` (enum) | ⚠️ Partial | Missing TV/media advanced, UI Events long tail |
| `KeyLocation` | `KeyLocation` (enum) | ✅ | Standard/Left/Right/Numpad |
| `NativeKeyCode` | `NativeKeyCode` (sealed interface) | ✅ | Per-platform native codes |
| `NativeKey` | `NativeLogicalKey` (sealed interface) | ✅ | Per-platform native logical keys |
| `KeyEvent` | `KeyEvent` (data class) | ✅ | Same fields + `native: NativeKeyInfo` |
| `RawKeyEvent` | `RawKeyEvent` (data class) | ✅ | Kadre adds `native` field |
| `Key::to_text()` | `KeyCode.defaultText()` / `effectiveText` | ⚠️ Partial | |

### Modifier Types

| winit | Kadre | Status | Notes |
|-------|-------|--------|-------|
| `ModifiersState` (Shift/Ctrl/Alt/Meta) | `KeyboardModifiers` (value class) | ✅ | Kadre adds AltGraph, CapsLock, NumLock, Symbol |
| `ModifiersKeys` | `ModifierKeys` (data class, 8 fields) | ✅ | Left/right tracking — runtime backend incomplete |
| `Modifiers` | `KeyboardModifierState(logical, physical)` | ✅ | |

### Kadre-Only Keyboard Features

| Feature | Description |
|---------|-------------|
| **`KeyChord`** | Typed keyboard shortcut: `physicalKey` + `logicalKey` + `modifiers` + `KeyChordModifierMatch` (Contains/Exact). No winit equivalent. |
| **`KeyChordModifierMatch`** | `Contains` = modifiers must be present, extras allowed. `Exact` = exact match only. |
| **`KeyboardModifiers` extras** | `AltGraph`, `CapsLock`, `NumLock`, `Symbol` flags beyond winit's Shift/Ctrl/Alt/Meta |
| **`KeyboardModifierState`** | Separates `logical` (bitflag) from `physical` (left/right per-key) modifier state |
| **`ModifierKeys`** | 8 explicit fields (`leftShift`, `rightShift`, `leftCtrl`, etc.) with `Pressed/Released/Unknown` states |

## Keyboard Backend Quality

| Capability | Web | AppKit | Win32 | Android | X11 | Wayland | UIKit |
|-----------|:---:|:------:|:-----:|:-------:|:---:|:-------:|:-----:|
| Physical key mapping | ✅ DOM `code` | ⚠️ QWERTY | ✅ VK map | ✅ keycode map | ⚠️ hardcoded table | ✅ evdev table | ✅ HID usage |
| `text` | ✅ | ✅ | ✅ | ✅ | ❌ null | ❌ null | ✅ |
| `textWithAllModifiers` | ⚠️ fallback | ⚠️ partial | ⚠️ partial | ⚠️ partial | ❌ | ❌ | ⚠️ partial |
| `keyWithoutModifiers` | ⚠️ fallback | ⚠️ partial | ⚠️ partial | ⚠️ partial | ❌ | ❌ | ⚠️ partial |
| `ModifiersChanged` | ✅ | ✅ | ✅ | ✅ | ✅ | ✅ | ✅ |
| `ModifierKeys` left/right | ❌ | ⚠️ limited | ⚠️ generic VK | ❌ | ✅ tracked | ✅ tracked | ❌ |
| `repeat` detection | ✅ DOM | ✅ | ✅ lParam | ✅ | ✅ XKB | ✅ | ✅ |
| Dead keys | ⚠️ browser | ✅ | ✅ | — | ✅ compose | ✅ xkb | — |

## IME

### IME Event Lifecycle

| winit | Kadre | Status |
|-------|-------|--------|
| `WindowEvent::Ime(Enabled)` | `WindowEvent.Ime(ImeEvent.Enabled)` | ✅ API + runtime (all backends) |
| `WindowEvent::Ime(Preedit)` | `WindowEvent.Ime(ImeEvent.Preedit(text, cursorRange?))` | ✅ API + runtime (all backends) |
| `WindowEvent::Ime(Commit)` | `WindowEvent.Ime(ImeEvent.Commit(text))` | ✅ API + runtime (all backends) |
| `WindowEvent::Ime(DeleteSurrounding)` | `WindowEvent.Ime(ImeEvent.DeleteSurrounding(beforeBytes, afterBytes))` | ✅ API + runtime (all backends) |
| `WindowEvent::Ime(Disabled)` | `WindowEvent.Ime(ImeEvent.Disabled)` | ✅ API + runtime (all backends) |

### IME Control Methods

| winit | Kadre | Status |
|-------|-------|--------|
| `Window::set_ime_allowed(bool)` | `Window.setImeAllowed(Boolean)` | ✅ |
| `Window::set_ime_cursor_area(position, size)` | `Window.setImeCursorArea(position, size)` | ✅ |
| `Window::set_ime_purpose(ImePurpose)` | `Window.setImePurpose(ImePurpose)` | ✅ Normal/Password/Terminal |
| `Window::ime_capabilities()` | absent | 🔶 Deferred |

### IME Backend Implementation

| Backend | Mechanism | Status |
|---------|-----------|--------|
| AppKit | `NSTextInputClient` protocol | ✅ REAL |
| Win32 | `WM_IME_STARTCOMPOSITION` / `WM_IME_COMPOSITION` / `WM_IME_ENDCOMPOSITION` | ✅ REAL |
| X11 | XIM (`XFilterEvent` + callbacks) | ✅ REAL |
| Wayland | `zwp_text_input_v3` protocol | ✅ REAL |
| Web | Hidden `<input>` element | ✅ REAL |
| Android | `KadreInputConnection` + `KadreImeSurfaceView` | ✅ REAL |
| UIKit | `UIKeyInput` / `UITextInput` protocol | ✅ REAL |
