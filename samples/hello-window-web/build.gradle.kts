/**
 * Sample hello-window-web — fenêtre canvas web cross-cible (JS + Wasm).
 *
 * Démontre l'API Kadre de base pour les cibles navigateur :
 * création de fenêtre canvas, journalisation des événements DOM.
 *
 * Cibles : js(IR) browser, wasmJs browser.
 *
 * setup module samples/hello-window-web.
 */
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl

plugins {
    id("org.jetbrains.kotlin.multiplatform")
}

kotlin {
    js(IR) {
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
