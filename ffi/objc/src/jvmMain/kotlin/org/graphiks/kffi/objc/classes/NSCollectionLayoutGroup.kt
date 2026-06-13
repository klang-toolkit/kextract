package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSCollectionLayoutGroup
 * Superclass: NSCollectionLayoutItem
 * Protocols: NSCopying
 */
open class NSCollectionLayoutGroup(override val ptr: MemorySegment) : NSCollectionLayoutItem(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSCollectionLayoutGroup") }
        
        fun horizontalGroupWithLayoutSize_subitem_count(layoutSize: MemorySegment, subitem: MemorySegment, count: Long): MemorySegment {
            val sel = ObjCRuntime.sel("horizontalGroupWithLayoutSize:subitem:count:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, layoutSize, subitem, count) as MemorySegment
        }
        
        fun horizontalGroupWithLayoutSize_subitems(layoutSize: MemorySegment, subitems: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("horizontalGroupWithLayoutSize:subitems:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, layoutSize, subitems) as MemorySegment
        }
        
        fun verticalGroupWithLayoutSize_subitem_count(layoutSize: MemorySegment, subitem: MemorySegment, count: Long): MemorySegment {
            val sel = ObjCRuntime.sel("verticalGroupWithLayoutSize:subitem:count:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, layoutSize, subitem, count) as MemorySegment
        }
        
        fun verticalGroupWithLayoutSize_subitems(layoutSize: MemorySegment, subitems: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("verticalGroupWithLayoutSize:subitems:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, layoutSize, subitems) as MemorySegment
        }
        
        fun customGroupWithLayoutSize_itemProvider(layoutSize: MemorySegment, itemProvider: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("customGroupWithLayoutSize:itemProvider:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, layoutSize, itemProvider) as MemorySegment
        }
        
        fun new(): MemorySegment {
            val sel = ObjCRuntime.sel("new")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
    }
    
    override fun init(): MemorySegment {
        val sel = ObjCRuntime.sel("init")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    open fun visualDescription(): MemorySegment {
        val sel = ObjCRuntime.sel("visualDescription")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    fun visualDescriptionAsString(): String = ObjCRuntime.toJavaString(visualDescription())
    
    // @property supplementaryItems
    /** @return NSArray<NSCollectionLayoutSupplementaryItem *> * */
    override fun supplementaryItems(): MemorySegment {
        val sel = ObjCRuntime.sel("supplementaryItems")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setSupplementaryItems(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setSupplementaryItems:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property interItemSpacing
    open fun interItemSpacing(): MemorySegment {
        val sel = ObjCRuntime.sel("interItemSpacing")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setInterItemSpacing(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setInterItemSpacing:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property subitems
    /** @return NSArray<NSCollectionLayoutItem *> * */
    open fun subitems(): MemorySegment {
        val sel = ObjCRuntime.sel("subitems")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
}

