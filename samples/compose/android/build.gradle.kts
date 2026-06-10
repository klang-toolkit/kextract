plugins {
    id("com.android.application")
    kotlin("android")
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.compose.multiplatform)
}

android {
    namespace = "org.graphiks.kadre.samples.compose.android"
    compileSdk = 35
    defaultConfig {
        applicationId = "org.graphiks.kadre.samples.compose.android"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        release { isMinifyEnabled = false }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_25
        targetCompatibility = JavaVersion.VERSION_25
    }
    kotlin { jvmToolchain(25) }
}

dependencies {
    implementation(project(":samples:compose:shared"))
    implementation(libs.androidx.activity.compose)
}
