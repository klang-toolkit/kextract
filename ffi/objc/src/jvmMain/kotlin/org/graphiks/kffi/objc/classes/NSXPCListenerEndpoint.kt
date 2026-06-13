package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSXPCListenerEndpoint
 * Superclass: NSObject
 * Protocols: NSSecureCoding
 */
open class NSXPCListenerEndpoint(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSXPCListenerEndpoint") }
        
    }
    
}

