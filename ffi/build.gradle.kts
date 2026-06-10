/**
 * Shared configuration for all ffi subprojects.
 *
 * Each ffi module is a KMP (JVM-only) library containing Panama FFM bindings
 * for a specific native technology. They have zero Kadre dependencies.
 */
subprojects {
    apply(plugin = "org.jetbrains.kotlin.multiplatform")

    configure<org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension> {
        jvmToolchain(25)
        jvm()
    }
}
