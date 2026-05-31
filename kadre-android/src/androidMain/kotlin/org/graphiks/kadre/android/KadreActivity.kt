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
 * Activity racine Kadre pour Android.
 *
 * Héberge un [SurfaceView] plein écran dont la [android.view.Surface] est
 * exposée via [AndroidWindow.rawWindowHandle] → [RawWindowHandle.Android].
 *
 * ## Usage
 * Sous-classer et implémenter [createHandler] :
 * ```kotlin
 * class MyActivity : KadreActivity() {
 *     override fun createHandler() = MyApplicationHandler()
 * }
 * ```
 *
 * ## Lifecycle → callbacks Kadre
 * - [onResume]  → [ApplicationHandler.resumed]
 * - [onPause]   → [ApplicationHandler.suspended]
 * - Surface créée   → [ApplicationHandler.canCreateSurfaces]
 * - Surface changée → [ApplicationHandler.windowEvent] ([WindowEvent.Resized])
 * - Surface détruite → [ApplicationHandler.destroySurfaces]
 * - [onDestroy] → guard `destroyed` activé, puis nettoyage
 *
 * ## Plein écran
 * Status bar et navigation bar masquées via `FLAG_FULLSCREEN` et layout cutout-aware.
 */
abstract class KadreActivity : ComponentActivity() {

    /**
     * Crée le gestionnaire d'application Kadre.
     * Appelé depuis [onCreate] avant toute initialisation de surface.
     */
    abstract fun createHandler(): ApplicationHandler

    /** Handler instancié lors de [onCreate]. */
    lateinit var handler: ApplicationHandler
        private set

    /**
     * Fenêtre Android Kadre.
     *
     * Initialisée lors du premier appel à [AndroidEventLoop.createWindow] (typiquement
     * dans [ApplicationHandler.canCreateSurfaces] via [surfaceCreated]).
     * Délègue la gestion de la surface à [AndroidEventLoop].
     */
    val kadreWindow: AndroidWindow?
        get() = eventLoop.pendingWindow

    /** Boucle d'événements Android. */
    internal lateinit var eventLoop: AndroidEventLoop

    /**
     * SurfaceView plein écran hébergeant la surface de rendu.
     *
     * Exposé en `internal` pour que [AndroidEventLoop.createWindow] puisse
     * instancier [AndroidWindow] avec le SurfaceView de cette Activity.
     */
    internal lateinit var surfaceView: SurfaceView

    /**
     * Guard contre tout dispatch de callback après [onDestroy].
     * Mis à `true` au début de [onDestroy], avant tout nettoyage.
     * Exposé en `internal` pour que [AndroidEventLoop.onFrame] puisse le lire.
     */
    @Volatile
    internal var destroyed = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ── Plein écran ────────────────────────────────────────────────────────
        @Suppress("DEPRECATION")
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        // Cutout-aware (API 28+) — flag appliqué sans import direct pour compat
        if (android.os.Build.VERSION.SDK_INT >= 28) {
            window.attributes.layoutInDisplayCutoutMode =
                WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
        }

        // ── SurfaceView plein écran ────────────────────────────────────────────
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
                // canCreateSurfaces déclenche createWindow dans le handler,
                // ce qui crée l'AndroidWindow via AndroidEventLoop.createWindow.
                // Ensuite on transfère la surface vers la fenêtre créée.
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
