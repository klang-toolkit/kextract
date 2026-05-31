/**
 * JS implementation of [WebEventLoop] via `window.requestAnimationFrame`.
 *
 * This file resides in `jsMain` — it can use `kotlinx.browser`
 * and `org.w3c.dom.*` to access the browser DOM APIs.
 *
 * ## requestAnimationFrame
 * The browser calls the RAF callback before each repaint, typically at 60 Hz
 * (or the screen refresh rate). The `timestamp` parameter is
 * passed in milliseconds since the page origin.
 *
 * ## setTimeout (WaitUntil mode)
 * In [ControlFlow.WaitUntil] mode, a `setTimeout` is scheduled for the target instant.
 * The delay is computed in milliseconds from `Date.now()`. If the instant has already
 * passed, the delay is 0 (executes as soon as possible).
 *
 * @since 1.0.0
 */
package org.graphiks.kadre.web

import org.graphiks.kadre.core.ApplicationHandler
import org.graphiks.kadre.core.ControlFlow
import kotlinx.browser.window
import kotlin.js.Date

/**
 * JS event loop — orchestrates frames via `window.requestAnimationFrame`.
 */
class JsWebEventLoop : WebEventLoop() {

    /** true if a RAF is already queued, to avoid duplicates in Wait mode. */
    private var rafPending = false

    /**
     * Schedules the next frame according to the current [controlFlow].
     *
     * - [ControlFlow.Poll]      → immediate RAF
     * - [ControlFlow.Wait]      → no RAF (will be triggered by [scheduleWakeUp])
     * - [ControlFlow.WaitUntil] → setTimeout until [ControlFlow.WaitUntil.instant], then RAF
     */
    override fun scheduleNextFrame(handler: ApplicationHandler) {
        when (val cf = controlFlow) {
            is ControlFlow.Poll -> {
                rafPending = true
                window.requestAnimationFrame { timestamp ->
                    rafPending = false
                    tick(handler, timestamp)
                }
            }
            is ControlFlow.Wait -> {
                // In Wait mode, we wait for a DOM event.
                // scheduleWakeUp() will be called by the DOM bridge when an event arrives.
            }
            is ControlFlow.WaitUntil -> {
                val delayMs = maxOf(0L, cf.instant - Date.now().toLong()).toInt()
                window.setTimeout({
                    if (!rafPending) {
                        rafPending = true
                        window.requestAnimationFrame { timestamp ->
                            rafPending = false
                            tick(handler, timestamp)
                        }
                    }
                }, delayMs)
            }
        }
    }

    /**
     * Wakes up the loop via a single RAF.
     *
     * Called in [ControlFlow.Wait] mode when a DOM event arrives,
     * or from [createProxy] to notify from another context.
     * Guarded by [rafPending] to avoid duplicate RAFs.
     */
    override fun scheduleWakeUp() {
        if (!rafPending) {
            rafPending = true
            window.requestAnimationFrame { timestamp ->
                rafPending = false
                // Retrieves the handler via a memoized field — see runApp
                _pendingWakeUpHandler?.let { tick(it, timestamp) }
            }
        }
    }

    /** Handler memoized for [scheduleWakeUp] outside the [scheduleNextFrame] context. */
    private var _pendingWakeUpHandler: ApplicationHandler? = null

    override fun runApp(handler: ApplicationHandler) {
        _pendingWakeUpHandler = handler
        super.runApp(handler)
    }

    /**
     * Creates a [JsWebDomBridge] — JS DOM bridge to the Kadre engine.
     */
    override fun createDomBridge(): WebDomBridge = JsWebDomBridge()
}
