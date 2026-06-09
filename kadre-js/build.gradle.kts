/**
 * Module kadre-js — kadre facade for the Kotlin/JS (IR) target.
 *
 * Exposes the kadre API to JavaScript/TypeScript consumers via the js target.
 * Delegates to kadre-web-common for the shared web implementation (JS + wasmJs).
 *
 * KMP target: js + browser only.
 *
 * GRA-30: initial setup of the kadre-js module.
 */
plugins {
    id("org.jetbrains.kotlin.multiplatform")
}

kotlin {
    js { browser() }

    sourceSets {
        jsMain {
            dependencies {
                api(project(":kadre-web-common"))
                api(project(":kadre-core"))
            }
        }
        jsTest {
            dependencies {
                implementation(kotlin("test-js"))
            }
        }
    }
}
