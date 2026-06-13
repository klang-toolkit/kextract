package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSUnitIlluminance
 * Superclass: NSDimension
 * Protocols: NSSecureCoding
 */
open class NSUnitIlluminance(override val ptr: MemorySegment) : NSDimension(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSUnitIlluminance") }
        
        fun lux(): MemorySegment {
            val sel = ObjCRuntime.sel("lux")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
    }
    
    // @property lux
    open fun lux(): MemorySegment {
        val sel = ObjCRuntime.sel("lux")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
}

