package org.graphiks.kadre.android

import org.graphiks.kadre.core.ActiveEventLoop
import org.graphiks.kadre.core.RawWindowHandle
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
    fun `AndroidWindow implements Window`() {
        assertTrue(
            Window::class.java.isAssignableFrom(AndroidWindow::class.java),
            "AndroidWindow must implement Window",
        )
    }

    @Test
    fun `AndroidEventLoop implements ActiveEventLoop`() {
        assertTrue(
            ActiveEventLoop::class.java.isAssignableFrom(AndroidEventLoop::class.java),
            "AndroidEventLoop must implement ActiveEventLoop",
        )
    }

    // ── createWindow verification ─────────────────────────────────────────

    @Test
    fun `AndroidEventLoop exposes createWindow returning Window`() {
        val method = AndroidEventLoop::class.java.methods
            .firstOrNull { it.name == "createWindow" && it.parameterCount == 1 }
        assertNotNull(method, "AndroidEventLoop.createWindow(WindowAttributes) must exist")
        assertTrue(
            Window::class.java.isAssignableFrom(method.returnType),
            "createWindow must return Window (or a subtype)",
        )
        assertEquals(
            WindowAttributes::class.java,
            method.parameterTypes[0],
            "createWindow must accept WindowAttributes",
        )
    }

    // ── pendingWindow verification ────────────────────────────────────────

    @Test
    fun `AndroidEventLoop exposes pendingWindow of nullable AndroidWindow type`() {
        // Kotlin `internal` members are mangled on the JVM: the getter generated for
        // `internal var pendingWindow` is named `getPendingWindow$<module>` and
        // is not accessible via `Class.methods` (public only).
        // So we go through `declaredMethods` with a prefix match.
        val method = AndroidEventLoop::class.java.declaredMethods
            .firstOrNull { it.name.startsWith("getPendingWindow") }
        assertNotNull(method, "AndroidEventLoop must expose pendingWindow (mangled internal getter)")
        assertEquals(
            AndroidWindow::class.java,
            method.returnType,
            "pendingWindow must be of type AndroidWindow",
        )
    }

    // ── Surface lifecycle verification ───────────────────────────

    @Test
    fun `AndroidEventLoop exposes onSurfaceCreated with Surface parameter`() {
        // `internal fun` is mangled on the JVM: onSurfaceCreated → onSurfaceCreated$<module>.
        // We use startsWith to be insensitive to the module suffix.
        val method = AndroidEventLoop::class.java.declaredMethods
            .firstOrNull { it.name.startsWith("onSurfaceCreated") }
        assertNotNull(method, "AndroidEventLoop.onSurfaceCreated(Surface) must exist")
        assertEquals(1, method.parameterCount, "onSurfaceCreated must accept a single parameter")
        assertEquals(
            "android.view.Surface",
            method.parameterTypes[0].name,
            "onSurfaceCreated must accept android.view.Surface",
        )
    }

    @Test
    fun `AndroidEventLoop exposes onSurfaceDestroyed without parameter`() {
        // Same reason: Kotlin `internal` mangling suffix.
        val method = AndroidEventLoop::class.java.declaredMethods
            .firstOrNull { it.name.startsWith("onSurfaceDestroyed") }
        assertNotNull(method, "AndroidEventLoop.onSurfaceDestroyed() must exist")
        assertEquals(0, method.parameterCount, "onSurfaceDestroyed must have no parameter")
    }

    @Test
    fun `AndroidWindow exposes onSurfaceAvailable with Surface parameter`() {
        // `internal fun` in AndroidWindow → mangled name, we search by prefix.
        val method = AndroidWindow::class.java.declaredMethods
            .firstOrNull { it.name.startsWith("onSurfaceAvailable") }
        assertNotNull(method, "AndroidWindow.onSurfaceAvailable(Surface) must exist")
        assertEquals(1, method.parameterCount)
        assertEquals(
            "android.view.Surface",
            method.parameterTypes[0].name,
        )
    }

    @Test
    fun `AndroidWindow exposes onSurfaceReleased without parameter`() {
        // `internal fun` in AndroidWindow → mangled name, we search by prefix.
        val method = AndroidWindow::class.java.declaredMethods
            .firstOrNull { it.name.startsWith("onSurfaceReleased") }
        assertNotNull(method, "AndroidWindow.onSurfaceReleased() must exist")
        assertEquals(0, method.parameterCount)
    }

    // ── rawWindowHandle verification ─────────────────────────────────────

    @Test
    fun `AndroidWindow rawWindowHandle returns RawWindowHandle`() {
        val method = AndroidWindow::class.java.methods
            .firstOrNull { it.name == "getRawWindowHandle" }
        assertNotNull(method, "AndroidWindow must expose rawWindowHandle")
        assertEquals(
            RawWindowHandle::class.java,
            method.returnType,
            "rawWindowHandle must return RawWindowHandle (strongly typed since R0.1)",
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
    fun `AndroidWindow rawWindowHandle is documented as throwing IllegalStateException before surfaceCreated`() {
        val method = AndroidWindow::class.java.methods
            .firstOrNull { it.name == "getRawWindowHandle" }
        assertNotNull(method, "rawWindowHandle must exist on AndroidWindow")
        // The throw contract is in the implementation (surface == null → throw).
        // Only verifiable on an emulator with a real SurfaceView.
    }
}
