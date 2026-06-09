/**
 * Module kadre-win32 — backend Windows Desktop via FFM JVM 25.
 *
 * Target: jvm only.
 * This module implements the kadre-core interfaces for Win32 (HWND,
 * CreateWindowEx, MSG loop, etc.) using the Foreign Function &
 * Memory API (JEP 454).
 *
 * GRA-12 : DPI awareness PerMonitorV2 + Win32 lazy FFM bindings.
 * GRA-5  : complete mouse events (WM_XBUTTON, WM_MOUSELEAVE, etc.)
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
