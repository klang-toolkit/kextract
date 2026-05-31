package io.ygdrasil.koreos.samples.android

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CaptureTest {
    @Test
    fun capturesTriangle() {
        val img = captureTriangle()
        assertTrue("dimensions", img.width == CAPTURE_WIDTH && img.height == CAPTURE_HEIGHT)
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
        assertTrue("pixels non-noirs (triangle): $nonBlack/$px", nonBlack in (px / 20)..(px / 2))
        assertTrue("régions R/G/B: r=$red g=$green b=$blue", red > 0 && green > 0 && blue > 0)
    }
}
