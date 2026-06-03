/**
 * Win32 message constants (WM_*) and virtual key codes (VK_*).
 *
 * Separated from Win32_h.kt to group all the message constants required
 * by KadreWndProc in one place.
 *
 * Reference: https://learn.microsoft.com/en-us/windows/win32/winmsg/window-messages
 */
package org.graphiks.kadre.win32

// ── Window lifecycle messages ─────────────────────────────────────────────────

/** WM_DESTROY — the window is being destroyed (already defined in Win32_h.kt, re-exported here for consistency). */
// WM_DESTROY = 0x0002  (already in Win32_h.kt)

/** WM_CLOSE — window close request (× button, Alt+F4). */
internal const val WM_CLOSE: Int = 0x0010

/** WM_PAINT — the window must be redrawn. */
internal const val WM_PAINT: Int = 0x000F

/** WM_SIZE — the window size has changed. */
internal const val WM_SIZE: Int = 0x0005

/** WM_MOVE — the window position has changed. */
internal const val WM_MOVE: Int = 0x0003

/** WM_NCACTIVATE — the non-client area active state changed. */
internal const val WM_NCACTIVATE: Int = 0x0086

/** WM_SETFOCUS — the window gained keyboard focus. */
internal const val WM_SETFOCUS: Int = 0x0007

/** WM_KILLFOCUS — the window lost keyboard focus. */
internal const val WM_KILLFOCUS: Int = 0x0008

/** WM_DPICHANGED — the window DPI has changed (moved to a different monitor). */
internal const val WM_DPICHANGED: Int = 0x02E0

// ── Keyboard messages ───────────────────────────────────────────────────────

/** WM_KEYDOWN — key pressed (non-system key). */
internal const val WM_KEYDOWN: Int = 0x0100

/** WM_KEYUP — key released (non-system key). */
internal const val WM_KEYUP: Int = 0x0101

/** WM_SYSKEYDOWN — system key pressed (Alt + key). */
internal const val WM_SYSKEYDOWN: Int = 0x0104

/** WM_SYSKEYUP — system key released (Alt + key). */
internal const val WM_SYSKEYUP: Int = 0x0105

// ── Mouse messages ────────────────────────────────────────────────────────────

/** WM_MOUSEMOVE — the cursor moved within the client area. */
internal const val WM_MOUSEMOVE: Int = 0x0200

/** WM_LBUTTONDOWN — left mouse button pressed. */
internal const val WM_LBUTTONDOWN: Int = 0x0201

/** WM_LBUTTONUP — left mouse button released. */
internal const val WM_LBUTTONUP: Int = 0x0202

/** WM_RBUTTONDOWN — right mouse button pressed. */
internal const val WM_RBUTTONDOWN: Int = 0x0204

/** WM_RBUTTONUP — right mouse button released. */
internal const val WM_RBUTTONUP: Int = 0x0205

/** WM_MBUTTONDOWN — middle mouse button pressed. */
internal const val WM_MBUTTONDOWN: Int = 0x0207

/** WM_MBUTTONUP — middle mouse button released. */
internal const val WM_MBUTTONUP: Int = 0x0208

/** WM_MOUSEWHEEL — the mouse wheel turned (vertical scroll). */
internal const val WM_MOUSEWHEEL: Int = 0x020A

/** WM_XBUTTONDOWN — additional mouse button pressed (button 4 or 5). */
internal const val WM_XBUTTONDOWN: Int = 0x020B

/** WM_XBUTTONUP — additional mouse button released (button 4 or 5). */
internal const val WM_XBUTTONUP: Int = 0x020C

/**
 * WM_MOUSELEAVE — the cursor left the window's client area.
 *
 * Sent only once after a call to TrackMouseEvent with TME_LEAVE.
 * Must be re-armed with a new call to TrackMouseEvent after receipt.
 */
internal const val WM_MOUSELEAVE: Int = 0x02A3

// ── Touch messages ────────────────────────────────────────────────────────────

/**
 * WM_TOUCH — one or more touch contacts changed on a touchscreen.
 *
 * `wParam` LOWORD = number of touch points (cInputs); `lParam` = HTOUCHINPUT
 * handle to pass to GetTouchInputInfo. The window must have called
 * RegisterTouchWindow to receive these messages.
 *
 * Reference: https://learn.microsoft.com/en-us/windows/win32/wintouch/wm-touch
 */
internal const val WM_TOUCH: Int = 0x0240

// ── TOUCHINPUT.dwFlags bits ────────────────────────────────────────────────────

/** TOUCHEVENTF_MOVE — the contact moved. */
internal const val TOUCHEVENTF_MOVE: Int = 0x0001

/** TOUCHEVENTF_DOWN — the contact was just placed on the surface. */
internal const val TOUCHEVENTF_DOWN: Int = 0x0002

/** TOUCHEVENTF_UP — the contact was just lifted from the surface. */
internal const val TOUCHEVENTF_UP: Int = 0x0004

/**
 * Size of the TOUCHINPUT structure in bytes on Win64.
 *
 * Layout (with natural 8-byte alignment):
 *   LONG       x           (offset 0,  4 bytes — physical screen coords, 1/100 px)
 *   LONG       y           (offset 4,  4 bytes)
 *   HANDLE     hSource     (offset 8,  8 bytes)
 *   DWORD      dwID        (offset 16, 4 bytes — contact identifier)
 *   DWORD      dwFlags     (offset 20, 4 bytes — TOUCHEVENTF_*)
 *   DWORD      dwMask      (offset 24, 4 bytes)
 *   DWORD      dwTime      (offset 28, 4 bytes)
 *   ULONG_PTR  dwExtraInfo (offset 32, 8 bytes)
 *   DWORD      cxContact   (offset 40, 4 bytes)
 *   DWORD      cyContact   (offset 44, 4 bytes)
 */
internal const val TOUCHINPUT_SIZE: Int = 48

/** Offset of TOUCHINPUT.x within the structure. */
internal const val TOUCHINPUT_OFFSET_X: Long = 0L

/** Offset of TOUCHINPUT.y within the structure. */
internal const val TOUCHINPUT_OFFSET_Y: Long = 4L

/** Offset of TOUCHINPUT.dwID within the structure. */
internal const val TOUCHINPUT_OFFSET_ID: Long = 16L

/** Offset of TOUCHINPUT.dwFlags within the structure. */
internal const val TOUCHINPUT_OFFSET_FLAGS: Long = 20L

/**
 * Divisor converting TOUCHINPUT.x / .y (hundredths of a physical pixel)
 * into physical pixels.
 */
internal const val TOUCH_COORD_SCALE: Double = 100.0

// ── Repeat bit in lParam (keyboard) ───────────────────────────────────────────

/**
 * Mask for bit 30 of lParam for keyboard messages.
 *
 * This bit is set to 1 if the key was already down before the message was sent
 * (key held → auto-repeat).
 *
 * Reference: https://learn.microsoft.com/en-us/windows/win32/inputdev/wm-keydown
 */
internal const val KF_REPEAT: Long = 0x4000_0000L

/**
 * Mask for bit 24 of lParam for keyboard messages.
 *
 * Extended scancodes identify keys from the enhanced keyboard set. We fold this
 * bit into the native scancode as 0xE000 | scanCode, matching winit's stable
 * native identity convention and avoiding collisions with non-extended keys.
 */
internal const val KF_EXTENDED: Long = 0x0100_0000L

// ── Virtual key codes (VK_*) ──────────────────────────────────────────────────
// Used by Win32KeyMapper for the VK → Key table

/** VK_BACK — Backspace key. */
internal const val VK_BACK: Int = 0x08

/** VK_TAB — Tab key. */
internal const val VK_TAB: Int = 0x09

/** VK_RETURN — Enter key. */
internal const val VK_RETURN: Int = 0x0D

/** VK_ESCAPE — Escape key. */
internal const val VK_ESCAPE: Int = 0x1B

/** VK_SPACE — space bar. */
internal const val VK_SPACE: Int = 0x20

/** VK_LEFT — left arrow. */
internal const val VK_LEFT: Int = 0x25

/** VK_UP — up arrow. */
internal const val VK_UP: Int = 0x26

/** VK_RIGHT — right arrow. */
internal const val VK_RIGHT: Int = 0x27

/** VK_DOWN — down arrow. */
internal const val VK_DOWN: Int = 0x28

/** VK_SHIFT — Shift key (generic). */
internal const val VK_SHIFT: Int = 0x10

/** VK_CONTROL — Control key (generic). */
internal const val VK_CONTROL: Int = 0x11

/** VK_MENU — Alt key (generic). */
internal const val VK_MENU: Int = 0x12

/** VK_LSHIFT — left Shift. */
internal const val VK_LSHIFT: Int = 0xA0

/** VK_RSHIFT — right Shift. */
internal const val VK_RSHIFT: Int = 0xA1

/** VK_LCONTROL — left Control. */
internal const val VK_LCONTROL: Int = 0xA2

/** VK_RCONTROL — right Control. */
internal const val VK_RCONTROL: Int = 0xA3

/** VK_LMENU — left Alt. */
internal const val VK_LMENU: Int = 0xA4

/** VK_RMENU — right Alt (AltGr). */
internal const val VK_RMENU: Int = 0xA5

/** VK_LWIN — left Windows key (Meta). */
internal const val VK_LWIN: Int = 0x5B

/** VK_RWIN — right Windows key (Meta). */
internal const val VK_RWIN: Int = 0x5C

// F1–F12
/** VK_F1 */
internal const val VK_F1: Int = 0x70
/** VK_F2 */
internal const val VK_F2: Int = 0x71
/** VK_F3 */
internal const val VK_F3: Int = 0x72
/** VK_F4 */
internal const val VK_F4: Int = 0x73
/** VK_F5 */
internal const val VK_F5: Int = 0x74
/** VK_F6 */
internal const val VK_F6: Int = 0x75
/** VK_F7 */
internal const val VK_F7: Int = 0x76
/** VK_F8 */
internal const val VK_F8: Int = 0x77
/** VK_F9 */
internal const val VK_F9: Int = 0x78
/** VK_F10 */
internal const val VK_F10: Int = 0x79
/** VK_F11 */
internal const val VK_F11: Int = 0x7A
/** VK_F12 */
internal const val VK_F12: Int = 0x7B

// Digits 0–9 (top row of the keyboard)
/** VK '0' */
internal const val VK_0: Int = 0x30
/** VK '1' */
internal const val VK_1: Int = 0x31
/** VK '2' */
internal const val VK_2: Int = 0x32
/** VK '3' */
internal const val VK_3: Int = 0x33
/** VK '4' */
internal const val VK_4: Int = 0x34
/** VK '5' */
internal const val VK_5: Int = 0x35
/** VK '6' */
internal const val VK_6: Int = 0x36
/** VK '7' */
internal const val VK_7: Int = 0x37
/** VK '8' */
internal const val VK_8: Int = 0x38
/** VK '9' */
internal const val VK_9: Int = 0x39

// Letters A–Z (uppercase ASCII codes)
/** VK 'A' */
internal const val VK_A: Int = 0x41
/** VK 'B' */
internal const val VK_B: Int = 0x42
/** VK 'C' */
internal const val VK_C: Int = 0x43
/** VK 'D' */
internal const val VK_D: Int = 0x44
/** VK 'E' */
internal const val VK_E: Int = 0x45
/** VK 'F' */
internal const val VK_F: Int = 0x46
/** VK 'G' */
internal const val VK_G: Int = 0x47
/** VK 'H' */
internal const val VK_H: Int = 0x48
/** VK 'I' */
internal const val VK_I: Int = 0x49
/** VK 'J' */
internal const val VK_J: Int = 0x4A
/** VK 'K' */
internal const val VK_K: Int = 0x4B
/** VK 'L' */
internal const val VK_L: Int = 0x4C
/** VK 'M' */
internal const val VK_M: Int = 0x4D
/** VK 'N' */
internal const val VK_N: Int = 0x4E
/** VK 'O' */
internal const val VK_O: Int = 0x4F
/** VK 'P' */
internal const val VK_P: Int = 0x50
/** VK 'Q' */
internal const val VK_Q: Int = 0x51
/** VK 'R' */
internal const val VK_R: Int = 0x52
/** VK 'S' */
internal const val VK_S: Int = 0x53
/** VK 'T' */
internal const val VK_T: Int = 0x54
/** VK 'U' */
internal const val VK_U: Int = 0x55
/** VK 'V' */
internal const val VK_V: Int = 0x56
/** VK 'W' */
internal const val VK_W: Int = 0x57
/** VK 'X' */
internal const val VK_X: Int = 0x58
/** VK 'Y' */
internal const val VK_Y: Int = 0x59
/** VK 'Z' */
internal const val VK_Z: Int = 0x5A

// ── WM_MOUSEWHEEL constants ─────────────────────────────────────────────────

/**
 * WHEEL_DELTA — wheel scroll unit (120 per standard notch).
 *
 * The high word of wParam contains the scroll delta in multiples of WHEEL_DELTA.
 */
internal const val WHEEL_DELTA: Int = 120

// ── WM_XBUTTONDOWN/UP constants ──────────────────────────────────────────────

/**
 * Mask to extract the X button number from the HIWORD of wParam.
 *
 * wParam of WM_XBUTTONDOWN/UP:
 *   LOWORD = state of keys and mouse buttons (MK_*)
 *   HIWORD = X button involved (XBUTTON1 or XBUTTON2)
 */
internal const val XBUTTON_HIWORD_MASK: Long = 0xFFFF_0000L

/** XBUTTON1 — first additional button (button 4 = Back). */
internal const val XBUTTON1: Int = 0x0001

/** XBUTTON2 — second additional button (button 5 = Forward). */
internal const val XBUTTON2: Int = 0x0002

// ── WM_DPICHANGED constants ─────────────────────────────────────────────────

/**
 * Offset to extract the DPI X from the wParam of WM_DPICHANGED.
 *
 * wParam = MAKEWPARAM(dpiX, dpiY) → low bits = dpiX, high bits = dpiY.
 */
internal const val DPI_WPARAM_MASK: Long = 0xFFFFL

// ── Message loop constants ───────────────────────────────────────────────────

/**
 * PM_REMOVE — flag for PeekMessageW: removes the message from the queue after reading.
 *
 * Reference: https://learn.microsoft.com/en-us/windows/win32/api/winuser/nf-winuser-peekmessagew
 */
internal const val PM_REMOVE: Int = 0x0001

/**
 * PM_NOREMOVE — flag for PeekMessageW: does not remove the message from the queue.
 *
 * Used to inspect the queue without consuming the message.
 */
internal const val PM_NOREMOVE: Int = 0x0000

/**
 * QS_ALLINPUT — MsgWaitForMultipleObjectsEx wake mask covering all types
 * of input messages (keyboard, mouse, timer, paint, send, posted).
 *
 * QS_ALLINPUT = QS_INPUT | QS_POSTMESSAGE | QS_TIMER | QS_PAINT | QS_HOTKEY | QS_SENDMESSAGE
 *
 * Reference: https://learn.microsoft.com/en-us/windows/win32/api/winuser/nf-winuser-msgwaitformultipleobjectsex
 */
internal const val QS_ALLINPUT: Int = 0x04FF

/**
 * MWMO_INPUTAVAILABLE — MsgWaitForMultipleObjectsEx flag: wakes the loop
 * if input messages are available (even if they have already been seen).
 *
 * Without this flag, the function may not wake up if the messages are in
 * the queue but have not yet been processed.
 *
 * Reference: https://learn.microsoft.com/en-us/windows/win32/api/winuser/nf-winuser-msgwaitformultipleobjectsex
 */
internal const val MWMO_INPUTAVAILABLE: Int = 0x0004

/**
 * WAIT_OBJECT_0 — return code from MsgWaitForMultipleObjectsEx indicating
 * that the first awaited object is signaled (or that messages are available
 * when nCount = 0, in which case the returned value is WAIT_OBJECT_0).
 *
 * Reference: https://learn.microsoft.com/en-us/windows/win32/api/synchapi/nf-synchapi-waitformultipleobjects
 */
internal const val WAIT_OBJECT_0: Int = 0x00000000

/**
 * WAIT_TIMEOUT — return code from MsgWaitForMultipleObjectsEx indicating
 * that the timeout expired without an object being signaled.
 */
internal const val WAIT_TIMEOUT: Int = 0x00000102

/**
 * INFINITE — timeout value for MsgWaitForMultipleObjectsEx indicating
 * an unbounded wait (equivalent to 0xFFFFFFFF).
 */
@Suppress("INTEGER_OVERFLOW")
internal const val INFINITE: Int = -1  // 0xFFFFFFFF unsigned

// ── IME message constants ─────────────────────────────────────────────────────

/** WM_IME_STARTCOMPOSITION — IME started composing text. */
internal const val WM_IME_STARTCOMPOSITION: Int = 0x010D

/** WM_IME_ENDCOMPOSITION — IME ended composing text. */
internal const val WM_IME_ENDCOMPOSITION: Int = 0x010E

/** WM_IME_COMPOSITION — IME composition string changed. */
internal const val WM_IME_COMPOSITION: Int = 0x010F

/** WM_IME_SETCONTEXT — IME context was set for the window. */
internal const val WM_IME_SETCONTEXT: Int = 0x0281

/** WM_IME_NOTIFY — IME notification. */
internal const val WM_IME_NOTIFY: Int = 0x0282

/** WM_INPUTLANGCHANGE — input language changed. */
internal const val WM_INPUTLANGCHANGE: Int = 0x0051

// ── GCS_* flags (WM_IME_COMPOSITION lParam) ──────────────────────────────────

/** GCS_COMPREADSTR — read composition string. */
internal const val GCS_COMPREADSTR: Int = 0x0001

/** GCS_COMPREADATTR — read composition attributes. */
internal const val GCS_COMPREADATTR: Int = 0x0002

/** GCS_COMPREADCLAUSE — read composition clause information. */
internal const val GCS_COMPREADCLAUSE: Int = 0x0004

/** GCS_COMPSTR — composition string. */
internal const val GCS_COMPSTR: Int = 0x0008

/** GCS_COMPATTR — composition attributes. */
internal const val GCS_COMPATTR: Int = 0x0010

/** GCS_COMPCLAUSE — composition clause information. */
internal const val GCS_COMPCLAUSE: Int = 0x0020

/** GCS_CURSORPOS — cursor position in the composition string. */
internal const val GCS_CURSORPOS: Int = 0x0080

/** GCS_DELTASTART — start of the delta. */
internal const val GCS_DELTASTART: Int = 0x0100

/** GCS_RESULTSTR — result string (committed text). */
internal const val GCS_RESULTSTR: Int = 0x0800

/** GCS_RESULTREADSTR — read result string. */
internal const val GCS_RESULTREADSTR: Int = 0x1000

// ── CPI_* constants ──────────────────────────────────────────────────────────

/** CPI_SETCOMPOSITIONSTRING — set composition string. */
internal const val CPI_SETCOMPOSITIONSTRING: Int = 0x0400

/** CPI_SETCURSORPOS — set cursor position. */
internal const val CPI_SETCURSORPOS: Int = 0x0001

// ── CFS_* constants (COMPOSITIONFORM.dwStyle) ────────────────────────────────

/** CFS_DEFAULT — use default composition window position. */
internal const val CFS_DEFAULT: Int = 0x0000

/** CFS_POINT — position using ptCurrentPos. */
internal const val CFS_POINT: Int = 0x0001

/** CFS_RECT — position using rcArea. */
internal const val CFS_RECT: Int = 0x0002

/** CFS_FORCE_POSITION — force the position even during composition. */
internal const val CFS_FORCE_POSITION: Int = 0x0020

/** CFS_EXCLUDE — exclude rcArea from the composition window. */
internal const val CFS_EXCLUDE: Int = 0x0004

// ── NIM_* constants (WM_IME_NOTIFY wParam) ───────────────────────────────────

/** NIM_SETOPENSTATUS — set IME open status. */
internal const val NIM_SETOPENSTATUS: Int = 0x0006

/** NIM_OPENSTATUS — query IME open status. */
internal const val NIM_OPENSTATUS: Int = 0x0007

// ── IACE_* constants (ImmAssociateContextEx) ──────────────────────────────────

/** IACE_DEFAULT — associate the default IMC. */
internal const val IACE_DEFAULT: Int = 0x0010

/** IACE_CHILDREN — disassociate / suppress IME for children. */
internal const val IACE_CHILDREN: Int = 0x0001

// ── SM_* constants (GetSystemMetrics) ─────────────────────────────────────────

/** SM_IMMENABLED — non-zero if the system has an IME installed. */
internal const val SM_IMMENABLED: Int = 0x0054

// ── WM_IME_SETCONTEXT LPARAM flags ───────────────────────────────────────────

/** ISC_SHOWUIALL — IME may show all UI windows. Clear this to prevent it. */
internal const val ISC_SHOWUIALL: Long = 0x80000000L

// ── COMPOSITIONFORM layout (Win64) ────────────────────────────────────────────

/**
 * COMPOSITIONFORM structure size in bytes on Win64.
 * Layout:
 *   DWORD dwStyle          (offset  0, 4 bytes)
 *   POINT ptCurrentPos:
 *     LONG x               (offset  4, 4 bytes)
 *     LONG y               (offset  8, 4 bytes)
 *   RECT  rcArea:
 *     LONG left            (offset 12, 4 bytes)
 *     LONG top             (offset 16, 4 bytes)
 *     LONG right           (offset 20, 4 bytes)
 *     LONG bottom          (offset 24, 4 bytes)
 */
internal const val COMPOSITIONFORM_SIZE: Long = 28L
internal const val COMPOSITIONFORM_ALIGN: Long = 4L
internal const val COMPOSITIONFORM_OFFSET_DWSTYLE: Long = 0L
internal const val COMPOSITIONFORM_OFFSET_PT_X: Long = 4L
internal const val COMPOSITIONFORM_OFFSET_PT_Y: Long = 8L
internal const val COMPOSITIONFORM_OFFSET_RC_LEFT: Long = 12L
internal const val COMPOSITIONFORM_OFFSET_RC_TOP: Long = 16L
internal const val COMPOSITIONFORM_OFFSET_RC_RIGHT: Long = 20L
internal const val COMPOSITIONFORM_OFFSET_RC_BOTTOM: Long = 24L

// ── CANDIDATEFORM layout (Win64) ──────────────────────────────────────────────

/**
 * CANDIDATEFORM structure size in bytes on Win64.
 * Layout:
 *   DWORD dwIndex          (offset  0, 4 bytes)
 *   DWORD dwStyle          (offset  4, 4 bytes)
 *   POINT ptCurrentPos:
 *     LONG x               (offset  8, 4 bytes)
 *     LONG y               (offset 12, 4 bytes)
 *   RECT  rcArea:
 *     LONG left            (offset 16, 4 bytes)
 *     LONG top             (offset 20, 4 bytes)
 *     LONG right           (offset 24, 4 bytes)
 *     LONG bottom          (offset 28, 4 bytes)
 */
internal const val CANDIDATEFORM_SIZE: Long = 32L
internal const val CANDIDATEFORM_ALIGN: Long = 4L
internal const val CANDIDATEFORM_OFFSET_DWINDEX: Long = 0L
internal const val CANDIDATEFORM_OFFSET_DWSTYLE: Long = 4L
internal const val CANDIDATEFORM_OFFSET_PT_X: Long = 8L
internal const val CANDIDATEFORM_OFFSET_PT_Y: Long = 12L
internal const val CANDIDATEFORM_OFFSET_RC_LEFT: Long = 16L
internal const val CANDIDATEFORM_OFFSET_RC_TOP: Long = 20L
internal const val CANDIDATEFORM_OFFSET_RC_RIGHT: Long = 24L
internal const val CANDIDATEFORM_OFFSET_RC_BOTTOM: Long = 28L
