package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSColorPanel
 * Superclass: NSPanel
 */
open class NSColorPanel(override val ptr: MemorySegment) : NSPanel(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSColorPanel") }
        
        fun dragColor_withEvent_fromView(color: MemorySegment, event: MemorySegment, sourceView: MemorySegment): Boolean {
            val sel = ObjCRuntime.sel("dragColor:withEvent:fromView:")
            return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, _class, sel, color, event, sourceView) as Boolean
        }
        
        fun setPickerMask(mask: MemorySegment): Unit {
            val sel = ObjCRuntime.sel("setPickerMask:")
            ObjCRuntime.msgSend(null, _class, sel, mask)
        }
        
        fun setPickerMode(mode: MemorySegment): Unit {
            val sel = ObjCRuntime.sel("setPickerMode:")
            ObjCRuntime.msgSend(null, _class, sel, mode)
        }
        
        fun sharedColorPanel(): MemorySegment {
            val sel = ObjCRuntime.sel("sharedColorPanel")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun sharedColorPanelExists(): Boolean {
            val sel = ObjCRuntime.sel("sharedColorPanelExists")
            return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, _class, sel) as Boolean
        }
        
    }
    
    open fun setAction(selector: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("setAction:")
        ObjCRuntime.msgSend(null, ptr, sel, selector)
    }
    
    open fun setTarget(target: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("setTarget:")
        ObjCRuntime.msgSend(null, ptr, sel, target)
    }
    
    open fun attachColorList(colorList: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("attachColorList:")
        ObjCRuntime.msgSend(null, ptr, sel, colorList)
    }
    
    open fun detachColorList(colorList: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("detachColorList:")
        ObjCRuntime.msgSend(null, ptr, sel, colorList)
    }
    
    // @property sharedColorPanel
    open fun sharedColorPanel(): MemorySegment {
        val sel = ObjCRuntime.sel("sharedColorPanel")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property sharedColorPanelExists
    open fun sharedColorPanelExists(): Boolean {
        val sel = ObjCRuntime.sel("sharedColorPanelExists")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property accessoryView
    open fun accessoryView(): MemorySegment {
        val sel = ObjCRuntime.sel("accessoryView")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setAccessoryView(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setAccessoryView:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property continuous
    open fun isContinuous(): Boolean {
        val sel = ObjCRuntime.sel("isContinuous")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setContinuous(value: Boolean) {
        val sel = ObjCRuntime.sel("setContinuous:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property showsAlpha
    open fun showsAlpha(): Boolean {
        val sel = ObjCRuntime.sel("showsAlpha")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setShowsAlpha(value: Boolean) {
        val sel = ObjCRuntime.sel("setShowsAlpha:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property mode
    open fun mode(): MemorySegment {
        val sel = ObjCRuntime.sel("mode")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setMode(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setMode:")
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
    
    // @property alpha
    open fun alpha(): Double {
        val sel = ObjCRuntime.sel("alpha")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    
    // @property maximumLinearExposure
    open fun maximumLinearExposure(): Double {
        val sel = ObjCRuntime.sel("maximumLinearExposure")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    open fun setMaximumLinearExposure(value: Double) {
        val sel = ObjCRuntime.sel("setMaximumLinearExposure:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
}

