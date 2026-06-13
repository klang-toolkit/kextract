package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM interface for Objective-C protocol: NSAccessibilityImage
 * Inherits protocols: NSAccessibilityElement
 */
interface NSAccessibilityImage {
    fun accessibilityLabel(): MemorySegment
    
}

