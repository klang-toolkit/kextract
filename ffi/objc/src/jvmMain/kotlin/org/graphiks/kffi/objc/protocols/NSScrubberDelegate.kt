package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM interface for Objective-C protocol: NSScrubberDelegate
 * Inherits protocols: NSObject
 */
interface NSScrubberDelegate {
    // @optional
    fun scrubber_didSelectItemAtIndex(scrubber: MemorySegment, selectedIndex: Long): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'scrubber:didSelectItemAtIndex:' not implemented")
    
    // @optional
    fun scrubber_didHighlightItemAtIndex(scrubber: MemorySegment, highlightedIndex: Long): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'scrubber:didHighlightItemAtIndex:' not implemented")
    
    // @optional
    fun scrubber_didChangeVisibleRange(scrubber: MemorySegment, visibleRange: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'scrubber:didChangeVisibleRange:' not implemented")
    
    // @optional
    fun didBeginInteractingWithScrubber(scrubber: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'didBeginInteractingWithScrubber:' not implemented")
    
    // @optional
    fun didFinishInteractingWithScrubber(scrubber: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'didFinishInteractingWithScrubber:' not implemented")
    
    // @optional
    fun didCancelInteractingWithScrubber(scrubber: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'didCancelInteractingWithScrubber:' not implemented")
    
}

