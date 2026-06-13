package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM interface for Objective-C protocol: NSEditorRegistration
 * Inherits protocols: NSObject
 */
interface NSEditorRegistration {
    // @optional
    fun objectDidBeginEditing(editor: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'objectDidBeginEditing:' not implemented")
    
    // @optional
    fun objectDidEndEditing(editor: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'objectDidEndEditing:' not implemented")
    
}

