/**
 * Sample hello-metal — minimal Metal view POC demonstrator (Milestone M1).
 *
 * This module illustrates using kadre to open a native macOS window
 * with a layer-backed contentView (CAMetalLayer), ready for a
 * Metal or wgpu4k renderer.
 *
 * Target: jvm (JDK 25, macOS Desktop only).
 * Dependency: kadre (facade) which embeds kadre-appkit in jvmMain.
 *
 * Note: this module uses the Kotlin/JVM plugin (not KMP) because it is jvm-only
 * and must be able to declare a standard application entry point.
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

    // macOS requires NSApplication to run on the main thread (AppKit).
    // -XstartOnFirstThread guarantees that the main JVM thread = main macOS thread.
    // This is a macOS-only JVM flag — the Windows/Linux JVM rejects it.
    //
    // --enable-native-access suppresses Panama FFM warnings (JDK 22+).
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
