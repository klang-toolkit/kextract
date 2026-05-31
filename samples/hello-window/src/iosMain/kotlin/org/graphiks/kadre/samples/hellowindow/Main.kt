/**
 * Point d'entrée iOS pour hello-window.
 *
 * Appelé par le framework Kadre au démarrage de l'application iOS.
 */
package org.graphiks.kadre.samples.hellowindow

import org.graphiks.kadre.EventLoop

fun main() {
    EventLoop().runApp(HelloApp())
}
