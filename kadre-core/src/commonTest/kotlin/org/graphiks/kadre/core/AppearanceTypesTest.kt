package org.graphiks.kadre.core

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class AppearanceTypesTest {
    @Test
    fun cursorImageEqualityUsesRgbaContent() {
        val first = CursorImage(byteArrayOf(1, 2, 3, 4), width = 1, height = 1)
        val same = CursorImage(byteArrayOf(1, 2, 3, 4), width = 1, height = 1)
        val differentPixels = CursorImage(byteArrayOf(1, 2, 3, 5), width = 1, height = 1)
        val differentHotspot = CursorImage(byteArrayOf(1, 2, 3, 4), width = 1, height = 1, hotspotX = 1)

        assertEquals(first, same)
        assertEquals(first.hashCode(), same.hashCode())
        assertNotEquals(first, differentPixels)
        assertNotEquals(first, differentHotspot)
    }

    @Test
    fun iconEqualityUsesRgbaContent() {
        val first = Icon(byteArrayOf(1, 2, 3, 4), width = 1, height = 1)
        val same = Icon(byteArrayOf(1, 2, 3, 4), width = 1, height = 1)
        val differentPixels = Icon(byteArrayOf(1, 2, 3, 5), width = 1, height = 1)
        val differentSize = Icon(byteArrayOf(1, 2, 3, 4), width = 2, height = 1)

        assertEquals(first, same)
        assertEquals(first.hashCode(), same.hashCode())
        assertNotEquals(first, differentPixels)
        assertNotEquals(first, differentSize)
    }
}
