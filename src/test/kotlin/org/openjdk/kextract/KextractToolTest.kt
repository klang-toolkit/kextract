/*
 * Copyright (c) 2020, 2026, Oracle and/or its affiliates. All rights reserved.
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

package org.openjdk.kextract

import org.openjdk.kextract.clang.LibClang
import org.openjdk.kextract.impl.Logger
import org.openjdk.kextract.impl.Options
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import java.nio.file.Path
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class KextractToolTest {
    @TempDir
    private lateinit var tempDir: Path

    @Test
    fun `test sanitizeClassName with valid name`() {
        assertEquals("test_header", KextractTool.sanitizeClassName("test.header"))
    }

    @Test
    fun `test sanitizeClassName with numbers at start`() {
        assertEquals("_123test", KextractTool.sanitizeClassName("123test"))
    }

    @Test
    fun `test sanitizeClassName with special chars`() {
        assertEquals("test___header", KextractTool.sanitizeClassName("test-/+header"))
    }

    @Test
    fun `test ToolProvider name`() {
        val provider = KextractTool.KextractToolProvider()
        assertEquals("kextract", provider.name())
    }

    @Test
    fun `test run with empty args`() {
        val tool = KextractTool(Logger.DEFAULT)
        val result = tool.run(arrayOf())
        assertEquals(KextractTool.FAILURE, result)
    }

    @Test
    fun `test run with no headers`() {
        val tool = KextractTool(Logger.DEFAULT)
        val result = tool.run(arrayOf("--output", tempDir.toString()))
        assertEquals(KextractTool.FAILURE, result)
    }

    @Test
    fun `test createIndex integration`() {
        // Tester l'integration avec LibClang
        val index = LibClang.createIndex(false)
        assertNotNull(index)
    }

    @Test
    fun `test error codes are accessible`() {
        assertEquals(0, KextractTool.SUCCESS)
        assertEquals(1, KextractTool.FAILURE)
        assertEquals(2, KextractTool.OPTION_ERROR)
        assertEquals(3, KextractTool.INPUT_ERROR)
        assertEquals(4, KextractTool.CLANG_ERROR)
        assertEquals(5, KextractTool.FATAL_ERROR)
        assertEquals(6, KextractTool.OUTPUT_ERROR)
    }

    @Test
    fun `test DEBUG flag`() {
        // Just verify it doesn't throw
        assertNotNull(KextractTool.DEBUG)
    }
}
