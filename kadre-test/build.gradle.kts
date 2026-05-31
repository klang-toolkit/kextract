/**
 * Module kadre-test — utilitaires de test pour Kadre (Redmine #89).
 *
 * Fournit [ScriptedEventLoop] et le DSL `scriptedTest { ... }` permettant de
 * piloter un [org.graphiks.kadre.core.ApplicationHandler] avec une séquence
 * d'événements déterministe, sans backend natif (NSApp, Activity, etc.).
 *
 * Module NON publié : helper de test réutilisable en commonTest par les samples
 * et les modules backend. Mêmes cibles que kadre-core pour une portabilité totale.
 */
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl

plugins {
    id("ygdrasil.conventions.kmp-library")
}

android {
    namespace = "org.graphiks.kadre.test"
}

kotlin {
    js(IR) { browser() }
    @OptIn(ExperimentalWasmDsl::class)
    wasmJs { browser() }

    sourceSets {
        commonMain.dependencies {
            api(project(":kadre-core"))
        }
        commonTest.dependencies {
            implementation(kotlin("test"))
        }
        jsTest.dependencies {
            implementation(kotlin("test-js"))
        }
        wasmJsTest.dependencies {
            implementation(kotlin("test-wasm-js"))
        }
    }
}
