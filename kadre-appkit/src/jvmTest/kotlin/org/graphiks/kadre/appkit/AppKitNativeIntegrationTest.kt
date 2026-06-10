package org.graphiks.kadre.appkit

import org.graphiks.kadre.appkit.bindings.ObjCRuntime
import kotlin.test.Test
import kotlin.test.assertNotNull

class AppKitNativeIntegrationTest {

    private fun isMacOs(): Boolean =
        System.getProperty("os.name", "").contains("Mac", ignoreCase = true) ||
        System.getProperty("os.name", "").contains("macOS", ignoreCase = true) ||
        System.getProperty("os.name", "").contains("Darwin", ignoreCase = true)

    @Test
    fun `ObjC runtime resolves on macOS`() {
        if (!isMacOs()) return
        assertNotNull(ObjCRuntime.sel("alloc"))
        assertNotNull(ObjCRuntime.getClass("NSObject"))
    }

    @Test
    fun `NSDraggingDestination selectors resolve on macOS`() {
        if (!isMacOs()) return
        assertNotNull(ObjCRuntime.sel("draggingEntered:"))
        assertNotNull(ObjCRuntime.sel("draggingUpdated:"))
        assertNotNull(ObjCRuntime.sel("draggingExited:"))
        assertNotNull(ObjCRuntime.sel("performDragOperation:"))
        assertNotNull(ObjCRuntime.sel("draggingEnded:"))
    }

    @Test
    fun `registerForDraggedTypes selector resolves on macOS`() {
        if (!isMacOs()) return
        assertNotNull(ObjCRuntime.sel("registerForDraggedTypes:"))
    }

    @Test
    fun `NSWindowDelegate selectors resolve on macOS`() {
        if (!isMacOs()) return
        assertNotNull(ObjCRuntime.sel("windowDidMiniaturize:"))
        assertNotNull(ObjCRuntime.sel("windowDidDeminiaturize:"))
    }

    @Test
    fun `NSScreen and safeArea selectors resolve on macOS`() {
        if (!isMacOs()) return
        assertNotNull(ObjCRuntime.sel("mainScreen"))
        assertNotNull(ObjCRuntime.sel("safeAreaInsets"))
        assertNotNull(ObjCRuntime.sel("contentLayoutRect"))
    }
}
