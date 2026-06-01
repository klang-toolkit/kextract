package org.graphiks.kadre.android

import android.view.KeyEvent
import org.graphiks.kadre.core.Key
import org.graphiks.kadre.core.Modifiers
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

/**
 * Unit tests for [AndroidKeyMapper].
 *
 * Pure mapping logic over [android.view.KeyEvent] constants (compile-time int
 * values), so no emulator or Robolectric is required.
 */
class AndroidKeyMapperTest {

    @Test
    fun `fromKeyCode maps letters`() {
        assertEquals(Key.A, AndroidKeyMapper.fromKeyCode(KeyEvent.KEYCODE_A))
        assertEquals(Key.Z, AndroidKeyMapper.fromKeyCode(KeyEvent.KEYCODE_Z))
    }

    @Test
    fun `fromKeyCode maps digits`() {
        assertEquals(Key.Digit0, AndroidKeyMapper.fromKeyCode(KeyEvent.KEYCODE_0))
        assertEquals(Key.Digit9, AndroidKeyMapper.fromKeyCode(KeyEvent.KEYCODE_9))
    }

    @Test
    fun `fromKeyCode maps function keys`() {
        assertEquals(Key.F1, AndroidKeyMapper.fromKeyCode(KeyEvent.KEYCODE_F1))
        assertEquals(Key.F12, AndroidKeyMapper.fromKeyCode(KeyEvent.KEYCODE_F12))
    }

    @Test
    fun `fromKeyCode maps the D-pad to arrow keys`() {
        assertEquals(Key.ArrowUp, AndroidKeyMapper.fromKeyCode(KeyEvent.KEYCODE_DPAD_UP))
        assertEquals(Key.ArrowDown, AndroidKeyMapper.fromKeyCode(KeyEvent.KEYCODE_DPAD_DOWN))
        assertEquals(Key.ArrowLeft, AndroidKeyMapper.fromKeyCode(KeyEvent.KEYCODE_DPAD_LEFT))
        assertEquals(Key.ArrowRight, AndroidKeyMapper.fromKeyCode(KeyEvent.KEYCODE_DPAD_RIGHT))
    }

    @Test
    fun `fromKeyCode maps special keys`() {
        assertEquals(Key.Space, AndroidKeyMapper.fromKeyCode(KeyEvent.KEYCODE_SPACE))
        assertEquals(Key.Enter, AndroidKeyMapper.fromKeyCode(KeyEvent.KEYCODE_ENTER))
        assertEquals(Key.Escape, AndroidKeyMapper.fromKeyCode(KeyEvent.KEYCODE_ESCAPE))
        assertEquals(Key.Backspace, AndroidKeyMapper.fromKeyCode(KeyEvent.KEYCODE_DEL))
        assertEquals(Key.Tab, AndroidKeyMapper.fromKeyCode(KeyEvent.KEYCODE_TAB))
    }

    @Test
    fun `fromKeyCode maps left and right modifiers`() {
        assertEquals(Key.ShiftLeft, AndroidKeyMapper.fromKeyCode(KeyEvent.KEYCODE_SHIFT_LEFT))
        assertEquals(Key.ShiftRight, AndroidKeyMapper.fromKeyCode(KeyEvent.KEYCODE_SHIFT_RIGHT))
        assertEquals(Key.ControlLeft, AndroidKeyMapper.fromKeyCode(KeyEvent.KEYCODE_CTRL_LEFT))
        assertEquals(Key.AltRight, AndroidKeyMapper.fromKeyCode(KeyEvent.KEYCODE_ALT_RIGHT))
        assertEquals(Key.MetaLeft, AndroidKeyMapper.fromKeyCode(KeyEvent.KEYCODE_META_LEFT))
    }

    @Test
    fun `fromKeyCode returns Unknown for unmapped keys`() {
        assertEquals(Key.Unknown, AndroidKeyMapper.fromKeyCode(KeyEvent.KEYCODE_VOLUME_UP))
        assertEquals(Key.Unknown, AndroidKeyMapper.fromKeyCode(KeyEvent.KEYCODE_BACK))
    }

    @Test
    fun `modifiersFrom returns NONE for an empty metaState`() {
        assertEquals(Modifiers.NONE, AndroidKeyMapper.modifiersFrom(0))
    }

    @Test
    fun `modifiersFrom decodes individual modifiers`() {
        assertTrue(AndroidKeyMapper.modifiersFrom(KeyEvent.META_SHIFT_ON).shift)
        assertTrue(AndroidKeyMapper.modifiersFrom(KeyEvent.META_CTRL_ON).ctrl)
        assertTrue(AndroidKeyMapper.modifiersFrom(KeyEvent.META_ALT_ON).alt)
        assertTrue(AndroidKeyMapper.modifiersFrom(KeyEvent.META_META_ON).meta)
    }

    @Test
    fun `modifiersFrom combines several modifiers`() {
        val mods = AndroidKeyMapper.modifiersFrom(KeyEvent.META_SHIFT_ON or KeyEvent.META_CTRL_ON)
        assertTrue(mods.shift)
        assertTrue(mods.ctrl)
        assertFalse(mods.alt)
        assertFalse(mods.meta)
    }
}
