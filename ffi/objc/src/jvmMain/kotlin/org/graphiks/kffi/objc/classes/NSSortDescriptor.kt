package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSSortDescriptor
 * Superclass: NSObject
 * Protocols: NSSecureCoding, NSCopying
 */
open class NSSortDescriptor(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSSortDescriptor") }
        
        fun sortDescriptorWithKey_ascending(key: MemorySegment, ascending: Boolean): MemorySegment {
            val sel = ObjCRuntime.sel("sortDescriptorWithKey:ascending:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, key, ascending) as MemorySegment
        }
        
        /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
        fun sortDescriptorWithKey_ascending(key: String, ascending: Boolean): MemorySegment = sortDescriptorWithKey_ascending(ObjCRuntime.newNSString(Arena.global(), key), ascending)
        
        fun sortDescriptorWithKey_ascending_selector(key: MemorySegment, ascending: Boolean, selector: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("sortDescriptorWithKey:ascending:selector:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, key, ascending, selector) as MemorySegment
        }
        
        /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
        fun sortDescriptorWithKey_ascending_selector(key: String, ascending: Boolean, selector: MemorySegment): MemorySegment = sortDescriptorWithKey_ascending_selector(ObjCRuntime.newNSString(Arena.global(), key), ascending, selector)
        
        fun sortDescriptorWithKey_ascending_comparator(key: MemorySegment, ascending: Boolean, cmptr: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("sortDescriptorWithKey:ascending:comparator:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, key, ascending, cmptr) as MemorySegment
        }
        
        /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
        fun sortDescriptorWithKey_ascending_comparator(key: String, ascending: Boolean, cmptr: MemorySegment): MemorySegment = sortDescriptorWithKey_ascending_comparator(ObjCRuntime.newNSString(Arena.global(), key), ascending, cmptr)
        
    }
    
    open fun initWithKey_ascending(key: MemorySegment, ascending: Boolean): MemorySegment {
        val sel = ObjCRuntime.sel("initWithKey:ascending:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, key, ascending) as MemorySegment
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun initWithKey_ascending(key: String, ascending: Boolean): MemorySegment = initWithKey_ascending(ObjCRuntime.newNSString(Arena.global(), key), ascending)
    
    open fun initWithKey_ascending_selector(key: MemorySegment, ascending: Boolean, selector: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithKey:ascending:selector:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, key, ascending, selector) as MemorySegment
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun initWithKey_ascending_selector(key: String, ascending: Boolean, selector: MemorySegment): MemorySegment = initWithKey_ascending_selector(ObjCRuntime.newNSString(Arena.global(), key), ascending, selector)
    
    open fun initWithCoder(coder: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithCoder:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, coder) as MemorySegment
    }
    
    open fun allowEvaluation(): Unit {
        val sel = ObjCRuntime.sel("allowEvaluation")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun initWithKey_ascending_comparator(key: MemorySegment, ascending: Boolean, cmptr: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithKey:ascending:comparator:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, key, ascending, cmptr) as MemorySegment
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun initWithKey_ascending_comparator(key: String, ascending: Boolean, cmptr: MemorySegment): MemorySegment = initWithKey_ascending_comparator(ObjCRuntime.newNSString(Arena.global(), key), ascending, cmptr)
    
    open fun compareObject_toObject(object1: MemorySegment, object2: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("compareObject:toObject:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, object1, object2) as MemorySegment
    }
    
    // @property key
    open fun key(): MemorySegment {
        val sel = ObjCRuntime.sel("key")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun keyAsString(): String = ObjCRuntime.toJavaString(key())
    
    // @property ascending
    open fun ascending(): Boolean {
        val sel = ObjCRuntime.sel("ascending")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property selector
    open fun selector(): MemorySegment {
        val sel = ObjCRuntime.sel("selector")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property comparator
    open fun comparator(): MemorySegment {
        val sel = ObjCRuntime.sel("comparator")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property reversedSortDescriptor
    open fun reversedSortDescriptor(): MemorySegment {
        val sel = ObjCRuntime.sel("reversedSortDescriptor")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    
    // ── Instance variables (direct field access not supported via Panama) ──
    // ivar: _sortDescriptorFlags: Long
    // ivar: _key: MemorySegment
    // ivar: _selector: MemorySegment
    // ivar: _selectorOrBlock: MemorySegment
}

