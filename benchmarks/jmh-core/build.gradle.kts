/**
 * Module benchmarks/jmh-core — JMH benchmark suite for pure Kadre components.
 *
 * JVM-only. Benchmarks the critical commonMain components: game physics tick,
 * BitmapFont renderer, AI, input adapter. Run: `./gradlew :benchmarks:jmh-core:jmh`.
 */
plugins {
    kotlin("jvm")
    id("me.champeau.jmh") version "0.7.2"
}

kotlin { jvmToolchain(25) }

dependencies {
    jmh(project(":samples:pong"))
    jmh(project(":kadre-core"))
}

jmh {
    // Fast profile (CI ~2-3 min): short iterations. For fine-grained local
    // measurements, increase warmup / timeOnIteration / iterations.
    warmupIterations.set(2)
    iterations.set(3)
    warmup.set("1s")
    timeOnIteration.set("1s")
    fork.set(1)
    resultFormat.set("JSON")
}
