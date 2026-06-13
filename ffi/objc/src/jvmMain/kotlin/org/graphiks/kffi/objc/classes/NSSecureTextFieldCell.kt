package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSSecureTextFieldCell
 * Superclass: NSTextFieldCell
 */
open class NSSecureTextFieldCell(override val ptr: MemorySegment) : NSTextFieldCell(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSSecureTextFieldCell") }
        
    }
    
    // @property echosBullets
    open fun echosBullets(): Boolean {
        val sel = ObjCRuntime.sel("echosBullets")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setEchosBullets(value: Boolean) {
        val sel = ObjCRuntime.sel("setEchosBullets:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
}

