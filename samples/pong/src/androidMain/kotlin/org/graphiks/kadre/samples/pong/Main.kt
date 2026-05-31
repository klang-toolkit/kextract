package org.graphiks.kadre.samples.pong

import org.graphiks.kadre.EventLoop

fun main() {
    // Android: renderer stub (Android support to be implemented)
    EventLoop().runApp(PongGame { _ ->
        object : PongRendererInterface {
            override fun draw(state: GameState) {}
            override fun resize(width: Int, height: Int) {}
            override fun release() {}
        }
    })
}
