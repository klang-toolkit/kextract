package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM interface for Objective-C protocol: NSAppearanceCustomization
 * Inherits protocols: NSObject
 */
interface NSAppearanceCustomization {
    fun appearance(): MemorySegment
    
    fun setAppearance(appearance: MemorySegment): Unit
    
    fun effectiveAppearance(): MemorySegment
    
}

