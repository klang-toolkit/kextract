/**
 * Smoke tests for [WaylandWindow].
 *
 * These tests verify that [WaylandWindow] can be constructed with mock
 * pointers without causing a crash, and that the returned handles are correct.
 * They run on all platforms (macOS, Windows, Linux) without requiring
 * libwayland-client.so.0.
 */
package org.graphiks.kadre.wayland

import org.graphiks.kadre.core.CursorGrabMode
import org.graphiks.kadre.core.PhysicalSize
import org.graphiks.kadre.core.Fullscreen
import org.graphiks.kadre.core.RawDisplayHandle
import org.graphiks.kadre.core.RawWindowHandle
import org.graphiks.kadre.core.RequestError
import org.graphiks.kadre.core.SurfaceSizeRequestResult
import org.graphiks.kadre.core.Theme
import org.graphiks.kadre.core.WindowButtons
import org.graphiks.kadre.core.WindowEvent
import org.graphiks.kadre.core.WindowRequestResult
import org.graphiks.kadre.core.WindowAttributes
import org.graphiks.kadre.core.WindowLevel
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs
import kotlin.test.assertNotNull

class WaylandWindowTest {

    @Test
    fun `WaylandWindow can be constructed with mock pointers without crashing`() {
        // createForTest bypasses the FFM calls — works on all platforms
        val window = WaylandWindow.createForTest(
            display = 0L,
            compositor = 0L,
            xdgWmBase = 0L,
            surface = 0L,
        )
        assertNotNull(window)
    }

    @Test
    fun `rawWindowHandle returns Wayland type with correct pointers`() {
        val window = WaylandWindow.createForTest(
            display = 42L,
            compositor = 0L,
            surface = 99L,
        )
        val handle = window.rawWindowHandle
        assertIs<RawWindowHandle.Wayland>(handle)
        assertEquals(99L, handle.surface)
        assertEquals(42L, handle.display)
    }

    @Test
    fun `rawDisplayHandle returns Wayland type with correct display pointer`() {
        val window = WaylandWindow.createForTest(display = 123L)
        val handle = window.rawDisplayHandle
        assertIs<RawDisplayHandle.Wayland>(handle)
        assertEquals(123L, handle.display)
    }

    @Test
    fun `innerSize returns attrs size when provided`() {
        val attrs = WindowAttributes(size = PhysicalSize(1280, 720))
        val window = WaylandWindow.createForTest(attrs = attrs)
        assertEquals(PhysicalSize(1280, 720), window.innerSize)
    }

    @Test
    fun `innerSize returns default 800x600 when attrs size is null`() {
        val attrs = WindowAttributes(size = null)
        val window = WaylandWindow.createForTest(attrs = attrs)
        assertEquals(PhysicalSize(800, 600), window.innerSize)
    }

    @Test
    fun `outerSize equals innerSize`() {
        val window = WaylandWindow.createForTest()
        assertEquals(window.innerSize, window.outerSize)
    }

    @Test
    fun `scaleFactor is 1 0`() {
        val window = WaylandWindow.createForTest()
        assertEquals(1.0, window.scaleFactor)
    }

    @Test
    fun `onConfigure updates innerSize when dimensions are positive`() {
        val window = WaylandWindow.createForTest()
        window.onConfigure(1920, 1080)
        assertEquals(PhysicalSize(1920, 1080), window.innerSize)
    }

    @Test
    fun `requestSurfaceSize applies surface size immediately like winit Wayland`() {
        val window = WaylandWindow.createForTest()
        val events = mutableListOf<WindowEvent>()
        window.onWindowEvent = events::add

        val result = window.requestSurfaceSize(PhysicalSize(640, 480))

        assertIs<SurfaceSizeRequestResult.Applied>(result)
        assertEquals(PhysicalSize(640, 480), result.size)
        assertEquals(PhysicalSize(640, 480), window.surfaceSize)
        assertEquals(PhysicalSize(640, 480), window.innerSize)
        assertEquals(listOf<WindowEvent>(WindowEvent.RedrawRequested), events)
    }

    @Test
    fun `requestSurfaceSize keeps compositor controlled size in non stateless Wayland states`() {
        val window = WaylandWindow.createForTest(attrs = WindowAttributes(size = PhysicalSize(800, 600)))
        val events = mutableListOf<WindowEvent>()
        window.onWindowEvent = events::add
        window.onToplevelStateConfigured(WaylandToplevelConfigureStates(fullscreen = true))

        val result = window.requestSurfaceSize(PhysicalSize(640, 480))

        assertIs<SurfaceSizeRequestResult.Applied>(result)
        assertEquals(PhysicalSize(800, 600), result.size)
        assertEquals(PhysicalSize(800, 600), window.surfaceSize)
        assertEquals(emptyList<WindowEvent>(), events)
    }

    @Test
    fun `Wayland configure states stateless matches winit request surface size rule`() {
        assertEquals(true, WaylandToplevelConfigureStates().isStateless())
        assertEquals(true, WaylandToplevelConfigureStates(resizing = true).isStateless())
        assertEquals(false, WaylandToplevelConfigureStates(maximized = true).isStateless())
        assertEquals(false, WaylandToplevelConfigureStates(fullscreen = true).isStateless())
        assertEquals(false, WaylandToplevelConfigureStates(tiled = true).isStateless())
    }

    @Test
    fun `Wayland min and max surface size constraints request redraw like winit`() {
        val window = WaylandWindow.createForTest()
        val events = mutableListOf<WindowEvent>()
        window.onWindowEvent = events::add

        window.setMinSurfaceSize(PhysicalSize(320, 200))
        window.setMaxSurfaceSize(PhysicalSize(1280, 720))

        assertEquals(listOf<WindowEvent>(WindowEvent.RedrawRequested, WindowEvent.RedrawRequested), events)
    }

    @Test
    fun `Wayland setResizable requests redraw only when state changes like winit`() {
        val window = WaylandWindow.createForTest(attrs = WindowAttributes(resizable = true))
        val events = mutableListOf<WindowEvent>()
        window.onWindowEvent = events::add

        window.setResizable(true)
        assertEquals(emptyList<WindowEvent>(), events)

        window.setResizable(false)
        assertEquals(false, window.isResizable)
        assertEquals(listOf<WindowEvent>(WindowEvent.RedrawRequested), events)
    }

    @Test
    fun `surface resize increments are initialized from attrs and mutable`() {
        val window = WaylandWindow.createForTest(
            attrs = WindowAttributes(resizeIncrements = PhysicalSize(8, 16)),
        )
        assertEquals(PhysicalSize(8, 16), window.surfaceResizeIncrements)

        window.setSurfaceResizeIncrements(PhysicalSize(4, 6))
        assertEquals(PhysicalSize(4, 6), window.surfaceResizeIncrements)

        window.setSurfaceResizeIncrements(null)
        assertEquals(null, window.surfaceResizeIncrements)
    }

    @Test
    fun `transparent hint is initialized from attrs and mutable`() {
        val window = WaylandWindow.createForTest(
            attrs = WindowAttributes(transparent = true),
        )
        assertEquals(true, window.transparentHint)

        window.setTransparent(false)
        assertEquals(false, window.transparentHint)

        window.setTransparent(true)
        assertEquals(true, window.transparentHint)
    }

    @Test
    fun `Wayland theme is initialized from attrs and mutable like winit`() {
        val window = WaylandWindow.createForTest(
            attrs = WindowAttributes(preferredTheme = Theme.Dark),
        )
        assertEquals(Theme.Dark, window.theme)

        window.setTheme(Theme.Light)
        assertEquals(Theme.Light, window.theme)

        window.setTheme(null)
        assertEquals(null, window.theme)
    }

    @Test
    fun `Wayland content protection is a success no-op like winit`() {
        assertEquals(WindowRequestResult.Success, waylandContentProtectionResult(true))
        assertEquals(WindowRequestResult.Success, waylandContentProtectionResult(false))
    }

    @Test
    fun `Wayland enabled buttons are all after set like winit`() {
        assertEquals(WindowButtons.ALL, waylandEnabledButtons())
        assertEquals(WindowButtons.ALL, waylandEnabledButtonsAfterSet(WindowButtons.NONE))
        assertEquals(WindowButtons.ALL, waylandEnabledButtonsAfterSet(WindowButtons.CLOSE))
        assertEquals(WindowButtons.ALL, waylandEnabledButtonsAfterSet(WindowButtons.ALL))
    }

    @Test
    fun `Wayland window level is a safe no-op like winit`() {
        val window = WaylandWindow.createForTest(surface = 4_242L)

        window.setWindowLevel(WindowLevel.Normal)
        window.setWindowLevel(WindowLevel.AlwaysOnTop)
        window.setWindowLevel(WindowLevel.AlwaysOnBottom)
    }

    @Test
    fun `Wayland opaque region uses full positive extent like winit`() {
        assertEquals(Int.MAX_VALUE, WAYLAND_OPAQUE_REGION_EXTENT)
    }

    @Test
    fun `Wayland cursor hittest disabled uses empty input region like winit`() {
        assertEquals(WaylandRegionRect(x = 0, y = 0, width = 0, height = 0), waylandEmptyInputRegionRect())
    }

    @Test
    fun `Wayland cursor hittest reports unsupported without a surface`() {
        val window = WaylandWindow.createForTest(surface = 0L)

        val result = window.setCursorHittest(false)

        assertIs<WindowRequestResult.Failure>(result)
        assertIs<RequestError.Unsupported>(result.error)
    }

    @Test
    fun `Wayland cursor grab none is a success no-op like winit`() {
        val window = WaylandWindow.createForTest()

        assertEquals(WindowRequestResult.Success, window.setCursorGrab(CursorGrabMode.None))

        val confined = window.setCursorGrab(CursorGrabMode.Confined)
        assertIs<WindowRequestResult.Failure>(confined)
        assertIs<RequestError.Unsupported>(confined.error)
    }

    @Test
    fun `Wayland cursor visibility state is mutable and hide is best effort`() {
        val surface = 4_242L
        val window = WaylandWindow.createForTest(surface = surface)

        window.setCursorVisible(false)
        assertEquals(false, window.cursorVisible)
        assertEquals(false, WaylandPointerState.isCursorVisible(surface))

        window.setCursorVisible(true)
        assertEquals(true, window.cursorVisible)
        assertEquals(true, WaylandPointerState.isCursorVisible(surface))

        WaylandPointerState.leaveSurface(surface)
    }

    @Test
    fun `Wayland pointer state exposes cursor request context from enter serial`() {
        val surface = 6_363L
        WaylandPointerState.enterPointer(ptr = 7_474L, surfacePtr = surface, serial = 11)

        val context = WaylandPointerState.currentCursor(surface)

        assertNotNull(context)
        assertEquals(7_474L, context.pointerPtr)
        assertEquals(11, context.enterSerial)

        WaylandPointerState.leaveSurface(surface)
        assertEquals(null, WaylandPointerState.currentCursor(surface))
    }

    @Test
    fun `Wayland decoration mode follows winit decorated flag`() {
        assertEquals(XDG_TOPLEVEL_DECORATION_MODE_SERVER_SIDE, waylandDecorationMode(decorated = true))
        assertEquals(XDG_TOPLEVEL_DECORATION_MODE_CLIENT_SIDE, waylandDecorationMode(decorated = false))
    }

    @Test
    fun `Wayland decorations state is initialized from attrs and mutable`() {
        val window = WaylandWindow.createForTest(
            attrs = WindowAttributes(decorations = false),
        )
        assertEquals(false, window.isDecorated)

        window.setDecorations(true)
        assertEquals(true, window.isDecorated)

        window.setDecorations(false)
        assertEquals(false, window.isDecorated)
    }

    @Test
    fun `onConfigure applies resize increments from minimum size base`() {
        val attrs = WindowAttributes(
            minSize = PhysicalSize(100, 50),
            resizeIncrements = PhysicalSize(30, 20),
        )
        val window = WaylandWindow.createForTest(attrs = attrs)

        window.onConfigure(176, 99)

        assertEquals(PhysicalSize(160, 90), window.innerSize)
    }

    @Test
    fun `onConfigure can skip resize increments for compositor enforced sizes`() {
        val attrs = WindowAttributes(
            minSize = PhysicalSize(100, 50),
            resizeIncrements = PhysicalSize(30, 20),
        )
        val window = WaylandWindow.createForTest(attrs = attrs)

        window.onConfigure(176, 99, applyResizeIncrements = false)

        assertEquals(PhysicalSize(176, 99), window.innerSize)
    }

    @Test
    fun `Wayland toplevel configure states update maximized and fullscreen like winit`() {
        val window = WaylandWindow.createForTest()

        window.onToplevelStateConfigured(
            WaylandToplevelConfigureStates(maximized = true, fullscreen = true),
        )

        assertEquals(true, window.isMaximized)
        assertIs<Fullscreen.Borderless>(window.fullscreen)

        window.onToplevelStateConfigured(WaylandToplevelConfigureStates())

        assertEquals(false, window.isMaximized)
        assertEquals(null, window.fullscreen)
    }

    @Test
    fun `Wayland hasFocus follows keyboard seat focus like winit`() {
        val surface = 8_484L
        val window = WaylandWindow.createForTest(surface = surface)
        WaylandFocusState.clear(surface)

        assertEquals(false, window.hasFocus)
        assertEquals(true, WaylandFocusState.addSeatFocus(surface, seatPtr = 1L))
        assertEquals(true, window.hasFocus)
        assertEquals(false, WaylandFocusState.addSeatFocus(surface, seatPtr = 2L))
        assertEquals(true, window.hasFocus)
        assertEquals(false, WaylandFocusState.removeSeatFocus(surface, seatPtr = 1L))
        assertEquals(true, window.hasFocus)
        assertEquals(true, WaylandFocusState.removeSeatFocus(surface, seatPtr = 2L))
        assertEquals(false, window.hasFocus)
    }

    @Test
    fun `Wayland focus removal ignores unknown surfaces`() {
        val surface = 9_595L
        WaylandFocusState.clear(surface)

        assertEquals(false, WaylandFocusState.removeSeatFocus(surface, seatPtr = 1L))
        assertEquals(false, WaylandFocusState.hasFocus(surface))
    }

    @Test
    fun `Wayland fullscreen and maximized setters wait for compositor configure like winit`() {
        val window = WaylandWindow.createForTest()

        window.setMaximized(true)
        window.setFullscreen(Fullscreen.Borderless())

        assertEquals(false, window.isMaximized)
        assertEquals(null, window.fullscreen)
    }

    @Test
    fun `Wayland resize increments apply only for resizing unconstrained states`() {
        assertEquals(
            true,
            waylandShouldApplyResizeIncrements(
                isResizing = true,
                isMaximized = false,
                isFullscreen = false,
                isTiled = false,
            ),
        )
        assertEquals(
            false,
            waylandShouldApplyResizeIncrements(
                isResizing = true,
                isMaximized = true,
                isFullscreen = false,
                isTiled = false,
            ),
        )
        assertEquals(
            false,
            waylandShouldApplyResizeIncrements(
                isResizing = false,
                isMaximized = false,
                isFullscreen = false,
                isTiled = false,
            ),
        )
    }

    @Test
    fun `wayland resize increments ignore invalid increments`() {
        val size = PhysicalSize(176, 99)
        assertEquals(size, waylandApplyResizeIncrements(size, PhysicalSize(100, 50), PhysicalSize(0, 20)))
        assertEquals(size, waylandApplyResizeIncrements(size, PhysicalSize(100, 50), PhysicalSize(30, -1)))
        assertEquals(size, waylandApplyResizeIncrements(size, PhysicalSize(100, 50), null))
    }

    @Test
    fun `onConfigure ignores zero dimensions`() {
        val attrs = WindowAttributes(size = PhysicalSize(800, 600))
        val window = WaylandWindow.createForTest(attrs = attrs)
        window.onConfigure(0, 0)
        assertEquals(PhysicalSize(800, 600), window.innerSize)
    }

    @Test
    fun `id is based on surface pointer`() {
        val window = WaylandWindow.createForTest(surface = 7777L)
        assertEquals(7777L, window.id.value)
    }

    @Test
    fun `close does not crash with null surface`() {
        // surface = 0 → close() must return without an FFM call
        val window = WaylandWindow.createForTest(surface = 0L)
        window.close() // Must not throw an exception
    }

    @Test
    fun `requestRedraw does not crash with null surface`() {
        val window = WaylandWindow.createForTest(surface = 0L)
        window.requestRedraw() // Must not throw an exception
    }

    @Test
    fun `setTitle does not crash`() {
        val window = WaylandWindow.createForTest()
        window.setTitle("Test Window") // Stub — must not throw an exception
    }

    @Test
    fun `setVisible is ignored on Wayland without native calls like winit`() {
        val window = WaylandWindow.createForTest(surface = 4_242L)
        window.setVisible(true)
        window.setVisible(false)
    }

    @Test
    fun `WaylandWindow create returns null when libwayland is not available`() {
        // On macOS/Windows, wlCompositorCreateSurface is null
        // → create() returns null gracefully
        if (libWaylandClient != null) return // Skip on Wayland Linux

        val result = WaylandWindow.create(
            display = 0L,
            compositor = 0L,
            xdgWmBase = 0L,
            attrs = WindowAttributes(),
        )
        // On non-Wayland, the binding is null and create() returns null
        assertEquals(null, result)
    }
}
