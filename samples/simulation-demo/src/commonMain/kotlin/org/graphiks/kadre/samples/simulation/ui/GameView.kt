package org.graphiks.kadre.samples.simulation.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import org.graphiks.kadre.samples.simulation.SimulationAppHandler

private const val GAME_WIDTH = 800f
private const val GAME_HEIGHT = 600f

@Composable
fun GameView(
    appHandler: SimulationAppHandler,
    modifier: Modifier = Modifier
) {
    val state = appHandler.currentScenarioState
    GameView(
        data = state.data,
        message = state.message,
        modifier = modifier
    )
}

@Composable
fun GameView(
    data: Map<String, Any>,
    message: String?,
    modifier: Modifier = Modifier
) {
    val playerX = (data["player_x"] as? Number)?.toFloat() ?: 400f
    val playerY = (data["player_y"] as? Number)?.toFloat() ?: 300f
    val score = data["score"] as? Int ?: 0
    val hit = data["hit"] as? Boolean
    val targetsX = (data["targets_x"] as? String)
        ?.split(",")
        ?.mapNotNull { it.toFloatOrNull() }
        ?: emptyList()
    val targetsY = (data["targets_y"] as? String)
        ?.split(",")
        ?.mapNotNull { it.toFloatOrNull() }
        ?: emptyList()

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Score: $score",
                style = MaterialTheme.typography.h4
            )
            Text(
                text = "ZQSD/WASD: move | Click: shoot",
                style = MaterialTheme.typography.caption,
                color = MaterialTheme.colors.onSurface.copy(alpha = 0.5f)
            )
        }

        Spacer(Modifier.height(8.dp))

        if (message != null) {
            Text(
                text = message,
                style = MaterialTheme.typography.body2,
                color = MaterialTheme.colors.primary,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }

        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            val scaleX = size.width / GAME_WIDTH
            val scaleY = size.height / GAME_HEIGHT

            drawRect(Color(0xFF1a1a2e))

            val borderColor = when {
                hit == true -> Color(0xFF44FF44)
                hit == false -> Color(0xFFFF4444)
                else -> Color(0xFF2a2a4e)
            }
            drawRect(borderColor, style = Stroke(width = 2f))

            for (i in targetsX.indices) {
                val tx = targetsX[i] * scaleX
                val ty = targetsY[i] * scaleY
                val r = 18f * ((scaleX + scaleY) / 2f)
                drawCircle(Color(0xFFFF4444), radius = r, center = Offset(tx, ty))
                drawCircle(Color.Red, radius = r * 0.8f, center = Offset(tx, ty))
                val cross = r * 0.5f
                drawLine(Color.White, Offset(tx - cross, ty), Offset(tx + cross, ty), strokeWidth = 2f)
                drawLine(Color.White, Offset(tx, ty - cross), Offset(tx, ty + cross), strokeWidth = 2f)
            }

            val px = playerX * scaleX
            val py = playerY * scaleY
            val pr = 16f * ((scaleX + scaleY) / 2f)
            drawCircle(Color(0xFF4488CC), radius = pr, center = Offset(px, py))
            drawCircle(Color(0xFF66BBFF), radius = pr * 0.7f, center = Offset(px, py))
            drawCircle(Color.White, radius = pr * 0.25f, center = Offset(px, py))
        }
    }
}
