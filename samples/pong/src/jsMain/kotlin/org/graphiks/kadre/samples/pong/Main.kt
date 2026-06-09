/**
 * Pong entry point on the browser side (JS/IR).
 *
 * Plugs the commonMain [PongGame] onto the [PongRendererWeb] (wgpu4k Web).
 *
 * Local launch: `./gradlew :samples:pong:jsBrowserDevelopmentRun`
 *   → page accessible at http://localhost:8080
 */
package org.graphiks.kadre.samples.pong

import org.graphiks.kadre.EventLoop

fun main() {
    println("[pong-web] Starting — Kadre + wgpu4k Web Pong")
    // We use PongAppWeb instead of commonMain PongGame: the Web backend
    // dispatches WebWindowEvent.* (not WindowEvent.*) → PongGame
    // does not catch RedrawRequested and rendering never starts.
    // To be unified on the backend side in a future ticket.
    EventLoop().runApp(PongAppWeb())
}
