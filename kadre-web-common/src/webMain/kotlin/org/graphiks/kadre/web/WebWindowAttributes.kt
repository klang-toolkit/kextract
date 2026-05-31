/**
 * Attributs de création d'une fenêtre Web — extension web-only du contrat
 * [org.graphiks.kadre.core.WindowAttributes].
 *
 * Inspiré du trait `WindowAttributesExtWebSys` de [winit](https://docs.rs/winit/latest/winit/platform/web/trait.WindowAttributesExtWebSys.html),
 * qui ajoute à `WindowAttributes` les méthodes `with_canvas(...)` et `with_append(...)`
 * spécifiques à la cible web. Le contrat core reste agnostique du DOM.
 *
 * ## Cas d'usage
 *
 * 1. **Canvas DOM existant** (cas typique d'un embed dans une page hôte) :
 *    ```kotlin
 *    webLoop.createWindow(WebWindowAttributes(canvasId = "my-canvas"))
 *    ```
 * 2. **Création auto, append au `<body>`** (cas typique d'une démo standalone) :
 *    ```kotlin
 *    webLoop.createWindow(WebWindowAttributes(appendToBody = true, width = 800, height = 600))
 *    ```
 * 3. **Création auto, parent explicite** :
 *    ```kotlin
 *    webLoop.createWindow(WebWindowAttributes(
 *        canvasId = "kadre",
 *        parentElementId = "app-root",
 *        appendToBody = true,
 *    ))
 *    ```
 *
 * ## Contrainte webMain
 * Ce fichier réside dans `webMain` : aucun type DOM (`HTMLCanvasElement`, …)
 * ne peut transiter ici. Le canvas est référencé par son `id` CSS uniquement.
 * Un overload exposant directement un `HTMLCanvasElement` pourrait être ajouté
 * en `jsMain` / `wasmJsMain` si nécessaire (out of scope cette PR).
 *
 * @property canvasId          Id CSS d'un `<canvas>` DOM existant ou à créer.
 *                              `null` ⇒ id par défaut `"kadre-canvas"`.
 * @property appendToBody       Si `true` et qu'aucun canvas portant [canvasId] n'existe,
 *                              Kadre crée un `<canvas>` (avec [width] × [height]) et
 *                              l'ajoute au DOM (dans [parentElementId] ou `<body>`).
 * @property parentElementId    Id CSS du parent où insérer le canvas créé.
 *                              Ignoré si le canvas préexiste. `null` ⇒ `<body>`.
 * @property width              Largeur initiale en pixels physiques du canvas créé.
 * @property height             Hauteur initiale en pixels physiques du canvas créé.
 * @property core               Attributs cross-platform sous-jacents
 *                              ([org.graphiks.kadre.core.WindowAttributes.title],
 *                              size, visible, resizable) — la majorité sont no-op
 *                              côté Web mais conservés pour cohérence multi-plateforme.
 *
 * @since 0.2.0
 */
package org.graphiks.kadre.web

import org.graphiks.kadre.core.WindowAttributes

data class WebWindowAttributes(
    val canvasId: String? = null,
    val appendToBody: Boolean = false,
    val parentElementId: String? = null,
    val width: Int = 800,
    val height: Int = 600,
    val core: WindowAttributes = WindowAttributes(),
) {
    /** Identifiant CSS effectif du canvas (avec fallback `"kadre-canvas"`). */
    val effectiveCanvasId: String get() = canvasId ?: DEFAULT_CANVAS_ID

    companion object {
        const val DEFAULT_CANVAS_ID: String = "kadre-canvas"
    }
}
