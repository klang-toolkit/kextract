package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSAccessibilityCustomRotor
 * Superclass: NSObject
 */
open class NSAccessibilityCustomRotor(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSAccessibilityCustomRotor") }
        
    }
    
    open fun initWithLabel_itemSearchDelegate(label: MemorySegment, itemSearchDelegate: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithLabel:itemSearchDelegate:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, label, itemSearchDelegate) as MemorySegment
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun initWithLabel_itemSearchDelegate(label: String, itemSearchDelegate: MemorySegment): MemorySegment = initWithLabel_itemSearchDelegate(ObjCRuntime.newNSString(Arena.global(), label), itemSearchDelegate)
    
    open fun initWithRotorType_itemSearchDelegate(rotorType: MemorySegment, itemSearchDelegate: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithRotorType:itemSearchDelegate:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, rotorType, itemSearchDelegate) as MemorySegment
    }
    
    // @property type
    open fun type(): MemorySegment {
        val sel = ObjCRuntime.sel("type")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setType(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setType:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property label
    open fun label(): MemorySegment {
        val sel = ObjCRuntime.sel("label")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setLabel(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setLabel:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun labelAsString(): String = ObjCRuntime.toJavaString(label())
    
    /** Convenience overload — accepts Kotlin [String] for the NSString property. */
    open fun setLabel(value: String) = setLabel(ObjCRuntime.newNSString(Arena.global(), value))
    
    // @property itemSearchDelegate
    /** @return id<NSAccessibilityCustomRotorItemSearchDelegate> */
    open fun itemSearchDelegate(): MemorySegment {
        val sel = ObjCRuntime.sel("itemSearchDelegate")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setItemSearchDelegate(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setItemSearchDelegate:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property itemLoadingDelegate
    /** @return id<NSAccessibilityElementLoading> */
    open fun itemLoadingDelegate(): MemorySegment {
        val sel = ObjCRuntime.sel("itemLoadingDelegate")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setItemLoadingDelegate(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setItemLoadingDelegate:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
}

