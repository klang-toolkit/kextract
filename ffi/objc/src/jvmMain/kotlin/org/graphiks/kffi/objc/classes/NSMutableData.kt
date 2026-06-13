package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSMutableData
 * Superclass: NSData
 */
open class NSMutableData(override val ptr: MemorySegment) : NSData(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSMutableData") }
        
    }
    
    // @property mutableBytes
    open fun mutableBytes(): MemorySegment {
        val sel = ObjCRuntime.sel("mutableBytes")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property length
    override fun length(): Long {
        val sel = ObjCRuntime.sel("length")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    open fun setLength(value: Long) {
        val sel = ObjCRuntime.sel("setLength:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
}

// ── Category: NSExtendedMutableData on NSMutableData ─────────────────────────────────────────

fun NSMutableData.appendBytes_length(bytes: MemorySegment, length: Long): Unit {
    val sel = ObjCRuntime.sel("appendBytes:length:")
    ObjCRuntime.msgSend(null, this.ptr, sel, bytes, length)
}

fun NSMutableData.appendData(other: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("appendData:")
    ObjCRuntime.msgSend(null, this.ptr, sel, other)
}

fun NSMutableData.increaseLengthBy(extraLength: Long): Unit {
    val sel = ObjCRuntime.sel("increaseLengthBy:")
    ObjCRuntime.msgSend(null, this.ptr, sel, extraLength)
}

fun NSMutableData.replaceBytesInRange_withBytes(range: MemorySegment, bytes: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("replaceBytesInRange:withBytes:")
    ObjCRuntime.msgSend(null, this.ptr, sel, range, bytes)
}

fun NSMutableData.resetBytesInRange(range: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("resetBytesInRange:")
    ObjCRuntime.msgSend(null, this.ptr, sel, range)
}

fun NSMutableData.setData(`data`: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("setData:")
    ObjCRuntime.msgSend(null, this.ptr, sel, `data`)
}

fun NSMutableData.replaceBytesInRange_withBytes_length(range: MemorySegment, replacementBytes: MemorySegment, replacementLength: Long): Unit {
    val sel = ObjCRuntime.sel("replaceBytesInRange:withBytes:length:")
    ObjCRuntime.msgSend(null, this.ptr, sel, range, replacementBytes, replacementLength)
}

// ── Category: NSMutableDataCreation on NSMutableData ─────────────────────────────────────────

fun NSMutableData.initWithCapacity(capacity: Long): MemorySegment {
    val sel = ObjCRuntime.sel("initWithCapacity:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, capacity) as MemorySegment
}

fun NSMutableData.initWithLength(length: Long): MemorySegment {
    val sel = ObjCRuntime.sel("initWithLength:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, length) as MemorySegment
}

// Class method: +[NSMutableData dataWithCapacity:]
fun NSMutableData_dataWithCapacity(aNumItems: Long): MemorySegment {
    val sel = ObjCRuntime.sel("dataWithCapacity:")
    val cls = ObjCRuntime.getClass("NSMutableData")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel, aNumItems) as MemorySegment
}

// Class method: +[NSMutableData dataWithLength:]
fun NSMutableData_dataWithLength(length: Long): MemorySegment {
    val sel = ObjCRuntime.sel("dataWithLength:")
    val cls = ObjCRuntime.getClass("NSMutableData")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel, length) as MemorySegment
}

// ── Category: NSMutableDataCompression on NSMutableData ─────────────────────────────────────────

fun NSMutableData.decompressUsingAlgorithm_error(algorithm: MemorySegment, error: MemorySegment): Boolean {
    val sel = ObjCRuntime.sel("decompressUsingAlgorithm:error:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel, algorithm, error) as Boolean
}

fun NSMutableData.compressUsingAlgorithm_error(algorithm: MemorySegment, error: MemorySegment): Boolean {
    val sel = ObjCRuntime.sel("compressUsingAlgorithm:error:")
    return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, this.ptr, sel, algorithm, error) as Boolean
}

