package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM interface for Objective-C protocol: NSAccessibilityNavigableStaticText
 * Inherits protocols: NSAccessibilityStaticText
 */
interface NSAccessibilityNavigableStaticText : NSAccessibilityStaticText {
    fun accessibilityStringForRange(range: MemorySegment): MemorySegment
    
    fun accessibilityLineForIndex(index: Long): Long
    
    fun accessibilityRangeForLine(lineNumber: Long): MemorySegment
    
    fun accessibilityFrameForRange(range: MemorySegment): MemorySegment
    
}

