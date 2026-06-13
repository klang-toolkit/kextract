package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSDirectoryEnumerator
 * Superclass: NSEnumerator
 */
open class NSDirectoryEnumerator(override val ptr: MemorySegment) : NSEnumerator(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSDirectoryEnumerator") }
        
    }
    
    open fun skipDescendents(): Unit {
        val sel = ObjCRuntime.sel("skipDescendents")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun skipDescendants(): Unit {
        val sel = ObjCRuntime.sel("skipDescendants")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    // @property fileAttributes
    /** @return NSDictionary<NSFileAttributeKey,id> * */
    open fun fileAttributes(): MemorySegment {
        val sel = ObjCRuntime.sel("fileAttributes")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property directoryAttributes
    /** @return NSDictionary<NSFileAttributeKey,id> * */
    open fun directoryAttributes(): MemorySegment {
        val sel = ObjCRuntime.sel("directoryAttributes")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property isEnumeratingDirectoryPostOrder
    open fun isEnumeratingDirectoryPostOrder(): Boolean {
        val sel = ObjCRuntime.sel("isEnumeratingDirectoryPostOrder")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property level
    open fun level(): Long {
        val sel = ObjCRuntime.sel("level")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    
}

