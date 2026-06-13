package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSSearchToolbarItem
 * Superclass: NSToolbarItem
 */
open class NSSearchToolbarItem(override val ptr: MemorySegment) : NSToolbarItem(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSSearchToolbarItem") }
        
    }
    
    open fun beginSearchInteraction(): Unit {
        val sel = ObjCRuntime.sel("beginSearchInteraction")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun endSearchInteraction(): Unit {
        val sel = ObjCRuntime.sel("endSearchInteraction")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    // @property searchField
    open fun searchField(): MemorySegment {
        val sel = ObjCRuntime.sel("searchField")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setSearchField(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setSearchField:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property view
    override fun view(): MemorySegment {
        val sel = ObjCRuntime.sel("view")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    override fun setView(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setView:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property resignsFirstResponderWithCancel
    open fun resignsFirstResponderWithCancel(): Boolean {
        val sel = ObjCRuntime.sel("resignsFirstResponderWithCancel")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setResignsFirstResponderWithCancel(value: Boolean) {
        val sel = ObjCRuntime.sel("setResignsFirstResponderWithCancel:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property preferredWidthForSearchField
    open fun preferredWidthForSearchField(): Double {
        val sel = ObjCRuntime.sel("preferredWidthForSearchField")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    open fun setPreferredWidthForSearchField(value: Double) {
        val sel = ObjCRuntime.sel("setPreferredWidthForSearchField:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
}

