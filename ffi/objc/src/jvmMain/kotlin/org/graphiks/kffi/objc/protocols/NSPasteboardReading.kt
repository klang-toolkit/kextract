package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM interface for Objective-C protocol: NSPasteboardReading
 * Inherits protocols: NSObject
 */
interface NSPasteboardReading {
    /** @return NSArray<NSPasteboardType> * */
    fun readableTypesForPasteboard(pasteboard: MemorySegment): MemorySegment
    
    // @optional
    fun readingOptionsForType_pasteboard(type: MemorySegment, pasteboard: MemorySegment): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'readingOptionsForType:pasteboard:' not implemented")
    
    // @optional
    fun initWithPasteboardPropertyList_ofType(propertyList: MemorySegment, type: MemorySegment): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'initWithPasteboardPropertyList:ofType:' not implemented")
    
}

