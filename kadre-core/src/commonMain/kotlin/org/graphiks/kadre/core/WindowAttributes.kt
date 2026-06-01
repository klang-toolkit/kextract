/**
 * Window configuration attributes.
 *
 * Scope: pure Kotlin types, no native reference.
 */
package org.graphiks.kadre.core

/**
 * Window creation parameters.
 *
 * @property title Title shown in the window's title bar.
 * @property size Initial size in physical pixels, or null to use the default size.
 * @property visible Indicates whether the window is visible at the time of its creation.
 * @property resizable Indicates whether the user can resize the window.
 * @property minSize Minimum surface size in physical pixels, or null for no constraint.
 * @property maxSize Maximum surface size in physical pixels, or null for no constraint.
 * @property position Initial outer position in physical pixels, or null to let the OS choose.
 * @property maximized Whether the window should start maximized.
 * @property decorations Whether the window should have platform decorations (title bar, borders).
 * @property active Whether the window should be active (focused) at creation time.
 */
data class WindowAttributes(
    val title: String = "Kadre",
    val size: PhysicalSize<Int>? = null,
    val visible: Boolean = true,
    val resizable: Boolean = true,
    val minSize: PhysicalSize<Int>? = null,
    val maxSize: PhysicalSize<Int>? = null,
    val position: PhysicalPosition<Int>? = null,
    val maximized: Boolean = false,
    val decorations: Boolean = true,
    val active: Boolean = true,
)
