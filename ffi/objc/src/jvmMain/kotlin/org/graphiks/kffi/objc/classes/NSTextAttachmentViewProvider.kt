package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSTextAttachmentViewProvider
 * Superclass: NSObject
 */
open class NSTextAttachmentViewProvider(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSTextAttachmentViewProvider") }
        
        fun new(): MemorySegment {
            val sel = ObjCRuntime.sel("new")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
    }
    
    open fun initWithTextAttachment_parentView_textLayoutManager_location(textAttachment: MemorySegment, parentView: MemorySegment, textLayoutManager: MemorySegment, location: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithTextAttachment:parentView:textLayoutManager:location:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, textAttachment, parentView, textLayoutManager, location) as MemorySegment
    }
    
    open fun init(): MemorySegment {
        val sel = ObjCRuntime.sel("init")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    open fun loadView(): Unit {
        val sel = ObjCRuntime.sel("loadView")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun attachmentBoundsForAttributes_location_textContainer_proposedLineFragment_position(attributes: MemorySegment, location: MemorySegment, textContainer: MemorySegment, proposedLineFragment: MemorySegment, position: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("attachmentBoundsForAttributes:location:textContainer:proposedLineFragment:position:")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"), ptr, sel, attributes, location, textContainer, ObjCRuntime.ObjCStructArg(proposedLineFragment, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect")), ObjCRuntime.ObjCStructArg(position, MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("CGPoint"))) as MemorySegment
    }
    
    // @property textAttachment
    open fun textAttachment(): MemorySegment {
        val sel = ObjCRuntime.sel("textAttachment")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property textLayoutManager
    open fun textLayoutManager(): MemorySegment {
        val sel = ObjCRuntime.sel("textLayoutManager")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property location
    /** @return id<NSTextLocation> */
    open fun location(): MemorySegment {
        val sel = ObjCRuntime.sel("location")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property view
    open fun view(): MemorySegment {
        val sel = ObjCRuntime.sel("view")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setView(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setView:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property tracksTextAttachmentViewBounds
    open fun tracksTextAttachmentViewBounds(): Boolean {
        val sel = ObjCRuntime.sel("tracksTextAttachmentViewBounds")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setTracksTextAttachmentViewBounds(value: Boolean) {
        val sel = ObjCRuntime.sel("setTracksTextAttachmentViewBounds:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
}

