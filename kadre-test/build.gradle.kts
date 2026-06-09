/**
 * Module kadre-test — test utilities for Kadre.
 *
 * Provides [ScriptedEventLoop] and the `scriptedTest { ... }` DSL driving an
 * [org.graphiks.kadre.core.ApplicationHandler] with a deterministic event
 * sequence, without a native backend (NSApp, Activity, etc.).
 *
 * Unpublished module: reusable test helper in commonTest for samples and
 * backend modules. Same targets as kadre-core for full portability.
 */
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl

plugins {
    id("ygdrasil.conventions.kmp-library")
}

android {
    namespace = "org.graphiks.kadre.test"
}

kotlin {
    js { browser() }
    @OptIn(ExperimentalWasmDsl::class)
    wasmJs { browser() }

    sourceSets {
        commonMain.dependencies {
            api(project(":kadre-core"))
        }
        commonTest.dependencies {
            implementation(kotlin("test"))
        }
        jsTest.dependencies {
            implementation(kotlin("test-js"))
        }
        wasmJsTest.dependencies {
            implementation(kotlin("test-wasm-js"))
        }
    }
}
