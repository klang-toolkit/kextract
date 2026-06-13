package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSHashTable
 * Superclass: NSObject
 * Protocols: NSCopying, NSSecureCoding, NSFastEnumeration
 */
open class NSHashTable(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSHashTable") }
        
        /** @return NSHashTable<ObjectType> * */
        fun hashTableWithOptions(options: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("hashTableWithOptions:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, options) as MemorySegment
        }
        
        fun hashTableWithWeakObjects(): MemorySegment {
            val sel = ObjCRuntime.sel("hashTableWithWeakObjects")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        /** @return NSHashTable<ObjectType> * */
        fun weakObjectsHashTable(): MemorySegment {
            val sel = ObjCRuntime.sel("weakObjectsHashTable")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
    }
    
    open fun initWithOptions_capacity(options: MemorySegment, initialCapacity: Long): MemorySegment {
        val sel = ObjCRuntime.sel("initWithOptions:capacity:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, options, initialCapacity) as MemorySegment
    }
    
    open fun initWithPointerFunctions_capacity(functions: MemorySegment, initialCapacity: Long): MemorySegment {
        val sel = ObjCRuntime.sel("initWithPointerFunctions:capacity:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, functions, initialCapacity) as MemorySegment
    }
    
    open fun member(`object`: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("member:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, `object`) as MemorySegment
    }
    
    /** @return NSEnumerator<ObjectType> * */
    open fun objectEnumerator(): MemorySegment {
        val sel = ObjCRuntime.sel("objectEnumerator")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    open fun addObject(`object`: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("addObject:")
        ObjCRuntime.msgSend(null, ptr, sel, `object`)
    }
    
    open fun removeObject(`object`: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("removeObject:")
        ObjCRuntime.msgSend(null, ptr, sel, `object`)
    }
    
    open fun removeAllObjects(): Unit {
        val sel = ObjCRuntime.sel("removeAllObjects")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun containsObject(anObject: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("containsObject:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, anObject) as Boolean
    }
    
    open fun intersectsHashTable(other: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("intersectsHashTable:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, other) as Boolean
    }
    
    open fun isEqualToHashTable(other: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("isEqualToHashTable:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, other) as Boolean
    }
    
    open fun isSubsetOfHashTable(other: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("isSubsetOfHashTable:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, other) as Boolean
    }
    
    open fun intersectHashTable(other: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("intersectHashTable:")
        ObjCRuntime.msgSend(null, ptr, sel, other)
    }
    
    open fun unionHashTable(other: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("unionHashTable:")
        ObjCRuntime.msgSend(null, ptr, sel, other)
    }
    
    open fun minusHashTable(other: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("minusHashTable:")
        ObjCRuntime.msgSend(null, ptr, sel, other)
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
    
    // @property allObjects
    /** @return NSArray<ObjectType> * */
    open fun allObjects(): MemorySegment {
        val sel = ObjCRuntime.sel("allObjects")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property anyObject
    open fun anyObject(): MemorySegment {
        val sel = ObjCRuntime.sel("anyObject")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property setRepresentation
    /** @return NSSet<ObjectType> * */
    open fun setRepresentation(): MemorySegment {
        val sel = ObjCRuntime.sel("setRepresentation")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
}

