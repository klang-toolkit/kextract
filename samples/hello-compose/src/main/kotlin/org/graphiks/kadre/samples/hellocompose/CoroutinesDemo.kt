/**
 * Prototype demo (Level 1): drive a Kadre app with coroutines via [EventLoopDispatcher].
 *
 * Launches a coroutine that ticks with `delay(...)` and then exits the loop. It proves:
 *   - coroutine bodies resume on the loop's main thread (same thread that runs the loop),
 *   - `delay` is honoured through ControlFlow.WaitUntil (the loop sleeps between ticks — no
 *     busy loop; CPU stays idle), and
 *   - structured shutdown (`eventLoop.exit()` from inside the coroutine).
 *
 * Run: ./gradlew :samples:hello-compose:run --args="--coroutines-demo"
 */
package org.graphiks.kadre.samples.hellocompose

import org.graphiks.kadre.ActiveEventLoop
import org.graphiks.kadre.ApplicationHandler
import org.graphiks.kadre.EventLoop
import org.graphiks.kadre.WindowId
import org.graphiks.kadre.core.WindowEvent
import org.graphiks.kadre.coroutines.EventLoopDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private class CoroutinesDemoApp : ApplicationHandler {

    private val dispatcher = EventLoopDispatcher()
    private val scope = CoroutineScope(SupervisorJob() + dispatcher)
    private val startedAt = System.currentTimeMillis()

    override fun canCreateSurfaces(eventLoop: ActiveEventLoop) {
        dispatcher.attach(eventLoop)
        val thread = Thread.currentThread().name
        println("[coroutines-demo] loop thread = '$thread'")

        scope.launch {
            repeat(5) { i ->
                val elapsed = System.currentTimeMillis() - startedAt
                println("[coroutines-demo] tick $i @ ${elapsed}ms on '${Thread.currentThread().name}'")
                delay(400)
            }
            println("[coroutines-demo] coroutine done — exiting loop")
            eventLoop.exit()
        }
    }

    override fun windowEvent(eventLoop: ActiveEventLoop, windowId: WindowId, event: WindowEvent) = Unit

    override fun aboutToWait(eventLoop: ActiveEventLoop) {
        // Drain ready coroutine work and let the dispatcher choose how to wait (Wait/WaitUntil/Poll).
        eventLoop.setControlFlow(dispatcher.pump())
    }
}

/** Entry point for `--coroutines-demo`. */
fun runCoroutinesDemo() {
    println("[coroutines-demo] starting — coroutine-driven Kadre loop")
    EventLoop().runApp(CoroutinesDemoApp())
    println("[coroutines-demo] done")
}
