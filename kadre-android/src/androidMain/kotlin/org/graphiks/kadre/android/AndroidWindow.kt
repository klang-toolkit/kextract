package org.graphiks.kadre.android

import android.view.SurfaceView
import org.graphiks.kadre.core.*

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
    override val rawWindowHandle: Any
        get() = RawWindowHandle.Android(
            surface = _surface
                ?: throw IllegalStateException(
                    "Surface Android non disponible : rawWindowHandle ne peut être " +
                    "appelé qu'après surfaceCreated et avant surfaceDestroyed. " +
                    "Attendez le callback ApplicationHandler.canCreateSurfaces."
                )
        )

    override val rawDisplayHandle: Any
        get() = RawDisplayHandle.Android

    @Volatile
    internal var needsRedraw: Boolean = false

    override fun requestRedraw() {
        needsRedraw = true
    }

    override fun setTitle(title: String) {
        // No-op: SurfaceViews have no title; the parent Activity handles the title
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
}
