/**
 * kadre-core event model.
 *
 * This file defines the two central event hierarchies:
 * - [WindowEvent]: events related to a window (resize, keyboard, pointer, etc.)
 * - [DeviceEvent]: raw device events (absolute motion, button, key)
 *
 * It also defines the support types used by these events:
 * [Key], [KeyState], [KeyLocation], [Modifiers], [MouseButton], [PointerSource],
 * [ButtonSource], [TouchPhase] and [ImePurpose].
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
 * Logical keyboard keys.
 *
 * Covers the letters A–Z, the digits Digit0–Digit9, the function keys
 * F1–F12, the navigation and modifier keys, as well as a fallback value
 * [Unknown] for unrecognized keys.
 */
enum class Key {
    // Letters
    /** "A" key. */ A,
    /** "B" key. */ B,
    /** "C" key. */ C,
    /** "D" key. */ D,
    /** "E" key. */ E,
    /** "F" key. */ F,
    /** "G" key. */ G,
    /** "H" key. */ H,
    /** "I" key. */ I,
    /** "J" key. */ J,
    /** "K" key. */ K,
    /** "L" key. */ L,
    /** "M" key. */ M,
    /** "N" key. */ N,
    /** "O" key. */ O,
    /** "P" key. */ P,
    /** "Q" key. */ Q,
    /** "R" key. */ R,
    /** "S" key. */ S,
    /** "T" key. */ T,
    /** "U" key. */ U,
    /** "V" key. */ V,
    /** "W" key. */ W,
    /** "X" key. */ X,
    /** "Y" key. */ Y,
    /** "Z" key. */ Z,

    // Digits
    /** Digit "0" key. */ Digit0,
    /** Digit "1" key. */ Digit1,
    /** Digit "2" key. */ Digit2,
    /** Digit "3" key. */ Digit3,
    /** Digit "4" key. */ Digit4,
    /** Digit "5" key. */ Digit5,
    /** Digit "6" key. */ Digit6,
    /** Digit "7" key. */ Digit7,
    /** Digit "8" key. */ Digit8,
    /** Digit "9" key. */ Digit9,

    // Function keys
    /** F1 function key. */ F1,
    /** F2 function key. */ F2,
    /** F3 function key. */ F3,
    /** F4 function key. */ F4,
    /** F5 function key. */ F5,
    /** F6 function key. */ F6,
    /** F7 function key. */ F7,
    /** F8 function key. */ F8,
    /** F9 function key. */ F9,
    /** F10 function key. */ F10,
    /** F11 function key. */ F11,
    /** F12 function key. */ F12,

    // Special keys
    /** Space bar. */ Space,
    /** Enter key. */ Enter,
    /** Escape key. */ Escape,
    /** Backspace key. */ Backspace,
    /** Tab key. */ Tab,

    // Navigation keys
    /** Up arrow. */ ArrowUp,
    /** Down arrow. */ ArrowDown,
    /** Left arrow. */ ArrowLeft,
    /** Right arrow. */ ArrowRight,

    // Modifiers
    /** Left Shift. */ ShiftLeft,
    /** Right Shift. */ ShiftRight,
    /** Left Control. */ ControlLeft,
    /** Right Control. */ ControlRight,
    /** Left Alt. */ AltLeft,
    /** Right Alt (AltGr). */ AltRight,
    /** Left Meta/Command (⌘/Win). */ MetaLeft,
    /** Right Meta/Command (⌘/Win). */ MetaRight,

    /** Key not recognized by the platform. */ Unknown,
}

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
 * Set of keyboard modifiers active at the time of an event.
 *
 * Implemented as a bit integer to minimize allocations.
 * Use the constants from the [companion object][Modifiers.Companion] to
 * build values, and the [plus] operator to combine them.
 *
 * ```kotlin
 * val mods = Modifiers.SHIFT + Modifiers.CTRL
 * assert(mods.contains(Modifiers.SHIFT))
 * assert(mods.shift)
 * assert(mods.ctrl)
 * ```
 *
 * @property bits Internal representation as a bit field.
 */
@JvmInline
value class Modifiers(val bits: Int) {

    /** `true` if the Shift key is pressed. */
    val shift: Boolean get() = bits and 0x1 != 0

    /** `true` if the Control key is pressed. */
    val ctrl: Boolean get() = bits and 0x2 != 0

    /** `true` if the Alt key is pressed. */
    val alt: Boolean get() = bits and 0x4 != 0

    /** `true` if the Meta key (⌘ / Win) is pressed. */
    val meta: Boolean get() = bits and 0x8 != 0

    /**
     * Combines these modifiers with [other].
     *
     * @return New set containing the modifiers of both operands.
     */
    operator fun plus(other: Modifiers): Modifiers = Modifiers(bits or other.bits)

    /**
     * Checks whether this set contains all the modifiers of [other].
     *
     * @return `true` if every bit of [other] is present in this set.
     */
    fun contains(other: Modifiers): Boolean = bits and other.bits == other.bits

    companion object {
        /** No active modifier. */
        val NONE = Modifiers(0x0)

        /** Only the Shift modifier is active. */
        val SHIFT = Modifiers(0x1)

        /** Only the Control modifier is active. */
        val CTRL = Modifiers(0x2)

        /** Only the Alt modifier is active. */
        val ALT = Modifiers(0x4)

        /** Only the Meta modifier is active. */
        val META = Modifiers(0x8)
    }
}

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
 * Physical location of a keyboard key.
 *
 * Distinguishes between keys that appear in multiple locations on the keyboard
 * (e.g. left vs. right Shift, numpad digits vs. top-row digits).
 *
 * @since R4
 */
enum class KeyLocation {
    /** Key appears only once or its position is the standard one. */
    Standard,

    /** Left-side instance of a key (e.g. left Shift, left Ctrl). */
    Left,

    /** Right-side instance of a key (e.g. right Shift, right Alt/AltGr). */
    Right,

    /** Key is on the numeric keypad. */
    Numpad,
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
 *         is WindowEvent.KeyboardInput  -> handleKeyboard(event.key, event.state, event.modifiers)
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
     *
     * @property key       Logical key involved (layout-dependent).
     * @property state     Key state ([KeyState.Pressed] or [KeyState.Released]).
     * @property modifiers Modifiers active at the time of the event.
     * @property isRepeat  `true` if the key is being held and this is an auto-repeat.
     * @property text      Unicode character(s) produced by this key press, or null if the
     *   key does not produce printable text (e.g. function keys, modifiers, arrows).
     *   Populated on a best-effort basis per backend — null is always safe to handle.
     * @property location  Physical location of the key on the keyboard (standard, left, right,
     *   or numpad). Defaults to [KeyLocation.Standard]. Populated where the platform exposes it.
     * @property scanCode  Platform-independent physical key code (evdev / HID usage / Win32 scan),
     *   independent of the active keyboard layout. Null if the backend does not expose it.
     */
    data class KeyboardInput(
        val deviceId: DeviceId?,
        val key: Key,
        val state: KeyState,
        val modifiers: Modifiers,
        val isRepeat: Boolean = false,
        val isSynthetic: Boolean = false,
        // ── R4 additions (with defaults to keep all existing call sites valid) ──
        val text: String? = null,
        val location: KeyLocation = KeyLocation.Standard,
        val scanCode: Int? = null,
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

    // ── R4: modifier state ────────────────────────────────────────────────────

    /**
     * The set of active keyboard modifiers changed.
     *
     * Emitted when a modifier key (Shift, Ctrl, Alt, Meta) is pressed or released.
     * Backends emit this on a best-effort basis:
     * - win32   : WM_KEYDOWN / WM_KEYUP on VK_SHIFT/CONTROL/MENU/WIN
     * - web     : keydown / keyup when `KeyboardEvent.key` is a modifier
     * - x11     : XkbStateNotify (TODO — not yet wired)
     * - wayland : wl_keyboard.modifiers (TODO — not yet wired)
     * - appkit  : NSEventTypeKeyDown / NSEventTypeKeyUp on modifier key codes
     * - android : onKeyDown/Up for KEYCODE_SHIFT_* etc. (TODO — not yet wired)
     * - uikit   : pressesBegan/Ended on modifier keys (TODO — not yet wired)
     *
     * @property modifiers New modifier state after the change.
     */
    data class ModifiersChanged(val modifiers: Modifiers) : WindowEvent

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
 *         is DeviceEvent.Key           -> handleKey(event.scancode, event.state)
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
     * A physical keyboard key changed state (identified by scancode).
     *
     * @property scancode Physical key code (independent of the keyboard layout).
     * @property state    Key state ([KeyState.Pressed] or [KeyState.Released]).
     */
    data class Key(val scancode: Int, val state: KeyState) : DeviceEvent

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
