/*
 * Copyright (c) 2020, 2025, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this code; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */
package org.openjdk.kextract.newimpl

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertTrue

class OptionsTest {
    @Test
    fun `test default Options`() {
        val options = Options.builder().build()
        assertEquals("", options.targetPackage)
        assertEquals(".", options.outputDir)
        assertEquals(false, options.useSystemLoadLibrary)
        assertTrue(options.clangArgs.isEmpty())
        assertTrue(options.libraries.isEmpty())
        assertNull(options.sharedClassName)
    }

    @Test
    fun `test Builder sets all properties`() {
        val options = Options.builder()
            .setTargetPackage("org.test")
            .setOutputDir("/output")
            .setUseSystemLoadLibrary(true)
            .addClangArg("-I/include")
            .addLibrary(Options.Library.parse("mylib"))
            .setSharedClassName("Symbols")
            .build()

        assertEquals("org.test", options.targetPackage)
        assertEquals("/output", options.outputDir)
        assertEquals(true, options.useSystemLoadLibrary)
        assertEquals(listOf("-I/include"), options.clangArgs)
        assertEquals(1, options.libraries.size)
    }

    @Test
    fun `test Library parse with name`() {
        val lib = Options.Library.parse("c")
        assertEquals("c", lib.libSpec)
        assertEquals(Options.Library.SpecKind.NAME, lib.specKind)
    }

    @Test
    fun `test Library parse with path`() {
        val lib = Options.Library.parse(":lib/c.so")
        assertEquals("lib/c.so", lib.libSpec)
        assertEquals(Options.Library.SpecKind.PATH, lib.specKind)
    }

    @Test
    fun `test Library parse with empty path throws`() {
        assertThrows<IllegalArgumentException> {
            Options.Library.parse(":")
        }
    }

    @Test
    fun `test toQuotedName escapes backslashes`() {
        val lib = Options.Library("path\\to\\lib", Options.Library.SpecKind.PATH)
        assertEquals("path\\\\to\\\\lib", Options.Library.toQuotedName(lib))
    }

    @Test
    fun `test Builder can only be used once`() {
        val builder = Options.builder()
        builder.build()
        assertThrows<IllegalStateException> {
            builder.build()
        }
    }

    @Test
    fun `test libraries are unmodifiable`() {
        val options = Options.builder()
            .addLibrary(Options.Library.parse("lib1"))
            .build()

        // Verify that the list is unmodifiable by trying to add to it
        @Suppress("UNCHECKED_CAST")
        val mutableLibraries = options.libraries as MutableList<Options.Library>
        assertThrows<UnsupportedOperationException> {
            mutableLibraries.add(Options.Library.parse("lib2"))
        }
    }

    @Test
    fun `test addIncludeSymbol works`() {
        val builder = Options.builder()
        builder.addIncludeSymbol(
            org.openjdk.kextract.impl.IncludeHelper.IncludeKind.CONSTANT,
            "MY_CONSTANT"
        )
        val options = builder.build()
        // Just verify it doesn't throw
        assertTrue(true)
    }

    @Test
    fun `test setDumpIncludeFile works`() {
        val options = Options.builder()
            .setDumpIncludeFile("dump.txt")
            .build()
        // Verify the file was set on the include helper
        assertEquals("dump.txt", options.includeHelper.dumpIncludesFile)
    }
}
