package org.graphiks.kadre.uikit

import org.graphiks.kadre.core.ApplicationHandler
import kotlinx.cinterop.BetaInteropApi
import kotlinx.cinterop.ExportObjCClass
import platform.UIKit.UIApplication
import platform.UIKit.UIApplicationDelegateProtocol
import platform.UIKit.UIResponder

/**
 * Kadre AppDelegate for iOS.
 *
 * Declared `@ExportObjCClass` to be visible to the Objective-C runtime
 * (required for `UIApplicationMain`). The handler is injected via [KadreRegistry]
 * before the application starts.
 *
 * ## Strict order of UIKit callbacks
 *
 * ### Startup
 * ```
 * application(_:didFinishLaunchingWithOptions:) → canCreateSurfaces
 * applicationDidBecomeActive                   → resumed
 * ```
 *
 * ### Screen lock / short interruption (call, Control Center)
 * ```
 * applicationWillResignActive  → suspended
 * applicationDidBecomeActive   → resumed       (unlock / return)
 * ```
 *
 * ### Full backgrounding (Home button, App Switcher)
 * ```
 * applicationWillResignActive  → suspended
 * applicationDidEnterBackground → destroySurfaces
 * applicationWillEnterForeground (no Kadre callback — transition)
 * applicationDidBecomeActive   → resumed
 * ```
 *
 * ### Termination
 * ```
 * applicationWillTerminate     → destroySurfaces (if not already called)
 * ```
 *
 * ## Two parallel signalling channels
 * Each activation callback drives **two** things on purpose:
 * 1. an app-level [ApplicationHandler] lifecycle call (`resumed` / `suspended` /
 *    `destroySurfaces`) — coarse, process-scoped;
 * 2. a per-window [WindowEvent] (`Focused` / `Destroyed`) via
 *    [UIKitActiveEventLoop.dispatchWindowFocused] / [dispatchWindowsDestroyed].
 *
 * The [WindowEvent] channel exists for parity with the desktop/winit backends so
 * that a consumer which only switches on [WindowEvent] still observes focus and
 * destruction. On the single-window AppDelegate model, app activation and window
 * focus coincide, so both are emitted from the same callback.
 *
 * ## M3 decision
 * AppDelegate-only (no `UISceneDelegate`) — avoids `UISceneConfiguration`/Info.plist.
 * Scene-based considered post-V1 if iOS multi-window is required.
 */
@OptIn(BetaInteropApi::class)
@ExportObjCClass
class KadreAppDelegate : UIResponder(), UIApplicationDelegateProtocol {

    private var eventLoop: UIKitActiveEventLoop? = null

    // ── Startup ─────────────────────────────────────────────────────────────

    /**
     * Application entry point.
     *
     * Retrieves the handler from [KadreRegistry], creates the [UIKitActiveEventLoop]
     * and triggers [ApplicationHandler.canCreateSurfaces].
     */
    override fun application(
        application: UIApplication,
        didFinishLaunchingWithOptions: Map<Any?, *>?,
    ): Boolean {
        println("[KadreAppDelegate] applicationDidFinishLaunching → canCreateSurfaces")
        val handler = KadreRegistry.handler
            ?: error("[KadreAppDelegate] No handler registered — call startKadreApplication before UIApplicationMain")
        val loop = UIKitActiveEventLoop(handler)
        eventLoop = loop
        handler.canCreateSurfaces(loop)
        return true
    }

    // ── Active / Inactive ───────────────────────────────────────────────────────

    /**
     * The application becomes active (foreground, keyboard focus).
     *
     * Triggers [ApplicationHandler.resumed].
     */
    override fun applicationDidBecomeActive(application: UIApplication) {
        println("[KadreAppDelegate] applicationDidBecomeActive → resumed + Focused(true)")
        eventLoop?.let {
            it.handler.resumed(it)
            it.dispatchWindowFocused(gained = true)
        }
    }

    /**
     * The application is about to become inactive (incoming call, Control Center, backgrounding).
     *
     * Triggers [ApplicationHandler.suspended].
     */
    override fun applicationWillResignActive(application: UIApplication) {
        println("[KadreAppDelegate] applicationWillResignActive → Focused(false) + suspended")
        eventLoop?.let {
            // Lose window focus before the app-level suspend.
            it.dispatchWindowFocused(gained = false)
            it.handler.suspended(it)
        }
    }

    // ── Background / Foreground ───────────────────────────────────────────

    /**
     * The application has moved fully to the background (Home, App Switcher).
     *
     * Triggers [ApplicationHandler.destroySurfaces] to let the app
     * release GPU resources before the process is fully suspended.
     *
     * Note: called AFTER [applicationWillResignActive] → [ApplicationHandler.suspended].
     */
    override fun applicationDidEnterBackground(application: UIApplication) {
        println("[KadreAppDelegate] applicationDidEnterBackground → destroySurfaces")
        eventLoop?.let { it.handler.destroySurfaces(it) }
    }

    /**
     * The application is about to return to the foreground (from App Switcher or app return).
     *
     * Triggers [ApplicationHandler.canCreateSurfaces] to allow
     * re-initialization of GPU surfaces.
     *
     * Note: called BEFORE [applicationDidBecomeActive] → [ApplicationHandler.resumed].
     */
    override fun applicationWillEnterForeground(application: UIApplication) {
        println("[KadreAppDelegate] applicationWillEnterForeground → canCreateSurfaces")
        eventLoop?.let { it.handler.canCreateSurfaces(it) }
    }

    // ── Termination ───────────────────────────────────────────────────────────

    /**
     * The application is about to be terminated by the system.
     *
     * Triggers [ApplicationHandler.destroySurfaces] (if not already called from
     * [applicationDidEnterBackground]) then cleans up the registry.
     */
    override fun applicationWillTerminate(application: UIApplication) {
        println("[KadreAppDelegate] applicationWillTerminate → Destroyed + destroySurfaces")
        eventLoop?.let {
            // Per-window terminal event before the app-level surface teardown.
            it.dispatchWindowsDestroyed()
            it.handler.destroySurfaces(it)
        }
        eventLoop = null
        KadreRegistry.handler = null
    }
}
