package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSURLDownload
 * Superclass: NSObject
 */
open class NSURLDownload(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSURLDownload") }
        
        fun canResumeDownloadDecodedWithEncodingMIMEType(MIMEType: MemorySegment): Boolean {
            val sel = ObjCRuntime.sel("canResumeDownloadDecodedWithEncodingMIMEType:")
            return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, _class, sel, MIMEType) as Boolean
        }
        
        /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
        fun canResumeDownloadDecodedWithEncodingMIMEType(MIMEType: String): Boolean = canResumeDownloadDecodedWithEncodingMIMEType(ObjCRuntime.newNSString(Arena.global(), MIMEType))
        
    }
    
    open fun initWithRequest_delegate(request: MemorySegment, delegate: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithRequest:delegate:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, request, delegate) as MemorySegment
    }
    
    open fun initWithResumeData_delegate_path(resumeData: MemorySegment, delegate: MemorySegment, path: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithResumeData:delegate:path:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, resumeData, delegate, path) as MemorySegment
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun initWithResumeData_delegate_path(resumeData: MemorySegment, delegate: MemorySegment, path: String): MemorySegment = initWithResumeData_delegate_path(resumeData, delegate, ObjCRuntime.newNSString(Arena.global(), path))
    
    open fun cancel(): Unit {
        val sel = ObjCRuntime.sel("cancel")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun setDestination_allowOverwrite(path: MemorySegment, allowOverwrite: Boolean): Unit {
        val sel = ObjCRuntime.sel("setDestination:allowOverwrite:")
        ObjCRuntime.msgSend(null, ptr, sel, path, allowOverwrite)
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun setDestination_allowOverwrite(path: String, allowOverwrite: Boolean): Unit = setDestination_allowOverwrite(ObjCRuntime.newNSString(Arena.global(), path), allowOverwrite)
    
    // @property request
    open fun request(): MemorySegment {
        val sel = ObjCRuntime.sel("request")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property resumeData
    open fun resumeData(): MemorySegment {
        val sel = ObjCRuntime.sel("resumeData")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property deletesFileUponFailure
    open fun deletesFileUponFailure(): Boolean {
        val sel = ObjCRuntime.sel("deletesFileUponFailure")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    open fun setDeletesFileUponFailure(value: Boolean) {
        val sel = ObjCRuntime.sel("setDeletesFileUponFailure:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    
    // ── Instance variables (direct field access not supported via Panama) ──
    // ivar: _internal: MemorySegment
}

