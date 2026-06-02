/**
 * Mapping of macOS key codes (NSEvent.keyCode) to Kadre keyboard types.
 *
 * Reference: QWERTY US keyboard layout.
 */
package org.graphiks.kadre.appkit

import org.graphiks.kadre.core.KeyCode
import org.graphiks.kadre.core.KeyboardModifiers
import org.graphiks.kadre.core.PhysicalKey

/**
 * Maps macOS virtual key codes (NSEvent.keyCode) to physical key codes.
 * Reference: QWERTY US keyboard layout key codes.
 */
internal object AppKitKeyMapper {

    fun physicalKey(code: Short): PhysicalKey = keyCode(code)?.let(PhysicalKey::Code)
        ?: PhysicalKey.Native(org.graphiks.kadre.core.KeyPlatform.AppKit, code.toLong())

    fun keyCode(code: Short): KeyCode? = when (code.toInt()) {
        // Letters (QWERTY US layout)
        0  -> KeyCode.KeyA
        11 -> KeyCode.KeyB
        8  -> KeyCode.KeyC
        2  -> KeyCode.KeyD
        14 -> KeyCode.KeyE
        3  -> KeyCode.KeyF
        5  -> KeyCode.KeyG
        4  -> KeyCode.KeyH
        34 -> KeyCode.KeyI
        38 -> KeyCode.KeyJ
        40 -> KeyCode.KeyK
        37 -> KeyCode.KeyL
        46 -> KeyCode.KeyM
        45 -> KeyCode.KeyN
        31 -> KeyCode.KeyO
        35 -> KeyCode.KeyP
        12 -> KeyCode.KeyQ
        15 -> KeyCode.KeyR
        1  -> KeyCode.KeyS
        17 -> KeyCode.KeyT
        32 -> KeyCode.KeyU
        9  -> KeyCode.KeyV
        13 -> KeyCode.KeyW
        7  -> KeyCode.KeyX
        16 -> KeyCode.KeyY
        6  -> KeyCode.KeyZ
        // Digits
        29 -> KeyCode.Digit0
        18 -> KeyCode.Digit1
        19 -> KeyCode.Digit2
        20 -> KeyCode.Digit3
        21 -> KeyCode.Digit4
        23 -> KeyCode.Digit5
        22 -> KeyCode.Digit6
        26 -> KeyCode.Digit7
        28 -> KeyCode.Digit8
        25 -> KeyCode.Digit9
        // Navigation
        123 -> KeyCode.ArrowLeft
        124 -> KeyCode.ArrowRight
        125 -> KeyCode.ArrowDown
        126 -> KeyCode.ArrowUp
        // Special keys
        36  -> KeyCode.Enter
        49  -> KeyCode.Space
        48  -> KeyCode.Tab
        51  -> KeyCode.Backspace
        53  -> KeyCode.Escape
        // Function keys
        122 -> KeyCode.F1
        120 -> KeyCode.F2
        99  -> KeyCode.F3
        118 -> KeyCode.F4
        96  -> KeyCode.F5
        97  -> KeyCode.F6
        98  -> KeyCode.F7
        100 -> KeyCode.F8
        101 -> KeyCode.F9
        109 -> KeyCode.F10
        103 -> KeyCode.F11
        111 -> KeyCode.F12
        else -> null
    }

    /**
     * Maps NSEventModifierFlags bitmask to Kadre [KeyboardModifiers].
     *
     * NSEventModifierFlagShift   = 0x20000
     * NSEventModifierFlagControl = 0x40000
     * NSEventModifierFlagOption  = 0x80000  (Alt)
     * NSEventModifierFlagCommand = 0x100000 (Meta)
     */
    fun modifierFlags(flags: Long): KeyboardModifiers {
        var mods = KeyboardModifiers.NONE
        if (flags and 0x20000L  != 0L) mods += KeyboardModifiers.Shift
        if (flags and 0x40000L  != 0L) mods += KeyboardModifiers.Ctrl
        if (flags and 0x80000L  != 0L) mods += KeyboardModifiers.Alt
        if (flags and 0x100000L != 0L) mods += KeyboardModifiers.Meta
        return mods
    }
}
