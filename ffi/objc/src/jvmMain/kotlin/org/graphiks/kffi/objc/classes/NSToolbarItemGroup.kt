package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSToolbarItemGroup
 * Superclass: NSToolbarItem
 */
open class NSToolbarItemGroup(override val ptr: MemorySegment) : NSToolbarItem(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSToolbarItemGroup") }
        
        fun groupWithItemIdentifier_titles_selectionMode_labels_target_action(itemIdentifier: MemorySegment, titles: MemorySegment, selectionMode: MemorySegment, labels: MemorySegment, target: MemorySegment, action: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("groupWithItemIdentifier:titles:selectionMode:labels:target:action:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, itemIdentifier, titles, selectionMode, labels, target, action) as MemorySegment
        }
        
        fun groupWithItemIdentifier_images_selectionMode_labels_target_action(itemIdentifier: MemorySegment, images: MemorySegment, selectionMode: MemorySegment, labels: MemorySegment, target: MemorySegment, action: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("groupWithItemIdentifier:images:selectionMode:labels:target:action:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, itemIdentifier, images, selectionMode, labels, target, action) as MemorySegment
        }
        
    }
    
    open fun setSelected_atIndex(selected: Boolean, index: Long): Unit {
        val sel = ObjCRuntime.sel("setSelected:atIndex:")
        ObjCRuntime.msgSend(null, ptr, sel, selected, index)
    }
    
    open fun isSelectedAtIndex(index: Long): Boolean {
        val sel = ObjCRuntime.sel("isSelectedAtIndex:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, index) as Boolean
    }
    
    // @property subitems
    /** @return NSArray<__kindof NSToolbarItem *> * */
    open fun subitems(): MemorySegment {
        val sel = ObjCRuntime.sel("subitems")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setSubitems(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setSubitems:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
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
    
    // @property selectionMode
    open fun selectionMode(): MemorySegment {
        val sel = ObjCRuntime.sel("selectionMode")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setSelectionMode(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setSelectionMode:")
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
    
}

