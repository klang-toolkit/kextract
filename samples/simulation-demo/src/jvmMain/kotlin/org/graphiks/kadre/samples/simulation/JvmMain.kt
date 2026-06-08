package org.graphiks.kadre.samples.simulation

import org.graphiks.kadre.PhysicalSize
import org.graphiks.kadre.WindowAttributes
import org.graphiks.kadre.core.WindowEvent
import org.graphiks.kadre.coroutines.kadreApplication
import org.graphiks.kadre.samples.simulation.compose.ComposeWindowRenderer
import org.graphiks.kadre.samples.simulation.compose.KeyForwarder
import org.graphiks.kadre.samples.simulation.compose.applyWindowEvent
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

fun main(args: Array<String>) {
    if (args.isEmpty()) {
        runComposeApp()
        return
    }

    val cliArgs = Cli.parse(args.toList())

    when (cliArgs.action) {
        Cli.Action.LIST, Cli.Action.INFO -> {
            System.exit(Cli.execute(cliArgs))
        }
        Cli.Action.RUN, Cli.Action.RUN_ALL -> {
            runCliDemo(cliArgs)
        }
        Cli.Action.INTERACTIVE -> {
            runComposeApp()
        }
    }
}

private fun runComposeApp() = kadreApplication {
    val appHandler = SimulationAppHandler()
    val keys = KeyForwarder()

    val win = createWindow(
        WindowAttributes(
            title = "Kadre Simulation Demo",
            size = PhysicalSize(1200, 800),
            resizable = true,
            visible = true,
        )
    )

    appHandler.setEventLoopAndWindow(eventLoop, win.window)

    val renderer = ComposeWindowRenderer.create(win.window.rawWindowHandle, win.window.scaleFactor, dispatcher).getOrElse {
        println("[simulation-demo] Cannot create renderer: ${it.message}")
        exit(); return@kadreApplication
    }

    val inner = win.window.innerSize
    renderer.resize(inner.width, inner.height, win.window.scaleFactor)
    renderer.setContent { SimulationDemoMain(appHandler) }

    win.window.focusWindow()
    win.window.requestRedraw()

    println("[simulation-demo] Ready — ${inner.width}×${inner.height} @ ${win.window.scaleFactor}x (${renderer::class.simpleName})")

    win.events.collect { event ->
        when (event) {
            is WindowEvent.CloseRequested -> {
                renderer.dispose()
                exit()
            }
            else -> {
                renderer.applyWindowEvent(event, win.window, keys)
                appHandler.windowEvent(eventLoop, win.window.id, event)
            }
        }
    }
}

private fun runCliDemo(cliArgs: Cli.CliArgs) = kadreApplication {
    registerScenarios()

    val scenarios = when (cliArgs.action) {
        Cli.Action.RUN -> {
            val meta = ScenarioRegistry.get(cliArgs.scenarioId!!)
                ?: run { println("Scenario '${cliArgs.scenarioId}' not found"); exit(); return@kadreApplication }
            listOf(meta)
        }
        Cli.Action.RUN_ALL -> ScenarioRegistry.all().sortedByDescending { it.scenario.priority }
        else -> emptyList()
    }

    if (scenarios.isEmpty()) {
        println("No scenarios to run")
        exit(); return@kadreApplication
    }

    val displayState = CliDisplayState().apply {
        totalCount = scenarios.size
        currentIndex = 1
        isRunning = true
    }

    val win = createWindow(
        WindowAttributes(
            title = "Kadre Simulation Demo",
            size = PhysicalSize(1200, 800),
            resizable = true,
            visible = true,
        )
    )

    val keys = KeyForwarder()
    val renderer = ComposeWindowRenderer.create(win.window.rawWindowHandle, win.window.scaleFactor, dispatcher).getOrElse {
        println("[simulation-demo] Cannot create renderer: ${it.message}")
        exit(); return@kadreApplication
    }

    val inner = win.window.innerSize
    renderer.resize(inner.width, inner.height, win.window.scaleFactor)
    renderer.setContent { CliScenarioDisplay(displayState) }

    win.window.focusWindow()
    win.window.requestRedraw()

    println("[simulation-demo] CLI demo — ${scenarios.size} scenario(s), ${cliArgs.duration}s each")

    var cancelled = false

    launch {
        win.events.collect { event ->
            when (event) {
                is WindowEvent.CloseRequested -> {
                    cancelled = true
                    renderer.dispose()
                    exit()
                }
                else -> {
                    renderer.applyWindowEvent(event, win.window, keys)
                    displayState.currentScenario?.scenario?.onWindowEvent(event)
                    displayState.logEvent(eventDesc(event))
                }
            }
        }
    }

    launch {
        val allResults = mutableListOf<Pair<ScenarioMetadata, ScenarioResult>>()

        for ((index, meta) in scenarios.withIndex()) {
            if (cancelled || !isActive) break

            displayState.currentScenario = meta
            displayState.currentIndex = index + 1
            displayState.isRunning = true
            displayState.remainingTime = cliArgs.duration
            displayState.scenarioMessage = ""
            displayState.results = emptyList()

            win.window.setTitle("Kadre Demo - ${meta.scenario.title}")

            meta.scenario.start(win.window, eventLoop) { event ->
                when (event) {
                    is ScenarioEvent.StateChanged -> {
                        displayState.scenarioMessage = event.state.message ?: ""
                        if (event.state.data.isNotEmpty()) {
                            displayState.gameData = event.state.data
                        }
                    }
                    is ScenarioEvent.Message -> println("[${event.severity}] ${event.text}")
                    else -> {}
                }
            }

            simulateEvents(meta, win.window, displayState)

            for (i in 0 until cliArgs.duration) {
                if (cancelled || !isActive) break
                delay(1000)
                displayState.remainingTime = cliArgs.duration - i - 1
            }

            if (cancelled || !isActive) break

            meta.scenario.stop()

            val result = meta.scenario.collectResult(cliArgs.duration * 1000L)
            allResults.add(meta to result)
            displayState.results = allResults.map { (m, r) -> m.scenario.id to r }

            println()
            println("=== ${meta.scenario.title} ===")
            Cli.printResult(result)

            if (index < scenarios.size - 1) {
                delay(1500)
            }
        }

        displayState.isRunning = false

        if (allResults.size > 1) {
            println()
            println("=== Summary ===")
            val successes = allResults.count { it.second.success }
            println("$successes/${allResults.size} scenarios succeeded")
        }

        if (cliArgs.output != null && allResults.isNotEmpty()) {
            Cli.exportJsonResults(allResults, cliArgs.output)
        }

        displayState.isAllDone = true

        delay(3000)
        renderer.dispose()
        exit()
    }
}
