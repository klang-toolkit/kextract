/**
 * Module kadre-core — interfaces et types Kotlin pur, aucune référence native.
 *
 * Cibles KMP : jvm, androidTarget, iosX64, iosArm64, iosSimulatorArm64, js(IR), wasmJs.
 * Ce module ne doit contenir aucun code dépendant d'une plateforme (pas de
 * java.*, platform.*, android.*) afin de rester 100 % commonMain.
 *
 * Les cibles web (js, wasmJs) ont été ajoutées dans le ticket #28 pour permettre
 * à la façade `kadre` d'exposer EventLoop aux cibles navigateur.
 */
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl

plugins {
    id("ygdrasil.conventions.kmp-library")
    id("ygdrasil.conventions.kmp-dokka")
    id("ygdrasil.conventions.kmp-publish")
}

android {
    namespace = "org.graphiks.kadre.core"
}

kotlin {
    // Cibles web — en plus des cibles iOS/JVM/Android du plugin de convention
    js(IR) { browser() }
    @OptIn(ExperimentalWasmDsl::class)
    wasmJs { browser() }

    // Validation de compatibilité ABI (Redmine #86) — intégrée au plugin Kotlin,
    // s'appuie sur le compilateur (pas d'ASM externe) → compatible JDK 25.
    // Tâches : updateKotlinAbi (régénère api/) et checkKotlinAbi (câblée dans check).
    @OptIn(org.jetbrains.kotlin.gradle.dsl.abi.ExperimentalAbiValidation::class)
    abiValidation { enabled.set(true) }

    // Active les classes expect/actual (Beta) sans avertissement.
    // Nécessaire pour EventLoop (expect class avec actual par plateforme).
    compilerOptions {
        freeCompilerArgs.add("-Xexpect-actual-classes")
    }

    sourceSets {
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
