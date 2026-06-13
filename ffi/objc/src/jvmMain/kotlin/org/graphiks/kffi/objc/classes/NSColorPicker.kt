package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSColorPicker
 * Superclass: NSObject
 * Protocols: NSColorPickingDefault
 */
open class NSColorPicker(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSColorPicker") }
        
    }
    
    open fun initWithPickerMask_colorPanel(mask: Long, owningColorPanel: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithPickerMask:colorPanel:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, mask, owningColorPanel) as MemorySegment
    }
    
    open fun insertNewButtonImage_in(newButtonImage: MemorySegment, buttonCell: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("insertNewButtonImage:in:")
        ObjCRuntime.msgSend(null, ptr, sel, newButtonImage, buttonCell)
    }
    
    open fun viewSizeChanged(sender: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("viewSizeChanged:")
        ObjCRuntime.msgSend(null, ptr, sel, sender)
    }
    
    open fun attachColorList(colorList: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("attachColorList:")
        ObjCRuntime.msgSend(null, ptr, sel, colorList)
    }
    
    open fun detachColorList(colorList: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("detachColorList:")
        ObjCRuntime.msgSend(null, ptr, sel, colorList)
    }
    
    open fun setMode(mode: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("setMode:")
        ObjCRuntime.msgSend(null, ptr, sel, mode)
    }
    
    // @property colorPanel
    open fun colorPanel(): MemorySegment {
        val sel = ObjCRuntime.sel("colorPanel")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property provideNewButtonImage
    open fun provideNewButtonImage(): MemorySegment {
        val sel = ObjCRuntime.sel("provideNewButtonImage")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property buttonToolTip
    open fun buttonToolTip(): MemorySegment {
        val sel = ObjCRuntime.sel("buttonToolTip")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun buttonToolTipAsString(): String = ObjCRuntime.toJavaString(buttonToolTip())
    
    // @property minContentSize
    open fun minContentSize(): MemorySegment {
        val sel = ObjCRuntime.sel("minContentSize")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("CGSize"), ptr, sel) as MemorySegment
    }
    
}

