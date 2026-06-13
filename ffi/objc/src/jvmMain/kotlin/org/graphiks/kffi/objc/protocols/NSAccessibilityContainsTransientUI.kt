package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM interface for Objective-C protocol: NSAccessibilityContainsTransientUI
 * Inherits protocols: NSAccessibilityElement
 */
interface NSAccessibilityContainsTransientUI {
    fun accessibilityPerformShowAlternateUI(): Boolean
    
    fun accessibilityPerformShowDefaultUI(): Boolean
    
    fun isAccessibilityAlternateUIVisible(): Boolean
    
}

