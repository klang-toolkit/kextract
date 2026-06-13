package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSClickGestureRecognizer
 * Superclass: NSGestureRecognizer
 * Protocols: NSCoding
 */
open class NSClickGestureRecognizer(override val ptr: MemorySegment) : NSGestureRecognizer(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSClickGestureRecognizer") }
        
    }
    
    // @property buttonMask
    open fun buttonMask(): Long {
        val sel = ObjCRuntime.sel("buttonMask")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    open fun setButtonMask(value: Long) {
        val sel = ObjCRuntime.sel("setButtonMask:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property numberOfClicksRequired
    open fun numberOfClicksRequired(): Long {
        val sel = ObjCRuntime.sel("numberOfClicksRequired")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    open fun setNumberOfClicksRequired(value: Long) {
        val sel = ObjCRuntime.sel("setNumberOfClicksRequired:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property numberOfTouchesRequired
    open fun numberOfTouchesRequired(): Long {
        val sel = ObjCRuntime.sel("numberOfTouchesRequired")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    open fun setNumberOfTouchesRequired(value: Long) {
        val sel = ObjCRuntime.sel("setNumberOfTouchesRequired:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
}

