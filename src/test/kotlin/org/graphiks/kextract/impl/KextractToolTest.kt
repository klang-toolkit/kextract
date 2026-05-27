package org.graphiks.kextract.pipeline

import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.io.ByteArrayOutputStream
import java.io.PrintWriter
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class KextractToolTest {
    
    @Test
    fun `test KextractTool can be instantiated`() {
        val logger = Logger(PrintWriter(ByteArrayOutputStream()), PrintWriter(ByteArrayOutputStream()))
        val tool = KextractTool(logger)
        assertNotNull(tool)
    }

    @Test
    fun `test error codes are defined`() {
        assertEquals(0, KextractTool.SUCCESS)
        assertEquals(1, KextractTool.FAILURE)
        assertEquals(2, KextractTool.OPTION_ERROR)
        assertEquals(3, KextractTool.INPUT_ERROR)
        assertEquals(4, KextractTool.CLANG_ERROR)
        assertEquals(5, KextractTool.FATAL_ERROR)
        assertEquals(6, KextractTool.OUTPUT_ERROR)
    }

    @Test
    fun `test sanitizeClassName with special characters`() {
        val tool = KextractTool(Logger.DEFAULT)
        
        // Test with dots
        assertEquals("test_header", tool.sanitizeClassName("test.header"))
        
        // Test with starting digits
        assertEquals("_123test", tool.sanitizeClassName("123test"))
        
        // Test with multiple special characters
        assertEquals("test___header", tool.sanitizeClassName("test-/+header"))
        
        // Test with valid name
        assertEquals("validName", tool.sanitizeClassName("validName"))
        
        // Test with underscores
        assertEquals("test_header_file", tool.sanitizeClassName("test_header_file"))
    }

    @Test
    fun `test run with empty args returns failure`() {
        val logger = Logger(PrintWriter(ByteArrayOutputStream()), PrintWriter(ByteArrayOutputStream()))
        val tool = KextractTool(logger)
        
        val result = tool.run(arrayOf())
        assertEquals(KextractTool.FAILURE, result)
    }

    @Disabled("SecurityManager removed in Java 17+; System.exit interception not supported")
    @Test
    fun `test run with help flag exits with success`() {
    }

    @Test
    fun `test parseArgs with output option`() {
        val logger = Logger(PrintWriter(ByteArrayOutputStream()), PrintWriter(ByteArrayOutputStream()))
        val tool = KextractTool(logger)
        
        val optionsBuilder = Options.builder()
        val positional = mutableListOf<String>()
        
        tool.parseArgs(listOf("-o", "output_dir", "header.h"), optionsBuilder, positional)
        
        val options = optionsBuilder.build()
        assertEquals("output_dir", options.outputDir)
        assertEquals(1, positional.size)
        assertEquals("header.h", positional[0])
    }

    @Test
    fun `test parseArgs with target package option`() {
        val logger = Logger(PrintWriter(ByteArrayOutputStream()), PrintWriter(ByteArrayOutputStream()))
        val tool = KextractTool(logger)
        
        val optionsBuilder = Options.builder()
        val positional = mutableListOf<String>()
        
        tool.parseArgs(listOf("-t", "com.example", "header.h"), optionsBuilder, positional)
        
        val options = optionsBuilder.build()
        assertEquals("com.example", options.targetPackage)
        assertEquals(1, positional.size)
        assertEquals("header.h", positional[0])
    }

    @Test
    fun `test parseArgs with include path option`() {
        val logger = Logger(PrintWriter(ByteArrayOutputStream()), PrintWriter(ByteArrayOutputStream()))
        val tool = KextractTool(logger)
        
        val optionsBuilder = Options.builder()
        val positional = mutableListOf<String>()
        
        tool.parseArgs(listOf("-I", "/usr/include", "header.h"), optionsBuilder, positional)
        
        val options = optionsBuilder.build()
        assertTrue(options.clangArgs.contains("-I/usr/include"))
        assertEquals(1, positional.size)
        assertEquals("header.h", positional[0])
    }

    @Test
    fun `test parseArgs with library option`() {
        val logger = Logger(PrintWriter(ByteArrayOutputStream()), PrintWriter(ByteArrayOutputStream()))
        val tool = KextractTool(logger)
        
        val optionsBuilder = Options.builder()
        val positional = mutableListOf<String>()
        
        tool.parseArgs(listOf("-l", "m", "header.h"), optionsBuilder, positional)
        
        val options = optionsBuilder.build()
        assertEquals(1, options.libraries.size)
        assertEquals("m", options.libraries[0].libSpec)
        assertEquals(Options.Library.SpecKind.NAME, options.libraries[0].specKind)
    }

    @Test
    fun `test parseArgs with path library option`() {
        val logger = Logger(PrintWriter(ByteArrayOutputStream()), PrintWriter(ByteArrayOutputStream()))
        val tool = KextractTool(logger)
        
        val optionsBuilder = Options.builder()
        val positional = mutableListOf<String>()
        
        tool.parseArgs(listOf("-l", ":/usr/lib/libm.so", "header.h"), optionsBuilder, positional)
        
        val options = optionsBuilder.build()
        assertEquals(1, options.libraries.size)
        assertEquals("/usr/lib/libm.so", options.libraries[0].libSpec)
        assertEquals(Options.Library.SpecKind.PATH, options.libraries[0].specKind)
    }

    @Test
    fun `test parseArgs with use system load library option`() {
        val logger = Logger(PrintWriter(ByteArrayOutputStream()), PrintWriter(ByteArrayOutputStream()))
        val tool = KextractTool(logger)
        
        val optionsBuilder = Options.builder()
        val positional = mutableListOf<String>()
        
        tool.parseArgs(listOf("--use-system-load-library", "header.h"), optionsBuilder, positional)
        
        val options = optionsBuilder.build()
        assertTrue(options.useSystemLoadLibrary)
    }

    @Test
    fun `test parseArgs with dump includes option`() {
        val logger = Logger(PrintWriter(ByteArrayOutputStream()), PrintWriter(ByteArrayOutputStream()))
        val tool = KextractTool(logger)
        
        val optionsBuilder = Options.builder()
        val positional = mutableListOf<String>()
        
        tool.parseArgs(listOf("--dump-includes", "includes.txt", "header.h"), optionsBuilder, positional)
        
        val options = optionsBuilder.build()
        assertEquals("includes.txt", options.includeHelper.dumpIncludesFile)
    }

    @Test
    fun `test parseArgs with clang args`() {
        val logger = Logger(PrintWriter(ByteArrayOutputStream()), PrintWriter(ByteArrayOutputStream()))
        val tool = KextractTool(logger)
        
        val optionsBuilder = Options.builder()
        val positional = mutableListOf<String>()
        
        tool.parseArgs(listOf("-DDEBUG", "-Wall", "header.h"), optionsBuilder, positional)
        
        val options = optionsBuilder.build()
        assertTrue(options.clangArgs.contains("-DDEBUG"))
        assertTrue(options.clangArgs.contains("-Wall"))
        assertEquals(1, positional.size)
        assertEquals("header.h", positional[0])
    }

    @Test
    fun `test isSpecialHeaderName`() {
        val tool = KextractTool(Logger.DEFAULT)
        
        // Test angle bracket headers
        assertTrue(tool.isSpecialHeaderName("<stdio.h>"))
        assertTrue(tool.isSpecialHeaderName("<stdlib.h>"))
        
        // Test special standard headers
        assertTrue(tool.isSpecialHeaderName("stdarg.h"))
        assertTrue(tool.isSpecialHeaderName("stddef.h"))
        assertTrue(tool.isSpecialHeaderName("stdint.h"))
        assertTrue(tool.isSpecialHeaderName("stdbool.h"))
        assertTrue(tool.isSpecialHeaderName("stdalign.h"))
        assertTrue(tool.isSpecialHeaderName("stdnoreturn.h"))
        assertTrue(tool.isSpecialHeaderName("stdckdint.h"))
        assertTrue(tool.isSpecialHeaderName("stdatomic.h"))
        
        // Test non-special headers
        assertTrue(!tool.isSpecialHeaderName("stdio.h"))
        assertTrue(!tool.isSpecialHeaderName("myheader.h"))
    }

    @Test
    fun `test generateTmpSource`() {
        val tool = KextractTool(Logger.DEFAULT)
        
        // Test with regular headers
        val source1 = tool.generateTmpSource(listOf("stdio.h", "stdlib.h"))
        assertEquals("""
            |#include "stdio.h"
            |#include "stdlib.h"
        """.trimMargin(), source1)
        
        // Test with special headers
        val source2 = tool.generateTmpSource(listOf("<stdio.h>", "<stdlib.h>"))
        assertEquals("""
            |#include <stdio.h>
            |#include <stdlib.h>
        """.trimMargin(), source2)
        
        // Test with mixed headers
        val source3 = tool.generateTmpSource(listOf("<stdio.h>", "myheader.h"))
        assertEquals("""
            |#include <stdio.h>
            |#include "myheader.h"
        """.trimMargin(), source3)
        
        // Test with empty list
        val source4 = tool.generateTmpSource(emptyList())
        assertEquals("", source4)
    }

    // convertToJavaLibrary removed — Options.Library is the unified library type
}
