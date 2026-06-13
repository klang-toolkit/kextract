package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM interface for Objective-C protocol: NSTextStorageDelegate
 * Inherits protocols: NSObject
 */
interface NSTextStorageDelegate {
    // @optional
    fun textStorage_willProcessEditing_range_changeInLength(textStorage: MemorySegment, editedMask: MemorySegment, editedRange: MemorySegment, delta: Long): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'textStorage:willProcessEditing:range:changeInLength:' not implemented")
    
    // @optional
    fun textStorage_didProcessEditing_range_changeInLength(textStorage: MemorySegment, editedMask: MemorySegment, editedRange: MemorySegment, delta: Long): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'textStorage:didProcessEditing:range:changeInLength:' not implemented")
    
}

