/**
 * Unit tests for X11KeyMapper, X11MouseMapper and X11DrawMapper.
 *
 * These tests are purely in-memory (no FFM, no libX11):
 * - The keysyms are tested via the KEYSYM_TABLE table.
 * - The mouse events are tested via MemorySegments allocated in Java.
 * - The ConfigureNotify/Expose events are tested with synthetic segments.
 *
 * , #62, #63.
 */
package org.graphiks.kadre.x11

import org.graphiks.kadre.core.Key
import org.graphiks.kadre.core.KeyState
import org.graphiks.kadre.core.Modifiers
import org.graphiks.kadre.core.MouseButton
import org.graphiks.kadre.core.ButtonSource
import org.graphiks.kadre.core.PointerKind
import org.graphiks.kadre.core.WindowEvent
import java.lang.foreign.Arena
import java.lang.foreign.MemorySegment
import java.lang.foreign.ValueLayout
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs
import kotlin.test.assertNull

// ── Helpers ───────────────────────────────────────────────────────────────────

/** Allocates a 96-byte XEvent segment, zero-initialized. */
private fun xEventSegment(arena: Arena): MemorySegment =
    arena.allocate(96L)

/** Sets the type at offset 0. */
private fun MemorySegment.setType(type: Int): MemorySegment {
    set(ValueLayout.JAVA_INT, 0L, type)
    return this
}

/** Sets an Int at the given offset. */
private fun MemorySegment.setInt(offset: Long, value: Int): MemorySegment {
    set(ValueLayout.JAVA_INT, offset, value)
    return this
}

/** Sets a Long at the given offset. */
private fun MemorySegment.setLong(offset: Long, value: Long): MemorySegment {
    set(ValueLayout.JAVA_LONG, offset, value)
    return this
}

// ── X11KeyMapper tests ────────────────────────────────────────────────────────

class X11KeyMapperTest {

    @Test
    fun `keysym table — lowercase letters a-z map to Key_A-Z`() {
        for (i in 0..25) {
            val keysym = 0x61 + i      // 'a'–'z'
            val expected = Key.entries[i] // Key.A..Key.Z (first 26 ordinals)
            val actual = KEYSYM_TABLE[keysym]
            assertEquals(expected, actual,
                "Keysym 0x${keysym.toString(16)} should map to $expected")
        }
    }

    @Test
    fun `keysym table — uppercase letters A-Z map to Key_A-Z`() {
        for (i in 0..25) {
            val keysym = 0x41 + i      // 'A'–'Z'
            val expected = Key.entries[i]
            val actual = KEYSYM_TABLE[keysym]
            assertEquals(expected, actual,
                "Keysym 0x${keysym.toString(16)} should map to $expected")
        }
    }

    @Test
    fun `keysym table — digits 0-9 map to Key_Digit0-9`() {
        val digits = listOf(
            Key.Digit0, Key.Digit1, Key.Digit2, Key.Digit3, Key.Digit4,
            Key.Digit5, Key.Digit6, Key.Digit7, Key.Digit8, Key.Digit9
        )
        for (i in 0..9) {
            val keysym = 0x30 + i
            assertEquals(digits[i], KEYSYM_TABLE[keysym],
                "Keysym 0x${keysym.toString(16)} should map to ${digits[i]}")
        }
    }

    @Test
    fun `keysym table — special keys`() {
        assertEquals(Key.Backspace, KEYSYM_TABLE[0xFF08])
        assertEquals(Key.Tab,       KEYSYM_TABLE[0xFF09])
        assertEquals(Key.Enter,     KEYSYM_TABLE[0xFF0D])
        assertEquals(Key.Escape,    KEYSYM_TABLE[0xFF1B])
        assertEquals(Key.Space,     KEYSYM_TABLE[0x0020])
    }

    @Test
    fun `keysym table — navigation keys`() {
        assertEquals(Key.ArrowLeft,  KEYSYM_TABLE[0xFF51])
        assertEquals(Key.ArrowUp,    KEYSYM_TABLE[0xFF52])
        assertEquals(Key.ArrowRight, KEYSYM_TABLE[0xFF53])
        assertEquals(Key.ArrowDown,  KEYSYM_TABLE[0xFF54])
    }

    @Test
    fun `keysym table — function keys F1-F12`() {
        val fKeys = listOf(Key.F1, Key.F2, Key.F3, Key.F4, Key.F5, Key.F6,
                           Key.F7, Key.F8, Key.F9, Key.F10, Key.F11, Key.F12)
        for (i in 0..11) {
            val keysym = 0xFFBE + i
            assertEquals(fKeys[i], KEYSYM_TABLE[keysym],
                "Keysym 0x${keysym.toString(16)} should map to ${fKeys[i]}")
        }
    }

    @Test
    fun `keysym table — modifiers`() {
        assertEquals(Key.ShiftLeft,    KEYSYM_TABLE[0xFFE1])
        assertEquals(Key.ShiftRight,   KEYSYM_TABLE[0xFFE2])
        assertEquals(Key.ControlLeft,  KEYSYM_TABLE[0xFFE3])
        assertEquals(Key.ControlRight, KEYSYM_TABLE[0xFFE4])
        assertEquals(Key.AltLeft,      KEYSYM_TABLE[0xFFE9])
        assertEquals(Key.AltRight,     KEYSYM_TABLE[0xFFEA])
        assertEquals(Key.MetaLeft,     KEYSYM_TABLE[0xFFEB])
        assertEquals(Key.MetaRight,    KEYSYM_TABLE[0xFFEC])
    }

    @Test
    fun `stateToModifiers — no modifier`() {
        val mods = stateToModifiers(0)
        assertEquals(Modifiers.NONE, mods)
    }

    @Test
    fun `stateToModifiers — ShiftMask 0x01`() {
        val mods = stateToModifiers(0x01)
        assertEquals(true,  mods.shift)
        assertEquals(false, mods.ctrl)
        assertEquals(false, mods.alt)
        assertEquals(false, mods.meta)
    }

    @Test
    fun `stateToModifiers — ControlMask 0x04`() {
        val mods = stateToModifiers(0x04)
        assertEquals(false, mods.shift)
        assertEquals(true,  mods.ctrl)
    }

    @Test
    fun `stateToModifiers — Mod1Mask 0x08 = Alt`() {
        val mods = stateToModifiers(0x08)
        assertEquals(true, mods.alt)
    }

    @Test
    fun `stateToModifiers — Mod4Mask 0x40 = Meta`() {
        val mods = stateToModifiers(0x40)
        assertEquals(true, mods.meta)
    }

    @Test
    fun `stateToModifiers — Shift+Ctrl combined`() {
        val mods = stateToModifiers(0x01 or 0x04)
        assertEquals(true, mods.shift)
        assertEquals(true, mods.ctrl)
        assertEquals(false, mods.alt)
    }

    @Test
    fun `fromXEvent — KeyPress with keysym 0x61 returns Key_A Pressed`() {
        Arena.ofConfined().use { arena ->
            val seg = xEventSegment(arena)
                .setType(KeyPress)
                .setInt(64L, 0)    // state: no modifier
                .setInt(68L, 38)   // keycode = 38 (arbitrary)

            X11KeyMapper.resetState()
            val event = X11KeyMapper.fromXEvent(seg, KeyPress, keysym = 0x61)

            assertIs<WindowEvent.KeyboardInput>(event)
            assertEquals(Key.A,           event.key)
            assertEquals(KeyState.Pressed, event.state)
            assertEquals(false,            event.isRepeat)
        }
    }

    @Test
    fun `fromXEvent — KeyRelease returns Released`() {
        Arena.ofConfined().use { arena ->
            val seg = xEventSegment(arena)
                .setType(KeyRelease)
                .setInt(64L, 0)
                .setInt(68L, 38)

            X11KeyMapper.resetState()
            val event = X11KeyMapper.fromXEvent(seg, KeyRelease, keysym = 0x61)

            assertIs<WindowEvent.KeyboardInput>(event)
            assertEquals(KeyState.Released, event.state)
            assertEquals(false, event.isRepeat)
        }
    }

    @Test
    fun `fromXEvent — repeat detected on double KeyPress same keycode`() {
        Arena.ofConfined().use { arena ->
            val seg = xEventSegment(arena)
                .setType(KeyPress)
                .setInt(64L, 0)
                .setInt(68L, 38)

            X11KeyMapper.resetState()
            // First press — not a repeat
            val first = X11KeyMapper.fromXEvent(seg, KeyPress, keysym = 0x61)
            assertIs<WindowEvent.KeyboardInput>(first)
            assertEquals(false, first.isRepeat)

            // Second press without release — repeat
            val second = X11KeyMapper.fromXEvent(seg, KeyPress, keysym = 0x61)
            assertIs<WindowEvent.KeyboardInput>(second)
            assertEquals(true, second.isRepeat)
        }
    }

    @Test
    fun `fromXEvent — unknown keysym returns Key_Unknown`() {
        Arena.ofConfined().use { arena ->
            val seg = xEventSegment(arena)
                .setType(KeyPress)
                .setInt(64L, 0)
                .setInt(68L, 99)

            X11KeyMapper.resetState()
            val event = X11KeyMapper.fromXEvent(seg, KeyPress, keysym = 0xDEAD)
            assertIs<WindowEvent.KeyboardInput>(event)
            assertEquals(Key.Unknown, event.key)
        }
    }

    @Test
    fun `fromXEvent — modifiers Shift+Alt forwarded correctly`() {
        Arena.ofConfined().use { arena ->
            val seg = xEventSegment(arena)
                .setType(KeyPress)
                .setInt(64L, 0x01 or 0x08)   // ShiftMask | Mod1Mask (Alt)
                .setInt(68L, 38)

            X11KeyMapper.resetState()
            val event = X11KeyMapper.fromXEvent(seg, KeyPress, keysym = 0x41)
            assertIs<WindowEvent.KeyboardInput>(event)
            assertEquals(true,  event.modifiers.shift)
            assertEquals(true,  event.modifiers.alt)
            assertEquals(false, event.modifiers.ctrl)
            assertEquals(false, event.modifiers.meta)
        }
    }
}

// ── X11MouseMapper tests ──────────────────────────────────────────────────────

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
                .setLong(64L, wmDelete)   // data.l[0] = wmDeleteWindow

            val event = X11DrawMapper.fromXEvent(seg, ClientMessage, null, wmDelete)
            assertEquals(WindowEvent.CloseRequested, event)
        }
    }

    @Test
    fun `ClientMessage with different atom returns null`() {
        Arena.ofConfined().use { arena ->
            val wmDelete = 0x1234_5678L
            val seg = xEventSegment(arena)
                .setType(ClientMessage)
                .setLong(64L, 0x9999L)   // different from wmDeleteWindow

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
