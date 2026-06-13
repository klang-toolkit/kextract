package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSPreviewRepresentingActivityItem
 * Superclass: NSObject
 * Protocols: NSPreviewRepresentableActivityItem
 */
open class NSPreviewRepresentingActivityItem(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSPreviewRepresentingActivityItem") }
        
        fun new(): MemorySegment {
            val sel = ObjCRuntime.sel("new")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
    }
    
    open fun initWithItem_title_image_icon(item: MemorySegment, title: MemorySegment, image: MemorySegment, icon: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithItem:title:image:icon:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, item, title, image, icon) as MemorySegment
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun initWithItem_title_image_icon(item: MemorySegment, title: String, image: MemorySegment, icon: MemorySegment): MemorySegment = initWithItem_title_image_icon(item, ObjCRuntime.newNSString(Arena.global(), title), image, icon)
    
    open fun initWithItem_title_imageProvider_iconProvider(item: MemorySegment, title: MemorySegment, imageProvider: MemorySegment, iconProvider: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithItem:title:imageProvider:iconProvider:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, item, title, imageProvider, iconProvider) as MemorySegment
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun initWithItem_title_imageProvider_iconProvider(item: MemorySegment, title: String, imageProvider: MemorySegment, iconProvider: MemorySegment): MemorySegment = initWithItem_title_imageProvider_iconProvider(item, ObjCRuntime.newNSString(Arena.global(), title), imageProvider, iconProvider)
    
    open fun init(): MemorySegment {
        val sel = ObjCRuntime.sel("init")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
}

