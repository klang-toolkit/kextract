package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSUnitElectricCurrent
 * Superclass: NSDimension
 * Protocols: NSSecureCoding
 */
open class NSUnitElectricCurrent(override val ptr: MemorySegment) : NSDimension(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSUnitElectricCurrent") }
        
        fun megaamperes(): MemorySegment {
            val sel = ObjCRuntime.sel("megaamperes")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun kiloamperes(): MemorySegment {
            val sel = ObjCRuntime.sel("kiloamperes")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun amperes(): MemorySegment {
            val sel = ObjCRuntime.sel("amperes")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun milliamperes(): MemorySegment {
            val sel = ObjCRuntime.sel("milliamperes")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun microamperes(): MemorySegment {
            val sel = ObjCRuntime.sel("microamperes")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
    }
    
    // @property megaamperes
    open fun megaamperes(): MemorySegment {
        val sel = ObjCRuntime.sel("megaamperes")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property kiloamperes
    open fun kiloamperes(): MemorySegment {
        val sel = ObjCRuntime.sel("kiloamperes")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property amperes
    open fun amperes(): MemorySegment {
        val sel = ObjCRuntime.sel("amperes")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property milliamperes
    open fun milliamperes(): MemorySegment {
        val sel = ObjCRuntime.sel("milliamperes")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property microamperes
    open fun microamperes(): MemorySegment {
        val sel = ObjCRuntime.sel("microamperes")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
}

