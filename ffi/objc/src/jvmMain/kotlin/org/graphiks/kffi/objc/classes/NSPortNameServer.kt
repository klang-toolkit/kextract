package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSPortNameServer
 * Superclass: NSObject
 */
open class NSPortNameServer(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSPortNameServer") }
        
        fun systemDefaultPortNameServer(): MemorySegment {
            val sel = ObjCRuntime.sel("systemDefaultPortNameServer")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
    }
    
    open fun portForName(name: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("portForName:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, name) as MemorySegment
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun portForName(name: String): MemorySegment = portForName(ObjCRuntime.newNSString(Arena.global(), name))
    
    open fun portForName_host(name: MemorySegment, host: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("portForName:host:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, name, host) as MemorySegment
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun portForName_host(name: String, host: String): MemorySegment = portForName_host(ObjCRuntime.newNSString(Arena.global(), name), ObjCRuntime.newNSString(Arena.global(), host))
    
    open fun registerPort_name(port: MemorySegment, name: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("registerPort:name:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, port, name) as Boolean
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun registerPort_name(port: MemorySegment, name: String): Boolean = registerPort_name(port, ObjCRuntime.newNSString(Arena.global(), name))
    
    open fun removePortForName(name: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("removePortForName:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, name) as Boolean
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun removePortForName(name: String): Boolean = removePortForName(ObjCRuntime.newNSString(Arena.global(), name))
    
}

