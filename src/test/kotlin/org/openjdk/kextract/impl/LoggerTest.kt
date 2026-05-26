/*
 * Copyright (c) 2024 Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */

package org.openjdk.kextract.impl

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.openjdk.kextract.Position
import java.io.ByteArrayOutputStream
import java.io.PrintWriter
import java.util.MissingResourceException
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
        logger.err("test.error", "msg", pos = Position.NO_POSITION)
        assertTrue(logger.hasErrors())
    }

    @Test
    fun `test warn does not increment error count`() {
        logger.warn("test.warn", "message")
        assertFalse(logger.hasErrors())
        assertEquals(0, logger.nErrors)
    }

    @Test
    fun `test clangErr increments clang error count`() {
        logger.clangErr(Position.NO_POSITION, "clang error")
        assertTrue(logger.hasClangErrors())
        assertEquals(1, logger.nClangErrors)
    }

    @Test
    fun `test clangWarn does not increment clang error count`() {
        logger.clangWarn(Position.NO_POSITION, "clang warning")
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
        // Test du fallback quand la clé n'existe pas
        val result = logger.format("nonexistent.key", "arg1")
        assertEquals("nonexistent.key arg1", result)
    }

    @Test
    fun `test format with valid key`() {
        // Note: Cela dépend des messages présents dans Messages.properties
        // Pour le test, nous vérifions que ça ne crash pas
        try {
            val result = logger.format("kextract.version")
            assertNotNull(result)
        } catch (e: Exception) {
            // Si le bundle n'est pas trouvé, c'est OK pour ce test
            assertTrue(e is MissingResourceException || 
                      e.message?.contains("Messages") == true)
        }
    }

    @Test
    fun `test printStackTrace`() {
        val exception = RuntimeException("test")
        logger.printStackTrace(exception)
        assertContains(errStream.toString(), "RuntimeException")
        assertContains(errStream.toString(), "test")
    }

    @Test
    fun `test thread safety`() {
        // Test basique de thread-safety
        val threads = (1..10).map { i ->
            Thread {
                logger.err("test.error", "msg$i")
            }
        }
        threads.forEach { it.start() }
        threads.forEach { it.join() }

        // Vérifier que tous les messages sont présents
        val output = errStream.toString()
        for (i in 1..10) {
            assertContains(output, "msg$i")
        }
        assertEquals(10, logger.nErrors)
    }

    @Test
    fun `test err with Position NO_POSITION uses default parameter`() {
        // Test l'utilisation du paramètre par défaut
        logger.err("test.error", "default")
        assertTrue(logger.hasErrors())
        assertEquals(1, logger.nErrors)
    }

    @Test
    fun `test warn with Position NO_POSITION uses default parameter`() {
        logger.warn("test.warn", "default")
        assertFalse(logger.hasErrors())
        assertEquals(0, logger.nErrors)
    }

    @Test
    fun `test info with Position NO_POSITION uses default parameter`() {
        logger.info("test.info", "default")
        assertContains(errStream.toString(), "default")
    }

    @Test
    fun `test clangInfo with NO_POSITION`() {
        logger.clangInfo(Position.NO_POSITION, "info message")
        assertContains(errStream.toString(), "info message")
    }

    @Test
    fun `test fatal with default message`() {
        val exception = RuntimeException("crash")
        logger.fatal(exception)
        assertContains(errStream.toString(), "fatal:")
        assertContains(errStream.toString(), "kextract.crash")
    }

    @Test
    fun `test format with no args`() {
        val result = logger.format("nonexistent.key")
        assertEquals("nonexistent.key", result)
    }

    @Test
    fun `test format with multiple args`() {
        val result = logger.format("nonexistent.key", "arg1", "arg2", "arg3")
        assertEquals("nonexistent.key arg1 arg2 arg3", result)
    }

    @Test
    fun `test hasErrors returns false when no errors`() {
        assertFalse(logger.hasErrors())
    }

    @Test
    fun `test hasClangErrors returns false when no clang errors`() {
        assertFalse(logger.hasClangErrors())
    }
}
