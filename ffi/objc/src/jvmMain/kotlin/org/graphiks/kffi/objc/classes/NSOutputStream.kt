package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSOutputStream
 * Superclass: NSStream
 */
open class NSOutputStream(override val ptr: MemorySegment) : NSStream(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSOutputStream") }
        
    }
    
    open fun write_maxLength(buffer: MemorySegment, len: Long): Long {
        val sel = ObjCRuntime.sel("write:maxLength:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel, buffer, len) as Long
    }
    
    open fun initToMemory(): MemorySegment {
        val sel = ObjCRuntime.sel("initToMemory")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    open fun initToBuffer_capacity(buffer: MemorySegment, capacity: Long): MemorySegment {
        val sel = ObjCRuntime.sel("initToBuffer:capacity:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, buffer, capacity) as MemorySegment
    }
    
    open fun initWithURL_append(url: MemorySegment, shouldAppend: Boolean): MemorySegment {
        val sel = ObjCRuntime.sel("initWithURL:append:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, url, shouldAppend) as MemorySegment
    }
    
    // @property hasSpaceAvailable
    open fun hasSpaceAvailable(): Boolean {
        val sel = ObjCRuntime.sel("hasSpaceAvailable")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
}

// ── Category: NSOutputStreamExtensions on NSOutputStream ─────────────────────────────────────────

fun NSOutputStream.initToFileAtPath_append(path: MemorySegment, shouldAppend: Boolean): MemorySegment {
    val sel = ObjCRuntime.sel("initToFileAtPath:append:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, path, shouldAppend) as MemorySegment
}

// Class method: +[NSOutputStream outputStreamToMemory]
fun NSOutputStream_outputStreamToMemory(): MemorySegment {
    val sel = ObjCRuntime.sel("outputStreamToMemory")
    val cls = ObjCRuntime.getClass("NSOutputStream")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel) as MemorySegment
}

// Class method: +[NSOutputStream outputStreamToBuffer:capacity:]
fun NSOutputStream_outputStreamToBuffer_capacity(buffer: MemorySegment, capacity: Long): MemorySegment {
    val sel = ObjCRuntime.sel("outputStreamToBuffer:capacity:")
    val cls = ObjCRuntime.getClass("NSOutputStream")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel, buffer, capacity) as MemorySegment
}

// Class method: +[NSOutputStream outputStreamToFileAtPath:append:]
fun NSOutputStream_outputStreamToFileAtPath_append(path: MemorySegment, shouldAppend: Boolean): MemorySegment {
    val sel = ObjCRuntime.sel("outputStreamToFileAtPath:append:")
    val cls = ObjCRuntime.getClass("NSOutputStream")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel, path, shouldAppend) as MemorySegment
}

// Class method: +[NSOutputStream outputStreamWithURL:append:]
fun NSOutputStream_outputStreamWithURL_append(url: MemorySegment, shouldAppend: Boolean): MemorySegment {
    val sel = ObjCRuntime.sel("outputStreamWithURL:append:")
    val cls = ObjCRuntime.getClass("NSOutputStream")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel, url, shouldAppend) as MemorySegment
}

