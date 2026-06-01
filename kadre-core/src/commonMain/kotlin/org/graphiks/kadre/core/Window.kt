/**
 * Interface representing a native window managed by kadre.
 *
 * Scope: pure Kotlin interface, no native reference.
 */
package org.graphiks.kadre.core

/**
 * Abstraction of a native window created by the event loop.
 *
 * The concrete implementations are provided by the platform modules
 * (kadre-appkit, etc.).
 */
interface Window {

    /** Unique identifier of the window. */
    val id: WindowId

    /**
     * Returns the native handle of the rendering surface.
     */
    val rawWindowHandle: RawWindowHandle

    /**
     * Returns the native handle of the display.
     */
    val rawDisplayHandle: RawDisplayHandle

    /**
     * Requests a redraw of the window at the next iteration.
     */
    fun requestRedraw()

    /**
     * Sets the title shown in the window's title bar.
     *
     * @param title New title of the window.
     */
    fun setTitle(title: String)

    /**
     * Returns the inner size of the window in physical pixels
     * (rendering surface, without the decorations).
     */
    val innerSize: PhysicalSize<Int>

    /**
     * Returns the outer size of the window in physical pixels
     * (rendering surface plus the platform decorations).
     */
    val outerSize: PhysicalSize<Int>

    /**
     * Returns the scale factor between logical and physical pixels
     * for this window.
     */
    val scaleFactor: Double

    /**
     * Makes the window visible or invisible.
     *
     * @param visible true to show the window, false to hide it.
     */
    fun setVisible(visible: Boolean)

    /**
     * Closes the window.
     *
     * Once closed, the window no longer emits events and its identifier
     * becomes invalid.
     */
    fun close()
}
