package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSMessagePort
 * Superclass: NSPort
 */
open class NSMessagePort(override val ptr: MemorySegment) : NSPort(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSMessagePort") }
        
    }
    
    
    // ── Instance variables (direct field access not supported via Panama) ──
    // ivar: _port: MemorySegment
    // ivar: _delegate: MemorySegment
}

