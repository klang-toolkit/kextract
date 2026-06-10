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

import org.graphiks.kadre.ffi.win32.*
import org.graphiks.kadre.core.KeyCode
import org.graphiks.kadre.core.KeyState
import org.graphiks.kadre.core.MouseButton
import org.graphiks.kadre.core.ButtonSource
import org.graphiks.kadre.core.FingerId
import org.graphiks.kadre.core.NativeKeyCode
import org.graphiks.kadre.core.PhysicalKey
import org.graphiks.kadre.core.PhysicalPosition
import org.graphiks.kadre.core.PhysicalSize
import org.graphiks.kadre.core.PointerKind
import org.graphiks.kadre.core.PointerSource
import org.graphiks.kadre.core.WindowEvent
import java.lang.foreign.Arena
import java.lang.foreign.ValueLayout
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
    fun `WM_PAINT emits RedrawRequested`() {
        KadreWndProc.dispatch(TEST_HWND, WM_PAINT, 0L, 0L)

        assertEquals(TEST_HWND, capturedHwnd)
        assertIs<WindowEvent.RedrawRequested>(capturedEvent)
    }

    @Test
    fun `WM_PAINT returns 0`() {
        val result = KadreWndProc.dispatch(TEST_HWND, WM_PAINT, 0L, 0L)
        assertEquals(0L, result)
    }

    // ── WM_SIZE ───────────────────────────────────────────────────────────────

    @Test
    fun `WM_SIZE emits Resized with correct dimensions`() {
        // lParam = MAKELPARAM(800, 600) = 600 << 16 | 800
        val lParam = (600L shl 16) or 800L
        KadreWndProc.dispatch(TEST_HWND, WM_SIZE, 0L, lParam)

        assertIs<WindowEvent.Resized>(capturedEvent).also { event ->
            assertEquals(PhysicalSize(800, 600), event.size)
        }
    }

    @Test
    fun `WM_SIZE returns 0`() {
        val result = KadreWndProc.dispatch(TEST_HWND, WM_SIZE, 0L, 0L)
        assertEquals(0L, result)
    }

    // ── WM_KEYDOWN ────────────────────────────────────────────────────────────

    @Test
    fun `WM_KEYDOWN emits KeyInput Pressed for key A`() {
        // wParam = VK_A, lParam = 0 (no repeat)
        KadreWndProc.dispatch(TEST_HWND, WM_KEYDOWN, VK_A.toLong(), 0L)

        assertIs<WindowEvent.KeyInput>(capturedEvent).also { event ->
            assertEquals(PhysicalKey.Code(KeyCode.KeyA), event.event.physicalKey)
            assertEquals(KeyState.Pressed, event.event.state)
            assertEquals(false, event.event.repeat)
        }
    }

    @Test
    fun `WM_KEYDOWN detects repeat via bit 30 of lParam`() {
        // Bit 30 = 0x4000_0000 → key already down (repeat)
        val lParamRepeat = KF_REPEAT
        KadreWndProc.dispatch(TEST_HWND, WM_KEYDOWN, VK_SPACE.toLong(), lParamRepeat)

        assertIs<WindowEvent.KeyInput>(capturedEvent).also { event ->
            assertEquals(PhysicalKey.Code(KeyCode.Space), event.event.physicalKey)
            assertEquals(KeyState.Pressed, event.event.state)
            assertEquals(true, event.event.repeat)
        }
    }

    @Test
    fun `WM_KEYDOWN preserves extended scancode in native key code`() {
        val scanCode = 0x4DL
        val lParam = (scanCode shl 16) or KF_EXTENDED
        KadreWndProc.dispatch(TEST_HWND, WM_KEYDOWN, VK_RIGHT.toLong(), lParam)

        assertIs<WindowEvent.KeyInput>(capturedEvent).also { event ->
            assertEquals(0xE000L or scanCode, event.event.native.scanCode)
            assertEquals(
                NativeKeyCode.Win32(scanCode = 0xE000L or scanCode, virtualKey = VK_RIGHT.toLong()),
                event.event.native.nativeCode,
            )
        }
    }

    @Test
    fun `WM_KEYUP emits KeyInput Released`() {
        KadreWndProc.dispatch(TEST_HWND, WM_KEYUP, VK_ESCAPE.toLong(), 0L)

        assertIs<WindowEvent.KeyInput>(capturedEvent).also { event ->
            assertEquals(PhysicalKey.Code(KeyCode.Escape), event.event.physicalKey)
            assertEquals(KeyState.Released, event.event.state)
            assertEquals(false, event.event.repeat)
        }
    }

    @Test
    fun `WM_SYSKEYDOWN emits KeyInput Pressed for key F4`() {
        KadreWndProc.dispatch(TEST_HWND, WM_SYSKEYDOWN, VK_F4.toLong(), 0L)

        assertIs<WindowEvent.KeyInput>(capturedEvent).also { event ->
            assertEquals(PhysicalKey.Code(KeyCode.F4), event.event.physicalKey)
            assertEquals(KeyState.Pressed, event.event.state)
        }
    }

    @Test
    fun `WM_SYSKEYUP emits KeyInput Released`() {
        KadreWndProc.dispatch(TEST_HWND, WM_SYSKEYUP, VK_F4.toLong(), 0L)

        assertIs<WindowEvent.KeyInput>(capturedEvent).also { event ->
            assertEquals(PhysicalKey.Code(KeyCode.F4), event.event.physicalKey)
            assertEquals(KeyState.Released, event.event.state)
        }
    }

    // ── WM_MOUSEMOVE ──────────────────────────────────────────────────────────

    @Test
    fun `WM_MOUSEMOVE emits PointerMoved with correct coordinates`() {
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
    fun `WM_LBUTTONDOWN emits PointerButton Left Pressed`() {
        KadreWndProc.dispatch(TEST_HWND, WM_LBUTTONDOWN, 0L, 0L)
        assertIs<WindowEvent.PointerButton>(capturedEvent).also { event ->
            assertEquals(ButtonSource.Mouse(MouseButton.Left), event.button)
            assertEquals(KeyState.Pressed, event.state)
        }
    }

    @Test
    fun `WM_LBUTTONUP emits PointerButton Left Released`() {
        KadreWndProc.dispatch(TEST_HWND, WM_LBUTTONUP, 0L, 0L)
        assertIs<WindowEvent.PointerButton>(capturedEvent).also { event ->
            assertEquals(ButtonSource.Mouse(MouseButton.Left), event.button)
            assertEquals(KeyState.Released, event.state)
        }
    }

    @Test
    fun `WM_RBUTTONDOWN emits PointerButton Right Pressed`() {
        KadreWndProc.dispatch(TEST_HWND, WM_RBUTTONDOWN, 0L, 0L)
        assertIs<WindowEvent.PointerButton>(capturedEvent).also { event ->
            assertEquals(ButtonSource.Mouse(MouseButton.Right), event.button)
            assertEquals(KeyState.Pressed, event.state)
        }
    }

    @Test
    fun `WM_RBUTTONUP emits PointerButton Right Released`() {
        KadreWndProc.dispatch(TEST_HWND, WM_RBUTTONUP, 0L, 0L)
        assertIs<WindowEvent.PointerButton>(capturedEvent).also { event ->
            assertEquals(ButtonSource.Mouse(MouseButton.Right), event.button)
            assertEquals(KeyState.Released, event.state)
        }
    }

    @Test
    fun `WM_MBUTTONDOWN emits PointerButton Middle Pressed`() {
        KadreWndProc.dispatch(TEST_HWND, WM_MBUTTONDOWN, 0L, 0L)
        assertIs<WindowEvent.PointerButton>(capturedEvent).also { event ->
            assertEquals(ButtonSource.Mouse(MouseButton.Middle), event.button)
            assertEquals(KeyState.Pressed, event.state)
        }
    }

    @Test
    fun `WM_MBUTTONUP emits PointerButton Middle Released`() {
        KadreWndProc.dispatch(TEST_HWND, WM_MBUTTONUP, 0L, 0L)
        assertIs<WindowEvent.PointerButton>(capturedEvent).also { event ->
            assertEquals(ButtonSource.Mouse(MouseButton.Middle), event.button)
            assertEquals(KeyState.Released, event.state)
        }
    }

    // ── WM_MOUSEWHEEL ─────────────────────────────────────────────────────────

    @Test
    fun `WM_MOUSEWHEEL emits MouseWheel with positive delta upward`() {
        // wParam HIWORD = +120 (one notch up)
        val wParam = 120L shl 16
        KadreWndProc.dispatch(TEST_HWND, WM_MOUSEWHEEL, wParam, 0L)

        assertIs<WindowEvent.MouseWheel>(capturedEvent).also { event ->
            assertEquals(0.0, event.deltaX)
            assertEquals(1.0, event.deltaY)
        }
    }

    @Test
    fun `WM_MOUSEWHEEL emits MouseWheel with negative delta downward`() {
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
    fun `WM_CLOSE emits CloseRequested`() {
        KadreWndProc.dispatch(TEST_HWND, WM_CLOSE, 0L, 0L)
        assertIs<WindowEvent.CloseRequested>(capturedEvent)
    }

    @Test
    fun `WM_CLOSE returns 0 without calling DefWindowProcW`() {
        val result = KadreWndProc.dispatch(TEST_HWND, WM_CLOSE, 0L, 0L)
        assertEquals(0L, result)
    }

    // ── WM_DESTROY ────────────────────────────────────────────────────────────

    @Test
    fun `WM_DESTROY emits Destroyed`() {
        KadreWndProc.dispatch(TEST_HWND, WM_DESTROY, 0L, 0L)
        assertIs<WindowEvent.Destroyed>(capturedEvent)
    }

    @Test
    fun `WM_DESTROY returns 0`() {
        val result = KadreWndProc.dispatch(TEST_HWND, WM_DESTROY, 0L, 0L)
        assertEquals(0L, result)
    }

    // ── WM_DPICHANGED ─────────────────────────────────────────────────────────

    @Test
    fun `WM_DPICHANGED emits ScaleFactorChanged with correct factor`() {
        // wParam LOWORD = 192 DPI → factor 2.0 (192 / 96)
        val wParam = 192L
        KadreWndProc.dispatch(TEST_HWND, WM_DPICHANGED, wParam, 0L)

        assertIs<WindowEvent.ScaleFactorChanged>(capturedEvent).also { event ->
            assertEquals(2.0, event.factor)
        }
    }

    @Test
    fun `WM_DPICHANGED with 96 DPI returns factor 1 0`() {
        // wParam LOWORD = 96 DPI → factor 1.0 (identity)
        val wParam = 96L
        KadreWndProc.dispatch(TEST_HWND, WM_DPICHANGED, wParam, 0L)

        assertIs<WindowEvent.ScaleFactorChanged>(capturedEvent).also { event ->
            assertEquals(1.0, event.factor)
        }
    }

    // ── WM_TOUCH ──────────────────────────────────────────────────────────────

    /**
     * Builds a one-contact TOUCHINPUT buffer for the decoder tests.
     *
     * @param x     screen X in physical pixels (the buffer stores hundredths)
     * @param y     screen Y in physical pixels
     * @param id    contact id (dwID)
     * @param flags TOUCHEVENTF_* bits
     */
    private fun touchInputBuffer(arena: Arena, x: Int, y: Int, id: Int, flags: Int) =
        arena.allocate(TOUCHINPUT_SIZE.toLong(), 8L).also { seg ->
            seg.set(ValueLayout.JAVA_INT, TOUCHINPUT_OFFSET_X, x * 100)
            seg.set(ValueLayout.JAVA_INT, TOUCHINPUT_OFFSET_Y, y * 100)
            seg.set(ValueLayout.JAVA_INT, TOUCHINPUT_OFFSET_ID, id)
            seg.set(ValueLayout.JAVA_INT, TOUCHINPUT_OFFSET_FLAGS, flags)
        }

    @Test
    fun `decodeTouchInput maps TOUCHEVENTF_DOWN to enter and press`() {
        Arena.ofConfined().use { arena ->
            val buffer = touchInputBuffer(arena, x = 320, y = 240, id = 7, flags = TOUCHEVENTF_DOWN)
            val events = KadreWndProc.decodeTouchInput(buffer, 0)

            assertIs<WindowEvent.PointerEntered>(events[0]).also { event ->
                assertEquals(PointerKind.Touch, event.kind)
                assertEquals(320.0, event.position.x)
                assertEquals(240.0, event.position.y)
            }
            assertIs<WindowEvent.PointerButton>(events[1]).also { event ->
                assertEquals(ButtonSource.Touch(FingerId(7L)), event.button)
                assertEquals(KeyState.Pressed, event.state)
                assertEquals(320.0, event.position.x)
                assertEquals(240.0, event.position.y)
            }
        }
    }

    @Test
    fun `decodeTouchInput maps TOUCHEVENTF_MOVE to pointer moved`() {
        Arena.ofConfined().use { arena ->
            val buffer = touchInputBuffer(arena, x = 10, y = 20, id = 1, flags = TOUCHEVENTF_MOVE)
            val events = KadreWndProc.decodeTouchInput(buffer, 0)

            assertIs<WindowEvent.PointerMoved>(events.single()).also { event ->
                assertEquals(PointerSource.Touch(FingerId(1L)), event.source)
                assertEquals(10.0, event.position.x)
                assertEquals(20.0, event.position.y)
            }
        }
    }

    @Test
    fun `decodeTouchInput maps TOUCHEVENTF_UP to release and leave`() {
        Arena.ofConfined().use { arena ->
            val buffer = touchInputBuffer(arena, x = 0, y = 0, id = 3, flags = TOUCHEVENTF_UP)
            val events = KadreWndProc.decodeTouchInput(buffer, 0)

            assertIs<WindowEvent.PointerButton>(events[0]).also { event ->
                assertEquals(ButtonSource.Touch(FingerId(3L)), event.button)
                assertEquals(KeyState.Released, event.state)
            }
            assertIs<WindowEvent.PointerLeft>(events[1]).also { event ->
                assertEquals(PointerKind.Touch, event.kind)
            }
        }
    }

    @Test
    fun `decodeTouchInput converts hundredths of a pixel`() {
        Arena.ofConfined().use { arena ->
            // Store raw hundredths directly to check the /100 conversion (12345 → 123.45 px).
            val buffer = arena.allocate(TOUCHINPUT_SIZE.toLong(), 8L)
            buffer.set(ValueLayout.JAVA_INT, TOUCHINPUT_OFFSET_X, 12345)
            buffer.set(ValueLayout.JAVA_INT, TOUCHINPUT_OFFSET_Y, 6789)
            buffer.set(ValueLayout.JAVA_INT, TOUCHINPUT_OFFSET_FLAGS, TOUCHEVENTF_MOVE)
            val event = KadreWndProc.decodeTouchInput(buffer, 0).single()
            assertIs<WindowEvent.PointerMoved>(event)
            assertEquals(123.45, event.position.x)
            assertEquals(67.89, event.position.y)
        }
    }

    @Test
    fun `decodeTouchInput converts TOUCHINPUT screen coordinates to client coordinates`() {
        Arena.ofConfined().use { arena ->
            // TOUCHINPUT stores screen coordinates in hundredths of a physical pixel.
            // The native ScreenToClient call only accepts integer POINT values, so the
            // decoder must convert the integer screen pixel and preserve the fraction.
            val buffer = arena.allocate(TOUCHINPUT_SIZE.toLong(), 8L)
            buffer.set(ValueLayout.JAVA_INT, TOUCHINPUT_OFFSET_X, 12345)
            buffer.set(ValueLayout.JAVA_INT, TOUCHINPUT_OFFSET_Y, 6789)
            buffer.set(ValueLayout.JAVA_INT, TOUCHINPUT_OFFSET_FLAGS, TOUCHEVENTF_MOVE)

            val event = KadreWndProc.decodeTouchInput(TEST_HWND, buffer, 0) { hwnd, screenX, screenY ->
                assertEquals(TEST_HWND, hwnd)
                assertEquals(123, screenX)
                assertEquals(67, screenY)
                PhysicalPosition(screenX - 100, screenY - 50)
            }.single()

            assertIs<WindowEvent.PointerMoved>(event)
            assertEquals(23.45, event.position.x)
            assertEquals(17.89, event.position.y)
        }
    }

    @Test
    fun `decodeTouchInput keeps screen coordinates when client conversion fails`() {
        Arena.ofConfined().use { arena ->
            val buffer = arena.allocate(TOUCHINPUT_SIZE.toLong(), 8L)
            buffer.set(ValueLayout.JAVA_INT, TOUCHINPUT_OFFSET_X, 12345)
            buffer.set(ValueLayout.JAVA_INT, TOUCHINPUT_OFFSET_Y, 6789)
            buffer.set(ValueLayout.JAVA_INT, TOUCHINPUT_OFFSET_FLAGS, TOUCHEVENTF_MOVE)

            val event = KadreWndProc.decodeTouchInput(TEST_HWND, buffer, 0) { _, _, _ -> null }.single()

            assertIs<WindowEvent.PointerMoved>(event)
            assertEquals(123.45, event.position.x)
            assertEquals(67.89, event.position.y)
        }
    }

    @Test
    fun `decodeTouchInput truncates negative touch coordinates toward zero before client conversion`() {
        Arena.ofConfined().use { arena ->
            val buffer = arena.allocate(TOUCHINPUT_SIZE.toLong(), 8L)
            buffer.set(ValueLayout.JAVA_INT, TOUCHINPUT_OFFSET_X, -12345)
            buffer.set(ValueLayout.JAVA_INT, TOUCHINPUT_OFFSET_Y, -6789)
            buffer.set(ValueLayout.JAVA_INT, TOUCHINPUT_OFFSET_FLAGS, TOUCHEVENTF_MOVE)

            val event = KadreWndProc.decodeTouchInput(TEST_HWND, buffer, 0) { _, screenX, screenY ->
                assertEquals(-123, screenX)
                assertEquals(-67, screenY)
                PhysicalPosition(screenX + 100, screenY + 50)
            }.single()

            assertIs<WindowEvent.PointerMoved>(event)
            assertEquals(-23.45, event.position.x)
            assertEquals(-17.89, event.position.y)
        }
    }

    @Test
    fun `decodeTouchInput reads the right contact at a given index`() {
        Arena.ofConfined().use { arena ->
            // Two contiguous TOUCHINPUT structs; decode the second one (index 1).
            val buffer = arena.allocate(2L * TOUCHINPUT_SIZE, 8L)
            val base = TOUCHINPUT_SIZE.toLong()
            buffer.set(ValueLayout.JAVA_INT, base + TOUCHINPUT_OFFSET_X, 5000)
            buffer.set(ValueLayout.JAVA_INT, base + TOUCHINPUT_OFFSET_Y, 6000)
            buffer.set(ValueLayout.JAVA_INT, base + TOUCHINPUT_OFFSET_ID, 42)
            buffer.set(ValueLayout.JAVA_INT, base + TOUCHINPUT_OFFSET_FLAGS, TOUCHEVENTF_DOWN)
            val events = KadreWndProc.decodeTouchInput(buffer, 1)
            assertIs<WindowEvent.PointerEntered>(events[0]).also { event ->
                assertEquals(50.0, event.position.x)
                assertEquals(60.0, event.position.y)
            }
            assertIs<WindowEvent.PointerButton>(events[1]).also { event ->
                assertEquals(ButtonSource.Touch(FingerId(42L)), event.button)
            }
        }
    }

    @Test
    fun `WM_TOUCH without native binding emits no event and returns 0`() {
        // On macOS/Linux getTouchInputInfo is null → graceful no-op.
        val wParam = 1L // LOWORD = 1 contact
        val result = KadreWndProc.dispatch(TEST_HWND, WM_TOUCH, wParam, 0L)
        assertEquals(0L, result)
        assertNull(capturedEvent)
    }

    @Test
    fun `WM_TOUCH constant equals 0x0240`() {
        assertEquals(0x0240, WM_TOUCH)
    }

    // ── Unknown message ─────────────────────────────────────────────────────────

    @Test
    fun `unknown message does not forward an event to the handler`() {
        // 0xDEAD is not a known WM_* — must go into else → defWindowProcW
        // On macOS/Linux, defWindowProcW is null → returns 0
        KadreWndProc.dispatch(TEST_HWND, 0xDEAD, 0L, 0L)
        // No event should have been emitted
        assertNull(capturedEvent)
    }

    // ── Handler not installed ─────────────────────────────────────────────────

    @Test
    fun `dispatch without installed handler does not throw an exception`() {
        KadreWndProc.uninstall()
        // Must not throw an exception — events are silently ignored
        KadreWndProc.dispatch(TEST_HWND, WM_PAINT, 0L, 0L)
        KadreWndProc.dispatch(TEST_HWND, WM_CLOSE, 0L, 0L)
    }

    // ── Win32 constants ─────────────────────────────────────────────────────────

    @Test
    fun `WM constants have the expected hexadecimal values`() {
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
    fun `KF_REPEAT is bit 30`() {
        assertEquals(0x4000_0000L, KF_REPEAT)
    }
}
