package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSFileAccessIntent
 * Superclass: NSObject
 */
open class NSFileAccessIntent(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSFileAccessIntent") }
        
        fun readingIntentWithURL_options(url: MemorySegment, options: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("readingIntentWithURL:options:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, url, options) as MemorySegment
        }
        
        fun writingIntentWithURL_options(url: MemorySegment, options: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("writingIntentWithURL:options:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, url, options) as MemorySegment
        }
        
    }
    
    // @property URL
    open fun URL(): MemorySegment {
        val sel = ObjCRuntime.sel("URL")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
}

