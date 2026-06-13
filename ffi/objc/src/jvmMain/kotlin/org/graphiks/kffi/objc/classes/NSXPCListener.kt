package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSXPCListener
 * Superclass: NSObject
 */
open class NSXPCListener(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSXPCListener") }
        
        fun serviceListener(): MemorySegment {
            val sel = ObjCRuntime.sel("serviceListener")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun anonymousListener(): MemorySegment {
            val sel = ObjCRuntime.sel("anonymousListener")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
    }
    
    open fun initWithMachServiceName(name: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithMachServiceName:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, name) as MemorySegment
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun initWithMachServiceName(name: String): MemorySegment = initWithMachServiceName(ObjCRuntime.newNSString(Arena.global(), name))
    
    open fun resume(): Unit {
        val sel = ObjCRuntime.sel("resume")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun suspend(): Unit {
        val sel = ObjCRuntime.sel("suspend")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun activate(): Unit {
        val sel = ObjCRuntime.sel("activate")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun invalidate(): Unit {
        val sel = ObjCRuntime.sel("invalidate")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun setConnectionCodeSigningRequirement(requirement: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("setConnectionCodeSigningRequirement:")
        ObjCRuntime.msgSend(null, ptr, sel, requirement)
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun setConnectionCodeSigningRequirement(requirement: String): Unit = setConnectionCodeSigningRequirement(ObjCRuntime.newNSString(Arena.global(), requirement))
    
    // @property delegate
    /** @return id<NSXPCListenerDelegate> */
    open fun delegate(): MemorySegment {
        val sel = ObjCRuntime.sel("delegate")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setDelegate(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setDelegate:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property endpoint
    open fun endpoint(): MemorySegment {
        val sel = ObjCRuntime.sel("endpoint")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
}

