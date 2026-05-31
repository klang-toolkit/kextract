/**
 * Unit tests for KadreWndProc.
 *
 * Verifies that the Win32 messages (WM_*) are correctly translated into kadre
 * [WindowEvent]s and forwarded to the installed handler.
 *
 * These tests run on all platforms (macOS, Linux, Windows) because
 * the dispatch is pure Kotlin logic. Only defWindowProcW returns 0 on
 * macOS/Linux (FFM MethodHandle null) — documented and expected behavior.
 *
 * Note: [KadreWndProc] is a singleton. Each test must install its own
 * handler and uninstall it at the end of the test to avoid interference.
 */
package org.graphiks.kadre.win32

import org.graphiks.kadre.core.Key
import org.graphiks.kadre.core.KeyState
import org.graphiks.kadre.core.MouseButton
import org.graphiks.kadre.core.PhysicalSize
import org.graphiks.kadre.core.WindowEvent
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs
import kotlin.test.assertNull

/** Fake HWND for the tests (arbitrary non-zero value). */
private const val TEST_HWND: Long = 0x1234_5678L

class KadreWndProcTest {

    /** Last event captured by the test handler. */
    private var capturedHwnd: Long? = null
    private var capturedEvent: WindowEvent? = null

    @BeforeTest
    fun setup() {
        capturedHwnd = null
        capturedEvent = null
        KadreWndProc.install { hwnd, event ->
            capturedHwnd = hwnd
            capturedEvent = event
        }
    }

    @AfterTest
    fun teardown() {
        KadreWndProc.uninstall()
    }

    // ── WM_PAINT ──────────────────────────────────────────────────────────────

    @Test
    fun `WM_PAINT emet RedrawRequested`() {
        KadreWndProc.dispatch(TEST_HWND, WM_PAINT, 0L, 0L)

        assertEquals(TEST_HWND, capturedHwnd)
        assertIs<WindowEvent.RedrawRequested>(capturedEvent)
    }

    @Test
    fun `WM_PAINT retourne 0`() {
        val result = KadreWndProc.dispatch(TEST_HWND, WM_PAINT, 0L, 0L)
        assertEquals(0L, result)
    }

    // ── WM_SIZE ───────────────────────────────────────────────────────────────

    @Test
    fun `WM_SIZE emet Resized avec dimensions correctes`() {
        // lParam = MAKELPARAM(800, 600) = 600 << 16 | 800
        val lParam = (600L shl 16) or 800L
        KadreWndProc.dispatch(TEST_HWND, WM_SIZE, 0L, lParam)

        assertIs<WindowEvent.Resized>(capturedEvent).also { event ->
            assertEquals(PhysicalSize(800, 600), event.size)
        }
    }

    @Test
    fun `WM_SIZE retourne 0`() {
        val result = KadreWndProc.dispatch(TEST_HWND, WM_SIZE, 0L, 0L)
        assertEquals(0L, result)
    }

    // ── WM_KEYDOWN ────────────────────────────────────────────────────────────

    @Test
    fun `WM_KEYDOWN emet KeyboardInput Pressed pour touche A`() {
        // wParam = VK_A, lParam = 0 (no repeat)
        KadreWndProc.dispatch(TEST_HWND, WM_KEYDOWN, VK_A.toLong(), 0L)

        assertIs<WindowEvent.KeyboardInput>(capturedEvent).also { event ->
            assertEquals(Key.A, event.key)
            assertEquals(KeyState.Pressed, event.state)
            assertEquals(false, event.isRepeat)
        }
    }

    @Test
    fun `WM_KEYDOWN detecte la repetition via bit 30 de lParam`() {
        // Bit 30 = 0x4000_0000 → key already down (repeat)
        val lParamRepeat = KF_REPEAT
        KadreWndProc.dispatch(TEST_HWND, WM_KEYDOWN, VK_SPACE.toLong(), lParamRepeat)

        assertIs<WindowEvent.KeyboardInput>(capturedEvent).also { event ->
            assertEquals(Key.Space, event.key)
            assertEquals(KeyState.Pressed, event.state)
            assertEquals(true, event.isRepeat)
        }
    }

    @Test
    fun `WM_KEYUP emet KeyboardInput Released`() {
        KadreWndProc.dispatch(TEST_HWND, WM_KEYUP, VK_ESCAPE.toLong(), 0L)

        assertIs<WindowEvent.KeyboardInput>(capturedEvent).also { event ->
            assertEquals(Key.Escape, event.key)
            assertEquals(KeyState.Released, event.state)
            assertEquals(false, event.isRepeat)
        }
    }

    @Test
    fun `WM_SYSKEYDOWN emet KeyboardInput Pressed pour touche F4`() {
        KadreWndProc.dispatch(TEST_HWND, WM_SYSKEYDOWN, VK_F4.toLong(), 0L)

        assertIs<WindowEvent.KeyboardInput>(capturedEvent).also { event ->
            assertEquals(Key.F4, event.key)
            assertEquals(KeyState.Pressed, event.state)
        }
    }

    @Test
    fun `WM_SYSKEYUP emet KeyboardInput Released`() {
        KadreWndProc.dispatch(TEST_HWND, WM_SYSKEYUP, VK_F4.toLong(), 0L)

        assertIs<WindowEvent.KeyboardInput>(capturedEvent).also { event ->
            assertEquals(Key.F4, event.key)
            assertEquals(KeyState.Released, event.state)
        }
    }

    // ── WM_MOUSEMOVE ──────────────────────────────────────────────────────────

    @Test
    fun `WM_MOUSEMOVE emet PointerMoved avec coordonnees correctes`() {
        // lParam = MAKELPARAM(320, 240)
        val lParam = (240L shl 16) or 320L
        KadreWndProc.dispatch(TEST_HWND, WM_MOUSEMOVE, 0L, lParam)

        assertIs<WindowEvent.PointerMoved>(capturedEvent).also { event ->
            assertEquals(320.0, event.position.x)
            assertEquals(240.0, event.position.y)
        }
    }

    // ── Mouse buttons ───────────────────────────────────────────────────────────

    @Test
    fun `WM_LBUTTONDOWN emet MouseInput Left Pressed`() {
        KadreWndProc.dispatch(TEST_HWND, WM_LBUTTONDOWN, 0L, 0L)
        assertIs<WindowEvent.MouseInput>(capturedEvent).also { event ->
            assertEquals(MouseButton.Left, event.button)
            assertEquals(KeyState.Pressed, event.state)
        }
    }

    @Test
    fun `WM_LBUTTONUP emet MouseInput Left Released`() {
        KadreWndProc.dispatch(TEST_HWND, WM_LBUTTONUP, 0L, 0L)
        assertIs<WindowEvent.MouseInput>(capturedEvent).also { event ->
            assertEquals(MouseButton.Left, event.button)
            assertEquals(KeyState.Released, event.state)
        }
    }

    @Test
    fun `WM_RBUTTONDOWN emet MouseInput Right Pressed`() {
        KadreWndProc.dispatch(TEST_HWND, WM_RBUTTONDOWN, 0L, 0L)
        assertIs<WindowEvent.MouseInput>(capturedEvent).also { event ->
            assertEquals(MouseButton.Right, event.button)
            assertEquals(KeyState.Pressed, event.state)
        }
    }

    @Test
    fun `WM_RBUTTONUP emet MouseInput Right Released`() {
        KadreWndProc.dispatch(TEST_HWND, WM_RBUTTONUP, 0L, 0L)
        assertIs<WindowEvent.MouseInput>(capturedEvent).also { event ->
            assertEquals(MouseButton.Right, event.button)
            assertEquals(KeyState.Released, event.state)
        }
    }

    @Test
    fun `WM_MBUTTONDOWN emet MouseInput Middle Pressed`() {
        KadreWndProc.dispatch(TEST_HWND, WM_MBUTTONDOWN, 0L, 0L)
        assertIs<WindowEvent.MouseInput>(capturedEvent).also { event ->
            assertEquals(MouseButton.Middle, event.button)
            assertEquals(KeyState.Pressed, event.state)
        }
    }

    @Test
    fun `WM_MBUTTONUP emet MouseInput Middle Released`() {
        KadreWndProc.dispatch(TEST_HWND, WM_MBUTTONUP, 0L, 0L)
        assertIs<WindowEvent.MouseInput>(capturedEvent).also { event ->
            assertEquals(MouseButton.Middle, event.button)
            assertEquals(KeyState.Released, event.state)
        }
    }

    // ── WM_MOUSEWHEEL ─────────────────────────────────────────────────────────

    @Test
    fun `WM_MOUSEWHEEL emet MouseWheel avec delta positif vers le haut`() {
        // wParam HIWORD = +120 (one notch up)
        val wParam = 120L shl 16
        KadreWndProc.dispatch(TEST_HWND, WM_MOUSEWHEEL, wParam, 0L)

        assertIs<WindowEvent.MouseWheel>(capturedEvent).also { event ->
            assertEquals(0.0, event.deltaX)
            assertEquals(1.0, event.deltaY)
        }
    }

    @Test
    fun `WM_MOUSEWHEEL emet MouseWheel avec delta negatif vers le bas`() {
        // wParam HIWORD = -120 (one notch down) → SHORT(-120) = 0xFF88
        val shortNeg120 = (-120).toShort().toInt() and 0xFFFF
        val wParam = (shortNeg120.toLong() shl 16)
        KadreWndProc.dispatch(TEST_HWND, WM_MOUSEWHEEL, wParam, 0L)

        assertIs<WindowEvent.MouseWheel>(capturedEvent).also { event ->
            assertEquals(0.0, event.deltaX)
            assertEquals(-1.0, event.deltaY)
        }
    }

    // ── WM_CLOSE ──────────────────────────────────────────────────────────────

    @Test
    fun `WM_CLOSE emet CloseRequested`() {
        KadreWndProc.dispatch(TEST_HWND, WM_CLOSE, 0L, 0L)
        assertIs<WindowEvent.CloseRequested>(capturedEvent)
    }

    @Test
    fun `WM_CLOSE retourne 0 sans appeler DefWindowProcW`() {
        val result = KadreWndProc.dispatch(TEST_HWND, WM_CLOSE, 0L, 0L)
        assertEquals(0L, result)
    }

    // ── WM_DESTROY ────────────────────────────────────────────────────────────

    @Test
    fun `WM_DESTROY emet Destroyed`() {
        KadreWndProc.dispatch(TEST_HWND, WM_DESTROY, 0L, 0L)
        assertIs<WindowEvent.Destroyed>(capturedEvent)
    }

    @Test
    fun `WM_DESTROY retourne 0`() {
        val result = KadreWndProc.dispatch(TEST_HWND, WM_DESTROY, 0L, 0L)
        assertEquals(0L, result)
    }

    // ── WM_DPICHANGED ─────────────────────────────────────────────────────────

    @Test
    fun `WM_DPICHANGED emet ScaleFactorChanged avec facteur correct`() {
        // wParam LOWORD = 192 DPI → factor 2.0 (192 / 96)
        val wParam = 192L
        KadreWndProc.dispatch(TEST_HWND, WM_DPICHANGED, wParam, 0L)

        assertIs<WindowEvent.ScaleFactorChanged>(capturedEvent).also { event ->
            assertEquals(2.0, event.factor)
        }
    }

    @Test
    fun `WM_DPICHANGED avec 96 DPI retourne facteur 1 0`() {
        // wParam LOWORD = 96 DPI → factor 1.0 (identity)
        val wParam = 96L
        KadreWndProc.dispatch(TEST_HWND, WM_DPICHANGED, wParam, 0L)

        assertIs<WindowEvent.ScaleFactorChanged>(capturedEvent).also { event ->
            assertEquals(1.0, event.factor)
        }
    }

    // ── Unknown message ─────────────────────────────────────────────────────────

    @Test
    fun `message inconnu ne transmet pas d evenement au handler`() {
        // 0xDEAD is not a known WM_* — must go into else → defWindowProcW
        // On macOS/Linux, defWindowProcW is null → returns 0
        KadreWndProc.dispatch(TEST_HWND, 0xDEAD, 0L, 0L)
        // No event should have been emitted
        assertNull(capturedEvent)
    }

    // ── Handler not installed ─────────────────────────────────────────────────

    @Test
    fun `dispatch sans handler installe ne leve pas d exception`() {
        KadreWndProc.uninstall()
        // Must not throw an exception — events are silently ignored
        KadreWndProc.dispatch(TEST_HWND, WM_PAINT, 0L, 0L)
        KadreWndProc.dispatch(TEST_HWND, WM_CLOSE, 0L, 0L)
    }

    // ── Win32 constants ─────────────────────────────────────────────────────────

    @Test
    fun `constantes WM ont les valeurs hexadecimales attendues`() {
        assertEquals(0x000F, WM_PAINT)
        assertEquals(0x0005, WM_SIZE)
        assertEquals(0x0100, WM_KEYDOWN)
        assertEquals(0x0101, WM_KEYUP)
        assertEquals(0x0104, WM_SYSKEYDOWN)
        assertEquals(0x0105, WM_SYSKEYUP)
        assertEquals(0x0200, WM_MOUSEMOVE)
        assertEquals(0x0201, WM_LBUTTONDOWN)
        assertEquals(0x0202, WM_LBUTTONUP)
        assertEquals(0x0204, WM_RBUTTONDOWN)
        assertEquals(0x0205, WM_RBUTTONUP)
        assertEquals(0x0207, WM_MBUTTONDOWN)
        assertEquals(0x0208, WM_MBUTTONUP)
        assertEquals(0x020A, WM_MOUSEWHEEL)
        assertEquals(0x0010, WM_CLOSE)
        assertEquals(0x0002, WM_DESTROY)
        assertEquals(0x02E0, WM_DPICHANGED)
    }

    @Test
    fun `KF_REPEAT est le bit 30`() {
        assertEquals(0x4000_0000L, KF_REPEAT)
    }
}
