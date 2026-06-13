package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM interface for Objective-C protocol: NSURLHandleClient
 */
interface NSURLHandleClient {
    fun URLHandle_resourceDataDidBecomeAvailable(sender: MemorySegment, newBytes: MemorySegment): Unit
    
    fun URLHandleResourceDidBeginLoading(sender: MemorySegment): Unit
    
    fun URLHandleResourceDidFinishLoading(sender: MemorySegment): Unit
    
    fun URLHandleResourceDidCancelLoading(sender: MemorySegment): Unit
    
    fun URLHandle_resourceDidFailLoadingWithReason(sender: MemorySegment, reason: MemorySegment): Unit
    
}

