package org.graphiks.kadre.samples.pong

/** Quad en coordonnées normalisées [0..1] pour le rendu de score. */
data class Quad(val x: Double, val y: Double, val w: Double, val h: Double)

/**
 * Police bitmap hardcodée 5×7 pixels pour les chiffres 0–9.
 * Chaque rangée est encodée sur 5 bits (MSB = colonne gauche).
 *
 * Redmine #77.
 */
object BitmapFont {

    // Chaque IntArray = 7 rangées de 5 bits (colonnes 0-4, bit 4 = gauche)
    private val DIGITS = arrayOf(
        // 0
        intArrayOf(0b01110, 0b10001, 0b10011, 0b10101, 0b11001, 0b10001, 0b01110),
        // 1
        intArrayOf(0b00100, 0b01100, 0b00100, 0b00100, 0b00100, 0b00100, 0b01110),
        // 2
        intArrayOf(0b01110, 0b10001, 0b00001, 0b00110, 0b01000, 0b10000, 0b11111),
        // 3
        intArrayOf(0b11111, 0b00001, 0b00010, 0b00110, 0b00001, 0b10001, 0b01110),
        // 4
        intArrayOf(0b00010, 0b00110, 0b01010, 0b10010, 0b11111, 0b00010, 0b00010),
        // 5
        intArrayOf(0b11111, 0b10000, 0b11110, 0b00001, 0b00001, 0b10001, 0b01110),
        // 6
        intArrayOf(0b00110, 0b01000, 0b10000, 0b11110, 0b10001, 0b10001, 0b01110),
        // 7
        intArrayOf(0b11111, 0b00001, 0b00010, 0b00100, 0b01000, 0b01000, 0b01000),
        // 8
        intArrayOf(0b01110, 0b10001, 0b10001, 0b01110, 0b10001, 0b10001, 0b01110),
        // 9
        intArrayOf(0b01110, 0b10001, 0b10001, 0b01111, 0b00001, 0b00010, 0b01100),
    )

    /**
     * Génère les quads d'un chiffre unique.
     *
     * @param digit    Chiffre à rendre (0–9).
     * @param x        Coin supérieur gauche X (coordonnées normalisées).
     * @param y        Coin supérieur gauche Y (coordonnées normalisées).
     * @param pixelSize Taille d'un pixel bitmap en coordonnées normalisées.
     * @return Liste des quads correspondant aux pixels allumés.
     */
    fun renderDigit(digit: Int, x: Double, y: Double, pixelSize: Double): List<Quad> {
        require(digit in 0..9) { "digit must be 0..9, got $digit" }
        val rows = DIGITS[digit]
        val quads = mutableListOf<Quad>()
        for (row in 0..6) {
            val rowBits = rows[row]
            for (col in 0..4) {
                if (rowBits and (1 shl (4 - col)) != 0) {
                    quads.add(
                        Quad(
                            x = x + col * pixelSize,
                            y = y + row * pixelSize,
                            w = pixelSize,
                            h = pixelSize,
                        )
                    )
                }
            }
        }
        return quads
    }

    /**
     * Génère les quads d'un nombre entier positif (plusieurs chiffres).
     *
     * @param n         Nombre à rendre (>= 0).
     * @param x         Coin supérieur gauche X du premier chiffre.
     * @param y         Coin supérieur gauche Y.
     * @param pixelSize Taille d'un pixel bitmap en coordonnées normalisées.
     * @return Liste concaténée des quads de tous les chiffres.
     */
    fun renderNumber(n: Int, x: Double, y: Double, pixelSize: Double): List<Quad> {
        val digits = n.toString().map { it.digitToInt() }
        val spacing = 6 * pixelSize  // 5 pixels de large + 1 pixel de gap
        return digits.flatMapIndexed { i, d ->
            renderDigit(d, x + i * spacing, y, pixelSize)
        }
    }
}
