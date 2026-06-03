package org.graphiks.kadre.core

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue

class WinitWindowingCompatibilityTest {
    private enum class WinitWindowingStatus {
        Implemented,
        UnsupportedPlatform,
        Deferred,
    }

    private data class WinitWindowingApi(
        val winitApi: String,
        val kadreApi: String,
        val status: WinitWindowingStatus?,
        val note: String,
    )

    @Test
    fun `winit windowing target commit and scope are frozen`() {
        assertEquals("c4afadbfabf7b1e7989b40b493db1a4c7bd8ff4e", targetWinitCommit)
        assertEquals(
            setOf(
                "WindowAttributes",
                "Window",
                "ActiveEventLoop",
                "monitor/fullscreen/window support types",
            ),
            targetScope,
        )
        assertEquals(
            setOf("keyboard", "pure pointer", "rich IME except required Window methods"),
            excludedScope,
        )
    }

    @Test
    fun `every targeted winit windowing api has an explicit compatibility status`() {
        val matrixNames = compatibilityMatrix.map { it.winitApi }.toSet()
        assertEquals(targetedWinitApis, matrixNames)

        val allowedStatuses = WinitWindowingStatus.entries.toSet()
        compatibilityMatrix.forEach { row ->
            val status = assertNotNull(row.status, "Missing explicit status for ${row.winitApi}")
            assertTrue(status in allowedStatuses, "Unexpected status $status for ${row.winitApi}")
            assertTrue(row.kadreApi.isNotBlank(), "Missing Kadre API mapping for ${row.winitApi}")
            assertTrue(row.note.isNotBlank(), "Missing note for ${row.winitApi}")
        }
    }

    @Test
    fun `window monitor convenience methods are unknown by default`() {
        val monitor = TestMonitorHandle()
        val window = TestWindow(currentMonitor = monitor)

        assertEquals(emptyList(), window.availableMonitors())
        assertNull(window.primaryMonitor())
    }

    @Test
    fun `window monitor convenience methods can be implemented by backend registry`() {
        val primary = TestMonitorHandle(id = 1L, name = "primary")
        val secondary = TestMonitorHandle(id = 2L, name = "secondary")
        val window = TestMonitorRegistryWindow(
            delegate = TestWindow(currentMonitor = secondary),
            availableMonitors = listOf(primary, secondary),
            primaryMonitor = primary,
        )

        assertEquals(listOf(primary, secondary), window.availableMonitors())
        assertEquals(primary, window.primaryMonitor())
    }

    @Test
    fun `cursor requests report typed unsupported errors`() {
        val window = TestWindow(currentMonitor = null)

        assertUnsupported(window.setCursorGrab(CursorGrabMode.Locked))
        assertUnsupported(window.setCursorPosition(PhysicalPosition(10, 20)))
        assertUnsupported(window.setCursorHittest(false))
    }

    @Test
    fun `window management requests report typed unsupported errors`() {
        val window = TestWindow(currentMonitor = null)

        assertUnsupported(window.showWindowMenu(PhysicalPosition(10, 20)))
        assertUnsupported(window.dragWindow())
        assertUnsupported(window.dragResizeWindow(ResizeDirection.SouthEast))
        assertUnsupported(window.requestUserAttention(UserAttentionType.Informational))
        assertUnsupported(window.requestUserAttention(null))
        assertUnsupported(window.setContentProtected(true))
        assertUnsupported(window.setContentProtected(false))
    }

    @Test
    fun `window visibility and minimized state can represent unknown platform state`() {
        val window = TestWindow(
            currentMonitor = null,
            visible = null,
            minimized = null,
        )

        assertNull(window.isVisible)
        assertNull(window.isMinimized)
    }

    @Test
    fun `Wayland optional appearance protocols are explicitly tracked`() {
        val note = compatibilityMatrix.single {
            it.winitApi == "Window appearance/state setters"
        }.note

        assertTrue(note.contains("xdg_activation_v1"))
        assertTrue(note.contains("xdg_toplevel_icon_manager_v1"))
        assertTrue(note.contains("ext_background_effect"))
        assertTrue(note.contains("KWin blur"))
    }

    private companion object {
        const val targetWinitCommit = "c4afadbfabf7b1e7989b40b493db1a4c7bd8ff4e"

        val targetScope = setOf(
            "WindowAttributes",
            "Window",
            "ActiveEventLoop",
            "monitor/fullscreen/window support types",
        )

        val excludedScope = setOf(
            "keyboard",
            "pure pointer",
            "rich IME except required Window methods",
        )

        val targetedWinitApis = setOf(
            "WindowAttributes",
            "Window.id/raw handles/redraw/title/size/scale",
            "Window surface geometry and constraints",
            "Window.is_visible",
            "Window.is_minimized",
            "Window monitor/fullscreen methods",
            "Window focus methods",
            "Window appearance/state setters",
            "Window reset_dead_keys",
            "Window.available_monitors",
            "Window.primary_monitor",
            "Window cursor setters and grab/position requests",
            "Window drag_window",
            "Window drag_resize_window",
            "Window show_window_menu",
            "Window.request_ime_update",
            "Window.ime_capabilities",
            "ActiveEventLoop create/control/exit/proxy",
            "ActiveEventLoop.available_monitors",
            "ActiveEventLoop.primary_monitor",
            "ActiveEventLoop.owned_display_handle",
            "MonitorHandle and VideoMode",
            "Fullscreen",
            "WindowRequestResult and RequestError",
        )

        val compatibilityMatrix = listOf(
            WinitWindowingApi(
                winitApi = "WindowAttributes",
                kadreApi = "WindowAttributes",
                status = WinitWindowingStatus.Implemented,
                note = "Creation attributes cover the main portable window state: title, size, visibility, resizable, position, fullscreen, decorations, icon, theme and level.",
            ),
            WinitWindowingApi(
                winitApi = "Window.id/raw handles/redraw/title/size/scale",
                kadreApi = "Window.id, rawWindowHandle, rawDisplayHandle, requestRedraw, title, innerSize, outerSize, scaleFactor",
                status = WinitWindowingStatus.Implemented,
                note = "Core window identity, handles, redraw and geometry accessors are represented in Kadre.",
            ),
            WinitWindowingApi(
                winitApi = "Window surface geometry and constraints",
                kadreApi = "surfacePosition, outerPosition, setOuterPosition, surfaceSize, requestSurfaceSize, outerSize, safeArea, setMinSurfaceSize, setMaxSurfaceSize, surfaceResizeIncrements, setSurfaceResizeIncrements",
                status = WinitWindowingStatus.Implemented,
                note = "Kadre exposes the portable surface geometry and size constraint API; platform support still varies by backend.",
            ),
            WinitWindowingApi(
                winitApi = "Window.is_visible",
                kadreApi = "Window.isVisible: Boolean?",
                status = WinitWindowingStatus.Implemented,
                note = "Kadre mirrors winit Option<bool> with a nullable Boolean; null means the platform does not expose a reliable visibility state.",
            ),
            WinitWindowingApi(
                winitApi = "Window.is_minimized",
                kadreApi = "Window.isMinimized: Boolean?",
                status = WinitWindowingStatus.Implemented,
                note = "Kadre mirrors winit Option<bool> with a nullable Boolean; null means the platform does not expose a reliable minimized state.",
            ),
            WinitWindowingApi(
                winitApi = "Window monitor/fullscreen methods",
                kadreApi = "Window.currentMonitor, setFullscreen, fullscreen",
                status = WinitWindowingStatus.Implemented,
                note = "Kadre has per-window current monitor and fullscreen state controls for the portable subset.",
            ),
            WinitWindowingApi(
                winitApi = "Window focus methods",
                kadreApi = "Window.focusWindow, hasFocus",
                status = WinitWindowingStatus.Implemented,
                note = "The common API exists and AppKit now has a concrete implementation; other backends may still report best-effort focus state.",
            ),
            WinitWindowingApi(
                winitApi = "Window appearance/state setters",
                kadreApi = "setWindowLevel, requestUserAttention, setTheme, theme, setTransparent, setBlur, setWindowIcon, setContentProtected",
                status = WinitWindowingStatus.Deferred,
                note = "Kadre exposes these methods; requestUserAttention and setContentProtected now return typed WindowRequestResult failures on unsupported backends. setWindowIcon is implemented on Win32/X11 and intentionally no-op on AppKit like winit. Wayland still lacks winit's optional xdg_activation_v1 attention path, xdg_toplevel_icon_manager_v1 icon path, and ext_background_effect / KWin blur protocols. X11 setTheme writes _GTK_THEME_VARIANT like winit while theme remains null; other appearance setters still need the same fallible-result audit.",
            ),
            WinitWindowingApi(
                winitApi = "Window reset_dead_keys",
                kadreApi = "Window.resetDeadKeys",
                status = WinitWindowingStatus.Implemented,
                note = "Kadre has the Window-level API and backend best-effort implementations/no-ops documented by platform.",
            ),
            WinitWindowingApi(
                winitApi = "Window.available_monitors",
                kadreApi = "Window.availableMonitors",
                status = WinitWindowingStatus.Implemented,
                note = "Kadre exposes a Window-level method; default is empty when unknown and desktop/synthetic backends override it from their monitor registry.",
            ),
            WinitWindowingApi(
                winitApi = "Window.primary_monitor",
                kadreApi = "Window.primaryMonitor",
                status = WinitWindowingStatus.Implemented,
                note = "Kadre exposes a nullable Window-level primary monitor lookup; default is null when unknown and backends override it where the platform has a primary monitor concept.",
            ),
            WinitWindowingApi(
                winitApi = "Window cursor setters and grab/position requests",
                kadreApi = "Window.setCursor, setCursorVisible, setCursorGrab, setCursorPosition, setCursorHittest",
                status = WinitWindowingStatus.Implemented,
                note = "Cursor grab, cursor position and hittest return WindowRequestResult; unsupported/no-op backends report RequestError.Unsupported. setCursor and setCursorVisible remain no-throw Unit setters.",
            ),
            WinitWindowingApi(
                winitApi = "Window drag_window",
                kadreApi = "Window.dragWindow",
                status = WinitWindowingStatus.Deferred,
                note = "Kadre returns WindowRequestResult instead of Unit no-op; AppKit uses the current NSEvent and reports RequestError.Ignored when none is available, Win32 queues cross-thread requests onto the message thread, X11 sends _NET_WM_MOVERESIZE, and Wayland sends xdg_toplevel.move. Final native drag completion is fire-and-forget.",
            ),
            WinitWindowingApi(
                winitApi = "Window drag_resize_window",
                kadreApi = "Window.dragResizeWindow",
                status = WinitWindowingStatus.Deferred,
                note = "Kadre returns WindowRequestResult instead of Unit no-op; Win32 queues cross-thread requests onto the message thread, X11 sends _NET_WM_MOVERESIZE, and Wayland sends xdg_toplevel.resize. AppKit is unsupported like winit; final native resize completion is fire-and-forget.",
            ),
            WinitWindowingApi(
                winitApi = "Window show_window_menu",
                kadreApi = "Window.showWindowMenu",
                status = WinitWindowingStatus.Deferred,
                note = "Kadre returns WindowRequestResult instead of Unit no-op; Win32 system-menu support is wired, Wayland sends xdg_toplevel.show_window_menu, while AppKit and X11 remain unsupported/no-op like local winit.",
            ),
            WinitWindowingApi(
                winitApi = "Window.request_ime_update",
                kadreApi = "setImeAllowed, setImeCursorArea, setImePurpose",
                status = WinitWindowingStatus.Deferred,
                note = "Rich IME request batching is outside this windowing pass except for existing Window methods that influence IME state.",
            ),
            WinitWindowingApi(
                winitApi = "Window.ime_capabilities",
                kadreApi = "missing rich IME capabilities API",
                status = WinitWindowingStatus.Deferred,
                note = "Kadre has inputCapabilities for device features, but not winit's IME capability reporting model.",
            ),
            WinitWindowingApi(
                winitApi = "ActiveEventLoop create/control/exit/proxy",
                kadreApi = "ActiveEventLoop.createWindow, controlFlow, setControlFlow, exit, isExiting, createProxy",
                status = WinitWindowingStatus.Implemented,
                note = "Core active event-loop control is represented in Kadre.",
            ),
            WinitWindowingApi(
                winitApi = "ActiveEventLoop.available_monitors",
                kadreApi = "ActiveEventLoop.availableMonitors",
                status = WinitWindowingStatus.Implemented,
                note = "Kadre exposes monitor enumeration on the active event loop.",
            ),
            WinitWindowingApi(
                winitApi = "ActiveEventLoop.primary_monitor",
                kadreApi = "ActiveEventLoop.primaryMonitor",
                status = WinitWindowingStatus.Implemented,
                note = "Kadre mirrors winit's nullable primary monitor result.",
            ),
            WinitWindowingApi(
                winitApi = "ActiveEventLoop.owned_display_handle",
                kadreApi = "ActiveEventLoop.ownedDisplayHandle(): OwnedDisplayHandle?",
                status = WinitWindowingStatus.Deferred,
                note = "winit returns a non-null OwnedDisplayHandle; Kadre returns null by default.",
            ),
            WinitWindowingApi(
                winitApi = "MonitorHandle and VideoMode",
                kadreApi = "MonitorHandle, VideoMode",
                status = WinitWindowingStatus.Implemented,
                note = "Kadre models monitor identity, name, position, scale factor, current video mode and video modes.",
            ),
            WinitWindowingApi(
                winitApi = "Fullscreen",
                kadreApi = "Fullscreen.Borderless, Fullscreen.Exclusive",
                status = WinitWindowingStatus.UnsupportedPlatform,
                note = "The API exists, but Exclusive is explicitly unsupported or downgraded on Wayland, Web, Android and UIKit.",
            ),
            WinitWindowingApi(
                winitApi = "WindowRequestResult and RequestError",
                kadreApi = "WindowRequestResult, SurfaceSizeRequestResult, RequestError",
                status = WinitWindowingStatus.Implemented,
                note = "Kadre has support result types for fallible requests, including RequestError.Ignored for winit ignored requests; appearance setters are being migrated away from silent no-ops incrementally.",
            ),
        )
    }

    private class TestMonitorHandle(
        override val id: Long = 1L,
        override val name: String? = "test-monitor",
    ) : MonitorHandle {
        override val position: PhysicalPosition<Int> = PhysicalPosition(0, 0)
        override val scaleFactor: Double = 1.0
        override val currentVideoMode: VideoMode? = null
        override val videoModes: List<VideoMode> = emptyList()
    }

    private fun assertUnsupported(result: WindowRequestResult) {
        assertTrue(
            result is WindowRequestResult.Failure && result.error is RequestError.Unsupported,
            "Expected unsupported failure, got $result",
        )
    }

    private class TestMonitorRegistryWindow(
        private val delegate: TestWindow,
        private val availableMonitors: List<MonitorHandle>,
        private val primaryMonitor: MonitorHandle?,
    ) : Window by delegate {
        override fun availableMonitors(): List<MonitorHandle> = availableMonitors
        override fun primaryMonitor(): MonitorHandle? = primaryMonitor
    }

    private class TestWindow(
        private val currentMonitor: MonitorHandle?,
        private val visible: Boolean? = true,
        private val minimized: Boolean? = false,
    ) : Window {
        override val id: WindowId = WindowId(1L)
        override val rawWindowHandle: RawWindowHandle = RawWindowHandle.Web(canvasElementId = "test-window")
        override val rawDisplayHandle: RawDisplayHandle = RawDisplayHandle.Web
        override val title: String = "test"
        override val innerSize: PhysicalSize<Int> = PhysicalSize(800, 600)
        override val outerSize: PhysicalSize<Int> = innerSize
        override val scaleFactor: Double = 1.0
        override val isVisible: Boolean? = visible
        override val isResizable: Boolean = true
        override val isMinimized: Boolean? = minimized
        override val isMaximized: Boolean = false
        override val isDecorated: Boolean = true
        override val outerPosition: PhysicalPosition<Int> = PhysicalPosition(0, 0)
        override val fullscreen: Fullscreen? = null
        override val theme: Theme? = null

        override fun requestRedraw() {}
        override fun setTitle(title: String) {}
        override fun setVisible(visible: Boolean) {}
        override fun close() {}
        override fun setResizable(resizable: Boolean) {}
        override fun setMinimized(minimized: Boolean) {}
        override fun setMaximized(maximized: Boolean) {}
        override fun setDecorations(decorated: Boolean) {}
        override fun setMinSurfaceSize(size: PhysicalSize<Int>?) {}
        override fun setMaxSurfaceSize(size: PhysicalSize<Int>?) {}
        override fun setOuterPosition(position: PhysicalPosition<Int>) {}
        override fun prePresentNotify() {}
        override fun currentMonitor(): MonitorHandle? = currentMonitor
        override fun setFullscreen(fullscreen: Fullscreen?) {}
        override fun setCursor(cursor: CursorIcon) {}
        override fun setCursorVisible(visible: Boolean) {}
        override fun setCursorGrab(mode: CursorGrabMode): WindowRequestResult =
            WindowRequestResult.Failure(RequestError.Unsupported("Test window does not support cursor grab"))
        override fun setCursorPosition(position: PhysicalPosition<Int>): WindowRequestResult =
            WindowRequestResult.Failure(RequestError.Unsupported("Test window does not support cursor warping"))
        override fun setCursorHittest(hittest: Boolean): WindowRequestResult =
            WindowRequestResult.Failure(RequestError.Unsupported("Test window does not support cursor hit-testing"))
        override fun setTheme(theme: Theme?) {}
        override fun setWindowLevel(level: WindowLevel) {}
        override fun setTransparent(transparent: Boolean) {}
        override fun setBlur(blur: Boolean) {}
        override fun setWindowIcon(icon: Icon?) {}
        override fun resetDeadKeys() {}
    }
}
