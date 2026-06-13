package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM interface for Objective-C protocol: CAAction
 */
interface CAAction {
    fun runActionForKey_object_arguments(event: MemorySegment, anObject: MemorySegment, dict: MemorySegment): Unit
    
}

