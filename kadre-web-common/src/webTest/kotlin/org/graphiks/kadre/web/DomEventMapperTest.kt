/**
 * Tests for the pure functions of DomEventMapper.
 *
 * These tests live in webTest (intermediate source set): they run
 * both on the js target and on wasmJs. No DOM dependency is required
 * since the tested functions are 100% pure Kotlin.
 */
package org.graphiks.kadre.web

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class DomEventMapperTest {

    // -----------------------------------------------------------------------
    // domCodeToKey — letters
    // -----------------------------------------------------------------------

    @Test
    fun `domCodeToKey maps letters`() {
        assertEquals(WebKey.A, domCodeToKey("KeyA"))
        assertEquals(WebKey.Z, domCodeToKey("KeyZ"))
        assertEquals(WebKey.M, domCodeToKey("KeyM"))
    }

    // -----------------------------------------------------------------------
    // domCodeToKey — digits
    // -----------------------------------------------------------------------

    @Test
    fun `domCodeToKey maps digits`() {
        assertEquals(WebKey.Digit0, domCodeToKey("Digit0"))
        assertEquals(WebKey.Digit9, domCodeToKey("Digit9"))
        assertEquals(WebKey.Digit5, domCodeToKey("Digit5"))
    }

    // -----------------------------------------------------------------------
    // domCodeToKey — function keys
    // -----------------------------------------------------------------------

    @Test
    fun `domCodeToKey maps function keys`() {
        assertEquals(WebKey.F1,  domCodeToKey("F1"))
        assertEquals(WebKey.F12, domCodeToKey("F12"))
        assertEquals(WebKey.F6,  domCodeToKey("F6"))
    }

    // -----------------------------------------------------------------------
    // domCodeToKey — special keys
    // -----------------------------------------------------------------------

    @Test
    fun `domCodeToKey maps special keys`() {
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
    fun `domCodeToKey maps navigation keys`() {
        assertEquals(WebKey.ArrowUp,    domCodeToKey("ArrowUp"))
        assertEquals(WebKey.ArrowDown,  domCodeToKey("ArrowDown"))
        assertEquals(WebKey.ArrowLeft,  domCodeToKey("ArrowLeft"))
        assertEquals(WebKey.ArrowRight, domCodeToKey("ArrowRight"))
    }

    // -----------------------------------------------------------------------
    // domCodeToKey — modifiers
    // -----------------------------------------------------------------------

    @Test
    fun `domCodeToKey maps modifiers`() {
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
    // domCodeToKey — unknown
    // -----------------------------------------------------------------------

    @Test
    fun `domCodeToKey returns Unknown for an unrecognized code`() {
        assertEquals(WebKey.Unknown, domCodeToKey(""))
        assertEquals(WebKey.Unknown, domCodeToKey("NumpadAdd"))
        assertEquals(WebKey.Unknown, domCodeToKey("BrowserBack"))
    }

    // -----------------------------------------------------------------------
    // domModifiers
    // -----------------------------------------------------------------------

    @Test
    fun `domModifiers returns NONE when all flags are false`() {
        val mods = domModifiers(shiftKey = false, ctrlKey = false, altKey = false, metaKey = false)
        assertEquals(WebModifiers.NONE, mods)
    }

    @Test
    fun `domModifiers returns SHIFT when shiftKey is true`() {
        val mods = domModifiers(shiftKey = true, ctrlKey = false, altKey = false, metaKey = false)
        assertTrue(mods.shift)
        assertFalse(mods.ctrl)
        assertFalse(mods.alt)
        assertFalse(mods.meta)
    }

    @Test
    fun `domModifiers returns CTRL when ctrlKey is true`() {
        val mods = domModifiers(shiftKey = false, ctrlKey = true, altKey = false, metaKey = false)
        assertFalse(mods.shift)
        assertTrue(mods.ctrl)
    }

    @Test
    fun `domModifiers returns ALT when altKey is true`() {
        val mods = domModifiers(shiftKey = false, ctrlKey = false, altKey = true, metaKey = false)
        assertTrue(mods.alt)
    }

    @Test
    fun `domModifiers returns META when metaKey is true`() {
        val mods = domModifiers(shiftKey = false, ctrlKey = false, altKey = false, metaKey = true)
        assertTrue(mods.meta)
    }

    @Test
    fun `domModifiers combines multiple modifiers correctly`() {
        val mods = domModifiers(shiftKey = true, ctrlKey = true, altKey = false, metaKey = false)
        assertTrue(mods.shift)
        assertTrue(mods.ctrl)
        assertFalse(mods.alt)
        assertFalse(mods.meta)
        assertTrue(mods.contains(WebModifiers.SHIFT + WebModifiers.CTRL))
    }

    @Test
    fun `domModifiers all modifiers active`() {
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
    fun `domButtonToMouseButton maps the left button`() {
        assertEquals(WebMouseButton.Left, domButtonToMouseButton(0))
    }

    @Test
    fun `domButtonToMouseButton maps the middle button`() {
        assertEquals(WebMouseButton.Middle, domButtonToMouseButton(1))
    }

    @Test
    fun `domButtonToMouseButton maps the right button`() {
        assertEquals(WebMouseButton.Right, domButtonToMouseButton(2))
    }

    @Test
    fun `domButtonToMouseButton maps extra buttons to Other`() {
        assertEquals(WebMouseButton.Other(3), domButtonToMouseButton(3))
        assertEquals(WebMouseButton.Other(4), domButtonToMouseButton(4))
        assertEquals(WebMouseButton.Other(10), domButtonToMouseButton(10))
    }

    // -----------------------------------------------------------------------
    // domKeyStateFromEventType
    // -----------------------------------------------------------------------

    @Test
    fun `domKeyStateFromEventType returns Pressed for keydown`() {
        assertEquals(WebKeyState.Pressed, domKeyStateFromEventType("keydown"))
    }

    @Test
    fun `domKeyStateFromEventType returns Released for keyup`() {
        assertEquals(WebKeyState.Released, domKeyStateFromEventType("keyup"))
    }

    @Test
    fun `domKeyStateFromEventType returns Pressed for pointerdown`() {
        assertEquals(WebKeyState.Pressed, domKeyStateFromEventType("pointerdown"))
    }

    @Test
    fun `domKeyStateFromEventType returns Released for pointerup`() {
        assertEquals(WebKeyState.Released, domKeyStateFromEventType("pointerup"))
    }

    @Test
    fun `domKeyStateFromEventType returns Released for any other value`() {
        assertEquals(WebKeyState.Released, domKeyStateFromEventType(""))
        assertEquals(WebKeyState.Released, domKeyStateFromEventType("click"))
    }

    // -----------------------------------------------------------------------
    // domTouchTypeToPhase
    // -----------------------------------------------------------------------

    @Test
    fun `domTouchTypeToPhase maps DOM types`() {
        assertEquals(WebTouchPhase.Started,   domTouchTypeToPhase("touchstart"))
        assertEquals(WebTouchPhase.Moved,     domTouchTypeToPhase("touchmove"))
        assertEquals(WebTouchPhase.Ended,     domTouchTypeToPhase("touchend"))
        assertEquals(WebTouchPhase.Cancelled, domTouchTypeToPhase("touchcancel"))
    }

    @Test
    fun `domTouchTypeToPhase returns Cancelled for an unknown type`() {
        assertEquals(WebTouchPhase.Cancelled, domTouchTypeToPhase(""))
        assertEquals(WebTouchPhase.Cancelled, domTouchTypeToPhase("click"))
    }

    // -----------------------------------------------------------------------
    // normalizeWheelDelta
    // -----------------------------------------------------------------------

    @Test
    fun `normalizeWheelDelta does not modify the delta in pixel mode (0)`() {
        assertEquals(42.0, normalizeWheelDelta(42.0, deltaMode = 0))
        assertEquals(-10.5, normalizeWheelDelta(-10.5, deltaMode = 0))
    }

    @Test
    fun `normalizeWheelDelta multiplies by 16 in line mode (1)`() {
        assertEquals(48.0, normalizeWheelDelta(3.0, deltaMode = 1))
        assertEquals(-16.0, normalizeWheelDelta(-1.0, deltaMode = 1))
    }

    @Test
    fun `normalizeWheelDelta multiplies by 600 in page mode (2)`() {
        assertEquals(600.0, normalizeWheelDelta(1.0, deltaMode = 2))
        assertEquals(-1200.0, normalizeWheelDelta(-2.0, deltaMode = 2))
    }

    @Test
    fun `normalizeWheelDelta treats an unknown mode as pixel`() {
        assertEquals(5.0, normalizeWheelDelta(5.0, deltaMode = 99))
    }

    @Test
    fun `normalizeWheelDelta handles a zero delta`() {
        assertEquals(0.0, normalizeWheelDelta(0.0, deltaMode = 0))
        assertEquals(0.0, normalizeWheelDelta(0.0, deltaMode = 1))
        assertEquals(0.0, normalizeWheelDelta(0.0, deltaMode = 2))
    }

    // -----------------------------------------------------------------------
    // WebWindowEvent — data class equals verification
    // -----------------------------------------------------------------------

    @Test
    fun `WebWindowEvent KeyboardInput structural equality`() {
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
    fun `WebWindowEvent PointerMoved structural equality`() {
        assertEquals(
            WebWindowEvent.PointerMoved(x = 10.0, y = 20.0),
            WebWindowEvent.PointerMoved(x = 10.0, y = 20.0),
        )
    }

    @Test
    fun `WebWindowEvent MouseInput structural equality`() {
        assertEquals(
            WebWindowEvent.MouseInput(WebMouseButton.Left, WebKeyState.Pressed),
            WebWindowEvent.MouseInput(WebMouseButton.Left, WebKeyState.Pressed),
        )
    }

    @Test
    fun `WebWindowEvent MouseWheel structural equality`() {
        assertEquals(
            WebWindowEvent.MouseWheel(deltaX = 1.0, deltaY = -2.0),
            WebWindowEvent.MouseWheel(deltaX = 1.0, deltaY = -2.0),
        )
    }

    @Test
    fun `WebWindowEvent Resized structural equality`() {
        assertEquals(
            WebWindowEvent.Resized(width = 800, height = 600),
            WebWindowEvent.Resized(width = 800, height = 600),
        )
    }

    @Test
    fun `WebWindowEvent Touch structural equality`() {
        assertEquals(
            WebWindowEvent.Touch(WebTouchPhase.Started, x = 12.0, y = 34.0, id = 1L),
            WebWindowEvent.Touch(WebTouchPhase.Started, x = 12.0, y = 34.0, id = 1L),
        )
    }

    @Test
    fun `WebWindowEvent ScaleFactorChanged structural equality`() {
        assertEquals(
            WebWindowEvent.ScaleFactorChanged(factor = 2.0),
            WebWindowEvent.ScaleFactorChanged(factor = 2.0),
        )
    }

    @Test
    fun `WebWindowEvent CloseRequested and Destroyed are singletons`() {
        assertEquals(WebWindowEvent.CloseRequested, WebWindowEvent.CloseRequested)
        assertEquals(WebWindowEvent.Destroyed, WebWindowEvent.Destroyed)
    }
}
