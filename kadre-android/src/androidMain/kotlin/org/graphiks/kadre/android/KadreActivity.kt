package org.graphiks.kadre.android

import android.content.res.Configuration
import android.os.Bundle
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.view.View
import android.view.WindowManager
import androidx.activity.ComponentActivity
import org.graphiks.kadre.core.ApplicationHandler
import org.graphiks.kadre.core.ButtonSource
import org.graphiks.kadre.core.DeviceId
import org.graphiks.kadre.core.FingerId
import org.graphiks.kadre.core.KeyPlatform
import org.graphiks.kadre.core.KeyState
import org.graphiks.kadre.core.NativeKeyInfo
import org.graphiks.kadre.core.NativeKeyCode
import org.graphiks.kadre.core.NativeLogicalKey
import org.graphiks.kadre.core.PhysicalPosition
import org.graphiks.kadre.core.PhysicalSize
import org.graphiks.kadre.core.PointerKind
import org.graphiks.kadre.core.PointerSource
import org.graphiks.kadre.core.TouchForce
import org.graphiks.kadre.core.TouchPhase
import org.graphiks.kadre.core.WindowEvent
import org.graphiks.kadre.core.defaultLogicalKey
import org.graphiks.kadre.core.defaultText
import org.graphiks.kadre.core.KeyEvent as KadreKeyEvent

/**
 * Root Kadre Activity for Android.
 *
 * Hosts a full-screen [SurfaceView] whose [android.view.Surface] is
 * exposed via [AndroidWindow.rawWindowHandle] → [RawWindowHandle.Android].
 *
 * ## Usage
 * Subclass and implement [createHandler]:
 * ```kotlin
 * class MyActivity : KadreActivity() {
 *     override fun createHandler() = MyApplicationHandler()
 * }
 * ```
 *
 * ## Lifecycle → Kadre callbacks
 * - [onResume]  → [ApplicationHandler.resumed]
 * - [onPause]   → [ApplicationHandler.suspended]
 * - Surface created   → [ApplicationHandler.canCreateSurfaces]
 * - Surface changed → [ApplicationHandler.windowEvent] ([WindowEvent.Resized])
 * - Surface destroyed → [ApplicationHandler.destroySurfaces]
 * - [onDestroy] → [WindowEvent.Destroyed], then `destroyed` guard set + cleanup
 *
 * ## Lifecycle vs WindowEvent (two parallel channels)
 * [onResume] / [onPause] / surface-destroyed drive the **app-level**
 * [ApplicationHandler] lifecycle (coarse, activity-scoped). Separately,
 * [onWindowFocusChanged] emits the **per-window** [WindowEvent.Focused] and
 * [onDestroy] emits [WindowEvent.Destroyed], for parity with the desktop/winit
 * backends so that consumers switching on [WindowEvent] also observe focus and
 * destruction. Focus (window-level) and resume/pause (activity-level) are
 * distinct signals on Android, so they are reported independently.
 *
 * ## Full screen
 * Status bar and navigation bar hidden via `FLAG_FULLSCREEN` and cutout-aware layout.
 */
abstract class KadreActivity : ComponentActivity() {

    /**
     * Creates the Kadre application handler.
     * Called from [onCreate] before any surface initialization.
     */
    abstract fun createHandler(): ApplicationHandler

    /** Handler instantiated in [onCreate]. */
    lateinit var handler: ApplicationHandler
        private set

    /**
     * Kadre Android window.
     *
     * Initialized on the first call to [AndroidEventLoop.createWindow] (typically
     * in [ApplicationHandler.canCreateSurfaces] via [surfaceCreated]).
     * Delegates surface management to [AndroidEventLoop].
     */
    val kadreWindow: AndroidWindow?
        get() = eventLoop.pendingWindow

    /** Android event loop. */
    internal lateinit var eventLoop: AndroidEventLoop

    /**
     * Full-screen SurfaceView hosting the rendering surface.
     *
     * Exposed as `internal` so that [AndroidEventLoop.createWindow] can
     * instantiate [AndroidWindow] with this Activity's SurfaceView.
     */
    internal lateinit var surfaceView: SurfaceView

    /**
     * Guard against any callback dispatch after [onDestroy].
     * Set to `true` at the start of [onDestroy], before any cleanup.
     * Exposed as `internal` so that [AndroidEventLoop.onFrame] can read it.
     */
    @Volatile
    internal var destroyed = false

    /**
     * Last dispatched scale factor (display density), to emit
     * [WindowEvent.ScaleFactorChanged] only on an actual change.
     * Initialized in [onCreate] from the current display density.
     */
    private var lastScaleFactor: Double = 1.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ── Full screen ────────────────────────────────────────────────────────
        @Suppress("DEPRECATION")
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        // Cutout-aware (API 28+) — flag applied without a direct import for compatibility
        if (android.os.Build.VERSION.SDK_INT >= 28) {
            window.attributes.layoutInDisplayCutoutMode =
                WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
        }

        // ── Full-screen SurfaceView ────────────────────────────────────────────
        surfaceView = SurfaceView(this).apply {
            layoutParams = android.view.ViewGroup.LayoutParams(
                android.view.ViewGroup.LayoutParams.MATCH_PARENT,
                android.view.ViewGroup.LayoutParams.MATCH_PARENT,
            )
            @Suppress("DEPRECATION")
            systemUiVisibility = (
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                View.SYSTEM_UI_FLAG_FULLSCREEN or
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
            )
        }
        setContentView(surfaceView)

        // ── Handler + EventLoop ────────────────────────────────────────────────
        handler = createHandler()
        eventLoop = AndroidEventLoop(this)
        lastScaleFactor = resources.displayMetrics.density.toDouble()

        // ── SurfaceHolder callbacks (surface lifecycle) ────────────────────────
        surfaceView.holder.addCallback(object : SurfaceHolder.Callback {
            override fun surfaceCreated(holder: SurfaceHolder) {
                if (destroyed) return
                println("[KadreActivity] surfaceCreated → surface available")
                // canCreateSurfaces triggers createWindow in the handler,
                // which creates the AndroidWindow via AndroidEventLoop.createWindow.
                // Then we transfer the surface to the created window.
                handler.canCreateSurfaces(eventLoop)
                eventLoop.onSurfaceCreated(holder.surface)
                eventLoop.pendingWindow?.let { eventLoop.scheduleFrameIfNeeded(it) }
            }

            override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
                if (destroyed) return
                println("[KadreActivity] surfaceChanged ${width}×${height}")
                eventLoop.pendingWindow?.let { window ->
                    handler.windowEvent(
                        eventLoop,
                        window.id,
                        WindowEvent.Resized(PhysicalSize(width, height)),
                    )
                }
            }

            override fun surfaceDestroyed(holder: SurfaceHolder) {
                if (destroyed) return
                println("[KadreActivity] surfaceDestroyed → surface released")
                handler.destroySurfaces(eventLoop)
                eventLoop.onSurfaceDestroyed()
            }
        })
    }

    override fun onResume() {
        super.onResume()
        if (destroyed) return
        handler.resumed(eventLoop)
        eventLoop.pendingWindow?.let { eventLoop.scheduleFrameIfNeeded(it) }
    }

    override fun onPause() {
        super.onPause()
        if (destroyed) return
        handler.suspended(eventLoop)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val window = eventLoop.pendingWindow
        if (destroyed || window == null) return super.onTouchEvent(event)
        dispatchMotionEvent(event, window)
        return true
    }

    private fun dispatchMotionEvent(event: MotionEvent, window: AndroidWindow) {
        val phase = when (event.actionMasked) {
            MotionEvent.ACTION_DOWN, MotionEvent.ACTION_POINTER_DOWN -> TouchPhase.Started
            MotionEvent.ACTION_MOVE -> TouchPhase.Moved
            MotionEvent.ACTION_UP, MotionEvent.ACTION_POINTER_UP -> TouchPhase.Ended
            MotionEvent.ACTION_CANCEL -> TouchPhase.Cancelled
            else -> return
        }

        if (event.actionMasked == MotionEvent.ACTION_MOVE || event.actionMasked == MotionEvent.ACTION_CANCEL) {
            for (pointerIndex in 0 until event.pointerCount) {
                dispatchTouchPointer(event, window, pointerIndex, phase)
            }
        } else {
            dispatchTouchPointer(event, window, event.actionIndex, phase)
        }
    }

    private fun dispatchTouchPointer(
        event: MotionEvent,
        window: AndroidWindow,
        pointerIndex: Int,
        phase: TouchPhase,
    ) {
        val location = PhysicalPosition(
            event.getX(pointerIndex).toDouble(),
            event.getY(pointerIndex).toDouble(),
        )
        val deviceId = DeviceId(event.deviceId.toLong())
        val fingerId = FingerId(event.getPointerId(pointerIndex).toLong())
        val primary = pointerIndex == 0
        val force = TouchForce.Normalized(event.getPressure(pointerIndex).toDouble())

        when (phase) {
            TouchPhase.Started -> {
                handler.windowEvent(
                    eventLoop,
                    window.id,
                    WindowEvent.PointerEntered(deviceId, location, primary, PointerKind.Touch),
                )
                handler.windowEvent(
                    eventLoop,
                    window.id,
                    WindowEvent.PointerButton(deviceId, KeyState.Pressed, location, primary, ButtonSource.Touch(fingerId, force)),
                )
            }
            TouchPhase.Moved -> handler.windowEvent(
                eventLoop,
                window.id,
                WindowEvent.PointerMoved(deviceId, location, primary, PointerSource.Touch(fingerId, force)),
            )
            TouchPhase.Ended -> {
                handler.windowEvent(
                    eventLoop,
                    window.id,
                    WindowEvent.PointerButton(deviceId, KeyState.Released, location, primary, ButtonSource.Touch(fingerId, force)),
                )
                handler.windowEvent(
                    eventLoop,
                    window.id,
                    WindowEvent.PointerLeft(deviceId, location, primary, PointerKind.Touch),
                )
            }
            TouchPhase.Cancelled -> {
                handler.windowEvent(
                    eventLoop,
                    window.id,
                    WindowEvent.PointerButton(deviceId, KeyState.Released, location, primary, ButtonSource.Touch(fingerId, force)),
                )
                handler.windowEvent(
                    eventLoop,
                    window.id,
                    WindowEvent.PointerLeft(deviceId, location, primary, PointerKind.Touch),
                )
            }
        }
    }

    // ── Keyboard (hardware keyboards, game controllers) ───────────────────────

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        // event.repeatCount > 0 ⇒ the key is held down (auto-repeat).
        if (dispatchKey(keyCode, event, KeyState.Pressed, isRepeat = event.repeatCount > 0)) return true
        return super.onKeyDown(keyCode, event)
    }

    override fun onKeyUp(keyCode: Int, event: KeyEvent): Boolean {
        if (dispatchKey(keyCode, event, KeyState.Released, isRepeat = false)) return true
        return super.onKeyUp(keyCode, event)
    }

    /**
     * Translates an Android key event into a [WindowEvent.KeyInput] and
     * dispatches it to the handler.
     *
     * @return `true` if the key was mapped and consumed; `false` for unmapped
     *   keys (volume, back, media…) so the system keeps its default behavior.
     */
    private fun dispatchKey(keyCode: Int, event: KeyEvent, state: KeyState, isRepeat: Boolean): Boolean {
        val window = eventLoop.pendingWindow
        if (destroyed || window == null) return false
        val mappedCode = AndroidKeyMapper.keyCode(keyCode) ?: return false
        val deviceId = DeviceId(event.deviceId.toLong())
        val native = NativeKeyInfo(
            platform = KeyPlatform.Android,
            scanCode = event.scanCode.toLong(),
            virtualKey = keyCode.toLong(),
            nativeCode = NativeKeyCode.Android(event.scanCode.toLong(), keyCode.toLong()),
            nativeKey = NativeLogicalKey.Android(keyCode.toLong()),
        )
        val logicalKey = mappedCode.defaultLogicalKey()
        handler.windowEvent(
            eventLoop,
            window.id,
            WindowEvent.KeyInput(
                event = KadreKeyEvent(
                    physicalKey = AndroidKeyMapper.physicalKey(keyCode),
                    logicalKey = logicalKey,
                    state = state,
                    modifiers = AndroidKeyMapper.modifiersFrom(event.metaState),
                    repeat = isRepeat,
                    text = mappedCode.defaultText(),
                    keyWithoutModifiers = logicalKey,
                    native = native,
                ),
                deviceId = deviceId,
            ),
        )
        return true
    }

    // ── Display density (scale factor) ────────────────────────────────────────

    /**
     * Emits [WindowEvent.ScaleFactorChanged] when the display density changes
     * (e.g. moved to an external display, runtime density override).
     *
     * Note: this is only invoked when the manifest declares the relevant
     * `android:configChanges` (e.g. `density`); otherwise Android recreates the
     * Activity instead, and the new density is picked up on re-creation.
     */
    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        val window = eventLoop.pendingWindow
        if (destroyed || window == null) return
        val density = resources.displayMetrics.density.toDouble()
        if (density != lastScaleFactor) {
            lastScaleFactor = density
            handler.windowEvent(eventLoop, window.id, WindowEvent.ScaleFactorChanged(density))
        }
    }

    // ── Focus (window-level) ──────────────────────────────────────────────────

    /**
     * Emits [WindowEvent.Focused] when the activity window gains or loses focus
     * (app switch, notification shade, dialog overlay).
     *
     * This is the per-window counterpart of the activity-level [onResume] /
     * [onPause] lifecycle and is reported independently (a window can lose focus
     * without the activity pausing, e.g. the notification shade).
     */
    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        val window = eventLoop.pendingWindow
        if (destroyed || window == null) return
        handler.windowEvent(eventLoop, window.id, WindowEvent.Focused(hasFocus))
    }

    override fun onDestroy() {
        // Per-window terminal event, emitted before the guard/cleanup while the
        // window is still resolvable (counterpart of the surface-destroyed
        // app-level destroySurfaces callback).
        eventLoop.pendingWindow?.let { window ->
            handler.windowEvent(eventLoop, window.id, WindowEvent.Destroyed)
        }
        destroyed = true
        eventLoop.onSurfaceDestroyed()
        super.onDestroy()
    }
}
