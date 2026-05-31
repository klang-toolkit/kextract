package org.graphiks.kadre.samples.pong

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class GameStateTest {

    // -------------------------------------------------------------------------
    // Initial state
    // -------------------------------------------------------------------------

    @Test
    fun `initial state has valid ball position`() {
        val s = GameState.INITIAL
        assertTrue(s.ball.x in 0.0..1.0)
        assertTrue(s.ball.y in 0.0..1.0)
    }

    @Test
    fun `initial state has zero scores`() {
        val s = GameState.INITIAL
        assertEquals(0, s.score.player)
        assertEquals(0, s.score.ai)
    }

    @Test
    fun `initial paddles are centered`() {
        val s = GameState.INITIAL
        assertEquals(0.5, s.player.y)
        assertEquals(0.5, s.ai.y)
    }

    @Test
    fun `initial ball has positive vx`() {
        assertTrue(GameState.INITIAL.ball.vx > 0.0)
    }

    @Test
    fun `initial paddle height is 0_2`() {
        assertEquals(0.2, GameState.INITIAL.player.height)
        assertEquals(0.2, GameState.INITIAL.ai.height)
    }

    // -------------------------------------------------------------------------
    // Paddle movement — player
    // -------------------------------------------------------------------------

    @Test
    fun `player moves up`() {
        val s = GameState.INITIAL.tick(1.0, PaddleInput.UP, PaddleInput.NONE)
        assertTrue(s.player.y < GameState.INITIAL.player.y)
    }

    @Test
    fun `player moves down`() {
        val s = GameState.INITIAL.tick(1.0, PaddleInput.DOWN, PaddleInput.NONE)
        assertTrue(s.player.y > GameState.INITIAL.player.y)
    }

    @Test
    fun `player stays still on NONE`() {
        val s = GameState.INITIAL.tick(1.0, PaddleInput.NONE, PaddleInput.NONE)
        assertEquals(GameState.INITIAL.player.y, s.player.y)
    }

    @Test
    fun `player paddle clamped at top boundary`() {
        // Move up many times
        var state = GameState.INITIAL
        repeat(20) { state = state.tick(1.0, PaddleInput.UP, PaddleInput.NONE) }
        assertTrue(state.player.y >= state.player.height / 2)
    }

    @Test
    fun `player paddle clamped at bottom boundary`() {
        var state = GameState.INITIAL
        repeat(20) { state = state.tick(1.0, PaddleInput.DOWN, PaddleInput.NONE) }
        assertTrue(state.player.y <= 1.0 - state.player.height / 2)
    }

    @Test
    fun `player paddle speed is proportional to dt`() {
        val s1 = GameState.INITIAL.tick(0.1, PaddleInput.DOWN, PaddleInput.NONE)
        val s2 = GameState.INITIAL.tick(0.2, PaddleInput.DOWN, PaddleInput.NONE)
        assertTrue(s2.player.y > s1.player.y)
    }

    // -------------------------------------------------------------------------
    // Paddle movement — AI
    // -------------------------------------------------------------------------

    @Test
    fun `ai moves up`() {
        val s = GameState.INITIAL.tick(1.0, PaddleInput.NONE, PaddleInput.UP)
        assertTrue(s.ai.y < GameState.INITIAL.ai.y)
    }

    @Test
    fun `ai moves down`() {
        val s = GameState.INITIAL.tick(1.0, PaddleInput.NONE, PaddleInput.DOWN)
        assertTrue(s.ai.y > GameState.INITIAL.ai.y)
    }

    @Test
    fun `ai stays still on NONE`() {
        val s = GameState.INITIAL.tick(1.0, PaddleInput.NONE, PaddleInput.NONE)
        assertEquals(GameState.INITIAL.ai.y, s.ai.y)
    }

    @Test
    fun `ai paddle clamped at top boundary`() {
        var state = GameState.INITIAL
        repeat(20) { state = state.tick(1.0, PaddleInput.NONE, PaddleInput.UP) }
        assertTrue(state.ai.y >= state.ai.height / 2)
    }

    @Test
    fun `ai paddle clamped at bottom boundary`() {
        var state = GameState.INITIAL
        repeat(20) { state = state.tick(1.0, PaddleInput.NONE, PaddleInput.DOWN) }
        assertTrue(state.ai.y <= 1.0 - state.ai.height / 2)
    }

    // -------------------------------------------------------------------------
    // Ball movement
    // -------------------------------------------------------------------------

    @Test
    fun `ball moves in dt proportional to velocity`() {
        // Use a state where ball won't collide
        val state = GameState(
            player = Paddle(y = 0.5),
            ai = Paddle(y = 0.5),
            ball = Ball(x = 0.5, y = 0.5, vx = 0.3, vy = 0.1),
            score = Score()
        )
        val next = state.tick(0.1, PaddleInput.NONE, PaddleInput.NONE)
        assertEquals(0.5 + 0.3 * 0.1, next.ball.x, 1e-9)
        assertEquals(0.5 + 0.1 * 0.1, next.ball.y, 1e-9)
    }

    @Test
    fun `ball x increases when vx positive`() {
        val state = GameState(
            player = Paddle(y = 0.5),
            ai = Paddle(y = 0.5),
            ball = Ball(x = 0.5, y = 0.5, vx = 0.3, vy = 0.0),
            score = Score()
        )
        val next = state.tick(0.1, PaddleInput.NONE, PaddleInput.NONE)
        assertTrue(next.ball.x > 0.5)
    }

    @Test
    fun `ball x decreases when vx negative`() {
        val state = GameState(
            player = Paddle(y = -0.5), // paddle out of the way
            ai = Paddle(y = 0.5),
            ball = Ball(x = 0.5, y = 0.5, vx = -0.3, vy = 0.0),
            score = Score()
        )
        val next = state.tick(0.1, PaddleInput.NONE, PaddleInput.NONE)
        assertTrue(next.ball.x < 0.5)
    }

    // -------------------------------------------------------------------------
    // Wall collisions (top / bottom)
    // -------------------------------------------------------------------------

    @Test
    fun `ball bounces off top wall`() {
        val state = GameState(
            player = Paddle(y = 0.5),
            ai = Paddle(y = 0.5),
            ball = Ball(x = 0.5, y = 0.01, vx = 0.0, vy = -0.5),
            score = Score()
        )
        val next = state.tick(0.1, PaddleInput.NONE, PaddleInput.NONE)
        assertTrue(next.ball.vy > 0, "vy should flip positive after top wall bounce")
        assertTrue(next.ball.y >= 0.0)
    }

    @Test
    fun `ball bounces off bottom wall`() {
        val state = GameState(
            player = Paddle(y = 0.5),
            ai = Paddle(y = 0.5),
            ball = Ball(x = 0.5, y = 0.99, vx = 0.0, vy = 0.5),
            score = Score()
        )
        val next = state.tick(0.1, PaddleInput.NONE, PaddleInput.NONE)
        assertTrue(next.ball.vy < 0, "vy should flip negative after bottom wall bounce")
        assertTrue(next.ball.y <= 1.0)
    }

    @Test
    fun `ball y clamped at 0 on top wall hit`() {
        val state = GameState(
            player = Paddle(y = 0.5),
            ai = Paddle(y = 0.5),
            ball = Ball(x = 0.5, y = 0.001, vx = 0.0, vy = -1.0),
            score = Score()
        )
        val next = state.tick(1.0, PaddleInput.NONE, PaddleInput.NONE)
        assertTrue(next.ball.y >= 0.0)
    }

    @Test
    fun `ball y clamped at 1 on bottom wall hit`() {
        val state = GameState(
            player = Paddle(y = 0.5),
            ai = Paddle(y = 0.5),
            ball = Ball(x = 0.5, y = 0.999, vx = 0.0, vy = 1.0),
            score = Score()
        )
        val next = state.tick(1.0, PaddleInput.NONE, PaddleInput.NONE)
        assertTrue(next.ball.y <= 1.0)
    }

    // -------------------------------------------------------------------------
    // Paddle collisions
    // -------------------------------------------------------------------------

    @Test
    fun `ball bounces off player paddle`() {
        // Ball moving left, just in front of player paddle, paddle centered on ball
        val state = GameState(
            player = Paddle(y = 0.5),
            ai = Paddle(y = 0.5),
            ball = Ball(x = 0.045, y = 0.5, vx = -0.5, vy = 0.0),
            score = Score()
        )
        val next = state.tick(0.1, PaddleInput.NONE, PaddleInput.NONE)
        assertTrue(next.ball.vx > 0, "vx should flip positive after player paddle bounce")
    }

    @Test
    fun `ball bounces off ai paddle`() {
        // Ball moving right, just in front of ai paddle, paddle centered on ball
        val state = GameState(
            player = Paddle(y = 0.5),
            ai = Paddle(y = 0.5),
            ball = Ball(x = 0.955, y = 0.5, vx = 0.5, vy = 0.0),
            score = Score()
        )
        val next = state.tick(0.1, PaddleInput.NONE, PaddleInput.NONE)
        assertTrue(next.ball.vx < 0, "vx should flip negative after ai paddle bounce")
    }

    @Test
    fun `paddle collision applies angle based on hit position`() {
        // Hit at the top edge of the player paddle → vy should be negative (upward)
        val paddle = Paddle(y = 0.5)
        val hitY = 0.5 - paddle.height / 2 + 0.01  // near top edge
        val state = GameState(
            player = paddle,
            ai = Paddle(y = 0.5),
            ball = Ball(x = 0.045, y = hitY, vx = -0.5, vy = 0.0),
            score = Score()
        )
        val next = state.tick(0.1, PaddleInput.NONE, PaddleInput.NONE)
        assertTrue(next.ball.vy < 0, "ball should deflect upward when hitting top of paddle")
    }

    @Test
    fun `ball not affected by player paddle if miss`() {
        // Ball moving left but paddle is at y=0.9, ball at y=0.1 → miss
        val state = GameState(
            player = Paddle(y = 0.9),
            ai = Paddle(y = 0.5),
            ball = Ball(x = 0.045, y = 0.1, vx = -0.5, vy = 0.0),
            score = Score()
        )
        val next = state.tick(0.01, PaddleInput.NONE, PaddleInput.NONE)
        // Ball should still be moving left (no collision since miss)
        assertTrue(next.ball.vx < 0, "ball should pass through if it misses the paddle")
    }

    // -------------------------------------------------------------------------
    // Scoring
    // -------------------------------------------------------------------------

    @Test
    fun `ai scores when ball exits left`() {
        val state = GameState(
            player = Paddle(y = 0.9), // paddle out of the way
            ai = Paddle(y = 0.5),
            ball = Ball(x = 0.005, y = 0.5, vx = -1.0, vy = 0.0),
            score = Score(player = 0, ai = 0)
        )
        val next = state.tick(0.1, PaddleInput.NONE, PaddleInput.NONE)
        assertEquals(1, next.score.ai)
        assertEquals(0, next.score.player)
    }

    @Test
    fun `player scores when ball exits right`() {
        val state = GameState(
            player = Paddle(y = 0.5),
            ai = Paddle(y = 0.9), // paddle out of the way
            ball = Ball(x = 0.995, y = 0.5, vx = 1.0, vy = 0.0),
            score = Score(player = 0, ai = 0)
        )
        val next = state.tick(0.1, PaddleInput.NONE, PaddleInput.NONE)
        assertEquals(1, next.score.player)
        assertEquals(0, next.score.ai)
    }

    @Test
    fun `score accumulates over multiple goals`() {
        var state = GameState(
            player = Paddle(y = 0.9),
            ai = Paddle(y = 0.5),
            ball = Ball(x = 0.005, y = 0.5, vx = -1.0, vy = 0.0),
            score = Score(player = 2, ai = 3)
        )
        state = state.tick(0.1, PaddleInput.NONE, PaddleInput.NONE)
        assertEquals(4, state.score.ai)
        assertEquals(2, state.score.player)
    }

    // -------------------------------------------------------------------------
    // Ball reset after scoring
    // -------------------------------------------------------------------------

    @Test
    fun `ball resets to center after ai scores`() {
        val state = GameState(
            player = Paddle(y = 0.9),
            ai = Paddle(y = 0.5),
            ball = Ball(x = 0.005, y = 0.5, vx = -1.0, vy = 0.0),
            score = Score()
        )
        val next = state.tick(0.1, PaddleInput.NONE, PaddleInput.NONE)
        assertEquals(0.5, next.ball.x)
        assertEquals(0.5, next.ball.y)
    }

    @Test
    fun `ball resets to center after player scores`() {
        val state = GameState(
            player = Paddle(y = 0.5),
            ai = Paddle(y = 0.9),
            ball = Ball(x = 0.995, y = 0.5, vx = 1.0, vy = 0.0),
            score = Score()
        )
        val next = state.tick(0.1, PaddleInput.NONE, PaddleInput.NONE)
        assertEquals(0.5, next.ball.x)
        assertEquals(0.5, next.ball.y)
    }

    @Test
    fun `ball resets with positive vx toward ai after ai scores`() {
        val state = GameState(
            player = Paddle(y = 0.9),
            ai = Paddle(y = 0.5),
            ball = Ball(x = 0.005, y = 0.5, vx = -1.0, vy = 0.0),
            score = Score()
        )
        val next = state.tick(0.1, PaddleInput.NONE, PaddleInput.NONE)
        assertTrue(next.ball.vx > 0, "after AI scores, ball should go toward AI (positive vx)")
    }

    @Test
    fun `ball resets with negative vx toward player after player scores`() {
        val state = GameState(
            player = Paddle(y = 0.5),
            ai = Paddle(y = 0.9),
            ball = Ball(x = 0.995, y = 0.5, vx = 1.0, vy = 0.0),
            score = Score()
        )
        val next = state.tick(0.1, PaddleInput.NONE, PaddleInput.NONE)
        assertTrue(next.ball.vx < 0, "after player scores, ball should go toward player (negative vx)")
    }

    @Test
    fun `ball vy is non-zero after reset`() {
        val state = GameState(
            player = Paddle(y = 0.9),
            ai = Paddle(y = 0.5),
            ball = Ball(x = 0.005, y = 0.5, vx = -1.0, vy = 0.0),
            score = Score()
        )
        val next = state.tick(0.1, PaddleInput.NONE, PaddleInput.NONE)
        assertTrue(next.ball.vy != 0.0)
    }

    // -------------------------------------------------------------------------
    // Data class equality and copy
    // -------------------------------------------------------------------------

    @Test
    fun `GameState copy produces equal value`() {
        val s = GameState.INITIAL
        val copy = s.copy()
        assertEquals(s, copy)
    }

    @Test
    fun `Score copy increments correctly`() {
        val score = Score(player = 1, ai = 2)
        assertEquals(Score(player = 2, ai = 2), score.copy(player = 2))
        assertEquals(Score(player = 1, ai = 3), score.copy(ai = 3))
    }

    @Test
    fun `Paddle data equality`() {
        assertEquals(Paddle(y = 0.5, height = 0.2), Paddle(y = 0.5))
    }

    @Test
    fun `Ball data equality`() {
        assertEquals(Ball(0.5, 0.5, 0.3, 0.1), Ball(0.5, 0.5, 0.3, 0.1))
    }

    // -------------------------------------------------------------------------
    // Edge cases
    // -------------------------------------------------------------------------

    @Test
    fun `zero dt produces no movement`() {
        val s = GameState.INITIAL.tick(0.0, PaddleInput.DOWN, PaddleInput.UP)
        assertEquals(GameState.INITIAL.player.y, s.player.y)
        assertEquals(GameState.INITIAL.ai.y, s.ai.y)
        assertEquals(GameState.INITIAL.ball.x, s.ball.x)
        assertEquals(GameState.INITIAL.ball.y, s.ball.y)
    }

    @Test
    fun `both paddles can move simultaneously`() {
        val s = GameState.INITIAL.tick(0.5, PaddleInput.UP, PaddleInput.DOWN)
        assertTrue(s.player.y < 0.5)
        assertTrue(s.ai.y > 0.5)
    }

    @Test
    fun `paddle top boundary exact - height over 2`() {
        val paddle = Paddle(y = 0.1, height = 0.2)
        val state = GameState(
            player = paddle,
            ai = Paddle(y = 0.5),
            ball = Ball(x = 0.5, y = 0.5, vx = 0.1, vy = 0.0),
            score = Score()
        )
        val next = state.tick(1.0, PaddleInput.UP, PaddleInput.NONE)
        assertTrue(next.player.y >= next.player.height / 2,
            "Paddle top: y=${next.player.y} should be >= height/2=${next.player.height / 2}")
    }

    @Test
    fun `paddle bottom boundary exact`() {
        val paddle = Paddle(y = 0.9, height = 0.2)
        val state = GameState(
            player = paddle,
            ai = Paddle(y = 0.5),
            ball = Ball(x = 0.5, y = 0.5, vx = 0.1, vy = 0.0),
            score = Score()
        )
        val next = state.tick(1.0, PaddleInput.DOWN, PaddleInput.NONE)
        assertTrue(next.player.y <= 1.0 - next.player.height / 2,
            "Paddle bottom: y=${next.player.y} should be <= ${1.0 - next.player.height / 2}")
    }
}
