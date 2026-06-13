package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSAttributedStringMarkdownParsingOptions
 * Superclass: NSObject
 * Protocols: NSCopying
 */
open class NSAttributedStringMarkdownParsingOptions(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSAttributedStringMarkdownParsingOptions") }
        
    }
    
    open fun init(): MemorySegment {
        val sel = ObjCRuntime.sel("init")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property allowsExtendedAttributes
    open fun allowsExtendedAttributes(): Boolean {
        val sel = ObjCRuntime.sel("allowsExtendedAttributes")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setAllowsExtendedAttributes(value: Boolean) {
        val sel = ObjCRuntime.sel("setAllowsExtendedAttributes:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property interpretedSyntax
    open fun interpretedSyntax(): MemorySegment {
        val sel = ObjCRuntime.sel("interpretedSyntax")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setInterpretedSyntax(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setInterpretedSyntax:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property failurePolicy
    open fun failurePolicy(): MemorySegment {
        val sel = ObjCRuntime.sel("failurePolicy")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setFailurePolicy(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setFailurePolicy:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property languageCode
    open fun languageCode(): MemorySegment {
        val sel = ObjCRuntime.sel("languageCode")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setLanguageCode(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setLanguageCode:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun languageCodeAsString(): String = ObjCRuntime.toJavaString(languageCode())
    
    /** Convenience overload — accepts Kotlin [String] for the NSString property. */
    open fun setLanguageCode(value: String) = setLanguageCode(ObjCRuntime.newNSString(Arena.global(), value))
    
    // @property appliesSourcePositionAttributes
    open fun appliesSourcePositionAttributes(): Boolean {
        val sel = ObjCRuntime.sel("appliesSourcePositionAttributes")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setAppliesSourcePositionAttributes(value: Boolean) {
        val sel = ObjCRuntime.sel("setAppliesSourcePositionAttributes:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
}

