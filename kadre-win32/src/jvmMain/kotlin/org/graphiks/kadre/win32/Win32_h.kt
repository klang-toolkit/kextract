/**
 * Bindings FFM pour les fonctions Win32 nécessaires à la gestion de fenêtres.
 *
 * Charge user32.dll et kernel32.dll via SymbolLookup.libraryLookup avec un
 * pattern tryCreate (try/catch Throwable) pour que le build passe sur macOS/Linux.
 *
 * Fonctions exposées :
 *  - RegisterClassExW  (user32)
 *  - CreateWindowExW   (user32)
 *  - ShowWindow        (user32)
 *  - UpdateWindow      (user32)
 *  - DestroyWindow     (user32)
 *  - DefWindowProcW    (user32)
 *  - SetWindowTextW    (user32)
 *  - PostQuitMessage   (user32)
 *  - GetKeyState       (user32)
 *  - GetModuleHandleW  (kernel32)
 *
 * Référence : https://learn.microsoft.com/en-us/windows/win32/learnwin32/
 */
package org.graphiks.kadre.win32

import java.lang.foreign.Arena
import java.lang.foreign.FunctionDescriptor
import java.lang.foreign.Linker
import java.lang.foreign.MemorySegment
import java.lang.foreign.SymbolLookup
import java.lang.foreign.ValueLayout
import java.lang.invoke.MethodHandle

// ── Lazy loading des bibliothèques ────────────────────────────────────────────

/**
 * Lookup user32.dll — null sur les plateformes non-Windows.
 *
 * Le try/catch sur Throwable est intentionnel : SymbolLookup.libraryLookup
 * peut lever IllegalArgumentException ou UnsatisfiedLinkError sur macOS/Linux,
 * et on veut que le build reste vert dans tous les cas.
 */
internal val user32: SymbolLookup? by lazy {
    try {
        SymbolLookup.libraryLookup("user32.dll", Arena.global())
    } catch (e: Throwable) {
        null
    }
}

/**
 * Lookup kernel32.dll — null sur les plateformes non-Windows.
 */
internal val kernel32: SymbolLookup? by lazy {
    try {
        SymbolLookup.libraryLookup("kernel32.dll", Arena.global())
    } catch (e: Throwable) {
        null
    }
}

private val linker: Linker = Linker.nativeLinker()

// ── Helpers ───────────────────────────────────────────────────────────────────

/**
 * Recherche un symbole dans un SymbolLookup et crée un MethodHandle downcall.
 * Retourne null si le lookup est null ou si le symbole est introuvable.
 */
private fun SymbolLookup?.downcall(name: String, desc: FunctionDescriptor): MethodHandle? {
    this ?: return null
    return this.find(name).map { linker.downcallHandle(it, desc) }.orElse(null)
}

// ── RegisterClassExW ──────────────────────────────────────────────────────────

/**
 * ATOM RegisterClassExW(const WNDCLASSEXW *lpwcx);
 *
 * Enregistre une classe de fenêtre Win32. Prend un pointeur vers WNDCLASSEXW,
 * retourne un ATOM (Short) : non-zéro en cas de succès.
 */
internal val registerClassExW: MethodHandle? by lazy {
    user32.downcall(
        "RegisterClassExW",
        FunctionDescriptor.of(
            ValueLayout.JAVA_SHORT,  // ATOM (WORD = unsigned short)
            ValueLayout.ADDRESS,     // const WNDCLASSEXW*
        )
    )
}

// ── CreateWindowExW ───────────────────────────────────────────────────────────

/**
 * HWND CreateWindowExW(
 *     DWORD     dwExStyle,
 *     LPCWSTR   lpClassName,
 *     LPCWSTR   lpWindowName,
 *     DWORD     dwStyle,
 *     int       X,
 *     int       Y,
 *     int       nWidth,
 *     int       nHeight,
 *     HWND      hWndParent,
 *     HMENU     hMenu,
 *     HINSTANCE hInstance,
 *     LPVOID    lpParam
 * );
 */
internal val createWindowExW: MethodHandle? by lazy {
    user32.downcall(
        "CreateWindowExW",
        FunctionDescriptor.of(
            ValueLayout.ADDRESS,    // HWND retour
            ValueLayout.JAVA_INT,   // dwExStyle (DWORD → int en C sur Win64)
            ValueLayout.ADDRESS,    // lpClassName (LPCWSTR)
            ValueLayout.ADDRESS,    // lpWindowName (LPCWSTR)
            ValueLayout.JAVA_INT,   // dwStyle (DWORD)
            ValueLayout.JAVA_INT,   // X
            ValueLayout.JAVA_INT,   // Y
            ValueLayout.JAVA_INT,   // nWidth
            ValueLayout.JAVA_INT,   // nHeight
            ValueLayout.ADDRESS,    // hWndParent (HWND)
            ValueLayout.ADDRESS,    // hMenu (HMENU)
            ValueLayout.ADDRESS,    // hInstance (HINSTANCE)
            ValueLayout.ADDRESS,    // lpParam (LPVOID)
        )
    )
}

// ── ShowWindow ────────────────────────────────────────────────────────────────

/**
 * BOOL ShowWindow(HWND hWnd, int nCmdShow);
 */
internal val showWindow: MethodHandle? by lazy {
    user32.downcall(
        "ShowWindow",
        FunctionDescriptor.of(
            ValueLayout.JAVA_INT,   // BOOL
            ValueLayout.ADDRESS,    // HWND
            ValueLayout.JAVA_INT,   // nCmdShow
        )
    )
}

// ── UpdateWindow ──────────────────────────────────────────────────────────────

/**
 * BOOL UpdateWindow(HWND hWnd);
 */
internal val updateWindow: MethodHandle? by lazy {
    user32.downcall(
        "UpdateWindow",
        FunctionDescriptor.of(
            ValueLayout.JAVA_INT,   // BOOL
            ValueLayout.ADDRESS,    // HWND
        )
    )
}

// ── DestroyWindow ─────────────────────────────────────────────────────────────

/**
 * BOOL DestroyWindow(HWND hWnd);
 */
internal val destroyWindow: MethodHandle? by lazy {
    user32.downcall(
        "DestroyWindow",
        FunctionDescriptor.of(
            ValueLayout.JAVA_INT,   // BOOL
            ValueLayout.ADDRESS,    // HWND
        )
    )
}

// ── DefWindowProcW ────────────────────────────────────────────────────────────

/**
 * LRESULT DefWindowProcW(HWND hWnd, UINT Msg, WPARAM wParam, LPARAM lParam);
 */
internal val defWindowProcW: MethodHandle? by lazy {
    user32.downcall(
        "DefWindowProcW",
        FunctionDescriptor.of(
            ValueLayout.JAVA_LONG,  // LRESULT
            ValueLayout.ADDRESS,    // HWND
            ValueLayout.JAVA_INT,   // UINT (message)
            ValueLayout.JAVA_LONG,  // WPARAM
            ValueLayout.JAVA_LONG,  // LPARAM
        )
    )
}

// ── SetWindowTextW ────────────────────────────────────────────────────────────

/**
 * BOOL SetWindowTextW(HWND hWnd, LPCWSTR lpString);
 */
internal val setWindowTextW: MethodHandle? by lazy {
    user32.downcall(
        "SetWindowTextW",
        FunctionDescriptor.of(
            ValueLayout.JAVA_INT,   // BOOL
            ValueLayout.ADDRESS,    // HWND
            ValueLayout.ADDRESS,    // LPCWSTR
        )
    )
}

// ── PostQuitMessage ───────────────────────────────────────────────────────────

/**
 * void PostQuitMessage(int nExitCode);
 *
 * Place un message WM_QUIT dans la file de messages du thread courant, ce qui
 * provoque la sortie de la boucle GetMessage.
 */
internal val postQuitMessage: MethodHandle? by lazy {
    user32.downcall(
        "PostQuitMessage",
        FunctionDescriptor.ofVoid(
            ValueLayout.JAVA_INT,   // nExitCode
        )
    )
}

// ── GetKeyState ───────────────────────────────────────────────────────────────

/**
 * SHORT GetKeyState(int nVirtKey);
 *
 * Retourne l'état d'une touche virtuelle au moment du traitement du dernier
 * message extrait par GetMessage. Bit 15 = touche enfoncée, bit 0 = toggle.
 */
internal val getKeyState: MethodHandle? by lazy {
    user32.downcall(
        "GetKeyState",
        FunctionDescriptor.of(
            ValueLayout.JAVA_SHORT, // SHORT
            ValueLayout.JAVA_INT,   // nVirtKey
        )
    )
}

// ── PeekMessageW ─────────────────────────────────────────────────────────────

/**
 * BOOL PeekMessageW(LPMSG lpMsg, HWND hWnd, UINT wMsgFilterMin, UINT wMsgFilterMax, UINT wRemoveMsg);
 *
 * Vérifie si un message est disponible dans la file et, si PM_REMOVE est spécifié,
 * le retire. Retourne non-zéro si un message est disponible, 0 sinon.
 * Non-bloquant — retourne immédiatement.
 */
internal val peekMessageW: MethodHandle? by lazy {
    user32.downcall(
        "PeekMessageW",
        FunctionDescriptor.of(
            ValueLayout.JAVA_INT,   // BOOL
            ValueLayout.ADDRESS,    // LPMSG lpMsg
            ValueLayout.ADDRESS,    // HWND hWnd (NULL = tous les messages du thread)
            ValueLayout.JAVA_INT,   // UINT wMsgFilterMin
            ValueLayout.JAVA_INT,   // UINT wMsgFilterMax
            ValueLayout.JAVA_INT,   // UINT wRemoveMsg
        )
    )
}

// ── GetMessageW ──────────────────────────────────────────────────────────────

/**
 * BOOL GetMessageW(LPMSG lpMsg, HWND hWnd, UINT wMsgFilterMin, UINT wMsgFilterMax);
 *
 * Extrait un message de la file de messages du thread. Bloquant — attend jusqu'à
 * ce qu'un message soit disponible.
 * Retourne > 0 si message, 0 si WM_QUIT, -1 en cas d'erreur.
 */
internal val getMessageW: MethodHandle? by lazy {
    user32.downcall(
        "GetMessageW",
        FunctionDescriptor.of(
            ValueLayout.JAVA_INT,   // BOOL
            ValueLayout.ADDRESS,    // LPMSG lpMsg
            ValueLayout.ADDRESS,    // HWND hWnd (NULL = tous les messages du thread)
            ValueLayout.JAVA_INT,   // UINT wMsgFilterMin
            ValueLayout.JAVA_INT,   // UINT wMsgFilterMax
        )
    )
}

// ── TranslateMessage ──────────────────────────────────────────────────────────

/**
 * BOOL TranslateMessage(const MSG *lpMsg);
 *
 * Traduit les messages virtuels-touche en messages de caractères (WM_CHAR).
 * Doit être appelé avant DispatchMessageW dans la boucle de messages.
 */
internal val translateMessage: MethodHandle? by lazy {
    user32.downcall(
        "TranslateMessage",
        FunctionDescriptor.of(
            ValueLayout.JAVA_INT,   // BOOL
            ValueLayout.ADDRESS,    // const MSG*
        )
    )
}

// ── DispatchMessageW ──────────────────────────────────────────────────────────

/**
 * LRESULT DispatchMessageW(const MSG *lpMsg);
 *
 * Dispatche un message vers la procédure de fenêtre (WndProc).
 */
internal val dispatchMessageW: MethodHandle? by lazy {
    user32.downcall(
        "DispatchMessageW",
        FunctionDescriptor.of(
            ValueLayout.JAVA_LONG,  // LRESULT
            ValueLayout.ADDRESS,    // const MSG*
        )
    )
}

// ── MsgWaitForMultipleObjectsEx ───────────────────────────────────────────────

/**
 * DWORD MsgWaitForMultipleObjectsEx(DWORD nCount, const HANDLE *pHandles,
 *     DWORD dwMilliseconds, DWORD dwWakeMask, DWORD dwFlags);
 *
 * Attend jusqu'à ce qu'un message arrive ou que le timeout expire.
 */
internal val msgWaitForMultipleObjectsEx: MethodHandle? by lazy {
    user32.downcall(
        "MsgWaitForMultipleObjectsEx",
        FunctionDescriptor.of(
            ValueLayout.JAVA_INT,   // DWORD (WAIT_*)
            ValueLayout.JAVA_INT,   // DWORD nCount
            ValueLayout.ADDRESS,    // const HANDLE* pHandles
            ValueLayout.JAVA_INT,   // DWORD dwMilliseconds
            ValueLayout.JAVA_INT,   // DWORD dwWakeMask
            ValueLayout.JAVA_INT,   // DWORD dwFlags
        )
    )
}

// ── GetModuleHandleW ──────────────────────────────────────────────────────────

/**
 * HMODULE GetModuleHandleW(LPCWSTR lpModuleName);
 *
 * Passer NULL pour obtenir le handle du module courant.
 */
internal val getModuleHandleW: MethodHandle? by lazy {
    kernel32.downcall(
        "GetModuleHandleW",
        FunctionDescriptor.of(
            ValueLayout.ADDRESS,    // HMODULE
            ValueLayout.ADDRESS,    // LPCWSTR (ou NULL)
        )
    )
}

// ── SetProcessDpiAwarenessContext ─────────────────────────────────────────────

/**
 * BOOL SetProcessDpiAwarenessContext(DPI_AWARENESS_CONTEXT value);
 *
 * Définit le mode de conscience DPI du processus. À appeler avant toute
 * création de fenêtre. Disponible depuis Windows 10 RS1 (1607, build 14393).
 *
 * DPI_AWARENESS_CONTEXT est un pseudo-handle : une constante entière négative
 * passée comme ADDRESS (pointer-sized) sur Win64.
 *
 * Constante utile :
 *   DPI_AWARENESS_CONTEXT_PER_MONITOR_AWARE_V2 = -4
 */
internal val setProcessDpiAwarenessContext: MethodHandle? by lazy {
    user32.downcall(
        "SetProcessDpiAwarenessContext",
        FunctionDescriptor.of(
            ValueLayout.JAVA_INT,   // BOOL
            ValueLayout.ADDRESS,    // DPI_AWARENESS_CONTEXT (HANDLE, pointer-sized)
        )
    )
}

/** DPI_AWARENESS_CONTEXT_PER_MONITOR_AWARE_V2 — valeur pseudo-handle Win32. */
internal const val DPI_AWARENESS_CONTEXT_PER_MONITOR_AWARE_V2: Long = -4L

/**
 * Active le mode Per-Monitor-V2 DPI awareness pour le processus.
 *
 * À appeler une seule fois au démarrage de l'EventLoop, avant toute création
 * de fenêtre. Idempotent en pratique : si la conscience DPI est déjà fixée
 * (par le manifest ou un appel précédent), Windows retourne FALSE et positionne
 * `ERROR_ACCESS_DENIED` — on l'ignore silencieusement.
 *
 * Sans effet (no-op) si le binding n'est pas disponible (Windows < 10 RS1) ou
 * sur une plateforme non-Windows où le lookup échoue.
 *
 * @return `true` si l'appel a réussi, `false` sinon (déjà fixé, indisponible…).
 */
internal fun enablePerMonitorV2DpiAwareness(): Boolean {
    val handle = setProcessDpiAwarenessContext ?: return false
    return try {
        val context = MemorySegment.ofAddress(DPI_AWARENESS_CONTEXT_PER_MONITOR_AWARE_V2)
        (handle.invokeExact(context) as Int) != 0
    } catch (_: Throwable) {
        false
    }
}

// ── GetDpiForWindow ───────────────────────────────────────────────────────────

/**
 * UINT GetDpiForWindow(HWND hwnd);
 *
 * Retourne le DPI effectif de la fenêtre. Disponible depuis Windows 10 RS1.
 * Retourne 0 si hwnd est invalide.
 */
internal val getDpiForWindow: MethodHandle? by lazy {
    user32.downcall(
        "GetDpiForWindow",
        FunctionDescriptor.of(
            ValueLayout.JAVA_INT,   // UINT
            ValueLayout.ADDRESS,    // HWND
        )
    )
}

// ── TrackMouseEvent ───────────────────────────────────────────────────────────

/**
 * BOOL TrackMouseEvent(LPTRACKMOUSEEVENT lpEventTrack);
 *
 * Arme la réception de WM_MOUSELEAVE / WM_MOUSEHOVER pour la fenêtre spécifiée.
 * Doit être ré-armé après chaque WM_MOUSELEAVE reçu.
 *
 * TRACKMOUSEEVENT layout (48 bytes sur Win64) :
 *   DWORD cbSize     (offset 0,  4 bytes)
 *   DWORD dwFlags    (offset 4,  4 bytes)
 *   HWND  hwndTrack  (offset 8,  8 bytes)
 *   DWORD dwHoverTime (offset 16, 4 bytes)
 *   [4 bytes padding pour alignement 8]
 */
internal val trackMouseEvent: MethodHandle? by lazy {
    user32.downcall(
        "TrackMouseEvent",
        FunctionDescriptor.of(
            ValueLayout.JAVA_INT,   // BOOL
            ValueLayout.ADDRESS,    // LPTRACKMOUSEEVENT
        )
    )
}

/** Taille de la structure TRACKMOUSEEVENT en bytes (DWORD+DWORD+HWND+DWORD+padding). */
internal const val TRACKMOUSEEVENT_SIZE: Int = 24 // 4+4+8+4+4 (avec padding)

/** TME_LEAVE : flag dwFlags pour recevoir WM_MOUSELEAVE. */
internal const val TME_LEAVE: Int = 0x00000002

// ── GetCursorPos ──────────────────────────────────────────────────────────────

/**
 * BOOL GetCursorPos(LPPOINT lpPoint);
 *
 * Retourne la position courante du curseur en coordonnées écran.
 * POINT = {LONG x, LONG y} = 8 bytes.
 */
internal val getCursorPos: MethodHandle? by lazy {
    user32.downcall(
        "GetCursorPos",
        FunctionDescriptor.of(
            ValueLayout.JAVA_INT,   // BOOL
            ValueLayout.ADDRESS,    // LPPOINT
        )
    )
}

// ── Helpers d'encodage Wide String ───────────────────────────────────────────

/**
 * Alloue une chaîne Wide (UTF-16 LE, null-terminée) dans l'arena fourni.
 *
 * Chaque caractère Java (UTF-16) est écrit directement — les caractères hors
 * BMP ne sont pas supportés (suffisant pour les titres de fenêtre).
 */
internal fun Arena.allocateWString(value: String): MemorySegment {
    // 2 octets par caractère + 2 octets pour le null-terminateur
    val seg = this.allocate((value.length + 1) * 2L, 2L)
    for (i in value.indices) {
        seg.setAtIndex(ValueLayout.JAVA_SHORT, i.toLong(), value[i].code.toShort())
    }
    // null-terminateur (déjà 0 par défaut, mais on l'écrit explicitement)
    seg.setAtIndex(ValueLayout.JAVA_SHORT, value.length.toLong(), 0)
    return seg
}

// ── Constantes Win32 ──────────────────────────────────────────────────────────

/** WS_OVERLAPPEDWINDOW = WS_OVERLAPPED|WS_CAPTION|WS_SYSMENU|WS_THICKFRAME|WS_MINIMIZEBOX|WS_MAXIMIZEBOX */
@Suppress("INTEGER_OVERFLOW")
internal const val WS_OVERLAPPEDWINDOW: Int = 0x00CF0000

/** WS_EX_APPWINDOW — bouton dans la barre des tâches */
internal const val WS_EX_APPWINDOW: Int = 0x00040000

/** SW_SHOW */
internal const val SW_SHOW: Int = 5

/** SW_HIDE */
internal const val SW_HIDE: Int = 0

/** CS_HREDRAW | CS_VREDRAW */
internal const val CS_HREDRAW_VREDRAW: Int = 0x0003

/** WM_DESTROY */
internal const val WM_DESTROY: Int = 0x0002
