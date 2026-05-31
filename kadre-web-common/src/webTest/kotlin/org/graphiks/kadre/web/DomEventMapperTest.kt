/**
 * Tests des fonctions pures de DomEventMapper.
 *
 * Ces tests vivent dans webTest (source set intermédiaire) : ils s'exécutent
 * aussi bien sur la cible js que sur wasmJs. Aucune dépendance DOM n'est requise
 * car les fonctions testées sont 100 % Kotlin pur.
 */
package org.graphiks.kadre.web

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class DomEventMapperTest {

    // -----------------------------------------------------------------------
    // domCodeToKey — lettres
    // -----------------------------------------------------------------------

    @Test
    fun `domCodeToKey mappe les lettres`() {
        assertEquals(WebKey.A, domCodeToKey("KeyA"))
        assertEquals(WebKey.Z, domCodeToKey("KeyZ"))
        assertEquals(WebKey.M, domCodeToKey("KeyM"))
    }

    // -----------------------------------------------------------------------
    // domCodeToKey — chiffres
    // -----------------------------------------------------------------------

    @Test
    fun `domCodeToKey mappe les chiffres`() {
        assertEquals(WebKey.Digit0, domCodeToKey("Digit0"))
        assertEquals(WebKey.Digit9, domCodeToKey("Digit9"))
        assertEquals(WebKey.Digit5, domCodeToKey("Digit5"))
    }

    // -----------------------------------------------------------------------
    // domCodeToKey — touches de fonction
    // -----------------------------------------------------------------------

    @Test
    fun `domCodeToKey mappe les touches de fonction`() {
        assertEquals(WebKey.F1,  domCodeToKey("F1"))
        assertEquals(WebKey.F12, domCodeToKey("F12"))
        assertEquals(WebKey.F6,  domCodeToKey("F6"))
    }

    // -----------------------------------------------------------------------
    // domCodeToKey — touches spéciales
    // -----------------------------------------------------------------------

    @Test
    fun `domCodeToKey mappe les touches spéciales`() {
        assertEquals(WebKey.Space,     domCodeToKey("Space"))
        assertEquals(WebKey.Enter,     domCodeToKey("Enter"))
        assertEquals(WebKey.Escape,    domCodeToKey("Escape"))
        assertEquals(WebKey.Backspace, domCodeToKey("Backspace"))
        assertEquals(WebKey.Tab,       domCodeToKey("Tab"))
    }

    // -----------------------------------------------------------------------
    // domCodeToKey — navigation
    // -----------------------------------------------------------------------

    @Test
    fun `domCodeToKey mappe les touches de navigation`() {
        assertEquals(WebKey.ArrowUp,    domCodeToKey("ArrowUp"))
        assertEquals(WebKey.ArrowDown,  domCodeToKey("ArrowDown"))
        assertEquals(WebKey.ArrowLeft,  domCodeToKey("ArrowLeft"))
        assertEquals(WebKey.ArrowRight, domCodeToKey("ArrowRight"))
    }

    // -----------------------------------------------------------------------
    // domCodeToKey — modificateurs
    // -----------------------------------------------------------------------

    @Test
    fun `domCodeToKey mappe les modificateurs`() {
        assertEquals(WebKey.ShiftLeft,    domCodeToKey("ShiftLeft"))
        assertEquals(WebKey.ShiftRight,   domCodeToKey("ShiftRight"))
        assertEquals(WebKey.ControlLeft,  domCodeToKey("ControlLeft"))
        assertEquals(WebKey.ControlRight, domCodeToKey("ControlRight"))
        assertEquals(WebKey.AltLeft,      domCodeToKey("AltLeft"))
        assertEquals(WebKey.AltRight,     domCodeToKey("AltRight"))
        assertEquals(WebKey.MetaLeft,     domCodeToKey("MetaLeft"))
        assertEquals(WebKey.MetaRight,    domCodeToKey("MetaRight"))
    }

    // -----------------------------------------------------------------------
    // domCodeToKey — inconnu
    // -----------------------------------------------------------------------

    @Test
    fun `domCodeToKey retourne Unknown pour un code non reconnu`() {
        assertEquals(WebKey.Unknown, domCodeToKey(""))
        assertEquals(WebKey.Unknown, domCodeToKey("NumpadAdd"))
        assertEquals(WebKey.Unknown, domCodeToKey("BrowserBack"))
    }

    // -----------------------------------------------------------------------
    // domModifiers
    // -----------------------------------------------------------------------

    @Test
    fun `domModifiers retourne NONE quand tous les drapeaux sont faux`() {
        val mods = domModifiers(shiftKey = false, ctrlKey = false, altKey = false, metaKey = false)
        assertEquals(WebModifiers.NONE, mods)
    }

    @Test
    fun `domModifiers retourne SHIFT quand shiftKey est vrai`() {
        val mods = domModifiers(shiftKey = true, ctrlKey = false, altKey = false, metaKey = false)
        assertTrue(mods.shift)
        assertFalse(mods.ctrl)
        assertFalse(mods.alt)
        assertFalse(mods.meta)
    }

    @Test
    fun `domModifiers retourne CTRL quand ctrlKey est vrai`() {
        val mods = domModifiers(shiftKey = false, ctrlKey = true, altKey = false, metaKey = false)
        assertFalse(mods.shift)
        assertTrue(mods.ctrl)
    }

    @Test
    fun `domModifiers retourne ALT quand altKey est vrai`() {
        val mods = domModifiers(shiftKey = false, ctrlKey = false, altKey = true, metaKey = false)
        assertTrue(mods.alt)
    }

    @Test
    fun `domModifiers retourne META quand metaKey est vrai`() {
        val mods = domModifiers(shiftKey = false, ctrlKey = false, altKey = false, metaKey = true)
        assertTrue(mods.meta)
    }

    @Test
    fun `domModifiers combine correctement plusieurs modificateurs`() {
        val mods = domModifiers(shiftKey = true, ctrlKey = true, altKey = false, metaKey = false)
        assertTrue(mods.shift)
        assertTrue(mods.ctrl)
        assertFalse(mods.alt)
        assertFalse(mods.meta)
        assertTrue(mods.contains(WebModifiers.SHIFT + WebModifiers.CTRL))
    }

    @Test
    fun `domModifiers tous les modificateurs actifs`() {
        val mods = domModifiers(shiftKey = true, ctrlKey = true, altKey = true, metaKey = true)
        assertTrue(mods.shift)
        assertTrue(mods.ctrl)
        assertTrue(mods.alt)
        assertTrue(mods.meta)
    }

    // -----------------------------------------------------------------------
    // domButtonToMouseButton
    // -----------------------------------------------------------------------

    @Test
    fun `domButtonToMouseButton mappe le bouton gauche`() {
        assertEquals(WebMouseButton.Left, domButtonToMouseButton(0))
    }

    @Test
    fun `domButtonToMouseButton mappe le bouton milieu`() {
        assertEquals(WebMouseButton.Middle, domButtonToMouseButton(1))
    }

    @Test
    fun `domButtonToMouseButton mappe le bouton droit`() {
        assertEquals(WebMouseButton.Right, domButtonToMouseButton(2))
    }

    @Test
    fun `domButtonToMouseButton mappe les boutons supplémentaires en Other`() {
        assertEquals(WebMouseButton.Other(3), domButtonToMouseButton(3))
        assertEquals(WebMouseButton.Other(4), domButtonToMouseButton(4))
        assertEquals(WebMouseButton.Other(10), domButtonToMouseButton(10))
    }

    // -----------------------------------------------------------------------
    // domKeyStateFromEventType
    // -----------------------------------------------------------------------

    @Test
    fun `domKeyStateFromEventType retourne Pressed pour keydown`() {
        assertEquals(WebKeyState.Pressed, domKeyStateFromEventType("keydown"))
    }

    @Test
    fun `domKeyStateFromEventType retourne Released pour keyup`() {
        assertEquals(WebKeyState.Released, domKeyStateFromEventType("keyup"))
    }

    @Test
    fun `domKeyStateFromEventType retourne Pressed pour pointerdown`() {
        assertEquals(WebKeyState.Pressed, domKeyStateFromEventType("pointerdown"))
    }

    @Test
    fun `domKeyStateFromEventType retourne Released pour pointerup`() {
        assertEquals(WebKeyState.Released, domKeyStateFromEventType("pointerup"))
    }

    @Test
    fun `domKeyStateFromEventType retourne Released pour toute autre valeur`() {
        assertEquals(WebKeyState.Released, domKeyStateFromEventType(""))
        assertEquals(WebKeyState.Released, domKeyStateFromEventType("click"))
    }

    // -----------------------------------------------------------------------
    // normalizeWheelDelta
    // -----------------------------------------------------------------------

    @Test
    fun `normalizeWheelDelta ne modifie pas le delta en mode pixel (0)`() {
        assertEquals(42.0, normalizeWheelDelta(42.0, deltaMode = 0))
        assertEquals(-10.5, normalizeWheelDelta(-10.5, deltaMode = 0))
    }

    @Test
    fun `normalizeWheelDelta multiplie par 16 en mode ligne (1)`() {
        assertEquals(48.0, normalizeWheelDelta(3.0, deltaMode = 1))
        assertEquals(-16.0, normalizeWheelDelta(-1.0, deltaMode = 1))
    }

    @Test
    fun `normalizeWheelDelta multiplie par 600 en mode page (2)`() {
        assertEquals(600.0, normalizeWheelDelta(1.0, deltaMode = 2))
        assertEquals(-1200.0, normalizeWheelDelta(-2.0, deltaMode = 2))
    }

    @Test
    fun `normalizeWheelDelta traite un mode inconnu comme pixel`() {
        assertEquals(5.0, normalizeWheelDelta(5.0, deltaMode = 99))
    }

    @Test
    fun `normalizeWheelDelta gere un delta zero`() {
        assertEquals(0.0, normalizeWheelDelta(0.0, deltaMode = 0))
        assertEquals(0.0, normalizeWheelDelta(0.0, deltaMode = 1))
        assertEquals(0.0, normalizeWheelDelta(0.0, deltaMode = 2))
    }

    // -----------------------------------------------------------------------
    // WebWindowEvent — vérification des data class equals
    // -----------------------------------------------------------------------

    @Test
    fun `WebWindowEvent KeyboardInput egalite structurelle`() {
        val e1 = WebWindowEvent.KeyboardInput(
            key = WebKey.A,
            state = WebKeyState.Pressed,
            modifiers = WebModifiers.NONE,
            isRepeat = false,
        )
        val e2 = WebWindowEvent.KeyboardInput(
            key = WebKey.A,
            state = WebKeyState.Pressed,
            modifiers = WebModifiers.NONE,
            isRepeat = false,
        )
        assertEquals(e1, e2)
    }

    @Test
    fun `WebWindowEvent PointerMoved egalite structurelle`() {
        assertEquals(
            WebWindowEvent.PointerMoved(x = 10.0, y = 20.0),
            WebWindowEvent.PointerMoved(x = 10.0, y = 20.0),
        )
    }

    @Test
    fun `WebWindowEvent MouseInput egalite structurelle`() {
        assertEquals(
            WebWindowEvent.MouseInput(WebMouseButton.Left, WebKeyState.Pressed),
            WebWindowEvent.MouseInput(WebMouseButton.Left, WebKeyState.Pressed),
        )
    }

    @Test
    fun `WebWindowEvent MouseWheel egalite structurelle`() {
        assertEquals(
            WebWindowEvent.MouseWheel(deltaX = 1.0, deltaY = -2.0),
            WebWindowEvent.MouseWheel(deltaX = 1.0, deltaY = -2.0),
        )
    }

    @Test
    fun `WebWindowEvent Resized egalite structurelle`() {
        assertEquals(
            WebWindowEvent.Resized(width = 800, height = 600),
            WebWindowEvent.Resized(width = 800, height = 600),
        )
    }
}
