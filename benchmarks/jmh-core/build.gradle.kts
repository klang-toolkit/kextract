/**
 * Module benchmarks/jmh-core — suite JMH sur les composants purs de Kadre.
 *
 * JVM-only. Mesure les composants commonMain critiques : tick physique du jeu,
 * rendu BitmapFont, IA, adaptateur d'entrée. Lancer : `./gradlew :benchmarks:jmh-core:jmh`.
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
    // Profil rapide (CI ~2-3 min) : itérations courtes. Pour des mesures fines en
    // local, augmenter warmup / timeOnIteration / iterations.
    warmupIterations.set(2)
    iterations.set(3)
    warmup.set("1s")
    timeOnIteration.set("1s")
    fork.set(1)
    resultFormat.set("JSON")
}
