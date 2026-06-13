package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSItemBadge
 * Superclass: NSObject
 */
open class NSItemBadge(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSItemBadge") }
        
        fun badgeWithCount(count: Long): MemorySegment {
            val sel = ObjCRuntime.sel("badgeWithCount:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, count) as MemorySegment
        }
        
        fun badgeWithText(text: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("badgeWithText:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, text) as MemorySegment
        }
        
        /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
        fun badgeWithText(text: String): MemorySegment = badgeWithText(ObjCRuntime.newNSString(Arena.global(), text))
        
        fun indicatorBadge(): MemorySegment {
            val sel = ObjCRuntime.sel("indicatorBadge")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
    }
    
    // @property text
    open fun text(): MemorySegment {
        val sel = ObjCRuntime.sel("text")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun textAsString(): String = ObjCRuntime.toJavaString(text())
    
}

