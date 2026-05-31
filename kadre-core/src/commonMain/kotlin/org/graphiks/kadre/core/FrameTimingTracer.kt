/**
 * FrameTimingTracer — runtime instrumentation of frame timing.
 *
 * Measures the `RedrawRequested → end of presentation` duration of each frame and publishes,
 * once per second, min/p50/p99/max statistics. Disabled by default:
 * **0% overhead** when [enabled] is `false` (all methods return
 * immediately, no allocation, no clock read).
 *
 * Activation:
 * - directly: `FrameTimingTracer.enabled = true`
 * - on JVM: at startup, read `-Dkadre.tracing=true` and set [enabled]
 *   (see the backend integration; not wired by default to stay overhead-free).
 *
 * Usage on the loop/render side:
 * ```kotlin
 * FrameTimingTracer.onRedrawStart()
 * // … render …
 * FrameTimingTracer.onPresentEnd()   // computes the duration and accumulates
 * ```
 *
 * Cross-platform clock via [kotlin.time.TimeSource.Monotonic].
 */
package org.graphiks.kadre.core

import kotlin.time.TimeSource
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

object FrameTimingTracer {

    /** Enables/disables tracing. `false` = 0 overhead (guards at the top of each method). */
    var enabled: Boolean = false

    /** Threshold above which a slow frame is logged individually (ms). */
    var slowFrameThresholdMs: Double = 16.7

    /** Sink of log lines — overridable in tests. Default: standard output. */
    var sink: (String) -> Unit = ::println

    private val clock = TimeSource.Monotonic
    private var frameStart: TimeSource.Monotonic.ValueTimeMark? = null
    private var windowStart: TimeSource.Monotonic.ValueTimeMark? = null
    private val samplesMs = ArrayList<Double>(128)

    /** Marks the start of a frame (reception of RedrawRequested). */
    fun onRedrawStart() {
        if (!enabled) return
        val now = clock.markNow()
        frameStart = now
        if (windowStart == null) windowStart = now
    }

    /**
     * Marks the end of presentation of a frame. Computes the duration since
     * [onRedrawStart], accumulates it, logs it if slow, and publishes the
     * aggregated stats every ~1 s.
     */
    fun onPresentEnd() {
        if (!enabled) return
        val start = frameStart ?: return
        val frameMs = start.elapsedNow().inWholeMicroseconds / 1000.0
        samplesMs += frameMs
        if (frameMs > slowFrameThresholdMs) {
            sink("[frame-timing] slow frame: ${frameMs.format2()} ms (> $slowFrameThresholdMs)")
        }
        val ws = windowStart
        if (ws != null && ws.elapsedNow() >= ONE_SECOND) {
            emitStats()
            samplesMs.clear()
            windowStart = clock.markNow()
        }
    }

    /** Forces immediate publication of the current statistics (useful in tests). */
    fun flush() {
        if (samplesMs.isNotEmpty()) emitStats()
        samplesMs.clear()
        windowStart = null
        frameStart = null
    }

    /** Resets the internal state (useful in tests). */
    fun reset() {
        samplesMs.clear()
        windowStart = null
        frameStart = null
    }

    private fun emitStats() {
        val sorted = samplesMs.sorted()
        if (sorted.isEmpty()) return
        val min = sorted.first()
        val max = sorted.last()
        val p50 = percentile(sorted, 0.50)
        val p99 = percentile(sorted, 0.99)
        val fps = sorted.size.toDouble()
        sink(
            "[frame-timing] frames=${sorted.size} ~${fps.format2()} fps | " +
                "min=${min.format2()} p50=${p50.format2()} p99=${p99.format2()} max=${max.format2()} ms"
        )
    }

    private fun percentile(sorted: List<Double>, q: Double): Double {
        if (sorted.isEmpty()) return 0.0
        val idx = ((sorted.size - 1) * q).toInt()
        return sorted[idx]
    }

    private fun Double.format2(): String {
        val scaled = (this * 100).toLong()
        return "${scaled / 100}.${(scaled % 100).toString().padStart(2, '0')}"
    }

    private val ONE_SECOND: Duration = 1.seconds
}
