/**
 * Sample hello-window — fenêtre native cross-platform.
 *
 * Démontre l'API Kadre de base : création de fenêtre, gestion des
 * événements clavier/souris/cycle de vie depuis un handler commun.
 *
 * Cibles : JVM (macOS via AppKit), iOS.
 * La cible Android est dans le module companion :samples:hello-window-android.
 *
 * Usage JVM : ./gradlew :samples:hello-window:run
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
    // -XstartOnFirstThread est un flag JVM macOS-only — la JVM Windows/Linux le rejette.
    jvmArgs(buildList {
        if (org.gradle.internal.os.OperatingSystem.current().isMacOsX) {
            add("-XstartOnFirstThread")
        }
        add("--enable-native-access=ALL-UNNAMED")
    })
}
