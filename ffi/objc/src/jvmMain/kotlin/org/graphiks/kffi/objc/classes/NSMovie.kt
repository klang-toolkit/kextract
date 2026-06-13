package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSMovie
 * Superclass: NSObject
 * Protocols: NSCoding
 */
open class NSMovie(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSMovie") }
        
    }
    
    open fun initWithCoder(coder: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithCoder:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, coder) as MemorySegment
    }
    
    open fun init(): MemorySegment {
        val sel = ObjCRuntime.sel("init")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    open fun initWithMovie(movie: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithMovie:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, movie) as MemorySegment
    }
    
    open fun QTMovie(): MemorySegment {
        val sel = ObjCRuntime.sel("QTMovie")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
}

