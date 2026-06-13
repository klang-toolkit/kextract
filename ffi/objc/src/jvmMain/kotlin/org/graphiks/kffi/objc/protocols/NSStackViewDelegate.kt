package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM interface for Objective-C protocol: NSStackViewDelegate
 * Inherits protocols: NSObject
 */
interface NSStackViewDelegate {
    // @optional
    fun stackView_willDetachViews(stackView: MemorySegment, views: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'stackView:willDetachViews:' not implemented")
    
    // @optional
    fun stackView_didReattachViews(stackView: MemorySegment, views: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'stackView:didReattachViews:' not implemented")
    
}

