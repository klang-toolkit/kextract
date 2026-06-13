package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSButtonTouchBarItem
 * Superclass: NSTouchBarItem
 */
open class NSButtonTouchBarItem(override val ptr: MemorySegment) : NSTouchBarItem(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSButtonTouchBarItem") }
        
        fun buttonTouchBarItemWithIdentifier_title_target_action(identifier: MemorySegment, title: MemorySegment, target: MemorySegment, action: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("buttonTouchBarItemWithIdentifier:title:target:action:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, identifier, title, target, action) as MemorySegment
        }
        
        /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
        fun buttonTouchBarItemWithIdentifier_title_target_action(identifier: MemorySegment, title: String, target: MemorySegment, action: MemorySegment): MemorySegment = buttonTouchBarItemWithIdentifier_title_target_action(identifier, ObjCRuntime.newNSString(Arena.global(), title), target, action)
        
        fun buttonTouchBarItemWithIdentifier_image_target_action(identifier: MemorySegment, image: MemorySegment, target: MemorySegment, action: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("buttonTouchBarItemWithIdentifier:image:target:action:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, identifier, image, target, action) as MemorySegment
        }
        
        fun buttonTouchBarItemWithIdentifier_title_image_target_action(identifier: MemorySegment, title: MemorySegment, image: MemorySegment, target: MemorySegment, action: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("buttonTouchBarItemWithIdentifier:title:image:target:action:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, identifier, title, image, target, action) as MemorySegment
        }
        
        /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
        fun buttonTouchBarItemWithIdentifier_title_image_target_action(identifier: MemorySegment, title: String, image: MemorySegment, target: MemorySegment, action: MemorySegment): MemorySegment = buttonTouchBarItemWithIdentifier_title_image_target_action(identifier, ObjCRuntime.newNSString(Arena.global(), title), image, target, action)
        
    }
    
    // @property title
    open fun title(): MemorySegment {
        val sel = ObjCRuntime.sel("title")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setTitle(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setTitle:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun titleAsString(): String = ObjCRuntime.toJavaString(title())
    
    /** Convenience overload — accepts Kotlin [String] for the NSString property. */
    open fun setTitle(value: String) = setTitle(ObjCRuntime.newNSString(Arena.global(), value))
    
    // @property image
    open fun image(): MemorySegment {
        val sel = ObjCRuntime.sel("image")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setImage(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setImage:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property bezelColor
    open fun bezelColor(): MemorySegment {
        val sel = ObjCRuntime.sel("bezelColor")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setBezelColor(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setBezelColor:")
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
    
    // @property customizationLabel
    override fun customizationLabel(): MemorySegment {
        val sel = ObjCRuntime.sel("customizationLabel")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setCustomizationLabel(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setCustomizationLabel:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
}

