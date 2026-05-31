/**
 * Layout mémoire pour la structure MSG Win32.
 *
 * La structure MSG est passée à PeekMessageW, GetMessageW, TranslateMessage
 * et DispatchMessageW. Elle doit être allouée dans une arène pour que les
 * appels FFM puissent écrire directement en mémoire.
 *
 * Structure MSG (Win64, 64 bits) :
 * Offset  Size  Field
 *  0       8    hwnd      (HWND — pointeur 64 bits)
 *  8       4    message   (UINT — 32 bits)
 * 12       4    (padding pour aligner wParam sur 8 octets)
 * 16       8    wParam    (WPARAM = UINT_PTR — 64 bits)
 * 24       8    lParam    (LPARAM = LONG_PTR — 64 bits)
 * 32       4    time      (DWORD — 32 bits)
 * 36       4    pt.x      (LONG — 32 bits)
 * 40       4    pt.y      (LONG — 32 bits)
 * 44       4    (padding final)
 * Total = 48 bytes
 *
 * Note : Sur Windows x64, le compilateur MSVC insère 4 octets de padding entre
 * `message` (UINT, 4 bytes) et `wParam` (UINT_PTR, 8 bytes) pour l'alignement
 * sur 8 octets. Le champ `pt` est un POINT (deux LONG = 8 bytes) suivi d'un
 * padding de 4 bytes pour aligner le sizeof sur 8.
 *
 * Référence : https://learn.microsoft.com/en-us/windows/win32/api/winuser/ns-winuser-msg
 */
package org.graphiks.kadre.win32

import java.lang.foreign.Arena
import java.lang.foreign.MemoryLayout
import java.lang.foreign.MemorySegment
import java.lang.foreign.ValueLayout

/**
 * Offsets et constantes du layout MSG Win64.
 *
 * Permet l'accès direct aux champs du segment mémoire MSG sans passer
 * par VarHandle (approche identique à WndClassExW pour la cohérence).
 */
internal object MsgLayout {

    // ── Offsets (en octets) ──────────────────────────────────────────────────
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

    /** Taille totale de la structure MSG en octets (48 bytes sur Win64). */
    const val SIZEOF: Int = 48

    /** Alignement requis (8 octets, alignement des pointeurs Win64). */
    const val ALIGN: Int = 8

    /**
     * Layout MemoryLayout équivalent à MSG Win64.
     *
     * Fourni pour documentation et validation, non utilisé directement
     * dans les appels FFM (on utilise les offsets manuels).
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
 * Alloue un segment mémoire pour une structure MSG dans l'arène fournie.
 *
 * Le segment est initialisé à zéro par défaut (comportement de l'allocateur FFM).
 * À utiliser avec PeekMessageW, GetMessageW, TranslateMessage, DispatchMessageW.
 *
 * @return Segment mémoire de [MsgLayout.SIZEOF] octets, aligné sur [MsgLayout.ALIGN].
 */
internal fun Arena.allocateMsg(): MemorySegment =
    this.allocate(MsgLayout.SIZEOF.toLong(), MsgLayout.ALIGN.toLong())

/**
 * Lit le champ `message` (UINT) depuis un segment MSG.
 *
 * Utile pour inspecter le type de message sans passer par un VarHandle.
 */
internal fun MemorySegment.msgMessage(): Int =
    this.get(ValueLayout.JAVA_INT, MsgLayout.OFFSET_MESSAGE.toLong())
