package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM interface for Objective-C protocol: NSAccessibilityButton
 * Inherits protocols: NSAccessibilityElement
 */
interface NSAccessibilityButton {
    fun accessibilityLabel(): MemorySegment
    
    fun accessibilityPerformPress(): Boolean
    
}

