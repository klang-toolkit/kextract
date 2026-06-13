/**
 * AppKit implementation of [ActiveEventLoop] and the [runApp] entry point.
 *
 * [AppKitEventLoop] implements [ActiveEventLoop] and is passed to each
 * [ApplicationHandler] callback. The top-level [runApp] function orchestrates
 * AppKit initialization (KadreApplication + KadreAppDelegate + NSApp.run).
 *
 * GRA-128: first complete wiring — M1.
 * GRA-136: effective ControlFlow + CFRunLoopObserver + thread-safe proxy.
 */
package org.graphiks.kadre.appkit

import org.graphiks.kffi.objc.NSApplicationActivationPolicy
import org.graphiks.kffi.objc.ObjCRuntime
import org.graphiks.kadre.core.ActiveEventLoop
import org.graphiks.kadre.core.ApplicationHandler
import org.graphiks.kadre.core.ControlFlow
import org.graphiks.kadre.core.CursorImage
import org.graphiks.kadre.core.CustomCursor
import org.graphiks.kadre.core.DeviceEvents
import org.graphiks.kadre.core.EventLoopProxy
import org.graphiks.kadre.core.MonitorHandle
import org.graphiks.kadre.core.OwnedDisplayHandle
import org.graphiks.kadre.core.RawDisplayHandle
import org.graphiks.kadre.core.Theme
import org.graphiks.kadre.core.Window
import org.graphiks.kadre.core.WindowAttributes
import java.lang.foreign.MemorySegment
import java.lang.foreign.ValueLayout
import java.util.concurrent.ConcurrentHashMap

/**
 * Global lock guaranteeing that only a single AppKit event loop is active
 * at a time within the process. DoD #3.
 *
 * Uses [java.util.concurrent.atomic.AtomicBoolean] for thread-safety:
 * [runApp] performs an atomic CAS false→true at startup and raises
 * [IllegalStateException] if the value was already true.
 */
internal val appKitRunning = java.util.concurrent.atomic.AtomicBoolean(false)

/**
 * Internal implementation of [ActiveEventLoop] for the AppKit platform (macOS).
 *
 * One instance is created per call to [runApp] and passed as the receiver
 * to all [ApplicationHandler] callbacks.
 *
 * M1 scope:
 * - [createWindow]: creates an [AppKitWindow] and installs a
 *   [KadreWindowDelegate] on it for close handling.
 * - [exit]: raises the [isExiting] flag then triggers
 *   `[NSApp terminate:nil]` to quit the AppKit loop.
 * - [controlFlow] / [setControlFlow]: state driven by [CFRunLoopRedrawObserver] (GRA-136).
 * - [createProxy]: implemented via [AppKitEventLoopProxy] (GRA-136) — thread-safe wakeUp.
 */
internal class AppKitEventLoop(
    internal val handler: ApplicationHandler,
) : ActiveEventLoop {

    /** Live windows: windowId → AppKitWindow. */
    internal val windows = ConcurrentHashMap<Long, AppKitWindow>()

    @Volatile
    private var _isExiting = false

    override val isExiting: Boolean
        get() = _isExiting

    @Volatile
    private var _controlFlow: ControlFlow = ControlFlow.Wait

    override val controlFlow: ControlFlow
        get() = _controlFlow

    override fun setControlFlow(controlFlow: ControlFlow) {
        _controlFlow = controlFlow
    }

    /**
     * Creates a new AppKit window and installs the close delegate.
     *
     * Must be called from the main thread (validated by [AppKitWindow.init]).
     */
    override fun createWindow(attributes: WindowAttributes): Window {
        val window = AppKitWindow(attributes)
        window.setWindowDelegate(handler, this)
        windows[window.id.value] = window
        return window
    }

    /**
     * Creates a window with AppKit-specific attributes.
     *
     * Reuses the core [createWindow] and applies macOS-specific settings
     * such as activation policy, tabbing identifier, shadow, titlebar
     * transparency, and more.
     */
    fun createWindow(attrs: AppKitWindowAttributes): Window {
        val window = createWindow(attrs.core) as AppKitWindow

        // Apply activation policy (application-level, set once)
        attrs.activationPolicy?.let { policy ->
            val nsApp = objcSharedApplication()
            ObjCRuntime.msgSend(
                ValueLayout.JAVA_BOOLEAN,
                nsApp,
                ObjCRuntime.sel("setActivationPolicy:"),
                NSApplicationActivationPolicy.fromValue(policy.toAppKitValue()),
            )
        }

        // Apply AppKit-specific window settings
        attrs.hasShadow?.let { window.setHasShadow(it) }
        attrs.tabbingIdentifier?.let { window.setTabbingIdentifier(it) }
        if (attrs.titlebarTransparent) window.setTitlebarTransparent(true)
        if (attrs.titleHidden) window.setTitleHidden(true)
        if (attrs.titlebarHidden) window.setTitlebarHidden(true)
        if (attrs.fullSizeContentView) window.setFullSizeContentView(true)
        if (attrs.movableByWindowBackground) window.setMovableByWindowBackground(true)

        return window
    }

    /**
     * Requests shutdown of the AppKit event loop.
     *
     * Raises [isExiting] then calls `[NSApp terminate:nil]`, which triggers
     * `applicationShouldTerminate:` in [KadreAppDelegate] — which returns
     * `NSTerminateNow` because [isExiting] is already true.
     */
    override fun exit() {
        _isExiting = true
        // Close all open windows before terminating
        windows.values.toList().forEach { window ->
            try { window.close() } catch (_: Exception) { /* ignore */ }
        }
        val nsAppClass = ObjCRuntime.getClass("NSApplication")
        val nsApp = ObjCRuntime.msgSend(
            ValueLayout.ADDRESS,
            nsAppClass,
            ObjCRuntime.sel("sharedApplication"),
        ) as MemorySegment
        ObjCRuntime.msgSend(null, nsApp, ObjCRuntime.sel("terminate:"), MemorySegment.NULL)
    }

    /**
     * Creates an [EventLoopProxy] whose [EventLoopProxy.wakeUp] is thread-safe
     * (GRA-136). Implemented via `CFRunLoopWakeUp(CFRunLoopGetMain())` — see
     * [AppKitEventLoopProxy].
     */
    override fun createProxy(): EventLoopProxy = AppKitEventLoopProxy.create()

    // ── Task 29: ownedDisplayHandle ─────────────────────────────────────────────

    /**
     * Returns an [OwnedDisplayHandle] wrapping [RawDisplayHandle.AppKit].
     */
    override fun ownedDisplayHandle(): OwnedDisplayHandle? =
        OwnedDisplayHandle(RawDisplayHandle.AppKit)

    // ── R2: monitor enumeration ───────────────────────────────────────────────

    /**
     * Returns all connected screens via NSScreen.screens.
     *
     * Falls back to an empty list if the AppKit bindings are unavailable
     * (e.g. non-macOS or headless CI).
     */
    override fun availableMonitors(): List<MonitorHandle> =
        AppKitMonitorHandle.allScreens()

    /**
     * Returns the primary monitor via NSScreen.mainScreen, or null if unavailable.
     */
    override fun primaryMonitor(): MonitorHandle? =
        AppKitMonitorHandle.primaryScreen()

    // ── R3: system theme ──────────────────────────────────────────────────────

    /**
     * Returns the current system-wide theme by reading `NSApp.effectiveAppearance`.
     *
     * Returns null if AppKit is not available or the call fails.
     */
    override fun systemTheme(): Theme? = try {
        val nsAppClass = ObjCRuntime.getClass("NSApplication")
        val nsApp = ObjCRuntime.msgSend(
            ValueLayout.ADDRESS,
            nsAppClass,
            ObjCRuntime.sel("sharedApplication"),
        ) as MemorySegment
        AppKitThemeHelper.effectiveTheme(nsApp)
    } catch (_: Throwable) { null }

    // ── R4: device event filter ───────────────────────────────────────────────

    /**
     * No-op on AppKit: device events are dispatched independently of focus.
     *
     * AppKit dispatches raw device events (PointerMotion, Button, Key) via
     * `sendEvent:` which fires regardless of window focus. A proper filter
     * would require an NSEvent global monitor; out of scope for R4.
     */
    override fun listenDeviceEvents(mode: DeviceEvents) {
        // no-op on AppKit: all device events are always dispatched
    }

    // ── R5-CustomCursor ─────────────────────────────────────────────────────────

    /**
     * Creates a custom cursor from RGBA pixel data on AppKit.
     *
     * Delegates to [AppKitCursorHelper.createNSCursorFromImage] which uses
     * CoreGraphics to create a CGImage, then wraps it in NSImage → NSCursor.
     */
    override fun createCustomCursor(image: CursorImage): CustomCursor? {
        val nsCursor = AppKitCursorHelper.createNSCursorFromImage(image) ?: return null
        return CustomCursor(id = nsCursor.address())
    }
}

/**
 * Entry point of the kadre event loop on macOS.
 *
 * Initializes AppKit, installs the delegates and starts the blocking
 * `NSApp.run()` loop. Only returns when the application closes.
 *
 * Must be called from the macOS main thread.
 *
 * @param handler Lifecycle and event handler.
 */
fun runApp(handler: ApplicationHandler) {
    check(appKitRunning.compareAndSet(false, true)) {
        "AppKitEventLoop.runApp() can only be called once per process. An AppKit event loop is already active."
    }

    MainThreadCheck.require()

    val eventLoop = AppKitEventLoop(handler)

    // 1. Subclass KadreApplication + sharedApplication (stored in sharedApp)
    val app = KadreApplication.initialize()

    try {
        // 2. Wire the loop onto the instance — retrieved via sharedApp (NSApp as? KadreApplication)
        //    in sendEvent:. No dedicated mutable static variable.
        app.eventLoop = eventLoop

        // 3. Activation policy: regular application (icon in the Dock)
        app.setActivationPolicyRegular()

        // 4. Application delegate — wires canCreateSurfaces / shouldTerminate
        val appDelegate = KadreAppDelegate(handler, eventLoop)
        app.setDelegate(appDelegate.ptr)

        // 5. Install the CFRunLoop observer for RedrawRequested coalescing (GRA-134)
        CFRunLoopRedrawObserver.install(handler, eventLoop, eventLoop.windows)

        // 6. Start the blocking AppKit loop — returns on close
        app.run()
    } finally {
        // Cleanup: releases the references and resets the lock to false
        // to allow a possible restart (tests or reentrant processes).
        app.eventLoop = null
        KadreApplication.sharedApp = null
        appKitRunning.set(false)
    }
}
