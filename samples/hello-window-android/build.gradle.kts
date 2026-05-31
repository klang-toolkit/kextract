/**
 * Sample hello-window-android — fenêtre Kadre sur Android.
 *
 * Miroir Android du sample hello-window (JVM/iOS).
 * Démontre la convergence d'API Kadre : même HelloApp, même comportement
 * sur les trois plateformes.
 *
 * Usage : ./gradlew :samples:hello-window-android:assembleDebug
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
