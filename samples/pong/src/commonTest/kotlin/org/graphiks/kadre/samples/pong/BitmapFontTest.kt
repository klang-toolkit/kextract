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
    fun `renderDigit 0 retourne des quads non vides`() {
        val quads = BitmapFont.renderDigit(0, 0.0, 0.0, pixelSize)
        assertTrue(quads.isNotEmpty(), "renderDigit(0) devrait retourner des quads")
    }

    @Test
    fun `renderDigit 1 a moins de quads que 8`() {
        val quads1 = BitmapFont.renderDigit(1, 0.0, 0.0, pixelSize)
        val quads8 = BitmapFont.renderDigit(8, 0.0, 0.0, pixelSize)
        assertTrue(
            quads1.size < quads8.size,
            "Le chiffre 1 (${quads1.size} quads) devrait avoir moins de pixels allumés que 8 (${quads8.size} quads)"
        )
    }

    @Test
    fun `renderDigit retourne au maximum 35 quads (5x7)`() {
        for (d in 0..9) {
            val quads = BitmapFont.renderDigit(d, 0.0, 0.0, pixelSize)
            assertTrue(quads.size <= 35, "renderDigit($d) a ${quads.size} quads, maximum attendu 35")
        }
    }

    @Test
    fun `renderDigit positionne les quads correctement`() {
        val x0 = 0.1
        val y0 = 0.2
        val quads = BitmapFont.renderDigit(0, x0, y0, pixelSize)
        // Tous les quads doivent être dans le rectangle 5×7 pixels
        for (q in quads) {
            assertTrue(q.x >= x0, "quad.x ${q.x} < x0 $x0")
            assertTrue(q.y >= y0, "quad.y ${q.y} < y0 $y0")
            assertTrue(q.x < x0 + 5 * pixelSize + 1e-9, "quad.x ${q.x} hors borne droite")
            assertTrue(q.y < y0 + 7 * pixelSize + 1e-9, "quad.y ${q.y} hors borne basse")
            assertEquals(pixelSize, q.w, absoluteTolerance = 1e-12)
            assertEquals(pixelSize, q.h, absoluteTolerance = 1e-12)
        }
    }

    @Test
    fun `renderDigit chiffre invalide lève IllegalArgumentException`() {
        assertFailsWith<IllegalArgumentException> {
            BitmapFont.renderDigit(10, 0.0, 0.0, pixelSize)
        }
        assertFailsWith<IllegalArgumentException> {
            BitmapFont.renderDigit(-1, 0.0, 0.0, pixelSize)
        }
    }

    @Test
    fun `tous les chiffres 0 à 9 rendent sans erreur`() {
        for (d in 0..9) {
            val quads = BitmapFont.renderDigit(d, 0.0, 0.0, pixelSize)
            assertTrue(quads.isNotEmpty(), "renderDigit($d) ne devrait pas être vide")
        }
    }

    // -------------------------------------------------------------------------
    // renderNumber tests
    // -------------------------------------------------------------------------

    @Test
    fun `renderNumber 42 retourne des quads pour 2 chiffres`() {
        val quads4 = BitmapFont.renderDigit(4, 0.0, 0.0, pixelSize)
        val quads2 = BitmapFont.renderDigit(2, 0.0, 0.0, pixelSize)
        val quads42 = BitmapFont.renderNumber(42, 0.0, 0.0, pixelSize)
        // Le nombre de quads doit correspondre aux deux chiffres individuels
        assertEquals(quads4.size + quads2.size, quads42.size)
    }

    @Test
    fun `renderNumber chiffre seul équivaut à renderDigit`() {
        val singleDigit = BitmapFont.renderDigit(7, 0.0, 0.0, pixelSize)
        val number = BitmapFont.renderNumber(7, 0.0, 0.0, pixelSize)
        assertEquals(singleDigit.size, number.size)
    }

    @Test
    fun `renderNumber décale le second chiffre vers la droite`() {
        val spacing = 6 * pixelSize
        val x0 = 0.0
        val quadsFirst = BitmapFont.renderDigit(1, x0, 0.0, pixelSize)
        val quadsSecond = BitmapFont.renderDigit(2, x0 + spacing, 0.0, pixelSize)
        val quadsNumber = BitmapFont.renderNumber(12, x0, 0.0, pixelSize)

        assertEquals(quadsFirst.size + quadsSecond.size, quadsNumber.size)
        // Vérifie que les positions correspondent
        val expected = (quadsFirst + quadsSecond).sortedWith(compareBy({ it.y }, { it.x }))
        val actual = quadsNumber.sortedWith(compareBy({ it.y }, { it.x }))
        for (i in expected.indices) {
            assertEquals(expected[i].x, actual[i].x, absoluteTolerance = 1e-12)
            assertEquals(expected[i].y, actual[i].y, absoluteTolerance = 1e-12)
        }
    }
}
