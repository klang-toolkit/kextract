package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM interface for Objective-C protocol: NSAccessibilitySwitch
 * Inherits protocols: NSAccessibilityButton
 */
interface NSAccessibilitySwitch : NSAccessibilityButton {
    fun accessibilityValue(): MemorySegment
    
    // @optional
    fun accessibilityPerformIncrement(): Boolean =
        throw UnsupportedOperationException("Optional ObjC method 'accessibilityPerformIncrement' not implemented")
    
    // @optional
    fun accessibilityPerformDecrement(): Boolean =
        throw UnsupportedOperationException("Optional ObjC method 'accessibilityPerformDecrement' not implemented")
    
}

