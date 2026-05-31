package org.graphiks.kadre.samples.pong

// Normalized viewport [0..1] coordinates
data class Paddle(val y: Double, val height: Double = 0.2)
data class Ball(val x: Double, val y: Double, val vx: Double, val vy: Double)
data class Score(val player: Int = 0, val ai: Int = 0)

enum class PaddleInput { UP, DOWN, NONE }

data class GameState(
    val player: Paddle,
    val ai: Paddle,
    val ball: Ball,
    val score: Score
) {
    companion object {
        val INITIAL = GameState(
            player = Paddle(y = 0.5),
            ai = Paddle(y = 0.5),
            ball = Ball(x = 0.5, y = 0.5, vx = 0.5, vy = 0.3),
            score = Score()
        )
    }
}
