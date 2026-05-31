/**
 * Assertion de thread principal pour les appels AppKit.
 *
 * AppKit (NSApplication, NSWindow, etc.) doit être invoqué exclusivement
 * depuis le thread principal du processus. Cette utilitaire interroge
 * `+[NSThread isMainThread]` via le runtime ObjC pour valider l'invariant.
 */
package org.graphiks.kadre.appkit

import org.graphiks.kadre.appkit.bindings.ObjCRuntime
import java.lang.foreign.ValueLayout

object MainThreadCheck {
    /**
     * Lance [IllegalArgumentException] si l'appel n'est pas effectué depuis
     * le thread principal du processus.
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
