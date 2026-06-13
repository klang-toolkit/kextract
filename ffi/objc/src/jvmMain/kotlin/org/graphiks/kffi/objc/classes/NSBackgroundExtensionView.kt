package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSBackgroundExtensionView
 * Superclass: NSView
 */
open class NSBackgroundExtensionView(override val ptr: MemorySegment) : NSView(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSBackgroundExtensionView") }
        
    }
    
    // @property contentView
    open fun contentView(): MemorySegment {
        val sel = ObjCRuntime.sel("contentView")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setContentView(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setContentView:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property automaticallyPlacesContentView
    open fun automaticallyPlacesContentView(): Boolean {
        val sel = ObjCRuntime.sel("automaticallyPlacesContentView")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setAutomaticallyPlacesContentView(value: Boolean) {
        val sel = ObjCRuntime.sel("setAutomaticallyPlacesContentView:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
}

