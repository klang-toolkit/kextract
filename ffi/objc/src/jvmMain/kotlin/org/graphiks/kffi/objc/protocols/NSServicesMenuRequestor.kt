package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM interface for Objective-C protocol: NSServicesMenuRequestor
 * Inherits protocols: NSObject
 */
interface NSServicesMenuRequestor {
    // @optional
    fun writeSelectionToPasteboard_types(pboard: MemorySegment, types: MemorySegment): Boolean =
        throw UnsupportedOperationException("Optional ObjC method 'writeSelectionToPasteboard:types:' not implemented")
    
    // @optional
    fun readSelectionFromPasteboard(pboard: MemorySegment): Boolean =
        throw UnsupportedOperationException("Optional ObjC method 'readSelectionFromPasteboard:' not implemented")
    
}

