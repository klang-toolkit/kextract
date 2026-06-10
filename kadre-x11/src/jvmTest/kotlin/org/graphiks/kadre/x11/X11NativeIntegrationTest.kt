package org.graphiks.kadre.x11

import org.graphiks.kadre.ffi.x11.*
import java.lang.foreign.MemorySegment
import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class X11NativeIntegrationTest {

    private fun isLinux(): Boolean =
        System.getProperty("os.name", "").contains("Linux", ignoreCase = true)

    private fun hasDisplay(): Boolean =
        System.getenv("DISPLAY") != null

    @Test
    fun `X11 library symbols resolve on Linux`() {
        if (!isLinux()) return
        assertNotNull(xOpenDisplay)
        assertNotNull(xInternAtom)
        assertNotNull(xConvertSelection)
        assertNotNull(xCloseDisplay)
    }

    @Test
    fun `Xdnd atoms are defined correctly`() {
        if (!isLinux() || !hasDisplay()) return
        val display = xOpenDisplay?.invokeExact(MemorySegment.NULL) as? MemorySegment ?: return
        try {
            assertTrue(x11DragAndDropAtom(display, "XdndAware") != 0L)
            assertTrue(x11DragAndDropAtom(display, "XdndEnter") != 0L)
            assertTrue(x11DragAndDropAtom(display, "XdndPosition") != 0L)
            assertTrue(x11DragAndDropAtom(display, "XdndDrop") != 0L)
            assertTrue(x11DragAndDropAtom(display, "XdndLeave") != 0L)
            assertTrue(x11DragAndDropAtom(display, "XdndSelection") != 0L)
            assertTrue(x11DragAndDropAtom(display, "XdndStatus") != 0L)
            assertTrue(x11DragAndDropAtom(display, "XdndFinished") != 0L)
            assertTrue(x11DragAndDropAtom(display, "text/uri-list") != 0L)
        } finally {
            xCloseDisplay?.invokeExact(display) as? Int
        }
    }

    @Test
    fun `XConvertSelection binding is non-null`() {
        if (!isLinux()) return
        assertNotNull(xConvertSelection)
    }

    @Test
    fun `X11 display connection works when DISPLAY is set`() {
        if (!isLinux() || !hasDisplay()) return
        val display = xOpenDisplay?.invokeExact(MemorySegment.NULL) as? MemorySegment
        assertNotNull(display)
        xCloseDisplay?.invokeExact(display) as? Int
    }
}
