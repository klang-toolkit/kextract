package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM interface for Objective-C protocol: NSDrawerDelegate
 * Inherits protocols: NSObject
 */
interface NSDrawerDelegate {
    // @optional
    fun drawerShouldOpen(sender: MemorySegment): Boolean =
        throw UnsupportedOperationException("Optional ObjC method 'drawerShouldOpen:' not implemented")
    
    // @optional
    fun drawerShouldClose(sender: MemorySegment): Boolean =
        throw UnsupportedOperationException("Optional ObjC method 'drawerShouldClose:' not implemented")
    
    // @optional
    fun drawerWillResizeContents_toSize(sender: MemorySegment, contentSize: MemorySegment): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'drawerWillResizeContents:toSize:' not implemented")
    
    // @optional
    fun drawerWillOpen(notification: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'drawerWillOpen:' not implemented")
    
    // @optional
    fun drawerDidOpen(notification: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'drawerDidOpen:' not implemented")
    
    // @optional
    fun drawerWillClose(notification: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'drawerWillClose:' not implemented")
    
    // @optional
    fun drawerDidClose(notification: MemorySegment): Unit =
        throw UnsupportedOperationException("Optional ObjC method 'drawerDidClose:' not implemented")
    
}

