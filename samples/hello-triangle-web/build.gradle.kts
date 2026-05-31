/**
 * Sample hello-triangle-web — RGB triangle rendering via wgpu4k Web.
 *
 * Demonstrates wgpu4k integration on the browser side:
 *   DOM canvas → [RawWindowHandle.Web] → CanvasSurface → Adapter → Device → Pipeline → render loop.
 *
 * Reuses the WGSL shader and render sequence from the desktop sample
 * [org.graphiks.kadre.samples.hellotriangle], adapted to the wgpu4k web API
 * (getCanvasSurface / top-level requestAdapter / configure without usage Set).
 *
 * Targets: js(IR) browser, wasmJs browser, `binaries.executable()`.
 *
 * Real GPU rendering is only verifiable in a browser (WebGPU); CI is limited
 * to compiling both targets.
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
                // wgpu4k publishes web variants (js / wasmJs) resolved automatically
                // per target: see gradle/libs.versions.toml (wgpu4k = 0.1.1).
                implementation(libs.wgpu4k)
                // Shared descriptors (SurfaceConfiguration, RenderPipelineDescriptor, etc.)
                implementation(libs.webgpu.ktypes.descriptors)
                // requestAdapter / requestDevice are suspend → coroutines needed.
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
