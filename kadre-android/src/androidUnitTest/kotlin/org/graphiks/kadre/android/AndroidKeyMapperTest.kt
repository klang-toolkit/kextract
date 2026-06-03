package org.graphiks.kadre.android

import android.view.KeyEvent
import org.graphiks.kadre.core.KeyCode
import org.graphiks.kadre.core.KeyState
import org.graphiks.kadre.core.KeyboardModifiers
import org.graphiks.kadre.core.ModifierKeyState
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
        assertEquals(KeyCode.KeyA, AndroidKeyMapper.keyCode(KeyEvent.KEYCODE_A))
        assertEquals(KeyCode.KeyZ, AndroidKeyMapper.keyCode(KeyEvent.KEYCODE_Z))
    }

    @Test
    fun `fromKeyCode maps digits`() {
        assertEquals(KeyCode.Digit0, AndroidKeyMapper.keyCode(KeyEvent.KEYCODE_0))
        assertEquals(KeyCode.Digit9, AndroidKeyMapper.keyCode(KeyEvent.KEYCODE_9))
    }

    @Test
    fun `fromKeyCode maps function keys`() {
        assertEquals(KeyCode.F1, AndroidKeyMapper.keyCode(KeyEvent.KEYCODE_F1))
        assertEquals(KeyCode.F12, AndroidKeyMapper.keyCode(KeyEvent.KEYCODE_F12))
    }

    @Test
    fun `fromKeyCode maps the D-pad to arrow keys`() {
        assertEquals(KeyCode.ArrowUp, AndroidKeyMapper.keyCode(KeyEvent.KEYCODE_DPAD_UP))
        assertEquals(KeyCode.ArrowDown, AndroidKeyMapper.keyCode(KeyEvent.KEYCODE_DPAD_DOWN))
        assertEquals(KeyCode.ArrowLeft, AndroidKeyMapper.keyCode(KeyEvent.KEYCODE_DPAD_LEFT))
        assertEquals(KeyCode.ArrowRight, AndroidKeyMapper.keyCode(KeyEvent.KEYCODE_DPAD_RIGHT))
    }

    @Test
    fun `fromKeyCode maps special keys`() {
        assertEquals(KeyCode.Space, AndroidKeyMapper.keyCode(KeyEvent.KEYCODE_SPACE))
        assertEquals(KeyCode.Enter, AndroidKeyMapper.keyCode(KeyEvent.KEYCODE_ENTER))
        assertEquals(KeyCode.Escape, AndroidKeyMapper.keyCode(KeyEvent.KEYCODE_ESCAPE))
        assertEquals(KeyCode.Backspace, AndroidKeyMapper.keyCode(KeyEvent.KEYCODE_DEL))
        assertEquals(KeyCode.Tab, AndroidKeyMapper.keyCode(KeyEvent.KEYCODE_TAB))
    }

    @Test
    fun `fromKeyCode maps left and right modifiers`() {
        assertEquals(KeyCode.ShiftLeft, AndroidKeyMapper.keyCode(KeyEvent.KEYCODE_SHIFT_LEFT))
        assertEquals(KeyCode.ShiftRight, AndroidKeyMapper.keyCode(KeyEvent.KEYCODE_SHIFT_RIGHT))
        assertEquals(KeyCode.ControlLeft, AndroidKeyMapper.keyCode(KeyEvent.KEYCODE_CTRL_LEFT))
        assertEquals(KeyCode.AltRight, AndroidKeyMapper.keyCode(KeyEvent.KEYCODE_ALT_RIGHT))
        assertEquals(KeyCode.MetaLeft, AndroidKeyMapper.keyCode(KeyEvent.KEYCODE_META_LEFT))
    }

    @Test
    fun `fromKeyCode returns Unknown for unmapped keys`() {
        assertEquals(null, AndroidKeyMapper.keyCode(KeyEvent.KEYCODE_VOLUME_UP))
        assertEquals(null, AndroidKeyMapper.keyCode(KeyEvent.KEYCODE_BACK))
    }

    @Test
    fun `modifiersFrom returns NONE for an empty metaState`() {
        assertEquals(KeyboardModifiers.NONE, AndroidKeyMapper.modifiersFrom(0))
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

    @Test
    fun `isModifierKey identifies keyboard modifiers`() {
        assertTrue(AndroidKeyMapper.isModifierKey(KeyEvent.KEYCODE_SHIFT_LEFT))
        assertTrue(AndroidKeyMapper.isModifierKey(KeyEvent.KEYCODE_CTRL_RIGHT))
        assertTrue(AndroidKeyMapper.isModifierKey(KeyEvent.KEYCODE_ALT_LEFT))
        assertTrue(AndroidKeyMapper.isModifierKey(KeyEvent.KEYCODE_META_RIGHT))
        assertFalse(AndroidKeyMapper.isModifierKey(KeyEvent.KEYCODE_A))
    }

    @Test
    fun `modifierStateFrom decodes physical left and right modifiers`() {
        val state = AndroidKeyMapper.modifierStateFrom(
            KeyEvent.META_SHIFT_LEFT_ON or
                KeyEvent.META_CTRL_RIGHT_ON or
                KeyEvent.META_ALT_ON or
                KeyEvent.META_ALT_LEFT_ON,
        )

        assertTrue(state.logical.shift)
        assertTrue(state.logical.ctrl)
        assertTrue(state.logical.alt)
        assertEquals(ModifierKeyState.Pressed, state.physical.leftShift)
        assertEquals(ModifierKeyState.Released, state.physical.rightShift)
        assertEquals(ModifierKeyState.Released, state.physical.leftCtrl)
        assertEquals(ModifierKeyState.Pressed, state.physical.rightCtrl)
        assertEquals(ModifierKeyState.Pressed, state.physical.leftAlt)
    }

    @Test
    fun `modifierStateFrom applies current key transition before logical aggregation`() {
        val pressed = AndroidKeyMapper.modifierStateFrom(
            metaState = 0,
            keyCode = KeyEvent.KEYCODE_SHIFT_LEFT,
            state = KeyState.Pressed,
        )
        assertTrue(pressed.logical.shift)
        assertEquals(ModifierKeyState.Pressed, pressed.physical.leftShift)

        val released = AndroidKeyMapper.modifierStateFrom(
            metaState = KeyEvent.META_SHIFT_ON or KeyEvent.META_SHIFT_LEFT_ON,
            keyCode = KeyEvent.KEYCODE_SHIFT_LEFT,
            state = KeyState.Released,
        )
        assertFalse(released.logical.shift)
        assertEquals(ModifierKeyState.Released, released.physical.leftShift)
    }

    @Test
    fun `modifierStateFrom keeps logical modifier active while the opposite side remains pressed`() {
        val releasedLeft = AndroidKeyMapper.modifierStateFrom(
            metaState = KeyEvent.META_SHIFT_ON or KeyEvent.META_SHIFT_LEFT_ON or KeyEvent.META_SHIFT_RIGHT_ON,
            keyCode = KeyEvent.KEYCODE_SHIFT_LEFT,
            state = KeyState.Released,
        )

        assertTrue(releasedLeft.logical.shift)
        assertEquals(ModifierKeyState.Released, releasedLeft.physical.leftShift)
        assertEquals(ModifierKeyState.Pressed, releasedLeft.physical.rightShift)
    }
}
