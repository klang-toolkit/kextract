/**
 * Sample hello-metal — démonstrateur POC Metal view minimale (Jalon M1).
 *
 * Ce module illustre l'utilisation de kadre pour ouvrir une fenêtre macOS
 * native avec un contentView layer-backed (CAMetalLayer), prête pour un
 * renderer Metal ou wgpu4k.
 *
 * Cible : jvm (JDK 25, macOS Desktop uniquement).
 * Dépendance : kadre (façade) qui embarque kadre-appkit en jvmMain.
 *
 * Note : ce module utilise le plugin Kotlin/JVM (non KMP) car il est jvm-only
 * et doit pouvoir déclarer un point d'entrée d'application standard.
 */
plugins {
    kotlin("jvm")
    application
}

kotlin {
    jvmToolchain(25)
}

application {
    mainClass.set("org.graphiks.kadre.samples.hellometal.MainKt")

    // macOS exige que NSApplication tourne sur le thread principal (AppKit).
    // -XstartOnFirstThread garantit que le thread JVM principal = thread macOS principal.
    // C'est un flag JVM macOS-only — la JVM Windows/Linux le rejette.
    //
    // --enable-native-access supprime les avertissements Panama FFM (JDK 22+).
    applicationDefaultJvmArgs = buildList {
        if (org.gradle.internal.os.OperatingSystem.current().isMacOsX) {
            add("-XstartOnFirstThread")
        }
        add("--enable-native-access=ALL-UNNAMED")
    }
}

dependencies {
    implementation(project(":kadre"))
}
