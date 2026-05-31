/**
 * Tests pour [FrameTimingTracer] (Redmine #90).
 *
 * Vérifie : 0 overhead / aucun log quand désactivé, accumulation + flush des stats
 * quand activé, et journalisation des frames lentes au-delà du seuil.
 */
package org.graphiks.kadre.core

import kotlin.test.AfterTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class FrameTimingTracerTest {

    @AfterTest
    fun cleanup() {
        FrameTimingTracer.enabled = false
        FrameTimingTracer.sink = ::println
        FrameTimingTracer.reset()
    }

    @Test
    fun desactive_aucunLog_aucuneCollecte() {
        val logs = mutableListOf<String>()
        FrameTimingTracer.sink = { logs += it }
        FrameTimingTracer.enabled = false

        repeat(10) {
            FrameTimingTracer.onRedrawStart()
            FrameTimingTracer.onPresentEnd()
        }
        FrameTimingTracer.flush()

        assertTrue(logs.isEmpty(), "Aucun log ne doit être émis quand désactivé")
    }

    @Test
    fun active_flushPublieDesStats() {
        val logs = mutableListOf<String>()
        FrameTimingTracer.sink = { logs += it }
        FrameTimingTracer.enabled = true
        FrameTimingTracer.reset()

        repeat(5) {
            FrameTimingTracer.onRedrawStart()
            FrameTimingTracer.onPresentEnd()
        }
        FrameTimingTracer.flush()

        val stats = logs.filter { it.contains("frames=") }
        assertEquals(1, stats.size, "flush() doit publier une ligne de stats agrégées")
        assertTrue(stats.first().contains("min=") && stats.first().contains("p99="))
    }

    @Test
    fun frameLente_estJournalisee() {
        val logs = mutableListOf<String>()
        FrameTimingTracer.sink = { logs += it }
        FrameTimingTracer.enabled = true
        FrameTimingTracer.slowFrameThresholdMs = 0.0 // toute frame > 0 ms est "lente"
        FrameTimingTracer.reset()

        FrameTimingTracer.onRedrawStart()
        // Petite charge pour garantir une durée mesurable > 0.
        var acc = 0L
        repeat(100_000) { acc += it }
        check(acc >= 0)
        FrameTimingTracer.onPresentEnd()

        assertTrue(
            logs.any { it.contains("slow frame") },
            "Une frame dépassant le seuil doit être journalisée",
        )
        FrameTimingTracer.slowFrameThresholdMs = 16.7
    }
}
