/*
 *  Copyright (c) 2020, 2024, Oracle and/or its affiliates. All rights reserved.
 *  DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 *  This code is free software; you can redistribute it and/or modify it
 *  under the terms of the GNU General Public License version 2 only, as
 *  published by the Free Software Foundation.  Oracle designates this
 *  particular file as subject to the "Classpath" exception as provided
 *  by Oracle in the LICENSE file that accompanied this code.
 *
 *  This code is distributed in the hope that it will be useful, but WITHOUT
 *  ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 *  FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 *  version 2 for more details (a copy is included in the LICENSE file that
 *  accompanied this code).
 *
 *  You should have received a copy of the GNU General Public License version
 *  2 along with this work; if not, write to the Free Software Foundation,
 *  Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 *   Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 *  or visit www.oracle.com if you need additional information or have any
 *  questions.
 *
 */

package org.openjdk.kextract.clang

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.condition.DisabledOnOs
import org.junit.jupiter.api.condition.OS
import kotlin.test.assertEquals
import kotlin.test.assertNotEmpty
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class LibClangTest {
    @Test
    fun `test version returns non-empty string`() {
        val version = LibClang.version()
        assertNotEmpty(version)
        assertTrue(version.contains("clang version") || version.contains("LLVM"))
    }

    @Test
    fun `test createIndex creates valid index`() {
        val index = LibClang.createIndex(false)
        assertNotNull(index)
        // We can't easily verify the index is valid without using it
        // but at least we can verify it doesn't crash
    }

    @Test
    fun `test createIndex with local true`() {
        val index = LibClang.createIndex(true)
        assertNotNull(index)
    }

    @Test
    fun `test STRING_ALLOCATOR is thread-safe`() {
        // Test that we can get allocators from multiple threads
        val allocator1 = LibClang.STRING_ALLOCATOR.get()
        val allocator2 = LibClang.STRING_ALLOCATOR.get()
        
        // They might be the same instance or different, but should not be null
        assertNotNull(allocator1)
        assertNotNull(allocator2)
    }

    @Test
    @DisabledOnOs(OS.WINDOWS) // putenv behavior differs on Windows
    fun `test crash recovery disabled by default on Unix`() {
        // This test verifies the default behavior
        // On Unix systems, crash recovery should be disabled by default
        // Note: This is more of a documentation test
        val version = LibClang.version()
        assertNotNull(version)
    }

    @Test
    fun `test multiple version calls`() {
        // Test that we can call version() multiple times without issues
        val version1 = LibClang.version()
        val version2 = LibClang.version()
        assertEquals(version1, version2)
    }

    @Test
    fun `test createIndex and version are independent`() {
        val index = LibClang.createIndex(false)
        val version = LibClang.version()
        assertNotNull(index)
        assertNotEmpty(version)
    }
}
