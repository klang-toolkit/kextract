// src/main/kotlin/org/openjdk/kextract/kotlin/models/KotlinSourceFile.kt
package org.graphiks.kextract.kotlin.models

import java.nio.file.Path

/**
 * Represents a generated Kotlin source file.
 * @param packageName The package (e.g., "org.mylib").
 * @param className The class name (e.g., "mylib_h").
 * @param contents The Kotlin code.
 */
data class KotlinSourceFile(
    val packageName: String,
    val className: String,
    val contents: String
) {
    /**
     * Returns the file path (e.g., "org/mylib/mylib_h.kt").
     */
    fun getPath(): Path = Path.of(packageName.replace('.', '/'), "$className.kt")

    /**
     * Returns the full qualified name (e.g., "org.mylib.mylib_h").
     */
    fun getQualifiedName(): String = "$packageName.$className"

}
