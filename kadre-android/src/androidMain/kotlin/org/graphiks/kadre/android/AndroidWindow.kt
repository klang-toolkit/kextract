package org.graphiks.kadre.android

import android.view.SurfaceView
import org.graphiks.kadre.core.*

/**
 * Implémentation Android de [Window].
 *
 * Encapsule un [SurfaceView] plein écran et expose la [android.view.Surface]
 * brute via [rawWindowHandle] (Strategy A — zéro JNI).
 *
 * ## Cycle de vie de la surface
 *
 * Un [AndroidWindow] peut être créé **avant** que la surface Android ne soit
 * disponible (pattern "pending window" — voir [AndroidEventLoop.createWindow]).
 * Le cycle de disponibilité est :
 *
 * | Événement | État surface | [rawWindowHandle] |
 * |-----------|--------------|-------------------|
 * | Après [AndroidEventLoop.createWindow] | Non disponible | Lance [IllegalStateException] |
 * | Après [onSurfaceAvailable] | Disponible | Retourne [RawWindowHandle.Android] valide |
 * | Après [onSurfaceReleased] | Non disponible | Lance [IllegalStateException] |
 *
 * Les renderers (wgpu4k, etc.) ne doivent accéder à [rawWindowHandle] que dans
 * ou après le callback [org.graphiks.kadre.core.ApplicationHandler.canCreateSurfaces],
 * et doivent relâcher le handle avant [org.graphiks.kadre.core.ApplicationHandler.destroySurfaces].
 */
class AndroidWindow internal constructor(
    internal val surfaceView: SurfaceView,
) : Window {

    override val id: WindowId = WindowId(surfaceView.hashCode().toLong())

    @Volatile
    private var _surface: android.view.Surface? = null

    /**
     * Rend la surface disponible pour le rendu.
     *
     * Appelé par [AndroidEventLoop.onSurfaceCreated] (qui est lui-même déclenché
     * par [KadreActivity]) lors de `surfaceCreated`. Après cet appel,
     * [rawWindowHandle] retourne un [RawWindowHandle.Android] valide.
     *
     * @param surface La surface Android fraîchement allouée par le SurfaceHolder.
     */
    internal fun onSurfaceAvailable(surface: android.view.Surface) {
        _surface = surface
    }

    /**
     * Invalide la surface de rendu.
     *
     * Appelé par [AndroidEventLoop.onSurfaceDestroyed] (qui est lui-même déclenché
     * par [KadreActivity]) lors de `surfaceDestroyed`. Après cet appel,
     * [rawWindowHandle] lance [IllegalStateException] jusqu'à la prochaine
     * invocation de [onSurfaceAvailable].
     */
    internal fun onSurfaceReleased() {
        _surface = null
    }

    /**
     * Retourne le handle natif de la surface Android.
     *
     * @return [RawWindowHandle.Android] encapsulant la [android.view.Surface] active.
     * @throws IllegalStateException si la surface n'est pas encore disponible
     *   (avant [onSurfaceAvailable]) ou a été libérée (après [onSurfaceReleased]).
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
        // No-op : les SurfaceView n'ont pas de titre ; l'Activity parente gère le titre
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
        // No-op au niveau library ; la fermeture est à l'app
    }
}
