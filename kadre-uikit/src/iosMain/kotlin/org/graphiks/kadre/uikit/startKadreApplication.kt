package org.graphiks.kadre.uikit

import org.graphiks.kadre.core.ApplicationHandler
import kotlinx.cinterop.BetaInteropApi
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.ObjCClass
import kotlinx.cinterop.getOriginalKotlinClass
import platform.Foundation.NSStringFromClass
import platform.UIKit.UIApplicationMain
import platform.objc.objc_getClass

/**
 * Starts the Kadre iOS application.
 *
 * Registers the handler in [KadreRegistry] then delegates to [UIApplicationMain]
 * with [KadreAppDelegate] as the delegate class.
 *
 * This function does not return (UIApplicationMain blocks until the app ends).
 *
 * @param handler Handler for the application's lifecycle.
 */
@OptIn(ExperimentalForeignApi::class, BetaInteropApi::class)
fun startKadreApplication(handler: ApplicationHandler) {
    KadreRegistry.handler = handler
    @Suppress("UNCHECKED_CAST")
    val delegateClass = objc_getClass("KadreAppDelegate") as ObjCClass
    val delegateClassName = NSStringFromClass(delegateClass)
    UIApplicationMain(0, null, null, delegateClassName)
}
