package org.graphiks.kadre.samples.pong

import org.graphiks.kadre.core.ButtonSource
import org.graphiks.kadre.core.FingerId
import org.graphiks.kadre.core.Key
import org.graphiks.kadre.core.KeyState
import org.graphiks.kadre.core.Modifiers
import org.graphiks.kadre.core.PhysicalPosition
import org.graphiks.kadre.core.PhysicalSize
import org.graphiks.kadre.core.PointerKind
import org.graphiks.kadre.core.PointerSource
import org.graphiks.kadre.core.WindowEvent
import kotlin.test.Test
import kotlin.test.assertEquals

class InputAdapterTest {

    private val screenSize = PhysicalSize(800, 600)

    private fun keyEvent(key: Key, state: KeyState) =
        WindowEvent.KeyboardInput(deviceId = null, key = key, state = state, modifiers = Modifiers.NONE)

    private fun touchButton(state: KeyState, x: Double, y: Double, id: Long = 0L) =
        WindowEvent.PointerButton(
            deviceId = null,
            state = state,
            position = PhysicalPosition(x, y),
            primary = true,
            button = ButtonSource.Touch(FingerId(id)),
        )

    private fun touchMove(x: Double, y: Double, id: Long = 0L) =
        WindowEvent.PointerMoved(
            deviceId = null,
            position = PhysicalPosition(x, y),
            primary = true,
            source = PointerSource.Touch(FingerId(id)),
        )

    private fun touchLeft(x: Double, y: Double, id: Long = 0L) =
        WindowEvent.PointerLeft(
            deviceId = null,
            position = PhysicalPosition(x, y),
            primary = id == 0L,
            kind = PointerKind.Touch,
        )

    // -------------------------------------------------------------------------
    // Keyboard tests
    // -------------------------------------------------------------------------

    @Test
    fun `ArrowUp Pressed → UP`() {
        val adapter = InputAdapter()
        adapter.onKey(keyEvent(Key.ArrowUp, KeyState.Pressed))
        assertEquals(PaddleInput.UP, adapter.playerInput)
    }

    @Test
    fun `ArrowDown Pressed → DOWN`() {
        val adapter = InputAdapter()
        adapter.onKey(keyEvent(Key.ArrowDown, KeyState.Pressed))
        assertEquals(PaddleInput.DOWN, adapter.playerInput)
    }

    @Test
    fun `ArrowUp Released → NONE`() {
        val adapter = InputAdapter()
        adapter.onKey(keyEvent(Key.ArrowUp, KeyState.Pressed))
        adapter.onKey(keyEvent(Key.ArrowUp, KeyState.Released))
        assertEquals(PaddleInput.NONE, adapter.playerInput)
    }

    @Test
    fun `ArrowDown Released → NONE`() {
        val adapter = InputAdapter()
        adapter.onKey(keyEvent(Key.ArrowDown, KeyState.Pressed))
        adapter.onKey(keyEvent(Key.ArrowDown, KeyState.Released))
        assertEquals(PaddleInput.NONE, adapter.playerInput)
    }

    @Test
    fun `other key does not affect the state`() {
        val adapter = InputAdapter()
        adapter.onKey(keyEvent(Key.ArrowUp, KeyState.Pressed))
        adapter.onKey(keyEvent(Key.Space, KeyState.Pressed))
        assertEquals(PaddleInput.UP, adapter.playerInput)
    }

    // -------------------------------------------------------------------------
    // Touch tests
    // -------------------------------------------------------------------------

    @Test
    fun `touch Started right top → UP`() {
        val adapter = InputAdapter()
        // x > 400 (right), y < 300 (top)
        adapter.onPointerButton(touchButton(KeyState.Pressed, x = 600.0, y = 100.0), screenSize)
        assertEquals(PaddleInput.UP, adapter.playerInput)
    }

    @Test
    fun `touch Started right bottom → DOWN`() {
        val adapter = InputAdapter()
        // x > 400 (right), y > 300 (bottom)
        adapter.onPointerButton(touchButton(KeyState.Pressed, x = 600.0, y = 500.0), screenSize)
        assertEquals(PaddleInput.DOWN, adapter.playerInput)
    }

    @Test
    fun `touch Started left ignored`() {
        val adapter = InputAdapter()
        // x < 400 (left) → does not change the state
        adapter.onPointerButton(touchButton(KeyState.Pressed, x = 100.0, y = 100.0), screenSize)
        assertEquals(PaddleInput.NONE, adapter.playerInput)
    }

    @Test
    fun `touch Moved right top → UP`() {
        val adapter = InputAdapter()
        adapter.onPointerMoved(touchMove(x = 700.0, y = 50.0), screenSize)
        assertEquals(PaddleInput.UP, adapter.playerInput)
    }

    @Test
    fun `touch Ended → NONE`() {
        val adapter = InputAdapter()
        adapter.onPointerButton(touchButton(KeyState.Pressed, x = 600.0, y = 100.0), screenSize)
        adapter.onPointerButton(touchButton(KeyState.Released, x = 600.0, y = 100.0), screenSize)
        assertEquals(PaddleInput.NONE, adapter.playerInput)
    }

    @Test
    fun `touch Cancelled → NONE`() {
        val adapter = InputAdapter()
        adapter.onPointerButton(touchButton(KeyState.Pressed, x = 600.0, y = 500.0), screenSize)
        adapter.onPointerLeft(touchLeft(x = 600.0, y = 500.0))
        assertEquals(PaddleInput.NONE, adapter.playerInput)
    }

    @Test
    fun `initial state is NONE`() {
        val adapter = InputAdapter()
        assertEquals(PaddleInput.NONE, adapter.playerInput)
    }
}
