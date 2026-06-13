package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM interface for Objective-C protocol: NSViewToolTipOwner
 * Inherits protocols: NSObject
 */
interface NSViewToolTipOwner {
    fun view_stringForToolTip_point_userData(view: MemorySegment, tag: Long, point: MemorySegment, `data`: MemorySegment): MemorySegment
    
}

