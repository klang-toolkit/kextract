package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM interface for Objective-C protocol: NSUserInterfaceItemIdentification
 */
interface NSUserInterfaceItemIdentification {
    fun identifier(): MemorySegment
    
    fun setIdentifier(identifier: MemorySegment): Unit
    
}

