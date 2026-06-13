package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSTextAlternatives
 * Superclass: NSObject
 * Protocols: NSSecureCoding
 */
open class NSTextAlternatives(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSTextAlternatives") }
        
    }
    
    open fun initWithPrimaryString_alternativeStrings(primaryString: MemorySegment, alternativeStrings: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithPrimaryString:alternativeStrings:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, primaryString, alternativeStrings) as MemorySegment
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun initWithPrimaryString_alternativeStrings(primaryString: String, alternativeStrings: MemorySegment): MemorySegment = initWithPrimaryString_alternativeStrings(ObjCRuntime.newNSString(Arena.global(), primaryString), alternativeStrings)
    
    open fun noteSelectedAlternativeString(alternativeString: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("noteSelectedAlternativeString:")
        ObjCRuntime.msgSend(null, ptr, sel, alternativeString)
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun noteSelectedAlternativeString(alternativeString: String): Unit = noteSelectedAlternativeString(ObjCRuntime.newNSString(Arena.global(), alternativeString))
    
    // @property primaryString
    open fun primaryString(): MemorySegment {
        val sel = ObjCRuntime.sel("primaryString")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun primaryStringAsString(): String = ObjCRuntime.toJavaString(primaryString())
    
    // @property alternativeStrings
    /** @return NSArray<NSString *> * */
    open fun alternativeStrings(): MemorySegment {
        val sel = ObjCRuntime.sel("alternativeStrings")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
}

