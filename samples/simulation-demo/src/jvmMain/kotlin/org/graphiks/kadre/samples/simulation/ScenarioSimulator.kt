package org.graphiks.kadre.samples.simulation

import org.graphiks.kadre.core.*
import kotlinx.coroutines.delay

/**
 * Wraps a [Scenario] to log every [WindowEvent] delivered to [onWindowEvent].
 */
private class LoggingScenario(
    private val inner: Scenario,
    private val state: CliDisplayState,
) : Scenario by inner {
    override fun onWindowEvent(event: WindowEvent) {
        state.logEvent(eventDesc(event))
        inner.onWindowEvent(event)
    }
}

internal fun eventDesc(event: WindowEvent): String = when (event) {
    is WindowEvent.KeyInput -> {
        val p = event.event.physicalKey
        val l = event.event.logicalKey
        val s = event.event.state
        val t = event.event.text?.let { " '$it'" } ?: ""
        "KEY $p → $l ${s.name}$t"
    }
    is WindowEvent.PointerMoved -> "MOVE (${event.position.x},${event.position.y})"
    is WindowEvent.PointerButton -> "BUTTON ${event.button} ${event.state} (${event.position.x},${event.position.y})"
    is WindowEvent.MouseWheel -> "WHEEL Δx=${event.deltaX} Δy=${event.deltaY}"
    is WindowEvent.Focused -> "FOCUS ${if (event.gained) "✓" else "✗"}"
    is WindowEvent.Resized -> "RESIZE ${event.size.width}×${event.size.height}"
    is WindowEvent.ScaleFactorChanged -> "DPI ${event.factor}"
    is WindowEvent.PointerEntered -> "ENTER (${event.position.x},${event.position.y})"
    is WindowEvent.PointerLeft -> "LEAVE"
    is WindowEvent.ModifiersChanged -> "MODS ${event.state}"
    is WindowEvent.CloseRequested -> "CLOSE"
    is WindowEvent.RedrawRequested -> "REDRAW"
    is WindowEvent.Destroyed -> "DESTROY"
    else -> event::class.simpleName ?: "?"
}

/**
 * Programmatically generates Kadre [WindowEvent] instances and delivers them
 * directly to [Scenario.onWindowEvent], simulating real user interaction
 * without any platform-specific API (no AWT, no Robot, no CGEvent).
 *
 * Events are first-class Kadre types — the scenario processes them identically
 * to OS-delivered events.
 */
suspend fun simulateEvents(meta: ScenarioMetadata, window: Window, state: CliDisplayState) {
    state.scenarioMessage = "Generating events..."
    state.clearEventLog()
    delay(300)

    val caps = meta.scenario.requiredCapabilities
    val scenario = LoggingScenario(meta.scenario, state)

    if (Capability.KEYBOARD in caps) {
        simulateKeyboard(scenario, meta.scenario.id, state)
    }

    if (Capability.MOUSE in caps) {
        simulateMouse(scenario, state)
    }

    if (Capability.TOUCH in caps || Capability.MULTI_TOUCH in caps) {
        simulateTouch(scenario, meta.scenario.id, state)
    }

    // Window-level scenarios use the Window API to trigger events naturally
    when (meta.scenario.id) {
        "window-resize" -> {
            state.scenarioMessage = "Resizing..."
            delay(200)
            window.requestSurfaceSize(PhysicalSize(800, 600))
            scenario.onWindowEvent(WindowEvent.Resized(PhysicalSize(800, 600)))
            delay(300)
            window.requestSurfaceSize(PhysicalSize(1000, 700))
            scenario.onWindowEvent(WindowEvent.Resized(PhysicalSize(1000, 700)))
            delay(300)
        }
        "window-fullscreen" -> {
            state.scenarioMessage = "Fullscreen..."
            delay(200)
            pressKey(scenario, KeyCode.KeyF, 'F')
            delay(1000)
            pressKey(scenario, KeyCode.Escape, '\u001b')
            delay(300)
        }
        "window-focus" -> {
            state.scenarioMessage = "Window manipulation..."
            delay(200)
            window.setMinimized(true)
            scenario.onWindowEvent(WindowEvent.Focused(false))
            delay(600)
            window.setMinimized(false)
            scenario.onWindowEvent(WindowEvent.Focused(true))
            delay(400)
            window.requestSurfaceSize(PhysicalSize(900, 650))
            scenario.onWindowEvent(WindowEvent.Resized(PhysicalSize(900, 650)))
            delay(300)
        }
        "window-multi" -> {
            state.scenarioMessage = "Window creation..."
            delay(200)
            pressKey(scenario, KeyCode.KeyN, 'N')
            sleep(200)
            pressKey(scenario, KeyCode.KeyN, 'N')
            sleep(200)
            pressKey(scenario, KeyCode.KeyN, 'N')
            delay(300)
        }
        "game-simple" -> {
            state.scenarioMessage = "🎮 MOVING TO TARGET 1..."
            delay(1500)
            pressKey(scenario, KeyCode.KeyW, 'w'); sleep(200)
            pressKey(scenario, KeyCode.KeyI, 'i'); sleep(200)
            pressKey(scenario, KeyCode.KeyW, 'w'); sleep(200)
            pressKey(scenario, KeyCode.KeyI, 'i'); sleep(200)
            pressKey(scenario, KeyCode.KeyA, 'a'); sleep(200)
            pressKey(scenario, KeyCode.KeyJ, 'j'); sleep(200)

            state.scenarioMessage = "🎯 SHOOTING AT TARGET 1..."
            delay(800)
            click(scenario, PhysicalPosition(200.0, 200.0)); sleep(600)
            delay(600)

            state.scenarioMessage = "🎮 MOVING TO TARGET 2..."
            delay(1000)
            pressKey(scenario, KeyCode.KeyD, 'd'); sleep(200)
            pressKey(scenario, KeyCode.KeyL, 'l'); sleep(200)
            pressKey(scenario, KeyCode.KeyD, 'd'); sleep(200)
            pressKey(scenario, KeyCode.KeyL, 'l'); sleep(200)

            state.scenarioMessage = "🎯 SHOOTING AT TARGET 2..."
            delay(800)
            click(scenario, PhysicalPosition(600.0, 200.0)); sleep(600)
            delay(600)

            state.scenarioMessage = "🎮 MOVING TO TARGET 3..."
            delay(1000)
            pressKey(scenario, KeyCode.KeyS, 's'); sleep(200)
            pressKey(scenario, KeyCode.KeyK, 'k'); sleep(200)
            pressKey(scenario, KeyCode.KeyS, 's'); sleep(200)
            pressKey(scenario, KeyCode.KeyK, 'k'); sleep(200)

            state.scenarioMessage = "🎯 SHOOTING AT TARGET 3..."
            delay(800)
            click(scenario, PhysicalPosition(400.0, 400.0)); sleep(600)
            delay(600)

            state.scenarioMessage = "🎮 MOVING TO TARGET 4..."
            delay(1000)
            pressKey(scenario, KeyCode.KeyA, 'a'); sleep(200)
            pressKey(scenario, KeyCode.KeyJ, 'j'); sleep(200)
            pressKey(scenario, KeyCode.KeyA, 'a'); sleep(200)
            pressKey(scenario, KeyCode.KeyJ, 'j'); sleep(200)
            pressKey(scenario, KeyCode.KeyS, 's'); sleep(200)
            pressKey(scenario, KeyCode.KeyK, 'k'); sleep(200)

            state.scenarioMessage = "🎯 SHOOTING AT TARGET 4..."
            delay(800)
            click(scenario, PhysicalPosition(200.0, 500.0)); sleep(600)
            delay(600)

            state.scenarioMessage = "🎮 MOVING TO TARGET 5..."
            delay(1000)
            pressKey(scenario, KeyCode.KeyD, 'd'); sleep(200)
            pressKey(scenario, KeyCode.KeyL, 'l'); sleep(200)
            pressKey(scenario, KeyCode.KeyD, 'd'); sleep(200)
            pressKey(scenario, KeyCode.KeyL, 'l'); sleep(200)
            pressKey(scenario, KeyCode.KeyW, 'w'); sleep(200)
            pressKey(scenario, KeyCode.KeyI, 'i'); sleep(200)

            state.scenarioMessage = "🎯 SHOOTING AT TARGET 5..."
            delay(800)
            click(scenario, PhysicalPosition(600.0, 500.0)); sleep(600)
            delay(1000)
        }
    }

    state.scenarioMessage = "Simulation finished"
    delay(200)
}

// ── Keyboard simulation ──────────────────────────────────────────────

private fun simulateKeyboard(scenario: Scenario, id: String, state: CliDisplayState) {
    when (id) {
        "keyboard-ime" -> imeSequence(scenario)
        "keyboard-shortcuts" -> shortcutSequence(scenario)
        "keyboard-modifiers" -> modifierSequence(scenario)
        "keyboard-repeat" -> repeatSequence(scenario)
        else -> basicSequence(scenario)
    }
}

private fun basicSequence(s: Scenario) {
    "Hello Kadre!".forEach { ch ->
        typeChar(s, ch); sleep(60)
    }
    pressKey(s, KeyCode.Enter, '\n'); sleep(100)
    "Keyboard events work!".forEach { ch ->
        typeChar(s, ch); sleep(60)
    }
    pressKey(s, KeyCode.Enter, '\n'); sleep(100)
    "12345".forEach { ch -> typeChar(s, ch); sleep(60) }
}

private fun imeSequence(s: Scenario) {
    "cafe".forEach { ch -> typeChar(s, ch); sleep(80) }
    pressKey(s, KeyCode.Enter, '\n'); sleep(200)
    "resume".forEach { ch -> typeChar(s, ch); sleep(80) }
}

private fun shortcutSequence(s: Scenario) {
    "Hello".forEach { ch -> typeChar(s, ch); sleep(60) }
    pressKey(s, KeyCode.Enter, '\n'); sleep(100)
    modifierChord(s, KeyCode.MetaLeft, KeyCode.KeyA); sleep(100)
    modifierChord(s, KeyCode.MetaLeft, KeyCode.KeyC); sleep(100)
    "pasted".forEach { ch -> typeChar(s, ch); sleep(60) }
}

private fun modifierSequence(s: Scenario) {
    keyDown(s, KeyCode.ShiftLeft, ""); keyUp(s, KeyCode.ShiftLeft, ""); sleep(80)
    keyDown(s, KeyCode.ControlLeft, ""); keyUp(s, KeyCode.ControlLeft, ""); sleep(80)
    keyDown(s, KeyCode.AltLeft, ""); keyUp(s, KeyCode.AltLeft, ""); sleep(80)
}

private fun repeatSequence(s: Scenario) {
    keyDown(s, KeyCode.KeyA, "a")
    sleep(100)
    for (i in 0 until 20) {
        s.onWindowEvent(WindowEvent.KeyInput(KeyEvent(
            PhysicalKey.Code(KeyCode.KeyA),
            LogicalKey.Character("a"),
            KeyState.Pressed,
            KeyboardModifiers.NONE,
            repeat = true,
            text = "a"
        )))
        sleep(30)
    }
    keyUp(s, KeyCode.KeyA, "a")
    sleep(100)
}

// ── Mouse simulation ─────────────────────────────────────────────────

private fun simulateMouse(scenario: Scenario, state: CliDisplayState) {
    val pos = PhysicalPosition(400.0, 300.0)
    val pos2 = PhysicalPosition(500.0, 300.0)
    val pos3 = PhysicalPosition(300.0, 350.0)

    // Move to center
    scenario.onWindowEvent(WindowEvent.PointerMoved(null, pos, true, PointerSource.Mouse))
    sleep(150)
    state.scenarioMessage = "Left click..."
    click(scenario, pos)
    sleep(150)

    // Move right
    state.scenarioMessage = "Right click..."
    scenario.onWindowEvent(WindowEvent.PointerMoved(null, pos2, true, PointerSource.Mouse))
    sleep(150)
    rightClick(scenario, pos2)
    sleep(150)

    // Move and scroll
    state.scenarioMessage = "Scrolling..."
    scenario.onWindowEvent(WindowEvent.PointerMoved(null, pos3, true, PointerSource.Mouse))
    sleep(100)
    scenario.onWindowEvent(WindowEvent.MouseWheel(null, 0.0, -3.0, TouchPhase.Moved))
    sleep(100)
    scenario.onWindowEvent(WindowEvent.MouseWheel(null, 0.0, 3.0, TouchPhase.Moved))
    sleep(100)

    // Drag
    state.scenarioMessage = "Drag and drop..."
    dragBetween(scenario, pos2, PhysicalPosition(600.0, 400.0))
}

// ── Touch simulation ──────────────────────────────────────────────────

private fun simulateTouch(scenario: Scenario, id: String, state: CliDisplayState) {
    when (id) {
        "touch-single" -> singleTouchSequence(scenario)
        "touch-multi" -> multiTouchSequence(scenario)
        "touch-gestures" -> gestureSequence(scenario)
    }
}

private fun singleTouchSequence(s: Scenario) {
    sleep(100)
    touchPress(s, PhysicalPosition(400.0, 300.0), FingerId(1L))
    sleep(80)
    touchMove(s, PhysicalPosition(450.0, 300.0), FingerId(1L))
    sleep(80)
    touchMove(s, PhysicalPosition(450.0, 350.0), FingerId(1L))
    sleep(80)
    touchMove(s, PhysicalPosition(400.0, 350.0), FingerId(1L))
    sleep(80)
    touchRelease(s, PhysicalPosition(400.0, 350.0), FingerId(1L))
    sleep(100)
}

private fun multiTouchSequence(s: Scenario) {
    sleep(100)
    touchPress(s, PhysicalPosition(300.0, 300.0), FingerId(1L))
    sleep(60)
    touchPress(s, PhysicalPosition(500.0, 300.0), FingerId(2L))
    sleep(80)
    touchMove(s, PhysicalPosition(350.0, 350.0), FingerId(1L))
    sleep(60)
    touchMove(s, PhysicalPosition(450.0, 350.0), FingerId(2L))
    sleep(80)
    touchRelease(s, PhysicalPosition(350.0, 350.0), FingerId(1L))
    sleep(60)
    touchRelease(s, PhysicalPosition(450.0, 350.0), FingerId(2L))
    sleep(100)
}

private fun gestureSequence(s: Scenario) {
    sleep(100)
    touchPress(s, PhysicalPosition(400.0, 300.0), FingerId(1L))
    sleep(50)
    touchRelease(s, PhysicalPosition(400.0, 300.0), FingerId(1L))
    sleep(200)
    touchPress(s, PhysicalPosition(150.0, 300.0), FingerId(1L))
    sleep(50)
    touchSwipe(s, PhysicalPosition(150.0, 300.0), PhysicalPosition(450.0, 300.0), FingerId(1L))
    touchRelease(s, PhysicalPosition(450.0, 300.0), FingerId(1L))
    sleep(200)
    touchPress(s, PhysicalPosition(400.0, 150.0), FingerId(1L))
    sleep(50)
    touchSwipe(s, PhysicalPosition(400.0, 150.0), PhysicalPosition(400.0, 450.0), FingerId(1L))
    touchRelease(s, PhysicalPosition(400.0, 450.0), FingerId(1L))
    sleep(100)
}

private fun touchPress(s: Scenario, pos: PhysicalPosition<Double>, finger: FingerId) {
    s.onWindowEvent(WindowEvent.PointerButton(null, KeyState.Pressed, pos, true, ButtonSource.Touch(finger)))
}

private fun touchRelease(s: Scenario, pos: PhysicalPosition<Double>, finger: FingerId) {
    s.onWindowEvent(WindowEvent.PointerButton(null, KeyState.Released, pos, true, ButtonSource.Touch(finger)))
}

private fun touchMove(s: Scenario, pos: PhysicalPosition<Double>, finger: FingerId) {
    s.onWindowEvent(WindowEvent.PointerMoved(null, pos, true, PointerSource.Touch(finger)))
}

private fun touchSwipe(s: Scenario, from: PhysicalPosition<Double>, to: PhysicalPosition<Double>, finger: FingerId) {
    val steps = 5
    for (i in 1..steps) {
        val x = from.x + (to.x - from.x) * i / steps
        val y = from.y + (to.y - from.y) * i / steps
        touchMove(s, PhysicalPosition(x, y), finger)
        sleep(30)
    }
}

// ── Helpers ──────────────────────────────────────────────────────────

private fun sleep(ms: Long) {
    try { Thread.sleep(ms) } catch (_: InterruptedException) {}
}

private fun typeChar(s: Scenario, ch: Char) {
    val (phys, log) = charToKey(ch)
    keyDown(s, phys, log, ch.toString())
    keyUp(s, phys, log, ch.toString())
}

private fun pressKey(s: Scenario, code: KeyCode, char: Char) {
    keyDown(s, code, char.toString())
    keyUp(s, code, char.toString())
}

private fun modifierChord(s: Scenario, mod: KeyCode, key: KeyCode) {
    modDown(s, mod)
    pressKey(s, key, ' ')
    modUp(s, mod)
}

private fun click(s: Scenario, pos: PhysicalPosition<Double>) {
    s.onWindowEvent(WindowEvent.PointerButton(null, KeyState.Pressed, pos, true, ButtonSource.Mouse(MouseButton.Left)))
    s.onWindowEvent(WindowEvent.PointerButton(null, KeyState.Released, pos, true, ButtonSource.Mouse(MouseButton.Left)))
}

private fun rightClick(s: Scenario, pos: PhysicalPosition<Double>) {
    s.onWindowEvent(WindowEvent.PointerButton(null, KeyState.Pressed, pos, true, ButtonSource.Mouse(MouseButton.Right)))
    s.onWindowEvent(WindowEvent.PointerButton(null, KeyState.Released, pos, true, ButtonSource.Mouse(MouseButton.Right)))
}

private fun dragBetween(s: Scenario, from: PhysicalPosition<Double>, to: PhysicalPosition<Double>) {
    s.onWindowEvent(WindowEvent.PointerMoved(null, from, true, PointerSource.Mouse))
    sleep(80)
    s.onWindowEvent(WindowEvent.PointerButton(null, KeyState.Pressed, from, true, ButtonSource.Mouse(MouseButton.Left)))
    sleep(80)
    val steps = 8
    for (i in 1..steps) {
        val x = from.x + (to.x - from.x) * i / steps
        val y = from.y + (to.y - from.y) * i / steps
        s.onWindowEvent(WindowEvent.PointerMoved(null, PhysicalPosition(x, y), true, PointerSource.Mouse))
        sleep(30)
    }
    s.onWindowEvent(WindowEvent.PointerButton(null, KeyState.Released, to, true, ButtonSource.Mouse(MouseButton.Left)))
}

private fun keyDown(s: Scenario, code: KeyCode, text: String) {
    s.onWindowEvent(keyEvent(code, character(code, text), KeyState.Pressed, text))
}

private fun keyUp(s: Scenario, code: KeyCode, text: String) {
    s.onWindowEvent(keyEvent(code, character(code, text), KeyState.Released, text))
}

private fun keyDown(s: Scenario, phys: PhysicalKey, log: LogicalKey, text: String) {
    s.onWindowEvent(WindowEvent.KeyInput(KeyEvent(phys, log, KeyState.Pressed, KeyboardModifiers.NONE, text = text)))
}

private fun keyUp(s: Scenario, phys: PhysicalKey, log: LogicalKey, text: String) {
    s.onWindowEvent(WindowEvent.KeyInput(KeyEvent(phys, log, KeyState.Released, KeyboardModifiers.NONE, text = text)))
}

private fun modDown(s: Scenario, code: KeyCode) {
    val name = when (code) {
        KeyCode.ShiftLeft, KeyCode.ShiftRight -> NamedKey.Shift
        KeyCode.ControlLeft, KeyCode.ControlRight -> NamedKey.Control
        KeyCode.AltLeft, KeyCode.AltRight -> NamedKey.Alt
        KeyCode.MetaLeft, KeyCode.MetaRight -> NamedKey.Meta
        else -> return
    }
    s.onWindowEvent(keyEvent(code, LogicalKey.Named(name), KeyState.Pressed, ""))
}

private fun modUp(s: Scenario, code: KeyCode) {
    val name = when (code) {
        KeyCode.ShiftLeft, KeyCode.ShiftRight -> NamedKey.Shift
        KeyCode.ControlLeft, KeyCode.ControlRight -> NamedKey.Control
        KeyCode.AltLeft, KeyCode.AltRight -> NamedKey.Alt
        KeyCode.MetaLeft, KeyCode.MetaRight -> NamedKey.Meta
        else -> return
    }
    s.onWindowEvent(keyEvent(code, LogicalKey.Named(name), KeyState.Released, ""))
}

private fun keyEvent(code: KeyCode, logical: LogicalKey, state: KeyState, text: String): WindowEvent.KeyInput =
    WindowEvent.KeyInput(KeyEvent(PhysicalKey.Code(code), logical, state, KeyboardModifiers.NONE, text = text))

private fun character(code: KeyCode, text: String): LogicalKey {
    if (text.length == 1) return LogicalKey.Character(text)
    return when (code) {
        KeyCode.Enter -> LogicalKey.Named(NamedKey.Enter)
        KeyCode.Tab -> LogicalKey.Named(NamedKey.Tab)
        KeyCode.Escape -> LogicalKey.Named(NamedKey.Escape)
        KeyCode.Backspace -> LogicalKey.Named(NamedKey.Backspace)
        KeyCode.ShiftLeft, KeyCode.ShiftRight -> LogicalKey.Named(NamedKey.Shift)
        KeyCode.ControlLeft, KeyCode.ControlRight -> LogicalKey.Named(NamedKey.Control)
        KeyCode.AltLeft, KeyCode.AltRight -> LogicalKey.Named(NamedKey.Alt)
        KeyCode.MetaLeft, KeyCode.MetaRight -> LogicalKey.Named(NamedKey.Meta)
        KeyCode.ArrowUp -> LogicalKey.Named(NamedKey.ArrowUp)
        KeyCode.ArrowDown -> LogicalKey.Named(NamedKey.ArrowDown)
        KeyCode.ArrowLeft -> LogicalKey.Named(NamedKey.ArrowLeft)
        KeyCode.ArrowRight -> LogicalKey.Named(NamedKey.ArrowRight)
        KeyCode.KeyA -> LogicalKey.Character("a")
        KeyCode.KeyC -> LogicalKey.Character("c")
        KeyCode.KeyF -> LogicalKey.Character("f")
        KeyCode.KeyV -> LogicalKey.Character("v")
        else -> LogicalKey.Unidentified()
    }
}

private fun charToKey(ch: Char): Pair<PhysicalKey, LogicalKey> {
    val code = when {
        ch in 'a'..'z' -> KeyCode.valueOf("Key${ch.uppercase()}")
        ch in 'A'..'Z' -> KeyCode.valueOf("Key${ch.uppercase()}")
        ch in '0'..'9' -> KeyCode.valueOf("Digit$ch")
        ch == ' ' -> KeyCode.Space
        ch == '\n' -> KeyCode.Enter
        ch == '.' -> KeyCode.Period
        ch == ',' -> KeyCode.Comma
        ch == '!' -> KeyCode.Digit1
        ch == '?' -> KeyCode.Slash
        ch == ':' -> KeyCode.Semicolon
        ch == '/' -> KeyCode.Slash
        ch == '\\' -> KeyCode.Backslash
        ch == '(' -> KeyCode.Digit9
        ch == ')' -> KeyCode.Digit0
        ch == '\'' -> KeyCode.Quote
        ch == '@' -> KeyCode.Digit2
        ch == '\u001b' -> KeyCode.Escape
        else -> KeyCode.Space
    }
    val log = when {
        ch == '\n' -> LogicalKey.Named(NamedKey.Enter)
        ch == '\u001b' -> LogicalKey.Named(NamedKey.Escape)
        ch.isLetterOrDigit() || ch == ' ' -> LogicalKey.Character(ch.toString())
        else -> LogicalKey.Character(ch.toString())
    }
    return PhysicalKey.Code(code) to log
}
