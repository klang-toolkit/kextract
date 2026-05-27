// src/main/kotlin/org/openjdk/kextract/kotlin/KotlinGenerator.kt
package org.graphiks.kextract.kotlin

import org.graphiks.kextract.Declaration
import org.graphiks.kextract.kotlin.builders.KotlinToplevelBuilder
import org.graphiks.kextract.kotlin.models.KotlinSourceFile
import org.graphiks.kextract.kotlin.objc.ObjCRuntimeTemplate
import org.graphiks.kextract.pipeline.Options

/**
 * Main entry point for Kotlin code generation.
 * This class is callable from Java (via KextractTool).
 */
class KotlinGenerator {
    /**
     * Generates Kotlin source files from a C/ObjC AST.
     *
     * When Objective-C declarations are present an additional `ObjCRuntime.kt`
     * helper file is automatically included in the result so callers can use
     * `ObjCRuntime.msgSend` / `ObjCRuntime.sel` / `ObjCRuntime.getClass`.
     *
     * @param scoped The root declaration (parsed from C/ObjC headers).
     * @param headerName The name of the header file (e.g., "mylib.h").
     * @param targetPackage The target package (e.g., "org.mylib").
     * @param libraries Libraries to load at runtime (used to generate lookup code).
     * @param useSystemLoadLibrary When true use System.loadLibrary instead of libraryLookup.
     * @return List of generated Kotlin source files.
     */
    fun generate(
        scoped: Declaration.Scoped,
        headerName: String,
        targetPackage: String,
        libraries: List<Options.Library> = emptyList(),
        useSystemLoadLibrary: Boolean = false
    ): List<KotlinSourceFile> {
        val className = sanitizeClassName(headerName)
        val toplevel = KotlinToplevelBuilder(
            targetPackage, className, headerName, libraries, useSystemLoadLibrary
        )
        scoped.accept(toplevel)
        val files = toplevel.getFiles().toMutableList()
        if (toplevel.needsObjCRuntime) {
            files.add(ObjCRuntimeTemplate.generate(targetPackage))
        }
        return files
    }

    private fun sanitizeClassName(name: String): String =
        name.substringAfterLast('/')
            .replace(Regex("[^a-zA-Z0-9_]"), "_")
            .replace(Regex("^\\d+"), "_")
}
