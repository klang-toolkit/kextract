package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM interface for Objective-C protocol: NSWindowRestoration
 * Inherits protocols: NSObject
 */
interface NSWindowRestoration {
    fun restoreWindowWithIdentifier_state_completionHandler(identifier: MemorySegment, state: MemorySegment, completionHandler: MemorySegment): Unit
    
}

