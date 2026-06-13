package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM interface for Objective-C protocol: NSExtensionRequestHandling
 * Inherits protocols: NSObject
 */
interface NSExtensionRequestHandling {
    fun beginRequestWithExtensionContext(context: MemorySegment): Unit
    
}

