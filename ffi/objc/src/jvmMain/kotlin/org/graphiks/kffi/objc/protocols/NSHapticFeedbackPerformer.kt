package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM interface for Objective-C protocol: NSHapticFeedbackPerformer
 * Inherits protocols: NSObject
 */
interface NSHapticFeedbackPerformer {
    fun performFeedbackPattern_performanceTime(pattern: MemorySegment, performanceTime: MemorySegment): Unit
    
}

