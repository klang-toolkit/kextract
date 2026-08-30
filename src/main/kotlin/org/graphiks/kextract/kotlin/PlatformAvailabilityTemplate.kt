package org.graphiks.kextract.kotlin

import org.graphiks.kextract.kotlin.models.KotlinSourceFile

/** Emits the opt-in marker used by generated declarations with platform availability metadata. */
internal object PlatformAvailabilityTemplate {
    fun generate(targetPackage: String, sourceRoot: String = ""): KotlinSourceFile = KotlinSourceFile(
        packageName = targetPackage,
        className = "PlatformAvailability",
        sourceRoot = sourceRoot,
        contents = buildString {
            if (targetPackage.isNotEmpty()) {
                appendLine("package $targetPackage")
                appendLine()
            }
            appendLine("@MustBeDocumented")
            appendLine("@Repeatable")
            appendLine("@RequiresOptIn(")
            appendLine("    message = \"Check platform availability at runtime before using this declaration.\",")
            appendLine("    level = RequiresOptIn.Level.ERROR,")
            appendLine(")")
            appendLine("@Target(")
            appendLine("    AnnotationTarget.CLASS,")
            appendLine("    AnnotationTarget.CONSTRUCTOR,")
            appendLine("    AnnotationTarget.FUNCTION,")
            appendLine("    AnnotationTarget.FIELD,")
            appendLine("    AnnotationTarget.PROPERTY,")
            appendLine("    AnnotationTarget.TYPEALIAS,")
            appendLine(")")
            appendLine("@Retention(AnnotationRetention.BINARY)")
            appendLine("annotation class PlatformAvailability(")
            appendLine("    val platform: String,")
            appendLine("    val introducedMajor: Int = -1,")
            appendLine("    val introducedMinor: Int = -1,")
            appendLine("    val introducedSubminor: Int = -1,")
            appendLine("    val deprecated: Boolean = false,")
            appendLine("    val deprecatedMajor: Int = -1,")
            appendLine("    val deprecatedMinor: Int = -1,")
            appendLine("    val deprecatedSubminor: Int = -1,")
            appendLine("    val obsoletedMajor: Int = -1,")
            appendLine("    val obsoletedMinor: Int = -1,")
            appendLine("    val obsoletedSubminor: Int = -1,")
            appendLine("    val unavailable: Boolean = false,")
            appendLine("    val message: String = \"\",")
            appendLine(")")
        },
    )
}
