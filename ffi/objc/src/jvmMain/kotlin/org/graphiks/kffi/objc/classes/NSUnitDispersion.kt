package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSUnitDispersion
 * Superclass: NSDimension
 * Protocols: NSSecureCoding
 */
open class NSUnitDispersion(override val ptr: MemorySegment) : NSDimension(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSUnitDispersion") }
        
        fun partsPerMillion(): MemorySegment {
            val sel = ObjCRuntime.sel("partsPerMillion")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
    }
    
    // @property partsPerMillion
    open fun partsPerMillion(): MemorySegment {
        val sel = ObjCRuntime.sel("partsPerMillion")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
}

