package org.graphiks.kadre.samples.pong

import org.graphiks.kadre.EventLoop

fun main() {
    // PongGame est en commonMain, PongRenderer en jvmMain
    // La factory fournit le renderer spécifique à la plateforme
    EventLoop().runApp(PongGame { rawHandle ->
        PongRenderer(rawHandle)
    })
}
