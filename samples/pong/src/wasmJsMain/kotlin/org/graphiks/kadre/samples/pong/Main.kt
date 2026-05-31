/**
 * Pong entry point on the browser side (Kotlin/Wasm).
 *
 * Plugs [PongAppWeb] (Web-only handler filtering `WebWindowEvent.*`) onto
 * [PongRendererWeb] (wgpu4k Web). Code identical to the JS/IR target — ported
 * without modification because `kotlinx.browser` / `org.w3c.dom` / `js("...")`
 * work in both web runtimes.
 *
 * Local launch: `./gradlew :samples:pong:wasmJsBrowserDevelopmentRun`
 *   → page accessible at http://localhost:8080
 */
package org.graphiks.kadre.samples.pong

import org.graphiks.kadre.EventLoop

fun main() {
    println("[pong-wasm] Démarrage — Kadre + wgpu4k Web Pong (Kotlin/Wasm)")
    // We use PongAppWeb instead of commonMain PongGame: the Web backend
    // dispatches WebWindowEvent.* (not WindowEvent.*) → PongGame
    // does not catch RedrawRequested and rendering never starts.
    // To be unified on the backend side in a future ticket.
    EventLoop().runApp(PongAppWeb())
}
