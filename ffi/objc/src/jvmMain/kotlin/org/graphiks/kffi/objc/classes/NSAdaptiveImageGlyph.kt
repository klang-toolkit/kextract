package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSAdaptiveImageGlyph
 * Superclass: NSObject
 * Protocols: NSCopying, NSSecureCoding, CTAdaptiveImageProviding
 */
open class NSAdaptiveImageGlyph(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSAdaptiveImageGlyph") }
        
        fun contentType(): MemorySegment {
            val sel = ObjCRuntime.sel("contentType")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
    }
    
    open fun initWithImageContent(imageContent: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithImageContent:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, imageContent) as MemorySegment
    }
    
    open fun initWithCoder(coder: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithCoder:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, coder) as MemorySegment
    }
    
    open fun init(): MemorySegment {
        val sel = ObjCRuntime.sel("init")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property imageContent
    open fun imageContent(): MemorySegment {
        val sel = ObjCRuntime.sel("imageContent")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property contentIdentifier
    open fun contentIdentifier(): MemorySegment {
        val sel = ObjCRuntime.sel("contentIdentifier")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun contentIdentifierAsString(): String = ObjCRuntime.toJavaString(contentIdentifier())
    
    // @property contentDescription
    open fun contentDescription(): MemorySegment {
        val sel = ObjCRuntime.sel("contentDescription")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun contentDescriptionAsString(): String = ObjCRuntime.toJavaString(contentDescription())
    
    // @property contentType
    open fun contentType(): MemorySegment {
        val sel = ObjCRuntime.sel("contentType")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
}

