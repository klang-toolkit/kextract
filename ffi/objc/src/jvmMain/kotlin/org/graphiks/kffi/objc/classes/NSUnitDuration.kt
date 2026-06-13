package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSUnitDuration
 * Superclass: NSDimension
 * Protocols: NSSecureCoding
 */
open class NSUnitDuration(override val ptr: MemorySegment) : NSDimension(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSUnitDuration") }
        
        fun hours(): MemorySegment {
            val sel = ObjCRuntime.sel("hours")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun minutes(): MemorySegment {
            val sel = ObjCRuntime.sel("minutes")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun seconds(): MemorySegment {
            val sel = ObjCRuntime.sel("seconds")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun milliseconds(): MemorySegment {
            val sel = ObjCRuntime.sel("milliseconds")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun microseconds(): MemorySegment {
            val sel = ObjCRuntime.sel("microseconds")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun nanoseconds(): MemorySegment {
            val sel = ObjCRuntime.sel("nanoseconds")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun picoseconds(): MemorySegment {
            val sel = ObjCRuntime.sel("picoseconds")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
    }
    
    // @property hours
    open fun hours(): MemorySegment {
        val sel = ObjCRuntime.sel("hours")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property minutes
    open fun minutes(): MemorySegment {
        val sel = ObjCRuntime.sel("minutes")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property seconds
    open fun seconds(): MemorySegment {
        val sel = ObjCRuntime.sel("seconds")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property milliseconds
    open fun milliseconds(): MemorySegment {
        val sel = ObjCRuntime.sel("milliseconds")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property microseconds
    open fun microseconds(): MemorySegment {
        val sel = ObjCRuntime.sel("microseconds")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property nanoseconds
    open fun nanoseconds(): MemorySegment {
        val sel = ObjCRuntime.sel("nanoseconds")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property picoseconds
    open fun picoseconds(): MemorySegment {
        val sel = ObjCRuntime.sel("picoseconds")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
}

