package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSAccessibilityCustomAction
 * Superclass: NSObject
 */
open class NSAccessibilityCustomAction(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSAccessibilityCustomAction") }
        
    }
    
    open fun initWithName_handler(name: MemorySegment, handler: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithName:handler:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, name, handler) as MemorySegment
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun initWithName_handler(name: String, handler: MemorySegment): MemorySegment = initWithName_handler(ObjCRuntime.newNSString(Arena.global(), name), handler)
    
    open fun initWithName_target_selector(name: MemorySegment, target: MemorySegment, selector: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithName:target:selector:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, name, target, selector) as MemorySegment
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun initWithName_target_selector(name: String, target: MemorySegment, selector: MemorySegment): MemorySegment = initWithName_target_selector(ObjCRuntime.newNSString(Arena.global(), name), target, selector)
    
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
    
    // @property handler
    open fun handler(): MemorySegment {
        val sel = ObjCRuntime.sel("handler")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setHandler(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setHandler:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property target
    /** @return id<NSObject> */
    open fun target(): MemorySegment {
        val sel = ObjCRuntime.sel("target")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setTarget(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setTarget:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property selector
    open fun selector(): MemorySegment {
        val sel = ObjCRuntime.sel("selector")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setSelector(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setSelector:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
}

