/**
 * Main-thread assertion for AppKit calls.
 *
 * AppKit (NSApplication, NSWindow, etc.) must be invoked exclusively
 * from the process main thread. This utility queries
 * `+[NSThread isMainThread]` via the ObjC runtime to validate the invariant.
 */
package org.graphiks.kadre.appkit

import org.graphiks.kadre.ffi.objc.ObjCRuntime
import java.lang.foreign.ValueLayout

object MainThreadCheck {
    /**
     * Throws [IllegalArgumentException] if the call is not made from
     * the process main thread.
     */
    fun require() {
        val isMainThread = ObjCRuntime.msgSend(
            ValueLayout.JAVA_BOOLEAN,
            ObjCRuntime.getClass("NSThread"),
            ObjCRuntime.sel("isMainThread"),
        ) as Boolean
        require(isMainThread) { "This operation must be called on the main thread" }
    }
}
