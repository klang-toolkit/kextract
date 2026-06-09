/**
 * Module kadre-wasm — kadre facade for the Kotlin/Wasm (wasmJs) target.
 *
 * Exposes the kadre API to WebAssembly consumers via the wasmJs target.
 * Delegates to kadre-web-common for the shared web implementation (JS + wasmJs).
 *
 * KMP target: wasmJs + browser only.
 *
 * GRA-32: initial setup of the kadre-wasm module.
 */
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl

plugins {
    id("org.jetbrains.kotlin.multiplatform")
}

kotlin {
    @OptIn(ExperimentalWasmDsl::class)
    wasmJs { browser() }

    sourceSets {
        wasmJsMain {
            dependencies {
                api(project(":kadre-web-common"))
                api(project(":kadre-core"))
            }
        }
        wasmJsTest {
            dependencies {
                implementation(kotlin("test-wasm-js"))
            }
        }
    }
}
