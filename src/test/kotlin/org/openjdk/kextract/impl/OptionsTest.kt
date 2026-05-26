/*
 * Copyright (c) 2025, Oracle and/or its affiliates. All rights reserved.
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
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 */
package org.openjdk.kextract.impl

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

/**
 * Unit tests for Options class (Kotlin version).
 */
class OptionsTest {

    @Test
    fun testBuilderCreation() {
        val options = Options.builder()
            .setTargetPackage("com.example")
            .setOutputDir("/tmp/output")
            .build()
        
        assertEquals("com.example", options.targetPackage)
        assertEquals("/tmp/output", options.outputDir)
    }

    @Test
    fun testAddClangArg() {
        val options = Options.builder()
            .addClangArg("-I/include")
            .addClangArg("-DDEBUG")
            .build()
        
        assertEquals(2, options.clangArgs.size)
        assertEquals("-I/include", options.clangArgs[0])
        assertEquals("-DDEBUG", options.clangArgs[1])
    }

    @Test
    fun testAddLibrary() {
        val library = Options.Library.parse("c")
        val options = Options.builder()
            .addLibrary(library)
            .build()
        
        assertEquals(1, options.libraries.size)
        assertEquals("c", options.libraries[0].libSpec)
        assertEquals(Options.SpecKind.NAME, options.libraries[0].specKind)
    }

    @Test
    fun testLibraryParsePath() {
        val library = Options.Library.parse(":/usr/lib/libc.so")
        assertEquals("/usr/lib/libc.so", library.libSpec)
        assertEquals(Options.SpecKind.PATH, library.specKind)
    }

    @Test
    fun testLibraryParseName() {
        val library = Options.Library.parse("m")
        assertEquals("m", library.libSpec)
        assertEquals(Options.SpecKind.NAME, library.specKind)
    }

    @Test
    fun testLibraryParseEmptyPath() {
        assertThrows(IllegalArgumentException::class.java) {
            Options.Library.parse(":")
        }
    }

    @Test
    fun testToQuotedName() {
        val library = Options.Library.parse("c")
        assertEquals("c", Options.Library.toQuotedName(library))
        
        val libraryWithBackslash = Options.Library.parse("path\\to\\lib")
        assertEquals("path\\\\to\\\\lib", Options.Library.toQuotedName(libraryWithBackslash))
    }

    @Test
    fun testSetUseSystemLoadLibrary() {
        val options = Options.builder()
            .setUseSystemLoadLibrary(true)
            .build()
        
        assertTrue(options.useSystemLoadLibrary)
    }

    @Test
    fun testSharedClassName() {
        val options = Options.builder()
            .setSharedClassName("MySharedClass")
            .build()
        
        assertEquals("MySharedClass", options.sharedClassName)
    }

    @Test
    fun testBuilder() {
        val builder = Options.builder()
        assertNotNull(builder)
    }

    @Test
    fun testIncludeHelper() {
        val options = Options.builder()
            .setDumpIncludeFile("/tmp/includes.txt")
            .build()
        
        assertNotNull(options.includeHelper)
        assertEquals("/tmp/includes.txt", options.includeHelper.dumpIncludesFile)
    }

    @Test
    fun testAddIncludeSymbol() {
        val options = Options.builder()
            .addIncludeSymbol(IncludeHelper.IncludeKind.FUNCTION, "myFunction")
            .build()
        
        assertNotNull(options)
        // Verify symbol was added by checking if IncludeHelper is enabled
        assertTrue(options.includeHelper.isEnabled())
    }

    @Test
    fun testIncludeKindOptionName() {
        assertEquals("include-constant", IncludeHelper.IncludeKind.CONSTANT.optionName())
        assertEquals("include-var", IncludeHelper.IncludeKind.VAR.optionName())
        assertEquals("include-function", IncludeHelper.IncludeKind.FUNCTION.optionName())
        assertEquals("include-typedef", IncludeHelper.IncludeKind.TYPEDEF.optionName())
        assertEquals("include-struct", IncludeHelper.IncludeKind.STRUCT.optionName())
        assertEquals("include-union", IncludeHelper.IncludeKind.UNION.optionName())
    }
}
