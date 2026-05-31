/**
 * Type aliases pour les types Win32 fondamentaux.
 *
 * Ces types sont des alias Kotlin des types primitifs C/Win32. Ils seront utilisés
 * par les bindings FFM générés par kextract en Sprint 3 (requires Windows SDK).
 *
 * Référence : https://learn.microsoft.com/en-us/windows/win32/winprog/windows-data-types
 *
 * Convention de nommage :
 *  - Les noms correspondent aux typedefs Win32 officiels (windef.h, minwindef.h).
 *  - Les handles opaques (HWND, HINSTANCE, …) sont représentés comme [Long] car
 *    la JVM ne dispose pas d'un type pointer natif ; on passera par MemorySegment
 *    dans les bindings FFM réels (kextract Sprint 3).
 *  - BOOL est mappé à [Int] (et non [Boolean]) pour correspondre exactement à
 *    la sémantique Win32 (0 = FALSE, non-zéro = TRUE).
 *
 * TODO Sprint 3 : Remplacer les alias Long/Int par les MemorySegment générés
 *   par kextract une fois le Windows SDK configuré dans le pipeline CI.
 */
package org.graphiks.kadre.win32.bindings

// ── Entiers non signés ────────────────────────────────────────────────────────

/**
 * DWORD — entier non signé 32 bits (unsigned long en C).
 *
 * Utilisé pour les codes de style de fenêtre, les identifiants de message,
 * les valeurs de retour d'erreur (GetLastError), etc.
 */
typealias DWORD = Long

/**
 * WORD — entier non signé 16 bits (unsigned short en C).
 *
 * Utilisé pour les identifiants de ressource, les codes de touche virtuelle, etc.
 */
typealias WORD = Int

/**
 * UINT — entier non signé 32 bits (unsigned int en C).
 *
 * Utilisé pour les identifiants de message Windows (WM_*, etc.).
 */
typealias UINT = Long

/**
 * ULONG_PTR — entier non signé de taille pointeur (size_t en C).
 *
 * Utilisé pour les paramètres wParam des messages Windows.
 */
typealias ULONG_PTR = Long

// ── Entiers signés ────────────────────────────────────────────────────────────

/**
 * LONG — entier signé 32 bits (long en C, toujours 32 bits sur Windows).
 *
 * Utilisé dans les structures RECT (left, top, right, bottom), POINT, etc.
 */
typealias LONG = Int

/**
 * LONG_PTR — entier signé de taille pointeur (ptrdiff_t en C).
 *
 * Utilisé pour les paramètres lParam des messages Windows et les styles étendus.
 */
typealias LONG_PTR = Long

/**
 * LRESULT — valeur de retour d'une procédure de fenêtre (LONG_PTR).
 *
 * Retourné par WindowProc et DefWindowProc.
 */
typealias LRESULT = Long

/**
 * WPARAM — paramètre de message « word param » (UINT_PTR en C).
 *
 * Premier paramètre de message (ex : touche virtuelle pour WM_KEYDOWN).
 */
typealias WPARAM = Long

/**
 * LPARAM — paramètre de message « long param » (LONG_PTR en C).
 *
 * Second paramètre de message (ex : coordonnées pour WM_MOUSEMOVE).
 */
typealias LPARAM = Long

// ── Booléen Win32 ─────────────────────────────────────────────────────────────

/**
 * BOOL — entier 32 bits représentant un booléen Win32 (int en C).
 *
 * 0 = FALSE, toute valeur non nulle = TRUE.
 * Intentionnellement [Int] et non [Boolean] pour correspondre à la sémantique
 * exacte de Win32 (certaines fonctions retournent des valeurs non-0/1).
 */
typealias BOOL = Int

// ── Handles opaques ───────────────────────────────────────────────────────────

/**
 * HWND — handle vers une fenêtre Win32.
 *
 * Opaque en C ; représenté comme [Long] (adresse pointeur 64 bits).
 * Sera remplacé par MemorySegment dans les bindings FFM générés (Sprint 3).
 */
typealias HWND = Long

/**
 * HINSTANCE — handle vers une instance d'application (module).
 *
 * Passé à WinMain et utilisé dans RegisterClassEx / CreateWindowEx.
 */
typealias HINSTANCE = Long

/**
 * HMODULE — handle vers un module chargé (équivalent à HINSTANCE).
 *
 * Retourné par GetModuleHandle, LoadLibrary, etc.
 */
typealias HMODULE = Long

/**
 * HICON — handle vers une icône Win32.
 *
 * Utilisé dans WNDCLASSEX pour spécifier les icônes de fenêtre.
 */
typealias HICON = Long

/**
 * HCURSOR — handle vers un curseur Win32.
 *
 * Utilisé dans WNDCLASSEX (hCursor) et SetCursor.
 */
typealias HCURSOR = Long

/**
 * HBRUSH — handle vers un pinceau GDI Win32.
 *
 * Utilisé pour la couleur d'arrière-plan dans WNDCLASSEX (hbrBackground).
 */
typealias HBRUSH = Long

/**
 * HDC — handle vers un Device Context (contexte graphique GDI).
 *
 * Retourné par GetDC, BeginPaint, CreateCompatibleDC, etc.
 */
typealias HDC = Long

/**
 * HMENU — handle vers un menu Win32.
 *
 * Passé à CreateWindowEx comme paramètre de menu.
 */
typealias HMENU = Long

/**
 * ATOM — identifiant entier retourné par RegisterClassEx.
 *
 * Utilisé pour identifier une classe de fenêtre enregistrée.
 */
typealias ATOM = Int
