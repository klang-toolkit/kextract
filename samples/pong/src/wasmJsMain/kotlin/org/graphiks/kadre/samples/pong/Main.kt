/**
 * Point d'entrée Pong côté navigateur (Kotlin/Wasm).
 *
 * Branche [PongAppWeb] (handler Web-only filtrant `WebWindowEvent.*`) sur
 * [PongRendererWeb] (wgpu4k Web). Code identique à la cible JS/IR — porté
 * sans modification car `kotlinx.browser` / `org.w3c.dom` / `js("...")`
 * fonctionnent dans les deux runtimes web.
 *
 * Lancement local : `./gradlew :samples:pong:wasmJsBrowserDevelopmentRun`
 *   → page accessible à http://localhost:8080
 */
package org.graphiks.kadre.samples.pong

import org.graphiks.kadre.EventLoop

fun main() {
    println("[pong-wasm] Démarrage — Kadre + wgpu4k Web Pong (Kotlin/Wasm)")
    // On utilise PongAppWeb au lieu de PongGame commonMain : le backend Web
    // dispatch des WebWindowEvent.* (pas des WindowEvent.*) → PongGame
    // ne capte pas RedrawRequested et le rendu ne s'amorce jamais.
    // À unifier côté backend dans un futur ticket.
    EventLoop().runApp(PongAppWeb())
}
