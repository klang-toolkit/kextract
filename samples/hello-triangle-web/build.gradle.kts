/**
 * Sample hello-triangle-web — rendu d'un triangle RGB via wgpu4k Web.
 *
 * Démontre l'intégration wgpu4k côté navigateur :
 *   canvas DOM → [RawWindowHandle.Web] → CanvasSurface → Adapter → Device → Pipeline → render loop.
 *
 * Réutilise le shader WGSL et la séquence de rendu du sample desktop
 * [org.graphiks.kadre.samples.hellotriangle], adaptés à l'API web de wgpu4k
 * (getCanvasSurface / requestAdapter top-level / configure sans Set d'usage).
 *
 * Cibles : js(IR) browser, wasmJs browser, `binaries.executable()`.
 *
 * Le rendu GPU réel n'est vérifiable qu'en navigateur (WebGPU) ; la CI se limite
 * à la compilation des deux cibles.
 */
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl

plugins {
    id("org.jetbrains.kotlin.multiplatform")
}

kotlin {
    js(IR) {
        browser {
            commonWebpackConfig {
                outputFileName = "hello-triangle-web.js"
            }
        }
        binaries.executable()
    }

    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        browser {
            commonWebpackConfig {
                outputFileName = "hello-triangle-wasm.js"
            }
        }
        binaries.executable()
    }

    sourceSets {
        commonMain {
            dependencies {
                implementation(project(":kadre"))
                // wgpu4k publie des variantes web (js / wasmJs) résolues automatiquement
                // selon la cible : voir gradle/libs.versions.toml (wgpu4k = 0.1.1).
                implementation(libs.wgpu4k)
                // Descripteurs partagés (SurfaceConfiguration, RenderPipelineDescriptor, etc.)
                implementation(libs.webgpu.ktypes.descriptors)
                // requestAdapter / requestDevice sont suspend → besoin des coroutines.
                implementation(libs.kotlinx.coroutines.core)
            }
        }
        jsMain {
            dependencies {
                implementation(project(":kadre-js"))
            }
        }
        wasmJsMain {
            dependencies {
                implementation(project(":kadre-wasm"))
            }
        }
    }
}
