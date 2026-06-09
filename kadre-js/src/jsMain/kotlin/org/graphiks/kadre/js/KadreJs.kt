/**
 * Entry point for kadre targeting Kotlin/JS.
 *
 * This module exposes the kadre API to JavaScript/TypeScript consumers.
 * The implementation delegates to kadre-web-common (WebEventLoop, WebWindow)
 * which provides the requestAnimationFrame-based event loop and
 * DOM event handling (keyboard, mouse, touch).
 *
 * ## Usage from JavaScript
 * ```javascript
 * import { KadreJs } from 'kadre-js'
 * KadreJs.version // "1.0.0"
 * ```
 *
 * ## Usage from Kotlin/JS
 * ```kotlin
 * import org.graphiks.kadre.js.KadreJs
 *
 * fun main() {
 *     KadreJs.version
 * }
 * ```
 *
 * GRA-30: initial setup of the kadre-js module.
 */
@file:OptIn(kotlin.js.ExperimentalJsExport::class)

package org.graphiks.kadre.js

/**
 * Singleton object exposing kadre-js metadata and initialization API.
 *
 * Stub — full implementation will be provided in a later ticket.
 */
@JsExport
object KadreJs {

    /**
     * Version of the kadre-js module.
     *
     * Corresponds to the project version defined in `gradle.properties`.
     */
    val version: String = "1.0.0"
}
