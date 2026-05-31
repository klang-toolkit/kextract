/**
 * Sample hello-triangle-ios — capture GPU offscreen iOS.
 *
 * Kotlin/Native iOS : rend le triangle wgpu4k Metal dans une texture offscreen
 * (CAMetalLayer), relit le framebuffer et l'encode en PNG via CoreGraphics. Exécuté
 * comme test iosSimulatorArm64Test (le simulateur fournit Metal).
 */
plugins {
    id("org.jetbrains.kotlin.multiplatform")
}

kotlin {
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        iosMain.dependencies {
            implementation(libs.wgpu4k)
            implementation(libs.webgpu.ktypes.descriptors)
        }
        iosTest.dependencies {
            implementation(kotlin("test"))
        }
    }
}
