package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSAccessibilityCustomRotorItemResult
 * Superclass: NSObject
 */
open class NSAccessibilityCustomRotorItemResult(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSAccessibilityCustomRotorItemResult") }
        
        fun new(): MemorySegment {
            val sel = ObjCRuntime.sel("new")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
    }
    
    open fun init(): MemorySegment {
        val sel = ObjCRuntime.sel("init")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    open fun initWithTargetElement(targetElement: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithTargetElement:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, targetElement) as MemorySegment
    }
    
    open fun initWithItemLoadingToken_customLabel(itemLoadingToken: MemorySegment, customLabel: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithItemLoadingToken:customLabel:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, itemLoadingToken, customLabel) as MemorySegment
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun initWithItemLoadingToken_customLabel(itemLoadingToken: MemorySegment, customLabel: String): MemorySegment = initWithItemLoadingToken_customLabel(itemLoadingToken, ObjCRuntime.newNSString(Arena.global(), customLabel))
    
    // @property targetElement
    /** @return id<NSAccessibilityElement> */
    open fun targetElement(): MemorySegment {
        val sel = ObjCRuntime.sel("targetElement")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property itemLoadingToken
    open fun itemLoadingToken(): MemorySegment {
        val sel = ObjCRuntime.sel("itemLoadingToken")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property targetRange
    open fun targetRange(): MemorySegment {
        val sel = ObjCRuntime.sel("targetRange")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(ValueLayout.JAVA_LONG.withName("location"), ValueLayout.JAVA_LONG.withName("length")).withName("_NSRange"), ptr, sel) as MemorySegment
    }
    open fun setTargetRange(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setTargetRange:")
        ObjCRuntime.msgSend(null, ptr, sel, ObjCRuntime.ObjCStructArg(value, MemoryLayout.structLayout(ValueLayout.JAVA_LONG.withName("location"), ValueLayout.JAVA_LONG.withName("length")).withName("_NSRange")))
    }
    
    // @property customLabel
    open fun customLabel(): MemorySegment {
        val sel = ObjCRuntime.sel("customLabel")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setCustomLabel(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setCustomLabel:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun customLabelAsString(): String = ObjCRuntime.toJavaString(customLabel())
    
    /** Convenience overload — accepts Kotlin [String] for the NSString property. */
    open fun setCustomLabel(value: String) = setCustomLabel(ObjCRuntime.newNSString(Arena.global(), value))
    
}

