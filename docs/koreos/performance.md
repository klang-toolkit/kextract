# Performance & Instrumentation

## JMH Benchmarks (`benchmarks/jmh-core`)

A [JMH](https://github.com/openjdk/jmh) suite measuring the pure components called on every frame. JVM-only, run via:

```bash
./gradlew :benchmarks:jmh-core:jmh
```

Results (JSON) are written to `benchmarks/jmh-core/build/results/jmh/results.json`.

### Coverage

| Benchmark | Component |
|-----------|-----------|
| `tickPlayerUp`, `tickIdle`, `tickBothMoving`, `tick64Frames` | `GameState.tick` (2D physics) |
| `aiSuggestUpdate`, `aiSuggestNoUpdate` | `PongAi.suggest` |
| `renderDigit`, `renderNumberTwoDigits`, `renderNumberFiveDigits` | `BitmapFont` |
| `inputOnKeyPress`, `inputOnKeyPressRelease` | `InputAdapter.onKey` |

> The Win32/X11 mappers are `internal` (not accessible from the benchmark module);
> they are covered by their module's unit tests.

### CI

The `bench-perf` job (`.github/workflows/bench-perf.yml`) runs the suite on each push to master and publishes the JSON as an artifact. Automatic comparison against a baseline (regression threshold) is an open point — see `benchmarks/baselines/README.md`.

## FrameTimingTracer (runtime)

`io.ygdrasil.koreos.core.FrameTimingTracer` measures the
`RedrawRequested → end of presentation` duration for each frame and publishes
`min/p50/p99/max` statistics and approximate FPS roughly once per second.

```kotlin
FrameTimingTracer.enabled = true            // disabled by default → 0 overhead
// in the render loop:
FrameTimingTracer.onRedrawStart()
// … render the frame …
FrameTimingTracer.onPresentEnd()
```

- **0% overhead when disabled**: all methods return immediately (no clock reads, no allocations) as long as `enabled == false`.
- Multiplatform clock (`kotlin.time.TimeSource.Monotonic`).
- `slowFrameThresholdMs`: logs individual frames that exceed the threshold.
- `sink`: redirects log lines (default `println`), overridable in tests.

On JVM, activation can be gated on `-Dkoreos.tracing=true` at backend startup (reading the system property → `FrameTimingTracer.enabled = true`).
