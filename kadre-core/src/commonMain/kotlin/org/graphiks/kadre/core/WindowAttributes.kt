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
 */
data class WindowAttributes(
    val title: String = "Kadre",
    val size: PhysicalSize<Int>? = null,
    val visible: Boolean = true,
    val resizable: Boolean = true,
)
