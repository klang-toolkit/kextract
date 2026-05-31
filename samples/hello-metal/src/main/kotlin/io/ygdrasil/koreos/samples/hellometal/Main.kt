/**
 * Sample hello-metal — démonstrateur POC Metal view minimale (Jalon M1).
 *
 * Illustre l'utilisation de bout-en-bout de la stack koreos :
 *   EventLoop → AppKitEventLoop → NSWindow + CAMetalLayer
 *
 * Usage : ./gradlew :samples:hello-metal:run
 * Prérequis : macOS avec JDK 25 (thread principal — lancé par Gradle).
 *
 * GRA-130 : première application koreos fonctionnelle.
 */
package io.ygdrasil.koreos.samples.hellometal

import io.ygdrasil.koreos.ActiveEventLoop
import io.ygdrasil.koreos.ApplicationHandler
import io.ygdrasil.koreos.EventLoop
import io.ygdrasil.koreos.PhysicalSize
import io.ygdrasil.koreos.WindowAttributes
import io.ygdrasil.koreos.WindowId
import io.ygdrasil.koreos.core.RawWindowHandle
import io.ygdrasil.koreos.core.WindowEvent

/**
 * Gestionnaire d'application hello-metal.
 *
 * Ouvre une fenêtre 800×600, affiche un log à chaque événement,
 * et quitte proprement sur `CloseRequested`.
 */
class HelloApp : ApplicationHandler {

    /**
     * Appelé dès qu'AppKit autorise la création de surfaces de rendu.
     *
     * Crée la fenêtre principale et vérifie que le layer est bien un CAMetalLayer.
     */
    override fun canCreateSurfaces(eventLoop: ActiveEventLoop) {
        println("[HelloApp] canCreateSurfaces — création de la fenêtre")

        val window = eventLoop.createWindow(
            WindowAttributes(
                title = "Hello Kadre M1",
                size = PhysicalSize(width = 800, height = 600),
                visible = true,
                resizable = true,
            )
        )

        val handle = window.rawWindowHandle
        if (handle is RawWindowHandle.AppKit) {
            println(
                "[HelloApp] RawWindowHandle.AppKit — nsView=0x%x, nsWindow=0x%x"
                    .format(handle.nsView, handle.nsWindow)
            )
            println("[HelloApp] Le contentView est layer-backed (CAMetalLayer prêt pour rendu)")
        } else {
            println("[HelloApp] rawWindowHandle : $handle")
        }

        println("[HelloApp] Fenêtre prête — windowId=${window.id.value}")
    }

    /**
     * Appelé à chaque événement de fenêtre.
     *
     * Affiche l'événement et initie la fermeture sur `CloseRequested`.
     */
    override fun windowEvent(eventLoop: ActiveEventLoop, windowId: WindowId, event: Any) {
        println("[HelloApp] windowEvent($windowId) → $event")

        if (event is WindowEvent.CloseRequested) {
            println("[HelloApp] CloseRequested — fermeture de l'application")
            eventLoop.exit()
        }
    }
}

/**
 * Point d'entrée du sample hello-metal.
 *
 * Doit être exécuté depuis le thread principal macOS.
 * Gradle assure que `run` s'exécute bien sur le main thread via
 * l'argument JVM `-XstartOnFirstThread` (ajouté dans build.gradle.kts).
 */
fun main() {
    println("[hello-metal] Démarrage — Kadre M1 POC")
    EventLoop().runApp(HelloApp())
    println("[hello-metal] Terminé")
}
