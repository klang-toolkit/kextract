package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM interface for Objective-C protocol: NSAccessibilityStepper
 * Inherits protocols: NSAccessibilityElement
 */
interface NSAccessibilityStepper {
    fun accessibilityLabel(): MemorySegment
    
    fun accessibilityPerformIncrement(): Boolean
    
    fun accessibilityPerformDecrement(): Boolean
    
    // @optional
    fun accessibilityValue(): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'accessibilityValue' not implemented")
    
}

