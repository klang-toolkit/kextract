/**
 * iOS entry point for hello-window.
 *
 * Called by the Kadre framework at iOS application startup.
 */
package org.graphiks.kadre.samples.hellowindow

import org.graphiks.kadre.EventLoop

fun main() {
    EventLoop().runApp(HelloApp())
}
