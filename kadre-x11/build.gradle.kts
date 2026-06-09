/**
 * Module kadre-x11 — backend Linux Desktop via FFM JVM 25.
 *
 * Target: jvm only.
 * This module implements the kadre-core interfaces for X11 (Display,
 * XCreateSimpleWindow, event loop, etc.) using the Foreign
 * Function & Memory API (JEP 454).
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
            }
        }
        jvmTest {
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }
}
