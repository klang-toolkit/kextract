package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSPanel
 * Superclass: NSWindow
 */
open class NSPanel(override val ptr: MemorySegment) : NSWindow(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSPanel") }
        
    }
    
    // @property floatingPanel
    open fun isFloatingPanel(): Boolean {
        val sel = ObjCRuntime.sel("isFloatingPanel")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setFloatingPanel(value: Boolean) {
        val sel = ObjCRuntime.sel("setFloatingPanel:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property becomesKeyOnlyIfNeeded
    open fun becomesKeyOnlyIfNeeded(): Boolean {
        val sel = ObjCRuntime.sel("becomesKeyOnlyIfNeeded")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setBecomesKeyOnlyIfNeeded(value: Boolean) {
        val sel = ObjCRuntime.sel("setBecomesKeyOnlyIfNeeded:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property worksWhenModal
    override fun worksWhenModal(): Boolean {
        val sel = ObjCRuntime.sel("worksWhenModal")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setWorksWhenModal(value: Boolean) {
        val sel = ObjCRuntime.sel("setWorksWhenModal:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
}

