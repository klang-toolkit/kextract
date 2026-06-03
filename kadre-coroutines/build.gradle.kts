/**
 * Module kadre-coroutines — coroutine-friendly layer over the Kadre event loop.
 *
 * Provides a main-thread CoroutineDispatcher backed by the Kadre loop (with Delay mapped to
 * ControlFlow.WaitUntil), an `application { }` builder, and window events exposed as a Flow.
 * No Compose / rendering dependency.
 *
 * Target: jvm (the desktop backends are JVM-only today). The public API is platform-neutral
 * and could be hoisted to commonMain with per-platform actuals (time + thread-safe queue) later.
 */
plugins {
    id("org.jetbrains.kotlin.multiplatform")
    id("ygdrasil.conventions.kmp-publish")
}

kotlin {
    jvmToolchain(25)

    @OptIn(org.jetbrains.kotlin.gradle.dsl.abi.ExperimentalAbiValidation::class)
    abiValidation()

    jvm()

    sourceSets {
        jvmMain {
            dependencies {
                api(project(":kadre"))
                api(libs.kotlinx.coroutines.core)
            }
        }
        jvmTest {
            dependencies {
                implementation(kotlin("test"))
                implementation(libs.kotlinx.coroutines.core)
            }
        }
    }
}
