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

import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertNotNull

class WaylandEventLoopSmokeTest {

    /**
     * Verifies that waylandRunning is false at JVM startup.
     *
     * If this test fails, it means a Wayland loop is already running — which
     * would be unexpected in an isolated unit-test context.
     */
    @Test
    fun `waylandRunning starts false`() {
        assertFalse(waylandRunning.get(), "waylandRunning doit être false au démarrage")
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
            throw AssertionError("wakeUp() ne doit jamais propager d'exception : $e", e)
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
            assertNotNull(nativePoll, "nativePoll doit être non-null si libC est disponible")
            assertNotNull(nativeEventfd, "nativeEventfd doit être non-null si libC est disponible")
            assertNotNull(nativeRead, "nativeRead doit être non-null si libC est disponible")
            assertNotNull(nativeWrite, "nativeWrite doit être non-null si libC est disponible")
            assertNotNull(nativeClose, "nativeClose doit être non-null si libC est disponible")
        }
    }
}
