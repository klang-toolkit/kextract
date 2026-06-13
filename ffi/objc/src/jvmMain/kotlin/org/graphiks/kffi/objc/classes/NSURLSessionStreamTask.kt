package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSURLSessionStreamTask
 * Superclass: NSURLSessionTask
 */
open class NSURLSessionStreamTask(override val ptr: MemorySegment) : NSURLSessionTask(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSURLSessionStreamTask") }
        
        fun new(): MemorySegment {
            val sel = ObjCRuntime.sel("new")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
    }
    
    open fun readDataOfMinLength_maxLength_timeout_completionHandler(minBytes: Long, maxBytes: Long, timeout: Double, completionHandler: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("readDataOfMinLength:maxLength:timeout:completionHandler:")
        ObjCRuntime.msgSend(null, ptr, sel, minBytes, maxBytes, timeout, completionHandler)
    }
    
    open fun writeData_timeout_completionHandler(`data`: MemorySegment, timeout: Double, completionHandler: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("writeData:timeout:completionHandler:")
        ObjCRuntime.msgSend(null, ptr, sel, `data`, timeout, completionHandler)
    }
    
    open fun captureStreams(): Unit {
        val sel = ObjCRuntime.sel("captureStreams")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun closeWrite(): Unit {
        val sel = ObjCRuntime.sel("closeWrite")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun closeRead(): Unit {
        val sel = ObjCRuntime.sel("closeRead")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun startSecureConnection(): Unit {
        val sel = ObjCRuntime.sel("startSecureConnection")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun stopSecureConnection(): Unit {
        val sel = ObjCRuntime.sel("stopSecureConnection")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    override fun init(): MemorySegment {
        val sel = ObjCRuntime.sel("init")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
}

