package org.graphiks.kadre.android

import android.os.Bundle
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.view.View
import android.view.WindowManager
import androidx.activity.ComponentActivity
import org.graphiks.kadre.core.ApplicationHandler
import org.graphiks.kadre.core.PhysicalPosition
import org.graphiks.kadre.core.PhysicalSize
import org.graphiks.kadre.core.TouchPhase
import org.graphiks.kadre.core.WindowEvent

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
 * - [onDestroy] → `destroyed` guard set, then cleanup
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

        if (event.actionMasked == MotionEvent.ACTION_MOVE) {
            for (pointerIndex in 0 until event.pointerCount) {
                val location = PhysicalPosition(
                    event.getX(pointerIndex).toDouble(),
                    event.getY(pointerIndex).toDouble(),
                )
                val id = event.getPointerId(pointerIndex).toLong()
                handler.windowEvent(eventLoop, window.id, WindowEvent.Touch(phase, location, id))
            }
        } else {
            val pointerIndex = event.actionIndex
            val location = PhysicalPosition(
                event.getX(pointerIndex).toDouble(),
                event.getY(pointerIndex).toDouble(),
            )
            val id = event.getPointerId(pointerIndex).toLong()
            handler.windowEvent(eventLoop, window.id, WindowEvent.Touch(phase, location, id))
        }
    }

    override fun onDestroy() {
        destroyed = true
        eventLoop.onSurfaceDestroyed()
        super.onDestroy()
    }
}
