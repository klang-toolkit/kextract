package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM interface for Objective-C protocol: NSUserActivityDelegate
 * Inherits protocols: NSObject
 */
interface NSUserActivityDelegate {
    // @optional
    fun userActivityWillSave(userActivity: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'userActivityWillSave:' not implemented")
    
    // @optional
    fun userActivityWasContinued(userActivity: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'userActivityWasContinued:' not implemented")
    
    // @optional
    fun userActivity_didReceiveInputStream_outputStream(userActivity: MemorySegment, inputStream: MemorySegment, outputStream: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'userActivity:didReceiveInputStream:outputStream:' not implemented")
    
}

