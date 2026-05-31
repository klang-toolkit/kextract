/**
 * JVM implementation of the event loop — delegates to AppKitEventLoop.
 *
 * This module (kadre-core) provides the `actual` declaration; the concrete
 * implementation lives in kadre-appkit via the top-level `runApp` function.
 *
 * The reflection-based indirection avoids a direct dependency from kadre-core
 * to kadre-appkit, in line with the project's modular architecture.
 */
package org.graphiks.kadre.core

/**
 * JVM implementation of [EventLoop].
 *
 * Delegates to `org.graphiks.kadre.appkit.AppKitEventLoopKt.runApp` via
 * reflection to avoid a direct kadre-core → kadre-appkit coupling.
 * This delegation is resolved at runtime: kadre-appkit must be on
 * the classpath.
 */
actual class EventLoop actual constructor() {

    /**
     * Starts the AppKit event loop and delegates callbacks to the provided handler.
     *
     * Blocking — returns only once the application closes.
     *
     * @param handler Handler for the application lifecycle and events.
     * @throws UnsupportedOperationException if kadre-appkit is not on the classpath.
     */
    actual fun runApp(handler: ApplicationHandler) {
        val klass = try {
            Class.forName("org.graphiks.kadre.appkit.AppKitEventLoopKt")
        } catch (e: ClassNotFoundException) {
            throw UnsupportedOperationException(
                "kadre-appkit introuvable sur le classpath. " +
                "Ajoutez la dépendance implementation(project(\":kadre-appkit\")).",
                e,
            )
        }
        val method = klass.getMethod("runApp", ApplicationHandler::class.java)
        method.invoke(null, handler)
    }
}
