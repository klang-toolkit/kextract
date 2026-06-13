package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM interface for Objective-C protocol: NSColorChanging
 * Inherits protocols: NSObject
 */
interface NSColorChanging {
    fun changeColor(sender: MemorySegment): Unit
    
}

