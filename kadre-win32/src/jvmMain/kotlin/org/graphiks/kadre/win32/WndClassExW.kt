/**
 * Layout mémoire et VarHandles pour la structure WNDCLASSEXW Win32.
 *
 * WNDCLASSEXW (64-bit Windows) :
 * Offset  Size  Field
 *  0       4    cbSize       (UINT)
 *  4       4    style        (UINT)
 *  8       8    lpfnWndProc  (WNDPROC — pointeur de fonction)
 * 16       4    cbClsExtra   (int)
 * 20       4    cbWndExtra   (int)
 * 24       8    hInstance    (HINSTANCE — pointeur)
 * 32       8    hIcon        (HICON — pointeur)
 * 40       8    hCursor      (HCURSOR — pointeur)
 * 48       8    hbrBackground(HBRUSH — pointeur)
 * 56       8    lpszMenuName (LPCWSTR — pointeur)
 * 64       8    lpszClassName(LPCWSTR — pointeur)
 * 72       8    hIconSm      (HICON — pointeur)
 * Total = 80 bytes
 *
 * Référence : https://learn.microsoft.com/en-us/windows/win32/api/winuser/ns-winuser-wndclassexw
 */
package org.graphiks.kadre.win32

import java.lang.foreign.Arena
import java.lang.foreign.MemoryLayout
import java.lang.foreign.MemorySegment
import java.lang.foreign.ValueLayout

/**
 * Accès typé à la structure WNDCLASSEXW allouée dans un [Arena].
 *
 * Usage :
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

    /** Segment mémoire brut de la structure. */
    val segment: MemorySegment = arena.allocate(SIZEOF.toLong(), ALIGN.toLong())

    /** cbSize : taille de la structure en octets (doit être = SIZEOF). */
    var cbSize: Int
        get() = segment.get(ValueLayout.JAVA_INT, OFFSET_CB_SIZE.toLong())
        set(value) = segment.set(ValueLayout.JAVA_INT, OFFSET_CB_SIZE.toLong(), value)

    /** style : flags CS_* de la classe de fenêtre. */
    var style: Int
        get() = segment.get(ValueLayout.JAVA_INT, OFFSET_STYLE.toLong())
        set(value) = segment.set(ValueLayout.JAVA_INT, OFFSET_STYLE.toLong(), value)

    /** lpfnWndProc : pointeur vers la procédure de fenêtre (WNDPROC). */
    var lpfnWndProc: MemorySegment
        get() = segment.get(ValueLayout.ADDRESS, OFFSET_WNDPROC.toLong())
        set(value) = segment.set(ValueLayout.ADDRESS, OFFSET_WNDPROC.toLong(), value)

    /** cbClsExtra : octets supplémentaires alloués après la structure de classe. */
    var cbClsExtra: Int
        get() = segment.get(ValueLayout.JAVA_INT, OFFSET_CLS_EXTRA.toLong())
        set(value) = segment.set(ValueLayout.JAVA_INT, OFFSET_CLS_EXTRA.toLong(), value)

    /** cbWndExtra : octets supplémentaires alloués après l'instance de fenêtre. */
    var cbWndExtra: Int
        get() = segment.get(ValueLayout.JAVA_INT, OFFSET_WND_EXTRA.toLong())
        set(value) = segment.set(ValueLayout.JAVA_INT, OFFSET_WND_EXTRA.toLong(), value)

    /** hInstance : handle du module de l'application. */
    var hInstance: MemorySegment
        get() = segment.get(ValueLayout.ADDRESS, OFFSET_HINSTANCE.toLong())
        set(value) = segment.set(ValueLayout.ADDRESS, OFFSET_HINSTANCE.toLong(), value)

    /** hIcon : handle vers l'icône de la fenêtre (NULL = défaut). */
    var hIcon: MemorySegment
        get() = segment.get(ValueLayout.ADDRESS, OFFSET_HICON.toLong())
        set(value) = segment.set(ValueLayout.ADDRESS, OFFSET_HICON.toLong(), value)

    /** hCursor : handle vers le curseur de la fenêtre (NULL = défaut). */
    var hCursor: MemorySegment
        get() = segment.get(ValueLayout.ADDRESS, OFFSET_HCURSOR.toLong())
        set(value) = segment.set(ValueLayout.ADDRESS, OFFSET_HCURSOR.toLong(), value)

    /** hbrBackground : pinceau pour l'arrière-plan (NULL = aucun). */
    var hbrBackground: MemorySegment
        get() = segment.get(ValueLayout.ADDRESS, OFFSET_HBRUSH.toLong())
        set(value) = segment.set(ValueLayout.ADDRESS, OFFSET_HBRUSH.toLong(), value)

    /** lpszMenuName : nom de la ressource menu (NULL = aucun menu). */
    var lpszMenuName: MemorySegment
        get() = segment.get(ValueLayout.ADDRESS, OFFSET_MENU_NAME.toLong())
        set(value) = segment.set(ValueLayout.ADDRESS, OFFSET_MENU_NAME.toLong(), value)

    /** lpszClassName : nom de la classe de fenêtre (Wide string). */
    var lpszClassName: MemorySegment
        get() = segment.get(ValueLayout.ADDRESS, OFFSET_CLASS_NAME.toLong())
        set(value) = segment.set(ValueLayout.ADDRESS, OFFSET_CLASS_NAME.toLong(), value)

    /** hIconSm : handle vers la petite icône (NULL = dérivée de hIcon). */
    var hIconSm: MemorySegment
        get() = segment.get(ValueLayout.ADDRESS, OFFSET_HICON_SM.toLong())
        set(value) = segment.set(ValueLayout.ADDRESS, OFFSET_HICON_SM.toLong(), value)

    companion object {
        // ── Offsets (en octets) ──────────────────────────────────────────────
        //
        // Calculés selon l'ABI Win64 :
        //  - Les pointeurs (WNDPROC, HINSTANCE, HICON, etc.) sont alignés sur 8 octets.
        //  - Les champs int (UINT, cbClsExtra, cbWndExtra) sont alignés sur 4 octets.
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

        /** Taille totale de la structure en octets (80 bytes sur Win64). */
        const val SIZEOF: Int = 80

        /** Alignement requis (8 octets, alignement des pointeurs Win64). */
        const val ALIGN: Int = 8

        /**
         * Layout MemoryLayout équivalent à WNDCLASSEXW.
         *
         * Fourni pour documentation et vérification, non utilisé directement
         * dans les appels FFM (on utilise les offsets manuels).
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
