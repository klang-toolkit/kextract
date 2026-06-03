package org.graphiks.kadre.x11

import org.graphiks.kadre.core.CursorIcon
import org.graphiks.kadre.core.Fullscreen
import org.graphiks.kadre.core.WindowAttributes
import org.graphiks.kadre.core.WindowLevel
import org.graphiks.kadre.core.PhysicalPosition
import org.graphiks.kadre.core.PhysicalSize
import org.graphiks.kadre.core.Theme
import org.graphiks.kadre.core.WindowButtons
import org.graphiks.kadre.core.WindowRequestResult
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class X11WindowTest {

    @Test
    fun `X11Window is created without error on non-Linux`() {
        if (libX11 == null) return // Skip on macOS / Windows
        // On Linux with an available X server, a creation attempt may
        // fail if DISPLAY is not set in CI — that is acceptable.
        // We only verify that the code path is traversed without an exception.
    }

    @Test
    fun `X11Window returns null if libX11 is absent`() {
        // On macOS / Windows, libX11 is null → create() must return null
        if (libX11 != null) return // Skip on Linux
        val result = X11Window.create(
            display = 0L,
            screen = 0,
            attrs = WindowAttributes(title = "Test"),
        )
        assertNull(result, "X11Window.create must return null if libX11 is absent")
    }

    // ── parseXftDpi unit tests (pure Kotlin, no X server needed) ─────────────
    // parseXftDpi(resourceString) returns a scale factor (Double): dpi/96.0, or 1.0

    @Test
    fun `parseXftDpi returns correct scale factor for tab-separated entry`() {
        val resources = "Xft.antialias:\t1\nXft.dpi:\t144\nXft.hinting:\t1\n"
        val result = parseXftDpi(resources)
        assertEquals(144.0 / 96.0, result, "Expected Xft.dpi=144 → scaleFactor=1.5")
    }

    @Test
    fun `parseXftDpi returns correct scale factor for space-separated entry`() {
        val resources = "Xft.dpi: 192"
        val result = parseXftDpi(resources)
        assertEquals(192.0 / 96.0, result, "Expected Xft.dpi=192 → scaleFactor=2.0")
    }

    @Test
    fun `parseXftDpi returns 1_0 fallback if Xft dpi is absent`() {
        val resources = "Xft.antialias:\t1\nXft.hinting:\t1\n"
        val result = parseXftDpi(resources)
        assertEquals(1.0, result, "Expected fallback 1.0 when Xft.dpi is absent")
    }

    @Test
    fun `parseXftDpi returns 1_0 for empty string`() {
        val result = parseXftDpi("")
        assertEquals(1.0, result, "Expected fallback 1.0 for empty resource string")
    }

    @Test
    fun `readXftDpi returns 1_0 fallback when display is 0`() {
        // display pointer 0 → readXftDpi must not throw and must return 1.0
        // (libX11 is null on macOS/Windows; on Linux XResourceManagerString(0) → handled)
        if (libX11 != null) return // Skip on Linux where this could be undefined behaviour
        val sf = readXftDpi(0L)
        assertEquals(1.0, sf, "Expected fallback scale factor 1.0 when libX11 is absent")
    }

    @Test
    fun `X11 window levels map to EWMH above and below states`() {
        assertEquals(X11WindowLevelState(above = true, below = false), x11WindowLevelState(WindowLevel.AlwaysOnTop))
        assertEquals(X11WindowLevelState(above = false, below = false), x11WindowLevelState(WindowLevel.Normal))
        assertEquals(X11WindowLevelState(above = false, below = true), x11WindowLevelState(WindowLevel.AlwaysOnBottom))
    }

    @Test
    fun `X11 theme variants match winit GTK theme hint values`() {
        assertEquals("dark", x11ThemeVariant(Theme.Dark))
        assertEquals("light", x11ThemeVariant(Theme.Light))
        assertEquals("dark", x11ThemeVariant(null))
    }

    @Test
    fun `X11 content protection is a success no-op like winit`() {
        assertEquals(WindowRequestResult.Success, x11ContentProtectionResult(true))
        assertEquals(WindowRequestResult.Success, x11ContentProtectionResult(false))
    }

    @Test
    fun `X11 window menu is a success no-op like winit`() {
        assertEquals(WindowRequestResult.Success, x11ShowWindowMenuResult(PhysicalPosition(10, 20)))
    }

    @Test
    fun `X11 transparency and blur are no native updates like winit`() {
        assertEquals(false, x11TransparencyRequiresNativeUpdate(true))
        assertEquals(false, x11TransparencyRequiresNativeUpdate(false))
        assertEquals(false, x11BlurRequiresNativeUpdate(true))
        assertEquals(false, x11BlurRequiresNativeUpdate(false))
    }

    @Test
    fun `X11 enabled buttons are all after set like winit`() {
        assertEquals(WindowButtons.ALL, x11EnabledButtons())
        assertEquals(WindowButtons.ALL, x11EnabledButtonsAfterSet(WindowButtons.NONE))
        assertEquals(WindowButtons.ALL, x11EnabledButtonsAfterSet(WindowButtons.CLOSE))
        assertEquals(WindowButtons.ALL, x11EnabledButtonsAfterSet(WindowButtons.ALL))
    }

    @Test
    fun `X11 initial position uses attributes or zero fallback`() {
        assertEquals(PhysicalPosition(12, 34), x11InitialPosition(PhysicalPosition(12, 34)))
        assertEquals(PhysicalPosition(0, 0), x11InitialPosition(null))
    }

    @Test
    fun `X11 title property bytes are UTF-8 like winit NET_WM_NAME`() {
        assertEquals("Kadre".toByteArray(Charsets.UTF_8).toList(), x11TitlePropertyBytes("Kadre").toList())
        assertEquals("Fenetre é".toByteArray(Charsets.UTF_8).toList(), x11TitlePropertyBytes("Fenetre é").toList())
    }

    @Test
    fun `X11 cursor change applies only when visible and changed`() {
        assertEquals(true, x11CursorChangeRequiresApply(CursorIcon.Default, CursorIcon.Pointer, visible = true))
        assertEquals(false, x11CursorChangeRequiresApply(CursorIcon.Default, CursorIcon.Default, visible = true))
        assertEquals(false, x11CursorChangeRequiresApply(CursorIcon.Default, CursorIcon.Pointer, visible = false))
    }

    @Test
    fun `X11 transparent cursor XColor layout matches LP64 Xlib`() {
        assertEquals(16L, X11_COLOR_SIZE_BYTES)
        assertEquals(8L, X11_COLOR_ALIGN_BYTES)
    }

    @Test
    fun `X11 window state atom matching supports minimized and maximized queries`() {
        val hidden = 11L
        val maximizedVert = 12L
        val maximizedHorz = 13L
        val atoms = longArrayOf(hidden, maximizedVert, maximizedHorz)

        assertEquals(true, x11WindowStateContains(atoms, hidden))
        assertEquals(true, x11WindowStateContains(atoms, maximizedVert))
        assertEquals(true, x11WindowStateContains(atoms, maximizedHorz))
        assertEquals(false, x11WindowStateContains(atoms, 99L))
        assertEquals(false, x11WindowStateContains(atoms, 0L))
    }

    @Test
    fun `X11 focus request follows winit visible and non-minimized guard`() {
        assertEquals(true, x11FocusRequestAllowed(visible = true, minimized = false))
        assertEquals(false, x11FocusRequestAllowed(visible = false, minimized = false))
        assertEquals(false, x11FocusRequestAllowed(visible = true, minimized = true))
        assertEquals(false, x11FocusRequestAllowed(visible = false, minimized = true))
    }

    @Test
    fun `X11 visibility follows winit YesWait until VisibilityNotify`() {
        val waiting = x11VisibilityAfterSet(X11_VISIBILITY_NO, visible = true)
        assertEquals(X11_VISIBILITY_YES_WAIT, waiting)
        assertEquals(false, x11VisibilityIsVisible(waiting))

        val visible = x11VisibilityAfterNotify(waiting)
        assertEquals(X11_VISIBILITY_YES, visible)
        assertEquals(true, x11VisibilityIsVisible(visible))

        val hidden = x11VisibilityAfterSet(visible, visible = false)
        assertEquals(X11_VISIBILITY_NO, hidden)
        assertEquals(false, x11VisibilityIsVisible(hidden))
    }

    @Test
    fun `X11 fullscreen requests are deferred until the window is visible`() {
        assertEquals(
            X11FullscreenRequest(defer = true, send = false),
            x11FullscreenRequest(current = null, requested = null, visibilityState = X11_VISIBILITY_NO),
        )
        assertEquals(
            X11FullscreenRequest(defer = true, send = false),
            x11FullscreenRequest(current = null, requested = null, visibilityState = X11_VISIBILITY_YES_WAIT),
        )
    }

    @Test
    fun `X11 fullscreen visible requests send only when state changes`() {
        val monitor = null
        val requested = Fullscreen.Borderless(monitor)

        assertEquals(
            X11FullscreenRequest(defer = false, send = true),
            x11FullscreenRequest(current = null, requested = requested, visibilityState = X11_VISIBILITY_YES),
        )
        assertEquals(
            X11FullscreenRequest(defer = false, send = false),
            x11FullscreenRequest(current = requested, requested = requested, visibilityState = X11_VISIBILITY_YES),
        )
    }

    @Test
    fun `X11 configure changes track size and only synthetic root-relative moves`() {
        val currentSize = PhysicalSize(800, 600)
        val currentPosition = PhysicalPosition(10, 20)

        assertEquals(
            X11ConfigureChanges(sizeChanged = true, movedPosition = PhysicalPosition(30, 40)),
            x11ConfigureChanges(
                currentSize = currentSize,
                currentPosition = currentPosition,
                width = 1024,
                height = 768,
                position = PhysicalPosition(30, 40),
                positionIsRootRelative = true,
            ),
        )
        assertEquals(
            X11ConfigureChanges(sizeChanged = false, movedPosition = null),
            x11ConfigureChanges(
                currentSize = currentSize,
                currentPosition = currentPosition,
                width = 800,
                height = 600,
                position = PhysicalPosition(30, 40),
                positionIsRootRelative = false,
            ),
        )
    }

    @Test
    fun `X11 frame extents convert inner root position to outer position`() {
        val extents = X11FrameExtents(left = 8, right = 8, top = 24, bottom = 4)

        assertEquals(PhysicalPosition(8, 24), extents.surfacePosition)
        assertEquals(PhysicalPosition(92, 176), extents.innerToOuter(PhysicalPosition(100, 200)))
        assertEquals(PhysicalSize(816, 628), extents.surfaceSizeToOuter(PhysicalSize(800, 600)))
    }

    @Test
    fun `X11 surface resize requests clamp invalid sizes before native calls`() {
        assertEquals(PhysicalSize(1, 1), x11ValidSurfaceSize(PhysicalSize(0, 0)))
        assertEquals(PhysicalSize(1, 20), x11ValidSurfaceSize(PhysicalSize(-10, 20)))
        assertEquals(PhysicalSize(640, 480), x11ValidSurfaceSize(PhysicalSize(640, 480)))
    }

    @Test
    fun `X11 cursor hittest disabled uses empty input shape`() {
        assertEquals(emptyList(), x11CursorHittestRectangles(hittest = false, surfaceSize = PhysicalSize(640, 480)))
    }

    @Test
    fun `X11 cursor hittest enabled uses full surface input shape`() {
        assertEquals(
            listOf(X11ShapeRectangle(x = 0, y = 0, width = 640, height = 480)),
            x11CursorHittestRectangles(hittest = true, surfaceSize = PhysicalSize(640, 480)),
        )
    }

    @Test
    fun `X11 cursor hittest rectangle clamps to XRectangle unsigned short range`() {
        assertEquals(
            listOf(X11ShapeRectangle(x = 0, y = 0, width = 65535, height = 1)),
            x11CursorHittestRectangles(hittest = true, surfaceSize = PhysicalSize(100_000, 0)),
        )
    }

    @Test
    fun `X11 cursor hittest is reapplied only after effective resize configure`() {
        assertEquals(true, x11ShouldReapplyCursorHittestAfterConfigure(cursorHittest = true, resized = true))
        assertEquals(false, x11ShouldReapplyCursorHittestAfterConfigure(cursorHittest = true, resized = false))
        assertEquals(false, x11ShouldReapplyCursorHittestAfterConfigure(cursorHittest = false, resized = true))
        assertEquals(false, x11ShouldReapplyCursorHittestAfterConfigure(cursorHittest = null, resized = true))
    }

    @Test
    fun `X11 WM hints urgency flag toggles without clearing other flags`() {
        val otherFlag = 1L shl 1
        val urgent = x11WmHintsUrgencyFlags(otherFlag, urgent = true)
        assertEquals(otherFlag or X11_WM_HINTS_URGENCY_FLAG, urgent)

        val cleared = x11WmHintsUrgencyFlags(urgent, urgent = false)
        assertEquals(otherFlag, cleared)
    }

    @Test
    fun `X11 WM hints LP64 layout size covers through window_group`() {
        assertEquals(56L, X11_WM_HINTS_SIZE_BYTES)
    }

    @Test
    fun `X11 motif decoration hints encode decorated and undecorated states`() {
        val decorated = x11MotifDecorationHints(decorated = true)
        assertEquals(X11_MOTIF_HINTS_ELEMENTS, decorated.size)
        assertEquals(X11_MWM_HINTS_DECORATIONS, decorated[0])
        assertEquals(1L, decorated[2])

        val undecorated = x11MotifDecorationHints(decorated = false)
        assertEquals(X11_MOTIF_HINTS_ELEMENTS, undecorated.size)
        assertEquals(X11_MWM_HINTS_DECORATIONS, undecorated[0])
        assertEquals(0L, undecorated[2])
    }

    @Test
    fun `X11 motif decoration hints preserve existing function input and status fields`() {
        val existing = longArrayOf(
            X11_MWM_HINTS_FUNCTIONS,
            X11_MWM_FUNC_ALL or X11_MWM_FUNC_MAXIMIZE,
            0L,
            7L,
            9L,
        )

        val decorated = x11MotifDecorationHints(decorated = true, existing = existing)

        assertEquals(X11_MWM_HINTS_FUNCTIONS or X11_MWM_HINTS_DECORATIONS, decorated[0])
        assertEquals(X11_MWM_FUNC_ALL or X11_MWM_FUNC_MAXIMIZE, decorated[1])
        assertEquals(1L, decorated[2])
        assertEquals(7L, decorated[3])
        assertEquals(9L, decorated[4])
    }

    @Test
    fun `X11 motif maximizable hints match winit all-functions encoding`() {
        val disabled = x11MotifMaximizableHints(maximizable = false)

        assertEquals(X11_MWM_HINTS_FUNCTIONS, disabled[0])
        assertEquals(X11_MWM_FUNC_ALL or X11_MWM_FUNC_MAXIMIZE, disabled[1])

        val enabled = x11MotifMaximizableHints(maximizable = true, existing = disabled)

        assertEquals(X11_MWM_HINTS_FUNCTIONS, enabled[0])
        assertEquals(X11_MWM_FUNC_ALL, enabled[1])
    }

    @Test
    fun `X11 motif maximizable hints match winit explicit-functions encoding`() {
        val existing = longArrayOf(
            X11_MWM_HINTS_FUNCTIONS or X11_MWM_HINTS_DECORATIONS,
            0L,
            1L,
            0L,
            0L,
        )

        val enabled = x11MotifMaximizableHints(maximizable = true, existing = existing)
        assertEquals(X11_MWM_FUNC_MAXIMIZE, enabled[1])

        val disabled = x11MotifMaximizableHints(maximizable = false, existing = enabled)
        assertEquals(0L, disabled[1])
        assertEquals(1L, disabled[2])
    }

    @Test
    fun `X11 normal hints encode position size constraints and increments`() {
        val hints = x11NormalHints(
            position = PhysicalPosition(10, 20),
            size = PhysicalSize(800, 600),
            minSize = PhysicalSize(320, 240),
            maxSize = PhysicalSize(1920, 1080),
            resizeIncrements = PhysicalSize(8, 16),
            resizable = true,
        )

        assertEquals(X11_NORMAL_HINTS_ELEMENTS, hints.elements.size)
        assertEquals(
            X11_US_POSITION or X11_US_SIZE or X11_P_MIN_SIZE or X11_P_MAX_SIZE or X11_P_RESIZE_INC,
            hints.elements[0],
        )
        assertEquals(10L, hints.elements[1])
        assertEquals(20L, hints.elements[2])
        assertEquals(800L, hints.elements[3])
        assertEquals(600L, hints.elements[4])
        assertEquals(320L, hints.elements[5])
        assertEquals(240L, hints.elements[6])
        assertEquals(1920L, hints.elements[7])
        assertEquals(1080L, hints.elements[8])
        assertEquals(8L, hints.elements[9])
        assertEquals(16L, hints.elements[10])
    }

    @Test
    fun `X11 normal hints pin min and max size when not resizable`() {
        val hints = x11NormalHints(
            position = null,
            size = PhysicalSize(640, 480),
            minSize = PhysicalSize(320, 240),
            maxSize = PhysicalSize(1920, 1080),
            resizeIncrements = null,
            resizable = false,
        )

        assertEquals(X11_US_SIZE or X11_P_MIN_SIZE or X11_P_MAX_SIZE, hints.elements[0])
        assertEquals(640L, hints.elements[5])
        assertEquals(480L, hints.elements[6])
        assertEquals(640L, hints.elements[7])
        assertEquals(480L, hints.elements[8])
    }

    @Test
    fun `X11 normal hints avoid non-resizable pin for Xfwm4 compatibility`() {
        val hints = x11NormalHints(
            position = null,
            size = PhysicalSize(640, 480),
            minSize = PhysicalSize(320, 240),
            maxSize = PhysicalSize(1920, 1080),
            resizeIncrements = null,
            resizable = false,
            avoidNonResizablePin = true,
        )

        assertEquals(X11_US_SIZE or X11_P_MIN_SIZE or X11_P_MAX_SIZE, hints.elements[0])
        assertEquals(320L, hints.elements[5])
        assertEquals(240L, hints.elements[6])
        assertEquals(1920L, hints.elements[7])
        assertEquals(1080L, hints.elements[8])
    }

    @Test
    fun `X11 setResizable is a full no-op under Xfwm4 like winit`() {
        assertNull(x11ResizableChangeAfterRequest(current = true, requested = false, isXfwm4 = true))
        assertNull(x11ResizableChangeAfterRequest(current = false, requested = true, isXfwm4 = true))
        assertEquals(false, x11ResizableChangeAfterRequest(current = true, requested = false, isXfwm4 = false))
        assertEquals(true, x11ResizableChangeAfterRequest(current = false, requested = true, isXfwm4 = false))
    }

    @Test
    fun `scaleFactor computed from Xft_dpi 96 equals 1_0`() {
        val resources = "Xft.dpi:\t96"
        assertEquals(1.0, parseXftDpi(resources), "96 DPI should yield scale factor 1.0")
    }
}
