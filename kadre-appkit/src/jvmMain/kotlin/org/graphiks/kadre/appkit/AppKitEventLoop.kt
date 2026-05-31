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

import org.graphiks.kadre.appkit.bindings.NSApplicationActivationPolicy
import org.graphiks.kadre.appkit.bindings.ObjCRuntime
import org.graphiks.kadre.core.ActiveEventLoop
import org.graphiks.kadre.core.ApplicationHandler
import org.graphiks.kadre.core.ControlFlow
import org.graphiks.kadre.core.EventLoopProxy
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
        "AppKitEventLoop.runApp() ne peut être appelé qu'une seule fois par processus. Une boucle d'événements AppKit est déjà active."
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
