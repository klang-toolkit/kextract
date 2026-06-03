package org.graphiks.kadre.android

import android.view.SurfaceView
import android.view.WindowManager
import org.graphiks.kadre.core.CursorGrabMode
import org.graphiks.kadre.core.CursorIcon
import org.graphiks.kadre.core.Fullscreen
import org.graphiks.kadre.core.Icon
import org.graphiks.kadre.core.InputCapabilities
import org.graphiks.kadre.core.MonitorHandle
import org.graphiks.kadre.core.PhysicalPosition
import org.graphiks.kadre.core.PhysicalSize
import org.graphiks.kadre.core.RawDisplayHandle
import org.graphiks.kadre.core.RawWindowHandle
import org.graphiks.kadre.core.RequestError
import org.graphiks.kadre.core.Theme
import org.graphiks.kadre.core.VideoMode
import org.graphiks.kadre.core.Window
import org.graphiks.kadre.core.WindowAttributes
import org.graphiks.kadre.core.WindowId
import org.graphiks.kadre.core.WindowLevel
import org.graphiks.kadre.core.WindowRequestResult

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

    override fun inputCapabilities(): InputCapabilities =
        InputCapabilities(deviceIds = true, touch = true, touchForce = true)

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
     * Android does not expose a reliable winit-style window visibility state.
     */
    override val isVisible: Boolean? get() = null

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

    /** Android does not expose a reliable minimized state. */
    override val isMinimized: Boolean? get() = null

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

    // ── R2: monitor & fullscreen ──────────────────────────────────────────────

    /**
     * Returns a synthetic monitor based on the Android display metrics.
     *
     * Uses the SurfaceView's display to read width/height/density.
     */
    override fun currentMonitor(): MonitorHandle? {
        return try {
            val dm = android.util.DisplayMetrics()
            surfaceView.display?.getRealMetrics(dm)
                ?: (surfaceView.context.getSystemService(android.content.Context.WINDOW_SERVICE)
                    as WindowManager).defaultDisplay.getRealMetrics(dm)
            object : MonitorHandle {
                override val id: Long = 0L
                override val name: String? = null
                override val position: PhysicalPosition<Int> = PhysicalPosition(0, 0)
                override val scaleFactor: Double = dm.density.toDouble()
                override val currentVideoMode: VideoMode = VideoMode(
                    PhysicalSize(dm.widthPixels, dm.heightPixels), null, null)
                override val videoModes: List<VideoMode> = listOf(currentVideoMode)
            }
        } catch (_: Throwable) { null }
    }

    /** In-memory fullscreen state (R2). */
    @Volatile private var _fullscreen: Fullscreen? = null

    override val fullscreen: Fullscreen? get() = _fullscreen

    // ── R3: cursor, theme & appearance — no-ops on Android ───────────────────

    /** No-op on Android: there is no visible cursor. */
    override fun setCursor(cursor: CursorIcon) { /* no-op: Android has no cursor */ }

    /** No-op on Android: there is no visible cursor. */
    override fun setCursorVisible(visible: Boolean) { /* no-op: Android has no cursor */ }

    /** Unsupported on Android: there is no cursor to grab. */
    override fun setCursorGrab(mode: CursorGrabMode): WindowRequestResult =
        WindowRequestResult.Failure(RequestError.Unsupported("Android has no cursor to grab"))

    /** Unsupported on Android: there is no cursor to warp. */
    override fun setCursorPosition(position: PhysicalPosition<Int>): WindowRequestResult =
        WindowRequestResult.Failure(RequestError.Unsupported("Android has no cursor to warp"))

    /** Unsupported on Android. */
    override fun setCursorHittest(hittest: Boolean): WindowRequestResult =
        WindowRequestResult.Failure(RequestError.Unsupported("Android does not support cursor hit-testing"))

    /**
     * Returns the current UI theme via UiModeManager.
     *
     * Reads [android.app.UiModeManager.nightMode]:
     * - MODE_NIGHT_YES → [Theme.Dark]
     * - MODE_NIGHT_NO  → [Theme.Light]
     * - otherwise      → null
     */
    override val theme: Theme?
        get() = try {
            val uiModeManager = surfaceView.context
                .getSystemService(android.content.Context.UI_MODE_SERVICE)
                    as? android.app.UiModeManager
            when (uiModeManager?.nightMode) {
                android.app.UiModeManager.MODE_NIGHT_YES -> Theme.Dark
                android.app.UiModeManager.MODE_NIGHT_NO  -> Theme.Light
                else -> null
            }
        } catch (_: Throwable) { null }

    /**
     * No-op on Android.
     *
     * Per-window theme override is not standard at the Window level;
     * it can be done at the Activity level via AppCompatDelegate.setDefaultNightMode().
     */
    override fun setTheme(theme: Theme?) { /* no-op: use AppCompatDelegate for Android theme */ }

    /** No-op on Android: Z-ordering is managed by the system. */
    override fun setWindowLevel(level: WindowLevel) { /* no-op: Android Z-ordering is system-managed */ }

    /** No-op on Android: transparency is managed by the renderer and Surface format. */
    override fun setTransparent(transparent: Boolean) { /* no-op: transparency requires SurfaceHolder.setFormat */ }

    /** No-op on Android: no standard blur API for SurfaceView. */
    override fun setBlur(blur: Boolean) { /* no-op: Android has no standard window blur API */ }

    /** No-op on Android: application icon is set via the manifest, not via Window. */
    override fun setWindowIcon(icon: Icon?) { /* no-op: Android icon is set via the app manifest */ }

    /**
     * Enters or exits immersive fullscreen on Android via WindowInsetsController.
     *
     * **Exclusive fullscreen is not supported on Android** — the immersive mode is
     * always borderless. [Fullscreen.Exclusive] is treated as [Fullscreen.Borderless].
     *
     * Requires API 30+ for [android.view.WindowInsetsController]. On older APIs this is
     * a documented no-op (the status/navigation bars remain visible).
     *
     * @param fullscreen New fullscreen state, or null to exit fullscreen.
     */
    override fun setFullscreen(fullscreen: Fullscreen?) {
        try {
            val activity = surfaceView.context
            if (activity is androidx.activity.ComponentActivity) {
                val window = activity.window ?: return
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {
                    val controller = window.insetsController ?: return
                    if (fullscreen != null) {
                        controller.hide(
                            android.view.WindowInsets.Type.statusBars() or
                            android.view.WindowInsets.Type.navigationBars()
                        )
                        controller.systemBarsBehavior =
                            android.view.WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
                    } else {
                        controller.show(
                            android.view.WindowInsets.Type.statusBars() or
                            android.view.WindowInsets.Type.navigationBars()
                        )
                    }
                }
                // For API < 30, fullscreen via SYSTEM_UI_FLAG_IMMERSIVE_STICKY is intentionally
                // omitted to avoid deprecation warnings — document as out of scope.
            }
        } catch (_: Throwable) {}
        _fullscreen = fullscreen
    }

    // ── R4: keyboard ──────────────────────────────────────────────────────────

    /**
     * No-op on Android: dead-key state is managed by the InputMethodManager.
     *
     * TODO(R4-android-dead-keys): call InputMethodManager.restartInput to reset IME.
     */
    override fun resetDeadKeys() {
        // no-op: Android IME state is managed by InputMethodManager
    }
}
