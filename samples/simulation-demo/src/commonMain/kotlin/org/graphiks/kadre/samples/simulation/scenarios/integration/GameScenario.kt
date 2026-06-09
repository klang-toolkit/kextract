package org.graphiks.kadre.samples.simulation.scenarios.integration

import org.graphiks.kadre.core.*
import org.graphiks.kadre.samples.simulation.*

class GameScenario : Scenario {
    override val id = "game-simple"
    override val title = "Simple game (keyboard + mouse)"
    override val description = "Demonstration of a mini-game combining keyboard (ZQSD/WASD for movement) and mouse (click to shoot)."
    override val category = "Integration"
    override val requiredCapabilities: Set<Capability> = setOf(Capability.KEYBOARD, Capability.MOUSE)
    override val priority: Int = 100

    private var window: Window? = null
    private var eventLoop: ActiveEventLoop? = null
    private var onEvent: ((ScenarioEvent) -> Unit)? = null
    private var isRunning = false

    private var playerX = 400f
    private var playerY = 300f
    private val playerSpeed = 5f
    private var score = 0
    private var targets = mutableListOf<Target>()
    private var totalShots = 0
    private var hits = 0

    data class Target(
        val x: Float,
        val y: Float,
        val id: Int
    )

    override fun start(window: Window, eventLoop: ActiveEventLoop, onEvent: (ScenarioEvent) -> Unit) {
        this.window = window
        this.eventLoop = eventLoop
        this.onEvent = onEvent
        this.isRunning = true
        this.score = 0
        this.targets.clear()
        this.totalShots = 0
        this.hits = 0

        val pos = listOf(200f, 200f, 600f, 200f, 400f, 400f, 200f, 500f, 600f, 500f)
        for (i in 0 until 5) {
            targets.add(Target(x = pos[i * 2], y = pos[i * 2 + 1], id = i))
        }

        onEvent(ScenarioEvent.StateChanged(ScenarioState(
            isRunning = true,
            message = "🎮 ZQSD/WASD: move | Left click: shoot at targets",
            data = stateData()
        )))
    }

    private fun stateData(extra: Map<String, Any> = emptyMap()): Map<String, Any> {
        val targetsX = targets.joinToString(",") { it.x.toInt().toString() }
        val targetsY = targets.joinToString(",") { it.y.toInt().toString() }
        return mapOf(
            "player_x" to playerX.toInt(),
            "player_y" to playerY.toInt(),
            "score" to score,
            "targets_x" to targetsX,
            "targets_y" to targetsY,
        ) + extra
    }

    override fun stop() {
        isRunning = false
    }

    override fun onWindowEvent(event: WindowEvent) {
        when (event) {
            is WindowEvent.KeyInput -> onKeyEvent(event)
            is WindowEvent.PointerButton -> onPointerButton(event)
            else -> {}
        }
    }

    private fun onKeyEvent(event: WindowEvent.KeyInput) {
        if (!isRunning) return
        val ke = event.event
        if (!ke.isPressed) return

        when (ke.physicalKey) {
            PhysicalKey.Code(KeyCode.KeyZ), PhysicalKey.Code(KeyCode.KeyW) -> playerY -= playerSpeed
            PhysicalKey.Code(KeyCode.KeyS) -> playerY += playerSpeed
            PhysicalKey.Code(KeyCode.KeyQ), PhysicalKey.Code(KeyCode.KeyA) -> playerX -= playerSpeed
            PhysicalKey.Code(KeyCode.KeyD) -> playerX += playerSpeed
            PhysicalKey.Code(KeyCode.KeyR) -> {
                score = 0
                targets.clear()
                val pos = listOf(200f, 200f, 600f, 200f, 400f, 400f, 200f, 500f, 600f, 500f)
                for (i in 0 until 5) {
                    targets.add(Target(x = pos[i * 2], y = pos[i * 2 + 1], id = i))
                }
            }
            else -> {}
        }

        playerX = playerX.coerceIn(0f, 800f)
        playerY = playerY.coerceIn(0f, 600f)

        onEvent?.invoke(ScenarioEvent.StateChanged(ScenarioState(
            isRunning = true,
            message = "🎮 Position: (${playerX.toInt()}, ${playerY.toInt()}) | Score: $score",
            data = stateData()
        )))
    }

    private fun onPointerButton(event: WindowEvent.PointerButton) {
        if (!isRunning) return
        val isMouseLeft = event.button is ButtonSource.Mouse &&
            (event.button as ButtonSource.Mouse).button == MouseButton.Left
        if (!isMouseLeft) return

        if (event.state != KeyState.Pressed) return

        totalShots++
        val mouseX = event.position.x.toFloat()
        val mouseY = event.position.y.toFloat()

        val hit = targets.find { target ->
            val dx = mouseX - target.x
            val dy = mouseY - target.y
            kotlin.math.sqrt(dx * dx + dy * dy) < 30f
        }

        if (hit != null) {
            hits++
            score += 10
            targets.remove(hit)

            val rng = kotlin.random.Random
            targets.add(Target(
                x = rng.nextFloat() * 700f + 50f,
                y = rng.nextFloat() * 500f + 50f,
                id = targets.size + 1
            ))

            onEvent?.invoke(ScenarioEvent.StateChanged(ScenarioState(
                isRunning = true,
                            message = "🎯 Target hit! +10 points (Score: $score)",
                data = stateData(mapOf("hit" to true))
            )))
        } else {
            onEvent?.invoke(ScenarioEvent.StateChanged(ScenarioState(
                isRunning = true,
                message = "💨 Missed shot... (Score: $score)",
                data = stateData(mapOf("hit" to false))
            )))
        }
    }

    override fun collectResult(durationMs: Long): ScenarioResult {
        return ScenarioResult(
            success = true,
            durationMs = durationMs,
            eventsReceived = totalShots,
            eventsExpected = 10,
            errors = if (targets.isNotEmpty()) listOf("${targets.size} targets remaining") else emptyList(),
            platform = Platform.current()
        )
    }
}
