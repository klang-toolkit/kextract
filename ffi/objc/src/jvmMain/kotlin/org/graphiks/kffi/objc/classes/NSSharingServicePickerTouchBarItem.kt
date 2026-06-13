package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSSharingServicePickerTouchBarItem
 * Superclass: NSTouchBarItem
 */
open class NSSharingServicePickerTouchBarItem(override val ptr: MemorySegment) : NSTouchBarItem(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSSharingServicePickerTouchBarItem") }
        
    }
    
    // @property delegate
    /** @return id<NSSharingServicePickerTouchBarItemDelegate> */
    open fun delegate(): MemorySegment {
        val sel = ObjCRuntime.sel("delegate")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setDelegate(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setDelegate:")
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
    
    // @property buttonTitle
    open fun buttonTitle(): MemorySegment {
        val sel = ObjCRuntime.sel("buttonTitle")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setButtonTitle(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setButtonTitle:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun buttonTitleAsString(): String = ObjCRuntime.toJavaString(buttonTitle())
    
    /** Convenience overload — accepts Kotlin [String] for the NSString property. */
    open fun setButtonTitle(value: String) = setButtonTitle(ObjCRuntime.newNSString(Arena.global(), value))
    
    // @property buttonImage
    open fun buttonImage(): MemorySegment {
        val sel = ObjCRuntime.sel("buttonImage")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setButtonImage(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setButtonImage:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
}

