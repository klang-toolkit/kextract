/**
 * Sample hello-triangle-android-capture — Android offscreen GPU capture.
 *
 * Renders the wgpu4k triangle (Vulkan) into an offscreen texture via a Surface
 * (SurfaceTexture), reads back the framebuffer and verifies the triangle. Run as an
 * instrumented test on emulator (software Vulkan SwiftShader).
 */
plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "org.graphiks.kadre.samples.androidcapture"
    compileSdk = 35
    defaultConfig {
        minSdk = 26
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        // TestStorage: lets the test write output files (the PNG capture)
        // that the runner automatically pulls back to the host (scoped storage bypassed).
        testInstrumentationRunnerArguments["useTestStorageService"] = "true"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

kotlin {
    jvmToolchain(17)
}

dependencies {
    implementation("io.ygdrasil:wgpu4k-toolkit:0.1.1")
    // android-native-helper : Helper.nativeWindowFromSurface (ANativeWindow via JNI).
    // Transitive in runtime scope via the toolkit → declared in compile.
    implementation("io.ygdrasil:android-native-helper:0.0.1")
    implementation(libs.webgpu.ktypes.descriptors)
    implementation(libs.kotlinx.coroutines.core)
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test:runner:1.6.2")
    // TestStorage: output-writing API + service pulled back by the runner.
    androidTestImplementation("androidx.test.services:storage:1.5.0")
    androidTestUtil("androidx.test.services:test-services:1.5.0")
}
