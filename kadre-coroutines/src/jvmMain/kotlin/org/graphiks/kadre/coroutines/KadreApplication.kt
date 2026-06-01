/**
 * Coroutine-friendly entry point over the Kadre callback loop.
 *
 * Instead of implementing `ApplicationHandler`, you write a suspending block:
 *
 * ```kotlin
 * kadreApplication {
 *     val win = createWindow(WindowAttributes(...))
 *     launch { while (isActive) { delay(1000); tick() } }   // coroutines
 *     win.events.collect { event -> ... }                   // events as a Flow
 * }
 * ```
 *
 * The block runs in a [CoroutineScope] backed by an [EventLoopDispatcher] (main thread) and
 * tied to the loop's lifetime — leaving the block / calling [KadreAppScope.exit] cancels it.
 * Window events are exposed as a [Flow] per window.
 *
 * The callback `ApplicationHandler` remains the underlying substrate.
 */
package org.graphiks.kadre.coroutines

import org.graphiks.kadre.ActiveEventLoop
import org.graphiks.kadre.ApplicationHandler
import org.graphiks.kadre.EventLoop
import org.graphiks.kadre.WindowAttributes
import org.graphiks.kadre.WindowId
import org.graphiks.kadre.core.Window
import org.graphiks.kadre.core.WindowEvent
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

/** A Kadre window plus its events exposed as a [Flow]. */
class KadreWindow internal constructor(val window: Window) {
    internal val mutableEvents = MutableSharedFlow<WindowEvent>(
        extraBufferCapacity = 256,
        onBufferOverflow = BufferOverflow.DROP_OLDEST, // never suspend the loop thread
    )
    val events: SharedFlow<WindowEvent> = mutableEvents.asSharedFlow()
}

/** Scope provided to a [kadreApplication] block. */
interface KadreAppScope : CoroutineScope {
    /** The main-thread dispatcher (also usable as a Compose recomposition dispatcher). */
    val dispatcher: EventLoopDispatcher

    /** Creates a window and exposes its events as a [Flow]. */
    fun createWindow(attributes: WindowAttributes): KadreWindow

    /** Requests shutdown of the loop and cancels the scope. */
    fun exit()
}

/** Runs the Kadre loop, executing [content] as a coroutine in a loop-scoped [KadreAppScope]. */
fun kadreApplication(content: suspend KadreAppScope.() -> Unit) {
    EventLoop().runApp(CoroutineApplicationHandler(content))
}

private class CoroutineApplicationHandler(
    private val content: suspend KadreAppScope.() -> Unit,
) : ApplicationHandler, KadreAppScope {

    override val dispatcher = EventLoopDispatcher()
    private val job = SupervisorJob()
    override val coroutineContext: CoroutineContext get() = dispatcher + job

    private var loop: ActiveEventLoop? = null
    private val windows = HashMap<Long, KadreWindow>()

    override fun canCreateSurfaces(eventLoop: ActiveEventLoop) {
        loop = eventLoop
        dispatcher.attach(eventLoop)
        launch { content() }
    }

    override fun createWindow(attributes: WindowAttributes): KadreWindow {
        val active = requireNotNull(loop) { "createWindow called before the loop is active" }
        val window = active.createWindow(attributes)
        return KadreWindow(window).also { windows[window.id.value] = it }
    }

    override fun exit() {
        job.cancel()
        loop?.exit()
    }

    override fun windowEvent(eventLoop: ActiveEventLoop, windowId: WindowId, event: WindowEvent) {
        windows[windowId.value]?.mutableEvents?.tryEmit(event)
    }

    override fun aboutToWait(eventLoop: ActiveEventLoop) {
        // Drain coroutine work on the main thread, then drive continuous redraw.
        dispatcher.pump()
        windows.values.forEach { it.window.requestRedraw() }
    }
}
