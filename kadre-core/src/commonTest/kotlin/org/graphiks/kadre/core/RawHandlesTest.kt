/**
 * Exhaustiveness tests for the sealed interfaces [RawWindowHandle] and [RawDisplayHandle].
 *
 * Each `when` branch is written WITHOUT an `else` clause so that the Kotlin
 * compiler reports an error at compile time if a variant were to be added
 * without updating these tests (exhaustiveness guarantee).
 */
package org.graphiks.kadre.core

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNull
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class RawHandlesTest {

    // -------------------------------------------------------------------------
    // RawWindowHandle — exhaustiveness
    // -------------------------------------------------------------------------

    /**
     * Verifies that the `when` on [RawWindowHandle] covers all variants without `else`.
     */
    @Test
    fun `when sur RawWindowHandle est exhaustif`() {
        val handles: List<RawWindowHandle> = listOf(
            RawWindowHandle.AppKit(nsView = 1L, nsWindow = 2L),
            RawWindowHandle.UiKit(uiView = 3L, uiViewController = 4L),
            RawWindowHandle.UiKit(uiView = 5L, uiViewController = null),
            RawWindowHandle.Android(surface = Any()),
            RawWindowHandle.Win32(hwnd = 6L, hinstance = 7L),
            RawWindowHandle.Web(canvasElementId = "my-canvas"),
            RawWindowHandle.Xlib(window = 8L, display = 9L),
            RawWindowHandle.Wayland(surface = 10L, display = 11L),
        )

        for (handle in handles) {
            // Without an `else` clause — the compiler guarantees exhaustiveness.
            val nom: String = when (handle) {
                is RawWindowHandle.AppKit   -> "AppKit"
                is RawWindowHandle.UiKit    -> "UiKit"
                is RawWindowHandle.Android  -> "Android"
                is RawWindowHandle.Win32    -> "Win32"
                is RawWindowHandle.Web      -> "Web"
                is RawWindowHandle.Xlib     -> "Xlib"
                is RawWindowHandle.Wayland  -> "Wayland"
            }
            assertNotNull(nom)
        }
    }

    @Test
    fun `AppKit expose nsView et nsWindow`() {
        val handle = RawWindowHandle.AppKit(nsView = 0xDEADBEEFL, nsWindow = 0xCAFEBABEL)
        assertEquals(0xDEADBEEFL, handle.nsView)
        assertEquals(0xCAFEBABEL, handle.nsWindow)
    }

    @Test
    fun `UiKit expose uiView et uiViewController nullable`() {
        val avecControleur = RawWindowHandle.UiKit(uiView = 10L, uiViewController = 20L)
        assertEquals(10L, avecControleur.uiView)
        assertEquals(20L, avecControleur.uiViewController)

        val sansControleur = RawWindowHandle.UiKit(uiView = 10L, uiViewController = null)
        assertEquals(10L, sansControleur.uiView)
        assertNull(sansControleur.uiViewController)
    }

    @Test
    fun `Android encapsule la surface comme Any`() {
        val surfaceMock = object {}
        val handle = RawWindowHandle.Android(surface = surfaceMock)
        assertTrue(handle.surface === surfaceMock)
    }

    // -------------------------------------------------------------------------
    // RawDisplayHandle — exhaustiveness
    // -------------------------------------------------------------------------

    /**
     * Verifies that the `when` on [RawDisplayHandle] covers all variants without `else`.
     */
    @Test
    fun `when sur RawDisplayHandle est exhaustif`() {
        val handles: List<RawDisplayHandle> = listOf(
            RawDisplayHandle.AppKit,
            RawDisplayHandle.UiKit,
            RawDisplayHandle.Android,
            RawDisplayHandle.Win32(hinstance = 8L),
            RawDisplayHandle.Web,
            RawDisplayHandle.Xlib(display = 12L),
            RawDisplayHandle.Wayland(display = 13L),
        )

        for (handle in handles) {
            // Without an `else` clause — the compiler guarantees exhaustiveness.
            val nom: String = when (handle) {
                RawDisplayHandle.AppKit      -> "AppKit"
                RawDisplayHandle.UiKit       -> "UiKit"
                RawDisplayHandle.Android     -> "Android"
                is RawDisplayHandle.Win32    -> "Win32"
                RawDisplayHandle.Web         -> "Web"
                is RawDisplayHandle.Xlib     -> "Xlib"
                is RawDisplayHandle.Wayland  -> "Wayland"
            }
            assertNotNull(nom)
        }
    }

    @Test
    fun `RawDisplayHandle AppKit est un singleton`() {
        assertTrue(RawDisplayHandle.AppKit === RawDisplayHandle.AppKit)
    }

    @Test
    fun `RawDisplayHandle UiKit est un singleton`() {
        assertTrue(RawDisplayHandle.UiKit === RawDisplayHandle.UiKit)
    }

    @Test
    fun `RawDisplayHandle Android est un singleton`() {
        assertTrue(RawDisplayHandle.Android === RawDisplayHandle.Android)
    }

    // -------------------------------------------------------------------------
    // Win32 — specific tests
    // -------------------------------------------------------------------------

    @Test
    fun `RawWindowHandle Win32 expose hwnd et hinstance`() {
        val handle = RawWindowHandle.Win32(hwnd = 0xDEADBEEFL, hinstance = 0xCAFEBABEL)
        assertEquals(0xDEADBEEFL, handle.hwnd)
        assertEquals(0xCAFEBABEL, handle.hinstance)
    }

    @Test
    fun `RawDisplayHandle Win32 expose hinstance`() {
        val handle = RawDisplayHandle.Win32(hinstance = 0xCAFEBABEL)
        assertEquals(0xCAFEBABEL, handle.hinstance)
    }

    // -------------------------------------------------------------------------
    // Web — specific tests
    // -------------------------------------------------------------------------

    @Test
    fun `RawWindowHandle Web id only est valide`() {
        val handle = RawWindowHandle.Web(canvasElementId = "my-canvas")
        assertEquals("my-canvas", handle.canvasElementId)
        assertNull(handle.canvasElement)
    }

    @Test
    fun `RawWindowHandle Web element only est valide`() {
        val element = object {}
        val handle = RawWindowHandle.Web(canvasElement = element)
        assertNull(handle.canvasElementId)
        assertTrue(handle.canvasElement === element)
    }

    @Test
    fun `RawWindowHandle Web les deux fournis est valide`() {
        val element = object {}
        val handle = RawWindowHandle.Web(canvasElementId = "my-canvas", canvasElement = element)
        assertEquals("my-canvas", handle.canvasElementId)
        assertTrue(handle.canvasElement === element)
    }

    @Test
    fun `RawWindowHandle Web les deux null leve IllegalArgumentException`() {
        assertFailsWith<IllegalArgumentException> {
            RawWindowHandle.Web(canvasElementId = null, canvasElement = null)
        }
    }

    @Test
    fun `RawDisplayHandle Web est un singleton`() {
        assertTrue(RawDisplayHandle.Web === RawDisplayHandle.Web)
    }
}
