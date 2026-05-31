/**
 * Pont DOM partagé entre les cibles JS et wasmJs.
 *
 * Cette interface définit le contrat d'attachement d'un rendu Kadre à un
 * élément HTML. Les implémentations concrètes résident dans jsMain et wasmJsMain
 * afin de pouvoir utiliser les API DOM propres à chaque cible.
 *
 * ## Contrainte
 * Ce fichier est dans webMain — AUCUN import DOM n'est autorisé ici.
 * Seuls les types Kotlin purs (String, Lambda, types locaux) sont permis.
 *
 * ## Note sur kadre-core
 * La dépendance sur kadre-core (WindowEvent, Key, etc.) sera activée
 * quand kadre-core exposera des cibles JS/wasmJs (ticket #32).
 * En attendant, [WindowEvent] est défini localement dans ce module.
 *
 * @since 1.0.0
 */
package org.graphiks.kadre.web

/**
 * Interface de liaison entre le DOM du navigateur et le moteur Kadre.
 *
 * Implémentée séparément pour JS (via [org.w3c.dom]) et wasmJs (via interop JS Wasm).
 */
interface WebDomBridge {

    /**
     * Attache le rendu Kadre à l'élément HTML identifié par [targetElementId].
     *
     * @param targetElementId Valeur de l'attribut `id` de l'élément cible dans le DOM.
     */
    fun attach(targetElementId: String)

    /**
     * Détache le rendu Kadre de l'élément DOM et libère les ressources associées.
     */
    fun detach()

    /**
     * Callback invoqué à chaque événement de fenêtre produit par le pont DOM.
     *
     * Peut être null si aucun écouteur n'est enregistré.
     */
    var onWindowEvent: ((WebWindowEvent) -> Unit)?

    /**
     * Garantit qu'un canvas DOM correspondant à [attrs] est présent et l'ajoute
     * au DOM si nécessaire (mode `appendToBody`).
     *
     * Implémentation par défaut : no-op (retourne l'id sans toucher au DOM).
     * Les implémentations concrètes ([JsWebDomBridge], [WasmJsWebDomBridge])
     * surchargent pour créer un `<canvas>` réel quand demandé.
     *
     * @return l'identifiant CSS final du canvas (à passer ensuite à [attach]).
     */
    fun ensureCanvas(attrs: WebWindowAttributes): String = attrs.effectiveCanvasId
}
