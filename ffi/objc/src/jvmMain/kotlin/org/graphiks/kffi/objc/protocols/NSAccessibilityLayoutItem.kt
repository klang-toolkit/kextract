package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM interface for Objective-C protocol: NSAccessibilityLayoutItem
 * Inherits protocols: NSAccessibilityGroup
 */
interface NSAccessibilityLayoutItem : NSAccessibilityGroup {
    // @optional
    fun setAccessibilityFrame(frame: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'setAccessibilityFrame:' not implemented")
    
}

