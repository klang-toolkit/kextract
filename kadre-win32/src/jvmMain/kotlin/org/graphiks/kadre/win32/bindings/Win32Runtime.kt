/**
 * Win32Runtime — stub du runtime FFM pour les bindings Win32.
 *
 * Ce module fournit le point d'entrée pour les appels FFM vers l'API Win32.
 * L'implémentation complète sera générée par kextract en Sprint 3 une fois
 * le Windows SDK configuré dans le pipeline CI/CD.
 *
 * Architecture prévue (Sprint 3) :
 *  - Chargement de user32.dll, kernel32.dll, gdi32.dll via SymbolLookup FFM
 *  - Wrappers typés autour des MethodHandle FFM pour CreateWindowEx,
 *    RegisterClassEx, DefWindowProc, ShowWindow, UpdateWindow, etc.
 *  - Boucle de messages Win32 (GetMessage / TranslateMessage / DispatchMessage)
 *    intégrée avec l'EventLoop de kadre-core.
 *
 * Référence : https://learn.microsoft.com/en-us/windows/win32/learnwin32/
 *
 * TODO Sprint 3 : Remplacer ce stub par Win32_h.kt généré par kextract +
 *   l'implémentation de [org.graphiks.kadre.core.EventLoop] et
 *   [org.graphiks.kadre.core.Window].
 */
package org.graphiks.kadre.win32.bindings

/**
 * Point d'entrée singleton pour le runtime Win32 via FFM.
 *
 * Fournit l'accès aux bibliothèques Win32 système (user32, kernel32, gdi32)
 * et aux symboles FFM nécessaires pour créer et gérer des fenêtres natives.
 *
 * Usage prévu (Sprint 3) :
 * ```kotlin
 * val hwnd = Win32Runtime.createWindow("Ma Fenêtre", 800, 600)
 * Win32Runtime.runMessageLoop()
 * ```
 *
 * Note : Cet objet est un stub — toutes les méthodes lèvent [NotImplementedError]
 * jusqu'à l'implémentation complète en Sprint 3.
 */
object Win32Runtime {

    /**
     * Nom de la bibliothèque User32 (gestion des fenêtres et messages).
     *
     * Sera utilisé par SymbolLookup.libraryLookup("user32", Arena.global()) en Sprint 3.
     */
    const val USER32_LIB = "user32"

    /**
     * Nom de la bibliothèque Kernel32 (fonctions système de base).
     *
     * Sera utilisé pour GetModuleHandle, ExitProcess, etc.
     */
    const val KERNEL32_LIB = "kernel32"

    /**
     * Nom de la bibliothèque GDI32 (Graphics Device Interface).
     *
     * Sera utilisé pour les opérations de dessin GDI si nécessaire.
     */
    const val GDI32_LIB = "gdi32"

    /**
     * Indique si le runtime tourne sur Windows.
     *
     * Permet aux modules consommateurs de vérifier la disponibilité du backend Win32
     * sans déclencher de chargement de bibliothèque.
     */
    val isAvailable: Boolean
        get() = System.getProperty("os.name")?.startsWith("Windows") == true
}
