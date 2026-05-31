/**
 * Sample hello-triangle-ios — iOS offscreen GPU capture.
 *
 * Kotlin/Native iOS: renders the wgpu4k Metal triangle into an offscreen texture
 * (CAMetalLayer), reads back the framebuffer and encodes it as PNG via CoreGraphics. Run
 * as an iosSimulatorArm64Test test (the simulator provides Metal).
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
