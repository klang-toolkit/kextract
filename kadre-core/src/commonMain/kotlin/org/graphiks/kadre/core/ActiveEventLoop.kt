/**
 * Interface representing the event loop that is active during a callback.
 *
 * Scope: pure Kotlin interface, no native reference.
 */
package org.graphiks.kadre.core

/**
 * Controls which raw [DeviceEvent]s the event loop dispatches to the application.
 *
 * Passed to [ActiveEventLoop.listenDeviceEvents].
 *
 * @since R4
 */
enum class DeviceEvents {
    /**
     * Device events are dispatched unconditionally, even when no application window
     * has focus (e.g. for FPS-style raw mouse input).
     */
    Always,

    /**
     * Device events are dispatched only while at least one application window
     * has keyboard focus. This is the default behavior.
     */
    WhenFocused,

    /**
     * No device events are dispatched. Useful to suppress raw input entirely.
     */
    Never,
}

/**
 * Access to the event loop from [ApplicationHandler] callbacks.
 *
 * This interface is passed as a parameter on each incoming call into the
 * application handler, allowing the latter to create windows, control the
 * execution flow, and initiate shutdown of the loop.
 */
interface ActiveEventLoop {

    /**
     * Creates a new window with the specified attributes.
     *
     * @param attributes Configuration parameters of the window to create.
     * @return The created window.
     */
    fun createWindow(attributes: WindowAttributes): Window

    /**
     * Sets the waiting behavior of the event loop
     * after the end of the current iteration.
     *
     * @param controlFlow New waiting behavior.
     */
    fun setControlFlow(controlFlow: ControlFlow)

    /**
     * Returns the currently configured waiting behavior.
     */
    val controlFlow: ControlFlow

    /**
     * Requests shutdown of the event loop.
     *
     * The loop does not stop immediately; it finishes the current
     * iteration before stopping.
     */
    fun exit()

    /**
     * Indicates whether a shutdown request has been issued.
     *
     * @return true if [exit] has been called and the loop is going to stop.
     */
    val isExiting: Boolean

    /**
     * Creates a thread-safe proxy to this event loop.
     *
     * @return An [EventLoopProxy] usable from any thread.
     */
    fun createProxy(): EventLoopProxy

    /**
     * Returns a persistent display handle usable independently from a window.
     */
    fun ownedDisplayHandle(): OwnedDisplayHandle? = null

    // ── R2: monitor enumeration ───────────────────────────────────────────────

    /**
     * Returns all monitors currently connected to the system.
     *
     * The list contains at least one entry on all backends when a display is
     * available. On mobile / web backends, a single synthetic monitor
     * representing the screen is returned.
     *
     * @return Immutable list of [MonitorHandle] objects.
     */
    fun availableMonitors(): List<MonitorHandle>

    /**
     * Returns the primary monitor, or null if no primary monitor can be determined.
     *
     * On desktop backends this is the monitor designated as "primary" by the OS.
     * On mobile / web backends this is the main screen.
     *
     * @return The primary [MonitorHandle], or null if unavailable.
     */
    fun primaryMonitor(): MonitorHandle?

    // ── R3: theme ────────────────────────────────────────────────────────────

    /**
     * Returns the current system-wide UI theme, or null if the information
     * is not available on this platform.
     *
     * | Backend  | Source                                                   |
     * |----------|----------------------------------------------------------|
     * | AppKit   | `NSApp.effectiveAppearance`                              |
     * | Win32    | Registry `AppsUseLightTheme`                             |
     * | UIKit    | `UITraitCollection.current.userInterfaceStyle`           |
     * | Android  | `UiModeManager.nightMode`                                |
     * | Web      | `matchMedia('prefers-color-scheme: dark')`               |
     * | X11      | No standard — always null (documented)                   |
     * | Wayland  | `org.freedesktop.portal.Settings` — no-op, always null  |
     */
    fun systemTheme(): Theme?

    // ── R5-CustomCursor ───────────────────────────────────────────────────────

    /**
     * Creates a custom cursor from the provided RGBA pixel data.
     *
     * Returns null if the backend does not support custom cursors (iOS, Android)
     * or if cursor creation failed (e.g. invalid image dimensions).
     *
     * Default implementation returns null — backends that support custom cursors
     * will override this method.
     * Never throws.
     *
     * TODO R5-CustomCursor: wire in AppKit, Win32, X11, Wayland, and Web backends.
     *
     * @param image RGBA image data with hot-spot information.
     * @return An opaque [CustomCursor] handle, or null on unsupported platforms.
     */
    fun createCustomCursor(image: CursorImage): CustomCursor? = null

    // ── R4: device event filter ───────────────────────────────────────────────

    /**
     * Controls which raw [DeviceEvent]s are dispatched to
     * [ApplicationHandler.deviceEvent].
     *
     * | Mode              | Behaviour                                                    |
     * |-------------------|--------------------------------------------------------------|
     * | [DeviceEvents.Always]      | Events are dispatched regardless of window focus.  |
     * | [DeviceEvents.WhenFocused] | Events are dispatched only while a window has focus (default). |
     * | [DeviceEvents.Never]       | No device events are dispatched.                   |
     *
     * Backends that do not distinguish focus-filtered device events treat
     * [DeviceEvents.WhenFocused] as [DeviceEvents.Always] (documented no-op difference).
     * Never throws.
     *
     * @param mode New filter mode.
     */
    fun listenDeviceEvents(mode: DeviceEvents)

    // ── R6: gamepad ──────────────────────────────────────────────────────────

    /**
     * Returns the gamepad controller for this event loop, or null if gamepad
     * input is not available on this platform.
     */
    val gamepadController: GamepadController? get() = null
}
