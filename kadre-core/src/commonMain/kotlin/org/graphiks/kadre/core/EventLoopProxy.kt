/**
 * Proxy allowing interaction with the event loop from another thread.
 *
 * Scope: pure Kotlin interface, no native reference.
 */
package org.graphiks.kadre.core

/**
 * Thread-safe proxy to the main event loop.
 *
 * Allows secondary threads to wake up the event loop
 * without having direct access to it.
 */
interface EventLoopProxy {

    /**
     * Wakes up the event loop if it is waiting.
     *
     * This method is safe to call from any thread.
     */
    fun wakeUp()
}
