/**
 * A main-thread [CoroutineDispatcher] backed by a Kadre event loop.
 *
 * `dispatch` enqueues work and wakes the loop via [EventLoopProxy]; the loop drains the queue
 * on its own (main) thread by calling [pump] once per iteration (the [kadreApplication] handler
 * does this for you). [pump] also returns the [ControlFlow] the loop should wait with:
 *   - pending immediate work → Poll (run again right away)
 *   - a scheduled delay        → WaitUntil(next deadline)  (sleep precisely, no busy loop)
 *   - nothing to do            → Wait                       (block until an external event)
 *
 * Implementing [Delay] makes `kotlinx.coroutines.delay` resume on the loop thread without
 * spinning — `delay(1000)` parks the loop until the deadline instead of burning CPU.
 */
package org.graphiks.kadre.coroutines

import org.graphiks.kadre.core.ActiveEventLoop
import org.graphiks.kadre.core.ControlFlow
import org.graphiks.kadre.core.EventLoopProxy
import java.util.PriorityQueue
import java.util.concurrent.ConcurrentLinkedQueue
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.resume
import kotlinx.coroutines.CancellableContinuation
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Delay
import kotlinx.coroutines.InternalCoroutinesApi

@OptIn(InternalCoroutinesApi::class)
class EventLoopDispatcher : CoroutineDispatcher(), Delay {

    private val immediate = ConcurrentLinkedQueue<Runnable>()

    private class Timed(val deadline: Long, val task: Runnable)
    private val lock = Any()
    private val timed = PriorityQueue<Timed>(compareBy { it.deadline })

    @Volatile private var proxy: EventLoopProxy? = null

    /** Binds the dispatcher to a running loop so it can wake it on dispatch. */
    fun attach(eventLoop: ActiveEventLoop) {
        proxy = eventLoop.createProxy()
    }

    override fun dispatch(context: CoroutineContext, block: Runnable) {
        immediate.add(block)
        proxy?.wakeUp()
    }

    override fun scheduleResumeAfterDelay(timeMillis: Long, continuation: CancellableContinuation<Unit>) {
        val deadline = System.currentTimeMillis() + timeMillis
        val entry = Timed(deadline, Runnable { continuation.resume(Unit) })
        synchronized(lock) { timed.add(entry) }
        continuation.invokeOnCancellation { synchronized(lock) { timed.remove(entry) } }
        proxy?.wakeUp()
    }

    /**
     * Runs all ready work on the calling (loop/main) thread and returns the [ControlFlow] the
     * loop should wait with next. Call once per loop iteration (e.g. from `aboutToWait`).
     */
    fun pump(): ControlFlow {
        drainImmediate()

        val now = System.currentTimeMillis()
        while (true) {
            val due = synchronized(lock) {
                timed.peek()?.takeIf { it.deadline <= now }?.also { timed.remove(it) }
            } ?: break
            due.task.run()
        }
        drainImmediate() // timed continuations may have dispatched more immediate work

        return synchronized(lock) {
            when {
                immediate.isNotEmpty() -> ControlFlow.Poll
                timed.isNotEmpty() -> ControlFlow.WaitUntil(timed.peek().deadline)
                else -> ControlFlow.Wait
            }
        }
    }

    private fun drainImmediate() {
        while (true) {
            (immediate.poll() ?: break).run()
        }
    }
}
