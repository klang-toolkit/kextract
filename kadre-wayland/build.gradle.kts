/**
 * Module kadre-wayland — backend Linux Wayland via FFM JVM 25.
 *
 * Target: jvm only.
 * This module implements the kadre-core interfaces for Wayland
 * using the Foreign Function & Memory API (JEP 454).
 *
 * FFM bindings are loaded lazily so the build
 * passes on macOS/Windows without libwayland-client.so.0 installed.
 *
 * Upstream dependency: kadre-core (jvm sourceSet only, via jvmMain).
 */
plugins {
    id("org.jetbrains.kotlin.multiplatform")
}

kotlin {
    jvmToolchain(25)

    jvm()

    sourceSets {
        jvmMain {
            dependencies {
                api(project(":kadre-core"))
                api(project(":ffi:wayland"))
            }
        }
        jvmTest {
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }
}
