/**
 * Handles bruts de fenêtre et d'affichage, indépendants de toute plateforme.
 *
 * Ces types sont utilisés pour passer les pointeurs natifs entre le code commun
 * et les modules platform-spécifiques sans importer de types natifs dans commonMain.
 * Les pointeurs sont représentés en [Long] ; le consommateur effectue le cast
 * vers le type natif approprié au point d'usage.
 *
 * Spécification de référence : Specs §6.1
 */
package org.graphiks.kadre.core

/**
 * Handle brut d'une fenêtre native.
 *
 * Chaque variant correspond à une plateforme cible. Les pointeurs sont exposés
 * en [Long] afin que commonMain reste 100 % Kotlin pur (pas d'import natif).
 */
sealed interface RawWindowHandle {

    /**
     * Handle de fenêtre AppKit (macOS).
     *
     * @property nsView   Pointeur vers l'instance `NSView` (cast vers `NSView*` au point d'usage).
     * @property nsWindow Pointeur vers l'instance `NSWindow` (cast vers `NSWindow*` au point d'usage).
     * @property nsLayer  Pointeur vers l'instance `CAMetalLayer` attachée au `NSView`.
     *                    Exposé directement pour éviter de passer par `[nsView layer]`
     *                    qui peut retourner la couche générique créée par AppKit si
     *                    l'ordre `setLayer`/`setWantsLayer` n'est pas respecté.
     */
    data class AppKit(val nsView: Long, val nsWindow: Long, val nsLayer: Long = 0L) : RawWindowHandle

    /**
     * Handle de fenêtre UIKit (iOS / tvOS).
     *
     * @property uiView           Pointeur vers l'instance `UIView` (cast vers `UIView*` au point d'usage).
     * @property uiViewController Pointeur optionnel vers l'instance `UIViewController`
     *                            (cast vers `UIViewController*` au point d'usage), ou `null`
     *                            si aucun contrôleur n'est associé.
     */
    data class UiKit(val uiView: Long, val uiViewController: Long?) : RawWindowHandle

    /**
     * Handle de fenêtre Android.
     *
     * @property surface Instance de la surface native. Au runtime, ce paramètre est
     *                   obligatoirement une instance de `android.view.Surface` ; le type
     *                   est déclaré [Any] afin de ne pas introduire d'import Android dans
     *                   commonMain — le consommateur effectue le cast explicite.
     */
    data class Android(val surface: Any) : RawWindowHandle

    /**
     * Handle de fenêtre Win32 (Windows).
     *
     * @property hwnd      Window handle Win32 (HWND), représenté comme Long pour compatibilité FFM.
     * @property hinstance Instance handle Win32 (HINSTANCE), représenté comme Long pour compatibilité FFM.
     */
    data class Win32(val hwnd: Long, val hinstance: Long) : RawWindowHandle

    /**
     * Handle de fenêtre X11/Xlib (Linux).
     *
     * @property window  XID de la fenêtre X11 (retourné par XCreateWindow).
     * @property display Pointeur vers le Display X11 (retourné par XOpenDisplay), représenté en Long.
     */
    data class Xlib(val window: Long, val display: Long) : RawWindowHandle

    /**
     * Handle de fenêtre Wayland (Linux).
     *
     * @property surface Pointeur vers la surface Wayland (wl_surface*), représenté en Long.
     * @property display Pointeur vers le display Wayland (wl_display*), représenté en Long.
     */
    data class Wayland(val surface: Long, val display: Long) : RawWindowHandle

    /**
     * Handle de fenêtre Web (navigateur / Wasm).
     *
     * Deux modes d'identification du canvas cible sont supportés :
     * - [canvasElementId] : identifiant CSS du `<canvas>` dans le DOM
     *   (ex. `"my-canvas"`). Pratique pour une configuration déclarative.
     * - [canvasElement] : référence directe à l'élément `HTMLCanvasElement`.
     *   Déclaré [Any] en commonMain pour éviter tout import DOM ; côté JS/Wasm,
     *   effectuer le cast explicite : `canvasElement as HTMLCanvasElement`.
     *
     * Au moins l'un des deux paramètres doit être non-null. Si les deux sont
     * fournis, [canvasElement] est prioritaire sur [canvasElementId].
     *
     * @property canvasElementId Identifiant CSS du canvas dans le DOM, ou `null`.
     * @property canvasElement   Référence directe à l'`HTMLCanvasElement`, ou `null`.
     *                           Côté JS/Wasm, caster avec `canvasElement as HTMLCanvasElement`.
     * @throws IllegalArgumentException si [canvasElementId] et [canvasElement] sont tous les deux `null`.
     */
    data class Web(
        val canvasElementId: String? = null,
        val canvasElement: Any? = null
    ) : RawWindowHandle {
        init {
            require(canvasElementId != null || canvasElement != null) {
                "RawWindowHandle.Web : au moins canvasElementId ou canvasElement doit être non-null"
            }
        }
    }
}

/**
 * Handle brut d'un écran (display) natif.
 *
 * Chaque variant est un singleton correspondant à une plateforme cible.
 * Sur ces plateformes, l'écran n'a pas de handle pointer distinct de la fenêtre.
 */
sealed interface RawDisplayHandle {

    /**
     * Handle d'affichage AppKit (macOS).
     *
     * Sur macOS, le display est implicitement géré par AppKit ; aucun pointeur
     * supplémentaire n'est nécessaire.
     */
    data object AppKit : RawDisplayHandle

    /**
     * Handle d'affichage UIKit (iOS / tvOS).
     *
     * Sur iOS, le display est implicitement géré par UIKit ; aucun pointeur
     * supplémentaire n'est nécessaire.
     */
    data object UiKit : RawDisplayHandle

    /**
     * Handle d'affichage Android.
     *
     * Sur Android, le display est géré par le système ; aucun pointeur
     * supplémentaire n'est nécessaire à ce niveau d'abstraction.
     */
    data object Android : RawDisplayHandle

    /**
     * Handle d'affichage Win32 (Windows).
     *
     * @property hinstance Instance handle Win32 (HINSTANCE), représenté comme Long pour compatibilité FFM.
     */
    data class Win32(val hinstance: Long) : RawDisplayHandle

    /**
     * Handle d'affichage Web (navigateur / Wasm).
     *
     * Dans le contexte Web, l'écran est géré implicitement par le navigateur ;
     * aucun pointeur supplémentaire n'est nécessaire à ce niveau d'abstraction.
     * Ce singleton marque simplement la cible d'affichage comme étant le contexte Web.
     */
    data object Web : RawDisplayHandle

    /**
     * Handle d'affichage X11/Xlib (Linux).
     *
     * @property display Pointeur vers le Display X11 (retourné par XOpenDisplay), représenté en Long.
     */
    data class Xlib(val display: Long) : RawDisplayHandle

    /**
     * Handle d'affichage Wayland (Linux).
     *
     * @property display Pointeur vers le display Wayland (wl_display*), représenté en Long.
     */
    data class Wayland(val display: Long) : RawDisplayHandle
}
