package org.graphiks.kextract.pipeline

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.io.TempDir
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Path
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class CommandLineTest {
    @TempDir
    private lateinit var tempDir: Path

    @Test
    fun `test parse with no @files`() {
        val result = CommandLine.parse(listOf("-I", "/include", "-DDEBUG"))
        assertEquals(listOf("-I", "/include", "-DDEBUG"), result)
    }

    @Test
    fun `test parse with @file`() {
        val tempFile = tempDir.resolve("test.args")
        Files.write(tempFile, listOf("-I", "/include", "-DDEBUG"))
        
        val result = CommandLine.parse(listOf("@${tempFile}"))
        assertEquals(listOf("-I", "/include", "-DDEBUG"), result)
    }

    @Test
    fun `test parse with @@ escape`() {
        val result = CommandLine.parse(listOf("@@file"))
        assertEquals(listOf("@file"), result)
    }

    @Test
    fun `test parse with multiple @files`() {
        val file1 = tempDir.resolve("file1.args")
        val file2 = tempDir.resolve("file2.args")
        Files.write(file1, listOf("-I", "/include"))
        Files.write(file2, listOf("-DDEBUG"))
        
        val result = CommandLine.parse(listOf("@${file1}", "@${file2}"))
        assertEquals(listOf("-I", "/include", "-DDEBUG"), result)
    }

    @Test
    fun `test parse with non-existent file throws IOException`() {
        assertThrows(IOException::class.java) {
            CommandLine.parse(listOf("@/nonexistent/file.args"))
        }
    }

    @Test
    fun `test Tokenizer with simple tokens`() {
        val input = "token1 token2 token3"
        val tokenizer = CommandLine.Tokenizer(input.reader())
        
        val tokens = mutableListOf<String>()
        var token: String?
        while (tokenizer.nextToken().also { token = it } != null) {
            tokens.add(token!!)
        }
        
        assertEquals(listOf("token1", "token2", "token3"), tokens)
    }

    @Test
    fun `test Tokenizer with quoted strings`() {
        val input = "'token 1' \"token 2\" token3"
        val tokenizer = CommandLine.Tokenizer(input.reader())
        
        val tokens = mutableListOf<String>()
        var token: String?
        while (tokenizer.nextToken().also { token = it } != null) {
            tokens.add(token!!)
        }
        
        assertEquals(listOf("token 1", "token 2", "token3"), tokens)
    }

    @Test
    fun `test Tokenizer with escaped quotes`() {
        val input = "'token \\'with quote\\''"
        val tokenizer = CommandLine.Tokenizer(input.reader())
        
        val token = tokenizer.nextToken()
        assertNotNull(token)
        assertContains(token, "with quote")
    }

    @Test
    fun `test Tokenizer with backslashes`() {
        val input = "'token\\\\with\\\\backslashes'"
        val tokenizer = CommandLine.Tokenizer(input.reader())
        
        val token = tokenizer.nextToken()
        assertNotNull(token)
        assertEquals("token\\with\\backslashes", token)
    }

    @Test
    fun `test Tokenizer with escape sequences`() {
        val input = "'token\\nwith\\tescapes'"
        val tokenizer = CommandLine.Tokenizer(input.reader())
        
        val token = tokenizer.nextToken()
        assertNotNull(token)
        assertContains(token, "\n")
        assertContains(token, "\t")
    }

    @Test
    fun `test Tokenizer with comments`() {
        val input = "token1 # this is a comment\ntoken2"
        val tokenizer = CommandLine.Tokenizer(input.reader())
        
        val tokens = mutableListOf<String>()
        var token: String?
        while (tokenizer.nextToken().also { token = it } != null) {
            tokens.add(token!!)
        }
        
        assertEquals(listOf("token1", "token2"), tokens)
    }

    @Test
    fun `test parse with empty args`() {
        val result = CommandLine.parse(emptyList<String>())
        assertEquals(emptyList<String>(), result)
    }

    @Test
    fun `test parse with null envVariable`() {
        val result = CommandLine.parse(null, listOf("arg1", "arg2"))
        assertEquals(listOf("arg1", "arg2"), result)
    }

    @Test
    fun `test parse with empty envVariable`() {
        val result = CommandLine.parse("", listOf("arg1", "arg2"))
        assertEquals(listOf("arg1", "arg2"), result)
    }

    @Test
    fun `test UnmatchedQuoteException`() {
        val exception = CommandLine.UnmatchedQuoteException("TEST_VAR")
        assertContains(exception.message ?: "", "Unmatched quote")
        assertContains(exception.message ?: "", "TEST_VAR")
    }
}
