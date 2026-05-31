/**
 * Sample hello-triangle — intégration wgpu4k : Instance + Surface depuis CAMetalLayer (GRA-137).
 *
 * Consomme le [RawWindowHandle] de Kadre pour initialiser wgpu4k :
 *   EventLoop → AppKitEventLoop → NSWindow + CAMetalLayer → WGPU Instance + Surface + Adapter + Device
 *
 * Périmètre M2 — pas encore de rendu : la fenêtre reste vide (clear par défaut).
 *
 * Usage : ./gradlew :samples:hello-triangle:run
 * Prérequis : macOS avec JDK 25 (thread principal — lancé par Gradle).
 */
plugins {
    kotlin("jvm")
    application
}

kotlin {
    jvmToolchain(25)
}

application {
    mainClass.set("org.graphiks.kadre.samples.hellotriangle.MainKt")

    // macOS : NSApplication doit tourner sur le thread principal JVM.
    // -XstartOnFirstThread est un flag JVM macOS-only — la JVM Windows/Linux le rejette.
    // Panama FFM : --enable-native-access supprime les warnings JDK 22+.
    applicationDefaultJvmArgs = buildList {
        if (org.gradle.internal.os.OperatingSystem.current().isMacOsX) {
            add("-XstartOnFirstThread")
        }
        add("--enable-native-access=ALL-UNNAMED")
    }
}

dependencies {
    implementation(project(":kadre"))
    implementation(libs.wgpu4k)
    // Data classes descripteurs wgpu4k (VertexState, FragmentState, RenderPipelineDescriptor, etc.)
    // synchronisées avec la version de webgpu-ktypes embarquée dans wgpu4k:0.1.1
    implementation(libs.webgpu.ktypes.descriptors)
    // runBlocking — transitif via wgpu4k mais déclaré explicitement pour la clarté
    implementation(libs.kotlinx.coroutines.core)
    // Backends Linux : requis par le mode --capture, qui réutilise
    // l'EventLoop kadre pour obtenir une fenêtre Wayland/X11. La façade les charge
    // par réflexion → ils doivent être sur le classpath. Inertes sur macOS/Windows.
    implementation(project(":kadre-wayland"))
    implementation(project(":kadre-x11"))
}
