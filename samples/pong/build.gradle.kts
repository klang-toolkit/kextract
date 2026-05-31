/**
 * Module samples/pong — squelette KMP 6 cibles.
 *
 * Cibles : jvm, androidTarget, iosX64, iosArm64, iosSimulatorArm64, js(IR), wasmJs.
 * setup module (skeleton uniquement, pas d'implémentation).
 *
 * Tickets liés :
 *   #74 : GameState — commonMain
 *   #79 : PongGame — commonMain
 *   #80 : entry points par plateforme
 */
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl

plugins {
    id("ygdrasil.conventions.kmp-library")
}

android {
    namespace = "org.graphiks.kadre.samples.pong"
}

kotlin {
    // Cibles web — en plus des cibles iOS/JVM/Android du plugin de convention.
    // binaries.executable() : génère un bundle exécutable (webpack) pour servir
    // le sample en navigateur via jsBrowserDevelopmentRun / wasmJsBrowserDevelopmentRun.
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
                // wgpu4k — rendu WebGPU JVM (PongRenderer)
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
// Usage : ./gradlew :samples:pong:run
tasks.register<JavaExec>("run") {
    group = "application"
    description = "Runs Pong on JVM (macOS AppKit + wgpu4k)"
    dependsOn("jvmJar")
    mainClass.set("org.graphiks.kadre.samples.pong.MainKt")
    classpath = files(
        kotlin.targets.getByName("jvm").compilations.getByName("main").output.allOutputs,
        configurations.getByName("jvmRuntimeClasspath"),
    )
    // -XstartOnFirstThread est un flag JVM macOS-only — la JVM Windows/Linux le rejette.
    jvmArgs(buildList {
        if (org.gradle.internal.os.OperatingSystem.current().isMacOsX) {
            add("-XstartOnFirstThread")
        }
        add("--enable-native-access=ALL-UNNAMED")
    })
}
