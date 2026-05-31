/**
 * Sample hello-touch — démontre le pipeline touch events Kadre.
 *
 * Cibles KMP : androidTarget + iOS (iosArm64, iosSimulatorArm64).
 * Le handler [HelloTouchHandler] est défini en commonMain et partagé
 * entre la cible Android (consommée par hello-touch-android) et iOS.
 */
plugins {
    id("ygdrasil.conventions.kmp-library")
}

android {
    namespace = "org.graphiks.kadre.samples.hellotouch"
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(project(":kadre"))
            }
        }
    }
}
