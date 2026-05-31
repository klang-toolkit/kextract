/**
 * Point d'entrée Pong côté navigateur (JS/IR).
 *
 * Branche le [PongGame] commonMain sur le [PongRendererWeb] (wgpu4k Web).
 *
 * Lancement local : `./gradlew :samples:pong:jsBrowserDevelopmentRun`
 *   → page accessible à http://localhost:8080
 */
package org.graphiks.kadre.samples.pong

import org.graphiks.kadre.EventLoop

fun main() {
    println("[pong-web] Démarrage — Kadre + wgpu4k Web Pong")
    // On utilise PongAppWeb au lieu de PongGame commonMain : le backend Web
    // dispatch des WebWindowEvent.* (pas des WindowEvent.*) → PongGame
    // ne capte pas RedrawRequested et le rendu ne s'amorce jamais.
    // À unifier côté backend dans un futur ticket.
    EventLoop().runApp(PongAppWeb())
}
