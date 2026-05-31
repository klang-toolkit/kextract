/**
 * Tests for [FrameTimingTracer].
 *
 * Verifies: 0 overhead / no log when disabled, accumulation + flush of stats
 * when enabled, and logging of slow frames above the threshold.
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
        FrameTimingTracer.slowFrameThresholdMs = 0.0 // any frame > 0 ms is "slow"
        FrameTimingTracer.reset()

        FrameTimingTracer.onRedrawStart()
        // Small workload to guarantee a measurable duration > 0.
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
