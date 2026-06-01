package org.graphiks.kadre.samples.pong

import kotlin.test.Test
import kotlin.test.assertEquals

class PongAiTest {
    private val state = GameState(
        player = Paddle(y = 0.5),
        ai = Paddle(y = 0.5),
        ball = Ball(x = 0.5, y = 0.5, vx = 0.5, vy = 0.3),
        score = Score()
    )

    @Test
    fun `ball at center paddle at center → None`() {
        val ai = PongAi(reactionLagMs = 0L)
        assertEquals(PaddleInput.NONE, ai.suggest(state, 0L))
    }

    @Test
    fun `ball at top paddle at center → UP`() {
        val ai = PongAi(reactionLagMs = 0L)
        val s = state.copy(ball = state.ball.copy(y = 0.1))
        assertEquals(PaddleInput.UP, ai.suggest(s, 0L))
    }

    @Test
    fun `ball at bottom paddle at center → DOWN`() {
        val ai = PongAi(reactionLagMs = 0L)
        val s = state.copy(ball = state.ball.copy(y = 0.9))
        assertEquals(PaddleInput.DOWN, ai.suggest(s, 0L))
    }

    @Test
    fun `lag prevents immediate update`() {
        val ai = PongAi(reactionLagMs = 100L)
        // Ball at center → None (target = 0.5)
        ai.suggest(state, 0L)
        // Ball moves up but lag not elapsed → still None
        val s2 = state.copy(ball = state.ball.copy(y = 0.1))
        assertEquals(PaddleInput.NONE, ai.suggest(s2, 50L))
    }

    @Test
    fun `lag elapsed → updates the target`() {
        val ai = PongAi(reactionLagMs = 100L)
        ai.suggest(state, 0L)
        val s2 = state.copy(ball = state.ball.copy(y = 0.1))
        // Lag elapsed → UP
        assertEquals(PaddleInput.UP, ai.suggest(s2, 150L))
    }

    @Test
    fun `reset puts the target back to 0,5`() {
        val ai = PongAi(reactionLagMs = 0L)
        val s = state.copy(ball = state.ball.copy(y = 0.9))
        ai.suggest(s, 0L) // target = 0.9
        ai.reset()
        // After reset: target = 0.5, paddle at 0.5 → None
        assertEquals(PaddleInput.NONE, ai.suggest(state, 1L))
    }
}
