/**
 * Module kadre-wasm — façade kadre pour la cible Kotlin/Wasm (wasmJs).
 *
 * Expose l'API kadre aux consommateurs WebAssembly via la cible wasmJs.
 * Délègue vers kadre-web-common pour l'implémentation partagée web (JS + wasmJs).
 *
 * Cible KMP : wasmJs + browser uniquement.
 *
 * GRA-32 : setup initial du module kadre-wasm.
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
