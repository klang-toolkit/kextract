package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSCollectionViewItem
 * Superclass: NSViewController
 * Protocols: NSCopying, NSCollectionViewElement
 */
open class NSCollectionViewItem(override val ptr: MemorySegment) : NSViewController(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSCollectionViewItem") }
        
    }
    
    // @property collectionView
    open fun collectionView(): MemorySegment {
        val sel = ObjCRuntime.sel("collectionView")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property selected
    open fun isSelected(): Boolean {
        val sel = ObjCRuntime.sel("isSelected")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setSelected(value: Boolean) {
        val sel = ObjCRuntime.sel("setSelected:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property highlightState
    open fun highlightState(): MemorySegment {
        val sel = ObjCRuntime.sel("highlightState")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setHighlightState(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setHighlightState:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property imageView
    open fun imageView(): MemorySegment {
        val sel = ObjCRuntime.sel("imageView")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setImageView(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setImageView:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property textField
    open fun textField(): MemorySegment {
        val sel = ObjCRuntime.sel("textField")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setTextField(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setTextField:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property draggingImageComponents
    /** @return NSArray<NSDraggingImageComponent *> * */
    open fun draggingImageComponents(): MemorySegment {
        val sel = ObjCRuntime.sel("draggingImageComponents")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
}

