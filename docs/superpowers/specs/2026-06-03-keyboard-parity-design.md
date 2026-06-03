# Keyboard Parity — Design

> Status: **Approved**
> Winit keyboard API parity for kadre — 5 incremental cycles on master.

## Motivation

Kadre's keyboard support covers ~66 `Key` entries and basic `KeyState`/`Modifiers` but is missing the full winit keyboard type hierarchy: `PhysicalKey`, `KeyCode`, `NamedKey`, `KeyLocation`, `NativeKey`, `ModifiersKeys`, IME, and the `text` field. Each backend hand-rolls its own key mapping table with no shared standard.

This spec defines 5 independent cycles, each self-contained, ordered by dependency.

## Approach

**Incremental on master** (option B). Each cycle is a complete design → plan → execute → gap-update loop. Cycles can be parallelised where they have no dependency.

## Cycle 1: Widen `Key` enum

| Type | winit | target |
|------|-------|--------|
| `NamedKey` variants | ~307 | ~307 |
| `Key.Character(String)` | ✅ | ✅ |
| `Key.Dead(Option<char>)` | ✅ | ✅ |
| `Key.Unidentified(NativeKey)` | deferred | Cycle 4 |

### Changes

**kadre-core:**
- Replace flat `Key` enum with a sealed class/interface matching winit's `Key`:
  ```kotlin
  sealed class Key {
      data class Named(val namedKey: NamedKey) : Key()
      data class Character(val value: String) : Key()
      data class Dead(val combining: Char?) : Key()
      data object Unidentified : Key()
  }
  ```
- Generate `NamedKey` enum from `keyboard-types` v0.8 `NamedKey` variants (307 entries). Script or manual transcription from the Rust source.
- `KeyLocation` enum: `Standard`, `Left`, `Right`, `Numpad`

**Per-backend:** Update each key mapper to return `Key.Named(NamedKey.X)` instead of `Key.X`. No functional change — just wrapping.

### Files touched
- `kadre-core/.../core/Events.kt` — replace `Key` enum
- `kadre-core/.../core/Key.kt` — new file (or inline) for `NamedKey` + `KeyLocation`
- Each backend's `*KeyMapper.kt` — wrap return values

## Cycle 2: PhysicalKey / KeyCode / NativeKey

| Type | winit | target |
|------|-------|--------|
| `KeyCode` (~260 entries) | ✅ | ✅ |
| `PhysicalKey.Code(KeyCode)` | ✅ | ✅ |
| `PhysicalKey.Unidentified(NativeKeyCode)` | ✅ | ✅ |
| `NativeKeyCode` (per-platform) | ✅ | ✅ |
| `NativeKey` | ✅ | ✅ |

### Changes

**kadre-core:**
- `KeyCode` enum: generated from `keyboard-types` v0.8 `Code` enum (~260 entries)
- `PhysicalKey` sealed class:
  ```kotlin
  sealed class PhysicalKey {
      data class Code(val keyCode: KeyCode) : PhysicalKey()
      data class Unidentified(val native: NativeKeyCode) : PhysicalKey()
  }
  ```
- `NativeKeyCode` sealed class with per-platform variants:
  ```kotlin
  sealed class NativeKeyCode {
      data class Android(val code: Int) : NativeKeyCode()
      data class MacOS(val code: Int) : NativeKeyCode()
      data class Windows(val code: Int) : NativeKeyCode()
      data class Xkb(val code: Int) : NativeKeyCode()
      data class Web(val code: String) : NativeKeyCode()
      data class Ohos(val code: Int) : NativeKeyCode()
  }
  ```
- `NativeKey` sealed class (same pattern but for logical keys)
- Add `physicalKey` and `nativeKeyCode` fields to event types

**Per-backend:** Map native scancodes → `KeyCode` entries. This is the heavy part — each backend needs a comprehensive mapping table (200+ entries).

## Cycle 3: Text field in KeyboardInput

### Changes

**kadre-core:**
- Add `text: String?` field to `WindowEvent.KeyboardInput`
- Add `text_with_all_modifiers: String?` and `key_without_modifiers: Key?` fields (deferred to optional follow-up)

**Per-backend:**

| Backend | Source for text |
|---------|-----------------|
| appkit | `event.characters` / `event.charactersIgnoringModifiers` |
| win32 | `WM_CHAR` / `ToUnicode` |
| x11 | `XLookupString` |
| wayland | `wl_keyboard.enter` compositor string (or xkbcommon) |
| uikit | `UIKey.keyCommand.input` |
| android | `KeyEvent.getDisplayLabel()` / `KeyEvent.getUnicodeChar()` |
| web | `event.key` (already available as string) |

## Cycle 4: ModifiersChanged + ModifiersKeys

### Changes

**kadre-core:**
- `ModifiersKeys` bitflags: `LSHIFT, RSHIFT, LCTRL, RCTRL, LALT, RALT, LMETA, RMETA`
- `ModifiersChanged(modifiers: ModifiersKeys)` as a `WindowEvent` variant
- Separate `ModifiersState` (simplified) from `ModifiersKeys` (left/right)
- `Modifiers` combines both (winit pattern)

**Per-backend:** Emit `ModifiersChanged` on modifier key press/release.

## Cycle 5: IME Support

### Changes

**kadre-core:**
- `Ime` sealed class:
  ```kotlin
  sealed class Ime {
      data object Enabled : Ime()
      data class Preedit(val text: String, val cursor: Pair<Int, Int>?) : Ime()
      data class Commit(val text: String) : Ime()
      data object Disabled : Ime()
  }
  ```
- `ImePurpose` enum: `Password, Text, Terminal, Custom(...)`
- `WindowEvent.Ime(Ime)` variant
- `Window.setImeAllowed(Boolean)`, `Window.setImeCursorArea(IntRect)`, `Window.setImePurpose(ImePurpose)`

**Per-backend:** Platform IME integration (NSTextInputClient on macOS, Imm32/TSF on Windows, InputMethod on Android, etc.)

## Success criteria

- All 5 cycles complete on master
- Gap note `winit-gaps/keyboard-ime.md` updated from `partial` to `present` for each field
- Each PR passes CI
- Existing keyboard behavior unchanged (regression tests pass)

## Non-goals

- keyboard-types code generation from Rust (manual Kotlin port is fine)
- Full test coverage of every key variant (smoke test per category is sufficient)
- WASM-specific keyboard path outside existing web-common delegation
