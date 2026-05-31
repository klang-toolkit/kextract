/**
 * Sample hello-compose — interactive Jetpack Compose UI inside a native Kadre window.
 *
 * Opens an 800×600 Kadre window and renders a Material3 Compose UI into its CAMetalLayer
 * via Skiko's Metal backend (see [ComposeMetalRenderer]). Mouse events from Kadre are
 * forwarded to the ComposeScene, so the button below is genuinely clickable.
 *
 * Frame loop (same pattern as hello-triangle):
 *   aboutToWait → requestRedraw → WindowEvent.RedrawRequested → renderer.renderFrame()
 *
 * Usage: ./gradlew :samples:hello-compose:run
 * Requirements: macOS arm64 with JDK 25 (run on the main thread by Gradle).
 */
package org.graphiks.kadre.samples.hellocompose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerButton
import androidx.compose.ui.unit.dp
import java.awt.Component
import java.awt.event.InputEvent
import java.awt.event.KeyEvent as AwtKeyEvent
import org.graphiks.kadre.ActiveEventLoop
import org.graphiks.kadre.ApplicationHandler
import org.graphiks.kadre.EventLoop
import org.graphiks.kadre.PhysicalSize
import org.graphiks.kadre.WindowAttributes
import org.graphiks.kadre.WindowId
import org.graphiks.kadre.core.Key as KadreKey
import org.graphiks.kadre.core.MouseButton
import org.graphiks.kadre.core.KeyState
import org.graphiks.kadre.core.RawWindowHandle
import org.graphiks.kadre.core.Window
import org.graphiks.kadre.core.WindowEvent

/**
 * The Compose UI shown inside the Kadre window.
 *
 * A counter incremented by a Material3 button — clicking it proves the whole chain works:
 * input forwarding → snapshot apply → recomposition → frame clock → Skia/Metal present.
 */
@androidx.compose.runtime.Composable
fun DemoUi() {
    MaterialTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            var clicks by remember { mutableStateOf(0) }
            var typed by remember { mutableStateOf("") }
            Column(
                modifier = Modifier.fillMaxSize().padding(32.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text("Jetpack Compose in a Kadre window 🪟", style = MaterialTheme.typography.headlineSmall)
                Text("Rendered via Skiko → Metal (CAMetalLayer)", style = MaterialTheme.typography.bodyMedium)
                Button(onClick = { clicks++ }) {
                    Text("Clicked $clicks times")
                }
                OutlinedTextField(
                    value = typed,
                    onValueChange = { typed = it },
                    label = { Text("Type here") },
                    singleLine = true,
                )
            }
        }
    }
}

class HelloComposeApp : ApplicationHandler {

    private var window: Window? = null
    private var renderer: ComposeMetalRenderer? = null

    // Keyboard forwarding builds real AWT KeyEvents (required for text input — see
    // ComposeMetalRenderer.sendKey). The source Component is created lazily and guarded:
    // if AWT init misbehaves under -XstartOnFirstThread, keyboard is disabled, not fatal.
    private var keyboardDisabled = false
    private val keyEventSource: Component? by lazy {
        runCatching { object : Component() {} }.getOrNull()
    }

    override fun canCreateSurfaces(eventLoop: ActiveEventLoop) {
        println("[hello-compose] canCreateSurfaces — creating window + ComposeScene")

        val win = eventLoop.createWindow(
            WindowAttributes(
                title = "Hello Compose — Kadre + Skiko/Metal",
                size = PhysicalSize(width = 800, height = 600),
                visible = true,
                resizable = true,
            ),
        )
        window = win

        val handle = win.rawWindowHandle
        if (handle !is RawWindowHandle.AppKit || handle.nsLayer == 0L) {
            println("[hello-compose] Unsupported platform (CAMetalLayer required): $handle")
            eventLoop.exit()
            return
        }

        val r = ComposeMetalRenderer(handle.nsLayer, win.scaleFactor)
        val inner = win.innerSize
        r.resize(inner.width, inner.height, win.scaleFactor)
        r.setContent { DemoUi() }
        renderer = r

        println("[hello-compose] Ready — ${inner.width}×${inner.height} @ ${win.scaleFactor}x")
    }

    override fun aboutToWait(eventLoop: ActiveEventLoop) {
        // Continuous redraw (~vsync) keeps recomposition/animation pumped — like hello-triangle.
        window?.requestRedraw()
    }

    override fun windowEvent(eventLoop: ActiveEventLoop, windowId: WindowId, event: Any) {
        val r = renderer
        when (event) {
            is WindowEvent.RedrawRequested -> r?.renderFrame()

            is WindowEvent.PointerMoved ->
                r?.onPointerMoved(event.position.x, event.position.y)

            is WindowEvent.MouseInput -> {
                val (bit, button) = mapButton(event.button) ?: return
                r?.onPointerButton(bit, event.state == KeyState.Pressed, button)
            }

            is WindowEvent.MouseWheel -> r?.onScroll(event.deltaX, event.deltaY)
            is WindowEvent.PointerEntered -> r?.onPointerEnter()
            is WindowEvent.PointerLeft -> r?.onPointerExit()

            is WindowEvent.KeyboardInput -> forwardKey(event, r)

            is WindowEvent.Resized -> {
                val win = window ?: return
                r?.resize(event.size.width, event.size.height, win.scaleFactor)
            }

            is WindowEvent.ScaleFactorChanged -> {
                val win = window ?: return
                val inner = win.innerSize
                r?.resize(inner.width, inner.height, event.factor)
            }

            is WindowEvent.CloseRequested -> {
                println("[hello-compose] CloseRequested — closing")
                r?.dispose()
                renderer = null
                eventLoop.exit()
            }

            else -> { /* ignore (keyboard forwarding is out of scope for this POC) */ }
        }
    }

    /** Maps a Kadre [MouseButton] to (Compose PointerButtons bit, [PointerButton]). */
    private fun mapButton(button: MouseButton): Pair<Int, PointerButton>? = when (button) {
        MouseButton.Left -> 1 to PointerButton.Primary
        MouseButton.Right -> 2 to PointerButton.Secondary
        MouseButton.Middle -> 4 to PointerButton.Tertiary
        is MouseButton.Other -> null
    }

    /**
     * Forwards a Kadre keyboard event to Compose as real AWT events:
     * - KEY_PRESSED / KEY_RELEASED drive editing commands (backspace, arrows, shortcuts).
     * - KEY_TYPED (on press, for printable keys) drives character insertion in text fields,
     *   which Compose only performs for genuine AWT typed events.
     */
    private fun forwardKey(event: WindowEvent.KeyboardInput, renderer: ComposeMetalRenderer?) {
        if (keyboardDisabled || renderer == null) return
        val source = keyEventSource ?: run { keyboardDisabled = true; return }

        val mods = awtModifiers(event.modifiers)
        val vk = toAwtKeyCode(event.key)
        val now = System.currentTimeMillis()

        runCatching {
            if (event.state == KeyState.Pressed) {
                if (vk != AwtKeyEvent.VK_UNDEFINED) {
                    renderer.sendKey(AwtKeyEvent(source, AwtKeyEvent.KEY_PRESSED, now, mods, vk, AwtKeyEvent.CHAR_UNDEFINED))
                }
                val ch = typedChar(event.key, event.modifiers.shift)
                if (ch != null && !event.modifiers.ctrl && !event.modifiers.meta) {
                    renderer.sendKey(AwtKeyEvent(source, AwtKeyEvent.KEY_TYPED, now, mods, AwtKeyEvent.VK_UNDEFINED, ch))
                }
            } else if (vk != AwtKeyEvent.VK_UNDEFINED) {
                renderer.sendKey(AwtKeyEvent(source, AwtKeyEvent.KEY_RELEASED, now, mods, vk, AwtKeyEvent.CHAR_UNDEFINED))
            }
        }.onFailure {
            keyboardDisabled = true
            println("[hello-compose] keyboard forwarding disabled: ${it.message}")
        }
    }
}

/** Builds the AWT extended-modifier mask from Kadre modifiers. */
private fun awtModifiers(m: org.graphiks.kadre.core.Modifiers): Int {
    var mask = 0
    if (m.shift) mask = mask or InputEvent.SHIFT_DOWN_MASK
    if (m.ctrl) mask = mask or InputEvent.CTRL_DOWN_MASK
    if (m.alt) mask = mask or InputEvent.ALT_DOWN_MASK
    if (m.meta) mask = mask or InputEvent.META_DOWN_MASK
    return mask
}

/** Maps a Kadre logical [KadreKey] to its AWT virtual key code (`VK_UNDEFINED` if unmapped). */
private fun toAwtKeyCode(key: KadreKey): Int = when (key) {
    in KadreKey.A..KadreKey.Z -> AwtKeyEvent.VK_A + (key.ordinal - KadreKey.A.ordinal)
    in KadreKey.Digit0..KadreKey.Digit9 -> AwtKeyEvent.VK_0 + (key.ordinal - KadreKey.Digit0.ordinal)
    in KadreKey.F1..KadreKey.F12 -> AwtKeyEvent.VK_F1 + (key.ordinal - KadreKey.F1.ordinal)
    KadreKey.Space -> AwtKeyEvent.VK_SPACE
    KadreKey.Enter -> AwtKeyEvent.VK_ENTER
    KadreKey.Escape -> AwtKeyEvent.VK_ESCAPE
    KadreKey.Backspace -> AwtKeyEvent.VK_BACK_SPACE
    KadreKey.Tab -> AwtKeyEvent.VK_TAB
    KadreKey.ArrowUp -> AwtKeyEvent.VK_UP
    KadreKey.ArrowDown -> AwtKeyEvent.VK_DOWN
    KadreKey.ArrowLeft -> AwtKeyEvent.VK_LEFT
    KadreKey.ArrowRight -> AwtKeyEvent.VK_RIGHT
    KadreKey.ShiftLeft, KadreKey.ShiftRight -> AwtKeyEvent.VK_SHIFT
    KadreKey.ControlLeft, KadreKey.ControlRight -> AwtKeyEvent.VK_CONTROL
    KadreKey.AltLeft, KadreKey.AltRight -> AwtKeyEvent.VK_ALT
    KadreKey.MetaLeft, KadreKey.MetaRight -> AwtKeyEvent.VK_META
    else -> AwtKeyEvent.VK_UNDEFINED
}

/** The character a printable key produces (respecting Shift for letters), or null. */
private fun typedChar(key: KadreKey, shift: Boolean): Char? = when {
    key in KadreKey.A..KadreKey.Z -> {
        val upper = 'A' + (key.ordinal - KadreKey.A.ordinal)
        if (shift) upper else upper.lowercaseChar()
    }
    key in KadreKey.Digit0..KadreKey.Digit9 -> '0' + (key.ordinal - KadreKey.Digit0.ordinal)
    key == KadreKey.Space -> ' '
    else -> null
}

/**
 * Entry point. Must run on the main macOS thread (Gradle adds `-XstartOnFirstThread`).
 *
 * Offscreen capture mode: `--capture <path>` renders [DemoUi] into an offscreen Skia raster
 * surface and writes a PNG, then exits — no window, no GPU, headless-safe (useful for CI).
 */
fun main(args: Array<String>) {
    if (args.contains("--keytest")) {
        val typed = keyboardSelfTest("hi")
        println("[hello-compose] keytest — text field received: '$typed' (expected 'hi')")
        if (typed != "hi") error("keytest FAILED: '$typed' != 'hi'")
        println("[hello-compose] keytest OK")
        return
    }

    val captureIndex = args.indexOf("--capture")
    if (captureIndex >= 0) {
        val path = args.getOrNull(captureIndex + 1)
            ?: error("--capture requires a file path: --capture <path>")
        captureDemoUiToPng(path)
        return
    }

    println("[hello-compose] Starting — Compose Multiplatform in a Kadre window")
    EventLoop().runApp(HelloComposeApp())
    println("[hello-compose] Done")
}
