package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM interface for Objective-C protocol: NSTextLocation
 * Inherits protocols: NSObject
 */
interface NSTextLocation {
    fun compare(location: MemorySegment): MemorySegment
    
}

