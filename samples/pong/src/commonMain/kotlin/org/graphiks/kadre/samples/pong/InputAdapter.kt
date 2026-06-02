package org.graphiks.kadre.samples.pong

import org.graphiks.kadre.core.ButtonSource
import org.graphiks.kadre.core.KeyCode
import org.graphiks.kadre.core.KeyState
import org.graphiks.kadre.core.PhysicalKey
import org.graphiks.kadre.core.PhysicalSize
import org.graphiks.kadre.core.PointerKind
import org.graphiks.kadre.core.PointerSource
import org.graphiks.kadre.core.WindowEvent

/**
 * Cross-platform input adapter for Pong.
 * Maps keyboard (desktop/web) and touch (mobile/web) to PaddleInput.
 *
 * .
 */
class InputAdapter {

    var playerInput: PaddleInput = PaddleInput.NONE
        private set

    fun onKey(event: WindowEvent.KeyInput) {
        playerInput = when {
            event.event.physicalKey == PhysicalKey.Code(KeyCode.ArrowUp) && event.event.state == KeyState.Pressed -> PaddleInput.UP
            event.event.physicalKey == PhysicalKey.Code(KeyCode.ArrowDown) && event.event.state == KeyState.Pressed -> PaddleInput.DOWN
            event.event.physicalKey in setOf(PhysicalKey.Code(KeyCode.ArrowUp), PhysicalKey.Code(KeyCode.ArrowDown)) && event.event.state == KeyState.Released -> PaddleInput.NONE
            else -> playerInput
        }
    }

    /**
     * Handles a touch contact.
     *
     * Play zone: right side of the screen (x > width/2).
     * Upper half → UP, lower half → DOWN.
     * Touch ended/cancelled → NONE.
     */
    fun onPointerButton(event: WindowEvent.PointerButton, screenSize: PhysicalSize<Int>) {
        val isTouch = event.button is ButtonSource.Touch
        if (!isTouch) return
        playerInput = when (event.state) {
            KeyState.Pressed -> paddleInputFor(event.position.x, event.position.y, screenSize)
            KeyState.Released -> PaddleInput.NONE
        }
    }

    fun onPointerMoved(event: WindowEvent.PointerMoved, screenSize: PhysicalSize<Int>) {
        if (event.source !is PointerSource.Touch) return
        playerInput = paddleInputFor(event.position.x, event.position.y, screenSize)
    }

    fun onPointerLeft(event: WindowEvent.PointerLeft) {
        if (event.kind == PointerKind.Touch) {
            playerInput = PaddleInput.NONE
        }
    }

    private fun paddleInputFor(x: Double, y: Double, screenSize: PhysicalSize<Int>): PaddleInput =
        if (x > screenSize.width / 2.0) {
            if (y < screenSize.height / 2.0) PaddleInput.UP else PaddleInput.DOWN
        } else {
            playerInput
        }
}
