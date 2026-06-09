/**
 * Entry point for kadre targeting Kotlin/Wasm (wasmJs).
 *
 * This module exposes the kadre API to WebAssembly consumers.
 * The implementation delegates to kadre-web-common (WebEventLoop, WebWindow)
 * which provides the requestAnimationFrame-based event loop and
 * DOM event handling (keyboard, mouse, touch).
 *
 * ## Usage from Kotlin/Wasm
 * ```kotlin
 * import org.graphiks.kadre.wasm.KadreWasm
 *
 * fun main() {
 *     KadreWasm.version
 * }
 * ```
 *
 * GRA-32: initial setup of the kadre-wasm module.
 */
package org.graphiks.kadre.wasm

/**
 * Singleton object exposing kadre-wasm metadata and initialization API.
 *
 * Stub — full implementation will be provided in a later ticket.
 */
object KadreWasm {

    /**
     * Version of the kadre-wasm module.
     *
     * Corresponds to the project version defined in `gradle.properties`.
     */
    val version: String = "1.0.0"
}
