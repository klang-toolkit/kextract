package org.graphiks.kadre.android

import android.content.res.Configuration
import android.graphics.Rect
import androidx.activity.ComponentActivity
import org.graphiks.kadre.core.ActiveEventLoop
import org.graphiks.kadre.core.Window
import org.graphiks.kadre.core.WindowAttributes

/**
 * Android-specific window creation attributes.
 *
 * Mirrors winit's `WindowExtAndroid` and `WindowAttributesExtAndroid`.
 *
 * @property core Core cross-platform window attributes.
 * @property handleVolumeKeys Whether the window should handle volume key presses
 *   instead of letting the OS handle them (default false).
 */
data class AndroidWindowAttributes(
    val core: WindowAttributes = WindowAttributes(),
    val handleVolumeKeys: Boolean = false,
)

/**
 * Casts this [Window] to [AndroidWindow] or throws if the window is not an Android window.
 */
private fun Window.asAndroid(): AndroidWindow =
    this as? AndroidWindow ?: throw IllegalStateException(
        "This window is not an Android window (${this::class.simpleName})"
    )

/**
 * Returns the Android content [Rect] — the visible portion of the window
 * excluding system decoration areas (status bar, navigation bar).
 *
 * Equivalent to winit's `WindowExtAndroid::contentRect`.
 */
fun Window.contentRect(): Rect {
    return asAndroid().contentRect()
}

/**
 * Returns the current Android [Configuration] for this window's context.
 *
 * Provides access to the device's runtime configuration (orientation,
 * night mode, screen layout, etc.).
 */
fun Window.config(): Configuration {
    return asAndroid().config()
}

/**
 * Returns the Android [ComponentActivity] backing this event loop.
 *
 * Equivalent to winit's `EventLoopExtAndroid::android_app`.
 *
 * @throws IllegalStateException if the event loop is not an [AndroidEventLoop].
 */
fun ActiveEventLoop.androidApp(): ComponentActivity {
    return (this as? AndroidEventLoop)?.activity
        ?: throw IllegalStateException(
            "This event loop is not an Android event loop (${this::class.simpleName})"
        )
}
