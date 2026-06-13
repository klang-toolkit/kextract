package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSFormatter
 * Superclass: NSObject
 * Protocols: NSCopying, NSCoding
 */
open class NSFormatter(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSFormatter") }
        
    }
    
    open fun stringForObjectValue(obj: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("stringForObjectValue:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, obj) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    fun stringForObjectValueAsString(obj: MemorySegment): String = ObjCRuntime.toJavaString(stringForObjectValue(obj))
    
    open fun attributedStringForObjectValue_withDefaultAttributes(obj: MemorySegment, attrs: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("attributedStringForObjectValue:withDefaultAttributes:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, obj, attrs) as MemorySegment
    }
    
    open fun editingStringForObjectValue(obj: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("editingStringForObjectValue:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, obj) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    fun editingStringForObjectValueAsString(obj: MemorySegment): String = ObjCRuntime.toJavaString(editingStringForObjectValue(obj))
    
    open fun getObjectValue_forString_errorDescription(obj: MemorySegment, string: MemorySegment, error: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("getObjectValue:forString:errorDescription:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, obj, string, error) as Boolean
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun getObjectValue_forString_errorDescription(obj: MemorySegment, string: String, error: String): Boolean = getObjectValue_forString_errorDescription(obj, ObjCRuntime.newNSString(Arena.global(), string), ObjCRuntime.newNSString(Arena.global(), error))
    
    open fun isPartialStringValid_newEditingString_errorDescription(partialString: MemorySegment, newString: MemorySegment, error: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("isPartialStringValid:newEditingString:errorDescription:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, partialString, newString, error) as Boolean
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun isPartialStringValid_newEditingString_errorDescription(partialString: String, newString: String, error: String): Boolean = isPartialStringValid_newEditingString_errorDescription(ObjCRuntime.newNSString(Arena.global(), partialString), ObjCRuntime.newNSString(Arena.global(), newString), ObjCRuntime.newNSString(Arena.global(), error))
    
    open fun isPartialStringValid_proposedSelectedRange_originalString_originalSelectedRange_errorDescription(partialStringPtr: MemorySegment, proposedSelRangePtr: MemorySegment, origString: MemorySegment, origSelRange: MemorySegment, error: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("isPartialStringValid:proposedSelectedRange:originalString:originalSelectedRange:errorDescription:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, partialStringPtr, proposedSelRangePtr, origString, ObjCRuntime.ObjCStructArg(origSelRange, MemoryLayout.structLayout(ValueLayout.JAVA_LONG.withName("location"), ValueLayout.JAVA_LONG.withName("length")).withName("_NSRange")), error) as Boolean
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun isPartialStringValid_proposedSelectedRange_originalString_originalSelectedRange_errorDescription(partialStringPtr: String, proposedSelRangePtr: MemorySegment, origString: String, origSelRange: MemorySegment, error: String): Boolean = isPartialStringValid_proposedSelectedRange_originalString_originalSelectedRange_errorDescription(ObjCRuntime.newNSString(Arena.global(), partialStringPtr), proposedSelRangePtr, ObjCRuntime.newNSString(Arena.global(), origString), origSelRange, ObjCRuntime.newNSString(Arena.global(), error))
    
}

