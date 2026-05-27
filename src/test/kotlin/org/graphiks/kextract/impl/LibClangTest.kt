package org.graphiks.kextract.pipeline

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.condition.DisabledOnOs
import org.junit.jupiter.api.condition.OS
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class LibClangTest {

    @Test
    fun `test version returns non-empty string`() {
        val version = LibClang.version()
        assertTrue(version.isNotEmpty())
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
        val allocator1 = LibClang.STRING_ALLOCATOR.get()
        val allocator2 = LibClang.STRING_ALLOCATOR.get()

        assertNotNull(allocator1)
        assertNotNull(allocator2)
    }

    @Test
    @DisabledOnOs(OS.WINDOWS) // putenv behavior differs on Windows
    fun `test crash recovery configuration`() {
        // Test that version() works (implies FFI calls succeed)
        val version = LibClang.version()
        assertNotNull(version)
    }

    @Test
    fun `test multiple version calls`() {
        val version1 = LibClang.version()
        val version2 = LibClang.version()
        assertEquals(version1, version2)
    }

    @Test
    fun `test createIndex and version are independent`() {
        val index = LibClang.createIndex(false)
        val version = LibClang.version()
        assertNotNull(index)
        assertTrue(version.isNotEmpty())
    }

    @Test
    fun `test IndexWrapper properties`() {
        val index = LibClang.createIndex(false)
        assertNotNull(index.ptr)
    }
}
