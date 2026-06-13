package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSExtensionItem
 * Superclass: NSObject
 * Protocols: NSCopying, NSSecureCoding
 */
open class NSExtensionItem(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSExtensionItem") }
        
    }
    
    // @property attributedTitle
    open fun attributedTitle(): MemorySegment {
        val sel = ObjCRuntime.sel("attributedTitle")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setAttributedTitle(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setAttributedTitle:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property attributedContentText
    open fun attributedContentText(): MemorySegment {
        val sel = ObjCRuntime.sel("attributedContentText")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setAttributedContentText(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setAttributedContentText:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property attachments
    /** @return NSArray<NSItemProvider *> * */
    open fun attachments(): MemorySegment {
        val sel = ObjCRuntime.sel("attachments")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setAttachments(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setAttachments:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property userInfo
    open fun userInfo(): MemorySegment {
        val sel = ObjCRuntime.sel("userInfo")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setUserInfo(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setUserInfo:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
}

