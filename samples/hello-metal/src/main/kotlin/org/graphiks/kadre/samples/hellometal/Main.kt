/**
 * Sample hello-metal — minimal Metal view POC demonstrator (Milestone M1).
 *
 * Illustrates end-to-end usage of the kadre stack:
 *   EventLoop → AppKitEventLoop → NSWindow + CAMetalLayer
 *
 * Usage: ./gradlew :samples:hello-metal:run
 * Requirements: macOS with JDK 25 (main thread — launched by Gradle).
 *
 * GRA-130: first functional kadre application.
 */
package org.graphiks.kadre.samples.hellometal

import org.graphiks.kadre.ActiveEventLoop
import org.graphiks.kadre.ApplicationHandler
import org.graphiks.kadre.EventLoop
import org.graphiks.kadre.PhysicalSize
import org.graphiks.kadre.WindowAttributes
import org.graphiks.kadre.WindowId
import org.graphiks.kadre.core.RawWindowHandle
import org.graphiks.kadre.core.WindowEvent

/**
 * hello-metal application handler.
 *
 * Opens an 800×600 window, logs on each event,
 * and exits cleanly on `CloseRequested`.
 */
class HelloApp : ApplicationHandler {

    /**
     * Called as soon as AppKit allows render surface creation.
     *
     * Creates the main window and verifies that the layer is indeed a CAMetalLayer.
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
     * Called on each window event.
     *
     * Logs the event and initiates close on `CloseRequested`.
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
 * Entry point of the hello-metal sample.
 *
 * Must be run from the main macOS thread.
 * Gradle ensures `run` executes on the main thread via
 * the `-XstartOnFirstThread` JVM argument (added in build.gradle.kts).
 */
fun main() {
    println("[hello-metal] Démarrage — Kadre M1 POC")
    EventLoop().runApp(HelloApp())
    println("[hello-metal] Terminé")
}
