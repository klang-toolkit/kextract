package org.graphiks.kadre.uikit

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import org.graphiks.kadre.core.KeyState
import org.graphiks.kadre.core.ModifierKeyState

class UiKitKeyMapperTest {
    @Test
    fun modifierStateTracksPressedAndReleasedSides() {
        val shiftLeft = 0xE1L
        val shiftRight = 0xE5L

        val initial = UiKitKeyMapper.initialModifierState()
        val leftPressed = UiKitKeyMapper.modifierStateFrom(initial, shiftLeft, KeyState.Pressed)
        val bothPressed = UiKitKeyMapper.modifierStateFrom(leftPressed, shiftRight, KeyState.Pressed)
        val rightReleased = UiKitKeyMapper.modifierStateFrom(bothPressed, shiftRight, KeyState.Released)
        val allReleased = UiKitKeyMapper.modifierStateFrom(rightReleased, shiftLeft, KeyState.Released)

        assertTrue(leftPressed.logical.shift)
        assertEquals(ModifierKeyState.Pressed, leftPressed.physical.leftShift)
        assertEquals(ModifierKeyState.Pressed, bothPressed.physical.leftShift)
        assertEquals(ModifierKeyState.Pressed, bothPressed.physical.rightShift)
        assertTrue(rightReleased.logical.shift)
        assertEquals(ModifierKeyState.Pressed, rightReleased.physical.leftShift)
        assertEquals(ModifierKeyState.Released, rightReleased.physical.rightShift)
        assertFalse(allReleased.logical.shift)
    }

    @Test
    fun unchangedModifierStateCanBeDeduplicatedByCaller() {
        val controlLeft = 0xE0L

        val initial = UiKitKeyMapper.initialModifierState()
        val firstRelease = UiKitKeyMapper.modifierStateFrom(initial, controlLeft, KeyState.Released)
        val pressed = UiKitKeyMapper.modifierStateFrom(initial, controlLeft, KeyState.Pressed)
        val repeatedPress = UiKitKeyMapper.modifierStateFrom(pressed, controlLeft, KeyState.Pressed)

        assertEquals(initial, firstRelease)
        assertEquals(pressed, repeatedPress)
    }
}
