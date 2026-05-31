package org.graphiks.kadre.samples.pong

import org.graphiks.kadre.EventLoop

fun main() {
    // PongGame is in commonMain, PongRenderer in jvmMain
    // The factory provides the platform-specific renderer
    EventLoop().runApp(PongGame { rawHandle ->
        PongRenderer(rawHandle)
    })
}
