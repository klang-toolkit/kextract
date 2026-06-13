package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSUUID
 * Superclass: NSObject
 * Protocols: NSCopying, NSSecureCoding
 */
open class NSUUID(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSUUID") }
        
        fun UUID(): MemorySegment {
            val sel = ObjCRuntime.sel("UUID")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
    }
    
    open fun init(): MemorySegment {
        val sel = ObjCRuntime.sel("init")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    open fun initWithUUIDString(string: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithUUIDString:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, string) as MemorySegment
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun initWithUUIDString(string: String): MemorySegment = initWithUUIDString(ObjCRuntime.newNSString(Arena.global(), string))
    
    open fun initWithUUIDBytes(bytes: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithUUIDBytes:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, bytes) as MemorySegment
    }
    
    open fun getUUIDBytes(uuid: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("getUUIDBytes:")
        ObjCRuntime.msgSend(null, ptr, sel, uuid)
    }
    
    open fun compare(otherUUID: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("compare:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, otherUUID) as MemorySegment
    }
    
    // @property UUIDString
    open fun UUIDString(): MemorySegment {
        val sel = ObjCRuntime.sel("UUIDString")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun UUIDStringAsString(): String = ObjCRuntime.toJavaString(UUIDString())
    
}

