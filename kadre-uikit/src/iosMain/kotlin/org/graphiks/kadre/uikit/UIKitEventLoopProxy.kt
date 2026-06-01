package org.graphiks.kadre.uikit

import org.graphiks.kadre.core.EventLoopProxy
import org.graphiks.kadre.core.StartCause
import kotlinx.cinterop.ExperimentalForeignApi
import platform.darwin.dispatch_async
import platform.darwin.dispatch_get_main_queue
import kotlin.concurrent.AtomicInt

/**
 * Thread-safe [EventLoopProxy] for the UIKit backend.
 *
 * [wakeUp] posts a block on the main queue via `dispatch_async` so that
 * [UIKitActiveEventLoop.handler.newEvents] is called on the main thread.
 *
 * ## Coalescing
 * A [AtomicInt] flag (`pending`) prevents redundant dispatches: if [wakeUp]
 * is called several times before the posted block executes, only one block
 * runs. The flag is reset inside the block, before calling the handler, so
 * a concurrent [wakeUp] issued *during* the callback correctly re-schedules
 * another tick.
 *
 * ## Why dispatch_async instead of CFRunLoopWakeUp
 * `CFRunLoopWakeUp(CFRunLoopGetMain())` only unblocks a `CFRunLoopRun` call.
 * On iOS, UIKit owns the run loop and `CADisplayLink` drives frame callbacks —
 * there is no blocking `CFRunLoopRun` to wake. Using `dispatch_async` on the
 * main queue is the idiomatic way to schedule work on the main thread from any
 * thread on Apple platforms, and it naturally coalesces via the flag above.
 */
@OptIn(ExperimentalForeignApi::class)
internal class UIKitEventLoopProxy(
    private val loop: UIKitActiveEventLoop,
) : EventLoopProxy {

    /** 1 = a dispatch is already pending, 0 = idle. */
    private val pending = AtomicInt(0)

    override fun wakeUp() {
        // CAS: only enqueue if no dispatch is already pending.
        if (!pending.compareAndSet(0, 1)) return

        dispatch_async(dispatch_get_main_queue()) {
            // Reset before invoking handler so a concurrent wakeUp() issued
            // from within the callback re-schedules a new tick correctly.
            pending.value = 0
            loop.handler.newEvents(loop, StartCause.WaitCancelled())
        }
    }
}
