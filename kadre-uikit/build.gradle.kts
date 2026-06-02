/**
 * Module kadre-uikit — backend iOS via Kotlin/Native + cinterop implicites.
 *
 * Cibles KMP : iosX64, iosArm64, iosSimulatorArm64.
 * Les frameworks Apple (UIKit, Foundation, QuartzCore, CoreGraphics) sont
 * disponibles via les cinterops built-in de K/N — aucun fichier .def requis.
 *
 * GRA-141 : setup initial du module.
 */
plugins {
    id("org.jetbrains.kotlin.multiplatform")
    id("ygdrasil.conventions.kmp-publish")
}

kotlin {
    // Validation de compatibilité ABI — intégrée au plugin Kotlin.
    @OptIn(org.jetbrains.kotlin.gradle.dsl.abi.ExperimentalAbiValidation::class)
    abiValidation { enabled.set(true) }

    // Cibles iOS
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    // La hiérarchie par défaut KMP 2.x crée automatiquement :
    //   commonMain → appleMain → iosMain → iosArm64Main / iosX64Main / iosSimulatorArm64Main

    sourceSets {
        commonMain {
            dependencies {
                api(project(":kadre-core"))
            }
        }
        commonTest {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        // iosMain est auto-créé par la hiérarchie KMP — pas besoin de le déclarer
        // sauf pour ajouter des dépendances spécifiques iOS
    }
}
