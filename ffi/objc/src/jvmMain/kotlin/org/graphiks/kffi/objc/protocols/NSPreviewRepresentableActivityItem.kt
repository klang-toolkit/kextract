package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM interface for Objective-C protocol: NSPreviewRepresentableActivityItem
 * Inherits protocols: NSObject
 */
interface NSPreviewRepresentableActivityItem {
    fun item(): MemorySegment
    
    // @optional
    fun title(): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'title' not implemented")
    
    // @optional
    fun imageProvider(): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'imageProvider' not implemented")
    
    // @optional
    fun iconProvider(): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'iconProvider' not implemented")
    
}

