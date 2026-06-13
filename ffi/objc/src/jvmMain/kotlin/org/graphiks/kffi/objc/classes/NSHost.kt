package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSHost
 * Superclass: NSObject
 */
open class NSHost(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSHost") }
        
        fun currentHost(): MemorySegment {
            val sel = ObjCRuntime.sel("currentHost")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun hostWithName(name: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("hostWithName:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, name) as MemorySegment
        }
        
        /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
        fun hostWithName(name: String): MemorySegment = hostWithName(ObjCRuntime.newNSString(Arena.global(), name))
        
        fun hostWithAddress(address: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("hostWithAddress:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, address) as MemorySegment
        }
        
        /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
        fun hostWithAddress(address: String): MemorySegment = hostWithAddress(ObjCRuntime.newNSString(Arena.global(), address))
        
        fun setHostCacheEnabled(flag: Boolean): Unit {
            val sel = ObjCRuntime.sel("setHostCacheEnabled:")
            ObjCRuntime.msgSend(null, _class, sel, flag)
        }
        
        fun isHostCacheEnabled(): Boolean {
            val sel = ObjCRuntime.sel("isHostCacheEnabled")
            return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, _class, sel) as Boolean
        }
        
        fun flushHostCache(): Unit {
            val sel = ObjCRuntime.sel("flushHostCache")
            ObjCRuntime.msgSend(null, _class, sel)
        }
        
    }
    
    open fun isEqualToHost(aHost: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("isEqualToHost:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, aHost) as Boolean
    }
    
    // @property name
    open fun name(): MemorySegment {
        val sel = ObjCRuntime.sel("name")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun nameAsString(): String = ObjCRuntime.toJavaString(name())
    
    // @property names
    /** @return NSArray<NSString *> * */
    open fun names(): MemorySegment {
        val sel = ObjCRuntime.sel("names")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property address
    open fun address(): MemorySegment {
        val sel = ObjCRuntime.sel("address")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun addressAsString(): String = ObjCRuntime.toJavaString(address())
    
    // @property addresses
    /** @return NSArray<NSString *> * */
    open fun addresses(): MemorySegment {
        val sel = ObjCRuntime.sel("addresses")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property localizedName
    open fun localizedName(): MemorySegment {
        val sel = ObjCRuntime.sel("localizedName")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun localizedNameAsString(): String = ObjCRuntime.toJavaString(localizedName())
    
    
    // ── Instance variables (direct field access not supported via Panama) ──
    // ivar: names: MemorySegment
    // ivar: addresses: MemorySegment
    // ivar: reserved: MemorySegment
}

