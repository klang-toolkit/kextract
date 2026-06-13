package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM interface for Objective-C protocol: NSUserActivityRestoring
 * Inherits protocols: NSObject
 */
interface NSUserActivityRestoring {
    fun restoreUserActivityState(userActivity: MemorySegment): Unit
    
}

