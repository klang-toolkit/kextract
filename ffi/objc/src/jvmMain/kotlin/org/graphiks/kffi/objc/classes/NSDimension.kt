package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSDimension
 * Superclass: NSUnit
 * Protocols: NSSecureCoding
 */
open class NSDimension(override val ptr: MemorySegment) : NSUnit(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSDimension") }
        
        fun baseUnit(): MemorySegment {
            val sel = ObjCRuntime.sel("baseUnit")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
    }
    
    open fun initWithSymbol_converter(symbol: MemorySegment, converter: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithSymbol:converter:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, symbol, converter) as MemorySegment
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun initWithSymbol_converter(symbol: String, converter: MemorySegment): MemorySegment = initWithSymbol_converter(ObjCRuntime.newNSString(Arena.global(), symbol), converter)
    
    // @property converter
    open fun converter(): MemorySegment {
        val sel = ObjCRuntime.sel("converter")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    
    // ── Instance variables (direct field access not supported via Panama) ──
    // ivar: _reserved: Long
    // ivar: _converter: MemorySegment
}

