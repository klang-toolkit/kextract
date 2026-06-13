package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM interface for Objective-C protocol: NSStreamDelegate
 * Inherits protocols: NSObject
 */
interface NSStreamDelegate {
    // @optional
    fun stream_handleEvent(aStream: MemorySegment, eventCode: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'stream:handleEvent:' not implemented")
    
}

