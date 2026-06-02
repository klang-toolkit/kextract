/**
 * kadre-core event model.
 *
 * This file defines the two central event hierarchies:
 * - [WindowEvent]: events related to a window (resize, keyboard, pointer, etc.)
 * - [DeviceEvent]: raw device events (absolute motion, button, key)
 *
 * It also defines the support types used by these events:
 * [KeyEvent], [PhysicalKey], [LogicalKey], [KeyState], [KeyboardModifiers],
 * [MouseButton], [PointerSource], [ButtonSource], [TouchPhase] and [ImePurpose].
 *
 * ## Pointer model decision (incubation)
 * Kadre is still incubating, so the window input API intentionally uses a breaking
 * unified pointer model close to winit instead of keeping legacy MouseInput/Touch events.
 *
 * ## Scope
 * All declarations are 100% commonMain (no native dependency).
 * Dispatch to backends remains out of scope for this file.
 *
 * @since 1.0.0
 * @see WindowEvent
 * @see DeviceEvent
 */
package org.graphiks.kadre.core

import kotlin.jvm.JvmInline

// ---------------------------------------------------------------------------
// Support types
// ---------------------------------------------------------------------------

/**
 * State of a key or a button.
 */
enum class KeyState {
    /** The key has just been pressed. */
    Pressed,

    /** The key has just been released. */
    Released,
}

/**
 * Physical keyboard key independent of the active keyboard layout.
 */
sealed interface PhysicalKey {
    data class Code(val code: KeyCode) : PhysicalKey
    data class Native(val platform: KeyPlatform, val code: Long) : PhysicalKey {
        constructor(nativeCode: NativeKeyCode) : this(nativeCode.platform, nativeCode.portableCode())

        val nativeCode: NativeKeyCode get() = NativeKeyCode.PlatformCode(platform, code)
    }
    data object Unidentified : PhysicalKey
}

/**
 * Layout-independent physical key code.
 *
 * Names intentionally follow the DOM / winit vocabulary to make backend
 * mappings straightforward.
 */
enum class KeyCode {
    Backquote,
    Backslash,
    BracketLeft,
    BracketRight,
    Comma,
    Digit0,
    Digit1,
    Digit2,
    Digit3,
    Digit4,
    Digit5,
    Digit6,
    Digit7,
    Digit8,
    Digit9,
    Equal,
    IntlBackslash,
    IntlRo,
    IntlYen,
    KeyA,
    KeyB,
    KeyC,
    KeyD,
    KeyE,
    KeyF,
    KeyG,
    KeyH,
    KeyI,
    KeyJ,
    KeyK,
    KeyL,
    KeyM,
    KeyN,
    KeyO,
    KeyP,
    KeyQ,
    KeyR,
    KeyS,
    KeyT,
    KeyU,
    KeyV,
    KeyW,
    KeyX,
    KeyY,
    KeyZ,
    Minus,
    Period,
    Quote,
    Semicolon,
    Slash,
    AltLeft,
    AltRight,
    Backspace,
    CapsLock,
    ContextMenu,
    ControlLeft,
    ControlRight,
    Enter,
    MetaLeft,
    MetaRight,
    ShiftLeft,
    ShiftRight,
    Space,
    Tab,
    Delete,
    End,
    Help,
    Home,
    Insert,
    PageDown,
    PageUp,
    ArrowDown,
    ArrowLeft,
    ArrowRight,
    ArrowUp,
    NumLock,
    Numpad0,
    Numpad1,
    Numpad2,
    Numpad3,
    Numpad4,
    Numpad5,
    Numpad6,
    Numpad7,
    Numpad8,
    Numpad9,
    NumpadAdd,
    NumpadBackspace,
    NumpadClear,
    NumpadComma,
    NumpadDecimal,
    NumpadDivide,
    NumpadEnter,
    NumpadEqual,
    NumpadMultiply,
    NumpadSubtract,
    Escape,
    Fn,
    FnLock,
    PrintScreen,
    ScrollLock,
    Pause,
    F1,
    F2,
    F3,
    F4,
    F5,
    F6,
    F7,
    F8,
    F9,
    F10,
    F11,
    F12,
    F13,
    F14,
    F15,
    F16,
    F17,
    F18,
    F19,
    F20,
    F21,
    F22,
    F23,
    F24,
    F25,
    F26,
    F27,
    F28,
    F29,
    F30,
    F31,
    F32,
    F33,
    F34,
    F35,
    AudioVolumeDown,
    AudioVolumeMute,
    AudioVolumeUp,
    BrowserBack,
    BrowserFavorites,
    BrowserForward,
    BrowserHome,
    BrowserRefresh,
    BrowserSearch,
    BrowserStop,
    Eject,
    LaunchApp1,
    LaunchApp2,
    LaunchMail,
    MediaPlayPause,
    MediaSelect,
    MediaStop,
    MediaTrackNext,
    MediaTrackPrevious,
    Power,
    Sleep,
    WakeUp,
}

/**
 * Logical key reported by the backend.
 *
 * Backends should use the active keyboard layout when their native event
 * exposes it. When it does not, they may emit a best-effort named/character
 * fallback and keep raw platform details in [NativeKeyInfo].
 */
sealed interface LogicalKey {
    data class Character(val text: String) : LogicalKey
    data class Named(val key: NamedKey) : LogicalKey
    data class Dead(val accent: String?) : LogicalKey
    data class Unidentified(val native: NativeKeyInfo = NativeKeyInfo()) : LogicalKey
}

/**
 * Named logical keys that do not depend on printable text.
 */
enum class NamedKey {
    Alt,
    AltGraph,
    CapsLock,
    Control,
    Fn,
    FnLock,
    Hyper,
    Meta,
    NumLock,
    ScrollLock,
    Shift,
    Super,
    Symbol,
    SymbolLock,
    Enter,
    Tab,
    Space,
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
    F1,
    F2,
    F3,
    F4,
    F5,
    F6,
    F7,
    F8,
    F9,
    F10,
    F11,
    F12,
    F13,
    F14,
    F15,
    F16,
    F17,
    F18,
    F19,
    F20,
    F21,
    F22,
    F23,
    F24,
    F25,
    F26,
    F27,
    F28,
    F29,
    F30,
    F31,
    F32,
    F33,
    F34,
    F35,
    AudioVolumeDown,
    AudioVolumeMute,
    AudioVolumeUp,
    MediaPlay,
    MediaPause,
    MediaPlayPause,
    MediaStop,
    MediaTrackNext,
    MediaTrackPrevious,
    BrowserBack,
    BrowserFavorites,
    BrowserForward,
    BrowserHome,
    BrowserRefresh,
    BrowserSearch,
    BrowserStop,
    LaunchApp1,
    LaunchApp2,
    LaunchMail,
    PrintScreen,
}

/**
 * Logical keyboard modifiers active at the time of an event.
 */
@JvmInline
value class KeyboardModifiers(val bits: Int) {
    val shift: Boolean get() = bits and SHIFT != 0
    val ctrl: Boolean get() = bits and CTRL != 0
    val alt: Boolean get() = bits and ALT != 0
    val meta: Boolean get() = bits and META != 0
    val altGraph: Boolean get() = bits and ALT_GRAPH != 0
    val capsLock: Boolean get() = bits and CAPS_LOCK != 0
    val numLock: Boolean get() = bits and NUM_LOCK != 0
    val symbol: Boolean get() = bits and SYMBOL != 0

    fun contains(other: KeyboardModifiers): Boolean = bits and other.bits == other.bits
    operator fun plus(other: KeyboardModifiers): KeyboardModifiers = KeyboardModifiers(bits or other.bits)
    operator fun minus(other: KeyboardModifiers): KeyboardModifiers = KeyboardModifiers(bits and other.bits.inv())

    companion object {
        const val SHIFT = 1 shl 0
        const val CTRL = 1 shl 1
        const val ALT = 1 shl 2
        const val META = 1 shl 3
        const val ALT_GRAPH = 1 shl 4
        const val CAPS_LOCK = 1 shl 5
        const val NUM_LOCK = 1 shl 6
        const val SYMBOL = 1 shl 7

        val NONE = KeyboardModifiers(0)
        val Shift = KeyboardModifiers(SHIFT)
        val Ctrl = KeyboardModifiers(CTRL)
        val Alt = KeyboardModifiers(ALT)
        val Meta = KeyboardModifiers(META)
        val AltGraph = KeyboardModifiers(ALT_GRAPH)
        val CapsLock = KeyboardModifiers(CAPS_LOCK)
        val NumLock = KeyboardModifiers(NUM_LOCK)
        val Symbol = KeyboardModifiers(SYMBOL)
    }
}

data class ModifierKeys(
    val leftShift: ModifierKeyState = ModifierKeyState.Unknown,
    val rightShift: ModifierKeyState = ModifierKeyState.Unknown,
    val leftCtrl: ModifierKeyState = ModifierKeyState.Unknown,
    val rightCtrl: ModifierKeyState = ModifierKeyState.Unknown,
    val leftAlt: ModifierKeyState = ModifierKeyState.Unknown,
    val rightAlt: ModifierKeyState = ModifierKeyState.Unknown,
    val leftMeta: ModifierKeyState = ModifierKeyState.Unknown,
    val rightMeta: ModifierKeyState = ModifierKeyState.Unknown,
)

enum class ModifierKeyState {
    Pressed,
    Released,
    Unknown,
}

data class KeyboardModifierState(
    val logical: KeyboardModifiers,
    val physical: ModifierKeys = ModifierKeys(),
)

enum class KeyLocation {
    Standard,
    Left,
    Right,
    Numpad,
}

enum class KeyPlatform {
    AppKit,
    UIKit,
    Android,
    Win32,
    X11,
    Wayland,
    Web,
    Unknown,
}

/**
 * Platform-specific physical key identity.
 *
 * This keeps the value typed by source platform instead of reducing every
 * backend to an opaque integer. It mirrors winit's `NativeKeyCode` role while
 * staying idiomatic for Kotlin callers.
 */
sealed interface NativeKeyCode {
    val platform: KeyPlatform

    data class AppKit(val keyCode: Long) : NativeKeyCode {
        override val platform: KeyPlatform = KeyPlatform.AppKit
    }

    data class UIKit(val hidUsage: Long) : NativeKeyCode {
        override val platform: KeyPlatform = KeyPlatform.UIKit
    }

    data class Android(val scanCode: Long?, val keyCode: Long) : NativeKeyCode {
        override val platform: KeyPlatform = KeyPlatform.Android
    }

    data class Win32(val scanCode: Long?, val virtualKey: Long) : NativeKeyCode {
        override val platform: KeyPlatform = KeyPlatform.Win32
    }

    data class X11(val keycode: Long) : NativeKeyCode {
        override val platform: KeyPlatform = KeyPlatform.X11
    }

    data class Wayland(val evdevCode: Long) : NativeKeyCode {
        override val platform: KeyPlatform = KeyPlatform.Wayland
    }

    data class Web(val code: String) : NativeKeyCode {
        override val platform: KeyPlatform = KeyPlatform.Web
    }

    data class PlatformCode(
        override val platform: KeyPlatform,
        val code: Long,
    ) : NativeKeyCode
}

private fun NativeKeyCode.portableCode(): Long = when (this) {
    is NativeKeyCode.AppKit -> keyCode
    is NativeKeyCode.UIKit -> hidUsage
    is NativeKeyCode.Android -> keyCode
    is NativeKeyCode.Win32 -> virtualKey
    is NativeKeyCode.X11 -> keycode
    is NativeKeyCode.Wayland -> evdevCode
    is NativeKeyCode.Web -> code.hashCode().toLong()
    is NativeKeyCode.PlatformCode -> code
}

/**
 * Platform-specific logical key identity, used when the backend cannot map the
 * key to a portable [LogicalKey.Character], [LogicalKey.Named], or [LogicalKey.Dead].
 */
sealed interface NativeLogicalKey {
    val platform: KeyPlatform

    data class AppKit(val characters: String?, val charactersIgnoringModifiers: String? = null) : NativeLogicalKey {
        override val platform: KeyPlatform = KeyPlatform.AppKit
    }

    data class UIKit(val keyCode: Long, val characters: String?) : NativeLogicalKey {
        override val platform: KeyPlatform = KeyPlatform.UIKit
    }

    data class Android(val keyCode: Long, val displayLabel: String? = null) : NativeLogicalKey {
        override val platform: KeyPlatform = KeyPlatform.Android
    }

    data class Win32(val virtualKey: Long) : NativeLogicalKey {
        override val platform: KeyPlatform = KeyPlatform.Win32
    }

    data class X11(val keysym: Long) : NativeLogicalKey {
        override val platform: KeyPlatform = KeyPlatform.X11
    }

    data class Wayland(val keysym: Long?) : NativeLogicalKey {
        override val platform: KeyPlatform = KeyPlatform.Wayland
    }

    data class Web(val key: String) : NativeLogicalKey {
        override val platform: KeyPlatform = KeyPlatform.Web
    }

    data class PlatformValue(
        override val platform: KeyPlatform,
        val value: String,
    ) : NativeLogicalKey
}

data class NativeKeyInfo(
    val platform: KeyPlatform = KeyPlatform.Unknown,
    val scanCode: Long? = null,
    val virtualKey: Long? = null,
    val keyCode: String? = null,
    val keyValue: String? = null,
    val nativeCode: NativeKeyCode? = null,
    val nativeKey: NativeLogicalKey? = null,
)

data class KeyEvent(
    val physicalKey: PhysicalKey,
    val logicalKey: LogicalKey,
    val state: KeyState,
    val modifiers: KeyboardModifiers,
    val location: KeyLocation = KeyLocation.Standard,
    val repeat: Boolean = false,
    val synthetic: Boolean = false,
    val text: String? = null,
    val textWithAllModifiers: String? = null,
    val keyWithoutModifiers: LogicalKey? = null,
    val native: NativeKeyInfo = NativeKeyInfo(),
) {
    val isPressed: Boolean get() = state == KeyState.Pressed
    val isReleased: Boolean get() = state == KeyState.Released
    val character: String? get() = (logicalKey as? LogicalKey.Character)?.text
    val shortcutKey: LogicalKey get() = keyWithoutModifiers ?: logicalKey
    val effectiveText: String? get() = textWithAllModifiers ?: text
}

data class RawKeyEvent(
    val physicalKey: PhysicalKey,
    val state: KeyState,
    val native: NativeKeyInfo = NativeKeyInfo(),
) {
    val scancode: Int? get() = native.scanCode?.toInt()
}

enum class KeyChordModifierMatch {
    /**
     * Required modifiers must be present; additional modifiers are accepted.
     */
    Contains,

    /**
     * The event modifiers must exactly match [KeyChord.modifiers].
     */
    Exact,
}

data class KeyChord(
    val physicalKey: PhysicalKey? = null,
    val logicalKey: LogicalKey? = null,
    val modifiers: KeyboardModifiers = KeyboardModifiers.NONE,
    val allowRepeat: Boolean = false,
    val modifierMatch: KeyChordModifierMatch = KeyChordModifierMatch.Contains,
) {
    init {
        require(physicalKey != null || logicalKey != null) {
            "KeyChord requires either physicalKey or logicalKey"
        }
    }

    fun matches(event: KeyEvent): Boolean {
        if (!event.isPressed) return false
        if (!allowRepeat && event.repeat) return false
        val modifiersMatch = when (modifierMatch) {
            KeyChordModifierMatch.Contains -> event.modifiers.contains(modifiers)
            KeyChordModifierMatch.Exact -> event.modifiers == modifiers
        }
        if (!modifiersMatch) return false
        if (physicalKey != null) return event.physicalKey == physicalKey
        return event.shortcutKey == logicalKey
    }
}

fun PhysicalKey.location(): KeyLocation = when (this) {
    is PhysicalKey.Code -> code.location()
    is PhysicalKey.Native,
    PhysicalKey.Unidentified -> KeyLocation.Standard
}

fun KeyCode.location(): KeyLocation = when (this) {
    KeyCode.AltLeft,
    KeyCode.ControlLeft,
    KeyCode.MetaLeft,
    KeyCode.ShiftLeft -> KeyLocation.Left

    KeyCode.AltRight,
    KeyCode.ControlRight,
    KeyCode.MetaRight,
    KeyCode.ShiftRight -> KeyLocation.Right

    KeyCode.NumLock,
    KeyCode.Numpad0,
    KeyCode.Numpad1,
    KeyCode.Numpad2,
    KeyCode.Numpad3,
    KeyCode.Numpad4,
    KeyCode.Numpad5,
    KeyCode.Numpad6,
    KeyCode.Numpad7,
    KeyCode.Numpad8,
    KeyCode.Numpad9,
    KeyCode.NumpadAdd,
    KeyCode.NumpadBackspace,
    KeyCode.NumpadClear,
    KeyCode.NumpadComma,
    KeyCode.NumpadDecimal,
    KeyCode.NumpadDivide,
    KeyCode.NumpadEnter,
    KeyCode.NumpadEqual,
    KeyCode.NumpadMultiply,
    KeyCode.NumpadSubtract -> KeyLocation.Numpad

    else -> KeyLocation.Standard
}

/**
 * Best-effort logical key for a physical key when the backend cannot provide
 * layout-aware text. Backends should prefer real platform text when available.
 */
fun KeyCode.defaultLogicalKey(): LogicalKey = when (this) {
    KeyCode.KeyA -> LogicalKey.Character("a")
    KeyCode.KeyB -> LogicalKey.Character("b")
    KeyCode.KeyC -> LogicalKey.Character("c")
    KeyCode.KeyD -> LogicalKey.Character("d")
    KeyCode.KeyE -> LogicalKey.Character("e")
    KeyCode.KeyF -> LogicalKey.Character("f")
    KeyCode.KeyG -> LogicalKey.Character("g")
    KeyCode.KeyH -> LogicalKey.Character("h")
    KeyCode.KeyI -> LogicalKey.Character("i")
    KeyCode.KeyJ -> LogicalKey.Character("j")
    KeyCode.KeyK -> LogicalKey.Character("k")
    KeyCode.KeyL -> LogicalKey.Character("l")
    KeyCode.KeyM -> LogicalKey.Character("m")
    KeyCode.KeyN -> LogicalKey.Character("n")
    KeyCode.KeyO -> LogicalKey.Character("o")
    KeyCode.KeyP -> LogicalKey.Character("p")
    KeyCode.KeyQ -> LogicalKey.Character("q")
    KeyCode.KeyR -> LogicalKey.Character("r")
    KeyCode.KeyS -> LogicalKey.Character("s")
    KeyCode.KeyT -> LogicalKey.Character("t")
    KeyCode.KeyU -> LogicalKey.Character("u")
    KeyCode.KeyV -> LogicalKey.Character("v")
    KeyCode.KeyW -> LogicalKey.Character("w")
    KeyCode.KeyX -> LogicalKey.Character("x")
    KeyCode.KeyY -> LogicalKey.Character("y")
    KeyCode.KeyZ -> LogicalKey.Character("z")
    KeyCode.Digit0 -> LogicalKey.Character("0")
    KeyCode.Digit1 -> LogicalKey.Character("1")
    KeyCode.Digit2 -> LogicalKey.Character("2")
    KeyCode.Digit3 -> LogicalKey.Character("3")
    KeyCode.Digit4 -> LogicalKey.Character("4")
    KeyCode.Digit5 -> LogicalKey.Character("5")
    KeyCode.Digit6 -> LogicalKey.Character("6")
    KeyCode.Digit7 -> LogicalKey.Character("7")
    KeyCode.Digit8 -> LogicalKey.Character("8")
    KeyCode.Digit9 -> LogicalKey.Character("9")
    KeyCode.ArrowDown -> LogicalKey.Named(NamedKey.ArrowDown)
    KeyCode.ArrowLeft -> LogicalKey.Named(NamedKey.ArrowLeft)
    KeyCode.ArrowRight -> LogicalKey.Named(NamedKey.ArrowRight)
    KeyCode.ArrowUp -> LogicalKey.Named(NamedKey.ArrowUp)
    KeyCode.Backspace -> LogicalKey.Named(NamedKey.Backspace)
    KeyCode.Delete -> LogicalKey.Named(NamedKey.Delete)
    KeyCode.End -> LogicalKey.Named(NamedKey.End)
    KeyCode.Enter, KeyCode.NumpadEnter -> LogicalKey.Named(NamedKey.Enter)
    KeyCode.Escape -> LogicalKey.Named(NamedKey.Escape)
    KeyCode.F1 -> LogicalKey.Named(NamedKey.F1)
    KeyCode.F2 -> LogicalKey.Named(NamedKey.F2)
    KeyCode.F3 -> LogicalKey.Named(NamedKey.F3)
    KeyCode.F4 -> LogicalKey.Named(NamedKey.F4)
    KeyCode.F5 -> LogicalKey.Named(NamedKey.F5)
    KeyCode.F6 -> LogicalKey.Named(NamedKey.F6)
    KeyCode.F7 -> LogicalKey.Named(NamedKey.F7)
    KeyCode.F8 -> LogicalKey.Named(NamedKey.F8)
    KeyCode.F9 -> LogicalKey.Named(NamedKey.F9)
    KeyCode.F10 -> LogicalKey.Named(NamedKey.F10)
    KeyCode.F11 -> LogicalKey.Named(NamedKey.F11)
    KeyCode.F12 -> LogicalKey.Named(NamedKey.F12)
    KeyCode.F13 -> LogicalKey.Named(NamedKey.F13)
    KeyCode.F14 -> LogicalKey.Named(NamedKey.F14)
    KeyCode.F15 -> LogicalKey.Named(NamedKey.F15)
    KeyCode.F16 -> LogicalKey.Named(NamedKey.F16)
    KeyCode.F17 -> LogicalKey.Named(NamedKey.F17)
    KeyCode.F18 -> LogicalKey.Named(NamedKey.F18)
    KeyCode.F19 -> LogicalKey.Named(NamedKey.F19)
    KeyCode.F20 -> LogicalKey.Named(NamedKey.F20)
    KeyCode.F21 -> LogicalKey.Named(NamedKey.F21)
    KeyCode.F22 -> LogicalKey.Named(NamedKey.F22)
    KeyCode.F23 -> LogicalKey.Named(NamedKey.F23)
    KeyCode.F24 -> LogicalKey.Named(NamedKey.F24)
    KeyCode.F25 -> LogicalKey.Named(NamedKey.F25)
    KeyCode.F26 -> LogicalKey.Named(NamedKey.F26)
    KeyCode.F27 -> LogicalKey.Named(NamedKey.F27)
    KeyCode.F28 -> LogicalKey.Named(NamedKey.F28)
    KeyCode.F29 -> LogicalKey.Named(NamedKey.F29)
    KeyCode.F30 -> LogicalKey.Named(NamedKey.F30)
    KeyCode.F31 -> LogicalKey.Named(NamedKey.F31)
    KeyCode.F32 -> LogicalKey.Named(NamedKey.F32)
    KeyCode.F33 -> LogicalKey.Named(NamedKey.F33)
    KeyCode.F34 -> LogicalKey.Named(NamedKey.F34)
    KeyCode.F35 -> LogicalKey.Named(NamedKey.F35)
    KeyCode.Home -> LogicalKey.Named(NamedKey.Home)
    KeyCode.Insert -> LogicalKey.Named(NamedKey.Insert)
    KeyCode.PageDown -> LogicalKey.Named(NamedKey.PageDown)
    KeyCode.PageUp -> LogicalKey.Named(NamedKey.PageUp)
    KeyCode.Space -> LogicalKey.Named(NamedKey.Space)
    KeyCode.Tab -> LogicalKey.Named(NamedKey.Tab)
    KeyCode.AltLeft, KeyCode.AltRight -> LogicalKey.Named(NamedKey.Alt)
    KeyCode.CapsLock -> LogicalKey.Named(NamedKey.CapsLock)
    KeyCode.ControlLeft, KeyCode.ControlRight -> LogicalKey.Named(NamedKey.Control)
    KeyCode.MetaLeft, KeyCode.MetaRight -> LogicalKey.Named(NamedKey.Meta)
    KeyCode.NumLock -> LogicalKey.Named(NamedKey.NumLock)
    KeyCode.ShiftLeft, KeyCode.ShiftRight -> LogicalKey.Named(NamedKey.Shift)
    else -> LogicalKey.Unidentified(NativeKeyInfo(keyCode = name))
}

fun KeyCode.defaultText(): String? = (defaultLogicalKey() as? LogicalKey.Character)?.text

/**
 * Mouse button.
 *
 * The three main buttons have named objects; additional buttons
 * are represented by [Other].
 */
sealed interface MouseButton {
    /** Left button (primary button). */
    data object Left : MouseButton

    /** Right button (secondary button / context menu). */
    data object Right : MouseButton

    /** Middle button (wheel or center button). */
    data object Middle : MouseButton

    /**
     * Additional button identified by its numeric index.
     *
     * @property button Button index (platform-specific, starts at 4).
     */
    data class Other(val button: Int) : MouseButton
}


/**
 * Identifier of a touch contact for the lifetime of the interaction.
 */
@JvmInline
value class FingerId(val value: Long)

/**
 * Coarse pointer source type.
 */
enum class PointerKind {
    Mouse,
    Touch,
    TabletTool,
    Unknown,
}

/**
 * Tablet/stylus tool kind.
 */
enum class TabletToolKind {
    Pen,
    Eraser,
    Cursor,
    Unknown,
}

/**
 * Tablet/stylus button kind.
 */
enum class TabletToolButton {
    Tip,
    Barrel,
    SecondaryBarrel,
    Eraser,
    Unknown,
}

/**
 * Optional tablet tool data exposed by backends that support it.
 */
data class TabletToolData(
    val pressure: Float? = null,
    val tiltX: Float? = null,
    val tiltY: Float? = null,
    val twistDegrees: Float? = null,
)

/**
 * Touch pressure/force value.
 */
sealed interface TouchForce {
    data class Calibrated(val force: Double, val maxPossibleForce: Double) : TouchForce
    data class Normalized(val value: Double) : TouchForce
}

/**
 * Source of a pointer motion event.
 */
sealed interface PointerSource {
    data object Mouse : PointerSource
    data class Touch(val fingerId: FingerId, val force: TouchForce? = null) : PointerSource
    data class TabletTool(
        val kind: TabletToolKind,
        val data: TabletToolData = TabletToolData(),
    ) : PointerSource
    data object Unknown : PointerSource
}

/**
 * Source of a pointer button event.
 */
sealed interface ButtonSource {
    data class Mouse(val button: MouseButton) : ButtonSource
    data class Touch(val fingerId: FingerId, val force: TouchForce? = null) : ButtonSource
    data class TabletTool(
        val kind: TabletToolKind,
        val button: TabletToolButton,
        val data: TabletToolData = TabletToolData(),
    ) : ButtonSource
    data class Unknown(val code: Int) : ButtonSource
}

/**
 * Phase of a touch contact.
 */
enum class TouchPhase {
    /** The contact has just been placed on the screen. */
    Started,

    /** The contact has moved on the screen. */
    Moved,

    /** The contact has been removed from the screen. */
    Ended,

    /** The contact has been cancelled (e.g. incoming call, system gesture). */
    Cancelled,
}

/**
 * Intended purpose of the IME text field currently focused.
 *
 * Passed to [Window.setImePurpose] so that the platform input method can
 * adapt its behaviour (e.g. hide suggestions for a terminal, mask characters
 * for a password field).
 *
 * @since R5-IME
 */
enum class ImePurpose {
    /** General text input — the default. */
    Normal,

    /** Password field — the IME should hide the composed text. */
    Password,

    /** Terminal / command input — suggestions and auto-correct should be suppressed. */
    Terminal,
}

// ---------------------------------------------------------------------------
// WindowEvent
// ---------------------------------------------------------------------------

/**
 * Event emitted by a window.
 *
 * Each variant corresponds to a state change or a user action
 * on the targeted window.
 *
 * ### Typical usage
 * ```kotlin
 * fun onWindowEvent(event: WindowEvent) {
 *     when (event) {
 *         WindowEvent.CloseRequested    -> quit()
 *         is WindowEvent.Resized        -> resize(event.size)
 *         is WindowEvent.Moved          -> move(event.position)
 *         is WindowEvent.ScaleFactorChanged -> updateDpi(event.factor)
 *         is WindowEvent.Focused        -> handleFocus(event.gained)
 *         is WindowEvent.KeyInput       -> handleKeyboard(event.event)
 *         is WindowEvent.PointerMoved   -> handlePointer(event.position, event.source)
 *         is WindowEvent.PointerEntered -> handleEnter(event.position, event.kind)
 *         is WindowEvent.PointerLeft    -> handleLeave(event.position, event.kind)
 *         is WindowEvent.PointerButton  -> handlePointerButton(event.button, event.state)
 *         is WindowEvent.MouseWheel     -> handleWheel(event.deltaX, event.deltaY)
 *         WindowEvent.RedrawRequested   -> redraw()
 *         WindowEvent.Destroyed         -> releaseResources()
 *         is WindowEvent.ThemeChanged   -> applyTheme(event.theme)
 *         is WindowEvent.ModifiersChanged -> updateModifiers(event.modifiers)
 *         is WindowEvent.Ime            -> handleIme(event.ime)
 *     }
 * }
 * ```
 */
sealed interface WindowEvent {

    /**
     * The user requested closing the window (× button, Alt+F4, ⌘W, etc.).
     *
     * The application remains free to ignore or defer the close.
     */
    data object CloseRequested : WindowEvent

    /**
     * The window has been resized.
     *
     * @property size New size in physical pixels.
     */
    data class Resized(val size: PhysicalSize<Int>) : WindowEvent

    /**
     * The window has been moved.
     *
     * @property position New position of the top-left corner in physical pixels.
     */
    data class Moved(val position: PhysicalPosition<Int>) : WindowEvent

    /**
     * The window's DPI scale factor changed (e.g. moved to another monitor).
     *
     * @property factor New scale factor (e.g. `2.0` on a Retina screen).
     */
    data class ScaleFactorChanged(val factor: Double) : WindowEvent

    /**
     * The window gained or lost keyboard focus.
     *
     * @property gained `true` if the window just gained focus, `false` if it lost it.
     */
    data class Focused(val gained: Boolean) : WindowEvent

    /**
     * A keyboard event occurred while the window had focus.
     */
    data class KeyInput(
        val event: KeyEvent,
        val deviceId: DeviceId? = null,
    ) : WindowEvent

    /**
     * The pointer moved over the window.
     *
     * @property position Current pointer position in physical pixels (floating point
     *   for the sub-pixel precision of tablets and trackpads).
     */
    data class PointerMoved(
        val deviceId: DeviceId?,
        val position: PhysicalPosition<Double>,
        val primary: Boolean,
        val source: PointerSource,
    ) : WindowEvent

    /**
     * The pointer just entered the window's client area.
     */
    data class PointerEntered(
        val deviceId: DeviceId?,
        val position: PhysicalPosition<Double>,
        val primary: Boolean,
        val kind: PointerKind,
    ) : WindowEvent

    /**
     * The pointer just left the window's client area.
     */
    data class PointerLeft(
        val deviceId: DeviceId?,
        val position: PhysicalPosition<Double>?,
        val primary: Boolean,
        val kind: PointerKind,
    ) : WindowEvent

    /**
     * A pointer button has been pressed or released.
     *
     * @property button Pointer button source.
     * @property state  Button state ([KeyState.Pressed] or [KeyState.Released]).
     */
    data class PointerButton(
        val deviceId: DeviceId?,
        val state: KeyState,
        val position: PhysicalPosition<Double>,
        val primary: Boolean,
        val button: ButtonSource,
    ) : WindowEvent

    /**
     * The mouse wheel (or trackpad) produced a scroll.
     *
     * @property deltaX Horizontal scroll (positive towards the right).
     * @property deltaY Vertical scroll (positive towards the bottom).
     */
    data class MouseWheel(
        val deviceId: DeviceId?,
        val deltaX: Double,
        val deltaY: Double,
        val phase: TouchPhase,
    ) : WindowEvent

    /**
     * The logical or physical keyboard modifier state changed.
     */
    data class ModifiersChanged(val state: KeyboardModifierState) : WindowEvent

    /**
     * The window must be redrawn.
     *
     * Emitted by the platform (vsync, region invalidation, etc.).
     */
    data object RedrawRequested : WindowEvent

    /**
     * The window has been destroyed and its native resources released.
     *
     * No further event will be emitted for this window after [Destroyed].
     */
    data object Destroyed : WindowEvent


    // ── R3: theme ────────────────────────────────────────────────────────────

    /**
     * The system UI theme changed (light ↔ dark).
     *
     * Emitted by backends that support theme-change notifications
     * (AppKit, Win32). Not emitted on X11, Wayland, Android (where
     * [ActiveEventLoop.systemTheme] should be polled) or Web (use
     * `matchMedia('prefers-color-scheme')` via the bridge).
     *
     * @property theme New active theme.
     */
    data class ThemeChanged(val theme: Theme) : WindowEvent

    // ── R5-DnD: drag & drop ──────────────────────────────────────────────────

    /**
     * A drag operation entered the window, carrying files at the given position.
     *
     * Emitted when the user drags files over the window client area.
     * Emission requires backend wiring — TODO per backend (AppKit NSDraggingDestination,
     * Win32 IDropTarget, X11 XDND, Wayland wl_data_device, Web dragenter, UIKit UIDropInteraction).
     *
     * @property position Current drag position in physical pixels.
     * @property paths    List of file paths (or file names on Web where full paths are unavailable).
     */
    data class DragEntered(val position: PhysicalPosition<Double>, val paths: List<String>) : WindowEvent

    /**
     * The drag cursor moved within the window while carrying files.
     *
     * Emitted continuously as the user moves the drag cursor over the window.
     * Default emission: no-op — TODO per backend.
     *
     * @property position Current drag position in physical pixels.
     */
    data class DragMoved(val position: PhysicalPosition<Double>) : WindowEvent

    /**
     * Files were dropped onto the window.
     *
     * Emitted when the user releases the drag within the window client area.
     * Default emission: no-op — TODO per backend.
     *
     * @property position Drop position in physical pixels.
     * @property paths    List of dropped file paths (or file names on Web).
     */
    data class DragDropped(val position: PhysicalPosition<Double>, val paths: List<String>) : WindowEvent

    /**
     * The drag cursor left the window without dropping.
     *
     * Emitted when the user moves the drag out of the window client area.
     * Default emission: no-op — TODO per backend.
     */
    data object DragLeft : WindowEvent

    // ── R5-Gestures: trackpad & touchscreen gestures ──────────────────────────

    /**
     * Two-finger pinch gesture, usually used for magnification.
     */
    data class PinchGesture(
        val deviceId: DeviceId?,
        val delta: Double,
        val phase: TouchPhase,
    ) : WindowEvent

    /**
     * N-finger pan gesture.
     */
    data class PanGesture(
        val deviceId: DeviceId?,
        val delta: PhysicalPosition<Float>,
        val phase: TouchPhase,
    ) : WindowEvent

    /**
     * Two-finger rotation gesture. Delta is in degrees.
     */
    data class RotationGesture(
        val deviceId: DeviceId?,
        val deltaDegrees: Float,
        val phase: TouchPhase,
    ) : WindowEvent

    /**
     * Double-tap gesture.
     */
    data class DoubleTapGesture(val deviceId: DeviceId?) : WindowEvent

    /**
     * Trackpad pressure event.
     */
    data class TouchpadPressure(
        val deviceId: DeviceId?,
        val pressure: Float,
        val stage: Long,
    ) : WindowEvent

    // ── R5-MiscWindow: occluded ───────────────────────────────────────────────

    /**
     * The window's occlusion state changed.
     *
     * Emitted when the window becomes hidden behind other windows ([occluded] = true)
     * or becomes visible again ([occluded] = false).
     *
     * Platform support:
     * - AppKit: `NSWindowDidChangeOcclusionStateNotification` — TODO.
     * - Web: Page Visibility API (`visibilitychange`) — TODO.
     * - Win32 / X11 / Wayland / Android / UIKit: no-op documented.
     *
     * @property occluded `true` if the window is now occluded, `false` if it is visible.
     */
    data class Occluded(val occluded: Boolean) : WindowEvent

    // ── R5-IME: input method ──────────────────────────────────────────────────

    /**
     * An IME (Input Method Editor) event occurred on this window.
     *
     * IME events are emitted on platforms that expose an input method pipeline
     * (Wayland via `zwp_text_input_v3`, X11 via XIC, Win32 via TSF/IMM32,
     * Android via `InputMethodManager`, iOS/macOS via `NSTextInputClient`).
     *
     * Emission of these events is out of scope for R5-IME — backends will be
     * wired in later milestones. Use [Window.setImeAllowed] to opt in.
     *
     * @property ime The concrete IME event sub-type.
     * @see ImeEvent
     */
    data class Ime(val ime: ImeEvent) : WindowEvent {

        /**
         * Sub-events of the IME pipeline.
         *
         * The typical lifecycle is:
         * 1. [Enabled]  — the IME context was activated (e.g. focus entered a text field).
         * 2. [Preedit]  — intermediate composed text (shown with underline in most UIs).
         * 3. [Commit]   — the final string to insert into the text buffer.
         * 4. [Disabled] — the IME context was deactivated.
         *
         * [DeleteSurrounding] may be emitted at any point to request deletion of text
         * around the cursor (needed by some CJK / prediction engines).
         */
        sealed interface ImeEvent {

            /**
             * The IME context was activated for this window.
             *
             * From this point on [Preedit], [Commit] and [DeleteSurrounding] events
             * may be emitted.
             */
            data object Enabled : ImeEvent

            /**
             * The IME is composing text (pre-edit string).
             *
             * The application should display [text] with a visual indicator (underline,
             * highlight) at the current cursor position. When [text] is empty the
             * pre-edit string is cleared.
             *
             * @property text         The current pre-edit string (may be empty).
             * @property cursorRange  Byte range `[start, end)` within [text] where the
             *   IME cursor / selection sits, or null if the IME does not expose it.
             */
            data class Preedit(val text: String, val cursorRange: Pair<Int, Int>?) : ImeEvent

            /**
             * The IME committed a final string.
             *
             * The application should insert [text] into its text buffer at the cursor
             * position, replacing any active pre-edit string.
             *
             * @property text The committed text to insert.
             */
            data class Commit(val text: String) : ImeEvent

            /**
             * The IME requests deletion of surrounding text.
             *
             * The application should delete [beforeBytes] bytes before the cursor and
             * [afterBytes] bytes after the cursor (byte offsets in UTF-8).
             *
             * @property beforeBytes Bytes to delete before the cursor (≥ 0).
             * @property afterBytes  Bytes to delete after the cursor (≥ 0).
             */
            data class DeleteSurrounding(val beforeBytes: Int, val afterBytes: Int) : ImeEvent

            /**
             * The IME context was deactivated for this window.
             *
             * No further [Preedit] or [Commit] events will be emitted until the next
             * [Enabled] event.
             */
            data object Disabled : ImeEvent
        }
    }
}

// ---------------------------------------------------------------------------
// DeviceEvent
// ---------------------------------------------------------------------------

/**
 * Raw input device event.
 *
 * Unlike [WindowEvent], these events are emitted independently of the active
 * window and reflect the raw state of the device.
 *
 * ### Typical usage
 * ```kotlin
 * fun onDeviceEvent(event: DeviceEvent) {
 *     when (event) {
 *         is DeviceEvent.PointerMotion -> handleMotion(event.dx, event.dy)
 *         is DeviceEvent.Button        -> handleButton(event.button, event.state)
 *         is DeviceEvent.Key           -> handleKey(event.event.physicalKey, event.state)
 *         is DeviceEvent.MouseWheel    -> handleWheel(event.deltaX, event.deltaY)
 *     }
 * }
 * ```
 */
sealed interface DeviceEvent {

    /**
     * Raw pointer motion (delta, not limited to the screen edges).
     *
     * @property dx Horizontal displacement in raw pixels.
     * @property dy Vertical displacement in raw pixels.
     */
    data class PointerMotion(val dx: Double, val dy: Double) : DeviceEvent

    /**
     * A physical device button changed state.
     *
     * @property button Button index (platform-specific).
     * @property state  Button state ([KeyState.Pressed] or [KeyState.Released]).
     */
    data class Button(val button: Int, val state: KeyState) : DeviceEvent

    /**
     * A physical keyboard key changed state before layout processing.
     */
    data class Key(val event: RawKeyEvent) : DeviceEvent {
        constructor(scancode: Int, state: KeyState) : this(
            RawKeyEvent(
                physicalKey = PhysicalKey.Native(NativeKeyCode.PlatformCode(KeyPlatform.Unknown, scancode.toLong())),
                state = state,
                native = NativeKeyInfo(
                    scanCode = scancode.toLong(),
                    nativeCode = NativeKeyCode.PlatformCode(KeyPlatform.Unknown, scancode.toLong()),
                ),
            ),
        )

        val scancode: Int? get() = event.scancode
        val state: KeyState get() = event.state
    }

    // ── R4 ────────────────────────────────────────────────────────────────────

    /**
     * The mouse wheel (or trackpad) scrolled — raw device event, not clipped to a window.
     *
     * Emitted alongside [WindowEvent.MouseWheel] when the device-events filter allows it.
     * See [ActiveEventLoop.listenDeviceEvents].
     *
     * @property deltaX Horizontal scroll delta (positive towards the right).
     * @property deltaY Vertical scroll delta (positive towards the bottom).
     */
    data class MouseWheel(val deltaX: Double, val deltaY: Double) : DeviceEvent
}
