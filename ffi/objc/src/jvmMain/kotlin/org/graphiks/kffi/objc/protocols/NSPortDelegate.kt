package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM interface for Objective-C protocol: NSPortDelegate
 * Inherits protocols: NSObject
 */
interface NSPortDelegate {
    // @optional
    fun handlePortMessage(message: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'handlePortMessage:' not implemented")
    
}

