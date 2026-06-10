/**
 * Memory layout for the Win32 MSG structure.
 *
 * The MSG structure is passed to PeekMessageW, GetMessageW, TranslateMessage
 * and DispatchMessageW. It must be allocated in an arena so that the
 * FFM calls can write directly into memory.
 *
 * MSG structure (Win64, 64-bit):
 * Offset  Size  Field
 *  0       8    hwnd      (HWND — 64-bit pointer)
 *  8       4    message   (UINT — 32-bit)
 * 12       4    (padding to align wParam on 8 bytes)
 * 16       8    wParam    (WPARAM = UINT_PTR — 64-bit)
 * 24       8    lParam    (LPARAM = LONG_PTR — 64-bit)
 * 32       4    time      (DWORD — 32-bit)
 * 36       4    pt.x      (LONG — 32-bit)
 * 40       4    pt.y      (LONG — 32-bit)
 * 44       4    (final padding)
 * Total = 48 bytes
 *
 * Note: On Windows x64, the MSVC compiler inserts 4 bytes of padding between
 * `message` (UINT, 4 bytes) and `wParam` (UINT_PTR, 8 bytes) for 8-byte
 * alignment. The `pt` field is a POINT (two LONGs = 8 bytes) followed by
 * 4 bytes of padding to align the sizeof on 8.
 *
 * Reference: https://learn.microsoft.com/en-us/windows/win32/api/winuser/ns-winuser-msg
 */
package org.graphiks.kadre.ffi.win32

import java.lang.foreign.Arena
import java.lang.foreign.MemoryLayout
import java.lang.foreign.MemorySegment
import java.lang.foreign.ValueLayout

/**
 * Offsets and constants of the Win64 MSG layout.
 *
 * Enables direct access to the fields of the MSG memory segment without going
 * through a VarHandle (same approach as WndClassExW for consistency).
 */
object MsgLayout {

    // ── Offsets (in bytes) ───────────────────────────────────────────────────
    //
    // Offset  Type      Field
    //  0      PTR(8)    hwnd
    //  8      INT(4)    message
    // 12      PAD(4)    —
    // 16      LONG(8)   wParam
    // 24      LONG(8)   lParam
    // 32      INT(4)    time
    // 36      INT(4)    pt.x
    // 40      INT(4)    pt.y
    // 44      PAD(4)    —
    // 48      ← sizeof

    const val OFFSET_HWND: Int    = 0
    const val OFFSET_MESSAGE: Int = 8
    const val OFFSET_WPARAM: Int  = 16
    const val OFFSET_LPARAM: Int  = 24
    const val OFFSET_TIME: Int    = 32
    const val OFFSET_PT_X: Int    = 36
    const val OFFSET_PT_Y: Int    = 40

    /** Total size of the MSG structure in bytes (48 bytes on Win64). */
    const val SIZEOF: Int = 48

    /** Required alignment (8 bytes, Win64 pointer alignment). */
    const val ALIGN: Int = 8

    /**
     * MemoryLayout equivalent to the Win64 MSG.
     *
     * Provided for documentation and validation, not used directly
     * in the FFM calls (the manual offsets are used instead).
     */
    val LAYOUT: MemoryLayout = MemoryLayout.structLayout(
        ValueLayout.ADDRESS.withName("hwnd"),            //  0: HWND (8 bytes)
        ValueLayout.JAVA_INT.withName("message"),        //  8: UINT (4 bytes)
        MemoryLayout.paddingLayout(4),                   // 12: padding (4 bytes)
        ValueLayout.JAVA_LONG.withName("wParam"),        // 16: WPARAM (8 bytes)
        ValueLayout.JAVA_LONG.withName("lParam"),        // 24: LPARAM (8 bytes)
        ValueLayout.JAVA_INT.withName("time"),           // 32: DWORD (4 bytes)
        ValueLayout.JAVA_INT.withName("pt_x"),           // 36: LONG pt.x (4 bytes)
        ValueLayout.JAVA_INT.withName("pt_y"),           // 40: LONG pt.y (4 bytes)
        MemoryLayout.paddingLayout(4),                   // 44: padding (4 bytes)
    ).withName("MSG")
}

/**
 * Allocates a memory segment for a MSG structure in the given arena.
 *
 * The segment is zero-initialized by default (FFM allocator behavior).
 * To be used with PeekMessageW, GetMessageW, TranslateMessage, DispatchMessageW.
 *
 * @return Memory segment of [MsgLayout.SIZEOF] bytes, aligned on [MsgLayout.ALIGN].
 */
fun Arena.allocateMsg(): MemorySegment =
    this.allocate(MsgLayout.SIZEOF.toLong(), MsgLayout.ALIGN.toLong())

/**
 * Reads the `message` field (UINT) from a MSG segment.
 *
 * Useful for inspecting the message type without going through a VarHandle.
 */
fun MemorySegment.msgMessage(): Int =
    this.get(ValueLayout.JAVA_INT, MsgLayout.OFFSET_MESSAGE.toLong())
