package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSXPCInterface
 * Superclass: NSObject
 */
open class NSXPCInterface(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSXPCInterface") }
        
        fun interfaceWithProtocol(protocol: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("interfaceWithProtocol:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, protocol) as MemorySegment
        }
        
    }
    
    open fun setClasses_forSelector_argumentIndex_ofReply(classes: MemorySegment, sel: MemorySegment, arg: Long, ofReply: Boolean): Unit {
        val sel = ObjCRuntime.sel("setClasses:forSelector:argumentIndex:ofReply:")
        ObjCRuntime.msgSend(null, ptr, sel, classes, sel, arg, ofReply)
    }
    
    /** @return NSSet<Class> * */
    open fun classesForSelector_argumentIndex_ofReply(sel: MemorySegment, arg: Long, ofReply: Boolean): MemorySegment {
        val sel = ObjCRuntime.sel("classesForSelector:argumentIndex:ofReply:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, sel, arg, ofReply) as MemorySegment
    }
    
    open fun setInterface_forSelector_argumentIndex_ofReply(ifc: MemorySegment, sel: MemorySegment, arg: Long, ofReply: Boolean): Unit {
        val sel = ObjCRuntime.sel("setInterface:forSelector:argumentIndex:ofReply:")
        ObjCRuntime.msgSend(null, ptr, sel, ifc, sel, arg, ofReply)
    }
    
    open fun interfaceForSelector_argumentIndex_ofReply(sel: MemorySegment, arg: Long, ofReply: Boolean): MemorySegment {
        val sel = ObjCRuntime.sel("interfaceForSelector:argumentIndex:ofReply:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, sel, arg, ofReply) as MemorySegment
    }
    
    open fun setXPCType_forSelector_argumentIndex_ofReply(type: MemorySegment, sel: MemorySegment, arg: Long, ofReply: Boolean): Unit {
        val sel = ObjCRuntime.sel("setXPCType:forSelector:argumentIndex:ofReply:")
        ObjCRuntime.msgSend(null, ptr, sel, type, sel, arg, ofReply)
    }
    
    open fun XPCTypeForSelector_argumentIndex_ofReply(sel: MemorySegment, arg: Long, ofReply: Boolean): MemorySegment {
        val sel = ObjCRuntime.sel("XPCTypeForSelector:argumentIndex:ofReply:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, sel, arg, ofReply) as MemorySegment
    }
    
    // @property protocol
    open fun protocol(): MemorySegment {
        val sel = ObjCRuntime.sel("protocol")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setProtocol(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setProtocol:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
}

