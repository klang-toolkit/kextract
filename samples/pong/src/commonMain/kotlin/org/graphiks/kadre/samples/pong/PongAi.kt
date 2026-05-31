package org.graphiks.kadre.samples.pong

/**
 * Simple AI for Pong: follows the ball with a configurable reaction delay.
 *
 * Sprint 5.
 */
class PongAi(private val reactionLagMs: Long = 80L) {

    private var lastTargetY: Double = 0.5
    private var lastUpdateMs: Long = 0L

    /**
     * Computes the recommended input for the AI paddle.
     *
     * Updates [lastTargetY] every [reactionLagMs] milliseconds.
     * The paddle moves up if it is above the target, down otherwise.
     */
    fun suggest(state: GameState, currentTimeMs: Long): PaddleInput {
        if (currentTimeMs - lastUpdateMs >= reactionLagMs) {
            lastTargetY = state.ball.y
            lastUpdateMs = currentTimeMs
        }

        val paddleY = state.ai.y
        val deadZone = 0.05

        return when {
            paddleY > lastTargetY + deadZone -> PaddleInput.UP
            paddleY < lastTargetY - deadZone -> PaddleInput.DOWN
            else -> PaddleInput.NONE
        }
    }

    /** Resets the AI's memory (useful after a ball reset). */
    fun reset() {
        lastTargetY = 0.5
        lastUpdateMs = 0L
    }
}
