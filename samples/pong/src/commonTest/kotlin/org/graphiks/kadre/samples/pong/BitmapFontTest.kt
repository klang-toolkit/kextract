package org.graphiks.kadre.samples.pong

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

class BitmapFontTest {

    private val pixelSize = 0.01

    // -------------------------------------------------------------------------
    // renderDigit tests
    // -------------------------------------------------------------------------

    @Test
    fun `renderDigit 0 returns non-empty quads`() {
        val quads = BitmapFont.renderDigit(0, 0.0, 0.0, pixelSize)
        assertTrue(quads.isNotEmpty(), "renderDigit(0) should return quads")
    }

    @Test
    fun `renderDigit 1 has fewer quads than 8`() {
        val quads1 = BitmapFont.renderDigit(1, 0.0, 0.0, pixelSize)
        val quads8 = BitmapFont.renderDigit(8, 0.0, 0.0, pixelSize)
        assertTrue(
            quads1.size < quads8.size,
            "Digit 1 (${quads1.size} quads) should have fewer lit pixels than 8 (${quads8.size} quads)"
        )
    }

    @Test
    fun `renderDigit returns at most 35 quads (5x7)`() {
        for (d in 0..9) {
            val quads = BitmapFont.renderDigit(d, 0.0, 0.0, pixelSize)
            assertTrue(quads.size <= 35, "renderDigit($d) has ${quads.size} quads, expected maximum 35")
        }
    }

    @Test
    fun `renderDigit positions the quads correctly`() {
        val x0 = 0.1
        val y0 = 0.2
        val quads = BitmapFont.renderDigit(0, x0, y0, pixelSize)
        // All quads must be within the 5×7 pixel rectangle
        for (q in quads) {
            assertTrue(q.x >= x0, "quad.x ${q.x} < x0 $x0")
            assertTrue(q.y >= y0, "quad.y ${q.y} < y0 $y0")
            assertTrue(q.x < x0 + 5 * pixelSize + 1e-9, "quad.x ${q.x} out of right bound")
            assertTrue(q.y < y0 + 7 * pixelSize + 1e-9, "quad.y ${q.y} out of bottom bound")
            assertEquals(pixelSize, q.w, absoluteTolerance = 1e-12)
            assertEquals(pixelSize, q.h, absoluteTolerance = 1e-12)
        }
    }

    @Test
    fun `renderDigit invalid digit throws IllegalArgumentException`() {
        assertFailsWith<IllegalArgumentException> {
            BitmapFont.renderDigit(10, 0.0, 0.0, pixelSize)
        }
        assertFailsWith<IllegalArgumentException> {
            BitmapFont.renderDigit(-1, 0.0, 0.0, pixelSize)
        }
    }

    @Test
    fun `all digits 0 to 9 render without error`() {
        for (d in 0..9) {
            val quads = BitmapFont.renderDigit(d, 0.0, 0.0, pixelSize)
            assertTrue(quads.isNotEmpty(), "renderDigit($d) should not be empty")
        }
    }

    // -------------------------------------------------------------------------
    // renderNumber tests
    // -------------------------------------------------------------------------

    @Test
    fun `renderNumber 42 returns quads for 2 digits`() {
        val quads4 = BitmapFont.renderDigit(4, 0.0, 0.0, pixelSize)
        val quads2 = BitmapFont.renderDigit(2, 0.0, 0.0, pixelSize)
        val quads42 = BitmapFont.renderNumber(42, 0.0, 0.0, pixelSize)
        // The number of quads must match the two individual digits
        assertEquals(quads4.size + quads2.size, quads42.size)
    }

    @Test
    fun `renderNumber single digit equals renderDigit`() {
        val singleDigit = BitmapFont.renderDigit(7, 0.0, 0.0, pixelSize)
        val number = BitmapFont.renderNumber(7, 0.0, 0.0, pixelSize)
        assertEquals(singleDigit.size, number.size)
    }

    @Test
    fun `renderNumber shifts the second digit to the right`() {
        val spacing = 6 * pixelSize
        val x0 = 0.0
        val quadsFirst = BitmapFont.renderDigit(1, x0, 0.0, pixelSize)
        val quadsSecond = BitmapFont.renderDigit(2, x0 + spacing, 0.0, pixelSize)
        val quadsNumber = BitmapFont.renderNumber(12, x0, 0.0, pixelSize)

        assertEquals(quadsFirst.size + quadsSecond.size, quadsNumber.size)
        // Verify that the positions match
        val expected = (quadsFirst + quadsSecond).sortedWith(compareBy({ it.y }, { it.x }))
        val actual = quadsNumber.sortedWith(compareBy({ it.y }, { it.x }))
        for (i in expected.indices) {
            assertEquals(expected[i].x, actual[i].x, absoluteTolerance = 1e-12)
            assertEquals(expected[i].y, actual[i].y, absoluteTolerance = 1e-12)
        }
    }
}
