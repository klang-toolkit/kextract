package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSBundleResourceRequest
 * Superclass: NSObject
 * Protocols: NSProgressReporting
 */
open class NSBundleResourceRequest(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSBundleResourceRequest") }
        
    }
    
    open fun init(): MemorySegment {
        val sel = ObjCRuntime.sel("init")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    open fun initWithTags(tags: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithTags:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, tags) as MemorySegment
    }
    
    open fun initWithTags_bundle(tags: MemorySegment, bundle: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithTags:bundle:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, tags, bundle) as MemorySegment
    }
    
    open fun beginAccessingResourcesWithCompletionHandler(completionHandler: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("beginAccessingResourcesWithCompletionHandler:")
        ObjCRuntime.msgSend(null, ptr, sel, completionHandler)
    }
    
    open fun conditionallyBeginAccessingResourcesWithCompletionHandler(completionHandler: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("conditionallyBeginAccessingResourcesWithCompletionHandler:")
        ObjCRuntime.msgSend(null, ptr, sel, completionHandler)
    }
    
    open fun endAccessingResources(): Unit {
        val sel = ObjCRuntime.sel("endAccessingResources")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    // @property loadingPriority
    open fun loadingPriority(): Double {
        val sel = ObjCRuntime.sel("loadingPriority")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_DOUBLE, ptr, sel) as Double
    }
    open fun setLoadingPriority(value: Double) {
        val sel = ObjCRuntime.sel("setLoadingPriority:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property tags
    /** @return NSSet<NSString *> * */
    open fun tags(): MemorySegment {
        val sel = ObjCRuntime.sel("tags")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property bundle
    open fun bundle(): MemorySegment {
        val sel = ObjCRuntime.sel("bundle")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property progress
    open fun progress(): MemorySegment {
        val sel = ObjCRuntime.sel("progress")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
}

