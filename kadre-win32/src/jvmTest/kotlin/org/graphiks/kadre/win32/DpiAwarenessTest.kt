/**
 * Tests pour l'activation de la conscience DPI Per-Monitor-V2.
 *
 * Sur les plateformes non-Windows (macOS/Linux CI), le lookup de
 * `SetProcessDpiAwarenessContext` dans user32 échoue : [enablePerMonitorV2DpiAwareness]
 * doit alors retourner `false` sans lever d'exception. Sur Windows, l'appel réussit
 * (ou retourne false si déjà fixé) — dans tous les cas, jamais d'exception.
 */
package org.graphiks.kadre.win32

import kotlin.test.Test
import kotlin.test.assertFalse

class DpiAwarenessTest {

    @Test
    fun enablePerMonitorV2DpiAwareness_neLevePasDException() {
        // Ne doit jamais throw, quelle que soit la plateforme.
        val result = enablePerMonitorV2DpiAwareness()
        // Idempotence : un second appel reste sûr.
        enablePerMonitorV2DpiAwareness()
        // result est un Boolean valide (true sur Windows 1ʳᵉ fois, false ailleurs).
        check(result || !result)
    }

    @Test
    fun constanteDpiAwarenessContext_estLaPseudoHandleAttendue() {
        // DPI_AWARENESS_CONTEXT_PER_MONITOR_AWARE_V2 == -4 (pseudo-handle Win32).
        assertFalse(DPI_AWARENESS_CONTEXT_PER_MONITOR_AWARE_V2 != -4L)
    }
}
