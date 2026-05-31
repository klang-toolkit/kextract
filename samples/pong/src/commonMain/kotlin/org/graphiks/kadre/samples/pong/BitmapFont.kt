package org.graphiks.kadre.samples.pong

/** Quad in normalized coordinates [0..1] for score rendering. */
data class Quad(val x: Double, val y: Double, val w: Double, val h: Double)

/**
 * Hardcoded 5×7 pixel bitmap font for digits 0–9.
 * Each row is encoded on 5 bits (MSB = left column).
 *
 * .
 */
object BitmapFont {

    // Each IntArray = 7 rows of 5 bits (columns 0-4, bit 4 = left)
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
     * Generates the quads of a single digit.
     *
     * @param digit    Digit to render (0–9).
     * @param x        Top-left corner X (normalized coordinates).
     * @param y        Top-left corner Y (normalized coordinates).
     * @param pixelSize Size of one bitmap pixel in normalized coordinates.
     * @return List of quads corresponding to the lit pixels.
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
     * Generates the quads of a positive integer (multiple digits).
     *
     * @param n         Number to render (>= 0).
     * @param x         Top-left corner X of the first digit.
     * @param y         Top-left corner Y.
     * @param pixelSize Size of one bitmap pixel in normalized coordinates.
     * @return Concatenated list of quads of all digits.
     */
    fun renderNumber(n: Int, x: Double, y: Double, pixelSize: Double): List<Quad> {
        val digits = n.toString().map { it.digitToInt() }
        val spacing = 6 * pixelSize  // 5 pixels wide + 1 pixel gap
        return digits.flatMapIndexed { i, d ->
            renderDigit(d, x + i * spacing, y, pixelSize)
        }
    }
}
