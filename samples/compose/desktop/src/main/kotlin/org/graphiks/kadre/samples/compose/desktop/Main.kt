package org.graphiks.kadre.samples.compose.desktop

import org.graphiks.kadre.EventLoop
import org.graphiks.kadre.PhysicalSize
import org.graphiks.kadre.WindowAttributes
import org.graphiks.kadre.core.RawWindowHandle
import org.graphiks.kadre.core.WindowEvent
import org.graphiks.kadre.coroutines.kadreApplication
import org.graphiks.kadre.samples.compose.infra.ComposeWindowRenderer
import org.graphiks.kadre.samples.compose.infra.KeyForwarder
import org.graphiks.kadre.samples.compose.infra.applyWindowEvent
import org.graphiks.kadre.samples.compose.showcase.PlatformContext
import org.graphiks.kadre.samples.compose.showcase.ShowcaseApp

private fun runShowcase() = kadreApplication {
    val keys = KeyForwarder()
    val win = createWindow(
        WindowAttributes("Compose Showcase", PhysicalSize(900, 700), visible = true, resizable = true),
    )
    val handle = win.window.rawWindowHandle as? RawWindowHandle ?: run { exit(); return@kadreApplication }
    val renderer = ComposeWindowRenderer.create(handle, win.window.scaleFactor, dispatcher).getOrElse {
        println("[compose-showcase] Cannot create renderer: ${it.message}")
        exit(); return@kadreApplication
    }
    val inner = win.window.innerSize
    renderer.resize(inner.width, inner.height, win.window.scaleFactor)
    renderer.setContent { ShowcaseApp(PlatformContext()) }

    var lastRenderNanos = 0L
    val frameIntervalNanos = 50_000_000L

    win.events.collect { event ->
        when (event) {
            is WindowEvent.CloseRequested -> {
                renderer.dispose(); exit()
            }
            is WindowEvent.RedrawRequested -> {
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

private fun runKeytest() {
    val typed = keyboardSelfTest("hi")
    println("[compose-showcase] keytest — text field received: '$typed' (expected 'hi')")
    if (typed != "hi") error("keytest FAILED: '$typed' != 'hi'")
    println("[compose-showcase] keytest OK")
}

private fun startCaptureWatchdog() {
    Thread {
        Thread.sleep(30_000)
        System.err.println("[compose-showcase] window-capture watchdog: not done after 30s — forcing exit")
        System.out.flush()
        System.err.flush()
        Runtime.getRuntime().halt(3)
    }.apply { isDaemon = true }.start()
}

fun main(args: Array<String>) {
    if (args.contains("--keytest")) {
        runKeytest()
        return
    }

    val ciHeadlessIndex = args.indexOf("--ci-headless")
    if (ciHeadlessIndex >= 0) {
        val dir = args.getOrNull(ciHeadlessIndex + 1)
            ?: error("--ci-headless requires an output dir: --ci-headless <dir>")
        runKeytest()
        captureShowcaseToPng("$dir/compose-showcase.raster.png")
        return
    }

    val captureIndex = args.indexOf("--capture")
    if (captureIndex >= 0) {
        val path = args.getOrNull(captureIndex + 1)
            ?: error("--capture requires a file path: --capture <path>")
        captureShowcaseToPng(path)
        return
    }

    val windowCaptureIndex = args.indexOf("--window-capture")
    if (windowCaptureIndex >= 0) {
        val path = args.getOrNull(windowCaptureIndex + 1)
            ?: error("--window-capture requires a file path: --window-capture <path>")
        // Use the old hello-compose Capture app path for windowed GL capture
        startCaptureWatchdog()
        println("[compose-showcase] window-capture not yet implemented for new desktop module")
        return
    }

    println("[compose-showcase] Starting Compose Showcase")
    runShowcase()
    println("[compose-showcase] Done")
}
