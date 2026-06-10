import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl

plugins {
    kotlin("multiplatform")
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.compose.compiler)
}

kotlin {
    js {
        browser { commonWebpackConfig { outputFileName = "compose-showcase.js" } }
        binaries.executable()
    }
    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        browser { commonWebpackConfig { outputFileName = "compose-showcase-wasm.js" } }
        binaries.executable()
    }
    jvmToolchain(25)

    sourceSets {
        commonMain {
            dependencies {
                implementation(project(":samples:compose:shared"))
            }
        }
    }
}
