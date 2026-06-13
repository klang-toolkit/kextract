package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM interface for Objective-C protocol: NSUserInterfaceCompression
 */
interface NSUserInterfaceCompression {
    fun compressWithPrioritizedCompressionOptions(prioritizedOptions: MemorySegment): Unit
    
    fun minimumSizeWithPrioritizedCompressionOptions(prioritizedOptions: MemorySegment): MemorySegment
    
    fun activeCompressionOptions(): MemorySegment
    
}

