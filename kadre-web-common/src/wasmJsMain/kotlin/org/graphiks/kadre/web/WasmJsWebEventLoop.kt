/**
 * wasmJs implementation of [WebEventLoop] via `requestAnimationFrame` Wasm interop.
 *
 * This file resides in `wasmJsMain` — it can use `external` declarations
 * and Wasm JS interop (JsAny, JsReference, etc.).
 *
 * ## requestAnimationFrame via Wasm interop
 * The `window.requestAnimationFrame` API is exposed via an `external` declaration
 * since automatic DOM bindings are not available in wasmJs like in JS.
 *
 * ## setTimeout (WaitUntil mode)
 * In [ControlFlow.WaitUntil] mode, a `setTimeout` is scheduled for the target instant.
 *
 * @since 1.0.0
 */
package org.graphiks.kadre.web

import org.graphiks.kadre.core.ApplicationHandler
import org.graphiks.kadre.core.ControlFlow

// ---------------------------------------------------------------------------
// Wasm JS interop — requestAnimationFrame and setTimeout
// ---------------------------------------------------------------------------

/** Callback passed to requestAnimationFrame: receives the timestamp in ms. */
private external fun requestAnimationFrame(callback: (Double) -> Unit): Int

/**
 * Schedules the execution of a callback after [delayMs] milliseconds.
 *
 * @param callback Callback to execute.
 * @param delayMs  Delay in milliseconds (0 = as soon as possible).
 * @return Timer identifier (not used here).
 */
private external fun setTimeout(callback: () -> Unit, delayMs: Int): Int

/** Returns the current timestamp in milliseconds since the Unix epoch. */
private external fun dateNow(): Double

// ---------------------------------------------------------------------------
// WasmJsWebEventLoop
// ---------------------------------------------------------------------------

/**
 * wasmJs event loop — orchestrates frames via `requestAnimationFrame` Wasm interop.
 */
class WasmJsWebEventLoop : WebEventLoop() {

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
                requestAnimationFrame { timestamp ->
                    rafPending = false
                    tick(handler, timestamp)
                }
            }
            is ControlFlow.Wait -> {
                // In Wait mode, we wait for a DOM event.
                // scheduleWakeUp() will be called by the DOM bridge when an event arrives.
            }
            is ControlFlow.WaitUntil -> {
                val delayMs = maxOf(0L, cf.instant - dateNow().toLong()).toInt()
                setTimeout({
                    if (!rafPending) {
                        rafPending = true
                        requestAnimationFrame { timestamp ->
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
            requestAnimationFrame { timestamp ->
                rafPending = false
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
     * Creates a [WasmJsWebDomBridge] — wasmJs DOM bridge to the Kadre engine.
     */
    override fun createDomBridge(): WebDomBridge = WasmJsWebDomBridge()
}
