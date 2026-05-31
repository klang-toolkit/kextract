/**
 * Module kadre-x11 — backend Linux Desktop via FFM JVM 25.
 *
 * Cible : jvm uniquement.
 * Ce module implémente les interfaces de kadre-core pour X11 (Display,
 * XCreateSimpleWindow, boucle d'événements, etc.) en utilisant la Foreign
 * Function & Memory API (JEP 454).
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
