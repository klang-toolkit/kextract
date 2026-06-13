package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM interface for Objective-C protocol: NSTextSelectionDataSource
 * Inherits protocols: NSObject
 */
interface NSTextSelectionDataSource {
    fun enumerateSubstringsFromLocation_options_usingBlock(location: MemorySegment, options: MemorySegment, block: MemorySegment): Unit
    
    fun textRangeForSelectionGranularity_enclosingLocation(selectionGranularity: MemorySegment, location: MemorySegment): MemorySegment
    
    /** @return id<NSTextLocation> */
    fun locationFromLocation_withOffset(location: MemorySegment, offset: Long): MemorySegment
    
    fun offsetFromLocation_toLocation(from: MemorySegment, to: MemorySegment): Long
    
    fun baseWritingDirectionAtLocation(location: MemorySegment): MemorySegment
    
    fun enumerateCaretOffsetsInLineFragmentAtLocation_usingBlock(location: MemorySegment, block: MemorySegment): Unit
    
    fun lineFragmentRangeForPoint_inContainerAtLocation(point: MemorySegment, location: MemorySegment): MemorySegment
    
    // @optional
    fun enumerateContainerBoundariesFromLocation_reverse_usingBlock(location: MemorySegment, reverse: Boolean, block: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'enumerateContainerBoundariesFromLocation:reverse:usingBlock:' not implemented")
    
    // @optional
    fun textLayoutOrientationAtLocation(location: MemorySegment): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'textLayoutOrientationAtLocation:' not implemented")
    
    fun documentRange(): MemorySegment
    
}

