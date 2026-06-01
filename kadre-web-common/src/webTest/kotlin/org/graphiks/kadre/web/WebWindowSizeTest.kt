/**
 * Tests for the dynamic size / scale state of [WebWindow].
 *
 * All tests use a stub [WebDomBridge] — no real DOM is required.
 * They run on both JS and wasmJs targets via the webTest source set.
 */
package org.graphiks.kadre.web

import org.graphiks.kadre.core.PhysicalSize
import kotlin.test.Test
import kotlin.test.assertEquals

// ---------------------------------------------------------------------------
// Stub bridge for unit tests
// ---------------------------------------------------------------------------

private class StubWebDomBridge(
    private val stubDpr: Double = 1.0,
    private val stubWidth: Int = 0,
    private val stubHeight: Int = 0,
) : WebDomBridge {
    override var onWindowEvent: ((WebWindowEvent) -> Unit)? = null
    override fun attach(targetElementId: String) {}
    override fun detach() {}
    override fun readDevicePixelRatio(): Double = stubDpr
    override fun readCanvasPhysicalSize(canvasId: String): Pair<Int, Int> =
        Pair(stubWidth, stubHeight)
}

// ---------------------------------------------------------------------------
// WebWindow — initial state from bridge
// ---------------------------------------------------------------------------

class WebWindowSizeTest {

    @Test
    fun `initial innerSize reflects bridge readCanvasPhysicalSize`() {
        val bridge = StubWebDomBridge(stubDpr = 2.0, stubWidth = 1280, stubHeight = 960)
        val loop = object : WebEventLoop() {}
        // Simulate what createWindow does: init + assign callbacks.
        val (w, h) = bridge.readCanvasPhysicalSize("canvas")
        val window = WebWindow("canvas", bridge)
        window.updatePhysicalSize(w, h)
        window.updateScaleFactor(bridge.readDevicePixelRatio())

        assertEquals(PhysicalSize(1280, 960), window.innerSize)
        assertEquals(PhysicalSize(1280, 960), window.outerSize)
    }

    @Test
    fun `initial scaleFactor reflects bridge readDevicePixelRatio`() {
        val bridge = StubWebDomBridge(stubDpr = 3.0)
        val window = WebWindow("canvas", bridge)
        window.updateScaleFactor(bridge.readDevicePixelRatio())

        assertEquals(3.0, window.scaleFactor)
    }

    @Test
    fun `default state before any update is 0x0 and scale 1-0`() {
        val bridge = StubWebDomBridge()
        val window = WebWindow("canvas", bridge)
        // No updatePhysicalSize / updateScaleFactor called yet.
        assertEquals(PhysicalSize(0, 0), window.innerSize)
        assertEquals(1.0, window.scaleFactor)
    }

    // -----------------------------------------------------------------------
    // updatePhysicalSize — dynamic updates
    // -----------------------------------------------------------------------

    @Test
    fun `updatePhysicalSize changes innerSize and outerSize`() {
        val window = WebWindow("canvas", StubWebDomBridge())
        window.updatePhysicalSize(640, 480)
        assertEquals(PhysicalSize(640, 480), window.innerSize)
        assertEquals(PhysicalSize(640, 480), window.outerSize)
    }

    @Test
    fun `multiple updatePhysicalSize calls keep the latest value`() {
        val window = WebWindow("canvas", StubWebDomBridge())
        window.updatePhysicalSize(800, 600)
        window.updatePhysicalSize(1920, 1080)
        assertEquals(PhysicalSize(1920, 1080), window.innerSize)
    }

    // -----------------------------------------------------------------------
    // updateScaleFactor — dynamic updates
    // -----------------------------------------------------------------------

    @Test
    fun `updateScaleFactor changes scaleFactor`() {
        val window = WebWindow("canvas", StubWebDomBridge())
        window.updateScaleFactor(2.0)
        assertEquals(2.0, window.scaleFactor)
    }

    @Test
    fun `multiple updateScaleFactor calls keep the latest value`() {
        val window = WebWindow("canvas", StubWebDomBridge())
        window.updateScaleFactor(1.5)
        window.updateScaleFactor(2.0)
        assertEquals(2.0, window.scaleFactor)
    }

    // -----------------------------------------------------------------------
    // WebEventLoop — event-driven updates via onWindowEvent callback
    // -----------------------------------------------------------------------

    @Test
    fun `WebEventLoop createWindow wires Resized event to window size`() {
        val bridge = StubWebDomBridge(stubDpr = 1.0, stubWidth = 0, stubHeight = 0)
        val loop = object : WebEventLoop() {
            override fun createDomBridge(): WebDomBridge = bridge
        }
        val window = loop.createWindow(WebWindowAttributes(canvasId = "canvas")) as WebWindow

        // Simulate a ResizeObserver callback arriving via the bridge.
        bridge.onWindowEvent?.invoke(WebWindowEvent.Resized(1024, 768))

        assertEquals(PhysicalSize(1024, 768), window.innerSize)
    }

    @Test
    fun `WebEventLoop createWindow wires ScaleFactorChanged event to scaleFactor`() {
        val bridge = StubWebDomBridge(stubDpr = 1.0)
        val loop = object : WebEventLoop() {
            override fun createDomBridge(): WebDomBridge = bridge
        }
        val window = loop.createWindow(WebWindowAttributes(canvasId = "canvas")) as WebWindow

        // Simulate a DPR-change callback arriving via the bridge.
        bridge.onWindowEvent?.invoke(WebWindowEvent.ScaleFactorChanged(2.0))

        assertEquals(2.0, window.scaleFactor)
    }

    @Test
    fun `WebEventLoop createWindow initialises size from bridge synchronously`() {
        val bridge = StubWebDomBridge(stubDpr = 2.0, stubWidth = 800, stubHeight = 600)
        val loop = object : WebEventLoop() {
            override fun createDomBridge(): WebDomBridge = bridge
        }
        val window = loop.createWindow(WebWindowAttributes(canvasId = "canvas")) as WebWindow

        // Should already reflect the bridge's initial state without waiting for any event.
        assertEquals(PhysicalSize(800, 600), window.innerSize)
        assertEquals(2.0, window.scaleFactor)
    }
}
