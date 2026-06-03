# Keyboard Parity Implementation Plan

> **For agentic workers:** REQUIRED SUB-SKILL: Use superpowers:subagent-driven-development (recommended) or superpowers:executing-plans to implement this plan task-by-task. Steps use checkbox (`- [ ]`) syntax for tracking.

**Goal:** Achieve winit keyboard API parity in kadre across 5 incremental cycles on master.

**Architecture:** Introduce `NamedKey`/`KeyCode`/`KeyLocation` enums matching `keyboard-types` v0.8, wrap them in sealed class hierarchies matching winit's `Key`/`PhysicalKey` types, then progressively add `text` fields, `ModifiersChanged` event, and IME support across all backends.

**Tech Stack:** Kotlin Multiplatform, kadre-core (commonMain), per-backend modules (appkit/win32/x11/wayland/uikit/android/web-common)

**Spec:** `docs/superpowers/specs/2026-06-03-keyboard-parity-design.md`

---
## File Structure

### New files
- `kadre-core/src/commonMain/kotlin/org/graphiks/kadre/core/KeyTypes.kt` — `NamedKey`, `KeyCode`, `KeyLocation`, `PhysicalKey`, `NativeKeyCode`, `NativeKey`
- `kadre-core/src/commonMain/kotlin/org/graphiks/kadre/core/Modifiers.kt` — `ModifiersState`, `ModifiersKeys`, `ModifiersKeyState`, `Modifiers`
- `kadre-core/src/commonMain/kotlin/org/graphiks/kadre/core/Ime.kt` — `Ime`, `ImePurpose`

### Modified files
- `kadre-core/.../core/Events.kt` — `Key` enum → sealed class, `KeyboardInput` gains `physicalKey`/`text`, added `ModifiersChanged`/`Ime` variants
- `kadre-core/.../core/Window.kt` — adds `setImeAllowed`/`setImeCursorArea`/`setImePurpose`

### Backend changes (all 7 backends)
- `kadre-appkit/.../input/AppKitKeyMapper.kt`
- `kadre-win32/.../input/Win32KeyMapper.kt`
- `kadre-x11/.../input/X11KeyMapper.kt`
- `kadre-wayland/.../input/WaylandKeyMapper.kt`
- `kadre-uikit/.../input/UiKitKeyMapper.kt`
- `kadre-android/.../input/AndroidKeyMapper.kt`
- `kadre-web-common/.../input/DomEventMapper.kt`

### Test files
- `kadre-core/src/commonTest/.../KeyTypesTest.kt`

---

### Task 1: Cycle 1 — Widen `Key` enum + add `NamedKey`/`KeyLocation`

**Files:**
- Create: `kadre-core/src/commonMain/kotlin/org/graphiks/kadre/core/KeyTypes.kt`
- Modify: `kadre-core/.../core/Events.kt` — replace `Key` enum with sealed class
- Modify: All 7 backend `*KeyMapper.kt` — wrap return values

`KeyTypes.kt` will define:

```kotlin
package org.graphiks.kadre.core

import kotlin.jvm.JvmInline

// Location of a physical key — from keyboard-types v0.8 Location
enum class KeyLocation(val value: Int) {
    Standard(0x00),
    Left(0x01),
    Right(0x02),
    Numpad(0x03);
}

// Logical key name — from keyboard-types v0.8 NamedKey
// Complete source: https://raw.githubusercontent.com/rust-windowing/keyboard-types/v0.8.0/src/named_key.rs
enum class NamedKey {
    Unidentified,
    Alt,
    AltGraph,
    CapsLock,
    Control,
    Fn,
    FnLock,
    Meta,
    NumLock,
    ScrollLock,
    Shift,
    Symbol,
    SymbolLock,
    Enter,
    Tab,
    ArrowDown,
    ArrowLeft,
    ArrowRight,
    ArrowUp,
    End,
    Home,
    PageDown,
    PageUp,
    Backspace,
    Clear,
    Copy,
    CrSel,
    Cut,
    Delete,
    EraseEof,
    ExSel,
    Insert,
    Paste,
    Redo,
    Undo,
    Accept,
    Again,
    Attn,
    Cancel,
    ContextMenu,
    Escape,
    Execute,
    Find,
    Help,
    Pause,
    Play,
    Props,
    Select,
    ZoomIn,
    ZoomOut,
    BrightnessDown,
    BrightnessUp,
    Eject,
    LogOff,
    Power,
    PowerOff,
    PrintScreen,
    Hibernate,
    Standby,
    WakeUp,
    AllCandidates,
    Alphanumeric,
    CodeInput,
    Compose,
    Convert,
    Dead,
    FinalMode,
    GroupFirst,
    GroupLast,
    GroupNext,
    GroupPrevious,
    ModeChange,
    NextCandidate,
    NonConvert,
    PreviousCandidate,
    Process,
    SingleCandidate,
    HangulMode,
    HanjaMode,
    JunjaMode,
    Eisu,
    Hankaku,
    Hiragana,
    HiraganaKatakana,
    KanaMode,
    KanjiMode,
    Katakana,
    Romaji,
    Zenkaku,
    ZenkakuHankaku,
    Soft1,
    Soft2,
    Soft3,
    Soft4,
    ChannelDown,
    ChannelUp,
    Close,
    MailForward,
    MailReply,
    MailSend,
    MediaClose,
    MediaFastForward,
    MediaPause,
    MediaPlay,
    MediaPlayPause,
    MediaRecord,
    MediaRewind,
    MediaStop,
    MediaTrackNext,
    MediaTrackPrevious,
    New,
    Open,
    Print,
    Save,
    SpellCheck,
    Key11,
    Key12,
    AudioBalanceLeft,
    AudioBalanceRight,
    AudioBassBoostDown,
    AudioBassBoostToggle,
    AudioBassBoostUp,
    AudioFaderFront,
    AudioFaderRear,
    AudioSurroundModeNext,
    AudioTrebleDown,
    AudioTrebleUp,
    AudioVolumeDown,
    AudioVolumeUp,
    AudioVolumeMute,
    MicrophoneToggle,
    MicrophoneVolumeDown,
    MicrophoneVolumeUp,
    MicrophoneVolumeMute,
    SpeechCorrectionList,
    SpeechInputToggle,
    LaunchApplication1,
    LaunchApplication2,
    LaunchCalendar,
    LaunchContacts,
    LaunchMail,
    LaunchMediaPlayer,
    LaunchMusicPlayer,
    LaunchPhone,
    LaunchScreenSaver,
    LaunchSpreadsheet,
    LaunchWebBrowser,
    LaunchWebCam,
    LaunchWordProcessor,
    BrowserBack,
    BrowserFavorites,
    BrowserForward,
    BrowserHome,
    BrowserRefresh,
    BrowserSearch,
    BrowserStop,
    AppSwitch,
    Call,
    Camera,
    CameraFocus,
    EndCall,
    GoBack,
    GoHome,
    HeadsetHook,
    LastNumberRedial,
    Notification,
    MannerMode,
    VoiceDial,
    TV,
    TV3DMode,
    TVAntennaCable,
    TVAudioDescription,
    TVAudioDescriptionMixDown,
    TVAudioDescriptionMixUp,
    TVContentsMenu,
    TVDataService,
    TVInput,
    TVInputComponent1,
    TVInputComponent2,
    TVInputComposite1,
    TVInputComposite2,
    TVInputHDMI1,
    TVInputHDMI2,
    TVInputHDMI3,
    TVInputHDMI4,
    TVInputVGA1,
    TVMediaContext,
    TVNetwork,
    TVNumberEntry,
    TVPower,
    TVRadioService,
    TVSatellite,
    TVSatelliteBS,
    TVSatelliteCS,
    TVSatelliteToggle,
    TVTerrestrialAnalog,
    TVTerrestrialDigital,
    TVTimer,
    AVRInput,
    AVRPower,
    ColorF0Red,
    ColorF1Green,
    ColorF2Yellow,
    ColorF3Blue,
    ColorF4Grey,
    ColorF5Brown,
    ClosedCaptionToggle,
    Dimmer,
    DisplaySwap,
    DVR,
    Exit,
    FavoriteClear0,
    FavoriteClear1,
    FavoriteClear2,
    FavoriteClear3,
    FavoriteRecall0,
    FavoriteRecall1,
    FavoriteRecall2,
    FavoriteRecall3,
    FavoriteStore0,
    FavoriteStore1,
    FavoriteStore2,
    FavoriteStore3,
    Guide,
    GuideNextDay,
    GuidePreviousDay,
    Info,
    InstantReplay,
    Link,
    ListProgram,
    LiveContent,
    Lock,
    MediaApps,
    MediaAudioTrack,
    MediaLast,
    MediaSkipBackward,
    MediaSkipForward,
    MediaStepBackward,
    MediaStepForward,
    MediaTopMenu,
    NavigateIn,
    NavigateNext,
    NavigateOut,
    NavigatePrevious,
    NextFavoriteChannel,
    NextUserProfile,
    OnDemand,
    Pairing,
    PinPDown,
    PinPMove,
    PinPToggle,
    PinPUp,
    PlaySpeedDown,
    PlaySpeedReset,
    PlaySpeedUp,
    RandomToggle,
    RcLowBattery,
    RecordSpeedNext,
    RfBypass,
    ScanChannelsToggle,
    ScreenModeNext,
    Settings,
    SplitScreenToggle,
    STBInput,
    STBPower,
    Subtitle,
    Teletext,
    VideoModeNext,
    Wink,
    ZoomToggle,
    F1, F2, F3, F4, F5, F6, F7, F8, F9, F10,
    F11, F12, F13, F14, F15, F16, F17, F18, F19, F20,
    F21, F22, F23, F24, F25, F26, F27, F28, F29, F30,
    F31, F32, F33, F34, F35;
}
```

- [ ] **Step 1: Replace `Key` enum with sealed class in `Events.kt`**

Current `Key` enum (A-Z, 0-9, F1-F12, Arrow keys, Space/Enter/Escape/Backspace/Tab, ShiftLeft/Right etc, ControlLeft/Right, AltLeft/Right, MetaLeft/Right, Unknown, 66 entries).

Replace with (remove old entries, add this):

```kotlin
sealed class Key {
    data class Named(val namedKey: NamedKey) : Key()
    data class Character(val value: String) : Key()
    data class Dead(val combining: Char?) : Key()
    data object Unidentified : Key()
}
```

- [ ] **Step 2: Update `KeyboardInput` data class — add `location: KeyLocation` field**

```kotlin
data class KeyboardInput(
    val key: Key,
    val state: KeyState,
    val modifiers: Modifiers,
    val isRepeat: Boolean = false,
    val location: KeyLocation = KeyLocation.Standard
)
```

- [ ] **Step 3: Update each backend `*KeyMapper.kt` to wrap return values**

Each backend currently returns `Key.X`. Change to `Key.Named(NamedKey.X)`.

Example pattern (appkit):
```kotlin
// Before: return Key.A
// After: return Key.Named(NamedKey.KeyA)
```

Specific changes per backend:
- `AppKitKeyMapper.kt`: 53 mappings → wrap in `Key.Named(NamedKey.…)`
- `Win32KeyMapper.kt`: 51 mappings → wrap
- `X11KeyMapper.kt`: 76 mappings → wrap
- `WaylandKeyMapper.kt`: 66 mappings → wrap
- `UiKitKeyMapper.kt`: letters/digits/F1-F12/modifiers + 8 → wrap
- `AndroidKeyMapper.kt`: 50 mappings → wrap
- `DomEventMapper.kt`: 65 entries (`domCodeToKey`) → wrap

- [ ] **Step 4: Write tests**

```kotlin
class KeyTypesTest {
    @Test
    fun `NamedKey contains all expected categories`() {
        assertTrue(NamedKey.entries.contains(NamedKey.F1))
        assertTrue(NamedKey.entries.contains(NamedKey.AudioVolumeUp))
        assertTrue(NamedKey.entries.contains(NamedKey.BrowserBack))
        assertTrue(NamedKey.entries.contains(NamedKey.TV))
    }

    @Test
    fun `Key can be constructed as Named`() {
        val k: Key = Key.Named(NamedKey.Enter)
        assertEquals(NamedKey.Enter, (k as Key.Named).namedKey)
    }

    @Test
    fun `Key can be constructed as Character`() {
        val k: Key = Key.Character("a")
        assertEquals("a", (k as Key.Character).value)
    }

    @Test
    fun `KeyLocation has standard value`() {
        val loc = KeyLocation.Standard
        assertEquals(0x00, loc.value)
    }
}
```

- [ ] **Step 5: Verify build + tests pass**

Run: `./gradlew :kadre-core:jvmTest`
Expected: BUILD SUCCESSFUL

- [ ] **Step 6: Commit**

```bash
git add -A
git commit -m "feat(keyboard): widen Key enum to NamedKey + add KeyLocation"
```

---

### Task 2: Cycle 2 — PhysicalKey / KeyCode / NativeKeyCode

**Files:**
- Modify: `kadre-core/.../core/KeyTypes.kt` — add `KeyCode`, `PhysicalKey`, `NativeKeyCode`, `NativeKey`
- Modify: `kadre-core/.../core/Events.kt` — add `physicalKey` field to `KeyboardInput`

- [ ] **Step 1: Add `KeyCode` enum** (from keyboard-types v0.8 `Code`)

Source: `https://raw.githubusercontent.com/rust-windowing/keyboard-types/v0.8.0/src/code.rs`

Entries (~138): `Backquote, Backslash, BracketLeft, BracketRight, Comma, Digit0-Digit9, Equal, IntlBackslash, IntlRo, IntlYen, KeyA-KeyZ, Minus, Period, Quote, Semicolon, Slash, AltLeft, AltRight, Backspace, CapsLock, ContextMenu, ControlLeft, ControlRight, Enter, MetaLeft, MetaRight, ShiftLeft, ShiftRight, Space, Tab, Convert, KanaMode, Lang1-Lang5, NonConvert, Delete, End, Help, Home, Insert, PageDown, PageUp, ArrowDown, ArrowLeft, ArrowRight, ArrowUp, NumLock, Numpad0-Numpad9, NumpadAdd, NumpadBackspace, NumpadClear, NumpadClearEntry, NumpadComma, NumpadDecimal, NumpadDivide, NumpadEnter, NumpadEqual, NumpadHash, NumpadMemoryAdd, NumpadMemoryClear, NumpadMemoryRecall, NumpadMemoryStore, NumpadMemorySubtract, NumpadMultiply, NumpadParenLeft, NumpadParenRight, NumpadStar, NumpadSubtract, Escape, Fn, FnLock, PrintScreen, ScrollLock, Pause, BrowserBack, BrowserFavorites, BrowserForward, BrowserHome, BrowserRefresh, BrowserSearch, BrowserStop, Eject, LaunchApp1, LaunchApp2, LaunchMail, MediaPlayPause, MediaSelect, MediaStop, MediaTrackNext, MediaTrackPrevious, Power, Sleep, AudioVolumeDown, AudioVolumeMute, AudioVolumeUp, WakeUp, Hyper, Super, Turbo, Abort, Resume, Suspend, Again, Copy, Cut, Find, Open, Paste, Props, Select, Undo, Hiragana, Katakana, Unidentified, F1-F24, BrightnessDown, BrightnessUp, DisplayToggleIntExt, KeyboardLayoutSelect, LaunchAssistant, LaunchControlPanel, LaunchScreenSaver, MailForward, MailReply, MailSend, MediaFastForward, MediaPause, MediaPlay, MediaRecord, MediaRewind, MicrophoneMuteToggle, PrivacyScreenToggle, KeyboardBacklightToggle, SelectTask, ShowAllWindows, ZoomToggle`

```kotlin
enum class KeyCode {
    Backquote, Backslash, BracketLeft, BracketRight, Comma,
    Digit0, Digit1, Digit2, Digit3, Digit4, Digit5, Digit6, Digit7, Digit8, Digit9,
    Equal, IntlBackslash, IntlRo, IntlYen,
    KeyA, KeyB, KeyC, KeyD, KeyE, KeyF, KeyG, KeyH, KeyI, KeyJ,
    KeyK, KeyL, KeyM, KeyN, KeyO, KeyP, KeyQ, KeyR, KeyS, KeyT,
    KeyU, KeyV, KeyW, KeyX, KeyY, KeyZ,
    Minus, Period, Quote, Semicolon, Slash,
    AltLeft, AltRight, Backspace, CapsLock, ContextMenu,
    ControlLeft, ControlRight, Enter, MetaLeft, MetaRight,
    ShiftLeft, ShiftRight, Space, Tab,
    Convert, KanaMode, Lang1, Lang2, Lang3, Lang4, Lang5, NonConvert,
    Delete, End, Help, Home, Insert, PageDown, PageUp,
    ArrowDown, ArrowLeft, ArrowRight, ArrowUp, NumLock,
    Numpad0, Numpad1, Numpad2, Numpad3, Numpad4, Numpad5, Numpad6, Numpad7, Numpad8, Numpad9,
    NumpadAdd, NumpadBackspace, NumpadClear, NumpadClearEntry, NumpadComma,
    NumpadDecimal, NumpadDivide, NumpadEnter, NumpadEqual, NumpadHash,
    NumpadMemoryAdd, NumpadMemoryClear, NumpadMemoryRecall, NumpadMemoryStore, NumpadMemorySubtract,
    NumpadMultiply, NumpadParenLeft, NumpadParenRight, NumpadStar, NumpadSubtract,
    Escape, Fn, FnLock, PrintScreen, ScrollLock, Pause,
    BrowserBack, BrowserFavorites, BrowserForward, BrowserHome,
    BrowserRefresh, BrowserSearch, BrowserStop,
    Eject, LaunchApp1, LaunchApp2, LaunchMail,
    MediaPlayPause, MediaSelect, MediaStop, MediaTrackNext, MediaTrackPrevious,
    Power, Sleep, AudioVolumeDown, AudioVolumeMute, AudioVolumeUp, WakeUp,
    Hyper, Super, Turbo, Abort, Resume, Suspend,
    Again, Copy, Cut, Find, Open, Paste, Props, Select, Undo,
    Hiragana, Katakana, Unidentified,
    F1, F2, F3, F4, F5, F6, F7, F8, F9, F10,
    F11, F12, F13, F14, F15, F16, F17, F18, F19, F20,
    F21, F22, F23, F24,
    BrightnessDown, BrightnessUp, DisplayToggleIntExt, KeyboardLayoutSelect,
    LaunchAssistant, LaunchControlPanel, LaunchScreenSaver,
    MailForward, MailReply, MailSend,
    MediaFastForward, MediaPause, MediaPlay, MediaRecord, MediaRewind,
    MicrophoneMuteToggle, PrivacyScreenToggle, KeyboardBacklightToggle,
    SelectTask, ShowAllWindows, ZoomToggle;
}
```

- [ ] **Step 2: Add `PhysicalKey`, `NativeKeyCode`, `NativeKey`**

```kotlin
sealed class NativeKeyCode {
    data object Unidentified : NativeKeyCode()
    data class Android(val code: Int) : NativeKeyCode()
    data class MacOS(val code: Int) : NativeKeyCode()
    data class Windows(val code: Int) : NativeKeyCode()
    data class Xkb(val code: Int) : NativeKeyCode()
    data class Ohos(val code: Int) : NativeKeyCode()
}

sealed class NativeKey {
    data object Unidentified : NativeKey()
    data class Android(val code: Int) : NativeKey()
    data class MacOS(val code: Int) : NativeKey()
    data class Windows(val code: Int) : NativeKey()
    data class Xkb(val code: Int) : NativeKey()
    data class Web(val code: String) : NativeKey()
    data class Ohos(val code: Int) : NativeKey()
}

sealed class PhysicalKey {
    data class Code(val keyCode: KeyCode) : PhysicalKey()
    data class Unidentified(val native: NativeKeyCode) : PhysicalKey()
}
```

- [ ] **Step 3: Add `physicalKey` field to `KeyboardInput`**

```kotlin
data class KeyboardInput(
    val key: Key,
    val state: KeyState,
    val modifiers: Modifiers,
    val isRepeat: Boolean = false,
    val location: KeyLocation = KeyLocation.Standard,
    val physicalKey: PhysicalKey? = null
)
```

- [ ] **Step 4: Write tests**

```kotlin
class PhysicalKeyTest {
    @Test
    fun `PhysicalKey can be constructed as Code`() {
        val pk = PhysicalKey.Code(KeyCode.KeyA)
        assertEquals(KeyCode.KeyA, (pk as PhysicalKey.Code).keyCode)
    }

    @Test
    fun `NativeKeyCode has Android variant`() {
        val nkc = NativeKeyCode.Android(0x1001)
        assertEquals(0x1001, (nkc as NativeKeyCode.Android).code)
    }

    @Test
    fun `NativeKey has Web variant`() {
        val nk = NativeKey.Web("KeyQ")
        assertEquals("KeyQ", (nk as NativeKey.Web).code)
    }
}
```

- [ ] **Step 5: Build + test**

Run: `./gradlew :kadre-core:jvmTest`
Expected: BUILD SUCCESSFUL

- [ ] **Step 6: Commit**

```bash
git add -A
git commit -m "feat(keyboard): add KeyCode, PhysicalKey, NativeKeyCode, NativeKey"
```

---

### Task 3: Cycle 3 — Text field in KeyboardInput

**Files:**
- Modify: `kadre-core/.../core/Events.kt` — add `text` field to `KeyboardInput`
- Modify: All 7 backends — populate `text` from platform source

- [ ] **Step 1: Add `text` field to `KeyboardInput`**

```kotlin
data class KeyboardInput(
    val key: Key,
    val state: KeyState,
    val modifiers: Modifiers,
    val isRepeat: Boolean = false,
    val location: KeyLocation = KeyLocation.Standard,
    val physicalKey: PhysicalKey? = null,
    val text: String? = null
)
```

- [ ] **Step 2: Implement `text` per backend**

**appkit:** `event.charactersIgnoringModifiers` (Swift: `NSEvent.characters`/`charactersIgnoringModifiers`)
```kotlin
val text: String? = when (keyEventType) {
    10L -> event.characters // NSEventTypeKeyDown
    11L -> null // NSEventTypeKeyUp
    else -> null
}
```

**win32:** Use `WM_CHAR` message to get text, or `ToUnicode` on `WM_KEYDOWN`. Win32KeyMapper already receives `lParam` from `WM_KEYDOWN`/`WM_KEYUP`. Add `WM_CHAR` handler:
```kotlin
// In WndProc, after handling WM_KEYDOWN:
when (msg) {
    WM_CHAR -> {
        val charCode = wParam.toInt()
        if (charCode in 0x20..0x10FFFF) {
            emit(WindowEvent.KeyboardInput(keyboardInput.copy(text = charCode.toChar().toString())))
        }
    }
}
```

**x11:** Use `XLookupString` on `XKeyEvent`:
```kotlin
// On KeyPress, call XLib.XLookupString(event, buffer, size, null, null)
// This requires JNA/JNI access to X11
// For initial implementation, use null (no text)
```

**wayland:** Text comes from `wl_keyboard.enter` compositor string or xkbcommon. For initial implementation, use `null`.

**uikit:** `UIKey.keyCommand.input`:
```kotlin
// On pressesBegan, the input is in keyCommand.input
val text: String? = uiKey.keyCommand?.input
```

**android:** `KeyEvent.getUnicodeChar()`:
```kotlin
val text: String? = event.unicodeChar?.let { 
    if (it != 0) it.toChar().toString() else null 
}
```

**web:** Already available as `event.key`. Web backend already has `event.key` string, just need to populate the `text` field:
```kotlin
text = if (event.key.length == 1) event.key else null
```

- [ ] **Step 3: Write tests**

```kotlin
class KeyboardInputTextTest {
    @Test
    fun `KeyboardInput text defaults to null`() {
        val ki = WindowEvent.KeyboardInput(Key.Named(NamedKey.Space), KeyState.Pressed, Modifiers.NONE)
        assertNull((ki as WindowEvent.KeyboardInput).text)
    }

    @Test
    fun `KeyboardInput with Character key has text`() {
        val ki = WindowEvent.KeyboardInput(
            Key.Character("a"), KeyState.Pressed, Modifiers.NONE,
            text = "a"
        )
        assertEquals("a", ki.text)
    }
}
```

- [ ] **Step 4: Build + test**

Run: `./gradlew :kadre-core:jvmTest`
Expected: BUILD SUCCESSFUL

- [ ] **Step 5: Commit**

```bash
git add -A
git commit -m "feat(keyboard): add text field to KeyboardInput per backend"
```

---

### Task 4: Cycle 4 — ModifiersChanged + ModifiersKeys

**Files:**
- Create: `kadre-core/src/commonMain/kotlin/org/graphiks/kadre/core/Modifiers.kt`
- Modify: `kadre-core/.../core/Events.kt` — add `ModifiersChanged` variant
- Modify: All 7 backends — emit `ModifiersChanged` on modifier key events

- [ ] **Step 1: Create `Modifiers.kt`**

```kotlin
package org.graphiks.kadre.core

@JvmInline
value class ModifiersState(val bits: Int) {
    companion object {
        val NONE = ModifiersState(0)
        val SHIFT = ModifiersState(0b100)
        val CONTROL = ModifiersState(0b100_000)
        val ALT = ModifiersState(0b100_000_000)
        val META = ModifiersState(0b100_000_000_000)
    }

    fun shiftKey(): Boolean = (bits and 0b100) != 0
    fun controlKey(): Boolean = (bits and 0b100_000) != 0
    fun altKey(): Boolean = (bits and 0b100_000_000) != 0
    fun metaKey(): Boolean = (bits and 0b100_000_000_000) != 0
}

enum class ModifiersKeyState {
    Unknown,
    Pressed
}

data class ModifiersKeys(val bits: UByte) {
    companion object {
        val LSHIFT = ModifiersKeys(0b0000_0001u)
        val RSHIFT = ModifiersKeys(0b0000_0010u)
        val LCONTROL = ModifiersKeys(0b0000_0100u)
        val RCONTROL = ModifiersKeys(0b0000_1000u)
        val LALT = ModifiersKeys(0b0001_0000u)
        val RALT = ModifiersKeys(0b0010_0000u)
        val LMETA = ModifiersKeys(0b0100_0000u)
        val RMETA = ModifiersKeys(0b1000_0000u)
        val NONE = ModifiersKeys(0u)
    }

    fun contains(other: ModifiersKeys): Boolean =
        (bits and other.bits) == other.bits
}
```

- [ ] **Step 2: Add `ModifiersChanged` to `WindowEvent`**

```kotlin
sealed class WindowEvent {
    // ... existing variants ...
    data class ModifiersChanged(val modifiers: ModifiersKeys) : WindowEvent()
    // ...
}
```

- [ ] **Step 3: Implement per-backend modifier tracking**

Each backend already tracks modifier state. The key change is emitting `ModifiersChanged` on modifier key press/release.

**appkit:** `NSEventTypeFlagsChanged` (type 12) or `sendEvent:` override:
```kotlin
when (eventType) {
    12L -> { // NSEventTypeFlagsChanged
        val oldMods = currentModifiers
        val newMods = modifierFlagsToModifiersKeys(event.modifierFlags)
        if (oldMods != newMods) {
            currentModifiers = newMods
            emit(WindowEvent.ModifiersChanged(newMods))
        }
    }
}
```

**win32:** Track modifier keys individually (VK_LSHIFT, VK_RSHIFT, VK_LCONTROL, VK_RCONTROL, etc.):
```kotlin
when (wParam.toInt()) {
    VK_LSHIFT, VK_RSHIFT, VK_LCONTROL, VK_RCONTROL,
    VK_LMENU, VK_RMENU, VK_LWIN, VK_RWIN -> {
        val newMods = getCurrentModifiersKeys()
        emit(WindowEvent.ModifiersChanged(newMods))
    }
}
```

**x11:** Track modifier mask changes in `XKeyEvent`:
```kotlin
val oldMods = currentModifiersKeys
val newMods = x11ModifierMaskToModifiersKeys(event.state)
if (oldMods != newMods) {
    currentModifiersKeys = newMods
    emit(WindowEvent.ModifiersChanged(newMods))
}
```

**wayland:** Use `wl_keyboard.modifiers` event (modifiers_depressed, modifiers_latched, modifiers_locked, modifiers_group).

**uikit:** `pressesChanged` callback tracks modifier keys.

**android:** `onKeyDown`/`onKeyUp` for modifier keys (KEYCODE_SHIFT_LEFT, etc.).

**web:** `event.getModifierState()` for individual modifier keys.

- [ ] **Step 4: Write tests**

```kotlin
class ModifiersChangedTest {
    @Test
    fun `ModifiersChanged is a WindowEvent`() {
        val event = WindowEvent.ModifiersChanged(ModifiersKeys.NONE)
        assertTrue(event is WindowEvent)
    }

    @Test
    fun `ModifiersKeys contains LSHIFT and RSHIFT`() {
        val both = ModifiersKeys(ModifiersKeys.LSHIFT.bits or ModifiersKeys.RSHIFT.bits)
        assertTrue(both.contains(ModifiersKeys.LSHIFT))
        assertTrue(both.contains(ModifiersKeys.RSHIFT))
    }

    @Test
    fun `NONE contains nothing`() {
        assertFalse(ModifiersKeys.NONE.contains(ModifiersKeys.LSHIFT))
    }
}
```

- [ ] **Step 5: Build + test**

Run: `./gradlew :kadre-core:jvmTest`
Expected: BUILD SUCCESSFUL

- [ ] **Step 6: Commit**

```bash
git add -A
git commit -m "feat(keyboard): add ModifiersChanged event + ModifiersKeys tracking"
```

---

### Task 5: Cycle 5 — IME Support

**Files:**
- Create: `kadre-core/src/commonMain/kotlin/org/graphiks/kadre/core/Ime.kt`
- Modify: `kadre-core/.../core/Events.kt` — add `Ime` variant
- Modify: `kadre-core/.../core/Window.kt` — add IME window methods

- [ ] **Step 1: Create `Ime.kt`**

```kotlin
package org.graphiks.kadre.core

sealed class Ime {
    data object Enabled : Ime()
    data class Preedit(val text: String, val cursor: Pair<Int, Int>?) : Ime()
    data class Commit(val text: String) : Ime()
    data object Disabled : Ime()
}

sealed class ImePurpose {
    data object Password : ImePurpose()
    data object Text : ImePurpose()
    data object Terminal : ImePurpose()
    data class Custom(val purpose: String) : ImePurpose()
}
```

- [ ] **Step 2: Add `Ime` to `WindowEvent`**

```kotlin
sealed class WindowEvent {
    // ... existing variants ...
    data class Ime(val ime: Ime) : WindowEvent()
}
```

- [ ] **Step 3: Add IME window methods to `Window` interface**

In `kadre-core/.../core/Window.kt`:
```kotlin
interface Window {
    // ... existing methods ...
    
    fun setImeAllowed(allowed: Boolean)
    fun setImeCursorArea(x: Int, y: Int, width: Int, height: Int)
    fun setImePurpose(purpose: ImePurpose)
}
```

Implement as no-ops in backends that don't support IME yet (x11, wayland, web). Implement for:
- **appkit:** `NSTextInputClient` protocol
- **win32:** `Imm32` or `TSF` (Text Services Framework)
- **android:** `InputMethodManager` API
- **uikit:** `UITextInput` protocol

For initial implementation, provide no-op defaults that can be overridden:

```kotlin
// Window.kt default implementations
interface Window {
    fun setImeAllowed(allowed: Boolean) { /* no-op */ }
    fun setImeCursorArea(x: Int, y: Int, width: Int, height: Int) { /* no-op */ }
    fun setImePurpose(purpose: ImePurpose) { /* no-op */ }
}
```

- [ ] **Step 4: Write tests**

```kotlin
class ImeTest {
    @Test
    fun `Ime can be Enabled`() {
        val ime = Ime.Enabled
        assertTrue(ime is Ime.Enabled)
    }

    @Test
    fun `Ime Commit has text`() {
        val ime = Ime.Commit("hello")
        assertEquals("hello", (ime as Ime.Commit).text)
    }

    @Test
    fun `Ime Preedit has text and cursor`() {
        val ime = Ime.Preedit("hell", Pair(0, 4))
        assertEquals("hell", ime.text)
        assertEquals(Pair(0, 4), ime.cursor)
    }

    @Test
    fun `WindowEvent Ime wraps Ime`() {
        val event = WindowEvent.Ime(Ime.Enabled)
        assertTrue(event is WindowEvent.Ime)
    }
}
```

- [ ] **Step 5: Build + test**

Run: `./gradlew :kadre-core:jvmTest`
Expected: BUILD SUCCESSFUL

- [ ] **Step 6: Commit**

```bash
git add -A
git commit -m "feat(keyboard): add IME event types and window methods"
```

---

### Task 6: Update gap note + update dashboard

- [ ] **Step 1: Update `winit-gaps/keyboard-ime.md` status fields**

Set `core-status` to `partial` → `present` for:
- NamedKey coverage: ~307 entries (was 66)
- KeyLocation: added
- PhysicalKey: added
- KeyCode: added
- text field: added
- ModifiersChanged: added
- Ime types: added

Remaining gaps (partial):
- `ModifiersKeys` left/right tracking on all backends (partial: core types exist, but not all backends emit ModifiersChanged)
- IME platform integration (core types exist, platform implementations are no-ops)
- `DeviceEvent.Key` with `PhysicalKey` (still uses raw scancode)
- `key_without_modifiers`, `text_with_all_modifiers` (deferred per spec)

- [ ] **Step 2: Update `_index.md` dashboard with new status**

- [ ] **Step 3: Commit**

```bash
git add -A
git commit -m "docs: update keyboard gap note status after parity implementation"
```
