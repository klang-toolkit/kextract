package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM interface for Objective-C protocol: NSAlertDelegate
 * Inherits protocols: NSObject
 */
interface NSAlertDelegate {
    // @optional
    fun alertShowHelp(alert: MemorySegment): Boolean =
        throw UnsupportedOperationException("Optional ObjC method 'alertShowHelp:' not implemented")
    
}

