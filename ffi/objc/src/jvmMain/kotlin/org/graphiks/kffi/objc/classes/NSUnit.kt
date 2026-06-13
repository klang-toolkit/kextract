package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSUnit
 * Superclass: NSObject
 * Protocols: NSCopying, NSSecureCoding
 */
open class NSUnit(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSUnit") }
        
        fun new(): MemorySegment {
            val sel = ObjCRuntime.sel("new")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
    }
    
    open fun init(): MemorySegment {
        val sel = ObjCRuntime.sel("init")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    open fun initWithSymbol(symbol: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithSymbol:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, symbol) as MemorySegment
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun initWithSymbol(symbol: String): MemorySegment = initWithSymbol(ObjCRuntime.newNSString(Arena.global(), symbol))
    
    // @property symbol
    open fun symbol(): MemorySegment {
        val sel = ObjCRuntime.sel("symbol")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun symbolAsString(): String = ObjCRuntime.toJavaString(symbol())
    
    
    // ── Instance variables (direct field access not supported via Panama) ──
    // ivar: _symbol: MemorySegment
}

