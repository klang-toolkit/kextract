package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSTextElement
 * Superclass: NSObject
 */
open class NSTextElement(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSTextElement") }
        
    }
    
    open fun initWithTextContentManager(textContentManager: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithTextContentManager:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, textContentManager) as MemorySegment
    }
    
    // @property textContentManager
    open fun textContentManager(): MemorySegment {
        val sel = ObjCRuntime.sel("textContentManager")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setTextContentManager(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setTextContentManager:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property elementRange
    open fun elementRange(): MemorySegment {
        val sel = ObjCRuntime.sel("elementRange")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setElementRange(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setElementRange:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property childElements
    /** @return NSArray<__kindof NSTextElement *> * */
    open fun childElements(): MemorySegment {
        val sel = ObjCRuntime.sel("childElements")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property parentElement
    open fun parentElement(): MemorySegment {
        val sel = ObjCRuntime.sel("parentElement")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property isRepresentedElement
    open fun isRepresentedElement(): Boolean {
        val sel = ObjCRuntime.sel("isRepresentedElement")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
}

