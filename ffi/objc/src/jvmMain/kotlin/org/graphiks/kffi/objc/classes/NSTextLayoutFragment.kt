package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSTextLayoutFragment
 * Superclass: NSObject
 * Protocols: NSSecureCoding
 */
open class NSTextLayoutFragment(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSTextLayoutFragment") }
        
    }
    
    open fun initWithTextElement_range(textElement: MemorySegment, rangeInElement: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithTextElement:range:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, textElement, rangeInElement) as MemorySegment
    }
    
    open fun initWithCoder(coder: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithCoder:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, coder) as MemorySegment
    }
    
    open fun init(): MemorySegment {
        val sel = ObjCRuntime.sel("init")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    open fun textLineFragmentForVerticalOffset_requiresExactMatch(verticalOffset: Double, requiresExactMatch: Boolean): MemorySegment {
        val sel = ObjCRuntime.sel("textLineFragmentForVerticalOffset:requiresExactMatch:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, verticalOffset, requiresExactMatch) as MemorySegment
    }
    
    open fun textLineFragmentForTextLocation_isUpstreamAffinity(textLocation: MemorySegment, isUpstreamAffinity: Boolean): MemorySegment {
        val sel = ObjCRuntime.sel("textLineFragmentForTextLocation:isUpstreamAffinity:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, textLocation, isUpstreamAffinity) as MemorySegment
    }
    
    open fun invalidateLayout(): Unit {
        val sel = ObjCRuntime.sel("invalidateLayout")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun drawAtPoint_inContext(point: MemorySegment, context: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("drawAtPoint:inContext:")
        ObjCRuntime.msgSend(null, ptr, sel, ObjCRuntime.ObjCStructArg(point, MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("CGPoint")), context)
    }
    
    open fun frameForTextAttachmentAtLocation(location: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("frameForTextAttachmentAtLocation:")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"), ptr, sel, location) as MemorySegment
    }
    
    // @property textLayoutManager
    open fun textLayoutManager(): MemorySegment {
        val sel = ObjCRuntime.sel("textLayoutManager")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property textElement
    open fun textElement(): MemorySegment {
        val sel = ObjCRuntime.sel("textElement")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property rangeInElement
    open fun rangeInElement(): MemorySegment {
        val sel = ObjCRuntime.sel("rangeInElement")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property textLineFragments
    /** @return NSArray<NSTextLineFragment *> * */
    open fun textLineFragments(): MemorySegment {
        val sel = ObjCRuntime.sel("textLineFragments")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property layoutQueue
    open fun layoutQueue(): MemorySegment {
        val sel = ObjCRuntime.sel("layoutQueue")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setLayoutQueue(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setLayoutQueue:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property state
    open fun state(): MemorySegment {
        val sel = ObjCRuntime.sel("state")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property layoutFragmentFrame
    open fun layoutFragmentFrame(): MemorySegment {
        val sel = ObjCRuntime.sel("layoutFragmentFrame")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"), ptr, sel) as MemorySegment
    }
    
    // @property renderingSurfaceBounds
    open fun renderingSurfaceBounds(): MemorySegment {
        val sel = ObjCRuntime.sel("renderingSurfaceBounds")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"), ptr, sel) as MemorySegment
    }
    
    // @property leadingPadding
    open fun leadingPadding(): Double {
        val sel = ObjCRuntime.sel("leadingPadding")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    
    // @property trailingPadding
    open fun trailingPadding(): Double {
        val sel = ObjCRuntime.sel("trailingPadding")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    
    // @property topMargin
    open fun topMargin(): Double {
        val sel = ObjCRuntime.sel("topMargin")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    
    // @property bottomMargin
    open fun bottomMargin(): Double {
        val sel = ObjCRuntime.sel("bottomMargin")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    
    // @property textAttachmentViewProviders
    /** @return NSArray<NSTextAttachmentViewProvider *> * */
    open fun textAttachmentViewProviders(): MemorySegment {
        val sel = ObjCRuntime.sel("textAttachmentViewProviders")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
}

