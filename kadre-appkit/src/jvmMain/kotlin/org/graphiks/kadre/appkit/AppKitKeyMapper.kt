/**
 * Mapping des codes de touches macOS (NSEvent.keyCode) vers l'énumération [Key] de Kadre.
 *
 * Référence : disposition de clavier QWERTY US.
 */
package org.graphiks.kadre.appkit

import org.graphiks.kadre.core.Key
import org.graphiks.kadre.core.Modifiers

/**
 * Maps macOS virtual key codes (NSEvent.keyCode) to Kadre [Key] enum.
 * Reference: QWERTY US keyboard layout key codes.
 */
internal object AppKitKeyMapper {

    fun keyCode(code: Short): Key = when (code.toInt()) {
        // Letters (QWERTY US layout)
        0  -> Key.A
        11 -> Key.B
        8  -> Key.C
        2  -> Key.D
        14 -> Key.E
        3  -> Key.F
        5  -> Key.G
        4  -> Key.H
        34 -> Key.I
        38 -> Key.J
        40 -> Key.K
        37 -> Key.L
        46 -> Key.M
        45 -> Key.N
        31 -> Key.O
        35 -> Key.P
        12 -> Key.Q
        15 -> Key.R
        1  -> Key.S
        17 -> Key.T
        32 -> Key.U
        9  -> Key.V
        13 -> Key.W
        7  -> Key.X
        16 -> Key.Y
        6  -> Key.Z
        // Digits
        29 -> Key.Digit0
        18 -> Key.Digit1
        19 -> Key.Digit2
        20 -> Key.Digit3
        21 -> Key.Digit4
        23 -> Key.Digit5
        22 -> Key.Digit6
        26 -> Key.Digit7
        28 -> Key.Digit8
        25 -> Key.Digit9
        // Navigation
        123 -> Key.ArrowLeft
        124 -> Key.ArrowRight
        125 -> Key.ArrowDown
        126 -> Key.ArrowUp
        // Special keys
        36  -> Key.Enter
        49  -> Key.Space
        48  -> Key.Tab
        51  -> Key.Backspace
        53  -> Key.Escape
        // Function keys
        122 -> Key.F1
        120 -> Key.F2
        99  -> Key.F3
        118 -> Key.F4
        96  -> Key.F5
        97  -> Key.F6
        98  -> Key.F7
        100 -> Key.F8
        101 -> Key.F9
        109 -> Key.F10
        103 -> Key.F11
        111 -> Key.F12
        else -> Key.Unknown
    }

    /**
     * Maps NSEventModifierFlags bitmask to Kadre [Modifiers].
     *
     * NSEventModifierFlagShift   = 0x20000
     * NSEventModifierFlagControl = 0x40000
     * NSEventModifierFlagOption  = 0x80000  (Alt)
     * NSEventModifierFlagCommand = 0x100000 (Meta)
     */
    fun modifierFlags(flags: Long): Modifiers {
        var mods = Modifiers.NONE
        if (flags and 0x20000L  != 0L) mods = mods + Modifiers.SHIFT
        if (flags and 0x40000L  != 0L) mods = mods + Modifiers.CTRL
        if (flags and 0x80000L  != 0L) mods = mods + Modifiers.ALT
        if (flags and 0x100000L != 0L) mods = mods + Modifiers.META
        return mods
    }
}
