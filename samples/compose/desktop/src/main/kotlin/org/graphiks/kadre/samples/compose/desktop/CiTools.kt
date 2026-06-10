package org.graphiks.kadre.samples.compose.desktop

import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.BroadcastFrameClock
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.Snapshot
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.asComposeCanvas
import androidx.compose.ui.scene.CanvasLayersComposeScene
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.IntSize
import org.graphiks.kadre.samples.compose.infra.awtKeyToComposeKeyEvent
import org.graphiks.kadre.samples.compose.showcase.PlatformContext
import org.graphiks.kadre.samples.compose.showcase.ShowcaseApp
import org.jetbrains.skia.EncodedImageFormat
import org.jetbrains.skia.Surface
import java.awt.Component
import java.awt.event.KeyEvent as AwtKeyEvent
import java.io.File
import kotlinx.coroutines.Dispatchers

@Composable
private fun KeyTestField(onText: (String) -> Unit) {
    val focus = remember { FocusRequester() }
    var text by remember { mutableStateOf("") }
    LaunchedEffect(Unit) { focus.requestFocus() }
    BasicTextField(
        value = text,
        onValueChange = { text = it; onText(it) },
        modifier = Modifier.focusRequester(focus),
    )
}

fun keyboardSelfTest(input: String = "hi"): String {
    val frameClock = BroadcastFrameClock()
    var captured = ""
    val scene = CanvasLayersComposeScene(
        density = Density(1f),
        size = IntSize(400, 200),
        coroutineContext = Dispatchers.Unconfined + frameClock,
    )
    val source = object : Component() {}
    try {
        scene.setContent { KeyTestField { captured = it } }
        val surface = Surface.makeRasterN32Premul(400, 200)
        fun frame() {
            Snapshot.sendApplyNotifications()
            frameClock.sendFrame(System.nanoTime())
            scene.render(surface.canvas.asComposeCanvas(), System.nanoTime())
        }
        repeat(5) { frame() }
        for (ch in input) {
            val awt = AwtKeyEvent(
                source, AwtKeyEvent.KEY_TYPED, System.currentTimeMillis(), 0,
                AwtKeyEvent.VK_UNDEFINED, ch,
            )
            scene.sendKeyEvent(awtKeyToComposeKeyEvent(awt))
            frame()
        }
    } finally {
        scene.close()
    }
    return captured
}

fun captureShowcaseToPng(path: String, width: Int = 800, height: Int = 600) {
    val frameClock = BroadcastFrameClock()
    val scene = CanvasLayersComposeScene(
        density = Density(1f),
        size = IntSize(width, height),
        coroutineContext = Dispatchers.Unconfined + frameClock,
    )
    try {
        scene.setContent { ShowcaseApp(PlatformContext()) }
        val surface = Surface.makeRasterN32Premul(width, height)
        repeat(2) {
            Snapshot.sendApplyNotifications()
            frameClock.sendFrame(System.nanoTime())
            scene.render(surface.canvas.asComposeCanvas(), System.nanoTime())
        }
        val png = surface.makeImageSnapshot().encodeToData(EncodedImageFormat.PNG)
            ?: error("PNG encoding failed")
        File(path).apply { parentFile?.mkdirs() }.writeBytes(png.bytes)
        println("[compose-showcase] Offscreen capture written: $path (${width}×$height)")
    } finally {
        scene.close()
    }
}
