plugins {
    kotlin("multiplatform")
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.compose.compiler)
}

kotlin {
    jvm()
    jvmToolchain(25)

    compilerOptions {
        freeCompilerArgs.addAll(
            "-opt-in=androidx.compose.ui.InternalComposeUiApi",
            "-opt-in=androidx.compose.ui.ExperimentalComposeUiApi",
        )
    }

    sourceSets {
        commonMain {
            dependencies {
                implementation(project(":kadre"))
                implementation(compose.runtime)
                implementation(compose.ui)
                implementation(compose.foundation)
                implementation(compose.material)
                implementation(compose.material3)
                implementation(compose.desktop.currentOs)
            }
        }
        jvmMain {
            dependencies {
                implementation(project(":kadre"))
                implementation(project(":kadre-coroutines"))
                implementation(project(":kadre-win32"))
                implementation(project(":kadre-x11"))
                implementation(project(":kadre-wayland"))
            }
        }
        commonTest {
            dependencies {
                implementation(kotlin("test"))
                implementation(libs.kotlinx.coroutines.test)
            }
        }
    }
}

tasks.register<JavaExec>("run") {
    group = "application"
    description = "Runs Simulation Demo on JVM"
    dependsOn("jvmJar")
    mainClass.set("org.graphiks.kadre.samples.simulation.JvmMainKt")
    classpath = files(
        kotlin.targets.getByName("jvm").compilations.getByName("main").output.allOutputs,
        configurations.getByName("jvmRuntimeClasspath"),
    )
    jvmArgs(buildList {
        if (org.gradle.internal.os.OperatingSystem.current().isMacOsX) {
            add("-XstartOnFirstThread")
        }
        add("--enable-native-access=ALL-UNNAMED")
    })
}
