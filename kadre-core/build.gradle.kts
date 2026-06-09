/**
 * Module kadre-core — pure Kotlin interfaces and types, no native references.
 *
 * KMP targets: jvm, androidTarget, iosX64, iosArm64, iosSimulatorArm64, js, wasmJs.
 * This module must not contain any platform-dependent code (no java.*,
 * platform.*, android.*) to remain 100% commonMain.
 *
 * Web targets (js, wasmJs) were added in ticket #28 to allow the `kadre`
 * facade to expose EventLoop to browser targets.
 */
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl

plugins {
    id("ygdrasil.conventions.kmp-library")
    id("ygdrasil.conventions.kmp-dokka")
    id("ygdrasil.conventions.kmp-publish")
}

android {
    namespace = "org.graphiks.kadre.core"
}

kotlin {
    // Web targets — in addition to the iOS/JVM/Android targets from the convention plugin
    js { browser() }
    @OptIn(ExperimentalWasmDsl::class)
    wasmJs { browser() }

    // ABI compatibility validation — integrated into the Kotlin plugin,
    // relies on the compiler (no external ASM) → compatible with JDK 25.
    // Tasks: updateKotlinAbi (regenerates api/) and checkKotlinAbi (wired into check).
    @OptIn(org.jetbrains.kotlin.gradle.dsl.abi.ExperimentalAbiValidation::class)
    abiValidation()

    // Enables expect/actual classes (Beta) without warning.
    // Required for EventLoop (expect class with actual per platform).
    compilerOptions {
        freeCompilerArgs.add("-Xexpect-actual-classes")
    }

    sourceSets {
        commonMain.dependencies {
            api(libs.kotlinx.coroutines.core)
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
