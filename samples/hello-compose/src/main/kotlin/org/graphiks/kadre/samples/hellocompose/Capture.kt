/**
 * Offscreen capture of [DemoUi] for deterministic, headless verification (CI).
 *
 * Renders the exact same Compose content as the live sample, but into an offscreen
 * Skia **raster** surface (no Metal, no Kadre window, no display required), then encodes
 * a PNG. This proves the Compose content rasterizes correctly, independently of the GPU
 * present path and of whether a display is attached.
 */
package org.graphiks.kadre.samples.hellocompose

import androidx.compose.runtime.BroadcastFrameClock
import androidx.compose.runtime.snapshots.Snapshot
import androidx.compose.ui.graphics.asComposeCanvas
import androidx.compose.ui.scene.CanvasLayersComposeScene
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.IntSize
import org.jetbrains.skia.EncodedImageFormat
import org.jetbrains.skia.Surface
import java.io.File
import kotlinx.coroutines.Dispatchers

/**
 * Renders [DemoUi] at [width]×[height] into an offscreen raster surface and writes a PNG to [path].
 */
fun captureDemoUiToPng(path: String, width: Int = 800, height: Int = 600) {
    val frameClock = BroadcastFrameClock()
    val scene = CanvasLayersComposeScene(
        density = Density(1f),
        size = IntSize(width, height),
        coroutineContext = Dispatchers.Unconfined + frameClock,
    )
    try {
        scene.setContent { DemoUi() }

        val surface = Surface.makeRasterN32Premul(width, height)
        // Pump a couple of frames so composition + layout + first draw settle.
        repeat(2) {
            Snapshot.sendApplyNotifications()
            frameClock.sendFrame(System.nanoTime())
            scene.render(surface.canvas.asComposeCanvas(), System.nanoTime())
        }

        val png = surface.makeImageSnapshot().encodeToData(EncodedImageFormat.PNG)
            ?: error("PNG encoding failed")
        File(path).writeBytes(png.bytes)
        println("[hello-compose] Offscreen capture written: $path (${width}×$height)")
    } finally {
        scene.close()
    }
}
