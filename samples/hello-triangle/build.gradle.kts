/**
 * Sample hello-triangle — wgpu4k integration: Instance + Surface from CAMetalLayer (GRA-137).
 *
 * Consumes Kadre's [RawWindowHandle] to initialize wgpu4k:
 *   EventLoop → AppKitEventLoop → NSWindow + CAMetalLayer → WGPU Instance + Surface + Adapter + Device
 *
 * M2 scope — no rendering yet: the window stays empty (default clear).
 *
 * Usage: ./gradlew :samples:hello-triangle:run
 * Requirements: macOS with JDK 25 (main thread — launched by Gradle).
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

    // macOS: NSApplication must run on the main JVM thread.
    // -XstartOnFirstThread is a macOS-only JVM flag — the Windows/Linux JVM rejects it.
    // Panama FFM: --enable-native-access suppresses JDK 22+ warnings.
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
    // wgpu4k descriptor data classes (VertexState, FragmentState, RenderPipelineDescriptor, etc.)
    // synchronized with the webgpu-ktypes version embedded in wgpu4k:0.1.1
    implementation(libs.webgpu.ktypes.descriptors)
    // runBlocking — transitive via wgpu4k but declared explicitly for clarity
    implementation(libs.kotlinx.coroutines.core)
    // Linux backends: required by --capture mode, which reuses
    // the kadre EventLoop to obtain a Wayland/X11 window. The facade loads them
    // by reflection → they must be on the classpath. Inert on macOS/Windows.
    implementation(project(":kadre-wayland"))
    implementation(project(":kadre-x11"))
}
