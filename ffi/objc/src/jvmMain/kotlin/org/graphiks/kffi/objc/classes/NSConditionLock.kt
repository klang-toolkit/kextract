package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSConditionLock
 * Superclass: NSObject
 * Protocols: NSLocking
 */
open class NSConditionLock(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSConditionLock") }
        
    }
    
    open fun initWithCondition(condition: Long): MemorySegment {
        val sel = ObjCRuntime.sel("initWithCondition:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, condition) as MemorySegment
    }
    
    open fun lockWhenCondition(condition: Long): Unit {
        val sel = ObjCRuntime.sel("lockWhenCondition:")
        ObjCRuntime.msgSend(null, ptr, sel, condition)
    }
    
    open fun tryLock(): Boolean {
        val sel = ObjCRuntime.sel("tryLock")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    open fun tryLockWhenCondition(condition: Long): Boolean {
        val sel = ObjCRuntime.sel("tryLockWhenCondition:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, condition) as Boolean
    }
    
    open fun unlockWithCondition(condition: Long): Unit {
        val sel = ObjCRuntime.sel("unlockWithCondition:")
        ObjCRuntime.msgSend(null, ptr, sel, condition)
    }
    
    open fun lockBeforeDate(limit: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("lockBeforeDate:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, limit) as Boolean
    }
    
    open fun lockWhenCondition_beforeDate(condition: Long, limit: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("lockWhenCondition:beforeDate:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, condition, limit) as Boolean
    }
    
    // @property condition
    open fun condition(): Long {
        val sel = ObjCRuntime.sel("condition")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    
    // @property name
    open fun name(): MemorySegment {
        val sel = ObjCRuntime.sel("name")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setName(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setName:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun nameAsString(): String = ObjCRuntime.toJavaString(name())
    
    /** Convenience overload — accepts Kotlin [String] for the NSString property. */
    open fun setName(value: String) = setName(ObjCRuntime.newNSString(Arena.global(), value))
    
}

