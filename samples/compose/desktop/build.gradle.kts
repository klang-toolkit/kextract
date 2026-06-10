plugins {
    kotlin("jvm")
    application
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.compose.multiplatform)
}

kotlin {
    jvmToolchain(25)
    compilerOptions {
        freeCompilerArgs.addAll(
            "-opt-in=androidx.compose.ui.InternalComposeUiApi",
            "-opt-in=androidx.compose.ui.ExperimentalComposeUiApi",
        )
    }
}

application {
    mainClass.set("org.graphiks.kadre.samples.compose.desktop.MainKt")
    applicationDefaultJvmArgs = buildList {
        if (org.gradle.internal.os.OperatingSystem.current().isMacOsX) {
            add("-XstartOnFirstThread")
        }
        add("--enable-native-access=ALL-UNNAMED")
    }
}

dependencies {
    implementation(project(":samples:compose:shared"))
    implementation(project(":kadre"))
    implementation(project(":kadre-coroutines"))
    implementation(project(":kadre-win32"))
    implementation(project(":kadre-x11"))
    implementation(project(":kadre-wayland"))
    implementation(compose.desktop.currentOs)
}
