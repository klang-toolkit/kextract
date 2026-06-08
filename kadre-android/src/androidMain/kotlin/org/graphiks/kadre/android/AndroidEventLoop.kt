package org.graphiks.kadre.android

import android.view.Choreographer
import androidx.activity.ComponentActivity
import org.graphiks.kadre.core.ActiveEventLoop
import org.graphiks.kadre.core.ControlFlow
import org.graphiks.kadre.core.DeviceEvents
import org.graphiks.kadre.core.EventLoopProxy
import org.graphiks.kadre.core.MonitorHandle
import org.graphiks.kadre.core.PhysicalPosition
import org.graphiks.kadre.core.PhysicalSize
import org.graphiks.kadre.core.Theme
import org.graphiks.kadre.core.VideoMode
import org.graphiks.kadre.core.Window
import org.graphiks.kadre.core.WindowAttributes
import org.graphiks.kadre.core.WindowEvent

/**
 * Android implementation of [ActiveEventLoop].
 *
 * ## Window lifecycle — "pending window" pattern
 *
 * On Android, the [android.view.Surface] is only available after the
 * [android.view.SurfaceHolder.Callback.surfaceCreated] callback; it can be released
 * and recreated (e.g. rotation, onPause/onResume). The "pending window" pattern
 * decouples the creation of the [AndroidWindow] object from the surface availability:
 *
 * 1. **[createWindow]** — called from [org.graphiks.kadre.core.ApplicationHandler.canCreateSurfaces]:
 *    immediately creates an [AndroidWindow] bound to the Activity's [SurfaceView] **before**
 *    the surface is available. The window is stored in [pendingWindow].
 *
 * 2. **[onSurfaceCreated]** — called by [KadreActivity] on `surfaceCreated`:
 *    transfers the surface to the [AndroidWindow] via [AndroidWindow.onSurfaceAvailable].
 *    From that point on, [AndroidWindow.rawWindowHandle] is valid.
 *
 * 3. **[onSurfaceDestroyed]** — called by [KadreActivity] on `surfaceDestroyed`:
 *    invalidates the surface via [AndroidWindow.onSurfaceReleased].
 *
 * ## Timing contract for [AndroidWindow.rawWindowHandle]
 *
 * [AndroidWindow.rawWindowHandle] throws [IllegalStateException] if the surface is not
 * yet available (between [createWindow] and [onSurfaceCreated]). Renderers must
 * only access the handle within or after [ApplicationHandler.canCreateSurfaces].
 *
 * ## Frame scheduling
 *
 * Frame timing is handled by [Choreographer]: [scheduleFrameIfNeeded]
 * schedules a vsync callback that dispatches [WindowEvent.RedrawRequested]
 * if [AndroidWindow.needsRedraw] is set, then [org.graphiks.kadre.core.ApplicationHandler.aboutToWait].
 * In [ControlFlow.Poll] mode, the callback reschedules itself automatically.
 *
 * [exit] terminates the parent Activity via [ComponentActivity.finish].
 */
internal class AndroidEventLoop(
    internal val activity: ComponentActivity,
) : ActiveEventLoop {

    @Volatile
    override var controlFlow: ControlFlow = ControlFlow.Wait
        private set

    private val choreographer = Choreographer.getInstance()

    @Volatile
    private var frameCallbackScheduled = false

    /**
     * Window created via [createWindow] and awaiting a surface.
     *
     * Null before the first call to [createWindow], non-null afterwards.
     * The surface itself is only available after [onSurfaceCreated].
     */
    @Volatile
    internal var pendingWindow: AndroidWindow? = null
        private set

    /**
     * Creates an [AndroidWindow] bound to the Activity's [SurfaceView].
     *
     * Immediately returns a valid [AndroidWindow] **before** the
     * [android.view.Surface] is available ("pending window" pattern).
     * [AndroidWindow.rawWindowHandle] is only accessible after [onSurfaceCreated].
     *
     * May be called multiple times: each call replaces the [pendingWindow]
     * reference (rare case — a single window per Activity is the norm).
     *
     * @param attributes Window attributes (title, size, etc.).
     *                   On Android, title and resizing are ignored.
     * @return An [AndroidWindow] whose surface will be available after [onSurfaceCreated].
     */
    override fun createWindow(attributes: WindowAttributes): Window {
        val kadreActivity = activity as KadreActivity
        val window = AndroidWindow(kadreActivity.surfaceView, this, kadreActivity)
        pendingWindow = window
        return window
    }

    /**
     * Creates a window with Android-specific attributes.
     *
     * Merges [AndroidWindowAttributes] fields into the core [WindowAttributes]
     * and applies platform-specific settings at creation time.
     */
    internal fun createWindow(attrs: AndroidWindowAttributes): Window {
        val window = createWindow(attrs.core) as AndroidWindow
        window.handleVolumeKeys = attrs.handleVolumeKeys
        return window
    }

    /**
     * Transfers the [android.view.Surface] to the pending window.
     *
     * Called by [KadreActivity] on `surfaceCreated`. After this call,
     * [AndroidWindow.rawWindowHandle] returns a valid
     * [org.graphiks.kadre.core.RawWindowHandle.Android]. If no window has yet been
     * created via [createWindow], the [holder] is ignored (the surface will be
     * provided on the next [createWindow]).
     *
     * @param surface The Android surface freshly created by the SurfaceHolder.
     */
    internal fun onSurfaceCreated(surface: android.view.Surface) {
        pendingWindow?.onSurfaceAvailable(surface)
    }

    /**
     * Invalidates the active window's surface.
     *
     * Called by [KadreActivity] on `surfaceDestroyed`. After this call,
     * [AndroidWindow.rawWindowHandle] throws [IllegalStateException] until the
     * next invocation of [onSurfaceCreated].
     */
    internal fun onSurfaceDestroyed() {
        pendingWindow?.onSurfaceReleased()
    }

    override fun setControlFlow(controlFlow: ControlFlow) {
        this.controlFlow = controlFlow
    }

    override fun exit() {
        activity.finish()
    }

    override val isExiting: Boolean
        get() = activity.isFinishing

    override fun createProxy(): EventLoopProxy = object : EventLoopProxy {
        override fun wakeUp() {
            // No-op: Android manages its own scheduling via the Looper/Handler
        }
    }

    // ── R2: monitor enumeration ───────────────────────────────────────────────

    /**
     * Returns a synthetic monitor based on the Android display metrics.
     *
     * Android exposes the physical screen size via DisplayMetrics. We use the
     * window manager's default display to build a single synthetic MonitorHandle.
     */
    override fun availableMonitors(): List<MonitorHandle> {
        return try {
            val dm = android.util.DisplayMetrics()
            (activity.getSystemService(android.content.Context.WINDOW_SERVICE)
                as android.view.WindowManager).defaultDisplay.getRealMetrics(dm)
            listOf(object : MonitorHandle {
                override val id: Long = 0L
                override val name: String? = null
                override val position: PhysicalPosition<Int> = PhysicalPosition(0, 0)
                override val scaleFactor: Double = dm.density.toDouble()
                override val currentVideoMode: VideoMode = VideoMode(
                    PhysicalSize(dm.widthPixels, dm.heightPixels), null,
                    dm.xdpi.toInt().let { if (it > 0) it else null }
                )
                override val videoModes: List<VideoMode> = listOf(currentVideoMode)
            })
        } catch (_: Throwable) { emptyList() }
    }

    /**
     * Returns the primary monitor (the single Android screen).
     */
    override fun primaryMonitor(): MonitorHandle? = availableMonitors().firstOrNull()

    // ── R3: system theme ──────────────────────────────────────────────────────

    /**
     * Returns the current system UI theme via UiModeManager.nightMode.
     *
     * - MODE_NIGHT_YES → [Theme.Dark]
     * - MODE_NIGHT_NO  → [Theme.Light]
     * - otherwise      → null (FOLLOW_SYSTEM or unknown)
     */
    override fun systemTheme(): Theme? = try {
        val uiModeManager = activity
            .getSystemService(android.content.Context.UI_MODE_SERVICE)
                as? android.app.UiModeManager
        when (uiModeManager?.nightMode) {
            android.app.UiModeManager.MODE_NIGHT_YES -> Theme.Dark
            android.app.UiModeManager.MODE_NIGHT_NO  -> Theme.Light
            else -> null
        }
    } catch (_: Throwable) { null }

    // ── R4: device event filter ───────────────────────────────────────────────

    /**
     * No-op on Android: raw device events are not dispatched at the Android level.
     */
    override fun listenDeviceEvents(mode: DeviceEvents) {
        // no-op on Android
    }

    /**
     * Schedules the next vsync callback if not already scheduled.
     * Must be called from the main thread.
     */
    internal fun scheduleFrameIfNeeded(window: AndroidWindow) {
        if (!frameCallbackScheduled) {
            frameCallbackScheduled = true
            choreographer.postFrameCallback { frameTimeNanos ->
                frameCallbackScheduled = false
                onFrame(frameTimeNanos, window)
            }
        }
    }

    private fun onFrame(frameTimeNanos: Long, window: AndroidWindow) {
        if (activity.isDestroyed) return
        val kadreActivity = activity as KadreActivity
        if (kadreActivity.destroyed) return

        // Dispatch RedrawRequested if needsRedraw is set
        if (window.needsRedraw) {
            window.needsRedraw = false
            kadreActivity.handler.windowEvent(this, window.id, WindowEvent.RedrawRequested)
        }

        // Dispatch aboutToWait
        kadreActivity.handler.aboutToWait(this)

        // Reschedule if in Poll mode or if needsRedraw was set again
        if (controlFlow == ControlFlow.Poll || window.needsRedraw) {
            scheduleFrameIfNeeded(window)
        }
    }
}
