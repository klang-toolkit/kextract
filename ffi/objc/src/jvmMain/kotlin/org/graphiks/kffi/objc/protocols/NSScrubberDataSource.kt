package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM interface for Objective-C protocol: NSScrubberDataSource
 * Inherits protocols: NSObject
 */
interface NSScrubberDataSource {
    fun numberOfItemsForScrubber(scrubber: MemorySegment): Long
    
    fun scrubber_viewForItemAtIndex(scrubber: MemorySegment, index: Long): MemorySegment
    
}

