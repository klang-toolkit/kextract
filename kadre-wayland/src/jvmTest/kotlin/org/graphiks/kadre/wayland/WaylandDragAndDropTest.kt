package org.graphiks.kadre.wayland

import org.graphiks.kadre.core.PhysicalPosition
import org.graphiks.kadre.core.WindowEvent
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs
import kotlin.test.assertTrue

class WaylandDragAndDropTest {

    @Test
    fun `wlFixedToDouble converts fixed-point to double`() {
        assertEquals(0.0, wlFixedToDouble(0))
        assertEquals(1.0, wlFixedToDouble(256))
        assertEquals(-1.0, wlFixedToDouble(-256))
        assertEquals(0.5, wlFixedToDouble(128))
        assertEquals(100.25, wlFixedToDouble(25664))
    }

    @Test
    fun `uri-list parsing extracts file paths from file URIs`() {
        val data = "file:///home/user/file.txt\r\nfile:///home/user/file2.txt\r\n".toByteArray()
        val paths = WaylandDragAndDrop.parseUriList(data)
        assertEquals(2, paths.size)
        assertTrue(paths[0].endsWith("file.txt"))
        assertTrue(paths[1].endsWith("file2.txt"))
    }

    @Test
    fun `uri-list parsing skips comments`() {
        val data = "# comment line\r\nfile:///home/user/file.txt\r\n".toByteArray()
        val paths = WaylandDragAndDrop.parseUriList(data)
        assertEquals(1, paths.size)
    }

    @Test
    fun `uri-list parsing handles percent-encoded paths`() {
        val data = "file:///home/user/file%20with%20spaces.txt\r\n".toByteArray()
        val paths = WaylandDragAndDrop.parseUriList(data)
        assertEquals(1, paths.size)
        assertEquals("/home/user/file with spaces.txt", paths[0])
    }

    @Test
    fun `uri-list parsing returns empty list for empty input`() {
        assertTrue(WaylandDragAndDrop.parseUriList(ByteArray(0)).isEmpty())
    }

    @Test
    fun `DragEntered event carries position and paths`() {
        val position = PhysicalPosition(100.5, 200.25)
        val event = WindowEvent.DragEntered(position, listOf("/path/file.txt"))
        assertEquals(100.5, event.position.x)
        assertEquals(200.25, event.position.y)
        assertEquals(1, event.paths.size)
        assertEquals("/path/file.txt", event.paths[0])
    }

    @Test
    fun `DragMoved event carries position`() {
        val position = PhysicalPosition(150.0, 250.0)
        val event = WindowEvent.DragMoved(position)
        assertEquals(150.0, event.position.x)
        assertEquals(250.0, event.position.y)
    }

    @Test
    fun `DragDropped event carries position and paths`() {
        val position = PhysicalPosition(300.0, 400.0)
        val event = WindowEvent.DragDropped(position, listOf("/a.txt", "/b.txt"))
        assertEquals(300.0, event.position.x)
        assertEquals(2, event.paths.size)
        assertEquals("/b.txt", event.paths[1])
    }

    @Test
    fun `DragLeft is a singleton data object`() {
        assertIs<WindowEvent>(WindowEvent.DragLeft)
    }

    @Test
    fun `seatHasCapability detects pointer bit`() {
        assertTrue(seatHasCapability(1, 1))
        assertTrue(!seatHasCapability(0, 1))
        assertTrue(!seatHasCapability(2, 1))
        assertTrue(seatHasCapability(3, 1))
    }
}
