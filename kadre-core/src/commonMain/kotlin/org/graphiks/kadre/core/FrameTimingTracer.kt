/**
 * FrameTimingTracer — instrumentation runtime du temps de frame (Redmine #90).
 *
 * Mesure la durée `RedrawRequested → fin de présentation` de chaque frame et publie,
 * une fois par seconde, des statistiques min/p50/p99/max. Désactivé par défaut :
 * **0 % d'overhead** quand [enabled] est `false` (toutes les méthodes retournent
 * immédiatement, aucune allocation, aucune lecture d'horloge).
 *
 * Activation :
 * - directement : `FrameTimingTracer.enabled = true`
 * - sur JVM : au démarrage, lire `-Dkadre.tracing=true` et positionner [enabled]
 *   (voir l'intégration backend ; non câblé par défaut pour rester sans overhead).
 *
 * Usage côté boucle/rendu :
 * ```kotlin
 * FrameTimingTracer.onRedrawStart()
 * // … rendu …
 * FrameTimingTracer.onPresentEnd()   // calcule la durée et accumule
 * ```
 *
 * Horloge multiplateforme via [kotlin.time.TimeSource.Monotonic].
 */
package org.graphiks.kadre.core

import kotlin.time.TimeSource
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

object FrameTimingTracer {

    /** Active/désactive le traçage. `false` = 0 overhead (gardes en tête de méthode). */
    var enabled: Boolean = false

    /** Seuil au-delà duquel une frame lente est journalisée individuellement (ms). */
    var slowFrameThresholdMs: Double = 16.7

    /** Sink des lignes de log — surchargeable en test. Défaut : sortie standard. */
    var sink: (String) -> Unit = ::println

    private val clock = TimeSource.Monotonic
    private var frameStart: TimeSource.Monotonic.ValueTimeMark? = null
    private var windowStart: TimeSource.Monotonic.ValueTimeMark? = null
    private val samplesMs = ArrayList<Double>(128)

    /** Marque le début d'une frame (réception de RedrawRequested). */
    fun onRedrawStart() {
        if (!enabled) return
        val now = clock.markNow()
        frameStart = now
        if (windowStart == null) windowStart = now
    }

    /**
     * Marque la fin de présentation d'une frame. Calcule la durée depuis
     * [onRedrawStart], l'accumule, journalise si lente, et publie les stats
     * agrégées toutes les ~1 s.
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

    /** Force la publication immédiate des statistiques courantes (utile en test). */
    fun flush() {
        if (samplesMs.isNotEmpty()) emitStats()
        samplesMs.clear()
        windowStart = null
        frameStart = null
    }

    /** Réinitialise l'état interne (utile en test). */
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
