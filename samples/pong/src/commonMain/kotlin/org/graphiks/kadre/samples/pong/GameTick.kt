package org.graphiks.kadre.samples.pong

private const val PADDLE_SPEED = 0.8  // units/second
private const val PADDLE_WIDTH = 0.02
private const val PADDLE_X_PLAYER = 0.02
private const val PADDLE_X_AI = 0.98
private const val BALL_RESET_VX = 0.5
private const val BALL_RESET_VY = 0.3

fun GameState.tick(dt: Double, playerInput: PaddleInput, aiInput: PaddleInput): GameState {
    // Move paddles
    val newPlayer = player.movePaddle(playerInput, dt)
    val newAi = ai.movePaddle(aiInput, dt)

    // Move ball
    var bx = ball.x + ball.vx * dt
    var by = ball.y + ball.vy * dt
    var vx = ball.vx
    var vy = ball.vy

    // Wall collisions (top/bottom)
    if (by <= 0.0) { by = 0.0; vy = -vy }
    if (by >= 1.0) { by = 1.0; vy = -vy }

    // Paddle collisions
    // Player paddle (left side)
    if (vx < 0 && bx <= PADDLE_X_PLAYER + PADDLE_WIDTH) {
        val hitPos = (by - newPlayer.y) / (newPlayer.height / 2)  // -1..1
        if (hitPos in -1.2..1.2) {
            bx = PADDLE_X_PLAYER + PADDLE_WIDTH
            vx = -vx
            vy = hitPos * 0.6  // angle based on hit position
        }
    }
    // AI paddle (right side)
    if (vx > 0 && bx >= PADDLE_X_AI - PADDLE_WIDTH) {
        val hitPos = (by - newAi.y) / (newAi.height / 2)
        if (hitPos in -1.2..1.2) {
            bx = PADDLE_X_AI - PADDLE_WIDTH
            vx = -vx
            vy = hitPos * 0.6
        }
    }

    // Scoring
    var newScore = score
    var resetBall = false
    if (bx < 0.0) {
        newScore = newScore.copy(ai = newScore.ai + 1)
        resetBall = true
    } else if (bx > 1.0) {
        newScore = newScore.copy(player = newScore.player + 1)
        resetBall = true
    }

    val newBall = if (resetBall) {
        Ball(x = 0.5, y = 0.5, vx = if (resetBall && bx < 0.0) BALL_RESET_VX else -BALL_RESET_VX, vy = BALL_RESET_VY)
    } else {
        Ball(x = bx, y = by, vx = vx, vy = vy)
    }

    return GameState(player = newPlayer, ai = newAi, ball = newBall, score = newScore)
}

private fun Paddle.movePaddle(input: PaddleInput, dt: Double): Paddle {
    val dy = when (input) {
        PaddleInput.UP -> -PADDLE_SPEED * dt
        PaddleInput.DOWN -> PADDLE_SPEED * dt
        PaddleInput.NONE -> 0.0
    }
    return copy(y = (y + dy).coerceIn(height / 2, 1.0 - height / 2))
}
