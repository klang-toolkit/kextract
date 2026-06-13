package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSGroupTouchBarItem
 * Superclass: NSTouchBarItem
 */
open class NSGroupTouchBarItem(override val ptr: MemorySegment) : NSTouchBarItem(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSGroupTouchBarItem") }
        
        fun groupItemWithIdentifier_items(identifier: MemorySegment, items: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("groupItemWithIdentifier:items:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, identifier, items) as MemorySegment
        }
        
        fun groupItemWithIdentifier_items_allowedCompressionOptions(identifier: MemorySegment, items: MemorySegment, allowedCompressionOptions: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("groupItemWithIdentifier:items:allowedCompressionOptions:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, identifier, items, allowedCompressionOptions) as MemorySegment
        }
        
        fun alertStyleGroupItemWithIdentifier(identifier: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("alertStyleGroupItemWithIdentifier:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, identifier) as MemorySegment
        }
        
    }
    
    // @property groupTouchBar
    open fun groupTouchBar(): MemorySegment {
        val sel = ObjCRuntime.sel("groupTouchBar")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setGroupTouchBar(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setGroupTouchBar:")
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
    
    // @property groupUserInterfaceLayoutDirection
    open fun groupUserInterfaceLayoutDirection(): MemorySegment {
        val sel = ObjCRuntime.sel("groupUserInterfaceLayoutDirection")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setGroupUserInterfaceLayoutDirection(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setGroupUserInterfaceLayoutDirection:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property prefersEqualWidths
    open fun prefersEqualWidths(): Boolean {
        val sel = ObjCRuntime.sel("prefersEqualWidths")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setPrefersEqualWidths(value: Boolean) {
        val sel = ObjCRuntime.sel("setPrefersEqualWidths:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property preferredItemWidth
    open fun preferredItemWidth(): Double {
        val sel = ObjCRuntime.sel("preferredItemWidth")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    open fun setPreferredItemWidth(value: Double) {
        val sel = ObjCRuntime.sel("setPreferredItemWidth:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property effectiveCompressionOptions
    open fun effectiveCompressionOptions(): MemorySegment {
        val sel = ObjCRuntime.sel("effectiveCompressionOptions")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property prioritizedCompressionOptions
    /** @return NSArray<NSUserInterfaceCompressionOptions *> * */
    open fun prioritizedCompressionOptions(): MemorySegment {
        val sel = ObjCRuntime.sel("prioritizedCompressionOptions")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setPrioritizedCompressionOptions(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setPrioritizedCompressionOptions:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
}

