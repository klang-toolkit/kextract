/**
 * Platform-agnostic host around a low-level Compose [ComposeScene].
 *
 * Owns everything that does NOT depend on the GPU/present backend: the scene, the frame
 * clock that drives recomposition/animation, the input forwarding, and the per-frame pump
 * that renders the scene into a Skia [org.jetbrains.skia.Canvas].
 *
 * The platform renderers ([MetalComposeRenderer], [GlComposeRenderer]) own only the
 * present path (acquire a Skia surface, call [pumpAndRender], present) and delegate scene
 * and input handling here.
 *
 * Threading: the scene's recomposer runs on [Dispatchers.Unconfined] + [frameClock], so its
 * continuations resume synchronously on the caller (main) thread when [pumpAndRender] ticks
 * the clock and applies snapshot notifications each frame.
 */
package org.graphiks.kadre.samples.hellocompose

import androidx.compose.runtime.BroadcastFrameClock
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.Snapshot
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.asComposeCanvas
import androidx.compose.ui.input.key.KeyEvent
import androidx.compose.ui.input.pointer.PointerButton
import androidx.compose.ui.input.pointer.PointerButtons
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.scene.CanvasLayersComposeScene
import androidx.compose.ui.scene.ComposeScene
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.LayoutDirection
import org.graphiks.kadre.coroutines.EventLoopDispatcher
import org.jetbrains.skia.Canvas

/**
 * @param dispatcher the Kadre main-thread coroutine dispatcher (Level 1). The scene's recomposer
 *   runs on it — unified with app coroutines — and [frameClock] (a [MonotonicFrameClock]) is
 *   advanced from the render loop so `withFrameNanos`/Compose animations work natively (Level 2).
 *   This replaces the previous Dispatchers.Unconfined + standalone-clock hack.
 */
class ComposeSceneHost(scaleFactor: Double, private val dispatcher: EventLoopDispatcher) {

    private val frameClock = BroadcastFrameClock()
    private val scene: ComposeScene = CanvasLayersComposeScene(
        density = Density(scaleFactor.toFloat()),
        layoutDirection = LayoutDirection.Ltr,
        size = IntSize(1, 1), // real size set by the first resize() before rendering
        coroutineContext = dispatcher + frameClock,
        invalidate = { /* continuous redraw is driven by the host loop */ },
    )

    /** Current pressed-button bitmask in Compose [PointerButtons] encoding. */
    private var pressedButtons = 0

    /** Last known pointer position (physical px) — synthesized for press/release/scroll. */
    private var lastPointer = Offset.Zero

    fun setContent(content: @Composable () -> Unit) = scene.setContent(content)

    /** Updates the Compose scene density + size. Sizes are physical pixels. */
    fun setDensityAndSize(widthPx: Int, heightPx: Int, scaleFactor: Double) {
        if (widthPx <= 0 || heightPx <= 0) return
        scene.density = Density(scaleFactor.toFloat())
        scene.size = IntSize(widthPx, heightPx)
    }

    /**
     * Pumps Compose state + the frame clock, then renders the scene into [skiaCanvas].
     * [widthPx]/[heightPx] keep the scene sized to the actual surface (covers resize races).
     */
    fun pumpAndRender(skiaCanvas: Canvas, widthPx: Int, heightPx: Int) {
        Snapshot.sendApplyNotifications()
        // Let the recomposer compose and reach its withFrameNanos await…
        dispatcher.pump()
        // …deliver a frame (wakes animations / withFrameNanos awaiters)…
        frameClock.sendFrame(System.nanoTime())
        // …then run the resumed continuations (animation values + recomposition).
        dispatcher.pump()
        if (scene.size != IntSize(widthPx, heightPx)) {
            scene.size = IntSize(widthPx, heightPx)
        }
        scene.render(skiaCanvas.asComposeCanvas(), System.nanoTime())
    }

    fun close() = runCatching { scene.close() }

    // ── Input forwarding ──────────────────────────────────────────────────────

    fun onPointerMoved(xPhysical: Double, yPhysical: Double) {
        lastPointer = Offset(xPhysical.toFloat(), yPhysical.toFloat())
        scene.sendPointerEvent(
            eventType = PointerEventType.Move,
            position = lastPointer,
            buttons = PointerButtons(pressedButtons),
        )
    }

    fun onPointerButton(bit: Int, pressed: Boolean, button: PointerButton) {
        pressedButtons = if (pressed) pressedButtons or bit else pressedButtons and bit.inv()
        scene.sendPointerEvent(
            eventType = if (pressed) PointerEventType.Press else PointerEventType.Release,
            position = lastPointer,
            buttons = PointerButtons(pressedButtons),
            button = button,
        )
    }

    fun onScroll(deltaX: Double, deltaY: Double) {
        scene.sendPointerEvent(
            eventType = PointerEventType.Scroll,
            position = lastPointer,
            scrollDelta = Offset(deltaX.toFloat(), deltaY.toFloat()),
            buttons = PointerButtons(pressedButtons),
        )
    }

    fun onPointerEnter() =
        scene.sendPointerEvent(eventType = PointerEventType.Enter, position = lastPointer)

    fun onPointerExit() =
        scene.sendPointerEvent(eventType = PointerEventType.Exit, position = lastPointer)

    /**
     * Forwards a keyboard event. Compose text fields only insert characters for genuine AWT
     * `KEY_TYPED` events (see `TextFieldKeyInput.isTypedEvent`), so callers pass real
     * [java.awt.event.KeyEvent]s; [awtKeyToComposeKeyEvent] preserves the AWT event.
     */
    fun sendKey(awtEvent: java.awt.event.KeyEvent) {
        scene.sendKeyEvent(awtKeyToComposeKeyEvent(awtEvent))
    }
}

/**
 * Converts a real AWT [java.awt.event.KeyEvent] into a Compose [KeyEvent] that preserves the
 * underlying AWT event (so `getAwtEventOrNull` returns it and text fields insert characters).
 *
 * The desktop `KeyEvent.toComposeEvent()` converter is `internal` to compose-ui and returns
 * the value class's underlying `InternalKeyEvent`; we reach it reflectively and re-box it into a
 * [KeyEvent] via the synthetic `box-impl`. Both are public at the bytecode level.
 */
internal fun awtKeyToComposeKeyEvent(awtEvent: java.awt.event.KeyEvent): KeyEvent {
    val internal = ComposeKeyEventBridge.toComposeEvent.invoke(null, awtEvent)
    return ComposeKeyEventBridge.box.invoke(null, internal) as KeyEvent
}

private object ComposeKeyEventBridge {
    val toComposeEvent: java.lang.reflect.Method =
        Class.forName("androidx.compose.ui.input.key.KeyEvent_desktopKt")
            .getMethod("toComposeEvent", java.awt.event.KeyEvent::class.java)

    /** `KeyEvent.box-impl(Object): KeyEvent` — boxes the underlying InternalKeyEvent. */
    val box: java.lang.reflect.Method =
        KeyEvent::class.java.getMethod("box-impl", Any::class.java)
}
