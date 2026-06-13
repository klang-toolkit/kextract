package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM interface for Objective-C protocol: NSTextDelegate
 * Inherits protocols: NSObject
 */
interface NSTextDelegate {
    // @optional
    fun textShouldBeginEditing(textObject: MemorySegment): Boolean =
        throw UnsupportedOperationException("Optional ObjC method 'textShouldBeginEditing:' not implemented")
    
    // @optional
    fun textShouldEndEditing(textObject: MemorySegment): Boolean =
        throw UnsupportedOperationException("Optional ObjC method 'textShouldEndEditing:' not implemented")
    
    // @optional
    fun textDidBeginEditing(notification: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'textDidBeginEditing:' not implemented")
    
    // @optional
    fun textDidEndEditing(notification: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'textDidEndEditing:' not implemented")
    
    // @optional
    fun textDidChange(notification: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'textDidChange:' not implemented")
    
}

