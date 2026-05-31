/**
 * Module kadre-android — backend Android pour kadre.
 *
 * Stratégie A : expose android.view.Surface brute, pas de JNI custom.
 * Cibles : androidTarget uniquement (pas de jvm ni iOS dans ce module).
 *
 * Utilise kmp-library (qui ajoute jvm + iOS via convention) mais seule la
 * cible androidTarget a du code source. Les publications iOS/JVM sont désactivées
 * pour éviter les erreurs de klib manquant lors de publishToMavenLocal.
 *
 * GRA-147 : setup module Android.
 * GRA-159 : publication Maven Central.
 */
plugins {
    id("ygdrasil.conventions.kmp-library")
    id("ygdrasil.conventions.kmp-publish")
}

afterEvaluate {
    // kadre-android is Android-only. kmp-library adds iOS/JVM targets but they
    // have no source, so their klibrary files are never generated and Maven
    // publication would fail. Disable those publication/metadata tasks.
    tasks.withType<AbstractPublishToMaven>().configureEach {
        val pubName = publication?.name ?: ""
        if (pubName.startsWith("ios") || pubName == "jvm") {
            enabled = false
        }
    }
    tasks.withType<GenerateModuleMetadata>().configureEach {
        val pubName = publication.orNull?.name ?: ""
        if (pubName.startsWith("ios") || pubName == "jvm") {
            enabled = false
        }
    }
}

android {
    namespace = "org.graphiks.kadre.android"
}

kotlin {
    // Validation de compatibilité ABI (Redmine #86) — intégrée au plugin Kotlin.
    @OptIn(org.jetbrains.kotlin.gradle.dsl.abi.ExperimentalAbiValidation::class)
    abiValidation { enabled.set(true) }

    sourceSets {
        commonMain {
            dependencies {
                api(project(":kadre-core"))
            }
        }
        androidMain {
            dependencies {
                implementation("androidx.activity:activity:1.10.1")
            }
        }
        val androidUnitTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }
}
