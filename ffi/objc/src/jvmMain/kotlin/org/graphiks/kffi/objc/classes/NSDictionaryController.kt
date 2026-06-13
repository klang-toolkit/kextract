package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSDictionaryController
 * Superclass: NSArrayController
 */
open class NSDictionaryController(override val ptr: MemorySegment) : NSArrayController(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSDictionaryController") }
        
    }
    
    override fun newObject(): MemorySegment {
        val sel = ObjCRuntime.sel("newObject")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property initialKey
    open fun initialKey(): MemorySegment {
        val sel = ObjCRuntime.sel("initialKey")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setInitialKey(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setInitialKey:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun initialKeyAsString(): String = ObjCRuntime.toJavaString(initialKey())
    
    /** Convenience overload — accepts Kotlin [String] for the NSString property. */
    open fun setInitialKey(value: String) = setInitialKey(ObjCRuntime.newNSString(Arena.global(), value))
    
    // @property initialValue
    open fun initialValue(): MemorySegment {
        val sel = ObjCRuntime.sel("initialValue")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setInitialValue(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setInitialValue:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property includedKeys
    /** @return NSArray<NSString *> * */
    open fun includedKeys(): MemorySegment {
        val sel = ObjCRuntime.sel("includedKeys")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setIncludedKeys(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setIncludedKeys:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property excludedKeys
    /** @return NSArray<NSString *> * */
    open fun excludedKeys(): MemorySegment {
        val sel = ObjCRuntime.sel("excludedKeys")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setExcludedKeys(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setExcludedKeys:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property localizedKeyDictionary
    /** @return NSDictionary<NSString *,NSString *> * */
    open fun localizedKeyDictionary(): MemorySegment {
        val sel = ObjCRuntime.sel("localizedKeyDictionary")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setLocalizedKeyDictionary(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setLocalizedKeyDictionary:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property localizedKeyTable
    open fun localizedKeyTable(): MemorySegment {
        val sel = ObjCRuntime.sel("localizedKeyTable")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setLocalizedKeyTable(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setLocalizedKeyTable:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun localizedKeyTableAsString(): String = ObjCRuntime.toJavaString(localizedKeyTable())
    
    /** Convenience overload — accepts Kotlin [String] for the NSString property. */
    open fun setLocalizedKeyTable(value: String) = setLocalizedKeyTable(ObjCRuntime.newNSString(Arena.global(), value))
    
}

