/**
 * Sample hello-window — cross-platform native window.
 *
 * Demonstrates the basic Kadre API: window creation, handling of
 * keyboard/mouse/lifecycle events from a common handler.
 *
 * Targets: JVM (macOS via AppKit), iOS.
 * The Android target is in the companion module :samples:hello-window-android.
 *
 * JVM usage: ./gradlew :samples:hello-window:run
 */
plugins {
    id("org.jetbrains.kotlin.multiplatform")
}

kotlin {
    jvm()
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        commonMain {
            dependencies {
                implementation(project(":kadre"))
            }
        }
        jvmMain {
            dependencies {
                // Kadre platform backends. The :kadre facade selects one by reflection at
                // runtime (AppKit on macOS, Win32 on Windows, X11/Wayland on Linux), so every
                // desktop backend must be on the classpath for the sample to open a window on
                // each OS. Inert on the other platforms.
                implementation(project(":kadre-win32"))
                implementation(project(":kadre-x11"))
                implementation(project(":kadre-wayland"))
            }
        }
    }
}

// JVM run task — uses -XstartOnFirstThread (required for AppKit on macOS)
tasks.register<JavaExec>("run") {
    group = "application"
    description = "Runs hello-window on JVM (macOS AppKit)"
    dependsOn("jvmJar")
    mainClass.set("org.graphiks.kadre.samples.hellowindow.MainKt")
    classpath = files(
        kotlin.targets.getByName("jvm").compilations.getByName("main").output.allOutputs,
        configurations.getByName("jvmRuntimeClasspath"),
    )
    // -XstartOnFirstThread is a macOS-only JVM flag — the Windows/Linux JVM rejects it.
    jvmArgs(buildList {
        if (org.gradle.internal.os.OperatingSystem.current().isMacOsX) {
            add("-XstartOnFirstThread")
        }
        add("--enable-native-access=ALL-UNNAMED")
    })
}
