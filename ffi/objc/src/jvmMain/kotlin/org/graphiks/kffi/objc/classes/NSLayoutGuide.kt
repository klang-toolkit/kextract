package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSLayoutGuide
 * Superclass: NSObject
 * Protocols: NSCoding, NSUserInterfaceItemIdentification
 */
open class NSLayoutGuide(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSLayoutGuide") }
        
    }
    
    /** @return NSArray<NSLayoutConstraint *> * */
    open fun constraintsAffectingLayoutForOrientation(orientation: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("constraintsAffectingLayoutForOrientation:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, orientation) as MemorySegment
    }
    
    // @property frame
    open fun frame(): MemorySegment {
        val sel = ObjCRuntime.sel("frame")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"), ptr, sel) as MemorySegment
    }
    
    // @property owningView
    open fun owningView(): MemorySegment {
        val sel = ObjCRuntime.sel("owningView")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setOwningView(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setOwningView:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property identifier
    open fun identifier(): MemorySegment {
        val sel = ObjCRuntime.sel("identifier")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setIdentifier(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setIdentifier:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property leadingAnchor
    open fun leadingAnchor(): MemorySegment {
        val sel = ObjCRuntime.sel("leadingAnchor")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property trailingAnchor
    open fun trailingAnchor(): MemorySegment {
        val sel = ObjCRuntime.sel("trailingAnchor")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property leftAnchor
    open fun leftAnchor(): MemorySegment {
        val sel = ObjCRuntime.sel("leftAnchor")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property rightAnchor
    open fun rightAnchor(): MemorySegment {
        val sel = ObjCRuntime.sel("rightAnchor")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property topAnchor
    open fun topAnchor(): MemorySegment {
        val sel = ObjCRuntime.sel("topAnchor")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property bottomAnchor
    open fun bottomAnchor(): MemorySegment {
        val sel = ObjCRuntime.sel("bottomAnchor")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property widthAnchor
    open fun widthAnchor(): MemorySegment {
        val sel = ObjCRuntime.sel("widthAnchor")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property heightAnchor
    open fun heightAnchor(): MemorySegment {
        val sel = ObjCRuntime.sel("heightAnchor")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property centerXAnchor
    open fun centerXAnchor(): MemorySegment {
        val sel = ObjCRuntime.sel("centerXAnchor")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property centerYAnchor
    open fun centerYAnchor(): MemorySegment {
        val sel = ObjCRuntime.sel("centerYAnchor")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property hasAmbiguousLayout
    open fun hasAmbiguousLayout(): Boolean {
        val sel = ObjCRuntime.sel("hasAmbiguousLayout")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
}

