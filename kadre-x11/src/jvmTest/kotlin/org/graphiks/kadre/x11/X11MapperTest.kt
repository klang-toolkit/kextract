/**
 * Unit tests for X11 keyboard, mouse and draw mappers.
 */
package org.graphiks.kadre.x11

import org.graphiks.kadre.core.ButtonSource
import org.graphiks.kadre.core.KeyCode
import org.graphiks.kadre.core.KeyState
import org.graphiks.kadre.core.KeyboardModifiers
import org.graphiks.kadre.core.MouseButton
import org.graphiks.kadre.core.ModifierKeyState
import org.graphiks.kadre.core.NativeKeyCode
import org.graphiks.kadre.core.PhysicalKey
import org.graphiks.kadre.core.PointerKind
import org.graphiks.kadre.core.WindowEvent
import java.lang.foreign.Arena
import java.lang.foreign.MemorySegment
import java.lang.foreign.ValueLayout
import kotlin.test.AfterTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs
import kotlin.test.assertNull
import kotlin.test.assertTrue

private fun xEventSegment(arena: Arena): MemorySegment = arena.allocate(96L)

private fun MemorySegment.setType(type: Int): MemorySegment {
    set(ValueLayout.JAVA_INT, 0L, type)
    return this
}

private fun MemorySegment.setInt(offset: Long, value: Int): MemorySegment {
    set(ValueLayout.JAVA_INT, offset, value)
    return this
}

private fun MemorySegment.setLong(offset: Long, value: Long): MemorySegment {
    set(ValueLayout.JAVA_LONG, offset, value)
    return this
}

private fun MemorySegment.setByte(offset: Long, value: Int): MemorySegment {
    set(ValueLayout.JAVA_BYTE, offset, value.toByte())
    return this
}

class X11MapperTest {
    @AfterTest
    fun reset() {
        X11KeyMapper.resetState()
        X11LiveRepeatTracker.reset()
    }

    @Test
    fun `keysym table maps common keys to KeyCode`() {
        assertEquals(KeyCode.KeyA, KEYSYM_TABLE[0x61])
        assertEquals(KeyCode.KeyZ, KEYSYM_TABLE[0x5A])
        assertEquals(KeyCode.Digit0, KEYSYM_TABLE[0x30])
        assertEquals(KeyCode.Enter, KEYSYM_TABLE[0xFF0D])
        assertEquals(KeyCode.ArrowLeft, KEYSYM_TABLE[0xFF51])
        assertEquals(KeyCode.F12, KEYSYM_TABLE[0xFFC9])
        assertEquals(KeyCode.MetaRight, KEYSYM_TABLE[0xFFEC])
    }

    @Test
    fun `stateToModifiers decodes modifier mask`() {
        val mods = stateToModifiers(0x01 or 0x04 or 0x08 or 0x40)
        val expected = KeyboardModifiers(
            KeyboardModifiers.SHIFT or KeyboardModifiers.CTRL or KeyboardModifiers.ALT or KeyboardModifiers.META,
        )
        assertEquals(expected, mods)
    }

    @Test
    fun `live x11StateToModifiers decodes meta modifier mask`() {
        val mods = x11StateToModifiers(0x40)
        assertEquals(KeyboardModifiers.Meta, mods)
    }

    @Test
    fun `fromXEvent maps key press to KeyInput`() {
        val event = assertIs<WindowEvent.KeyInput>(
            X11KeyMapper.fromXEvent(xEvent(state = 0x01, keycode = 38), KeyPress, keysym = 0x61),
        ).event

        assertEquals(PhysicalKey.Code(KeyCode.KeyA), event.physicalKey)
        assertEquals(KeyState.Pressed, event.state)
        assertEquals(KeyboardModifiers.Shift, event.modifiers)
    }

    @Test
    fun `fromXEvent detects repeats`() {
        X11KeyMapper.fromXEvent(xEvent(keycode = 38), KeyPress, keysym = 0x61)
        val repeat = X11KeyMapper.fromXEvent(xEvent(keycode = 38), KeyPress, keysym = 0x61)!!.event
        assertTrue(repeat.repeat)
    }

    @Test
    fun `live repeat tracker marks second press as repeat and resets on release`() {
        assertEquals(false, X11LiveRepeatTracker.update(38, KeyState.Pressed))
        assertEquals(true, X11LiveRepeatTracker.update(38, KeyState.Pressed))
        assertEquals(false, X11LiveRepeatTracker.update(38, KeyState.Released))
        assertEquals(false, X11LiveRepeatTracker.update(38, KeyState.Pressed))
    }

    @Test
    fun `modifierStateFrom tracks physical sides and logical modifiers`() {
        val initial = X11KeyMapper.initialModifierState()
        val leftPressed = X11KeyMapper.modifierStateFrom(initial, KeyCode.ShiftLeft, KeyState.Pressed)
        val bothPressed = X11KeyMapper.modifierStateFrom(leftPressed, KeyCode.ShiftRight, KeyState.Pressed)
        val rightReleased = X11KeyMapper.modifierStateFrom(bothPressed, KeyCode.ShiftRight, KeyState.Released)

        assertTrue(leftPressed.logical.shift)
        assertEquals(ModifierKeyState.Pressed, leftPressed.physical.leftShift)
        assertEquals(ModifierKeyState.Pressed, bothPressed.physical.leftShift)
        assertEquals(ModifierKeyState.Pressed, bothPressed.physical.rightShift)
        assertTrue(rightReleased.logical.shift)
        assertEquals(ModifierKeyState.Pressed, rightReleased.physical.leftShift)
        assertEquals(ModifierKeyState.Released, rightReleased.physical.rightShift)
    }

    @Test
    fun `fromXEvent uses post-transition modifiers for modifier key events`() {
        val shiftPress = assertIs<WindowEvent.KeyInput>(
            X11KeyMapper.fromXEvent(xEvent(keycode = 50), KeyPress, keysym = 0xFFE1),
        ).event
        val shiftRelease = assertIs<WindowEvent.KeyInput>(
            X11KeyMapper.fromXEvent(xEvent(state = 0x01, keycode = 50), KeyRelease, keysym = 0xFFE1),
        ).event

        assertTrue(shiftPress.modifiers.shift)
        assertEquals(KeyState.Pressed, shiftPress.state)
        assertEquals(KeyboardModifiers.NONE, shiftRelease.modifiers)
        assertEquals(KeyState.Released, shiftRelease.state)
    }

    @Test
    fun `resetModifiersChangedIfNeeded clears tracked modifiers once`() {
        val tracker = X11KeyboardModifierTracker()
        val pressed = tracker.modifierStateFor(KeyCode.ControlLeft, KeyState.Pressed)
        assertIs<WindowEvent.ModifiersChanged>(tracker.modifiersChangedIfNeeded(pressed))

        val reset = assertIs<WindowEvent.ModifiersChanged>(tracker.resetIfNeeded())
        assertEquals(KeyboardModifiers.NONE, reset.state.logical)
        assertEquals(null, tracker.resetIfNeeded())
    }

    @Test
    fun `keymap snapshot initializes pressed modifier state`() {
        Arena.ofConfined().use { arena ->
            val keymap = arena.allocate(32L)
                .setByte((50 / 8).toLong(), 1 shl (50 % 8))
                .setByte((37 / 8).toLong(), 1 shl (37 % 8))

            val state = x11ModifierStateFromPressedKeycodes(x11PressedKeycodesFromKeymap(keymap))

            assertTrue(state.logical.shift)
            assertTrue(state.logical.ctrl)
            assertEquals(ModifierKeyState.Pressed, state.physical.leftShift)
            assertEquals(ModifierKeyState.Pressed, state.physical.leftCtrl)
        }
    }

    @Test
    fun `tracker focus initialization emits current modifiers after reset`() {
        val tracker = X11KeyboardModifierTracker()
        val pressed = x11ModifierStateFromPressedKeycodes(listOf(50))

        val initialized = assertIs<WindowEvent.ModifiersChanged>(tracker.initializeIfNeeded(pressed))
        assertTrue(initialized.state.logical.shift)
        assertEquals(null, tracker.initializeIfNeeded(pressed))
        assertEquals(KeyboardModifiers.NONE, tracker.resetIfNeeded()!!.state.logical)
        val reinitialized = assertIs<WindowEvent.ModifiersChanged>(tracker.initializeIfNeeded(pressed))
        assertTrue(reinitialized.state.logical.shift)
    }

    @Test
    fun `unknown keysym preserves native keycode`() {
        val event = X11KeyMapper.fromXEvent(xEvent(keycode = 255), KeyPress, keysym = 0)!!.event
        assertEquals(PhysicalKey.Native(NativeKeyCode.X11(255)), event.physicalKey)
    }
}

private fun xEvent(state: Int = 0, keycode: Int): MemorySegment {
    val segment = MemorySegment.ofArray(LongArray(12))
    segment.set(ValueLayout.JAVA_INT, 64L, state)
    segment.set(ValueLayout.JAVA_INT, 68L, keycode)
    return segment
}
class X11MouseMapperTest {

    @Test
    fun `ButtonPress button 1 returns PointerButton Left Pressed`() {
        Arena.ofConfined().use { arena ->
            val seg = xEventSegment(arena)
                .setType(ButtonPress)
                .setInt(68L, 1)   // button = Left

            val event = X11MouseMapper.fromXEvent(seg, ButtonPress)
            assertIs<WindowEvent.PointerButton>(event)
            assertEquals(ButtonSource.Mouse(MouseButton.Left), event.button)
            assertEquals(KeyState.Pressed,   event.state)
        }
    }

    @Test
    fun `ButtonRelease button 3 returns PointerButton Right Released`() {
        Arena.ofConfined().use { arena ->
            val seg = xEventSegment(arena)
                .setType(ButtonRelease)
                .setInt(68L, 3)   // button = Right

            val event = X11MouseMapper.fromXEvent(seg, ButtonRelease)
            assertIs<WindowEvent.PointerButton>(event)
            assertEquals(ButtonSource.Mouse(MouseButton.Right), event.button)
            assertEquals(KeyState.Released,   event.state)
        }
    }

    @Test
    fun `ButtonPress button 2 returns PointerButton Middle Pressed`() {
        Arena.ofConfined().use { arena ->
            val seg = xEventSegment(arena)
                .setType(ButtonPress)
                .setInt(68L, 2)

            val event = X11MouseMapper.fromXEvent(seg, ButtonPress)
            assertIs<WindowEvent.PointerButton>(event)
            assertEquals(ButtonSource.Mouse(MouseButton.Middle), event.button)
        }
    }

    @Test
    fun `ButtonPress button 4 returns MouseWheel positive deltaY scroll down`() {
        Arena.ofConfined().use { arena ->
            val seg = xEventSegment(arena)
                .setType(ButtonPress)
                .setInt(68L, 4)   // scroll down

            val event = X11MouseMapper.fromXEvent(seg, ButtonPress)
            assertIs<WindowEvent.MouseWheel>(event)
            assertEquals(0.0,  event.deltaX)
            assertEquals(1.0,  event.deltaY)
        }
    }

    @Test
    fun `ButtonPress button 5 returns MouseWheel negative deltaY scroll up`() {
        Arena.ofConfined().use { arena ->
            val seg = xEventSegment(arena)
                .setType(ButtonPress)
                .setInt(68L, 5)   // scroll up

            val event = X11MouseMapper.fromXEvent(seg, ButtonPress)
            assertIs<WindowEvent.MouseWheel>(event)
            assertEquals(0.0,  event.deltaX)
            assertEquals(-1.0, event.deltaY)
        }
    }

    @Test
    fun `ButtonPress button 6 returns MouseWheel negative deltaX scroll left`() {
        Arena.ofConfined().use { arena ->
            val seg = xEventSegment(arena)
                .setType(ButtonPress)
                .setInt(68L, 6)   // scroll left

            val event = X11MouseMapper.fromXEvent(seg, ButtonPress)
            assertIs<WindowEvent.MouseWheel>(event)
            assertEquals(-1.0, event.deltaX)
            assertEquals(0.0,  event.deltaY)
        }
    }

    @Test
    fun `ButtonPress button 7 returns MouseWheel positive deltaX scroll right`() {
        Arena.ofConfined().use { arena ->
            val seg = xEventSegment(arena)
                .setType(ButtonPress)
                .setInt(68L, 7)   // scroll right

            val event = X11MouseMapper.fromXEvent(seg, ButtonPress)
            assertIs<WindowEvent.MouseWheel>(event)
            assertEquals(1.0, event.deltaX)
            assertEquals(0.0, event.deltaY)
        }
    }

    @Test
    fun `ButtonRelease button 4 returns null — no wheel release event`() {
        Arena.ofConfined().use { arena ->
            val seg = xEventSegment(arena)
                .setType(ButtonRelease)
                .setInt(68L, 4)   // scroll down release

            val event = X11MouseMapper.fromXEvent(seg, ButtonRelease)
            assertNull(event, "A wheel ButtonRelease must not generate an event")
        }
    }

    @Test
    fun `MotionNotify returns PointerMoved with correct coordinates`() {
        Arena.ofConfined().use { arena ->
            val seg = xEventSegment(arena)
                .setType(MotionNotify)
                .setInt(48L, 320)   // x
                .setInt(52L, 240)   // y

            val event = X11MouseMapper.fromXEvent(seg, MotionNotify)
            assertIs<WindowEvent.PointerMoved>(event)
            assertEquals(320.0, event.position.x)
            assertEquals(240.0, event.position.y)
        }
    }

    @Test
    fun `EnterNotify returns PointerEntered`() {
        Arena.ofConfined().use { arena ->
            val seg = xEventSegment(arena).setType(EnterNotify)
            val event = X11MouseMapper.fromXEvent(seg, EnterNotify)
            assertIs<WindowEvent.PointerEntered>(event)
            assertEquals(PointerKind.Mouse, event.kind)
        }
    }

    @Test
    fun `LeaveNotify returns PointerLeft`() {
        Arena.ofConfined().use { arena ->
            val seg = xEventSegment(arena).setType(LeaveNotify)
            val event = X11MouseMapper.fromXEvent(seg, LeaveNotify)
            assertIs<WindowEvent.PointerLeft>(event)
            assertEquals(PointerKind.Mouse, event.kind)
        }
    }

    @Test
    fun `FocusIn returns Focused gained=true`() {
        Arena.ofConfined().use { arena ->
            val seg = xEventSegment(arena).setType(FocusIn)
            val event = X11MouseMapper.fromXEvent(seg, FocusIn)
            assertIs<WindowEvent.Focused>(event)
            assertEquals(true, event.gained)
        }
    }

    @Test
    fun `FocusOut returns Focused gained=false`() {
        Arena.ofConfined().use { arena ->
            val seg = xEventSegment(arena).setType(FocusOut)
            val event = X11MouseMapper.fromXEvent(seg, FocusOut)
            assertIs<WindowEvent.Focused>(event)
            assertEquals(false, event.gained)
        }
    }

    @Test
    fun `unknown type returns null`() {
        Arena.ofConfined().use { arena ->
            val seg = xEventSegment(arena).setType(99)
            val event = X11MouseMapper.fromXEvent(seg, 99)
            assertNull(event)
        }
    }
}

// ── X11DrawMapper tests ───────────────────────────────────────────────────────

class X11DrawMapperTest {

    @Test
    fun `Expose count=0 returns RedrawRequested`() {
        Arena.ofConfined().use { arena ->
            val seg = xEventSegment(arena)
                .setType(Expose)
                .setInt(40L, 0)   // count = 0

            val event = X11DrawMapper.fromXEvent(seg, Expose, null, 0L)
            assertEquals(WindowEvent.RedrawRequested, event)
        }
    }

    @Test
    fun `Expose count=2 returns null — not the last expose`() {
        Arena.ofConfined().use { arena ->
            val seg = xEventSegment(arena)
                .setType(Expose)
                .setInt(40L, 2)   // count = 2 (two additional Expose events will follow)

            val event = X11DrawMapper.fromXEvent(seg, Expose, null, 0L)
            assertNull(event)
        }
    }

    @Test
    fun `ConfigureNotify — null window returns Resized with dimensions`() {
        Arena.ofConfined().use { arena ->
            val seg = xEventSegment(arena)
                .setType(ConfigureNotify)
                .setInt(24L, 10)    // x
                .setInt(28L, 20)    // y
                .setInt(32L, 1280)  // width
                .setInt(36L, 720)   // height

            val event = X11DrawMapper.fromXEvent(seg, ConfigureNotify, null, 0L)
            assertIs<WindowEvent.Resized>(event)
            assertEquals(1280, event.size.width)
            assertEquals(720,  event.size.height)
        }
    }

    @Test
    fun `ClientMessage with atom matching wmDeleteWindow returns CloseRequested`() {
        Arena.ofConfined().use { arena ->
            val wmDelete = 0x1234_5678L
            val seg = xEventSegment(arena)
                .setType(ClientMessage)
                .setLong(XCLIENT_DATA_L0_OFFSET, wmDelete)   // data.l[0] = wmDeleteWindow

            val event = X11DrawMapper.fromXEvent(seg, ClientMessage, null, wmDelete)
            assertEquals(WindowEvent.CloseRequested, event)
        }
    }

    @Test
    fun `ClientMessage ignores stale non-canonical data offset`() {
        Arena.ofConfined().use { arena ->
            val wmDelete = 0x1234_5678L
            val seg = xEventSegment(arena)
                .setType(ClientMessage)
                .setLong(64L, wmDelete)

            val event = X11DrawMapper.fromXEvent(seg, ClientMessage, null, wmDelete)
            assertNull(event)
        }
    }

    @Test
    fun `ClientMessage with different atom returns null`() {
        Arena.ofConfined().use { arena ->
            val wmDelete = 0x1234_5678L
            val seg = xEventSegment(arena)
                .setType(ClientMessage)
                .setLong(XCLIENT_DATA_L0_OFFSET, 0x9999L)   // different from wmDeleteWindow

            val event = X11DrawMapper.fromXEvent(seg, ClientMessage, null, wmDelete)
            assertNull(event)
        }
    }

    @Test
    fun `unknown type returns null`() {
        Arena.ofConfined().use { arena ->
            val seg = xEventSegment(arena).setType(99)
            val event = X11DrawMapper.fromXEvent(seg, 99, null, 0L)
            assertNull(event)
        }
    }
}

// ── parseXftDpi tests ─────────────────────────────────────────────────────────

class ParseXftDpiTest {

    @Test
    fun `Xft_dpi 96 returns factor 1_0`() {
        val resources = "Xft.dpi:\t96\nXft.antialias:\t1\n"
        assertEquals(1.0, parseXftDpi(resources))
    }

    @Test
    fun `Xft_dpi 192 returns factor 2_0`() {
        val resources = "Xft.dpi:\t192\n"
        assertEquals(2.0, parseXftDpi(resources))
    }

    @Test
    fun `Xft_dpi 144 returns factor 1_5`() {
        val resources = "Xft.dpi:\t144\n"
        assertEquals(1.5, parseXftDpi(resources))
    }

    @Test
    fun `empty string returns 1_0`() {
        assertEquals(1.0, parseXftDpi(""))
    }

    @Test
    fun `Xft_dpi absent returns 1_0`() {
        val resources = "Xft.antialias:\t1\nXft.hinting:\t1\n"
        assertEquals(1.0, parseXftDpi(resources))
    }

    @Test
    fun `Xft_dpi with space instead of tab`() {
        val resources = "Xft.dpi: 120\n"
        assertEquals(120.0 / 96.0, parseXftDpi(resources))
    }
}
