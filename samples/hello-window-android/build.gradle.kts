/**
 * Sample hello-window-android — Kadre window on Android.
 *
 * Android mirror of the hello-window sample (JVM/iOS).
 * Demonstrates Kadre API convergence: same HelloApp, same behavior
 * on all three platforms.
 *
 * Usage: ./gradlew :samples:hello-window-android:assembleDebug
 */
plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "org.graphiks.kadre.samples.hellowindowandroid"
    compileSdk = 35
    defaultConfig {
        applicationId = "org.graphiks.kadre.samples.hellowindowandroid"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlin {
        jvmToolchain(17)
    }
}

dependencies {
    implementation(project(":kadre"))
    implementation("androidx.activity:activity:1.10.1")
}
