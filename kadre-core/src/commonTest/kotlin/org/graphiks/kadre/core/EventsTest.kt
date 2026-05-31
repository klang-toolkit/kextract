/**
 * Tests unitaires pour le modèle d'événements kadre-core.
 *
 * Vérifie :
 * - L'exhaustivité des `when` sur [WindowEvent] et [DeviceEvent] (sans `else`)
 * - La logique de bits de [Modifiers] : combinaison et appartenance
 * - La construction et l'égalité de chaque variant
 * - L'enum [Key] couvre au moins le set requis (lettres, chiffres, F1–F12, navigation)
 * - L'enum [KeyState] possède exactement les deux états attendus
 * - L'enum [TouchPhase] possède exactement les quatre phases attendues
 */
package org.graphiks.kadre.core

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class EventsTest {

    // -----------------------------------------------------------------------
    // Modifiers — logique de bits
    // -----------------------------------------------------------------------

    @Test
    fun `SHIFT plus CTRL contient SHIFT`() {
        val mods = Modifiers.SHIFT + Modifiers.CTRL
        assertTrue(mods.contains(Modifiers.SHIFT), "SHIFT+CTRL doit contenir SHIFT")
    }

    @Test
    fun `SHIFT plus CTRL contient CTRL`() {
        val mods = Modifiers.SHIFT + Modifiers.CTRL
        assertTrue(mods.contains(Modifiers.CTRL), "SHIFT+CTRL doit contenir CTRL")
    }

    @Test
    fun `SHIFT plus CTRL ne contient pas ALT`() {
        val mods = Modifiers.SHIFT + Modifiers.CTRL
        assertFalse(mods.contains(Modifiers.ALT), "SHIFT+CTRL ne doit pas contenir ALT")
    }

    @Test
    fun `NONE ne contient aucun modificateur`() {
        val mods = Modifiers.NONE
        assertFalse(mods.shift, "NONE.shift doit être false")
        assertFalse(mods.ctrl,  "NONE.ctrl doit être false")
        assertFalse(mods.alt,   "NONE.alt doit être false")
        assertFalse(mods.meta,  "NONE.meta doit être false")
    }

    @Test
    fun `chaque constante active exactement sa propriété booléenne`() {
        assertTrue(Modifiers.SHIFT.shift, "SHIFT.shift doit être true")
        assertTrue(Modifiers.CTRL.ctrl,   "CTRL.ctrl doit être true")
        assertTrue(Modifiers.ALT.alt,     "ALT.alt doit être true")
        assertTrue(Modifiers.META.meta,   "META.meta doit être true")
    }

    @Test
    fun `SHIFT plus CTRL plus ALT plus META active les quatre propriétés`() {
        val tout = Modifiers.SHIFT + Modifiers.CTRL + Modifiers.ALT + Modifiers.META
        assertTrue(tout.shift, "shift doit être true")
        assertTrue(tout.ctrl,  "ctrl doit être true")
        assertTrue(tout.alt,   "alt doit être true")
        assertTrue(tout.meta,  "meta doit être true")
    }

    @Test
    fun `NONE contient NONE`() {
        assertTrue(Modifiers.NONE.contains(Modifiers.NONE))
    }

    @Test
    fun `plus est idempotent`() {
        val mods = Modifiers.SHIFT + Modifiers.SHIFT
        assertEquals(Modifiers.SHIFT, mods, "SHIFT+SHIFT doit égaler SHIFT")
    }

    // -----------------------------------------------------------------------
    // Key — couverture du set requis
    // -----------------------------------------------------------------------

    @Test
    fun `toutes les lettres A a Z sont presentes dans Key`() {
        val lettres = ('A'..'Z').map { it.toString() }
        val entrees = Key.entries.map { it.name }
        for (lettre in lettres) {
            assertTrue(lettre in entrees, "Key.$lettre manquant")
        }
    }

    @Test
    fun `tous les chiffres Digit0 a Digit9 sont presents dans Key`() {
        val chiffres = (0..9).map { "Digit$it" }
        val entrees = Key.entries.map { it.name }
        for (chiffre in chiffres) {
            assertTrue(chiffre in entrees, "Key.$chiffre manquant")
        }
    }

    @Test
    fun `toutes les touches de fonction F1 a F12 sont presentes dans Key`() {
        val fonctions = (1..12).map { "F$it" }
        val entrees = Key.entries.map { it.name }
        for (fn in fonctions) {
            assertTrue(fn in entrees, "Key.$fn manquant")
        }
    }

    @Test
    fun `les touches de navigation sont presentes dans Key`() {
        val navigation = listOf("ArrowUp", "ArrowDown", "ArrowLeft", "ArrowRight")
        val entrees = Key.entries.map { it.name }
        for (touche in navigation) {
            assertTrue(touche in entrees, "Key.$touche manquant")
        }
    }

    @Test
    fun `les modificateurs sont presents dans Key`() {
        val modificateurs = listOf(
            "ShiftLeft", "ShiftRight",
            "ControlLeft", "ControlRight",
            "AltLeft", "AltRight",
            "MetaLeft", "MetaRight",
        )
        val entrees = Key.entries.map { it.name }
        for (touche in modificateurs) {
            assertTrue(touche in entrees, "Key.$touche manquant")
        }
    }

    @Test
    fun `les touches speciales sont presentes dans Key`() {
        val speciales = listOf("Space", "Enter", "Escape", "Backspace", "Tab", "Unknown")
        val entrees = Key.entries.map { it.name }
        for (touche in speciales) {
            assertTrue(touche in entrees, "Key.$touche manquant")
        }
    }

    // -----------------------------------------------------------------------
    // KeyState
    // -----------------------------------------------------------------------

    @Test
    fun `KeyState possede exactement Pressed et Released`() {
        val noms = KeyState.entries.map { it.name }.toSet()
        assertEquals(setOf("Pressed", "Released"), noms)
    }

    // -----------------------------------------------------------------------
    // TouchPhase
    // -----------------------------------------------------------------------

    @Test
    fun `TouchPhase possede exactement les quatre phases`() {
        val noms = TouchPhase.entries.map { it.name }.toSet()
        assertEquals(setOf("Started", "Moved", "Ended", "Cancelled"), noms)
    }

    // -----------------------------------------------------------------------
    // MouseButton — égalité et structure
    // -----------------------------------------------------------------------

    @Test
    fun `MouseButton Left est un singleton`() {
        assertTrue(MouseButton.Left === MouseButton.Left)
    }

    @Test
    fun `MouseButton Other conserve son indice`() {
        val bouton = MouseButton.Other(5)
        assertEquals(5, bouton.button)
    }

    @Test
    fun `deux MouseButton Other avec memes indices sont egaux`() {
        assertEquals(MouseButton.Other(3), MouseButton.Other(3))
    }

    // -----------------------------------------------------------------------
    // WindowEvent — exhaustivité when (sans else) + construction des variants
    // -----------------------------------------------------------------------

    /**
     * Enumère explicitement les 14 variants sans clause `else`.
     * Si un variant est ajouté ou retiré, ce `when` ne compilera plus.
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
    }

    @Test
    fun `WindowEvent CloseRequested est correctement classe`() {
        assertEquals("CloseRequested", classerWindowEvent(WindowEvent.CloseRequested))
    }

    @Test
    fun `WindowEvent Resized conserve la taille`() {
        val taille = PhysicalSize(1920, 1080)
        val event = WindowEvent.Resized(taille)
        assertEquals("Resized", classerWindowEvent(event))
        assertEquals(taille, event.size)
    }

    @Test
    fun `WindowEvent Moved conserve la position`() {
        val pos = PhysicalPosition(100, 200)
        val event = WindowEvent.Moved(pos)
        assertEquals("Moved", classerWindowEvent(event))
        assertEquals(pos, event.position)
    }

    @Test
    fun `WindowEvent ScaleFactorChanged conserve le facteur`() {
        val event = WindowEvent.ScaleFactorChanged(2.0)
        assertEquals("ScaleFactorChanged", classerWindowEvent(event))
        assertEquals(2.0, event.factor)
    }

    @Test
    fun `WindowEvent Focused conserve le booleen`() {
        val eventGained = WindowEvent.Focused(true)
        val eventLost   = WindowEvent.Focused(false)
        assertEquals("Focused", classerWindowEvent(eventGained))
        assertTrue(eventGained.gained)
        assertFalse(eventLost.gained)
    }

    @Test
    fun `WindowEvent KeyboardInput conserve key state et modifiers`() {
        val event = WindowEvent.KeyboardInput(Key.A, KeyState.Pressed, Modifiers.SHIFT)
        assertEquals("KeyboardInput", classerWindowEvent(event))
        assertEquals(Key.A, event.key)
        assertEquals(KeyState.Pressed, event.state)
        assertEquals(Modifiers.SHIFT, event.modifiers)
    }

    @Test
    fun `WindowEvent PointerMoved conserve la position`() {
        val pos = PhysicalPosition(123.4, 567.8)
        val event = WindowEvent.PointerMoved(pos)
        assertEquals("PointerMoved", classerWindowEvent(event))
        assertEquals(pos, event.position)
    }

    @Test
    fun `WindowEvent PointerEntered est correctement classe`() {
        assertEquals("PointerEntered", classerWindowEvent(WindowEvent.PointerEntered))
    }

    @Test
    fun `WindowEvent PointerLeft est correctement classe`() {
        assertEquals("PointerLeft", classerWindowEvent(WindowEvent.PointerLeft))
    }

    @Test
    fun `WindowEvent MouseInput conserve bouton et etat`() {
        val event = WindowEvent.MouseInput(MouseButton.Left, KeyState.Released)
        assertEquals("MouseInput", classerWindowEvent(event))
        assertEquals(MouseButton.Left, event.button)
        assertEquals(KeyState.Released, event.state)
    }

    @Test
    fun `WindowEvent MouseWheel conserve les deltas`() {
        val event = WindowEvent.MouseWheel(3.0, -1.5)
        assertEquals("MouseWheel", classerWindowEvent(event))
        assertEquals(3.0,  event.deltaX)
        assertEquals(-1.5, event.deltaY)
    }

    @Test
    fun `WindowEvent Touch conserve phase location et id`() {
        val loc = PhysicalPosition(50.0, 75.0)
        val event = WindowEvent.Touch(TouchPhase.Started, loc, 42L)
        assertEquals("Touch", classerWindowEvent(event))
        assertEquals(TouchPhase.Started, event.phase)
        assertEquals(loc, event.location)
        assertEquals(42L, event.id)
    }

    @Test
    fun `WindowEvent RedrawRequested est correctement classe`() {
        assertEquals("RedrawRequested", classerWindowEvent(WindowEvent.RedrawRequested))
    }

    @Test
    fun `WindowEvent Destroyed est correctement classe`() {
        assertEquals("Destroyed", classerWindowEvent(WindowEvent.Destroyed))
    }

    // -----------------------------------------------------------------------
    // DeviceEvent — exhaustivité when (sans else) + construction des variants
    // -----------------------------------------------------------------------

    /**
     * Enumère explicitement les 3 variants sans clause `else`.
     */
    private fun classerDeviceEvent(event: DeviceEvent): String = when (event) {
        is DeviceEvent.PointerMotion -> "PointerMotion"
        is DeviceEvent.Button        -> "Button"
        is DeviceEvent.Key           -> "Key"
    }

    @Test
    fun `DeviceEvent PointerMotion conserve dx et dy`() {
        val event = DeviceEvent.PointerMotion(1.5, -2.5)
        assertEquals("PointerMotion", classerDeviceEvent(event))
        assertEquals(1.5,  event.dx)
        assertEquals(-2.5, event.dy)
    }

    @Test
    fun `DeviceEvent Button conserve le bouton et l etat`() {
        val event = DeviceEvent.Button(2, KeyState.Pressed)
        assertEquals("Button", classerDeviceEvent(event))
        assertEquals(2, event.button)
        assertEquals(KeyState.Pressed, event.state)
    }

    @Test
    fun `DeviceEvent Key conserve le scancode et l etat`() {
        val event = DeviceEvent.Key(0x1E, KeyState.Released)
        assertEquals("Key", classerDeviceEvent(event))
        assertEquals(0x1E, event.scancode)
        assertEquals(KeyState.Released, event.state)
    }

    // -----------------------------------------------------------------------
    // Invariant DoD : (SHIFT + CTRL).contains(SHIFT) == true
    // -----------------------------------------------------------------------

    @Test
    fun `DoD - SHIFT plus CTRL contient SHIFT est vrai`() {
        assertTrue((Modifiers.SHIFT + Modifiers.CTRL).contains(Modifiers.SHIFT))
    }
}
