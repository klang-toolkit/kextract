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
 * Démarre l'application Kadre iOS.
 *
 * Enregistre le handler dans [KadreRegistry] puis délègue à [UIApplicationMain]
 * avec [KadreAppDelegate] comme classe de delegate.
 *
 * Cette fonction ne retourne pas (UIApplicationMain bloque jusqu'à la fin de l'app).
 *
 * @param handler Gestionnaire du cycle de vie de l'application.
 */
@OptIn(ExperimentalForeignApi::class, BetaInteropApi::class)
fun startKadreApplication(handler: ApplicationHandler) {
    KadreRegistry.handler = handler
    @Suppress("UNCHECKED_CAST")
    val delegateClass = objc_getClass("KadreAppDelegate") as ObjCClass
    val delegateClassName = NSStringFromClass(delegateClass)
    UIApplicationMain(0, null, null, delegateClassName)
}
