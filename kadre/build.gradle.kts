/**
 * Module kadre — façade publique KMP.
 *
 * Ce module expose l'API Kadre aux consommateurs finaux via le mécanisme
 * expect/actual. Il délègue vers les backends spécifiques :
 *   - jvmMain     → kadre-appkit (macOS Desktop via FFM/kextract)
 *   - iosMain     → kadre-uikit (M3, hors scope M1)
 *   - androidMain → kadre-android (M3, hors scope M1)
 *   - jsMain      → kadre-web-common (stub — impl complète dans #24)
 *   - wasmJsMain  → kadre-web-common (stub — impl complète dans #24)
 *
 * Cibles KMP : jvm, androidTarget, iosX64, iosArm64, iosSimulatorArm64, js(IR), wasmJs.
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
    // Cibles web — en plus des cibles iOS/JVM/Android du plugin de convention
    js(IR) { browser() }
    @OptIn(ExperimentalWasmDsl::class)
    wasmJs { browser() }

    // Validation de compatibilité ABI — intégrée au plugin Kotlin.
    @OptIn(org.jetbrains.kotlin.gradle.dsl.abi.ExperimentalAbiValidation::class)
    abiValidation { enabled.set(true) }

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
