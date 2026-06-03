package org.graphiks.kadre.uikit

import org.graphiks.kadre.core.PhysicalSize
import org.graphiks.kadre.core.Window
import org.graphiks.kadre.core.WindowAttributes

enum class ValidOrientations {
    LandscapeAndPortrait,
    Landscape,
    Portrait,
}

enum class StatusBarStyle {
    Default,
    LightContent,
    DarkContent,
}

data class UiKitWindowAttributes(
    val core: WindowAttributes = WindowAttributes(),
    val scaleFactor: Double? = null,
    val validOrientations: ValidOrientations? = null,
    val prefersHomeIndicatorHidden: Boolean = false,
    val prefersStatusBarHidden: Boolean = false,
    val preferredStatusBarStyle: StatusBarStyle? = null,
    val recognizePinchGesture: Boolean = false,
    val recognizePanGesture: Boolean = false,
    val recognizeDoubleTapGesture: Boolean = false,
    val recognizeRotationGesture: Boolean = false,
)

private fun Window.asUiKit(): UiKitWindow =
    this as? UiKitWindow ?: throw IllegalStateException(
        "This window is not a UIKit window (${this::class.simpleName})"
    )

fun Window.contentRect(): PhysicalSize<Int> {
    val uiKit = asUiKit()
    return uiKit.contentRect()
}

fun Window.setPrefersHomeIndicatorHidden(hidden: Boolean) {
    asUiKit().setPrefersHomeIndicatorHidden(hidden)
}

fun Window.setPrefersStatusBarHidden(hidden: Boolean) {
    asUiKit().setPrefersStatusBarHidden(hidden)
}

fun Window.setPreferredStatusBarStyle(style: StatusBarStyle?) {
    asUiKit().setPreferredStatusBarStyle(style)
}
