package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM interface for Objective-C protocol: CALayerDelegate
 * Inherits protocols: NSObject
 */
interface CALayerDelegate {
    // @optional
    fun displayLayer(layer: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'displayLayer:' not implemented")
    
    // @optional
    fun drawLayer_inContext(layer: MemorySegment, ctx: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'drawLayer:inContext:' not implemented")
    
    // @optional
    fun layerWillDraw(layer: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'layerWillDraw:' not implemented")
    
    // @optional
    fun layoutSublayersOfLayer(layer: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'layoutSublayersOfLayer:' not implemented")
    
    /** @return id<CAAction> */
    // @optional
    fun actionForLayer_forKey(layer: MemorySegment, event: MemorySegment): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'actionForLayer:forKey:' not implemented")
    
}

