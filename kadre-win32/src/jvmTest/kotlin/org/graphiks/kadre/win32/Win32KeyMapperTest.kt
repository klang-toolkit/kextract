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

import org.graphiks.kadre.core.Key
import kotlin.test.Test
import kotlin.test.assertEquals

class Win32KeyMapperTest {

    @Test
    fun `letters A to Z are correctly mapped`() {
        assertEquals(Key.A, Win32KeyMapper.fromVkCode(VK_A))
        assertEquals(Key.B, Win32KeyMapper.fromVkCode(VK_B))
        assertEquals(Key.C, Win32KeyMapper.fromVkCode(VK_C))
        assertEquals(Key.M, Win32KeyMapper.fromVkCode(VK_M))
        assertEquals(Key.Z, Win32KeyMapper.fromVkCode(VK_Z))
    }

    @Test
    fun `digits 0 to 9 are correctly mapped`() {
        assertEquals(Key.Digit0, Win32KeyMapper.fromVkCode(VK_0))
        assertEquals(Key.Digit1, Win32KeyMapper.fromVkCode(VK_1))
        assertEquals(Key.Digit5, Win32KeyMapper.fromVkCode(VK_5))
        assertEquals(Key.Digit9, Win32KeyMapper.fromVkCode(VK_9))
    }

    @Test
    fun `function keys F1 to F12 are correctly mapped`() {
        assertEquals(Key.F1,  Win32KeyMapper.fromVkCode(VK_F1))
        assertEquals(Key.F5,  Win32KeyMapper.fromVkCode(VK_F5))
        assertEquals(Key.F12, Win32KeyMapper.fromVkCode(VK_F12))
    }

    @Test
    fun `navigation keys are correctly mapped`() {
        assertEquals(Key.ArrowLeft,  Win32KeyMapper.fromVkCode(VK_LEFT))
        assertEquals(Key.ArrowRight, Win32KeyMapper.fromVkCode(VK_RIGHT))
        assertEquals(Key.ArrowUp,    Win32KeyMapper.fromVkCode(VK_UP))
        assertEquals(Key.ArrowDown,  Win32KeyMapper.fromVkCode(VK_DOWN))
    }

    @Test
    fun `special keys are correctly mapped`() {
        assertEquals(Key.Space,     Win32KeyMapper.fromVkCode(VK_SPACE))
        assertEquals(Key.Enter,     Win32KeyMapper.fromVkCode(VK_RETURN))
        assertEquals(Key.Escape,    Win32KeyMapper.fromVkCode(VK_ESCAPE))
        assertEquals(Key.Backspace, Win32KeyMapper.fromVkCode(VK_BACK))
        assertEquals(Key.Tab,       Win32KeyMapper.fromVkCode(VK_TAB))
    }

    @Test
    fun `left and right modifiers are correctly mapped`() {
        assertEquals(Key.ShiftLeft,    Win32KeyMapper.fromVkCode(VK_LSHIFT))
        assertEquals(Key.ShiftRight,   Win32KeyMapper.fromVkCode(VK_RSHIFT))
        assertEquals(Key.ControlLeft,  Win32KeyMapper.fromVkCode(VK_LCONTROL))
        assertEquals(Key.ControlRight, Win32KeyMapper.fromVkCode(VK_RCONTROL))
        assertEquals(Key.AltLeft,      Win32KeyMapper.fromVkCode(VK_LMENU))
        assertEquals(Key.AltRight,     Win32KeyMapper.fromVkCode(VK_RMENU))
        assertEquals(Key.MetaLeft,     Win32KeyMapper.fromVkCode(VK_LWIN))
        assertEquals(Key.MetaRight,    Win32KeyMapper.fromVkCode(VK_RWIN))
    }

    @Test
    fun `generic modifiers map to the left variant`() {
        assertEquals(Key.ShiftLeft,   Win32KeyMapper.fromVkCode(VK_SHIFT))
        assertEquals(Key.ControlLeft, Win32KeyMapper.fromVkCode(VK_CONTROL))
        assertEquals(Key.AltLeft,     Win32KeyMapper.fromVkCode(VK_MENU))
    }

    @Test
    fun `unknown VK code returns Key Unknown`() {
        assertEquals(Key.Unknown, Win32KeyMapper.fromVkCode(0x00))
        assertEquals(Key.Unknown, Win32KeyMapper.fromVkCode(0xFF))
        assertEquals(Key.Unknown, Win32KeyMapper.fromVkCode(-1))
    }
}
