/**
 * Module kadre-web-common — code partagé entre les cibles JS et wasmJs.
 *
 * Cibles KMP : js(IR) + wasmJs, avec un source set intermédiaire webMain
 * qui dépend de commonMain et regroupe le code commun aux deux backends web.
 *
 * Contrainte : webMain ne doit contenir AUCUN import DOM.
 * Les imports kotlinx.browser et org.w3c.dom.* sont réservés à jsMain.
 * Les interops JS Wasm sont réservées à wasmJsMain.
 *
 * setup initial du module.
 */
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl

plugins {
    id("org.jetbrains.kotlin.multiplatform")
}

kotlin {
    // Le template de hiérarchie KMP 2.x crée automatiquement webMain et webTest
    // quand js + wasmJs sont déclarés ensemble, sans dependsOn() explicites.
    applyDefaultHierarchyTemplate()

    js(IR) { browser() }
    @OptIn(ExperimentalWasmDsl::class)
    wasmJs { browser() }

    sourceSets {
        // webMain et webTest sont créés automatiquement par applyDefaultHierarchyTemplate()
        // quand js + wasmJs sont déclarés ensemble. On les récupère avec `by getting`.

        // Depuis le ticket #28, kadre-core expose les cibles js(IR) et wasmJs.
        // On peut donc en dépendre depuis commonMain (hérité par webMain, jsMain, wasmJsMain).
        commonMain {
            dependencies {
                api(project(":kadre-core"))
            }
        }

        jsTest {
            dependencies {
                implementation(kotlin("test-js"))
            }
        }
        wasmJsTest {
            dependencies {
                implementation(kotlin("test-wasm-js"))
            }
        }
    }
}
