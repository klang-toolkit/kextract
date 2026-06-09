/**
 * Module kadre — public KMP facade.
 *
 * This module exposes the Kadre API to end consumers via the expect/actual
 * mechanism. It delegates to specific backends:
 *   - jvmMain     → kadre-appkit (macOS Desktop via FFM/kextract)
 *   - iosMain     → kadre-uikit (M3, outside M1 scope)
 *   - androidMain → kadre-android (M3, outside M1 scope)
 *   - jsMain      → kadre-web-common (stub — full implementation in #24)
 *   - wasmJsMain  → kadre-web-common (stub — full implementation in #24)
 *
 * Cibles KMP : jvm, androidTarget, iosX64, iosArm64, iosSimulatorArm64, js, wasmJs.
 */
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl

plugins {
    id("ygdrasil.conventions.kmp-library")
    id("ygdrasil.conventions.kmp-dokka")
    id("ygdrasil.conventions.kmp-publish")
}

android {
    namespace = "org.graphiks.kadre"
}

kotlin {
    // Web targets — in addition to iOS/JVM/Android targets from the convention plugin
    js { browser() }
    @OptIn(ExperimentalWasmDsl::class)
    wasmJs { browser() }

    // ABI compatibility validation — integrated into the Kotlin plugin.
    @OptIn(org.jetbrains.kotlin.gradle.dsl.abi.ExperimentalAbiValidation::class)
    abiValidation()

    compilerOptions {
        freeCompilerArgs.add("-Xexpect-actual-classes")
    }

    sourceSets {
        commonMain {
            dependencies {
                api(project(":kadre-core"))
            }
        }

        androidMain {
            dependencies {
                api(project(":kadre-android"))
            }
        }

        jvmMain {
            dependencies {
                api(project(":kadre-appkit"))
            }
        }

        iosArm64Main {
            dependencies {
                api(project(":kadre-uikit"))
            }
        }

        iosSimulatorArm64Main {
            dependencies {
                api(project(":kadre-uikit"))
            }
        }

        iosX64Main {
            dependencies {
                api(project(":kadre-uikit"))
            }
        }

        jsMain {
            dependencies {
                api(project(":kadre-web-common"))
            }
        }

        wasmJsMain {
            dependencies {
                api(project(":kadre-web-common"))
            }
        }

        jvmTest {
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }
}
