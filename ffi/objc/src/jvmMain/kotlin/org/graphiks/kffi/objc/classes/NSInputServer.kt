package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSInputServer
 * Superclass: NSObject
 * Protocols: NSInputServiceProvider, NSInputServerMouseTracker
 */
open class NSInputServer(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSInputServer") }
        
    }
    
    open fun initWithDelegate_name(delegate: MemorySegment, name: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithDelegate:name:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, delegate, name) as MemorySegment
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun initWithDelegate_name(delegate: MemorySegment, name: String): MemorySegment = initWithDelegate_name(delegate, ObjCRuntime.newNSString(Arena.global(), name))
    
}

