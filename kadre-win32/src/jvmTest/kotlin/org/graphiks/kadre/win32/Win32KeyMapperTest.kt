/**
 * Unit tests for Win32KeyMapper.
 *
 * Verifies that the Win32 virtual key codes (VK_*) are correctly
 * translated into kadre logical keys [Key].
 *
 * These tests run on all platforms (macOS, Linux, Windows)
 * because Win32KeyMapper is a pure Kotlin mapping table without FFM.
 */
package org.graphiks.kadre.win32

import org.graphiks.kadre.core.KeyCode
import kotlin.test.Test
import kotlin.test.assertEquals

class Win32KeyMapperTest {

    @Test
    fun `letters A to Z are correctly mapped`() {
        assertEquals(KeyCode.KeyA, Win32KeyMapper.keyCode(VK_A))
        assertEquals(KeyCode.KeyB, Win32KeyMapper.keyCode(VK_B))
        assertEquals(KeyCode.KeyC, Win32KeyMapper.keyCode(VK_C))
        assertEquals(KeyCode.KeyM, Win32KeyMapper.keyCode(VK_M))
        assertEquals(KeyCode.KeyZ, Win32KeyMapper.keyCode(VK_Z))
    }

    @Test
    fun `digits 0 to 9 are correctly mapped`() {
        assertEquals(KeyCode.Digit0, Win32KeyMapper.keyCode(VK_0))
        assertEquals(KeyCode.Digit1, Win32KeyMapper.keyCode(VK_1))
        assertEquals(KeyCode.Digit5, Win32KeyMapper.keyCode(VK_5))
        assertEquals(KeyCode.Digit9, Win32KeyMapper.keyCode(VK_9))
    }

    @Test
    fun `function keys F1 to F12 are correctly mapped`() {
        assertEquals(KeyCode.F1,  Win32KeyMapper.keyCode(VK_F1))
        assertEquals(KeyCode.F5,  Win32KeyMapper.keyCode(VK_F5))
        assertEquals(KeyCode.F12, Win32KeyMapper.keyCode(VK_F12))
    }

    @Test
    fun `navigation keys are correctly mapped`() {
        assertEquals(KeyCode.ArrowLeft,  Win32KeyMapper.keyCode(VK_LEFT))
        assertEquals(KeyCode.ArrowRight, Win32KeyMapper.keyCode(VK_RIGHT))
        assertEquals(KeyCode.ArrowUp,    Win32KeyMapper.keyCode(VK_UP))
        assertEquals(KeyCode.ArrowDown,  Win32KeyMapper.keyCode(VK_DOWN))
    }

    @Test
    fun `special keys are correctly mapped`() {
        assertEquals(KeyCode.Space,     Win32KeyMapper.keyCode(VK_SPACE))
        assertEquals(KeyCode.Enter,     Win32KeyMapper.keyCode(VK_RETURN))
        assertEquals(KeyCode.Escape,    Win32KeyMapper.keyCode(VK_ESCAPE))
        assertEquals(KeyCode.Backspace, Win32KeyMapper.keyCode(VK_BACK))
        assertEquals(KeyCode.Tab,       Win32KeyMapper.keyCode(VK_TAB))
    }

    @Test
    fun `left and right modifiers are correctly mapped`() {
        assertEquals(KeyCode.ShiftLeft,    Win32KeyMapper.keyCode(VK_LSHIFT))
        assertEquals(KeyCode.ShiftRight,   Win32KeyMapper.keyCode(VK_RSHIFT))
        assertEquals(KeyCode.ControlLeft,  Win32KeyMapper.keyCode(VK_LCONTROL))
        assertEquals(KeyCode.ControlRight, Win32KeyMapper.keyCode(VK_RCONTROL))
        assertEquals(KeyCode.AltLeft,      Win32KeyMapper.keyCode(VK_LMENU))
        assertEquals(KeyCode.AltRight,     Win32KeyMapper.keyCode(VK_RMENU))
        assertEquals(KeyCode.MetaLeft,     Win32KeyMapper.keyCode(VK_LWIN))
        assertEquals(KeyCode.MetaRight,    Win32KeyMapper.keyCode(VK_RWIN))
    }

    @Test
    fun `generic modifiers map to the left variant`() {
        assertEquals(KeyCode.ShiftLeft,   Win32KeyMapper.keyCode(VK_SHIFT))
        assertEquals(KeyCode.ControlLeft, Win32KeyMapper.keyCode(VK_CONTROL))
        assertEquals(KeyCode.AltLeft,     Win32KeyMapper.keyCode(VK_MENU))
    }

    @Test
    fun `unknown VK code returns Key Unknown`() {
        assertEquals(null, Win32KeyMapper.keyCode(0x00))
        assertEquals(null, Win32KeyMapper.keyCode(0xFF))
        assertEquals(null, Win32KeyMapper.keyCode(-1))
    }
}
