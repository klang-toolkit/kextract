package org.graphiks.kextract.pipeline

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream
import java.io.PrintWriter
import java.nio.file.Path
import java.util.concurrent.CountDownLatch
import org.graphiks.kextract.Position
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertSame
import kotlin.test.assertTrue

class LoggerTest {
    private lateinit var outStream: ByteArrayOutputStream
    private lateinit var errStream: ByteArrayOutputStream
    private lateinit var logger: Logger

    @BeforeEach
    fun setUp() {
        outStream = ByteArrayOutputStream()
        errStream = ByteArrayOutputStream()
        logger = Logger(
            PrintWriter(outStream, true),
            PrintWriter(errStream, true)
        )
    }

    @Test
    fun `test err without position`() {
        logger.err("test.error", "arg1", "arg2")
        assertTrue(logger.hasErrors())
        assertEquals(1, logger.nErrors)
        assertContains(errStream.toString(), "error:")
        assertContains(errStream.toString(), "arg1")
        assertContains(errStream.toString(), "arg2")
    }

    @Test
    fun `test err with position`() {
        val pos = Position(Path.of("test.h"), 10, 5)
        logger.err("test.error", "msg", pos = pos)
        assertTrue(logger.hasErrors())
        assertContains(errStream.toString(), "test.h:10:5")
    }

    @Test
    fun `test warn does not increment error count`() {
        logger.warn("test.warn", "message")
        assertFalse(logger.hasErrors())
        assertEquals(0, logger.nErrors)
    }

    @Test
    fun `test clangErr increments clang error count`() {
        logger.clangErr(Position(Path.of("test.h"), 1, 1), "clang error")
        assertTrue(logger.hasClangErrors())
        assertEquals(1, logger.nClangErrors)
    }

    @Test
    fun `test clangWarn does not increment clang error count`() {
        logger.clangWarn(Position(Path.of("test.h"), 1, 1), "clang warning")
        assertFalse(logger.hasClangErrors())
        assertEquals(0, logger.nClangErrors)
    }

    @Test
    fun `test info without position`() {
        logger.info("test.info", "msg")
        assertContains(errStream.toString(), "msg")
    }

    @Test
    fun `test DEFAULT instance is singleton`() {
        assertSame(Logger.DEFAULT, Logger.DEFAULT)
    }

    @Test
    fun `test fatal with throwable`() {
        val exception = RuntimeException("test exception")
        logger.fatal(exception, "kextract.crash")
        assertContains(errStream.toString(), "fatal:")
    }

    @Test
    fun `test format with missing key`() {
        val result = logger.format("nonexistent.key", "arg1")
        assertEquals("nonexistent.key arg1", result)
    }

    @Test
    fun `test printStackTrace`() {
        val exception = RuntimeException("test")
        logger.printStackTrace(exception)
        assertContains(errStream.toString(), "RuntimeException")
        assertContains(errStream.toString(), "test")
    }

    @Test
    fun `test Position toString`() {
        val pos = Position(Path.of("path/to/file.h"), 42, 10)
        // Use Path.toString() to produce the platform-appropriate separator so the
        // assertion holds on both POSIX (/) and Windows (\).
        val expected = "${Path.of("path/to/file.h")}:42:10"
        assertEquals(expected, pos.toString())
    }

    @Test
    fun `concurrent error reporting preserves every message and counter update`() {
        val threadCount = 16
        val reportsPerThread = 250
        val ready = CountDownLatch(threadCount)
        val start = CountDownLatch(1)
        val threads = List(threadCount) { threadIndex ->
            Thread {
                ready.countDown()
                start.await()
                repeat(reportsPerThread) { reportIndex ->
                    logger.err("test.error", "regular-$threadIndex-$reportIndex")
                    logger.clangErr(null, "clang-$threadIndex-$reportIndex")
                }
            }
        }
        threads.forEach { it.start() }
        ready.await()
        start.countDown()
        threads.forEach { it.join() }

        val expectedPerKind = threadCount * reportsPerThread
        val emittedLines = errStream.toString().lineSequence().count { it.isNotBlank() }
        assertEquals(expectedPerKind * 2, emittedLines)
        assertEquals(expectedPerKind, logger.nErrors)
        assertEquals(expectedPerKind, logger.nClangErrors)
    }
}
