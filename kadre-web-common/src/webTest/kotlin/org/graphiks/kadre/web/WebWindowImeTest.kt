/**
 * Tests for the IME support in [WebWindow].
 *
 * All tests use a stub [WebDomBridge] — no real DOM is required.
 */
package org.graphiks.kadre.web

import org.graphiks.kadre.core.ImePurpose
import org.graphiks.kadre.core.PhysicalPosition
import org.graphiks.kadre.core.PhysicalSize
import kotlin.test.Test
import kotlin.test.assertEquals

// ---------------------------------------------------------------------------
// Stub bridge that records IME method calls
// ---------------------------------------------------------------------------

private class ImeRecordingBridge : WebDomBridge {
    override var onWindowEvent: ((WebWindowEvent) -> Unit)? = null
    override fun attach(targetElementId: String) {}
    override fun detach() {}

    var lastImePurpose: String? = null
    var lastCursorAreaX: Int? = null
    var lastCursorAreaY: Int? = null
    var lastCursorAreaW: Int? = null
    var lastCursorAreaH: Int? = null

    override fun setImePurpose(purpose: String) {
        lastImePurpose = purpose
    }

    override fun setImeCursorArea(x: Int, y: Int, width: Int, height: Int) {
        lastCursorAreaX = x
        lastCursorAreaY = y
        lastCursorAreaW = width
        lastCursorAreaH = height
    }
}

// ---------------------------------------------------------------------------
// WebWindow — setImePurpose
// ---------------------------------------------------------------------------

class WebWindowImeTest {

    @Test
    fun `setImePurpose delegates purpose to bridge`() {
        val bridge = ImeRecordingBridge()
        val window = WebWindow("canvas", bridge)

        window.setImePurpose(ImePurpose.Terminal)

        assertEquals("terminal", bridge.lastImePurpose)
    }

    @Test
    fun `setImePurpose Normal delegates normal`() {
        val bridge = ImeRecordingBridge()
        val window = WebWindow("canvas", bridge)

        window.setImePurpose(ImePurpose.Normal)

        assertEquals("normal", bridge.lastImePurpose)
    }

    @Test
    fun `setImePurpose Password delegates password`() {
        val bridge = ImeRecordingBridge()
        val window = WebWindow("canvas", bridge)

        window.setImePurpose(ImePurpose.Password)

        assertEquals("password", bridge.lastImePurpose)
    }

    @Test
    fun `setImePurpose Terminal delegates terminal`() {
        val bridge = ImeRecordingBridge()
        val window = WebWindow("canvas", bridge)

        window.setImePurpose(ImePurpose.Terminal)

        assertEquals("terminal", bridge.lastImePurpose)
    }

    // -----------------------------------------------------------------------
    // WebWindow — setImeCursorArea
    // -----------------------------------------------------------------------

    @Test
    fun `setImeCursorArea delegates position and size to bridge`() {
        val bridge = ImeRecordingBridge()
        val window = WebWindow("canvas", bridge)

        window.setImeCursorArea(
            position = PhysicalPosition(100, 200),
            size = PhysicalSize(30, 40),
        )

        assertEquals(100, bridge.lastCursorAreaX)
        assertEquals(200, bridge.lastCursorAreaY)
        assertEquals(30, bridge.lastCursorAreaW)
        assertEquals(40, bridge.lastCursorAreaH)
    }

    @Test
    fun `setImeCursorArea with zero values delegates correctly`() {
        val bridge = ImeRecordingBridge()
        val window = WebWindow("canvas", bridge)

        window.setImeCursorArea(
            position = PhysicalPosition(0, 0),
            size = PhysicalSize(0, 0),
        )

        assertEquals(0, bridge.lastCursorAreaX)
        assertEquals(0, bridge.lastCursorAreaY)
        assertEquals(0, bridge.lastCursorAreaW)
        assertEquals(0, bridge.lastCursorAreaH)
    }

    @Test
    fun `setImeCursorArea with large values delegates correctly`() {
        val bridge = ImeRecordingBridge()
        val window = WebWindow("canvas", bridge)

        window.setImeCursorArea(
            position = PhysicalPosition(1920, 1080),
            size = PhysicalSize(500, 300),
        )

        assertEquals(1920, bridge.lastCursorAreaX)
        assertEquals(1080, bridge.lastCursorAreaY)
        assertEquals(500, bridge.lastCursorAreaW)
        assertEquals(300, bridge.lastCursorAreaH)
    }

    // -----------------------------------------------------------------------
    // WebWindow — setImeAllowed
    // -----------------------------------------------------------------------

    @Test
    fun `setImeAllowed true then false does not throw`() {
        val bridge = ImeRecordingBridge()
        val window = WebWindow("canvas", bridge)

        // These delegate to the bridge; should not throw even without real DOM.
        window.setImeAllowed(true)
        window.setImeAllowed(false)
    }
}
