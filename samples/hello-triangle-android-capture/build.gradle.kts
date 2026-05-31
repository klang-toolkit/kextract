/**
 * Sample hello-triangle-android-capture — capture GPU offscreen Android (Redmine #88).
 *
 * Rend le triangle wgpu4k (Vulkan) dans une texture offscreen via une Surface
 * (SurfaceTexture), relit le framebuffer et vérifie le triangle. Exécuté comme test
 * instrumenté sur émulateur (Vulkan logiciel SwiftShader).
 */
plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "io.ygdrasil.koreos.samples.androidcapture"
    compileSdk = 35
    defaultConfig {
        minSdk = 26
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    // Transitif en scope runtime via le toolkit → déclaré en compile.
    implementation("io.ygdrasil:android-native-helper:0.0.1")
    implementation(libs.webgpu.ktypes.descriptors)
    implementation(libs.kotlinx.coroutines.core)
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test:runner:1.6.2")
}
