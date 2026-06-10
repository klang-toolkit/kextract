package org.graphiks.kadre.samples.compose.web

import androidx.compose.ui.window.CanvasBasedWindow
import org.graphiks.kadre.samples.compose.showcase.PlatformContext
import org.graphiks.kadre.samples.compose.showcase.ShowcaseApp

fun main() {
    CanvasBasedWindow("Compose Showcase") { ShowcaseApp(PlatformContext()) }
}
