package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSPipe
 * Superclass: NSObject
 */
open class NSPipe(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSPipe") }
        
        fun pipe(): MemorySegment {
            val sel = ObjCRuntime.sel("pipe")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
    }
    
    // @property fileHandleForReading
    open fun fileHandleForReading(): MemorySegment {
        val sel = ObjCRuntime.sel("fileHandleForReading")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property fileHandleForWriting
    open fun fileHandleForWriting(): MemorySegment {
        val sel = ObjCRuntime.sel("fileHandleForWriting")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
}

