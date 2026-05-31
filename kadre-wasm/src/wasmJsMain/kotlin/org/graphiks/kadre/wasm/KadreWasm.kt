/**
 * Point d'entrée de kadre pour la cible Kotlin/Wasm (wasmJs).
 *
 * Ce module expose l'API kadre aux consommateurs WebAssembly.
 * L'implémentation délègue vers kadre-web-common (WebEventLoop, WebWindow)
 * qui fournit la boucle d'événements basée sur requestAnimationFrame et
 * la gestion des événements DOM (clavier, souris, tactile).
 *
 * ## Usage depuis Kotlin/Wasm
 * ```kotlin
 * import org.graphiks.kadre.wasm.KadreWasm
 *
 * fun main() {
 *     KadreWasm.version
 * }
 * ```
 *
 * GRA-32 : setup initial du module kadre-wasm.
 */
package org.graphiks.kadre.wasm

/**
 * Objet singleton exposant les métadonnées et l'API d'initialisation de kadre-wasm.
 *
 * Stub — l'implémentation complète sera fournie dans un ticket ultérieur.
 */
object KadreWasm {

    /**
     * Version du module kadre-wasm.
     *
     * Correspond à la version du projet définie dans `gradle.properties`.
     */
    val version: String = "1.0.0"
}
