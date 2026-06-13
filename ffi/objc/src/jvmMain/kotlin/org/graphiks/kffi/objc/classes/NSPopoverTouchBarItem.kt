package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSPopoverTouchBarItem
 * Superclass: NSTouchBarItem
 */
open class NSPopoverTouchBarItem(override val ptr: MemorySegment) : NSTouchBarItem(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSPopoverTouchBarItem") }
        
    }
    
    open fun showPopover(sender: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("showPopover:")
        ObjCRuntime.msgSend(null, ptr, sel, sender)
    }
    
    open fun dismissPopover(sender: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("dismissPopover:")
        ObjCRuntime.msgSend(null, ptr, sel, sender)
    }
    
    open fun makeStandardActivatePopoverGestureRecognizer(): MemorySegment {
        val sel = ObjCRuntime.sel("makeStandardActivatePopoverGestureRecognizer")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property popoverTouchBar
    open fun popoverTouchBar(): MemorySegment {
        val sel = ObjCRuntime.sel("popoverTouchBar")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setPopoverTouchBar(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setPopoverTouchBar:")
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
    
    // @property collapsedRepresentation
    open fun collapsedRepresentation(): MemorySegment {
        val sel = ObjCRuntime.sel("collapsedRepresentation")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setCollapsedRepresentation(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setCollapsedRepresentation:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property collapsedRepresentationImage
    open fun collapsedRepresentationImage(): MemorySegment {
        val sel = ObjCRuntime.sel("collapsedRepresentationImage")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setCollapsedRepresentationImage(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setCollapsedRepresentationImage:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property collapsedRepresentationLabel
    open fun collapsedRepresentationLabel(): MemorySegment {
        val sel = ObjCRuntime.sel("collapsedRepresentationLabel")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setCollapsedRepresentationLabel(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setCollapsedRepresentationLabel:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun collapsedRepresentationLabelAsString(): String = ObjCRuntime.toJavaString(collapsedRepresentationLabel())
    
    /** Convenience overload — accepts Kotlin [String] for the NSString property. */
    open fun setCollapsedRepresentationLabel(value: String) = setCollapsedRepresentationLabel(ObjCRuntime.newNSString(Arena.global(), value))
    
    // @property pressAndHoldTouchBar
    open fun pressAndHoldTouchBar(): MemorySegment {
        val sel = ObjCRuntime.sel("pressAndHoldTouchBar")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setPressAndHoldTouchBar(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setPressAndHoldTouchBar:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property showsCloseButton
    open fun showsCloseButton(): Boolean {
        val sel = ObjCRuntime.sel("showsCloseButton")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setShowsCloseButton(value: Boolean) {
        val sel = ObjCRuntime.sel("setShowsCloseButton:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
}

