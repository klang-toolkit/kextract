package org.graphiks.kadre.web

import org.graphiks.kadre.core.ActiveEventLoop
import org.graphiks.kadre.core.ApplicationHandler
import org.graphiks.kadre.core.StartCause
import org.graphiks.kadre.core.WindowEvent
import org.graphiks.kadre.core.WindowId
import kotlin.test.Test
import kotlin.test.assertEquals

class WebEventLoopTest {

    @Test
    fun `dom events are dispatched to the owning web window`() {
        val firstBridge = RecordingBridge()
        val secondBridge = RecordingBridge()
        val loop = TestWebEventLoop(listOf(firstBridge, secondBridge))

        val firstWindow = loop.createWindow(WebWindowAttributes(canvasId = "first-canvas"))
        val secondWindow = loop.createWindow(WebWindowAttributes(canvasId = "second-canvas"))
        val handler = RecordingHandler()

        firstBridge.emit(WebWindowEvent.Focused(true))
        secondBridge.emit(WebWindowEvent.RedrawRequested)
        loop.pump(handler)

        val expected: List<Pair<WindowId, WindowEvent>> =
            listOf(
                firstWindow.id to WindowEvent.Focused(true),
                secondWindow.id to WindowEvent.RedrawRequested,
            )

        assertEquals(expected, handler.windowEvents)
    }

    @Test
    fun `default web windows receive distinct internal ids`() {
        val firstBridge = RecordingBridge()
        val secondBridge = RecordingBridge()
        val loop = TestWebEventLoop(listOf(firstBridge, secondBridge))

        val firstWindow = loop.createWindow(WebWindowAttributes())
        val secondWindow = loop.createWindow(WebWindowAttributes())
        val handler = RecordingHandler()

        firstBridge.emit(WebWindowEvent.RedrawRequested)
        secondBridge.emit(WebWindowEvent.RedrawRequested)
        loop.pump(handler)

        assertEquals(listOf(WindowId(1L), WindowId(2L)), listOf(firstWindow.id, secondWindow.id))
        val expected: List<Pair<WindowId, WindowEvent>> =
            listOf(
                firstWindow.id to WindowEvent.RedrawRequested,
                secondWindow.id to WindowEvent.RedrawRequested,
            )

        assertEquals(expected, handler.windowEvents)
    }

    private class TestWebEventLoop(
        private val bridges: List<RecordingBridge>,
    ) : WebEventLoop() {
        private var nextBridge = 0

        fun pump(handler: ApplicationHandler) {
            tick(handler)
        }

        override fun createDomBridge(): WebDomBridge = bridges[nextBridge++]
    }

    private class RecordingBridge : WebDomBridge {
        override var onWindowEvent: ((WebWindowEvent) -> Unit)? = null
        val attachedCanvasIds = mutableListOf<String>()
        var detached = false

        override fun attach(targetElementId: String) {
            attachedCanvasIds += targetElementId
        }

        override fun detach() {
            detached = true
        }

        fun emit(event: WebWindowEvent) {
            onWindowEvent?.invoke(event)
        }
    }

    private class RecordingHandler : ApplicationHandler {
        val windowEvents = mutableListOf<Pair<WindowId, WindowEvent>>()

        override fun canCreateSurfaces(eventLoop: ActiveEventLoop) = Unit

        override fun newEvents(eventLoop: ActiveEventLoop, startCause: StartCause) = Unit

        override fun windowEvent(eventLoop: ActiveEventLoop, windowId: WindowId, event: WindowEvent) {
            windowEvents += windowId to event
        }
    }
}
