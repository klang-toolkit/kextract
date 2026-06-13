package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSUnitEnergy
 * Superclass: NSDimension
 * Protocols: NSSecureCoding
 */
open class NSUnitEnergy(override val ptr: MemorySegment) : NSDimension(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSUnitEnergy") }
        
        fun kilojoules(): MemorySegment {
            val sel = ObjCRuntime.sel("kilojoules")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun joules(): MemorySegment {
            val sel = ObjCRuntime.sel("joules")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun kilocalories(): MemorySegment {
            val sel = ObjCRuntime.sel("kilocalories")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun calories(): MemorySegment {
            val sel = ObjCRuntime.sel("calories")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun kilowattHours(): MemorySegment {
            val sel = ObjCRuntime.sel("kilowattHours")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
    }
    
    // @property kilojoules
    open fun kilojoules(): MemorySegment {
        val sel = ObjCRuntime.sel("kilojoules")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property joules
    open fun joules(): MemorySegment {
        val sel = ObjCRuntime.sel("joules")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property kilocalories
    open fun kilocalories(): MemorySegment {
        val sel = ObjCRuntime.sel("kilocalories")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property calories
    open fun calories(): MemorySegment {
        val sel = ObjCRuntime.sel("calories")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property kilowattHours
    open fun kilowattHours(): MemorySegment {
        val sel = ObjCRuntime.sel("kilowattHours")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
}

