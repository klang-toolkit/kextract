// src/main/kotlin/org/openjdk/kextract/kotlin/builders/SourceBuilder.kt
package org.graphiks.kextract.kotlin.builders

/**
 * Base builder for Kotlin source code with indentation support.
 */
open class SourceBuilder {
    private val sb = StringBuilder()
    private var indentLevel = 0
    private val indentString = "    " // 4 spaces

    /**
     * Appends a line with current indentation.
     */
    fun appendLine(line: String = "") {
        sb.append("${indentString.repeat(indentLevel)}$line\n")
    }

    /**
     * Appends a block of code (multi-line string) with current indentation.
     */
    fun appendBlock(block: String) {
        block.lines().forEach { line ->
            if (line.isNotEmpty()) {
                appendLine(line)
            } else {
                appendLine()
            }
        }
    }

    /**
     * Increases indentation level.
     */
    fun indent() {
        indentLevel++
    }

    /**
     * Decreases indentation level.
     */
    fun unindent() {
        indentLevel = maxOf(indentLevel - 1, 0)
    }

    /**
     * Returns the current indentation string.
     */
    fun currentIndent(): String = indentString.repeat(indentLevel)

    /**
     * Returns the built code.
     */
    override fun toString(): String = sb.toString()

    /**
     * Clears the builder.
     */
    fun clear() {
        sb.clear()
        indentLevel = 0
    }
}
