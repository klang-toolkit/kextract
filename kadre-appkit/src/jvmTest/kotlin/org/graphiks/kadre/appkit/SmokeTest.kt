package org.graphiks.kadre.appkit

import org.graphiks.kadre.appkit.bindings.NSApplication
import org.graphiks.kadre.appkit.bindings.NSApplicationDelegate
import org.graphiks.kadre.appkit.bindings.NSEvent
import org.graphiks.kadre.appkit.bindings.NSView
import org.graphiks.kadre.appkit.bindings.NSWindow
import org.graphiks.kadre.appkit.bindings.NSWindowDelegate
import org.graphiks.kadre.appkit.bindings.ObjCRuntime
import kotlin.test.Test
import kotlin.test.assertNotNull

/**
 * Smoke test — vérifie que les bindings Kotlin/FFM générés par kextract v0.0.0-test6
 * compilent correctement et que les types AppKit sont accessibles.
 *
 * Note : aucune instanciation réelle d'objet Obj-C n'est effectuée ici (cela
 * nécessiterait un thread macOS avec autorelease pool et la librairie AppKit
 * chargée). Ce test valide uniquement la compilation et la lisibilité des
 * companions/interfaces générés.
 */
class SmokeTest {

    @Test
    fun `les classes AppKit generees sont accessibles`() {
        // Vérifie que les companions existent et sont référençables
        // (pas d'appel ObjC réel — juste vérification que les types compilent)
        val nsApplicationClass: kotlin.reflect.KClass<NSApplication> = NSApplication::class
        val nsWindowClass: kotlin.reflect.KClass<NSWindow> = NSWindow::class
        val nsViewClass: kotlin.reflect.KClass<NSView> = NSView::class
        val nsEventClass: kotlin.reflect.KClass<NSEvent> = NSEvent::class

        assertNotNull(nsApplicationClass)
        assertNotNull(nsWindowClass)
        assertNotNull(nsViewClass)
        assertNotNull(nsEventClass)
    }

    @Test
    fun `les interfaces delegate AppKit sont importables`() {
        // Vérifie que les protocoles NSApplicationDelegate et NSWindowDelegate
        // sont générés comme des interfaces Kotlin accessibles
        val appDelegateClass: kotlin.reflect.KClass<NSApplicationDelegate> = NSApplicationDelegate::class
        val windowDelegateClass: kotlin.reflect.KClass<NSWindowDelegate> = NSWindowDelegate::class

        assertNotNull(appDelegateClass)
        assertNotNull(windowDelegateClass)
    }

    @Test
    fun `ObjCRuntime est accessible`() {
        // Vérifie que le runtime FFM/ObjC bridge compile
        val runtime: ObjCRuntime = ObjCRuntime
        assertNotNull(runtime)
    }
}
