package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSPointerArray
 * Superclass: NSObject
 * Protocols: NSFastEnumeration, NSCopying, NSSecureCoding
 */
open class NSPointerArray(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSPointerArray") }
        
        fun pointerArrayWithOptions(options: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("pointerArrayWithOptions:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, options) as MemorySegment
        }
        
        fun pointerArrayWithPointerFunctions(functions: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("pointerArrayWithPointerFunctions:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, functions) as MemorySegment
        }
        
    }
    
    open fun initWithOptions(options: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithOptions:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, options) as MemorySegment
    }
    
    open fun initWithPointerFunctions(functions: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithPointerFunctions:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, functions) as MemorySegment
    }
    
    open fun pointerAtIndex(index: Long): MemorySegment {
        val sel = ObjCRuntime.sel("pointerAtIndex:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, index) as MemorySegment
    }
    
    open fun addPointer(pointer: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("addPointer:")
        ObjCRuntime.msgSend(null, ptr, sel, pointer)
    }
    
    open fun removePointerAtIndex(index: Long): Unit {
        val sel = ObjCRuntime.sel("removePointerAtIndex:")
        ObjCRuntime.msgSend(null, ptr, sel, index)
    }
    
    open fun insertPointer_atIndex(item: MemorySegment, index: Long): Unit {
        val sel = ObjCRuntime.sel("insertPointer:atIndex:")
        ObjCRuntime.msgSend(null, ptr, sel, item, index)
    }
    
    open fun replacePointerAtIndex_withPointer(index: Long, item: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("replacePointerAtIndex:withPointer:")
        ObjCRuntime.msgSend(null, ptr, sel, index, item)
    }
    
    open fun compact(): Unit {
        val sel = ObjCRuntime.sel("compact")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    // @property pointerFunctions
    open fun pointerFunctions(): MemorySegment {
        val sel = ObjCRuntime.sel("pointerFunctions")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property count
    open fun count(): Long {
        val sel = ObjCRuntime.sel("count")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    open fun setCount(value: Long) {
        val sel = ObjCRuntime.sel("setCount:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
}

// ── Category: NSPointerArrayConveniences on NSPointerArray ─────────────────────────────────────────

fun NSPointerArray.allObjects(): MemorySegment {
    val sel = ObjCRuntime.sel("allObjects")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

// Class method: +[NSPointerArray pointerArrayWithStrongObjects]
fun NSPointerArray_pointerArrayWithStrongObjects(): MemorySegment {
    val sel = ObjCRuntime.sel("pointerArrayWithStrongObjects")
    val cls = ObjCRuntime.getClass("NSPointerArray")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel) as MemorySegment
}

// Class method: +[NSPointerArray pointerArrayWithWeakObjects]
fun NSPointerArray_pointerArrayWithWeakObjects(): MemorySegment {
    val sel = ObjCRuntime.sel("pointerArrayWithWeakObjects")
    val cls = ObjCRuntime.getClass("NSPointerArray")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel) as MemorySegment
}

// Class method: +[NSPointerArray strongObjectsPointerArray]
fun NSPointerArray_strongObjectsPointerArray(): MemorySegment {
    val sel = ObjCRuntime.sel("strongObjectsPointerArray")
    val cls = ObjCRuntime.getClass("NSPointerArray")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel) as MemorySegment
}

// Class method: +[NSPointerArray weakObjectsPointerArray]
fun NSPointerArray_weakObjectsPointerArray(): MemorySegment {
    val sel = ObjCRuntime.sel("weakObjectsPointerArray")
    val cls = ObjCRuntime.getClass("NSPointerArray")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel) as MemorySegment
}

