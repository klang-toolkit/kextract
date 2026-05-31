/**
 * Module kadre-win32 — backend Windows Desktop via FFM JVM 25.
 *
 * Cible : jvm uniquement.
 * Ce module implémente les interfaces de kadre-core pour Win32 (HWND,
 * CreateWindowEx, MSG loop, etc.) en utilisant la Foreign Function &
 * Memory API (JEP 454).
 *
 * GRA-12 : DPI awareness PerMonitorV2 + bindings Win32 lazy FFM.
 * GRA-5  : événements souris complets (WM_XBUTTON, WM_MOUSELEAVE, etc.)
 *
 * Dépendance amont : kadre-core (uniquement la sourceSet jvm via jvmMain).
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
