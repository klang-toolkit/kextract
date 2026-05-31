package org.graphiks.kadre.uikit

import org.graphiks.kadre.core.ApplicationHandler
import kotlinx.cinterop.BetaInteropApi
import kotlinx.cinterop.ExportObjCClass
import platform.UIKit.UIApplication
import platform.UIKit.UIApplicationDelegateProtocol
import platform.UIKit.UIResponder

/**
 * AppDelegate Kadre pour iOS.
 *
 * Déclaré `@ExportObjCClass` pour être visible par l'Objective-C runtime
 * (requis pour `UIApplicationMain`). Le handler est injecté via [KadreRegistry]
 * avant le démarrage de l'application.
 *
 * ## Ordre strict des callbacks UIKit
 *
 * ### Démarrage
 * ```
 * application(_:didFinishLaunchingWithOptions:) → canCreateSurfaces
 * applicationDidBecomeActive                   → resumed
 * ```
 *
 * ### Verrouillage écran / interruption courte (appel, Control Center)
 * ```
 * applicationWillResignActive  → suspended
 * applicationDidBecomeActive   → resumed       (déverrouillage / retour)
 * ```
 *
 * ### Mise en arrière-plan complète (bouton Home, App Switcher)
 * ```
 * applicationWillResignActive  → suspended
 * applicationDidEnterBackground → destroySurfaces
 * applicationWillEnterForeground (pas de callback Kadre — transition)
 * applicationDidBecomeActive   → resumed
 * ```
 *
 * ### Terminaison
 * ```
 * applicationWillTerminate     → destroySurfaces (si pas encore appelé)
 * ```
 *
 * ## Décision M3
 * AppDelegate-only (pas de `UISceneDelegate`) — évite `UISceneConfiguration`/Info.plist.
 * Scene-based envisagé post-V1 si le multi-fenêtre iOS est requis.
 */
@OptIn(BetaInteropApi::class)
@ExportObjCClass
class KadreAppDelegate : UIResponder(), UIApplicationDelegateProtocol {

    private var eventLoop: UIKitActiveEventLoop? = null

    // ── Démarrage ─────────────────────────────────────────────────────────────

    /**
     * Point d'entrée de l'application.
     *
     * Récupère le handler depuis [KadreRegistry], crée l'[UIKitActiveEventLoop]
     * et déclenche [ApplicationHandler.canCreateSurfaces].
     */
    override fun application(
        application: UIApplication,
        didFinishLaunchingWithOptions: Map<Any?, *>?,
    ): Boolean {
        println("[KadreAppDelegate] applicationDidFinishLaunching → canCreateSurfaces")
        val handler = KadreRegistry.handler
            ?: error("[KadreAppDelegate] Aucun handler enregistré — appelez startKadreApplication avant UIApplicationMain")
        val loop = UIKitActiveEventLoop(handler)
        eventLoop = loop
        handler.canCreateSurfaces(loop)
        return true
    }

    // ── Actif / Inactif ───────────────────────────────────────────────────────

    /**
     * L'application devient active (premier plan, focus clavier).
     *
     * Déclenche [ApplicationHandler.resumed].
     */
    override fun applicationDidBecomeActive(application: UIApplication) {
        println("[KadreAppDelegate] applicationDidBecomeActive → resumed")
        eventLoop?.let { it.handler.resumed(it) }
    }

    /**
     * L'application va devenir inactive (appel entrant, Control Center, mise en fond).
     *
     * Déclenche [ApplicationHandler.suspended].
     */
    override fun applicationWillResignActive(application: UIApplication) {
        println("[KadreAppDelegate] applicationWillResignActive → suspended")
        eventLoop?.let { it.handler.suspended(it) }
    }

    // ── Arrière-plan / Premier plan ───────────────────────────────────────────

    /**
     * L'application est passée en arrière-plan complet (Home, App Switcher).
     *
     * Déclenche [ApplicationHandler.destroySurfaces] pour permettre à l'app
     * de libérer les ressources GPU avant suspension complète du processus.
     *
     * Note : appelé APRÈS [applicationWillResignActive] → [ApplicationHandler.suspended].
     */
    override fun applicationDidEnterBackground(application: UIApplication) {
        println("[KadreAppDelegate] applicationDidEnterBackground → destroySurfaces")
        eventLoop?.let { it.handler.destroySurfaces(it) }
    }

    /**
     * L'application va revenir au premier plan (depuis App Switcher ou retour app).
     *
     * Déclenche [ApplicationHandler.canCreateSurfaces] pour permettre la
     * ré-initialisation des surfaces GPU.
     *
     * Note : appelé AVANT [applicationDidBecomeActive] → [ApplicationHandler.resumed].
     */
    override fun applicationWillEnterForeground(application: UIApplication) {
        println("[KadreAppDelegate] applicationWillEnterForeground → canCreateSurfaces")
        eventLoop?.let { it.handler.canCreateSurfaces(it) }
    }

    // ── Terminaison ───────────────────────────────────────────────────────────

    /**
     * L'application va être terminée par le système.
     *
     * Déclenche [ApplicationHandler.destroySurfaces] (si pas déjà appelé depuis
     * [applicationDidEnterBackground]) puis nettoie le registre.
     */
    override fun applicationWillTerminate(application: UIApplication) {
        println("[KadreAppDelegate] applicationWillTerminate → destroySurfaces")
        eventLoop?.let { it.handler.destroySurfaces(it) }
        eventLoop = null
        KadreRegistry.handler = null
    }
}
