/**
 * Maps Kadre [WindowEvent]s onto a [ComposeWindowRenderer] (pointer, keyboard, resize) and
 * forwards keyboard events as real AWT events. Shared by the callback-style app and the
 * coroutine/Flow-style app ([kadreApplication]).
 */
package org.graphiks.kadre.samples.hellocompose

import androidx.compose.ui.input.pointer.PointerButton
import org.graphiks.kadre.core.ButtonSource
import org.graphiks.kadre.core.Key as KadreKey
import org.graphiks.kadre.core.KeyState
import org.graphiks.kadre.core.Modifiers
import org.graphiks.kadre.core.MouseButton
import org.graphiks.kadre.core.Window
import org.graphiks.kadre.core.WindowEvent
import java.awt.Component
import java.awt.event.InputEvent
import java.awt.event.KeyEvent as AwtKeyEvent

/**
 * Applies a Kadre [event] to this renderer (everything except CloseRequested, which the caller
 * handles so it can also dispose/exit).
 */
internal fun ComposeWindowRenderer.applyWindowEvent(event: WindowEvent, window: Window, keys: KeyForwarder) {
    when (event) {
        is WindowEvent.RedrawRequested -> renderFrame()
        is WindowEvent.PointerMoved -> onPointerMoved(event.position.x, event.position.y)
        is WindowEvent.PointerButton ->
            (event.button as? ButtonSource.Mouse)?.let { source ->
                mapButton(source.button)?.let { (bit, button) ->
                    onPointerButton(bit, event.state == KeyState.Pressed, button)
                }
            }
        is WindowEvent.MouseWheel -> onScroll(event.deltaX, event.deltaY)
        is WindowEvent.PointerEntered -> onPointerEnter()
        is WindowEvent.PointerLeft -> onPointerExit()
        is WindowEvent.KeyboardInput -> keys.forward(event, this)
        is WindowEvent.Resized -> resize(event.size.width, event.size.height, window.scaleFactor)
        is WindowEvent.ScaleFactorChanged -> {
            val inner = window.innerSize
            resize(inner.width, inner.height, event.factor)
        }
        else -> { /* CloseRequested handled by the caller; others ignored */ }
    }
}

/** Maps a Kadre [MouseButton] to (Compose PointerButtons bit, [PointerButton]). */
internal fun mapButton(button: MouseButton): Pair<Int, PointerButton>? = when (button) {
    MouseButton.Left -> 1 to PointerButton.Primary
    MouseButton.Right -> 2 to PointerButton.Secondary
    MouseButton.Middle -> 4 to PointerButton.Tertiary
    is MouseButton.Other -> null
}

/**
 * Forwards Kadre keyboard events to Compose as real AWT events:
 * - KEY_PRESSED / KEY_RELEASED drive editing commands (backspace, arrows, shortcuts).
 * - KEY_TYPED (on press, printable keys) drives character insertion in text fields, which
 *   Compose only performs for genuine AWT typed events.
 *
 * The AWT source [Component] is created lazily and guarded: if AWT init misbehaves under
 * `-XstartOnFirstThread`, keyboard is disabled (not fatal).
 */
class KeyForwarder {
    private var disabled = false
    private val source: Component? by lazy { runCatching { object : Component() {} }.getOrNull() }

    fun forward(event: WindowEvent.KeyboardInput, renderer: ComposeWindowRenderer) {
        if (disabled) return
        val src = source ?: run { disabled = true; return }

        val mods = awtModifiers(event.modifiers)
        val vk = toAwtKeyCode(event.key)
        val now = System.currentTimeMillis()

        runCatching {
            if (event.state == KeyState.Pressed) {
                if (vk != AwtKeyEvent.VK_UNDEFINED) {
                    renderer.sendKey(AwtKeyEvent(src, AwtKeyEvent.KEY_PRESSED, now, mods, vk, AwtKeyEvent.CHAR_UNDEFINED))
                }
                val ch = typedChar(event.key, event.modifiers.shift)
                if (ch != null && !event.modifiers.ctrl && !event.modifiers.meta) {
                    renderer.sendKey(AwtKeyEvent(src, AwtKeyEvent.KEY_TYPED, now, mods, AwtKeyEvent.VK_UNDEFINED, ch))
                }
            } else if (vk != AwtKeyEvent.VK_UNDEFINED) {
                renderer.sendKey(AwtKeyEvent(src, AwtKeyEvent.KEY_RELEASED, now, mods, vk, AwtKeyEvent.CHAR_UNDEFINED))
            }
        }.onFailure {
            disabled = true
            println("[hello-compose] keyboard forwarding disabled: ${it.message}")
        }
    }
}

private fun awtModifiers(m: Modifiers): Int {
    var mask = 0
    if (m.shift) mask = mask or InputEvent.SHIFT_DOWN_MASK
    if (m.ctrl) mask = mask or InputEvent.CTRL_DOWN_MASK
    if (m.alt) mask = mask or InputEvent.ALT_DOWN_MASK
    if (m.meta) mask = mask or InputEvent.META_DOWN_MASK
    return mask
}

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

private fun typedChar(key: KadreKey, shift: Boolean): Char? = when {
    key in KadreKey.A..KadreKey.Z -> {
        val upper = 'A' + (key.ordinal - KadreKey.A.ordinal)
        if (shift) upper else upper.lowercaseChar()
    }
    key in KadreKey.Digit0..KadreKey.Digit9 -> '0' + (key.ordinal - KadreKey.Digit0.ordinal)
    key == KadreKey.Space -> ' '
    else -> null
}
