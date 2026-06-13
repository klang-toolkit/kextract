package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM interface for Objective-C protocol: NSTextElementProvider
 * Inherits protocols: NSObject
 */
interface NSTextElementProvider {
    /** @return id<NSTextLocation> */
    fun enumerateTextElementsFromLocation_options_usingBlock(textLocation: MemorySegment, options: MemorySegment, block: MemorySegment): MemorySegment
    
    fun replaceContentsInRange_withTextElements(range: MemorySegment, textElements: MemorySegment): Unit
    
    fun synchronizeToBackingStore(completionHandler: MemorySegment): Unit
    
    /** @return id<NSTextLocation> */
    // @optional
    fun locationFromLocation_withOffset(location: MemorySegment, offset: Long): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'locationFromLocation:withOffset:' not implemented")
    
    // @optional
    fun offsetFromLocation_toLocation(from: MemorySegment, to: MemorySegment): Long =
        throw UnsupportedOperationException("Optional ObjC method 'offsetFromLocation:toLocation:' not implemented")
    
    // @optional
    fun adjustedRangeFromRange_forEditingTextSelection(textRange: MemorySegment, forEditingTextSelection: Boolean): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'adjustedRangeFromRange:forEditingTextSelection:' not implemented")
    
    fun documentRange(): MemorySegment
    
}

