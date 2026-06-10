/**
 * Maps Kadre [WindowEvent]s onto a [ComposeWindowRenderer] (pointer, keyboard, resize) and
 * forwards keyboard events as real AWT events. Shared by the callback-style app and the
 * coroutine/Flow-style app ([kadreApplication]).
 */
package org.graphiks.kadre.samples.compose.infra

import androidx.compose.ui.input.pointer.PointerButton
import org.graphiks.kadre.core.ButtonSource
import org.graphiks.kadre.core.KeyCode
import org.graphiks.kadre.core.KeyState
import org.graphiks.kadre.core.KeyboardModifiers
import org.graphiks.kadre.core.LogicalKey
import org.graphiks.kadre.core.PhysicalKey
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
fun ComposeWindowRenderer.applyWindowEvent(event: WindowEvent, window: Window, keys: KeyForwarder) {
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
        is WindowEvent.KeyInput -> keys.forward(event, this)
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

    fun forward(event: WindowEvent.KeyInput, renderer: ComposeWindowRenderer) {
        if (disabled) return
        val src = source ?: run { disabled = true; return }

        val key = event.event
        val mods = awtModifiers(key.modifiers)
        val vk = toAwtKeyCode(key.physicalKey)
        val now = System.currentTimeMillis()

        runCatching {
            if (key.state == KeyState.Pressed) {
                if (vk != AwtKeyEvent.VK_UNDEFINED) {
                    renderer.sendKey(AwtKeyEvent(src, AwtKeyEvent.KEY_PRESSED, now, mods, vk, AwtKeyEvent.CHAR_UNDEFINED))
                }
                val ch = typedChar(key.logicalKey, key.physicalKey, key.modifiers.shift)
                if (ch != null && !key.modifiers.ctrl && !key.modifiers.meta) {
                    renderer.sendKey(AwtKeyEvent(src, AwtKeyEvent.KEY_TYPED, now, mods, AwtKeyEvent.VK_UNDEFINED, ch))
                }
            } else if (vk != AwtKeyEvent.VK_UNDEFINED) {
                renderer.sendKey(AwtKeyEvent(src, AwtKeyEvent.KEY_RELEASED, now, mods, vk, AwtKeyEvent.CHAR_UNDEFINED))
            }
        }.onFailure {
            disabled = true
            println("[compose-infra] keyboard forwarding disabled: ${it.message}")
        }
    }
}

private fun awtModifiers(m: KeyboardModifiers): Int {
    var mask = 0
    if (m.shift) mask = mask or InputEvent.SHIFT_DOWN_MASK
    if (m.ctrl) mask = mask or InputEvent.CTRL_DOWN_MASK
    if (m.alt) mask = mask or InputEvent.ALT_DOWN_MASK
    if (m.meta) mask = mask or InputEvent.META_DOWN_MASK
    return mask
}

private fun toAwtKeyCode(physicalKey: PhysicalKey): Int {
    val key = (physicalKey as? PhysicalKey.Code)?.code ?: return AwtKeyEvent.VK_UNDEFINED
    return when (key) {
        in KeyCode.KeyA..KeyCode.KeyZ -> AwtKeyEvent.VK_A + (key.ordinal - KeyCode.KeyA.ordinal)
        in KeyCode.Digit0..KeyCode.Digit9 -> AwtKeyEvent.VK_0 + (key.ordinal - KeyCode.Digit0.ordinal)
        in KeyCode.F1..KeyCode.F12 -> AwtKeyEvent.VK_F1 + (key.ordinal - KeyCode.F1.ordinal)
        KeyCode.Space -> AwtKeyEvent.VK_SPACE
        KeyCode.Enter -> AwtKeyEvent.VK_ENTER
        KeyCode.Escape -> AwtKeyEvent.VK_ESCAPE
        KeyCode.Backspace -> AwtKeyEvent.VK_BACK_SPACE
        KeyCode.Tab -> AwtKeyEvent.VK_TAB
        KeyCode.ArrowUp -> AwtKeyEvent.VK_UP
        KeyCode.ArrowDown -> AwtKeyEvent.VK_DOWN
        KeyCode.ArrowLeft -> AwtKeyEvent.VK_LEFT
        KeyCode.ArrowRight -> AwtKeyEvent.VK_RIGHT
        KeyCode.ShiftLeft, KeyCode.ShiftRight -> AwtKeyEvent.VK_SHIFT
        KeyCode.ControlLeft, KeyCode.ControlRight -> AwtKeyEvent.VK_CONTROL
        KeyCode.AltLeft, KeyCode.AltRight -> AwtKeyEvent.VK_ALT
        KeyCode.MetaLeft, KeyCode.MetaRight -> AwtKeyEvent.VK_META
        else -> AwtKeyEvent.VK_UNDEFINED
    }
}

private fun typedChar(logicalKey: LogicalKey, physicalKey: PhysicalKey, shift: Boolean): Char? {
    (logicalKey as? LogicalKey.Character)?.text?.singleOrNull()?.let { return it }
    val key = (physicalKey as? PhysicalKey.Code)?.code ?: return null
    return when {
        key in KeyCode.KeyA..KeyCode.KeyZ -> {
            val upper = 'A' + (key.ordinal - KeyCode.KeyA.ordinal)
            if (shift) upper else upper.lowercaseChar()
        }
        key in KeyCode.Digit0..KeyCode.Digit9 -> '0' + (key.ordinal - KeyCode.Digit0.ordinal)
        key == KeyCode.Space -> ' '
        else -> null
    }
}
