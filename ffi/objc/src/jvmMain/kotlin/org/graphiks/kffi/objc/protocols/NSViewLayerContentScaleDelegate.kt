package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM interface for Objective-C protocol: NSViewLayerContentScaleDelegate
 * Inherits protocols: NSObject
 */
interface NSViewLayerContentScaleDelegate {
    // @optional
    fun layer_shouldInheritContentsScale_fromWindow(layer: MemorySegment, newScale: Double, window: MemorySegment): Boolean =
        throw UnsupportedOperationException("Optional ObjC method 'layer:shouldInheritContentsScale:fromWindow:' not implemented")
    
}

