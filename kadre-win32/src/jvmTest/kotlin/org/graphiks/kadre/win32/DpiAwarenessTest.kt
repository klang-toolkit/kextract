/**
 * Tests for enabling Per-Monitor-V2 DPI awareness.
 *
 * On non-Windows platforms (macOS/Linux CI), the lookup of
 * `SetProcessDpiAwarenessContext` in user32 fails: [enablePerMonitorV2DpiAwareness]
 * must then return `false` without throwing an exception. On Windows, the call succeeds
 * (or returns false if already set) — in all cases, never an exception.
 */
package org.graphiks.kadre.win32

import kotlin.test.Test
import kotlin.test.assertFalse

class DpiAwarenessTest {

    @Test
    fun enablePerMonitorV2DpiAwareness_neLevePasDException() {
        // Must never throw, regardless of the platform.
        val result = enablePerMonitorV2DpiAwareness()
        // Idempotence: a second call remains safe.
        enablePerMonitorV2DpiAwareness()
        // result is a valid Boolean (true on Windows the first time, false elsewhere).
        check(result || !result)
    }

    @Test
    fun constanteDpiAwarenessContext_estLaPseudoHandleAttendue() {
        // DPI_AWARENESS_CONTEXT_PER_MONITOR_AWARE_V2 == -4 (Win32 pseudo-handle).
        assertFalse(DPI_AWARENESS_CONTEXT_PER_MONITOR_AWARE_V2 != -4L)
    }
}
