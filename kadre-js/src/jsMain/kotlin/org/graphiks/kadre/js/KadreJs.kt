/**
 * Point d'entrée de kadre pour la cible Kotlin/JS.
 *
 * Ce module expose l'API kadre aux consommateurs JavaScript/TypeScript.
 * L'implémentation délègue vers kadre-web-common (WebEventLoop, WebWindow)
 * qui fournit la boucle d'événements basée sur requestAnimationFrame et
 * la gestion des événements DOM (clavier, souris, tactile).
 *
 * ## Usage depuis JavaScript
 * ```javascript
 * import { KadreJs } from 'kadre-js'
 * KadreJs.version // "1.0.0"
 * ```
 *
 * ## Usage depuis Kotlin/JS
 * ```kotlin
 * import org.graphiks.kadre.js.KadreJs
 *
 * fun main() {
 *     KadreJs.version
 * }
 * ```
 *
 * GRA-30 : setup initial du module kadre-js.
 */
@file:OptIn(kotlin.js.ExperimentalJsExport::class)

package org.graphiks.kadre.js

/**
 * Objet singleton exposant les métadonnées et l'API d'initialisation de kadre-js.
 *
 * Stub — l'implémentation complète sera fournie dans un ticket ultérieur.
 */
@JsExport
object KadreJs {

    /**
     * Version du module kadre-js.
     *
     * Correspond à la version du projet définie dans `gradle.properties`.
     */
    val version: String = "1.0.0"
}
