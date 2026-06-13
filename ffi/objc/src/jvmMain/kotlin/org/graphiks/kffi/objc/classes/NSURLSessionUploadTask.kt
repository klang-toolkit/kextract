package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSURLSessionUploadTask
 * Superclass: NSURLSessionDataTask
 */
open class NSURLSessionUploadTask(override val ptr: MemorySegment) : NSURLSessionDataTask(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSURLSessionUploadTask") }
        
        fun new(): MemorySegment {
            val sel = ObjCRuntime.sel("new")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
    }
    
    override fun init(): MemorySegment {
        val sel = ObjCRuntime.sel("init")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    open fun cancelByProducingResumeData(completionHandler: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("cancelByProducingResumeData:")
        ObjCRuntime.msgSend(null, ptr, sel, completionHandler)
    }
    
}

