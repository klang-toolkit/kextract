/**
 * Sample hello-compose — Jetpack Compose (Compose Multiplatform) inside a Kadre window.
 *
 * Demonstrates hosting an interactive Compose UI in a native macOS Kadre window:
 *   Kadre NSWindow + CAMetalLayer → Skiko (Metal) → ComposeScene → Material3 UI.
 *
 * Unlike Compose for Desktop's usual Swing/AWT host, Kadre owns a pure native
 * NSWindow created via Panama FFM. We therefore drive a low-level [ComposeScene]
 * ourselves and render it into Kadre's CAMetalLayer through Skiko's Metal backend
 * (DirectContext.makeMetal / BackendRenderTarget.makeMetal / Surface.makeFromBackendRenderTarget).
 *
 * Input (mouse move / click / scroll) from Kadre's WindowEvents is forwarded to the
 * ComposeScene so the UI is genuinely interactive.
 *
 * Target: jvm (JDK 25, macOS arm64 only — same constraints as hello-metal/hello-triangle).
 *
 * Usage: ./gradlew :samples:hello-compose:run
 */
plugins {
    kotlin("jvm")
    application
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.compose.multiplatform)
}

kotlin {
    jvmToolchain(25)

    // The low-level ComposeScene / pointer APIs used to embed Compose in a non-AWT host
    // are marked internal/experimental ("for use only between compose-ui modules").
    compilerOptions {
        freeCompilerArgs.addAll(
            "-opt-in=androidx.compose.ui.InternalComposeUiApi",
            "-opt-in=androidx.compose.ui.ExperimentalComposeUiApi",
        )
    }
}

application {
    mainClass.set("org.graphiks.kadre.samples.hellocompose.MainKt")

    // macOS: NSApplication (and Metal presentation) must run on the main JVM thread.
    applicationDefaultJvmArgs = buildList {
        if (org.gradle.internal.os.OperatingSystem.current().isMacOsX) {
            add("-XstartOnFirstThread")
        }
        // Panama FFM (objc_msgSend, MTLCreateSystemDefaultDevice) — JDK 22+.
        add("--enable-native-access=ALL-UNNAMED")
    }
}

dependencies {
    implementation(project(":kadre"))
    // Coroutine-friendly layer: main-thread dispatcher + application { } + window events Flow.
    implementation(project(":kadre-coroutines"))

    // Kadre platform backends. The :kadre facade selects one by reflection at runtime
    // (AppKit on macOS, Win32 on Windows, X11/Wayland on Linux), so all desktop backends
    // must be on the classpath for the sample to open a window on each OS. Inert elsewhere.
    implementation(project(":kadre-win32"))
    implementation(project(":kadre-x11"))
    implementation(project(":kadre-wayland"))

    // Compose Multiplatform (desktop / JVM).
    implementation(compose.runtime)
    implementation(compose.foundation)
    implementation(compose.material3)
    implementation(compose.ui)

    // Brings the Skiko native runtime for the *current* OS/arch (macos, windows or linux),
    // so DirectContext.makeMetal (macOS) / makeGL (Windows/Linux) have native code at runtime.
    implementation(compose.desktop.currentOs)

    implementation(libs.kotlinx.coroutines.core)
}
