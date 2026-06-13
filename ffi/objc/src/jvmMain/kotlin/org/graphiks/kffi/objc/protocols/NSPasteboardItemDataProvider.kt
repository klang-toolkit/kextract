package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM interface for Objective-C protocol: NSPasteboardItemDataProvider
 * Inherits protocols: NSObject
 */
interface NSPasteboardItemDataProvider {
    fun pasteboard_item_provideDataForType(pasteboard: MemorySegment, item: MemorySegment, type: MemorySegment): Unit
    
    // @optional
    fun pasteboardFinishedWithDataProvider(pasteboard: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'pasteboardFinishedWithDataProvider:' not implemented")
    
}

