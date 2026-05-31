/**
 * Module samples/pong — 6-target KMP skeleton.
 *
 * Targets: jvm, androidTarget, iosX64, iosArm64, iosSimulatorArm64, js(IR), wasmJs.
 * setup module (skeleton only, no implementation).
 *
 * Related tickets:
 *   #74: GameState — commonMain
 *   #79: PongGame — commonMain
 *   #80: per-platform entry points
 */
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl

plugins {
    id("ygdrasil.conventions.kmp-library")
}

android {
    namespace = "org.graphiks.kadre.samples.pong"
}

kotlin {
    // Web targets — in addition to the iOS/JVM/Android targets from the convention plugin.
    // binaries.executable(): generates an executable bundle (webpack) to serve
    // the sample in a browser via jsBrowserDevelopmentRun / wasmJsBrowserDevelopmentRun.
    js(IR) {
        browser {
            commonWebpackConfig {
                outputFileName = "pong-web.js"
            }
        }
        binaries.executable()
    }
    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        browser {
            commonWebpackConfig {
                outputFileName = "pong-wasm.js"
            }
        }
        binaries.executable()
    }

    sourceSets {
        commonMain {
            dependencies {
                api(project(":kadre"))
            }
        }
        commonTest {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        jvmMain {
            dependencies {
                // wgpu4k — JVM WebGPU rendering (PongRenderer)
                implementation(libs.wgpu4k)
                implementation(libs.webgpu.ktypes.descriptors)
                implementation(libs.kotlinx.coroutines.core)
                // kadre-appkit — ObjCRuntime (macOS CAMetalLayer)
                implementation(project(":kadre-appkit"))
            }
        }
        jsMain {
            dependencies {
                // wgpu4k Web (CanvasSurface, requestAdapter suspend) + facade kadre JS
                implementation(libs.wgpu4k)
                implementation(libs.webgpu.ktypes.descriptors)
                implementation(libs.kotlinx.coroutines.core)
                implementation(project(":kadre-js"))
            }
        }
        wasmJsMain {
            dependencies {
                implementation(libs.wgpu4k)
                implementation(libs.webgpu.ktypes.descriptors)
                implementation(libs.kotlinx.coroutines.core)
                implementation(project(":kadre-wasm"))
            }
        }
    }
}

// JVM run task — uses -XstartOnFirstThread (required for AppKit on macOS)
// Usage: ./gradlew :samples:pong:run
tasks.register<JavaExec>("run") {
    group = "application"
    description = "Runs Pong on JVM (macOS AppKit + wgpu4k)"
    dependsOn("jvmJar")
    mainClass.set("org.graphiks.kadre.samples.pong.MainKt")
    classpath = files(
        kotlin.targets.getByName("jvm").compilations.getByName("main").output.allOutputs,
        configurations.getByName("jvmRuntimeClasspath"),
    )
    // -XstartOnFirstThread is a macOS-only JVM flag — the Windows/Linux JVM rejects it.
    jvmArgs(buildList {
        if (org.gradle.internal.os.OperatingSystem.current().isMacOsX) {
            add("-XstartOnFirstThread")
        }
        add("--enable-native-access=ALL-UNNAMED")
    })
}
