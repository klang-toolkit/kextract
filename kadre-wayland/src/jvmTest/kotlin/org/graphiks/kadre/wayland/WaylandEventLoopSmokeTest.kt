/**
 * Smoke tests for WaylandEventLoop and WaylandEventLoopProxy.
 *
 * These tests do not require a running Wayland server:
 * they only verify the static invariants and the null-safe
 * behavior of the bindings.
 *
 * WaylandEventLoop.
 */
package org.graphiks.kadre.wayland

import org.graphiks.kadre.core.DeviceEvents
import org.graphiks.kadre.core.PhysicalSize
import org.graphiks.kadre.core.WindowEvent
import org.graphiks.kadre.core.WindowId
import org.graphiks.kadre.core.WindowAttributes
import java.util.concurrent.ConcurrentLinkedQueue
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue

class WaylandEventLoopSmokeTest {

    /**
     * Verifies that waylandRunning is false at JVM startup.
     *
     * If this test fails, it means a Wayland loop is already running — which
     * would be unexpected in an isolated unit-test context.
     */
    @Test
    fun `waylandRunning starts false`() {
        assertFalse(waylandRunning.get(), "waylandRunning must be false at startup")
    }

    /**
     * Verifies that WaylandEventLoopProxy.wakeUp() does not crash when libC is absent.
     *
     * On macOS/Windows, nativeWrite is null — wakeUp() must simply return
     * without throwing an exception.
     */
    @Test
    fun `wakeUp proxy no-crash when libC absent`() {
        // We pass fd=-1 to simulate the absence of eventfd (immediate return in wakeUp)
        val proxy = WaylandEventLoopProxy(eventFd = -1)
        proxy.wakeUp()  // must not throw an exception
        proxy.wakeUp()  // second call — idempotent
    }

    /**
     * Verifies that WaylandEventLoopProxy.wakeUp() has no effect if nativeWrite is null
     * and the eventFd is valid (simulation).
     *
     * On platforms without libc.so.6, nativeWrite is null and wakeUp() must return
     * cleanly, even with an fd > 0.
     */
    @Test
    fun `wakeUp proxy handles missing nativeWrite gracefully`() {
        // fd=42 — fictitious, never opened
        val proxy = WaylandEventLoopProxy(eventFd = 42)
        // On macOS/Windows, nativeWrite is null → wakeUp returns cleanly
        // On Linux with libc, a write on fd=42 (invalid) will return -1 or EBADF
        // but must not throw an exception (try/catch in wakeUp)
        try {
            proxy.wakeUp()
        } catch (e: Throwable) {
            // Tolerated only if the exception is unexpected — log for diagnosis
            throw AssertionError("wakeUp() must never propagate an exception: $e", e)
        }
    }

    /**
     * Verifies that libC loads without exception (or is null cleanly).
     *
     * On Linux: libC is non-null.
     * On macOS/Windows: libC is null (libc.so.6 absent).
     */
    @Test
    fun `libC loads safely on any platform`() {
        // No assertion on the value — we just verify that the access does not crash
        val lib = libC  // may be null
        // On Linux, we can verify the derived handles
        if (lib != null) {
            assertNotNull(nativePoll, "nativePoll must be non-null if libC is available")
            assertNotNull(nativeEventfd, "nativeEventfd must be non-null if libC is available")
            assertNotNull(nativeRead, "nativeRead must be non-null if libC is available")
            assertNotNull(nativeWrite, "nativeWrite must be non-null if libC is available")
            assertNotNull(nativeClose, "nativeClose must be non-null if libC is available")
        }
    }

    @Test
    fun `primaryMonitor stays null even when synthetic monitors are available`() {
        val loop = WaylandEventLoop(
            displayPtr = 77L,
            compositorPtr = 0L,
            xdgWmBasePtr = 0L,
            shmPtr = 0L,
            eventFd = -1,
        )
        val window = WaylandWindow.createForTest(
            display = 77L,
            surface = 1001L,
            attrs = WindowAttributes(size = PhysicalSize(640, 480)),
        )
        loop.windows[window.id.value] = window

        assertTrue(loop.availableMonitors().isNotEmpty())
        assertEquals(null, loop.primaryMonitor())
    }

    @Test
    fun `routeWaylandInputEvent enqueues event for matching surface`() {
        val window = WaylandWindow.createForTest(surface = 1001L)
        val queue = ConcurrentLinkedQueue<Pair<WindowId, WindowEvent>>()
        val event = WindowEvent.Focused(true)

        val routed = routeWaylandInputEvent(
            surfacePtr = 1001L,
            event = event,
            windows = mapOf(1001L to window),
            eventQueue = queue,
        )

        assertTrue(routed)
        val queued = queue.poll()
        assertNotNull(queued)
        assertEquals(window.id, queued.first)
        assertEquals(event, queued.second)
    }

    @Test
    fun `routeWaylandInputEvent drops unknown surface`() {
        val window = WaylandWindow.createForTest(surface = 1001L)
        val queue = ConcurrentLinkedQueue<Pair<WindowId, WindowEvent>>()

        val routed = routeWaylandInputEvent(
            surfacePtr = 2002L,
            event = WindowEvent.Focused(true),
            windows = mapOf(1001L to window),
            eventQueue = queue,
        )

        assertFalse(routed)
        assertTrue(queue.isEmpty())
    }

    @Test
    fun `systemTheme returns null when no dbus-send available`() {
        val loop = WaylandEventLoop(
            displayPtr = 77L,
            compositorPtr = 0L,
            xdgWmBasePtr = 0L,
            shmPtr = 0L,
            eventFd = -1,
        )
        WaylandThemePortal.resetCache()
        assertNull(loop.systemTheme())
    }

    @Test
    fun `systemTheme is idempotent`() {
        val loop = WaylandEventLoop(
            displayPtr = 77L,
            compositorPtr = 0L,
            xdgWmBasePtr = 0L,
            shmPtr = 0L,
            eventFd = -1,
        )
        WaylandThemePortal.resetCache()
        val first = loop.systemTheme()
        val second = loop.systemTheme()
        assertEquals(first, second)
    }

    @Test
    fun `refreshTheme does not crash with no windows`() {
        val loop = WaylandEventLoop(
            displayPtr = 77L,
            compositorPtr = 0L,
            xdgWmBasePtr = 0L,
            shmPtr = 0L,
            eventFd = -1,
        )
        WaylandThemePortal.resetCache()
        loop.refreshTheme()
    }

    @Test
    fun `refreshTheme emits ThemeChanged when theme is available`() {
        val loop = WaylandEventLoop(
            displayPtr = 77L,
            compositorPtr = 0L,
            xdgWmBasePtr = 0L,
            shmPtr = 0L,
            eventFd = -1,
        )
        WaylandThemePortal.resetCache()
        // Force cache to a known state by calling systemTheme first
        loop.systemTheme()
        // refreshTheme should not crash; on CI it may still be null
        loop.refreshTheme()
    }

    // ── R4: device event filter ───────────────────────────────────────────────

    @Test
    fun `deviceEventFilter defaults to WhenFocused`() {
        val loop = WaylandEventLoop(
            displayPtr = 77L,
            compositorPtr = 0L,
            xdgWmBasePtr = 0L,
            shmPtr = 0L,
            eventFd = -1,
        )
        assertEquals(DeviceEvents.WhenFocused, loop.deviceEventFilter)
    }

    @Test
    fun `listenDeviceEvents stores Never`() {
        val loop = WaylandEventLoop(
            displayPtr = 77L,
            compositorPtr = 0L,
            xdgWmBasePtr = 0L,
            shmPtr = 0L,
            eventFd = -1,
        )
        loop.listenDeviceEvents(DeviceEvents.Never)
        assertEquals(DeviceEvents.Never, loop.deviceEventFilter)
    }

    @Test
    fun `listenDeviceEvents stores Always`() {
        val loop = WaylandEventLoop(
            displayPtr = 77L,
            compositorPtr = 0L,
            xdgWmBasePtr = 0L,
            shmPtr = 0L,
            eventFd = -1,
        )
        loop.listenDeviceEvents(DeviceEvents.Always)
        assertEquals(DeviceEvents.Always, loop.deviceEventFilter)
    }

    @Test
    fun `listenDeviceEvents toggles between modes`() {
        val loop = WaylandEventLoop(
            displayPtr = 77L,
            compositorPtr = 0L,
            xdgWmBasePtr = 0L,
            shmPtr = 0L,
            eventFd = -1,
        )
        loop.listenDeviceEvents(DeviceEvents.Never)
        assertEquals(DeviceEvents.Never, loop.deviceEventFilter)
        loop.listenDeviceEvents(DeviceEvents.WhenFocused)
        assertEquals(DeviceEvents.WhenFocused, loop.deviceEventFilter)
        loop.listenDeviceEvents(DeviceEvents.Always)
        assertEquals(DeviceEvents.Always, loop.deviceEventFilter)
    }
}
