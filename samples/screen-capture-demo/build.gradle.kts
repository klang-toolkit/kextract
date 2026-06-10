/**
 * Sample: Screen Capture Demo
 * 
 * Demonstrates the ScreenCapturer API across platforms.
 * Shows available displays and windows, allows capturing frames.
 */
plugins {
    kotlin("jvm")
    application
}

kotlin {
    jvmToolchain(25)
}

application {
    mainClass.set("org.graphiks.kadre.samples.screencapture.MainKt")
    
    // Enable native access for FFM
    applicationDefaultJvmArgs = listOf("--enable-native-access=ALL-UNNAMED")
}

dependencies {
    implementation(project(":kadre-core"))
    implementation(project(":kadre-win32"))
    implementation(project(":kadre-x11"))
    implementation(project(":kadre-wayland"))
    implementation(project(":kadre-appkit"))
}

tasks.withType<JavaExec> {
    jvmArgs = listOf("-Djava.library.path=${System.getenv("JAVA_LIBRARY_PATH") ?: ""}")
}
