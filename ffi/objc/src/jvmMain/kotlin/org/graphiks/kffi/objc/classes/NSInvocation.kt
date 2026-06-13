package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSInvocation
 * Superclass: NSObject
 */
open class NSInvocation(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSInvocation") }
        
        fun invocationWithMethodSignature(sig: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("invocationWithMethodSignature:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, sig) as MemorySegment
        }
        
    }
    
    open fun retainArguments(): Unit {
        val sel = ObjCRuntime.sel("retainArguments")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun getReturnValue(retLoc: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("getReturnValue:")
        ObjCRuntime.msgSend(null, ptr, sel, retLoc)
    }
    
    open fun setReturnValue(retLoc: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("setReturnValue:")
        ObjCRuntime.msgSend(null, ptr, sel, retLoc)
    }
    
    open fun getArgument_atIndex(argumentLocation: MemorySegment, idx: Long): Unit {
        val sel = ObjCRuntime.sel("getArgument:atIndex:")
        ObjCRuntime.msgSend(null, ptr, sel, argumentLocation, idx)
    }
    
    open fun setArgument_atIndex(argumentLocation: MemorySegment, idx: Long): Unit {
        val sel = ObjCRuntime.sel("setArgument:atIndex:")
        ObjCRuntime.msgSend(null, ptr, sel, argumentLocation, idx)
    }
    
    open fun invoke(): Unit {
        val sel = ObjCRuntime.sel("invoke")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun invokeWithTarget(target: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("invokeWithTarget:")
        ObjCRuntime.msgSend(null, ptr, sel, target)
    }
    
    open fun invokeUsingIMP(imp: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("invokeUsingIMP:")
        ObjCRuntime.msgSend(null, ptr, sel, imp)
    }
    
    // @property methodSignature
    open fun methodSignature(): MemorySegment {
        val sel = ObjCRuntime.sel("methodSignature")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property argumentsRetained
    open fun argumentsRetained(): Boolean {
        val sel = ObjCRuntime.sel("argumentsRetained")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property target
    open fun target(): MemorySegment {
        val sel = ObjCRuntime.sel("target")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setTarget(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setTarget:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property selector
    open fun selector(): MemorySegment {
        val sel = ObjCRuntime.sel("selector")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setSelector(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setSelector:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
}

