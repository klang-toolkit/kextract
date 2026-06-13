package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM interface for Objective-C protocol: NSInputServerMouseTracker
 */
interface NSInputServerMouseTracker {
    fun mouseDownOnCharacterIndex_atCoordinate_withModifier_client(index: Long, point: MemorySegment, flags: Long, sender: MemorySegment): Boolean
    
    fun mouseDraggedOnCharacterIndex_atCoordinate_withModifier_client(index: Long, point: MemorySegment, flags: Long, sender: MemorySegment): Boolean
    
    fun mouseUpOnCharacterIndex_atCoordinate_withModifier_client(index: Long, point: MemorySegment, flags: Long, sender: MemorySegment): Unit
    
}

