// src/main/kotlin/org/openjdk/kextract/kotlin/KotlinGenerator.kt
package org.openjdk.kextract.kotlin

import org.openjdk.kextract.Declaration
import org.openjdk.kextract.kotlin.builders.KotlinToplevelBuilder
import org.openjdk.kextract.kotlin.models.KotlinSourceFile

/**
 * Main entry point for Kotlin code generation.
 * This class is callable from Java (via KextractTool).
 */
class KotlinGenerator {
    /**
     * Generates Kotlin source files from a C AST.
     * @param scoped The root declaration (parsed from C headers).
     * @param headerName The name of the header file (e.g., "mylib.h").
     * @param targetPackage The target package (e.g., "org.mylib").
     * @return List of generated Kotlin source files.
     */
    fun generate(
        scoped: Declaration.Scoped,
        headerName: String,
        targetPackage: String
    ): List<KotlinSourceFile> {
        val className = sanitizeClassName(headerName)
        val toplevel = KotlinToplevelBuilder(targetPackage, className, headerName)
        scoped.accept(toplevel, scoped)
        return toplevel.getFiles()
    }

    private fun sanitizeClassName(name: String): String =
        name.substringAfterLast('/')
            .replace(Regex("[^a-zA-Z0-9_]"), "_")
            .replace(Regex("^\\d+"), "_")
}
