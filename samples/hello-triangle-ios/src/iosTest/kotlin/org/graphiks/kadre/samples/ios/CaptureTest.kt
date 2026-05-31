@file:OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
package org.graphiks.kadre.samples.ios

import platform.Metal.MTLCreateSystemDefaultDevice
import kotlin.test.Test
import kotlin.test.assertTrue

/**
 * iOS offscreen GPU capture — best-effort.
 *
 * The headless iOS simulator (CI / K/N test harness) does NOT expose a Metal device
 * (MTLCreateSystemDefaultDevice == null): the test then skips cleanly. On a
 * real device (or a simulator with Metal), it renders the triangle and verifies its presence.
 */
class CaptureTest {
    @Test
    fun capturesTriangleIfMetalAvailable() {
        if (MTLCreateSystemDefaultDevice() == null) {
            println("[ios-visual] Pas de device Metal (simulateur headless) — capture sautée. " +
                "Le rendu réel nécessite un device iOS (ou un simulateur avec Metal).")
            return
        }

        val img = captureTriangle()
        assertTrue(img.width == CAPTURE_WIDTH && img.height == CAPTURE_HEIGHT, "dimensions")

        var nonBlack = 0; var red = 0; var green = 0; var blue = 0
        val px = img.width * img.height
        for (i in 0 until px) {
            val r = img.rgba[i * 4].toInt() and 0xFF
            val g = img.rgba[i * 4 + 1].toInt() and 0xFF
            val b = img.rgba[i * 4 + 2].toInt() and 0xFF
            if (r > 16 || g > 16 || b > 16) nonBlack++
            if (r > 128 && g < 96 && b < 96) red++
            if (g > 128 && r < 96 && b < 96) green++
            if (b > 128 && r < 96 && g < 96) blue++
        }
        assertTrue(nonBlack in (px / 20)..(px / 2), "pixels non-noirs (triangle) : $nonBlack/$px")
        assertTrue(red > 0 && green > 0 && blue > 0, "régions R/G/B : r=$red g=$green b=$blue")
    }
}
