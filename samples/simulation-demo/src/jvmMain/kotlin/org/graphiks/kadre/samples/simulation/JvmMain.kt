package org.graphiks.kadre.samples.simulation

import org.graphiks.kadre.PhysicalSize
import org.graphiks.kadre.WindowAttributes
import org.graphiks.kadre.core.WindowEvent
import org.graphiks.kadre.coroutines.kadreApplication
import org.graphiks.kadre.samples.simulation.compose.ComposeWindowRenderer
import org.graphiks.kadre.samples.simulation.compose.KeyForwarder
import org.graphiks.kadre.samples.simulation.compose.applyWindowEvent
import kotlinx.coroutines.flow.collect

fun main(args: Array<String>) {
    if (args.isNotEmpty()) {
        val cliArgs = Cli.parse(args.toList())
        val exitCode = Cli.execute(cliArgs)
        System.exit(exitCode)
    }
    runComposeApp()
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
            }
        }
    }
}
