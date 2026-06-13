package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSColorPickerTouchBarItem
 * Superclass: NSTouchBarItem
 */
open class NSColorPickerTouchBarItem(override val ptr: MemorySegment) : NSTouchBarItem(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSColorPickerTouchBarItem") }
        
        fun colorPickerWithIdentifier(identifier: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("colorPickerWithIdentifier:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, identifier) as MemorySegment
        }
        
        fun textColorPickerWithIdentifier(identifier: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("textColorPickerWithIdentifier:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, identifier) as MemorySegment
        }
        
        fun strokeColorPickerWithIdentifier(identifier: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("strokeColorPickerWithIdentifier:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, identifier) as MemorySegment
        }
        
        fun colorPickerWithIdentifier_buttonImage(identifier: MemorySegment, image: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("colorPickerWithIdentifier:buttonImage:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, identifier, image) as MemorySegment
        }
        
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
    
    // @property showsAlpha
    open fun showsAlpha(): Boolean {
        val sel = ObjCRuntime.sel("showsAlpha")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setShowsAlpha(value: Boolean) {
        val sel = ObjCRuntime.sel("setShowsAlpha:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property allowedColorSpaces
    /** @return NSArray<NSColorSpace *> * */
    open fun allowedColorSpaces(): MemorySegment {
        val sel = ObjCRuntime.sel("allowedColorSpaces")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setAllowedColorSpaces(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setAllowedColorSpaces:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property colorList
    open fun colorList(): MemorySegment {
        val sel = ObjCRuntime.sel("colorList")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setColorList(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setColorList:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property customizationLabel
    override fun customizationLabel(): MemorySegment {
        val sel = ObjCRuntime.sel("customizationLabel")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setCustomizationLabel(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setCustomizationLabel:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property target
    open fun target(): MemorySegment {
        val sel = ObjCRuntime.sel("target")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setTarget(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setTarget:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property action
    open fun action(): MemorySegment {
        val sel = ObjCRuntime.sel("action")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setAction(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setAction:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property enabled
    open fun isEnabled(): Boolean {
        val sel = ObjCRuntime.sel("isEnabled")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setEnabled(value: Boolean) {
        val sel = ObjCRuntime.sel("setEnabled:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
}

