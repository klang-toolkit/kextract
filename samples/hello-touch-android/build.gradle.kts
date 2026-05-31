/**
 * Sample hello-touch-android — Android touch events demonstration application.
 *
 * Consumes [org.graphiks.kadre.samples.hellotouch.HelloTouchHandler] from
 * `:samples:hello-touch` (commonMain KMP), demonstrating that the handler is
 * 100% common Kotlin shared between iOS and Android.
 */
plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "org.graphiks.kadre.samples.hellotouchandroid"
    compileSdk = 35
    defaultConfig {
        applicationId = "org.graphiks.kadre.samples.hellotouchandroid"
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
    implementation(project(":samples:hello-touch"))
    implementation("androidx.activity:activity:1.10.1")
}
