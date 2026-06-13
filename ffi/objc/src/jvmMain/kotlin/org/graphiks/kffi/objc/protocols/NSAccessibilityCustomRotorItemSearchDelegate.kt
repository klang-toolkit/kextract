package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM interface for Objective-C protocol: NSAccessibilityCustomRotorItemSearchDelegate
 * Inherits protocols: NSObject
 */
interface NSAccessibilityCustomRotorItemSearchDelegate {
    fun rotor_resultForSearchParameters(rotor: MemorySegment, searchParameters: MemorySegment): MemorySegment
    
}

