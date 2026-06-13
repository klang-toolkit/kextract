package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM interface for Objective-C protocol: NSScrubberFlowLayoutDelegate
 * Inherits protocols: NSScrubberDelegate
 */
interface NSScrubberFlowLayoutDelegate : NSScrubberDelegate {
    // @optional
    fun scrubber_layout_sizeForItemAtIndex(scrubber: MemorySegment, layout: MemorySegment, itemIndex: Long): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'scrubber:layout:sizeForItemAtIndex:' not implemented")
    
}

