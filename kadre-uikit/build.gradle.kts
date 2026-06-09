/**
 * Module kadre-uikit — iOS backend via Kotlin/Native + implicit cinterop.
 *
 * KMP targets: iosX64, iosArm64, iosSimulatorArm64.
 * Apple frameworks (UIKit, Foundation, QuartzCore, CoreGraphics) are
 * available via K/N built-in cinterops — no .def files required.
 *
 * GRA-141: initial module setup.
 */
plugins {
    id("org.jetbrains.kotlin.multiplatform")
    id("ygdrasil.conventions.kmp-publish")
}

kotlin {
    // ABI compatibility validation — integrated into the Kotlin plugin.
    @OptIn(org.jetbrains.kotlin.gradle.dsl.abi.ExperimentalAbiValidation::class)
    abiValidation()

    // iOS targets
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    // The default KMP 2.x hierarchy automatically creates:
    //   commonMain → appleMain → iosMain → iosArm64Main / iosX64Main / iosSimulatorArm64Main

    sourceSets {
        commonMain {
            dependencies {
                api(project(":kadre-core"))
            }
        }
        commonTest {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        // iosMain is auto-created by the KMP hierarchy — no need to declare it
        // except to add iOS-specific dependencies
    }
}
