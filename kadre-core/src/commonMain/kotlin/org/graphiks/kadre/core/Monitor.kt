/**
 * R2 — Monitor enumeration and fullscreen types.
 *
 * Scope: pure Kotlin types, no native reference.
 */
package org.graphiks.kadre.core

/**
 * Handle to a physical monitor.
 *
 * Returned by [ActiveEventLoop.availableMonitors] and [ActiveEventLoop.primaryMonitor].
 * Concrete instances are created by each platform backend.
 *
 * @property id         Platform-specific monitor identifier (HMONITOR, CGDirectDisplayID, XRROutput, etc.).
 * @property name       Human-readable monitor name (may be null if unavailable on the platform).
 * @property position   Position of the top-left corner of the monitor in the virtual screen space,
 *                      expressed in physical pixels.
 * @property scaleFactor Scale factor between logical and physical pixels (e.g. 2.0 on a Retina display).
 * @property currentVideoMode Current video mode of the monitor, or null if unavailable.
 * @property videoModes  All video modes supported by the monitor (may be empty on some backends).
 */
interface MonitorHandle {
    val id: Long
    val name: String?
    val position: PhysicalPosition<Int>
    val scaleFactor: Double
    val currentVideoMode: VideoMode?
    val videoModes: List<VideoMode>
}

/**
 * A video mode supported by a monitor.
 *
 * @property size           Resolution in physical pixels.
 * @property bitDepth       Color depth in bits per channel (e.g. 8 for 24-bit color), or null if unavailable.
 * @property refreshRateMilliHz Refresh rate in milli-Hz (e.g. 60_000 for 60 Hz), or null if unavailable.
 */
data class VideoMode(
    val size: PhysicalSize<Int>,
    val bitDepth: Int?,
    val refreshRateMilliHz: Int?,
)

/**
 * Fullscreen mode requested via [Window.setFullscreen].
 *
 * ## Variants
 *
 * - [Borderless] — covers the monitor with a borderless window. Works on all backends.
 * - [Exclusive]  — requests exclusive fullscreen with a specific video mode.
 *   Only supported on AppKit, Win32 and X11 (backend-specific implementation).
 *   On Wayland, Web, Android and UIKit, passing [Exclusive] falls back to borderless
 *   and is documented as a no-op for the exclusive part.
 */
sealed interface Fullscreen {
    /**
     * Borderless (windowed) fullscreen: covers a monitor without changing its video mode.
     *
     * @param monitor Target monitor, or null to use the monitor that currently contains
     *                the window (or the primary monitor if the window has no screen).
     */
    data class Borderless(val monitor: MonitorHandle? = null) : Fullscreen

    /**
     * Exclusive fullscreen: requests a mode change on the given monitor.
     *
     * Not supported on Wayland, Web, Android and UIKit — those backends treat
     * [Exclusive] as [Borderless] and log a note about the fallback.
     *
     * @param monitor   The monitor to go fullscreen on.
     * @param videoMode The video mode to set.
     */
    data class Exclusive(val monitor: MonitorHandle, val videoMode: VideoMode) : Fullscreen
}
