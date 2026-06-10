package org.graphiks.kadre.win32

import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlin.test.assertEquals

private fun isWindows(): Boolean =
    System.getProperty("os.name", "").contains("Windows", ignoreCase = true)

class Win32NativeIntegrationTest {

    @Test
    fun `GetGestureInfo binding resolves on Windows`() {
        if (!isWindows()) return
        assertNotNull(getGestureInfo)
        assertNotNull(closeGestureInfoHandle)
    }

    @Test
    fun `DragAcceptFiles binding resolves on Windows`() {
        if (!isWindows()) return
        assertNotNull(dragAcceptFiles)
        assertNotNull(dragQueryFileW)
        assertNotNull(dragQueryPoint)
        assertNotNull(dragFinish)
    }

    @Test
    fun `ImmGetContext binding resolves on Windows`() {
        if (!isWindows()) return
        assertNotNull(immGetContext)
        assertNotNull(immSetConversionStatus)
        assertNotNull(immReleaseContext)
    }

    @Test
    fun `GESTUREINFO_SIZE matches Windows struct`() {
        assertEquals(48, GESTUREINFO_SIZE)
    }
}
