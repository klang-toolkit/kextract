package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSCountedSet
 * Superclass: NSMutableSet
 */
open class NSCountedSet(override val ptr: MemorySegment) : NSMutableSet(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSCountedSet") }
        
    }
    
    override fun initWithCapacity(numItems: Long): MemorySegment {
        val sel = ObjCRuntime.sel("initWithCapacity:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, numItems) as MemorySegment
    }
    
    open fun initWithArray(array: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithArray:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, array) as MemorySegment
    }
    
    open fun initWithSet(`set`: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithSet:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, `set`) as MemorySegment
    }
    
    open fun countForObject(`object`: MemorySegment): Long {
        val sel = ObjCRuntime.sel("countForObject:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel, `object`) as Long
    }
    
    /** @return NSEnumerator<ObjectType> * */
    override fun objectEnumerator(): MemorySegment {
        val sel = ObjCRuntime.sel("objectEnumerator")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    override fun addObject(`object`: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("addObject:")
        ObjCRuntime.msgSend(null, ptr, sel, `object`)
    }
    
    override fun removeObject(`object`: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("removeObject:")
        ObjCRuntime.msgSend(null, ptr, sel, `object`)
    }
    
}

