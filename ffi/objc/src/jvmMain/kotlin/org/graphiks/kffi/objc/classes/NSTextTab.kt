package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSTextTab
 * Superclass: NSObject
 * Protocols: NSCopying, NSCoding, NSSecureCoding
 */
open class NSTextTab(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSTextTab") }
        
        fun columnTerminatorsForLocale(aLocale: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("columnTerminatorsForLocale:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, aLocale) as MemorySegment
        }
        
    }
    
    // @property location
    open fun location(): Double {
        val sel = ObjCRuntime.sel("location")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    
    // @property options
    /** @return NSDictionary<NSTextTabOptionKey,id> * */
    open fun options(): MemorySegment {
        val sel = ObjCRuntime.sel("options")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
}

// ── Category:  on NSTextTab ─────────────────────────────────────────

fun NSTextTab.initWithTextAlignment_location_options(alignment: MemorySegment, loc: Double, options: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("initWithTextAlignment:location:options:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, alignment, loc, options) as MemorySegment
}

fun NSTextTab.alignment(): MemorySegment {
    val sel = ObjCRuntime.sel("alignment")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

// ── Category: NSTextTabDeprecated on NSTextTab ─────────────────────────────────────────

fun NSTextTab.initWithType_location(type: MemorySegment, loc: Double): MemorySegment {
    val sel = ObjCRuntime.sel("initWithType:location:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, type, loc) as MemorySegment
}

fun NSTextTab.tabStopType(): MemorySegment {
    val sel = ObjCRuntime.sel("tabStopType")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

