/**
 * Mapping of Win32 virtual key codes (VK_*) to kadre logical keys [Key].
 *
 * Reference: https://learn.microsoft.com/en-us/windows/win32/inputdev/virtual-key-codes
 *
 * The VK codes are passed in the wParam of the WM_KEYDOWN / WM_KEYUP /
 * WM_SYSKEYDOWN / WM_SYSKEYUP messages. This mapper provides the complete mapping
 * for the A–Z, 0–9, F1–F12, arrow, modifier and special keys.
 */
package org.graphiks.kadre.win32

import org.graphiks.kadre.core.Key

/**
 * Converts a Win32 virtual key code (VK_*) into a kadre [Key].
 *
 * Returns [Key.Unknown] if the VK code is not recognized.
 *
 * @receiver VK code passed in the wParam of a Win32 keyboard message.
 * @return Corresponding kadre logical key.
 */
internal object Win32KeyMapper {

    /**
     * VK code → [Key] mapping table.
     *
     * Built only once (lazy via companion) to avoid repeated allocations.
     */
    private val table: Map<Int, Key> = buildMap {
        // Letters A–Z (VK codes = uppercase ASCII codes 0x41–0x5A)
        put(VK_A, Key.A)
        put(VK_B, Key.B)
        put(VK_C, Key.C)
        put(VK_D, Key.D)
        put(VK_E, Key.E)
        put(VK_F, Key.F)
        put(VK_G, Key.G)
        put(VK_H, Key.H)
        put(VK_I, Key.I)
        put(VK_J, Key.J)
        put(VK_K, Key.K)
        put(VK_L, Key.L)
        put(VK_M, Key.M)
        put(VK_N, Key.N)
        put(VK_O, Key.O)
        put(VK_P, Key.P)
        put(VK_Q, Key.Q)
        put(VK_R, Key.R)
        put(VK_S, Key.S)
        put(VK_T, Key.T)
        put(VK_U, Key.U)
        put(VK_V, Key.V)
        put(VK_W, Key.W)
        put(VK_X, Key.X)
        put(VK_Y, Key.Y)
        put(VK_Z, Key.Z)

        // Digits 0–9 (top row, VK codes = ASCII codes 0x30–0x39)
        put(VK_0, Key.Digit0)
        put(VK_1, Key.Digit1)
        put(VK_2, Key.Digit2)
        put(VK_3, Key.Digit3)
        put(VK_4, Key.Digit4)
        put(VK_5, Key.Digit5)
        put(VK_6, Key.Digit6)
        put(VK_7, Key.Digit7)
        put(VK_8, Key.Digit8)
        put(VK_9, Key.Digit9)

        // Function keys F1–F12
        put(VK_F1, Key.F1)
        put(VK_F2, Key.F2)
        put(VK_F3, Key.F3)
        put(VK_F4, Key.F4)
        put(VK_F5, Key.F5)
        put(VK_F6, Key.F6)
        put(VK_F7, Key.F7)
        put(VK_F8, Key.F8)
        put(VK_F9, Key.F9)
        put(VK_F10, Key.F10)
        put(VK_F11, Key.F11)
        put(VK_F12, Key.F12)

        // Navigation keys
        put(VK_LEFT,  Key.ArrowLeft)
        put(VK_RIGHT, Key.ArrowRight)
        put(VK_UP,    Key.ArrowUp)
        put(VK_DOWN,  Key.ArrowDown)

        // Special keys
        put(VK_SPACE,  Key.Space)
        put(VK_RETURN, Key.Enter)
        put(VK_ESCAPE, Key.Escape)
        put(VK_BACK,   Key.Backspace)
        put(VK_TAB,    Key.Tab)

        // Modifiers (left/right versions + generic)
        put(VK_LSHIFT,   Key.ShiftLeft)
        put(VK_RSHIFT,   Key.ShiftRight)
        put(VK_SHIFT,    Key.ShiftLeft)   // generic → left by default
        put(VK_LCONTROL, Key.ControlLeft)
        put(VK_RCONTROL, Key.ControlRight)
        put(VK_CONTROL,  Key.ControlLeft) // generic → left by default
        put(VK_LMENU,    Key.AltLeft)
        put(VK_RMENU,    Key.AltRight)
        put(VK_MENU,     Key.AltLeft)     // generic → left by default
        put(VK_LWIN,     Key.MetaLeft)
        put(VK_RWIN,     Key.MetaRight)
    }

    /**
     * Returns the [Key] corresponding to the given VK code.
     *
     * @param vkCode Win32 virtual key code (wParam of a WM_KEY* message).
     * @return kadre key, or [Key.Unknown] if the code is not in the table.
     */
    fun fromVkCode(vkCode: Int): Key = table[vkCode] ?: Key.Unknown
}
