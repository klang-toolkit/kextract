/**
 * Benchmarks JMH sur les composants purs de Pong et du moteur.
 *
 * Mesure le temps moyen par opération (nanosecondes) des chemins chauds appelés
 * à chaque frame : tick physique, IA, rendu de texte bitmap, adaptateur d'entrée.
 */
package org.graphiks.kadre.bench

import org.graphiks.kadre.core.Key
import org.graphiks.kadre.core.KeyState
import org.graphiks.kadre.core.Modifiers
import org.graphiks.kadre.core.WindowEvent
import org.graphiks.kadre.samples.pong.BitmapFont
import org.graphiks.kadre.samples.pong.GameState
import org.graphiks.kadre.samples.pong.InputAdapter
import org.graphiks.kadre.samples.pong.PaddleInput
import org.graphiks.kadre.samples.pong.PongAi
import org.graphiks.kadre.samples.pong.tick
import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.annotations.BenchmarkMode
import org.openjdk.jmh.annotations.Mode
import org.openjdk.jmh.annotations.OutputTimeUnit
import org.openjdk.jmh.annotations.Scope
import org.openjdk.jmh.annotations.State
import org.openjdk.jmh.infra.Blackhole
import java.util.concurrent.TimeUnit

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
open class PongBenchmarks {

    private val initial = GameState.INITIAL
    private val ai = PongAi()
    private val keyDown = WindowEvent.KeyboardInput(null, Key.ArrowUp, KeyState.Pressed, Modifiers.NONE)
    private val keyUp = WindowEvent.KeyboardInput(null, Key.ArrowUp, KeyState.Released, Modifiers.NONE)

    // ── Tick physique ────────────────────────────────────────────────────────

    @Benchmark
    fun tickPlayerUp(): GameState =
        initial.tick(dt = 0.016, playerInput = PaddleInput.UP, aiInput = PaddleInput.NONE)

    @Benchmark
    fun tickIdle(): GameState =
        initial.tick(dt = 0.016, playerInput = PaddleInput.NONE, aiInput = PaddleInput.NONE)

    @Benchmark
    fun tickBothMoving(): GameState =
        initial.tick(dt = 0.016, playerInput = PaddleInput.UP, aiInput = PaddleInput.DOWN)

    @Benchmark
    fun tick64Frames(): GameState {
        var s = initial
        repeat(64) { s = s.tick(0.016, PaddleInput.UP, PaddleInput.DOWN) }
        return s
    }

    // ── IA ────────────────────────────────────────────────────────────────────

    @Benchmark
    fun aiSuggestUpdate(): PaddleInput = ai.suggest(initial, currentTimeMs = 1_000L)

    @Benchmark
    fun aiSuggestNoUpdate(): PaddleInput = ai.suggest(initial, currentTimeMs = 0L)

    // ── Rendu BitmapFont ───────────────────────────────────────────────────────

    @Benchmark
    fun renderDigit(bh: Blackhole) {
        bh.consume(BitmapFont.renderDigit(7, x = 0.0, y = 0.0, pixelSize = 0.05))
    }

    @Benchmark
    fun renderNumberTwoDigits(bh: Blackhole) {
        bh.consume(BitmapFont.renderNumber(42, x = 0.0, y = 0.0, pixelSize = 0.05))
    }

    @Benchmark
    fun renderNumberFiveDigits(bh: Blackhole) {
        bh.consume(BitmapFont.renderNumber(98765, x = 0.0, y = 0.0, pixelSize = 0.05))
    }

    // ── Adaptateur d'entrée ─────────────────────────────────────────────────────

    @Benchmark
    fun inputOnKeyPress(): PaddleInput {
        val adapter = InputAdapter()
        adapter.onKey(keyDown)
        return adapter.playerInput
    }

    @Benchmark
    fun inputOnKeyPressRelease(): PaddleInput {
        val adapter = InputAdapter()
        adapter.onKey(keyDown)
        adapter.onKey(keyUp)
        return adapter.playerInput
    }
}
