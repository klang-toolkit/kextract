package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSUnitElectricCharge
 * Superclass: NSDimension
 * Protocols: NSSecureCoding
 */
open class NSUnitElectricCharge(override val ptr: MemorySegment) : NSDimension(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSUnitElectricCharge") }
        
        fun coulombs(): MemorySegment {
            val sel = ObjCRuntime.sel("coulombs")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun megaampereHours(): MemorySegment {
            val sel = ObjCRuntime.sel("megaampereHours")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun kiloampereHours(): MemorySegment {
            val sel = ObjCRuntime.sel("kiloampereHours")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun ampereHours(): MemorySegment {
            val sel = ObjCRuntime.sel("ampereHours")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun milliampereHours(): MemorySegment {
            val sel = ObjCRuntime.sel("milliampereHours")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun microampereHours(): MemorySegment {
            val sel = ObjCRuntime.sel("microampereHours")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
    }
    
    // @property coulombs
    open fun coulombs(): MemorySegment {
        val sel = ObjCRuntime.sel("coulombs")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property megaampereHours
    open fun megaampereHours(): MemorySegment {
        val sel = ObjCRuntime.sel("megaampereHours")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property kiloampereHours
    open fun kiloampereHours(): MemorySegment {
        val sel = ObjCRuntime.sel("kiloampereHours")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property ampereHours
    open fun ampereHours(): MemorySegment {
        val sel = ObjCRuntime.sel("ampereHours")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property milliampereHours
    open fun milliampereHours(): MemorySegment {
        val sel = ObjCRuntime.sel("milliampereHours")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property microampereHours
    open fun microampereHours(): MemorySegment {
        val sel = ObjCRuntime.sel("microampereHours")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
}

