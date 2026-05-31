package org.graphiks.kadre.uikit

import org.graphiks.kadre.core.ApplicationHandler

/**
 * Global Kadre registry for iOS.
 *
 * Stores the ApplicationHandler before UIApplicationMain starts.
 * Necessary because UIApplicationMain instantiates KadreAppDelegate itself
 * without allowing dependency injection through the constructor.
 *
 * Usage:
 * ```kotlin
 * startKadreApplication(myHandler)  // stores in KadreRegistry then launches UIApplicationMain
 * ```
 */
object KadreRegistry {
    var handler: ApplicationHandler? = null
}
