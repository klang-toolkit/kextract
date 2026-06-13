package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM interface for Objective-C protocol: CALayoutManager
 * Inherits protocols: NSObject
 */
interface CALayoutManager {
    // @optional
    fun preferredSizeOfLayer(layer: MemorySegment): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'preferredSizeOfLayer:' not implemented")
    
    // @optional
    fun invalidateLayoutOfLayer(layer: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'invalidateLayoutOfLayer:' not implemented")
    
    // @optional
    fun layoutSublayersOfLayer(layer: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'layoutSublayersOfLayer:' not implemented")
    
}

