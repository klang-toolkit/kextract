package org.graphiks.kadre.android

import android.view.SurfaceView
import org.graphiks.kadre.core.PhysicalPosition
import org.graphiks.kadre.core.PhysicalSize
import org.graphiks.kadre.core.RawDisplayHandle
import org.graphiks.kadre.core.RawWindowHandle
import org.graphiks.kadre.core.Window
import org.graphiks.kadre.core.WindowAttributes
import org.graphiks.kadre.core.WindowId

/**
 * Android implementation of [Window].
 *
 * Wraps a full-screen [SurfaceView] and exposes the raw [android.view.Surface]
 * via [rawWindowHandle] (Strategy A — zero JNI).
 *
 * ## Surface lifecycle
 *
 * An [AndroidWindow] may be created **before** the Android surface is
 * available ("pending window" pattern — see [AndroidEventLoop.createWindow]).
 * The availability cycle is:
 *
 * | Event | Surface state | [rawWindowHandle] |
 * |-----------|--------------|-------------------|
 * | After [AndroidEventLoop.createWindow] | Unavailable | Throws [IllegalStateException] |
 * | After [onSurfaceAvailable] | Available | Returns valid [RawWindowHandle.Android] |
 * | After [onSurfaceReleased] | Unavailable | Throws [IllegalStateException] |
 *
 * Renderers (wgpu4k, etc.) must only access [rawWindowHandle] within
 * or after the [org.graphiks.kadre.core.ApplicationHandler.canCreateSurfaces] callback,
 * and must release the handle before [org.graphiks.kadre.core.ApplicationHandler.destroySurfaces].
 */
class AndroidWindow internal constructor(
    internal val surfaceView: SurfaceView,
) : Window {

    override val id: WindowId = WindowId(surfaceView.hashCode().toLong())

    @Volatile
    private var _surface: android.view.Surface? = null

    /**
     * Makes the surface available for rendering.
     *
     * Called by [AndroidEventLoop.onSurfaceCreated] (which is itself triggered
     * by [KadreActivity]) on `surfaceCreated`. After this call,
     * [rawWindowHandle] returns a valid [RawWindowHandle.Android].
     *
     * @param surface The Android surface freshly allocated by the SurfaceHolder.
     */
    internal fun onSurfaceAvailable(surface: android.view.Surface) {
        _surface = surface
    }

    /**
     * Invalidates the rendering surface.
     *
     * Called by [AndroidEventLoop.onSurfaceDestroyed] (which is itself triggered
     * by [KadreActivity]) on `surfaceDestroyed`. After this call,
     * [rawWindowHandle] throws [IllegalStateException] until the next
     * invocation of [onSurfaceAvailable].
     */
    internal fun onSurfaceReleased() {
        _surface = null
    }

    /**
     * Returns the native handle of the Android surface.
     *
     * @return [RawWindowHandle.Android] wrapping the active [android.view.Surface].
     * @throws IllegalStateException if the surface is not yet available
     *   (before [onSurfaceAvailable]) or has been released (after [onSurfaceReleased]).
     */
    override val rawWindowHandle: RawWindowHandle
        get() = RawWindowHandle.Android(
            surface = _surface
                ?: throw IllegalStateException(
                    "Surface Android non disponible : rawWindowHandle ne peut être " +
                    "appelé qu'après surfaceCreated et avant surfaceDestroyed. " +
                    "Attendez le callback ApplicationHandler.canCreateSurfaces."
                )
        )

    override val rawDisplayHandle: RawDisplayHandle
        get() = RawDisplayHandle.Android

    @Volatile
    internal var needsRedraw: Boolean = false

    override fun requestRedraw() {
        needsRedraw = true
    }

    override val innerSize: PhysicalSize<Int>
        get() = PhysicalSize(surfaceView.width, surfaceView.height)

    override val outerSize: PhysicalSize<Int>
        get() = PhysicalSize(surfaceView.width, surfaceView.height)

    override val scaleFactor: Double
        get() = surfaceView.resources.displayMetrics.density.toDouble()

    override fun setVisible(visible: Boolean) {
        surfaceView.visibility = if (visible) android.view.View.VISIBLE else android.view.View.GONE
    }

    override fun close() {
        // No-op at the library level; closing is up to the app
    }

    // ── R1: window state & geometry — no-ops on Android ───────────────────────
    //
    // Android does not support programmatic window resizing, minimization,
    // maximization, or decoration changes. The Activity lifecycle and the system
    // UI control these aspects. All members below are documented no-ops.

    /** Android windows always have the full-screen Activity title; tracked for getter parity. */
    @Volatile private var _title: String = ""

    /**
     * Sets the title. On Android this is a no-op at the window level; the Activity
     * title bar is managed via Activity.setTitle() outside kadre's scope.
     */
    override fun setTitle(title: String) { _title = title }

    override val title: String get() = _title

    /**
     * Android windows are always visible while the Activity is in the foreground.
     * Returns true; calling [setVisible] has no effect.
     */
    override val isVisible: Boolean get() = surfaceView.visibility == android.view.View.VISIBLE

    /**
     * Android does not support programmatic resizing.
     * This is a no-op — the system controls the window geometry.
     */
    override fun setResizable(resizable: Boolean) { /* no-op: Android does not support programmatic resizing */ }

    /** Android windows are not user-resizable. Always returns false. */
    override val isResizable: Boolean get() = false

    /**
     * Android does not support programmatic minimization.
     * This is a no-op — use Activity.moveTaskToBack() if needed.
     */
    override fun setMinimized(minimized: Boolean) { /* no-op: Android does not support programmatic minimization */ }

    /** Android does not expose an isMinimized state. Always returns false. */
    override val isMinimized: Boolean get() = false

    /**
     * Android does not support programmatic maximization.
     * This is a no-op — the window always fills the available screen area.
     */
    override fun setMaximized(maximized: Boolean) { /* no-op: Android windows always fill the screen */ }

    /** Android windows always fill the screen. Always returns false (not a maximize concept). */
    override val isMaximized: Boolean get() = false

    /**
     * Android does not support platform window decorations in the traditional sense.
     * This is a no-op — the system UI (status bar, navigation bar) is controlled by the Activity.
     */
    override fun setDecorations(decorated: Boolean) { /* no-op: Android decorations are managed by the system UI */ }

    /** Android windows have no platform decorations (title bar / resize borders). Always returns false. */
    override val isDecorated: Boolean get() = false

    /**
     * Android does not support surface size constraints.
     * This is a no-op — the surface size is determined by the screen and Activity layout.
     */
    override fun setMinSurfaceSize(size: PhysicalSize<Int>?) { /* no-op: Android does not support surface size constraints */ }

    /**
     * Android does not support surface size constraints.
     * This is a no-op — the surface size is determined by the screen and Activity layout.
     */
    override fun setMaxSurfaceSize(size: PhysicalSize<Int>?) { /* no-op: Android does not support surface size constraints */ }

    /**
     * Android does not expose a global window position.
     * Returns PhysicalPosition(0, 0) as the window always fills the screen.
     */
    override val outerPosition: PhysicalPosition<Int> get() = PhysicalPosition(0, 0)

    /**
     * Android does not support programmatic window positioning.
     * This is a no-op — the window always fills the Activity area.
     */
    override fun setOuterPosition(position: PhysicalPosition<Int>) { /* no-op: Android does not support programmatic window positioning */ }

    /**
     * No-op on Android: there is no Wayland-style pre-commit concept on this platform.
     */
    override fun prePresentNotify() { /* no-op on Android */ }
}
