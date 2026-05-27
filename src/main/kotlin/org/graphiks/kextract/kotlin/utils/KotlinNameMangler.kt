// src/main/kotlin/org/openjdk/kextract/kotlin/utils/KotlinNameMangler.kt
package org.graphiks.kextract.kotlin.utils

import org.graphiks.kextract.Declaration

/**
 * Mangles C names to valid Kotlin identifiers.
 * Handles:
 * - Invalid characters (e.g., '$' -> '_')
 * - Reserved keywords (e.g., 'fun' -> 'fun_')
 * - Leading digits (e.g., '1foo' -> '_1foo')
 */
object KotlinNameMangler {
    private val RESERVED_KEYWORDS = setOf(
        // Soft keywords
        "file", "field", "property", "receiver", "param", "setparam", "delegate",
        // Hard keywords
        "package", "import", "class", "interface", "object", "data", "sealed",
        "fun", "val", "var", "typealias", "constructor", "by", "get", "set", "where",
        "if", "else", "when", "try", "catch", "finally", "for", "while", "do", "continue",
        "break", "return", "throw", "is", "in", "as", "this", "super", "null", "true", "false"
    )

    /**
     * Mangles a C name to a valid Kotlin identifier.
     */
    fun mangle(name: String): String {
        var result = name
            .replace(Regex("[^a-zA-Z0-9_]"), "_") // Replace invalid chars
            .replace(Regex("^\\d+"), "_")        // No leading digits

        // Handle consecutive underscores
        result = result.replace(Regex("_+"), "_")

        // Handle reserved keywords
        if (RESERVED_KEYWORDS.contains(result)) {
            result += "_"
        }

        return result
    }

    fun mangle(decl: Declaration): String = mangle(decl.name())
}
