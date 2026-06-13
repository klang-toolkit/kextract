package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM interface for Objective-C protocol: NSPasteboardTypeOwner
 * Inherits protocols: NSObject
 */
interface NSPasteboardTypeOwner {
    fun pasteboard_provideDataForType(sender: MemorySegment, type: MemorySegment): Unit
    
    // @optional
    fun pasteboardChangedOwner(sender: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'pasteboardChangedOwner:' not implemented")
    
}

