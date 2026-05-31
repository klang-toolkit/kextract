package org.graphiks.kadre.android

import org.graphiks.kadre.core.ActiveEventLoop
import org.graphiks.kadre.core.Window
import org.graphiks.kadre.core.WindowAttributes
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

/**
 * Contract tests for [AndroidWindow] and [AndroidEventLoop] (GRA-47).
 *
 * These tests verify the interface signatures and contracts via reflection,
 * without instantiating real Android objects (which require an emulator or Robolectric).
 *
 * ## "pending window" contract
 *
 * 1. [AndroidEventLoop.createWindow] immediately returns a valid [AndroidWindow],
 *    even before the [android.view.Surface] is available.
 * 2. [AndroidWindow.rawWindowHandle] throws [IllegalStateException] if called before
 *    [AndroidWindow.onSurfaceAvailable].
 * 3. [AndroidEventLoop.onSurfaceCreated] transfers the surface to the [AndroidWindow]
 *    stored in [AndroidEventLoop.pendingWindow].
 * 4. [AndroidEventLoop.onSurfaceDestroyed] invalidates the [AndroidWindow]'s surface.
 *
 * The actual runtime tests are validated manually via hello-window-android
 * on an Android emulator/device.
 */
class AndroidWindowContractTest {

    // ── Interface verification ─────────────────────────────────────────────

    @Test
    fun `AndroidWindow implemente Window`() {
        assertTrue(
            Window::class.java.isAssignableFrom(AndroidWindow::class.java),
            "AndroidWindow doit implémenter Window",
        )
    }

    @Test
    fun `AndroidEventLoop implemente ActiveEventLoop`() {
        assertTrue(
            ActiveEventLoop::class.java.isAssignableFrom(AndroidEventLoop::class.java),
            "AndroidEventLoop doit implémenter ActiveEventLoop",
        )
    }

    // ── createWindow verification ─────────────────────────────────────────

    @Test
    fun `AndroidEventLoop expose createWindow retournant Window`() {
        val method = AndroidEventLoop::class.java.methods
            .firstOrNull { it.name == "createWindow" && it.parameterCount == 1 }
        assertNotNull(method, "AndroidEventLoop.createWindow(WindowAttributes) doit exister")
        assertTrue(
            Window::class.java.isAssignableFrom(method.returnType),
            "createWindow doit retourner Window (ou un sous-type)",
        )
        assertEquals(
            WindowAttributes::class.java,
            method.parameterTypes[0],
            "createWindow doit accepter WindowAttributes",
        )
    }

    // ── pendingWindow verification ────────────────────────────────────────

    @Test
    fun `AndroidEventLoop expose pendingWindow de type AndroidWindow nullable`() {
        // Kotlin `internal` members are mangled on the JVM: the getter generated for
        // `internal var pendingWindow` is named `getPendingWindow$<module>` and
        // is not accessible via `Class.methods` (public only).
        // So we go through `declaredMethods` with a prefix match.
        val method = AndroidEventLoop::class.java.declaredMethods
            .firstOrNull { it.name.startsWith("getPendingWindow") }
        assertNotNull(method, "AndroidEventLoop doit exposer pendingWindow (getter interne manglé)")
        assertEquals(
            AndroidWindow::class.java,
            method.returnType,
            "pendingWindow doit être de type AndroidWindow",
        )
    }

    // ── Surface lifecycle verification ───────────────────────────

    @Test
    fun `AndroidEventLoop expose onSurfaceCreated avec parametre Surface`() {
        // `internal fun` is mangled on the JVM: onSurfaceCreated → onSurfaceCreated$<module>.
        // We use startsWith to be insensitive to the module suffix.
        val method = AndroidEventLoop::class.java.declaredMethods
            .firstOrNull { it.name.startsWith("onSurfaceCreated") }
        assertNotNull(method, "AndroidEventLoop.onSurfaceCreated(Surface) doit exister")
        assertEquals(1, method.parameterCount, "onSurfaceCreated doit accepter un seul paramètre")
        assertEquals(
            "android.view.Surface",
            method.parameterTypes[0].name,
            "onSurfaceCreated doit accepter android.view.Surface",
        )
    }

    @Test
    fun `AndroidEventLoop expose onSurfaceDestroyed sans parametre`() {
        // Same reason: Kotlin `internal` mangling suffix.
        val method = AndroidEventLoop::class.java.declaredMethods
            .firstOrNull { it.name.startsWith("onSurfaceDestroyed") }
        assertNotNull(method, "AndroidEventLoop.onSurfaceDestroyed() doit exister")
        assertEquals(0, method.parameterCount, "onSurfaceDestroyed ne doit pas avoir de paramètre")
    }

    @Test
    fun `AndroidWindow expose onSurfaceAvailable avec parametre Surface`() {
        // `internal fun` in AndroidWindow → mangled name, we search by prefix.
        val method = AndroidWindow::class.java.declaredMethods
            .firstOrNull { it.name.startsWith("onSurfaceAvailable") }
        assertNotNull(method, "AndroidWindow.onSurfaceAvailable(Surface) doit exister")
        assertEquals(1, method.parameterCount)
        assertEquals(
            "android.view.Surface",
            method.parameterTypes[0].name,
        )
    }

    @Test
    fun `AndroidWindow expose onSurfaceReleased sans parametre`() {
        // `internal fun` in AndroidWindow → mangled name, we search by prefix.
        val method = AndroidWindow::class.java.declaredMethods
            .firstOrNull { it.name.startsWith("onSurfaceReleased") }
        assertNotNull(method, "AndroidWindow.onSurfaceReleased() doit exister")
        assertEquals(0, method.parameterCount)
    }

    // ── rawWindowHandle verification ─────────────────────────────────────

    @Test
    fun `AndroidWindow rawWindowHandle retourne Any`() {
        val method = AndroidWindow::class.java.methods
            .firstOrNull { it.name == "getRawWindowHandle" }
        assertNotNull(method, "AndroidWindow doit exposer rawWindowHandle")
        assertEquals(
            Any::class.java,
            method.returnType,
            "rawWindowHandle doit retourner Any (type-erased pour commonMain)",
        )
    }

    /**
     * Documents the contract: [AndroidWindow.rawWindowHandle] throws
     * [IllegalStateException] before [AndroidWindow.onSurfaceAvailable] is called.
     *
     * Validated by code inspection (explicit throw in the getter) and by
     * manual integration test on an Android emulator.
     */
    @Test
    fun `AndroidWindow rawWindowHandle est documente comme levant IllegalStateException avant surfaceCreated`() {
        val method = AndroidWindow::class.java.methods
            .firstOrNull { it.name == "getRawWindowHandle" }
        assertNotNull(method, "rawWindowHandle doit exister sur AndroidWindow")
        // The throw contract is in the implementation (surface == null → throw).
        // Only verifiable on an emulator with a real SurfaceView.
    }
}
