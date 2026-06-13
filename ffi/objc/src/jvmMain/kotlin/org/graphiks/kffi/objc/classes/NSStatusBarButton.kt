package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSStatusBarButton
 * Superclass: NSButton
 */
open class NSStatusBarButton(override val ptr: MemorySegment) : NSButton(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSStatusBarButton") }
        
    }
    
    // @property appearsDisabled
    open fun appearsDisabled(): Boolean {
        val sel = ObjCRuntime.sel("appearsDisabled")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setAppearsDisabled(value: Boolean) {
        val sel = ObjCRuntime.sel("setAppearsDisabled:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
}

