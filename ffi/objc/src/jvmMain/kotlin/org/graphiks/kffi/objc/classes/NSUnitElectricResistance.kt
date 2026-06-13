package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSUnitElectricResistance
 * Superclass: NSDimension
 * Protocols: NSSecureCoding
 */
open class NSUnitElectricResistance(override val ptr: MemorySegment) : NSDimension(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSUnitElectricResistance") }
        
        fun megaohms(): MemorySegment {
            val sel = ObjCRuntime.sel("megaohms")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun kiloohms(): MemorySegment {
            val sel = ObjCRuntime.sel("kiloohms")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun ohms(): MemorySegment {
            val sel = ObjCRuntime.sel("ohms")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun milliohms(): MemorySegment {
            val sel = ObjCRuntime.sel("milliohms")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun microohms(): MemorySegment {
            val sel = ObjCRuntime.sel("microohms")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
    }
    
    // @property megaohms
    open fun megaohms(): MemorySegment {
        val sel = ObjCRuntime.sel("megaohms")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property kiloohms
    open fun kiloohms(): MemorySegment {
        val sel = ObjCRuntime.sel("kiloohms")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property ohms
    open fun ohms(): MemorySegment {
        val sel = ObjCRuntime.sel("ohms")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property milliohms
    open fun milliohms(): MemorySegment {
        val sel = ObjCRuntime.sel("milliohms")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property microohms
    open fun microohms(): MemorySegment {
        val sel = ObjCRuntime.sel("microohms")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
}

