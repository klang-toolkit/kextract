/**
 * Memory layout and VarHandles for the Win32 WNDCLASSEXW structure.
 *
 * WNDCLASSEXW (64-bit Windows):
 * Offset  Size  Field
 *  0       4    cbSize       (UINT)
 *  4       4    style        (UINT)
 *  8       8    lpfnWndProc  (WNDPROC — function pointer)
 * 16       4    cbClsExtra   (int)
 * 20       4    cbWndExtra   (int)
 * 24       8    hInstance    (HINSTANCE — pointer)
 * 32       8    hIcon        (HICON — pointer)
 * 40       8    hCursor      (HCURSOR — pointer)
 * 48       8    hbrBackground(HBRUSH — pointer)
 * 56       8    lpszMenuName (LPCWSTR — pointer)
 * 64       8    lpszClassName(LPCWSTR — pointer)
 * 72       8    hIconSm      (HICON — pointer)
 * Total = 80 bytes
 *
 * Reference: https://learn.microsoft.com/en-us/windows/win32/api/winuser/ns-winuser-wndclassexw
 */
package org.graphiks.kadre.win32

import java.lang.foreign.Arena
import java.lang.foreign.MemoryLayout
import java.lang.foreign.MemorySegment
import java.lang.foreign.ValueLayout

/**
 * Typed access to the WNDCLASSEXW structure allocated in an [Arena].
 *
 * Usage:
 * ```kotlin
 * Arena.ofConfined().use { arena ->
 *     val wndClass = WndClassExW(arena)
 *     wndClass.cbSize = WndClassExW.SIZEOF
 *     wndClass.style = CS_HREDRAW_VREDRAW
 *     wndClass.lpfnWndProc = wndProcStub
 *     wndClass.hInstance = hInstance
 *     wndClass.lpszClassName = classNamePtr
 * }
 * ```
 */
internal class WndClassExW(arena: Arena) {

    /** Raw memory segment of the structure. */
    val segment: MemorySegment = arena.allocate(SIZEOF.toLong(), ALIGN.toLong())

    /** cbSize: size of the structure in bytes (must be = SIZEOF). */
    var cbSize: Int
        get() = segment.get(ValueLayout.JAVA_INT, OFFSET_CB_SIZE.toLong())
        set(value) = segment.set(ValueLayout.JAVA_INT, OFFSET_CB_SIZE.toLong(), value)

    /** style: CS_* flags of the window class. */
    var style: Int
        get() = segment.get(ValueLayout.JAVA_INT, OFFSET_STYLE.toLong())
        set(value) = segment.set(ValueLayout.JAVA_INT, OFFSET_STYLE.toLong(), value)

    /** lpfnWndProc: pointer to the window procedure (WNDPROC). */
    var lpfnWndProc: MemorySegment
        get() = segment.get(ValueLayout.ADDRESS, OFFSET_WNDPROC.toLong())
        set(value) = segment.set(ValueLayout.ADDRESS, OFFSET_WNDPROC.toLong(), value)

    /** cbClsExtra: extra bytes allocated after the class structure. */
    var cbClsExtra: Int
        get() = segment.get(ValueLayout.JAVA_INT, OFFSET_CLS_EXTRA.toLong())
        set(value) = segment.set(ValueLayout.JAVA_INT, OFFSET_CLS_EXTRA.toLong(), value)

    /** cbWndExtra: extra bytes allocated after the window instance. */
    var cbWndExtra: Int
        get() = segment.get(ValueLayout.JAVA_INT, OFFSET_WND_EXTRA.toLong())
        set(value) = segment.set(ValueLayout.JAVA_INT, OFFSET_WND_EXTRA.toLong(), value)

    /** hInstance: handle of the application module. */
    var hInstance: MemorySegment
        get() = segment.get(ValueLayout.ADDRESS, OFFSET_HINSTANCE.toLong())
        set(value) = segment.set(ValueLayout.ADDRESS, OFFSET_HINSTANCE.toLong(), value)

    /** hIcon: handle to the window icon (NULL = default). */
    var hIcon: MemorySegment
        get() = segment.get(ValueLayout.ADDRESS, OFFSET_HICON.toLong())
        set(value) = segment.set(ValueLayout.ADDRESS, OFFSET_HICON.toLong(), value)

    /** hCursor: handle to the window cursor (NULL = default). */
    var hCursor: MemorySegment
        get() = segment.get(ValueLayout.ADDRESS, OFFSET_HCURSOR.toLong())
        set(value) = segment.set(ValueLayout.ADDRESS, OFFSET_HCURSOR.toLong(), value)

    /** hbrBackground: brush for the background (NULL = none). */
    var hbrBackground: MemorySegment
        get() = segment.get(ValueLayout.ADDRESS, OFFSET_HBRUSH.toLong())
        set(value) = segment.set(ValueLayout.ADDRESS, OFFSET_HBRUSH.toLong(), value)

    /** lpszMenuName: name of the menu resource (NULL = no menu). */
    var lpszMenuName: MemorySegment
        get() = segment.get(ValueLayout.ADDRESS, OFFSET_MENU_NAME.toLong())
        set(value) = segment.set(ValueLayout.ADDRESS, OFFSET_MENU_NAME.toLong(), value)

    /** lpszClassName: name of the window class (Wide string). */
    var lpszClassName: MemorySegment
        get() = segment.get(ValueLayout.ADDRESS, OFFSET_CLASS_NAME.toLong())
        set(value) = segment.set(ValueLayout.ADDRESS, OFFSET_CLASS_NAME.toLong(), value)

    /** hIconSm: handle to the small icon (NULL = derived from hIcon). */
    var hIconSm: MemorySegment
        get() = segment.get(ValueLayout.ADDRESS, OFFSET_HICON_SM.toLong())
        set(value) = segment.set(ValueLayout.ADDRESS, OFFSET_HICON_SM.toLong(), value)

    companion object {
        // ── Offsets (in bytes) ───────────────────────────────────────────────
        //
        // Computed according to the Win64 ABI:
        //  - Pointers (WNDPROC, HINSTANCE, HICON, etc.) are 8-byte aligned.
        //  - Int fields (UINT, cbClsExtra, cbWndExtra) are 4-byte aligned.
        //
        // Offset  Type     Field
        //  0      UINT(4)  cbSize
        //  4      UINT(4)  style
        //  8      PTR(8)   lpfnWndProc
        // 16      INT(4)   cbClsExtra
        // 20      INT(4)   cbWndExtra
        // 24      PTR(8)   hInstance
        // 32      PTR(8)   hIcon
        // 40      PTR(8)   hCursor
        // 48      PTR(8)   hbrBackground
        // 56      PTR(8)   lpszMenuName
        // 64      PTR(8)   lpszClassName
        // 72      PTR(8)   hIconSm
        // 80      ← sizeof

        const val OFFSET_CB_SIZE: Int    = 0
        const val OFFSET_STYLE: Int      = 4
        const val OFFSET_WNDPROC: Int    = 8
        const val OFFSET_CLS_EXTRA: Int  = 16
        const val OFFSET_WND_EXTRA: Int  = 20
        const val OFFSET_HINSTANCE: Int  = 24
        const val OFFSET_HICON: Int      = 32
        const val OFFSET_HCURSOR: Int    = 40
        const val OFFSET_HBRUSH: Int     = 48
        const val OFFSET_MENU_NAME: Int  = 56
        const val OFFSET_CLASS_NAME: Int = 64
        const val OFFSET_HICON_SM: Int   = 72

        /** Total size of the structure in bytes (80 bytes on Win64). */
        const val SIZEOF: Int = 80

        /** Required alignment (8 bytes, Win64 pointer alignment). */
        const val ALIGN: Int = 8

        /**
         * MemoryLayout equivalent to WNDCLASSEXW.
         *
         * Provided for documentation and verification, not used directly
         * in the FFM calls (the manual offsets are used instead).
         */
        val LAYOUT: MemoryLayout = MemoryLayout.structLayout(
            ValueLayout.JAVA_INT.withName("cbSize"),
            ValueLayout.JAVA_INT.withName("style"),
            ValueLayout.ADDRESS.withName("lpfnWndProc"),
            ValueLayout.JAVA_INT.withName("cbClsExtra"),
            ValueLayout.JAVA_INT.withName("cbWndExtra"),
            ValueLayout.ADDRESS.withName("hInstance"),
            ValueLayout.ADDRESS.withName("hIcon"),
            ValueLayout.ADDRESS.withName("hCursor"),
            ValueLayout.ADDRESS.withName("hbrBackground"),
            ValueLayout.ADDRESS.withName("lpszMenuName"),
            ValueLayout.ADDRESS.withName("lpszClassName"),
            ValueLayout.ADDRESS.withName("hIconSm"),
        ).withName("WNDCLASSEXW")
    }
}
