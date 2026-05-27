package org.graphiks.kextract.pipeline

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertTrue

class OptionsTest {

    @Test
    fun `Options has correct defaults`() {
        val options = Options()
        assertEquals("", options.targetPackage)
        assertEquals(".", options.outputDir)
        assertEquals(false, options.useSystemLoadLibrary)
        assertTrue(options.clangArgs.isEmpty())
        assertTrue(options.libraries.isEmpty())
        assertNull(options.sharedClassName)
    }

    @Test
    fun `Options constructor sets all properties`() {
        val lib = Options.Library.parse("mylib")
        val options = Options(
            targetPackage      = "org.test",
            outputDir          = "/output",
            useSystemLoadLibrary = true,
            clangArgs          = listOf("-I/include"),
            libraries          = listOf(lib),
            sharedClassName    = "Symbols"
        )

        assertEquals("org.test", options.targetPackage)
        assertEquals("/output", options.outputDir)
        assertEquals(true, options.useSystemLoadLibrary)
        assertEquals(listOf("-I/include"), options.clangArgs)
        assertEquals(1, options.libraries.size)
        assertEquals("Symbols", options.sharedClassName)
    }

    @Test
    fun `Library parse with name`() {
        val lib = Options.Library.parse("c")
        assertEquals("c", lib.libSpec)
        assertEquals(Options.Library.SpecKind.NAME, lib.specKind)
    }

    @Test
    fun `Library parse with path`() {
        val lib = Options.Library.parse(":lib/c.so")
        assertEquals("lib/c.so", lib.libSpec)
        assertEquals(Options.Library.SpecKind.PATH, lib.specKind)
    }

    @Test
    fun `Library parse with empty path throws`() {
        assertThrows<IllegalArgumentException> { Options.Library.parse(":") }
    }

    @Test
    fun `toQuotedName escapes backslashes`() {
        val lib = Options.Library("path\\to\\lib", Options.Library.SpecKind.PATH)
        assertEquals("path\\\\to\\\\lib", Options.Library.toQuotedName(lib))
    }
}
