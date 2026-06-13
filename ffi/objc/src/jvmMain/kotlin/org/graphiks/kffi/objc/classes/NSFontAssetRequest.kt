package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSFontAssetRequest
 * Superclass: NSObject
 * Protocols: NSProgressReporting
 */
open class NSFontAssetRequest(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSFontAssetRequest") }
        
    }
    
    open fun init(): MemorySegment {
        val sel = ObjCRuntime.sel("init")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    open fun initWithFontDescriptors_options(fontDescriptors: MemorySegment, options: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithFontDescriptors:options:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, fontDescriptors, options) as MemorySegment
    }
    
    open fun downloadFontAssetsWithCompletionHandler(completionHandler: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("downloadFontAssetsWithCompletionHandler:")
        ObjCRuntime.msgSend(null, ptr, sel, completionHandler)
    }
    
    // @property downloadedFontDescriptors
    /** @return NSArray<NSFontDescriptor *> * */
    open fun downloadedFontDescriptors(): MemorySegment {
        val sel = ObjCRuntime.sel("downloadedFontDescriptors")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property progress
    open fun progress(): MemorySegment {
        val sel = ObjCRuntime.sel("progress")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
}

