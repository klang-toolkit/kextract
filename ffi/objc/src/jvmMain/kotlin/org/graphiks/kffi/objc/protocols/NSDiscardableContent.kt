package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM interface for Objective-C protocol: NSDiscardableContent
 */
interface NSDiscardableContent {
    fun beginContentAccess(): Boolean
    
    fun endContentAccess(): Unit
    
    fun discardContentIfPossible(): Unit
    
    fun isContentDiscarded(): Boolean
    
}

