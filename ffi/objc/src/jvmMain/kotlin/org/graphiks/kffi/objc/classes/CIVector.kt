package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: CIVector
 * Superclass: NSObject
 * Protocols: NSCopying, NSSecureCoding
 */
open class CIVector(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("CIVector") }
        
        fun vectorWithValues_count(values: MemorySegment, count: Long): MemorySegment {
            val sel = ObjCRuntime.sel("vectorWithValues:count:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, values, count) as MemorySegment
        }
        
        fun vectorWithX(x: Double): MemorySegment {
            val sel = ObjCRuntime.sel("vectorWithX:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, x) as MemorySegment
        }
        
        fun vectorWithX_Y(x: Double, y: Double): MemorySegment {
            val sel = ObjCRuntime.sel("vectorWithX:Y:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, x, y) as MemorySegment
        }
        
        fun vectorWithX_Y_Z(x: Double, y: Double, z: Double): MemorySegment {
            val sel = ObjCRuntime.sel("vectorWithX:Y:Z:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, x, y, z) as MemorySegment
        }
        
        fun vectorWithX_Y_Z_W(x: Double, y: Double, z: Double, w: Double): MemorySegment {
            val sel = ObjCRuntime.sel("vectorWithX:Y:Z:W:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, x, y, z, w) as MemorySegment
        }
        
        fun vectorWithCGPoint(p: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("vectorWithCGPoint:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, ObjCRuntime.ObjCStructArg(p, MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("CGPoint"))) as MemorySegment
        }
        
        fun vectorWithCGRect(r: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("vectorWithCGRect:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, ObjCRuntime.ObjCStructArg(r, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"))) as MemorySegment
        }
        
        fun vectorWithCGAffineTransform(t: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("vectorWithCGAffineTransform:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, t) as MemorySegment
        }
        
        fun vectorWithString(representation: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("vectorWithString:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, representation) as MemorySegment
        }
        
        /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
        fun vectorWithString(representation: String): MemorySegment = vectorWithString(ObjCRuntime.newNSString(Arena.global(), representation))
        
    }
    
    open fun initWithValues_count(values: MemorySegment, count: Long): MemorySegment {
        val sel = ObjCRuntime.sel("initWithValues:count:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, values, count) as MemorySegment
    }
    
    open fun initWithX(x: Double): MemorySegment {
        val sel = ObjCRuntime.sel("initWithX:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, x) as MemorySegment
    }
    
    open fun initWithX_Y(x: Double, y: Double): MemorySegment {
        val sel = ObjCRuntime.sel("initWithX:Y:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, x, y) as MemorySegment
    }
    
    open fun initWithX_Y_Z(x: Double, y: Double, z: Double): MemorySegment {
        val sel = ObjCRuntime.sel("initWithX:Y:Z:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, x, y, z) as MemorySegment
    }
    
    open fun initWithX_Y_Z_W(x: Double, y: Double, z: Double, w: Double): MemorySegment {
        val sel = ObjCRuntime.sel("initWithX:Y:Z:W:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, x, y, z, w) as MemorySegment
    }
    
    open fun initWithCGPoint(p: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithCGPoint:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, ObjCRuntime.ObjCStructArg(p, MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("CGPoint"))) as MemorySegment
    }
    
    open fun initWithCGRect(r: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithCGRect:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, ObjCRuntime.ObjCStructArg(r, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"))) as MemorySegment
    }
    
    open fun initWithCGAffineTransform(t: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithCGAffineTransform:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, t) as MemorySegment
    }
    
    open fun initWithString(representation: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithString:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, representation) as MemorySegment
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun initWithString(representation: String): MemorySegment = initWithString(ObjCRuntime.newNSString(Arena.global(), representation))
    
    open fun valueAtIndex(index: Long): Double {
        val sel = ObjCRuntime.sel("valueAtIndex:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel, index) as Double
    }
    
    // @property count
    open fun count(): Long {
        val sel = ObjCRuntime.sel("count")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    
    // @property X
    open fun X(): Double {
        val sel = ObjCRuntime.sel("X")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    
    // @property Y
    open fun Y(): Double {
        val sel = ObjCRuntime.sel("Y")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    
    // @property Z
    open fun Z(): Double {
        val sel = ObjCRuntime.sel("Z")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    
    // @property W
    open fun W(): Double {
        val sel = ObjCRuntime.sel("W")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    
    // @property CGPointValue
    open fun CGPointValue(): MemorySegment {
        val sel = ObjCRuntime.sel("CGPointValue")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("CGPoint"), ptr, sel) as MemorySegment
    }
    
    // @property CGRectValue
    open fun CGRectValue(): MemorySegment {
        val sel = ObjCRuntime.sel("CGRectValue")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"), ptr, sel) as MemorySegment
    }
    
    // @property CGAffineTransformValue
    open fun CGAffineTransformValue(): MemorySegment {
        val sel = ObjCRuntime.sel("CGAffineTransformValue")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property stringRepresentation
    open fun stringRepresentation(): MemorySegment {
        val sel = ObjCRuntime.sel("stringRepresentation")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun stringRepresentationAsString(): String = ObjCRuntime.toJavaString(stringRepresentation())
    
    
    // ── Instance variables (direct field access not supported via Panama) ──
    // ivar: _count: Long
    // ivar: _u: MemorySegment
}

