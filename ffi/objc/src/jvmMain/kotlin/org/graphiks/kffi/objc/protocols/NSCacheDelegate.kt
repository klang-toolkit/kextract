package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM interface for Objective-C protocol: NSCacheDelegate
 * Inherits protocols: NSObject
 */
interface NSCacheDelegate {
    // @optional
    fun cache_willEvictObject(cache: MemorySegment, obj: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'cache:willEvictObject:' not implemented")
    
}

