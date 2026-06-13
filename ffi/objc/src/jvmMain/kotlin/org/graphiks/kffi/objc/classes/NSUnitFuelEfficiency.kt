package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSUnitFuelEfficiency
 * Superclass: NSDimension
 * Protocols: NSSecureCoding
 */
open class NSUnitFuelEfficiency(override val ptr: MemorySegment) : NSDimension(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSUnitFuelEfficiency") }
        
        fun litersPer100Kilometers(): MemorySegment {
            val sel = ObjCRuntime.sel("litersPer100Kilometers")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun milesPerImperialGallon(): MemorySegment {
            val sel = ObjCRuntime.sel("milesPerImperialGallon")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun milesPerGallon(): MemorySegment {
            val sel = ObjCRuntime.sel("milesPerGallon")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
    }
    
    // @property litersPer100Kilometers
    open fun litersPer100Kilometers(): MemorySegment {
        val sel = ObjCRuntime.sel("litersPer100Kilometers")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property milesPerImperialGallon
    open fun milesPerImperialGallon(): MemorySegment {
        val sel = ObjCRuntime.sel("milesPerImperialGallon")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property milesPerGallon
    open fun milesPerGallon(): MemorySegment {
        val sel = ObjCRuntime.sel("milesPerGallon")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
}

