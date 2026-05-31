/**
 * Sample hello-compose — interactive Jetpack Compose UI inside a native Kadre window.
 *
 * The live app is written with the coroutine-friendly layer ([kadreApplication]): windows are
 * created in a coroutine scope, events are collected as a Flow, and a coroutine ticks an
 * "uptime" state via `delay()` while a Material3 spinner animates through the loop-driven frame
 * clock. Rendering goes through Skiko (Metal on macOS, OpenGL on Windows/Linux).
 *
 * Usage: ./gradlew :samples:hello-compose:run
 */
package org.graphiks.kadre.samples.hellocompose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.graphiks.kadre.ActiveEventLoop
import org.graphiks.kadre.ApplicationHandler
import org.graphiks.kadre.EventLoop
import org.graphiks.kadre.PhysicalSize
import org.graphiks.kadre.WindowAttributes
import org.graphiks.kadre.WindowId
import org.graphiks.kadre.core.RawWindowHandle
import org.graphiks.kadre.core.WindowEvent
import org.graphiks.kadre.coroutines.EventLoopDispatcher
import org.graphiks.kadre.coroutines.kadreApplication
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

/**
 * The Compose UI shown inside the Kadre window.
 *
 * - the button + text field prove input forwarding (pointer + keyboard),
 * - the spinner animates via withFrameNanos against the loop-driven frame clock,
 * - [uptimeSeconds] (≥ 0) is driven by a coroutine via `delay()`.
 */
@androidx.compose.runtime.Composable
fun DemoUi(uptimeSeconds: Int = -1) {
    MaterialTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            var clicks by remember { mutableStateOf(0) }
            var typed by remember { mutableStateOf("") }
            Column(
                modifier = Modifier.fillMaxSize().padding(32.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text("Jetpack Compose in a Kadre window 🪟", style = MaterialTheme.typography.headlineSmall)
                Text("Rendered via Skiko (Metal on macOS, OpenGL on Windows/Linux)", style = MaterialTheme.typography.bodyMedium)
                if (uptimeSeconds >= 0) {
                    // Driven by a coroutine via EventLoopDispatcher + delay() — updates on the main thread.
                    Text("⏱ coroutine uptime: ${uptimeSeconds}s", style = MaterialTheme.typography.titleMedium)
                }
                // Indeterminate spinner — animates via withFrameNanos against the loop-driven
                // MonotonicFrameClock (Level 2). A smooth spin proves the frame clock works.
                CircularProgressIndicator()
                Button(onClick = { clicks++ }) {
                    Text("Clicked $clicks times")
                }
                OutlinedTextField(
                    value = typed,
                    onValueChange = { typed = it },
                    label = { Text("Type here") },
                    singleLine = true,
                )
            }
        }
    }
}

/**
 * The interactive app, expressed with the coroutine/Flow layer (Level 3).
 *
 * No [ApplicationHandler] boilerplate: create a window, launch coroutines, collect its events.
 */
private fun runComposeApp() = kadreApplication {
    val uptime = mutableStateOf(0)
    val keys = KeyForwarder()

    val win = createWindow(
        WindowAttributes("Hello Compose — Kadre + Skiko", PhysicalSize(800, 600), visible = true, resizable = true),
    )
    val handle = win.window.rawWindowHandle as? RawWindowHandle ?: run {
        println("[hello-compose] Unexpected window handle: ${win.window.rawWindowHandle}")
        exit(); return@kadreApplication
    }
    val renderer = ComposeWindowRenderer.create(handle, win.window.scaleFactor, dispatcher).getOrElse {
        println("[hello-compose] Cannot create renderer: ${it.message}")
        exit(); return@kadreApplication
    }
    val inner = win.window.innerSize
    renderer.resize(inner.width, inner.height, win.window.scaleFactor)
    renderer.setContent { DemoUi(uptimeSeconds = uptime.value) }
    println("[hello-compose] Ready — ${inner.width}×${inner.height} @ ${win.window.scaleFactor}x (${renderer::class.simpleName})")

    // A coroutine ticking a Compose state via delay() — coroutine-driven UI.
    launch {
        while (isActive) {
            delay(1000)
            uptime.value += 1
        }
    }

    // DEMO/APP CHOICE (not a framework concern): cap the frame rate to ~20 fps. Kadre renders on
    // the UI thread, and this demo's heavy continuous Compose frames would otherwise saturate the
    // main thread and make a window move/resize lag behind the cursor at the start of the gesture.
    // Capping leaves the thread free for the OS. Pure app-side pacing — a lighter/faster app may
    // render uncapped.
    var lastRenderNanos = 0L
    val frameIntervalNanos = 50_000_000L // ~20 fps

    // Window events as a Flow. collect suspends until the loop exits.
    win.events.collect { event ->
        when {
            event is WindowEvent.CloseRequested -> {
                println("[hello-compose] CloseRequested — closing")
                renderer.dispose()
                exit()
            }
            event is WindowEvent.RedrawRequested -> {
                val now = System.nanoTime()
                if (now - lastRenderNanos >= frameIntervalNanos) {
                    lastRenderNanos = now
                    renderer.applyWindowEvent(event, win.window, keys)
                }
            }
            else -> renderer.applyWindowEvent(event, win.window, keys)
        }
    }
}

/**
 * Headless windowed capture (callback style, synchronous): open a window, render a few frames
 * through the real platform present path (Metal/GL), snapshot to a PNG, and exit. Used by CI.
 */
private class CaptureApp(private val capturePath: String) : ApplicationHandler {
    private val dispatcher = EventLoopDispatcher()

    override fun canCreateSurfaces(eventLoop: ActiveEventLoop) {
        val win = eventLoop.createWindow(
            WindowAttributes("Hello Compose — capture", PhysicalSize(800, 600), visible = true, resizable = true),
        )
        val handle = win.rawWindowHandle as? RawWindowHandle ?: run { eventLoop.exit(); return }
        val r = ComposeWindowRenderer.create(handle, win.scaleFactor, dispatcher).getOrElse {
            println("[hello-compose] Cannot create renderer: ${it.message}"); eventLoop.exit(); return
        }
        val inner = win.innerSize
        r.resize(inner.width, inner.height, win.scaleFactor)
        r.setContent { DemoUi() }
        // Render a few frames so composition + first paint settle, then snapshot and exit —
        // independent of the event loop's redraw cadence (which may never fire headlessly).
        repeat(4) { r.renderFrame() }
        val ok = r.captureFrameToPng(capturePath)
        println("[hello-compose] window-capture ${if (ok) "written" else "FAILED"}: $capturePath")
        r.dispose()
        eventLoop.exit()
    }

    override fun windowEvent(eventLoop: ActiveEventLoop, windowId: WindowId, event: Any) = Unit
}

/**
 * Entry point. Must run on the main macOS thread (Gradle adds `-XstartOnFirstThread`).
 *
 * Modes: `--keytest`, `--capture <png>` (offscreen raster), `--ci-headless <dir>`,
 * `--window-capture <png>` (windowed GL/Metal capture), `--coroutines-demo`.
 */
fun main(args: Array<String>) {
    if (args.contains("--keytest")) {
        runKeytest()
        return
    }

    if (args.contains("--coroutines-demo")) {
        runCoroutinesDemo()
        return
    }

    // Combined headless checks in one JVM (keytest + raster capture) — one Gradle invocation.
    val ciHeadlessIndex = args.indexOf("--ci-headless")
    if (ciHeadlessIndex >= 0) {
        val dir = args.getOrNull(ciHeadlessIndex + 1)
            ?: error("--ci-headless requires an output dir: --ci-headless <dir>")
        runKeytest()
        captureDemoUiToPng("$dir/hello-compose.raster.png")
        return
    }

    val captureIndex = args.indexOf("--capture")
    if (captureIndex >= 0) {
        val path = args.getOrNull(captureIndex + 1)
            ?: error("--capture requires a file path: --capture <path>")
        captureDemoUiToPng(path)
        return
    }

    val windowCaptureIndex = args.indexOf("--window-capture")
    if (windowCaptureIndex >= 0) {
        val path = args.getOrNull(windowCaptureIndex + 1)
            ?: error("--window-capture requires a file path: --window-capture <path>")
        startCaptureWatchdog()
        EventLoop().runApp(CaptureApp(path))
        return
    }

    println("[hello-compose] Starting — Compose Multiplatform in a Kadre window (coroutines/Flow)")
    runComposeApp()
    println("[hello-compose] Done")
}

/** Runs the headless keyboard self-test, failing the process if it doesn't type "hi". */
private fun runKeytest() {
    val typed = keyboardSelfTest("hi")
    println("[hello-compose] keytest — text field received: '$typed' (expected 'hi')")
    if (typed != "hi") error("keytest FAILED: '$typed' != 'hi'")
    println("[hello-compose] keytest OK")
}

/** Force-exits the JVM if a windowed capture is still blocked after 30s (CI safety net). */
private fun startCaptureWatchdog() {
    Thread {
        Thread.sleep(30_000)
        System.err.println("[hello-compose] window-capture watchdog: not done after 30s — forcing exit")
        System.out.flush()
        System.err.flush()
        Runtime.getRuntime().halt(3)
    }.apply { isDaemon = true }.start()
}
