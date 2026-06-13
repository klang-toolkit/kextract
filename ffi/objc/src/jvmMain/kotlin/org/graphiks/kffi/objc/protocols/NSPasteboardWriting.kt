package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM interface for Objective-C protocol: NSPasteboardWriting
 * Inherits protocols: NSObject
 */
interface NSPasteboardWriting {
    /** @return NSArray<NSPasteboardType> * */
    fun writableTypesForPasteboard(pasteboard: MemorySegment): MemorySegment
    
    // @optional
    fun writingOptionsForType_pasteboard(type: MemorySegment, pasteboard: MemorySegment): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'writingOptionsForType:pasteboard:' not implemented")
    
    fun pasteboardPropertyListForType(type: MemorySegment): MemorySegment
    
}

