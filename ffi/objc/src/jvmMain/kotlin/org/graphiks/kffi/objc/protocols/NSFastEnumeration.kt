package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM interface for Objective-C protocol: NSFastEnumeration
 */
interface NSFastEnumeration {
    fun countByEnumeratingWithState_objects_count(state: MemorySegment, buffer: MemorySegment, len: Long): Long
    
}

