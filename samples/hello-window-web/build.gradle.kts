/**
 * Sample hello-window-web — cross-target web canvas window (JS + Wasm).
 *
 * Demonstrates the basic Kadre API for browser targets:
 * canvas window creation, logging of DOM events.
 *
 * Targets: js browser, wasmJs browser.
 *
 * setup module samples/hello-window-web.
 */
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl

plugins {
    id("org.jetbrains.kotlin.multiplatform")
}

kotlin {
    js {
        browser {
            commonWebpackConfig {
                outputFileName = "hello-window-web.js"
            }
        }
        binaries.executable()
    }

    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        browser {
            commonWebpackConfig {
                outputFileName = "hello-window-wasm.js"
            }
        }
        binaries.executable()
    }

    sourceSets {
        commonMain {
            dependencies {
                implementation(project(":kadre"))
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
