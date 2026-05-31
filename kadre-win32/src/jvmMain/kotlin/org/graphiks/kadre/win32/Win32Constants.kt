/**
 * Constantes de messages Win32 (WM_*) et codes de touches virtuelles (VK_*).
 *
 * Séparé de Win32_h.kt pour regrouper toutes les constantes de messages nécessaires
 * à KadreWndProc en un seul endroit.
 *
 * Référence : https://learn.microsoft.com/en-us/windows/win32/winmsg/window-messages
 */
package org.graphiks.kadre.win32

// ── Messages de cycle de vie de la fenêtre ────────────────────────────────────

/** WM_DESTROY — la fenêtre est détruite (déjà défini dans Win32_h.kt, réexporté ici pour cohérence). */
// WM_DESTROY = 0x0002  (déjà dans Win32_h.kt)

/** WM_CLOSE — demande de fermeture de la fenêtre (bouton ×, Alt+F4). */
internal const val WM_CLOSE: Int = 0x0010

/** WM_PAINT — la fenêtre doit être redessinée. */
internal const val WM_PAINT: Int = 0x000F

/** WM_SIZE — la taille de la fenêtre a changé. */
internal const val WM_SIZE: Int = 0x0005

/** WM_DPICHANGED — le DPI de la fenêtre a changé (déplacement vers un autre moniteur). */
internal const val WM_DPICHANGED: Int = 0x02E0

// ── Messages clavier ──────────────────────────────────────────────────────────

/** WM_KEYDOWN — touche enfoncée (touche non-système). */
internal const val WM_KEYDOWN: Int = 0x0100

/** WM_KEYUP — touche relâchée (touche non-système). */
internal const val WM_KEYUP: Int = 0x0101

/** WM_SYSKEYDOWN — touche système enfoncée (Alt + touche). */
internal const val WM_SYSKEYDOWN: Int = 0x0104

/** WM_SYSKEYUP — touche système relâchée (Alt + touche). */
internal const val WM_SYSKEYUP: Int = 0x0105

// ── Messages souris ───────────────────────────────────────────────────────────

/** WM_MOUSEMOVE — le curseur s'est déplacé dans la zone cliente. */
internal const val WM_MOUSEMOVE: Int = 0x0200

/** WM_LBUTTONDOWN — bouton gauche de la souris enfoncé. */
internal const val WM_LBUTTONDOWN: Int = 0x0201

/** WM_LBUTTONUP — bouton gauche de la souris relâché. */
internal const val WM_LBUTTONUP: Int = 0x0202

/** WM_RBUTTONDOWN — bouton droit de la souris enfoncé. */
internal const val WM_RBUTTONDOWN: Int = 0x0204

/** WM_RBUTTONUP — bouton droit de la souris relâché. */
internal const val WM_RBUTTONUP: Int = 0x0205

/** WM_MBUTTONDOWN — bouton du milieu de la souris enfoncé. */
internal const val WM_MBUTTONDOWN: Int = 0x0207

/** WM_MBUTTONUP — bouton du milieu de la souris relâché. */
internal const val WM_MBUTTONUP: Int = 0x0208

/** WM_MOUSEWHEEL — la molette de la souris a tourné (défilement vertical). */
internal const val WM_MOUSEWHEEL: Int = 0x020A

/** WM_XBUTTONDOWN — bouton supplémentaire de la souris enfoncé (bouton 4 ou 5). */
internal const val WM_XBUTTONDOWN: Int = 0x020B

/** WM_XBUTTONUP — bouton supplémentaire de la souris relâché (bouton 4 ou 5). */
internal const val WM_XBUTTONUP: Int = 0x020C

/**
 * WM_MOUSELEAVE — le curseur a quitté la zone cliente de la fenêtre.
 *
 * Envoyé une seule fois après un appel à TrackMouseEvent avec TME_LEAVE.
 * Doit être ré-armé par un nouvel appel à TrackMouseEvent après réception.
 */
internal const val WM_MOUSELEAVE: Int = 0x02A3

// ── Bit de répétition dans lParam (clavier) ───────────────────────────────────

/**
 * Masque du bit 30 de lParam pour les messages clavier.
 *
 * Ce bit est positionné à 1 si la touche était déjà enfoncée avant l'envoi du message
 * (touche maintenue → répétition automatique).
 *
 * Référence : https://learn.microsoft.com/en-us/windows/win32/inputdev/wm-keydown
 */
internal const val KF_REPEAT: Long = 0x4000_0000L

// ── Codes de touches virtuelles (VK_*) ───────────────────────────────────────
// Utilisés par Win32KeyMapper pour la table VK → Key

/** VK_BACK — touche Retour arrière (Backspace). */
internal const val VK_BACK: Int = 0x08

/** VK_TAB — touche Tabulation. */
internal const val VK_TAB: Int = 0x09

/** VK_RETURN — touche Entrée. */
internal const val VK_RETURN: Int = 0x0D

/** VK_ESCAPE — touche Échappement. */
internal const val VK_ESCAPE: Int = 0x1B

/** VK_SPACE — barre d'espace. */
internal const val VK_SPACE: Int = 0x20

/** VK_LEFT — flèche gauche. */
internal const val VK_LEFT: Int = 0x25

/** VK_UP — flèche haut. */
internal const val VK_UP: Int = 0x26

/** VK_RIGHT — flèche droite. */
internal const val VK_RIGHT: Int = 0x27

/** VK_DOWN — flèche bas. */
internal const val VK_DOWN: Int = 0x28

/** VK_SHIFT — touche Majuscule (générique). */
internal const val VK_SHIFT: Int = 0x10

/** VK_CONTROL — touche Contrôle (générique). */
internal const val VK_CONTROL: Int = 0x11

/** VK_MENU — touche Alt (générique). */
internal const val VK_MENU: Int = 0x12

/** VK_LSHIFT — Majuscule gauche. */
internal const val VK_LSHIFT: Int = 0xA0

/** VK_RSHIFT — Majuscule droite. */
internal const val VK_RSHIFT: Int = 0xA1

/** VK_LCONTROL — Contrôle gauche. */
internal const val VK_LCONTROL: Int = 0xA2

/** VK_RCONTROL — Contrôle droite. */
internal const val VK_RCONTROL: Int = 0xA3

/** VK_LMENU — Alt gauche. */
internal const val VK_LMENU: Int = 0xA4

/** VK_RMENU — Alt droite (AltGr). */
internal const val VK_RMENU: Int = 0xA5

/** VK_LWIN — touche Windows gauche (Meta). */
internal const val VK_LWIN: Int = 0x5B

/** VK_RWIN — touche Windows droite (Meta). */
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

// Chiffres 0–9 (rangée du haut du clavier)
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

// Lettres A–Z (codes ASCII majuscules)
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

// ── Constantes WM_MOUSEWHEEL ──────────────────────────────────────────────────

/**
 * WHEEL_DELTA — unité de défilement de la molette (120 clics par cran standard).
 *
 * La valeur haute de wParam contient le delta de défilement en multiples de WHEEL_DELTA.
 */
internal const val WHEEL_DELTA: Int = 120

// ── Constantes WM_XBUTTONDOWN/UP ─────────────────────────────────────────────

/**
 * Masque pour extraire le numéro de bouton X depuis le HIWORD de wParam.
 *
 * wParam de WM_XBUTTONDOWN/UP :
 *   LOWORD = état des touches et boutons de souris (MK_*)
 *   HIWORD = bouton X concerné (XBUTTON1 ou XBUTTON2)
 */
internal const val XBUTTON_HIWORD_MASK: Long = 0xFFFF_0000L

/** XBUTTON1 — premier bouton supplémentaire (bouton 4 = Back). */
internal const val XBUTTON1: Int = 0x0001

/** XBUTTON2 — second bouton supplémentaire (bouton 5 = Forward). */
internal const val XBUTTON2: Int = 0x0002

// ── Constantes WM_DPICHANGED ──────────────────────────────────────────────────

/**
 * Décalage pour extraire le DPI X depuis wParam de WM_DPICHANGED.
 *
 * wParam = MAKEWPARAM(dpiX, dpiY) → bits bas = dpiX, bits hauts = dpiY.
 */
internal const val DPI_WPARAM_MASK: Long = 0xFFFFL

// ── Constantes de la boucle de messages ──────────────────────────────────────

/**
 * PM_REMOVE — flag pour PeekMessageW : retire le message de la file après lecture.
 *
 * Référence : https://learn.microsoft.com/en-us/windows/win32/api/winuser/nf-winuser-peekmessagew
 */
internal const val PM_REMOVE: Int = 0x0001

/**
 * PM_NOREMOVE — flag pour PeekMessageW : ne retire pas le message de la file.
 *
 * Utilisé pour inspecter la file sans consommer le message.
 */
internal const val PM_NOREMOVE: Int = 0x0000

/**
 * QS_ALLINPUT — masque de réveil MsgWaitForMultipleObjectsEx couvrant tous les types
 * de messages d'entrée (clavier, souris, horloge, peinture, envoi, file).
 *
 * QS_ALLINPUT = QS_INPUT | QS_POSTMESSAGE | QS_TIMER | QS_PAINT | QS_HOTKEY | QS_SENDMESSAGE
 *
 * Référence : https://learn.microsoft.com/en-us/windows/win32/api/winuser/nf-winuser-msgwaitformultipleobjectsex
 */
internal const val QS_ALLINPUT: Int = 0x04FF

/**
 * MWMO_INPUTAVAILABLE — flag MsgWaitForMultipleObjectsEx : réveille la boucle
 * si des messages d'entrée sont disponibles (même s'ils ont déjà été vus).
 *
 * Sans ce flag, la fonction peut ne pas se réveiller si les messages sont dans
 * la file mais n'ont pas encore été traités.
 *
 * Référence : https://learn.microsoft.com/en-us/windows/win32/api/winuser/nf-winuser-msgwaitformultipleobjectsex
 */
internal const val MWMO_INPUTAVAILABLE: Int = 0x0004

/**
 * WAIT_OBJECT_0 — code de retour de MsgWaitForMultipleObjectsEx indiquant
 * que le premier objet attendu est signalé (ou que des messages sont disponibles
 * quand nCount = 0, auquel cas la valeur retournée est WAIT_OBJECT_0).
 *
 * Référence : https://learn.microsoft.com/en-us/windows/win32/api/synchapi/nf-synchapi-waitformultipleobjects
 */
internal const val WAIT_OBJECT_0: Int = 0x00000000

/**
 * WAIT_TIMEOUT — code de retour de MsgWaitForMultipleObjectsEx indiquant
 * que le timeout a expiré sans qu'un objet soit signalé.
 */
internal const val WAIT_TIMEOUT: Int = 0x00000102

/**
 * INFINITE — valeur de timeout pour MsgWaitForMultipleObjectsEx indiquant
 * une attente sans limite de temps (équivalent à 0xFFFFFFFF).
 */
@Suppress("INTEGER_OVERFLOW")
internal const val INFINITE: Int = -1  // 0xFFFFFFFF en unsigned
