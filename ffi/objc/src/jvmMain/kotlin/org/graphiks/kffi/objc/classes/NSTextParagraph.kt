package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSTextParagraph
 * Superclass: NSTextElement
 */
open class NSTextParagraph(override val ptr: MemorySegment) : NSTextElement(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSTextParagraph") }
        
    }
    
    open fun initWithAttributedString(attributedString: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithAttributedString:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, attributedString) as MemorySegment
    }
    
    // @property attributedString
    open fun attributedString(): MemorySegment {
        val sel = ObjCRuntime.sel("attributedString")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property paragraphContentRange
    open fun paragraphContentRange(): MemorySegment {
        val sel = ObjCRuntime.sel("paragraphContentRange")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property paragraphSeparatorRange
    open fun paragraphSeparatorRange(): MemorySegment {
        val sel = ObjCRuntime.sel("paragraphSeparatorRange")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
}

