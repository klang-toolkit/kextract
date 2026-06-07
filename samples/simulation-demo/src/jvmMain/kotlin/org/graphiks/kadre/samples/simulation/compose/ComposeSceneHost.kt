package org.graphiks.kadre.samples.simulation.compose

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

class ComposeSceneHost(scaleFactor: Double, private val dispatcher: EventLoopDispatcher) {

    private val frameClock = BroadcastFrameClock()
    private val scene: ComposeScene = CanvasLayersComposeScene(
        density = Density(scaleFactor.toFloat()),
        layoutDirection = LayoutDirection.Ltr,
        size = IntSize(1, 1),
        coroutineContext = dispatcher + frameClock,
        invalidate = { },
    )

    private var pressedButtons = 0

    private var lastPointer = Offset.Zero

    fun setContent(content: @Composable () -> Unit) = scene.setContent(content)

    fun setDensityAndSize(widthPx: Int, heightPx: Int, scaleFactor: Double) {
        if (widthPx <= 0 || heightPx <= 0) return
        scene.density = Density(scaleFactor.toFloat())
        scene.size = IntSize(widthPx, heightPx)
    }

    fun pumpAndRender(skiaCanvas: Canvas, widthPx: Int, heightPx: Int) {
        Snapshot.sendApplyNotifications()
        dispatcher.pump()
        frameClock.sendFrame(System.nanoTime())
        dispatcher.pump()
        if (scene.size != IntSize(widthPx, heightPx)) {
            scene.size = IntSize(widthPx, heightPx)
        }
        scene.render(skiaCanvas.asComposeCanvas(), System.nanoTime())
    }

    fun close() = runCatching { scene.close() }

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

    fun sendKey(awtEvent: java.awt.event.KeyEvent) {
        scene.sendKeyEvent(awtKeyToComposeKeyEvent(awtEvent))
    }
}

internal fun awtKeyToComposeKeyEvent(awtEvent: java.awt.event.KeyEvent): KeyEvent {
    val internal = ComposeKeyEventBridge.toComposeEvent.invoke(null, awtEvent)
    return ComposeKeyEventBridge.box.invoke(null, internal) as KeyEvent
}

private object ComposeKeyEventBridge {
    val toComposeEvent: java.lang.reflect.Method =
        Class.forName("androidx.compose.ui.input.key.KeyEvent_desktopKt")
            .getMethod("toComposeEvent", java.awt.event.KeyEvent::class.java)

    val box: java.lang.reflect.Method =
        KeyEvent::class.java.getMethod("box-impl", Any::class.java)
}
