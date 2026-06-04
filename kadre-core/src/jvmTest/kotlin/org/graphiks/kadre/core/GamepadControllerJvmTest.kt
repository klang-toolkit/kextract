package org.graphiks.kadre.core

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class GamepadControllerJvmTest {
    @Test
    fun `controller creation`() {
        val controller = GamepadController(PlatformGamepadBackend())
        assertTrue(controller.isSupported)
    }

    @Test
    fun `pollEvents returns empty on JVM stub`() {
        val controller = GamepadController(PlatformGamepadBackend())
        assertTrue(controller.pollEvents().isEmpty())
    }

    @Test
    fun `allGamepads returns empty on JVM stub`() {
        val controller = GamepadController(PlatformGamepadBackend())
        assertTrue(controller.allGamepads().isEmpty())
    }

    @Test
    fun `gamepad returns null for any id`() {
        val controller = GamepadController(PlatformGamepadBackend())
        assertEquals(null, controller.gamepad(GamepadId(1)))
        assertEquals(null, controller.gamepad(GamepadId(99)))
    }
}
