package org.graphiks.kadre.appkit

import org.graphiks.kadre.core.ControlFlow
import org.graphiks.kadre.core.EventLoopProxy
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

/**
 * Typing tests for GRA-136. No native runtime invocation
 * (the CF downcalls require a configured macOS environment;
 *  the runtime paths are covered by the integration tests).
 */
class AppKitEventLoopProxyTest {

    @Test
    fun `AppKitEventLoopProxy implémente EventLoopProxy`() {
        assertTrue(
            EventLoopProxy::class.java.isAssignableFrom(AppKitEventLoopProxy::class.java),
            "AppKitEventLoopProxy doit implémenter EventLoopProxy",
        )
    }

    @Test
    fun `AppKitEventLoopProxy expose create dans son companion`() {
        val companionClass = AppKitEventLoopProxy.Companion::class.java
        val method = companionClass.declaredMethods
            .firstOrNull { it.name == "create" }
        assertNotNull(method, "AppKitEventLoopProxy.Companion doit exposer create()")
        // The actual return is AppKitEventLoopProxy (a subtype of EventLoopProxy).
        assertTrue(
            EventLoopProxy::class.java.isAssignableFrom(method.returnType),
            "create() doit retourner un EventLoopProxy",
        )
    }

    @Test
    fun `wakeUp existe et retourne void`() {
        val method = AppKitEventLoopProxy::class.java.methods
            .firstOrNull { it.name == "wakeUp" }
        assertNotNull(method, "AppKitEventLoopProxy doit avoir wakeUp()")
        assertEquals(Void.TYPE, method.returnType)
        assertEquals(0, method.parameterCount)
    }

    @Test
    fun `AppKitEventLoop createProxy ne lève plus UnsupportedOperationException (GRA-136)`() {
        val method = AppKitEventLoop::class.java.methods
            .firstOrNull { it.name == "createProxy" }
        assertNotNull(method, "AppKitEventLoop doit avoir createProxy()")
        assertEquals(EventLoopProxy::class.java, method.returnType)
    }

    @Test
    fun `CFRunLoopRedrawObserver onBeforeWaiting accepte ControlFlow WaitUntil`() {
        val cf = ControlFlow.WaitUntil(System.currentTimeMillis() + 100L)
        assertTrue(cf is ControlFlow.WaitUntil)
        assertTrue(cf.instant > 0)
    }
}
