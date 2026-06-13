package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM interface for Objective-C protocol: NSDockTilePlugIn
 * Inherits protocols: NSObject
 */
interface NSDockTilePlugIn {
    fun setDockTile(dockTile: MemorySegment): Unit
    
    // @optional
    fun dockMenu(): MemorySegment =
        throw UnsupportedOperationException("Optional ObjC method 'dockMenu' not implemented")
    
}

