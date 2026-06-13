package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM interface for Objective-C protocol: NSMachPortDelegate
 * Inherits protocols: NSPortDelegate
 */
interface NSMachPortDelegate : NSPortDelegate {
    // @optional
    fun handleMachMessage(msg: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'handleMachMessage:' not implemented")
    
}

