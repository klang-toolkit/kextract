package org.graphiks.kextract.kotlin.builders

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class SourceBuilderTest {
    @Test
    fun `blank lines never contain indentation whitespace`() {
        val builder = SourceBuilder()

        builder.appendLine("outer {")
        builder.indent()
        builder.appendLine()
        builder.appendLine("inner")

        assertEquals("outer {\n\n    inner\n", builder.toString())
    }
}
