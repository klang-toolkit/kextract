package ygdrasil.conventions

plugins {
    id("org.jetbrains.kotlin.multiplatform")
    id("com.android.library")
}

kotlin {
    jvmToolchain(25)
    
    androidTarget()
    
    jvm() // Target for Desktop (JVM)
    
    // iOS targets
    iosX64()
    iosArm64()
    iosSimulatorArm64()
}

android {
    compileSdk = 35
    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_25
        targetCompatibility = JavaVersion.VERSION_25
    }
}
