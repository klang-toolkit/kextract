package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSTokenFieldCell
 * Superclass: NSTextFieldCell
 */
open class NSTokenFieldCell(override val ptr: MemorySegment) : NSTextFieldCell(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSTokenFieldCell") }
        
        fun defaultCompletionDelay(): Double {
            val sel = ObjCRuntime.sel("defaultCompletionDelay")
            return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, _class, sel) as Double
        }
        
        fun defaultTokenizingCharacterSet(): MemorySegment {
            val sel = ObjCRuntime.sel("defaultTokenizingCharacterSet")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
    }
    
    // @property tokenStyle
    open fun tokenStyle(): MemorySegment {
        val sel = ObjCRuntime.sel("tokenStyle")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setTokenStyle(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setTokenStyle:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property completionDelay
    open fun completionDelay(): Double {
        val sel = ObjCRuntime.sel("completionDelay")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    open fun setCompletionDelay(value: Double) {
        val sel = ObjCRuntime.sel("setCompletionDelay:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property defaultCompletionDelay
    open fun defaultCompletionDelay(): Double {
        val sel = ObjCRuntime.sel("defaultCompletionDelay")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    
    // @property tokenizingCharacterSet
    open fun tokenizingCharacterSet(): MemorySegment {
        val sel = ObjCRuntime.sel("tokenizingCharacterSet")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setTokenizingCharacterSet(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setTokenizingCharacterSet:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property defaultTokenizingCharacterSet
    open fun defaultTokenizingCharacterSet(): MemorySegment {
        val sel = ObjCRuntime.sel("defaultTokenizingCharacterSet")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property delegate
    /** @return id<NSTokenFieldCellDelegate> */
    open fun delegate(): MemorySegment {
        val sel = ObjCRuntime.sel("delegate")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setDelegate(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setDelegate:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
}

