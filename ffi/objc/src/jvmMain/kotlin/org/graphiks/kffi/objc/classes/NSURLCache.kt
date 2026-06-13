package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSURLCache
 * Superclass: NSObject
 */
open class NSURLCache(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSURLCache") }
        
        fun sharedURLCache(): MemorySegment {
            val sel = ObjCRuntime.sel("sharedURLCache")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun setSharedURLCache(sharedURLCache: MemorySegment): Unit {
            val sel = ObjCRuntime.sel("setSharedURLCache:")
            ObjCRuntime.msgSend(null, _class, sel, sharedURLCache)
        }
        
    }
    
    open fun initWithMemoryCapacity_diskCapacity_diskPath(memoryCapacity: Long, diskCapacity: Long, path: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithMemoryCapacity:diskCapacity:diskPath:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, memoryCapacity, diskCapacity, path) as MemorySegment
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun initWithMemoryCapacity_diskCapacity_diskPath(memoryCapacity: Long, diskCapacity: Long, path: String): MemorySegment = initWithMemoryCapacity_diskCapacity_diskPath(memoryCapacity, diskCapacity, ObjCRuntime.newNSString(Arena.global(), path))
    
    open fun initWithMemoryCapacity_diskCapacity_directoryURL(memoryCapacity: Long, diskCapacity: Long, directoryURL: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithMemoryCapacity:diskCapacity:directoryURL:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, memoryCapacity, diskCapacity, directoryURL) as MemorySegment
    }
    
    open fun cachedResponseForRequest(request: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("cachedResponseForRequest:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, request) as MemorySegment
    }
    
    open fun storeCachedResponse_forRequest(cachedResponse: MemorySegment, request: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("storeCachedResponse:forRequest:")
        ObjCRuntime.msgSend(null, ptr, sel, cachedResponse, request)
    }
    
    open fun removeCachedResponseForRequest(request: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("removeCachedResponseForRequest:")
        ObjCRuntime.msgSend(null, ptr, sel, request)
    }
    
    open fun removeAllCachedResponses(): Unit {
        val sel = ObjCRuntime.sel("removeAllCachedResponses")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun removeCachedResponsesSinceDate(date: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("removeCachedResponsesSinceDate:")
        ObjCRuntime.msgSend(null, ptr, sel, date)
    }
    
    // @property sharedURLCache
    open fun sharedURLCache(): MemorySegment {
        val sel = ObjCRuntime.sel("sharedURLCache")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setSharedURLCache(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setSharedURLCache:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property memoryCapacity
    open fun memoryCapacity(): Long {
        val sel = ObjCRuntime.sel("memoryCapacity")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    open fun setMemoryCapacity(value: Long) {
        val sel = ObjCRuntime.sel("setMemoryCapacity:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property diskCapacity
    open fun diskCapacity(): Long {
        val sel = ObjCRuntime.sel("diskCapacity")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    open fun setDiskCapacity(value: Long) {
        val sel = ObjCRuntime.sel("setDiskCapacity:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    // @property currentMemoryUsage
    open fun currentMemoryUsage(): Long {
        val sel = ObjCRuntime.sel("currentMemoryUsage")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    
    // @property currentDiskUsage
    open fun currentDiskUsage(): Long {
        val sel = ObjCRuntime.sel("currentDiskUsage")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_LONG, ptr, sel) as Long
    }
    
    
    // ── Instance variables (direct field access not supported via Panama) ──
    // ivar: _internal: MemorySegment
}

// ── Category: NSURLSessionTaskAdditions on NSURLCache ─────────────────────────────────────────

fun NSURLCache.storeCachedResponse_forDataTask(cachedResponse: MemorySegment, dataTask: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("storeCachedResponse:forDataTask:")
    ObjCRuntime.msgSend(null, this.ptr, sel, cachedResponse, dataTask)
}

fun NSURLCache.getCachedResponseForDataTask_completionHandler(dataTask: MemorySegment, completionHandler: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("getCachedResponseForDataTask:completionHandler:")
    ObjCRuntime.msgSend(null, this.ptr, sel, dataTask, completionHandler)
}

fun NSURLCache.removeCachedResponseForDataTask(dataTask: MemorySegment): Unit {
    val sel = ObjCRuntime.sel("removeCachedResponseForDataTask:")
    ObjCRuntime.msgSend(null, this.ptr, sel, dataTask)
}

