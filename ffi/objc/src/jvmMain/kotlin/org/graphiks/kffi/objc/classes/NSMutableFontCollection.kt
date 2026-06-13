package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSMutableFontCollection
 * Superclass: NSFontCollection
 */
open class NSMutableFontCollection(override val ptr: MemorySegment) : NSFontCollection(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSMutableFontCollection") }
        
        fun fontCollectionWithDescriptors(queryDescriptors: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("fontCollectionWithDescriptors:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, queryDescriptors) as MemorySegment
        }
        
        fun fontCollectionWithLocale(locale: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("fontCollectionWithLocale:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, locale) as MemorySegment
        }
        
        fun fontCollectionWithName(name: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("fontCollectionWithName:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, name) as MemorySegment
        }
        
        fun fontCollectionWithName_visibility(name: MemorySegment, visibility: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("fontCollectionWithName:visibility:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, name, visibility) as MemorySegment
        }
        
        fun fontCollectionWithAllAvailableDescriptors(): MemorySegment {
            val sel = ObjCRuntime.sel("fontCollectionWithAllAvailableDescriptors")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
    }
    
    open fun addQueryForDescriptors(descriptors: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("addQueryForDescriptors:")
        ObjCRuntime.msgSend(null, ptr, sel, descriptors)
    }
    
    open fun removeQueryForDescriptors(descriptors: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("removeQueryForDescriptors:")
        ObjCRuntime.msgSend(null, ptr, sel, descriptors)
    }
    
    // @property fontCollectionWithAllAvailableDescriptors
    override fun fontCollectionWithAllAvailableDescriptors(): MemorySegment {
        val sel = ObjCRuntime.sel("fontCollectionWithAllAvailableDescriptors")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property queryDescriptors
    /** @return NSArray<NSFontDescriptor *> * */
    override fun queryDescriptors(): MemorySegment {
        val sel = ObjCRuntime.sel("queryDescriptors")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setQueryDescriptors(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setQueryDescriptors:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property exclusionDescriptors
    /** @return NSArray<NSFontDescriptor *> * */
    override fun exclusionDescriptors(): MemorySegment {
        val sel = ObjCRuntime.sel("exclusionDescriptors")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setExclusionDescriptors(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setExclusionDescriptors:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
}

