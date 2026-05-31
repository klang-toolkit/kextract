/**
 * Headless self-test for the keyboard-forwarding path.
 *
 * Drives an offscreen ComposeScene containing a focused text field, then sends the exact
 * same kind of events the live sample produces (real AWT `KEY_TYPED` events converted via
 * the desktop `toComposeEvent`), and prints the resulting text. This deterministically
 * verifies the whole chain — AWT event → toComposeEvent → ComposeScene → text insertion —
 * without a display, independently of Kadre.
 */
package org.graphiks.kadre.samples.hellocompose

import androidx.compose.foundation.text.BasicTextField
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
import org.jetbrains.skia.Surface
import java.awt.Component
import java.awt.event.KeyEvent as AwtKeyEvent
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

/** Types [input] into an offscreen focused text field and returns what the field received. */
fun keyboardSelfTest(input: String = "hi"): String {
    val frameClock = androidx.compose.runtime.BroadcastFrameClock()
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
        // Compose, lay out and let the focus request take effect.
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
