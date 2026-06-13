package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSListFormatter
 * Superclass: NSFormatter
 */
open class NSListFormatter(override val ptr: MemorySegment) : NSFormatter(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSListFormatter") }
        
        fun localizedStringByJoiningStrings(strings: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("localizedStringByJoiningStrings:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, strings) as MemorySegment
        }
        
        /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
        fun localizedStringByJoiningStringsAsString(strings: MemorySegment): String = ObjCRuntime.toJavaString(localizedStringByJoiningStrings(strings))
        
    }
    
    open fun stringFromItems(items: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("stringFromItems:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, items) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    fun stringFromItemsAsString(items: MemorySegment): String = ObjCRuntime.toJavaString(stringFromItems(items))
    
    override fun stringForObjectValue(obj: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("stringForObjectValue:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, obj) as MemorySegment
    }
    
    // @property locale
    open fun locale(): MemorySegment {
        val sel = ObjCRuntime.sel("locale")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setLocale(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setLocale:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property itemFormatter
    open fun itemFormatter(): MemorySegment {
        val sel = ObjCRuntime.sel("itemFormatter")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setItemFormatter(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setItemFormatter:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
}

