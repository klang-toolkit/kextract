/**
 * kadre-core event model.
 *
 * This file defines the two central event hierarchies:
 * - [WindowEvent]: events related to a window (resize, keyboard, pointer, etc.)
 * - [DeviceEvent]: raw device events (absolute motion, button, key)
 *
 * It also defines the support types used by these events:
 * [Key], [KeyState], [Modifiers], [MouseButton] and [TouchPhase].
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
 *         is WindowEvent.PointerMoved   -> handlePointer(event.position)
 *         WindowEvent.PointerEntered    -> handleEnter()
 *         WindowEvent.PointerLeft       -> handleLeave()
 *         is WindowEvent.MouseInput     -> handleMouse(event.button, event.state)
 *         is WindowEvent.MouseWheel     -> handleWheel(event.deltaX, event.deltaY)
 *         is WindowEvent.Touch          -> handleTouch(event.phase, event.location, event.id)
 *         WindowEvent.RedrawRequested   -> redraw()
 *         WindowEvent.Destroyed         -> releaseResources()
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
     * @property key     Logical key involved.
     * @property state   Key state ([KeyState.Pressed] or [KeyState.Released]).
     * @property modifiers Modifiers active at the time of the event.
     */
    data class KeyboardInput(
        val key: Key,
        val state: KeyState,
        val modifiers: Modifiers,
        val isRepeat: Boolean = false,
    ) : WindowEvent

    /**
     * The pointer moved over the window.
     *
     * @property position Current pointer position in physical pixels (floating point
     *   for the sub-pixel precision of tablets and trackpads).
     */
    data class PointerMoved(val position: PhysicalPosition<Double>) : WindowEvent

    /**
     * The pointer just entered the window's client area.
     */
    data object PointerEntered : WindowEvent

    /**
     * The pointer just left the window's client area.
     */
    data object PointerLeft : WindowEvent

    /**
     * A mouse button has been pressed or released.
     *
     * @property button Button involved.
     * @property state  Button state ([KeyState.Pressed] or [KeyState.Released]).
     */
    data class MouseInput(val button: MouseButton, val state: KeyState) : WindowEvent

    /**
     * The mouse wheel (or trackpad) produced a scroll.
     *
     * @property deltaX Horizontal scroll (positive towards the right).
     * @property deltaY Vertical scroll (positive towards the bottom).
     */
    data class MouseWheel(val deltaX: Double, val deltaY: Double) : WindowEvent

    /**
     * A touch contact changed state.
     *
     * @property phase    Contact phase.
     * @property location Contact position in physical pixels.
     * @property id       Unique contact identifier (stable between [TouchPhase.Started] and
     *   [TouchPhase.Ended]/[TouchPhase.Cancelled]).
     */
    data class Touch(
        val phase: TouchPhase,
        val location: PhysicalPosition<Double>,
        val id: Long,
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
}
