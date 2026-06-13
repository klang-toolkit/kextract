package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSExtensionContext
 * Superclass: NSObject
 */
open class NSExtensionContext(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSExtensionContext") }
        
    }
    
    open fun completeRequestReturningItems_completionHandler(items: MemorySegment, completionHandler: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("completeRequestReturningItems:completionHandler:")
        ObjCRuntime.msgSend(null, ptr, sel, items, completionHandler)
    }
    
    open fun cancelRequestWithError(error: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("cancelRequestWithError:")
        ObjCRuntime.msgSend(null, ptr, sel, error)
    }
    
    open fun openURL_completionHandler(URL: MemorySegment, completionHandler: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("openURL:completionHandler:")
        ObjCRuntime.msgSend(null, ptr, sel, URL, completionHandler)
    }
    
    // @property inputItems
    open fun inputItems(): MemorySegment {
        val sel = ObjCRuntime.sel("inputItems")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
}

