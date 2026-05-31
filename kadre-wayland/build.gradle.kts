/**
 * Module kadre-wayland — backend Linux Wayland via FFM JVM 25.
 *
 * Cible : jvm uniquement.
 * Ce module implémente les interfaces de kadre-core pour Wayland
 * en utilisant la Foreign Function & Memory API (JEP 454).
 *
 * Les bindings FFM sont chargés paresseusement (lazy) pour que le build
 * passe sur macOS/Windows sans libwayland-client.so.0 installé.
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
