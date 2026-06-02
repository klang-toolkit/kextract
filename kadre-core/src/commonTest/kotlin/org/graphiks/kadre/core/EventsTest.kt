/**
 * Unit tests for the kadre-core event model.
 */
package org.graphiks.kadre.core

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class EventsTest {

    @Test
    fun `SHIFT plus CTRL contains SHIFT`() {
        val mods = KeyboardModifiers.Shift + KeyboardModifiers.Ctrl
        assertTrue(mods.contains(KeyboardModifiers.Shift))
    }

    @Test
    fun `SHIFT plus CTRL contains CTRL`() {
        val mods = KeyboardModifiers.Shift + KeyboardModifiers.Ctrl
        assertTrue(mods.contains(KeyboardModifiers.Ctrl))
    }

    @Test
    fun `SHIFT plus CTRL does not contain ALT`() {
        val mods = KeyboardModifiers.Shift + KeyboardModifiers.Ctrl
        assertFalse(mods.contains(KeyboardModifiers.Alt))
    }

    @Test
    fun `NONE contains no keyboard modifier`() {
        val mods = KeyboardModifiers.NONE
        assertFalse(mods.shift)
        assertFalse(mods.ctrl)
        assertFalse(mods.alt)
        assertFalse(mods.meta)
        assertFalse(mods.altGraph)
        assertFalse(mods.capsLock)
        assertFalse(mods.numLock)
        assertFalse(mods.symbol)
    }

    @Test
    fun `lock and symbol modifier factories expose their bits`() {
        assertTrue(KeyboardModifiers.CapsLock.capsLock)
        assertTrue(KeyboardModifiers.NumLock.numLock)
        assertTrue(KeyboardModifiers.Symbol.symbol)
    }

    @Test
    fun `minus removes a modifier`() {
        val mods = KeyboardModifiers.Shift + KeyboardModifiers.Ctrl - KeyboardModifiers.Shift
        assertFalse(mods.shift)
        assertTrue(mods.ctrl)
    }

    @Test
    fun `physical key codes include letters digits navigation and F35`() {
        val entries = KeyCode.entries.map { it.name }.toSet()
        assertTrue("KeyA" in entries)
        assertTrue("KeyZ" in entries)
        assertTrue("Digit0" in entries)
        assertTrue("Digit9" in entries)
        assertTrue("ArrowUp" in entries)
        assertTrue("NumpadEnter" in entries)
        assertTrue("F35" in entries)
    }

    @Test
    fun `physical key codes include priority IME and language keys`() {
        val entries = KeyCode.entries.map { it.name }.toSet()
        val expected = setOf(
            "Convert",
            "KanaMode",
            "Lang1",
            "Lang2",
            "Lang3",
            "Lang4",
            "Lang5",
            "NonConvert",
            "Hiragana",
            "Katakana",
        )

        assertTrue(entries.containsAll(expected))
    }

    @Test
    fun `physical key codes include priority extended numpad keys`() {
        val entries = KeyCode.entries.map { it.name }.toSet()
        val expected = setOf(
            "NumpadClearEntry",
            "NumpadHash",
            "NumpadMemoryAdd",
            "NumpadMemoryClear",
            "NumpadMemoryRecall",
            "NumpadMemoryStore",
            "NumpadMemorySubtract",
            "NumpadParenLeft",
            "NumpadParenRight",
            "NumpadStar",
        )

        assertTrue(entries.containsAll(expected))
    }

    @Test
    fun `physical key codes include priority modifier system app and media keys`() {
        val entries = KeyCode.entries.map { it.name }.toSet()
        val expected = setOf(
            "Hyper",
            "Super",
            "Turbo",
            "Abort",
            "Resume",
            "Suspend",
            "Again",
            "Copy",
            "Cut",
            "Find",
            "Open",
            "Paste",
            "Props",
            "Select",
            "Undo",
            "BrightnessDown",
            "BrightnessUp",
            "DisplayToggleIntExt",
            "KeyboardLayoutSelect",
            "LaunchAssistant",
            "LaunchControlPanel",
            "LaunchScreenSaver",
            "MailForward",
            "MailReply",
            "MailSend",
            "MediaFastForward",
            "MediaPause",
            "MediaPlay",
            "MediaRecord",
            "MediaRewind",
            "MicrophoneMuteToggle",
            "PrivacyScreenToggle",
            "KeyboardBacklightToggle",
            "SelectTask",
            "ShowAllWindows",
            "ZoomToggle",
        )

        assertTrue(entries.containsAll(expected))
    }


    @Test
    fun `physical key codes match keyboard types 0 8 3 except unidentified`() {
        val expected = setOf(
            "Backquote",
            "Backslash",
            "BracketLeft",
            "BracketRight",
            "Comma",
            "Digit0",
            "Digit1",
            "Digit2",
            "Digit3",
            "Digit4",
            "Digit5",
            "Digit6",
            "Digit7",
            "Digit8",
            "Digit9",
            "Equal",
            "IntlBackslash",
            "IntlRo",
            "IntlYen",
            "KeyA",
            "KeyB",
            "KeyC",
            "KeyD",
            "KeyE",
            "KeyF",
            "KeyG",
            "KeyH",
            "KeyI",
            "KeyJ",
            "KeyK",
            "KeyL",
            "KeyM",
            "KeyN",
            "KeyO",
            "KeyP",
            "KeyQ",
            "KeyR",
            "KeyS",
            "KeyT",
            "KeyU",
            "KeyV",
            "KeyW",
            "KeyX",
            "KeyY",
            "KeyZ",
            "Minus",
            "Period",
            "Quote",
            "Semicolon",
            "Slash",
            "AltLeft",
            "AltRight",
            "Backspace",
            "CapsLock",
            "ContextMenu",
            "ControlLeft",
            "ControlRight",
            "Enter",
            "MetaLeft",
            "MetaRight",
            "ShiftLeft",
            "ShiftRight",
            "Space",
            "Tab",
            "Convert",
            "KanaMode",
            "Lang1",
            "Lang2",
            "Lang3",
            "Lang4",
            "Lang5",
            "NonConvert",
            "Delete",
            "End",
            "Help",
            "Home",
            "Insert",
            "PageDown",
            "PageUp",
            "ArrowDown",
            "ArrowLeft",
            "ArrowRight",
            "ArrowUp",
            "NumLock",
            "Numpad0",
            "Numpad1",
            "Numpad2",
            "Numpad3",
            "Numpad4",
            "Numpad5",
            "Numpad6",
            "Numpad7",
            "Numpad8",
            "Numpad9",
            "NumpadAdd",
            "NumpadBackspace",
            "NumpadClear",
            "NumpadClearEntry",
            "NumpadComma",
            "NumpadDecimal",
            "NumpadDivide",
            "NumpadEnter",
            "NumpadEqual",
            "NumpadHash",
            "NumpadMemoryAdd",
            "NumpadMemoryClear",
            "NumpadMemoryRecall",
            "NumpadMemoryStore",
            "NumpadMemorySubtract",
            "NumpadMultiply",
            "NumpadParenLeft",
            "NumpadParenRight",
            "NumpadStar",
            "NumpadSubtract",
            "Escape",
            "Fn",
            "FnLock",
            "PrintScreen",
            "ScrollLock",
            "Pause",
            "BrowserBack",
            "BrowserFavorites",
            "BrowserForward",
            "BrowserHome",
            "BrowserRefresh",
            "BrowserSearch",
            "BrowserStop",
            "Eject",
            "LaunchApp1",
            "LaunchApp2",
            "LaunchMail",
            "MediaPlayPause",
            "MediaSelect",
            "MediaStop",
            "MediaTrackNext",
            "MediaTrackPrevious",
            "Power",
            "Sleep",
            "AudioVolumeDown",
            "AudioVolumeMute",
            "AudioVolumeUp",
            "WakeUp",
            "Hyper",
            "Super",
            "Turbo",
            "Abort",
            "Resume",
            "Suspend",
            "Again",
            "Copy",
            "Cut",
            "Find",
            "Open",
            "Paste",
            "Props",
            "Select",
            "Undo",
            "Hiragana",
            "Katakana",
            "F1",
            "F2",
            "F3",
            "F4",
            "F5",
            "F6",
            "F7",
            "F8",
            "F9",
            "F10",
            "F11",
            "F12",
            "F13",
            "F14",
            "F15",
            "F16",
            "F17",
            "F18",
            "F19",
            "F20",
            "F21",
            "F22",
            "F23",
            "F24",
            "F25",
            "F26",
            "F27",
            "F28",
            "F29",
            "F30",
            "F31",
            "F32",
            "F33",
            "F34",
            "F35",
            "BrightnessDown",
            "BrightnessUp",
            "DisplayToggleIntExt",
            "KeyboardLayoutSelect",
            "LaunchAssistant",
            "LaunchControlPanel",
            "LaunchScreenSaver",
            "MailForward",
            "MailReply",
            "MailSend",
            "MediaFastForward",
            "MediaPause",
            "MediaPlay",
            "MediaRecord",
            "MediaRewind",
            "MicrophoneMuteToggle",
            "PrivacyScreenToggle",
            "KeyboardBacklightToggle",
            "SelectTask",
            "ShowAllWindows",
            "ZoomToggle",
        )

        assertEquals(expected, KeyCode.entries.map { it.name }.toSet())
    }

    @Test
    fun `physical key codes keep unidentified represented by PhysicalKey instead of KeyCode`() {
        assertFalse("Unidentified" in KeyCode.entries.map { it.name }.toSet())
        assertEquals(PhysicalKey.Unidentified, PhysicalKey.Unidentified)
        assertEquals(PhysicalKey.Native(KeyPlatform.Web, 0), PhysicalKey.Native(KeyPlatform.Web, 0))
    }

    @Test
    fun `named keys include text navigation modifiers and media`() {
        val entries = NamedKey.entries.map { it.name }.toSet()
        assertTrue("Enter" in entries)
        assertTrue("ArrowDown" in entries)
        assertTrue("AltGraph" in entries)
        assertTrue("MediaPlayPause" in entries)
        assertTrue("F35" in entries)
    }

    @Test
    fun `named keys include priority IME power app and media keys from keyboard types`() {
        val entries = NamedKey.entries.map { it.name }.toSet()
        val expected = setOf(
            "Convert",
            "KanaMode",
            "NonConvert",
            "Hiragana",
            "Katakana",
            "BrightnessDown",
            "BrightnessUp",
            "Eject",
            "Power",
            "PowerOff",
            "Hibernate",
            "Standby",
            "WakeUp",
            "MailForward",
            "MailReply",
            "MailSend",
            "MediaFastForward",
            "MediaRecord",
            "MediaRewind",
            "Open",
            "Print",
            "Save",
            "SpellCheck",
            "MicrophoneToggle",
            "MicrophoneVolumeMute",
            "LaunchApplication1",
            "LaunchApplication2",
        )

        assertTrue(entries.containsAll(expected))
        assertFalse("LaunchApp1" in entries)
        assertFalse("LaunchApp2" in entries)
        assertFalse("LaunchAssistant" in entries)
    }

    @Test
    fun `named keys match keyboard types 0 8 3 except unidentified`() {
        val expected = setOf(
            "Alt",
            "AltGraph",
            "CapsLock",
            "Control",
            "Fn",
            "FnLock",
            "Meta",
            "NumLock",
            "ScrollLock",
            "Shift",
            "Symbol",
            "SymbolLock",
            "Hyper",
            "Super",
            "Enter",
            "Tab",
            "ArrowDown",
            "ArrowLeft",
            "ArrowRight",
            "ArrowUp",
            "End",
            "Home",
            "PageDown",
            "PageUp",
            "Backspace",
            "Clear",
            "Copy",
            "CrSel",
            "Cut",
            "Delete",
            "EraseEof",
            "ExSel",
            "Insert",
            "Paste",
            "Redo",
            "Undo",
            "Accept",
            "Again",
            "Attn",
            "Cancel",
            "ContextMenu",
            "Escape",
            "Execute",
            "Find",
            "Help",
            "Pause",
            "Play",
            "Props",
            "Select",
            "ZoomIn",
            "ZoomOut",
            "BrightnessDown",
            "BrightnessUp",
            "Eject",
            "LogOff",
            "Power",
            "PowerOff",
            "PrintScreen",
            "Hibernate",
            "Standby",
            "WakeUp",
            "AllCandidates",
            "Alphanumeric",
            "CodeInput",
            "Compose",
            "Convert",
            "Dead",
            "FinalMode",
            "GroupFirst",
            "GroupLast",
            "GroupNext",
            "GroupPrevious",
            "ModeChange",
            "NextCandidate",
            "NonConvert",
            "PreviousCandidate",
            "Process",
            "SingleCandidate",
            "HangulMode",
            "HanjaMode",
            "JunjaMode",
            "Eisu",
            "Hankaku",
            "Hiragana",
            "HiraganaKatakana",
            "KanaMode",
            "KanjiMode",
            "Katakana",
            "Romaji",
            "Zenkaku",
            "ZenkakuHankaku",
            "Soft1",
            "Soft2",
            "Soft3",
            "Soft4",
            "ChannelDown",
            "ChannelUp",
            "Close",
            "MailForward",
            "MailReply",
            "MailSend",
            "MediaClose",
            "MediaFastForward",
            "MediaPause",
            "MediaPlay",
            "MediaPlayPause",
            "MediaRecord",
            "MediaRewind",
            "MediaStop",
            "MediaTrackNext",
            "MediaTrackPrevious",
            "New",
            "Open",
            "Print",
            "Save",
            "SpellCheck",
            "Key11",
            "Key12",
            "AudioBalanceLeft",
            "AudioBalanceRight",
            "AudioBassBoostDown",
            "AudioBassBoostToggle",
            "AudioBassBoostUp",
            "AudioFaderFront",
            "AudioFaderRear",
            "AudioSurroundModeNext",
            "AudioTrebleDown",
            "AudioTrebleUp",
            "AudioVolumeDown",
            "AudioVolumeUp",
            "AudioVolumeMute",
            "MicrophoneToggle",
            "MicrophoneVolumeDown",
            "MicrophoneVolumeUp",
            "MicrophoneVolumeMute",
            "SpeechCorrectionList",
            "SpeechInputToggle",
            "LaunchApplication1",
            "LaunchApplication2",
            "LaunchCalendar",
            "LaunchContacts",
            "LaunchMail",
            "LaunchMediaPlayer",
            "LaunchMusicPlayer",
            "LaunchPhone",
            "LaunchScreenSaver",
            "LaunchSpreadsheet",
            "LaunchWebBrowser",
            "LaunchWebCam",
            "LaunchWordProcessor",
            "BrowserBack",
            "BrowserFavorites",
            "BrowserForward",
            "BrowserHome",
            "BrowserRefresh",
            "BrowserSearch",
            "BrowserStop",
            "AppSwitch",
            "Call",
            "Camera",
            "CameraFocus",
            "EndCall",
            "GoBack",
            "GoHome",
            "HeadsetHook",
            "LastNumberRedial",
            "Notification",
            "MannerMode",
            "VoiceDial",
            "TV",
            "TV3DMode",
            "TVAntennaCable",
            "TVAudioDescription",
            "TVAudioDescriptionMixDown",
            "TVAudioDescriptionMixUp",
            "TVContentsMenu",
            "TVDataService",
            "TVInput",
            "TVInputComponent1",
            "TVInputComponent2",
            "TVInputComposite1",
            "TVInputComposite2",
            "TVInputHDMI1",
            "TVInputHDMI2",
            "TVInputHDMI3",
            "TVInputHDMI4",
            "TVInputVGA1",
            "TVMediaContext",
            "TVNetwork",
            "TVNumberEntry",
            "TVPower",
            "TVRadioService",
            "TVSatellite",
            "TVSatelliteBS",
            "TVSatelliteCS",
            "TVSatelliteToggle",
            "TVTerrestrialAnalog",
            "TVTerrestrialDigital",
            "TVTimer",
            "AVRInput",
            "AVRPower",
            "ColorF0Red",
            "ColorF1Green",
            "ColorF2Yellow",
            "ColorF3Blue",
            "ColorF4Grey",
            "ColorF5Brown",
            "ClosedCaptionToggle",
            "Dimmer",
            "DisplaySwap",
            "DVR",
            "Exit",
            "FavoriteClear0",
            "FavoriteClear1",
            "FavoriteClear2",
            "FavoriteClear3",
            "FavoriteRecall0",
            "FavoriteRecall1",
            "FavoriteRecall2",
            "FavoriteRecall3",
            "FavoriteStore0",
            "FavoriteStore1",
            "FavoriteStore2",
            "FavoriteStore3",
            "Guide",
            "GuideNextDay",
            "GuidePreviousDay",
            "Info",
            "InstantReplay",
            "Link",
            "ListProgram",
            "LiveContent",
            "Lock",
            "MediaApps",
            "MediaAudioTrack",
            "MediaLast",
            "MediaSkipBackward",
            "MediaSkipForward",
            "MediaStepBackward",
            "MediaStepForward",
            "MediaTopMenu",
            "NavigateIn",
            "NavigateNext",
            "NavigateOut",
            "NavigatePrevious",
            "NextFavoriteChannel",
            "NextUserProfile",
            "OnDemand",
            "Pairing",
            "PinPDown",
            "PinPMove",
            "PinPToggle",
            "PinPUp",
            "PlaySpeedDown",
            "PlaySpeedReset",
            "PlaySpeedUp",
            "RandomToggle",
            "RcLowBattery",
            "RecordSpeedNext",
            "RfBypass",
            "ScanChannelsToggle",
            "ScreenModeNext",
            "Settings",
            "SplitScreenToggle",
            "STBInput",
            "STBPower",
            "Subtitle",
            "Teletext",
            "VideoModeNext",
            "Wink",
            "ZoomToggle",
            "F1",
            "F2",
            "F3",
            "F4",
            "F5",
            "F6",
            "F7",
            "F8",
            "F9",
            "F10",
            "F11",
            "F12",
            "F13",
            "F14",
            "F15",
            "F16",
            "F17",
            "F18",
            "F19",
            "F20",
            "F21",
            "F22",
            "F23",
            "F24",
            "F25",
            "F26",
            "F27",
            "F28",
            "F29",
            "F30",
            "F31",
            "F32",
            "F33",
            "F34",
            "F35",
        )

        assertEquals(expected, NamedKey.entries.map { it.name }.toSet())
    }

    @Test
    fun `KeyEvent separates physical and logical key`() {
        val event = KeyEvent(
            physicalKey = PhysicalKey.Code(KeyCode.KeyW),
            logicalKey = LogicalKey.Character("z"),
            state = KeyState.Pressed,
            modifiers = KeyboardModifiers.NONE,
            text = "z",
            keyWithoutModifiers = LogicalKey.Character("z"),
            native = NativeKeyInfo(platform = KeyPlatform.Web, keyCode = "KeyW", keyValue = "z"),
        )

        assertEquals(PhysicalKey.Code(KeyCode.KeyW), event.physicalKey)
        assertEquals(LogicalKey.Character("z"), event.logicalKey)
        assertEquals("z", event.character)
        assertTrue(event.isPressed)
        assertFalse(event.isReleased)
        assertEquals(KeyPlatform.Web, event.native.platform)
    }

    @Test
    fun `KeyEvent exposes shortcut key and preferred text`() {
        val event = KeyEvent(
            physicalKey = PhysicalKey.Code(KeyCode.KeyS),
            logicalKey = LogicalKey.Character("S"),
            state = KeyState.Pressed,
            modifiers = KeyboardModifiers.Ctrl + KeyboardModifiers.Shift,
            text = null,
            textWithAllModifiers = "\u0013",
            keyWithoutModifiers = LogicalKey.Character("s"),
        )

        assertEquals(LogicalKey.Character("s"), event.shortcutKey)
        assertEquals("\u0013", event.effectiveText)
    }

    @Test
    fun `KeyEvent falls back to logical key and text when optional shortcut fields are absent`() {
        val event = KeyEvent(
            physicalKey = PhysicalKey.Code(KeyCode.KeyA),
            logicalKey = LogicalKey.Character("a"),
            state = KeyState.Pressed,
            modifiers = KeyboardModifiers.NONE,
            text = "a",
        )

        assertEquals(LogicalKey.Character("a"), event.shortcutKey)
        assertEquals("a", event.effectiveText)
    }

    @Test
    fun `LogicalKey Dead is distinct from printable character`() {
        assertFalse(LogicalKey.Dead("^") == LogicalKey.Character("^"))
    }

    @Test
    fun `Native physical key keeps platform code`() {
        val key = PhysicalKey.Native(NativeKeyCode.AppKit(126))
        assertEquals(KeyPlatform.AppKit, key.platform)
        assertEquals(126, key.code)
        assertEquals(NativeKeyCode.PlatformCode(KeyPlatform.AppKit, 126), key.nativeCode)
    }

    @Test
    fun `Native physical key keeps legacy platform code constructor`() {
        val key = PhysicalKey.Native(KeyPlatform.X11, 64)

        assertEquals(KeyPlatform.X11, key.platform)
        assertEquals(NativeKeyCode.PlatformCode(KeyPlatform.X11, 64), key.nativeCode)
    }

    @Test
    fun `NativeKeyInfo can carry typed physical and logical native identities`() {
        val info = NativeKeyInfo(
            platform = KeyPlatform.Web,
            keyCode = "IntlYen",
            keyValue = "\u00a5",
            nativeCode = NativeKeyCode.Web("IntlYen"),
            nativeKey = NativeLogicalKey.Web("\u00a5"),
        )

        assertEquals(NativeKeyCode.Web("IntlYen"), info.nativeCode)
        assertEquals(NativeLogicalKey.Web("\u00a5"), info.nativeKey)
    }

    @Test
    fun `physical key location can be inferred from standardized key codes`() {
        assertEquals(KeyLocation.Left, KeyCode.ShiftLeft.location())
        assertEquals(KeyLocation.Right, KeyCode.ControlRight.location())
        assertEquals(KeyLocation.Numpad, KeyCode.NumpadEnter.location())
        assertEquals(KeyLocation.Standard, KeyCode.KeyA.location())
        assertEquals(KeyLocation.Left, PhysicalKey.Code(KeyCode.AltLeft).location())
        assertEquals(KeyLocation.Standard, PhysicalKey.Native(KeyPlatform.X11, 64).location())
    }

    @Test
    fun `extended numpad physical key codes infer numpad location`() {
        val numpadKeys = listOf(
            KeyCode.NumpadClearEntry,
            KeyCode.NumpadHash,
            KeyCode.NumpadMemoryAdd,
            KeyCode.NumpadMemoryClear,
            KeyCode.NumpadMemoryRecall,
            KeyCode.NumpadMemoryStore,
            KeyCode.NumpadMemorySubtract,
            KeyCode.NumpadParenLeft,
            KeyCode.NumpadParenRight,
            KeyCode.NumpadStar,
        )

        numpadKeys.forEach { keyCode ->
            assertEquals(KeyLocation.Numpad, keyCode.location())
        }
    }

    @Test
    fun `default logical key and text cover portable printable and named keys`() {
        assertEquals(LogicalKey.Character("a"), KeyCode.KeyA.defaultLogicalKey())
        assertEquals(LogicalKey.Character("9"), KeyCode.Digit9.defaultLogicalKey())
        assertEquals(LogicalKey.Named(NamedKey.ArrowDown), KeyCode.ArrowDown.defaultLogicalKey())
        assertEquals(LogicalKey.Named(NamedKey.Enter), KeyCode.NumpadEnter.defaultLogicalKey())
        assertEquals(LogicalKey.Character(" "), KeyCode.Space.defaultLogicalKey())
        assertEquals("a", KeyCode.KeyA.defaultText())
        assertEquals("9", KeyCode.Digit9.defaultText())
        assertEquals(" ", KeyCode.Space.defaultText())
        assertEquals(null, KeyCode.ArrowDown.defaultText())
    }

    @Test
    fun `default logical key maps obvious priority IME app and media keys`() {
        assertEquals(LogicalKey.Named(NamedKey.Convert), KeyCode.Convert.defaultLogicalKey())
        assertEquals(LogicalKey.Named(NamedKey.KanaMode), KeyCode.KanaMode.defaultLogicalKey())
        assertEquals(LogicalKey.Named(NamedKey.NonConvert), KeyCode.NonConvert.defaultLogicalKey())
        assertEquals(LogicalKey.Named(NamedKey.Hiragana), KeyCode.Hiragana.defaultLogicalKey())
        assertEquals(LogicalKey.Named(NamedKey.Katakana), KeyCode.Katakana.defaultLogicalKey())
        assertEquals(LogicalKey.Named(NamedKey.Hyper), KeyCode.Hyper.defaultLogicalKey())
        assertEquals(LogicalKey.Named(NamedKey.Super), KeyCode.Super.defaultLogicalKey())
        assertEquals(LogicalKey.Named(NamedKey.Again), KeyCode.Again.defaultLogicalKey())
        assertEquals(LogicalKey.Named(NamedKey.Copy), KeyCode.Copy.defaultLogicalKey())
        assertEquals(LogicalKey.Named(NamedKey.Cut), KeyCode.Cut.defaultLogicalKey())
        assertEquals(LogicalKey.Named(NamedKey.Find), KeyCode.Find.defaultLogicalKey())
        assertEquals(LogicalKey.Named(NamedKey.Open), KeyCode.Open.defaultLogicalKey())
        assertEquals(LogicalKey.Named(NamedKey.Paste), KeyCode.Paste.defaultLogicalKey())
        assertEquals(LogicalKey.Named(NamedKey.Props), KeyCode.Props.defaultLogicalKey())
        assertEquals(LogicalKey.Named(NamedKey.Select), KeyCode.Select.defaultLogicalKey())
        assertEquals(LogicalKey.Named(NamedKey.Undo), KeyCode.Undo.defaultLogicalKey())
        assertEquals(LogicalKey.Named(NamedKey.BrightnessDown), KeyCode.BrightnessDown.defaultLogicalKey())
        assertEquals(LogicalKey.Named(NamedKey.BrightnessUp), KeyCode.BrightnessUp.defaultLogicalKey())
        assertEquals(LogicalKey.Named(NamedKey.MailForward), KeyCode.MailForward.defaultLogicalKey())
        assertEquals(LogicalKey.Named(NamedKey.MailReply), KeyCode.MailReply.defaultLogicalKey())
        assertEquals(LogicalKey.Named(NamedKey.MailSend), KeyCode.MailSend.defaultLogicalKey())
        assertEquals(LogicalKey.Named(NamedKey.MediaFastForward), KeyCode.MediaFastForward.defaultLogicalKey())
        assertEquals(LogicalKey.Named(NamedKey.MediaPause), KeyCode.MediaPause.defaultLogicalKey())
        assertEquals(LogicalKey.Named(NamedKey.MediaPlay), KeyCode.MediaPlay.defaultLogicalKey())
        assertEquals(LogicalKey.Named(NamedKey.MediaRecord), KeyCode.MediaRecord.defaultLogicalKey())
        assertEquals(LogicalKey.Named(NamedKey.MediaRewind), KeyCode.MediaRewind.defaultLogicalKey())
        assertEquals(LogicalKey.Named(NamedKey.MicrophoneVolumeMute), KeyCode.MicrophoneMuteToggle.defaultLogicalKey())
    }

    @Test
    fun `default logical key preserves uncertain priority keys as unidentified native key code`() {
        val logical = KeyCode.LaunchAssistant.defaultLogicalKey()

        assertTrue(logical is LogicalKey.Unidentified)
        assertEquals("LaunchAssistant", logical.native.keyCode)
    }

    @Test
    fun `default logical key preserves unsupported portable key as unidentified native key code`() {
        val logical = KeyCode.IntlYen.defaultLogicalKey()

        assertTrue(logical is LogicalKey.Unidentified)
        assertEquals("IntlYen", logical.native.keyCode)
    }

    @Test
    fun `KeyChord can match physical bindings independent of layout`() {
        val chord = KeyChord(physicalKey = PhysicalKey.Code(KeyCode.KeyW))
        val event = KeyEvent(
            physicalKey = PhysicalKey.Code(KeyCode.KeyW),
            logicalKey = LogicalKey.Character("z"),
            state = KeyState.Pressed,
            modifiers = KeyboardModifiers.NONE,
        )

        assertTrue(chord.matches(event))
    }

    @Test
    fun `KeyChord contains modifier matching allows additional modifiers by default`() {
        val chord = KeyChord(
            logicalKey = LogicalKey.Character("s"),
            modifiers = KeyboardModifiers.Ctrl,
        )
        val event = KeyEvent(
            physicalKey = PhysicalKey.Code(KeyCode.KeyS),
            logicalKey = LogicalKey.Character("s"),
            state = KeyState.Pressed,
            modifiers = KeyboardModifiers.Ctrl + KeyboardModifiers.Shift,
        )

        assertTrue(chord.matches(event))
    }

    @Test
    fun `KeyChord can require exact modifiers`() {
        val chord = KeyChord(
            logicalKey = LogicalKey.Character("s"),
            modifiers = KeyboardModifiers.Ctrl,
            modifierMatch = KeyChordModifierMatch.Exact,
        )
        val event = KeyEvent(
            physicalKey = PhysicalKey.Code(KeyCode.KeyS),
            logicalKey = LogicalKey.Character("s"),
            state = KeyState.Pressed,
            modifiers = KeyboardModifiers.Ctrl + KeyboardModifiers.Shift,
        )

        assertFalse(chord.matches(event))
    }

    @Test
    fun `KeyChord exact modifiers match when no additional modifiers are present`() {
        val chord = KeyChord(
            logicalKey = LogicalKey.Character("s"),
            modifiers = KeyboardModifiers.Ctrl,
            modifierMatch = KeyChordModifierMatch.Exact,
        )
        val event = KeyEvent(
            physicalKey = PhysicalKey.Code(KeyCode.KeyS),
            logicalKey = LogicalKey.Character("s"),
            state = KeyState.Pressed,
            modifiers = KeyboardModifiers.Ctrl,
        )

        assertTrue(chord.matches(event))
    }

    @Test
    fun `KeyChord rejects repeat by default`() {
        val chord = KeyChord(logicalKey = LogicalKey.Named(NamedKey.Enter))
        val event = KeyEvent(
            physicalKey = PhysicalKey.Code(KeyCode.Enter),
            logicalKey = LogicalKey.Named(NamedKey.Enter),
            state = KeyState.Pressed,
            modifiers = KeyboardModifiers.NONE,
            repeat = true,
        )

        assertFalse(chord.matches(event))
    }

    @Test
    fun `KeyChord can allow repeat explicitly`() {
        val chord = KeyChord(
            logicalKey = LogicalKey.Named(NamedKey.Enter),
            allowRepeat = true,
        )
        val event = KeyEvent(
            physicalKey = PhysicalKey.Code(KeyCode.Enter),
            logicalKey = LogicalKey.Named(NamedKey.Enter),
            state = KeyState.Pressed,
            modifiers = KeyboardModifiers.NONE,
            repeat = true,
        )

        assertTrue(chord.matches(event))
    }

    @Test
    fun `KeyChord never matches released events`() {
        val chord = KeyChord(logicalKey = LogicalKey.Named(NamedKey.Enter))
        val event = KeyEvent(
            physicalKey = PhysicalKey.Code(KeyCode.Enter),
            logicalKey = LogicalKey.Named(NamedKey.Enter),
            state = KeyState.Released,
            modifiers = KeyboardModifiers.NONE,
        )

        assertFalse(chord.matches(event))
    }

    @Test
    fun `KeyChord can match logical shortcuts with modifiers`() {
        val chord = KeyChord(
            logicalKey = LogicalKey.Character("s"),
            modifiers = KeyboardModifiers.Ctrl,
        )
        val event = KeyEvent(
            physicalKey = PhysicalKey.Code(KeyCode.KeyS),
            logicalKey = LogicalKey.Character("s"),
            state = KeyState.Pressed,
            modifiers = KeyboardModifiers.Ctrl + KeyboardModifiers.Shift,
        )

        assertTrue(chord.matches(event))
    }

    @Test
    fun `KeyChord logical shortcuts use key without modifiers when present`() {
        val chord = KeyChord(
            logicalKey = LogicalKey.Character("s"),
            modifiers = KeyboardModifiers.Ctrl,
        )
        val event = KeyEvent(
            physicalKey = PhysicalKey.Code(KeyCode.KeyS),
            logicalKey = LogicalKey.Character("S"),
            state = KeyState.Pressed,
            modifiers = KeyboardModifiers.Ctrl,
            keyWithoutModifiers = LogicalKey.Character("s"),
        )

        assertTrue(chord.matches(event))
    }

    @Test
    fun `KeyState has exactly Pressed and Released`() {
        assertEquals(setOf("Pressed", "Released"), KeyState.entries.map { it.name }.toSet())
    }

    @Test
    fun `TouchPhase has exactly the four phases`() {
        assertEquals(setOf("Started", "Moved", "Ended", "Cancelled"), TouchPhase.entries.map { it.name }.toSet())
    }

    @Test
    fun `MouseButton Left is a singleton`() {
        assertTrue(MouseButton.Left === MouseButton.Left)
    }

    @Test
    fun `MouseButton Other keeps its index`() {
        assertEquals(5, MouseButton.Other(5).button)
    }

    @Test
    fun `two MouseButton Other with same indices are equal`() {
        assertEquals(MouseButton.Other(3), MouseButton.Other(3))
    }

    private fun classifyWindowEvent(event: WindowEvent): String = when (event) {
        WindowEvent.CloseRequested -> "CloseRequested"
        is WindowEvent.Resized -> "Resized"
        is WindowEvent.Moved -> "Moved"
        is WindowEvent.ScaleFactorChanged -> "ScaleFactorChanged"
        is WindowEvent.Focused -> "Focused"
        is WindowEvent.KeyInput -> "KeyInput"
        is WindowEvent.PointerMoved -> "PointerMoved"
        is WindowEvent.PointerEntered -> "PointerEntered"
        is WindowEvent.PointerLeft -> "PointerLeft"
        is WindowEvent.PointerButton -> "PointerButton"
        is WindowEvent.MouseWheel -> "MouseWheel"
        is WindowEvent.ModifiersChanged -> "ModifiersChanged"
        WindowEvent.RedrawRequested -> "RedrawRequested"
        WindowEvent.Destroyed -> "Destroyed"
        is WindowEvent.ThemeChanged -> "ThemeChanged"
        is WindowEvent.Ime -> "Ime"
        is WindowEvent.DragEntered -> "DragEntered"
        is WindowEvent.DragMoved -> "DragMoved"
        is WindowEvent.DragDropped -> "DragDropped"
        WindowEvent.DragLeft -> "DragLeft"
        is WindowEvent.PinchGesture -> "PinchGesture"
        is WindowEvent.PanGesture -> "PanGesture"
        is WindowEvent.RotationGesture -> "RotationGesture"
        is WindowEvent.DoubleTapGesture -> "DoubleTapGesture"
        is WindowEvent.TouchpadPressure -> "TouchpadPressure"
        is WindowEvent.Occluded -> "Occluded"
    }

    @Test
    fun `WindowEvent KeyInput keeps rich event`() {
        val deviceId = DeviceId(9L)
        val keyEvent = KeyEvent(
            physicalKey = PhysicalKey.Code(KeyCode.KeyA),
            logicalKey = LogicalKey.Character("a"),
            state = KeyState.Pressed,
            modifiers = KeyboardModifiers.Shift,
            text = "A",
        )
        val event = WindowEvent.KeyInput(keyEvent, deviceId = deviceId)
        assertEquals("KeyInput", classifyWindowEvent(event))
        assertEquals(keyEvent, event.event)
        assertEquals(deviceId, event.deviceId)
    }

    @Test
    fun `WindowEvent KeyInput defaults to unknown device id`() {
        val keyEvent = KeyEvent(
            physicalKey = PhysicalKey.Code(KeyCode.KeyA),
            logicalKey = LogicalKey.Character("a"),
            state = KeyState.Pressed,
            modifiers = KeyboardModifiers.NONE,
        )
        val event = WindowEvent.KeyInput(keyEvent)

        assertEquals(null, event.deviceId)
    }

    @Test
    fun `WindowEvent ModifiersChanged keeps logical and physical state`() {
        val state = KeyboardModifierState(
            logical = KeyboardModifiers.Shift,
            physical = ModifierKeys(leftShift = ModifierKeyState.Pressed),
        )
        val event = WindowEvent.ModifiersChanged(state)

        assertEquals("ModifiersChanged", classifyWindowEvent(event))
        assertTrue(event.state.logical.shift)
        assertEquals(ModifierKeyState.Pressed, event.state.physical.leftShift)
    }

    @Test
    fun `WindowEvent basic variants keep payloads`() {
        val pointerPos = PhysicalPosition(1.0, 2.0)
        assertEquals("CloseRequested", classifyWindowEvent(WindowEvent.CloseRequested))
        assertEquals("Resized", classifyWindowEvent(WindowEvent.Resized(PhysicalSize(1920, 1080))))
        assertEquals("Moved", classifyWindowEvent(WindowEvent.Moved(PhysicalPosition(10, 20))))
        assertEquals("ScaleFactorChanged", classifyWindowEvent(WindowEvent.ScaleFactorChanged(2.0)))
        assertEquals("Focused", classifyWindowEvent(WindowEvent.Focused(true)))
        assertEquals("PointerMoved", classifyWindowEvent(WindowEvent.PointerMoved(DeviceId(3L), pointerPos, true, PointerSource.Mouse)))
        assertEquals("PointerEntered", classifyWindowEvent(WindowEvent.PointerEntered(null, pointerPos, true, PointerKind.Mouse)))
        assertEquals("PointerLeft", classifyWindowEvent(WindowEvent.PointerLeft(null, null, true, PointerKind.Unknown)))
        assertEquals("PointerButton", classifyWindowEvent(WindowEvent.PointerButton(DeviceId(9L), KeyState.Released, pointerPos, true, ButtonSource.Mouse(MouseButton.Left))))
        assertEquals("MouseWheel", classifyWindowEvent(WindowEvent.MouseWheel(DeviceId(2L), 1.0, -1.0, TouchPhase.Moved)))
        assertEquals("RedrawRequested", classifyWindowEvent(WindowEvent.RedrawRequested))
        assertEquals("Destroyed", classifyWindowEvent(WindowEvent.Destroyed))
    }

    @Test
    fun `PointerSource Touch keeps finger id and force`() {
        val source = PointerSource.Touch(
            fingerId = FingerId(42L),
            force = TouchForce.Normalized(0.5),
        )
        assertEquals(FingerId(42L), source.fingerId)
        assertEquals(TouchForce.Normalized(0.5), source.force)
    }

    @Test
    fun `WindowEvent ThemeChanged keeps the theme`() {
        val event = WindowEvent.ThemeChanged(Theme.Dark)
        assertEquals("ThemeChanged", classifyWindowEvent(event))
        assertEquals(Theme.Dark, event.theme)
    }

    @Test
    fun `WindowEvent Ime variants keep payloads`() {
        val commit = WindowEvent.Ime.ImeEvent.Commit("hello")
        val preedit = WindowEvent.Ime.ImeEvent.Preedit("abc", Pair(0, 3))
        val delete = WindowEvent.Ime.ImeEvent.DeleteSurrounding(3, 5)

        assertEquals("Ime", classifyWindowEvent(WindowEvent.Ime(commit)))
        assertEquals("hello", commit.text)
        assertEquals(Pair(0, 3), preedit.cursorRange)
        assertEquals(3, delete.beforeBytes)
        assertEquals(5, delete.afterBytes)
        assertTrue(WindowEvent.Ime.ImeEvent.Enabled === WindowEvent.Ime.ImeEvent.Enabled)
        assertTrue(WindowEvent.Ime.ImeEvent.Disabled === WindowEvent.Ime.ImeEvent.Disabled)
    }

    @Test
    fun `WindowEvent drag and gesture variants keep payloads`() {
        val pos = PhysicalPosition(10.0, 20.0)
        val panDelta = PhysicalPosition(1.5f, -2.5f)
        val deviceId = DeviceId(11L)

        val dragEntered = WindowEvent.DragEntered(pos, listOf("a"))
        val dragMoved = WindowEvent.DragMoved(pos)
        val dragDropped = WindowEvent.DragDropped(pos, listOf("b"))
        val pinch = WindowEvent.PinchGesture(deviceId, delta = 0.5, phase = TouchPhase.Moved)
        val pan = WindowEvent.PanGesture(deviceId, delta = panDelta, phase = TouchPhase.Started)
        val rotation = WindowEvent.RotationGesture(deviceId, deltaDegrees = 12.5f, phase = TouchPhase.Ended)
        val doubleTap = WindowEvent.DoubleTapGesture(deviceId)
        val pressure = WindowEvent.TouchpadPressure(deviceId, pressure = 0.8f, stage = 2L)

        assertEquals("DragEntered", classifyWindowEvent(dragEntered))
        assertEquals(pos, dragEntered.position)
        assertEquals("DragMoved", classifyWindowEvent(dragMoved))
        assertEquals("DragDropped", classifyWindowEvent(dragDropped))
        assertEquals("DragLeft", classifyWindowEvent(WindowEvent.DragLeft))
        assertEquals("PinchGesture", classifyWindowEvent(pinch))
        assertEquals(0.5, pinch.delta)
        assertEquals("PanGesture", classifyWindowEvent(pan))
        assertEquals(panDelta, pan.delta)
        assertEquals("RotationGesture", classifyWindowEvent(rotation))
        assertEquals(12.5f, rotation.deltaDegrees)
        assertEquals("DoubleTapGesture", classifyWindowEvent(doubleTap))
        assertEquals("TouchpadPressure", classifyWindowEvent(pressure))
        assertEquals(2L, pressure.stage)
        assertEquals("Occluded", classifyWindowEvent(WindowEvent.Occluded(true)))
    }

    private fun classifyDeviceEvent(event: DeviceEvent): String = when (event) {
        is DeviceEvent.PointerMotion -> "PointerMotion"
        is DeviceEvent.Button -> "Button"
        is DeviceEvent.Key -> "Key"
        is DeviceEvent.MouseWheel -> "MouseWheel"
    }

    @Test
    fun `DeviceEvent variants keep payloads`() {
        val motion = DeviceEvent.PointerMotion(1.5, -2.5)
        val button = DeviceEvent.Button(2, KeyState.Pressed)
        val key = DeviceEvent.Key(
            RawKeyEvent(
                physicalKey = PhysicalKey.Code(KeyCode.KeyA),
                state = KeyState.Released,
                native = NativeKeyInfo(platform = KeyPlatform.X11, scanCode = 0x1E),
            ),
        )
        val wheel = DeviceEvent.MouseWheel(3.0, -1.5)

        assertEquals("PointerMotion", classifyDeviceEvent(motion))
        assertEquals(1.5, motion.dx)
        assertEquals("Button", classifyDeviceEvent(button))
        assertEquals(2, button.button)
        assertEquals("Key", classifyDeviceEvent(key))
        assertEquals(PhysicalKey.Code(KeyCode.KeyA), key.event.physicalKey)
        assertEquals(0x1E, key.event.scancode)
        assertEquals("MouseWheel", classifyDeviceEvent(wheel))
        assertEquals(-1.5, wheel.deltaY)
    }

    @Test
    fun `DeviceEvent Key keeps legacy scancode constructor`() {
        val key = DeviceEvent.Key(0x1E, KeyState.Pressed)

        assertEquals(PhysicalKey.Native(KeyPlatform.Unknown, 0x1E), key.event.physicalKey)
        assertEquals(0x1E, key.scancode)
        assertEquals(KeyState.Pressed, key.state)
        assertEquals(NativeKeyCode.PlatformCode(KeyPlatform.Unknown, 0x1E), key.event.native.nativeCode)
    }

    @Test
    fun `RawKeyEvent scancode is absent when native scan code is absent`() {
        val event = RawKeyEvent(
            physicalKey = PhysicalKey.Unidentified,
            state = KeyState.Pressed,
            native = NativeKeyInfo(),
        )

        assertEquals(null, event.scancode)
    }
}
