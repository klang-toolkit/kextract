package org.graphiks.kadre.core

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class GamepadTypesTest {

    @Test
    fun `Button fromOrdinal 0 returns South`() {
        assertEquals(Button.South, Button.fromOrdinal(0))
    }

    @Test
    fun `Button fromOrdinal last entry returns last button`() {
        val lastIndex = Button.entries.size - 1
        assertEquals(Button.entries.last(), Button.fromOrdinal(lastIndex))
    }

    @Test
    fun `Button fromOrdinal out of bounds returns South`() {
        assertEquals(Button.South, Button.fromOrdinal(Button.entries.size))
    }

    @Test
    fun `Button fromOrdinal negative returns South`() {
        assertEquals(Button.South, Button.fromOrdinal(-1))
    }

    @Test
    fun `Axis fromOrdinal 0 returns LeftStickX`() {
        assertEquals(Axis.LeftStickX, Axis.fromOrdinal(0))
    }

    @Test
    fun `Axis fromOrdinal last entry returns last Axis`() {
        val lastIndex = Axis.entries.size - 1
        assertEquals(Axis.entries.last(), Axis.fromOrdinal(lastIndex))
    }

    @Test
    fun `Axis fromOrdinal out of bounds returns LeftStickX`() {
        assertEquals(Axis.LeftStickX, Axis.fromOrdinal(Axis.entries.size))
    }

    @Test
    fun `GamepadState defaults to empty maps`() {
        val state = GamepadState()
        assertTrue(state.buttons.isEmpty())
        assertTrue(state.axes.isEmpty())
    }

    @Test
    fun `GamepadState with buttons includes them`() {
        val state = GamepadState(buttons = mapOf(Button.South to 1.0f))
        assertEquals(1.0f, state.buttons[Button.South])
    }

    @Test
    fun `GamepadId holds value`() {
        val id = GamepadId(42)
        assertEquals(42, id.value)
    }

    @Test
    fun `ButtonPressed event constructs correctly`() {
        val id = GamepadId(1)
        val event = GamepadEvent.ButtonPressed(id = id, button = Button.East, time = 100L)
        assertEquals(id, event.id)
        assertEquals(Button.East, event.button)
        assertEquals(100L, event.time)
    }

    @Test
    fun `ButtonReleased event constructs correctly`() {
        val id = GamepadId(2)
        val event = GamepadEvent.ButtonReleased(id = id, button = Button.West, time = 200L)
        assertEquals(id, event.id)
        assertEquals(Button.West, event.button)
        assertEquals(200L, event.time)
    }

    @Test
    fun `AxisChanged event constructs correctly`() {
        val id = GamepadId(3)
        val event = GamepadEvent.AxisChanged(id = id, axis = Axis.RightStickX, value = 0.5f, time = 300L)
        assertEquals(id, event.id)
        assertEquals(Axis.RightStickX, event.axis)
        assertEquals(0.5f, event.value)
        assertEquals(300L, event.time)
    }

    @Test
    fun `Connected event constructs correctly`() {
        val id = GamepadId(0)
        val event = GamepadEvent.Connected(id = id, name = "Xbox Controller", time = 400L)
        assertEquals(id, event.id)
        assertEquals("Xbox Controller", event.name)
        assertEquals(400L, event.time)
    }

    @Test
    fun `Disconnected event constructs correctly`() {
        val id = GamepadId(0)
        val event = GamepadEvent.Disconnected(id = id, time = 500L)
        assertEquals(id, event.id)
        assertEquals(500L, event.time)
    }

    @Test
    fun `PowerInfo Unknown is data object`() {
        assertTrue(PowerInfo.Unknown is PowerInfo)
    }

    @Test
    fun `PowerInfo Wired is data object`() {
        assertTrue(PowerInfo.Wired is PowerInfo)
    }

    @Test
    fun `PowerInfo Charged is data object`() {
        assertTrue(PowerInfo.Charged is PowerInfo)
    }

    @Test
    fun `PowerInfo Discharging carries battery level`() {
        val info: PowerInfo = PowerInfo.Discharging(75)
        assertEquals(75, (info as PowerInfo.Discharging).battery)
    }

    @Test
    fun `PowerInfo Charging carries battery level`() {
        val info: PowerInfo = PowerInfo.Charging(50)
        assertEquals(50, (info as PowerInfo.Charging).battery)
    }

    @Test
    fun `GamepadEvent sealed interface allows exhaustive when`() {
        val id = GamepadId(0)
        val events: List<GamepadEvent> = listOf(
            GamepadEvent.ButtonPressed(id, Button.South),
            GamepadEvent.ButtonReleased(id, Button.North),
            GamepadEvent.AxisChanged(id, Axis.LeftStickY, 0.0f),
            GamepadEvent.Connected(id, "gamepad"),
            GamepadEvent.Disconnected(id),
        )
        val descriptions = events.map { event ->
            when (event) {
                is GamepadEvent.ButtonPressed -> "pressed ${event.button}"
                is GamepadEvent.ButtonReleased -> "released ${event.button}"
                is GamepadEvent.AxisChanged -> "axis ${event.axis}=${event.value}"
                is GamepadEvent.Connected -> "connected '${event.name}'"
                is GamepadEvent.Disconnected -> "disconnected"
            }
        }
        assertEquals(5, descriptions.size)
    }
}
