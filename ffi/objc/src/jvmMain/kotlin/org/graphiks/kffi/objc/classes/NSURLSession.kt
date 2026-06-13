package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: NSURLSession
 * Superclass: NSObject
 */
open class NSURLSession(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("NSURLSession") }
        
        fun sessionWithConfiguration(configuration: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("sessionWithConfiguration:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, configuration) as MemorySegment
        }
        
        fun sessionWithConfiguration_delegate_delegateQueue(configuration: MemorySegment, delegate: MemorySegment, queue: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("sessionWithConfiguration:delegate:delegateQueue:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, configuration, delegate, queue) as MemorySegment
        }
        
        fun new(): MemorySegment {
            val sel = ObjCRuntime.sel("new")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun sharedSession(): MemorySegment {
            val sel = ObjCRuntime.sel("sharedSession")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
    }
    
    open fun finishTasksAndInvalidate(): Unit {
        val sel = ObjCRuntime.sel("finishTasksAndInvalidate")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun invalidateAndCancel(): Unit {
        val sel = ObjCRuntime.sel("invalidateAndCancel")
        ObjCRuntime.msgSend(null, ptr, sel)
    }
    
    open fun resetWithCompletionHandler(completionHandler: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("resetWithCompletionHandler:")
        ObjCRuntime.msgSend(null, ptr, sel, completionHandler)
    }
    
    open fun flushWithCompletionHandler(completionHandler: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("flushWithCompletionHandler:")
        ObjCRuntime.msgSend(null, ptr, sel, completionHandler)
    }
    
    open fun getTasksWithCompletionHandler(completionHandler: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("getTasksWithCompletionHandler:")
        ObjCRuntime.msgSend(null, ptr, sel, completionHandler)
    }
    
    open fun getAllTasksWithCompletionHandler(completionHandler: MemorySegment): Unit {
        val sel = ObjCRuntime.sel("getAllTasksWithCompletionHandler:")
        ObjCRuntime.msgSend(null, ptr, sel, completionHandler)
    }
    
    open fun dataTaskWithRequest(request: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("dataTaskWithRequest:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, request) as MemorySegment
    }
    
    open fun dataTaskWithURL(url: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("dataTaskWithURL:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, url) as MemorySegment
    }
    
    open fun uploadTaskWithRequest_fromFile(request: MemorySegment, fileURL: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("uploadTaskWithRequest:fromFile:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, request, fileURL) as MemorySegment
    }
    
    open fun uploadTaskWithRequest_fromData(request: MemorySegment, bodyData: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("uploadTaskWithRequest:fromData:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, request, bodyData) as MemorySegment
    }
    
    open fun uploadTaskWithResumeData(resumeData: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("uploadTaskWithResumeData:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, resumeData) as MemorySegment
    }
    
    open fun uploadTaskWithStreamedRequest(request: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("uploadTaskWithStreamedRequest:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, request) as MemorySegment
    }
    
    open fun downloadTaskWithRequest(request: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("downloadTaskWithRequest:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, request) as MemorySegment
    }
    
    open fun downloadTaskWithURL(url: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("downloadTaskWithURL:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, url) as MemorySegment
    }
    
    open fun downloadTaskWithResumeData(resumeData: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("downloadTaskWithResumeData:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, resumeData) as MemorySegment
    }
    
    open fun streamTaskWithHostName_port(hostname: MemorySegment, port: Long): MemorySegment {
        val sel = ObjCRuntime.sel("streamTaskWithHostName:port:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, hostname, port) as MemorySegment
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun streamTaskWithHostName_port(hostname: String, port: Long): MemorySegment = streamTaskWithHostName_port(ObjCRuntime.newNSString(Arena.global(), hostname), port)
    
    open fun streamTaskWithNetService(service: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("streamTaskWithNetService:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, service) as MemorySegment
    }
    
    open fun webSocketTaskWithURL(url: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("webSocketTaskWithURL:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, url) as MemorySegment
    }
    
    open fun webSocketTaskWithURL_protocols(url: MemorySegment, protocols: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("webSocketTaskWithURL:protocols:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, url, protocols) as MemorySegment
    }
    
    open fun webSocketTaskWithRequest(request: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("webSocketTaskWithRequest:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, request) as MemorySegment
    }
    
    open fun init(): MemorySegment {
        val sel = ObjCRuntime.sel("init")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property sharedSession
    open fun sharedSession(): MemorySegment {
        val sel = ObjCRuntime.sel("sharedSession")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property delegateQueue
    open fun delegateQueue(): MemorySegment {
        val sel = ObjCRuntime.sel("delegateQueue")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property delegate
    /** @return id<NSURLSessionDelegate> */
    open fun delegate(): MemorySegment {
        val sel = ObjCRuntime.sel("delegate")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property configuration
    open fun configuration(): MemorySegment {
        val sel = ObjCRuntime.sel("configuration")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property sessionDescription
    open fun sessionDescription(): MemorySegment {
        val sel = ObjCRuntime.sel("sessionDescription")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    open fun setSessionDescription(value: MemorySegment) {
        val sel = ObjCRuntime.sel("setSessionDescription:")
        ObjCRuntime.msgSend(null, ptr, sel, value)
    }
    
    /** Convenience overload — returns Kotlin [String] by converting the NSString via UTF8String. */
    open fun sessionDescriptionAsString(): String = ObjCRuntime.toJavaString(sessionDescription())
    
    /** Convenience overload — accepts Kotlin [String] for the NSString property. */
    open fun setSessionDescription(value: String) = setSessionDescription(ObjCRuntime.newNSString(Arena.global(), value))
    
}

// ── Category: NSURLSessionAsynchronousConvenience on NSURLSession ─────────────────────────────────────────

fun NSURLSession.dataTaskWithRequest_completionHandler(request: MemorySegment, completionHandler: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("dataTaskWithRequest:completionHandler:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, request, completionHandler) as MemorySegment
}

fun NSURLSession.dataTaskWithURL_completionHandler(url: MemorySegment, completionHandler: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("dataTaskWithURL:completionHandler:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, url, completionHandler) as MemorySegment
}

fun NSURLSession.uploadTaskWithRequest_fromFile_completionHandler(request: MemorySegment, fileURL: MemorySegment, completionHandler: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("uploadTaskWithRequest:fromFile:completionHandler:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, request, fileURL, completionHandler) as MemorySegment
}

fun NSURLSession.uploadTaskWithRequest_fromData_completionHandler(request: MemorySegment, bodyData: MemorySegment, completionHandler: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("uploadTaskWithRequest:fromData:completionHandler:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, request, bodyData, completionHandler) as MemorySegment
}

fun NSURLSession.uploadTaskWithResumeData_completionHandler(resumeData: MemorySegment, completionHandler: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("uploadTaskWithResumeData:completionHandler:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, resumeData, completionHandler) as MemorySegment
}

fun NSURLSession.downloadTaskWithRequest_completionHandler(request: MemorySegment, completionHandler: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("downloadTaskWithRequest:completionHandler:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, request, completionHandler) as MemorySegment
}

fun NSURLSession.downloadTaskWithURL_completionHandler(url: MemorySegment, completionHandler: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("downloadTaskWithURL:completionHandler:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, url, completionHandler) as MemorySegment
}

fun NSURLSession.downloadTaskWithResumeData_completionHandler(resumeData: MemorySegment, completionHandler: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("downloadTaskWithResumeData:completionHandler:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, resumeData, completionHandler) as MemorySegment
}

