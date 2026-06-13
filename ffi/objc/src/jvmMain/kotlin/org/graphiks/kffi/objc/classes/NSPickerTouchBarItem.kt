package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSPickerTouchBarItem
 * Superclass: NSTouchBarItem
 */
open class NSPickerTouchBarItem(override val ptr: MemorySegment) : NSTouchBarItem(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSPickerTouchBarItem") }
        
        fun pickerTouchBarItemWithIdentifier_labels_selectionMode_target_action(identifier: MemorySegment, labels: MemorySegment, selectionMode: MemorySegment, target: MemorySegment, action: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("pickerTouchBarItemWithIdentifier:labels:selectionMode:target:action:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, identifier, labels, selectionMode, target, action) as MemorySegment
        }
        
        fun pickerTouchBarItemWithIdentifier_images_selectionMode_target_action(identifier: MemorySegment, images: MemorySegment, selectionMode: MemorySegment, target: MemorySegment, action: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("pickerTouchBarItemWithIdentifier:images:selectionMode:target:action:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, identifier, images, selectionMode, target, action) as MemorySegment
        }
        
    }
    
    open fun setImage_atIndex(image: MemorySegment, index: Long): Unit {
        val sel = ObjCRuntime.sel("setImage:atIndex:")
        ObjCRuntime.msgSend(null, ptr, sel, image, index)
    }
    
    open fun imageAtIndex(index: Long): MemorySegment {
        val sel = ObjCRuntime.sel("imageAtIndex:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, index) as MemorySegment
    }
    
    open fun setLabel_atIndex(label: MemorySegment, index: Long): Unit {
        val sel = ObjCRuntime.sel("setLabel:atIndex:")
        ObjCRuntime.msgSend(null, ptr, sel, label, index)
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun setLabel_atIndex(label: String, index: Long): Unit = setLabel_atIndex(ObjCRuntime.newNSString(Arena.global(), label), index)
    
    open fun labelAtIndex(index: Long): MemorySegment {
        val sel = ObjCRuntime.sel("labelAtIndex:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, index) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    fun labelAtIndexAsString(index: Long): String = ObjCRuntime.toJavaString(labelAtIndex(index))
    
    open fun setEnabled_atIndex(enabled: Boolean, index: Long): Unit {
        val sel = ObjCRuntime.sel("setEnabled:atIndex:")
        ObjCRuntime.msgSend(null, ptr, sel, enabled, index)
    }
    
    open fun isEnabledAtIndex(index: Long): Boolean {
        val sel = ObjCRuntime.sel("isEnabledAtIndex:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, index) as Boolean
    }
    
    // @property controlRepresentation
    open fun controlRepresentation(): MemorySegment {
        val sel = ObjCRuntime.sel("controlRepresentation")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setControlRepresentation(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setControlRepresentation:")
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
    
    // @property collapsedRepresentationImage
    open fun collapsedRepresentationImage(): MemorySegment {
        val sel = ObjCRuntime.sel("collapsedRepresentationImage")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setCollapsedRepresentationImage(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setCollapsedRepresentationImage:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property selectedIndex
    open fun selectedIndex(): Long {
        val sel = ObjCRuntime.sel("selectedIndex")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    open fun setSelectedIndex(value: Long) {
        val sel = ObjCRuntime.sel("setSelectedIndex:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property selectionColor
    open fun selectionColor(): MemorySegment {
        val sel = ObjCRuntime.sel("selectionColor")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setSelectionColor(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setSelectionColor:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property selectionMode
    open fun selectionMode(): MemorySegment {
        val sel = ObjCRuntime.sel("selectionMode")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setSelectionMode(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setSelectionMode:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property numberOfOptions
    open fun numberOfOptions(): Long {
        val sel = ObjCRuntime.sel("numberOfOptions")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    open fun setNumberOfOptions(value: Long) {
        val sel = ObjCRuntime.sel("setNumberOfOptions:")
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

