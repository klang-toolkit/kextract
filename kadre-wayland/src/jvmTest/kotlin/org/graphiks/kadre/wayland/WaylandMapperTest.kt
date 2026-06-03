package org.graphiks.kadre.wayland

import org.graphiks.kadre.core.ButtonSource
import org.graphiks.kadre.core.FingerId
import org.graphiks.kadre.core.KeyCode
import org.graphiks.kadre.core.KeyState
import org.graphiks.kadre.core.KeyboardModifiers
import org.graphiks.kadre.core.ModifierKeyState
import org.graphiks.kadre.core.MouseButton
import org.graphiks.kadre.core.NativeKeyCode
import org.graphiks.kadre.core.PhysicalKey
import org.graphiks.kadre.core.PhysicalPosition
import org.graphiks.kadre.core.PointerKind
import org.graphiks.kadre.core.PointerSource
import org.graphiks.kadre.core.WindowEvent
import java.lang.foreign.Arena
import java.lang.foreign.ValueLayout
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertIs
import kotlin.test.assertNull
import kotlin.test.assertTrue

class WaylandMapperTest {
    @Test
    fun `linux keycodes map to physical KeyCode`() {
        assertEquals(KeyCode.KeyA, linuxKeycodeToKeyCode(30))
        assertEquals(KeyCode.KeyZ, linuxKeycodeToKeyCode(44))
        assertEquals(KeyCode.KeyQ, linuxKeycodeToKeyCode(16))
        assertEquals(KeyCode.KeyM, linuxKeycodeToKeyCode(50))
        assertEquals(KeyCode.Digit1, linuxKeycodeToKeyCode(2))
        assertEquals(KeyCode.Digit0, linuxKeycodeToKeyCode(11))
        assertEquals(KeyCode.Enter, linuxKeycodeToKeyCode(28))
        assertEquals(KeyCode.Escape, linuxKeycodeToKeyCode(1))
        assertEquals(KeyCode.ArrowUp, linuxKeycodeToKeyCode(103))
        assertEquals(KeyCode.ArrowDown, linuxKeycodeToKeyCode(108))
        assertEquals(KeyCode.ShiftLeft, linuxKeycodeToKeyCode(42))
        assertEquals(KeyCode.MetaRight, linuxKeycodeToKeyCode(126))
        assertNull(linuxKeycodeToKeyCode(999))
    }

    @Test
    fun `wayland state maps to key state`() {
        assertEquals(KeyState.Released, waylandKeyStateToKeyState(WL_KEY_RELEASED))
        assertEquals(KeyState.Pressed, waylandKeyStateToKeyState(WL_KEY_PRESSED))
        assertEquals(KeyState.Pressed, waylandKeyStateToKeyState(WL_KEY_REPEATED))
    }

    @Test
    fun `mapWaylandKeyEvent builds KeyInput`() {
        val mods = KeyboardModifiers.Shift + KeyboardModifiers.Ctrl
        val event = assertIs<WindowEvent.KeyInput>(
            mapWaylandKeyEvent(30, WL_KEY_REPEATED, mods),
        ).event

        assertEquals(PhysicalKey.Code(KeyCode.KeyA), event.physicalKey)
        assertEquals(KeyState.Pressed, event.state)
        assertTrue(event.repeat)
        assertEquals(mods, event.modifiers)
    }

    @Test
    fun `unknown keycode preserves native physical key`() {
        val event = mapWaylandKeyEvent(999, WL_KEY_PRESSED).event
        assertEquals(PhysicalKey.Native(NativeKeyCode.Wayland(999)), event.physicalKey)
    }

    @Test
    fun `modifier state tracks pressed and released sides`() {
        val initial = waylandInitialModifierState()
        val leftShift = waylandModifierStateFrom(initial, 42, KeyState.Pressed)
        val bothShift = waylandModifierStateFrom(leftShift, 54, KeyState.Pressed)
        val rightReleased = waylandModifierStateFrom(bothShift, 54, KeyState.Released)
        val allReleased = waylandModifierStateFrom(rightReleased, 42, KeyState.Released)

        assertTrue(leftShift.logical.shift)
        assertEquals(ModifierKeyState.Pressed, leftShift.physical.leftShift)
        assertEquals(ModifierKeyState.Pressed, bothShift.physical.leftShift)
        assertEquals(ModifierKeyState.Pressed, bothShift.physical.rightShift)
        assertTrue(rightReleased.logical.shift)
        assertEquals(ModifierKeyState.Pressed, rightReleased.physical.leftShift)
        assertEquals(ModifierKeyState.Released, rightReleased.physical.rightShift)
        assertFalse(allReleased.logical.shift)
    }

    @Test
    fun `modifier state tracks ctrl alt and meta sides`() {
        val initial = waylandInitialModifierState()

        val leftCtrl = waylandModifierStateFrom(initial, 29, KeyState.Pressed)
        val rightCtrl = waylandModifierStateFrom(initial, 97, KeyState.Pressed)
        val leftAlt = waylandModifierStateFrom(initial, 56, KeyState.Pressed)
        val rightAlt = waylandModifierStateFrom(initial, 100, KeyState.Pressed)
        val leftMeta = waylandModifierStateFrom(initial, 125, KeyState.Pressed)
        val rightMeta = waylandModifierStateFrom(initial, 126, KeyState.Pressed)

        assertTrue(leftCtrl.logical.ctrl)
        assertEquals(ModifierKeyState.Pressed, leftCtrl.physical.leftCtrl)
        assertTrue(rightCtrl.logical.ctrl)
        assertEquals(ModifierKeyState.Pressed, rightCtrl.physical.rightCtrl)
        assertTrue(leftAlt.logical.alt)
        assertEquals(ModifierKeyState.Pressed, leftAlt.physical.leftAlt)
        assertTrue(rightAlt.logical.alt)
        assertEquals(ModifierKeyState.Pressed, rightAlt.physical.rightAlt)
        assertTrue(leftMeta.logical.meta)
        assertEquals(ModifierKeyState.Pressed, leftMeta.physical.leftMeta)
        assertTrue(rightMeta.logical.meta)
        assertEquals(ModifierKeyState.Pressed, rightMeta.physical.rightMeta)
    }

    @Test
    fun `unchanged modifier state can be deduplicated by caller`() {
        val initial = waylandInitialModifierState()
        val firstRelease = waylandModifierStateFrom(initial, 29, KeyState.Released)
        val pressed = waylandModifierStateFrom(initial, 29, KeyState.Pressed)
        val repeatedPress = waylandModifierStateFrom(pressed, 29, KeyState.Pressed)

        assertEquals(initial, firstRelease)
        assertEquals(pressed, repeatedPress)
    }

    @Test
    fun `keyboard tracker emits ModifiersChanged before modifier KeyInput`() {
        val events = WaylandKeyboardModifierTracker().mapKey(42, WL_KEY_PRESSED)

        val modifiersChanged = assertIs<WindowEvent.ModifiersChanged>(events[0])
        val keyInput = assertIs<WindowEvent.KeyInput>(events[1])
        assertTrue(modifiersChanged.state.logical.shift)
        assertEquals(ModifierKeyState.Pressed, modifiersChanged.state.physical.leftShift)
        assertEquals(KeyboardModifiers.Shift, keyInput.event.modifiers)
        assertEquals(KeyState.Pressed, keyInput.event.state)
    }

    @Test
    fun `keyboard tracker avoids duplicate modifier change events`() {
        val tracker = WaylandKeyboardModifierTracker()
        tracker.mapKey(29, WL_KEY_PRESSED)

        val repeatedEvents = tracker.mapKey(29, WL_KEY_REPEATED)
        val releaseEvents = tracker.mapKey(97, WL_KEY_RELEASED)

        assertEquals(1, repeatedEvents.size)
        assertIs<WindowEvent.KeyInput>(repeatedEvents.single())
        assertEquals(1, releaseEvents.size)
        assertIs<WindowEvent.KeyInput>(releaseEvents.single())
    }

    @Test
    fun `keyboard tracker applies modifiers to non modifier key events`() {
        val tracker = WaylandKeyboardModifierTracker()
        tracker.mapKey(42, WL_KEY_PRESSED)

        val event = assertIs<WindowEvent.KeyInput>(tracker.mapKey(30, WL_KEY_PRESSED).single()).event

        assertEquals(KeyboardModifiers.Shift, event.modifiers)
    }

    @Test
    fun `keyboard tracker resets modifiers before focus lost`() {
        val tracker = WaylandKeyboardModifierTracker()
        tracker.mapKey(125, WL_KEY_PRESSED)

        val events = tracker.mapFocusLost()

        val modifiersChanged = assertIs<WindowEvent.ModifiersChanged>(events[0])
        val focused = assertIs<WindowEvent.Focused>(events[1])
        assertEquals(KeyboardModifiers.NONE, modifiersChanged.state.logical)
        assertEquals(ModifierKeyState.Released, modifiersChanged.state.physical.leftMeta)
        assertFalse(focused.gained)
    }

    @Test
    fun `keyboard tracker does not emit reset when modifiers are already clear`() {
        val events = WaylandKeyboardModifierTracker().mapFocusLost()

        assertEquals(1, events.size)
        assertFalse(assertIs<WindowEvent.Focused>(events.single()).gained)
    }

    @Test
    fun `keyboard tracker initializes modifiers from focus enter pressed keys`() {
        val events = WaylandKeyboardModifierTracker().mapFocusGained(listOf(42, 29))

        val modifiersChanged = assertIs<WindowEvent.ModifiersChanged>(events[0])
        val focused = assertIs<WindowEvent.Focused>(events[1])
        assertTrue(modifiersChanged.state.logical.shift)
        assertTrue(modifiersChanged.state.logical.ctrl)
        assertEquals(ModifierKeyState.Pressed, modifiersChanged.state.physical.leftShift)
        assertEquals(ModifierKeyState.Pressed, modifiersChanged.state.physical.leftCtrl)
        assertTrue(focused.gained)
    }

    @Test
    fun `pressed keys parser reads wl array keycodes`() {
        Arena.ofConfined().use { arena ->
            val keyData = arena.allocate(ValueLayout.JAVA_INT, 2)
            keyData.set(ValueLayout.JAVA_INT, 0L, 42)
            keyData.set(ValueLayout.JAVA_INT, ValueLayout.JAVA_INT.byteSize(), 29)
            val wlArray = arena.allocate(24L)
            wlArray.set(ValueLayout.JAVA_LONG, 0L, ValueLayout.JAVA_INT.byteSize() * 2)
            wlArray.set(ValueLayout.JAVA_LONG, 8L, ValueLayout.JAVA_INT.byteSize() * 2)
            wlArray.set(ValueLayout.ADDRESS, 16L, keyData)

            assertEquals(listOf(42, 29), waylandPressedKeysFromArray(wlArray))
        }
    }
}
class WaylandFocusedMapperTest {

    @Test
    fun `mapWaylandKeyboardFocused true returns Focused gained`() {
        val event = mapWaylandKeyboardFocused(true)
        assertTrue(event is WindowEvent.Focused)
        assertTrue(event.gained)
    }

    @Test
    fun `mapWaylandKeyboardFocused false returns Focused not gained`() {
        val event = mapWaylandKeyboardFocused(false)
        assertTrue(event is WindowEvent.Focused)
        assertFalse(event.gained)
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
    fun `mapWaylandPointerButton BTN_LEFT pressed returns PointerButton Left Pressed`() {
        val event = mapWaylandPointerButton(
            button = BTN_LEFT,
            state = WL_POINTER_BUTTON_STATE_PRESSED,
            position = PhysicalPosition(12.0, 34.0),
        )
        assertEquals(ButtonSource.Mouse(MouseButton.Left), event.button)
        assertEquals(KeyState.Pressed, event.state)
        assertEquals(12.0, event.position.x)
        assertEquals(34.0, event.position.y)
    }

    @Test
    fun `mapWaylandPointerButton BTN_RIGHT released returns PointerButton Right Released`() {
        val event = mapWaylandPointerButton(
            button = BTN_RIGHT,
            state = WL_POINTER_BUTTON_STATE_RELEASED,
            position = PhysicalPosition(12.0, 34.0),
        )
        assertEquals(ButtonSource.Mouse(MouseButton.Right), event.button)
        assertEquals(KeyState.Released, event.state)
    }

    @Test
    fun `mapWaylandPointerButton BTN_MIDDLE pressed returns PointerButton Middle Pressed`() {
        val event = mapWaylandPointerButton(
            button = BTN_MIDDLE,
            state = WL_POINTER_BUTTON_STATE_PRESSED,
            position = PhysicalPosition(12.0, 34.0),
        )
        assertEquals(ButtonSource.Mouse(MouseButton.Middle), event.button)
        assertEquals(KeyState.Pressed, event.state)
    }

    @Test
    fun `mapWaylandPointerButton returns WindowEvent_PointerButton`() {
        val event = mapWaylandPointerButton(
            button = BTN_LEFT,
            state = WL_POINTER_BUTTON_STATE_PRESSED,
            position = PhysicalPosition(12.0, 34.0),
        )
        assertIs<WindowEvent.PointerButton>(event)
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

// ============================================================================
// WaylandTouchMapper tests
// ============================================================================

class WaylandTouchMapperTest {

    // ── mapWaylandTouchDown ───────────────────────────────────────────────────

    @Test
    fun `mapWaylandTouchDown produces enter and press`() {
        val events = mapWaylandTouchDown(id = 0, xFixed = 256, yFixed = 512)
        assertIs<WindowEvent.PointerEntered>(events[0])
        assertIs<WindowEvent.PointerButton>(events[1]).also { event ->
            assertEquals(KeyState.Pressed, event.state)
            assertEquals(ButtonSource.Touch(FingerId(0L)), event.button)
        }
    }

    @Test
    fun `mapWaylandTouchDown converts wl_fixed coordinates correctly`() {
        // x = 10.0 → wl_fixed = 10 * 256 = 2560; y = 20.0 → 5120
        val event = assertIs<WindowEvent.PointerButton>(mapWaylandTouchDown(id = 1, xFixed = 2560, yFixed = 5120)[1])
        assertEquals(10.0, event.position.x)
        assertEquals(20.0, event.position.y)
    }

    @Test
    fun `mapWaylandTouchDown preserves finger id`() {
        val event = assertIs<WindowEvent.PointerButton>(mapWaylandTouchDown(id = 3, xFixed = 0, yFixed = 0)[1])
        assertEquals(ButtonSource.Touch(FingerId(3L)), event.button)
    }

    // ── mapWaylandTouchUp ─────────────────────────────────────────────────────

    @Test
    fun `mapWaylandTouchUp produces release and leave`() {
        val events = mapWaylandTouchUp(id = 0, location = PhysicalPosition(1.0, 2.0))
        assertIs<WindowEvent.PointerButton>(events[0]).also { event ->
            assertEquals(KeyState.Released, event.state)
            assertEquals(ButtonSource.Touch(FingerId(0L)), event.button)
        }
        assertIs<WindowEvent.PointerLeft>(events[1]).also { event ->
            assertEquals(PointerKind.Touch, event.kind)
        }
    }

    @Test
    fun `mapWaylandTouchUp preserves finger id`() {
        val event = assertIs<WindowEvent.PointerButton>(mapWaylandTouchUp(id = 5, location = PhysicalPosition(1.0, 2.0))[0])
        assertEquals(ButtonSource.Touch(FingerId(5L)), event.button)
    }

    // ── mapWaylandTouchMotion ─────────────────────────────────────────────────

    @Test
    fun `mapWaylandTouchMotion produces PointerMoved`() {
        val event = mapWaylandTouchMotion(id = 0, xFixed = 256, yFixed = 256)
        assertIs<WindowEvent.PointerMoved>(event)
        assertEquals(PointerSource.Touch(FingerId(0L)), event.source)
    }

    @Test
    fun `mapWaylandTouchMotion converts wl_fixed coordinates correctly`() {
        // x = 5.0 → 1280; y = 7.5 → 1920
        val event = mapWaylandTouchMotion(id = 2, xFixed = 1280, yFixed = 1920)
        assertEquals(5.0, event.position.x)
        assertEquals(7.5, event.position.y)
    }

    // ── mapWaylandTouchCancel ─────────────────────────────────────────────────

    @Test
    fun `mapWaylandTouchCancel produces release and leave`() {
        val events = mapWaylandTouchCancel(id = 0, location = PhysicalPosition(1.0, 2.0))
        assertIs<WindowEvent.PointerButton>(events[0]).also { event ->
            assertEquals(KeyState.Released, event.state)
        }
        assertIs<WindowEvent.PointerLeft>(events[1]).also { event ->
            assertEquals(PointerKind.Touch, event.kind)
        }
    }

    @Test
    fun `mapWaylandTouchCancel preserves finger id`() {
        val event = assertIs<WindowEvent.PointerButton>(mapWaylandTouchCancel(id = 7, location = PhysicalPosition(1.0, 2.0))[0])
        assertEquals(ButtonSource.Touch(FingerId(7L)), event.button)
    }
}

// ============================================================================
// seatHasCapability tests (WaylandSeat)
// ============================================================================

class WaylandSeatCapabilityTest {

    // Capability bit values as defined by the Wayland protocol:
    //   POINTER  = 1
    //   KEYBOARD = 2
    //   TOUCH    = 4

    @Test
    fun `seatHasCapability returns true when bit is set`() {
        // caps = KEYBOARD | TOUCH = 6
        assertTrue(seatHasCapability(caps = 6, capBit = 2)) // KEYBOARD
        assertTrue(seatHasCapability(caps = 6, capBit = 4)) // TOUCH
    }

    @Test
    fun `seatHasCapability returns false when bit is absent`() {
        // caps = KEYBOARD | TOUCH = 6, no POINTER
        assertFalse(seatHasCapability(caps = 6, capBit = 1)) // POINTER absent
    }

    @Test
    fun `seatHasCapability returns false for zero capabilities`() {
        assertFalse(seatHasCapability(caps = 0, capBit = 1))
        assertFalse(seatHasCapability(caps = 0, capBit = 2))
        assertFalse(seatHasCapability(caps = 0, capBit = 4))
    }

    @Test
    fun `seatHasCapability handles full bitmask`() {
        // All three capabilities present (1 | 2 | 4 = 7)
        assertTrue(seatHasCapability(caps = 7, capBit = 1))
        assertTrue(seatHasCapability(caps = 7, capBit = 2))
        assertTrue(seatHasCapability(caps = 7, capBit = 4))
    }

    @Test
    fun `seatHasCapability pointer-only seat`() {
        // Only pointer available (typical desktop mouse-only setup)
        assertTrue(seatHasCapability(caps = 1, capBit = 1))  // POINTER present
        assertFalse(seatHasCapability(caps = 1, capBit = 2)) // KEYBOARD absent
        assertFalse(seatHasCapability(caps = 1, capBit = 4)) // TOUCH absent
    }
}
