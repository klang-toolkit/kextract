package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSMachPort
 * Superclass: NSPort
 */
open class NSMachPort(override val ptr: MemorySegment) : NSPort(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSMachPort") }
        
        fun portWithMachPort(machPort: Int): MemorySegment {
            val sel = ObjCRuntime.sel("portWithMachPort:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, machPort) as MemorySegment
        }
        
        fun portWithMachPort_options(machPort: Int, f: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("portWithMachPort:options:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, machPort, f) as MemorySegment
        }
        
    }
    
    open fun initWithMachPort(machPort: Int): MemorySegment {
        val sel = ObjCRuntime.sel("initWithMachPort:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, machPort) as MemorySegment
    }
    
    override fun setDelegate(anObject: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("setDelegate:")
        ObjCRuntime.msgSend(null, ptr, sel, anObject)
    }
    
    /** @return id<NSMachPortDelegate> */
    override fun delegate(): MemorySegment {
        val sel = ObjCRuntime.sel("delegate")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    open fun initWithMachPort_options(machPort: Int, f: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithMachPort:options:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, machPort, f) as MemorySegment
    }
    
    override fun scheduleInRunLoop_forMode(runLoop: MemorySegment, mode: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("scheduleInRunLoop:forMode:")
        ObjCRuntime.msgSend(null, ptr, sel, runLoop, mode)
    }
    
    override fun removeFromRunLoop_forMode(runLoop: MemorySegment, mode: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("removeFromRunLoop:forMode:")
        ObjCRuntime.msgSend(null, ptr, sel, runLoop, mode)
    }
    
    // @property machPort
    open fun machPort(): Int {
        val sel = ObjCRuntime.sel("machPort")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_INT, ptr, sel) as Int
    }
    
}

