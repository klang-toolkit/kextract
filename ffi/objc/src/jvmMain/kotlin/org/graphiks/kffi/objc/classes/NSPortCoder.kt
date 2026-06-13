package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSPortCoder
 * Superclass: NSCoder
 */
open class NSPortCoder(override val ptr: MemorySegment) : NSCoder(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSPortCoder") }
        
        fun portCoderWithReceivePort_sendPort_components(rcvPort: MemorySegment, sndPort: MemorySegment, comps: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("portCoderWithReceivePort:sendPort:components:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, rcvPort, sndPort, comps) as MemorySegment
        }
        
    }
    
    open fun isBycopy(): Boolean {
        val sel = ObjCRuntime.sel("isBycopy")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    open fun isByref(): Boolean {
        val sel = ObjCRuntime.sel("isByref")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    open fun encodePortObject(aport: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("encodePortObject:")
        ObjCRuntime.msgSend(null, ptr, sel, aport)
    }
    
    open fun decodePortObject(): MemorySegment {
        val sel = ObjCRuntime.sel("decodePortObject")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    open fun connection(): MemorySegment {
        val sel = ObjCRuntime.sel("connection")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    open fun initWithReceivePort_sendPort_components(rcvPort: MemorySegment, sndPort: MemorySegment, comps: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithReceivePort:sendPort:components:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, rcvPort, sndPort, comps) as MemorySegment
    }
    
    open fun dispatch(): Unit {
        val sel = ObjCRuntime.sel("dispatch")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
}

