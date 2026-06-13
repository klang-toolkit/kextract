package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSWindowTabGroup
 * Superclass: NSObject
 */
open class NSWindowTabGroup(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSWindowTabGroup") }
        
    }
    
    open fun addWindow(window: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("addWindow:")
        ObjCRuntime.msgSend(null, ptr, sel, window)
    }
    
    open fun insertWindow_atIndex(window: MemorySegment, index: Long): Unit {
        val sel = ObjCRuntime.sel("insertWindow:atIndex:")
        ObjCRuntime.msgSend(null, ptr, sel, window, index)
    }
    
    open fun removeWindow(window: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("removeWindow:")
        ObjCRuntime.msgSend(null, ptr, sel, window)
    }
    
    // @property identifier
    open fun identifier(): MemorySegment {
        val sel = ObjCRuntime.sel("identifier")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property windows
    /** @return NSArray<NSWindow *> * */
    open fun windows(): MemorySegment {
        val sel = ObjCRuntime.sel("windows")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property overviewVisible
    open fun isOverviewVisible(): Boolean {
        val sel = ObjCRuntime.sel("isOverviewVisible")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setOverviewVisible(value: Boolean) {
        val sel = ObjCRuntime.sel("setOverviewVisible:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property tabBarVisible
    open fun isTabBarVisible(): Boolean {
        val sel = ObjCRuntime.sel("isTabBarVisible")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property selectedWindow
    open fun selectedWindow(): MemorySegment {
        val sel = ObjCRuntime.sel("selectedWindow")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setSelectedWindow(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setSelectedWindow:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
}

