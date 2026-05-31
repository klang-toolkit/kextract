/**
 * Point d'entrée JVM pour hello-window (macOS via AppKit).
 *
 * Usage : ./gradlew :samples:hello-window:run
 */
package org.graphiks.kadre.samples.hellowindow

import org.graphiks.kadre.EventLoop

fun main() {
    EventLoop().runApp(HelloApp())
}
