package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSUnitPower
 * Superclass: NSDimension
 * Protocols: NSSecureCoding
 */
open class NSUnitPower(override val ptr: MemorySegment) : NSDimension(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSUnitPower") }
        
        fun terawatts(): MemorySegment {
            val sel = ObjCRuntime.sel("terawatts")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun gigawatts(): MemorySegment {
            val sel = ObjCRuntime.sel("gigawatts")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun megawatts(): MemorySegment {
            val sel = ObjCRuntime.sel("megawatts")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun kilowatts(): MemorySegment {
            val sel = ObjCRuntime.sel("kilowatts")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun watts(): MemorySegment {
            val sel = ObjCRuntime.sel("watts")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun milliwatts(): MemorySegment {
            val sel = ObjCRuntime.sel("milliwatts")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun microwatts(): MemorySegment {
            val sel = ObjCRuntime.sel("microwatts")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun nanowatts(): MemorySegment {
            val sel = ObjCRuntime.sel("nanowatts")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun picowatts(): MemorySegment {
            val sel = ObjCRuntime.sel("picowatts")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun femtowatts(): MemorySegment {
            val sel = ObjCRuntime.sel("femtowatts")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun horsepower(): MemorySegment {
            val sel = ObjCRuntime.sel("horsepower")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
    }
    
    // @property terawatts
    open fun terawatts(): MemorySegment {
        val sel = ObjCRuntime.sel("terawatts")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property gigawatts
    open fun gigawatts(): MemorySegment {
        val sel = ObjCRuntime.sel("gigawatts")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property megawatts
    open fun megawatts(): MemorySegment {
        val sel = ObjCRuntime.sel("megawatts")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property kilowatts
    open fun kilowatts(): MemorySegment {
        val sel = ObjCRuntime.sel("kilowatts")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property watts
    open fun watts(): MemorySegment {
        val sel = ObjCRuntime.sel("watts")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property milliwatts
    open fun milliwatts(): MemorySegment {
        val sel = ObjCRuntime.sel("milliwatts")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property microwatts
    open fun microwatts(): MemorySegment {
        val sel = ObjCRuntime.sel("microwatts")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property nanowatts
    open fun nanowatts(): MemorySegment {
        val sel = ObjCRuntime.sel("nanowatts")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property picowatts
    open fun picowatts(): MemorySegment {
        val sel = ObjCRuntime.sel("picowatts")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property femtowatts
    open fun femtowatts(): MemorySegment {
        val sel = ObjCRuntime.sel("femtowatts")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property horsepower
    open fun horsepower(): MemorySegment {
        val sel = ObjCRuntime.sel("horsepower")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
}

