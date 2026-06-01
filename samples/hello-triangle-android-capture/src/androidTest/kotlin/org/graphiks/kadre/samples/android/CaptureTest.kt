package org.graphiks.kadre.samples.android

import android.graphics.Bitmap
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.io.PlatformTestStorageRegistry
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CaptureTest {
    @Test
    fun capturesTriangle() {
        val img = captureTriangle()
        assertTrue("dimensions", img.width == CAPTURE_WIDTH && img.height == CAPTURE_HEIGHT)

        // Encode the capture as PNG via TestStorage: the runner pulls it back to the host,
        // where CI uploads it as an artifact.
        writePng(img)

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
        assertTrue("non-black pixels (triangle): $nonBlack/$px", nonBlack in (px / 20)..(px / 2))
        assertTrue("R/G/B regions: r=$red g=$green b=$blue", red > 0 && green > 0 && blue > 0)
    }

    private fun writePng(img: CaptureImage) {
        val pixels = IntArray(img.width * img.height)
        for (i in pixels.indices) {
            val r = img.rgba[i * 4].toInt() and 0xFF
            val g = img.rgba[i * 4 + 1].toInt() and 0xFF
            val b = img.rgba[i * 4 + 2].toInt() and 0xFF
            val a = img.rgba[i * 4 + 3].toInt() and 0xFF
            pixels[i] = (a shl 24) or (r shl 16) or (g shl 8) or b
        }
        val bitmap = Bitmap.createBitmap(img.width, img.height, Bitmap.Config.ARGB_8888)
        bitmap.setPixels(pixels, 0, img.width, 0, 0, img.width, img.height)

        // Written via TestStorage: the runner pulls the file back to the host (under
        // build/outputs/.../additional_output), bypassing scoped storage.
        val storage = PlatformTestStorageRegistry.getInstance()
        storage.openOutputFile("hello-triangle-android.png").use {
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, it)
        }
        bitmap.recycle()
        println("[android-visual] PNG capture written via TestStorage")
    }
}
