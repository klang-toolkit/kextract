package org.graphiks.kadre.samples.pong

import org.graphiks.kadre.EventLoop

fun main() {
    // iOS: renderer stub (iOS support to be implemented #78)
    EventLoop().runApp(PongGame { _ ->
        object : PongRendererInterface {
            override fun draw(state: GameState) {}
            override fun resize(width: Int, height: Int) {}
            override fun release() {}
        }
    })
}
