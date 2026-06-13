package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSInputStream
 * Superclass: NSStream
 */
open class NSInputStream(override val ptr: MemorySegment) : NSStream(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSInputStream") }
        
    }
    
    open fun read_maxLength(buffer: MemorySegment, len: Long): Long {
        val sel = ObjCRuntime.sel("read:maxLength:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel, buffer, len) as Long
    }
    
    open fun getBuffer_length(buffer: MemorySegment, len: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("getBuffer:length:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, buffer, len) as Boolean
    }
    
    open fun initWithData(`data`: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithData:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, `data`) as MemorySegment
    }
    
    open fun initWithURL(url: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithURL:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, url) as MemorySegment
    }
    
    // @property hasBytesAvailable
    open fun hasBytesAvailable(): Boolean {
        val sel = ObjCRuntime.sel("hasBytesAvailable")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
}

// ── Category: NSInputStreamExtensions on NSInputStream ─────────────────────────────────────────

fun NSInputStream.initWithFileAtPath(path: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("initWithFileAtPath:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, path) as MemorySegment
}

// Class method: +[NSInputStream inputStreamWithData:]
fun NSInputStream_inputStreamWithData(`data`: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("inputStreamWithData:")
    val cls = ObjCRuntime.getClass("NSInputStream")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel, `data`) as MemorySegment
}

// Class method: +[NSInputStream inputStreamWithFileAtPath:]
fun NSInputStream_inputStreamWithFileAtPath(path: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("inputStreamWithFileAtPath:")
    val cls = ObjCRuntime.getClass("NSInputStream")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel, path) as MemorySegment
}

// Class method: +[NSInputStream inputStreamWithURL:]
fun NSInputStream_inputStreamWithURL(url: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("inputStreamWithURL:")
    val cls = ObjCRuntime.getClass("NSInputStream")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel, url) as MemorySegment
}

