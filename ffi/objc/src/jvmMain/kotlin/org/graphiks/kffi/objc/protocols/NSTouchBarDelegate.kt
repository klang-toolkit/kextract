package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM interface for Objective-C protocol: NSTouchBarDelegate
 * Inherits protocols: NSObject
 */
interface NSTouchBarDelegate {
    // @optional
    fun touchBar_makeItemForIdentifier(touchBar: MemorySegment, identifier: MemorySegment): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'touchBar:makeItemForIdentifier:' not implemented")
    
}

