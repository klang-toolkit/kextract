/**
 * Module kadre-web-common — code shared between JS and wasmJs targets.
 *
 * KMP targets: js + wasmJs, with an intermediate webMain source set
 * that depends on commonMain and groups code common to both web backends.
 *
 * Constraint: webMain must NOT contain any DOM imports.
 * kotlinx.browser and org.w3c.dom.* imports are reserved for jsMain.
 * Wasm JS interops are reserved for wasmJsMain.
 *
 * Initial module setup.
 */
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl

plugins {
    id("org.jetbrains.kotlin.multiplatform")
}

kotlin {
    // The KMP 2.x hierarchy template automatically creates webMain and webTest
    // when js + wasmJs are declared together, without explicit dependsOn().
    applyDefaultHierarchyTemplate()

    js { browser() }
    @OptIn(ExperimentalWasmDsl::class)
    wasmJs { browser() }

    sourceSets {
        // webMain and webTest are automatically created by applyDefaultHierarchyTemplate()
        // when js + wasmJs are declared together. We retrieve them with `by getting`.

        // Since ticket #28, kadre-core exposes the js and wasmJs targets.
        // We can therefore depend on it from commonMain (inherited by webMain, jsMain, wasmJsMain).
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
