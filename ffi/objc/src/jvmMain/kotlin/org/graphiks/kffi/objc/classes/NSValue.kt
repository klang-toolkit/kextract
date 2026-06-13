package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSValue
 * Superclass: NSObject
 * Protocols: NSCopying, NSSecureCoding
 */
open class NSValue(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSValue") }
        
    }
    
    open fun getValue_size(value: MemorySegment, size: Long): Unit {
        val sel = ObjCRuntime.sel("getValue:size:")
        ObjCRuntime.msgSend(null, ptr, sel, value, size)
    }
    
    open fun initWithBytes_objCType(value: MemorySegment, type: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithBytes:objCType:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, value, type) as MemorySegment
    }
    
    open fun initWithCoder(coder: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithCoder:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, coder) as MemorySegment
    }
    
    // @property objCType
    open fun objCType(): MemorySegment {
        val sel = ObjCRuntime.sel("objCType")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
}

// ── Category: NSValueCreation on NSValue ─────────────────────────────────────────

// Class method: +[NSValue valueWithBytes:objCType:]
fun NSValue_valueWithBytes_objCType(value: MemorySegment, type: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("valueWithBytes:objCType:")
    val cls = ObjCRuntime.getClass("NSValue")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel, value, type) as MemorySegment
}

// Class method: +[NSValue value:withObjCType:]
fun NSValue_value_withObjCType(value: MemorySegment, type: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("value:withObjCType:")
    val cls = ObjCRuntime.getClass("NSValue")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel, value, type) as MemorySegment
}

// ── Category: NSValueExtensionMethods on NSValue ─────────────────────────────────────────

fun NSValue.isEqualToValue(value: MemorySegment): Boolean {
    val sel = ObjCRuntime.sel("isEqualToValue:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel, value) as Boolean
}

fun NSValue.nonretainedObjectValue(): MemorySegment {
    val sel = ObjCRuntime.sel("nonretainedObjectValue")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun NSValue.pointerValue(): MemorySegment {
    val sel = ObjCRuntime.sel("pointerValue")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

// Class method: +[NSValue valueWithNonretainedObject:]
fun NSValue_valueWithNonretainedObject(anObject: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("valueWithNonretainedObject:")
    val cls = ObjCRuntime.getClass("NSValue")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel, anObject) as MemorySegment
}

// Class method: +[NSValue valueWithPointer:]
fun NSValue_valueWithPointer(pointer: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("valueWithPointer:")
    val cls = ObjCRuntime.getClass("NSValue")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel, pointer) as MemorySegment
}

// ── Category: NSDeprecated on NSValue ─────────────────────────────────────────

fun NSValue.getValue(value: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("getValue:")
    ObjCRuntime.msgSend(null, this.ptr, sel, value)
}

// ── Category: NSValueRangeExtensions on NSValue ─────────────────────────────────────────

fun NSValue.rangeValue(): MemorySegment {
    val sel = ObjCRuntime.sel("rangeValue")
    return ObjCRuntime.msgSend(MemoryLayout.structLayout(ValueLayout.JAVA_LONG.withName("location"), ValueLayout.JAVA_LONG.withName("length")).withName("_NSRange"), this.ptr, sel) as MemorySegment
}

// Class method: +[NSValue valueWithRange:]
fun NSValue_valueWithRange(range: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("valueWithRange:")
    val cls = ObjCRuntime.getClass("NSValue")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel, range) as MemorySegment
}

// ── Category: NSValueGeometryExtensions on NSValue ─────────────────────────────────────────

fun NSValue.pointValue(): MemorySegment {
    val sel = ObjCRuntime.sel("pointValue")
    return ObjCRuntime.msgSend(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("CGPoint"), this.ptr, sel) as MemorySegment
}

fun NSValue.sizeValue(): MemorySegment {
    val sel = ObjCRuntime.sel("sizeValue")
    return ObjCRuntime.msgSend(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("CGSize"), this.ptr, sel) as MemorySegment
}

fun NSValue.rectValue(): MemorySegment {
    val sel = ObjCRuntime.sel("rectValue")
    return ObjCRuntime.msgSend(MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"), this.ptr, sel) as MemorySegment
}

fun NSValue.edgeInsetsValue(): MemorySegment {
    val sel = ObjCRuntime.sel("edgeInsetsValue")
    return ObjCRuntime.msgSend(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("top"), ValueLayout.JAVA_DOUBLE.withName("left"), ValueLayout.JAVA_DOUBLE.withName("bottom"), ValueLayout.JAVA_DOUBLE.withName("right")).withName("NSEdgeInsets"), this.ptr, sel) as MemorySegment
}

// Class method: +[NSValue valueWithPoint:]
fun NSValue_valueWithPoint(point: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("valueWithPoint:")
    val cls = ObjCRuntime.getClass("NSValue")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel, point) as MemorySegment
}

// Class method: +[NSValue valueWithSize:]
fun NSValue_valueWithSize(size: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("valueWithSize:")
    val cls = ObjCRuntime.getClass("NSValue")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel, size) as MemorySegment
}

// Class method: +[NSValue valueWithRect:]
fun NSValue_valueWithRect(rect: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("valueWithRect:")
    val cls = ObjCRuntime.getClass("NSValue")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel, rect) as MemorySegment
}

// Class method: +[NSValue valueWithEdgeInsets:]
fun NSValue_valueWithEdgeInsets(insets: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("valueWithEdgeInsets:")
    val cls = ObjCRuntime.getClass("NSValue")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel, insets) as MemorySegment
}

// ── Category: CATransform3DAdditions on NSValue ─────────────────────────────────────────

fun NSValue.CATransform3DValue(): MemorySegment {
    val sel = ObjCRuntime.sel("CATransform3DValue")
    return ObjCRuntime.msgSend(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("m11"), ValueLayout.JAVA_DOUBLE.withName("m12"), ValueLayout.JAVA_DOUBLE.withName("m13"), ValueLayout.JAVA_DOUBLE.withName("m14"), ValueLayout.JAVA_DOUBLE.withName("m21"), ValueLayout.JAVA_DOUBLE.withName("m22"), ValueLayout.JAVA_DOUBLE.withName("m23"), ValueLayout.JAVA_DOUBLE.withName("m24"), ValueLayout.JAVA_DOUBLE.withName("m31"), ValueLayout.JAVA_DOUBLE.withName("m32"), ValueLayout.JAVA_DOUBLE.withName("m33"), ValueLayout.JAVA_DOUBLE.withName("m34"), ValueLayout.JAVA_DOUBLE.withName("m41"), ValueLayout.JAVA_DOUBLE.withName("m42"), ValueLayout.JAVA_DOUBLE.withName("m43"), ValueLayout.JAVA_DOUBLE.withName("m44")).withName("CATransform3D"), this.ptr, sel) as MemorySegment
}

// Class method: +[NSValue valueWithCATransform3D:]
fun NSValue_valueWithCATransform3D(t: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("valueWithCATransform3D:")
    val cls = ObjCRuntime.getClass("NSValue")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel, t) as MemorySegment
}

