/**
 * Implémentation web de l'interface [Window].
 *
 * ## Canvas
 * Le canvas cible est identifié par [attrs.title] utilisé comme `id` CSS.
 * Si le titre est vide, l'identifiant par défaut `"kadre-canvas"` est utilisé.
 * L'attachement DOM réel est délégué à [WebDomBridge.attach].
 *
 * ## Contrainte webMain
 * Ce fichier réside dans `webMain` — AUCUN import DOM n'est autorisé ici.
 * Les types DOM (HTMLCanvasElement, etc.) sont gérés exclusivement dans
 * `jsMain` via [JsWebDomBridge] et dans `wasmJsMain` via `WasmJsWebDomBridge`.
 *
 * ## Stubs temporaires
 * [innerSize], [outerSize] et [scaleFactor] retournent des valeurs fixes
 * (800×600, scaleFactor 1.0). Ils seront branchés sur un ResizeObserver
 * dans le ticket #24.
 *
 * @param attrs   Attributs de création de la fenêtre (titre utilisé comme id CSS).
 * @param bridge  Pont DOM utilisé pour attacher / détacher le canvas.
 *
 * @since 1.0.0
 */
package org.graphiks.kadre.web

import org.graphiks.kadre.core.PhysicalSize
import org.graphiks.kadre.core.RawDisplayHandle
import org.graphiks.kadre.core.RawWindowHandle
import org.graphiks.kadre.core.Window
import org.graphiks.kadre.core.WindowAttributes
import org.graphiks.kadre.core.WindowId

/**
 * Implémentation [Window] pour les backends web (JS et wasmJs).
 *
 * Résout le canvas via [attrs.title] comme identifiant CSS, ou crée
 * dynamiquement un canvas `"kadre-canvas"` si le titre est vide.
 *
 * [setTitle] est un no-op côté Web (les pages n'ont pas de barre de titre
 * au sens fenêtre native).
 */
class WebWindow(
    /**
     * Identifiant CSS de l'élément canvas cible.
     *
     * Doit correspondre à un `<canvas>` déjà présent dans le DOM. Utiliser
     * [WebDomBridge.ensureCanvas] (ou [WebEventLoop.createWindow] avec un
     * [WebWindowAttributes]) pour la création auto.
     */
    private val canvasElementId: String,
    private val bridge: WebDomBridge,
) : Window {

    /**
     * Construit une fenêtre Web depuis le contrat core [WindowAttributes].
     *
     * **Legacy** : utilise `attrs.title` comme `id` CSS du canvas, ou
     * `"kadre-canvas"` à défaut — convention non-idiomatique (le titre n'a
     * sémantiquement rien à voir avec un `id` DOM). Préférer
     * `WebEventLoop.createWindow(WebWindowAttributes)`.
     */
    @Deprecated(
        "Convention title-as-canvasId. Utiliser WebEventLoop.createWindow(WebWindowAttributes) " +
                "pour cibler explicitement un canvas DOM par son id.",
    )
    constructor(attrs: WindowAttributes, bridge: WebDomBridge)
            : this(attrs.title.ifEmpty { WebWindowAttributes.DEFAULT_CANVAS_ID }, bridge)


    /**
     * Identifiant unique de cette fenêtre web.
     *
     * Généré à partir du hash de [canvasElementId] pour être stable
     * et reproductible sur la même page.
     */
    override val id: WindowId = WindowId(canvasElementId.hashCode().toLong())

    /**
     * Handle brut de la surface de rendu — identifie le canvas par son id CSS.
     *
     * Retourne [RawWindowHandle.Web] avec [canvasElementId].
     * Déclaré `Any` dans [Window] pour rester indépendant de la plateforme.
     */
    override val rawWindowHandle: Any
        get() = RawWindowHandle.Web(canvasElementId = canvasElementId)

    /**
     * Handle brut de l'affichage — singleton web sans pointeur supplémentaire.
     *
     * Retourne [RawDisplayHandle.Web].
     * Déclaré `Any` dans [Window] pour rester indépendant de la plateforme.
     */
    override val rawDisplayHandle: Any
        get() = RawDisplayHandle.Web

    /**
     * Taille interne de la fenêtre (surface de rendu) en pixels physiques.
     *
     * Stub fixe à 800×600 — sera branché sur un ResizeObserver dans le ticket #24.
     */
    override val innerSize: PhysicalSize<Int>
        get() = PhysicalSize(800, 600)

    /**
     * Taille externe de la fenêtre en pixels physiques.
     *
     * Identique à [innerSize] côté Web (pas de décorations natives).
     * Stub fixe à 800×600 — sera mis à jour dans le ticket #24.
     */
    override val outerSize: PhysicalSize<Int>
        get() = PhysicalSize(800, 600)

    /**
     * Facteur d'échelle entre pixels logiques et physiques.
     *
     * Stub fixe à 1.0 — sera branché sur `window.devicePixelRatio` dans le ticket #24.
     */
    override val scaleFactor: Double
        get() = 1.0

    /**
     * Demande un redessin de la fenêtre via [WebWindowEvent.RedrawRequested].
     *
     * Émet l'événement vers [WebDomBridge.onWindowEvent] s'il est enregistré.
     */
    override fun requestRedraw() {
        bridge.onWindowEvent?.invoke(WebWindowEvent.RedrawRequested)
    }

    /**
     * No-op côté Web.
     *
     * Les pages web n'ont pas de barre de titre au sens d'une fenêtre native.
     * `document.title` pourrait être mis à jour ici mais sort du périmètre de ce ticket.
     */
    override fun setTitle(title: String) {
        // no-op Web — document.title serait la cible, hors périmètre ticket #25
    }

    /**
     * Gère la visibilité du canvas via CSS (futur) — no-op dans cette version.
     *
     * @param visible true pour afficher, false pour masquer.
     */
    override fun setVisible(visible: Boolean) {
        // no-op Web — CSS display pourrait être piloté ici (hors périmètre #25)
    }

    /**
     * Ferme la fenêtre web en détachant le pont DOM.
     *
     * Délègue à [WebDomBridge.detach] pour retirer les écouteurs DOM
     * et libérer les ressources associées.
     */
    override fun close() {
        bridge.detach()
    }
}
