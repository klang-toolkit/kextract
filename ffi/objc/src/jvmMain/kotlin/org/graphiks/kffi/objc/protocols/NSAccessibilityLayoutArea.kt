package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM interface for Objective-C protocol: NSAccessibilityLayoutArea
 * Inherits protocols: NSAccessibilityGroup
 */
interface NSAccessibilityLayoutArea : NSAccessibilityGroup {
    fun accessibilityLabel(): MemorySegment
    
    fun accessibilityChildren(): MemorySegment
    
    fun accessibilitySelectedChildren(): MemorySegment
    
    fun accessibilityFocusedUIElement(): MemorySegment
    
}

