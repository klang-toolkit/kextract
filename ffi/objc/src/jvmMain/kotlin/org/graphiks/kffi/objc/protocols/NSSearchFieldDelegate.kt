package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM interface for Objective-C protocol: NSSearchFieldDelegate
 * Inherits protocols: NSTextFieldDelegate
 */
interface NSSearchFieldDelegate : NSTextFieldDelegate {
    // @optional
    fun searchFieldDidStartSearching(sender: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'searchFieldDidStartSearching:' not implemented")
    
    // @optional
    fun searchFieldDidEndSearching(sender: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'searchFieldDidEndSearching:' not implemented")
    
}

