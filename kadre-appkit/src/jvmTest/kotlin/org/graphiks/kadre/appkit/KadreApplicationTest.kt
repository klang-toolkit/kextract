package org.graphiks.kadre.appkit

import org.graphiks.kadre.appkit.bindings.NSApplication
import org.graphiks.kadre.core.ActiveEventLoop
import org.graphiks.kadre.core.ApplicationHandler
import org.graphiks.kadre.core.ControlFlow
import org.graphiks.kadre.core.EventLoopProxy
import org.graphiks.kadre.core.StartCause
import org.graphiks.kadre.core.Window
import org.graphiks.kadre.core.WindowAttributes
import org.graphiks.kadre.core.WindowId
import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue
import kotlin.test.assertFailsWith

/**
 * Compilation tests for the GRA-125 classes.
 *
 * No real runtime test: calling `NSApp` requires the macOS main thread
 * and is validated by the M1 demo application rather than by the unit
 * tests.
 */
class KadreApplicationTest {

    @Test
    fun `KadreApplication est une sous-classe de NSApplication`() {
        // Type-level check: KadreApplication must inherit
        // from NSApplication (see kextract binding).
        val nsAppClass = NSApplication::class.java
        assertTrue(nsAppClass.isAssignableFrom(KadreApplication::class.java))
    }

    /**
     * eventLoop must be an instance property (non-static).
     *
     * Verifies that:
     * - `KadreApplication` has a non-static `eventLoop` field (JVM backing field).
     * - `KadreApplication` has a static `sharedApp` field (equivalent to
     *   `NSApp as? KadreApplication`) — backing field of the companion property.
     * - No static `eventLoop` field exists (the global static variable was removed).
     */
    @Test
    fun `eventLoop est une propriete d instance et non une variable statique`() {
        val allFields = KadreApplication::class.java.declaredFields

        // eventLoop must exist as an instance field (non-static)
        val eventLoopField = allFields.firstOrNull { it.name == "eventLoop" }
        assertNotNull(
            eventLoopField,
            "eventLoop doit être un champ de KadreApplication, champs trouvés : ${allFields.map { it.name }}"
        )
        assertTrue(
            !java.lang.reflect.Modifier.isStatic(eventLoopField!!.modifiers),
            "eventLoop doit être non-statique (propriété d'instance)"
        )

        // sharedApp must be a static field (backing field of the companion property)
        val sharedAppField = allFields.firstOrNull { it.name == "sharedApp" }
        assertNotNull(
            sharedAppField,
            "sharedApp doit exister dans KadreApplication pour remplacer NSApp as? KadreApplication"
        )
        assertTrue(
            java.lang.reflect.Modifier.isStatic(sharedAppField!!.modifiers),
            "sharedApp doit être statique (companion object property)"
        )
    }

    /**
     * sharedApp is initially null before any call to initialize().
     *
     * Guarantees that there is no eager initialization and that the
     * "NSApp as? KadreApplication" pattern only returns null if initialize() has
     * not yet been called.
     */
    @Test
    fun `sharedApp est null avant initialize`() {
        // Note: this test assumes that initialize() has not been called in this test
        // process, which is the case since the tests do not touch the macOS runtime.
        assertNull(
            KadreApplication.sharedApp,
            "sharedApp doit être null si initialize() n'a jamais été appelé"
        )
    }

    @Test
    fun `KadreAppDelegate accepte un ApplicationHandler et un ActiveEventLoop`() {
        // Verify the constructor signature without instantiating the underlying
        // ObjC object (which would require the macOS main thread).
        val ctor = KadreAppDelegate::class.java.constructors.first()
        val paramTypes = ctor.parameterTypes
        assertTrue(paramTypes.any { ApplicationHandler::class.java.isAssignableFrom(it) })
        assertTrue(paramTypes.any { ActiveEventLoop::class.java.isAssignableFrom(it) })
    }

    @Test
    fun `MainThreadCheck est invocable`() {
        // The type compiles and the singleton object is accessible.
        val check: MainThreadCheck = MainThreadCheck
        assertNotNull(check)
    }

    @Test
    fun `Callbacks expose des methodes JvmStatic`() {
        // Guarantees that the trampolines required by Linker.upcallStub
        // exist and are static at the JVM level.
        val didFinish = KadreAppDelegate.Callbacks::class.java
            .getDeclaredMethod(
                "applicationDidFinishLaunching",
                java.lang.foreign.MemorySegment::class.java,
                java.lang.foreign.MemorySegment::class.java,
                java.lang.foreign.MemorySegment::class.java,
            )
        val shouldTerminate = KadreAppDelegate.Callbacks::class.java
            .getDeclaredMethod(
                "applicationShouldTerminate",
                java.lang.foreign.MemorySegment::class.java,
                java.lang.foreign.MemorySegment::class.java,
                java.lang.foreign.MemorySegment::class.java,
            )
        assertTrue(java.lang.reflect.Modifier.isStatic(didFinish.modifiers))
        assertTrue(java.lang.reflect.Modifier.isStatic(shouldTerminate.modifiers))
    }

    /**
     * DoD #3: a second call to [runApp] must throw [IllegalStateException].
     *
     * Strategy:
     * 1. We force [appKitRunning] to true via reflection on the generated Kotlin class
     *    (`AppKitEventLoopKt`) to simulate an already-active loop.
     * 2. We call [runApp] from an arbitrary thread (non-main): the
     *    AtomicBoolean guard is checked BEFORE [MainThreadCheck], so the exception is thrown
     *    immediately without interaction with the AppKit runtime.
     * 3. The field is restored to false in finally so as not to pollute the other tests.
     */
    @Test
    fun `runApp double appel lance IllegalStateException`() {
        // Access the file-level AtomicBoolean via the generated Kotlin class
        val ktClass = Class.forName("org.graphiks.kadre.appkit.AppKitEventLoopKt")
        val runningField = ktClass.getDeclaredField("appKitRunning")
        runningField.isAccessible = true
        val running = runningField.get(null) as java.util.concurrent.atomic.AtomicBoolean

        val previousValue = running.get()
        try {
            // Simulate an already-active loop
            running.set(true)

            val handler = NoopHandler()

            assertFailsWith<IllegalStateException>(
                message = "runApp() doit lever IllegalStateException si une boucle est déjà active"
            ) {
                // The check(appKitRunning.compareAndSet(false, true)) guard fails first,
                // before any access to the AppKit runtime or to MainThreadCheck.
                runApp(handler)
            }
        } finally {
            // Restore the initial state so as not to affect the other tests
            running.set(previousValue)
        }
    }

    /** No-op stub to validate the KadreAppDelegate constructor signature. */
    @Suppress("unused")
    private class NoopHandler : ApplicationHandler {
        override fun canCreateSurfaces(eventLoop: ActiveEventLoop) = Unit
        override fun windowEvent(eventLoop: ActiveEventLoop, windowId: WindowId, event: Any) = Unit
    }

    /** No-op stub to validate the KadreAppDelegate constructor signature. */
    @Suppress("unused")
    private class NoopEventLoop : ActiveEventLoop {
        override fun createWindow(attributes: WindowAttributes): Window =
            throw UnsupportedOperationException()
        override fun setControlFlow(controlFlow: ControlFlow) = Unit
        override val controlFlow: ControlFlow = ControlFlow.Poll
        override fun exit() = Unit
        override val isExiting: Boolean = false
        override fun createProxy(): EventLoopProxy = throw UnsupportedOperationException()
    }
}
