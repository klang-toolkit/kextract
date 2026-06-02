/**
 * Unit tests for the kadre-core event model.
 */
package org.graphiks.kadre.core

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class EventsTest {

    @Test
    fun `SHIFT plus CTRL contains SHIFT`() {
        val mods = KeyboardModifiers.Shift + KeyboardModifiers.Ctrl
        assertTrue(mods.contains(KeyboardModifiers.Shift))
    }

    @Test
    fun `SHIFT plus CTRL contains CTRL`() {
        val mods = KeyboardModifiers.Shift + KeyboardModifiers.Ctrl
        assertTrue(mods.contains(KeyboardModifiers.Ctrl))
    }

    @Test
    fun `SHIFT plus CTRL does not contain ALT`() {
        val mods = KeyboardModifiers.Shift + KeyboardModifiers.Ctrl
        assertFalse(mods.contains(KeyboardModifiers.Alt))
    }

    @Test
    fun `NONE contains no keyboard modifier`() {
        val mods = KeyboardModifiers.NONE
        assertFalse(mods.shift)
        assertFalse(mods.ctrl)
        assertFalse(mods.alt)
        assertFalse(mods.meta)
        assertFalse(mods.altGraph)
        assertFalse(mods.capsLock)
        assertFalse(mods.numLock)
        assertFalse(mods.symbol)
    }

    @Test
    fun `lock and symbol modifier factories expose their bits`() {
        assertTrue(KeyboardModifiers.CapsLock.capsLock)
        assertTrue(KeyboardModifiers.NumLock.numLock)
        assertTrue(KeyboardModifiers.Symbol.symbol)
    }

    @Test
    fun `minus removes a modifier`() {
        val mods = KeyboardModifiers.Shift + KeyboardModifiers.Ctrl - KeyboardModifiers.Shift
        assertFalse(mods.shift)
        assertTrue(mods.ctrl)
    }

    @Test
    fun `physical key codes include letters digits navigation and F35`() {
        val entries = KeyCode.entries.map { it.name }.toSet()
        assertTrue("KeyA" in entries)
        assertTrue("KeyZ" in entries)
        assertTrue("Digit0" in entries)
        assertTrue("Digit9" in entries)
        assertTrue("ArrowUp" in entries)
        assertTrue("NumpadEnter" in entries)
        assertTrue("F35" in entries)
    }

    @Test
    fun `named keys include text navigation modifiers and media`() {
        val entries = NamedKey.entries.map { it.name }.toSet()
        assertTrue("Enter" in entries)
        assertTrue("ArrowDown" in entries)
        assertTrue("AltGraph" in entries)
        assertTrue("MediaPlayPause" in entries)
        assertTrue("F35" in entries)
    }

    @Test
    fun `KeyEvent separates physical and logical key`() {
        val event = KeyEvent(
            physicalKey = PhysicalKey.Code(KeyCode.KeyW),
            logicalKey = LogicalKey.Character("z"),
            state = KeyState.Pressed,
            modifiers = KeyboardModifiers.NONE,
            text = "z",
            keyWithoutModifiers = LogicalKey.Character("z"),
            native = NativeKeyInfo(platform = KeyPlatform.Web, keyCode = "KeyW", keyValue = "z"),
        )

        assertEquals(PhysicalKey.Code(KeyCode.KeyW), event.physicalKey)
        assertEquals(LogicalKey.Character("z"), event.logicalKey)
        assertEquals("z", event.character)
        assertTrue(event.isPressed)
        assertFalse(event.isReleased)
        assertEquals(KeyPlatform.Web, event.native.platform)
    }

    @Test
    fun `KeyEvent exposes shortcut key and preferred text`() {
        val event = KeyEvent(
            physicalKey = PhysicalKey.Code(KeyCode.KeyS),
            logicalKey = LogicalKey.Character("S"),
            state = KeyState.Pressed,
            modifiers = KeyboardModifiers.Ctrl + KeyboardModifiers.Shift,
            text = null,
            textWithAllModifiers = "\u0013",
            keyWithoutModifiers = LogicalKey.Character("s"),
        )

        assertEquals(LogicalKey.Character("s"), event.shortcutKey)
        assertEquals("\u0013", event.effectiveText)
    }

    @Test
    fun `LogicalKey Dead is distinct from printable character`() {
        assertFalse(LogicalKey.Dead("^") == LogicalKey.Character("^"))
    }

    @Test
    fun `Native physical key keeps platform code`() {
        val key = PhysicalKey.Native(KeyPlatform.AppKit, 126)
        assertEquals(KeyPlatform.AppKit, key.platform)
        assertEquals(126, key.code)
    }

    @Test
    fun `physical key location can be inferred from standardized key codes`() {
        assertEquals(KeyLocation.Left, KeyCode.ShiftLeft.location())
        assertEquals(KeyLocation.Right, KeyCode.ControlRight.location())
        assertEquals(KeyLocation.Numpad, KeyCode.NumpadEnter.location())
        assertEquals(KeyLocation.Standard, KeyCode.KeyA.location())
        assertEquals(KeyLocation.Left, PhysicalKey.Code(KeyCode.AltLeft).location())
        assertEquals(KeyLocation.Standard, PhysicalKey.Native(KeyPlatform.X11, 64).location())
    }

    @Test
    fun `KeyChord can match physical bindings independent of layout`() {
        val chord = KeyChord(physicalKey = PhysicalKey.Code(KeyCode.KeyW))
        val event = KeyEvent(
            physicalKey = PhysicalKey.Code(KeyCode.KeyW),
            logicalKey = LogicalKey.Character("z"),
            state = KeyState.Pressed,
            modifiers = KeyboardModifiers.NONE,
        )

        assertTrue(chord.matches(event))
    }

    @Test
    fun `KeyChord can require exact modifiers`() {
        val chord = KeyChord(
            logicalKey = LogicalKey.Character("s"),
            modifiers = KeyboardModifiers.Ctrl,
            modifierMatch = KeyChordModifierMatch.Exact,
        )
        val event = KeyEvent(
            physicalKey = PhysicalKey.Code(KeyCode.KeyS),
            logicalKey = LogicalKey.Character("s"),
            state = KeyState.Pressed,
            modifiers = KeyboardModifiers.Ctrl + KeyboardModifiers.Shift,
        )

        assertFalse(chord.matches(event))
    }

    @Test
    fun `KeyChord rejects repeat by default`() {
        val chord = KeyChord(logicalKey = LogicalKey.Named(NamedKey.Enter))
        val event = KeyEvent(
            physicalKey = PhysicalKey.Code(KeyCode.Enter),
            logicalKey = LogicalKey.Named(NamedKey.Enter),
            state = KeyState.Pressed,
            modifiers = KeyboardModifiers.NONE,
            repeat = true,
        )

        assertFalse(chord.matches(event))
    }

    @Test
    fun `KeyChord can match logical shortcuts with modifiers`() {
        val chord = KeyChord(
            logicalKey = LogicalKey.Character("s"),
            modifiers = KeyboardModifiers.Ctrl,
        )
        val event = KeyEvent(
            physicalKey = PhysicalKey.Code(KeyCode.KeyS),
            logicalKey = LogicalKey.Character("s"),
            state = KeyState.Pressed,
            modifiers = KeyboardModifiers.Ctrl + KeyboardModifiers.Shift,
        )

        assertTrue(chord.matches(event))
    }

    @Test
    fun `KeyState has exactly Pressed and Released`() {
        assertEquals(setOf("Pressed", "Released"), KeyState.entries.map { it.name }.toSet())
    }

    @Test
    fun `TouchPhase has exactly the four phases`() {
        assertEquals(setOf("Started", "Moved", "Ended", "Cancelled"), TouchPhase.entries.map { it.name }.toSet())
    }

    @Test
    fun `MouseButton Left is a singleton`() {
        assertTrue(MouseButton.Left === MouseButton.Left)
    }

    @Test
    fun `MouseButton Other keeps its index`() {
        assertEquals(5, MouseButton.Other(5).button)
    }

    @Test
    fun `two MouseButton Other with same indices are equal`() {
        assertEquals(MouseButton.Other(3), MouseButton.Other(3))
    }

    private fun classifyWindowEvent(event: WindowEvent): String = when (event) {
        WindowEvent.CloseRequested -> "CloseRequested"
        is WindowEvent.Resized -> "Resized"
        is WindowEvent.Moved -> "Moved"
        is WindowEvent.ScaleFactorChanged -> "ScaleFactorChanged"
        is WindowEvent.Focused -> "Focused"
        is WindowEvent.KeyInput -> "KeyInput"
        is WindowEvent.PointerMoved -> "PointerMoved"
        is WindowEvent.PointerEntered -> "PointerEntered"
        is WindowEvent.PointerLeft -> "PointerLeft"
        is WindowEvent.PointerButton -> "PointerButton"
        is WindowEvent.MouseWheel -> "MouseWheel"
        is WindowEvent.ModifiersChanged -> "ModifiersChanged"
        WindowEvent.RedrawRequested -> "RedrawRequested"
        WindowEvent.Destroyed -> "Destroyed"
        is WindowEvent.ThemeChanged -> "ThemeChanged"
        is WindowEvent.Ime -> "Ime"
        is WindowEvent.DragEntered -> "DragEntered"
        is WindowEvent.DragMoved -> "DragMoved"
        is WindowEvent.DragDropped -> "DragDropped"
        WindowEvent.DragLeft -> "DragLeft"
        is WindowEvent.PinchGesture -> "PinchGesture"
        is WindowEvent.PanGesture -> "PanGesture"
        is WindowEvent.RotationGesture -> "RotationGesture"
        is WindowEvent.DoubleTapGesture -> "DoubleTapGesture"
        is WindowEvent.TouchpadPressure -> "TouchpadPressure"
        is WindowEvent.Occluded -> "Occluded"
    }

    @Test
    fun `WindowEvent KeyInput keeps rich event`() {
        val keyEvent = KeyEvent(
            physicalKey = PhysicalKey.Code(KeyCode.KeyA),
            logicalKey = LogicalKey.Character("a"),
            state = KeyState.Pressed,
            modifiers = KeyboardModifiers.Shift,
            text = "A",
        )
        val event = WindowEvent.KeyInput(keyEvent)
        assertEquals("KeyInput", classifyWindowEvent(event))
        assertEquals(keyEvent, event.event)
    }

    @Test
    fun `WindowEvent ModifiersChanged keeps logical and physical state`() {
        val state = KeyboardModifierState(
            logical = KeyboardModifiers.Shift,
            physical = ModifierKeys(leftShift = ModifierKeyState.Pressed),
        )
        val event = WindowEvent.ModifiersChanged(state)

        assertEquals("ModifiersChanged", classifyWindowEvent(event))
        assertTrue(event.state.logical.shift)
        assertEquals(ModifierKeyState.Pressed, event.state.physical.leftShift)
    }

    @Test
    fun `WindowEvent basic variants keep payloads`() {
        val pointerPos = PhysicalPosition(1.0, 2.0)
        assertEquals("CloseRequested", classifyWindowEvent(WindowEvent.CloseRequested))
        assertEquals("Resized", classifyWindowEvent(WindowEvent.Resized(PhysicalSize(1920, 1080))))
        assertEquals("Moved", classifyWindowEvent(WindowEvent.Moved(PhysicalPosition(10, 20))))
        assertEquals("ScaleFactorChanged", classifyWindowEvent(WindowEvent.ScaleFactorChanged(2.0)))
        assertEquals("Focused", classifyWindowEvent(WindowEvent.Focused(true)))
        assertEquals("PointerMoved", classifyWindowEvent(WindowEvent.PointerMoved(DeviceId(3L), pointerPos, true, PointerSource.Mouse)))
        assertEquals("PointerEntered", classifyWindowEvent(WindowEvent.PointerEntered(null, pointerPos, true, PointerKind.Mouse)))
        assertEquals("PointerLeft", classifyWindowEvent(WindowEvent.PointerLeft(null, null, true, PointerKind.Unknown)))
        assertEquals("PointerButton", classifyWindowEvent(WindowEvent.PointerButton(DeviceId(9L), KeyState.Released, pointerPos, true, ButtonSource.Mouse(MouseButton.Left))))
        assertEquals("MouseWheel", classifyWindowEvent(WindowEvent.MouseWheel(DeviceId(2L), 1.0, -1.0, TouchPhase.Moved)))
        assertEquals("RedrawRequested", classifyWindowEvent(WindowEvent.RedrawRequested))
        assertEquals("Destroyed", classifyWindowEvent(WindowEvent.Destroyed))
    }

    @Test
    fun `PointerSource Touch keeps finger id and force`() {
        val source = PointerSource.Touch(
            fingerId = FingerId(42L),
            force = TouchForce.Normalized(0.5),
        )
        assertEquals(FingerId(42L), source.fingerId)
        assertEquals(TouchForce.Normalized(0.5), source.force)
    }

    @Test
    fun `WindowEvent ThemeChanged keeps the theme`() {
        val event = WindowEvent.ThemeChanged(Theme.Dark)
        assertEquals("ThemeChanged", classifyWindowEvent(event))
        assertEquals(Theme.Dark, event.theme)
    }

    @Test
    fun `WindowEvent Ime variants keep payloads`() {
        val commit = WindowEvent.Ime.ImeEvent.Commit("hello")
        val preedit = WindowEvent.Ime.ImeEvent.Preedit("abc", Pair(0, 3))
        val delete = WindowEvent.Ime.ImeEvent.DeleteSurrounding(3, 5)

        assertEquals("Ime", classifyWindowEvent(WindowEvent.Ime(commit)))
        assertEquals("hello", commit.text)
        assertEquals(Pair(0, 3), preedit.cursorRange)
        assertEquals(3, delete.beforeBytes)
        assertEquals(5, delete.afterBytes)
        assertTrue(WindowEvent.Ime.ImeEvent.Enabled === WindowEvent.Ime.ImeEvent.Enabled)
        assertTrue(WindowEvent.Ime.ImeEvent.Disabled === WindowEvent.Ime.ImeEvent.Disabled)
    }

    @Test
    fun `WindowEvent drag and gesture variants keep payloads`() {
        val pos = PhysicalPosition(10.0, 20.0)
        val panDelta = PhysicalPosition(1.5f, -2.5f)
        val deviceId = DeviceId(11L)

        val dragEntered = WindowEvent.DragEntered(pos, listOf("a"))
        val dragMoved = WindowEvent.DragMoved(pos)
        val dragDropped = WindowEvent.DragDropped(pos, listOf("b"))
        val pinch = WindowEvent.PinchGesture(deviceId, delta = 0.5, phase = TouchPhase.Moved)
        val pan = WindowEvent.PanGesture(deviceId, delta = panDelta, phase = TouchPhase.Started)
        val rotation = WindowEvent.RotationGesture(deviceId, deltaDegrees = 12.5f, phase = TouchPhase.Ended)
        val doubleTap = WindowEvent.DoubleTapGesture(deviceId)
        val pressure = WindowEvent.TouchpadPressure(deviceId, pressure = 0.8f, stage = 2L)

        assertEquals("DragEntered", classifyWindowEvent(dragEntered))
        assertEquals(pos, dragEntered.position)
        assertEquals("DragMoved", classifyWindowEvent(dragMoved))
        assertEquals("DragDropped", classifyWindowEvent(dragDropped))
        assertEquals("DragLeft", classifyWindowEvent(WindowEvent.DragLeft))
        assertEquals("PinchGesture", classifyWindowEvent(pinch))
        assertEquals(0.5, pinch.delta)
        assertEquals("PanGesture", classifyWindowEvent(pan))
        assertEquals(panDelta, pan.delta)
        assertEquals("RotationGesture", classifyWindowEvent(rotation))
        assertEquals(12.5f, rotation.deltaDegrees)
        assertEquals("DoubleTapGesture", classifyWindowEvent(doubleTap))
        assertEquals("TouchpadPressure", classifyWindowEvent(pressure))
        assertEquals(2L, pressure.stage)
        assertEquals("Occluded", classifyWindowEvent(WindowEvent.Occluded(true)))
    }

    private fun classifyDeviceEvent(event: DeviceEvent): String = when (event) {
        is DeviceEvent.PointerMotion -> "PointerMotion"
        is DeviceEvent.Button -> "Button"
        is DeviceEvent.Key -> "Key"
        is DeviceEvent.MouseWheel -> "MouseWheel"
    }

    @Test
    fun `DeviceEvent variants keep payloads`() {
        val motion = DeviceEvent.PointerMotion(1.5, -2.5)
        val button = DeviceEvent.Button(2, KeyState.Pressed)
        val key = DeviceEvent.Key(
            RawKeyEvent(
                physicalKey = PhysicalKey.Code(KeyCode.KeyA),
                state = KeyState.Released,
                native = NativeKeyInfo(platform = KeyPlatform.X11, scanCode = 0x1E),
            ),
        )
        val wheel = DeviceEvent.MouseWheel(3.0, -1.5)

        assertEquals("PointerMotion", classifyDeviceEvent(motion))
        assertEquals(1.5, motion.dx)
        assertEquals("Button", classifyDeviceEvent(button))
        assertEquals(2, button.button)
        assertEquals("Key", classifyDeviceEvent(key))
        assertEquals(PhysicalKey.Code(KeyCode.KeyA), key.event.physicalKey)
        assertEquals(0x1E, key.event.scancode)
        assertEquals("MouseWheel", classifyDeviceEvent(wheel))
        assertEquals(-1.5, wheel.deltaY)
    }

    @Test
    fun `DeviceEvent Key keeps legacy scancode constructor`() {
        val key = DeviceEvent.Key(0x1E, KeyState.Pressed)

        assertEquals(PhysicalKey.Native(KeyPlatform.Unknown, 0x1E), key.event.physicalKey)
        assertEquals(0x1E, key.scancode)
        assertEquals(KeyState.Pressed, key.state)
    }
}
