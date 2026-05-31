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
    fun `balle au centre paddle au centre → None`() {
        val ai = PongAi(reactionLagMs = 0L)
        assertEquals(PaddleInput.NONE, ai.suggest(state, 0L))
    }

    @Test
    fun `balle en haut paddle au centre → UP`() {
        val ai = PongAi(reactionLagMs = 0L)
        val s = state.copy(ball = state.ball.copy(y = 0.1))
        assertEquals(PaddleInput.UP, ai.suggest(s, 0L))
    }

    @Test
    fun `balle en bas paddle au centre → DOWN`() {
        val ai = PongAi(reactionLagMs = 0L)
        val s = state.copy(ball = state.ball.copy(y = 0.9))
        assertEquals(PaddleInput.DOWN, ai.suggest(s, 0L))
    }

    @Test
    fun `lag empêche mise à jour immédiate`() {
        val ai = PongAi(reactionLagMs = 100L)
        // Ball at center → None (target = 0.5)
        ai.suggest(state, 0L)
        // Ball moves up but lag not elapsed → still None
        val s2 = state.copy(ball = state.ball.copy(y = 0.1))
        assertEquals(PaddleInput.NONE, ai.suggest(s2, 50L))
    }

    @Test
    fun `lag écoulé → met à jour la cible`() {
        val ai = PongAi(reactionLagMs = 100L)
        ai.suggest(state, 0L)
        val s2 = state.copy(ball = state.ball.copy(y = 0.1))
        // Lag elapsed → UP
        assertEquals(PaddleInput.UP, ai.suggest(s2, 150L))
    }

    @Test
    fun `reset remet la cible à 0,5`() {
        val ai = PongAi(reactionLagMs = 0L)
        val s = state.copy(ball = state.ball.copy(y = 0.9))
        ai.suggest(s, 0L) // target = 0.9
        ai.reset()
        // After reset: target = 0.5, paddle at 0.5 → None
        assertEquals(PaddleInput.NONE, ai.suggest(state, 1L))
    }
}
