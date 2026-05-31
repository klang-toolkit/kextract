/**
 * Tests unitaires pour [WaylandKeyMapper] et [WaylandMouseMapper].
 *
 * These tests verify the correctness of the mapping tables and the conversion
 * functions without a dependency on libwayland-client.so.0.
 * They run on all platforms (macOS, Windows, Linux).
 */
package org.graphiks.kadre.wayland

import org.graphiks.kadre.core.Key
import org.graphiks.kadre.core.KeyState
import org.graphiks.kadre.core.Modifiers
import org.graphiks.kadre.core.MouseButton
import org.graphiks.kadre.core.WindowEvent
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

// ============================================================================
// WaylandKeyMapper tests
// ============================================================================

class WaylandKeyMapperTest {

    // ── linuxKeycodeToKey ────────────────────────────────────────────────────

    @Test
    fun `KEY_A (30) maps to Key_A`() {
        assertEquals(Key.A, linuxKeycodeToKey(30))
    }

    @Test
    fun `KEY_Z (44) maps to Key_Z`() {
        assertEquals(Key.Z, linuxKeycodeToKey(44))
    }

    @Test
    fun `KEY_Q (16) maps to Key_Q`() {
        assertEquals(Key.Q, linuxKeycodeToKey(16))
    }

    @Test
    fun `KEY_M (50) maps to Key_M`() {
        assertEquals(Key.M, linuxKeycodeToKey(50))
    }

    @Test
    fun `KEY_1 (2) maps to Key_Digit1`() {
        assertEquals(Key.Digit1, linuxKeycodeToKey(2))
    }

    @Test
    fun `KEY_0 (11) maps to Key_Digit0`() {
        assertEquals(Key.Digit0, linuxKeycodeToKey(11))
    }

    @Test
    fun `KEY_9 (10) maps to Key_Digit9`() {
        assertEquals(Key.Digit9, linuxKeycodeToKey(10))
    }

    @Test
    fun `KEY_ENTER (28) maps to Key_Enter`() {
        assertEquals(Key.Enter, linuxKeycodeToKey(28))
    }

    @Test
    fun `KEY_ESC (1) maps to Key_Escape`() {
        assertEquals(Key.Escape, linuxKeycodeToKey(1))
    }

    @Test
    fun `KEY_BACKSPACE (14) maps to Key_Backspace`() {
        assertEquals(Key.Backspace, linuxKeycodeToKey(14))
    }

    @Test
    fun `KEY_TAB (15) maps to Key_Tab`() {
        assertEquals(Key.Tab, linuxKeycodeToKey(15))
    }

    @Test
    fun `KEY_SPACE (57) maps to Key_Space`() {
        assertEquals(Key.Space, linuxKeycodeToKey(57))
    }

    @Test
    fun `KEY_F1 (59) maps to Key_F1`() {
        assertEquals(Key.F1, linuxKeycodeToKey(59))
    }

    @Test
    fun `KEY_F12 (88) maps to Key_F12`() {
        assertEquals(Key.F12, linuxKeycodeToKey(88))
    }

    @Test
    fun `KEY_UP (103) maps to Key_ArrowUp`() {
        assertEquals(Key.ArrowUp, linuxKeycodeToKey(103))
    }

    @Test
    fun `KEY_LEFT (105) maps to Key_ArrowLeft`() {
        assertEquals(Key.ArrowLeft, linuxKeycodeToKey(105))
    }

    @Test
    fun `KEY_RIGHT (106) maps to Key_ArrowRight`() {
        assertEquals(Key.ArrowRight, linuxKeycodeToKey(106))
    }

    @Test
    fun `KEY_DOWN (108) maps to Key_ArrowDown`() {
        assertEquals(Key.ArrowDown, linuxKeycodeToKey(108))
    }

    @Test
    fun `KEY_LEFTSHIFT (42) maps to Key_ShiftLeft`() {
        assertEquals(Key.ShiftLeft, linuxKeycodeToKey(42))
    }

    @Test
    fun `KEY_RIGHTSHIFT (54) maps to Key_ShiftRight`() {
        assertEquals(Key.ShiftRight, linuxKeycodeToKey(54))
    }

    @Test
    fun `KEY_LEFTCTRL (29) maps to Key_ControlLeft`() {
        assertEquals(Key.ControlLeft, linuxKeycodeToKey(29))
    }

    @Test
    fun `KEY_RIGHTCTRL (97) maps to Key_ControlRight`() {
        assertEquals(Key.ControlRight, linuxKeycodeToKey(97))
    }

    @Test
    fun `KEY_LEFTALT (56) maps to Key_AltLeft`() {
        assertEquals(Key.AltLeft, linuxKeycodeToKey(56))
    }

    @Test
    fun `KEY_RIGHTALT (100) maps to Key_AltRight`() {
        assertEquals(Key.AltRight, linuxKeycodeToKey(100))
    }

    @Test
    fun `KEY_LEFTMETA (125) maps to Key_MetaLeft`() {
        assertEquals(Key.MetaLeft, linuxKeycodeToKey(125))
    }

    @Test
    fun `KEY_RIGHTMETA (126) maps to Key_MetaRight`() {
        assertEquals(Key.MetaRight, linuxKeycodeToKey(126))
    }

    @Test
    fun `unknown keycode maps to Key_Unknown`() {
        assertEquals(Key.Unknown, linuxKeycodeToKey(999))
        assertEquals(Key.Unknown, linuxKeycodeToKey(0))
        assertEquals(Key.Unknown, linuxKeycodeToKey(-1))
    }

    // ── waylandKeyStateToKeyState ─────────────────────────────────────────────

    @Test
    fun `state 0 (released) maps to KeyState_Released`() {
        assertEquals(KeyState.Released, waylandKeyStateToKeyState(WL_KEY_RELEASED))
    }

    @Test
    fun `state 1 (pressed) maps to KeyState_Pressed`() {
        assertEquals(KeyState.Pressed, waylandKeyStateToKeyState(WL_KEY_PRESSED))
    }

    @Test
    fun `state 2 (repeated) maps to KeyState_Pressed`() {
        assertEquals(KeyState.Pressed, waylandKeyStateToKeyState(WL_KEY_REPEATED))
    }

    // ── mapWaylandKeyEvent ────────────────────────────────────────────────────

    @Test
    fun `mapWaylandKeyEvent KEY_A pressed returns KeyboardInput with Key_A Pressed`() {
        val event = mapWaylandKeyEvent(keycode = 30, state = WL_KEY_PRESSED)
        assertEquals(Key.A, event.key)
        assertEquals(KeyState.Pressed, event.state)
        assertFalse(event.isRepeat)
    }

    @Test
    fun `mapWaylandKeyEvent KEY_A released returns KeyboardInput with KeyState_Released`() {
        val event = mapWaylandKeyEvent(keycode = 30, state = WL_KEY_RELEASED)
        assertEquals(Key.A, event.key)
        assertEquals(KeyState.Released, event.state)
        assertFalse(event.isRepeat)
    }

    @Test
    fun `mapWaylandKeyEvent state 2 sets isRepeat to true`() {
        val event = mapWaylandKeyEvent(keycode = 30, state = WL_KEY_REPEATED)
        assertEquals(Key.A, event.key)
        assertEquals(KeyState.Pressed, event.state)
        assertTrue(event.isRepeat)
    }

    @Test
    fun `mapWaylandKeyEvent propagates modifiers`() {
        val mods = Modifiers.SHIFT + Modifiers.CTRL
        val event = mapWaylandKeyEvent(keycode = 30, state = WL_KEY_PRESSED, modifiers = mods)
        assertEquals(mods, event.modifiers)
    }

    @Test
    fun `mapWaylandKeyEvent unknown keycode produces Key_Unknown`() {
        val event = mapWaylandKeyEvent(keycode = 999, state = WL_KEY_PRESSED)
        assertEquals(Key.Unknown, event.key)
    }

    @Test
    fun `mapWaylandKeyEvent returns WindowEvent_KeyboardInput`() {
        val event = mapWaylandKeyEvent(keycode = 30, state = WL_KEY_PRESSED)
        assertTrue(event is WindowEvent.KeyboardInput)
    }
}

// ============================================================================
// WaylandMouseMapper tests
// ============================================================================

class WaylandMouseMapperTest {

    // ── wlFixedToDouble ───────────────────────────────────────────────────────

    @Test
    fun `wlFixedToDouble converts 256 to 1_0`() {
        assertEquals(1.0, wlFixedToDouble(256))
    }

    @Test
    fun `wlFixedToDouble converts 2560 to 10_0`() {
        assertEquals(10.0, wlFixedToDouble(2560))
    }

    @Test
    fun `wlFixedToDouble converts 0 to 0_0`() {
        assertEquals(0.0, wlFixedToDouble(0))
    }

    @Test
    fun `wlFixedToDouble converts 128 to 0_5`() {
        assertEquals(0.5, wlFixedToDouble(128))
    }

    // ── linuxButtonToMouseButton ──────────────────────────────────────────────

    @Test
    fun `BTN_LEFT (272) maps to MouseButton_Left`() {
        assertEquals(MouseButton.Left, linuxButtonToMouseButton(BTN_LEFT))
    }

    @Test
    fun `BTN_RIGHT (273) maps to MouseButton_Right`() {
        assertEquals(MouseButton.Right, linuxButtonToMouseButton(BTN_RIGHT))
    }

    @Test
    fun `BTN_MIDDLE (274) maps to MouseButton_Middle`() {
        assertEquals(MouseButton.Middle, linuxButtonToMouseButton(BTN_MIDDLE))
    }

    @Test
    fun `BTN_SIDE (275) maps to MouseButton_Other(275)`() {
        assertEquals(MouseButton.Other(BTN_SIDE), linuxButtonToMouseButton(BTN_SIDE))
    }

    @Test
    fun `BTN_EXTRA (276) maps to MouseButton_Other(276)`() {
        assertEquals(MouseButton.Other(BTN_EXTRA), linuxButtonToMouseButton(BTN_EXTRA))
    }

    @Test
    fun `unknown button maps to MouseButton_Other`() {
        assertEquals(MouseButton.Other(999), linuxButtonToMouseButton(999))
    }

    // ── waylandButtonStateToKeyState ──────────────────────────────────────────

    @Test
    fun `button state 0 (released) maps to KeyState_Released`() {
        assertEquals(KeyState.Released, waylandButtonStateToKeyState(WL_POINTER_BUTTON_STATE_RELEASED))
    }

    @Test
    fun `button state 1 (pressed) maps to KeyState_Pressed`() {
        assertEquals(KeyState.Pressed, waylandButtonStateToKeyState(WL_POINTER_BUTTON_STATE_PRESSED))
    }

    // ── mapWaylandPointerMotion ───────────────────────────────────────────────

    @Test
    fun `mapWaylandPointerMotion converts wl_fixed coordinates correctly`() {
        // x = 100.0 → wl_fixed = 100 * 256 = 25600
        // y =  50.0 → wl_fixed =  50 * 256 = 12800
        val event = mapWaylandPointerMotion(xFixed = 25600, yFixed = 12800)
        assertEquals(100.0, event.position.x)
        assertEquals(50.0, event.position.y)
    }

    @Test
    fun `mapWaylandPointerMotion returns PointerMoved`() {
        val event = mapWaylandPointerMotion(xFixed = 256, yFixed = 512)
        assertTrue(event is WindowEvent.PointerMoved)
    }

    // ── mapWaylandPointerButton ───────────────────────────────────────────────

    @Test
    fun `mapWaylandPointerButton BTN_LEFT pressed returns MouseInput Left Pressed`() {
        val event = mapWaylandPointerButton(button = BTN_LEFT, state = WL_POINTER_BUTTON_STATE_PRESSED)
        assertEquals(MouseButton.Left, event.button)
        assertEquals(KeyState.Pressed, event.state)
    }

    @Test
    fun `mapWaylandPointerButton BTN_RIGHT released returns MouseInput Right Released`() {
        val event = mapWaylandPointerButton(button = BTN_RIGHT, state = WL_POINTER_BUTTON_STATE_RELEASED)
        assertEquals(MouseButton.Right, event.button)
        assertEquals(KeyState.Released, event.state)
    }

    @Test
    fun `mapWaylandPointerButton BTN_MIDDLE pressed returns MouseInput Middle Pressed`() {
        val event = mapWaylandPointerButton(button = BTN_MIDDLE, state = WL_POINTER_BUTTON_STATE_PRESSED)
        assertEquals(MouseButton.Middle, event.button)
        assertEquals(KeyState.Pressed, event.state)
    }

    @Test
    fun `mapWaylandPointerButton returns WindowEvent_MouseInput`() {
        val event = mapWaylandPointerButton(button = BTN_LEFT, state = WL_POINTER_BUTTON_STATE_PRESSED)
        assertTrue(event is WindowEvent.MouseInput)
    }

    // ── mapWaylandPointerAxis ─────────────────────────────────────────────────

    @Test
    fun `mapWaylandPointerAxis vertical axis sets deltaY`() {
        // 10.0 pixels → wl_fixed = 10 * 256 = 2560
        val event = mapWaylandPointerAxis(axis = WL_POINTER_AXIS_VERTICAL_SCROLL, valueFixed = 2560)
        assertEquals(0.0, event.deltaX)
        assertEquals(10.0, event.deltaY)
    }

    @Test
    fun `mapWaylandPointerAxis horizontal axis sets deltaX`() {
        // 5.0 pixels → wl_fixed = 5 * 256 = 1280
        val event = mapWaylandPointerAxis(axis = WL_POINTER_AXIS_HORIZONTAL_SCROLL, valueFixed = 1280)
        assertEquals(5.0, event.deltaX)
        assertEquals(0.0, event.deltaY)
    }

    @Test
    fun `mapWaylandPointerAxis unknown axis returns zero deltas`() {
        val event = mapWaylandPointerAxis(axis = 99, valueFixed = 1000)
        assertEquals(0.0, event.deltaX)
        assertEquals(0.0, event.deltaY)
    }

    @Test
    fun `mapWaylandPointerAxis returns WindowEvent_MouseWheel`() {
        val event = mapWaylandPointerAxis(axis = WL_POINTER_AXIS_VERTICAL_SCROLL, valueFixed = 256)
        assertTrue(event is WindowEvent.MouseWheel)
    }
}
