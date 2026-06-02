package org.graphiks.kadre.core

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
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
                kadreApi = "Window.isVisible: Boolean",
                status = WinitWindowingStatus.Deferred,
                note = "winit returns Option<bool>; Kadre currently exposes a non-null Boolean and cannot express unknown platform visibility.",
            ),
            WinitWindowingApi(
                winitApi = "Window.is_minimized",
                kadreApi = "Window.isMinimized: Boolean",
                status = WinitWindowingStatus.Deferred,
                note = "winit returns Option<bool>; Kadre currently exposes a non-null Boolean and cannot express unknown platform minimized state.",
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
                note = "Kadre exposes these methods, but several are still default no-ops or backend-incomplete; AppKit content protection is implemented.",
            ),
            WinitWindowingApi(
                winitApi = "Window reset_dead_keys",
                kadreApi = "Window.resetDeadKeys",
                status = WinitWindowingStatus.Implemented,
                note = "Kadre has the Window-level API and backend best-effort implementations/no-ops documented by platform.",
            ),
            WinitWindowingApi(
                winitApi = "Window.available_monitors",
                kadreApi = "missing on Window; ActiveEventLoop.availableMonitors exists",
                status = WinitWindowingStatus.Deferred,
                note = "winit exposes monitor enumeration on both ActiveEventLoop and Window; Kadre only exposes it through ActiveEventLoop today.",
            ),
            WinitWindowingApi(
                winitApi = "Window.primary_monitor",
                kadreApi = "missing on Window; ActiveEventLoop.primaryMonitor exists",
                status = WinitWindowingStatus.Deferred,
                note = "winit exposes primary monitor lookup on Window as a convenience; Kadre lacks the Window-level method.",
            ),
            WinitWindowingApi(
                winitApi = "Window cursor setters and grab/position requests",
                kadreApi = "Window.setCursor, setCursorVisible, setCursorGrab, setCursorPosition, setCursorHittest",
                status = WinitWindowingStatus.Deferred,
                note = "Cursor grab, cursor position and hittest are fallible in winit; Kadre methods return Unit and several backends document no-op behavior.",
            ),
            WinitWindowingApi(
                winitApi = "Window drag_window",
                kadreApi = "Window.dragWindow",
                status = WinitWindowingStatus.Deferred,
                note = "winit returns Result for platform failure; Kadre currently defaults to a no-op Unit method.",
            ),
            WinitWindowingApi(
                winitApi = "Window drag_resize_window",
                kadreApi = "Window.dragResizeWindow",
                status = WinitWindowingStatus.Deferred,
                note = "winit returns Result for platform failure; Kadre currently defaults to a no-op Unit method.",
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
                note = "Kadre has support result types for fallible requests, though not all Window methods use them yet.",
            ),
        )
    }
}
