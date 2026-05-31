package org.graphiks.kadre.samples.pong

/**
 * IA simple pour Pong : suit la balle avec un délai de réaction configurable.
 *
 * Sprint 5.
 */
class PongAi(private val reactionLagMs: Long = 80L) {

    private var lastTargetY: Double = 0.5
    private var lastUpdateMs: Long = 0L

    /**
     * Calcule l'input recommandé pour la raquette AI.
     *
     * Met à jour [lastTargetY] toutes les [reactionLagMs] millisecondes.
     * La raquette remonte si elle est au-dessus de la cible, descend sinon.
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

    /** Remet à zéro la mémoire de l'IA (utile après un reset de balle). */
    fun reset() {
        lastTargetY = 0.5
        lastUpdateMs = 0L
    }
}
