package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSTextInsertionIndicator
 * Superclass: NSView
 */
open class NSTextInsertionIndicator(override val ptr: MemorySegment) : NSView(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSTextInsertionIndicator") }
        
    }
    
    // @property displayMode
    open fun displayMode(): MemorySegment {
        val sel = ObjCRuntime.sel("displayMode")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setDisplayMode(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setDisplayMode:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property color
    open fun color(): MemorySegment {
        val sel = ObjCRuntime.sel("color")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setColor(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setColor:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property automaticModeOptions
    open fun automaticModeOptions(): MemorySegment {
        val sel = ObjCRuntime.sel("automaticModeOptions")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setAutomaticModeOptions(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setAutomaticModeOptions:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property effectsViewInserter
    open fun effectsViewInserter(): MemorySegment {
        val sel = ObjCRuntime.sel("effectsViewInserter")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setEffectsViewInserter(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setEffectsViewInserter:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
}

