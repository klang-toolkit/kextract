/**
 * Unit tests for the kadre-core event model.
 *
 * Verifies:
 * - The exhaustiveness of the `when` on [WindowEvent] and [DeviceEvent] (without `else`)
 * - The bit logic of [Modifiers]: combination and membership
 * - The construction and equality of each variant
 * - The [Key] enum covers at least the required set (letters, digits, F1–F12, navigation)
 * - The [KeyState] enum has exactly the two expected states
 * - The [TouchPhase] enum has exactly the four expected phases
 */
package org.graphiks.kadre.core

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class EventsTest {

    // -----------------------------------------------------------------------
    // Modifiers — bit logic
    // -----------------------------------------------------------------------

    @Test
    fun `SHIFT plus CTRL contains SHIFT`() {
        val mods = Modifiers.SHIFT + Modifiers.CTRL
        assertTrue(mods.contains(Modifiers.SHIFT), "SHIFT+CTRL must contain SHIFT")
    }

    @Test
    fun `SHIFT plus CTRL contains CTRL`() {
        val mods = Modifiers.SHIFT + Modifiers.CTRL
        assertTrue(mods.contains(Modifiers.CTRL), "SHIFT+CTRL must contain CTRL")
    }

    @Test
    fun `SHIFT plus CTRL does not contain ALT`() {
        val mods = Modifiers.SHIFT + Modifiers.CTRL
        assertFalse(mods.contains(Modifiers.ALT), "SHIFT+CTRL must not contain ALT")
    }

    @Test
    fun `NONE contains no modifier`() {
        val mods = Modifiers.NONE
        assertFalse(mods.shift, "NONE.shift must be false")
        assertFalse(mods.ctrl,  "NONE.ctrl must be false")
        assertFalse(mods.alt,   "NONE.alt must be false")
        assertFalse(mods.meta,  "NONE.meta must be false")
    }

    @Test
    fun `each constant enables exactly its boolean property`() {
        assertTrue(Modifiers.SHIFT.shift, "SHIFT.shift must be true")
        assertTrue(Modifiers.CTRL.ctrl,   "CTRL.ctrl must be true")
        assertTrue(Modifiers.ALT.alt,     "ALT.alt must be true")
        assertTrue(Modifiers.META.meta,   "META.meta must be true")
    }

    @Test
    fun `SHIFT plus CTRL plus ALT plus META enables all four properties`() {
        val tout = Modifiers.SHIFT + Modifiers.CTRL + Modifiers.ALT + Modifiers.META
        assertTrue(tout.shift, "shift must be true")
        assertTrue(tout.ctrl,  "ctrl must be true")
        assertTrue(tout.alt,   "alt must be true")
        assertTrue(tout.meta,  "meta must be true")
    }

    @Test
    fun `NONE contains NONE`() {
        assertTrue(Modifiers.NONE.contains(Modifiers.NONE))
    }

    @Test
    fun `plus is idempotent`() {
        val mods = Modifiers.SHIFT + Modifiers.SHIFT
        assertEquals(Modifiers.SHIFT, mods, "SHIFT+SHIFT must equal SHIFT")
    }

    // -----------------------------------------------------------------------
    // Key — coverage of the required set
    // -----------------------------------------------------------------------

    @Test
    fun `all letters A to Z are present in Key`() {
        val lettres = ('A'..'Z').map { it.toString() }
        val entrees = Key.entries.map { it.name }
        for (lettre in lettres) {
            assertTrue(lettre in entrees, "Key.$lettre missing")
        }
    }

    @Test
    fun `all digits Digit0 to Digit9 are present in Key`() {
        val chiffres = (0..9).map { "Digit$it" }
        val entrees = Key.entries.map { it.name }
        for (chiffre in chiffres) {
            assertTrue(chiffre in entrees, "Key.$chiffre missing")
        }
    }

    @Test
    fun `all function keys F1 to F12 are present in Key`() {
        val fonctions = (1..12).map { "F$it" }
        val entrees = Key.entries.map { it.name }
        for (fn in fonctions) {
            assertTrue(fn in entrees, "Key.$fn missing")
        }
    }

    @Test
    fun `navigation keys are present in Key`() {
        val navigation = listOf("ArrowUp", "ArrowDown", "ArrowLeft", "ArrowRight")
        val entrees = Key.entries.map { it.name }
        for (touche in navigation) {
            assertTrue(touche in entrees, "Key.$touche missing")
        }
    }

    @Test
    fun `modifiers are present in Key`() {
        val modificateurs = listOf(
            "ShiftLeft", "ShiftRight",
            "ControlLeft", "ControlRight",
            "AltLeft", "AltRight",
            "MetaLeft", "MetaRight",
        )
        val entrees = Key.entries.map { it.name }
        for (touche in modificateurs) {
            assertTrue(touche in entrees, "Key.$touche missing")
        }
    }

    @Test
    fun `special keys are present in Key`() {
        val speciales = listOf("Space", "Enter", "Escape", "Backspace", "Tab", "Unknown")
        val entrees = Key.entries.map { it.name }
        for (touche in speciales) {
            assertTrue(touche in entrees, "Key.$touche missing")
        }
    }

    // -----------------------------------------------------------------------
    // KeyState
    // -----------------------------------------------------------------------

    @Test
    fun `KeyState has exactly Pressed and Released`() {
        val noms = KeyState.entries.map { it.name }.toSet()
        assertEquals(setOf("Pressed", "Released"), noms)
    }

    // -----------------------------------------------------------------------
    // TouchPhase
    // -----------------------------------------------------------------------

    @Test
    fun `TouchPhase has exactly the four phases`() {
        val noms = TouchPhase.entries.map { it.name }.toSet()
        assertEquals(setOf("Started", "Moved", "Ended", "Cancelled"), noms)
    }

    // -----------------------------------------------------------------------
    // MouseButton — equality and structure
    // -----------------------------------------------------------------------

    @Test
    fun `MouseButton Left is a singleton`() {
        assertTrue(MouseButton.Left === MouseButton.Left)
    }

    @Test
    fun `MouseButton Other keeps its index`() {
        val bouton = MouseButton.Other(5)
        assertEquals(5, bouton.button)
    }

    @Test
    fun `two MouseButton Other with same indices are equal`() {
        assertEquals(MouseButton.Other(3), MouseButton.Other(3))
    }

    // -----------------------------------------------------------------------
    // WindowEvent — when exhaustiveness (without else) + variant construction
    // -----------------------------------------------------------------------

    /**
     * Explicitly enumerates the 28 variants without an `else` clause.
     * If a variant is added or removed, this `when` will no longer compile.
     * R4 added: ModifiersChanged.
     * R5-IME added: Ime.
     * R5-DnD added: DragEntered, DragMoved, DragDropped, DragLeft.
     * R5-Gestures added: PinchGesture, PanGesture, RotationGesture, DoubleTapGesture, TouchpadPressure.
     * R5-MiscWindow added: Occluded.
     */
    private fun classerWindowEvent(event: WindowEvent): String = when (event) {
        WindowEvent.CloseRequested        -> "CloseRequested"
        is WindowEvent.Resized            -> "Resized"
        is WindowEvent.Moved              -> "Moved"
        is WindowEvent.ScaleFactorChanged -> "ScaleFactorChanged"
        is WindowEvent.Focused            -> "Focused"
        is WindowEvent.KeyboardInput      -> "KeyboardInput"
        is WindowEvent.PointerMoved       -> "PointerMoved"
        WindowEvent.PointerEntered        -> "PointerEntered"
        WindowEvent.PointerLeft           -> "PointerLeft"
        is WindowEvent.MouseInput         -> "MouseInput"
        is WindowEvent.MouseWheel         -> "MouseWheel"
        is WindowEvent.Touch              -> "Touch"
        WindowEvent.RedrawRequested       -> "RedrawRequested"
        WindowEvent.Destroyed             -> "Destroyed"
        is WindowEvent.ThemeChanged       -> "ThemeChanged"
        is WindowEvent.ModifiersChanged   -> "ModifiersChanged"  // R4
        is WindowEvent.Ime                -> "Ime"               // R5-IME
        is WindowEvent.DragEntered        -> "DragEntered"       // R5-DnD
        is WindowEvent.DragMoved          -> "DragMoved"         // R5-DnD
        is WindowEvent.DragDropped        -> "DragDropped"       // R5-DnD
        WindowEvent.DragLeft              -> "DragLeft"          // R5-DnD
        is WindowEvent.PinchGesture       -> "PinchGesture"      // R5-Gestures
        is WindowEvent.PanGesture         -> "PanGesture"        // R5-Gestures
        is WindowEvent.RotationGesture    -> "RotationGesture"   // R5-Gestures
        WindowEvent.DoubleTapGesture      -> "DoubleTapGesture"  // R5-Gestures
        is WindowEvent.TouchpadPressure   -> "TouchpadPressure"  // R5-Gestures
        is WindowEvent.Occluded           -> "Occluded"          // R5-MiscWindow
    }

    @Test
    fun `WindowEvent CloseRequested is correctly classified`() {
        assertEquals("CloseRequested", classerWindowEvent(WindowEvent.CloseRequested))
    }

    @Test
    fun `WindowEvent Resized keeps the size`() {
        val taille = PhysicalSize(1920, 1080)
        val event = WindowEvent.Resized(taille)
        assertEquals("Resized", classerWindowEvent(event))
        assertEquals(taille, event.size)
    }

    @Test
    fun `WindowEvent Moved keeps the position`() {
        val pos = PhysicalPosition(100, 200)
        val event = WindowEvent.Moved(pos)
        assertEquals("Moved", classerWindowEvent(event))
        assertEquals(pos, event.position)
    }

    @Test
    fun `WindowEvent ScaleFactorChanged keeps the factor`() {
        val event = WindowEvent.ScaleFactorChanged(2.0)
        assertEquals("ScaleFactorChanged", classerWindowEvent(event))
        assertEquals(2.0, event.factor)
    }

    @Test
    fun `WindowEvent Focused keeps the boolean`() {
        val eventGained = WindowEvent.Focused(true)
        val eventLost   = WindowEvent.Focused(false)
        assertEquals("Focused", classerWindowEvent(eventGained))
        assertTrue(eventGained.gained)
        assertFalse(eventLost.gained)
    }

    @Test
    fun `WindowEvent KeyboardInput keeps key state and modifiers`() {
        val event = WindowEvent.KeyboardInput(Key.A, KeyState.Pressed, Modifiers.SHIFT)
        assertEquals("KeyboardInput", classerWindowEvent(event))
        assertEquals(Key.A, event.key)
        assertEquals(KeyState.Pressed, event.state)
        assertEquals(Modifiers.SHIFT, event.modifiers)
    }

    @Test
    fun `WindowEvent PointerMoved keeps the position`() {
        val pos = PhysicalPosition(123.4, 567.8)
        val event = WindowEvent.PointerMoved(pos)
        assertEquals("PointerMoved", classerWindowEvent(event))
        assertEquals(pos, event.position)
    }

    @Test
    fun `WindowEvent PointerEntered is correctly classified`() {
        assertEquals("PointerEntered", classerWindowEvent(WindowEvent.PointerEntered))
    }

    @Test
    fun `WindowEvent PointerLeft is correctly classified`() {
        assertEquals("PointerLeft", classerWindowEvent(WindowEvent.PointerLeft))
    }

    @Test
    fun `WindowEvent MouseInput keeps button and state`() {
        val event = WindowEvent.MouseInput(MouseButton.Left, KeyState.Released)
        assertEquals("MouseInput", classerWindowEvent(event))
        assertEquals(MouseButton.Left, event.button)
        assertEquals(KeyState.Released, event.state)
    }

    @Test
    fun `WindowEvent MouseWheel keeps the deltas`() {
        val event = WindowEvent.MouseWheel(3.0, -1.5)
        assertEquals("MouseWheel", classerWindowEvent(event))
        assertEquals(3.0,  event.deltaX)
        assertEquals(-1.5, event.deltaY)
    }

    @Test
    fun `WindowEvent Touch keeps phase location and id`() {
        val loc = PhysicalPosition(50.0, 75.0)
        val event = WindowEvent.Touch(TouchPhase.Started, loc, 42L)
        assertEquals("Touch", classerWindowEvent(event))
        assertEquals(TouchPhase.Started, event.phase)
        assertEquals(loc, event.location)
        assertEquals(42L, event.id)
    }

    @Test
    fun `WindowEvent RedrawRequested is correctly classified`() {
        assertEquals("RedrawRequested", classerWindowEvent(WindowEvent.RedrawRequested))
    }

    @Test
    fun `WindowEvent Destroyed is correctly classified`() {
        assertEquals("Destroyed", classerWindowEvent(WindowEvent.Destroyed))
    }

    @Test
    fun `WindowEvent ThemeChanged keeps the theme`() {
        val event = WindowEvent.ThemeChanged(Theme.Dark)
        assertEquals("ThemeChanged", classerWindowEvent(event))
        assertEquals(Theme.Dark, event.theme)
    }

    // -----------------------------------------------------------------------
    // WindowEvent.Ime — R5-IME
    // -----------------------------------------------------------------------

    @Test
    fun `WindowEvent Ime Commit keeps the committed text`() {
        val imeEvent = WindowEvent.Ime.ImeEvent.Commit("hello")
        val event = WindowEvent.Ime(imeEvent)
        assertEquals("Ime", classerWindowEvent(event))
        val commit = event.ime as WindowEvent.Ime.ImeEvent.Commit
        assertEquals("hello", commit.text)
    }

    @Test
    fun `WindowEvent Ime Preedit keeps text and cursor range`() {
        val imeEvent = WindowEvent.Ime.ImeEvent.Preedit("こん", Pair(0, 3))
        val event = WindowEvent.Ime(imeEvent)
        assertEquals("Ime", classerWindowEvent(event))
        val preedit = event.ime as WindowEvent.Ime.ImeEvent.Preedit
        assertEquals("こん", preedit.text)
        assertEquals(Pair(0, 3), preedit.cursorRange)
    }

    @Test
    fun `WindowEvent Ime Preedit with null cursor range`() {
        val imeEvent = WindowEvent.Ime.ImeEvent.Preedit("abc", null)
        val preedit = imeEvent
        assertEquals(null, preedit.cursorRange)
    }

    @Test
    fun `WindowEvent Ime Enabled and Disabled are singletons`() {
        val enabled1 = WindowEvent.Ime.ImeEvent.Enabled
        val enabled2 = WindowEvent.Ime.ImeEvent.Enabled
        assertTrue(enabled1 === enabled2, "Enabled must be a singleton")
        val disabled1 = WindowEvent.Ime.ImeEvent.Disabled
        val disabled2 = WindowEvent.Ime.ImeEvent.Disabled
        assertTrue(disabled1 === disabled2, "Disabled must be a singleton")
    }

    @Test
    fun `WindowEvent Ime DeleteSurrounding keeps before and after bytes`() {
        val imeEvent = WindowEvent.Ime.ImeEvent.DeleteSurrounding(3, 5)
        val event = WindowEvent.Ime(imeEvent)
        assertEquals("Ime", classerWindowEvent(event))
        val del = event.ime as WindowEvent.Ime.ImeEvent.DeleteSurrounding
        assertEquals(3, del.beforeBytes)
        assertEquals(5, del.afterBytes)
    }

    // -----------------------------------------------------------------------
    // WindowEvent — R5-DnD
    // -----------------------------------------------------------------------

    @Test
    fun `WindowEvent DragEntered keeps position and paths`() {
        val pos = PhysicalPosition(10.0, 20.0)
        val paths = listOf("/tmp/foo.txt", "/tmp/bar.png")
        val event = WindowEvent.DragEntered(pos, paths)
        assertEquals("DragEntered", classerWindowEvent(event))
        assertEquals(pos, event.position)
        assertEquals(paths, event.paths)
    }

    @Test
    fun `WindowEvent DragMoved keeps position`() {
        val pos = PhysicalPosition(50.0, 60.0)
        val event = WindowEvent.DragMoved(pos)
        assertEquals("DragMoved", classerWindowEvent(event))
        assertEquals(pos, event.position)
    }

    @Test
    fun `WindowEvent DragDropped keeps position and paths`() {
        val pos = PhysicalPosition(30.0, 40.0)
        val paths = listOf("/home/user/doc.pdf")
        val event = WindowEvent.DragDropped(pos, paths)
        assertEquals("DragDropped", classerWindowEvent(event))
        assertEquals(pos, event.position)
        assertEquals(paths, event.paths)
    }

    @Test
    fun `WindowEvent DragLeft is correctly classified`() {
        assertEquals("DragLeft", classerWindowEvent(WindowEvent.DragLeft))
    }

    // -----------------------------------------------------------------------
    // WindowEvent — R5-Gestures
    // -----------------------------------------------------------------------

    @Test
    fun `WindowEvent PinchGesture keeps delta and phase`() {
        val event = WindowEvent.PinchGesture(0.5, TouchPhase.Moved)
        assertEquals("PinchGesture", classerWindowEvent(event))
        assertEquals(0.5, event.delta)
        assertEquals(TouchPhase.Moved, event.phase)
    }

    @Test
    fun `WindowEvent PanGesture keeps delta and phase`() {
        val delta = PhysicalPosition(3.0, -1.0)
        val event = WindowEvent.PanGesture(delta, TouchPhase.Started)
        assertEquals("PanGesture", classerWindowEvent(event))
        assertEquals(delta, event.delta)
        assertEquals(TouchPhase.Started, event.phase)
    }

    @Test
    fun `WindowEvent RotationGesture keeps delta and phase`() {
        val event = WindowEvent.RotationGesture(1.57, TouchPhase.Ended)
        assertEquals("RotationGesture", classerWindowEvent(event))
        assertEquals(1.57, event.delta)
        assertEquals(TouchPhase.Ended, event.phase)
    }

    @Test
    fun `WindowEvent DoubleTapGesture is correctly classified`() {
        assertEquals("DoubleTapGesture", classerWindowEvent(WindowEvent.DoubleTapGesture))
    }

    @Test
    fun `WindowEvent TouchpadPressure keeps pressure and stage`() {
        val event = WindowEvent.TouchpadPressure(0.8f, 2)
        assertEquals("TouchpadPressure", classerWindowEvent(event))
        assertEquals(0.8f, event.pressure)
        assertEquals(2, event.stage)
    }

    // -----------------------------------------------------------------------
    // WindowEvent — R5-MiscWindow
    // -----------------------------------------------------------------------

    @Test
    fun `WindowEvent Occluded keeps the occluded flag`() {
        val occluded = WindowEvent.Occluded(true)
        val revealed = WindowEvent.Occluded(false)
        assertEquals("Occluded", classerWindowEvent(occluded))
        assertTrue(occluded.occluded)
        assertFalse(revealed.occluded)
    }

    // -----------------------------------------------------------------------
    // DeviceEvent — when exhaustiveness (without else) + variant construction
    // -----------------------------------------------------------------------

    /**
     * Explicitly enumerates the 4 variants without an `else` clause.
     * R4 added: MouseWheel.
     */
    private fun classerDeviceEvent(event: DeviceEvent): String = when (event) {
        is DeviceEvent.PointerMotion -> "PointerMotion"
        is DeviceEvent.Button        -> "Button"
        is DeviceEvent.Key           -> "Key"
        is DeviceEvent.MouseWheel    -> "MouseWheel"  // R4
    }

    @Test
    fun `DeviceEvent PointerMotion keeps dx and dy`() {
        val event = DeviceEvent.PointerMotion(1.5, -2.5)
        assertEquals("PointerMotion", classerDeviceEvent(event))
        assertEquals(1.5,  event.dx)
        assertEquals(-2.5, event.dy)
    }

    @Test
    fun `DeviceEvent Button keeps the button and the state`() {
        val event = DeviceEvent.Button(2, KeyState.Pressed)
        assertEquals("Button", classerDeviceEvent(event))
        assertEquals(2, event.button)
        assertEquals(KeyState.Pressed, event.state)
    }

    @Test
    fun `DeviceEvent Key keeps the scancode and the state`() {
        val event = DeviceEvent.Key(0x1E, KeyState.Released)
        assertEquals("Key", classerDeviceEvent(event))
        assertEquals(0x1E, event.scancode)
        assertEquals(KeyState.Released, event.state)
    }

    // -----------------------------------------------------------------------
    // Invariant DoD : (SHIFT + CTRL).contains(SHIFT) == true
    // -----------------------------------------------------------------------

    @Test
    fun `DoD - SHIFT plus CTRL contains SHIFT is true`() {
        assertTrue((Modifiers.SHIFT + Modifiers.CTRL).contains(Modifiers.SHIFT))
    }
}
