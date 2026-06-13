package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSPurgeableData
 * Superclass: NSMutableData
 * Protocols: NSDiscardableContent
 */
open class NSPurgeableData(override val ptr: MemorySegment) : NSMutableData(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSPurgeableData") }
        
    }
    
}

