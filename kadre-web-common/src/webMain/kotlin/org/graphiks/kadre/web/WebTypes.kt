/**
 * Web types mirroring the kadre-core types.
 *
 * These types reproduce the kadre-core models (Key, KeyState, Modifiers,
 * MouseButton, WindowEvent) for the JS and wasmJs targets, which cannot
 * yet depend on kadre-core (it does not yet expose JS targets).
 *
 * ## Planned migration
 * When kadre-core is extended with the JS/wasmJs targets (ticket #32),
 * these types will be replaced with typealiases to kadre-core and the
 * Web* classes will be removed.
 *
 * ## Constraint
 * webMain file — NO DOM import allowed.
 *
 * @since 1.0.0
 */
package org.graphiks.kadre.web

// ---------------------------------------------------------------------------
// Logical keys
// ---------------------------------------------------------------------------

/**
 * Logical keyboard keys (web mirror of kadre-core.Key).
 */
enum class WebKey {
    // Letters
    A, B, C, D, E, F, G, H, I, J, K, L, M,
    N, O, P, Q, R, S, T, U, V, W, X, Y, Z,

    // Digits
    Digit0, Digit1, Digit2, Digit3, Digit4,
    Digit5, Digit6, Digit7, Digit8, Digit9,

    // Function keys
    F1, F2, F3, F4, F5, F6, F7, F8, F9, F10, F11, F12,

    // Special keys
    Space, Enter, Escape, Backspace, Tab,

    // Navigation
    ArrowUp, ArrowDown, ArrowLeft, ArrowRight,

    // Modifiers
    ShiftLeft, ShiftRight,
    ControlLeft, ControlRight,
    AltLeft, AltRight,
    MetaLeft, MetaRight,

    Unknown,
}

// ---------------------------------------------------------------------------
// Key location
// ---------------------------------------------------------------------------

/**
 * Physical location of a keyboard key on the keyboard (web mirror of kadre-core.KeyLocation).
 *
 * Maps the DOM `KeyboardEvent.location` property values:
 * - `0` (DOM_KEY_LOCATION_STANDARD) → [Standard]
 * - `1` (DOM_KEY_LOCATION_LEFT)     → [Left]
 * - `2` (DOM_KEY_LOCATION_RIGHT)    → [Right]
 * - `3` (DOM_KEY_LOCATION_NUMPAD)   → [Numpad]
 */
enum class WebKeyLocation {
    Standard,
    Left,
    Right,
    Numpad,
}

// ---------------------------------------------------------------------------
// Key state
// ---------------------------------------------------------------------------

/** State of a key or a button. */
enum class WebKeyState {
    /** The key has just been pressed. */
    Pressed,

    /** The key has just been released. */
    Released,
}

// ---------------------------------------------------------------------------
// Modifiers
// ---------------------------------------------------------------------------

/**
 * Set of active keyboard modifiers (web mirror of kadre-core.Modifiers).
 *
 * Internal representation as a bit field.
 */
data class WebModifiers(val bits: Int) {

    /** `true` if Shift is pressed. */
    val shift: Boolean get() = bits and 0x1 != 0

    /** `true` if Control is pressed. */
    val ctrl: Boolean get() = bits and 0x2 != 0

    /** `true` if Alt is pressed. */
    val alt: Boolean get() = bits and 0x4 != 0

    /** `true` if Meta (⌘/Win) is pressed. */
    val meta: Boolean get() = bits and 0x8 != 0

    /** Combines these modifiers with [other]. */
    operator fun plus(other: WebModifiers): WebModifiers = WebModifiers(bits or other.bits)

    /** Checks whether this set contains all the modifiers of [other]. */
    fun contains(other: WebModifiers): Boolean = bits and other.bits == other.bits

    companion object {
        val NONE  = WebModifiers(0x0)
        val SHIFT = WebModifiers(0x1)
        val CTRL  = WebModifiers(0x2)
        val ALT   = WebModifiers(0x4)
        val META  = WebModifiers(0x8)
    }
}

// ---------------------------------------------------------------------------
// Mouse button
// ---------------------------------------------------------------------------

/** Mouse button (web mirror of kadre-core.MouseButton). */
sealed interface WebMouseButton {
    data object Left   : WebMouseButton
    data object Right  : WebMouseButton
    data object Middle : WebMouseButton
    data class Other(val button: Int) : WebMouseButton
}

// ---------------------------------------------------------------------------
// Touch phase
// ---------------------------------------------------------------------------

/**
 * Phase of a touch contact (web mirror of kadre-core.TouchPhase).
 *
 * Maps the DOM touch event types: `touchstart` → [Started], `touchmove` →
 * [Moved], `touchend` → [Ended], `touchcancel` → [Cancelled].
 */
enum class WebTouchPhase {
    /** The contact has just been placed on the surface (`touchstart`). */
    Started,

    /** The contact has moved (`touchmove`). */
    Moved,

    /** The contact has been lifted (`touchend`). */
    Ended,

    /** The contact has been cancelled by the browser (`touchcancel`). */
    Cancelled,
}

// ---------------------------------------------------------------------------
// Web window events
// ---------------------------------------------------------------------------

/**
 * Window events emitted by [WebDomBridge] (web mirror of kadre-core.WindowEvent).
 *
 * Subset of the DOM events relevant to a Kadre web renderer.
 */
sealed interface WebWindowEvent {

    /**
     * The user requested to close / navigate away from the page.
     *
     * **Web-specific**: emitted from the `beforeunload` DOM event. The browser
     * does not let the application veto the navigation programmatically, so this
     * is purely informational (counterpart of a desktop window's × button).
     */
    data object CloseRequested : WebWindowEvent

    /**
     * The window/canvas has been resized.
     *
     * @property width  New width in physical pixels.
     * @property height New height in physical pixels.
     */
    data class Resized(val width: Int, val height: Int) : WebWindowEvent

    /**
     * A keyboard event occurred.
     *
     * @property key       Logical key.
     * @property state     State (pressed / released).
     * @property modifiers Active modifiers.
     * @property isRepeat  `true` if the event is an auto-repeat.
     * @property text      Printable character(s) produced (from `KeyboardEvent.key` if printable), or null.
     * @property location  Physical key location on the keyboard.
     * @property scanCode  DOM `KeyboardEvent.code` string used as a layout-independent identifier.
     */
    data class KeyboardInput(
        val key: WebKey,
        val state: WebKeyState,
        val modifiers: WebModifiers,
        val isRepeat: Boolean = false,
        val text: String? = null,
        val location: WebKeyLocation = WebKeyLocation.Standard,
        val scanCode: String? = null,
    ) : WebWindowEvent

    /**
     * The set of active keyboard modifiers changed.
     *
     * Emitted when a modifier key (Shift, Ctrl, Alt, Meta) is pressed or released.
     *
     * @property modifiers New modifier state after the change.
     */
    data class ModifiersChanged(val modifiers: WebModifiers) : WebWindowEvent

    /**
     * The pointer has moved.
     *
     * @property x X position in physical pixels.
     * @property y Y position in physical pixels.
     */
    data class PointerMoved(val x: Double, val y: Double) : WebWindowEvent

    /** The pointer entered the canvas. */
    data object PointerEntered : WebWindowEvent

    /** The pointer left the canvas. */
    data object PointerLeft : WebWindowEvent

    /**
     * A mouse button changed state.
     *
     * @property button Button concerned.
     * @property state  Button state.
     */
    data class MouseInput(val button: WebMouseButton, val state: WebKeyState) : WebWindowEvent

    /**
     * The wheel produced a scroll.
     *
     * @property deltaX Horizontal scroll.
     * @property deltaY Vertical scroll.
     */
    data class MouseWheel(val deltaX: Double, val deltaY: Double) : WebWindowEvent

    /** The window gained or lost focus. */
    data class Focused(val gained: Boolean) : WebWindowEvent

    /**
     * A touch contact changed state.
     *
     * @property phase Contact phase.
     * @property x     X position in physical pixels (client coordinates).
     * @property y     Y position in physical pixels (client coordinates).
     * @property id    Contact identifier (DOM `Touch.identifier`), stable between
     *   [WebTouchPhase.Started] and [WebTouchPhase.Ended] / [WebTouchPhase.Cancelled].
     */
    data class Touch(
        val phase: WebTouchPhase,
        val x: Double,
        val y: Double,
        val id: Long,
    ) : WebWindowEvent

    /**
     * The DPI scale factor changed.
     *
     * **Web-specific**: derived from `window.devicePixelRatio`, observed via a
     * re-arming `matchMedia('(resolution: <dpr>dppx)')` listener. Fires on zoom
     * changes or when the page moves to a screen with a different pixel ratio.
     *
     * @property factor New scale factor (e.g. `2.0` on a Retina display).
     */
    data class ScaleFactorChanged(val factor: Double) : WebWindowEvent

    /** A redraw is requested (requestAnimationFrame). */
    data object RedrawRequested : WebWindowEvent

    /**
     * The page is being unloaded and the renderer should release its resources.
     *
     * **Web-specific**: emitted from the `pagehide` DOM event (counterpart of a
     * desktop window's `Destroyed`). No further event is emitted afterwards.
     */
    data object Destroyed : WebWindowEvent
}
