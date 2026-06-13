package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSStream
 * Superclass: NSObject
 */
open class NSStream(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSStream") }
        
    }
    
    open fun open(): Unit {
        val sel = ObjCRuntime.sel("open")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun close(): Unit {
        val sel = ObjCRuntime.sel("close")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun propertyForKey(key: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("propertyForKey:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, key) as MemorySegment
    }
    
    open fun setProperty_forKey(property: MemorySegment, key: MemorySegment): Boolean {
        val sel = ObjCRuntime.sel("setProperty:forKey:")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel, property, key) as Boolean
    }
    
    open fun scheduleInRunLoop_forMode(aRunLoop: MemorySegment, mode: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("scheduleInRunLoop:forMode:")
        ObjCRuntime.msgSend(null, ptr, sel, aRunLoop, mode)
    }
    
    open fun removeFromRunLoop_forMode(aRunLoop: MemorySegment, mode: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("removeFromRunLoop:forMode:")
        ObjCRuntime.msgSend(null, ptr, sel, aRunLoop, mode)
    }
    
    // @property delegate
    /** @return id<NSStreamDelegate> */
    open fun delegate(): MemorySegment {
        val sel = ObjCRuntime.sel("delegate")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setDelegate(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setDelegate:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property streamStatus
    open fun streamStatus(): MemorySegment {
        val sel = ObjCRuntime.sel("streamStatus")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property streamError
    open fun streamError(): MemorySegment {
        val sel = ObjCRuntime.sel("streamError")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
}

// ── Category: NSSocketStreamCreationExtensions on NSStream ─────────────────────────────────────────

// Class method: +[NSStream getStreamsToHostWithName:port:inputStream:outputStream:]
fun NSStream_getStreamsToHostWithName_port_inputStream_outputStream(hostname: MemorySegment, port: Long, inputStream: MemorySegment, outputStream: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("getStreamsToHostWithName:port:inputStream:outputStream:")
    val cls = ObjCRuntime.getClass("NSStream")
    ObjCRuntime.msgSend(null, cls, sel, hostname, port, inputStream, outputStream)
}

// Class method: +[NSStream getStreamsToHost:port:inputStream:outputStream:]
fun NSStream_getStreamsToHost_port_inputStream_outputStream(host: MemorySegment, port: Long, inputStream: MemorySegment, outputStream: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("getStreamsToHost:port:inputStream:outputStream:")
    val cls = ObjCRuntime.getClass("NSStream")
    ObjCRuntime.msgSend(null, cls, sel, host, port, inputStream, outputStream)
}

// ── Category: NSStreamBoundPairCreationExtensions on NSStream ─────────────────────────────────────────

// Class method: +[NSStream getBoundStreamsWithBufferSize:inputStream:outputStream:]
fun NSStream_getBoundStreamsWithBufferSize_inputStream_outputStream(bufferSize: Long, inputStream: MemorySegment, outputStream: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("getBoundStreamsWithBufferSize:inputStream:outputStream:")
    val cls = ObjCRuntime.getClass("NSStream")
    ObjCRuntime.msgSend(null, cls, sel, bufferSize, inputStream, outputStream)
}

