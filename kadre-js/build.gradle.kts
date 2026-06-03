/**
 * Module kadre-js — façade kadre pour la cible Kotlin/JS (IR).
 *
 * Expose l'API kadre aux consommateurs JavaScript/TypeScript via la cible js.
 * Délègue vers kadre-web-common pour l'implémentation partagée web (JS + wasmJs).
 *
 * Cible KMP : js + browser uniquement.
 *
 * GRA-30 : setup initial du module kadre-js.
 */
plugins {
    id("org.jetbrains.kotlin.multiplatform")
}

kotlin {
    js { browser() }

    sourceSets {
        jsMain {
            dependencies {
                api(project(":kadre-web-common"))
                api(project(":kadre-core"))
            }
        }
        jsTest {
            dependencies {
                implementation(kotlin("test-js"))
            }
        }
    }
}
