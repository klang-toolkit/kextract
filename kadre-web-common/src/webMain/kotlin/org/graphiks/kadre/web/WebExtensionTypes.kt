package org.graphiks.kadre.web

import org.graphiks.kadre.core.ActiveEventLoop
import org.graphiks.kadre.core.CursorImage
import org.graphiks.kadre.core.CustomCursor
import org.graphiks.kadre.core.Window

enum class PollStrategy {
    IdleCallback,
    Scheduler,
}

enum class WaitUntilStrategy {
    Scheduler,
    Worker,
}

private fun Window.asWebWindow(): WebWindow =
    this as? WebWindow ?: throw IllegalStateException(
        "This window is not a WebWindow (${this::class.simpleName})"
    )

fun Window.canvas(): Any? = asWebWindow().bridge.getCanvasElement()

fun Window.setPreventDefault(prevent: Boolean) {
    asWebWindow().bridge.preventDefaultEnabled = prevent
}

fun ActiveEventLoop.setPollStrategy(strategy: PollStrategy) {
    (this as? WebEventLoop)?.let { it.pollStrategy = strategy }
}

fun ActiveEventLoop.setWaitUntilStrategy(strategy: WaitUntilStrategy) {
    (this as? WebEventLoop)?.let { it.waitUntilStrategy = strategy }
}

fun ActiveEventLoop.createCustomCursorAsync(image: CursorImage): CustomCursor? {
    return createCustomCursor(image)
}
